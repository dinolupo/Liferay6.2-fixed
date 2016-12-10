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

package com.liferay.portlet.dynamicdatalists.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DDLRecordSetService}.
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordSetService
 * @generated
 */
@ProviderType
public class DDLRecordSetServiceWrapper implements DDLRecordSetService,
	ServiceWrapper<DDLRecordSetService> {
	public DDLRecordSetServiceWrapper(DDLRecordSetService ddlRecordSetService) {
		_ddlRecordSetService = ddlRecordSetService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _ddlRecordSetService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ddlRecordSetService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet addRecordSet(
		long groupId, long ddmStructureId, java.lang.String recordSetKey,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int minDisplayRows, int scope,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.addRecordSet(groupId, ddmStructureId,
			recordSetKey, nameMap, descriptionMap, minDisplayRows, scope,
			serviceContext);
	}

	@Override
	public void deleteRecordSet(long recordSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ddlRecordSetService.deleteRecordSet(recordSetId);
	}

	@Override
	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet getRecordSet(
		long recordSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.getRecordSet(recordSetId);
	}

	@Override
	public java.util.List<com.liferay.portlet.dynamicdatalists.model.DDLRecordSet> search(
		long companyId, long groupId, java.lang.String keywords, int scope,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.search(companyId, groupId, keywords, scope,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.portlet.dynamicdatalists.model.DDLRecordSet> search(
		long companyId, long groupId, java.lang.String name,
		java.lang.String description, int scope, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.search(companyId, groupId, name,
			description, scope, andOperator, start, end, orderByComparator);
	}

	@Override
	public int searchCount(long companyId, long groupId,
		java.lang.String keywords, int scope)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.searchCount(companyId, groupId, keywords,
			scope);
	}

	@Override
	public int searchCount(long companyId, long groupId, java.lang.String name,
		java.lang.String description, int scope, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.searchCount(companyId, groupId, name,
			description, scope, andOperator);
	}

	@Override
	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet updateMinDisplayRows(
		long recordSetId, int minDisplayRows,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.updateMinDisplayRows(recordSetId,
			minDisplayRows, serviceContext);
	}

	@Override
	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet updateRecordSet(
		long recordSetId, long ddmStructureId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int minDisplayRows,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.updateRecordSet(recordSetId,
			ddmStructureId, nameMap, descriptionMap, minDisplayRows,
			serviceContext);
	}

	@Override
	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet updateRecordSet(
		long groupId, long ddmStructureId, java.lang.String recordSetKey,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int minDisplayRows,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.updateRecordSet(groupId, ddmStructureId,
			recordSetKey, nameMap, descriptionMap, minDisplayRows,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public DDLRecordSetService getWrappedDDLRecordSetService() {
		return _ddlRecordSetService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedDDLRecordSetService(
		DDLRecordSetService ddlRecordSetService) {
		_ddlRecordSetService = ddlRecordSetService;
	}

	@Override
	public DDLRecordSetService getWrappedService() {
		return _ddlRecordSetService;
	}

	@Override
	public void setWrappedService(DDLRecordSetService ddlRecordSetService) {
		_ddlRecordSetService = ddlRecordSetService;
	}

	private DDLRecordSetService _ddlRecordSetService;
}