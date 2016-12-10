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

package com.liferay.portal.asset;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistry;
import com.liferay.portlet.asset.model.AssetRendererFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Bruno Farache
 * @author Marcellus Tavares
 */
public class AssetRendererFactoryRegistryImpl
	implements AssetRendererFactoryRegistry {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getAssetRendererFactories(
	 *             long)}
	 */
	@Override
	public List<AssetRendererFactory> getAssetRendererFactories() {
		return ListUtil.fromMapValues(_assetRenderFactoriesMapByClassName);
	}

	@Override
	public List<AssetRendererFactory> getAssetRendererFactories(
		long companyId) {

		return ListUtil.fromMapValues(
			filterAssetRendererFactories(
				companyId, _assetRenderFactoriesMapByClassName));
	}

	@Override
	public AssetRendererFactory getAssetRendererFactoryByClassName(
		String className) {

		return _assetRenderFactoriesMapByClassName.get(className);
	}

	@Override
	public AssetRendererFactory getAssetRendererFactoryByType(String type) {
		return _assetRenderFactoriesMapByClassType.get(type);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getClassNameIds( long)}
	 */
	@Override
	public long[] getClassNameIds() {
		return getClassNameIds(0);
	}

	@Override
	public long[] getClassNameIds(long companyId) {
		Map<String, AssetRendererFactory> assetRenderFactories =
			_assetRenderFactoriesMapByClassName;

		if (companyId > 0) {
			assetRenderFactories = filterAssetRendererFactories(
				companyId, _assetRenderFactoriesMapByClassName);
		}

		long[] classNameIds = new long[assetRenderFactories.size()];

		int i = 0;

		for (AssetRendererFactory assetRendererFactory :
				assetRenderFactories.values()) {

			classNameIds[i] = assetRendererFactory.getClassNameId();

			i++;
		}

		return classNameIds;
	}

	@Override
	public void register(AssetRendererFactory assetRendererFactory) {
		String className = assetRendererFactory.getClassName();

		AssetRendererFactory classNameAssetRendererFactory =
			_assetRenderFactoriesMapByClassName.put(
				className, assetRendererFactory);

		if (_log.isWarnEnabled() && (classNameAssetRendererFactory != null)) {
			_log.warn(
				"Replacing " + classNameAssetRendererFactory +
					" for class name " + className + " with " +
						assetRendererFactory);
		}

		String type = assetRendererFactory.getType();

		AssetRendererFactory typeAssetRendererFactory =
			_assetRenderFactoriesMapByClassType.put(type, assetRendererFactory);

		if (_log.isWarnEnabled() && (typeAssetRendererFactory != null)) {
			_log.warn(
				"Replacing " + typeAssetRendererFactory + " for type " + type +
					" with " + assetRendererFactory);
		}
	}

	@Override
	public void unregister(AssetRendererFactory assetRendererFactory) {
		_assetRenderFactoriesMapByClassName.remove(
			assetRendererFactory.getClassName());
		_assetRenderFactoriesMapByClassType.remove(
			assetRendererFactory.getType());
	}

	private Map<String, AssetRendererFactory> filterAssetRendererFactories(
		long companyId,
		Map<String, AssetRendererFactory> assetRendererFactories) {

		Map<String, AssetRendererFactory> filteredAssetRendererFactories =
			new ConcurrentHashMap<String, AssetRendererFactory>();

		for (String className : assetRendererFactories.keySet()) {
			AssetRendererFactory assetRendererFactory =
				assetRendererFactories.get(className);

			if (assetRendererFactory.isActive(companyId)) {
				filteredAssetRendererFactories.put(
					className, assetRendererFactory);
			}
		}

		return filteredAssetRendererFactories;
	}

	private static Log _log = LogFactoryUtil.getLog(
		AssetRendererFactoryRegistryImpl.class);

	private Map<String, AssetRendererFactory>
		_assetRenderFactoriesMapByClassName =
			new ConcurrentHashMap<String, AssetRendererFactory>();
	private Map<String, AssetRendererFactory>
		_assetRenderFactoriesMapByClassType =
			new ConcurrentHashMap<String, AssetRendererFactory>();

}