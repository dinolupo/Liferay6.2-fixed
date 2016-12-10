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

package com.liferay.portal.util;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.UniqueList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eduardo Lundgren
 */
public class JavaScriptBundleUtil {

	public static void clearCache() {
		_portalCache.removeAll();
	}

	public static String[] getFileNames(String bundleId) {
		String[] fileNames = _portalCache.get(bundleId);

		if (fileNames == null) {
			List<String> fileNamesList = new ArrayList<String>();

			List<String> dependencies = _getDependencies(
				bundleId, new UniqueList<String>());

			for (String dependency : dependencies) {
				String[] dependencyFileNames = PropsUtil.getArray(dependency);

				for (String dependencyFileName : dependencyFileNames) {
					fileNamesList.add(dependencyFileName);
				}
			}

			fileNames = fileNamesList.toArray(new String[fileNamesList.size()]);

			_portalCache.put(bundleId, fileNames);
		}

		return fileNames;
	}

	private static List<String> _getDependencies(
		String bundleId, List<String> dependencies) {

		if (!ArrayUtil.contains(PropsValues.JAVASCRIPT_BUNDLE_IDS, bundleId)) {
			return dependencies;
		}

		String[] bundleDependencies = PropsUtil.getArray(
			PropsKeys.JAVASCRIPT_BUNDLE_DEPENDENCIES, new Filter(bundleId));

		for (String bundleDependency : bundleDependencies) {
			String[] bundleDependencyDependencies = PropsUtil.getArray(
				PropsKeys.JAVASCRIPT_BUNDLE_DEPENDENCIES,
				new Filter(bundleDependency));

			if (!ArrayUtil.contains(bundleDependencyDependencies, bundleId)) {
				_getDependencies(bundleDependency, dependencies);
			}

			dependencies.add(bundleDependency);
		}

		dependencies.add(bundleId);

		return dependencies;
	}

	private static final String _CACHE_NAME =
		JavaScriptBundleUtil.class.getName();

	private static PortalCache<String, String[]> _portalCache =
		SingleVMPoolUtil.getCache(_CACHE_NAME);

}