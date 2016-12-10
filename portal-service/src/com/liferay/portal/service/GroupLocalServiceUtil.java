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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for Group. This utility wraps
 * {@link com.liferay.portal.service.impl.GroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see GroupLocalService
 * @see com.liferay.portal.service.base.GroupLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.GroupLocalServiceImpl
 * @generated
 */
@ProviderType
public class GroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.GroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the group to the database. Also notifies the appropriate model listeners.
	*
	* @param group the group
	* @return the group that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group addGroup(
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addGroup(group);
	}

	/**
	* Creates a new group with the primary key. Does not add the group to the database.
	*
	* @param groupId the primary key for the new group
	* @return the new group
	*/
	public static com.liferay.portal.model.Group createGroup(long groupId) {
		return getService().createGroup(groupId);
	}

	/**
	* Deletes the group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param groupId the primary key of the group
	* @return the group that was removed
	* @throws PortalException if a group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group deleteGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteGroup(groupId);
	}

	/**
	* Deletes the group from the database. Also notifies the appropriate model listeners.
	*
	* @param group the group
	* @return the group that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group deleteGroup(
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteGroup(group);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.GroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.GroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.model.Group fetchGroup(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchGroup(groupId);
	}

	/**
	* Returns the group with the matching UUID and company.
	*
	* @param uuid the group's UUID
	* @param companyId the primary key of the company
	* @return the matching group, or <code>null</code> if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group fetchGroupByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchGroupByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the group with the primary key.
	*
	* @param groupId the primary key of the group
	* @return the group
	* @throws PortalException if a group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroup(groupId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the group with the matching UUID and company.
	*
	* @param uuid the group's UUID
	* @param companyId the primary key of the company
	* @return the matching group
	* @throws PortalException if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getGroupByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of all the groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.GroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of groups
	* @param end the upper bound of the range of groups (not inclusive)
	* @return the range of groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getGroups(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroups(start, end);
	}

	/**
	* Returns the number of groups.
	*
	* @return the number of groups
	* @throws SystemException if a system exception occurred
	*/
	public static int getGroupsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupsCount();
	}

	/**
	* Updates the group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param group the group
	* @return the group that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group updateGroup(
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateGroup(group);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOrganizationGroup(long organizationId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addOrganizationGroup(organizationId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOrganizationGroup(long organizationId,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addOrganizationGroup(organizationId, group);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOrganizationGroups(long organizationId,
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addOrganizationGroups(organizationId, groupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOrganizationGroups(long organizationId,
		java.util.List<com.liferay.portal.model.Group> Groups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addOrganizationGroups(organizationId, Groups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearOrganizationGroups(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearOrganizationGroups(organizationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOrganizationGroup(long organizationId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteOrganizationGroup(organizationId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOrganizationGroup(long organizationId,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteOrganizationGroup(organizationId, group);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOrganizationGroups(long organizationId,
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteOrganizationGroups(organizationId, groupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOrganizationGroups(long organizationId,
		java.util.List<com.liferay.portal.model.Group> Groups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteOrganizationGroups(organizationId, Groups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getOrganizationGroups(
		long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrganizationGroups(organizationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getOrganizationGroups(
		long organizationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrganizationGroups(organizationId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getOrganizationGroups(
		long organizationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOrganizationGroups(organizationId, start, end,
			orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getOrganizationGroupsCount(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrganizationGroupsCount(organizationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasOrganizationGroup(long organizationId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasOrganizationGroup(organizationId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasOrganizationGroups(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasOrganizationGroups(organizationId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setOrganizationGroups(long organizationId,
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().setOrganizationGroups(organizationId, groupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addRoleGroup(long roleId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addRoleGroup(roleId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addRoleGroup(long roleId,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addRoleGroup(roleId, group);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addRoleGroups(long roleId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addRoleGroups(roleId, groupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addRoleGroups(long roleId,
		java.util.List<com.liferay.portal.model.Group> Groups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addRoleGroups(roleId, Groups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearRoleGroups(long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearRoleGroups(roleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteRoleGroup(long roleId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteRoleGroup(roleId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteRoleGroup(long roleId,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteRoleGroup(roleId, group);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteRoleGroups(long roleId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteRoleGroups(roleId, groupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteRoleGroups(long roleId,
		java.util.List<com.liferay.portal.model.Group> Groups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteRoleGroups(roleId, Groups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getRoleGroups(
		long roleId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getRoleGroups(roleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getRoleGroups(
		long roleId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getRoleGroups(roleId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getRoleGroups(
		long roleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getRoleGroups(roleId, start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getRoleGroupsCount(long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getRoleGroupsCount(roleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasRoleGroup(long roleId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasRoleGroup(roleId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasRoleGroups(long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasRoleGroups(roleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setRoleGroups(long roleId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().setRoleGroups(roleId, groupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroupGroup(long userGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addUserGroupGroup(userGroupId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroupGroup(long userGroupId,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addUserGroupGroup(userGroupId, group);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroupGroups(long userGroupId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addUserGroupGroups(userGroupId, groupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroupGroups(long userGroupId,
		java.util.List<com.liferay.portal.model.Group> Groups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addUserGroupGroups(userGroupId, Groups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearUserGroupGroups(long userGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearUserGroupGroups(userGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserGroupGroup(long userGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroupGroup(userGroupId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserGroupGroup(long userGroupId,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroupGroup(userGroupId, group);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserGroupGroups(long userGroupId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroupGroups(userGroupId, groupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserGroupGroups(long userGroupId,
		java.util.List<com.liferay.portal.model.Group> Groups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroupGroups(userGroupId, Groups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getUserGroupGroups(
		long userGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroupGroups(userGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getUserGroupGroups(
		long userGroupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroupGroups(userGroupId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getUserGroupGroups(
		long userGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getUserGroupGroups(userGroupId, start, end,
			orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getUserGroupGroupsCount(long userGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroupGroupsCount(userGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasUserGroupGroup(long userGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasUserGroupGroup(userGroupId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasUserGroupGroups(long userGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasUserGroupGroups(userGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setUserGroupGroups(long userGroupId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().setUserGroupGroups(userGroupId, groupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroup(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addUserGroup(userId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroup(long userId,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addUserGroup(userId, group);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroups(long userId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addUserGroups(userId, groupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroups(long userId,
		java.util.List<com.liferay.portal.model.Group> Groups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addUserGroups(userId, Groups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearUserGroups(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearUserGroups(userId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserGroup(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroup(userId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserGroup(long userId,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroup(userId, group);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserGroups(long userId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroups(userId, groupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserGroups(long userId,
		java.util.List<com.liferay.portal.model.Group> Groups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroups(userId, Groups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getUserGroups(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroups(userId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getUserGroups(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroups(userId, start, end);
	}

	/**
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getUserGroups(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroups(userId, start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getUserGroupsCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroupsCount(userId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasUserGroup(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasUserGroup(userId, groupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasUserGroups(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasUserGroups(userId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setUserGroups(long userId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().setUserGroups(userId, groupIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	/**
	* Adds a group.
	*
	* @param userId the primary key of the group's creator/owner
	* @param parentGroupId the primary key of the parent group
	* @param className the entity's class name
	* @param classPK the primary key of the entity's instance
	* @param liveGroupId the primary key of the live group
	* @param name the entity's name
	* @param description the group's description (optionally
	<code>null</code>)
	* @param type the group's type. For more information see {@link
	com.liferay.portal.model.GroupConstants}
	* @param friendlyURL the group's friendlyURL (optionally
	<code>null</code>)
	* @param site whether the group is to be associated with a main site
	* @param active whether the group is active
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set asset category IDs and asset tag
	names for the group, and whether the group is for staging.
	* @return the group
	* @throws PortalException if a creator could not be found, if the group's
	information was invalid, if a layout could not be found, or if a
	valid friendly URL could not be created for the group
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group addGroup(long userId,
		long parentGroupId, java.lang.String className, long classPK,
		long liveGroupId, java.lang.String name, java.lang.String description,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean site, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addGroup(userId, parentGroupId, className, classPK,
			liveGroupId, name, description, type, manualMembership,
			membershipRestriction, friendlyURL, site, active, serviceContext);
	}

	/**
	* Adds the group using the default live group.
	*
	* @param userId the primary key of the group's creator/owner
	* @param parentGroupId the primary key of the parent group
	* @param className the entity's class name
	* @param classPK the primary key of the entity's instance
	* @param name the entity's name
	* @param description the group's description (optionally
	<code>null</code>)
	* @param type the group's type. For more information see {@link
	com.liferay.portal.model.GroupConstants}
	* @param friendlyURL the group's friendlyURL
	* @param site whether the group is to be associated with a main site
	* @param active whether the group is active
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set asset category IDs and asset tag
	names for the group, and whether the group is for staging.
	* @return the group
	* @throws PortalException if a creator could not be found, if the
	group's information was invalid, if a layout could not be
	found, or if a valid friendly URL could not be created for
	the group
	* @throws SystemException if a system exception occurred
	* @deprecated As of 6.2.0, replaced by {@link #addGroup(long, long, String,
	long, long, String, String, int, boolean, int, String,
	boolean, boolean, ServiceContext)}
	*/
	public static com.liferay.portal.model.Group addGroup(long userId,
		long parentGroupId, java.lang.String className, long classPK,
		java.lang.String name, java.lang.String description, int type,
		java.lang.String friendlyURL, boolean site, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addGroup(userId, parentGroupId, className, classPK, name,
			description, type, friendlyURL, site, active, serviceContext);
	}

	/**
	* Adds a group.
	*
	* @param userId the primary key of the group's creator/owner
	* @param className the entity's class name
	* @param classPK the primary key of the entity's instance
	* @param liveGroupId the primary key of the live group
	* @param name the entity's name
	* @param description the group's description (optionally
	<code>null</code>)
	* @param type the group's type. For more information see {@link
	com.liferay.portal.model.GroupConstants}
	* @param friendlyURL the group's friendlyURL (optionally
	<code>null</code>)
	* @param site whether the group is to be associated with a main site
	* @param active whether the group is active
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set asset category IDs and asset tag
	names for the group, and whether the group is for staging.
	* @return the group
	* @throws PortalException if a creator could not be found, if the
	group's information was invalid, if a layout could not be
	found, or if a valid friendly URL could not be created for
	the group
	* @throws SystemException if a system exception occurred
	* @deprecated As of 6.2.0, replaced by {@link #addGroup(long, long, String,
	long, long, String, String, int, boolean, int, String,
	boolean, boolean, ServiceContext)}
	*/
	public static com.liferay.portal.model.Group addGroup(long userId,
		java.lang.String className, long classPK, long liveGroupId,
		java.lang.String name, java.lang.String description, int type,
		java.lang.String friendlyURL, boolean site, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addGroup(userId, className, classPK, liveGroupId, name,
			description, type, friendlyURL, site, active, serviceContext);
	}

	/**
	* Adds the group using the default live group.
	*
	* @param userId the primary key of the group's creator/owner
	* @param className the entity's class name
	* @param classPK the primary key of the entity's instance
	* @param name the entity's name
	* @param description the group's description (optionally
	<code>null</code>)
	* @param type the group's type. For more information see {@link
	com.liferay.portal.model.GroupConstants}
	* @param friendlyURL the group's friendlyURL
	* @param site whether the group is to be associated with a main site
	* @param active whether the group is active
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set asset category IDs and asset tag
	names for the group, and whether the group is for staging.
	* @return the group
	* @throws PortalException if a creator could not be found, if the
	group's information was invalid, if a layout could not be
	found, or if a valid friendly URL could not be created for
	the group
	* @throws SystemException if a system exception occurred
	* @deprecated As of 6.2.0, replaced by {@link #addGroup(long, long, String,
	long, long, String, String, int, boolean, int, String,
	boolean, boolean, ServiceContext)}
	*/
	public static com.liferay.portal.model.Group addGroup(long userId,
		java.lang.String className, long classPK, java.lang.String name,
		java.lang.String description, int type, java.lang.String friendlyURL,
		boolean site, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addGroup(userId, className, classPK, name, description,
			type, friendlyURL, site, active, serviceContext);
	}

	/**
	* Adds a company group if it does not exist. This method is typically used
	* when a virtual host is added.
	*
	* @param companyId the primary key of the company
	* @throws PortalException if a default user for the company could not be
	found, if the group's information was invalid, if a layout could
	not be found, or if a valid friendly URL could not be created for
	the group
	* @throws SystemException if a system exception occurred
	*/
	public static void checkCompanyGroup(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().checkCompanyGroup(companyId);
	}

	/**
	* Creates systems groups and other related data needed by the system on the
	* very first startup. Also takes care of creating the Control Panel groups
	* and layouts.
	*
	* @param companyId the primary key of the company
	* @throws PortalException if a new system group could not be created
	* @throws SystemException if a system exception occurred
	*/
	public static void checkSystemGroups(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().checkSystemGroups(companyId);
	}

	public static void disableStaging(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().disableStaging(groupId);
	}

	public static void enableStaging(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().enableStaging(groupId);
	}

	/**
	* Returns the group with the matching friendly URL.
	*
	* @param companyId the primary key of the company
	* @param friendlyURL the friendly URL
	* @return the group with the friendly URL, or <code>null</code> if a
	matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group fetchFriendlyURLGroup(
		long companyId, java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchFriendlyURLGroup(companyId, friendlyURL);
	}

	/**
	* Returns the group with the matching group name by first searching the
	* system groups and then using the finder cache.
	*
	* @param companyId the primary key of the company
	* @param name the group's name
	* @return the group with the name and associated company, or
	<code>null</code> if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group fetchGroup(long companyId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchGroup(companyId, name);
	}

	/**
	* Returns the company group.
	*
	* @param companyId the primary key of the company
	* @return the group associated with the company
	* @throws PortalException if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getCompanyGroup(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyGroup(companyId);
	}

	/**
	* Returns a range of all the groups associated with the company.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the range of groups associated with the company
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getCompanyGroups(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyGroups(companyId, start, end);
	}

	/**
	* Returns the number of groups associated with the company.
	*
	* @param companyId the primary key of the company
	* @return the number of groups associated with the company
	* @throws SystemException if a system exception occurred
	*/
	public static int getCompanyGroupsCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyGroupsCount(companyId);
	}

	/**
	* Returns the group with the matching friendly URL.
	*
	* @param companyId the primary key of the company
	* @param friendlyURL the group's friendlyURL
	* @return the group with the friendly URL
	* @throws PortalException if a matching group could not be found, or if the
	friendly URL was invalid
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getFriendlyURLGroup(
		long companyId, java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFriendlyURLGroup(companyId, friendlyURL);
	}

	/**
	* Returns the group with the matching group name.
	*
	* @param companyId the primary key of the company
	* @param name the group's name
	* @return the group with the name
	* @throws PortalException if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getGroup(long companyId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroup(companyId, name);
	}

	public static java.lang.String getGroupDescriptiveName(
		com.liferay.portal.model.Group group, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupDescriptiveName(group, locale);
	}

	public static java.lang.String getGroupDescriptiveName(long groupId,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupDescriptiveName(groupId, locale);
	}

	/**
	* Returns all the groups that are direct children of the parent group.
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param site whether the group is to be associated with a main site
	* @return the matching groups, or <code>null</code> if no matches were
	found
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getGroups(
		long companyId, long parentGroupId, boolean site)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroups(companyId, parentGroupId, site);
	}

	/**
	* Returns all the groups that are direct children of the parent group with
	* the matching className.
	*
	* @param companyId the primary key of the company
	* @param className the class name of the group
	* @param parentGroupId the primary key of the parent group
	* @return the matching groups, or <code>null</code> if no matches were
	found
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getGroups(
		long companyId, java.lang.String className, long parentGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroups(companyId, className, parentGroupId);
	}

	/**
	* Returns a range of all the groups that are direct children of the parent
	* group with the matching className.
	*
	* @param companyId the primary key of the company
	* @param className the class name of the group
	* @param parentGroupId the primary key of the parent group
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getGroups(
		long companyId, java.lang.String className, long parentGroupId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroups(companyId, className, parentGroupId, start, end);
	}

	/**
	* Returns the groups with the matching primary keys.
	*
	* @param groupIds the primary keys of the groups
	* @return the groups with the primary keys
	* @throws PortalException if any one of the groups could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getGroups(
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroups(groupIds);
	}

	/**
	* Returns the number of groups that are direct children of the parent
	* group.
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param site whether the group is to be associated with a main site
	* @return the number of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static int getGroupsCount(long companyId, long parentGroupId,
		boolean site)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupsCount(companyId, parentGroupId, site);
	}

	/**
	* Returns the number of groups that are direct children of the parent group
	* with the matching className.
	*
	* @param companyId the primary key of the company
	* @param className the class name of the group
	* @param parentGroupId the primary key of the parent group
	* @return the number of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static int getGroupsCount(long companyId,
		java.lang.String className, long parentGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupsCount(companyId, className, parentGroupId);
	}

	/**
	* Returns the group associated with the layout.
	*
	* @param companyId the primary key of the company
	* @param plid the primary key of the layout
	* @return the group associated with the layout
	* @throws PortalException if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getLayoutGroup(
		long companyId, long plid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLayoutGroup(companyId, plid);
	}

	/**
	* Returns the group associated with the layout prototype.
	*
	* @param companyId the primary key of the company
	* @param layoutPrototypeId the primary key of the layout prototype
	* @return the group associated with the layout prototype
	* @throws PortalException if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getLayoutPrototypeGroup(
		long companyId, long layoutPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLayoutPrototypeGroup(companyId, layoutPrototypeId);
	}

	/**
	* Returns the group associated with the layout set prototype.
	*
	* @param companyId the primary key of the company
	* @param layoutSetPrototypeId the primary key of the layout set prototype
	* @return the group associated with the layout set prototype
	* @throws PortalException if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getLayoutSetPrototypeGroup(
		long companyId, long layoutSetPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLayoutSetPrototypeGroup(companyId, layoutSetPrototypeId);
	}

	/**
	* Returns a range of all groups that are children of the parent group and
	* that have at least one layout.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param site whether the group is to be associated with a main site
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the range of matching groups
	* @throws SystemException if a system exception occurred
	* @deprecated As of 6.2.0, replaced by {@link #getLayoutsGroups(long, long,
	boolean, int, int, OrderByComparator)}
	*/
	public static java.util.List<com.liferay.portal.model.Group> getLayoutsGroups(
		long companyId, long parentGroupId, boolean site, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLayoutsGroups(companyId, parentGroupId, site, start, end);
	}

	/**
	* Returns a range of all groups that are children of the parent group and
	* that have at least one layout.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param site whether the group is to be associated with a main site
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the range of matching groups ordered by comparator
	<code>obc</code>
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getLayoutsGroups(
		long companyId, long parentGroupId, boolean site, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLayoutsGroups(companyId, parentGroupId, site, start,
			end, obc);
	}

	/**
	* Returns the number of groups that are children or the parent group and
	* that have at least one layout
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param site whether the group is to be associated with a main site
	* @return the number of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static int getLayoutsGroupsCount(long companyId, long parentGroupId,
		boolean site)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLayoutsGroupsCount(companyId, parentGroupId, site);
	}

	/**
	* Returns all live groups.
	*
	* @return all live groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getLiveGroups()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLiveGroups();
	}

	/**
	* Returns a range of all non-system groups of a specified type (className)
	* that have no layouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param className the entity's class name
	* @param privateLayout whether to include groups with private layout sets
	or non-private layout sets
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the range of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getNoLayoutsGroups(
		java.lang.String className, boolean privateLayout, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getNoLayoutsGroups(className, privateLayout, start, end);
	}

	/**
	* Returns all non-system groups having <code>null</code> or empty friendly
	* URLs.
	*
	* @return the non-system groups having <code>null</code> or empty friendly
	URLs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getNullFriendlyURLGroups()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getNullFriendlyURLGroups();
	}

	/**
	* Returns the specified organization group.
	*
	* @param companyId the primary key of the company
	* @param organizationId the primary key of the organization
	* @return the group associated with the organization
	* @throws PortalException if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getOrganizationGroup(
		long companyId, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrganizationGroup(companyId, organizationId);
	}

	/**
	* Returns the specified organization groups.
	*
	* @param organizations the organizations
	* @return the groups associated with the organizations
	*/
	public static java.util.List<com.liferay.portal.model.Group> getOrganizationsGroups(
		java.util.List<com.liferay.portal.model.Organization> organizations) {
		return getService().getOrganizationsGroups(organizations);
	}

	/**
	* Returns all the groups related to the organizations.
	*
	* @param organizations the organizations
	* @return the groups related to the organizations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getOrganizationsRelatedGroups(
		java.util.List<com.liferay.portal.model.Organization> organizations)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrganizationsRelatedGroups(organizations);
	}

	/**
	* Returns the group followed by all its parent groups ordered by closest
	* ancestor.
	*
	* @param groupId the primary key of the group
	* @return the group followed by all its parent groups ordered by closest
	ancestor
	* @throws PortalException if a group with the primary key could not be
	found
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getParentGroups(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getParentGroups(groupId);
	}

	/**
	* Returns the staging group.
	*
	* @param liveGroupId the primary key of the live group
	* @return the staging group
	* @throws PortalException if a matching staging group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getStagingGroup(
		long liveGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getStagingGroup(liveGroupId);
	}

	/**
	* Returns the group associated with the user.
	*
	* @param companyId the primary key of the company
	* @param userId the primary key of the user
	* @return the group associated with the user
	* @throws PortalException if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getUserGroup(long companyId,
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroup(companyId, userId);
	}

	/**
	* Returns the specified "user group" group. That is, the group that
	* represents the {@link com.liferay.portal.model.UserGroup} entity.
	*
	* @param companyId the primary key of the company
	* @param userGroupId the primary key of the user group
	* @return the group associated with the user group
	* @throws PortalException if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group getUserGroupGroup(
		long companyId, long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroupGroup(companyId, userGroupId);
	}

	/**
	* Returns all the user's site groups and immediate organization groups,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
	*
	* @param userId the primary key of the user
	* @param inherit whether to include the user's inherited organization
	groups and user groups
	* @return the user's groups and immediate organization groups
	* @throws PortalException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getUserGroups(
		long userId, boolean inherit)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroups(userId, inherit);
	}

	/**
	* Returns an ordered range of all the user's site groups and immediate
	* organization groups, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param userId the primary key of the user
	* @param inherit whether to include the user's inherited organization
	groups and user groups
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the range of the user's groups and immediate organization groups
	ordered by name
	* @throws PortalException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getUserGroups(
		long userId, boolean inherit, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroups(userId, inherit, start, end);
	}

	/**
	* Returns the groups associated with the user groups.
	*
	* @param userGroups the user groups
	* @return the groups associated with the user groups
	* @throws PortalException if any one of the user group's group could not be
	found
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getUserGroupsGroups(
		java.util.List<com.liferay.portal.model.UserGroup> userGroups)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroupsGroups(userGroups);
	}

	/**
	* Returns all the groups related to the user groups.
	*
	* @param userGroups the user groups
	* @return the groups related to the user groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getUserGroupsRelatedGroups(
		java.util.List<com.liferay.portal.model.UserGroup> userGroups)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroupsRelatedGroups(userGroups);
	}

	/**
	* Returns the range of all groups associated with the user's organization
	* groups, including the ancestors of the organization groups, unless portal
	* property <code>organizations.membership.strict</code> is set to
	* <code>true</code>.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param userId the primary key of the user
	* @param start the lower bound of the range of groups to consider
	* @param end the upper bound of the range of groups to consider (not
	inclusive)
	* @return the range of groups associated with the user's organization
	groups
	* @throws PortalException if a user with the primary key could not be found
	or if another portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getUserOrganizationsGroups(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserOrganizationsGroups(userId, start, end);
	}

	public static com.liferay.portal.model.Group getUserPersonalSiteGroup(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserPersonalSiteGroup(companyId);
	}

	public static java.util.List<com.liferay.portal.model.Group> getUserSitesGroups(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserSitesGroups(userId);
	}

	public static java.util.List<com.liferay.portal.model.Group> getUserSitesGroups(
		long userId, boolean includeAdministrative)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserSitesGroups(userId, includeAdministrative);
	}

	/**
	* Returns <code>true</code> if the live group has a staging group.
	*
	* @param liveGroupId the primary key of the live group
	* @return <code>true</code> if the live group has a staging group;
	<code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasStagingGroup(long liveGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasStagingGroup(liveGroupId);
	}

	/**
	* Returns <code>true</code> if the user is immediately associated with the
	* group, or optionally if the user is associated with the group via the
	* user's organizations, inherited organizations, or user groups.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the group
	* @param inherit whether to include organization groups and user groups to
	which the user belongs in the determination
	* @return <code>true</code> if the user is associated with the group;
	<code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasUserGroup(long userId, long groupId,
		boolean inherit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasUserGroup(userId, groupId, inherit);
	}

	/**
	* Returns the group with the matching group name by first searching the
	* system groups and then using the finder cache.
	*
	* @param companyId the primary key of the company
	* @param name the group's name
	* @return the group with the name and associated company, or
	<code>null</code> if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group loadFetchGroup(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().loadFetchGroup(companyId, name);
	}

	/**
	* Returns the group with the matching group name.
	*
	* @param companyId the primary key of the company
	* @param name the group's name
	* @return the group with the name and associated company
	* @throws PortalException if a matching group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group loadGetGroup(long companyId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().loadGetGroup(companyId, name);
	}

	/**
	* Rebuilds the group tree.
	*
	* <p>
	* Only call this method if the tree has become stale through operations
	* other than normal CRUD. Under normal circumstances the tree is
	* automatically rebuilt whenever necessary.
	* </p>
	*
	* @param companyId the primary key of the group's company
	* @throws PortalException if a group with the primary key could not be
	found
	* @throws SystemException if a system exception occurred
	*/
	public static void rebuildTree(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().rebuildTree(companyId);
	}

	/**
	* Returns an ordered range of all the company's groups, optionally
	* including the user's inherited organization groups and user groups.
	* System and staged groups are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(companyId, params, start, end);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the keywords, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long parentGroupId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, parentGroupId, keywords, params, start,
			end);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the keywords, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long parentGroupId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, parentGroupId, keywords, params, start,
			end, obc);
	}

	/**
	* Returns an ordered range of all the site groups belonging to the parent
	* group and organization groups that match the name and description,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long parentGroupId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, parentGroupId, name, description, params,
			andOperator, start, end);
	}

	/**
	* Returns an ordered range of all the site groups belonging to the parent
	* group and organization groups that match the name and description,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long parentGroupId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, parentGroupId, name, description, params,
			andOperator, start, end, obc);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the class name IDs and keywords, optionally including the
	* user's inherited organization groups and user groups. System and staged
	* groups are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long[] classNameIds, long parentGroupId,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, classNameIds, parentGroupId, keywords,
			params, start, end);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the class name IDs and keywords, optionally including the
	* user's inherited organization groups and user groups. System and staged
	* groups are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long[] classNameIds, long parentGroupId,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, classNameIds, parentGroupId, keywords,
			params, start, end, obc);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the class name IDs, name, and description, optionally
	* including the user's inherited organization groups and user groups.
	* System and staged groups are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long[] classNameIds, long parentGroupId,
		java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, classNameIds, parentGroupId, name,
			description, params, andOperator, start, end);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the class name IDs, name, and description, optionally
	* including the user's inherited organization groups and user groups.
	* System and staged groups are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long[] classNameIds, long parentGroupId,
		java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, classNameIds, parentGroupId, name,
			description, params, andOperator, start, end, obc);
	}

	/**
	* Returns an ordered range of all the groups that match the class name IDs
	* and keywords, optionally including the user's inherited organization
	* groups and user groups. System and staged groups are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long[] classNameIds, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, classNameIds, keywords, params, start, end);
	}

	/**
	* Returns an ordered range of all the groups that match the class name IDs
	* and keywords, optionally including the user's inherited organization
	* groups and user groups. System and staged groups are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long[] classNameIds, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, classNameIds, keywords, params, start,
			end, obc);
	}

	/**
	* Returns an ordered range of all the groups that match the class name IDs,
	* name, and description, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long[] classNameIds, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, classNameIds, name, description, params,
			andOperator, start, end);
	}

	/**
	* Returns an ordered range of all the groups that match the class name IDs,
	* name, and description, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, long[] classNameIds, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, classNameIds, name, description, params,
			andOperator, start, end, obc);
	}

	/**
	* Returns an ordered range of all the groups that match the keywords,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(companyId, keywords, params, start, end);
	}

	/**
	* Returns an ordered range of all the groups that match the keywords,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(companyId, keywords, params, start, end, obc);
	}

	/**
	* Returns an ordered range of all the site groups and organization groups
	* that match the name and description, optionally including the user's
	* inherited organization groups and user groups. System and staged groups
	* are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, name, description, params, andOperator,
			start, end);
	}

	/**
	* Returns an ordered range of all the site groups and organization groups
	* that match the name and description, optionally including the user's
	* inherited organization groups and user groups. System and staged groups
	* are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> search(
		long companyId, java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, name, description, params, andOperator,
			start, end, obc);
	}

	/**
	* Returns the number of groups belonging to the parent group that match the
	* keywords, optionally including the user's inherited organization groups
	* and user groups. System and staged groups are not included.
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @return the number of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static int searchCount(long companyId, long parentGroupId,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, parentGroupId, keywords, params);
	}

	/**
	* Returns the number of groups belonging to the parent group and immediate
	* organization groups that match the name and description, optionally
	* including the user's inherited organization groups and user groups.
	* System and staged groups are not included.
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @return the number of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static int searchCount(long companyId, long parentGroupId,
		java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, parentGroupId, name, description,
			params, andOperator);
	}

	/**
	* Returns the number of groups belonging to the parent group that match the
	* class name IDs, and keywords, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @return the number of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static int searchCount(long companyId, long[] classNameIds,
		long parentGroupId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, classNameIds, parentGroupId,
			keywords, params);
	}

	/**
	* Returns the number of groups belonging to the parent group that match the
	* class name IDs, name, and description, optionally including the user's
	* inherited organization groups and user groups. System and staged groups
	* are not included.
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @return the number of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static int searchCount(long companyId, long[] classNameIds,
		long parentGroupId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, classNameIds, parentGroupId, name,
			description, params, andOperator);
	}

	/**
	* Returns the number of groups that match the class name IDs, and keywords,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @return the number of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static int searchCount(long companyId, long[] classNameIds,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, classNameIds, keywords, params);
	}

	/**
	* Returns the number of groups that match the class name IDs, name, and
	* description, optionally including the user's inherited organization
	* groups and user groups. System and staged groups are not included.
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @return the number of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static int searchCount(long companyId, long[] classNameIds,
		java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, classNameIds, name, description,
			params, andOperator);
	}

	/**
	* Returns the number of groups that match the keywords, optionally
	* including the user's inherited organization groups and user groups.
	* System and staged groups are not included.
	*
	* @param companyId the primary key of the company
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @return the number of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static int searchCount(long companyId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(companyId, keywords, params);
	}

	/**
	* Returns the number of groups and immediate organization groups that match
	* the name and description, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
	*
	* @param companyId the primary key of the company
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.service.persistence.GroupFinder}
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @return the number of matching groups
	* @throws SystemException if a system exception occurred
	*/
	public static int searchCount(long companyId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, name, description, params,
			andOperator);
	}

	/**
	* Removes the groups from the role.
	*
	* @param roleId the primary key of the role
	* @param groupIds the primary keys of the groups
	* @throws SystemException if a system exception occurred
	*/
	public static void unsetRoleGroups(long roleId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().unsetRoleGroups(roleId, groupIds);
	}

	/**
	* Removes the user from the groups.
	*
	* @param userId the primary key of the user
	* @param groupIds the primary keys of the groups
	* @throws SystemException if a system exception occurred
	*/
	public static void unsetUserGroups(long userId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().unsetUserGroups(userId, groupIds);
	}

	/**
	* Updates the group's asset replacing categories and tag names.
	*
	* @param userId the primary key of the user
	* @param group the group
	* @param assetCategoryIds the primary keys of the asset categories
	(optionally <code>null</code>)
	* @param assetTagNames the asset tag names (optionally <code>null</code>)
	* @throws PortalException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void updateAsset(long userId,
		com.liferay.portal.model.Group group, long[] assetCategoryIds,
		java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateAsset(userId, group, assetCategoryIds, assetTagNames);
	}

	/**
	* Updates the group's friendly URL.
	*
	* @param groupId the primary key of the group
	* @param friendlyURL the group's new friendlyURL (optionally
	<code>null</code>)
	* @return the group
	* @throws PortalException if a group with the primary key could not be
	found or if a valid friendly URL could not be created for the
	group
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group updateFriendlyURL(
		long groupId, java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFriendlyURL(groupId, friendlyURL);
	}

	/**
	* Updates the group.
	*
	* @param groupId the primary key of the group
	* @param parentGroupId the primary key of the parent group
	* @param name the group's new name
	* @param description the group's new description (optionally
	<code>null</code>)
	* @param type the group's new type. For more information see {@link
	com.liferay.portal.model.GroupConstants}
	* @param friendlyURL the group's new friendlyURL (optionally
	<code>null</code>)
	* @param active whether the group is active
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set asset category IDs and asset tag
	names for the group.
	* @return the group
	* @throws PortalException if a group with the primary key could not be
	found or if the friendly URL was invalid or could one not be
	created
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group updateGroup(long groupId,
		long parentGroupId, java.lang.String name,
		java.lang.String description, int type, boolean manualMembership,
		int membershipRestriction, java.lang.String friendlyURL,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateGroup(groupId, parentGroupId, name, description,
			type, manualMembership, membershipRestriction, friendlyURL, active,
			serviceContext);
	}

	/**
	* Updates the group's type settings.
	*
	* @param groupId the primary key of the group
	* @param typeSettings the group's new type settings (optionally
	<code>null</code>)
	* @return the group
	* @throws PortalException if a group with the primary key could not be
	found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group updateGroup(long groupId,
		java.lang.String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateGroup(groupId, typeSettings);
	}

	/**
	* Associates the group with a main site if the group is an organization.
	*
	* @param groupId the primary key of the group
	* @param site whether the group is to be associated with a main site
	* @return the group
	* @throws PortalException if a group with the primary key could not be
	found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Group updateSite(long groupId,
		boolean site)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSite(groupId, site);
	}

	public static GroupLocalService getService() {
		if (_service == null) {
			_service = (GroupLocalService)PortalBeanLocatorUtil.locate(GroupLocalService.class.getName());

			ReferenceRegistry.registerReference(GroupLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(GroupLocalService service) {
	}

	private static GroupLocalService _service;
}