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

import com.liferay.portal.model.ResourceTypePermission;

/**
 * The persistence interface for the resource type permission service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceTypePermissionPersistenceImpl
 * @see ResourceTypePermissionUtil
 * @generated
 */
@ProviderType
public interface ResourceTypePermissionPersistence extends BasePersistence<ResourceTypePermission> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ResourceTypePermissionUtil} to access the resource type permission persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the resource type permissions where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the matching resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ResourceTypePermission> findByRoleId(
		long roleId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the resource type permissions where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceTypePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of resource type permissions
	* @param end the upper bound of the range of resource type permissions (not inclusive)
	* @return the range of matching resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ResourceTypePermission> findByRoleId(
		long roleId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the resource type permissions where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceTypePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of resource type permissions
	* @param end the upper bound of the range of resource type permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ResourceTypePermission> findByRoleId(
		long roleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first resource type permission in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource type permission
	* @throws com.liferay.portal.NoSuchResourceTypePermissionException if a matching resource type permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission findByRoleId_First(
		long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceTypePermissionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first resource type permission in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource type permission, or <code>null</code> if a matching resource type permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission fetchByRoleId_First(
		long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last resource type permission in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource type permission
	* @throws com.liferay.portal.NoSuchResourceTypePermissionException if a matching resource type permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission findByRoleId_Last(
		long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceTypePermissionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last resource type permission in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource type permission, or <code>null</code> if a matching resource type permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission fetchByRoleId_Last(
		long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the resource type permissions before and after the current resource type permission in the ordered set where roleId = &#63;.
	*
	* @param resourceTypePermissionId the primary key of the current resource type permission
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource type permission
	* @throws com.liferay.portal.NoSuchResourceTypePermissionException if a resource type permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission[] findByRoleId_PrevAndNext(
		long resourceTypePermissionId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceTypePermissionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the resource type permissions where roleId = &#63; from the database.
	*
	* @param roleId the role ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByRoleId(long roleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of resource type permissions where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the number of matching resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public int countByRoleId(long roleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the resource type permissions where companyId = &#63; and name = &#63; and roleId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param roleId the role ID
	* @return the matching resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ResourceTypePermission> findByC_N_R(
		long companyId, java.lang.String name, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the resource type permissions where companyId = &#63; and name = &#63; and roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceTypePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param roleId the role ID
	* @param start the lower bound of the range of resource type permissions
	* @param end the upper bound of the range of resource type permissions (not inclusive)
	* @return the range of matching resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ResourceTypePermission> findByC_N_R(
		long companyId, java.lang.String name, long roleId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the resource type permissions where companyId = &#63; and name = &#63; and roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceTypePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param roleId the role ID
	* @param start the lower bound of the range of resource type permissions
	* @param end the upper bound of the range of resource type permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ResourceTypePermission> findByC_N_R(
		long companyId, java.lang.String name, long roleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first resource type permission in the ordered set where companyId = &#63; and name = &#63; and roleId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource type permission
	* @throws com.liferay.portal.NoSuchResourceTypePermissionException if a matching resource type permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission findByC_N_R_First(
		long companyId, java.lang.String name, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceTypePermissionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first resource type permission in the ordered set where companyId = &#63; and name = &#63; and roleId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource type permission, or <code>null</code> if a matching resource type permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission fetchByC_N_R_First(
		long companyId, java.lang.String name, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last resource type permission in the ordered set where companyId = &#63; and name = &#63; and roleId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource type permission
	* @throws com.liferay.portal.NoSuchResourceTypePermissionException if a matching resource type permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission findByC_N_R_Last(
		long companyId, java.lang.String name, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceTypePermissionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last resource type permission in the ordered set where companyId = &#63; and name = &#63; and roleId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource type permission, or <code>null</code> if a matching resource type permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission fetchByC_N_R_Last(
		long companyId, java.lang.String name, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the resource type permissions before and after the current resource type permission in the ordered set where companyId = &#63; and name = &#63; and roleId = &#63;.
	*
	* @param resourceTypePermissionId the primary key of the current resource type permission
	* @param companyId the company ID
	* @param name the name
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource type permission
	* @throws com.liferay.portal.NoSuchResourceTypePermissionException if a resource type permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission[] findByC_N_R_PrevAndNext(
		long resourceTypePermissionId, long companyId, java.lang.String name,
		long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceTypePermissionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the resource type permissions where companyId = &#63; and name = &#63; and roleId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @param roleId the role ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_N_R(long companyId, java.lang.String name, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of resource type permissions where companyId = &#63; and name = &#63; and roleId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param roleId the role ID
	* @return the number of matching resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_N_R(long companyId, java.lang.String name, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the resource type permission where companyId = &#63; and groupId = &#63; and name = &#63; and roleId = &#63; or throws a {@link com.liferay.portal.NoSuchResourceTypePermissionException} if it could not be found.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param roleId the role ID
	* @return the matching resource type permission
	* @throws com.liferay.portal.NoSuchResourceTypePermissionException if a matching resource type permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission findByC_G_N_R(
		long companyId, long groupId, java.lang.String name, long roleId)
		throws com.liferay.portal.NoSuchResourceTypePermissionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the resource type permission where companyId = &#63; and groupId = &#63; and name = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param roleId the role ID
	* @return the matching resource type permission, or <code>null</code> if a matching resource type permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission fetchByC_G_N_R(
		long companyId, long groupId, java.lang.String name, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the resource type permission where companyId = &#63; and groupId = &#63; and name = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param roleId the role ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching resource type permission, or <code>null</code> if a matching resource type permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission fetchByC_G_N_R(
		long companyId, long groupId, java.lang.String name, long roleId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the resource type permission where companyId = &#63; and groupId = &#63; and name = &#63; and roleId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param roleId the role ID
	* @return the resource type permission that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission removeByC_G_N_R(
		long companyId, long groupId, java.lang.String name, long roleId)
		throws com.liferay.portal.NoSuchResourceTypePermissionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of resource type permissions where companyId = &#63; and groupId = &#63; and name = &#63; and roleId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param roleId the role ID
	* @return the number of matching resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_G_N_R(long companyId, long groupId,
		java.lang.String name, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the resource type permission in the entity cache if it is enabled.
	*
	* @param resourceTypePermission the resource type permission
	*/
	public void cacheResult(
		com.liferay.portal.model.ResourceTypePermission resourceTypePermission);

	/**
	* Caches the resource type permissions in the entity cache if it is enabled.
	*
	* @param resourceTypePermissions the resource type permissions
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.model.ResourceTypePermission> resourceTypePermissions);

	/**
	* Creates a new resource type permission with the primary key. Does not add the resource type permission to the database.
	*
	* @param resourceTypePermissionId the primary key for the new resource type permission
	* @return the new resource type permission
	*/
	public com.liferay.portal.model.ResourceTypePermission create(
		long resourceTypePermissionId);

	/**
	* Removes the resource type permission with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceTypePermissionId the primary key of the resource type permission
	* @return the resource type permission that was removed
	* @throws com.liferay.portal.NoSuchResourceTypePermissionException if a resource type permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission remove(
		long resourceTypePermissionId)
		throws com.liferay.portal.NoSuchResourceTypePermissionException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.ResourceTypePermission updateImpl(
		com.liferay.portal.model.ResourceTypePermission resourceTypePermission)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the resource type permission with the primary key or throws a {@link com.liferay.portal.NoSuchResourceTypePermissionException} if it could not be found.
	*
	* @param resourceTypePermissionId the primary key of the resource type permission
	* @return the resource type permission
	* @throws com.liferay.portal.NoSuchResourceTypePermissionException if a resource type permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission findByPrimaryKey(
		long resourceTypePermissionId)
		throws com.liferay.portal.NoSuchResourceTypePermissionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the resource type permission with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param resourceTypePermissionId the primary key of the resource type permission
	* @return the resource type permission, or <code>null</code> if a resource type permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission fetchByPrimaryKey(
		long resourceTypePermissionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the resource type permissions.
	*
	* @return the resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ResourceTypePermission> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the resource type permissions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceTypePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource type permissions
	* @param end the upper bound of the range of resource type permissions (not inclusive)
	* @return the range of resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ResourceTypePermission> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the resource type permissions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceTypePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource type permissions
	* @param end the upper bound of the range of resource type permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ResourceTypePermission> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the resource type permissions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of resource type permissions.
	*
	* @return the number of resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}