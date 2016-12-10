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

package com.liferay.portlet.assetpublisher.util;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.util.AssetUtil;

import javax.portlet.PortletURL;

/**
 * @author Juan Fernández
 */
public class AssetPublisherHelperImpl implements AssetPublisherHelper {

	@Override
	public String getAssetViewURL(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, AssetEntry assetEntry) {

		return getAssetViewURL(
			liferayPortletRequest, liferayPortletResponse, assetEntry, false);
	}

	public static String getAssetViewURL(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, AssetEntry assetEntry,
		boolean viewInContext) {

		PortletURL viewFullContentURL =
			liferayPortletResponse.createRenderURL();

		viewFullContentURL.setParameter(
			"struts_action", "/asset_publisher/view_content");

		viewFullContentURL.setParameter(
			"assetEntryId", String.valueOf(assetEntry.getEntryId()));

		AssetRendererFactory assetRendererFactory =
			assetEntry.getAssetRendererFactory();

		AssetRenderer assetRenderer = assetEntry.getAssetRenderer();

		viewFullContentURL.setParameter("type", assetRendererFactory.getType());

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (Validator.isNotNull(assetRenderer.getUrlTitle())) {
			if (assetRenderer.getGroupId() != themeDisplay.getScopeGroupId()) {
				viewFullContentURL.setParameter(
					"groupId", String.valueOf(assetRenderer.getGroupId()));
			}

			viewFullContentURL.setParameter(
				"urlTitle", assetRenderer.getUrlTitle());
		}

		String viewURL = null;

		String currentURL = null;

		if (viewInContext) {
			currentURL = PortalUtil.getCurrentURL(liferayPortletRequest);

			String viewFullContentURLString = viewFullContentURL.toString();

			viewFullContentURLString = HttpUtil.setParameter(
				viewFullContentURLString, "redirect", currentURL);

			try {
				viewURL = assetRenderer.getURLViewInContext(
					liferayPortletRequest, liferayPortletResponse,
					viewFullContentURLString);
			}
			catch (Exception e) {
			}
		}
		else {
			PortletURL currentURLObj = PortletURLUtil.getCurrent(
				liferayPortletRequest, liferayPortletResponse);

			currentURL = currentURLObj.toString();
		}

		if (Validator.isNull(viewURL)) {
			viewURL = viewFullContentURL.toString();
		}

		viewURL = AssetUtil.checkViewURL(
			assetEntry, viewInContext, viewURL, currentURL, themeDisplay, true);

		return viewURL;
	}

}