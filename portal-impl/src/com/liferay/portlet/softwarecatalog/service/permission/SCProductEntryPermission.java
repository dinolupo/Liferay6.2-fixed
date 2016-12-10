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

package com.liferay.portlet.softwarecatalog.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.softwarecatalog.model.SCProductEntry;
import com.liferay.portlet.softwarecatalog.service.SCProductEntryLocalServiceUtil;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SCProductEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long productEntryId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, productEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, SCProductEntry productEntry,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, productEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long productEntryId,
			String actionId)
		throws PortalException, SystemException {

		SCProductEntry productEntry =
			SCProductEntryLocalServiceUtil.getProductEntry(productEntryId);

		return contains(permissionChecker, productEntry, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, SCProductEntry productEntry,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				productEntry.getCompanyId(), SCProductEntry.class.getName(),
				productEntry.getProductEntryId(), productEntry.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			productEntry.getGroupId(), SCProductEntry.class.getName(),
			productEntry.getProductEntryId(), actionId);
	}

}