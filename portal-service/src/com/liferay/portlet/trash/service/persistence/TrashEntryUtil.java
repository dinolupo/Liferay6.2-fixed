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

package com.liferay.portlet.trash.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.trash.model.TrashEntry;

import java.util.List;

/**
 * The persistence utility for the trash entry service. This utility wraps {@link TrashEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrashEntryPersistence
 * @see TrashEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class TrashEntryUtil {
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
	public static void clearCache(TrashEntry trashEntry) {
		getPersistence().clearCache(trashEntry);
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
	public static List<TrashEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrashEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrashEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static TrashEntry update(TrashEntry trashEntry)
		throws SystemException {
		return getPersistence().update(trashEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static TrashEntry update(TrashEntry trashEntry,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(trashEntry, serviceContext);
	}

	/**
	* Returns all the trash entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the trash entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of trash entries
	* @param end the upper bound of the range of trash entries (not inclusive)
	* @return the range of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the trash entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of trash entries
	* @param end the upper bound of the range of trash entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first trash entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first trash entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching trash entry, or <code>null</code> if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last trash entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last trash entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching trash entry, or <code>null</code> if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the trash entries before and after the current trash entry in the ordered set where groupId = &#63;.
	*
	* @param entryId the primary key of the current trash entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a trash entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry[] findByGroupId_PrevAndNext(
		long entryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(entryId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the trash entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of trash entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the trash entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the trash entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of trash entries
	* @param end the upper bound of the range of trash entries (not inclusive)
	* @return the range of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the trash entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of trash entries
	* @param end the upper bound of the range of trash entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first trash entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first trash entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching trash entry, or <code>null</code> if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last trash entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last trash entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching trash entry, or <code>null</code> if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the trash entries before and after the current trash entry in the ordered set where companyId = &#63;.
	*
	* @param entryId the primary key of the current trash entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a trash entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry[] findByCompanyId_PrevAndNext(
		long entryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(entryId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the trash entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of trash entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the trash entries where groupId = &#63; and createDate &lt; &#63;.
	*
	* @param groupId the group ID
	* @param createDate the create date
	* @return the matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByG_LtCD(
		long groupId, java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_LtCD(groupId, createDate);
	}

	/**
	* Returns a range of all the trash entries where groupId = &#63; and createDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param createDate the create date
	* @param start the lower bound of the range of trash entries
	* @param end the upper bound of the range of trash entries (not inclusive)
	* @return the range of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByG_LtCD(
		long groupId, java.util.Date createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_LtCD(groupId, createDate, start, end);
	}

	/**
	* Returns an ordered range of all the trash entries where groupId = &#63; and createDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param createDate the create date
	* @param start the lower bound of the range of trash entries
	* @param end the upper bound of the range of trash entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByG_LtCD(
		long groupId, java.util.Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_LtCD(groupId, createDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first trash entry in the ordered set where groupId = &#63; and createDate &lt; &#63;.
	*
	* @param groupId the group ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry findByG_LtCD_First(
		long groupId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence()
				   .findByG_LtCD_First(groupId, createDate, orderByComparator);
	}

	/**
	* Returns the first trash entry in the ordered set where groupId = &#63; and createDate &lt; &#63;.
	*
	* @param groupId the group ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching trash entry, or <code>null</code> if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry fetchByG_LtCD_First(
		long groupId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_LtCD_First(groupId, createDate, orderByComparator);
	}

	/**
	* Returns the last trash entry in the ordered set where groupId = &#63; and createDate &lt; &#63;.
	*
	* @param groupId the group ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry findByG_LtCD_Last(
		long groupId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence()
				   .findByG_LtCD_Last(groupId, createDate, orderByComparator);
	}

	/**
	* Returns the last trash entry in the ordered set where groupId = &#63; and createDate &lt; &#63;.
	*
	* @param groupId the group ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching trash entry, or <code>null</code> if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry fetchByG_LtCD_Last(
		long groupId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_LtCD_Last(groupId, createDate, orderByComparator);
	}

	/**
	* Returns the trash entries before and after the current trash entry in the ordered set where groupId = &#63; and createDate &lt; &#63;.
	*
	* @param entryId the primary key of the current trash entry
	* @param groupId the group ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a trash entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry[] findByG_LtCD_PrevAndNext(
		long entryId, long groupId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence()
				   .findByG_LtCD_PrevAndNext(entryId, groupId, createDate,
			orderByComparator);
	}

	/**
	* Removes all the trash entries where groupId = &#63; and createDate &lt; &#63; from the database.
	*
	* @param groupId the group ID
	* @param createDate the create date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_LtCD(long groupId, java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_LtCD(groupId, createDate);
	}

	/**
	* Returns the number of trash entries where groupId = &#63; and createDate &lt; &#63;.
	*
	* @param groupId the group ID
	* @param createDate the create date
	* @return the number of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_LtCD(long groupId, java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_LtCD(groupId, createDate);
	}

	/**
	* Returns all the trash entries where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @return the matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByG_C(
		long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupId, classNameId);
	}

	/**
	* Returns a range of all the trash entries where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of trash entries
	* @param end the upper bound of the range of trash entries (not inclusive)
	* @return the range of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByG_C(
		long groupId, long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupId, classNameId, start, end);
	}

	/**
	* Returns an ordered range of all the trash entries where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of trash entries
	* @param end the upper bound of the range of trash entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findByG_C(
		long groupId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C(groupId, classNameId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first trash entry in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry findByG_C_First(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence()
				   .findByG_C_First(groupId, classNameId, orderByComparator);
	}

	/**
	* Returns the first trash entry in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching trash entry, or <code>null</code> if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry fetchByG_C_First(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_C_First(groupId, classNameId, orderByComparator);
	}

	/**
	* Returns the last trash entry in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry findByG_C_Last(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence()
				   .findByG_C_Last(groupId, classNameId, orderByComparator);
	}

	/**
	* Returns the last trash entry in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching trash entry, or <code>null</code> if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry fetchByG_C_Last(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_C_Last(groupId, classNameId, orderByComparator);
	}

	/**
	* Returns the trash entries before and after the current trash entry in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param entryId the primary key of the current trash entry
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a trash entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry[] findByG_C_PrevAndNext(
		long entryId, long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence()
				   .findByG_C_PrevAndNext(entryId, groupId, classNameId,
			orderByComparator);
	}

	/**
	* Removes all the trash entries where groupId = &#63; and classNameId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_C(long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_C(groupId, classNameId);
	}

	/**
	* Returns the number of trash entries where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @return the number of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C(long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_C(groupId, classNameId);
	}

	/**
	* Returns the trash entry where classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.portlet.trash.NoSuchEntryException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Returns the trash entry where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching trash entry, or <code>null</code> if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry fetchByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_C(classNameId, classPK);
	}

	/**
	* Returns the trash entry where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching trash entry, or <code>null</code> if a matching trash entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry fetchByC_C(
		long classNameId, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C(classNameId, classPK, retrieveFromCache);
	}

	/**
	* Removes the trash entry where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the trash entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry removeByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of trash entries where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Caches the trash entry in the entity cache if it is enabled.
	*
	* @param trashEntry the trash entry
	*/
	public static void cacheResult(
		com.liferay.portlet.trash.model.TrashEntry trashEntry) {
		getPersistence().cacheResult(trashEntry);
	}

	/**
	* Caches the trash entries in the entity cache if it is enabled.
	*
	* @param trashEntries the trash entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portlet.trash.model.TrashEntry> trashEntries) {
		getPersistence().cacheResult(trashEntries);
	}

	/**
	* Creates a new trash entry with the primary key. Does not add the trash entry to the database.
	*
	* @param entryId the primary key for the new trash entry
	* @return the new trash entry
	*/
	public static com.liferay.portlet.trash.model.TrashEntry create(
		long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	* Removes the trash entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the trash entry
	* @return the trash entry that was removed
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a trash entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry remove(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence().remove(entryId);
	}

	public static com.liferay.portlet.trash.model.TrashEntry updateImpl(
		com.liferay.portlet.trash.model.TrashEntry trashEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trashEntry);
	}

	/**
	* Returns the trash entry with the primary key or throws a {@link com.liferay.portlet.trash.NoSuchEntryException} if it could not be found.
	*
	* @param entryId the primary key of the trash entry
	* @return the trash entry
	* @throws com.liferay.portlet.trash.NoSuchEntryException if a trash entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry findByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.trash.NoSuchEntryException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	* Returns the trash entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the trash entry
	* @return the trash entry, or <code>null</code> if a trash entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.trash.model.TrashEntry fetchByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	/**
	* Returns all the trash entries.
	*
	* @return the trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the trash entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of trash entries
	* @param end the upper bound of the range of trash entries (not inclusive)
	* @return the range of trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the trash entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.trash.model.impl.TrashEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of trash entries
	* @param end the upper bound of the range of trash entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.trash.model.TrashEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the trash entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of trash entries.
	*
	* @return the number of trash entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrashEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrashEntryPersistence)PortalBeanLocatorUtil.locate(TrashEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(TrashEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(TrashEntryPersistence persistence) {
	}

	private static TrashEntryPersistence _persistence;
}