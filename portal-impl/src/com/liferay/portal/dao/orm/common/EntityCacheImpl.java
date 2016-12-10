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

package com.liferay.portal.dao.orm.common;

import com.liferay.portal.dao.shard.advice.ShardAdvice;
import com.liferay.portal.kernel.cache.CacheRegistryItem;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.dao.shard.ShardUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.util.PropsValues;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.collections.map.LRUMap;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
@DoPrivileged
public class EntityCacheImpl
	implements BeanFactoryAware, CacheRegistryItem, EntityCache {

	public static final String CACHE_NAME = EntityCache.class.getName();

	public void afterPropertiesSet() {
		CacheRegistryUtil.register(this);
	}

	@Override
	public void clearCache() {
		clearLocalCache();

		for (PortalCache<?, ?> portalCache : _portalCaches.values()) {
			portalCache.removeAll();
		}
	}

	@Override
	public void clearCache(String className) {
		clearLocalCache();

		PortalCache<?, ?> portalCache = _getPortalCache(className, true);

		if (portalCache != null) {
			portalCache.removeAll();
		}
	}

	@Override
	public void clearLocalCache() {
		if (_localCacheAvailable) {
			_localCache.remove();
		}
	}

	@Override
	public PortalCache<Serializable, Serializable> getPortalCache(
		Class<?> clazz) {

		return _getPortalCache(clazz.getName(), true);
	}

	@Override
	public String getRegistryName() {
		return CACHE_NAME;
	}

	@Override
	public Serializable getResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey) {

		if (!PropsValues.VALUE_OBJECT_ENTITY_CACHE_ENABLED ||
			!entityCacheEnabled || !CacheRegistryUtil.isActive()) {

			return null;
		}

		Serializable result = null;

		Map<Serializable, Serializable> localCache = null;

		Serializable localCacheKey = null;

		if (_localCacheAvailable) {
			localCache = _localCache.get();

			localCacheKey = _encodeLocalCacheKey(clazz, primaryKey);

			result = localCache.get(localCacheKey);
		}

		if (result == null) {
			PortalCache<Serializable, Serializable> portalCache =
				_getPortalCache(clazz.getName(), true);

			Serializable cacheKey = _encodeCacheKey(primaryKey);

			result = portalCache.get(cacheKey);

			if (result == null) {
				result = StringPool.BLANK;
			}

			if (_localCacheAvailable) {
				localCache.put(localCacheKey, result);
			}
		}

		return _toEntityModel(result);
	}

	@Override
	public void invalidate() {
		clearCache();
	}

	@Override
	public Serializable loadResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
		SessionFactory sessionFactory) {

		if (!PropsValues.VALUE_OBJECT_ENTITY_CACHE_ENABLED ||
			!entityCacheEnabled || !CacheRegistryUtil.isActive()) {

			Session session = null;

			try {
				session = sessionFactory.openSession();

				return (Serializable)session.load(clazz, primaryKey);
			}
			finally {
				sessionFactory.closeSession(session);
			}
		}

		Serializable result = null;

		Map<Serializable, Serializable> localCache = null;

		Serializable localCacheKey = null;

		if (_localCacheAvailable) {
			localCache = _localCache.get();

			localCacheKey = _encodeLocalCacheKey(clazz, primaryKey);

			result = localCache.get(localCacheKey);
		}

		Serializable loadResult = null;

		if (result == null) {
			PortalCache<Serializable, Serializable> portalCache =
				_getPortalCache(clazz.getName(), true);

			Serializable cacheKey = _encodeCacheKey(primaryKey);

			result = portalCache.get(cacheKey);

			if (result == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Load " + clazz + " " + primaryKey + " from session");
				}

				Session session = null;

				try {
					session = sessionFactory.openSession();

					loadResult = (Serializable)session.load(clazz, primaryKey);
				}
				finally {
					if (loadResult == null) {
						result = StringPool.BLANK;
					}
					else {
						result = ((BaseModel<?>)loadResult).toCacheModel();
					}

					portalCache.put(cacheKey, result);

					sessionFactory.closeSession(session);
				}
			}

			if (_localCacheAvailable) {
				localCache.put(localCacheKey, result);
			}
		}

		if (loadResult != null) {
			return loadResult;
		}

		return _toEntityModel(result);
	}

	@Override
	public void putResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
		Serializable result) {

		if (!PropsValues.VALUE_OBJECT_ENTITY_CACHE_ENABLED ||
			!entityCacheEnabled || !CacheRegistryUtil.isActive() ||
			(result == null)) {

			return;
		}

		result = ((BaseModel<?>)result).toCacheModel();

		if (_localCacheAvailable) {
			Map<Serializable, Serializable> localCache = _localCache.get();

			Serializable localCacheKey = _encodeLocalCacheKey(
				clazz, primaryKey);

			localCache.put(localCacheKey, result);
		}

		PortalCache<Serializable, Serializable> portalCache = _getPortalCache(
			clazz.getName(), true);

		Serializable cacheKey = _encodeCacheKey(primaryKey);

		portalCache.put(cacheKey, result);
	}

	@Override
	public void removeCache(String className) {
		_portalCaches.remove(className);

		String groupKey = _GROUP_KEY_PREFIX.concat(className);

		_multiVMPool.removeCache(groupKey);
	}

	@Override
	public void removeResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey) {

		if (!PropsValues.VALUE_OBJECT_ENTITY_CACHE_ENABLED ||
			!entityCacheEnabled || !CacheRegistryUtil.isActive()) {

			return;
		}

		if (_localCacheAvailable) {
			Map<Serializable, Serializable> localCache = _localCache.get();

			Serializable localCacheKey = _encodeLocalCacheKey(
				clazz, primaryKey);

			localCache.remove(localCacheKey);
		}

		PortalCache<Serializable, Serializable> portalCache = _getPortalCache(
			clazz.getName(), true);

		Serializable cacheKey = _encodeCacheKey(primaryKey);

		portalCache.remove(cacheKey);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		if (beanFactory.containsBean(ShardAdvice.class.getName())) {
			_shardEnabled = true;
		}
	}

	public void setMultiVMPool(MultiVMPool multiVMPool) {
		_multiVMPool = multiVMPool;
	}

	private Serializable _encodeCacheKey(Serializable primaryKey) {
		if (_shardEnabled) {
			return new CacheKey(ShardUtil.getCurrentShardName(), primaryKey);
		}

		return primaryKey;
	}

	private Serializable _encodeLocalCacheKey(
		Class<?> clazz, Serializable primaryKey) {

		if (_shardEnabled) {
			return new ShardLocalCacheKey(
				ShardUtil.getCurrentShardName(), clazz.getName(), primaryKey);
		}

		return new LocalCacheKey(clazz.getName(), primaryKey);
	}

	private PortalCache<Serializable, Serializable> _getPortalCache(
		String className, boolean createIfAbsent) {

		PortalCache<Serializable, Serializable> portalCache = _portalCaches.get(
			className);

		if ((portalCache == null) && createIfAbsent) {
			String groupKey = _GROUP_KEY_PREFIX.concat(className);

			portalCache =
				(PortalCache<Serializable, Serializable>)_multiVMPool.getCache(
					groupKey, PropsValues.VALUE_OBJECT_ENTITY_BLOCKING_CACHE);

			PortalCache<Serializable, Serializable> previousPortalCache =
				_portalCaches.putIfAbsent(className, portalCache);

			if (previousPortalCache != null) {
				portalCache = previousPortalCache;
			}
		}

		return portalCache;
	}

	private Serializable _toEntityModel(Serializable result) {
		if (result == StringPool.BLANK) {
			return null;
		}

		CacheModel<?> cacheModel = (CacheModel<?>)result;

		BaseModel<?> entityModel = (BaseModel<?>)cacheModel.toEntityModel();

		entityModel.setCachedModel(true);

		return entityModel;
	}

	private static final String _GROUP_KEY_PREFIX = CACHE_NAME.concat(
		StringPool.PERIOD);

	private static Log _log = LogFactoryUtil.getLog(EntityCacheImpl.class);

	private static ThreadLocal<LRUMap> _localCache;
	private static boolean _localCacheAvailable;

	static {
		if (PropsValues.VALUE_OBJECT_ENTITY_THREAD_LOCAL_CACHE_MAX_SIZE > 0) {
			_localCache = new AutoResetThreadLocal<LRUMap>(
				EntityCacheImpl.class + "._localCache",
				new LRUMap(
					PropsValues.
						VALUE_OBJECT_ENTITY_THREAD_LOCAL_CACHE_MAX_SIZE));
			_localCacheAvailable = true;
		}
	}

	private MultiVMPool _multiVMPool;
	private ConcurrentMap<String, PortalCache<Serializable, Serializable>>
		_portalCaches =
			new ConcurrentHashMap
				<String, PortalCache<Serializable, Serializable>>();
	private boolean _shardEnabled;

	private static class CacheKey implements Externalizable {

		@SuppressWarnings("unused")
		public CacheKey() {
		}

		public CacheKey(String shardName, Serializable primaryKey) {
			_shardName = shardName;
			_primaryKey = primaryKey;
		}

		@Override
		public boolean equals(Object obj) {
			CacheKey cacheKey = (CacheKey)obj;

			if (cacheKey._shardName.equals(_shardName) &&
				cacheKey._primaryKey.equals(_primaryKey)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return _shardName.hashCode() * 11 + _primaryKey.hashCode();
		}

		@Override
		public void readExternal(ObjectInput objectInput)
			throws ClassNotFoundException, IOException {

			_primaryKey = (Serializable)objectInput.readObject();
			_shardName = objectInput.readUTF();
		}

		@Override
		public void writeExternal(ObjectOutput objectOutput)
			throws IOException {

			objectOutput.writeObject(_primaryKey);
			objectOutput.writeUTF(_shardName);
		}

		private static final long serialVersionUID = 1L;

		private Serializable _primaryKey;
		private String _shardName;

	}

	private static class LocalCacheKey implements Serializable {

		public LocalCacheKey(String className, Serializable primaryKey) {
			_className = className;
			_primaryKey = primaryKey;
		}

		@Override
		public boolean equals(Object obj) {
			LocalCacheKey localCacheKey = (LocalCacheKey)obj;

			if (localCacheKey._className.equals(_className) &&
				localCacheKey._primaryKey.equals(_primaryKey)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return _className.hashCode() * 11 + _primaryKey.hashCode();
		}

		private static final long serialVersionUID = 1L;

		private final String _className;
		private final Serializable _primaryKey;

	}

	private static class ShardLocalCacheKey implements Serializable {

		public ShardLocalCacheKey(
			String shardName, String className, Serializable primaryKey) {

			_shardName = shardName;
			_className = className;
			_primaryKey = primaryKey;
		}

		@Override
		public boolean equals(Object obj) {
			ShardLocalCacheKey shardLocalCacheKey = (ShardLocalCacheKey)obj;

			if (shardLocalCacheKey._shardName.equals(_shardName) &&
				shardLocalCacheKey._className.equals(_className) &&
				shardLocalCacheKey._primaryKey.equals(_primaryKey)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			int hashCode = HashUtil.hash(0, _shardName);

			hashCode = HashUtil.hash(hashCode, _className);
			hashCode = HashUtil.hash(hashCode, _primaryKey);

			return hashCode;
		}

		private static final long serialVersionUID = 1L;

		private final String _className;
		private final Serializable _primaryKey;
		private final String _shardName;

	}

}