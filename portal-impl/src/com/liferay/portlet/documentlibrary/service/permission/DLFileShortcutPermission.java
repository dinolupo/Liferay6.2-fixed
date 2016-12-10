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

package com.liferay.portlet.documentlibrary.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.service.DLFileShortcutLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileShortcutPermission {

	public static void check(
			PermissionChecker permissionChecker, DLFileShortcut dlFileShortcut,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, dlFileShortcut, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long fileShortcutId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, fileShortcutId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, DLFileShortcut dlFileShortcut,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, dlFileShortcut.getGroupId(),
			DLFileShortcut.class.getName(), dlFileShortcut.getFileShortcutId(),
			PortletKeys.DOCUMENT_LIBRARY, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				dlFileShortcut.getCompanyId(), DLFileShortcut.class.getName(),
				dlFileShortcut.getFileShortcutId(), dlFileShortcut.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			dlFileShortcut.getGroupId(), DLFileShortcut.class.getName(),
			dlFileShortcut.getFileShortcutId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long fileShortcutId,
			String actionId)
		throws PortalException, SystemException {

		DLFileShortcut dlFileShortcut =
			DLFileShortcutLocalServiceUtil.getFileShortcut(fileShortcutId);

		return contains(permissionChecker, dlFileShortcut, actionId);
	}

}