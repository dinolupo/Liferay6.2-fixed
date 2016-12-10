/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.staging.Staging;
import com.liferay.portal.kernel.staging.StagingConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Joshua Gok
 */
public class VerifyPortalPreferences extends VerifyProcess {

	protected String convertStagingPreferencesToJSON(String preferences)
		throws Exception {

		Document newDocument = SAXReaderUtil.createDocument();

		Element newRootElement = SAXReaderUtil.createElement(
			"portlet-preferences");

		newDocument.add(newRootElement);

		Document document = SAXReaderUtil.read(preferences);

		Element rootElement = document.getRootElement();

		Iterator<Element> iterator = rootElement.elementIterator();

		Map<String, String> stagingPreferencesMap =
			new HashMap<String, String>();

		while (iterator.hasNext()) {
			Element preferenceElement = iterator.next();

			String preferenceName = preferenceElement.elementText("name");

			if (preferenceName.contains(Staging.class.getName())) {
				String preferenceValue = preferenceElement.elementText("value");

				int index = preferenceName.indexOf(StringPool.POUND);

				stagingPreferencesMap.put(
					preferenceName.substring(index + 1), preferenceValue);
			}
			else {
				newRootElement.add(preferenceElement.createCopy());
			}
		}

		JSONArray stagingPreferencesJsonArray =
			JSONFactoryUtil.createJSONArray();

		for (String key : stagingPreferencesMap.keySet()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(key, stagingPreferencesMap.get(key));

			stagingPreferencesJsonArray.put(jsonObject);
		}

		if (stagingPreferencesJsonArray.length() > 0) {
			Element preferenceElement = SAXReaderUtil.createElement(
				"preference");

			Element nameElement = SAXReaderUtil.createElement("name");

			String stagingPreferencesName =
				Staging.class.getName() + StringPool.POUND +
				StagingConstants.STAGING_RECENT_LAYOUT_IDS_MAP;

			nameElement.setText(stagingPreferencesName);

			Element valueElement = SAXReaderUtil.createElement("value");

			valueElement.setText(stagingPreferencesJsonArray.toString());

			preferenceElement.add(nameElement);
			preferenceElement.add(valueElement);

			newRootElement.add(preferenceElement);
		}

		return DDMXMLUtil.formatXML(newDocument);
	}

	@Override
	protected void doVerify() throws Exception {
		updatePortalPreferences();
	}

	protected void updatePortalPreferences() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select portalPreferencesId, preferences from " +
					"PortalPreferences");

			rs = ps.executeQuery();

			while (rs.next()) {
				long portalPreferencesId = rs.getLong("portalPreferencesId");

				String preferences = rs.getString("preferences");

				updateUserStagingPreferences(portalPreferencesId, preferences);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateUserStagingPreferences(
			long portalPreferencesId, String preferences)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"update PortalPreferences set preferences = ? where " +
					"portalPreferencesId = ?");

			ps.setString(1, convertStagingPreferencesToJSON(preferences));
			ps.setLong(2, portalPreferencesId);
			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

}