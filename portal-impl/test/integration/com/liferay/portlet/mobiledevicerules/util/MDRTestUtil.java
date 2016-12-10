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

package com.liferay.portlet.mobiledevicerules.util;

import com.liferay.portal.mobile.device.rulegroup.action.impl.SimpleRedirectActionHandler;
import com.liferay.portal.mobile.device.rulegroup.rule.impl.SimpleRuleHandler;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.util.LayoutTestUtil;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;
import com.liferay.portlet.mobiledevicerules.model.MDRRule;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;
import com.liferay.portlet.mobiledevicerules.service.MDRActionLocalServiceUtil;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupInstanceLocalServiceUtil;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupLocalServiceUtil;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleLocalServiceUtil;

import java.util.Locale;
import java.util.Map;

/**
 * @author Mate Thurzo
 */
public class MDRTestUtil {

	public static MDRAction addAction(long ruleGroupInstanceId)
		throws Exception {

		return addAction(
			ruleGroupInstanceId, ServiceTestUtil.randomLocaleStringMap(),
			ServiceTestUtil.randomLocaleStringMap(),
			SimpleRedirectActionHandler.getHandlerType(), null);
	}

	public static MDRAction addAction(
			long ruleGroupInstanceId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings)
		throws Exception {

		return MDRActionLocalServiceUtil.addAction(
			ruleGroupInstanceId, nameMap, descriptionMap, type, typeSettings,
			ServiceTestUtil.getServiceContext());
	}

	public static MDRRule addRule(long ruleGroupId) throws Exception {
		return addRule(
			ruleGroupId, ServiceTestUtil.randomLocaleStringMap(),
			ServiceTestUtil.randomLocaleStringMap(),
			SimpleRuleHandler.getHandlerType(), null);
	}

	public static MDRRule addRule(
			long ruleGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings)
		throws Exception {

		return MDRRuleLocalServiceUtil.addRule(
			ruleGroupId, nameMap, descriptionMap, type, typeSettings,
			ServiceTestUtil.getServiceContext());
	}

	public static MDRRuleGroup addRuleGroup(long groupId) throws Exception {
		return addRuleGroup(
			groupId, ServiceTestUtil.randomLocaleStringMap(),
			ServiceTestUtil.randomLocaleStringMap());
	}

	public static MDRRuleGroup addRuleGroup(
			long groupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap)
		throws Exception {

		return MDRRuleGroupLocalServiceUtil.addRuleGroup(
			groupId, nameMap, descriptionMap,
			ServiceTestUtil.getServiceContext(groupId));
	}

	public static MDRRuleGroupInstance addRuleGroupInstance(
			long groupId, long ruleGroupId)
		throws Exception {

		Layout layout = LayoutTestUtil.addLayout(
			groupId, ServiceTestUtil.randomString());

		return addRuleGroupInstance(
			groupId, Layout.class.getName(), layout.getPlid(), ruleGroupId);
	}

	public static MDRRuleGroupInstance addRuleGroupInstance(
			long groupId, String className, long classPK, long ruleGroupId)
		throws Exception {

		return MDRRuleGroupInstanceLocalServiceUtil.addRuleGroupInstance(
			groupId, className, classPK, ruleGroupId,
			ServiceTestUtil.getServiceContext(groupId));
	}

}