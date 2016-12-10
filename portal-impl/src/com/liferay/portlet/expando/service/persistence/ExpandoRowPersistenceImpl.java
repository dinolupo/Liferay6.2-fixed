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

package com.liferay.portlet.expando.service.persistence;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.expando.NoSuchRowException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.impl.ExpandoRowImpl;
import com.liferay.portlet.expando.model.impl.ExpandoRowModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the expando row service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoRowPersistence
 * @see ExpandoRowUtil
 * @generated
 */
public class ExpandoRowPersistenceImpl extends BasePersistenceImpl<ExpandoRow>
	implements ExpandoRowPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExpandoRowUtil} to access the expando row persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExpandoRowImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowModelImpl.FINDER_CACHE_ENABLED, ExpandoRowImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowModelImpl.FINDER_CACHE_ENABLED, ExpandoRowImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TABLEID = new FinderPath(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowModelImpl.FINDER_CACHE_ENABLED, ExpandoRowImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTableId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TABLEID =
		new FinderPath(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowModelImpl.FINDER_CACHE_ENABLED, ExpandoRowImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTableId",
			new String[] { Long.class.getName() },
			ExpandoRowModelImpl.TABLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TABLEID = new FinderPath(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTableId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the expando rows where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @return the matching expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoRow> findByTableId(long tableId)
		throws SystemException {
		return findByTableId(tableId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the expando rows where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.expando.model.impl.ExpandoRowModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param start the lower bound of the range of expando rows
	 * @param end the upper bound of the range of expando rows (not inclusive)
	 * @return the range of matching expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoRow> findByTableId(long tableId, int start, int end)
		throws SystemException {
		return findByTableId(tableId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando rows where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.expando.model.impl.ExpandoRowModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param start the lower bound of the range of expando rows
	 * @param end the upper bound of the range of expando rows (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoRow> findByTableId(long tableId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TABLEID;
			finderArgs = new Object[] { tableId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TABLEID;
			finderArgs = new Object[] { tableId, start, end, orderByComparator };
		}

		List<ExpandoRow> list = (List<ExpandoRow>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ExpandoRow expandoRow : list) {
				if ((tableId != expandoRow.getTableId())) {
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

			query.append(_SQL_SELECT_EXPANDOROW_WHERE);

			query.append(_FINDER_COLUMN_TABLEID_TABLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ExpandoRowModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tableId);

				if (!pagination) {
					list = (List<ExpandoRow>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ExpandoRow>(list);
				}
				else {
					list = (List<ExpandoRow>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first expando row in the ordered set where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando row
	 * @throws com.liferay.portlet.expando.NoSuchRowException if a matching expando row could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow findByTableId_First(long tableId,
		OrderByComparator orderByComparator)
		throws NoSuchRowException, SystemException {
		ExpandoRow expandoRow = fetchByTableId_First(tableId, orderByComparator);

		if (expandoRow != null) {
			return expandoRow;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("tableId=");
		msg.append(tableId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRowException(msg.toString());
	}

	/**
	 * Returns the first expando row in the ordered set where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando row, or <code>null</code> if a matching expando row could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow fetchByTableId_First(long tableId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ExpandoRow> list = findByTableId(tableId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last expando row in the ordered set where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando row
	 * @throws com.liferay.portlet.expando.NoSuchRowException if a matching expando row could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow findByTableId_Last(long tableId,
		OrderByComparator orderByComparator)
		throws NoSuchRowException, SystemException {
		ExpandoRow expandoRow = fetchByTableId_Last(tableId, orderByComparator);

		if (expandoRow != null) {
			return expandoRow;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("tableId=");
		msg.append(tableId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRowException(msg.toString());
	}

	/**
	 * Returns the last expando row in the ordered set where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando row, or <code>null</code> if a matching expando row could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow fetchByTableId_Last(long tableId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTableId(tableId);

		if (count == 0) {
			return null;
		}

		List<ExpandoRow> list = findByTableId(tableId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the expando rows before and after the current expando row in the ordered set where tableId = &#63;.
	 *
	 * @param rowId the primary key of the current expando row
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando row
	 * @throws com.liferay.portlet.expando.NoSuchRowException if a expando row with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow[] findByTableId_PrevAndNext(long rowId, long tableId,
		OrderByComparator orderByComparator)
		throws NoSuchRowException, SystemException {
		ExpandoRow expandoRow = findByPrimaryKey(rowId);

		Session session = null;

		try {
			session = openSession();

			ExpandoRow[] array = new ExpandoRowImpl[3];

			array[0] = getByTableId_PrevAndNext(session, expandoRow, tableId,
					orderByComparator, true);

			array[1] = expandoRow;

			array[2] = getByTableId_PrevAndNext(session, expandoRow, tableId,
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

	protected ExpandoRow getByTableId_PrevAndNext(Session session,
		ExpandoRow expandoRow, long tableId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EXPANDOROW_WHERE);

		query.append(_FINDER_COLUMN_TABLEID_TABLEID_2);

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
			query.append(ExpandoRowModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(tableId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(expandoRow);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExpandoRow> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the expando rows where tableId = &#63; from the database.
	 *
	 * @param tableId the table ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByTableId(long tableId) throws SystemException {
		for (ExpandoRow expandoRow : findByTableId(tableId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(expandoRow);
		}
	}

	/**
	 * Returns the number of expando rows where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @return the number of matching expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByTableId(long tableId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TABLEID;

		Object[] finderArgs = new Object[] { tableId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EXPANDOROW_WHERE);

			query.append(_FINDER_COLUMN_TABLEID_TABLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tableId);

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

	private static final String _FINDER_COLUMN_TABLEID_TABLEID_2 = "expandoRow.tableId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSPK = new FinderPath(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowModelImpl.FINDER_CACHE_ENABLED, ExpandoRowImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByClassPK",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSPK =
		new FinderPath(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowModelImpl.FINDER_CACHE_ENABLED, ExpandoRowImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByClassPK",
			new String[] { Long.class.getName() },
			ExpandoRowModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSPK = new FinderPath(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByClassPK",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the expando rows where classPK = &#63;.
	 *
	 * @param classPK the class p k
	 * @return the matching expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoRow> findByClassPK(long classPK)
		throws SystemException {
		return findByClassPK(classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the expando rows where classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.expando.model.impl.ExpandoRowModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classPK the class p k
	 * @param start the lower bound of the range of expando rows
	 * @param end the upper bound of the range of expando rows (not inclusive)
	 * @return the range of matching expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoRow> findByClassPK(long classPK, int start, int end)
		throws SystemException {
		return findByClassPK(classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando rows where classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.expando.model.impl.ExpandoRowModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classPK the class p k
	 * @param start the lower bound of the range of expando rows
	 * @param end the upper bound of the range of expando rows (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoRow> findByClassPK(long classPK, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSPK;
			finderArgs = new Object[] { classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSPK;
			finderArgs = new Object[] { classPK, start, end, orderByComparator };
		}

		List<ExpandoRow> list = (List<ExpandoRow>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ExpandoRow expandoRow : list) {
				if ((classPK != expandoRow.getClassPK())) {
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

			query.append(_SQL_SELECT_EXPANDOROW_WHERE);

			query.append(_FINDER_COLUMN_CLASSPK_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ExpandoRowModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<ExpandoRow>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ExpandoRow>(list);
				}
				else {
					list = (List<ExpandoRow>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first expando row in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando row
	 * @throws com.liferay.portlet.expando.NoSuchRowException if a matching expando row could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow findByClassPK_First(long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchRowException, SystemException {
		ExpandoRow expandoRow = fetchByClassPK_First(classPK, orderByComparator);

		if (expandoRow != null) {
			return expandoRow;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRowException(msg.toString());
	}

	/**
	 * Returns the first expando row in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando row, or <code>null</code> if a matching expando row could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow fetchByClassPK_First(long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<ExpandoRow> list = findByClassPK(classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last expando row in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando row
	 * @throws com.liferay.portlet.expando.NoSuchRowException if a matching expando row could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow findByClassPK_Last(long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchRowException, SystemException {
		ExpandoRow expandoRow = fetchByClassPK_Last(classPK, orderByComparator);

		if (expandoRow != null) {
			return expandoRow;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRowException(msg.toString());
	}

	/**
	 * Returns the last expando row in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando row, or <code>null</code> if a matching expando row could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow fetchByClassPK_Last(long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByClassPK(classPK);

		if (count == 0) {
			return null;
		}

		List<ExpandoRow> list = findByClassPK(classPK, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the expando rows before and after the current expando row in the ordered set where classPK = &#63;.
	 *
	 * @param rowId the primary key of the current expando row
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando row
	 * @throws com.liferay.portlet.expando.NoSuchRowException if a expando row with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow[] findByClassPK_PrevAndNext(long rowId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchRowException, SystemException {
		ExpandoRow expandoRow = findByPrimaryKey(rowId);

		Session session = null;

		try {
			session = openSession();

			ExpandoRow[] array = new ExpandoRowImpl[3];

			array[0] = getByClassPK_PrevAndNext(session, expandoRow, classPK,
					orderByComparator, true);

			array[1] = expandoRow;

			array[2] = getByClassPK_PrevAndNext(session, expandoRow, classPK,
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

	protected ExpandoRow getByClassPK_PrevAndNext(Session session,
		ExpandoRow expandoRow, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EXPANDOROW_WHERE);

		query.append(_FINDER_COLUMN_CLASSPK_CLASSPK_2);

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
			query.append(ExpandoRowModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(expandoRow);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExpandoRow> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the expando rows where classPK = &#63; from the database.
	 *
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByClassPK(long classPK) throws SystemException {
		for (ExpandoRow expandoRow : findByClassPK(classPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(expandoRow);
		}
	}

	/**
	 * Returns the number of expando rows where classPK = &#63;.
	 *
	 * @param classPK the class p k
	 * @return the number of matching expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByClassPK(long classPK) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSPK;

		Object[] finderArgs = new Object[] { classPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EXPANDOROW_WHERE);

			query.append(_FINDER_COLUMN_CLASSPK_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_CLASSPK_CLASSPK_2 = "expandoRow.classPK = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_T_C = new FinderPath(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowModelImpl.FINDER_CACHE_ENABLED, ExpandoRowImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByT_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			ExpandoRowModelImpl.TABLEID_COLUMN_BITMASK |
			ExpandoRowModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_T_C = new FinderPath(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByT_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the expando row where tableId = &#63; and classPK = &#63; or throws a {@link com.liferay.portlet.expando.NoSuchRowException} if it could not be found.
	 *
	 * @param tableId the table ID
	 * @param classPK the class p k
	 * @return the matching expando row
	 * @throws com.liferay.portlet.expando.NoSuchRowException if a matching expando row could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow findByT_C(long tableId, long classPK)
		throws NoSuchRowException, SystemException {
		ExpandoRow expandoRow = fetchByT_C(tableId, classPK);

		if (expandoRow == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("tableId=");
			msg.append(tableId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRowException(msg.toString());
		}

		return expandoRow;
	}

	/**
	 * Returns the expando row where tableId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param tableId the table ID
	 * @param classPK the class p k
	 * @return the matching expando row, or <code>null</code> if a matching expando row could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow fetchByT_C(long tableId, long classPK)
		throws SystemException {
		return fetchByT_C(tableId, classPK, true);
	}

	/**
	 * Returns the expando row where tableId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param tableId the table ID
	 * @param classPK the class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching expando row, or <code>null</code> if a matching expando row could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow fetchByT_C(long tableId, long classPK,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { tableId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_T_C,
					finderArgs, this);
		}

		if (result instanceof ExpandoRow) {
			ExpandoRow expandoRow = (ExpandoRow)result;

			if ((tableId != expandoRow.getTableId()) ||
					(classPK != expandoRow.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EXPANDOROW_WHERE);

			query.append(_FINDER_COLUMN_T_C_TABLEID_2);

			query.append(_FINDER_COLUMN_T_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tableId);

				qPos.add(classPK);

				List<ExpandoRow> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_T_C,
						finderArgs, list);
				}
				else {
					ExpandoRow expandoRow = list.get(0);

					result = expandoRow;

					cacheResult(expandoRow);

					if ((expandoRow.getTableId() != tableId) ||
							(expandoRow.getClassPK() != classPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_T_C,
							finderArgs, expandoRow);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_T_C,
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
			return (ExpandoRow)result;
		}
	}

	/**
	 * Removes the expando row where tableId = &#63; and classPK = &#63; from the database.
	 *
	 * @param tableId the table ID
	 * @param classPK the class p k
	 * @return the expando row that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow removeByT_C(long tableId, long classPK)
		throws NoSuchRowException, SystemException {
		ExpandoRow expandoRow = findByT_C(tableId, classPK);

		return remove(expandoRow);
	}

	/**
	 * Returns the number of expando rows where tableId = &#63; and classPK = &#63;.
	 *
	 * @param tableId the table ID
	 * @param classPK the class p k
	 * @return the number of matching expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByT_C(long tableId, long classPK) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_T_C;

		Object[] finderArgs = new Object[] { tableId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EXPANDOROW_WHERE);

			query.append(_FINDER_COLUMN_T_C_TABLEID_2);

			query.append(_FINDER_COLUMN_T_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tableId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_T_C_TABLEID_2 = "expandoRow.tableId = ? AND ";
	private static final String _FINDER_COLUMN_T_C_CLASSPK_2 = "expandoRow.classPK = ?";

	public ExpandoRowPersistenceImpl() {
		setModelClass(ExpandoRow.class);
	}

	/**
	 * Caches the expando row in the entity cache if it is enabled.
	 *
	 * @param expandoRow the expando row
	 */
	@Override
	public void cacheResult(ExpandoRow expandoRow) {
		EntityCacheUtil.putResult(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowImpl.class, expandoRow.getPrimaryKey(), expandoRow);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_T_C,
			new Object[] { expandoRow.getTableId(), expandoRow.getClassPK() },
			expandoRow);

		expandoRow.resetOriginalValues();
	}

	/**
	 * Caches the expando rows in the entity cache if it is enabled.
	 *
	 * @param expandoRows the expando rows
	 */
	@Override
	public void cacheResult(List<ExpandoRow> expandoRows) {
		for (ExpandoRow expandoRow : expandoRows) {
			if (EntityCacheUtil.getResult(
						ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
						ExpandoRowImpl.class, expandoRow.getPrimaryKey()) == null) {
				cacheResult(expandoRow);
			}
			else {
				expandoRow.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all expando rows.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExpandoRowImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ExpandoRowImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the expando row.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExpandoRow expandoRow) {
		EntityCacheUtil.removeResult(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowImpl.class, expandoRow.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(expandoRow);
	}

	@Override
	public void clearCache(List<ExpandoRow> expandoRows) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ExpandoRow expandoRow : expandoRows) {
			EntityCacheUtil.removeResult(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
				ExpandoRowImpl.class, expandoRow.getPrimaryKey());

			clearUniqueFindersCache(expandoRow);
		}
	}

	protected void cacheUniqueFindersCache(ExpandoRow expandoRow) {
		if (expandoRow.isNew()) {
			Object[] args = new Object[] {
					expandoRow.getTableId(), expandoRow.getClassPK()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_T_C, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_T_C, args, expandoRow);
		}
		else {
			ExpandoRowModelImpl expandoRowModelImpl = (ExpandoRowModelImpl)expandoRow;

			if ((expandoRowModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_T_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						expandoRow.getTableId(), expandoRow.getClassPK()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_T_C, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_T_C, args,
					expandoRow);
			}
		}
	}

	protected void clearUniqueFindersCache(ExpandoRow expandoRow) {
		ExpandoRowModelImpl expandoRowModelImpl = (ExpandoRowModelImpl)expandoRow;

		Object[] args = new Object[] {
				expandoRow.getTableId(), expandoRow.getClassPK()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_C, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_T_C, args);

		if ((expandoRowModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_T_C.getColumnBitmask()) != 0) {
			args = new Object[] {
					expandoRowModelImpl.getOriginalTableId(),
					expandoRowModelImpl.getOriginalClassPK()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_C, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_T_C, args);
		}
	}

	/**
	 * Creates a new expando row with the primary key. Does not add the expando row to the database.
	 *
	 * @param rowId the primary key for the new expando row
	 * @return the new expando row
	 */
	@Override
	public ExpandoRow create(long rowId) {
		ExpandoRow expandoRow = new ExpandoRowImpl();

		expandoRow.setNew(true);
		expandoRow.setPrimaryKey(rowId);

		return expandoRow;
	}

	/**
	 * Removes the expando row with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rowId the primary key of the expando row
	 * @return the expando row that was removed
	 * @throws com.liferay.portlet.expando.NoSuchRowException if a expando row with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow remove(long rowId)
		throws NoSuchRowException, SystemException {
		return remove((Serializable)rowId);
	}

	/**
	 * Removes the expando row with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the expando row
	 * @return the expando row that was removed
	 * @throws com.liferay.portlet.expando.NoSuchRowException if a expando row with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow remove(Serializable primaryKey)
		throws NoSuchRowException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ExpandoRow expandoRow = (ExpandoRow)session.get(ExpandoRowImpl.class,
					primaryKey);

			if (expandoRow == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRowException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(expandoRow);
		}
		catch (NoSuchRowException nsee) {
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
	protected ExpandoRow removeImpl(ExpandoRow expandoRow)
		throws SystemException {
		expandoRow = toUnwrappedModel(expandoRow);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(expandoRow)) {
				expandoRow = (ExpandoRow)session.get(ExpandoRowImpl.class,
						expandoRow.getPrimaryKeyObj());
			}

			if (expandoRow != null) {
				session.delete(expandoRow);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (expandoRow != null) {
			clearCache(expandoRow);
		}

		return expandoRow;
	}

	@Override
	public ExpandoRow updateImpl(
		com.liferay.portlet.expando.model.ExpandoRow expandoRow)
		throws SystemException {
		expandoRow = toUnwrappedModel(expandoRow);

		boolean isNew = expandoRow.isNew();

		ExpandoRowModelImpl expandoRowModelImpl = (ExpandoRowModelImpl)expandoRow;

		Session session = null;

		try {
			session = openSession();

			if (expandoRow.isNew()) {
				session.save(expandoRow);

				expandoRow.setNew(false);
			}
			else {
				session.merge(expandoRow);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ExpandoRowModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((expandoRowModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TABLEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						expandoRowModelImpl.getOriginalTableId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TABLEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TABLEID,
					args);

				args = new Object[] { expandoRowModelImpl.getTableId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TABLEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TABLEID,
					args);
			}

			if ((expandoRowModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						expandoRowModelImpl.getOriginalClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSPK,
					args);

				args = new Object[] { expandoRowModelImpl.getClassPK() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSPK,
					args);
			}
		}

		EntityCacheUtil.putResult(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoRowImpl.class, expandoRow.getPrimaryKey(), expandoRow);

		clearUniqueFindersCache(expandoRow);
		cacheUniqueFindersCache(expandoRow);

		return expandoRow;
	}

	protected ExpandoRow toUnwrappedModel(ExpandoRow expandoRow) {
		if (expandoRow instanceof ExpandoRowImpl) {
			return expandoRow;
		}

		ExpandoRowImpl expandoRowImpl = new ExpandoRowImpl();

		expandoRowImpl.setNew(expandoRow.isNew());
		expandoRowImpl.setPrimaryKey(expandoRow.getPrimaryKey());

		expandoRowImpl.setRowId(expandoRow.getRowId());
		expandoRowImpl.setCompanyId(expandoRow.getCompanyId());
		expandoRowImpl.setModifiedDate(expandoRow.getModifiedDate());
		expandoRowImpl.setTableId(expandoRow.getTableId());
		expandoRowImpl.setClassPK(expandoRow.getClassPK());

		return expandoRowImpl;
	}

	/**
	 * Returns the expando row with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the expando row
	 * @return the expando row
	 * @throws com.liferay.portlet.expando.NoSuchRowException if a expando row with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRowException, SystemException {
		ExpandoRow expandoRow = fetchByPrimaryKey(primaryKey);

		if (expandoRow == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRowException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return expandoRow;
	}

	/**
	 * Returns the expando row with the primary key or throws a {@link com.liferay.portlet.expando.NoSuchRowException} if it could not be found.
	 *
	 * @param rowId the primary key of the expando row
	 * @return the expando row
	 * @throws com.liferay.portlet.expando.NoSuchRowException if a expando row with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow findByPrimaryKey(long rowId)
		throws NoSuchRowException, SystemException {
		return findByPrimaryKey((Serializable)rowId);
	}

	/**
	 * Returns the expando row with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the expando row
	 * @return the expando row, or <code>null</code> if a expando row with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ExpandoRow expandoRow = (ExpandoRow)EntityCacheUtil.getResult(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
				ExpandoRowImpl.class, primaryKey);

		if (expandoRow == _nullExpandoRow) {
			return null;
		}

		if (expandoRow == null) {
			Session session = null;

			try {
				session = openSession();

				expandoRow = (ExpandoRow)session.get(ExpandoRowImpl.class,
						primaryKey);

				if (expandoRow != null) {
					cacheResult(expandoRow);
				}
				else {
					EntityCacheUtil.putResult(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
						ExpandoRowImpl.class, primaryKey, _nullExpandoRow);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ExpandoRowModelImpl.ENTITY_CACHE_ENABLED,
					ExpandoRowImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return expandoRow;
	}

	/**
	 * Returns the expando row with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rowId the primary key of the expando row
	 * @return the expando row, or <code>null</code> if a expando row with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoRow fetchByPrimaryKey(long rowId) throws SystemException {
		return fetchByPrimaryKey((Serializable)rowId);
	}

	/**
	 * Returns all the expando rows.
	 *
	 * @return the expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoRow> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the expando rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.expando.model.impl.ExpandoRowModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of expando rows
	 * @param end the upper bound of the range of expando rows (not inclusive)
	 * @return the range of expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoRow> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.expando.model.impl.ExpandoRowModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of expando rows
	 * @param end the upper bound of the range of expando rows (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of expando rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoRow> findAll(int start, int end,
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

		List<ExpandoRow> list = (List<ExpandoRow>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EXPANDOROW);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXPANDOROW;

				if (pagination) {
					sql = sql.concat(ExpandoRowModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ExpandoRow>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ExpandoRow>(list);
				}
				else {
					list = (List<ExpandoRow>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the expando rows from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ExpandoRow expandoRow : findAll()) {
			remove(expandoRow);
		}
	}

	/**
	 * Returns the number of expando rows.
	 *
	 * @return the number of expando rows
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

				Query q = session.createQuery(_SQL_COUNT_EXPANDOROW);

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
	 * Initializes the expando row persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.expando.model.ExpandoRow")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ExpandoRow>> listenersList = new ArrayList<ModelListener<ExpandoRow>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ExpandoRow>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ExpandoRowImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_EXPANDOROW = "SELECT expandoRow FROM ExpandoRow expandoRow";
	private static final String _SQL_SELECT_EXPANDOROW_WHERE = "SELECT expandoRow FROM ExpandoRow expandoRow WHERE ";
	private static final String _SQL_COUNT_EXPANDOROW = "SELECT COUNT(expandoRow) FROM ExpandoRow expandoRow";
	private static final String _SQL_COUNT_EXPANDOROW_WHERE = "SELECT COUNT(expandoRow) FROM ExpandoRow expandoRow WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "expandoRow.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ExpandoRow exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ExpandoRow exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(ExpandoRowPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"rowId"
			});
	private static ExpandoRow _nullExpandoRow = new ExpandoRowImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ExpandoRow> toCacheModel() {
				return _nullExpandoRowCacheModel;
			}
		};

	private static CacheModel<ExpandoRow> _nullExpandoRowCacheModel = new CacheModel<ExpandoRow>() {
			@Override
			public ExpandoRow toEntityModel() {
				return _nullExpandoRow;
			}
		};
}