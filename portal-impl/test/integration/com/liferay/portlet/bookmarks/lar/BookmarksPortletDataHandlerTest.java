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

package com.liferay.portlet.bookmarks.lar;

import com.liferay.portal.kernel.lar.PortletDataHandler;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.lar.BasePortletDataHandlerTestCase;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.portlet.bookmarks.service.BookmarksFolderServiceUtil;
import com.liferay.portlet.bookmarks.util.BookmarksTestUtil;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class BookmarksPortletDataHandlerTest
	extends BasePortletDataHandlerTestCase {

	@Test
	public void testDeleteAllFolders() throws Exception {
		Group group = GroupTestUtil.addGroup();

		BookmarksFolder parentFolder = BookmarksTestUtil.addFolder(
			group.getGroupId(), "parent");

		BookmarksFolder childFolder = BookmarksTestUtil.addFolder(
			group.getGroupId(), parentFolder.getFolderId(), "child");

		BookmarksFolderServiceUtil.moveFolderToTrash(
			childFolder.getPrimaryKey());

		BookmarksFolderServiceUtil.moveFolderToTrash(
			parentFolder.getPrimaryKey());

		BookmarksFolderServiceUtil.deleteFolder(parentFolder.getFolderId());

		GroupLocalServiceUtil.deleteGroup(group);

		List<BookmarksFolder> folders =
			BookmarksFolderLocalServiceUtil.getFolders(group.getGroupId());

		Assert.assertEquals(0, folders.size());
	}

	@Override
	protected void addStagedModels() throws Exception {
		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			stagingGroup.getGroupId(), ServiceTestUtil.randomString());

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			stagingGroup.getGroupId());

		BookmarksTestUtil.addEntry(folder.getFolderId(), true, serviceContext);
	}

	@Override
	protected PortletDataHandler createPortletDataHandler() {
		return new BookmarksPortletDataHandler();
	}

	@Override
	protected String getPortletId() {
		return PortletKeys.BOOKMARKS;
	}

}