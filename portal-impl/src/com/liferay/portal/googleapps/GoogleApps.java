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

package com.liferay.portal.googleapps;

import com.liferay.portal.kernel.googleapps.GEmailSettingsManager;
import com.liferay.portal.kernel.googleapps.GGroupManager;
import com.liferay.portal.kernel.googleapps.GNicknameManager;
import com.liferay.portal.kernel.googleapps.GUserManager;

/**
 * @author Brian Wing Shun Chan
 */
public class GoogleApps {

	public GoogleApps(long companyId) {
		_gAuthenticator = new GAuthenticator(companyId);

		init();
	}

	public GoogleApps(String domain, String userName, String password) {
		_gAuthenticator = new GAuthenticator(domain, userName, password);

		init();
	}

	public GAuthenticator getGAuthenticator() {
		return _gAuthenticator;
	}

	public GEmailSettingsManager getGEmailSettingsManager() {
		return _gEmailSettingsManager;
	}

	public GGroupManager getGGroupManager() {
		return _gGroupManager;
	}

	public GNicknameManager getGNicknameManager() {
		return _gNicknameManager;
	}

	public GUserManager getGUserManager() {
		return _gUserManager;
	}

	protected void init() {
		_gEmailSettingsManager = new GEmailSettingsManagerImpl(this);
		_gGroupManager = new GGroupManagerImpl(this);
		_gNicknameManager = new GNicknameManagerImpl(this);
		_gUserManager = new GUserManagerImpl(this);
	}

	private GAuthenticator _gAuthenticator;
	private GEmailSettingsManager _gEmailSettingsManager;
	private GGroupManager _gGroupManager;
	private GNicknameManager _gNicknameManager;
	private GUserManager _gUserManager;

}