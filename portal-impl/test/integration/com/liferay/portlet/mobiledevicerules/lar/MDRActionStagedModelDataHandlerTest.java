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

package com.liferay.portlet.mobiledevicerules.lar;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.lar.BaseStagedModelDataHandlerTestCase;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.LayoutTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;
import com.liferay.portlet.mobiledevicerules.service.MDRActionLocalServiceUtil;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupInstanceLocalServiceUtil;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupLocalServiceUtil;
import com.liferay.portlet.mobiledevicerules.util.MDRTestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.runner.RunWith;

/**
 * @author Mate Thurzo
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class MDRActionStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();

		_layout = LayoutTestUtil.addLayout(
			stagingGroup.getGroupId(), ServiceTestUtil.randomString());

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(_layout.getUuid());

		LayoutLocalServiceUtil.addLayout(
			TestPropsValues.getUserId(), liveGroup.getGroupId(),
			_layout.getPrivateLayout(), _layout.getParentLayoutId(),
			_layout.getName(), _layout.getTitle(), _layout.getDescription(),
			_layout.getType(), _layout.getHidden(), _layout.getFriendlyURL(),
			serviceContext);
	}

	@Override
	protected Map<String, List<StagedModel>> addDependentStagedModelsMap(
			Group group)
		throws Exception {

		Map<String, List<StagedModel>> dependentStagedModelsMap =
			new HashMap<String, List<StagedModel>>();

		MDRRuleGroup ruleGroup = MDRTestUtil.addRuleGroup(group.getGroupId());

		addDependentStagedModel(
			dependentStagedModelsMap, MDRRuleGroup.class, ruleGroup);

		MDRRuleGroupInstance ruleGroupInstance =
			MDRTestUtil.addRuleGroupInstance(
				group.getGroupId(), Layout.class.getName(), _layout.getPlid(),
				ruleGroup.getRuleGroupId());

		addDependentStagedModel(
			dependentStagedModelsMap, MDRRuleGroupInstance.class,
			ruleGroupInstance);

		return dependentStagedModelsMap;
	}

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		List<StagedModel> dependentStagedModels = dependentStagedModelsMap.get(
			MDRRuleGroupInstance.class.getSimpleName());

		MDRRuleGroupInstance ruleGroupInstance =
			(MDRRuleGroupInstance)dependentStagedModels.get(0);

		return MDRTestUtil.addAction(
			ruleGroupInstance.getRuleGroupInstanceId());
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group) {
		try {
			return MDRActionLocalServiceUtil.getMDRActionByUuidAndGroupId(
				uuid, group.getGroupId());
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return MDRAction.class;
	}

	@Override
	protected void validateImport(
			Map<String, List<StagedModel>> dependentStagedModelsMap,
			Group group)
		throws Exception {

		List<StagedModel> ruleGroupDependentStagedModels =
			dependentStagedModelsMap.get(MDRRuleGroup.class.getSimpleName());

		Assert.assertEquals(1, ruleGroupDependentStagedModels.size());

		MDRRuleGroup ruleGroup =
			(MDRRuleGroup)ruleGroupDependentStagedModels.get(0);

		MDRRuleGroupLocalServiceUtil.getMDRRuleGroupByUuidAndGroupId(
			ruleGroup.getUuid(), group.getGroupId());

		List<StagedModel> ruleGroupInstanceDependentStagedModels =
			dependentStagedModelsMap.get(
				MDRRuleGroupInstance.class.getSimpleName());

		Assert.assertEquals(1, ruleGroupInstanceDependentStagedModels.size());

		MDRRuleGroupInstance ruleGroupInstance =
			(MDRRuleGroupInstance)ruleGroupInstanceDependentStagedModels.get(0);

		MDRRuleGroupInstanceLocalServiceUtil.
			getMDRRuleGroupInstanceByUuidAndGroupId(
				ruleGroupInstance.getUuid(), group.getGroupId());
	}

	private Layout _layout;

}