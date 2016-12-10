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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for ResourceAction. This utility wraps
 * {@link com.liferay.portal.service.impl.ResourceActionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ResourceActionLocalService
 * @see com.liferay.portal.service.base.ResourceActionLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.ResourceActionLocalServiceImpl
 * @generated
 */
@ProviderType
public class ResourceActionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.ResourceActionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the resource action to the database. Also notifies the appropriate model listeners.
	*
	* @param resourceAction the resource action
	* @return the resource action that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceAction addResourceAction(
		com.liferay.portal.model.ResourceAction resourceAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addResourceAction(resourceAction);
	}

	/**
	* Creates a new resource action with the primary key. Does not add the resource action to the database.
	*
	* @param resourceActionId the primary key for the new resource action
	* @return the new resource action
	*/
	public static com.liferay.portal.model.ResourceAction createResourceAction(
		long resourceActionId) {
		return getService().createResourceAction(resourceActionId);
	}

	/**
	* Deletes the resource action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceActionId the primary key of the resource action
	* @return the resource action that was removed
	* @throws PortalException if a resource action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceAction deleteResourceAction(
		long resourceActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteResourceAction(resourceActionId);
	}

	/**
	* Deletes the resource action from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceAction the resource action
	* @return the resource action that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceAction deleteResourceAction(
		com.liferay.portal.model.ResourceAction resourceAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteResourceAction(resourceAction);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.model.ResourceAction fetchResourceAction(
		long resourceActionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchResourceAction(resourceActionId);
	}

	/**
	* Returns the resource action with the primary key.
	*
	* @param resourceActionId the primary key of the resource action
	* @return the resource action
	* @throws PortalException if a resource action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceAction getResourceAction(
		long resourceActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getResourceAction(resourceActionId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the resource actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource actions
	* @param end the upper bound of the range of resource actions (not inclusive)
	* @return the range of resource actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ResourceAction> getResourceActions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getResourceActions(start, end);
	}

	/**
	* Returns the number of resource actions.
	*
	* @return the number of resource actions
	* @throws SystemException if a system exception occurred
	*/
	public static int getResourceActionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getResourceActionsCount();
	}

	/**
	* Updates the resource action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resourceAction the resource action
	* @return the resource action that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceAction updateResourceAction(
		com.liferay.portal.model.ResourceAction resourceAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateResourceAction(resourceAction);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static void checkResourceActions()
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().checkResourceActions();
	}

	public static void checkResourceActions(java.lang.String name,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().checkResourceActions(name, actionIds);
	}

	public static void checkResourceActions(java.lang.String name,
		java.util.List<java.lang.String> actionIds, boolean addDefaultActions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().checkResourceActions(name, actionIds, addDefaultActions);
	}

	public static com.liferay.portal.model.ResourceAction fetchResourceAction(
		java.lang.String name, java.lang.String actionId) {
		return getService().fetchResourceAction(name, actionId);
	}

	public static com.liferay.portal.model.ResourceAction getResourceAction(
		java.lang.String name, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getResourceAction(name, actionId);
	}

	public static java.util.List<com.liferay.portal.model.ResourceAction> getResourceActions(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getResourceActions(name);
	}

	public static ResourceActionLocalService getService() {
		if (_service == null) {
			_service = (ResourceActionLocalService)PortalBeanLocatorUtil.locate(ResourceActionLocalService.class.getName());

			ReferenceRegistry.registerReference(ResourceActionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ResourceActionLocalService service) {
	}

	private static ResourceActionLocalService _service;
}