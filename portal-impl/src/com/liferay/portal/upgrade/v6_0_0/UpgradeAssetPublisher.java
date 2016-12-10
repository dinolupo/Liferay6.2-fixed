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

package com.liferay.portal.upgrade.v6_0_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.upgrade.util.UpgradeAssetPublisherManualEntries;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Julio Camarero
 * @author Douglas Wong
 */
public class UpgradeAssetPublisher extends BaseUpgradePortletPreferences {

	protected String[] getAssetEntryXmls(String[] manualEntries)
		throws Exception {

		String[] assetEntryXmls = new String[manualEntries.length];

		for (int i = 0; i < manualEntries.length; i++) {
			String manualEntry = manualEntries[i];

			Document document = SAXReaderUtil.read(manualEntry);

			Element rootElement = document.getRootElement();

			UpgradeAssetPublisherManualEntries.upgradeToAssetEntryIdElement(
				rootElement);

			UpgradeAssetPublisherManualEntries.upgradeToAssetEntryTypeElement(
				rootElement);

			assetEntryXmls[i] = document.formattedString(StringPool.BLANK);
		}

		return assetEntryXmls;
	}

	@Override
	protected String[] getPortletIds() {
		return new String[] {"101_INSTANCE_%"};
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		long layoutId = GetterUtil.getLong(
			portletPreferences.getValue("lfr-scope-layout-id", null));

		portletPreferences.reset("lfr-scope-layout-id");

		if (layoutId != 0) {
			portletPreferences.setValues(
				"scope-ids", new String[] {"Layout_" + layoutId});

			portletPreferences.setValue(
				"default-scope", Boolean.FALSE.toString());
		}

		long classNameId = GetterUtil.getLong(
			portletPreferences.getValue("class-name-id", null));

		portletPreferences.reset("class-name-id");

		if (classNameId != 0) {
			portletPreferences.setValues(
				"class-name-ids", new String[] {String.valueOf(classNameId)});

			portletPreferences.setValue(
				"any-asset-type", Boolean.FALSE.toString());
		}

		boolean andOperator = GetterUtil.getBoolean(
			portletPreferences.getValue("and-operator", null));

		portletPreferences.reset("and-operator");

		String[] assetTagNames = portletPreferences.getValues("entries", null);
		String[] notAssetTagNames = portletPreferences.getValues(
			"not-entries", null);

		int i = 0;

		if (assetTagNames != null) {
			portletPreferences.reset("entries");

			portletPreferences.setValue(
				"queryContains" + i, Boolean.TRUE.toString());
			portletPreferences.setValue(
				"queryAndOperator" + i, String.valueOf(andOperator));
			portletPreferences.setValue("queryName" + i, "assetTags");
			portletPreferences.setValues("queryValues" + i, assetTagNames);

			i++;
		}

		if (notAssetTagNames != null) {
			portletPreferences.reset("not-entries");

			portletPreferences.setValue(
				"queryContains" + i, Boolean.FALSE.toString());
			portletPreferences.setValue(
				"queryAndOperator" + i, String.valueOf(andOperator));
			portletPreferences.setValue("queryName" + i, "assetTags");
			portletPreferences.setValues("queryValues" + i, notAssetTagNames);

			i++;
		}

		String selectionStyle = portletPreferences.getValue(
			"selection-style", null);

		if (Validator.isNotNull(selectionStyle) &&
			!selectionStyle.equals("dynamic")) {

			String[] manualEntries = portletPreferences.getValues(
				"manual-entries", new String[0]);

			String[] assetEntryXmls = getAssetEntryXmls(manualEntries);

			portletPreferences.setValues("asset-entry-xml", assetEntryXmls);
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

}