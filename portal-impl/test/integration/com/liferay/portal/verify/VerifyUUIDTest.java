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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class VerifyUUIDTest extends BaseVerifyTestCase {

	@Test
	public void testVerifyModel() throws Exception {
		testVerifyModel("Layout", "plid");
	}

	@Test(expected = SQLException.class)
	public void testVerifyModelWithUnknownPKColumnName() throws Exception {
		testVerifyModel("Layout", _UNKNOWN);
	}

	@Test(expected = SQLException.class)
	public void testVerifyUnknownModelWithUnknownPKColumnName()
		throws Exception {

		testVerifyModel(_UNKNOWN, _UNKNOWN);
	}

	@Override
	protected VerifyProcess getVerifyProcess() {
		return new VerifyUUID();
	}

	protected void testVerifyModel(String model, String pkColumnName)
		throws Exception {

		VerifyUUID.verifyModel(model, pkColumnName);
	}

	private static final String _UNKNOWN = "Unknown";

}