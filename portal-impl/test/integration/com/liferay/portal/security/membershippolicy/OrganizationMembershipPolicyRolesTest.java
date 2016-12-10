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

package com.liferay.portal.security.membershippolicy;

import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.security.membershippolicy.util.MembershipPolicyTestUtil;
import com.liferay.portal.service.RoleServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.UserGroupRolePK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Roberto Díaz
 */
public class OrganizationMembershipPolicyRolesTest
	extends BaseOrganizationMembershipPolicyTestCase {

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUsersToForbiddenRole() throws Exception {
		long[] forbiddenRoleIds = addForbiddenRoles();

		UserGroupRoleServiceUtil.addUserGroupRoles(
			addUsers(), organization.getGroupId(), forbiddenRoleIds[0]);
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUserToForbiddenRole() throws Exception {
		List<UserGroupRole> userGroupRoles = new ArrayList<UserGroupRole>();

		long[] userIds = addUsers();
		long[] forbiddenRoleIds = addForbiddenRoles();

		UserGroupRolePK userGroupRolePK = new UserGroupRolePK(
			userIds[0], organization.getGroupId(), forbiddenRoleIds[0]);

		UserGroupRole userGroupRole =
			UserGroupRoleLocalServiceUtil.createUserGroupRole(userGroupRolePK);

		userGroupRoles.add(userGroupRole);

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, null, null, null, null, userGroupRoles);
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUserToForbiddenRoles() throws Exception {
		long[] userIds = addUsers();

		UserGroupRoleServiceUtil.addUserGroupRoles(
			userIds[0], organization.getGroupId(), addForbiddenRoles());
	}

	@Test
	public void testPropagateWhenAssigningRolesToUser() throws Exception {
		List<UserGroupRole> userGroupRoles = new ArrayList<UserGroupRole>();

		long[] userIds = addUsers();
		long[] standardRoleIds = addStandardRoles();

		UserGroupRolePK userGroupRolePK = new UserGroupRolePK(
			userIds[0], organization.getGroupId(), standardRoleIds[0]);

		UserGroupRole userGroupRole =
			UserGroupRoleLocalServiceUtil.createUserGroupRole(userGroupRolePK);

		userGroupRoles.add(userGroupRole);

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, null, null, null, null, userGroupRoles);

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenAssigningUsersToRole() throws Exception {
		long[] standardRoleIds = addStandardRoles();

		UserGroupRoleServiceUtil.addUserGroupRoles(
			addUsers(), organization.getGroupId(), standardRoleIds[0]);

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenAssigningUserToRoles() throws Exception {
		long[] userIds = addUsers();

		UserGroupRoleServiceUtil.addUserGroupRoles(
			userIds[0], organization.getGroupId(), addStandardRoles());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenUnassigningRolesFromUser() throws Exception {
		long[] userIds = addUsers();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		UserGroupRoleServiceUtil.addUserGroupRoles(
			user.getUserId(), organization.getGroupId(), addStandardRoles());

		MembershipPolicyTestUtil.updateUser(
			user, null, null, null, null,
			Collections.<UserGroupRole>emptyList());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenUnassigningUserFromRoles() throws Exception {
		long[] userIds = addUsers();

		UserGroupRoleServiceUtil.deleteUserGroupRoles(
			userIds[0], organization.getGroupId(), addStandardRoles());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testUnassignRequiredRolesFromUser() throws Exception {
		long[] userIds = addUsers();

		UserGroupRoleServiceUtil.addUserGroupRoles(
			userIds[0], organization.getGroupId(), addRequiredRoles());

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		List<UserGroupRole> initialUserGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRoles(user.getUserId());

		List<UserGroupRole> emptyNonAbstractList =
			new ArrayList<UserGroupRole>();

		MembershipPolicyTestUtil.updateUser(
			user, null, null, null, null, emptyNonAbstractList);

		List<UserGroupRole> currentUserGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRoles(user.getUserId());

		Assert.assertEquals(
			initialUserGroupRoles.size(), currentUserGroupRoles.size());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testUnassignUserFromRequiredRoles() throws Exception {
		long[] userIds = addUsers();

		UserGroupRoleServiceUtil.deleteUserGroupRoles(
			userIds[0], organization.getGroupId(), addRequiredRoles());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testUnassignUsersFromRequiredRole() throws Exception {
		long[] requiredRoleIds = addRequiredRoles();

		UserGroupRoleServiceUtil.deleteUserGroupRoles(
			addUsers(), organization.getGroupId(), requiredRoleIds[0]);
	}

	@Test
	public void testUnassignUsersFromRole() throws Exception {
		long[] standardRoleIds = addStandardRoles();

		UserGroupRoleServiceUtil.deleteUserGroupRoles(
			addUsers(), organization.getGroupId(), standardRoleIds[0]);

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testVerifyWhenAddingRole() throws Exception {
		MembershipPolicyTestUtil.addRole(RoleConstants.TYPE_ORGANIZATION);

		Assert.assertTrue(isVerify());
	}

	@Test
	public void testVerifyWhenUpdatingRole() throws Exception {
		Role role = MembershipPolicyTestUtil.addRole(
			RoleConstants.TYPE_ORGANIZATION);

		RoleServiceUtil.updateRole(
			role.getRoleId(), ServiceTestUtil.randomString(),
			role.getTitleMap(), role.getDescriptionMap(), role.getSubtype(),
			new ServiceContext());

		Assert.assertTrue(isVerify());
	}

}