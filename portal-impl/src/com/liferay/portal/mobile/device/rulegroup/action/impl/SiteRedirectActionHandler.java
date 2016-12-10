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

package com.liferay.portal.mobile.device.rulegroup.action.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.LayoutLocalService;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Edward Han
 */
public class SiteRedirectActionHandler extends BaseRedirectActionHandler {

	public static String getHandlerType() {
		return SiteRedirectActionHandler.class.getName();
	}

	@Override
	public Collection<String> getPropertyNames() {
		return _propertyNames;
	}

	@Override
	public String getType() {
		return getHandlerType();
	}

	public void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	public void setLayoutLocalService(LayoutLocalService layoutLocalService) {
		_layoutLocalService = layoutLocalService;
	}

	@Override
	protected String getURL(
			MDRAction mdrAction, HttpServletRequest request,
			HttpServletResponse response)
		throws PortalException, SystemException {

		UnicodeProperties typeSettingsProperties =
			mdrAction.getTypeSettingsProperties();

		long plid = GetterUtil.getLong(
			typeSettingsProperties.getProperty("plid"));

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout themeDisplayLayout = themeDisplay.getLayout();

		if (plid == themeDisplayLayout.getPlid()) {
			return null;
		}

		Layout layout = _layoutLocalService.fetchLayout(plid);

		long groupId = GetterUtil.getLong(
			typeSettingsProperties.getProperty("groupId"));

		if ((layout != null) && (layout.getGroupId() != groupId)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Layout " + layout.getPlid() +
						" does not belong to group " + groupId);
			}

			layout = null;
		}

		if (layout == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Using default public layout");
			}

			Group group = null;

			if (groupId != themeDisplayLayout.getGroupId()) {
				group = _groupLocalService.fetchGroup(groupId);
			}

			if (group == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No group found with group ID " + groupId);
				}

				return null;
			}

			layout = LayoutLocalServiceUtil.fetchLayout(
				group.getDefaultPublicPlid());
		}

		if (layout != null) {
			return PortalUtil.getLayoutURL(layout, themeDisplay);
		}

		if (_log.isWarnEnabled()) {
			_log.warn("Unable to resolve default layout");
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(
		SiteRedirectActionHandler.class);

	private static Collection<String> _propertyNames;

	@BeanReference(type = GroupLocalService.class)
	private GroupLocalService _groupLocalService;

	@BeanReference(type = LayoutLocalService.class)
	private LayoutLocalService _layoutLocalService;

	static {
		_propertyNames = new ArrayList<String>(2);

		_propertyNames.add("groupId");
		_propertyNames.add("plid");

		_propertyNames = Collections.unmodifiableCollection(_propertyNames);
	}

}