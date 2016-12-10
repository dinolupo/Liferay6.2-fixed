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

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetTagsSummaryTag extends IncludeTag {

	public String getAssetTagNames() {
		return _assetTagNames;
	}

	public PortletURL getPortletURL() {
		return _portletURL;
	}

	public void setAssetTagNames(String assetTagNames) {
		_assetTagNames = assetTagNames;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	@Override
	protected void cleanUp() {
		_assetTagNames = null;
		_className = null;
		_classPK = 0;
		_message = null;
		_portletURL = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:asset-tags-summary:assetTagNames", _assetTagNames);
		request.setAttribute(
			"liferay-ui:asset-tags-summary:className", _className);
		request.setAttribute(
			"liferay-ui:asset-tags-summary:classPK", String.valueOf(_classPK));
		request.setAttribute("liferay-ui:asset-tags-summary:message", _message);
		request.setAttribute(
			"liferay-ui:asset-tags-summary:portletURL", _portletURL);
	}

	private static final String _PAGE =
		"/html/taglib/ui/asset_tags_summary/page.jsp";

	private String _assetTagNames;
	private String _className;
	private long _classPK;
	private String _message;
	private PortletURL _portletURL;

}