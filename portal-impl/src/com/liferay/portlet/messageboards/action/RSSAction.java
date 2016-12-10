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

package com.liferay.portlet.messageboards.action;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;
import com.liferay.util.RSSUtil;

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

		String plid = ParamUtil.getString(request, "p_l_id");
		long companyId = ParamUtil.getLong(request, "companyId");
		long groupId = ParamUtil.getLong(request, "groupId");
		long userId = ParamUtil.getLong(request, "userId");
		long categoryId = ParamUtil.getLong(request, "mbCategoryId");
		long threadId = ParamUtil.getLong(request, "threadId");
		int max = ParamUtil.getInteger(
			request, "max", SearchContainer.DEFAULT_DELTA);
		String type = ParamUtil.getString(
			request, "type", RSSUtil.FORMAT_DEFAULT);
		double version = ParamUtil.getDouble(
			request, "version", RSSUtil.VERSION_DEFAULT);
		String displayStyle = ParamUtil.getString(
			request, "displayStyle", RSSUtil.DISPLAY_STYLE_DEFAULT);

		String entryURL =
			themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
				"/message_boards/find_message?p_l_id=" + plid;

		String rss = StringPool.BLANK;

		if (companyId > 0) {
			String feedURL = StringPool.BLANK;

			rss = MBMessageServiceUtil.getCompanyMessagesRSS(
				companyId, WorkflowConstants.STATUS_APPROVED, max, type,
				version, displayStyle, feedURL, entryURL, themeDisplay);
		}
		else if (groupId > 0) {
			String topLink = ParamUtil.getString(request, "topLink");

			String feedURL = null;

			if (topLink.equals("recent-posts")) {
				feedURL =
					themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
						"/message_boards/find_recent_posts?p_l_id=" + plid;
			}
			else {
				feedURL =
					themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
						"/message_boards/find_category?p_l_id=" + plid +
							"&mbCategoryId=" + categoryId;
			}

			if (userId > 0) {
				rss = MBMessageServiceUtil.getGroupMessagesRSS(
					groupId, userId, WorkflowConstants.STATUS_APPROVED, max,
					type, version, displayStyle, feedURL, entryURL,
					themeDisplay);
			}
			else {
				rss = MBMessageServiceUtil.getGroupMessagesRSS(
					groupId, WorkflowConstants.STATUS_APPROVED, max, type,
					version, displayStyle, feedURL, entryURL, themeDisplay);
			}
		}
		else if (categoryId > 0) {
			String feedURL =
				themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
					"/message_boards/find_category?p_l_id=" + plid +
						"&mbCategoryId=" + categoryId;

			rss = MBMessageServiceUtil.getCategoryMessagesRSS(
				groupId, categoryId, WorkflowConstants.STATUS_APPROVED, max,
				type, version, displayStyle, feedURL, entryURL, themeDisplay);
		}
		else if (threadId > 0) {
			String feedURL =
				themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
					"/message_boards/find_thread?p_l_id=" + plid +
						"&threadId=" + threadId;

			rss = MBMessageServiceUtil.getThreadMessagesRSS(
				threadId, WorkflowConstants.STATUS_APPROVED, max, type, version,
				displayStyle, feedURL, entryURL, themeDisplay);
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