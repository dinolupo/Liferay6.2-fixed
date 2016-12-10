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

package com.liferay.portlet.documentlibrary.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileShortcutLocalServiceUtil;

import java.util.Map;

/**
 * @author Mate Thurzo
 */
public class DLFileShortcutStagedModelDataHandler
	extends BaseStagedModelDataHandler<DLFileShortcut> {

	public static final String[] CLASS_NAMES = {DLFileShortcut.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		DLFileShortcut dlFileShortcut =
			DLFileShortcutLocalServiceUtil.fetchDLFileShortcutByUuidAndGroupId(
				uuid, groupId);

		if (dlFileShortcut != null) {
			DLFileShortcutLocalServiceUtil.deleteFileShortcut(dlFileShortcut);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(DLFileShortcut shortcut) {
		return shortcut.getToTitle();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, DLFileShortcut fileShortcut)
		throws Exception {

		if (fileShortcut.getFolderId() !=
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, fileShortcut, fileShortcut.getFolder(),
				PortletDataContext.REFERENCE_TYPE_PARENT);
		}

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			fileShortcut.getToFileEntryId());

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, fileShortcut, fileEntry,
			PortletDataContext.REFERENCE_TYPE_STRONG);

		Element fileShortcutElement = portletDataContext.getExportDataElement(
			fileShortcut);

		fileShortcutElement.addAttribute(
			"file-entry-uuid", fileEntry.getUuid());

		portletDataContext.addClassedModel(
			fileShortcutElement,
			ExportImportPathUtil.getModelPath(fileShortcut), fileShortcut);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, DLFileShortcut fileShortcut)
		throws Exception {

		long userId = portletDataContext.getUserId(fileShortcut.getUserUuid());

		if (fileShortcut.getFolderId() !=
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			StagedModelDataHandlerUtil.importReferenceStagedModel(
				portletDataContext, fileShortcut, DLFolder.class,
				fileShortcut.getFolderId());
		}

		Map<Long, Long> folderIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Folder.class);

		long folderId = MapUtil.getLong(
			folderIds, fileShortcut.getFolderId(), fileShortcut.getFolderId());

		long groupId = portletDataContext.getScopeGroupId();

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			Folder folder = FolderUtil.findByPrimaryKey(folderId);

			groupId = folder.getRepositoryId();
		}

		StagedModelDataHandlerUtil.importReferenceStagedModel(
			portletDataContext, fileShortcut, DLFileEntry.class,
			fileShortcut.getToFileEntryId());

		Element fileShortcutElement =
			portletDataContext.getImportDataStagedModelElement(fileShortcut);

		String fileEntryUuid = fileShortcutElement.attributeValue(
			"file-entry-uuid");

		FileEntry importedFileEntry = FileEntryUtil.fetchByUUID_R(
			fileEntryUuid, groupId);

		if (importedFileEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to fetch file entry {uuid=" + fileEntryUuid +
						", groupId=" + groupId + "}");
			}

			return;
		}

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			fileShortcut);

		DLFileShortcut importedFileShortcut = null;

		if (portletDataContext.isDataStrategyMirror()) {
			DLFileShortcut existingFileShortcut =
				DLFileShortcutLocalServiceUtil.
					fetchDLFileShortcutByUuidAndGroupId(
						fileShortcut.getUuid(),
						portletDataContext.getScopeGroupId());

			if (existingFileShortcut == null) {
				serviceContext.setUuid(fileShortcut.getUuid());

				importedFileShortcut = DLAppLocalServiceUtil.addFileShortcut(
					userId, groupId, folderId,
					importedFileEntry.getFileEntryId(), serviceContext);
			}
			else {
				importedFileShortcut = DLAppLocalServiceUtil.updateFileShortcut(
					userId, existingFileShortcut.getFileShortcutId(), folderId,
					importedFileEntry.getFileEntryId(), serviceContext);
			}
		}
		else {
			importedFileShortcut = DLAppLocalServiceUtil.addFileShortcut(
				userId, groupId, folderId, importedFileEntry.getFileEntryId(),
				serviceContext);
		}

		portletDataContext.importClassedModel(
			fileShortcut, importedFileShortcut);
	}

	@Override
	protected void doRestoreStagedModel(
			PortletDataContext portletDataContext, DLFileShortcut fileShortcut)
		throws Exception {

		long userId = portletDataContext.getUserId(fileShortcut.getUserUuid());

		DLFileShortcut existingFileShortcut =
			DLFileShortcutLocalServiceUtil.fetchDLFileShortcutByUuidAndGroupId(
				fileShortcut.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingFileShortcut == null) ||
			!existingFileShortcut.isInTrash()) {

			return;
		}

		TrashHandler trashHandler = existingFileShortcut.getTrashHandler();

		if (trashHandler.isRestorable(
				existingFileShortcut.getFileShortcutId())) {

			trashHandler.restoreTrashEntry(
				userId, existingFileShortcut.getFileShortcutId());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DLFileShortcutStagedModelDataHandler.class);

}