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

package com.liferay.portal.util;

import com.liferay.portal.RequiredGroupException;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.theme.ThemeDisplay;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * @author Vilmos Papp
 * @author Akos Thurzo
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class PortalImplBaseURLTestCase {

	@Before
	public void setUp() throws Exception {
		company = CompanyLocalServiceUtil.getCompany(
			TestPropsValues.getCompanyId());

		long controlPanelPlid = PortalUtil.getControlPanelPlid(
			company.getCompanyId());

		controlPanelLayout = LayoutLocalServiceUtil.getLayout(controlPanelPlid);

		group = GroupTestUtil.addGroup();

		layout = LayoutTestUtil.addLayout(
			group.getGroupId(), ServiceTestUtil.randomString());
	}

	@After
	public void tearDown() throws Exception {
		try {
			GroupLocalServiceUtil.deleteGroup(group);
		}
		catch (RequiredGroupException rge) {
			LayoutLocalServiceUtil.deleteLayout(layout);
		}
	}

	protected ThemeDisplay initThemeDisplay(
			Company company, Group group, Layout layout, String virtualHostname)
		throws Exception {

		company.setVirtualHostname(virtualHostname);

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(company);
		themeDisplay.setI18nLanguageId(StringPool.BLANK);
		themeDisplay.setLayout(layout);
		themeDisplay.setLayoutSet(layout.getLayoutSet());
		themeDisplay.setSecure(false);
		themeDisplay.setServerName(virtualHostname);
		themeDisplay.setServerPort(8080);
		themeDisplay.setSiteGroupId(group.getGroupId());
		themeDisplay.setUser(TestPropsValues.getUser());
		themeDisplay.setWidget(false);

		return themeDisplay;
	}

	protected static final String LOCALHOST = "localhost";

	protected static final String VIRTUAL_HOSTNAME = "test.com";

	protected Company company;
	protected Layout controlPanelLayout;
	protected Group group;
	protected Layout layout;

}