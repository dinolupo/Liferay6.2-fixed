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

package com.liferay.portal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.util.ContentUtil;

/**
 * @author David Truong
 */
public class RobotsUtil {

	public static String getDefaultRobots() {
		return getDefaultRobots(null);
	}

	public static String getDefaultRobots(String virtualHost) {
		if (Validator.isNotNull(virtualHost)) {
			String content = ContentUtil.get(
				PropsValues.ROBOTS_TXT_WITH_SITEMAP);

			content = StringUtil.replace(content, "[$HOST$]", virtualHost);

			return content;
		}

		return ContentUtil.get(PropsValues.ROBOTS_TXT_WITHOUT_SITEMAP);
	}

	public static String getRobots(LayoutSet layoutSet)
		throws PortalException, SystemException {

		if (layoutSet == null) {
			return getDefaultRobots(null);
		}

		String virtualHostname = StringPool.BLANK;

		try {
			virtualHostname = layoutSet.getVirtualHostname();
		}
		catch (Exception e) {
		}

		if (Validator.isNull(virtualHostname) &&
			Validator.isNotNull(PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME) ) {

			Group group = GroupLocalServiceUtil.getGroup(
				layoutSet.getCompanyId(),
				PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME);

			if (layoutSet.getGroupId() == group.getGroupId()) {
				Company company = CompanyLocalServiceUtil.getCompany(
					layoutSet.getCompanyId());

				virtualHostname = company.getVirtualHostname();
			}
		}

		Group group = layoutSet.getGroup();

		return GetterUtil.get(
			group.getTypeSettingsProperty(
				layoutSet.isPrivateLayout() + "-robots.txt"),
				getDefaultRobots(virtualHostname));
	}

}