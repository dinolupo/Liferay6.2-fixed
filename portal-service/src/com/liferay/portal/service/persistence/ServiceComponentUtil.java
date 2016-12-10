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

package com.liferay.portal.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.model.ServiceComponent;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the service component service. This utility wraps {@link ServiceComponentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceComponentPersistence
 * @see ServiceComponentPersistenceImpl
 * @generated
 */
@ProviderType
public class ServiceComponentUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(ServiceComponent serviceComponent) {
		getPersistence().clearCache(serviceComponent);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ServiceComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ServiceComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ServiceComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ServiceComponent update(ServiceComponent serviceComponent)
		throws SystemException {
		return getPersistence().update(serviceComponent);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ServiceComponent update(ServiceComponent serviceComponent,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(serviceComponent, serviceContext);
	}

	/**
	* Returns all the service components where buildNamespace = &#63;.
	*
	* @param buildNamespace the build namespace
	* @return the matching service components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ServiceComponent> findByBuildNamespace(
		java.lang.String buildNamespace)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByBuildNamespace(buildNamespace);
	}

	/**
	* Returns a range of all the service components where buildNamespace = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ServiceComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param buildNamespace the build namespace
	* @param start the lower bound of the range of service components
	* @param end the upper bound of the range of service components (not inclusive)
	* @return the range of matching service components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ServiceComponent> findByBuildNamespace(
		java.lang.String buildNamespace, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByBuildNamespace(buildNamespace, start, end);
	}

	/**
	* Returns an ordered range of all the service components where buildNamespace = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ServiceComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param buildNamespace the build namespace
	* @param start the lower bound of the range of service components
	* @param end the upper bound of the range of service components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ServiceComponent> findByBuildNamespace(
		java.lang.String buildNamespace, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBuildNamespace(buildNamespace, start, end,
			orderByComparator);
	}

	/**
	* Returns the first service component in the ordered set where buildNamespace = &#63;.
	*
	* @param buildNamespace the build namespace
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service component
	* @throws com.liferay.portal.NoSuchServiceComponentException if a matching service component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent findByBuildNamespace_First(
		java.lang.String buildNamespace,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchServiceComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBuildNamespace_First(buildNamespace, orderByComparator);
	}

	/**
	* Returns the first service component in the ordered set where buildNamespace = &#63;.
	*
	* @param buildNamespace the build namespace
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service component, or <code>null</code> if a matching service component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent fetchByBuildNamespace_First(
		java.lang.String buildNamespace,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByBuildNamespace_First(buildNamespace,
			orderByComparator);
	}

	/**
	* Returns the last service component in the ordered set where buildNamespace = &#63;.
	*
	* @param buildNamespace the build namespace
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service component
	* @throws com.liferay.portal.NoSuchServiceComponentException if a matching service component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent findByBuildNamespace_Last(
		java.lang.String buildNamespace,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchServiceComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBuildNamespace_Last(buildNamespace, orderByComparator);
	}

	/**
	* Returns the last service component in the ordered set where buildNamespace = &#63;.
	*
	* @param buildNamespace the build namespace
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service component, or <code>null</code> if a matching service component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent fetchByBuildNamespace_Last(
		java.lang.String buildNamespace,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByBuildNamespace_Last(buildNamespace, orderByComparator);
	}

	/**
	* Returns the service components before and after the current service component in the ordered set where buildNamespace = &#63;.
	*
	* @param serviceComponentId the primary key of the current service component
	* @param buildNamespace the build namespace
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service component
	* @throws com.liferay.portal.NoSuchServiceComponentException if a service component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent[] findByBuildNamespace_PrevAndNext(
		long serviceComponentId, java.lang.String buildNamespace,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchServiceComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBuildNamespace_PrevAndNext(serviceComponentId,
			buildNamespace, orderByComparator);
	}

	/**
	* Removes all the service components where buildNamespace = &#63; from the database.
	*
	* @param buildNamespace the build namespace
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByBuildNamespace(java.lang.String buildNamespace)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByBuildNamespace(buildNamespace);
	}

	/**
	* Returns the number of service components where buildNamespace = &#63;.
	*
	* @param buildNamespace the build namespace
	* @return the number of matching service components
	* @throws SystemException if a system exception occurred
	*/
	public static int countByBuildNamespace(java.lang.String buildNamespace)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByBuildNamespace(buildNamespace);
	}

	/**
	* Returns the service component where buildNamespace = &#63; and buildNumber = &#63; or throws a {@link com.liferay.portal.NoSuchServiceComponentException} if it could not be found.
	*
	* @param buildNamespace the build namespace
	* @param buildNumber the build number
	* @return the matching service component
	* @throws com.liferay.portal.NoSuchServiceComponentException if a matching service component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent findByBNS_BNU(
		java.lang.String buildNamespace, long buildNumber)
		throws com.liferay.portal.NoSuchServiceComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByBNS_BNU(buildNamespace, buildNumber);
	}

	/**
	* Returns the service component where buildNamespace = &#63; and buildNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param buildNamespace the build namespace
	* @param buildNumber the build number
	* @return the matching service component, or <code>null</code> if a matching service component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent fetchByBNS_BNU(
		java.lang.String buildNamespace, long buildNumber)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByBNS_BNU(buildNamespace, buildNumber);
	}

	/**
	* Returns the service component where buildNamespace = &#63; and buildNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param buildNamespace the build namespace
	* @param buildNumber the build number
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching service component, or <code>null</code> if a matching service component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent fetchByBNS_BNU(
		java.lang.String buildNamespace, long buildNumber,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByBNS_BNU(buildNamespace, buildNumber,
			retrieveFromCache);
	}

	/**
	* Removes the service component where buildNamespace = &#63; and buildNumber = &#63; from the database.
	*
	* @param buildNamespace the build namespace
	* @param buildNumber the build number
	* @return the service component that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent removeByBNS_BNU(
		java.lang.String buildNamespace, long buildNumber)
		throws com.liferay.portal.NoSuchServiceComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByBNS_BNU(buildNamespace, buildNumber);
	}

	/**
	* Returns the number of service components where buildNamespace = &#63; and buildNumber = &#63;.
	*
	* @param buildNamespace the build namespace
	* @param buildNumber the build number
	* @return the number of matching service components
	* @throws SystemException if a system exception occurred
	*/
	public static int countByBNS_BNU(java.lang.String buildNamespace,
		long buildNumber)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByBNS_BNU(buildNamespace, buildNumber);
	}

	/**
	* Caches the service component in the entity cache if it is enabled.
	*
	* @param serviceComponent the service component
	*/
	public static void cacheResult(
		com.liferay.portal.model.ServiceComponent serviceComponent) {
		getPersistence().cacheResult(serviceComponent);
	}

	/**
	* Caches the service components in the entity cache if it is enabled.
	*
	* @param serviceComponents the service components
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.ServiceComponent> serviceComponents) {
		getPersistence().cacheResult(serviceComponents);
	}

	/**
	* Creates a new service component with the primary key. Does not add the service component to the database.
	*
	* @param serviceComponentId the primary key for the new service component
	* @return the new service component
	*/
	public static com.liferay.portal.model.ServiceComponent create(
		long serviceComponentId) {
		return getPersistence().create(serviceComponentId);
	}

	/**
	* Removes the service component with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceComponentId the primary key of the service component
	* @return the service component that was removed
	* @throws com.liferay.portal.NoSuchServiceComponentException if a service component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent remove(
		long serviceComponentId)
		throws com.liferay.portal.NoSuchServiceComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(serviceComponentId);
	}

	public static com.liferay.portal.model.ServiceComponent updateImpl(
		com.liferay.portal.model.ServiceComponent serviceComponent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(serviceComponent);
	}

	/**
	* Returns the service component with the primary key or throws a {@link com.liferay.portal.NoSuchServiceComponentException} if it could not be found.
	*
	* @param serviceComponentId the primary key of the service component
	* @return the service component
	* @throws com.liferay.portal.NoSuchServiceComponentException if a service component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent findByPrimaryKey(
		long serviceComponentId)
		throws com.liferay.portal.NoSuchServiceComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(serviceComponentId);
	}

	/**
	* Returns the service component with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceComponentId the primary key of the service component
	* @return the service component, or <code>null</code> if a service component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ServiceComponent fetchByPrimaryKey(
		long serviceComponentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(serviceComponentId);
	}

	/**
	* Returns all the service components.
	*
	* @return the service components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ServiceComponent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the service components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ServiceComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service components
	* @param end the upper bound of the range of service components (not inclusive)
	* @return the range of service components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ServiceComponent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the service components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ServiceComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service components
	* @param end the upper bound of the range of service components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ServiceComponent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the service components from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of service components.
	*
	* @return the number of service components
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ServiceComponentPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ServiceComponentPersistence)PortalBeanLocatorUtil.locate(ServiceComponentPersistence.class.getName());

			ReferenceRegistry.registerReference(ServiceComponentUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ServiceComponentPersistence persistence) {
	}

	private static ServiceComponentPersistence _persistence;
}