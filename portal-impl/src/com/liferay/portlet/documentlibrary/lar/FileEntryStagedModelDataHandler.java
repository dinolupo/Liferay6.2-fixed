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
import com.liferay.portal.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataException;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileEntryWrapper;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Repository;
import com.liferay.portal.repository.liferayrepository.LiferayRepository;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata;
import com.liferay.portlet.documentlibrary.model.DLFileEntryType;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLImpl;
import com.liferay.portlet.documentlibrary.util.DLProcessorRegistryUtil;
import com.liferay.portlet.documentlibrary.util.DLProcessorThreadLocal;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.storage.StorageEngineUtil;
import com.liferay.portlet.trash.util.TrashUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Map;

/**
 * @author Mate Thurzo
 */
public class FileEntryStagedModelDataHandler
	extends BaseStagedModelDataHandler<FileEntry> {

	public static final String[] CLASS_NAMES = {
		DLFileEntry.class.getName(), FileEntry.class.getName(),
		LiferayFileEntry.class.getName()
	};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		FileEntry fileEntry = FileEntryUtil.fetchByUUID_R(uuid, groupId);

		if (fileEntry != null) {
			DLAppLocalServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(FileEntry fileEntry) {
		if (fileEntry.isInTrash()) {
			return TrashUtil.getOriginalTitle(fileEntry.getTitle());
		}

		return fileEntry.getTitle();
	}

	@Override
	public void importStagedModel(
			PortletDataContext portletDataContext, FileEntry fileEntry)
		throws PortletDataException {

		boolean dlProcessorEnabled = DLProcessorThreadLocal.isEnabled();

		try {
			DLProcessorThreadLocal.setEnabled(false);

			super.importStagedModel(portletDataContext, fileEntry);
		}
		finally {
			DLProcessorThreadLocal.setEnabled(dlProcessorEnabled);
		}
	}

	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext, FileEntry stagedModel)
		throws PortletDataException {

		try {
			doRestoreStagedModel(portletDataContext, stagedModel);
		}
		catch (PortletDataException pde) {
			throw pde;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, FileEntry fileEntry)
		throws Exception {

		Element fileEntryElement = portletDataContext.getExportDataElement(
			fileEntry);

		String fileEntryPath = ExportImportPathUtil.getModelPath(fileEntry);

		if (!fileEntry.isDefaultRepository()) {
			Repository repository = RepositoryLocalServiceUtil.getRepository(
				fileEntry.getRepositoryId());

			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, fileEntry, repository,
				PortletDataContext.REFERENCE_TYPE_STRONG);

			portletDataContext.addClassedModel(
				fileEntryElement, fileEntryPath, fileEntry);

			long liferayRepositoryClassNameId = PortalUtil.getClassNameId(
				LiferayRepository.class.getName());

			if (repository.getClassNameId() != liferayRepositoryClassNameId) {
				return;
			}
		}

		if (fileEntry.getFolderId() !=
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, fileEntry, fileEntry.getFolder(),
				PortletDataContext.REFERENCE_TYPE_PARENT);
		}

		LiferayFileEntry liferayFileEntry = (LiferayFileEntry)unwrap(fileEntry);

		liferayFileEntry.setCachedFileVersion(fileEntry.getFileVersion());

		if (!portletDataContext.isPerformDirectBinaryImport()) {
			InputStream is = null;

			try {
				is = FileEntryUtil.getContentStream(fileEntry);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to retrieve content for file entry " +
							fileEntry.getFileEntryId(),
						e);
				}
			}

			if (is == null) {
				fileEntryElement.detach();

				return;
			}

			try {
				String binPath = ExportImportPathUtil.getModelPath(
					fileEntry, fileEntry.getVersion());

				if (portletDataContext.isPathNotProcessed(binPath)) {
					portletDataContext.addZipEntry(binPath, is);
				}

				fileEntryElement.addAttribute("bin-path", binPath);
			}
			finally {
				try {
					is.close();
				}
				catch (IOException ioe) {
					_log.error(ioe, ioe);
				}
			}
		}

		if (portletDataContext.getBooleanParameter(
				DLPortletDataHandler.NAMESPACE, "previews-and-thumbnails")) {

			DLProcessorRegistryUtil.exportGeneratedFiles(
				portletDataContext, fileEntry, fileEntryElement);
		}

		exportMetaData(portletDataContext, fileEntryElement, fileEntry);

		portletDataContext.addClassedModel(
			fileEntryElement, fileEntryPath, liferayFileEntry,
			DLFileEntry.class);
	}

	@Override
	protected void doImportCompanyStagedModel(
			PortletDataContext portletDataContext, String uuid,
			long fileEntryId)
		throws Exception {

		FileEntry existingFileEntry = FileEntryUtil.fetchByUUID_R(
			uuid, portletDataContext.getScopeGroupId());

		if (existingFileEntry == null) {
			existingFileEntry = FileEntryUtil.fetchByUUID_R(
				uuid, portletDataContext.getCompanyGroupId());
		}

		Map<Long, Long> dlFileEntryIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DLFileEntry.class);

		dlFileEntryIds.put(fileEntryId, existingFileEntry.getFileEntryId());

		Map<Long, Long> fileEntryIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				FileEntry.class);

		fileEntryIds.put(fileEntryId, existingFileEntry.getFileEntryId());
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, FileEntry fileEntry)
		throws Exception {

		long userId = portletDataContext.getUserId(fileEntry.getUserUuid());

		if (!fileEntry.isDefaultRepository()) {
			StagedModelDataHandlerUtil.importReferenceStagedModel(
				portletDataContext, fileEntry, Repository.class,
				fileEntry.getRepositoryId());

			return;
		}

		if (fileEntry.getFolderId() !=
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			StagedModelDataHandlerUtil.importReferenceStagedModel(
				portletDataContext, fileEntry, DLFolder.class,
				fileEntry.getFolderId());
		}

		Map<Long, Long> folderIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Folder.class);

		long folderId = MapUtil.getLong(
			folderIds, fileEntry.getFolderId(), fileEntry.getFolderId());

		long[] assetCategoryIds = portletDataContext.getAssetCategoryIds(
			DLFileEntry.class, fileEntry.getFileEntryId());
		String[] assetTagNames = portletDataContext.getAssetTagNames(
			DLFileEntry.class, fileEntry.getFileEntryId());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			fileEntry, DLFileEntry.class);

		serviceContext.setAttribute(
			"sourceFileName", "A." + fileEntry.getExtension());
		serviceContext.setUserId(userId);

		Element fileEntryElement = portletDataContext.getImportDataElement(
			fileEntry);

		String binPath = fileEntryElement.attributeValue("bin-path");

		InputStream is = null;

		if (Validator.isNull(binPath) &&
			portletDataContext.isPerformDirectBinaryImport()) {

			try {
				is = FileEntryUtil.getContentStream(fileEntry);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to retrieve content for file entry " +
							fileEntry.getFileEntryId(),
						e);
				}

				return;
			}
		}
		else {
			is = portletDataContext.getZipEntryAsInputStream(binPath);
		}

		if (is == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No file found for file entry " +
						fileEntry.getFileEntryId());
			}

			return;
		}

		importMetaData(
			portletDataContext, fileEntryElement, fileEntry, serviceContext);

		FileEntry importedFileEntry = null;

		String titleWithExtension = DLUtil.getTitleWithExtension(fileEntry);

		if (portletDataContext.isDataStrategyMirror()) {
			FileEntry existingFileEntry = FileEntryUtil.fetchByUUID_R(
				fileEntry.getUuid(), portletDataContext.getScopeGroupId());

			FileVersion fileVersion = fileEntry.getFileVersion();

			if (existingFileEntry == null) {
				if (portletDataContext.isDataStrategyMirrorWithOverwriting()) {
					FileEntry existingTitleFileEntry =
						FileEntryUtil.fetchByR_F_T(
							portletDataContext.getScopeGroupId(), folderId,
							fileEntry.getTitle());

					if (existingTitleFileEntry != null) {
						DLAppLocalServiceUtil.deleteFileEntry(
							existingTitleFileEntry.getFileEntryId());
					}
				}

				serviceContext.setAttribute(
					"fileVersionUuid", fileVersion.getUuid());
				serviceContext.setUuid(fileEntry.getUuid());

				String fileEntryTitle =
					DLFileEntryLocalServiceUtil.getUniqueTitle(
						portletDataContext.getScopeGroupId(), folderId, 0,
						fileEntry.getTitle(), fileEntry.getExtension());

				importedFileEntry = DLAppLocalServiceUtil.addFileEntry(
					userId, portletDataContext.getScopeGroupId(), folderId,
					titleWithExtension, fileEntry.getMimeType(), fileEntryTitle,
					fileEntry.getDescription(), null, is, fileEntry.getSize(),
					serviceContext);

				if (fileEntry.isInTrash()) {
					importedFileEntry = DLAppServiceUtil.moveFileEntryToTrash(
						importedFileEntry.getFileEntryId());
				}
			}
			else {
				FileVersion latestExistingFileVersion =
					DLImpl.getLatestFileVersion(existingFileEntry, true);

				boolean indexEnabled = serviceContext.isIndexingEnabled();

				boolean deleteFileEntry = false;
				boolean updateFileEntry = false;

				if (!Validator.equals(
						fileVersion.getUuid(),
						latestExistingFileVersion.getUuid())) {

					deleteFileEntry = true;
					updateFileEntry = true;
				}
				else {
					InputStream existingFileVersionInputStream = null;

					try {
						existingFileVersionInputStream =
							latestExistingFileVersion.getContentStream(false);
					}
					catch (Exception e) {
						if (_log.isDebugEnabled()) {
							_log.debug(e, e);
						}
					}
					finally {
						if (existingFileVersionInputStream != null) {
							existingFileVersionInputStream.close();
						}
					}

					if (existingFileVersionInputStream == null) {
						updateFileEntry = true;
					}
				}

				try {
					serviceContext.setIndexingEnabled(false);

					if (updateFileEntry) {
						DLFileVersion alreadyExistingFileVersion =
							DLFileVersionLocalServiceUtil.
								getFileVersionByUuidAndGroupId(
									fileVersion.getUuid(),
									existingFileEntry.getGroupId());

						if (alreadyExistingFileVersion != null) {
							serviceContext.setAttribute(
								"existingDLFileVersionId",
								alreadyExistingFileVersion.getFileVersionId());
						}

						serviceContext.setUuid(fileVersion.getUuid());

						String fileEntryTitle =
							DLFileEntryLocalServiceUtil.getUniqueTitle(
								portletDataContext.getScopeGroupId(),
								existingFileEntry.getFolderId(),
								existingFileEntry.getFileEntryId(),
								fileEntry.getTitle(), fileEntry.getExtension());

						importedFileEntry =
							DLAppLocalServiceUtil.updateFileEntry(
								userId, existingFileEntry.getFileEntryId(),
								titleWithExtension, fileEntry.getMimeType(),
								fileEntryTitle, fileEntry.getDescription(),
								null, false, is, fileEntry.getSize(),
								serviceContext);
					}
					else {
						DLAppLocalServiceUtil.updateAsset(
							userId, existingFileEntry,
							latestExistingFileVersion, assetCategoryIds,
							assetTagNames, null);

						importedFileEntry = existingFileEntry;
					}

					if (importedFileEntry.getFolderId() != folderId) {
						importedFileEntry = DLAppLocalServiceUtil.moveFileEntry(
							userId, importedFileEntry.getFileEntryId(),
							folderId, serviceContext);
					}

					FileEntry unwrappedFileEntry = unwrap(importedFileEntry);

					if (unwrappedFileEntry instanceof LiferayFileEntry) {
						LiferayFileEntry liferayFileEntry =
							(LiferayFileEntry)unwrappedFileEntry;

						Indexer indexer = IndexerRegistryUtil.getIndexer(
							DLFileEntry.class);

						indexer.reindex(liferayFileEntry.getModel());
					}

					if (deleteFileEntry &&
						ExportImportThreadLocal.isStagingInProcess()) {

						DLAppServiceUtil.deleteFileVersion(
							latestExistingFileVersion.getFileEntryId(),
							latestExistingFileVersion.getVersion());
					}
				}
				finally {
					serviceContext.setIndexingEnabled(indexEnabled);
				}
			}
		}
		else {
			String fileEntryTitle = DLFileEntryLocalServiceUtil.getUniqueTitle(
					portletDataContext.getScopeGroupId(), folderId, 0,
					fileEntry.getTitle(), fileEntry.getExtension());

			importedFileEntry = DLAppLocalServiceUtil.addFileEntry(
				userId, portletDataContext.getScopeGroupId(), folderId,
				titleWithExtension, fileEntry.getMimeType(), fileEntryTitle,
				fileEntry.getDescription(), null, is, fileEntry.getSize(),
				serviceContext);
		}

		if (portletDataContext.getBooleanParameter(
				DLPortletDataHandler.NAMESPACE, "previews-and-thumbnails")) {

			DLProcessorRegistryUtil.importGeneratedFiles(
				portletDataContext, fileEntry, importedFileEntry,
				fileEntryElement);
		}

		portletDataContext.importClassedModel(
			fileEntry, importedFileEntry, DLFileEntry.class);

		Map<Long, Long> fileEntryIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				FileEntry.class);

		fileEntryIds.put(
			fileEntry.getFileEntryId(), importedFileEntry.getFileEntryId());
	}

	@Override
	protected void doRestoreStagedModel(
			PortletDataContext portletDataContext, FileEntry fileEntry)
		throws Exception {

		long userId = portletDataContext.getUserId(fileEntry.getUserUuid());

		FileEntry existingFileEntry = FileEntryUtil.fetchByUUID_R(
			fileEntry.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingFileEntry == null) || !existingFileEntry.isInTrash()) {
			return;
		}

		TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(
			DLFileEntry.class.getName());

		if (trashHandler.isRestorable(existingFileEntry.getFileEntryId())) {
			trashHandler.restoreTrashEntry(
				userId, existingFileEntry.getFileEntryId());
		}
	}

	protected void exportMetaData(
			PortletDataContext portletDataContext, Element fileEntryElement,
			FileEntry fileEntry)
		throws Exception {

		LiferayFileEntry liferayFileEntry = (LiferayFileEntry)unwrap(fileEntry);

		DLFileEntry dlFileEntry = liferayFileEntry.getDLFileEntry();

		long fileEntryTypeId = dlFileEntry.getFileEntryTypeId();

		DLFileEntryType dlFileEntryType =
			DLFileEntryTypeLocalServiceUtil.fetchFileEntryType(fileEntryTypeId);

		if ((dlFileEntryType == null) || !dlFileEntryType.isExportable()) {
			return;
		}

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, fileEntry, dlFileEntryType,
			PortletDataContext.REFERENCE_TYPE_STRONG);

		List<DDMStructure> ddmStructures = dlFileEntryType.getDDMStructures();

		for (DDMStructure ddmStructure : ddmStructures) {
			FileVersion fileVersion = fileEntry.getFileVersion();

			DLFileEntryMetadata dlFileEntryMetadata =
				DLFileEntryMetadataLocalServiceUtil.fetchFileEntryMetadata(
					ddmStructure.getStructureId(),
					fileVersion.getFileVersionId());

			if (dlFileEntryMetadata == null) {
				continue;
			}

			Element structureFields = fileEntryElement.addElement(
				"structure-fields");

			String path = ExportImportPathUtil.getModelPath(
				ddmStructure,
				String.valueOf(dlFileEntryMetadata.getDDMStorageId()));

			structureFields.addAttribute("path", path);

			structureFields.addAttribute(
				"structureUuid", ddmStructure.getUuid());

			Fields fields = StorageEngineUtil.getFields(
				dlFileEntryMetadata.getDDMStorageId());

			portletDataContext.addZipEntry(path, fields);
		}
	}

	protected void importMetaData(
			PortletDataContext portletDataContext, Element fileEntryElement,
			FileEntry fileEntry, ServiceContext serviceContext)
		throws Exception {

		LiferayFileEntry liferayFileEntry = (LiferayFileEntry)fileEntry;

		DLFileEntry dlFileEntry = liferayFileEntry.getDLFileEntry();

		StagedModelDataHandlerUtil.importReferenceStagedModel(
			portletDataContext, fileEntry, DLFileEntryType.class,
			dlFileEntry.getFileEntryTypeId());

		Map<Long, Long> dlFileEntryTypeIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DLFileEntryType.class);

		long dlFileEntryTypeId = MapUtil.getLong(
			dlFileEntryTypeIds, dlFileEntry.getFileEntryTypeId(),
			dlFileEntry.getFileEntryTypeId());

		DLFileEntryType existingDLFileEntryType =
			DLFileEntryTypeLocalServiceUtil.fetchDLFileEntryType(
				dlFileEntryTypeId);

		if (existingDLFileEntryType == null) {
			serviceContext.setAttribute("fileEntryTypeId", -1);

			return;
		}

		serviceContext.setAttribute(
			"fileEntryTypeId", existingDLFileEntryType.getFileEntryTypeId());

		List<DDMStructure> ddmStructures =
			existingDLFileEntryType.getDDMStructures();

		for (DDMStructure ddmStructure : ddmStructures) {
			Element structureFieldsElement =
				(Element)fileEntryElement.selectSingleNode(
					"structure-fields[@structureUuid='".concat(
						ddmStructure.getUuid()).concat("']"));

			if (structureFieldsElement == null) {
				continue;
			}

			String path = structureFieldsElement.attributeValue("path");

			Fields fields = (Fields)portletDataContext.getZipEntryAsObject(
				path);

			serviceContext.setAttribute(
				Fields.class.getName() + ddmStructure.getStructureId(), fields);
		}
	}

	@Override
	protected void validateExport(
			PortletDataContext portletDataContext, FileEntry fileEntry)
		throws PortletDataException {

		if ((fileEntry.getGroupId() != portletDataContext.getGroupId()) &&
			(fileEntry.getGroupId() != portletDataContext.getScopeGroupId())) {

			PortletDataException pde = new PortletDataException(
				PortletDataException.INVALID_GROUP);

			pde.setStagedModel(fileEntry);

			throw pde;
		}

		try {
			FileVersion fileVersion = fileEntry.getFileVersion();

			if (!portletDataContext.isInitialPublication() &&
				!ArrayUtil.contains(
					getExportableStatuses(), fileVersion.getStatus())) {

				throw new PortletDataException(
					PortletDataException.STATUS_UNAVAILABLE);
			}
		}
		catch (PortletDataException pde) {
			throw pde;
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to check workflow status for file entry " +
						fileEntry.getFileEntryId());
			}
		}

		if (fileEntry.isInTrash() || fileEntry.isInTrashContainer()) {
			PortletDataException pde = new PortletDataException(
				PortletDataException.STATUS_IN_TRASH);

			pde.setStagedModel(fileEntry);

			throw pde;
		}
	}

	@Override
	protected boolean validateMissingReference(
			String uuid, long companyId, long groupId)
		throws Exception {

		DLFileEntry dlFileEntry =
			DLFileEntryLocalServiceUtil.fetchDLFileEntryByUuidAndGroupId(
				uuid, groupId);

		if (dlFileEntry == null) {
			return false;
		}

		return true;
	}

	protected FileEntry unwrap(FileEntry fileEntry) {
		while (fileEntry instanceof FileEntryWrapper) {
			FileEntryWrapper fileEntryWrapper = (FileEntryWrapper)fileEntry;

			fileEntry = fileEntryWrapper.getWrappedModel();
		}

		return fileEntry;
	}

	private static Log _log = LogFactoryUtil.getLog(
		FileEntryStagedModelDataHandler.class);

}