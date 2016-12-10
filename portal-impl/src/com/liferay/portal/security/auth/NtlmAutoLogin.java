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

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ldap.PortalLDAPImporterUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bruno Farache
 */
public class NtlmAutoLogin extends BaseAutoLogin {

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(request);

		if (!AuthSettingsUtil.isNtlmEnabled(companyId)) {
			return null;
		}

		String screenName = (String)request.getAttribute(
			WebKeys.NTLM_REMOTE_USER);

		if (screenName == null) {
			return null;
		}

		request.removeAttribute(WebKeys.NTLM_REMOTE_USER);

		User user = PortalLDAPImporterUtil.importLDAPUserByScreenName(
			companyId, screenName);

		if (user == null) {
			return null;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		if (Validator.isNotNull(redirect)) {
			request.setAttribute(
				AutoLogin.AUTO_LOGIN_REDIRECT_AND_CONTINUE,
				PortalUtil.escapeRedirect(redirect));
		}

		String[] credentials = new String[3];

		credentials[0] = String.valueOf(user.getUserId());
		credentials[1] = user.getPassword();
		credentials[2] = Boolean.TRUE.toString();

		return credentials;
	}

}