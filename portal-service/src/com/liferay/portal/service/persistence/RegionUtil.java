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
import com.liferay.portal.model.Region;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the region service. This utility wraps {@link RegionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegionPersistence
 * @see RegionPersistenceImpl
 * @generated
 */
@ProviderType
public class RegionUtil {
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
	public static void clearCache(Region region) {
		getPersistence().clearCache(region);
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
	public static List<Region> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Region> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Region> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Region update(Region region) throws SystemException {
		return getPersistence().update(region);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Region update(Region region, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(region, serviceContext);
	}

	/**
	* Returns all the regions where countryId = &#63;.
	*
	* @param countryId the country ID
	* @return the matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findByCountryId(
		long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCountryId(countryId);
	}

	/**
	* Returns a range of all the regions where countryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryId the country ID
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @return the range of matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findByCountryId(
		long countryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCountryId(countryId, start, end);
	}

	/**
	* Returns an ordered range of all the regions where countryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryId the country ID
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findByCountryId(
		long countryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCountryId(countryId, start, end, orderByComparator);
	}

	/**
	* Returns the first region in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region
	* @throws com.liferay.portal.NoSuchRegionException if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region findByCountryId_First(
		long countryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCountryId_First(countryId, orderByComparator);
	}

	/**
	* Returns the first region in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region, or <code>null</code> if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region fetchByCountryId_First(
		long countryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCountryId_First(countryId, orderByComparator);
	}

	/**
	* Returns the last region in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region
	* @throws com.liferay.portal.NoSuchRegionException if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region findByCountryId_Last(
		long countryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCountryId_Last(countryId, orderByComparator);
	}

	/**
	* Returns the last region in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region, or <code>null</code> if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region fetchByCountryId_Last(
		long countryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCountryId_Last(countryId, orderByComparator);
	}

	/**
	* Returns the regions before and after the current region in the ordered set where countryId = &#63;.
	*
	* @param regionId the primary key of the current region
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next region
	* @throws com.liferay.portal.NoSuchRegionException if a region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region[] findByCountryId_PrevAndNext(
		long regionId, long countryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCountryId_PrevAndNext(regionId, countryId,
			orderByComparator);
	}

	/**
	* Removes all the regions where countryId = &#63; from the database.
	*
	* @param countryId the country ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCountryId(long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCountryId(countryId);
	}

	/**
	* Returns the number of regions where countryId = &#63;.
	*
	* @param countryId the country ID
	* @return the number of matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCountryId(long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCountryId(countryId);
	}

	/**
	* Returns all the regions where active = &#63;.
	*
	* @param active the active
	* @return the matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findByActive(
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByActive(active);
	}

	/**
	* Returns a range of all the regions where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @return the range of matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findByActive(
		boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByActive(active, start, end);
	}

	/**
	* Returns an ordered range of all the regions where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findByActive(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByActive(active, start, end, orderByComparator);
	}

	/**
	* Returns the first region in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region
	* @throws com.liferay.portal.NoSuchRegionException if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region findByActive_First(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByActive_First(active, orderByComparator);
	}

	/**
	* Returns the first region in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region, or <code>null</code> if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region fetchByActive_First(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByActive_First(active, orderByComparator);
	}

	/**
	* Returns the last region in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region
	* @throws com.liferay.portal.NoSuchRegionException if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region findByActive_Last(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByActive_Last(active, orderByComparator);
	}

	/**
	* Returns the last region in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region, or <code>null</code> if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region fetchByActive_Last(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByActive_Last(active, orderByComparator);
	}

	/**
	* Returns the regions before and after the current region in the ordered set where active = &#63;.
	*
	* @param regionId the primary key of the current region
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next region
	* @throws com.liferay.portal.NoSuchRegionException if a region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region[] findByActive_PrevAndNext(
		long regionId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByActive_PrevAndNext(regionId, active, orderByComparator);
	}

	/**
	* Removes all the regions where active = &#63; from the database.
	*
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByActive(boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByActive(active);
	}

	/**
	* Returns the number of regions where active = &#63;.
	*
	* @param active the active
	* @return the number of matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByActive(boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByActive(active);
	}

	/**
	* Returns the region where countryId = &#63; and regionCode = &#63; or throws a {@link com.liferay.portal.NoSuchRegionException} if it could not be found.
	*
	* @param countryId the country ID
	* @param regionCode the region code
	* @return the matching region
	* @throws com.liferay.portal.NoSuchRegionException if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region findByC_R(long countryId,
		java.lang.String regionCode)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_R(countryId, regionCode);
	}

	/**
	* Returns the region where countryId = &#63; and regionCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param countryId the country ID
	* @param regionCode the region code
	* @return the matching region, or <code>null</code> if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region fetchByC_R(long countryId,
		java.lang.String regionCode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_R(countryId, regionCode);
	}

	/**
	* Returns the region where countryId = &#63; and regionCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param countryId the country ID
	* @param regionCode the region code
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching region, or <code>null</code> if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region fetchByC_R(long countryId,
		java.lang.String regionCode, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_R(countryId, regionCode, retrieveFromCache);
	}

	/**
	* Removes the region where countryId = &#63; and regionCode = &#63; from the database.
	*
	* @param countryId the country ID
	* @param regionCode the region code
	* @return the region that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region removeByC_R(long countryId,
		java.lang.String regionCode)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_R(countryId, regionCode);
	}

	/**
	* Returns the number of regions where countryId = &#63; and regionCode = &#63;.
	*
	* @param countryId the country ID
	* @param regionCode the region code
	* @return the number of matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_R(long countryId, java.lang.String regionCode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_R(countryId, regionCode);
	}

	/**
	* Returns all the regions where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @return the matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findByC_A(
		long countryId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_A(countryId, active);
	}

	/**
	* Returns a range of all the regions where countryId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryId the country ID
	* @param active the active
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @return the range of matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findByC_A(
		long countryId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_A(countryId, active, start, end);
	}

	/**
	* Returns an ordered range of all the regions where countryId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryId the country ID
	* @param active the active
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findByC_A(
		long countryId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_A(countryId, active, start, end, orderByComparator);
	}

	/**
	* Returns the first region in the ordered set where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region
	* @throws com.liferay.portal.NoSuchRegionException if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region findByC_A_First(
		long countryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_A_First(countryId, active, orderByComparator);
	}

	/**
	* Returns the first region in the ordered set where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region, or <code>null</code> if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region fetchByC_A_First(
		long countryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_A_First(countryId, active, orderByComparator);
	}

	/**
	* Returns the last region in the ordered set where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region
	* @throws com.liferay.portal.NoSuchRegionException if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region findByC_A_Last(
		long countryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_A_Last(countryId, active, orderByComparator);
	}

	/**
	* Returns the last region in the ordered set where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region, or <code>null</code> if a matching region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region fetchByC_A_Last(
		long countryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_A_Last(countryId, active, orderByComparator);
	}

	/**
	* Returns the regions before and after the current region in the ordered set where countryId = &#63; and active = &#63;.
	*
	* @param regionId the primary key of the current region
	* @param countryId the country ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next region
	* @throws com.liferay.portal.NoSuchRegionException if a region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region[] findByC_A_PrevAndNext(
		long regionId, long countryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_A_PrevAndNext(regionId, countryId, active,
			orderByComparator);
	}

	/**
	* Removes all the regions where countryId = &#63; and active = &#63; from the database.
	*
	* @param countryId the country ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_A(long countryId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_A(countryId, active);
	}

	/**
	* Returns the number of regions where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @return the number of matching regions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_A(long countryId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_A(countryId, active);
	}

	/**
	* Caches the region in the entity cache if it is enabled.
	*
	* @param region the region
	*/
	public static void cacheResult(com.liferay.portal.model.Region region) {
		getPersistence().cacheResult(region);
	}

	/**
	* Caches the regions in the entity cache if it is enabled.
	*
	* @param regions the regions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.Region> regions) {
		getPersistence().cacheResult(regions);
	}

	/**
	* Creates a new region with the primary key. Does not add the region to the database.
	*
	* @param regionId the primary key for the new region
	* @return the new region
	*/
	public static com.liferay.portal.model.Region create(long regionId) {
		return getPersistence().create(regionId);
	}

	/**
	* Removes the region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param regionId the primary key of the region
	* @return the region that was removed
	* @throws com.liferay.portal.NoSuchRegionException if a region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region remove(long regionId)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(regionId);
	}

	public static com.liferay.portal.model.Region updateImpl(
		com.liferay.portal.model.Region region)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(region);
	}

	/**
	* Returns the region with the primary key or throws a {@link com.liferay.portal.NoSuchRegionException} if it could not be found.
	*
	* @param regionId the primary key of the region
	* @return the region
	* @throws com.liferay.portal.NoSuchRegionException if a region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region findByPrimaryKey(
		long regionId)
		throws com.liferay.portal.NoSuchRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(regionId);
	}

	/**
	* Returns the region with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param regionId the primary key of the region
	* @return the region, or <code>null</code> if a region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Region fetchByPrimaryKey(
		long regionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(regionId);
	}

	/**
	* Returns all the regions.
	*
	* @return the regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @return the range of regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Region> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the regions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of regions.
	*
	* @return the number of regions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static RegionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (RegionPersistence)PortalBeanLocatorUtil.locate(RegionPersistence.class.getName());

			ReferenceRegistry.registerReference(RegionUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(RegionPersistence persistence) {
	}

	private static RegionPersistence _persistence;
}