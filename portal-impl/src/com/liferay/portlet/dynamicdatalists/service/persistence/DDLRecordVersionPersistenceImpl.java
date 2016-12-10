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

package com.liferay.portlet.dynamicdatalists.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion;
import com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordVersionImpl;
import com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordVersionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the d d l record version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordVersionPersistence
 * @see DDLRecordVersionUtil
 * @generated
 */
public class DDLRecordVersionPersistenceImpl extends BasePersistenceImpl<DDLRecordVersion>
	implements DDLRecordVersionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DDLRecordVersionUtil} to access the d d l record version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DDLRecordVersionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionModelImpl.FINDER_CACHE_ENABLED,
			DDLRecordVersionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionModelImpl.FINDER_CACHE_ENABLED,
			DDLRecordVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RECORDID = new FinderPath(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionModelImpl.FINDER_CACHE_ENABLED,
			DDLRecordVersionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByRecordId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECORDID =
		new FinderPath(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionModelImpl.FINDER_CACHE_ENABLED,
			DDLRecordVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRecordId",
			new String[] { Long.class.getName() },
			DDLRecordVersionModelImpl.RECORDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RECORDID = new FinderPath(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRecordId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the d d l record versions where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @return the matching d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordVersion> findByRecordId(long recordId)
		throws SystemException {
		return findByRecordId(recordId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the d d l record versions where recordId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recordId the record ID
	 * @param start the lower bound of the range of d d l record versions
	 * @param end the upper bound of the range of d d l record versions (not inclusive)
	 * @return the range of matching d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordVersion> findByRecordId(long recordId, int start,
		int end) throws SystemException {
		return findByRecordId(recordId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the d d l record versions where recordId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recordId the record ID
	 * @param start the lower bound of the range of d d l record versions
	 * @param end the upper bound of the range of d d l record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordVersion> findByRecordId(long recordId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECORDID;
			finderArgs = new Object[] { recordId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RECORDID;
			finderArgs = new Object[] { recordId, start, end, orderByComparator };
		}

		List<DDLRecordVersion> list = (List<DDLRecordVersion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DDLRecordVersion ddlRecordVersion : list) {
				if ((recordId != ddlRecordVersion.getRecordId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DDLRECORDVERSION_WHERE);

			query.append(_FINDER_COLUMN_RECORDID_RECORDID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DDLRecordVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(recordId);

				if (!pagination) {
					list = (List<DDLRecordVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DDLRecordVersion>(list);
				}
				else {
					list = (List<DDLRecordVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first d d l record version in the ordered set where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d l record version
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException if a matching d d l record version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion findByRecordId_First(long recordId,
		OrderByComparator orderByComparator)
		throws NoSuchRecordVersionException, SystemException {
		DDLRecordVersion ddlRecordVersion = fetchByRecordId_First(recordId,
				orderByComparator);

		if (ddlRecordVersion != null) {
			return ddlRecordVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("recordId=");
		msg.append(recordId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordVersionException(msg.toString());
	}

	/**
	 * Returns the first d d l record version in the ordered set where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d l record version, or <code>null</code> if a matching d d l record version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion fetchByRecordId_First(long recordId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DDLRecordVersion> list = findByRecordId(recordId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last d d l record version in the ordered set where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d l record version
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException if a matching d d l record version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion findByRecordId_Last(long recordId,
		OrderByComparator orderByComparator)
		throws NoSuchRecordVersionException, SystemException {
		DDLRecordVersion ddlRecordVersion = fetchByRecordId_Last(recordId,
				orderByComparator);

		if (ddlRecordVersion != null) {
			return ddlRecordVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("recordId=");
		msg.append(recordId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordVersionException(msg.toString());
	}

	/**
	 * Returns the last d d l record version in the ordered set where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d l record version, or <code>null</code> if a matching d d l record version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion fetchByRecordId_Last(long recordId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRecordId(recordId);

		if (count == 0) {
			return null;
		}

		List<DDLRecordVersion> list = findByRecordId(recordId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the d d l record versions before and after the current d d l record version in the ordered set where recordId = &#63;.
	 *
	 * @param recordVersionId the primary key of the current d d l record version
	 * @param recordId the record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next d d l record version
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException if a d d l record version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion[] findByRecordId_PrevAndNext(long recordVersionId,
		long recordId, OrderByComparator orderByComparator)
		throws NoSuchRecordVersionException, SystemException {
		DDLRecordVersion ddlRecordVersion = findByPrimaryKey(recordVersionId);

		Session session = null;

		try {
			session = openSession();

			DDLRecordVersion[] array = new DDLRecordVersionImpl[3];

			array[0] = getByRecordId_PrevAndNext(session, ddlRecordVersion,
					recordId, orderByComparator, true);

			array[1] = ddlRecordVersion;

			array[2] = getByRecordId_PrevAndNext(session, ddlRecordVersion,
					recordId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDLRecordVersion getByRecordId_PrevAndNext(Session session,
		DDLRecordVersion ddlRecordVersion, long recordId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DDLRECORDVERSION_WHERE);

		query.append(_FINDER_COLUMN_RECORDID_RECORDID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(DDLRecordVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(recordId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ddlRecordVersion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DDLRecordVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the d d l record versions where recordId = &#63; from the database.
	 *
	 * @param recordId the record ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByRecordId(long recordId) throws SystemException {
		for (DDLRecordVersion ddlRecordVersion : findByRecordId(recordId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ddlRecordVersion);
		}
	}

	/**
	 * Returns the number of d d l record versions where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @return the number of matching d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByRecordId(long recordId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RECORDID;

		Object[] finderArgs = new Object[] { recordId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DDLRECORDVERSION_WHERE);

			query.append(_FINDER_COLUMN_RECORDID_RECORDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(recordId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_RECORDID_RECORDID_2 = "ddlRecordVersion.recordId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_R_V = new FinderPath(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionModelImpl.FINDER_CACHE_ENABLED,
			DDLRecordVersionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByR_V",
			new String[] { Long.class.getName(), String.class.getName() },
			DDLRecordVersionModelImpl.RECORDID_COLUMN_BITMASK |
			DDLRecordVersionModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_V = new FinderPath(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_V",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the d d l record version where recordId = &#63; and version = &#63; or throws a {@link com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException} if it could not be found.
	 *
	 * @param recordId the record ID
	 * @param version the version
	 * @return the matching d d l record version
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException if a matching d d l record version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion findByR_V(long recordId, String version)
		throws NoSuchRecordVersionException, SystemException {
		DDLRecordVersion ddlRecordVersion = fetchByR_V(recordId, version);

		if (ddlRecordVersion == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("recordId=");
			msg.append(recordId);

			msg.append(", version=");
			msg.append(version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRecordVersionException(msg.toString());
		}

		return ddlRecordVersion;
	}

	/**
	 * Returns the d d l record version where recordId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param recordId the record ID
	 * @param version the version
	 * @return the matching d d l record version, or <code>null</code> if a matching d d l record version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion fetchByR_V(long recordId, String version)
		throws SystemException {
		return fetchByR_V(recordId, version, true);
	}

	/**
	 * Returns the d d l record version where recordId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param recordId the record ID
	 * @param version the version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching d d l record version, or <code>null</code> if a matching d d l record version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion fetchByR_V(long recordId, String version,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { recordId, version };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_R_V,
					finderArgs, this);
		}

		if (result instanceof DDLRecordVersion) {
			DDLRecordVersion ddlRecordVersion = (DDLRecordVersion)result;

			if ((recordId != ddlRecordVersion.getRecordId()) ||
					!Validator.equals(version, ddlRecordVersion.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DDLRECORDVERSION_WHERE);

			query.append(_FINDER_COLUMN_R_V_RECORDID_2);

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_R_V_VERSION_1);
			}
			else if (version.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_R_V_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_R_V_VERSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(recordId);

				if (bindVersion) {
					qPos.add(version);
				}

				List<DDLRecordVersion> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
						finderArgs, list);
				}
				else {
					DDLRecordVersion ddlRecordVersion = list.get(0);

					result = ddlRecordVersion;

					cacheResult(ddlRecordVersion);

					if ((ddlRecordVersion.getRecordId() != recordId) ||
							(ddlRecordVersion.getVersion() == null) ||
							!ddlRecordVersion.getVersion().equals(version)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
							finderArgs, ddlRecordVersion);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (DDLRecordVersion)result;
		}
	}

	/**
	 * Removes the d d l record version where recordId = &#63; and version = &#63; from the database.
	 *
	 * @param recordId the record ID
	 * @param version the version
	 * @return the d d l record version that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion removeByR_V(long recordId, String version)
		throws NoSuchRecordVersionException, SystemException {
		DDLRecordVersion ddlRecordVersion = findByR_V(recordId, version);

		return remove(ddlRecordVersion);
	}

	/**
	 * Returns the number of d d l record versions where recordId = &#63; and version = &#63;.
	 *
	 * @param recordId the record ID
	 * @param version the version
	 * @return the number of matching d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByR_V(long recordId, String version)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_V;

		Object[] finderArgs = new Object[] { recordId, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DDLRECORDVERSION_WHERE);

			query.append(_FINDER_COLUMN_R_V_RECORDID_2);

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_R_V_VERSION_1);
			}
			else if (version.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_R_V_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_R_V_VERSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(recordId);

				if (bindVersion) {
					qPos.add(version);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_R_V_RECORDID_2 = "ddlRecordVersion.recordId = ? AND ";
	private static final String _FINDER_COLUMN_R_V_VERSION_1 = "ddlRecordVersion.version IS NULL";
	private static final String _FINDER_COLUMN_R_V_VERSION_2 = "ddlRecordVersion.version = ?";
	private static final String _FINDER_COLUMN_R_V_VERSION_3 = "(ddlRecordVersion.version IS NULL OR ddlRecordVersion.version = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S = new FinderPath(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionModelImpl.FINDER_CACHE_ENABLED,
			DDLRecordVersionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByR_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S = new FinderPath(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionModelImpl.FINDER_CACHE_ENABLED,
			DDLRecordVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			DDLRecordVersionModelImpl.RECORDID_COLUMN_BITMASK |
			DDLRecordVersionModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_S = new FinderPath(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the d d l record versions where recordId = &#63; and status = &#63;.
	 *
	 * @param recordId the record ID
	 * @param status the status
	 * @return the matching d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordVersion> findByR_S(long recordId, int status)
		throws SystemException {
		return findByR_S(recordId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the d d l record versions where recordId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recordId the record ID
	 * @param status the status
	 * @param start the lower bound of the range of d d l record versions
	 * @param end the upper bound of the range of d d l record versions (not inclusive)
	 * @return the range of matching d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordVersion> findByR_S(long recordId, int status,
		int start, int end) throws SystemException {
		return findByR_S(recordId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the d d l record versions where recordId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recordId the record ID
	 * @param status the status
	 * @param start the lower bound of the range of d d l record versions
	 * @param end the upper bound of the range of d d l record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordVersion> findByR_S(long recordId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] { recordId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] {
					recordId, status,
					
					start, end, orderByComparator
				};
		}

		List<DDLRecordVersion> list = (List<DDLRecordVersion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DDLRecordVersion ddlRecordVersion : list) {
				if ((recordId != ddlRecordVersion.getRecordId()) ||
						(status != ddlRecordVersion.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DDLRECORDVERSION_WHERE);

			query.append(_FINDER_COLUMN_R_S_RECORDID_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DDLRecordVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(recordId);

				qPos.add(status);

				if (!pagination) {
					list = (List<DDLRecordVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DDLRecordVersion>(list);
				}
				else {
					list = (List<DDLRecordVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first d d l record version in the ordered set where recordId = &#63; and status = &#63;.
	 *
	 * @param recordId the record ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d l record version
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException if a matching d d l record version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion findByR_S_First(long recordId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchRecordVersionException, SystemException {
		DDLRecordVersion ddlRecordVersion = fetchByR_S_First(recordId, status,
				orderByComparator);

		if (ddlRecordVersion != null) {
			return ddlRecordVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("recordId=");
		msg.append(recordId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordVersionException(msg.toString());
	}

	/**
	 * Returns the first d d l record version in the ordered set where recordId = &#63; and status = &#63;.
	 *
	 * @param recordId the record ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d l record version, or <code>null</code> if a matching d d l record version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion fetchByR_S_First(long recordId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<DDLRecordVersion> list = findByR_S(recordId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last d d l record version in the ordered set where recordId = &#63; and status = &#63;.
	 *
	 * @param recordId the record ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d l record version
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException if a matching d d l record version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion findByR_S_Last(long recordId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchRecordVersionException, SystemException {
		DDLRecordVersion ddlRecordVersion = fetchByR_S_Last(recordId, status,
				orderByComparator);

		if (ddlRecordVersion != null) {
			return ddlRecordVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("recordId=");
		msg.append(recordId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordVersionException(msg.toString());
	}

	/**
	 * Returns the last d d l record version in the ordered set where recordId = &#63; and status = &#63;.
	 *
	 * @param recordId the record ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d l record version, or <code>null</code> if a matching d d l record version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion fetchByR_S_Last(long recordId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByR_S(recordId, status);

		if (count == 0) {
			return null;
		}

		List<DDLRecordVersion> list = findByR_S(recordId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the d d l record versions before and after the current d d l record version in the ordered set where recordId = &#63; and status = &#63;.
	 *
	 * @param recordVersionId the primary key of the current d d l record version
	 * @param recordId the record ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next d d l record version
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException if a d d l record version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion[] findByR_S_PrevAndNext(long recordVersionId,
		long recordId, int status, OrderByComparator orderByComparator)
		throws NoSuchRecordVersionException, SystemException {
		DDLRecordVersion ddlRecordVersion = findByPrimaryKey(recordVersionId);

		Session session = null;

		try {
			session = openSession();

			DDLRecordVersion[] array = new DDLRecordVersionImpl[3];

			array[0] = getByR_S_PrevAndNext(session, ddlRecordVersion,
					recordId, status, orderByComparator, true);

			array[1] = ddlRecordVersion;

			array[2] = getByR_S_PrevAndNext(session, ddlRecordVersion,
					recordId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDLRecordVersion getByR_S_PrevAndNext(Session session,
		DDLRecordVersion ddlRecordVersion, long recordId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DDLRECORDVERSION_WHERE);

		query.append(_FINDER_COLUMN_R_S_RECORDID_2);

		query.append(_FINDER_COLUMN_R_S_STATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(DDLRecordVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(recordId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ddlRecordVersion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DDLRecordVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the d d l record versions where recordId = &#63; and status = &#63; from the database.
	 *
	 * @param recordId the record ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByR_S(long recordId, int status)
		throws SystemException {
		for (DDLRecordVersion ddlRecordVersion : findByR_S(recordId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ddlRecordVersion);
		}
	}

	/**
	 * Returns the number of d d l record versions where recordId = &#63; and status = &#63;.
	 *
	 * @param recordId the record ID
	 * @param status the status
	 * @return the number of matching d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByR_S(long recordId, int status) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_S;

		Object[] finderArgs = new Object[] { recordId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DDLRECORDVERSION_WHERE);

			query.append(_FINDER_COLUMN_R_S_RECORDID_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(recordId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_R_S_RECORDID_2 = "ddlRecordVersion.recordId = ? AND ";
	private static final String _FINDER_COLUMN_R_S_STATUS_2 = "ddlRecordVersion.status = ?";

	public DDLRecordVersionPersistenceImpl() {
		setModelClass(DDLRecordVersion.class);
	}

	/**
	 * Caches the d d l record version in the entity cache if it is enabled.
	 *
	 * @param ddlRecordVersion the d d l record version
	 */
	@Override
	public void cacheResult(DDLRecordVersion ddlRecordVersion) {
		EntityCacheUtil.putResult(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionImpl.class, ddlRecordVersion.getPrimaryKey(),
			ddlRecordVersion);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
			new Object[] {
				ddlRecordVersion.getRecordId(), ddlRecordVersion.getVersion()
			}, ddlRecordVersion);

		ddlRecordVersion.resetOriginalValues();
	}

	/**
	 * Caches the d d l record versions in the entity cache if it is enabled.
	 *
	 * @param ddlRecordVersions the d d l record versions
	 */
	@Override
	public void cacheResult(List<DDLRecordVersion> ddlRecordVersions) {
		for (DDLRecordVersion ddlRecordVersion : ddlRecordVersions) {
			if (EntityCacheUtil.getResult(
						DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
						DDLRecordVersionImpl.class,
						ddlRecordVersion.getPrimaryKey()) == null) {
				cacheResult(ddlRecordVersion);
			}
			else {
				ddlRecordVersion.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all d d l record versions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DDLRecordVersionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DDLRecordVersionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the d d l record version.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DDLRecordVersion ddlRecordVersion) {
		EntityCacheUtil.removeResult(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionImpl.class, ddlRecordVersion.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(ddlRecordVersion);
	}

	@Override
	public void clearCache(List<DDLRecordVersion> ddlRecordVersions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DDLRecordVersion ddlRecordVersion : ddlRecordVersions) {
			EntityCacheUtil.removeResult(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
				DDLRecordVersionImpl.class, ddlRecordVersion.getPrimaryKey());

			clearUniqueFindersCache(ddlRecordVersion);
		}
	}

	protected void cacheUniqueFindersCache(DDLRecordVersion ddlRecordVersion) {
		if (ddlRecordVersion.isNew()) {
			Object[] args = new Object[] {
					ddlRecordVersion.getRecordId(),
					ddlRecordVersion.getVersion()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_V, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V, args,
				ddlRecordVersion);
		}
		else {
			DDLRecordVersionModelImpl ddlRecordVersionModelImpl = (DDLRecordVersionModelImpl)ddlRecordVersion;

			if ((ddlRecordVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_R_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ddlRecordVersion.getRecordId(),
						ddlRecordVersion.getVersion()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_V, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V, args,
					ddlRecordVersion);
			}
		}
	}

	protected void clearUniqueFindersCache(DDLRecordVersion ddlRecordVersion) {
		DDLRecordVersionModelImpl ddlRecordVersionModelImpl = (DDLRecordVersionModelImpl)ddlRecordVersion;

		Object[] args = new Object[] {
				ddlRecordVersion.getRecordId(), ddlRecordVersion.getVersion()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_V, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V, args);

		if ((ddlRecordVersionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_R_V.getColumnBitmask()) != 0) {
			args = new Object[] {
					ddlRecordVersionModelImpl.getOriginalRecordId(),
					ddlRecordVersionModelImpl.getOriginalVersion()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_V, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V, args);
		}
	}

	/**
	 * Creates a new d d l record version with the primary key. Does not add the d d l record version to the database.
	 *
	 * @param recordVersionId the primary key for the new d d l record version
	 * @return the new d d l record version
	 */
	@Override
	public DDLRecordVersion create(long recordVersionId) {
		DDLRecordVersion ddlRecordVersion = new DDLRecordVersionImpl();

		ddlRecordVersion.setNew(true);
		ddlRecordVersion.setPrimaryKey(recordVersionId);

		return ddlRecordVersion;
	}

	/**
	 * Removes the d d l record version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param recordVersionId the primary key of the d d l record version
	 * @return the d d l record version that was removed
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException if a d d l record version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion remove(long recordVersionId)
		throws NoSuchRecordVersionException, SystemException {
		return remove((Serializable)recordVersionId);
	}

	/**
	 * Removes the d d l record version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the d d l record version
	 * @return the d d l record version that was removed
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException if a d d l record version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion remove(Serializable primaryKey)
		throws NoSuchRecordVersionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DDLRecordVersion ddlRecordVersion = (DDLRecordVersion)session.get(DDLRecordVersionImpl.class,
					primaryKey);

			if (ddlRecordVersion == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRecordVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ddlRecordVersion);
		}
		catch (NoSuchRecordVersionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected DDLRecordVersion removeImpl(DDLRecordVersion ddlRecordVersion)
		throws SystemException {
		ddlRecordVersion = toUnwrappedModel(ddlRecordVersion);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddlRecordVersion)) {
				ddlRecordVersion = (DDLRecordVersion)session.get(DDLRecordVersionImpl.class,
						ddlRecordVersion.getPrimaryKeyObj());
			}

			if (ddlRecordVersion != null) {
				session.delete(ddlRecordVersion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ddlRecordVersion != null) {
			clearCache(ddlRecordVersion);
		}

		return ddlRecordVersion;
	}

	@Override
	public DDLRecordVersion updateImpl(
		com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion ddlRecordVersion)
		throws SystemException {
		ddlRecordVersion = toUnwrappedModel(ddlRecordVersion);

		boolean isNew = ddlRecordVersion.isNew();

		DDLRecordVersionModelImpl ddlRecordVersionModelImpl = (DDLRecordVersionModelImpl)ddlRecordVersion;

		Session session = null;

		try {
			session = openSession();

			if (ddlRecordVersion.isNew()) {
				session.save(ddlRecordVersion);

				ddlRecordVersion.setNew(false);
			}
			else {
				session.merge(ddlRecordVersion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DDLRecordVersionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ddlRecordVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECORDID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ddlRecordVersionModelImpl.getOriginalRecordId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RECORDID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECORDID,
					args);

				args = new Object[] { ddlRecordVersionModelImpl.getRecordId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RECORDID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECORDID,
					args);
			}

			if ((ddlRecordVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ddlRecordVersionModelImpl.getOriginalRecordId(),
						ddlRecordVersionModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);

				args = new Object[] {
						ddlRecordVersionModelImpl.getRecordId(),
						ddlRecordVersionModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);
			}
		}

		EntityCacheUtil.putResult(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordVersionImpl.class, ddlRecordVersion.getPrimaryKey(),
			ddlRecordVersion);

		clearUniqueFindersCache(ddlRecordVersion);
		cacheUniqueFindersCache(ddlRecordVersion);

		return ddlRecordVersion;
	}

	protected DDLRecordVersion toUnwrappedModel(
		DDLRecordVersion ddlRecordVersion) {
		if (ddlRecordVersion instanceof DDLRecordVersionImpl) {
			return ddlRecordVersion;
		}

		DDLRecordVersionImpl ddlRecordVersionImpl = new DDLRecordVersionImpl();

		ddlRecordVersionImpl.setNew(ddlRecordVersion.isNew());
		ddlRecordVersionImpl.setPrimaryKey(ddlRecordVersion.getPrimaryKey());

		ddlRecordVersionImpl.setRecordVersionId(ddlRecordVersion.getRecordVersionId());
		ddlRecordVersionImpl.setGroupId(ddlRecordVersion.getGroupId());
		ddlRecordVersionImpl.setCompanyId(ddlRecordVersion.getCompanyId());
		ddlRecordVersionImpl.setUserId(ddlRecordVersion.getUserId());
		ddlRecordVersionImpl.setUserName(ddlRecordVersion.getUserName());
		ddlRecordVersionImpl.setCreateDate(ddlRecordVersion.getCreateDate());
		ddlRecordVersionImpl.setDDMStorageId(ddlRecordVersion.getDDMStorageId());
		ddlRecordVersionImpl.setRecordSetId(ddlRecordVersion.getRecordSetId());
		ddlRecordVersionImpl.setRecordId(ddlRecordVersion.getRecordId());
		ddlRecordVersionImpl.setVersion(ddlRecordVersion.getVersion());
		ddlRecordVersionImpl.setDisplayIndex(ddlRecordVersion.getDisplayIndex());
		ddlRecordVersionImpl.setStatus(ddlRecordVersion.getStatus());
		ddlRecordVersionImpl.setStatusByUserId(ddlRecordVersion.getStatusByUserId());
		ddlRecordVersionImpl.setStatusByUserName(ddlRecordVersion.getStatusByUserName());
		ddlRecordVersionImpl.setStatusDate(ddlRecordVersion.getStatusDate());

		return ddlRecordVersionImpl;
	}

	/**
	 * Returns the d d l record version with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the d d l record version
	 * @return the d d l record version
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException if a d d l record version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRecordVersionException, SystemException {
		DDLRecordVersion ddlRecordVersion = fetchByPrimaryKey(primaryKey);

		if (ddlRecordVersion == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRecordVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ddlRecordVersion;
	}

	/**
	 * Returns the d d l record version with the primary key or throws a {@link com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException} if it could not be found.
	 *
	 * @param recordVersionId the primary key of the d d l record version
	 * @return the d d l record version
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordVersionException if a d d l record version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion findByPrimaryKey(long recordVersionId)
		throws NoSuchRecordVersionException, SystemException {
		return findByPrimaryKey((Serializable)recordVersionId);
	}

	/**
	 * Returns the d d l record version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the d d l record version
	 * @return the d d l record version, or <code>null</code> if a d d l record version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		DDLRecordVersion ddlRecordVersion = (DDLRecordVersion)EntityCacheUtil.getResult(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
				DDLRecordVersionImpl.class, primaryKey);

		if (ddlRecordVersion == _nullDDLRecordVersion) {
			return null;
		}

		if (ddlRecordVersion == null) {
			Session session = null;

			try {
				session = openSession();

				ddlRecordVersion = (DDLRecordVersion)session.get(DDLRecordVersionImpl.class,
						primaryKey);

				if (ddlRecordVersion != null) {
					cacheResult(ddlRecordVersion);
				}
				else {
					EntityCacheUtil.putResult(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
						DDLRecordVersionImpl.class, primaryKey,
						_nullDDLRecordVersion);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DDLRecordVersionModelImpl.ENTITY_CACHE_ENABLED,
					DDLRecordVersionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ddlRecordVersion;
	}

	/**
	 * Returns the d d l record version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param recordVersionId the primary key of the d d l record version
	 * @return the d d l record version, or <code>null</code> if a d d l record version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordVersion fetchByPrimaryKey(long recordVersionId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)recordVersionId);
	}

	/**
	 * Returns all the d d l record versions.
	 *
	 * @return the d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordVersion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the d d l record versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d l record versions
	 * @param end the upper bound of the range of d d l record versions (not inclusive)
	 * @return the range of d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordVersion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the d d l record versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d l record versions
	 * @param end the upper bound of the range of d d l record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordVersion> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<DDLRecordVersion> list = (List<DDLRecordVersion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DDLRECORDVERSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DDLRECORDVERSION;

				if (pagination) {
					sql = sql.concat(DDLRecordVersionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DDLRecordVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DDLRecordVersion>(list);
				}
				else {
					list = (List<DDLRecordVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the d d l record versions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (DDLRecordVersion ddlRecordVersion : findAll()) {
			remove(ddlRecordVersion);
		}
	}

	/**
	 * Returns the number of d d l record versions.
	 *
	 * @return the number of d d l record versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DDLRECORDVERSION);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the d d l record version persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DDLRecordVersion>> listenersList = new ArrayList<ModelListener<DDLRecordVersion>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DDLRecordVersion>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(DDLRecordVersionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DDLRECORDVERSION = "SELECT ddlRecordVersion FROM DDLRecordVersion ddlRecordVersion";
	private static final String _SQL_SELECT_DDLRECORDVERSION_WHERE = "SELECT ddlRecordVersion FROM DDLRecordVersion ddlRecordVersion WHERE ";
	private static final String _SQL_COUNT_DDLRECORDVERSION = "SELECT COUNT(ddlRecordVersion) FROM DDLRecordVersion ddlRecordVersion";
	private static final String _SQL_COUNT_DDLRECORDVERSION_WHERE = "SELECT COUNT(ddlRecordVersion) FROM DDLRecordVersion ddlRecordVersion WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ddlRecordVersion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DDLRecordVersion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DDLRecordVersion exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(DDLRecordVersionPersistenceImpl.class);
	private static DDLRecordVersion _nullDDLRecordVersion = new DDLRecordVersionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DDLRecordVersion> toCacheModel() {
				return _nullDDLRecordVersionCacheModel;
			}
		};

	private static CacheModel<DDLRecordVersion> _nullDDLRecordVersionCacheModel = new CacheModel<DDLRecordVersion>() {
			@Override
			public DDLRecordVersion toEntityModel() {
				return _nullDDLRecordVersion;
			}
		};
}