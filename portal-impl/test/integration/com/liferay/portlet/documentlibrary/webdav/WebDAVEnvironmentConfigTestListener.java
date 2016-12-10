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

package com.liferay.portlet.documentlibrary.webdav;

import com.liferay.portal.kernel.test.TestContext;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.webdav.methods.Method;
import com.liferay.portal.test.EnvironmentExecutionTestListener;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;

/**
 * @author Miguel Pastor
 */
public class WebDAVEnvironmentConfigTestListener
	extends EnvironmentExecutionTestListener {

	@Override
	public void runAfterClass(TestContext testContext) {
		_baseWebDAVTestCase.service(Method.DELETE, "", null, null);

		super.runAfterClass(testContext);
	}

	@Override
	public void runBeforeClass(TestContext testContext) {
		super.runBeforeClass(testContext);

		Tuple tuple = _baseWebDAVTestCase.service(Method.MKCOL, "", null, null);

		int statusCode = BaseWebDAVTestCase.getStatusCode(tuple);

		if (statusCode == HttpServletResponse.SC_METHOD_NOT_ALLOWED) {
			_baseWebDAVTestCase.service(Method.DELETE, "", null, null);

			tuple = _baseWebDAVTestCase.service(Method.MKCOL, "", null, null);

			statusCode = BaseWebDAVTestCase.getStatusCode(tuple);

			Assert.assertEquals(HttpServletResponse.SC_CREATED, statusCode);
		}
	}

	private BaseWebDAVTestCase _baseWebDAVTestCase = new BaseWebDAVTestCase();

}