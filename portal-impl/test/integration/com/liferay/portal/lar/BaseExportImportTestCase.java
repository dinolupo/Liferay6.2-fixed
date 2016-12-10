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

package com.liferay.portal.lar;

import com.liferay.portal.RequiredGroupException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.LayoutTestUtil;

import java.io.File;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Eduardo Garcia
 */
public class BaseExportImportTestCase extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		group = GroupTestUtil.addGroup();
		importedGroup = GroupTestUtil.addGroup();

		layout = LayoutTestUtil.addLayout(
			group.getGroupId(), ServiceTestUtil.randomString());

		// Delete and readd to ensure a different layout ID (not ID or UUID).
		// See LPS-32132.

		LayoutLocalServiceUtil.deleteLayout(layout, true, new ServiceContext());

		layout = LayoutTestUtil.addLayout(
			group.getGroupId(), ServiceTestUtil.randomString());
	}

	@After
	public void tearDown() throws Exception {
		try {
			if (group != null) {
				GroupLocalServiceUtil.deleteGroup(group);
			}

			if (importedGroup != null) {
				GroupLocalServiceUtil.deleteGroup(importedGroup);
			}
		}
		catch (RequiredGroupException rge) {
		}

		if (layout != null) {
			LayoutLocalServiceUtil.deleteLayout(layout);
		}

		if (importedLayout != null) {
			LayoutLocalServiceUtil.deleteLayout(importedLayout);
		}

		if ((larFile != null) && larFile.exists()) {
			FileUtil.delete(larFile);
		}
	}

	protected void addParameter(
		Map<String, String[]> parameterMap, String name, String value) {

		parameterMap.put(name, new String[] {value});
	}

	protected void addParameter(
		Map<String, String[]> parameterMap, String namespace, String name,
		boolean value) {

		PortletDataHandlerBoolean portletDataHandlerBoolean =
			new PortletDataHandlerBoolean(namespace, name);

		addParameter(
			parameterMap, portletDataHandlerBoolean.getNamespacedControlName(),
			String.valueOf(value));
	}

	protected StagedModel addStagedModel(long groupId) throws Exception {
		return null;
	}

	protected void deleteStagedModel(StagedModel stagedModel) throws Exception {
		return;
	}

	protected Map<String, String[]> getExportParameterMap() throws Exception {
		Map<String, String[]> parameterMap =
			new LinkedHashMap<String, String[]>();

		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_CONFIGURATION,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_CONFIGURATION_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_DATA,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_DATA_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_SETUP_ALL,
			new String[] {Boolean.TRUE.toString()});

		return parameterMap;
	}

	protected Map<String, String[]> getImportParameterMap() throws Exception {
		Map<String, String[]> parameterMap =
			new LinkedHashMap<String, String[]>();

		parameterMap.put(
			PortletDataHandlerKeys.DATA_STRATEGY,
			new String[] {
				PortletDataHandlerKeys.DATA_STRATEGY_MIRROR_OVERWRITE});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_CONFIGURATION,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_CONFIGURATION_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_DATA,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_DATA_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_SETUP_ALL,
			new String[] {Boolean.TRUE.toString()});

		return parameterMap;
	}

	@SuppressWarnings("unused")
	protected StagedModel getStagedModel(String uuid, long groupId)
		throws PortalException, SystemException {

		return null;
	}

	@SuppressWarnings("unused")
	protected String getStagedModelUuid(StagedModel stagedModel)
		throws PortalException, SystemException {

		return stagedModel.getUuid();
	}

	protected Group group;
	protected Group importedGroup;
	protected Layout importedLayout;
	protected File larFile;
	protected Layout layout;

}