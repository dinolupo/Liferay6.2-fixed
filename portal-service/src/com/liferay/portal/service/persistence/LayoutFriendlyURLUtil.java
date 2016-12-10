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
import com.liferay.portal.model.LayoutFriendlyURL;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the layout friendly u r l service. This utility wraps {@link LayoutFriendlyURLPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFriendlyURLPersistence
 * @see LayoutFriendlyURLPersistenceImpl
 * @generated
 */
@ProviderType
public class LayoutFriendlyURLUtil {
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
	public static void clearCache(LayoutFriendlyURL layoutFriendlyURL) {
		getPersistence().clearCache(layoutFriendlyURL);
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
	public static List<LayoutFriendlyURL> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LayoutFriendlyURL> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LayoutFriendlyURL> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static LayoutFriendlyURL update(LayoutFriendlyURL layoutFriendlyURL)
		throws SystemException {
		return getPersistence().update(layoutFriendlyURL);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static LayoutFriendlyURL update(
		LayoutFriendlyURL layoutFriendlyURL, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(layoutFriendlyURL, serviceContext);
	}

	/**
	* Returns all the layout friendly u r ls where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the layout friendly u r ls where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @return the range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the layout friendly u r ls where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the layout friendly u r ls before and after the current layout friendly u r l in the ordered set where uuid = &#63;.
	*
	* @param layoutFriendlyURLId the primary key of the current layout friendly u r l
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a layout friendly u r l with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL[] findByUuid_PrevAndNext(
		long layoutFriendlyURLId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(layoutFriendlyURLId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the layout friendly u r ls where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of layout friendly u r ls where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the layout friendly u r l where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.portal.NoSuchLayoutFriendlyURLException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the layout friendly u r l where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the layout friendly u r l where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the layout friendly u r l where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the layout friendly u r l that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of layout friendly u r ls where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the layout friendly u r ls where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the layout friendly u r ls where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @return the range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the layout friendly u r ls where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the layout friendly u r ls before and after the current layout friendly u r l in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param layoutFriendlyURLId the primary key of the current layout friendly u r l
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a layout friendly u r l with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL[] findByUuid_C_PrevAndNext(
		long layoutFriendlyURLId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(layoutFriendlyURLId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the layout friendly u r ls where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of layout friendly u r ls where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the layout friendly u r ls where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the layout friendly u r ls where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @return the range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the layout friendly u r ls where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the layout friendly u r ls before and after the current layout friendly u r l in the ordered set where groupId = &#63;.
	*
	* @param layoutFriendlyURLId the primary key of the current layout friendly u r l
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a layout friendly u r l with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL[] findByGroupId_PrevAndNext(
		long layoutFriendlyURLId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(layoutFriendlyURLId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the layout friendly u r ls where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of layout friendly u r ls where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the layout friendly u r ls where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the layout friendly u r ls where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @return the range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the layout friendly u r ls where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the layout friendly u r ls before and after the current layout friendly u r l in the ordered set where companyId = &#63;.
	*
	* @param layoutFriendlyURLId the primary key of the current layout friendly u r l
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a layout friendly u r l with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL[] findByCompanyId_PrevAndNext(
		long layoutFriendlyURLId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(layoutFriendlyURLId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the layout friendly u r ls where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of layout friendly u r ls where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the layout friendly u r ls where plid = &#63;.
	*
	* @param plid the plid
	* @return the matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByPlid(
		long plid) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPlid(plid);
	}

	/**
	* Returns a range of all the layout friendly u r ls where plid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param plid the plid
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @return the range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByPlid(
		long plid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPlid(plid, start, end);
	}

	/**
	* Returns an ordered range of all the layout friendly u r ls where plid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param plid the plid
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByPlid(
		long plid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPlid(plid, start, end, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where plid = &#63;.
	*
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByPlid_First(
		long plid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPlid_First(plid, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where plid = &#63;.
	*
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByPlid_First(
		long plid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPlid_First(plid, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where plid = &#63;.
	*
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByPlid_Last(
		long plid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPlid_Last(plid, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where plid = &#63;.
	*
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByPlid_Last(
		long plid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPlid_Last(plid, orderByComparator);
	}

	/**
	* Returns the layout friendly u r ls before and after the current layout friendly u r l in the ordered set where plid = &#63;.
	*
	* @param layoutFriendlyURLId the primary key of the current layout friendly u r l
	* @param plid the plid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a layout friendly u r l with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL[] findByPlid_PrevAndNext(
		long layoutFriendlyURLId, long plid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPlid_PrevAndNext(layoutFriendlyURLId, plid,
			orderByComparator);
	}

	/**
	* Removes all the layout friendly u r ls where plid = &#63; from the database.
	*
	* @param plid the plid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPlid(long plid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPlid(plid);
	}

	/**
	* Returns the number of layout friendly u r ls where plid = &#63;.
	*
	* @param plid the plid
	* @return the number of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPlid(long plid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPlid(plid);
	}

	/**
	* Returns all the layout friendly u r ls where plid = &#63; and friendlyURL = &#63;.
	*
	* @param plid the plid
	* @param friendlyURL the friendly u r l
	* @return the matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByP_F(
		long plid, java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_F(plid, friendlyURL);
	}

	/**
	* Returns a range of all the layout friendly u r ls where plid = &#63; and friendlyURL = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param plid the plid
	* @param friendlyURL the friendly u r l
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @return the range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByP_F(
		long plid, java.lang.String friendlyURL, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_F(plid, friendlyURL, start, end);
	}

	/**
	* Returns an ordered range of all the layout friendly u r ls where plid = &#63; and friendlyURL = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param plid the plid
	* @param friendlyURL the friendly u r l
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByP_F(
		long plid, java.lang.String friendlyURL, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_F(plid, friendlyURL, start, end, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where plid = &#63; and friendlyURL = &#63;.
	*
	* @param plid the plid
	* @param friendlyURL the friendly u r l
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByP_F_First(
		long plid, java.lang.String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_F_First(plid, friendlyURL, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where plid = &#63; and friendlyURL = &#63;.
	*
	* @param plid the plid
	* @param friendlyURL the friendly u r l
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByP_F_First(
		long plid, java.lang.String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByP_F_First(plid, friendlyURL, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where plid = &#63; and friendlyURL = &#63;.
	*
	* @param plid the plid
	* @param friendlyURL the friendly u r l
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByP_F_Last(
		long plid, java.lang.String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_F_Last(plid, friendlyURL, orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where plid = &#63; and friendlyURL = &#63;.
	*
	* @param plid the plid
	* @param friendlyURL the friendly u r l
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByP_F_Last(
		long plid, java.lang.String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByP_F_Last(plid, friendlyURL, orderByComparator);
	}

	/**
	* Returns the layout friendly u r ls before and after the current layout friendly u r l in the ordered set where plid = &#63; and friendlyURL = &#63;.
	*
	* @param layoutFriendlyURLId the primary key of the current layout friendly u r l
	* @param plid the plid
	* @param friendlyURL the friendly u r l
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a layout friendly u r l with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL[] findByP_F_PrevAndNext(
		long layoutFriendlyURLId, long plid, java.lang.String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_F_PrevAndNext(layoutFriendlyURLId, plid,
			friendlyURL, orderByComparator);
	}

	/**
	* Removes all the layout friendly u r ls where plid = &#63; and friendlyURL = &#63; from the database.
	*
	* @param plid the plid
	* @param friendlyURL the friendly u r l
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_F(long plid, java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_F(plid, friendlyURL);
	}

	/**
	* Returns the number of layout friendly u r ls where plid = &#63; and friendlyURL = &#63;.
	*
	* @param plid the plid
	* @param friendlyURL the friendly u r l
	* @return the number of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_F(long plid, java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_F(plid, friendlyURL);
	}

	/**
	* Returns the layout friendly u r l where plid = &#63; and languageId = &#63; or throws a {@link com.liferay.portal.NoSuchLayoutFriendlyURLException} if it could not be found.
	*
	* @param plid the plid
	* @param languageId the language ID
	* @return the matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByP_L(
		long plid, java.lang.String languageId)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_L(plid, languageId);
	}

	/**
	* Returns the layout friendly u r l where plid = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param plid the plid
	* @param languageId the language ID
	* @return the matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByP_L(
		long plid, java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByP_L(plid, languageId);
	}

	/**
	* Returns the layout friendly u r l where plid = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param plid the plid
	* @param languageId the language ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByP_L(
		long plid, java.lang.String languageId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByP_L(plid, languageId, retrieveFromCache);
	}

	/**
	* Removes the layout friendly u r l where plid = &#63; and languageId = &#63; from the database.
	*
	* @param plid the plid
	* @param languageId the language ID
	* @return the layout friendly u r l that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL removeByP_L(
		long plid, java.lang.String languageId)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByP_L(plid, languageId);
	}

	/**
	* Returns the number of layout friendly u r ls where plid = &#63; and languageId = &#63;.
	*
	* @param plid the plid
	* @param languageId the language ID
	* @return the number of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_L(long plid, java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_L(plid, languageId);
	}

	/**
	* Returns all the layout friendly u r ls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @return the matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByG_P_F(
		long groupId, boolean privateLayout, java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_P_F(groupId, privateLayout, friendlyURL);
	}

	/**
	* Returns a range of all the layout friendly u r ls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @return the range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByG_P_F(
		long groupId, boolean privateLayout, java.lang.String friendlyURL,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_F(groupId, privateLayout, friendlyURL, start, end);
	}

	/**
	* Returns an ordered range of all the layout friendly u r ls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findByG_P_F(
		long groupId, boolean privateLayout, java.lang.String friendlyURL,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_F(groupId, privateLayout, friendlyURL, start,
			end, orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByG_P_F_First(
		long groupId, boolean privateLayout, java.lang.String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_F_First(groupId, privateLayout, friendlyURL,
			orderByComparator);
	}

	/**
	* Returns the first layout friendly u r l in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByG_P_F_First(
		long groupId, boolean privateLayout, java.lang.String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_F_First(groupId, privateLayout, friendlyURL,
			orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByG_P_F_Last(
		long groupId, boolean privateLayout, java.lang.String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_F_Last(groupId, privateLayout, friendlyURL,
			orderByComparator);
	}

	/**
	* Returns the last layout friendly u r l in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByG_P_F_Last(
		long groupId, boolean privateLayout, java.lang.String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_F_Last(groupId, privateLayout, friendlyURL,
			orderByComparator);
	}

	/**
	* Returns the layout friendly u r ls before and after the current layout friendly u r l in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	*
	* @param layoutFriendlyURLId the primary key of the current layout friendly u r l
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a layout friendly u r l with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL[] findByG_P_F_PrevAndNext(
		long layoutFriendlyURLId, long groupId, boolean privateLayout,
		java.lang.String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_F_PrevAndNext(layoutFriendlyURLId, groupId,
			privateLayout, friendlyURL, orderByComparator);
	}

	/**
	* Removes all the layout friendly u r ls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; from the database.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_F(long groupId, boolean privateLayout,
		java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P_F(groupId, privateLayout, friendlyURL);
	}

	/**
	* Returns the number of layout friendly u r ls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @return the number of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_F(long groupId, boolean privateLayout,
		java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_P_F(groupId, privateLayout, friendlyURL);
	}

	/**
	* Returns the layout friendly u r l where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; or throws a {@link com.liferay.portal.NoSuchLayoutFriendlyURLException} if it could not be found.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param languageId the language ID
	* @return the matching layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByG_P_F_L(
		long groupId, boolean privateLayout, java.lang.String friendlyURL,
		java.lang.String languageId)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_F_L(groupId, privateLayout, friendlyURL,
			languageId);
	}

	/**
	* Returns the layout friendly u r l where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param languageId the language ID
	* @return the matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByG_P_F_L(
		long groupId, boolean privateLayout, java.lang.String friendlyURL,
		java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_F_L(groupId, privateLayout, friendlyURL,
			languageId);
	}

	/**
	* Returns the layout friendly u r l where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param languageId the language ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByG_P_F_L(
		long groupId, boolean privateLayout, java.lang.String friendlyURL,
		java.lang.String languageId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_F_L(groupId, privateLayout, friendlyURL,
			languageId, retrieveFromCache);
	}

	/**
	* Removes the layout friendly u r l where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param languageId the language ID
	* @return the layout friendly u r l that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL removeByG_P_F_L(
		long groupId, boolean privateLayout, java.lang.String friendlyURL,
		java.lang.String languageId)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByG_P_F_L(groupId, privateLayout, friendlyURL,
			languageId);
	}

	/**
	* Returns the number of layout friendly u r ls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param friendlyURL the friendly u r l
	* @param languageId the language ID
	* @return the number of matching layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_F_L(long groupId, boolean privateLayout,
		java.lang.String friendlyURL, java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_F_L(groupId, privateLayout, friendlyURL,
			languageId);
	}

	/**
	* Caches the layout friendly u r l in the entity cache if it is enabled.
	*
	* @param layoutFriendlyURL the layout friendly u r l
	*/
	public static void cacheResult(
		com.liferay.portal.model.LayoutFriendlyURL layoutFriendlyURL) {
		getPersistence().cacheResult(layoutFriendlyURL);
	}

	/**
	* Caches the layout friendly u r ls in the entity cache if it is enabled.
	*
	* @param layoutFriendlyURLs the layout friendly u r ls
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.LayoutFriendlyURL> layoutFriendlyURLs) {
		getPersistence().cacheResult(layoutFriendlyURLs);
	}

	/**
	* Creates a new layout friendly u r l with the primary key. Does not add the layout friendly u r l to the database.
	*
	* @param layoutFriendlyURLId the primary key for the new layout friendly u r l
	* @return the new layout friendly u r l
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL create(
		long layoutFriendlyURLId) {
		return getPersistence().create(layoutFriendlyURLId);
	}

	/**
	* Removes the layout friendly u r l with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutFriendlyURLId the primary key of the layout friendly u r l
	* @return the layout friendly u r l that was removed
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a layout friendly u r l with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL remove(
		long layoutFriendlyURLId)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(layoutFriendlyURLId);
	}

	public static com.liferay.portal.model.LayoutFriendlyURL updateImpl(
		com.liferay.portal.model.LayoutFriendlyURL layoutFriendlyURL)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(layoutFriendlyURL);
	}

	/**
	* Returns the layout friendly u r l with the primary key or throws a {@link com.liferay.portal.NoSuchLayoutFriendlyURLException} if it could not be found.
	*
	* @param layoutFriendlyURLId the primary key of the layout friendly u r l
	* @return the layout friendly u r l
	* @throws com.liferay.portal.NoSuchLayoutFriendlyURLException if a layout friendly u r l with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL findByPrimaryKey(
		long layoutFriendlyURLId)
		throws com.liferay.portal.NoSuchLayoutFriendlyURLException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(layoutFriendlyURLId);
	}

	/**
	* Returns the layout friendly u r l with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param layoutFriendlyURLId the primary key of the layout friendly u r l
	* @return the layout friendly u r l, or <code>null</code> if a layout friendly u r l with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutFriendlyURL fetchByPrimaryKey(
		long layoutFriendlyURLId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(layoutFriendlyURLId);
	}

	/**
	* Returns all the layout friendly u r ls.
	*
	* @return the layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the layout friendly u r ls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @return the range of layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the layout friendly u r ls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutFriendlyURL> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the layout friendly u r ls from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of layout friendly u r ls.
	*
	* @return the number of layout friendly u r ls
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static LayoutFriendlyURLPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (LayoutFriendlyURLPersistence)PortalBeanLocatorUtil.locate(LayoutFriendlyURLPersistence.class.getName());

			ReferenceRegistry.registerReference(LayoutFriendlyURLUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(LayoutFriendlyURLPersistence persistence) {
	}

	private static LayoutFriendlyURLPersistence _persistence;
}