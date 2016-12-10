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

package com.liferay.portal.security.permission;

import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.UserConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author László Csontos
 */
public class UserPermissionCheckerBagImpl implements UserPermissionCheckerBag {

	public UserPermissionCheckerBagImpl() {
		this(UserConstants.USER_ID_DEFAULT);
	}

	public UserPermissionCheckerBagImpl(long userId) {
		_userGroups = Collections.<Group>emptyList();
		_userId = userId;
		_userOrgs = Collections.<Organization>emptyList();
		_userOrgGroups = Collections.<Group>emptyList();
		_userUserGroupGroups = Collections.<Group>emptyList();
	}

	public UserPermissionCheckerBagImpl(
		long userId, List<Group> userGroups, List<Organization> userOrgs,
		List<Group> userOrgGroups, List<Group> userUserGroupGroups) {

		_userGroups = userGroups;
		_userId = userId;
		_userOrgs = userOrgs;
		_userOrgGroups = userOrgGroups;
		_userUserGroupGroups = userUserGroupGroups;
	}

	public UserPermissionCheckerBagImpl(
		UserPermissionCheckerBag userPermissionCheckerBag) {

		this(
			userPermissionCheckerBag.getUserId(),
			userPermissionCheckerBag.getUserGroups(),
			userPermissionCheckerBag.getUserOrgs(),
			userPermissionCheckerBag.getUserOrgGroups(),
			userPermissionCheckerBag.getUserUserGroupGroups());
	}

	@Override
	public List<Group> getGroups() {
		if (_groups == null) {
			Collection<Group>[] groupsArray = new Collection[3];

			int groupsSize = 0;

			if (!_userGroups.isEmpty()) {
				groupsArray[0] = _userGroups;
				groupsSize += _userGroups.size();
			}

			if (!_userOrgGroups.isEmpty()) {
				groupsArray[1] = _userOrgGroups;
				groupsSize += _userOrgGroups.size();
			}

			if (!_userUserGroupGroups.isEmpty()) {
				groupsArray[2] = _userUserGroupGroups;
				groupsSize += _userUserGroupGroups.size();
			}

			_groups = new ArrayList<Group>(groupsSize);

			for (Collection<Group> groupsItem : groupsArray) {
				if (groupsItem != null) {
					_groups.addAll(groupsItem);
				}
			}
		}

		return _groups;
	}

	@Override
	public List<Group> getUserGroups() {
		return _userGroups;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public List<Group> getUserOrgGroups() {
		return _userOrgGroups;
	}

	@Override
	public List<Organization> getUserOrgs() {
		return _userOrgs;
	}

	@Override
	public List<Group> getUserUserGroupGroups() {
		return _userUserGroupGroups;
	}

	private List<Group> _groups;
	private final List<Group> _userGroups;
	private final long _userId;
	private final List<Group> _userOrgGroups;
	private final List<Organization> _userOrgs;
	private final List<Group> _userUserGroupGroups;

}