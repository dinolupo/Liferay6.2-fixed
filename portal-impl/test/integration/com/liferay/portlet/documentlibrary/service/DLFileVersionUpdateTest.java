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

package com.liferay.portlet.documentlibrary.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alexander Chow
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class DLFileVersionUpdateTest extends BaseDLAppTestCase {

	@Test
	public void testWithExtensionWithContent() throws Exception {
		testVersionUpdate(
			_FULL_FILE_NAME, _ZERO_BYTES, ContentTypes.TEXT_PLAIN,
			_FULL_FILE_NAME, CONTENT.getBytes(), ContentTypes.TEXT_PLAIN);
	}

	@Test
	public void testWithExtensionWithoutContent() throws Exception {
		testVersionUpdate(
			_FULL_FILE_NAME, _ZERO_BYTES, ContentTypes.TEXT_PLAIN,
			_FULL_FILE_NAME, _ZERO_BYTES, ContentTypes.TEXT_PLAIN);
	}

	@Test
	public void testWithoutExtensionWithContent() throws Exception {
		testVersionUpdate(
			_BASE_FILE_NAME, _ZERO_BYTES, ContentTypes.APPLICATION_OCTET_STREAM,
			_BASE_FILE_NAME, CONTENT.getBytes(), ContentTypes.TEXT_PLAIN);
	}

	@Test
	public void testWithoutExtensionWithoutContent() throws Exception {
		testVersionUpdate(
			_BASE_FILE_NAME, _ZERO_BYTES, ContentTypes.APPLICATION_OCTET_STREAM,
			_BASE_FILE_NAME, _ZERO_BYTES,
			ContentTypes.APPLICATION_OCTET_STREAM);
	}

	protected void testVersionUpdate(
			String addFileName, byte[] addBytes, String addMimeType,
			String updateFileName, byte[] updateBytes, String updateMimeType)
		throws PortalException, SystemException {

		String description = StringPool.BLANK;
		String changeLog = StringPool.BLANK;

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			group.getGroupId());

		FileEntry fileEntry = DLAppServiceUtil.addFileEntry(
			group.getGroupId(), parentFolder.getFolderId(), addFileName,
			addMimeType, addFileName, description, changeLog, addBytes,
			serviceContext);

		fileEntry = DLAppServiceUtil.updateFileEntry(
			fileEntry.getFileEntryId(), updateFileName, updateMimeType,
			updateFileName, description, changeLog, false, updateBytes,
			serviceContext);

		FileVersion fileVersion = fileEntry.getFileVersion();

		Assert.assertEquals(
			DLFileEntryConstants.VERSION_DEFAULT, fileVersion.getVersion());
		Assert.assertEquals(updateMimeType, fileVersion.getMimeType());
		Assert.assertEquals(updateBytes.length, fileVersion.getSize());
		Assert.assertEquals(
			fileVersion.getExtension(), fileEntry.getExtension());
		Assert.assertEquals(fileVersion.getMimeType(), fileEntry.getMimeType());
		Assert.assertEquals(fileVersion.getSize(), fileEntry.getSize());
	}

	private static final String _BASE_FILE_NAME = "Test";

	private static final String _FULL_FILE_NAME = "Test.txt";

	private static final byte[] _ZERO_BYTES = new byte[0];

}