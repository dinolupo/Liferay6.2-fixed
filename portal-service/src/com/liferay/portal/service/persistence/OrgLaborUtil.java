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
import com.liferay.portal.model.OrgLabor;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the org labor service. This utility wraps {@link OrgLaborPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrgLaborPersistence
 * @see OrgLaborPersistenceImpl
 * @generated
 */
@ProviderType
public class OrgLaborUtil {
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
	public static void clearCache(OrgLabor orgLabor) {
		getPersistence().clearCache(orgLabor);
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
	public static List<OrgLabor> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OrgLabor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OrgLabor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static OrgLabor update(OrgLabor orgLabor) throws SystemException {
		return getPersistence().update(orgLabor);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static OrgLabor update(OrgLabor orgLabor,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(orgLabor, serviceContext);
	}

	/**
	* Returns all the org labors where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the matching org labors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgLabor> findByOrganizationId(
		long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOrganizationId(organizationId);
	}

	/**
	* Returns a range of all the org labors where organizationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrgLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param organizationId the organization ID
	* @param start the lower bound of the range of org labors
	* @param end the upper bound of the range of org labors (not inclusive)
	* @return the range of matching org labors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgLabor> findByOrganizationId(
		long organizationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOrganizationId(organizationId, start, end);
	}

	/**
	* Returns an ordered range of all the org labors where organizationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrgLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param organizationId the organization ID
	* @param start the lower bound of the range of org labors
	* @param end the upper bound of the range of org labors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching org labors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgLabor> findByOrganizationId(
		long organizationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrganizationId(organizationId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first org labor in the ordered set where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching org labor
	* @throws com.liferay.portal.NoSuchOrgLaborException if a matching org labor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgLabor findByOrganizationId_First(
		long organizationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchOrgLaborException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrganizationId_First(organizationId, orderByComparator);
	}

	/**
	* Returns the first org labor in the ordered set where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching org labor, or <code>null</code> if a matching org labor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgLabor fetchByOrganizationId_First(
		long organizationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOrganizationId_First(organizationId,
			orderByComparator);
	}

	/**
	* Returns the last org labor in the ordered set where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching org labor
	* @throws com.liferay.portal.NoSuchOrgLaborException if a matching org labor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgLabor findByOrganizationId_Last(
		long organizationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchOrgLaborException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrganizationId_Last(organizationId, orderByComparator);
	}

	/**
	* Returns the last org labor in the ordered set where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching org labor, or <code>null</code> if a matching org labor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgLabor fetchByOrganizationId_Last(
		long organizationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOrganizationId_Last(organizationId, orderByComparator);
	}

	/**
	* Returns the org labors before and after the current org labor in the ordered set where organizationId = &#63;.
	*
	* @param orgLaborId the primary key of the current org labor
	* @param organizationId the organization ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next org labor
	* @throws com.liferay.portal.NoSuchOrgLaborException if a org labor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgLabor[] findByOrganizationId_PrevAndNext(
		long orgLaborId, long organizationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchOrgLaborException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrganizationId_PrevAndNext(orgLaborId,
			organizationId, orderByComparator);
	}

	/**
	* Removes all the org labors where organizationId = &#63; from the database.
	*
	* @param organizationId the organization ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOrganizationId(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOrganizationId(organizationId);
	}

	/**
	* Returns the number of org labors where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the number of matching org labors
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOrganizationId(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOrganizationId(organizationId);
	}

	/**
	* Caches the org labor in the entity cache if it is enabled.
	*
	* @param orgLabor the org labor
	*/
	public static void cacheResult(com.liferay.portal.model.OrgLabor orgLabor) {
		getPersistence().cacheResult(orgLabor);
	}

	/**
	* Caches the org labors in the entity cache if it is enabled.
	*
	* @param orgLabors the org labors
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.OrgLabor> orgLabors) {
		getPersistence().cacheResult(orgLabors);
	}

	/**
	* Creates a new org labor with the primary key. Does not add the org labor to the database.
	*
	* @param orgLaborId the primary key for the new org labor
	* @return the new org labor
	*/
	public static com.liferay.portal.model.OrgLabor create(long orgLaborId) {
		return getPersistence().create(orgLaborId);
	}

	/**
	* Removes the org labor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orgLaborId the primary key of the org labor
	* @return the org labor that was removed
	* @throws com.liferay.portal.NoSuchOrgLaborException if a org labor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgLabor remove(long orgLaborId)
		throws com.liferay.portal.NoSuchOrgLaborException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(orgLaborId);
	}

	public static com.liferay.portal.model.OrgLabor updateImpl(
		com.liferay.portal.model.OrgLabor orgLabor)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(orgLabor);
	}

	/**
	* Returns the org labor with the primary key or throws a {@link com.liferay.portal.NoSuchOrgLaborException} if it could not be found.
	*
	* @param orgLaborId the primary key of the org labor
	* @return the org labor
	* @throws com.liferay.portal.NoSuchOrgLaborException if a org labor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgLabor findByPrimaryKey(
		long orgLaborId)
		throws com.liferay.portal.NoSuchOrgLaborException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(orgLaborId);
	}

	/**
	* Returns the org labor with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param orgLaborId the primary key of the org labor
	* @return the org labor, or <code>null</code> if a org labor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgLabor fetchByPrimaryKey(
		long orgLaborId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(orgLaborId);
	}

	/**
	* Returns all the org labors.
	*
	* @return the org labors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgLabor> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the org labors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrgLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of org labors
	* @param end the upper bound of the range of org labors (not inclusive)
	* @return the range of org labors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgLabor> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the org labors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrgLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of org labors
	* @param end the upper bound of the range of org labors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of org labors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgLabor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the org labors from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of org labors.
	*
	* @return the number of org labors
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static OrgLaborPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (OrgLaborPersistence)PortalBeanLocatorUtil.locate(OrgLaborPersistence.class.getName());

			ReferenceRegistry.registerReference(OrgLaborUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(OrgLaborPersistence persistence) {
	}

	private static OrgLaborPersistence _persistence;
}