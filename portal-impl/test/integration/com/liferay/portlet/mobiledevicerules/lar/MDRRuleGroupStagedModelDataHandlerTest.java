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
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupLocalServiceUtil;
import com.liferay.portlet.mobiledevicerules.util.MDRTestUtil;

import java.util.List;
import java.util.Map;

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
public class MDRRuleGroupStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		return MDRTestUtil.addRuleGroup(group.getGroupId());
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group) {
		try {
			return MDRRuleGroupLocalServiceUtil.getMDRRuleGroupByUuidAndGroupId(
				uuid, group.getGroupId());
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return MDRRuleGroup.class;
	}

}