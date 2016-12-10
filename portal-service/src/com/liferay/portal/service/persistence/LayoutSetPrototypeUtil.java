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
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the layout set prototype service. This utility wraps {@link LayoutSetPrototypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetPrototypePersistence
 * @see LayoutSetPrototypePersistenceImpl
 * @generated
 */
@ProviderType
public class LayoutSetPrototypeUtil {
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
	public static void clearCache(LayoutSetPrototype layoutSetPrototype) {
		getPersistence().clearCache(layoutSetPrototype);
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
	public static List<LayoutSetPrototype> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LayoutSetPrototype> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LayoutSetPrototype> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static LayoutSetPrototype update(
		LayoutSetPrototype layoutSetPrototype) throws SystemException {
		return getPersistence().update(layoutSetPrototype);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static LayoutSetPrototype update(
		LayoutSetPrototype layoutSetPrototype, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(layoutSetPrototype, serviceContext);
	}

	/**
	* Returns all the layout set prototypes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the layout set prototypes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @return the range of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the layout set prototypes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first layout set prototype in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first layout set prototype in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set prototype, or <code>null</code> if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last layout set prototype in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last layout set prototype in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set prototype, or <code>null</code> if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the layout set prototypes before and after the current layout set prototype in the ordered set where uuid = &#63;.
	*
	* @param layoutSetPrototypeId the primary key of the current layout set prototype
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype[] findByUuid_PrevAndNext(
		long layoutSetPrototypeId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(layoutSetPrototypeId, uuid,
			orderByComparator);
	}

	/**
	* Returns all the layout set prototypes that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUuid(uuid);
	}

	/**
	* Returns a range of all the layout set prototypes that the user has permission to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @return the range of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the layout set prototypes that the user has permissions to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the layout set prototypes before and after the current layout set prototype in the ordered set of layout set prototypes that the user has permission to view where uuid = &#63;.
	*
	* @param layoutSetPrototypeId the primary key of the current layout set prototype
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype[] filterFindByUuid_PrevAndNext(
		long layoutSetPrototypeId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUuid_PrevAndNext(layoutSetPrototypeId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the layout set prototypes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of layout set prototypes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of layout set prototypes that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	* Returns all the layout set prototypes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the layout set prototypes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @return the range of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the layout set prototypes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first layout set prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first layout set prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set prototype, or <code>null</code> if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last layout set prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last layout set prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set prototype, or <code>null</code> if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the layout set prototypes before and after the current layout set prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param layoutSetPrototypeId the primary key of the current layout set prototype
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype[] findByUuid_C_PrevAndNext(
		long layoutSetPrototypeId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(layoutSetPrototypeId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Returns all the layout set prototypes that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the layout set prototypes that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @return the range of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the layout set prototypes that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUuid_C(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the layout set prototypes before and after the current layout set prototype in the ordered set of layout set prototypes that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* @param layoutSetPrototypeId the primary key of the current layout set prototype
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype[] filterFindByUuid_C_PrevAndNext(
		long layoutSetPrototypeId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUuid_C_PrevAndNext(layoutSetPrototypeId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the layout set prototypes where uuid = &#63; and companyId = &#63; from the database.
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
	* Returns the number of layout set prototypes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of layout set prototypes that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the layout set prototypes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the layout set prototypes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @return the range of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the layout set prototypes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first layout set prototype in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first layout set prototype in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set prototype, or <code>null</code> if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last layout set prototype in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last layout set prototype in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set prototype, or <code>null</code> if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the layout set prototypes before and after the current layout set prototype in the ordered set where companyId = &#63;.
	*
	* @param layoutSetPrototypeId the primary key of the current layout set prototype
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype[] findByCompanyId_PrevAndNext(
		long layoutSetPrototypeId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(layoutSetPrototypeId,
			companyId, orderByComparator);
	}

	/**
	* Returns all the layout set prototypes that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByCompanyId(companyId);
	}

	/**
	* Returns a range of all the layout set prototypes that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @return the range of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the layout set prototypes that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByCompanyId(companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the layout set prototypes before and after the current layout set prototype in the ordered set of layout set prototypes that the user has permission to view where companyId = &#63;.
	*
	* @param layoutSetPrototypeId the primary key of the current layout set prototype
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype[] filterFindByCompanyId_PrevAndNext(
		long layoutSetPrototypeId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByCompanyId_PrevAndNext(layoutSetPrototypeId,
			companyId, orderByComparator);
	}

	/**
	* Removes all the layout set prototypes where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of layout set prototypes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of layout set prototypes that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByCompanyId(companyId);
	}

	/**
	* Returns all the layout set prototypes where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByC_A(
		long companyId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_A(companyId, active);
	}

	/**
	* Returns a range of all the layout set prototypes where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @return the range of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByC_A(
		long companyId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_A(companyId, active, start, end);
	}

	/**
	* Returns an ordered range of all the layout set prototypes where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findByC_A(
		long companyId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_A(companyId, active, start, end, orderByComparator);
	}

	/**
	* Returns the first layout set prototype in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype findByC_A_First(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_A_First(companyId, active, orderByComparator);
	}

	/**
	* Returns the first layout set prototype in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set prototype, or <code>null</code> if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype fetchByC_A_First(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_A_First(companyId, active, orderByComparator);
	}

	/**
	* Returns the last layout set prototype in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype findByC_A_Last(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_A_Last(companyId, active, orderByComparator);
	}

	/**
	* Returns the last layout set prototype in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set prototype, or <code>null</code> if a matching layout set prototype could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype fetchByC_A_Last(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_A_Last(companyId, active, orderByComparator);
	}

	/**
	* Returns the layout set prototypes before and after the current layout set prototype in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param layoutSetPrototypeId the primary key of the current layout set prototype
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype[] findByC_A_PrevAndNext(
		long layoutSetPrototypeId, long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_A_PrevAndNext(layoutSetPrototypeId, companyId,
			active, orderByComparator);
	}

	/**
	* Returns all the layout set prototypes that the user has permission to view where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByC_A(
		long companyId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByC_A(companyId, active);
	}

	/**
	* Returns a range of all the layout set prototypes that the user has permission to view where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @return the range of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByC_A(
		long companyId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByC_A(companyId, active, start, end);
	}

	/**
	* Returns an ordered range of all the layout set prototypes that the user has permissions to view where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> filterFindByC_A(
		long companyId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByC_A(companyId, active, start, end,
			orderByComparator);
	}

	/**
	* Returns the layout set prototypes before and after the current layout set prototype in the ordered set of layout set prototypes that the user has permission to view where companyId = &#63; and active = &#63;.
	*
	* @param layoutSetPrototypeId the primary key of the current layout set prototype
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype[] filterFindByC_A_PrevAndNext(
		long layoutSetPrototypeId, long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByC_A_PrevAndNext(layoutSetPrototypeId,
			companyId, active, orderByComparator);
	}

	/**
	* Removes all the layout set prototypes where companyId = &#63; and active = &#63; from the database.
	*
	* @param companyId the company ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_A(long companyId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_A(companyId, active);
	}

	/**
	* Returns the number of layout set prototypes where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the number of matching layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_A(long companyId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_A(companyId, active);
	}

	/**
	* Returns the number of layout set prototypes that the user has permission to view where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the number of matching layout set prototypes that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByC_A(long companyId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByC_A(companyId, active);
	}

	/**
	* Caches the layout set prototype in the entity cache if it is enabled.
	*
	* @param layoutSetPrototype the layout set prototype
	*/
	public static void cacheResult(
		com.liferay.portal.model.LayoutSetPrototype layoutSetPrototype) {
		getPersistence().cacheResult(layoutSetPrototype);
	}

	/**
	* Caches the layout set prototypes in the entity cache if it is enabled.
	*
	* @param layoutSetPrototypes the layout set prototypes
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.LayoutSetPrototype> layoutSetPrototypes) {
		getPersistence().cacheResult(layoutSetPrototypes);
	}

	/**
	* Creates a new layout set prototype with the primary key. Does not add the layout set prototype to the database.
	*
	* @param layoutSetPrototypeId the primary key for the new layout set prototype
	* @return the new layout set prototype
	*/
	public static com.liferay.portal.model.LayoutSetPrototype create(
		long layoutSetPrototypeId) {
		return getPersistence().create(layoutSetPrototypeId);
	}

	/**
	* Removes the layout set prototype with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetPrototypeId the primary key of the layout set prototype
	* @return the layout set prototype that was removed
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype remove(
		long layoutSetPrototypeId)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(layoutSetPrototypeId);
	}

	public static com.liferay.portal.model.LayoutSetPrototype updateImpl(
		com.liferay.portal.model.LayoutSetPrototype layoutSetPrototype)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(layoutSetPrototype);
	}

	/**
	* Returns the layout set prototype with the primary key or throws a {@link com.liferay.portal.NoSuchLayoutSetPrototypeException} if it could not be found.
	*
	* @param layoutSetPrototypeId the primary key of the layout set prototype
	* @return the layout set prototype
	* @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype findByPrimaryKey(
		long layoutSetPrototypeId)
		throws com.liferay.portal.NoSuchLayoutSetPrototypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(layoutSetPrototypeId);
	}

	/**
	* Returns the layout set prototype with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param layoutSetPrototypeId the primary key of the layout set prototype
	* @return the layout set prototype, or <code>null</code> if a layout set prototype with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetPrototype fetchByPrimaryKey(
		long layoutSetPrototypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(layoutSetPrototypeId);
	}

	/**
	* Returns all the layout set prototypes.
	*
	* @return the layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the layout set prototypes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @return the range of layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the layout set prototypes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout set prototypes
	* @param end the upper bound of the range of layout set prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetPrototype> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the layout set prototypes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of layout set prototypes.
	*
	* @return the number of layout set prototypes
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static LayoutSetPrototypePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (LayoutSetPrototypePersistence)PortalBeanLocatorUtil.locate(LayoutSetPrototypePersistence.class.getName());

			ReferenceRegistry.registerReference(LayoutSetPrototypeUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(LayoutSetPrototypePersistence persistence) {
	}

	private static LayoutSetPrototypePersistence _persistence;
}