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

package com.liferay.portlet.calendar.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class CalEventPermission {

	public static void check(
			PermissionChecker permissionChecker, CalEvent event,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, event, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long eventId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, eventId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, CalEvent event, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				event.getCompanyId(), CalEvent.class.getName(),
				event.getEventId(), event.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			event.getGroupId(), CalEvent.class.getName(), event.getEventId(),
			actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long eventId, String actionId)
		throws PortalException, SystemException {

		CalEvent event = CalEventLocalServiceUtil.getEvent(eventId);

		return contains(permissionChecker, event, actionId);
	}

}