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
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the user group role service. This utility wraps {@link UserGroupRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupRolePersistence
 * @see UserGroupRolePersistenceImpl
 * @generated
 */
@ProviderType
public class UserGroupRoleUtil {
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
	public static void clearCache(UserGroupRole userGroupRole) {
		getPersistence().clearCache(userGroupRole);
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
	public static List<UserGroupRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserGroupRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserGroupRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static UserGroupRole update(UserGroupRole userGroupRole)
		throws SystemException {
		return getPersistence().update(userGroupRole);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static UserGroupRole update(UserGroupRole userGroupRole,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(userGroupRole, serviceContext);
	}

	/**
	* Returns all the user group roles where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the user group roles where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the user group roles where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first user group role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first user group role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role, or <code>null</code> if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last user group role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last user group role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role, or <code>null</code> if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the user group roles before and after the current user group role in the ordered set where userId = &#63;.
	*
	* @param userGroupRolePK the primary key of the current user group role
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole[] findByUserId_PrevAndNext(
		com.liferay.portal.service.persistence.UserGroupRolePK userGroupRolePK,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(userGroupRolePK, userId,
			orderByComparator);
	}

	/**
	* Removes all the user group roles where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of user group roles where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the user group roles where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the user group roles where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the user group roles where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first user group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first user group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role, or <code>null</code> if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last user group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last user group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role, or <code>null</code> if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the user group roles before and after the current user group role in the ordered set where groupId = &#63;.
	*
	* @param userGroupRolePK the primary key of the current user group role
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole[] findByGroupId_PrevAndNext(
		com.liferay.portal.service.persistence.UserGroupRolePK userGroupRolePK,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(userGroupRolePK, groupId,
			orderByComparator);
	}

	/**
	* Removes all the user group roles where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of user group roles where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the user group roles where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByRoleId(
		long roleId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRoleId(roleId);
	}

	/**
	* Returns a range of all the user group roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByRoleId(
		long roleId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRoleId(roleId, start, end);
	}

	/**
	* Returns an ordered range of all the user group roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByRoleId(
		long roleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRoleId(roleId, start, end, orderByComparator);
	}

	/**
	* Returns the first user group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole findByRoleId_First(
		long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRoleId_First(roleId, orderByComparator);
	}

	/**
	* Returns the first user group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role, or <code>null</code> if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole fetchByRoleId_First(
		long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByRoleId_First(roleId, orderByComparator);
	}

	/**
	* Returns the last user group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole findByRoleId_Last(
		long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRoleId_Last(roleId, orderByComparator);
	}

	/**
	* Returns the last user group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role, or <code>null</code> if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole fetchByRoleId_Last(
		long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByRoleId_Last(roleId, orderByComparator);
	}

	/**
	* Returns the user group roles before and after the current user group role in the ordered set where roleId = &#63;.
	*
	* @param userGroupRolePK the primary key of the current user group role
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole[] findByRoleId_PrevAndNext(
		com.liferay.portal.service.persistence.UserGroupRolePK userGroupRolePK,
		long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRoleId_PrevAndNext(userGroupRolePK, roleId,
			orderByComparator);
	}

	/**
	* Removes all the user group roles where roleId = &#63; from the database.
	*
	* @param roleId the role ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByRoleId(long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByRoleId(roleId);
	}

	/**
	* Returns the number of user group roles where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the number of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByRoleId(long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByRoleId(roleId);
	}

	/**
	* Returns all the user group roles where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @return the matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByU_G(
		long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_G(userId, groupId);
	}

	/**
	* Returns a range of all the user group roles where userId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByU_G(
		long userId, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_G(userId, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the user group roles where userId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByU_G(
		long userId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_G(userId, groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first user group role in the ordered set where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole findByU_G_First(
		long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_G_First(userId, groupId, orderByComparator);
	}

	/**
	* Returns the first user group role in the ordered set where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role, or <code>null</code> if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole fetchByU_G_First(
		long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_G_First(userId, groupId, orderByComparator);
	}

	/**
	* Returns the last user group role in the ordered set where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole findByU_G_Last(
		long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_G_Last(userId, groupId, orderByComparator);
	}

	/**
	* Returns the last user group role in the ordered set where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role, or <code>null</code> if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole fetchByU_G_Last(
		long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_G_Last(userId, groupId, orderByComparator);
	}

	/**
	* Returns the user group roles before and after the current user group role in the ordered set where userId = &#63; and groupId = &#63;.
	*
	* @param userGroupRolePK the primary key of the current user group role
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole[] findByU_G_PrevAndNext(
		com.liferay.portal.service.persistence.UserGroupRolePK userGroupRolePK,
		long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_G_PrevAndNext(userGroupRolePK, userId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the user group roles where userId = &#63; and groupId = &#63; from the database.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_G(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_G(userId, groupId);
	}

	/**
	* Returns the number of user group roles where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @return the number of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_G(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_G(userId, groupId);
	}

	/**
	* Returns all the user group roles where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @return the matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByG_R(
		long groupId, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_R(groupId, roleId);
	}

	/**
	* Returns a range of all the user group roles where groupId = &#63; and roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByG_R(
		long groupId, long roleId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_R(groupId, roleId, start, end);
	}

	/**
	* Returns an ordered range of all the user group roles where groupId = &#63; and roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByG_R(
		long groupId, long roleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R(groupId, roleId, start, end, orderByComparator);
	}

	/**
	* Returns the first user group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole findByG_R_First(
		long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_First(groupId, roleId, orderByComparator);
	}

	/**
	* Returns the first user group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role, or <code>null</code> if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole fetchByG_R_First(
		long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_R_First(groupId, roleId, orderByComparator);
	}

	/**
	* Returns the last user group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole findByG_R_Last(
		long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_Last(groupId, roleId, orderByComparator);
	}

	/**
	* Returns the last user group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role, or <code>null</code> if a matching user group role could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole fetchByG_R_Last(
		long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_R_Last(groupId, roleId, orderByComparator);
	}

	/**
	* Returns the user group roles before and after the current user group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param userGroupRolePK the primary key of the current user group role
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole[] findByG_R_PrevAndNext(
		com.liferay.portal.service.persistence.UserGroupRolePK userGroupRolePK,
		long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_PrevAndNext(userGroupRolePK, groupId, roleId,
			orderByComparator);
	}

	/**
	* Removes all the user group roles where groupId = &#63; and roleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_R(long groupId, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_R(groupId, roleId);
	}

	/**
	* Returns the number of user group roles where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @return the number of matching user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_R(long groupId, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_R(groupId, roleId);
	}

	/**
	* Caches the user group role in the entity cache if it is enabled.
	*
	* @param userGroupRole the user group role
	*/
	public static void cacheResult(
		com.liferay.portal.model.UserGroupRole userGroupRole) {
		getPersistence().cacheResult(userGroupRole);
	}

	/**
	* Caches the user group roles in the entity cache if it is enabled.
	*
	* @param userGroupRoles the user group roles
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.UserGroupRole> userGroupRoles) {
		getPersistence().cacheResult(userGroupRoles);
	}

	/**
	* Creates a new user group role with the primary key. Does not add the user group role to the database.
	*
	* @param userGroupRolePK the primary key for the new user group role
	* @return the new user group role
	*/
	public static com.liferay.portal.model.UserGroupRole create(
		com.liferay.portal.service.persistence.UserGroupRolePK userGroupRolePK) {
		return getPersistence().create(userGroupRolePK);
	}

	/**
	* Removes the user group role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userGroupRolePK the primary key of the user group role
	* @return the user group role that was removed
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole remove(
		com.liferay.portal.service.persistence.UserGroupRolePK userGroupRolePK)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(userGroupRolePK);
	}

	public static com.liferay.portal.model.UserGroupRole updateImpl(
		com.liferay.portal.model.UserGroupRole userGroupRole)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(userGroupRole);
	}

	/**
	* Returns the user group role with the primary key or throws a {@link com.liferay.portal.NoSuchUserGroupRoleException} if it could not be found.
	*
	* @param userGroupRolePK the primary key of the user group role
	* @return the user group role
	* @throws com.liferay.portal.NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole findByPrimaryKey(
		com.liferay.portal.service.persistence.UserGroupRolePK userGroupRolePK)
		throws com.liferay.portal.NoSuchUserGroupRoleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(userGroupRolePK);
	}

	/**
	* Returns the user group role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userGroupRolePK the primary key of the user group role
	* @return the user group role, or <code>null</code> if a user group role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroupRole fetchByPrimaryKey(
		com.liferay.portal.service.persistence.UserGroupRolePK userGroupRolePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(userGroupRolePK);
	}

	/**
	* Returns all the user group roles.
	*
	* @return the user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user group roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user group roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the user group roles from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user group roles.
	*
	* @return the number of user group roles
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static UserGroupRolePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (UserGroupRolePersistence)PortalBeanLocatorUtil.locate(UserGroupRolePersistence.class.getName());

			ReferenceRegistry.registerReference(UserGroupRoleUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(UserGroupRolePersistence persistence) {
	}

	private static UserGroupRolePersistence _persistence;
}