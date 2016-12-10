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

import com.liferay.portal.kernel.exception.PortalException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class AuthTokenWrapper implements AuthToken {

	public AuthTokenWrapper(AuthToken authToken) {
		_authToken = authToken;
		_originalAuthToken = authToken;
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             #checkCSRFToken(HttpServletRequest, String)}
	 */
	@Override
	public void check(HttpServletRequest request) throws PortalException {
		_authToken.check(request);
	}

	@Override
	public void checkCSRFToken(HttpServletRequest request, String origin)
		throws PrincipalException {

		_authToken.checkCSRFToken(request, origin);
	}

	@Override
	public String getToken(HttpServletRequest request) {
		return _authToken.getToken(request);
	}

	@Override
	public String getToken(
		HttpServletRequest request, long plid, String portletId) {

		return _authToken.getToken(request, plid, portletId);
	}

	@Override
	public boolean isValidPortletInvocationToken(
		HttpServletRequest request, long plid, String portletId,
		String strutsAction, String tokenValue) {

		return _authToken.isValidPortletInvocationToken(
			request, plid, portletId, strutsAction, tokenValue);
	}

	public void setAuthToken(AuthToken authToken) {
		if (authToken == null) {
			_authToken = _originalAuthToken;
		}
		else {
			_authToken = authToken;
		}
	}

	private AuthToken _authToken;
	private AuthToken _originalAuthToken;

}