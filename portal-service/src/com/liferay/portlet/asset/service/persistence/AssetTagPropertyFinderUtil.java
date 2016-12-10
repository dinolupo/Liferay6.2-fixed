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

package com.liferay.portlet.asset.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class AssetTagPropertyFinderUtil {
	public static int countByG_K(long groupId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_K(groupId, key);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTagProperty> findByG_K(
		long groupId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByG_K(groupId, key);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTagProperty> findByG_K(
		long groupId, java.lang.String key, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByG_K(groupId, key, start, end);
	}

	public static AssetTagPropertyFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetTagPropertyFinder)PortalBeanLocatorUtil.locate(AssetTagPropertyFinder.class.getName());

			ReferenceRegistry.registerReference(AssetTagPropertyFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetTagPropertyFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetTagPropertyFinderUtil.class,
			"_finder");
	}

	private static AssetTagPropertyFinder _finder;
}