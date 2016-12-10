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

package com.liferay.portal.service.persistence;

import com.liferay.portal.NoSuchLayoutSetException;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.impl.LayoutSetImpl;
import com.liferay.portal.model.impl.LayoutSetModelImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the layout set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetPersistence
 * @see LayoutSetUtil
 * @generated
 */
public class LayoutSetPersistenceImpl extends BasePersistenceImpl<LayoutSet>
	implements LayoutSetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LayoutSetUtil} to access the layout set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LayoutSetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			LayoutSetModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the layout sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching layout sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LayoutSet> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LayoutSet> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LayoutSet> findByGroupId(long groupId, int start, int end,
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

		List<LayoutSet> list = (List<LayoutSet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LayoutSet layoutSet : list) {
				if ((groupId != layoutSet.getGroupId())) {
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

			query.append(_SQL_SELECT_LAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<LayoutSet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<LayoutSet>(list);
				}
				else {
					list = (List<LayoutSet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws com.liferay.portal.NoSuchLayoutSetException if a matching layout set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetException, SystemException {
		LayoutSet layoutSet = fetchByGroupId_First(groupId, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLayoutSetException(msg.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LayoutSet> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws com.liferay.portal.NoSuchLayoutSetException if a matching layout set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetException, SystemException {
		LayoutSet layoutSet = fetchByGroupId_Last(groupId, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLayoutSetException(msg.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where groupId = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws com.liferay.portal.NoSuchLayoutSetException if a layout set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet[] findByGroupId_PrevAndNext(long layoutSetId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchLayoutSetException, SystemException {
		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, layoutSet, groupId,
					orderByComparator, true);

			array[1] = layoutSet;

			array[2] = getByGroupId_PrevAndNext(session, layoutSet, groupId,
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

	protected LayoutSet getByGroupId_PrevAndNext(Session session,
		LayoutSet layoutSet, long groupId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTSET_WHERE);

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
			query.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(layoutSet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (LayoutSet layoutSet : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching layout sets
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

			query.append(_SQL_COUNT_LAYOUTSET_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "layoutSet.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LAYOUTSETPROTOTYPEUUID =
		new FinderPath(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLayoutSetPrototypeUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTSETPROTOTYPEUUID =
		new FinderPath(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByLayoutSetPrototypeUuid",
			new String[] { String.class.getName() },
			LayoutSetModelImpl.LAYOUTSETPROTOTYPEUUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LAYOUTSETPROTOTYPEUUID = new FinderPath(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLayoutSetPrototypeUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @return the matching layout sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid(
		String layoutSetPrototypeUuid) throws SystemException {
		return findByLayoutSetPrototypeUuid(layoutSetPrototypeUuid,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid(
		String layoutSetPrototypeUuid, int start, int end)
		throws SystemException {
		return findByLayoutSetPrototypeUuid(layoutSetPrototypeUuid, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid(
		String layoutSetPrototypeUuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTSETPROTOTYPEUUID;
			finderArgs = new Object[] { layoutSetPrototypeUuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LAYOUTSETPROTOTYPEUUID;
			finderArgs = new Object[] {
					layoutSetPrototypeUuid,
					
					start, end, orderByComparator
				};
		}

		List<LayoutSet> list = (List<LayoutSet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LayoutSet layoutSet : list) {
				if (!Validator.equals(layoutSetPrototypeUuid,
							layoutSet.getLayoutSetPrototypeUuid())) {
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

			query.append(_SQL_SELECT_LAYOUTSET_WHERE);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid == null) {
				query.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_1);
			}
			else if (layoutSetPrototypeUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				query.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLayoutSetPrototypeUuid) {
					qPos.add(layoutSetPrototypeUuid);
				}

				if (!pagination) {
					list = (List<LayoutSet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<LayoutSet>(list);
				}
				else {
					list = (List<LayoutSet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws com.liferay.portal.NoSuchLayoutSetException if a matching layout set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet findByLayoutSetPrototypeUuid_First(
		String layoutSetPrototypeUuid, OrderByComparator orderByComparator)
		throws NoSuchLayoutSetException, SystemException {
		LayoutSet layoutSet = fetchByLayoutSetPrototypeUuid_First(layoutSetPrototypeUuid,
				orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("layoutSetPrototypeUuid=");
		msg.append(layoutSetPrototypeUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLayoutSetException(msg.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet fetchByLayoutSetPrototypeUuid_First(
		String layoutSetPrototypeUuid, OrderByComparator orderByComparator)
		throws SystemException {
		List<LayoutSet> list = findByLayoutSetPrototypeUuid(layoutSetPrototypeUuid,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws com.liferay.portal.NoSuchLayoutSetException if a matching layout set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet findByLayoutSetPrototypeUuid_Last(
		String layoutSetPrototypeUuid, OrderByComparator orderByComparator)
		throws NoSuchLayoutSetException, SystemException {
		LayoutSet layoutSet = fetchByLayoutSetPrototypeUuid_Last(layoutSetPrototypeUuid,
				orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("layoutSetPrototypeUuid=");
		msg.append(layoutSetPrototypeUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLayoutSetException(msg.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet fetchByLayoutSetPrototypeUuid_Last(
		String layoutSetPrototypeUuid, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByLayoutSetPrototypeUuid(layoutSetPrototypeUuid);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByLayoutSetPrototypeUuid(layoutSetPrototypeUuid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws com.liferay.portal.NoSuchLayoutSetException if a layout set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet[] findByLayoutSetPrototypeUuid_PrevAndNext(
		long layoutSetId, String layoutSetPrototypeUuid,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetException, SystemException {
		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByLayoutSetPrototypeUuid_PrevAndNext(session,
					layoutSet, layoutSetPrototypeUuid, orderByComparator, true);

			array[1] = layoutSet;

			array[2] = getByLayoutSetPrototypeUuid_PrevAndNext(session,
					layoutSet, layoutSetPrototypeUuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSet getByLayoutSetPrototypeUuid_PrevAndNext(
		Session session, LayoutSet layoutSet, String layoutSetPrototypeUuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTSET_WHERE);

		boolean bindLayoutSetPrototypeUuid = false;

		if (layoutSetPrototypeUuid == null) {
			query.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_1);
		}
		else if (layoutSetPrototypeUuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3);
		}
		else {
			bindLayoutSetPrototypeUuid = true;

			query.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2);
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
			query.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindLayoutSetPrototypeUuid) {
			qPos.add(layoutSetPrototypeUuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(layoutSet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where layoutSetPrototypeUuid = &#63; from the database.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByLayoutSetPrototypeUuid(String layoutSetPrototypeUuid)
		throws SystemException {
		for (LayoutSet layoutSet : findByLayoutSetPrototypeUuid(
				layoutSetPrototypeUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @return the number of matching layout sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByLayoutSetPrototypeUuid(String layoutSetPrototypeUuid)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LAYOUTSETPROTOTYPEUUID;

		Object[] finderArgs = new Object[] { layoutSetPrototypeUuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LAYOUTSET_WHERE);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid == null) {
				query.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_1);
			}
			else if (layoutSetPrototypeUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				query.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLayoutSetPrototypeUuid) {
					qPos.add(layoutSetPrototypeUuid);
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

	private static final String _FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_1 =
		"layoutSet.layoutSetPrototypeUuid IS NULL";
	private static final String _FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2 =
		"layoutSet.layoutSetPrototypeUuid = ?";
	private static final String _FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3 =
		"(layoutSet.layoutSetPrototypeUuid IS NULL OR layoutSet.layoutSetPrototypeUuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_P = new FinderPath(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_P",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			LayoutSetModelImpl.GROUPID_COLUMN_BITMASK |
			LayoutSetModelImpl.PRIVATELAYOUT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P = new FinderPath(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns the layout set where groupId = &#63; and privateLayout = &#63; or throws a {@link com.liferay.portal.NoSuchLayoutSetException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @return the matching layout set
	 * @throws com.liferay.portal.NoSuchLayoutSetException if a matching layout set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet findByG_P(long groupId, boolean privateLayout)
		throws NoSuchLayoutSetException, SystemException {
		LayoutSet layoutSet = fetchByG_P(groupId, privateLayout);

		if (layoutSet == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", privateLayout=");
			msg.append(privateLayout);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchLayoutSetException(msg.toString());
		}

		return layoutSet;
	}

	/**
	 * Returns the layout set where groupId = &#63; and privateLayout = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet fetchByG_P(long groupId, boolean privateLayout)
		throws SystemException {
		return fetchByG_P(groupId, privateLayout, true);
	}

	/**
	 * Returns the layout set where groupId = &#63; and privateLayout = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet fetchByG_P(long groupId, boolean privateLayout,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, privateLayout };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_P,
					finderArgs, this);
		}

		if (result instanceof LayoutSet) {
			LayoutSet layoutSet = (LayoutSet)result;

			if ((groupId != layoutSet.getGroupId()) ||
					(privateLayout != layoutSet.getPrivateLayout())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PRIVATELAYOUT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(privateLayout);

				List<LayoutSet> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P,
						finderArgs, list);
				}
				else {
					LayoutSet layoutSet = list.get(0);

					result = layoutSet;

					cacheResult(layoutSet);

					if ((layoutSet.getGroupId() != groupId) ||
							(layoutSet.getPrivateLayout() != privateLayout)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P,
							finderArgs, layoutSet);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_P,
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
			return (LayoutSet)result;
		}
	}

	/**
	 * Removes the layout set where groupId = &#63; and privateLayout = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @return the layout set that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet removeByG_P(long groupId, boolean privateLayout)
		throws NoSuchLayoutSetException, SystemException {
		LayoutSet layoutSet = findByG_P(groupId, privateLayout);

		return remove(layoutSet);
	}

	/**
	 * Returns the number of layout sets where groupId = &#63; and privateLayout = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @return the number of matching layout sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_P(long groupId, boolean privateLayout)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P;

		Object[] finderArgs = new Object[] { groupId, privateLayout };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PRIVATELAYOUT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(privateLayout);

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

	private static final String _FINDER_COLUMN_G_P_GROUPID_2 = "layoutSet.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_PRIVATELAYOUT_2 = "layoutSet.privateLayout = ?";

	public LayoutSetPersistenceImpl() {
		setModelClass(LayoutSet.class);
	}

	/**
	 * Caches the layout set in the entity cache if it is enabled.
	 *
	 * @param layoutSet the layout set
	 */
	@Override
	public void cacheResult(LayoutSet layoutSet) {
		EntityCacheUtil.putResult(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetImpl.class, layoutSet.getPrimaryKey(), layoutSet);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P,
			new Object[] { layoutSet.getGroupId(), layoutSet.getPrivateLayout() },
			layoutSet);

		layoutSet.resetOriginalValues();
	}

	/**
	 * Caches the layout sets in the entity cache if it is enabled.
	 *
	 * @param layoutSets the layout sets
	 */
	@Override
	public void cacheResult(List<LayoutSet> layoutSets) {
		for (LayoutSet layoutSet : layoutSets) {
			if (EntityCacheUtil.getResult(
						LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
						LayoutSetImpl.class, layoutSet.getPrimaryKey()) == null) {
				cacheResult(layoutSet);
			}
			else {
				layoutSet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all layout sets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(LayoutSetImpl.class.getName());
		}

		EntityCacheUtil.clearCache(LayoutSetImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the layout set.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LayoutSet layoutSet) {
		EntityCacheUtil.removeResult(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetImpl.class, layoutSet.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(layoutSet);
	}

	@Override
	public void clearCache(List<LayoutSet> layoutSets) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LayoutSet layoutSet : layoutSets) {
			EntityCacheUtil.removeResult(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
				LayoutSetImpl.class, layoutSet.getPrimaryKey());

			clearUniqueFindersCache(layoutSet);
		}
	}

	protected void cacheUniqueFindersCache(LayoutSet layoutSet) {
		if (layoutSet.isNew()) {
			Object[] args = new Object[] {
					layoutSet.getGroupId(), layoutSet.getPrivateLayout()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P, args, layoutSet);
		}
		else {
			LayoutSetModelImpl layoutSetModelImpl = (LayoutSetModelImpl)layoutSet;

			if ((layoutSetModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutSet.getGroupId(), layoutSet.getPrivateLayout()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P, args,
					layoutSet);
			}
		}
	}

	protected void clearUniqueFindersCache(LayoutSet layoutSet) {
		LayoutSetModelImpl layoutSetModelImpl = (LayoutSetModelImpl)layoutSet;

		Object[] args = new Object[] {
				layoutSet.getGroupId(), layoutSet.getPrivateLayout()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_P, args);

		if ((layoutSetModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_P.getColumnBitmask()) != 0) {
			args = new Object[] {
					layoutSetModelImpl.getOriginalGroupId(),
					layoutSetModelImpl.getOriginalPrivateLayout()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_P, args);
		}
	}

	/**
	 * Creates a new layout set with the primary key. Does not add the layout set to the database.
	 *
	 * @param layoutSetId the primary key for the new layout set
	 * @return the new layout set
	 */
	@Override
	public LayoutSet create(long layoutSetId) {
		LayoutSet layoutSet = new LayoutSetImpl();

		layoutSet.setNew(true);
		layoutSet.setPrimaryKey(layoutSetId);

		return layoutSet;
	}

	/**
	 * Removes the layout set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutSetId the primary key of the layout set
	 * @return the layout set that was removed
	 * @throws com.liferay.portal.NoSuchLayoutSetException if a layout set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet remove(long layoutSetId)
		throws NoSuchLayoutSetException, SystemException {
		return remove((Serializable)layoutSetId);
	}

	/**
	 * Removes the layout set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the layout set
	 * @return the layout set that was removed
	 * @throws com.liferay.portal.NoSuchLayoutSetException if a layout set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet remove(Serializable primaryKey)
		throws NoSuchLayoutSetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			LayoutSet layoutSet = (LayoutSet)session.get(LayoutSetImpl.class,
					primaryKey);

			if (layoutSet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLayoutSetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(layoutSet);
		}
		catch (NoSuchLayoutSetException nsee) {
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
	protected LayoutSet removeImpl(LayoutSet layoutSet)
		throws SystemException {
		layoutSet = toUnwrappedModel(layoutSet);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(layoutSet)) {
				layoutSet = (LayoutSet)session.get(LayoutSetImpl.class,
						layoutSet.getPrimaryKeyObj());
			}

			if (layoutSet != null) {
				session.delete(layoutSet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (layoutSet != null) {
			clearCache(layoutSet);
		}

		return layoutSet;
	}

	@Override
	public LayoutSet updateImpl(com.liferay.portal.model.LayoutSet layoutSet)
		throws SystemException {
		layoutSet = toUnwrappedModel(layoutSet);

		boolean isNew = layoutSet.isNew();

		LayoutSetModelImpl layoutSetModelImpl = (LayoutSetModelImpl)layoutSet;

		Session session = null;

		try {
			session = openSession();

			if (layoutSet.isNew()) {
				session.save(layoutSet);

				layoutSet.setNew(false);
			}
			else {
				session.merge(layoutSet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LayoutSetModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((layoutSetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutSetModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { layoutSetModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((layoutSetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTSETPROTOTYPEUUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutSetModelImpl.getOriginalLayoutSetPrototypeUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LAYOUTSETPROTOTYPEUUID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTSETPROTOTYPEUUID,
					args);

				args = new Object[] {
						layoutSetModelImpl.getLayoutSetPrototypeUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LAYOUTSETPROTOTYPEUUID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTSETPROTOTYPEUUID,
					args);
			}
		}

		EntityCacheUtil.putResult(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetImpl.class, layoutSet.getPrimaryKey(), layoutSet);

		clearUniqueFindersCache(layoutSet);
		cacheUniqueFindersCache(layoutSet);

		return layoutSet;
	}

	protected LayoutSet toUnwrappedModel(LayoutSet layoutSet) {
		if (layoutSet instanceof LayoutSetImpl) {
			return layoutSet;
		}

		LayoutSetImpl layoutSetImpl = new LayoutSetImpl();

		layoutSetImpl.setNew(layoutSet.isNew());
		layoutSetImpl.setPrimaryKey(layoutSet.getPrimaryKey());

		layoutSetImpl.setLayoutSetId(layoutSet.getLayoutSetId());
		layoutSetImpl.setGroupId(layoutSet.getGroupId());
		layoutSetImpl.setCompanyId(layoutSet.getCompanyId());
		layoutSetImpl.setCreateDate(layoutSet.getCreateDate());
		layoutSetImpl.setModifiedDate(layoutSet.getModifiedDate());
		layoutSetImpl.setPrivateLayout(layoutSet.isPrivateLayout());
		layoutSetImpl.setLogo(layoutSet.isLogo());
		layoutSetImpl.setLogoId(layoutSet.getLogoId());
		layoutSetImpl.setThemeId(layoutSet.getThemeId());
		layoutSetImpl.setColorSchemeId(layoutSet.getColorSchemeId());
		layoutSetImpl.setWapThemeId(layoutSet.getWapThemeId());
		layoutSetImpl.setWapColorSchemeId(layoutSet.getWapColorSchemeId());
		layoutSetImpl.setCss(layoutSet.getCss());
		layoutSetImpl.setPageCount(layoutSet.getPageCount());
		layoutSetImpl.setSettings(layoutSet.getSettings());
		layoutSetImpl.setLayoutSetPrototypeUuid(layoutSet.getLayoutSetPrototypeUuid());
		layoutSetImpl.setLayoutSetPrototypeLinkEnabled(layoutSet.isLayoutSetPrototypeLinkEnabled());

		return layoutSetImpl;
	}

	/**
	 * Returns the layout set with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout set
	 * @return the layout set
	 * @throws com.liferay.portal.NoSuchLayoutSetException if a layout set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLayoutSetException, SystemException {
		LayoutSet layoutSet = fetchByPrimaryKey(primaryKey);

		if (layoutSet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLayoutSetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return layoutSet;
	}

	/**
	 * Returns the layout set with the primary key or throws a {@link com.liferay.portal.NoSuchLayoutSetException} if it could not be found.
	 *
	 * @param layoutSetId the primary key of the layout set
	 * @return the layout set
	 * @throws com.liferay.portal.NoSuchLayoutSetException if a layout set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet findByPrimaryKey(long layoutSetId)
		throws NoSuchLayoutSetException, SystemException {
		return findByPrimaryKey((Serializable)layoutSetId);
	}

	/**
	 * Returns the layout set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout set
	 * @return the layout set, or <code>null</code> if a layout set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		LayoutSet layoutSet = (LayoutSet)EntityCacheUtil.getResult(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
				LayoutSetImpl.class, primaryKey);

		if (layoutSet == _nullLayoutSet) {
			return null;
		}

		if (layoutSet == null) {
			Session session = null;

			try {
				session = openSession();

				layoutSet = (LayoutSet)session.get(LayoutSetImpl.class,
						primaryKey);

				if (layoutSet != null) {
					cacheResult(layoutSet);
				}
				else {
					EntityCacheUtil.putResult(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
						LayoutSetImpl.class, primaryKey, _nullLayoutSet);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
					LayoutSetImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return layoutSet;
	}

	/**
	 * Returns the layout set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param layoutSetId the primary key of the layout set
	 * @return the layout set, or <code>null</code> if a layout set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LayoutSet fetchByPrimaryKey(long layoutSetId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)layoutSetId);
	}

	/**
	 * Returns all the layout sets.
	 *
	 * @return the layout sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LayoutSet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of layout sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LayoutSet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of layout sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LayoutSet> findAll(int start, int end,
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

		List<LayoutSet> list = (List<LayoutSet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_LAYOUTSET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LAYOUTSET;

				if (pagination) {
					sql = sql.concat(LayoutSetModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LayoutSet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<LayoutSet>(list);
				}
				else {
					list = (List<LayoutSet>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the layout sets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (LayoutSet layoutSet : findAll()) {
			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets.
	 *
	 * @return the number of layout sets
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

				Query q = session.createQuery(_SQL_COUNT_LAYOUTSET);

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
	 * Initializes the layout set persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portal.model.LayoutSet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<LayoutSet>> listenersList = new ArrayList<ModelListener<LayoutSet>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<LayoutSet>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(LayoutSetImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_LAYOUTSET = "SELECT layoutSet FROM LayoutSet layoutSet";
	private static final String _SQL_SELECT_LAYOUTSET_WHERE = "SELECT layoutSet FROM LayoutSet layoutSet WHERE ";
	private static final String _SQL_COUNT_LAYOUTSET = "SELECT COUNT(layoutSet) FROM LayoutSet layoutSet";
	private static final String _SQL_COUNT_LAYOUTSET_WHERE = "SELECT COUNT(layoutSet) FROM LayoutSet layoutSet WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "layoutSet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LayoutSet exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LayoutSet exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(LayoutSetPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"settings"
			});
	private static LayoutSet _nullLayoutSet = new LayoutSetImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<LayoutSet> toCacheModel() {
				return _nullLayoutSetCacheModel;
			}
		};

	private static CacheModel<LayoutSet> _nullLayoutSetCacheModel = new CacheModel<LayoutSet>() {
			@Override
			public LayoutSet toEntityModel() {
				return _nullLayoutSet;
			}
		};
}