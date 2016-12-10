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

package com.liferay.portlet.trash.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TrashVersionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TrashVersionLocalService
 * @generated
 */
@ProviderType
public class TrashVersionLocalServiceWrapper implements TrashVersionLocalService,
	ServiceWrapper<TrashVersionLocalService> {
	public TrashVersionLocalServiceWrapper(
		TrashVersionLocalService trashVersionLocalService) {
		_trashVersionLocalService = trashVersionLocalService;
	}

	/**
	* Adds the trash version to the database. Also notifies the appropriate model listeners.
	*
	* @param trashVersion the trash version
	* @return the trash version that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.trash.model.TrashVersion addTrashVersion(
		com.liferay.portlet.trash.model.TrashVersion trashVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.addTrashVersion(trashVersion);
	}

	/**
	* Creates a new trash version with the primary key. Does not add the trash version to the database.
	*
	* @param versionId the primary key for the new trash version
	* @return the new trash version
	*/
	@Override
	public com.liferay.portlet.trash.model.TrashVersion createTrashVersion(
		long versionId) {
		return _trashVersionLocalService.createTrashVersion(versionId);
	}

	/**
	* Deletes the trash version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param versionId the primary key of the trash version
	* @return the trash version that was removed
	* @throws PortalException if a trash version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.trash.model.TrashVersion deleteTrashVersion(
		long versionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.deleteTrashVersion(versionId);
	}

	/**
	* Deletes the trash version from the database. Also notifies the appropriate model listeners.
	*
	* @param trashVersion the trash version
	* @return the trash version that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.trash.model.TrashVersion deleteTrashVersion(
		com.liferay.portlet.trash.model.TrashVersion trashVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.deleteTrashVersion(trashVersion);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trashVersionLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portlet.trash.model.TrashVersion fetchTrashVersion(
		long versionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.fetchTrashVersion(versionId);
	}

	/**
	* Returns the trash version with the primary key.
	*
	* @param versionId the primary key of the trash version
	* @return the trash version
	* @throws PortalException if a trash version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.trash.model.TrashVersion getTrashVersion(
		long versionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.getTrashVersion(versionId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the trash versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of trash versions
	* @param end the upper bound of the range of trash versions (not inclusive)
	* @return the range of trash versions
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portlet.trash.model.TrashVersion> getTrashVersions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.getTrashVersions(start, end);
	}

	/**
	* Returns the number of trash versions.
	*
	* @return the number of trash versions
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getTrashVersionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.getTrashVersionsCount();
	}

	/**
	* Updates the trash version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trashVersion the trash version
	* @return the trash version that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.trash.model.TrashVersion updateTrashVersion(
		com.liferay.portlet.trash.model.TrashVersion trashVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.updateTrashVersion(trashVersion);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _trashVersionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trashVersionLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public void addTrashVersion(long trashEntryId, java.lang.String className,
		long classPK, int status,
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties)
		throws com.liferay.portal.kernel.exception.SystemException {
		_trashVersionLocalService.addTrashVersion(trashEntryId, className,
			classPK, status, typeSettingsProperties);
	}

	@Override
	public com.liferay.portlet.trash.model.TrashVersion deleteTrashVersion(
		long entryId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.deleteTrashVersion(entryId, className,
			classPK);
	}

	@Override
	public com.liferay.portlet.trash.model.TrashVersion fetchVersion(
		long entryId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.fetchVersion(entryId, className,
			classPK);
	}

	@Override
	public java.util.List<com.liferay.portlet.trash.model.TrashVersion> getVersions(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.getVersions(entryId);
	}

	@Override
	public java.util.List<com.liferay.portlet.trash.model.TrashVersion> getVersions(
		long entryId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.getVersions(entryId, className);
	}

	/**
	* Returns all the trash versions associated with the trash entry.
	*
	* @param className the class name of the trash entity
	* @param classPK the primary key of the trash entity
	* @return all the trash versions associated with the trash entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portlet.trash.model.TrashVersion> getVersions(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trashVersionLocalService.getVersions(className, classPK);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public TrashVersionLocalService getWrappedTrashVersionLocalService() {
		return _trashVersionLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedTrashVersionLocalService(
		TrashVersionLocalService trashVersionLocalService) {
		_trashVersionLocalService = trashVersionLocalService;
	}

	@Override
	public TrashVersionLocalService getWrappedService() {
		return _trashVersionLocalService;
	}

	@Override
	public void setWrappedService(
		TrashVersionLocalService trashVersionLocalService) {
		_trashVersionLocalService = trashVersionLocalService;
	}

	private TrashVersionLocalService _trashVersionLocalService;
}