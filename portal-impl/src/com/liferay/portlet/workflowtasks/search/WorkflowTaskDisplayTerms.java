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

package com.liferay.portlet.workflowtasks.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Marcellus Tavares
 */
public class WorkflowTaskDisplayTerms extends DisplayTerms {

	public static final String NAME = "name";

	public static final String TYPE = "type";

	public WorkflowTaskDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		name = ParamUtil.getString(portletRequest, NAME);
		type = ParamUtil.getString(portletRequest, TYPE);
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	protected String name;
	protected String type;

}