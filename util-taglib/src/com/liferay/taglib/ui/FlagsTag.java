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

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class FlagsTag extends IncludeTag {

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setContentTitle(String contentTitle) {
		_contentTitle = contentTitle;
	}

	public void setLabel(boolean label) {
		_label = label;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public void setReportedUserId(long reportedUserId) {
		_reportedUserId = reportedUserId;
	}

	@Override
	protected void cleanUp() {
		_className = null;
		_classPK = 0;
		_contentTitle = null;
		_label = true;
		_message = null;
		_reportedUserId = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:flags:className", _className);
		request.setAttribute(
			"liferay-ui:flags:classPK", String.valueOf(_classPK));
		request.setAttribute("liferay-ui:flags:contentTitle", _contentTitle);
		request.setAttribute("liferay-ui:flags:label", String.valueOf(_label));
		request.setAttribute("liferay-ui:flags:message", _message);
		request.setAttribute(
			"liferay-ui:flags:reportedUserId", String.valueOf(_reportedUserId));
	}

	private static final String _PAGE = "/html/taglib/ui/flags/page.jsp";

	private String _className;
	private long _classPK;
	private String _contentTitle;
	private boolean _label = true;
	private String _message;
	private long _reportedUserId;

}