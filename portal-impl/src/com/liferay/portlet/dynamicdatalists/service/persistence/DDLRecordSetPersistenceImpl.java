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
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetImpl;
import com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the d d l record set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordSetPersistence
 * @see DDLRecordSetUtil
 * @generated
 */
public class DDLRecordSetPersistenceImpl extends BasePersistenceImpl<DDLRecordSet>
	implements DDLRecordSetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DDLRecordSetUtil} to access the d d l record set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DDLRecordSetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, DDLRecordSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, DDLRecordSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, DDLRecordSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, DDLRecordSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DDLRecordSetModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the d d l record sets where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the d d l record sets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of d d l record sets
	 * @param end the upper bound of the range of d d l record sets (not inclusive)
	 * @return the range of matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the d d l record sets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of d d l record sets
	 * @param end the upper bound of the range of d d l record sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<DDLRecordSet> list = (List<DDLRecordSet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DDLRecordSet ddlRecordSet : list) {
				if (!Validator.equals(uuid, ddlRecordSet.getUuid())) {
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

			query.append(_SQL_SELECT_DDLRECORDSET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DDLRecordSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<DDLRecordSet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DDLRecordSet>(list);
				}
				else {
					list = (List<DDLRecordSet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first d d l record set in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = fetchByUuid_First(uuid, orderByComparator);

		if (ddlRecordSet != null) {
			return ddlRecordSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordSetException(msg.toString());
	}

	/**
	 * Returns the first d d l record set in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<DDLRecordSet> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last d d l record set in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = fetchByUuid_Last(uuid, orderByComparator);

		if (ddlRecordSet != null) {
			return ddlRecordSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordSetException(msg.toString());
	}

	/**
	 * Returns the last d d l record set in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DDLRecordSet> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the d d l record sets before and after the current d d l record set in the ordered set where uuid = &#63;.
	 *
	 * @param recordSetId the primary key of the current d d l record set
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a d d l record set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet[] findByUuid_PrevAndNext(long recordSetId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = findByPrimaryKey(recordSetId);

		Session session = null;

		try {
			session = openSession();

			DDLRecordSet[] array = new DDLRecordSetImpl[3];

			array[0] = getByUuid_PrevAndNext(session, ddlRecordSet, uuid,
					orderByComparator, true);

			array[1] = ddlRecordSet;

			array[2] = getByUuid_PrevAndNext(session, ddlRecordSet, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDLRecordSet getByUuid_PrevAndNext(Session session,
		DDLRecordSet ddlRecordSet, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DDLRECORDSET_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

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
			query.append(DDLRecordSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ddlRecordSet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DDLRecordSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the d d l record sets where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (DDLRecordSet ddlRecordSet : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ddlRecordSet);
		}
	}

	/**
	 * Returns the number of d d l record sets where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid(String uuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DDLRECORDSET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "ddlRecordSet.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "ddlRecordSet.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(ddlRecordSet.uuid IS NULL OR ddlRecordSet.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, DDLRecordSetImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DDLRecordSetModelImpl.UUID_COLUMN_BITMASK |
			DDLRecordSetModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the d d l record set where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet findByUUID_G(String uuid, long groupId)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = fetchByUUID_G(uuid, groupId);

		if (ddlRecordSet == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRecordSetException(msg.toString());
		}

		return ddlRecordSet;
	}

	/**
	 * Returns the d d l record set where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the d d l record set where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DDLRecordSet) {
			DDLRecordSet ddlRecordSet = (DDLRecordSet)result;

			if (!Validator.equals(uuid, ddlRecordSet.getUuid()) ||
					(groupId != ddlRecordSet.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DDLRECORDSET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<DDLRecordSet> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DDLRecordSet ddlRecordSet = list.get(0);

					result = ddlRecordSet;

					cacheResult(ddlRecordSet);

					if ((ddlRecordSet.getUuid() == null) ||
							!ddlRecordSet.getUuid().equals(uuid) ||
							(ddlRecordSet.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, ddlRecordSet);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
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
			return (DDLRecordSet)result;
		}
	}

	/**
	 * Removes the d d l record set where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the d d l record set that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet removeByUUID_G(String uuid, long groupId)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = findByUUID_G(uuid, groupId);

		return remove(ddlRecordSet);
	}

	/**
	 * Returns the number of d d l record sets where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DDLRECORDSET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "ddlRecordSet.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "ddlRecordSet.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(ddlRecordSet.uuid IS NULL OR ddlRecordSet.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "ddlRecordSet.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, DDLRecordSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, DDLRecordSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DDLRecordSetModelImpl.UUID_COLUMN_BITMASK |
			DDLRecordSetModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the d d l record sets where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the d d l record sets where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of d d l record sets
	 * @param end the upper bound of the range of d d l record sets (not inclusive)
	 * @return the range of matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the d d l record sets where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of d d l record sets
	 * @param end the upper bound of the range of d d l record sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<DDLRecordSet> list = (List<DDLRecordSet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DDLRecordSet ddlRecordSet : list) {
				if (!Validator.equals(uuid, ddlRecordSet.getUuid()) ||
						(companyId != ddlRecordSet.getCompanyId())) {
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

			query.append(_SQL_SELECT_DDLRECORDSET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DDLRecordSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<DDLRecordSet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DDLRecordSet>(list);
				}
				else {
					list = (List<DDLRecordSet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first d d l record set in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (ddlRecordSet != null) {
			return ddlRecordSet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordSetException(msg.toString());
	}

	/**
	 * Returns the first d d l record set in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DDLRecordSet> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last d d l record set in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (ddlRecordSet != null) {
			return ddlRecordSet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordSetException(msg.toString());
	}

	/**
	 * Returns the last d d l record set in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DDLRecordSet> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the d d l record sets before and after the current d d l record set in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param recordSetId the primary key of the current d d l record set
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a d d l record set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet[] findByUuid_C_PrevAndNext(long recordSetId,
		String uuid, long companyId, OrderByComparator orderByComparator)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = findByPrimaryKey(recordSetId);

		Session session = null;

		try {
			session = openSession();

			DDLRecordSet[] array = new DDLRecordSetImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, ddlRecordSet, uuid,
					companyId, orderByComparator, true);

			array[1] = ddlRecordSet;

			array[2] = getByUuid_C_PrevAndNext(session, ddlRecordSet, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDLRecordSet getByUuid_C_PrevAndNext(Session session,
		DDLRecordSet ddlRecordSet, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DDLRECORDSET_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(DDLRecordSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ddlRecordSet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DDLRecordSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the d d l record sets where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (DDLRecordSet ddlRecordSet : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ddlRecordSet);
		}
	}

	/**
	 * Returns the number of d d l record sets where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DDLRECORDSET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "ddlRecordSet.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "ddlRecordSet.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(ddlRecordSet.uuid IS NULL OR ddlRecordSet.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "ddlRecordSet.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, DDLRecordSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, DDLRecordSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			DDLRecordSetModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the d d l record sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the d d l record sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of d d l record sets
	 * @param end the upper bound of the range of d d l record sets (not inclusive)
	 * @return the range of matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the d d l record sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of d d l record sets
	 * @param end the upper bound of the range of d d l record sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<DDLRecordSet> list = (List<DDLRecordSet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DDLRecordSet ddlRecordSet : list) {
				if ((groupId != ddlRecordSet.getGroupId())) {
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

			query.append(_SQL_SELECT_DDLRECORDSET_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DDLRecordSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<DDLRecordSet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DDLRecordSet>(list);
				}
				else {
					list = (List<DDLRecordSet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first d d l record set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = fetchByGroupId_First(groupId,
				orderByComparator);

		if (ddlRecordSet != null) {
			return ddlRecordSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordSetException(msg.toString());
	}

	/**
	 * Returns the first d d l record set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DDLRecordSet> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last d d l record set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (ddlRecordSet != null) {
			return ddlRecordSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordSetException(msg.toString());
	}

	/**
	 * Returns the last d d l record set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<DDLRecordSet> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the d d l record sets before and after the current d d l record set in the ordered set where groupId = &#63;.
	 *
	 * @param recordSetId the primary key of the current d d l record set
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a d d l record set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet[] findByGroupId_PrevAndNext(long recordSetId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = findByPrimaryKey(recordSetId);

		Session session = null;

		try {
			session = openSession();

			DDLRecordSet[] array = new DDLRecordSetImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, ddlRecordSet, groupId,
					orderByComparator, true);

			array[1] = ddlRecordSet;

			array[2] = getByGroupId_PrevAndNext(session, ddlRecordSet, groupId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDLRecordSet getByGroupId_PrevAndNext(Session session,
		DDLRecordSet ddlRecordSet, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DDLRECORDSET_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(DDLRecordSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ddlRecordSet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DDLRecordSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the d d l record sets that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching d d l record sets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the d d l record sets that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of d d l record sets
	 * @param end the upper bound of the range of d d l record sets (not inclusive)
	 * @return the range of matching d d l record sets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> filterFindByGroupId(long groupId, int start,
		int end) throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the d d l record sets that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of d d l record sets
	 * @param end the upper bound of the range of d d l record sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching d d l record sets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_DDLRECORDSET_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_DDLRECORDSET_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_DDLRECORDSET_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(DDLRecordSetModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(DDLRecordSetModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				DDLRecordSet.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, DDLRecordSetImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, DDLRecordSetImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<DDLRecordSet>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the d d l record sets before and after the current d d l record set in the ordered set of d d l record sets that the user has permission to view where groupId = &#63;.
	 *
	 * @param recordSetId the primary key of the current d d l record set
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a d d l record set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet[] filterFindByGroupId_PrevAndNext(long recordSetId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchRecordSetException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(recordSetId, groupId,
				orderByComparator);
		}

		DDLRecordSet ddlRecordSet = findByPrimaryKey(recordSetId);

		Session session = null;

		try {
			session = openSession();

			DDLRecordSet[] array = new DDLRecordSetImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, ddlRecordSet,
					groupId, orderByComparator, true);

			array[1] = ddlRecordSet;

			array[2] = filterGetByGroupId_PrevAndNext(session, ddlRecordSet,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDLRecordSet filterGetByGroupId_PrevAndNext(Session session,
		DDLRecordSet ddlRecordSet, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_DDLRECORDSET_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_DDLRECORDSET_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_DDLRECORDSET_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(DDLRecordSetModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(DDLRecordSetModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				DDLRecordSet.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, DDLRecordSetImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, DDLRecordSetImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ddlRecordSet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DDLRecordSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the d d l record sets where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (DDLRecordSet ddlRecordSet : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ddlRecordSet);
		}
	}

	/**
	 * Returns the number of d d l record sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DDLRECORDSET_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	/**
	 * Returns the number of d d l record sets that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching d d l record sets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_DDLRECORDSET_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				DDLRecordSet.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "ddlRecordSet.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_R = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, DDLRecordSetImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_R",
			new String[] { Long.class.getName(), String.class.getName() },
			DDLRecordSetModelImpl.GROUPID_COLUMN_BITMASK |
			DDLRecordSetModelImpl.RECORDSETKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R = new FinderPath(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the d d l record set where groupId = &#63; and recordSetKey = &#63; or throws a {@link com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param recordSetKey the record set key
	 * @return the matching d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet findByG_R(long groupId, String recordSetKey)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = fetchByG_R(groupId, recordSetKey);

		if (ddlRecordSet == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", recordSetKey=");
			msg.append(recordSetKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRecordSetException(msg.toString());
		}

		return ddlRecordSet;
	}

	/**
	 * Returns the d d l record set where groupId = &#63; and recordSetKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param recordSetKey the record set key
	 * @return the matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByG_R(long groupId, String recordSetKey)
		throws SystemException {
		return fetchByG_R(groupId, recordSetKey, true);
	}

	/**
	 * Returns the d d l record set where groupId = &#63; and recordSetKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param recordSetKey the record set key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching d d l record set, or <code>null</code> if a matching d d l record set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByG_R(long groupId, String recordSetKey,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, recordSetKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_R,
					finderArgs, this);
		}

		if (result instanceof DDLRecordSet) {
			DDLRecordSet ddlRecordSet = (DDLRecordSet)result;

			if ((groupId != ddlRecordSet.getGroupId()) ||
					!Validator.equals(recordSetKey,
						ddlRecordSet.getRecordSetKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DDLRECORDSET_WHERE);

			query.append(_FINDER_COLUMN_G_R_GROUPID_2);

			boolean bindRecordSetKey = false;

			if (recordSetKey == null) {
				query.append(_FINDER_COLUMN_G_R_RECORDSETKEY_1);
			}
			else if (recordSetKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_R_RECORDSETKEY_3);
			}
			else {
				bindRecordSetKey = true;

				query.append(_FINDER_COLUMN_G_R_RECORDSETKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindRecordSetKey) {
					qPos.add(recordSetKey);
				}

				List<DDLRecordSet> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R,
						finderArgs, list);
				}
				else {
					DDLRecordSet ddlRecordSet = list.get(0);

					result = ddlRecordSet;

					cacheResult(ddlRecordSet);

					if ((ddlRecordSet.getGroupId() != groupId) ||
							(ddlRecordSet.getRecordSetKey() == null) ||
							!ddlRecordSet.getRecordSetKey().equals(recordSetKey)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R,
							finderArgs, ddlRecordSet);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_R,
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
			return (DDLRecordSet)result;
		}
	}

	/**
	 * Removes the d d l record set where groupId = &#63; and recordSetKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param recordSetKey the record set key
	 * @return the d d l record set that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet removeByG_R(long groupId, String recordSetKey)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = findByG_R(groupId, recordSetKey);

		return remove(ddlRecordSet);
	}

	/**
	 * Returns the number of d d l record sets where groupId = &#63; and recordSetKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param recordSetKey the record set key
	 * @return the number of matching d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_R(long groupId, String recordSetKey)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_R;

		Object[] finderArgs = new Object[] { groupId, recordSetKey };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DDLRECORDSET_WHERE);

			query.append(_FINDER_COLUMN_G_R_GROUPID_2);

			boolean bindRecordSetKey = false;

			if (recordSetKey == null) {
				query.append(_FINDER_COLUMN_G_R_RECORDSETKEY_1);
			}
			else if (recordSetKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_R_RECORDSETKEY_3);
			}
			else {
				bindRecordSetKey = true;

				query.append(_FINDER_COLUMN_G_R_RECORDSETKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindRecordSetKey) {
					qPos.add(recordSetKey);
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

	private static final String _FINDER_COLUMN_G_R_GROUPID_2 = "ddlRecordSet.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_RECORDSETKEY_1 = "ddlRecordSet.recordSetKey IS NULL";
	private static final String _FINDER_COLUMN_G_R_RECORDSETKEY_2 = "ddlRecordSet.recordSetKey = ?";
	private static final String _FINDER_COLUMN_G_R_RECORDSETKEY_3 = "(ddlRecordSet.recordSetKey IS NULL OR ddlRecordSet.recordSetKey = '')";

	public DDLRecordSetPersistenceImpl() {
		setModelClass(DDLRecordSet.class);
	}

	/**
	 * Caches the d d l record set in the entity cache if it is enabled.
	 *
	 * @param ddlRecordSet the d d l record set
	 */
	@Override
	public void cacheResult(DDLRecordSet ddlRecordSet) {
		EntityCacheUtil.putResult(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetImpl.class, ddlRecordSet.getPrimaryKey(), ddlRecordSet);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { ddlRecordSet.getUuid(), ddlRecordSet.getGroupId() },
			ddlRecordSet);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R,
			new Object[] {
				ddlRecordSet.getGroupId(), ddlRecordSet.getRecordSetKey()
			}, ddlRecordSet);

		ddlRecordSet.resetOriginalValues();
	}

	/**
	 * Caches the d d l record sets in the entity cache if it is enabled.
	 *
	 * @param ddlRecordSets the d d l record sets
	 */
	@Override
	public void cacheResult(List<DDLRecordSet> ddlRecordSets) {
		for (DDLRecordSet ddlRecordSet : ddlRecordSets) {
			if (EntityCacheUtil.getResult(
						DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
						DDLRecordSetImpl.class, ddlRecordSet.getPrimaryKey()) == null) {
				cacheResult(ddlRecordSet);
			}
			else {
				ddlRecordSet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all d d l record sets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DDLRecordSetImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DDLRecordSetImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the d d l record set.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DDLRecordSet ddlRecordSet) {
		EntityCacheUtil.removeResult(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetImpl.class, ddlRecordSet.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(ddlRecordSet);
	}

	@Override
	public void clearCache(List<DDLRecordSet> ddlRecordSets) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DDLRecordSet ddlRecordSet : ddlRecordSets) {
			EntityCacheUtil.removeResult(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
				DDLRecordSetImpl.class, ddlRecordSet.getPrimaryKey());

			clearUniqueFindersCache(ddlRecordSet);
		}
	}

	protected void cacheUniqueFindersCache(DDLRecordSet ddlRecordSet) {
		if (ddlRecordSet.isNew()) {
			Object[] args = new Object[] {
					ddlRecordSet.getUuid(), ddlRecordSet.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				ddlRecordSet);

			args = new Object[] {
					ddlRecordSet.getGroupId(), ddlRecordSet.getRecordSetKey()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_R, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R, args,
				ddlRecordSet);
		}
		else {
			DDLRecordSetModelImpl ddlRecordSetModelImpl = (DDLRecordSetModelImpl)ddlRecordSet;

			if ((ddlRecordSetModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ddlRecordSet.getUuid(), ddlRecordSet.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					ddlRecordSet);
			}

			if ((ddlRecordSetModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ddlRecordSet.getGroupId(),
						ddlRecordSet.getRecordSetKey()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_R, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R, args,
					ddlRecordSet);
			}
		}
	}

	protected void clearUniqueFindersCache(DDLRecordSet ddlRecordSet) {
		DDLRecordSetModelImpl ddlRecordSetModelImpl = (DDLRecordSetModelImpl)ddlRecordSet;

		Object[] args = new Object[] {
				ddlRecordSet.getUuid(), ddlRecordSet.getGroupId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((ddlRecordSetModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					ddlRecordSetModelImpl.getOriginalUuid(),
					ddlRecordSetModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] {
				ddlRecordSet.getGroupId(), ddlRecordSet.getRecordSetKey()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_R, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_R, args);

		if ((ddlRecordSetModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_R.getColumnBitmask()) != 0) {
			args = new Object[] {
					ddlRecordSetModelImpl.getOriginalGroupId(),
					ddlRecordSetModelImpl.getOriginalRecordSetKey()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_R, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_R, args);
		}
	}

	/**
	 * Creates a new d d l record set with the primary key. Does not add the d d l record set to the database.
	 *
	 * @param recordSetId the primary key for the new d d l record set
	 * @return the new d d l record set
	 */
	@Override
	public DDLRecordSet create(long recordSetId) {
		DDLRecordSet ddlRecordSet = new DDLRecordSetImpl();

		ddlRecordSet.setNew(true);
		ddlRecordSet.setPrimaryKey(recordSetId);

		String uuid = PortalUUIDUtil.generate();

		ddlRecordSet.setUuid(uuid);

		return ddlRecordSet;
	}

	/**
	 * Removes the d d l record set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param recordSetId the primary key of the d d l record set
	 * @return the d d l record set that was removed
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a d d l record set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet remove(long recordSetId)
		throws NoSuchRecordSetException, SystemException {
		return remove((Serializable)recordSetId);
	}

	/**
	 * Removes the d d l record set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the d d l record set
	 * @return the d d l record set that was removed
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a d d l record set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet remove(Serializable primaryKey)
		throws NoSuchRecordSetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DDLRecordSet ddlRecordSet = (DDLRecordSet)session.get(DDLRecordSetImpl.class,
					primaryKey);

			if (ddlRecordSet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRecordSetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ddlRecordSet);
		}
		catch (NoSuchRecordSetException nsee) {
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
	protected DDLRecordSet removeImpl(DDLRecordSet ddlRecordSet)
		throws SystemException {
		ddlRecordSet = toUnwrappedModel(ddlRecordSet);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddlRecordSet)) {
				ddlRecordSet = (DDLRecordSet)session.get(DDLRecordSetImpl.class,
						ddlRecordSet.getPrimaryKeyObj());
			}

			if (ddlRecordSet != null) {
				session.delete(ddlRecordSet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ddlRecordSet != null) {
			clearCache(ddlRecordSet);
		}

		return ddlRecordSet;
	}

	@Override
	public DDLRecordSet updateImpl(
		com.liferay.portlet.dynamicdatalists.model.DDLRecordSet ddlRecordSet)
		throws SystemException {
		ddlRecordSet = toUnwrappedModel(ddlRecordSet);

		boolean isNew = ddlRecordSet.isNew();

		DDLRecordSetModelImpl ddlRecordSetModelImpl = (DDLRecordSetModelImpl)ddlRecordSet;

		if (Validator.isNull(ddlRecordSet.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			ddlRecordSet.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (ddlRecordSet.isNew()) {
				session.save(ddlRecordSet);

				ddlRecordSet.setNew(false);
			}
			else {
				session.merge(ddlRecordSet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DDLRecordSetModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ddlRecordSetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ddlRecordSetModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { ddlRecordSetModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((ddlRecordSetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ddlRecordSetModelImpl.getOriginalUuid(),
						ddlRecordSetModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						ddlRecordSetModelImpl.getUuid(),
						ddlRecordSetModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((ddlRecordSetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ddlRecordSetModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { ddlRecordSetModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		EntityCacheUtil.putResult(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
			DDLRecordSetImpl.class, ddlRecordSet.getPrimaryKey(), ddlRecordSet);

		clearUniqueFindersCache(ddlRecordSet);
		cacheUniqueFindersCache(ddlRecordSet);

		return ddlRecordSet;
	}

	protected DDLRecordSet toUnwrappedModel(DDLRecordSet ddlRecordSet) {
		if (ddlRecordSet instanceof DDLRecordSetImpl) {
			return ddlRecordSet;
		}

		DDLRecordSetImpl ddlRecordSetImpl = new DDLRecordSetImpl();

		ddlRecordSetImpl.setNew(ddlRecordSet.isNew());
		ddlRecordSetImpl.setPrimaryKey(ddlRecordSet.getPrimaryKey());

		ddlRecordSetImpl.setUuid(ddlRecordSet.getUuid());
		ddlRecordSetImpl.setRecordSetId(ddlRecordSet.getRecordSetId());
		ddlRecordSetImpl.setGroupId(ddlRecordSet.getGroupId());
		ddlRecordSetImpl.setCompanyId(ddlRecordSet.getCompanyId());
		ddlRecordSetImpl.setUserId(ddlRecordSet.getUserId());
		ddlRecordSetImpl.setUserName(ddlRecordSet.getUserName());
		ddlRecordSetImpl.setCreateDate(ddlRecordSet.getCreateDate());
		ddlRecordSetImpl.setModifiedDate(ddlRecordSet.getModifiedDate());
		ddlRecordSetImpl.setDDMStructureId(ddlRecordSet.getDDMStructureId());
		ddlRecordSetImpl.setRecordSetKey(ddlRecordSet.getRecordSetKey());
		ddlRecordSetImpl.setName(ddlRecordSet.getName());
		ddlRecordSetImpl.setDescription(ddlRecordSet.getDescription());
		ddlRecordSetImpl.setMinDisplayRows(ddlRecordSet.getMinDisplayRows());
		ddlRecordSetImpl.setScope(ddlRecordSet.getScope());

		return ddlRecordSetImpl;
	}

	/**
	 * Returns the d d l record set with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the d d l record set
	 * @return the d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a d d l record set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRecordSetException, SystemException {
		DDLRecordSet ddlRecordSet = fetchByPrimaryKey(primaryKey);

		if (ddlRecordSet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRecordSetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ddlRecordSet;
	}

	/**
	 * Returns the d d l record set with the primary key or throws a {@link com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException} if it could not be found.
	 *
	 * @param recordSetId the primary key of the d d l record set
	 * @return the d d l record set
	 * @throws com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException if a d d l record set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet findByPrimaryKey(long recordSetId)
		throws NoSuchRecordSetException, SystemException {
		return findByPrimaryKey((Serializable)recordSetId);
	}

	/**
	 * Returns the d d l record set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the d d l record set
	 * @return the d d l record set, or <code>null</code> if a d d l record set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		DDLRecordSet ddlRecordSet = (DDLRecordSet)EntityCacheUtil.getResult(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
				DDLRecordSetImpl.class, primaryKey);

		if (ddlRecordSet == _nullDDLRecordSet) {
			return null;
		}

		if (ddlRecordSet == null) {
			Session session = null;

			try {
				session = openSession();

				ddlRecordSet = (DDLRecordSet)session.get(DDLRecordSetImpl.class,
						primaryKey);

				if (ddlRecordSet != null) {
					cacheResult(ddlRecordSet);
				}
				else {
					EntityCacheUtil.putResult(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
						DDLRecordSetImpl.class, primaryKey, _nullDDLRecordSet);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DDLRecordSetModelImpl.ENTITY_CACHE_ENABLED,
					DDLRecordSetImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ddlRecordSet;
	}

	/**
	 * Returns the d d l record set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param recordSetId the primary key of the d d l record set
	 * @return the d d l record set, or <code>null</code> if a d d l record set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDLRecordSet fetchByPrimaryKey(long recordSetId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)recordSetId);
	}

	/**
	 * Returns all the d d l record sets.
	 *
	 * @return the d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the d d l record sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d l record sets
	 * @param end the upper bound of the range of d d l record sets (not inclusive)
	 * @return the range of d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the d d l record sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d l record sets
	 * @param end the upper bound of the range of d d l record sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of d d l record sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDLRecordSet> findAll(int start, int end,
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

		List<DDLRecordSet> list = (List<DDLRecordSet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DDLRECORDSET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DDLRECORDSET;

				if (pagination) {
					sql = sql.concat(DDLRecordSetModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DDLRecordSet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DDLRecordSet>(list);
				}
				else {
					list = (List<DDLRecordSet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the d d l record sets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (DDLRecordSet ddlRecordSet : findAll()) {
			remove(ddlRecordSet);
		}
	}

	/**
	 * Returns the number of d d l record sets.
	 *
	 * @return the number of d d l record sets
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

				Query q = session.createQuery(_SQL_COUNT_DDLRECORDSET);

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

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the d d l record set persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.dynamicdatalists.model.DDLRecordSet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DDLRecordSet>> listenersList = new ArrayList<ModelListener<DDLRecordSet>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DDLRecordSet>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(DDLRecordSetImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DDLRECORDSET = "SELECT ddlRecordSet FROM DDLRecordSet ddlRecordSet";
	private static final String _SQL_SELECT_DDLRECORDSET_WHERE = "SELECT ddlRecordSet FROM DDLRecordSet ddlRecordSet WHERE ";
	private static final String _SQL_COUNT_DDLRECORDSET = "SELECT COUNT(ddlRecordSet) FROM DDLRecordSet ddlRecordSet";
	private static final String _SQL_COUNT_DDLRECORDSET_WHERE = "SELECT COUNT(ddlRecordSet) FROM DDLRecordSet ddlRecordSet WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "ddlRecordSet.recordSetId";
	private static final String _FILTER_SQL_SELECT_DDLRECORDSET_WHERE = "SELECT DISTINCT {ddlRecordSet.*} FROM DDLRecordSet ddlRecordSet WHERE ";
	private static final String _FILTER_SQL_SELECT_DDLRECORDSET_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {DDLRecordSet.*} FROM (SELECT DISTINCT ddlRecordSet.recordSetId FROM DDLRecordSet ddlRecordSet WHERE ";
	private static final String _FILTER_SQL_SELECT_DDLRECORDSET_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN DDLRecordSet ON TEMP_TABLE.recordSetId = DDLRecordSet.recordSetId";
	private static final String _FILTER_SQL_COUNT_DDLRECORDSET_WHERE = "SELECT COUNT(DISTINCT ddlRecordSet.recordSetId) AS COUNT_VALUE FROM DDLRecordSet ddlRecordSet WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "ddlRecordSet";
	private static final String _FILTER_ENTITY_TABLE = "DDLRecordSet";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ddlRecordSet.";
	private static final String _ORDER_BY_ENTITY_TABLE = "DDLRecordSet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DDLRecordSet exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DDLRecordSet exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(DDLRecordSetPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static DDLRecordSet _nullDDLRecordSet = new DDLRecordSetImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DDLRecordSet> toCacheModel() {
				return _nullDDLRecordSetCacheModel;
			}
		};

	private static CacheModel<DDLRecordSet> _nullDDLRecordSetCacheModel = new CacheModel<DDLRecordSet>() {
			@Override
			public DDLRecordSet toEntityModel() {
				return _nullDDLRecordSet;
			}
		};
}