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

package com.liferay.taglib.aui.base;

import javax.servlet.jsp.JspException;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 * @author Nathan Cavanaugh
 * @author Julio Camarero
 * @generated
 */
public abstract class BaseValidatorTagImpl extends com.liferay.portal.kernel.servlet.taglib.BaseBodyTagSupport {

	@Override
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	public boolean getCustomValidatorRequired() {
		return _customValidatorRequired;
	}

	public java.lang.String getErrorMessage() {
		return _errorMessage;
	}

	public java.lang.String getName() {
		return _name;
	}

	public void setCustomValidatorRequired(boolean customValidatorRequired) {
		_customValidatorRequired = customValidatorRequired;
	}

	public void setErrorMessage(java.lang.String errorMessage) {
		_errorMessage = errorMessage;
	}

	public void setName(java.lang.String name) {
		_name = name;
	}

	protected void cleanUp() {
		_customValidatorRequired = false;
		_errorMessage = null;
		_name = null;
	}

	protected String getPage() {
		return _PAGE;
	}

	private static final String _PAGE =
		"/html/taglib/aui/validator/page.jsp";

	private boolean _customValidatorRequired = false;
	private java.lang.String _errorMessage = null;
	private java.lang.String _name = null;

}