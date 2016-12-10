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

package com.liferay.taglib.ddm.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Bruno Basto
 * @generated
 */
public class BaseHTMLTag extends com.liferay.taglib.util.IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public boolean getCheckRequired() {
		return _checkRequired;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public com.liferay.portlet.dynamicdatamapping.storage.Fields getFields() {
		return _fields;
	}

	public java.lang.String getFieldsNamespace() {
		return _fieldsNamespace;
	}

	public boolean getReadOnly() {
		return _readOnly;
	}

	public boolean getRepeatable() {
		return _repeatable;
	}

	public java.util.Locale getRequestedLocale() {
		return _requestedLocale;
	}

	public void setCheckRequired(boolean checkRequired) {
		_checkRequired = checkRequired;

		setScopedAttribute("checkRequired", checkRequired);
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		setScopedAttribute("classNameId", classNameId);
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;

		setScopedAttribute("classPK", classPK);
	}

	public void setFields(com.liferay.portlet.dynamicdatamapping.storage.Fields fields) {
		_fields = fields;

		setScopedAttribute("fields", fields);
	}

	public void setFieldsNamespace(java.lang.String fieldsNamespace) {
		_fieldsNamespace = fieldsNamespace;

		setScopedAttribute("fieldsNamespace", fieldsNamespace);
	}

	public void setReadOnly(boolean readOnly) {
		_readOnly = readOnly;

		setScopedAttribute("readOnly", readOnly);
	}

	public void setRepeatable(boolean repeatable) {
		_repeatable = repeatable;

		setScopedAttribute("repeatable", repeatable);
	}

	public void setRequestedLocale(java.util.Locale requestedLocale) {
		_requestedLocale = requestedLocale;

		setScopedAttribute("requestedLocale", requestedLocale);
	}

	@Override
	protected void cleanUp() {
		_checkRequired = true;
		_classNameId = 0;
		_classPK = 0;
		_fields = null;
		_fieldsNamespace = null;
		_readOnly = false;
		_repeatable = true;
		_requestedLocale = null;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, "checkRequired", _checkRequired);
		setNamespacedAttribute(request, "classNameId", _classNameId);
		setNamespacedAttribute(request, "classPK", _classPK);
		setNamespacedAttribute(request, "fields", _fields);
		setNamespacedAttribute(request, "fieldsNamespace", _fieldsNamespace);
		setNamespacedAttribute(request, "readOnly", _readOnly);
		setNamespacedAttribute(request, "repeatable", _repeatable);
		setNamespacedAttribute(request, "requestedLocale", _requestedLocale);
	}

	protected static final String _ATTRIBUTE_NAMESPACE = "ddm:html:";

	private static final String _END_PAGE =
		"/html/taglib/ddm/html/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ddm/html/start.jsp";

	private boolean _checkRequired = true;
	private long _classNameId = 0;
	private long _classPK = 0;
	private com.liferay.portlet.dynamicdatamapping.storage.Fields _fields = null;
	private java.lang.String _fieldsNamespace = null;
	private boolean _readOnly = false;
	private boolean _repeatable = true;
	private java.util.Locale _requestedLocale = null;

}