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

package com.liferay.portlet.mobiledevicerules.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.service.MDRActionLocalServiceUtil;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupLocalServiceUtil;

import java.util.List;

/**
 * @author Edward C. Han
 */
public class MDRRuleGroupInstanceImpl extends MDRRuleGroupInstanceBaseImpl {

	public MDRRuleGroupInstanceImpl() {
	}

	@Override
	public List<MDRAction> getActions() throws SystemException {
		return MDRActionLocalServiceUtil.getActions(getRuleGroupInstanceId());
	}

	@Override
	public MDRRuleGroup getRuleGroup() throws PortalException, SystemException {
		return MDRRuleGroupLocalServiceUtil.getRuleGroup(getRuleGroupId());
	}

}