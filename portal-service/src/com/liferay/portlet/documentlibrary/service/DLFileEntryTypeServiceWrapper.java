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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DLFileEntryTypeService}.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryTypeService
 * @generated
 */
@ProviderType
public class DLFileEntryTypeServiceWrapper implements DLFileEntryTypeService,
	ServiceWrapper<DLFileEntryTypeService> {
	public DLFileEntryTypeServiceWrapper(
		DLFileEntryTypeService dlFileEntryTypeService) {
		_dlFileEntryTypeService = dlFileEntryTypeService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dlFileEntryTypeService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dlFileEntryTypeService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portlet.documentlibrary.model.DLFileEntryType addFileEntryType(
		long groupId, java.lang.String fileEntryTypeKey,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long[] ddmStructureIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.addFileEntryType(groupId,
			fileEntryTypeKey, nameMap, descriptionMap, ddmStructureIds,
			serviceContext);
	}

	@Override
	public com.liferay.portlet.documentlibrary.model.DLFileEntryType addFileEntryType(
		long groupId, java.lang.String name, java.lang.String description,
		long[] ddmStructureIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.addFileEntryType(groupId, name,
			description, ddmStructureIds, serviceContext);
	}

	@Override
	public void deleteFileEntryType(long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryTypeService.deleteFileEntryType(fileEntryTypeId);
	}

	@Override
	public com.liferay.portlet.documentlibrary.model.DLFileEntryType getFileEntryType(
		long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.getFileEntryType(fileEntryTypeId);
	}

	@Override
	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> getFileEntryTypes(
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.getFileEntryTypes(groupIds);
	}

	@Override
	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> getFileEntryTypes(
		long[] groupIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.getFileEntryTypes(groupIds, start, end);
	}

	@Override
	public int getFileEntryTypesCount(long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.getFileEntryTypesCount(groupIds);
	}

	@Override
	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> getFolderFileEntryTypes(
		long[] groupIds, long folderId, boolean inherited)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.getFolderFileEntryTypes(groupIds,
			folderId, inherited);
	}

	@Override
	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> search(
		long companyId, long[] groupIds, java.lang.String keywords,
		boolean includeBasicFileEntryType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.search(companyId, groupIds, keywords,
			includeBasicFileEntryType, start, end, orderByComparator);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		java.lang.String keywords, boolean includeBasicFileEntryType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.searchCount(companyId, groupIds,
			keywords, includeBasicFileEntryType);
	}

	@Override
	public void updateFileEntryType(long fileEntryTypeId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long[] ddmStructureIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryTypeService.updateFileEntryType(fileEntryTypeId, nameMap,
			descriptionMap, ddmStructureIds, serviceContext);
	}

	@Override
	public void updateFileEntryType(long fileEntryTypeId,
		java.lang.String name, java.lang.String description,
		long[] ddmStructureIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryTypeService.updateFileEntryType(fileEntryTypeId, name,
			description, ddmStructureIds, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public DLFileEntryTypeService getWrappedDLFileEntryTypeService() {
		return _dlFileEntryTypeService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedDLFileEntryTypeService(
		DLFileEntryTypeService dlFileEntryTypeService) {
		_dlFileEntryTypeService = dlFileEntryTypeService;
	}

	@Override
	public DLFileEntryTypeService getWrappedService() {
		return _dlFileEntryTypeService;
	}

	@Override
	public void setWrappedService(DLFileEntryTypeService dlFileEntryTypeService) {
		_dlFileEntryTypeService = dlFileEntryTypeService;
	}

	private DLFileEntryTypeService _dlFileEntryTypeService;
}