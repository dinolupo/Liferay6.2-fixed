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

package com.liferay.portlet.asset.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.assetpublisher.util.AssetPublisherUtil;
import com.liferay.portlet.trash.util.TrashUtil;

import java.util.Date;
import java.util.Locale;

import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

/**
 * @author Jorge Ferrer
 * @author Sergio González
 */
public abstract class BaseAssetRenderer implements AssetRenderer {

	@Override
	public String getAddToPagePortletId() throws Exception {
		return PortletKeys.ASSET_PUBLISHER;
	}

	public AssetRendererFactory getAssetRendererFactory() {
		if (_assetRendererFactory != null) {
			return _assetRendererFactory;
		}

		_assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				getClassName());

		return _assetRendererFactory;
	}

	@Override
	public int getAssetRendererType() {
		return _assetRendererType;
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _AVAILABLE_LANGUAGE_IDS;
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getAvailableLanguageIds}
	 */
	@Override
	public String[] getAvailableLocales() {
		return getAvailableLanguageIds();
	}

	@Override
	public String getDiscussionPath() {
		return null;
	}

	@Override
	public Date getDisplayDate() {
		return null;
	}

	@Override
	public String getIconPath(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return getIconPath(themeDisplay);
	}

	@Override
	public String getNewName(String oldName, String token) {
		return TrashUtil.getNewName(oldName, token);
	}

	@Override
	public String getPreviewPath(
			PortletRequest portletRequest, PortletResponse PortletResponse)
		throws Exception {

		return "/html/portlet/asset_publisher/display/preview.jsp";
	}

	@Override
	public String getSearchSummary(Locale locale) {
		return getSummary(locale);
	}

	@Override
	public String getThumbnailPath(PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return themeDisplay.getPathThemeImages() +
			"/file_system/large/default.png";
	}

	@Override
	public String getURLDownload(ThemeDisplay themeDisplay) {
		return null;
	}

	@Override
	public PortletURL getURLEdit(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		return null;
	}

	@Override
	public PortletURL getURLEdit(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState, PortletURL redirectURL)
		throws Exception {

		LiferayPortletURL editPortletURL = (LiferayPortletURL)getURLEdit(
			liferayPortletRequest, liferayPortletResponse);

		if (editPortletURL == null) {
			return null;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Group group = themeDisplay.getScopeGroup();

		if (group.isLayout()) {
			Layout layout = themeDisplay.getLayout();

			group = layout.getGroup();
		}

		if (group.hasStagingGroup()) {
			return null;
		}

		editPortletURL.setDoAsGroupId(getGroupId());

		editPortletURL.setParameter("redirect", redirectURL.toString());

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String portletResource = ParamUtil.getString(
			liferayPortletRequest, "portletResource", portletDisplay.getId());

		if (Validator.isNotNull(portletResource)) {
			editPortletURL.setParameter(
				"referringPortletResource", portletResource);
		}
		else {
			editPortletURL.setParameter(
				"referringPortletResource", portletDisplay.getId());
		}

		editPortletURL.setPortletMode(PortletMode.VIEW);
		editPortletURL.setRefererPlid(themeDisplay.getPlid());
		editPortletURL.setWindowState(windowState);

		return editPortletURL;
	}

	@Override
	public PortletURL getURLExport(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		return null;
	}

	@Override
	public String getURLImagePreview(PortletRequest portletRequest)
		throws Exception {

		return getThumbnailPath(portletRequest);
	}

	@Override
	public String getUrlTitle() {
		return null;
	}

	@Override
	public PortletURL getURLView(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws Exception {

		return null;
	}

	@Override
	public String getURLViewInContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			String noSuchEntryRedirect)
		throws Exception {

		return null;
	}

	@Override
	public String getViewInContextMessage() {
		return "view-in-context";
	}

	@Override
	@SuppressWarnings("unused")
	public boolean hasEditPermission(PermissionChecker permissionChecker)
		throws PortalException, SystemException {

		return false;
	}

	@Override
	@SuppressWarnings("unused")
	public boolean hasViewPermission(PermissionChecker permissionChecker)
		throws PortalException, SystemException {

		return true;
	}

	@Override
	public boolean isConvertible() {
		return false;
	}

	@Override
	public boolean isDisplayable() {
		return true;
	}

	@Override
	public boolean isLocalizable() {
		return false;
	}

	@Override
	public boolean isPreviewInContext() {
		return false;
	}

	@Override
	public boolean isPrintable() {
		return false;
	}

	public String renderActions(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		return null;
	}

	@Override
	public void setAddToPagePreferences(
			PortletPreferences preferences, String portletId,
			ThemeDisplay themeDisplay)
		throws Exception {

		preferences.setValue("displayStyle", "full-content");
		preferences.setValue(
			"emailAssetEntryAddedEnabled", Boolean.FALSE.toString());
		preferences.setValue("selectionStyle", "manual");
		preferences.setValue("showAddContentButton", Boolean.FALSE.toString());

		AssetEntry entry = AssetEntryLocalServiceUtil.getEntry(
			getClassName(), getClassPK());

		AssetPublisherUtil.addSelection(
			themeDisplay, preferences, portletId, entry.getEntryId(), -1,
			entry.getClassName());
	}

	public void setAssetRendererType(int assetRendererType) {
		_assetRendererType = assetRendererType;
	}

	protected long getControlPanelPlid(
			LiferayPortletRequest liferayPortletRequest)
		throws PortalException, SystemException {

		return PortalUtil.getControlPanelPlid(liferayPortletRequest);
	}

	protected long getControlPanelPlid(ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		return PortalUtil.getControlPanelPlid(themeDisplay.getCompanyId());
	}

	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/page.png";
	}

	protected String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest, String noSuchEntryRedirect,
		String path, String primaryKeyParameterName,
		long primaryKeyParameterValue) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		StringBundler sb = new StringBundler(11);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathMain());
		sb.append(path);
		sb.append("?p_l_id=");
		sb.append(themeDisplay.getPlid());
		sb.append("&noSuchEntryRedirect=");
		sb.append(HttpUtil.encodeURL(noSuchEntryRedirect));
		sb.append(StringPool.AMPERSAND);
		sb.append(primaryKeyParameterName);
		sb.append(StringPool.EQUAL);
		sb.append(primaryKeyParameterValue);

		return PortalUtil.addPreservedParameters(themeDisplay, sb.toString());
	}

	private static final String[] _AVAILABLE_LANGUAGE_IDS = new String[0];

	private AssetRendererFactory _assetRendererFactory;
	private int _assetRendererType = AssetRendererFactory.TYPE_LATEST_APPROVED;

}