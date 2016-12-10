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

package com.liferay.portlet.asset.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;

/**
 * @author Eduardo Lundgren
 */
public class AssetTagPermission {

	public static void check(
			PermissionChecker permissionChecker, AssetTag tag, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, tag, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long tagId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, tagId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, AssetTag tag, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				tag.getCompanyId(), AssetTag.class.getName(), tag.getTagId(),
				tag.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			tag.getGroupId(), AssetTag.class.getName(), tag.getTagId(),
			actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long tagId, String actionId)
		throws PortalException, SystemException {

		AssetTag tag = AssetTagLocalServiceUtil.getTag(tagId);

		return contains(permissionChecker, tag, actionId);
	}

}