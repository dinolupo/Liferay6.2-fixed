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
public class AssetEntryFinderUtil {
	public static int countEntries(
		com.liferay.portlet.asset.service.persistence.AssetEntryQuery entryQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countEntries(entryQuery);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetEntry> findEntries(
		com.liferay.portlet.asset.service.persistence.AssetEntryQuery entryQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findEntries(entryQuery);
	}

	public static AssetEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetEntryFinder)PortalBeanLocatorUtil.locate(AssetEntryFinder.class.getName());

			ReferenceRegistry.registerReference(AssetEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetEntryFinderUtil.class,
			"_finder");
	}

	private static AssetEntryFinder _finder;
}