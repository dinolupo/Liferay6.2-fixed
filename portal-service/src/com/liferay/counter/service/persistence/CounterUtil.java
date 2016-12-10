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

package com.liferay.counter.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.counter.model.Counter;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the counter service. This utility wraps {@link CounterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CounterPersistence
 * @see CounterPersistenceImpl
 * @generated
 */
@ProviderType
public class CounterUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Counter counter) {
		getPersistence().clearCache(counter);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Counter> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Counter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Counter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Counter update(Counter counter) throws SystemException {
		return getPersistence().update(counter);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Counter update(Counter counter, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(counter, serviceContext);
	}

	/**
	* Caches the counter in the entity cache if it is enabled.
	*
	* @param counter the counter
	*/
	public static void cacheResult(com.liferay.counter.model.Counter counter) {
		getPersistence().cacheResult(counter);
	}

	/**
	* Caches the counters in the entity cache if it is enabled.
	*
	* @param counters the counters
	*/
	public static void cacheResult(
		java.util.List<com.liferay.counter.model.Counter> counters) {
		getPersistence().cacheResult(counters);
	}

	/**
	* Creates a new counter with the primary key. Does not add the counter to the database.
	*
	* @param name the primary key for the new counter
	* @return the new counter
	*/
	public static com.liferay.counter.model.Counter create(
		java.lang.String name) {
		return getPersistence().create(name);
	}

	/**
	* Removes the counter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param name the primary key of the counter
	* @return the counter that was removed
	* @throws com.liferay.counter.NoSuchCounterException if a counter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.counter.model.Counter remove(
		java.lang.String name)
		throws com.liferay.counter.NoSuchCounterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(name);
	}

	public static com.liferay.counter.model.Counter updateImpl(
		com.liferay.counter.model.Counter counter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(counter);
	}

	/**
	* Returns the counter with the primary key or throws a {@link com.liferay.counter.NoSuchCounterException} if it could not be found.
	*
	* @param name the primary key of the counter
	* @return the counter
	* @throws com.liferay.counter.NoSuchCounterException if a counter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.counter.model.Counter findByPrimaryKey(
		java.lang.String name)
		throws com.liferay.counter.NoSuchCounterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(name);
	}

	/**
	* Returns the counter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param name the primary key of the counter
	* @return the counter, or <code>null</code> if a counter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.counter.model.Counter fetchByPrimaryKey(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(name);
	}

	/**
	* Returns all the counters.
	*
	* @return the counters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.counter.model.Counter> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.counter.model.impl.CounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of counters
	* @param end the upper bound of the range of counters (not inclusive)
	* @return the range of counters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.counter.model.Counter> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.counter.model.impl.CounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of counters
	* @param end the upper bound of the range of counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of counters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.counter.model.Counter> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the counters from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of counters.
	*
	* @return the number of counters
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CounterPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CounterPersistence)PortalBeanLocatorUtil.locate(CounterPersistence.class.getName());

			ReferenceRegistry.registerReference(CounterUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(CounterPersistence persistence) {
	}

	private static CounterPersistence _persistence;
}