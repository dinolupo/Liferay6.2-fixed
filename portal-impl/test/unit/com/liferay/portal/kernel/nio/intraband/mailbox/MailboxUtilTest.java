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

package com.liferay.portal.kernel.nio.intraband.mailbox;

import com.liferay.portal.kernel.io.BigEndianCodec;
import com.liferay.portal.kernel.nio.intraband.CompletionHandler;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.DatagramHelper;
import com.liferay.portal.kernel.nio.intraband.MockIntraband;
import com.liferay.portal.kernel.nio.intraband.MockRegistrationReference;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtilAdvice;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.ThreadUtil;
import com.liferay.portal.test.AdviseWith;
import com.liferay.portal.test.AspectJMockingNewJVMJUnitTestRunner;

import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import java.nio.ByteBuffer;

import java.util.concurrent.BlockingQueue;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Shuyang Zhou
 */
@RunWith(AspectJMockingNewJVMJUnitTestRunner.class)
public class MailboxUtilTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@AdviseWith(
		adviceClasses = {PropsUtilAdvice.class, ReceiptStubAdvice.class}
	)
	@Test
	public void testDepositMailWithReaperThreadDisabled() {
		PropsUtilAdvice.setProps(
			PropsKeys.INTRABAND_MAILBOX_REAPER_THREAD_ENABLED,
			Boolean.FALSE.toString());
		PropsUtilAdvice.setProps(
			PropsKeys.INTRABAND_MAILBOX_STORAGE_LIFE, String.valueOf(0));

		Assert.assertEquals(0, MailboxUtil.depositMail(ByteBuffer.allocate(0)));
		Assert.assertEquals(1, MailboxUtil.depositMail(ByteBuffer.allocate(0)));
		Assert.assertEquals(2, MailboxUtil.depositMail(ByteBuffer.allocate(0)));
		Assert.assertEquals(3, MailboxUtil.depositMail(ByteBuffer.allocate(0)));

		Thread reaperThread = null;

		for (Thread thread : ThreadUtil.getThreads()) {
			if (thread == null) {
				continue;
			}

			String name = thread.getName();

			if (name.equals(MailboxUtil.class.getName())) {
				reaperThread = thread;

				break;
			}
		}

		Assert.assertNull(reaperThread);
	}

	@AdviseWith(
		adviceClasses = {PropsUtilAdvice.class, ReceiptStubAdvice.class}
	)
	@Test
	public void testDepositMailWithReaperThreadEnabled() throws Exception {
		PropsUtilAdvice.setProps(
			PropsKeys.INTRABAND_MAILBOX_REAPER_THREAD_ENABLED,
			Boolean.TRUE.toString());
		PropsUtilAdvice.setProps(
			PropsKeys.INTRABAND_MAILBOX_STORAGE_LIFE, String.valueOf(0));

		Assert.assertEquals(0, MailboxUtil.depositMail(ByteBuffer.allocate(0)));
		Assert.assertEquals(1, MailboxUtil.depositMail(ByteBuffer.allocate(0)));
		Assert.assertEquals(2, MailboxUtil.depositMail(ByteBuffer.allocate(0)));
		Assert.assertEquals(3, MailboxUtil.depositMail(ByteBuffer.allocate(0)));

		Thread reaperThread = null;

		for (Thread thread : ThreadUtil.getThreads()) {
			if ((thread != null) &&
				thread.getName().equals(MailboxUtil.class.getName())) {

				reaperThread = thread;

				break;
			}
		}

		Assert.assertNotNull(reaperThread);

		reaperThread.interrupt();

		while (reaperThread.isInterrupted());

		Assert.assertTrue(reaperThread.isAlive());

		BlockingQueue<Object> overdueMailQueue = getOverdueMailQueue();

		while (!overdueMailQueue.isEmpty());

		ReceiptStubAdvice._throwException = true;

		RecorderUncaughtExceptionHandler recorderUncaughtExceptionHandler =
			new RecorderUncaughtExceptionHandler();

		reaperThread.setUncaughtExceptionHandler(
			recorderUncaughtExceptionHandler);

		overdueMailQueue.offer(createReceiptStub());

		reaperThread.join(1000);

		Assert.assertSame(
			reaperThread, RecorderUncaughtExceptionHandler._thread);

		Throwable throwable = RecorderUncaughtExceptionHandler._throwable;

		Assert.assertSame(IllegalStateException.class, throwable.getClass());

		Assert.assertFalse(reaperThread.isAlive());
	}

	@AdviseWith(adviceClasses = {PropsUtilAdvice.class})
	@Test
	public void testReceiveMailWithReaperThreadDisabled() {
		PropsUtilAdvice.setProps(
			PropsKeys.INTRABAND_MAILBOX_REAPER_THREAD_ENABLED,
			Boolean.FALSE.toString());
		PropsUtilAdvice.setProps(
			PropsKeys.INTRABAND_MAILBOX_STORAGE_LIFE, String.valueOf(10000));

		Assert.assertNull(MailboxUtil.receiveMail(0));

		ByteBuffer byteBuffer1 = ByteBuffer.allocate(0);

		long receipt1 = MailboxUtil.depositMail(byteBuffer1);

		ByteBuffer byteBuffer2 = ByteBuffer.allocate(0);

		long receipt2 = MailboxUtil.depositMail(byteBuffer2);

		Assert.assertSame(byteBuffer2, MailboxUtil.receiveMail(receipt2));
		Assert.assertSame(byteBuffer1, MailboxUtil.receiveMail(receipt1));
	}

	@AdviseWith(adviceClasses = {PropsUtilAdvice.class})
	@Test
	public void testReceiveMailWithReaperThreadEnabled() {
		PropsUtilAdvice.setProps(
			PropsKeys.INTRABAND_MAILBOX_REAPER_THREAD_ENABLED,
			Boolean.TRUE.toString());
		PropsUtilAdvice.setProps(
			PropsKeys.INTRABAND_MAILBOX_STORAGE_LIFE, String.valueOf(10000));

		Assert.assertNull(MailboxUtil.receiveMail(0));

		ByteBuffer byteBuffer1 = ByteBuffer.allocate(0);

		long receipt1 = MailboxUtil.depositMail(byteBuffer1);

		ByteBuffer byteBuffer2 = ByteBuffer.allocate(0);

		long receipt2 = MailboxUtil.depositMail(byteBuffer2);

		Assert.assertSame(byteBuffer2, MailboxUtil.receiveMail(receipt2));
		Assert.assertSame(byteBuffer1, MailboxUtil.receiveMail(receipt1));
	}

	@AdviseWith(adviceClasses = {PropsUtilAdvice.class})
	@Test
	public void testSendMailFail() {
		MockIntraband mockIntraband = new MockIntraband() {

			@Override
			protected void doSendDatagram(
				RegistrationReference registrationReference,
				Datagram datagram) {

				throw new RuntimeException();
			}

		};

		try {
			MailboxUtil.sendMail(
				new MockRegistrationReference(mockIntraband),
				ByteBuffer.allocate(0));

			Assert.fail();
		}
		catch (MailboxException me) {
			Throwable throwable = me.getCause();

			Assert.assertEquals(RuntimeException.class, throwable.getClass());
		}
	}

	@AdviseWith(adviceClasses = {PropsUtilAdvice.class})
	@Test
	public void testSendMailSuccess() throws MailboxException {
		final long receipt = 100;

		MockIntraband mockIntraband = new MockIntraband() {

			@Override
			protected void doSendDatagram(
				RegistrationReference registrationReference,
				Datagram datagram) {

				byte[] data = new byte[8];

				BigEndianCodec.putLong(data, 0, receipt);

				CompletionHandler<?> completionHandler =
					DatagramHelper.getCompletionHandler(datagram);

				completionHandler.replied(
					null,
					Datagram.createResponseDatagram(
						datagram, ByteBuffer.wrap(data)));
			}

		};

		Assert.assertEquals(
			receipt,
			MailboxUtil.sendMail(
				new MockRegistrationReference(mockIntraband),
				ByteBuffer.allocate(0)));
	}

	@Aspect
	public static class ReceiptStubAdvice {

		@Around(
			"execution(public long com.liferay.portal.kernel.nio.intraband." +
				"mailbox.MailboxUtil$ReceiptStub.getReceipt())")
		public Object getReceipt(ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {

			if (_throwException) {
				throw new IllegalStateException();
			}

			return proceedingJoinPoint.proceed();
		}

		private static boolean _throwException;

	}

	protected Object createReceiptStub() throws Exception {
		String mailboxUtilClassName = MailboxUtil.class.getName();

		Class<?> clazz = Class.forName(
			mailboxUtilClassName.concat("$ReceiptStub"));

		Constructor<?> constructor = clazz.getConstructor(long.class);

		return constructor.newInstance(0);
	}

	protected BlockingQueue<Object> getOverdueMailQueue() throws Exception {
		Field overdueMailQueueField = ReflectionUtil.getDeclaredField(
			MailboxUtil.class, "_overdueMailQueue");

		return (BlockingQueue<Object>)overdueMailQueueField.get(null);
	}

	private static class RecorderUncaughtExceptionHandler
		implements UncaughtExceptionHandler {

		@Override
		public void uncaughtException(Thread thread, Throwable throwable) {
			_thread = thread;
			_throwable = throwable;
		}

		private static volatile Thread _thread;
		private static volatile Throwable _throwable;

	}

}