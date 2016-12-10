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

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class PageIteratorTag extends IncludeTag {

	public void setCur(int cur) {
		_cur = cur;
	}

	public void setCurParam(String curParam) {
		_curParam = curParam;
	}

	public void setDelta(int delta) {
		_delta = delta;
	}

	public void setDeltaConfigurable(boolean deltaConfigurable) {
		_deltaConfigurable = deltaConfigurable;
	}

	public void setDeltaParam(String deltaParam) {
		_deltaParam = deltaParam;
	}

	public void setFormName(String formName) {
		_formName = formName;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setJsCall(String jsCall) {
		_jsCall = jsCall;
	}

	public void setMaxPages(int maxPages) {
		_maxPages = maxPages;
	}

	public void setTarget(String target) {
		_target = target;
	}

	public void setTotal(int total) {
		_total = total;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setUrl(String url) {
		String[] urlArray = PortalUtil.stripURLAnchor(url, StringPool.POUND);

		_url = urlArray[0];
		_urlAnchor = urlArray[1];

		if (_url.indexOf(CharPool.QUESTION) == -1) {
			_url += "?";
		}
		else if (!_url.endsWith("&")) {
			_url += "&";
		}
	}

	@Override
	protected void cleanUp() {
		_cur = 0;
		_curParam = null;
		_delta = SearchContainer.DEFAULT_DELTA;
		_deltaConfigurable = SearchContainer.DEFAULT_DELTA_CONFIGURABLE;
		_deltaParam = SearchContainer.DEFAULT_DELTA_PARAM;
		_formName = "fm";
		_id = null;
		_jsCall = null;
		_maxPages = 10;
		_pages = 0;
		_target = "_self";
		_total = 0;
		_type = "regular";
		_url = null;
		_urlAnchor = null;
	}

	@Override
	protected String getEndPage() {
		if (_pages > 1) {
			return _END_PAGE;
		}
		else {
			return null;
		}
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		_pages = (int)Math.ceil((double)_total / _delta);

		request.setAttribute(
			"liferay-ui:page-iterator:cur", String.valueOf(_cur));
		request.setAttribute("liferay-ui:page-iterator:curParam", _curParam);
		request.setAttribute(
			"liferay-ui:page-iterator:delta", String.valueOf(_delta));
		request.setAttribute(
			"liferay-ui:page-iterator:deltaConfigurable",
			String.valueOf(_deltaConfigurable));
		request.setAttribute(
			"liferay-ui:page-iterator:deltaParam", _deltaParam);
		request.setAttribute("liferay-ui:page-iterator:formName", _formName);
		request.setAttribute("liferay-ui:page-iterator:id", _id);
		request.setAttribute("liferay-ui:page-iterator:jsCall", _jsCall);
		request.setAttribute(
			"liferay-ui:page-iterator:maxPages", String.valueOf(_maxPages));
		request.setAttribute(
			"liferay-ui:page-iterator:pages", String.valueOf(_pages));
		request.setAttribute("liferay-ui:page-iterator:target", _target);
		request.setAttribute(
			"liferay-ui:page-iterator:total", String.valueOf(_total));
		request.setAttribute("liferay-ui:page-iterator:type", _type);
		request.setAttribute("liferay-ui:page-iterator:url", _url);
		request.setAttribute("liferay-ui:page-iterator:urlAnchor", _urlAnchor);
	}

	private static final String _END_PAGE =
		"/html/taglib/ui/page_iterator/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ui/page_iterator/start.jsp";

	private int _cur;
	private String _curParam;
	private int _delta = SearchContainer.DEFAULT_DELTA;
	private boolean _deltaConfigurable =
		SearchContainer.DEFAULT_DELTA_CONFIGURABLE;
	private String _deltaParam = SearchContainer.DEFAULT_DELTA_PARAM;
	private String _formName = "fm";
	private String _id;
	private String _jsCall;
	private int _maxPages = 10;
	private int _pages;
	private String _target = "_self";
	private int _total;
	private String _type = "regular";
	private String _url;
	private String _urlAnchor;

}