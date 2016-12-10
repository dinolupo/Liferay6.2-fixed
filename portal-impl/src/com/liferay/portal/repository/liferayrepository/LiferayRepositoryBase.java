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

package com.liferay.portal.repository.liferayrepository;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SortedArrayList;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.repository.liferayrepository.util.LiferayBase;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.RepositoryLocalService;
import com.liferay.portal.service.RepositoryService;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFileEntryType;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileVersionService;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalService;
import com.liferay.portlet.documentlibrary.service.DLFolderService;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.util.DDMUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Alexander Chow
 */
public abstract class LiferayRepositoryBase extends LiferayBase {

	public LiferayRepositoryBase(
		RepositoryLocalService repositoryLocalService,
		RepositoryService repositoryService,
		DLAppHelperLocalService dlAppHelperLocalService,
		DLFileEntryLocalService dlFileEntryLocalService,
		DLFileEntryService dlFileEntryService,
		DLFileEntryTypeLocalService dlFileEntryTypeLocalService,
		DLFileVersionLocalService dlFileVersionLocalService,
		DLFileVersionService dlFileVersionService,
		DLFolderLocalService dlFolderLocalService,
		DLFolderService dlFolderService,
		ResourceLocalService resourceLocalService, long repositoryId) {

		this.repositoryLocalService = repositoryLocalService;
		this.repositoryService = repositoryService;
		this.dlAppHelperLocalService = dlAppHelperLocalService;
		this.dlFileEntryLocalService = dlFileEntryLocalService;
		this.dlFileEntryService = dlFileEntryService;
		this.dlFileEntryTypeLocalService = dlFileEntryTypeLocalService;
		this.dlFileVersionLocalService = dlFileVersionLocalService;
		this.dlFileVersionService = dlFileVersionService;
		this.dlFolderLocalService = dlFolderLocalService;
		this.dlFolderService = dlFolderService;
		this.resourceLocalService = resourceLocalService;

		initByRepositoryId(repositoryId);
	}

	public LiferayRepositoryBase(
			RepositoryLocalService repositoryLocalService,
			RepositoryService repositoryService,
			DLAppHelperLocalService dlAppHelperLocalService,
			DLFileEntryLocalService dlFileEntryLocalService,
			DLFileEntryService dlFileEntryService,
			DLFileEntryTypeLocalService dlFileEntryTypeLocalService,
			DLFileVersionLocalService dlFileVersionLocalService,
			DLFileVersionService dlFileVersionService,
			DLFolderLocalService dlFolderLocalService,
			DLFolderService dlFolderService,
			ResourceLocalService resourceLocalService, long folderId,
			long fileEntryId, long fileVersionId)
		throws PrincipalException {

		this.repositoryLocalService = repositoryLocalService;
		this.repositoryService = repositoryService;
		this.dlAppHelperLocalService = dlAppHelperLocalService;
		this.dlFileEntryLocalService = dlFileEntryLocalService;
		this.dlFileEntryService = dlFileEntryService;
		this.dlFileEntryTypeLocalService = dlFileEntryTypeLocalService;
		this.dlFileVersionLocalService = dlFileVersionLocalService;
		this.dlFileVersionService = dlFileVersionService;
		this.dlFolderLocalService = dlFolderLocalService;
		this.dlFolderService = dlFolderService;
		this.resourceLocalService = resourceLocalService;

		if (folderId != 0) {
			initByFolderId(folderId);
		}
		else if (fileEntryId != 0) {
			initByFileEntryId(fileEntryId);
		}
		else if (fileVersionId != 0) {
			initByFileVersionId(fileVersionId);
		}
	}

	public long getRepositoryId() {
		return _repositoryId;
	}

	protected void addFileEntryResources(
			DLFileEntry dlFileEntry, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			resourceLocalService.addResources(
				dlFileEntry.getCompanyId(), dlFileEntry.getGroupId(),
				dlFileEntry.getUserId(), DLFileEntry.class.getName(),
				dlFileEntry.getFileEntryId(), false,
				serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			if (serviceContext.isDeriveDefaultPermissions()) {
				serviceContext.deriveDefaultPermissions(
					dlFileEntry.getRepositoryId(),
					DLFileEntryConstants.getClassName());
			}

			resourceLocalService.addModelResources(
				dlFileEntry.getCompanyId(), dlFileEntry.getGroupId(),
				dlFileEntry.getUserId(), DLFileEntry.class.getName(),
				dlFileEntry.getFileEntryId(),
				serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}
	}

	protected long getDefaultFileEntryTypeId(
			ServiceContext serviceContext, long folderId)
		throws PortalException, SystemException {

		folderId = dlFolderLocalService.getFolderId(
			serviceContext.getCompanyId(), folderId);

		return dlFileEntryTypeLocalService.getDefaultFileEntryTypeId(folderId);
	}

	protected HashMap<String, Fields> getFieldsMap(
			ServiceContext serviceContext, long fileEntryTypeId)
		throws PortalException, SystemException {

		HashMap<String, Fields> fieldsMap = new HashMap<String, Fields>();

		if (fileEntryTypeId <= 0) {
			return fieldsMap;
		}

		DLFileEntryType fileEntryType =
			DLFileEntryTypeLocalServiceUtil.getFileEntryType(fileEntryTypeId);

		List<DDMStructure> ddmStructures = fileEntryType.getDDMStructures();

		for (DDMStructure ddmStructure : ddmStructures) {
			String namespace = String.valueOf(ddmStructure.getStructureId());

			Fields fields = (Fields)serviceContext.getAttribute(
				Fields.class.getName() + ddmStructure.getStructureId());

			if (fields == null) {
				fields = DDMUtil.getFields(
					ddmStructure.getStructureId(), namespace, serviceContext);
			}

			fieldsMap.put(ddmStructure.getStructureKey(), fields);
		}

		return fieldsMap;
	}

	protected long getGroupId() {
		return _groupId;
	}

	protected SortedArrayList<Long> getLongList(
		ServiceContext serviceContext, String name) {

		String value = ParamUtil.getString(serviceContext, name);

		if (value == null) {
			return new SortedArrayList<Long>();
		}

		long[] longArray = StringUtil.split(value, 0L);

		SortedArrayList<Long> longList = new SortedArrayList<Long>();

		for (long longValue : longArray) {
			longList.add(longValue);
		}

		return longList;
	}

	protected abstract void initByFileEntryId(long fileEntryId)
		throws PrincipalException;

	protected abstract void initByFileVersionId(long fileVersionId)
		throws PrincipalException;

	protected abstract void initByFolderId(long folderId)
		throws PrincipalException;

	protected abstract void initByRepositoryId(long repositoryId);

	protected boolean isDefaultRepository() {
		if (_groupId == _repositoryId) {
			return true;
		}
		else {
			return false;
		}
	}

	protected void setDlFolderId(long dlFolderId) {
		_dlFolderId = dlFolderId;
	}

	protected void setGroupId(long groupId) {
		_groupId = groupId;
	}

	protected void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;
	}

	protected long toFolderId(long folderId) {
		if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return _dlFolderId;
		}
		else {
			return folderId;
		}
	}

	protected List<Long> toFolderIds(List<Long> folderIds) {
		List<Long> toFolderIds = new ArrayList<Long>(folderIds.size());

		for (long folderId : folderIds) {
			toFolderIds.add(toFolderId(folderId));
		}

		return toFolderIds;
	}

	protected DLAppHelperLocalService dlAppHelperLocalService;
	protected DLFileEntryLocalService dlFileEntryLocalService;
	protected DLFileEntryService dlFileEntryService;
	protected DLFileEntryTypeLocalService dlFileEntryTypeLocalService;
	protected DLFileVersionLocalService dlFileVersionLocalService;
	protected DLFileVersionService dlFileVersionService;
	protected DLFolderLocalService dlFolderLocalService;
	protected DLFolderService dlFolderService;
	protected RepositoryLocalService repositoryLocalService;
	protected RepositoryService repositoryService;
	protected ResourceLocalService resourceLocalService;

	private long _dlFolderId;
	private long _groupId;
	private long _repositoryId;

}