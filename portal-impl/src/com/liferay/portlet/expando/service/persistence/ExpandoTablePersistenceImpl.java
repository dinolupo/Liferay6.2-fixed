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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.impl.ExpandoTableImpl;
import com.liferay.portlet.expando.model.impl.ExpandoTableModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the expando table service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoTablePersistence
 * @see ExpandoTableUtil
 * @generated
 */
public class ExpandoTablePersistenceImpl extends BasePersistenceImpl<ExpandoTable>
	implements ExpandoTablePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExpandoTableUtil} to access the expando table persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExpandoTableImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoTableModelImpl.FINDER_CACHE_ENABLED, ExpandoTableImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoTableModelImpl.FINDER_CACHE_ENABLED, ExpandoTableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoTableModelImpl.FINDER_CACHE_ENABLED, ExpandoTableImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoTableModelImpl.FINDER_CACHE_ENABLED, ExpandoTableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			ExpandoTableModelImpl.COMPANYID_COLUMN_BITMASK |
			ExpandoTableModelImpl.CLASSNAMEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the expando tables where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @return the matching expando tables
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoTable> findByC_C(long companyId, long classNameId)
		throws SystemException {
		return findByC_C(companyId, classNameId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the expando tables where companyId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.expando.model.impl.ExpandoTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of expando tables
	 * @param end the upper bound of the range of expando tables (not inclusive)
	 * @return the range of matching expando tables
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoTable> findByC_C(long companyId, long classNameId,
		int start, int end) throws SystemException {
		return findByC_C(companyId, classNameId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando tables where companyId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.expando.model.impl.ExpandoTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of expando tables
	 * @param end the upper bound of the range of expando tables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando tables
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoTable> findByC_C(long companyId, long classNameId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] { companyId, classNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] {
					companyId, classNameId,
					
					start, end, orderByComparator
				};
		}

		List<ExpandoTable> list = (List<ExpandoTable>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ExpandoTable expandoTable : list) {
				if ((companyId != expandoTable.getCompanyId()) ||
						(classNameId != expandoTable.getClassNameId())) {
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

			query.append(_SQL_SELECT_EXPANDOTABLE_WHERE);

			query.append(_FINDER_COLUMN_C_C_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ExpandoTableModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(classNameId);

				if (!pagination) {
					list = (List<ExpandoTable>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ExpandoTable>(list);
				}
				else {
					list = (List<ExpandoTable>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first expando table in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando table
	 * @throws com.liferay.portlet.expando.NoSuchTableException if a matching expando table could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable findByC_C_First(long companyId, long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchTableException, SystemException {
		ExpandoTable expandoTable = fetchByC_C_First(companyId, classNameId,
				orderByComparator);

		if (expandoTable != null) {
			return expandoTable;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTableException(msg.toString());
	}

	/**
	 * Returns the first expando table in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando table, or <code>null</code> if a matching expando table could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable fetchByC_C_First(long companyId, long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ExpandoTable> list = findByC_C(companyId, classNameId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last expando table in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando table
	 * @throws com.liferay.portlet.expando.NoSuchTableException if a matching expando table could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable findByC_C_Last(long companyId, long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchTableException, SystemException {
		ExpandoTable expandoTable = fetchByC_C_Last(companyId, classNameId,
				orderByComparator);

		if (expandoTable != null) {
			return expandoTable;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTableException(msg.toString());
	}

	/**
	 * Returns the last expando table in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando table, or <code>null</code> if a matching expando table could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable fetchByC_C_Last(long companyId, long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C(companyId, classNameId);

		if (count == 0) {
			return null;
		}

		List<ExpandoTable> list = findByC_C(companyId, classNameId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the expando tables before and after the current expando table in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param tableId the primary key of the current expando table
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando table
	 * @throws com.liferay.portlet.expando.NoSuchTableException if a expando table with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable[] findByC_C_PrevAndNext(long tableId, long companyId,
		long classNameId, OrderByComparator orderByComparator)
		throws NoSuchTableException, SystemException {
		ExpandoTable expandoTable = findByPrimaryKey(tableId);

		Session session = null;

		try {
			session = openSession();

			ExpandoTable[] array = new ExpandoTableImpl[3];

			array[0] = getByC_C_PrevAndNext(session, expandoTable, companyId,
					classNameId, orderByComparator, true);

			array[1] = expandoTable;

			array[2] = getByC_C_PrevAndNext(session, expandoTable, companyId,
					classNameId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExpandoTable getByC_C_PrevAndNext(Session session,
		ExpandoTable expandoTable, long companyId, long classNameId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EXPANDOTABLE_WHERE);

		query.append(_FINDER_COLUMN_C_C_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

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
			query.append(ExpandoTableModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(classNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(expandoTable);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExpandoTable> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the expando tables where companyId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_C(long companyId, long classNameId)
		throws SystemException {
		for (ExpandoTable expandoTable : findByC_C(companyId, classNameId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(expandoTable);
		}
	}

	/**
	 * Returns the number of expando tables where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @return the number of matching expando tables
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_C(long companyId, long classNameId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { companyId, classNameId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EXPANDOTABLE_WHERE);

			query.append(_FINDER_COLUMN_C_C_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(classNameId);

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

	private static final String _FINDER_COLUMN_C_C_COMPANYID_2 = "expandoTable.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "expandoTable.classNameId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C_N = new FinderPath(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoTableModelImpl.FINDER_CACHE_ENABLED, ExpandoTableImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			ExpandoTableModelImpl.COMPANYID_COLUMN_BITMASK |
			ExpandoTableModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			ExpandoTableModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_N = new FinderPath(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the expando table where companyId = &#63; and classNameId = &#63; and name = &#63; or throws a {@link com.liferay.portlet.expando.NoSuchTableException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching expando table
	 * @throws com.liferay.portlet.expando.NoSuchTableException if a matching expando table could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable findByC_C_N(long companyId, long classNameId,
		String name) throws NoSuchTableException, SystemException {
		ExpandoTable expandoTable = fetchByC_C_N(companyId, classNameId, name);

		if (expandoTable == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTableException(msg.toString());
		}

		return expandoTable;
	}

	/**
	 * Returns the expando table where companyId = &#63; and classNameId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching expando table, or <code>null</code> if a matching expando table could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable fetchByC_C_N(long companyId, long classNameId,
		String name) throws SystemException {
		return fetchByC_C_N(companyId, classNameId, name, true);
	}

	/**
	 * Returns the expando table where companyId = &#63; and classNameId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching expando table, or <code>null</code> if a matching expando table could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable fetchByC_C_N(long companyId, long classNameId,
		String name, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, classNameId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C_N,
					finderArgs, this);
		}

		if (result instanceof ExpandoTable) {
			ExpandoTable expandoTable = (ExpandoTable)result;

			if ((companyId != expandoTable.getCompanyId()) ||
					(classNameId != expandoTable.getClassNameId()) ||
					!Validator.equals(name, expandoTable.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_EXPANDOTABLE_WHERE);

			query.append(_FINDER_COLUMN_C_C_N_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_C_N_CLASSNAMEID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_C_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_C_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_C_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(classNameId);

				if (bindName) {
					qPos.add(name);
				}

				List<ExpandoTable> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_N,
						finderArgs, list);
				}
				else {
					ExpandoTable expandoTable = list.get(0);

					result = expandoTable;

					cacheResult(expandoTable);

					if ((expandoTable.getCompanyId() != companyId) ||
							(expandoTable.getClassNameId() != classNameId) ||
							(expandoTable.getName() == null) ||
							!expandoTable.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_N,
							finderArgs, expandoTable);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_N,
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
			return (ExpandoTable)result;
		}
	}

	/**
	 * Removes the expando table where companyId = &#63; and classNameId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the expando table that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable removeByC_C_N(long companyId, long classNameId,
		String name) throws NoSuchTableException, SystemException {
		ExpandoTable expandoTable = findByC_C_N(companyId, classNameId, name);

		return remove(expandoTable);
	}

	/**
	 * Returns the number of expando tables where companyId = &#63; and classNameId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching expando tables
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_C_N(long companyId, long classNameId, String name)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_N;

		Object[] finderArgs = new Object[] { companyId, classNameId, name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_EXPANDOTABLE_WHERE);

			query.append(_FINDER_COLUMN_C_C_N_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_C_N_CLASSNAMEID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_C_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_C_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_C_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(classNameId);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_C_C_N_COMPANYID_2 = "expandoTable.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_N_CLASSNAMEID_2 = "expandoTable.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_N_NAME_1 = "expandoTable.name IS NULL";
	private static final String _FINDER_COLUMN_C_C_N_NAME_2 = "expandoTable.name = ?";
	private static final String _FINDER_COLUMN_C_C_N_NAME_3 = "(expandoTable.name IS NULL OR expandoTable.name = '')";

	public ExpandoTablePersistenceImpl() {
		setModelClass(ExpandoTable.class);
	}

	/**
	 * Caches the expando table in the entity cache if it is enabled.
	 *
	 * @param expandoTable the expando table
	 */
	@Override
	public void cacheResult(ExpandoTable expandoTable) {
		EntityCacheUtil.putResult(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoTableImpl.class, expandoTable.getPrimaryKey(), expandoTable);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_N,
			new Object[] {
				expandoTable.getCompanyId(), expandoTable.getClassNameId(),
				expandoTable.getName()
			}, expandoTable);

		expandoTable.resetOriginalValues();
	}

	/**
	 * Caches the expando tables in the entity cache if it is enabled.
	 *
	 * @param expandoTables the expando tables
	 */
	@Override
	public void cacheResult(List<ExpandoTable> expandoTables) {
		for (ExpandoTable expandoTable : expandoTables) {
			if (EntityCacheUtil.getResult(
						ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
						ExpandoTableImpl.class, expandoTable.getPrimaryKey()) == null) {
				cacheResult(expandoTable);
			}
			else {
				expandoTable.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all expando tables.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExpandoTableImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ExpandoTableImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the expando table.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExpandoTable expandoTable) {
		EntityCacheUtil.removeResult(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoTableImpl.class, expandoTable.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(expandoTable);
	}

	@Override
	public void clearCache(List<ExpandoTable> expandoTables) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ExpandoTable expandoTable : expandoTables) {
			EntityCacheUtil.removeResult(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
				ExpandoTableImpl.class, expandoTable.getPrimaryKey());

			clearUniqueFindersCache(expandoTable);
		}
	}

	protected void cacheUniqueFindersCache(ExpandoTable expandoTable) {
		if (expandoTable.isNew()) {
			Object[] args = new Object[] {
					expandoTable.getCompanyId(), expandoTable.getClassNameId(),
					expandoTable.getName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_N, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_N, args,
				expandoTable);
		}
		else {
			ExpandoTableModelImpl expandoTableModelImpl = (ExpandoTableModelImpl)expandoTable;

			if ((expandoTableModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_C_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						expandoTable.getCompanyId(),
						expandoTable.getClassNameId(), expandoTable.getName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_N, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_N, args,
					expandoTable);
			}
		}
	}

	protected void clearUniqueFindersCache(ExpandoTable expandoTable) {
		ExpandoTableModelImpl expandoTableModelImpl = (ExpandoTableModelImpl)expandoTable;

		Object[] args = new Object[] {
				expandoTable.getCompanyId(), expandoTable.getClassNameId(),
				expandoTable.getName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_N, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_N, args);

		if ((expandoTableModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C_N.getColumnBitmask()) != 0) {
			args = new Object[] {
					expandoTableModelImpl.getOriginalCompanyId(),
					expandoTableModelImpl.getOriginalClassNameId(),
					expandoTableModelImpl.getOriginalName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_N, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_N, args);
		}
	}

	/**
	 * Creates a new expando table with the primary key. Does not add the expando table to the database.
	 *
	 * @param tableId the primary key for the new expando table
	 * @return the new expando table
	 */
	@Override
	public ExpandoTable create(long tableId) {
		ExpandoTable expandoTable = new ExpandoTableImpl();

		expandoTable.setNew(true);
		expandoTable.setPrimaryKey(tableId);

		return expandoTable;
	}

	/**
	 * Removes the expando table with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tableId the primary key of the expando table
	 * @return the expando table that was removed
	 * @throws com.liferay.portlet.expando.NoSuchTableException if a expando table with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable remove(long tableId)
		throws NoSuchTableException, SystemException {
		return remove((Serializable)tableId);
	}

	/**
	 * Removes the expando table with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the expando table
	 * @return the expando table that was removed
	 * @throws com.liferay.portlet.expando.NoSuchTableException if a expando table with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable remove(Serializable primaryKey)
		throws NoSuchTableException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ExpandoTable expandoTable = (ExpandoTable)session.get(ExpandoTableImpl.class,
					primaryKey);

			if (expandoTable == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(expandoTable);
		}
		catch (NoSuchTableException nsee) {
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
	protected ExpandoTable removeImpl(ExpandoTable expandoTable)
		throws SystemException {
		expandoTable = toUnwrappedModel(expandoTable);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(expandoTable)) {
				expandoTable = (ExpandoTable)session.get(ExpandoTableImpl.class,
						expandoTable.getPrimaryKeyObj());
			}

			if (expandoTable != null) {
				session.delete(expandoTable);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (expandoTable != null) {
			clearCache(expandoTable);
		}

		return expandoTable;
	}

	@Override
	public ExpandoTable updateImpl(
		com.liferay.portlet.expando.model.ExpandoTable expandoTable)
		throws SystemException {
		expandoTable = toUnwrappedModel(expandoTable);

		boolean isNew = expandoTable.isNew();

		ExpandoTableModelImpl expandoTableModelImpl = (ExpandoTableModelImpl)expandoTable;

		Session session = null;

		try {
			session = openSession();

			if (expandoTable.isNew()) {
				session.save(expandoTable);

				expandoTable.setNew(false);
			}
			else {
				session.merge(expandoTable);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ExpandoTableModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((expandoTableModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						expandoTableModelImpl.getOriginalCompanyId(),
						expandoTableModelImpl.getOriginalClassNameId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						expandoTableModelImpl.getCompanyId(),
						expandoTableModelImpl.getClassNameId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}
		}

		EntityCacheUtil.putResult(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoTableImpl.class, expandoTable.getPrimaryKey(), expandoTable);

		clearUniqueFindersCache(expandoTable);
		cacheUniqueFindersCache(expandoTable);

		return expandoTable;
	}

	protected ExpandoTable toUnwrappedModel(ExpandoTable expandoTable) {
		if (expandoTable instanceof ExpandoTableImpl) {
			return expandoTable;
		}

		ExpandoTableImpl expandoTableImpl = new ExpandoTableImpl();

		expandoTableImpl.setNew(expandoTable.isNew());
		expandoTableImpl.setPrimaryKey(expandoTable.getPrimaryKey());

		expandoTableImpl.setTableId(expandoTable.getTableId());
		expandoTableImpl.setCompanyId(expandoTable.getCompanyId());
		expandoTableImpl.setClassNameId(expandoTable.getClassNameId());
		expandoTableImpl.setName(expandoTable.getName());

		return expandoTableImpl;
	}

	/**
	 * Returns the expando table with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the expando table
	 * @return the expando table
	 * @throws com.liferay.portlet.expando.NoSuchTableException if a expando table with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTableException, SystemException {
		ExpandoTable expandoTable = fetchByPrimaryKey(primaryKey);

		if (expandoTable == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return expandoTable;
	}

	/**
	 * Returns the expando table with the primary key or throws a {@link com.liferay.portlet.expando.NoSuchTableException} if it could not be found.
	 *
	 * @param tableId the primary key of the expando table
	 * @return the expando table
	 * @throws com.liferay.portlet.expando.NoSuchTableException if a expando table with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable findByPrimaryKey(long tableId)
		throws NoSuchTableException, SystemException {
		return findByPrimaryKey((Serializable)tableId);
	}

	/**
	 * Returns the expando table with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the expando table
	 * @return the expando table, or <code>null</code> if a expando table with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ExpandoTable expandoTable = (ExpandoTable)EntityCacheUtil.getResult(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
				ExpandoTableImpl.class, primaryKey);

		if (expandoTable == _nullExpandoTable) {
			return null;
		}

		if (expandoTable == null) {
			Session session = null;

			try {
				session = openSession();

				expandoTable = (ExpandoTable)session.get(ExpandoTableImpl.class,
						primaryKey);

				if (expandoTable != null) {
					cacheResult(expandoTable);
				}
				else {
					EntityCacheUtil.putResult(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
						ExpandoTableImpl.class, primaryKey, _nullExpandoTable);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ExpandoTableModelImpl.ENTITY_CACHE_ENABLED,
					ExpandoTableImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return expandoTable;
	}

	/**
	 * Returns the expando table with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param tableId the primary key of the expando table
	 * @return the expando table, or <code>null</code> if a expando table with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoTable fetchByPrimaryKey(long tableId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)tableId);
	}

	/**
	 * Returns all the expando tables.
	 *
	 * @return the expando tables
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoTable> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the expando tables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.expando.model.impl.ExpandoTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of expando tables
	 * @param end the upper bound of the range of expando tables (not inclusive)
	 * @return the range of expando tables
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoTable> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando tables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.expando.model.impl.ExpandoTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of expando tables
	 * @param end the upper bound of the range of expando tables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of expando tables
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ExpandoTable> findAll(int start, int end,
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

		List<ExpandoTable> list = (List<ExpandoTable>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EXPANDOTABLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXPANDOTABLE;

				if (pagination) {
					sql = sql.concat(ExpandoTableModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ExpandoTable>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ExpandoTable>(list);
				}
				else {
					list = (List<ExpandoTable>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the expando tables from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ExpandoTable expandoTable : findAll()) {
			remove(expandoTable);
		}
	}

	/**
	 * Returns the number of expando tables.
	 *
	 * @return the number of expando tables
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

				Query q = session.createQuery(_SQL_COUNT_EXPANDOTABLE);

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
	 * Initializes the expando table persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.expando.model.ExpandoTable")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ExpandoTable>> listenersList = new ArrayList<ModelListener<ExpandoTable>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ExpandoTable>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ExpandoTableImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_EXPANDOTABLE = "SELECT expandoTable FROM ExpandoTable expandoTable";
	private static final String _SQL_SELECT_EXPANDOTABLE_WHERE = "SELECT expandoTable FROM ExpandoTable expandoTable WHERE ";
	private static final String _SQL_COUNT_EXPANDOTABLE = "SELECT COUNT(expandoTable) FROM ExpandoTable expandoTable";
	private static final String _SQL_COUNT_EXPANDOTABLE_WHERE = "SELECT COUNT(expandoTable) FROM ExpandoTable expandoTable WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "expandoTable.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ExpandoTable exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ExpandoTable exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(ExpandoTablePersistenceImpl.class);
	private static ExpandoTable _nullExpandoTable = new ExpandoTableImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ExpandoTable> toCacheModel() {
				return _nullExpandoTableCacheModel;
			}
		};

	private static CacheModel<ExpandoTable> _nullExpandoTableCacheModel = new CacheModel<ExpandoTable>() {
			@Override
			public ExpandoTable toEntityModel() {
				return _nullExpandoTable;
			}
		};
}