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

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.JDKLoggerTestUtil;
import com.liferay.portal.test.AdviseWith;
import com.liferay.portal.test.AspectJMockingNewClassLoaderJUnitTestRunner;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Shuyang Zhou
 */
@RunWith(AspectJMockingNewClassLoaderJUnitTestRunner.class)
public class BaseAsyncDatagramReceiveHandlerTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@AdviseWith(adviceClasses = {PortalExecutorManagerUtilAdvice.class})
	@Test
	public void testErrorDispatch() {
		List<LogRecord> logRecords = JDKLoggerTestUtil.configureJDKLogger(
			BaseAsyncDatagramReceiveHandler.class.getName(), Level.SEVERE);

		ErrorAsyncDatagramReceiveHandler errorAsyncDatagramReceiveHandler =
			new ErrorAsyncDatagramReceiveHandler();

		errorAsyncDatagramReceiveHandler.receive(null, null);

		Assert.assertEquals(1, logRecords.size());

		LogRecord logRecord = logRecords.get(0);

		Assert.assertEquals("Unable to dispatch", logRecord.getMessage());

		Throwable throwable = logRecord.getThrown();

		Assert.assertEquals(Exception.class, throwable.getClass());

		logRecords = JDKLoggerTestUtil.configureJDKLogger(
			BaseAsyncDatagramReceiveHandler.class.getName(), Level.OFF);

		errorAsyncDatagramReceiveHandler =
			new ErrorAsyncDatagramReceiveHandler();

		errorAsyncDatagramReceiveHandler.receive(null, null);

		Assert.assertTrue(logRecords.isEmpty());
	}

	@AdviseWith(adviceClasses = {PortalExecutorManagerUtilAdvice.class})
	@Test
	public void testNormalDispatch() {
		DummyAsyncDatagramReceiveHandler dummyAsyncDatagramReceiveHandler =
			new DummyAsyncDatagramReceiveHandler();

		dummyAsyncDatagramReceiveHandler.doReceive(null, null);

		Assert.assertTrue(dummyAsyncDatagramReceiveHandler._received);
	}

	private static class DummyAsyncDatagramReceiveHandler
		extends BaseAsyncDatagramReceiveHandler {

		@Override
		protected void doReceive(
			RegistrationReference registrationReference, Datagram datagram) {

			_received = true;
		}

		private boolean _received;

	}

	private static class ErrorAsyncDatagramReceiveHandler
		extends BaseAsyncDatagramReceiveHandler {

		@Override
		protected void doReceive(
				RegistrationReference registrationReference, Datagram datagram)
			throws Exception {

			throw new Exception();
		}

	}

}