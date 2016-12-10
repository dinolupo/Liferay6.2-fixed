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

package com.liferay.portal.kernel.backgroundtask;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskStatusRegistryUtil {

	public static BackgroundTaskStatus getBackgroundTaskStatus(
		long backgroundTaskId) {

		return getBackgroundTaskStatusRegistry().getBackgroundTaskStatus(
			backgroundTaskId);
	}

	public static BackgroundTaskStatusRegistry
		getBackgroundTaskStatusRegistry() {

		PortalRuntimePermission.checkGetBeanProperty(
			BackgroundTaskStatusRegistryUtil.class);

		return _backgroundTaskStatusRegistry;
	}

	public static BackgroundTaskStatus registerBackgroundTaskStatus(
		long backgroundTaskId) {

		return getBackgroundTaskStatusRegistry().registerBackgroundTaskStatus(
			backgroundTaskId);
	}

	public static BackgroundTaskStatus unregisterBackgroundTaskStatus(
		long backgroundTaskId) {

		return getBackgroundTaskStatusRegistry().unregisterBackgroundTaskStatus(
			backgroundTaskId);
	}

	public void setBackgroundTaskStatusRegistry(
		BackgroundTaskStatusRegistry backgroundTaskStatusRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_backgroundTaskStatusRegistry = backgroundTaskStatusRegistry;
	}

	private static BackgroundTaskStatusRegistry _backgroundTaskStatusRegistry;

}