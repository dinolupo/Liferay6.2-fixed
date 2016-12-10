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

package com.liferay.portal.kernel.io;

import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.io.File;

import java.lang.reflect.Field;

import java.security.Permission;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class AutoDeleteFileInputStreamTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testAutoRemoveFileInputStream() throws Exception {
		File tempFile = new File("tempFile");

		Assert.assertTrue(tempFile.createNewFile());

		AutoDeleteFileInputStream autoRemoveFileInputStream =
			new AutoDeleteFileInputStream(tempFile);

		final AtomicInteger checkDeleteCount = new AtomicInteger();

		SecurityManager securityManager = new SecurityManager() {

			@Override
			public void checkDelete(String file) {
				if (file.contains("tempFile")) {
					checkDeleteCount.getAndIncrement();
				}
			}

			@Override
			public void checkPermission(Permission permission) {
			}

		};

		System.setSecurityManager(securityManager);

		try {
			autoRemoveFileInputStream.close();
		}
		finally {
			System.setSecurityManager(null);
		}

		Assert.assertFalse(tempFile.exists());
		Assert.assertEquals(1, checkDeleteCount.get());

		checkDeleteCount.set(0);

		Assert.assertTrue(tempFile.createNewFile());

		autoRemoveFileInputStream = new AutoDeleteFileInputStream(tempFile);

		Assert.assertTrue(tempFile.delete());

		System.setSecurityManager(securityManager);

		try {
			autoRemoveFileInputStream.close();
		}
		finally {
			System.setSecurityManager(null);
		}

		Assert.assertFalse(tempFile.exists());
		Assert.assertEquals(2, checkDeleteCount.get());

		Class<?> clazz = Class.forName("java.io.DeleteOnExitHook");

		Field filesField = ReflectionUtil.getDeclaredField(clazz, "files");

		Set<String> files = (Set<String>)filesField.get(null);

		Assert.assertTrue(files.contains(tempFile.getPath()));
	}

}