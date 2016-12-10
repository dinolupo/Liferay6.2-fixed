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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Set;

/**
 * @author Tomas Polesovsky
 * @author Raymond Augé
 */
public class AuthTokenWhitelistUtil {

	public static AuthTokenWhitelist getAuthTokenWhitelist() {
		PortalRuntimePermission.checkGetBeanProperty(AuthTokenWhitelist.class);

		return _authTokenWhitelist;
	}

	public static Set<String> getPortletCSRFWhitelist() {
		return getAuthTokenWhitelist().getPortletCSRFWhitelist();
	}

	public static Set<String> getPortletCSRFWhitelistActions() {
		return getAuthTokenWhitelist().getPortletCSRFWhitelistActions();
	}

	public static Set<String> getPortletInvocationWhitelist() {
		return getAuthTokenWhitelist().getPortletInvocationWhitelist();
	}

	public static Set<String> getPortletInvocationWhitelistActions() {
		return getAuthTokenWhitelist().getPortletInvocationWhitelistActions();
	}

	public static boolean isCSRFOrigintWhitelisted(
		long companyId, String origin) {

		return getAuthTokenWhitelist().isOriginCSRFWhitelisted(
			companyId, origin);
	}

	public static boolean isPortletCSRFWhitelisted(
		long companyId, String portletId, String strutsAction) {

		return getAuthTokenWhitelist().isPortletCSRFWhitelisted(
			companyId, portletId, strutsAction);
	}

	public static boolean isPortletInvocationWhitelisted(
		long companyId, String portletId, String strutsAction) {

		return getAuthTokenWhitelist().isPortletInvocationWhitelisted(
			companyId, portletId, strutsAction);
	}

	public static boolean isValidSharedSecret(String sharedSecret) {
		return getAuthTokenWhitelist().isValidSharedSecret(sharedSecret);
	}

	public static Set<String> resetOriginCSRFWhitelist() {
		return getAuthTokenWhitelist().resetOriginCSRFWhitelist();
	}

	public static Set<String> resetPortletCSRFWhitelist() {
		return getAuthTokenWhitelist().resetPortletCSRFWhitelist();
	}

	public static Set<String> resetPortletCSRFWhitelistActions() {
		return getAuthTokenWhitelist().resetPortletCSRFWhitelistActions();
	}

	public static Set<String> resetPortletInvocationWhitelist() {
		return getAuthTokenWhitelist().resetPortletInvocationWhitelist();
	}

	public static Set<String> resetPortletInvocationWhitelistActions() {
		return getAuthTokenWhitelist().resetPortletInvocationWhitelistActions();
	}

	public void setAuthTokenWhitelist(AuthTokenWhitelist authTokenWhitelist) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_authTokenWhitelist = authTokenWhitelist;
	}

	private static AuthTokenWhitelist _authTokenWhitelist;

}