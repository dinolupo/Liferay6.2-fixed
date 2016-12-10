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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class MultiValueMapFactoryUtil {

	public static MultiValueMap<?, ?> getMultiValueMap(int type) {
		return getMultiValueMapFactory().getMultiValueMap(type);
	}

	public static MultiValueMap<?, ?> getMultiValueMap(String propertyKey) {
		return getMultiValueMapFactory().getMultiValueMap(propertyKey);
	}

	public static MultiValueMapFactory getMultiValueMapFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			MultiValueMapFactoryUtil.class);

		return _multiValueMapFactory;
	}

	public void setMultiValueMapFactory(
		MultiValueMapFactory multiValueMapFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_multiValueMapFactory = multiValueMapFactory;
	}

	private static MultiValueMapFactory _multiValueMapFactory;

}