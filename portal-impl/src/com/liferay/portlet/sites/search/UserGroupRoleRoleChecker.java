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

package com.liferay.portlet.sites.search;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.membershippolicy.SiteMembershipPolicyUtil;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;

import javax.portlet.RenderResponse;

/**
 * @author Jorge Ferrer
 */
public class UserGroupRoleRoleChecker extends RowChecker {

	public UserGroupRoleRoleChecker(
		RenderResponse renderResponse, User user, Group group) {

		super(renderResponse);

		_user = user;
		_group = group;
	}

	@Override
	public boolean isChecked(Object obj) {
		Role role = (Role)obj;

		try {
			return UserGroupRoleLocalServiceUtil.hasUserGroupRole(
				_user.getUserId(), _group.getGroupId(), role.getRoleId());
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	@Override
	public boolean isDisabled(Object obj) {
		Role role = (Role)obj;

		try {
			PermissionChecker permissionChecker =
				PermissionThreadLocal.getPermissionChecker();

			if (isChecked(role)) {
				if (SiteMembershipPolicyUtil.isRoleProtected(
						permissionChecker, _user.getUserId(),
						_group.getGroupId(), role.getRoleId()) ||
					SiteMembershipPolicyUtil.isRoleRequired(
						_user.getUserId(), _group.getGroupId(),
						role.getRoleId())) {

					return true;
				}
			}
			else {
				if (!SiteMembershipPolicyUtil.isRoleAllowed(
						_user.getUserId(), _group.getGroupId(),
						role.getRoleId())) {

					return true;
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return super.isDisabled(obj);
	}

	private static Log _log = LogFactoryUtil.getLog(
		UserGroupRoleRoleChecker.class);

	private Group _group;
	private User _user;

}