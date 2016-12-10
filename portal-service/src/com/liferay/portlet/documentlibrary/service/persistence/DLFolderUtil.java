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

package com.liferay.portlet.documentlibrary.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.documentlibrary.model.DLFolder;

import java.util.List;

/**
 * The persistence utility for the document library folder service. This utility wraps {@link DLFolderPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFolderPersistence
 * @see DLFolderPersistenceImpl
 * @generated
 */
@ProviderType
public class DLFolderUtil {
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
	public static void clearCache(DLFolder dlFolder) {
		getPersistence().clearCache(dlFolder);
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
	public static List<DLFolder> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DLFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DLFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static DLFolder update(DLFolder dlFolder) throws SystemException {
		return getPersistence().update(dlFolder);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static DLFolder update(DLFolder dlFolder,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(dlFolder, serviceContext);
	}

	/**
	* Returns all the document library folders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the document library folders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set where uuid = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] findByUuid_PrevAndNext(
		long folderId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByUuid_PrevAndNext(folderId, uuid, orderByComparator);
	}

	/**
	* Removes all the document library folders where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of document library folders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the document library folder where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.portlet.documentlibrary.NoSuchFolderException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the document library folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the document library folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the document library folder where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the document library folder that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of document library folders where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the document library folders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the document library folders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] findByUuid_C_PrevAndNext(
		long folderId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(folderId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the document library folders where uuid = &#63; and companyId = &#63; from the database.
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
	* Returns the number of document library folders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the document library folders where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the document library folders where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set where groupId = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] findByGroupId_PrevAndNext(
		long folderId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(folderId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the document library folders that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the document library folders that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set of document library folders that the user has permission to view where groupId = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] filterFindByGroupId_PrevAndNext(
		long folderId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(folderId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the document library folders where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of document library folders where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of document library folders that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the document library folders where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the document library folders where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set where companyId = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] findByCompanyId_PrevAndNext(
		long folderId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(folderId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the document library folders where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of document library folders where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the document library folder where repositoryId = &#63; or throws a {@link com.liferay.portlet.documentlibrary.NoSuchFolderException} if it could not be found.
	*
	* @param repositoryId the repository ID
	* @return the matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByRepositoryId(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().findByRepositoryId(repositoryId);
	}

	/**
	* Returns the document library folder where repositoryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param repositoryId the repository ID
	* @return the matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByRepositoryId(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByRepositoryId(repositoryId);
	}

	/**
	* Returns the document library folder where repositoryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param repositoryId the repository ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByRepositoryId(
		long repositoryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByRepositoryId(repositoryId, retrieveFromCache);
	}

	/**
	* Removes the document library folder where repositoryId = &#63; from the database.
	*
	* @param repositoryId the repository ID
	* @return the document library folder that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder removeByRepositoryId(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().removeByRepositoryId(repositoryId);
	}

	/**
	* Returns the number of document library folders where repositoryId = &#63;.
	*
	* @param repositoryId the repository ID
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByRepositoryId(long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByRepositoryId(repositoryId);
	}

	/**
	* Returns all the document library folders where repositoryId = &#63;.
	*
	* @param repositoryId the repository ID
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByRepositoryIdList(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRepositoryIdList(repositoryId);
	}

	/**
	* Returns a range of all the document library folders where repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByRepositoryIdList(
		long repositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRepositoryIdList(repositoryId, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByRepositoryIdList(
		long repositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRepositoryIdList(repositoryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where repositoryId = &#63;.
	*
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByRepositoryIdList_First(
		long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByRepositoryIdList_First(repositoryId, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where repositoryId = &#63;.
	*
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByRepositoryIdList_First(
		long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByRepositoryIdList_First(repositoryId,
			orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where repositoryId = &#63;.
	*
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByRepositoryIdList_Last(
		long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByRepositoryIdList_Last(repositoryId, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where repositoryId = &#63;.
	*
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByRepositoryIdList_Last(
		long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByRepositoryIdList_Last(repositoryId, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set where repositoryId = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] findByRepositoryIdList_PrevAndNext(
		long folderId, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByRepositoryIdList_PrevAndNext(folderId, repositoryId,
			orderByComparator);
	}

	/**
	* Removes all the document library folders where repositoryId = &#63; from the database.
	*
	* @param repositoryId the repository ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByRepositoryIdList(long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByRepositoryIdList(repositoryId);
	}

	/**
	* Returns the number of document library folders where repositoryId = &#63;.
	*
	* @param repositoryId the repository ID
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByRepositoryIdList(long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByRepositoryIdList(repositoryId);
	}

	/**
	* Returns all the document library folders where groupId = &#63; and parentFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_P(
		long groupId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_P(groupId, parentFolderId);
	}

	/**
	* Returns a range of all the document library folders where groupId = &#63; and parentFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_P(
		long groupId, long parentFolderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_P(groupId, parentFolderId, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where groupId = &#63; and parentFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_P(
		long groupId, long parentFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P(groupId, parentFolderId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where groupId = &#63; and parentFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByG_P_First(
		long groupId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_P_First(groupId, parentFolderId, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where groupId = &#63; and parentFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByG_P_First(
		long groupId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_First(groupId, parentFolderId, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where groupId = &#63; and parentFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByG_P_Last(
		long groupId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_P_Last(groupId, parentFolderId, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where groupId = &#63; and parentFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByG_P_Last(
		long groupId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_Last(groupId, parentFolderId, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set where groupId = &#63; and parentFolderId = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] findByG_P_PrevAndNext(
		long folderId, long groupId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_P_PrevAndNext(folderId, groupId, parentFolderId,
			orderByComparator);
	}

	/**
	* Returns all the document library folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @return the matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_P(
		long groupId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_P(groupId, parentFolderId);
	}

	/**
	* Returns a range of all the document library folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_P(
		long groupId, long parentFolderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P(groupId, parentFolderId, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders that the user has permissions to view where groupId = &#63; and parentFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_P(
		long groupId, long parentFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P(groupId, parentFolderId, start, end,
			orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set of document library folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] filterFindByG_P_PrevAndNext(
		long folderId, long groupId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .filterFindByG_P_PrevAndNext(folderId, groupId,
			parentFolderId, orderByComparator);
	}

	/**
	* Removes all the document library folders where groupId = &#63; and parentFolderId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P(long groupId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P(groupId, parentFolderId);
	}

	/**
	* Returns the number of document library folders where groupId = &#63; and parentFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P(long groupId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_P(groupId, parentFolderId);
	}

	/**
	* Returns the number of document library folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @return the number of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P(long groupId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_P(groupId, parentFolderId);
	}

	/**
	* Returns all the document library folders where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByC_NotS(
		long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_NotS(companyId, status);
	}

	/**
	* Returns a range of all the document library folders where companyId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByC_NotS(
		long companyId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_NotS(companyId, status, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where companyId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByC_NotS(
		long companyId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_NotS(companyId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByC_NotS_First(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByC_NotS_First(companyId, status, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByC_NotS_First(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_NotS_First(companyId, status, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByC_NotS_Last(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByC_NotS_Last(companyId, status, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByC_NotS_Last(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_NotS_Last(companyId, status, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] findByC_NotS_PrevAndNext(
		long folderId, long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByC_NotS_PrevAndNext(folderId, companyId, status,
			orderByComparator);
	}

	/**
	* Removes all the document library folders where companyId = &#63; and status &ne; &#63; from the database.
	*
	* @param companyId the company ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_NotS(long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_NotS(companyId, status);
	}

	/**
	* Returns the number of document library folders where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_NotS(long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_NotS(companyId, status);
	}

	/**
	* Returns all the document library folders where parentFolderId = &#63; and name = &#63;.
	*
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByP_N(
		long parentFolderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_N(parentFolderId, name);
	}

	/**
	* Returns a range of all the document library folders where parentFolderId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByP_N(
		long parentFolderId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_N(parentFolderId, name, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where parentFolderId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByP_N(
		long parentFolderId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_N(parentFolderId, name, start, end,
			orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where parentFolderId = &#63; and name = &#63;.
	*
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByP_N_First(
		long parentFolderId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByP_N_First(parentFolderId, name, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where parentFolderId = &#63; and name = &#63;.
	*
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByP_N_First(
		long parentFolderId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByP_N_First(parentFolderId, name, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where parentFolderId = &#63; and name = &#63;.
	*
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByP_N_Last(
		long parentFolderId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByP_N_Last(parentFolderId, name, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where parentFolderId = &#63; and name = &#63;.
	*
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByP_N_Last(
		long parentFolderId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByP_N_Last(parentFolderId, name, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set where parentFolderId = &#63; and name = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] findByP_N_PrevAndNext(
		long folderId, long parentFolderId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByP_N_PrevAndNext(folderId, parentFolderId, name,
			orderByComparator);
	}

	/**
	* Removes all the document library folders where parentFolderId = &#63; and name = &#63; from the database.
	*
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_N(long parentFolderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_N(parentFolderId, name);
	}

	/**
	* Returns the number of document library folders where parentFolderId = &#63; and name = &#63;.
	*
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_N(long parentFolderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_N(parentFolderId, name);
	}

	/**
	* Returns all the document library folders where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	*
	* @param folderId the folder ID
	* @param companyId the company ID
	* @param parentFolderId the parent folder ID
	* @param status the status
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByF_C_P_NotS(
		long folderId, long companyId, long parentFolderId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_C_P_NotS(folderId, companyId, parentFolderId, status);
	}

	/**
	* Returns a range of all the document library folders where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param folderId the folder ID
	* @param companyId the company ID
	* @param parentFolderId the parent folder ID
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByF_C_P_NotS(
		long folderId, long companyId, long parentFolderId, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_C_P_NotS(folderId, companyId, parentFolderId,
			status, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param folderId the folder ID
	* @param companyId the company ID
	* @param parentFolderId the parent folder ID
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByF_C_P_NotS(
		long folderId, long companyId, long parentFolderId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_C_P_NotS(folderId, companyId, parentFolderId,
			status, start, end, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	*
	* @param folderId the folder ID
	* @param companyId the company ID
	* @param parentFolderId the parent folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByF_C_P_NotS_First(
		long folderId, long companyId, long parentFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByF_C_P_NotS_First(folderId, companyId, parentFolderId,
			status, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	*
	* @param folderId the folder ID
	* @param companyId the company ID
	* @param parentFolderId the parent folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByF_C_P_NotS_First(
		long folderId, long companyId, long parentFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByF_C_P_NotS_First(folderId, companyId,
			parentFolderId, status, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	*
	* @param folderId the folder ID
	* @param companyId the company ID
	* @param parentFolderId the parent folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByF_C_P_NotS_Last(
		long folderId, long companyId, long parentFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByF_C_P_NotS_Last(folderId, companyId, parentFolderId,
			status, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	*
	* @param folderId the folder ID
	* @param companyId the company ID
	* @param parentFolderId the parent folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByF_C_P_NotS_Last(
		long folderId, long companyId, long parentFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByF_C_P_NotS_Last(folderId, companyId, parentFolderId,
			status, orderByComparator);
	}

	/**
	* Removes all the document library folders where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63; from the database.
	*
	* @param folderId the folder ID
	* @param companyId the company ID
	* @param parentFolderId the parent folder ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByF_C_P_NotS(long folderId, long companyId,
		long parentFolderId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByF_C_P_NotS(folderId, companyId, parentFolderId, status);
	}

	/**
	* Returns the number of document library folders where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	*
	* @param folderId the folder ID
	* @param companyId the company ID
	* @param parentFolderId the parent folder ID
	* @param status the status
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByF_C_P_NotS(long folderId, long companyId,
		long parentFolderId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByF_C_P_NotS(folderId, companyId, parentFolderId,
			status);
	}

	/**
	* Returns the document library folder where groupId = &#63; and parentFolderId = &#63; and name = &#63; or throws a {@link com.liferay.portlet.documentlibrary.NoSuchFolderException} if it could not be found.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @return the matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByG_P_N(
		long groupId, long parentFolderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().findByG_P_N(groupId, parentFolderId, name);
	}

	/**
	* Returns the document library folder where groupId = &#63; and parentFolderId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @return the matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByG_P_N(
		long groupId, long parentFolderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_P_N(groupId, parentFolderId, name);
	}

	/**
	* Returns the document library folder where groupId = &#63; and parentFolderId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByG_P_N(
		long groupId, long parentFolderId, java.lang.String name,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_N(groupId, parentFolderId, name,
			retrieveFromCache);
	}

	/**
	* Removes the document library folder where groupId = &#63; and parentFolderId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @return the document library folder that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder removeByG_P_N(
		long groupId, long parentFolderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().removeByG_P_N(groupId, parentFolderId, name);
	}

	/**
	* Returns the number of document library folders where groupId = &#63; and parentFolderId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param name the name
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_N(long groupId, long parentFolderId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_P_N(groupId, parentFolderId, name);
	}

	/**
	* Returns all the document library folders where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_M_P_H(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_M_P_H(groupId, mountPoint, parentFolderId, hidden);
	}

	/**
	* Returns a range of all the document library folders where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_M_P_H(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_M_P_H(groupId, mountPoint, parentFolderId, hidden,
			start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_M_P_H(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_M_P_H(groupId, mountPoint, parentFolderId, hidden,
			start, end, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByG_M_P_H_First(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_M_P_H_First(groupId, mountPoint, parentFolderId,
			hidden, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByG_M_P_H_First(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_M_P_H_First(groupId, mountPoint, parentFolderId,
			hidden, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByG_M_P_H_Last(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_M_P_H_Last(groupId, mountPoint, parentFolderId,
			hidden, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByG_M_P_H_Last(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_M_P_H_Last(groupId, mountPoint, parentFolderId,
			hidden, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] findByG_M_P_H_PrevAndNext(
		long folderId, long groupId, boolean mountPoint, long parentFolderId,
		boolean hidden,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_M_P_H_PrevAndNext(folderId, groupId, mountPoint,
			parentFolderId, hidden, orderByComparator);
	}

	/**
	* Returns all the document library folders that the user has permission to view where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @return the matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_M_P_H(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_M_P_H(groupId, mountPoint, parentFolderId,
			hidden);
	}

	/**
	* Returns a range of all the document library folders that the user has permission to view where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_M_P_H(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_M_P_H(groupId, mountPoint, parentFolderId,
			hidden, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders that the user has permissions to view where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_M_P_H(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_M_P_H(groupId, mountPoint, parentFolderId,
			hidden, start, end, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set of document library folders that the user has permission to view where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] filterFindByG_M_P_H_PrevAndNext(
		long folderId, long groupId, boolean mountPoint, long parentFolderId,
		boolean hidden,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .filterFindByG_M_P_H_PrevAndNext(folderId, groupId,
			mountPoint, parentFolderId, hidden, orderByComparator);
	}

	/**
	* Removes all the document library folders where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; from the database.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_M_P_H(long groupId, boolean mountPoint,
		long parentFolderId, boolean hidden)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_M_P_H(groupId, mountPoint, parentFolderId, hidden);
	}

	/**
	* Returns the number of document library folders where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_M_P_H(long groupId, boolean mountPoint,
		long parentFolderId, boolean hidden)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_M_P_H(groupId, mountPoint, parentFolderId, hidden);
	}

	/**
	* Returns the number of document library folders that the user has permission to view where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @return the number of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_M_P_H(long groupId, boolean mountPoint,
		long parentFolderId, boolean hidden)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_M_P_H(groupId, mountPoint, parentFolderId,
			hidden);
	}

	/**
	* Returns all the document library folders where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_P_H_S(
		long groupId, long parentFolderId, boolean hidden, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_H_S(groupId, parentFolderId, hidden, status);
	}

	/**
	* Returns a range of all the document library folders where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_P_H_S(
		long groupId, long parentFolderId, boolean hidden, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_H_S(groupId, parentFolderId, hidden, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_P_H_S(
		long groupId, long parentFolderId, boolean hidden, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_H_S(groupId, parentFolderId, hidden, status,
			start, end, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByG_P_H_S_First(
		long groupId, long parentFolderId, boolean hidden, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_P_H_S_First(groupId, parentFolderId, hidden,
			status, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByG_P_H_S_First(
		long groupId, long parentFolderId, boolean hidden, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_H_S_First(groupId, parentFolderId, hidden,
			status, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByG_P_H_S_Last(
		long groupId, long parentFolderId, boolean hidden, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_P_H_S_Last(groupId, parentFolderId, hidden, status,
			orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByG_P_H_S_Last(
		long groupId, long parentFolderId, boolean hidden, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_H_S_Last(groupId, parentFolderId, hidden,
			status, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] findByG_P_H_S_PrevAndNext(
		long folderId, long groupId, long parentFolderId, boolean hidden,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_P_H_S_PrevAndNext(folderId, groupId,
			parentFolderId, hidden, status, orderByComparator);
	}

	/**
	* Returns all the document library folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @return the matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_P_H_S(
		long groupId, long parentFolderId, boolean hidden, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_H_S(groupId, parentFolderId, hidden, status);
	}

	/**
	* Returns a range of all the document library folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_P_H_S(
		long groupId, long parentFolderId, boolean hidden, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_H_S(groupId, parentFolderId, hidden,
			status, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders that the user has permissions to view where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_P_H_S(
		long groupId, long parentFolderId, boolean hidden, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_H_S(groupId, parentFolderId, hidden,
			status, start, end, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set of document library folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] filterFindByG_P_H_S_PrevAndNext(
		long folderId, long groupId, long parentFolderId, boolean hidden,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .filterFindByG_P_H_S_PrevAndNext(folderId, groupId,
			parentFolderId, hidden, status, orderByComparator);
	}

	/**
	* Removes all the document library folders where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_H_S(long groupId, long parentFolderId,
		boolean hidden, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P_H_S(groupId, parentFolderId, hidden, status);
	}

	/**
	* Returns the number of document library folders where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_H_S(long groupId, long parentFolderId,
		boolean hidden, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_H_S(groupId, parentFolderId, hidden, status);
	}

	/**
	* Returns the number of document library folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @return the number of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_H_S(long groupId, long parentFolderId,
		boolean hidden, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_H_S(groupId, parentFolderId, hidden, status);
	}

	/**
	* Returns all the document library folders where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @return the matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_M_P_H_S(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_M_P_H_S(groupId, mountPoint, parentFolderId,
			hidden, status);
	}

	/**
	* Returns a range of all the document library folders where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_M_P_H_S(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_M_P_H_S(groupId, mountPoint, parentFolderId,
			hidden, status, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findByG_M_P_H_S(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_M_P_H_S(groupId, mountPoint, parentFolderId,
			hidden, status, start, end, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByG_M_P_H_S_First(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_M_P_H_S_First(groupId, mountPoint, parentFolderId,
			hidden, status, orderByComparator);
	}

	/**
	* Returns the first document library folder in the ordered set where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByG_M_P_H_S_First(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_M_P_H_S_First(groupId, mountPoint, parentFolderId,
			hidden, status, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByG_M_P_H_S_Last(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_M_P_H_S_Last(groupId, mountPoint, parentFolderId,
			hidden, status, orderByComparator);
	}

	/**
	* Returns the last document library folder in the ordered set where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library folder, or <code>null</code> if a matching document library folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByG_M_P_H_S_Last(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_M_P_H_S_Last(groupId, mountPoint, parentFolderId,
			hidden, status, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] findByG_M_P_H_S_PrevAndNext(
		long folderId, long groupId, boolean mountPoint, long parentFolderId,
		boolean hidden, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .findByG_M_P_H_S_PrevAndNext(folderId, groupId, mountPoint,
			parentFolderId, hidden, status, orderByComparator);
	}

	/**
	* Returns all the document library folders that the user has permission to view where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @return the matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_M_P_H_S(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_M_P_H_S(groupId, mountPoint, parentFolderId,
			hidden, status);
	}

	/**
	* Returns a range of all the document library folders that the user has permission to view where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_M_P_H_S(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_M_P_H_S(groupId, mountPoint, parentFolderId,
			hidden, status, start, end);
	}

	/**
	* Returns an ordered range of all the document library folders that the user has permissions to view where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> filterFindByG_M_P_H_S(
		long groupId, boolean mountPoint, long parentFolderId, boolean hidden,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_M_P_H_S(groupId, mountPoint, parentFolderId,
			hidden, status, start, end, orderByComparator);
	}

	/**
	* Returns the document library folders before and after the current document library folder in the ordered set of document library folders that the user has permission to view where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param folderId the primary key of the current document library folder
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder[] filterFindByG_M_P_H_S_PrevAndNext(
		long folderId, long groupId, boolean mountPoint, long parentFolderId,
		boolean hidden, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence()
				   .filterFindByG_M_P_H_S_PrevAndNext(folderId, groupId,
			mountPoint, parentFolderId, hidden, status, orderByComparator);
	}

	/**
	* Removes all the document library folders where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_M_P_H_S(long groupId, boolean mountPoint,
		long parentFolderId, boolean hidden, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_M_P_H_S(groupId, mountPoint, parentFolderId, hidden,
			status);
	}

	/**
	* Returns the number of document library folders where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @return the number of matching document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_M_P_H_S(long groupId, boolean mountPoint,
		long parentFolderId, boolean hidden, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_M_P_H_S(groupId, mountPoint, parentFolderId,
			hidden, status);
	}

	/**
	* Returns the number of document library folders that the user has permission to view where groupId = &#63; and mountPoint = &#63; and parentFolderId = &#63; and hidden = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param mountPoint the mount point
	* @param parentFolderId the parent folder ID
	* @param hidden the hidden
	* @param status the status
	* @return the number of matching document library folders that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_M_P_H_S(long groupId, boolean mountPoint,
		long parentFolderId, boolean hidden, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_M_P_H_S(groupId, mountPoint, parentFolderId,
			hidden, status);
	}

	/**
	* Caches the document library folder in the entity cache if it is enabled.
	*
	* @param dlFolder the document library folder
	*/
	public static void cacheResult(
		com.liferay.portlet.documentlibrary.model.DLFolder dlFolder) {
		getPersistence().cacheResult(dlFolder);
	}

	/**
	* Caches the document library folders in the entity cache if it is enabled.
	*
	* @param dlFolders the document library folders
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> dlFolders) {
		getPersistence().cacheResult(dlFolders);
	}

	/**
	* Creates a new document library folder with the primary key. Does not add the document library folder to the database.
	*
	* @param folderId the primary key for the new document library folder
	* @return the new document library folder
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder create(
		long folderId) {
		return getPersistence().create(folderId);
	}

	/**
	* Removes the document library folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the document library folder
	* @return the document library folder that was removed
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder remove(
		long folderId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().remove(folderId);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFolder updateImpl(
		com.liferay.portlet.documentlibrary.model.DLFolder dlFolder)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(dlFolder);
	}

	/**
	* Returns the document library folder with the primary key or throws a {@link com.liferay.portlet.documentlibrary.NoSuchFolderException} if it could not be found.
	*
	* @param folderId the primary key of the document library folder
	* @return the document library folder
	* @throws com.liferay.portlet.documentlibrary.NoSuchFolderException if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder findByPrimaryKey(
		long folderId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFolderException {
		return getPersistence().findByPrimaryKey(folderId);
	}

	/**
	* Returns the document library folder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param folderId the primary key of the document library folder
	* @return the document library folder, or <code>null</code> if a document library folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFolder fetchByPrimaryKey(
		long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(folderId);
	}

	/**
	* Returns all the document library folders.
	*
	* @return the document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the document library folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the document library folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFolder> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the document library folders from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of document library folders.
	*
	* @return the number of document library folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the document library file entry types associated with the document library folder.
	*
	* @param pk the primary key of the document library folder
	* @return the document library file entry types associated with the document library folder
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> getDLFileEntryTypes(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getDLFileEntryTypes(pk);
	}

	/**
	* Returns a range of all the document library file entry types associated with the document library folder.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the document library folder
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of document library file entry types associated with the document library folder
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> getDLFileEntryTypes(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getDLFileEntryTypes(pk, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entry types associated with the document library folder.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the document library folder
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of document library file entry types associated with the document library folder
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> getDLFileEntryTypes(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getDLFileEntryTypes(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of document library file entry types associated with the document library folder.
	*
	* @param pk the primary key of the document library folder
	* @return the number of document library file entry types associated with the document library folder
	* @throws SystemException if a system exception occurred
	*/
	public static int getDLFileEntryTypesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getDLFileEntryTypesSize(pk);
	}

	/**
	* Returns <code>true</code> if the document library file entry type is associated with the document library folder.
	*
	* @param pk the primary key of the document library folder
	* @param dlFileEntryTypePK the primary key of the document library file entry type
	* @return <code>true</code> if the document library file entry type is associated with the document library folder; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsDLFileEntryType(long pk,
		long dlFileEntryTypePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsDLFileEntryType(pk, dlFileEntryTypePK);
	}

	/**
	* Returns <code>true</code> if the document library folder has any document library file entry types associated with it.
	*
	* @param pk the primary key of the document library folder to check for associations with document library file entry types
	* @return <code>true</code> if the document library folder has any document library file entry types associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsDLFileEntryTypes(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsDLFileEntryTypes(pk);
	}

	/**
	* Adds an association between the document library folder and the document library file entry type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the document library folder
	* @param dlFileEntryTypePK the primary key of the document library file entry type
	* @throws SystemException if a system exception occurred
	*/
	public static void addDLFileEntryType(long pk, long dlFileEntryTypePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addDLFileEntryType(pk, dlFileEntryTypePK);
	}

	/**
	* Adds an association between the document library folder and the document library file entry type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the document library folder
	* @param dlFileEntryType the document library file entry type
	* @throws SystemException if a system exception occurred
	*/
	public static void addDLFileEntryType(long pk,
		com.liferay.portlet.documentlibrary.model.DLFileEntryType dlFileEntryType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addDLFileEntryType(pk, dlFileEntryType);
	}

	/**
	* Adds an association between the document library folder and the document library file entry types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the document library folder
	* @param dlFileEntryTypePKs the primary keys of the document library file entry types
	* @throws SystemException if a system exception occurred
	*/
	public static void addDLFileEntryTypes(long pk, long[] dlFileEntryTypePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addDLFileEntryTypes(pk, dlFileEntryTypePKs);
	}

	/**
	* Adds an association between the document library folder and the document library file entry types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the document library folder
	* @param dlFileEntryTypes the document library file entry types
	* @throws SystemException if a system exception occurred
	*/
	public static void addDLFileEntryTypes(long pk,
		java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> dlFileEntryTypes)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addDLFileEntryTypes(pk, dlFileEntryTypes);
	}

	/**
	* Clears all associations between the document library folder and its document library file entry types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the document library folder to clear the associated document library file entry types from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearDLFileEntryTypes(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearDLFileEntryTypes(pk);
	}

	/**
	* Removes the association between the document library folder and the document library file entry type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the document library folder
	* @param dlFileEntryTypePK the primary key of the document library file entry type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeDLFileEntryType(long pk, long dlFileEntryTypePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeDLFileEntryType(pk, dlFileEntryTypePK);
	}

	/**
	* Removes the association between the document library folder and the document library file entry type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the document library folder
	* @param dlFileEntryType the document library file entry type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeDLFileEntryType(long pk,
		com.liferay.portlet.documentlibrary.model.DLFileEntryType dlFileEntryType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeDLFileEntryType(pk, dlFileEntryType);
	}

	/**
	* Removes the association between the document library folder and the document library file entry types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the document library folder
	* @param dlFileEntryTypePKs the primary keys of the document library file entry types
	* @throws SystemException if a system exception occurred
	*/
	public static void removeDLFileEntryTypes(long pk, long[] dlFileEntryTypePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeDLFileEntryTypes(pk, dlFileEntryTypePKs);
	}

	/**
	* Removes the association between the document library folder and the document library file entry types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the document library folder
	* @param dlFileEntryTypes the document library file entry types
	* @throws SystemException if a system exception occurred
	*/
	public static void removeDLFileEntryTypes(long pk,
		java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> dlFileEntryTypes)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeDLFileEntryTypes(pk, dlFileEntryTypes);
	}

	/**
	* Sets the document library file entry types associated with the document library folder, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the document library folder
	* @param dlFileEntryTypePKs the primary keys of the document library file entry types to be associated with the document library folder
	* @throws SystemException if a system exception occurred
	*/
	public static void setDLFileEntryTypes(long pk, long[] dlFileEntryTypePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setDLFileEntryTypes(pk, dlFileEntryTypePKs);
	}

	/**
	* Sets the document library file entry types associated with the document library folder, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the document library folder
	* @param dlFileEntryTypes the document library file entry types to be associated with the document library folder
	* @throws SystemException if a system exception occurred
	*/
	public static void setDLFileEntryTypes(long pk,
		java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> dlFileEntryTypes)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setDLFileEntryTypes(pk, dlFileEntryTypes);
	}

	public static DLFolderPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (DLFolderPersistence)PortalBeanLocatorUtil.locate(DLFolderPersistence.class.getName());

			ReferenceRegistry.registerReference(DLFolderUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(DLFolderPersistence persistence) {
	}

	private static DLFolderPersistence _persistence;
}