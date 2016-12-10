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
import com.liferay.portal.model.PortalPreferences;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the portal preferences service. This utility wraps {@link PortalPreferencesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortalPreferencesPersistence
 * @see PortalPreferencesPersistenceImpl
 * @generated
 */
@ProviderType
public class PortalPreferencesUtil {
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
	public static void clearCache(PortalPreferences portalPreferences) {
		getPersistence().clearCache(portalPreferences);
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
	public static List<PortalPreferences> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PortalPreferences> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PortalPreferences> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static PortalPreferences update(PortalPreferences portalPreferences)
		throws SystemException {
		return getPersistence().update(portalPreferences);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static PortalPreferences update(
		PortalPreferences portalPreferences, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(portalPreferences, serviceContext);
	}

	/**
	* Returns the portal preferences where ownerId = &#63; and ownerType = &#63; or throws a {@link com.liferay.portal.NoSuchPreferencesException} if it could not be found.
	*
	* @param ownerId the owner ID
	* @param ownerType the owner type
	* @return the matching portal preferences
	* @throws com.liferay.portal.NoSuchPreferencesException if a matching portal preferences could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PortalPreferences findByO_O(
		long ownerId, int ownerType)
		throws com.liferay.portal.NoSuchPreferencesException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByO_O(ownerId, ownerType);
	}

	/**
	* Returns the portal preferences where ownerId = &#63; and ownerType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param ownerId the owner ID
	* @param ownerType the owner type
	* @return the matching portal preferences, or <code>null</code> if a matching portal preferences could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PortalPreferences fetchByO_O(
		long ownerId, int ownerType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByO_O(ownerId, ownerType);
	}

	/**
	* Returns the portal preferences where ownerId = &#63; and ownerType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param ownerId the owner ID
	* @param ownerType the owner type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching portal preferences, or <code>null</code> if a matching portal preferences could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PortalPreferences fetchByO_O(
		long ownerId, int ownerType, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByO_O(ownerId, ownerType, retrieveFromCache);
	}

	/**
	* Removes the portal preferences where ownerId = &#63; and ownerType = &#63; from the database.
	*
	* @param ownerId the owner ID
	* @param ownerType the owner type
	* @return the portal preferences that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PortalPreferences removeByO_O(
		long ownerId, int ownerType)
		throws com.liferay.portal.NoSuchPreferencesException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByO_O(ownerId, ownerType);
	}

	/**
	* Returns the number of portal preferenceses where ownerId = &#63; and ownerType = &#63;.
	*
	* @param ownerId the owner ID
	* @param ownerType the owner type
	* @return the number of matching portal preferenceses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByO_O(long ownerId, int ownerType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByO_O(ownerId, ownerType);
	}

	/**
	* Caches the portal preferences in the entity cache if it is enabled.
	*
	* @param portalPreferences the portal preferences
	*/
	public static void cacheResult(
		com.liferay.portal.model.PortalPreferences portalPreferences) {
		getPersistence().cacheResult(portalPreferences);
	}

	/**
	* Caches the portal preferenceses in the entity cache if it is enabled.
	*
	* @param portalPreferenceses the portal preferenceses
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.PortalPreferences> portalPreferenceses) {
		getPersistence().cacheResult(portalPreferenceses);
	}

	/**
	* Creates a new portal preferences with the primary key. Does not add the portal preferences to the database.
	*
	* @param portalPreferencesId the primary key for the new portal preferences
	* @return the new portal preferences
	*/
	public static com.liferay.portal.model.PortalPreferences create(
		long portalPreferencesId) {
		return getPersistence().create(portalPreferencesId);
	}

	/**
	* Removes the portal preferences with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param portalPreferencesId the primary key of the portal preferences
	* @return the portal preferences that was removed
	* @throws com.liferay.portal.NoSuchPreferencesException if a portal preferences with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PortalPreferences remove(
		long portalPreferencesId)
		throws com.liferay.portal.NoSuchPreferencesException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(portalPreferencesId);
	}

	public static com.liferay.portal.model.PortalPreferences updateImpl(
		com.liferay.portal.model.PortalPreferences portalPreferences)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(portalPreferences);
	}

	/**
	* Returns the portal preferences with the primary key or throws a {@link com.liferay.portal.NoSuchPreferencesException} if it could not be found.
	*
	* @param portalPreferencesId the primary key of the portal preferences
	* @return the portal preferences
	* @throws com.liferay.portal.NoSuchPreferencesException if a portal preferences with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PortalPreferences findByPrimaryKey(
		long portalPreferencesId)
		throws com.liferay.portal.NoSuchPreferencesException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(portalPreferencesId);
	}

	/**
	* Returns the portal preferences with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param portalPreferencesId the primary key of the portal preferences
	* @return the portal preferences, or <code>null</code> if a portal preferences with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PortalPreferences fetchByPrimaryKey(
		long portalPreferencesId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(portalPreferencesId);
	}

	/**
	* Returns all the portal preferenceses.
	*
	* @return the portal preferenceses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.PortalPreferences> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the portal preferenceses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PortalPreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of portal preferenceses
	* @param end the upper bound of the range of portal preferenceses (not inclusive)
	* @return the range of portal preferenceses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.PortalPreferences> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the portal preferenceses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PortalPreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of portal preferenceses
	* @param end the upper bound of the range of portal preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of portal preferenceses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.PortalPreferences> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the portal preferenceses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of portal preferenceses.
	*
	* @return the number of portal preferenceses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static PortalPreferencesPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PortalPreferencesPersistence)PortalBeanLocatorUtil.locate(PortalPreferencesPersistence.class.getName());

			ReferenceRegistry.registerReference(PortalPreferencesUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(PortalPreferencesPersistence persistence) {
	}

	private static PortalPreferencesPersistence _persistence;
}