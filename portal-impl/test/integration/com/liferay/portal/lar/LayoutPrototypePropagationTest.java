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

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalCallbackAwareExecutionTestListener;
import com.liferay.portal.util.LayoutTestUtil;
import com.liferay.portal.util.TestPropsValues;

import org.junit.runner.RunWith;

/**
 * @author Eduardo Garcia
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		TransactionalCallbackAwareExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Transactional
public class LayoutPrototypePropagationTest
	extends BasePrototypePropagationTestCase {

	@Override
	protected void doSetUp() throws Exception {
		prototypeLayout = layoutPrototypeLayout;

		journalArticle = globalJournalArticle;

		journalContentPortletId =
			addJournalContentPortletToLayout(
				TestPropsValues.getUserId(), layoutPrototypeLayout,
				journalArticle, "column-1");

		layout = LayoutTestUtil.addLayout(
			group.getGroupId(), ServiceTestUtil.randomString(), true,
			layoutPrototype, true);

		layout = propagateChanges(layout);
	}

	@Override
	protected void setLinkEnabled(boolean linkEnabled) throws Exception {
		layout.setLayoutPrototypeLinkEnabled(linkEnabled);

		LayoutLocalServiceUtil.updateLayout(layout);
	}

}