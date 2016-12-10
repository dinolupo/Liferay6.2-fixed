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

package com.liferay.portlet.blogs.action;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.blogs.service.BlogsEntryServiceUtil;
import com.liferay.util.RSSUtil;

import java.util.Date;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class RSSAction extends com.liferay.portal.struts.RSSAction {

	@Override
	protected byte[] getRSS(HttpServletRequest request) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		long plid = ParamUtil.getLong(request, "p_l_id");
		long companyId = ParamUtil.getLong(request, "companyId");
		long groupId = ParamUtil.getLong(request, "groupId");
		long organizationId = ParamUtil.getLong(request, "organizationId");
		int status = WorkflowConstants.STATUS_APPROVED;
		int max = ParamUtil.getInteger(
			request, "max", SearchContainer.DEFAULT_DELTA);
		String type = ParamUtil.getString(
			request, "type", RSSUtil.FORMAT_DEFAULT);
		double version = ParamUtil.getDouble(
			request, "version", RSSUtil.VERSION_DEFAULT);
		String displayStyle = ParamUtil.getString(
			request, "displayStyle", RSSUtil.DISPLAY_STYLE_DEFAULT);

		String feedURL =
			themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
				"/blogs/find_entry?";

		String entryURL = feedURL;

		String rss = StringPool.BLANK;

		if (companyId > 0) {
			feedURL = StringPool.BLANK;

			rss = BlogsEntryServiceUtil.getCompanyEntriesRSS(
				companyId, new Date(), status, max, type, version, displayStyle,
				feedURL, entryURL, themeDisplay);
		}
		else if (groupId > 0) {
			feedURL += "p_l_id=" + plid;

			entryURL = feedURL;

			rss = BlogsEntryServiceUtil.getGroupEntriesRSS(
				groupId, new Date(), status, max, type, version, displayStyle,
				feedURL, entryURL, themeDisplay);
		}
		else if (organizationId > 0) {
			feedURL = StringPool.BLANK;

			rss = BlogsEntryServiceUtil.getOrganizationEntriesRSS(
				organizationId, new Date(), status, max, type, version,
				displayStyle, feedURL, entryURL, themeDisplay);
		}
		else if (layout != null) {
			groupId = themeDisplay.getScopeGroupId();

			feedURL =
				PortalUtil.getLayoutFullURL(themeDisplay) +
					Portal.FRIENDLY_URL_SEPARATOR + "blogs/rss";

			entryURL = feedURL;

			rss = BlogsEntryServiceUtil.getGroupEntriesRSS(
				groupId, new Date(), status, max, type, version, displayStyle,
				feedURL, entryURL, themeDisplay);
		}

		return rss.getBytes(StringPool.UTF8);
	}

	@Override
	protected boolean isRSSFeedsEnabled(PortletRequest portletRequest)
		throws Exception {

		if (!super.isRSSFeedsEnabled(portletRequest)) {
			return false;
		}

		PortletPreferences portletPreferences = portletRequest.getPreferences();

		return GetterUtil.getBoolean(
			portletPreferences.getValue("enableRss", null), true);
	}

}