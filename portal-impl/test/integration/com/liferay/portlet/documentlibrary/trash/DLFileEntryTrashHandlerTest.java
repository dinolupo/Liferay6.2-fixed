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

package com.liferay.portlet.documentlibrary.trash;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.ClassedModel;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.WorkflowedModel;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileRank;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileRankLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLAppTestUtil;
import com.liferay.portlet.trash.BaseTrashHandlerTestCase;
import com.liferay.portlet.trash.util.TrashUtil;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alexander Chow
 * @author Julio Camarero
 * @author Eudaldo Alonso
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class DLFileEntryTrashHandlerTest extends BaseTrashHandlerTestCase {

	@Ignore()
	@Override
	@Test
	public void testDeleteTrashVersions() throws Exception {
	}

	@Test
	@Transactional
	public void testTrashDLFileRank() throws Exception {
		trashDLFileRank();
	}

	@Ignore()
	@Override
	@Test
	public void testTrashVersionParentBaseModelAndRestore() throws Exception {
	}

	@Override
	protected BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved,
			ServiceContext serviceContext)
		throws Exception {

		DLFolder dlFolder = (DLFolder)parentBaseModel;

		return addBaseModelWithWorkflow(
			dlFolder.getGroupId(), dlFolder.getFolderId(), approved);
	}

	@Override
	protected BaseModel<?> addBaseModelWithWorkflow(
			boolean approved, ServiceContext serviceContext)
		throws Exception {

		return addBaseModelWithWorkflow(
			serviceContext.getScopeGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, approved);
	}

	protected BaseModel<?> addBaseModelWithWorkflow(
			long groupId, long folderId, boolean approved)
		throws Exception {

		String title = getSearchKeywords();

		title += ServiceTestUtil.randomString(
			_FILE_ENTRY_TITLE_MAX_LENGTH - title.length());

		FileEntry fileEntry = DLAppTestUtil.addFileEntry(
			groupId, folderId, ServiceTestUtil.randomString() + ".txt", title,
			approved);

		return (DLFileEntry)fileEntry.getModel();
	}

	@Override
	protected void deleteParentBaseModel(
			BaseModel<?> parentBaseModel, boolean includeTrashedEntries)
		throws Exception {

		DLFolder dlFolder = (DLFolder)parentBaseModel;

		DLFolderLocalServiceUtil.deleteFolder(dlFolder.getFolderId(), false);
	}

	protected int getActiveDLFileRanksCount(long groupId, long fileEntryId)
		throws Exception {

		List<DLFileRank> dlFileRanks = DLFileRankLocalServiceUtil.getFileRanks(
			groupId, TestPropsValues.getUserId());

		int count = 0;

		for (DLFileRank dlFileRank : dlFileRanks) {
			if (dlFileRank.getFileEntryId() == fileEntryId) {
				count++;
			}
		}

		return count;
	}

	@Override
	protected BaseModel<?> getBaseModel(long primaryKey) throws Exception {
		return DLFileEntryLocalServiceUtil.getDLFileEntry(primaryKey);
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return DLFileEntry.class;
	}

	@Override
	protected String getBaseModelName(ClassedModel classedModel) {
		DLFileEntry dlFileEntry = (DLFileEntry)classedModel;

		return dlFileEntry.getTitle();
	}

	@Override
	protected int getMineBaseModelsCount(long groupId, long userId)
		throws Exception {

		return DLAppServiceUtil.getGroupFileEntriesCount(
			groupId, userId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, null,
			WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	protected int getNotInTrashBaseModelsCount(BaseModel<?> parentBaseModel)
		throws Exception {

		DLFolder dlFolder = (DLFolder)parentBaseModel;

		return DLFileEntryServiceUtil.getFileEntriesCount(
			dlFolder.getGroupId(), dlFolder.getFolderId(),
			WorkflowConstants.STATUS_ANY);
	}

	@Override
	protected BaseModel<?> getParentBaseModel(
			Group group, ServiceContext serviceContext)
		throws Exception {

		Folder folder = DLAppTestUtil.addFolder(
			group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			ServiceTestUtil.randomString(_FOLDER_NAME_MAX_LENGTH));

		return (DLFolder)folder.getModel();
	}

	@Override
	protected Class<?> getParentBaseModelClass() {
		return DLFolder.class;
	}

	@Override
	protected int getRecentBaseModelsCount(long groupId) throws Exception {
		return DLAppServiceUtil.getGroupFileEntriesCount(
			groupId, 0, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, null,
			WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	protected String getSearchKeywords() {
		return "Title";
	}

	@Override
	protected String getUniqueTitle(BaseModel<?> baseModel) {
		DLFileEntry dlFileEntry = (DLFileEntry)baseModel;

		String title = dlFileEntry.getTitle();

		return TrashUtil.getOriginalTitle(title);
	}

	@Override
	protected WorkflowedModel getWorkflowedModel(ClassedModel baseModel)
		throws Exception {

		DLFileEntry dlFileEntry = (DLFileEntry)baseModel;

		return dlFileEntry.getFileVersion();
	}

	@Override
	protected BaseModel<?> moveBaseModelFromTrash(
			ClassedModel classedModel, Group group,
			ServiceContext serviceContext)
		throws Exception {

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		DLAppServiceUtil.moveFileEntryFromTrash(
			(Long)classedModel.getPrimaryKeyObj(),
			(Long)parentBaseModel.getPrimaryKeyObj(), serviceContext);

		return parentBaseModel;
	}

	@Override
	protected void moveBaseModelToTrash(long primaryKey) throws Exception {
		DLAppServiceUtil.moveFileEntryToTrash(primaryKey);
	}

	@Override
	protected void moveParentBaseModelToTrash(long primaryKey)
		throws Exception {

		DLAppServiceUtil.moveFolderToTrash(primaryKey);
	}

	@Override
	protected void restoreParentBaseModelFromTrash(long primaryKey)
		throws Exception {

		DLAppServiceUtil.restoreFolderFromTrash(primaryKey);
	}

	protected void trashDLFileRank() throws Exception {
		Group group = GroupTestUtil.addGroup();

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			group.getGroupId());

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		BaseModel<?> baseModel = addBaseModel(
			parentBaseModel, true, serviceContext);

		DLAppLocalServiceUtil.addFileRank(
			group.getGroupId(), TestPropsValues.getCompanyId(),
			TestPropsValues.getUserId(), (Long)baseModel.getPrimaryKeyObj(),
			serviceContext);

		Assert.assertEquals(
			1,
			getActiveDLFileRanksCount(
				group.getGroupId(), (Long)baseModel.getPrimaryKeyObj()));

		moveBaseModelToTrash((Long)baseModel.getPrimaryKeyObj());

		Assert.assertEquals(
			0,
			getActiveDLFileRanksCount(
				group.getGroupId(), (Long)baseModel.getPrimaryKeyObj()));

		TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(
			getBaseModelClassName());

		trashHandler.restoreTrashEntry(
			TestPropsValues.getUserId(), getTrashEntryClassPK(baseModel));

		Assert.assertEquals(
			1,
			getActiveDLFileRanksCount(
				group.getGroupId(), (Long)baseModel.getPrimaryKeyObj()));
	}

	@Override
	protected BaseModel<?> updateBaseModel(
			long primaryKey, ServiceContext serviceContext)
		throws Exception {

		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(
			primaryKey);

		String content = "Content: Enterprise. Open Source. For Life.";

		FileEntry fileEntry = DLAppServiceUtil.updateFileEntry(
			primaryKey, ServiceTestUtil.randomString() + ".txt",
			ContentTypes.TEXT_PLAIN, dlFileEntry.getTitle(), StringPool.BLANK,
			StringPool.BLANK, false, content.getBytes(), serviceContext);

		LiferayFileEntry liferayFileEntry = (LiferayFileEntry)fileEntry;

		return liferayFileEntry.getDLFileEntry();
	}

	private static final int _FILE_ENTRY_TITLE_MAX_LENGTH = 255;

	private static final int _FOLDER_NAME_MAX_LENGTH = 100;

}