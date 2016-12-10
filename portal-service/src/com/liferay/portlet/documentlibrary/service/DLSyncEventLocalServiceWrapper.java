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
 * Provides a wrapper for {@link DLSyncEventLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DLSyncEventLocalService
 * @generated
 */
@ProviderType
public class DLSyncEventLocalServiceWrapper implements DLSyncEventLocalService,
	ServiceWrapper<DLSyncEventLocalService> {
	public DLSyncEventLocalServiceWrapper(
		DLSyncEventLocalService dlSyncEventLocalService) {
		_dlSyncEventLocalService = dlSyncEventLocalService;
	}

	/**
	* Adds the d l sync event to the database. Also notifies the appropriate model listeners.
	*
	* @param dlSyncEvent the d l sync event
	* @return the d l sync event that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.documentlibrary.model.DLSyncEvent addDLSyncEvent(
		com.liferay.portlet.documentlibrary.model.DLSyncEvent dlSyncEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.addDLSyncEvent(dlSyncEvent);
	}

	/**
	* Creates a new d l sync event with the primary key. Does not add the d l sync event to the database.
	*
	* @param syncEventId the primary key for the new d l sync event
	* @return the new d l sync event
	*/
	@Override
	public com.liferay.portlet.documentlibrary.model.DLSyncEvent createDLSyncEvent(
		long syncEventId) {
		return _dlSyncEventLocalService.createDLSyncEvent(syncEventId);
	}

	/**
	* Deletes the d l sync event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncEventId the primary key of the d l sync event
	* @return the d l sync event that was removed
	* @throws PortalException if a d l sync event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.documentlibrary.model.DLSyncEvent deleteDLSyncEvent(
		long syncEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.deleteDLSyncEvent(syncEventId);
	}

	/**
	* Deletes the d l sync event from the database. Also notifies the appropriate model listeners.
	*
	* @param dlSyncEvent the d l sync event
	* @return the d l sync event that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.documentlibrary.model.DLSyncEvent deleteDLSyncEvent(
		com.liferay.portlet.documentlibrary.model.DLSyncEvent dlSyncEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.deleteDLSyncEvent(dlSyncEvent);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dlSyncEventLocalService.dynamicQuery();
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
		return _dlSyncEventLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLSyncEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dlSyncEventLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLSyncEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dlSyncEventLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _dlSyncEventLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dlSyncEventLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portlet.documentlibrary.model.DLSyncEvent fetchDLSyncEvent(
		long syncEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.fetchDLSyncEvent(syncEventId);
	}

	/**
	* Returns the d l sync event with the primary key.
	*
	* @param syncEventId the primary key of the d l sync event
	* @return the d l sync event
	* @throws PortalException if a d l sync event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.documentlibrary.model.DLSyncEvent getDLSyncEvent(
		long syncEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.getDLSyncEvent(syncEventId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the d l sync events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLSyncEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of d l sync events
	* @param end the upper bound of the range of d l sync events (not inclusive)
	* @return the range of d l sync events
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portlet.documentlibrary.model.DLSyncEvent> getDLSyncEvents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.getDLSyncEvents(start, end);
	}

	/**
	* Returns the number of d l sync events.
	*
	* @return the number of d l sync events
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getDLSyncEventsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.getDLSyncEventsCount();
	}

	/**
	* Updates the d l sync event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dlSyncEvent the d l sync event
	* @return the d l sync event that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.documentlibrary.model.DLSyncEvent updateDLSyncEvent(
		com.liferay.portlet.documentlibrary.model.DLSyncEvent dlSyncEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.updateDLSyncEvent(dlSyncEvent);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dlSyncEventLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dlSyncEventLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portlet.documentlibrary.model.DLSyncEvent addDLSyncEvent(
		java.lang.String event, java.lang.String type, long typePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.addDLSyncEvent(event, type, typePK);
	}

	@Override
	public void deleteDLSyncEvents()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlSyncEventLocalService.deleteDLSyncEvents();
	}

	@Override
	public java.util.List<com.liferay.portlet.documentlibrary.model.DLSyncEvent> getDLSyncEvents(
		long modifiedTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.getDLSyncEvents(modifiedTime);
	}

	@Override
	public java.util.List<com.liferay.portlet.documentlibrary.model.DLSyncEvent> getLatestDLSyncEvents()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncEventLocalService.getLatestDLSyncEvents();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public DLSyncEventLocalService getWrappedDLSyncEventLocalService() {
		return _dlSyncEventLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedDLSyncEventLocalService(
		DLSyncEventLocalService dlSyncEventLocalService) {
		_dlSyncEventLocalService = dlSyncEventLocalService;
	}

	@Override
	public DLSyncEventLocalService getWrappedService() {
		return _dlSyncEventLocalService;
	}

	@Override
	public void setWrappedService(
		DLSyncEventLocalService dlSyncEventLocalService) {
		_dlSyncEventLocalService = dlSyncEventLocalService;
	}

	private DLSyncEventLocalService _dlSyncEventLocalService;
}