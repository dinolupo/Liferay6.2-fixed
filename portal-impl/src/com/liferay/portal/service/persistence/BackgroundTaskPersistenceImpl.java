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

import com.liferay.portal.NoSuchBackgroundTaskException;
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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.impl.BackgroundTaskImpl;
import com.liferay.portal.model.impl.BackgroundTaskModelImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the background task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BackgroundTaskPersistence
 * @see BackgroundTaskUtil
 * @generated
 */
public class BackgroundTaskPersistenceImpl extends BasePersistenceImpl<BackgroundTask>
	implements BackgroundTaskPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BackgroundTaskUtil} to access the background task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BackgroundTaskImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			BackgroundTaskModelImpl.GROUPID_COLUMN_BITMASK |
			BackgroundTaskModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the background tasks where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByGroupId(long groupId, int start, int end,
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

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((groupId != backgroundTask.getGroupId())) {
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

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
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
	 * Returns the first background task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByGroupId_First(groupId,
				orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the first background task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<BackgroundTask> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<BackgroundTask> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the background tasks before and after the current background task in the ordered set where groupId = &#63;.
	 *
	 * @param backgroundTaskId the primary key of the current background task
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask[] findByGroupId_PrevAndNext(long backgroundTaskId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = findByPrimaryKey(backgroundTaskId);

		Session session = null;

		try {
			session = openSession();

			BackgroundTask[] array = new BackgroundTaskImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, backgroundTask,
					groupId, orderByComparator, true);

			array[1] = backgroundTask;

			array[2] = getByGroupId_PrevAndNext(session, backgroundTask,
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

	protected BackgroundTask getByGroupId_PrevAndNext(Session session,
		BackgroundTask backgroundTask, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

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
			query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backgroundTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackgroundTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the background tasks where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (BackgroundTask backgroundTask : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(backgroundTask);
		}
	}

	/**
	 * Returns the number of background tasks where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching background tasks
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

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "backgroundTask.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			BackgroundTaskModelImpl.COMPANYID_COLUMN_BITMASK |
			BackgroundTaskModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the background tasks where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the background tasks where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((companyId != backgroundTask.getCompanyId())) {
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

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
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
	 * Returns the first background task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the first background task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<BackgroundTask> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last background task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the last background task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<BackgroundTask> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the background tasks before and after the current background task in the ordered set where companyId = &#63;.
	 *
	 * @param backgroundTaskId the primary key of the current background task
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask[] findByCompanyId_PrevAndNext(long backgroundTaskId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = findByPrimaryKey(backgroundTaskId);

		Session session = null;

		try {
			session = openSession();

			BackgroundTask[] array = new BackgroundTaskImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, backgroundTask,
					companyId, orderByComparator, true);

			array[1] = backgroundTask;

			array[2] = getByCompanyId_PrevAndNext(session, backgroundTask,
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

	protected BackgroundTask getByCompanyId_PrevAndNext(Session session,
		BackgroundTask backgroundTask, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backgroundTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackgroundTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the background tasks where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCompanyId(long companyId) throws SystemException {
		for (BackgroundTask backgroundTask : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(backgroundTask);
		}
	}

	/**
	 * Returns the number of background tasks where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCompanyId(long companyId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "backgroundTask.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Integer.class.getName() },
			BackgroundTaskModelImpl.STATUS_COLUMN_BITMASK |
			BackgroundTaskModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the background tasks where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByStatus(int status)
		throws SystemException {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByStatus(int status, int start, int end)
		throws SystemException {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByStatus(int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status, start, end, orderByComparator };
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((status != backgroundTask.getStatus())) {
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

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
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
	 * Returns the first background task in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByStatus_First(int status,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByStatus_First(status,
				orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the first background task in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByStatus_First(int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<BackgroundTask> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last background task in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByStatus_Last(int status,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByStatus_Last(status,
				orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the last background task in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByStatus_Last(int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<BackgroundTask> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the background tasks before and after the current background task in the ordered set where status = &#63;.
	 *
	 * @param backgroundTaskId the primary key of the current background task
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask[] findByStatus_PrevAndNext(long backgroundTaskId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = findByPrimaryKey(backgroundTaskId);

		Session session = null;

		try {
			session = openSession();

			BackgroundTask[] array = new BackgroundTaskImpl[3];

			array[0] = getByStatus_PrevAndNext(session, backgroundTask, status,
					orderByComparator, true);

			array[1] = backgroundTask;

			array[2] = getByStatus_PrevAndNext(session, backgroundTask, status,
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

	protected BackgroundTask getByStatus_PrevAndNext(Session session,
		BackgroundTask backgroundTask, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

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
			query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backgroundTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackgroundTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the background tasks where status = &#63; from the database.
	 *
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByStatus(int status) throws SystemException {
		for (BackgroundTask backgroundTask : findByStatus(status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(backgroundTask);
		}
	}

	/**
	 * Returns the number of background tasks where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByStatus(int status) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS;

		Object[] finderArgs = new Object[] { status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "backgroundTask.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_T",
			new String[] { Long.class.getName(), String.class.getName() },
			BackgroundTaskModelImpl.GROUPID_COLUMN_BITMASK |
			BackgroundTaskModelImpl.TASKEXECUTORCLASSNAME_COLUMN_BITMASK |
			BackgroundTaskModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_T = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_T",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_T",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the background tasks where groupId = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T(long groupId,
		String taskExecutorClassName) throws SystemException {
		return findByG_T(groupId, taskExecutorClassName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T(long groupId,
		String taskExecutorClassName, int start, int end)
		throws SystemException {
		return findByG_T(groupId, taskExecutorClassName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T(long groupId,
		String taskExecutorClassName, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T;
			finderArgs = new Object[] { groupId, taskExecutorClassName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T;
			finderArgs = new Object[] {
					groupId, taskExecutorClassName,
					
					start, end, orderByComparator
				};
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((groupId != backgroundTask.getGroupId()) ||
						!Validator.equals(taskExecutorClassName,
							backgroundTask.getTaskExecutorClassName())) {
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

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_T_GROUPID_2);

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
				}

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
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
	 * Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_T_First(long groupId,
		String taskExecutorClassName, OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_T_First(groupId,
				taskExecutorClassName, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_T_First(long groupId,
		String taskExecutorClassName, OrderByComparator orderByComparator)
		throws SystemException {
		List<BackgroundTask> list = findByG_T(groupId, taskExecutorClassName,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_T_Last(long groupId,
		String taskExecutorClassName, OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_T_Last(groupId,
				taskExecutorClassName, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_T_Last(long groupId,
		String taskExecutorClassName, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByG_T(groupId, taskExecutorClassName);

		if (count == 0) {
			return null;
		}

		List<BackgroundTask> list = findByG_T(groupId, taskExecutorClassName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param backgroundTaskId the primary key of the current background task
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask[] findByG_T_PrevAndNext(long backgroundTaskId,
		long groupId, String taskExecutorClassName,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = findByPrimaryKey(backgroundTaskId);

		Session session = null;

		try {
			session = openSession();

			BackgroundTask[] array = new BackgroundTaskImpl[3];

			array[0] = getByG_T_PrevAndNext(session, backgroundTask, groupId,
					taskExecutorClassName, orderByComparator, true);

			array[1] = backgroundTask;

			array[2] = getByG_T_PrevAndNext(session, backgroundTask, groupId,
					taskExecutorClassName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BackgroundTask getByG_T_PrevAndNext(Session session,
		BackgroundTask backgroundTask, long groupId,
		String taskExecutorClassName, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

		query.append(_FINDER_COLUMN_G_T_GROUPID_2);

		boolean bindTaskExecutorClassName = false;

		if (taskExecutorClassName == null) {
			query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_1);
		}
		else if (taskExecutorClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_3);
		}
		else {
			bindTaskExecutorClassName = true;

			query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_2);
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
			query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindTaskExecutorClassName) {
			qPos.add(taskExecutorClassName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backgroundTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackgroundTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T(long groupId,
		String[] taskExecutorClassNames) throws SystemException {
		return findByG_T(groupId, taskExecutorClassNames, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T(long groupId,
		String[] taskExecutorClassNames, int start, int end)
		throws SystemException {
		return findByG_T(groupId, taskExecutorClassNames, start, end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T(long groupId,
		String[] taskExecutorClassNames, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if ((taskExecutorClassNames != null) &&
				(taskExecutorClassNames.length == 1)) {
			return findByG_T(groupId, taskExecutorClassNames[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(taskExecutorClassNames)
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(taskExecutorClassNames),
					
					start, end, orderByComparator
				};
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((groupId != backgroundTask.getGroupId()) ||
						!ArrayUtil.contains(taskExecutorClassNames,
							backgroundTask.getTaskExecutorClassName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_T_GROUPID_5);

			conjunctionable = true;

			if ((taskExecutorClassNames == null) ||
					(taskExecutorClassNames.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < taskExecutorClassNames.length; i++) {
					String taskExecutorClassName = taskExecutorClassNames[i];

					if (taskExecutorClassName == null) {
						query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_4);
					}
					else if (taskExecutorClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_5);
					}

					if ((i + 1) < taskExecutorClassNames.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (taskExecutorClassNames != null) {
					qPos.add(taskExecutorClassNames);
				}

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_T(long groupId, String taskExecutorClassName)
		throws SystemException {
		for (BackgroundTask backgroundTask : findByG_T(groupId,
				taskExecutorClassName, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(backgroundTask);
		}
	}

	/**
	 * Returns the number of background tasks where groupId = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_T(long groupId, String taskExecutorClassName)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_T;

		Object[] finderArgs = new Object[] { groupId, taskExecutorClassName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_T_GROUPID_2);

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
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

	/**
	 * Returns the number of background tasks where groupId = &#63; and taskExecutorClassName = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_T(long groupId, String[] taskExecutorClassNames)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(taskExecutorClassNames)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_T_GROUPID_5);

			conjunctionable = true;

			if ((taskExecutorClassNames == null) ||
					(taskExecutorClassNames.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < taskExecutorClassNames.length; i++) {
					String taskExecutorClassName = taskExecutorClassNames[i];

					if (taskExecutorClassName == null) {
						query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_4);
					}
					else if (taskExecutorClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_5);
					}

					if ((i + 1) < taskExecutorClassNames.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (taskExecutorClassNames != null) {
					qPos.add(taskExecutorClassNames);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_T_GROUPID_2 = "backgroundTask.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_T_GROUPID_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_1 = "backgroundTask.taskExecutorClassName IS NULL";
	private static final String _FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_2 = "backgroundTask.taskExecutorClassName = ?";
	private static final String _FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_3 = "(backgroundTask.taskExecutorClassName IS NULL OR backgroundTask.taskExecutorClassName = '')";
	private static final String _FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_4 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_1) + ")";
	private static final String _FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_2) + ")";
	private static final String _FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_6 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_TASKEXECUTORCLASSNAME_3) + ")";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			BackgroundTaskModelImpl.GROUPID_COLUMN_BITMASK |
			BackgroundTaskModelImpl.STATUS_COLUMN_BITMASK |
			BackgroundTaskModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_S = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the background tasks where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_S(long groupId, int status)
		throws SystemException {
		return findByG_S(groupId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the background tasks where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_S(long groupId, int status, int start,
		int end) throws SystemException {
		return findByG_S(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_S(long groupId, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] { groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] {
					groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((groupId != backgroundTask.getGroupId()) ||
						(status != backgroundTask.getStatus())) {
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

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
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
	 * Returns the first background task in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_S_First(long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_S_First(groupId, status,
				orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the first background task in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_S_First(long groupId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<BackgroundTask> list = findByG_S(groupId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_S_Last(long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_S_Last(groupId, status,
				orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_S_Last(long groupId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_S(groupId, status);

		if (count == 0) {
			return null;
		}

		List<BackgroundTask> list = findByG_S(groupId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param backgroundTaskId the primary key of the current background task
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask[] findByG_S_PrevAndNext(long backgroundTaskId,
		long groupId, int status, OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = findByPrimaryKey(backgroundTaskId);

		Session session = null;

		try {
			session = openSession();

			BackgroundTask[] array = new BackgroundTaskImpl[3];

			array[0] = getByG_S_PrevAndNext(session, backgroundTask, groupId,
					status, orderByComparator, true);

			array[1] = backgroundTask;

			array[2] = getByG_S_PrevAndNext(session, backgroundTask, groupId,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BackgroundTask getByG_S_PrevAndNext(Session session,
		BackgroundTask backgroundTask, long groupId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

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
			query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backgroundTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackgroundTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the background tasks where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_S(long groupId, int status) throws SystemException {
		for (BackgroundTask backgroundTask : findByG_S(groupId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(backgroundTask);
		}
	}

	/**
	 * Returns the number of background tasks where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_S(long groupId, int status) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_S;

		Object[] finderArgs = new Object[] { groupId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_G_S_GROUPID_2 = "backgroundTask.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_S_STATUS_2 = "backgroundTask.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_T_S = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByT_S",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_S = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByT_S",
			new String[] { String.class.getName(), Integer.class.getName() },
			BackgroundTaskModelImpl.TASKEXECUTORCLASSNAME_COLUMN_BITMASK |
			BackgroundTaskModelImpl.STATUS_COLUMN_BITMASK |
			BackgroundTaskModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_T_S = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByT_S",
			new String[] { String.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_S = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByT_S",
			new String[] { String.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the background tasks where taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByT_S(String taskExecutorClassName,
		int status) throws SystemException {
		return findByT_S(taskExecutorClassName, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByT_S(String taskExecutorClassName,
		int status, int start, int end) throws SystemException {
		return findByT_S(taskExecutorClassName, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks where taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByT_S(String taskExecutorClassName,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_S;
			finderArgs = new Object[] { taskExecutorClassName, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_T_S;
			finderArgs = new Object[] {
					taskExecutorClassName, status,
					
					start, end, orderByComparator
				};
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if (!Validator.equals(taskExecutorClassName,
							backgroundTask.getTaskExecutorClassName()) ||
						(status != backgroundTask.getStatus())) {
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

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_T_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
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
	 * Returns the first background task in the ordered set where taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByT_S_First(String taskExecutorClassName,
		int status, OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByT_S_First(taskExecutorClassName,
				status, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the first background task in the ordered set where taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByT_S_First(String taskExecutorClassName,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<BackgroundTask> list = findByT_S(taskExecutorClassName, status, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last background task in the ordered set where taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByT_S_Last(String taskExecutorClassName,
		int status, OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByT_S_Last(taskExecutorClassName,
				status, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the last background task in the ordered set where taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByT_S_Last(String taskExecutorClassName,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByT_S(taskExecutorClassName, status);

		if (count == 0) {
			return null;
		}

		List<BackgroundTask> list = findByT_S(taskExecutorClassName, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the background tasks before and after the current background task in the ordered set where taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param backgroundTaskId the primary key of the current background task
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask[] findByT_S_PrevAndNext(long backgroundTaskId,
		String taskExecutorClassName, int status,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = findByPrimaryKey(backgroundTaskId);

		Session session = null;

		try {
			session = openSession();

			BackgroundTask[] array = new BackgroundTaskImpl[3];

			array[0] = getByT_S_PrevAndNext(session, backgroundTask,
					taskExecutorClassName, status, orderByComparator, true);

			array[1] = backgroundTask;

			array[2] = getByT_S_PrevAndNext(session, backgroundTask,
					taskExecutorClassName, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BackgroundTask getByT_S_PrevAndNext(Session session,
		BackgroundTask backgroundTask, String taskExecutorClassName,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

		boolean bindTaskExecutorClassName = false;

		if (taskExecutorClassName == null) {
			query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_1);
		}
		else if (taskExecutorClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_3);
		}
		else {
			bindTaskExecutorClassName = true;

			query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_2);
		}

		query.append(_FINDER_COLUMN_T_S_STATUS_2);

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
			query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTaskExecutorClassName) {
			qPos.add(taskExecutorClassName);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backgroundTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackgroundTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the background tasks where taskExecutorClassName = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param taskExecutorClassNames the task executor class names
	 * @param status the status
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByT_S(String[] taskExecutorClassNames,
		int status) throws SystemException {
		return findByT_S(taskExecutorClassNames, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where taskExecutorClassName = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param taskExecutorClassNames the task executor class names
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByT_S(String[] taskExecutorClassNames,
		int status, int start, int end) throws SystemException {
		return findByT_S(taskExecutorClassNames, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks where taskExecutorClassName = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param taskExecutorClassNames the task executor class names
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByT_S(String[] taskExecutorClassNames,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if ((taskExecutorClassNames != null) &&
				(taskExecutorClassNames.length == 1)) {
			return findByT_S(taskExecutorClassNames[0], status, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(taskExecutorClassNames), status
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(taskExecutorClassNames), status,
					
					start, end, orderByComparator
				};
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_T_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if (!ArrayUtil.contains(taskExecutorClassNames,
							backgroundTask.getTaskExecutorClassName()) ||
						(status != backgroundTask.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			boolean conjunctionable = false;

			if ((taskExecutorClassNames == null) ||
					(taskExecutorClassNames.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < taskExecutorClassNames.length; i++) {
					String taskExecutorClassName = taskExecutorClassNames[i];

					if (taskExecutorClassName == null) {
						query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_4);
					}
					else if (taskExecutorClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_6);
					}
					else {
						query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_5);
					}

					if ((i + 1) < taskExecutorClassNames.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_T_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (taskExecutorClassNames != null) {
					qPos.add(taskExecutorClassNames);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_T_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_T_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the background tasks where taskExecutorClassName = &#63; and status = &#63; from the database.
	 *
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByT_S(String taskExecutorClassName, int status)
		throws SystemException {
		for (BackgroundTask backgroundTask : findByT_S(taskExecutorClassName,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(backgroundTask);
		}
	}

	/**
	 * Returns the number of background tasks where taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByT_S(String taskExecutorClassName, int status)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_T_S;

		Object[] finderArgs = new Object[] { taskExecutorClassName, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_T_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
				}

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

	/**
	 * Returns the number of background tasks where taskExecutorClassName = any &#63; and status = &#63;.
	 *
	 * @param taskExecutorClassNames the task executor class names
	 * @param status the status
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByT_S(String[] taskExecutorClassNames, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(taskExecutorClassNames), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			boolean conjunctionable = false;

			if ((taskExecutorClassNames == null) ||
					(taskExecutorClassNames.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < taskExecutorClassNames.length; i++) {
					String taskExecutorClassName = taskExecutorClassNames[i];

					if (taskExecutorClassName == null) {
						query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_4);
					}
					else if (taskExecutorClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_6);
					}
					else {
						query.append(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_5);
					}

					if ((i + 1) < taskExecutorClassNames.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_T_S_STATUS_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (taskExecutorClassNames != null) {
					qPos.add(taskExecutorClassNames);
				}

				qPos.add(status);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_1 = "backgroundTask.taskExecutorClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_2 = "backgroundTask.taskExecutorClassName = ? AND ";
	private static final String _FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_3 = "(backgroundTask.taskExecutorClassName IS NULL OR backgroundTask.taskExecutorClassName = '') AND ";
	private static final String _FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_4 = "(" +
		removeConjunction(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_1) + ")";
	private static final String _FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_5 = "(" +
		removeConjunction(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_2) + ")";
	private static final String _FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_6 = "(" +
		removeConjunction(_FINDER_COLUMN_T_S_TASKEXECUTORCLASSNAME_3) + ")";
	private static final String _FINDER_COLUMN_T_S_STATUS_2 = "backgroundTask.status = ?";
	private static final String _FINDER_COLUMN_T_S_STATUS_5 = "(" +
		removeConjunction(_FINDER_COLUMN_T_S_STATUS_2) + ")";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_N_T = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_N_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N_T = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_N_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			BackgroundTaskModelImpl.GROUPID_COLUMN_BITMASK |
			BackgroundTaskModelImpl.NAME_COLUMN_BITMASK |
			BackgroundTaskModelImpl.TASKEXECUTORCLASSNAME_COLUMN_BITMASK |
			BackgroundTaskModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_N_T = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_N_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_N_T(long groupId, String name,
		String taskExecutorClassName) throws SystemException {
		return findByG_N_T(groupId, name, taskExecutorClassName,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_N_T(long groupId, String name,
		String taskExecutorClassName, int start, int end)
		throws SystemException {
		return findByG_N_T(groupId, name, taskExecutorClassName, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_N_T(long groupId, String name,
		String taskExecutorClassName, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N_T;
			finderArgs = new Object[] { groupId, name, taskExecutorClassName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_N_T;
			finderArgs = new Object[] {
					groupId, name, taskExecutorClassName,
					
					start, end, orderByComparator
				};
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((groupId != backgroundTask.getGroupId()) ||
						!Validator.equals(name, backgroundTask.getName()) ||
						!Validator.equals(taskExecutorClassName,
							backgroundTask.getTaskExecutorClassName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_N_T_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_T_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_T_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_N_T_NAME_2);
			}

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
				}

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
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
	 * Returns the first background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_N_T_First(long groupId, String name,
		String taskExecutorClassName, OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_N_T_First(groupId, name,
				taskExecutorClassName, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the first background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_N_T_First(long groupId, String name,
		String taskExecutorClassName, OrderByComparator orderByComparator)
		throws SystemException {
		List<BackgroundTask> list = findByG_N_T(groupId, name,
				taskExecutorClassName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_N_T_Last(long groupId, String name,
		String taskExecutorClassName, OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_N_T_Last(groupId, name,
				taskExecutorClassName, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_N_T_Last(long groupId, String name,
		String taskExecutorClassName, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByG_N_T(groupId, name, taskExecutorClassName);

		if (count == 0) {
			return null;
		}

		List<BackgroundTask> list = findByG_N_T(groupId, name,
				taskExecutorClassName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param backgroundTaskId the primary key of the current background task
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask[] findByG_N_T_PrevAndNext(long backgroundTaskId,
		long groupId, String name, String taskExecutorClassName,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = findByPrimaryKey(backgroundTaskId);

		Session session = null;

		try {
			session = openSession();

			BackgroundTask[] array = new BackgroundTaskImpl[3];

			array[0] = getByG_N_T_PrevAndNext(session, backgroundTask, groupId,
					name, taskExecutorClassName, orderByComparator, true);

			array[1] = backgroundTask;

			array[2] = getByG_N_T_PrevAndNext(session, backgroundTask, groupId,
					name, taskExecutorClassName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BackgroundTask getByG_N_T_PrevAndNext(Session session,
		BackgroundTask backgroundTask, long groupId, String name,
		String taskExecutorClassName, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

		query.append(_FINDER_COLUMN_G_N_T_GROUPID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_N_T_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_N_T_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_N_T_NAME_2);
		}

		boolean bindTaskExecutorClassName = false;

		if (taskExecutorClassName == null) {
			query.append(_FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_1);
		}
		else if (taskExecutorClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_3);
		}
		else {
			bindTaskExecutorClassName = true;

			query.append(_FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_2);
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
			query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindName) {
			qPos.add(name);
		}

		if (bindTaskExecutorClassName) {
			qPos.add(taskExecutorClassName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backgroundTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackgroundTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_N_T(long groupId, String name,
		String taskExecutorClassName) throws SystemException {
		for (BackgroundTask backgroundTask : findByG_N_T(groupId, name,
				taskExecutorClassName, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(backgroundTask);
		}
	}

	/**
	 * Returns the number of background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_N_T(long groupId, String name,
		String taskExecutorClassName) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_N_T;

		Object[] finderArgs = new Object[] { groupId, name, taskExecutorClassName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_N_T_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_T_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_T_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_N_T_NAME_2);
			}

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
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

	private static final String _FINDER_COLUMN_G_N_T_GROUPID_2 = "backgroundTask.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_N_T_NAME_1 = "backgroundTask.name IS NULL AND ";
	private static final String _FINDER_COLUMN_G_N_T_NAME_2 = "backgroundTask.name = ? AND ";
	private static final String _FINDER_COLUMN_G_N_T_NAME_3 = "(backgroundTask.name IS NULL OR backgroundTask.name = '') AND ";
	private static final String _FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_1 = "backgroundTask.taskExecutorClassName IS NULL";
	private static final String _FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_2 = "backgroundTask.taskExecutorClassName = ?";
	private static final String _FINDER_COLUMN_G_N_T_TASKEXECUTORCLASSNAME_3 = "(backgroundTask.taskExecutorClassName IS NULL OR backgroundTask.taskExecutorClassName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T_C = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_T_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T_C = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_T_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			BackgroundTaskModelImpl.GROUPID_COLUMN_BITMASK |
			BackgroundTaskModelImpl.TASKEXECUTORCLASSNAME_COLUMN_BITMASK |
			BackgroundTaskModelImpl.COMPLETED_COLUMN_BITMASK |
			BackgroundTaskModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_T_C = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_T_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T_C = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_T_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_C(long groupId,
		String taskExecutorClassName, boolean completed)
		throws SystemException {
		return findByG_T_C(groupId, taskExecutorClassName, completed,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_C(long groupId,
		String taskExecutorClassName, boolean completed, int start, int end)
		throws SystemException {
		return findByG_T_C(groupId, taskExecutorClassName, completed, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_C(long groupId,
		String taskExecutorClassName, boolean completed, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T_C;
			finderArgs = new Object[] { groupId, taskExecutorClassName, completed };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T_C;
			finderArgs = new Object[] {
					groupId, taskExecutorClassName, completed,
					
					start, end, orderByComparator
				};
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((groupId != backgroundTask.getGroupId()) ||
						!Validator.equals(taskExecutorClassName,
							backgroundTask.getTaskExecutorClassName()) ||
						(completed != backgroundTask.getCompleted())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_T_C_GROUPID_2);

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_G_T_C_COMPLETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
				}

				qPos.add(completed);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
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
	 * Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_T_C_First(long groupId,
		String taskExecutorClassName, boolean completed,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_T_C_First(groupId,
				taskExecutorClassName, completed, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(", completed=");
		msg.append(completed);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_T_C_First(long groupId,
		String taskExecutorClassName, boolean completed,
		OrderByComparator orderByComparator) throws SystemException {
		List<BackgroundTask> list = findByG_T_C(groupId, taskExecutorClassName,
				completed, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_T_C_Last(long groupId,
		String taskExecutorClassName, boolean completed,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_T_C_Last(groupId,
				taskExecutorClassName, completed, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(", completed=");
		msg.append(completed);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_T_C_Last(long groupId,
		String taskExecutorClassName, boolean completed,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_T_C(groupId, taskExecutorClassName, completed);

		if (count == 0) {
			return null;
		}

		List<BackgroundTask> list = findByG_T_C(groupId, taskExecutorClassName,
				completed, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param backgroundTaskId the primary key of the current background task
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask[] findByG_T_C_PrevAndNext(long backgroundTaskId,
		long groupId, String taskExecutorClassName, boolean completed,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = findByPrimaryKey(backgroundTaskId);

		Session session = null;

		try {
			session = openSession();

			BackgroundTask[] array = new BackgroundTaskImpl[3];

			array[0] = getByG_T_C_PrevAndNext(session, backgroundTask, groupId,
					taskExecutorClassName, completed, orderByComparator, true);

			array[1] = backgroundTask;

			array[2] = getByG_T_C_PrevAndNext(session, backgroundTask, groupId,
					taskExecutorClassName, completed, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BackgroundTask getByG_T_C_PrevAndNext(Session session,
		BackgroundTask backgroundTask, long groupId,
		String taskExecutorClassName, boolean completed,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

		query.append(_FINDER_COLUMN_G_T_C_GROUPID_2);

		boolean bindTaskExecutorClassName = false;

		if (taskExecutorClassName == null) {
			query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_1);
		}
		else if (taskExecutorClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_3);
		}
		else {
			bindTaskExecutorClassName = true;

			query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_2);
		}

		query.append(_FINDER_COLUMN_G_T_C_COMPLETED_2);

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
			query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindTaskExecutorClassName) {
			qPos.add(taskExecutorClassName);
		}

		qPos.add(completed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backgroundTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackgroundTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @param completed the completed
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_C(long groupId,
		String[] taskExecutorClassNames, boolean completed)
		throws SystemException {
		return findByG_T_C(groupId, taskExecutorClassNames, completed,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @param completed the completed
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_C(long groupId,
		String[] taskExecutorClassNames, boolean completed, int start, int end)
		throws SystemException {
		return findByG_T_C(groupId, taskExecutorClassNames, completed, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @param completed the completed
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_C(long groupId,
		String[] taskExecutorClassNames, boolean completed, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if ((taskExecutorClassNames != null) &&
				(taskExecutorClassNames.length == 1)) {
			return findByG_T_C(groupId, taskExecutorClassNames[0], completed,
				start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(taskExecutorClassNames), completed
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(taskExecutorClassNames), completed,
					
					start, end, orderByComparator
				};
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T_C,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((groupId != backgroundTask.getGroupId()) ||
						!ArrayUtil.contains(taskExecutorClassNames,
							backgroundTask.getTaskExecutorClassName()) ||
						(completed != backgroundTask.getCompleted())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_T_C_GROUPID_5);

			conjunctionable = true;

			if ((taskExecutorClassNames == null) ||
					(taskExecutorClassNames.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < taskExecutorClassNames.length; i++) {
					String taskExecutorClassName = taskExecutorClassNames[i];

					if (taskExecutorClassName == null) {
						query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_4);
					}
					else if (taskExecutorClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_5);
					}

					if ((i + 1) < taskExecutorClassNames.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_T_C_COMPLETED_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (taskExecutorClassNames != null) {
					qPos.add(taskExecutorClassNames);
				}

				qPos.add(completed);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T_C,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T_C,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_T_C(long groupId, String taskExecutorClassName,
		boolean completed) throws SystemException {
		for (BackgroundTask backgroundTask : findByG_T_C(groupId,
				taskExecutorClassName, completed, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(backgroundTask);
		}
	}

	/**
	 * Returns the number of background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_T_C(long groupId, String taskExecutorClassName,
		boolean completed) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_T_C;

		Object[] finderArgs = new Object[] {
				groupId, taskExecutorClassName, completed
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_T_C_GROUPID_2);

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_G_T_C_COMPLETED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
				}

				qPos.add(completed);

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
	 * Returns the number of background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @param completed the completed
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_T_C(long groupId, String[] taskExecutorClassNames,
		boolean completed) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(taskExecutorClassNames), completed
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_T_C_GROUPID_5);

			conjunctionable = true;

			if ((taskExecutorClassNames == null) ||
					(taskExecutorClassNames.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < taskExecutorClassNames.length; i++) {
					String taskExecutorClassName = taskExecutorClassNames[i];

					if (taskExecutorClassName == null) {
						query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_4);
					}
					else if (taskExecutorClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_5);
					}

					if ((i + 1) < taskExecutorClassNames.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_T_C_COMPLETED_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (taskExecutorClassNames != null) {
					qPos.add(taskExecutorClassNames);
				}

				qPos.add(completed);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T_C,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T_C,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_T_C_GROUPID_2 = "backgroundTask.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_T_C_GROUPID_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_C_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_1 = "backgroundTask.taskExecutorClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_2 = "backgroundTask.taskExecutorClassName = ? AND ";
	private static final String _FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_3 = "(backgroundTask.taskExecutorClassName IS NULL OR backgroundTask.taskExecutorClassName = '') AND ";
	private static final String _FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_4 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_1) + ")";
	private static final String _FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_2) + ")";
	private static final String _FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_6 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_C_TASKEXECUTORCLASSNAME_3) + ")";
	private static final String _FINDER_COLUMN_G_T_C_COMPLETED_2 = "backgroundTask.completed = ?";
	private static final String _FINDER_COLUMN_G_T_C_COMPLETED_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_C_COMPLETED_2) + ")";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T_S = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_T_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T_S = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_T_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			BackgroundTaskModelImpl.GROUPID_COLUMN_BITMASK |
			BackgroundTaskModelImpl.TASKEXECUTORCLASSNAME_COLUMN_BITMASK |
			BackgroundTaskModelImpl.STATUS_COLUMN_BITMASK |
			BackgroundTaskModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_T_S = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_T_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T_S = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_T_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_S(long groupId,
		String taskExecutorClassName, int status) throws SystemException {
		return findByG_T_S(groupId, taskExecutorClassName, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_S(long groupId,
		String taskExecutorClassName, int status, int start, int end)
		throws SystemException {
		return findByG_T_S(groupId, taskExecutorClassName, status, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_S(long groupId,
		String taskExecutorClassName, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T_S;
			finderArgs = new Object[] { groupId, taskExecutorClassName, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T_S;
			finderArgs = new Object[] {
					groupId, taskExecutorClassName, status,
					
					start, end, orderByComparator
				};
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((groupId != backgroundTask.getGroupId()) ||
						!Validator.equals(taskExecutorClassName,
							backgroundTask.getTaskExecutorClassName()) ||
						(status != backgroundTask.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_T_S_GROUPID_2);

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_G_T_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
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
	 * Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_T_S_First(long groupId,
		String taskExecutorClassName, int status,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_T_S_First(groupId,
				taskExecutorClassName, status, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_T_S_First(long groupId,
		String taskExecutorClassName, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<BackgroundTask> list = findByG_T_S(groupId, taskExecutorClassName,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_T_S_Last(long groupId,
		String taskExecutorClassName, int status,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_T_S_Last(groupId,
				taskExecutorClassName, status, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_T_S_Last(long groupId,
		String taskExecutorClassName, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_T_S(groupId, taskExecutorClassName, status);

		if (count == 0) {
			return null;
		}

		List<BackgroundTask> list = findByG_T_S(groupId, taskExecutorClassName,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param backgroundTaskId the primary key of the current background task
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask[] findByG_T_S_PrevAndNext(long backgroundTaskId,
		long groupId, String taskExecutorClassName, int status,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = findByPrimaryKey(backgroundTaskId);

		Session session = null;

		try {
			session = openSession();

			BackgroundTask[] array = new BackgroundTaskImpl[3];

			array[0] = getByG_T_S_PrevAndNext(session, backgroundTask, groupId,
					taskExecutorClassName, status, orderByComparator, true);

			array[1] = backgroundTask;

			array[2] = getByG_T_S_PrevAndNext(session, backgroundTask, groupId,
					taskExecutorClassName, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BackgroundTask getByG_T_S_PrevAndNext(Session session,
		BackgroundTask backgroundTask, long groupId,
		String taskExecutorClassName, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

		query.append(_FINDER_COLUMN_G_T_S_GROUPID_2);

		boolean bindTaskExecutorClassName = false;

		if (taskExecutorClassName == null) {
			query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_1);
		}
		else if (taskExecutorClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_3);
		}
		else {
			bindTaskExecutorClassName = true;

			query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_2);
		}

		query.append(_FINDER_COLUMN_G_T_S_STATUS_2);

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
			query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindTaskExecutorClassName) {
			qPos.add(taskExecutorClassName);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backgroundTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackgroundTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @param status the status
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_S(long groupId,
		String[] taskExecutorClassNames, int status) throws SystemException {
		return findByG_T_S(groupId, taskExecutorClassNames, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_S(long groupId,
		String[] taskExecutorClassNames, int status, int start, int end)
		throws SystemException {
		return findByG_T_S(groupId, taskExecutorClassNames, status, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @param status the status
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_T_S(long groupId,
		String[] taskExecutorClassNames, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if ((taskExecutorClassNames != null) &&
				(taskExecutorClassNames.length == 1)) {
			return findByG_T_S(groupId, taskExecutorClassNames[0], status,
				start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(taskExecutorClassNames), status
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(taskExecutorClassNames), status,
					
					start, end, orderByComparator
				};
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((groupId != backgroundTask.getGroupId()) ||
						!ArrayUtil.contains(taskExecutorClassNames,
							backgroundTask.getTaskExecutorClassName()) ||
						(status != backgroundTask.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_T_S_GROUPID_5);

			conjunctionable = true;

			if ((taskExecutorClassNames == null) ||
					(taskExecutorClassNames.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < taskExecutorClassNames.length; i++) {
					String taskExecutorClassName = taskExecutorClassNames[i];

					if (taskExecutorClassName == null) {
						query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_4);
					}
					else if (taskExecutorClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_5);
					}

					if ((i + 1) < taskExecutorClassNames.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_T_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (taskExecutorClassNames != null) {
					qPos.add(taskExecutorClassNames);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_T_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_T_S(long groupId, String taskExecutorClassName,
		int status) throws SystemException {
		for (BackgroundTask backgroundTask : findByG_T_S(groupId,
				taskExecutorClassName, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(backgroundTask);
		}
	}

	/**
	 * Returns the number of background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassName the task executor class name
	 * @param status the status
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_T_S(long groupId, String taskExecutorClassName,
		int status) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_T_S;

		Object[] finderArgs = new Object[] {
				groupId, taskExecutorClassName, status
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_T_S_GROUPID_2);

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_G_T_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
				}

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

	/**
	 * Returns the number of background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param taskExecutorClassNames the task executor class names
	 * @param status the status
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_T_S(long groupId, String[] taskExecutorClassNames,
		int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(taskExecutorClassNames), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_T_S_GROUPID_5);

			conjunctionable = true;

			if ((taskExecutorClassNames == null) ||
					(taskExecutorClassNames.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < taskExecutorClassNames.length; i++) {
					String taskExecutorClassName = taskExecutorClassNames[i];

					if (taskExecutorClassName == null) {
						query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_4);
					}
					else if (taskExecutorClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_5);
					}

					if ((i + 1) < taskExecutorClassNames.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_T_S_STATUS_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (taskExecutorClassNames != null) {
					qPos.add(taskExecutorClassNames);
				}

				qPos.add(status);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_T_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_T_S_GROUPID_2 = "backgroundTask.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_T_S_GROUPID_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_S_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_1 = "backgroundTask.taskExecutorClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_2 = "backgroundTask.taskExecutorClassName = ? AND ";
	private static final String _FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_3 = "(backgroundTask.taskExecutorClassName IS NULL OR backgroundTask.taskExecutorClassName = '') AND ";
	private static final String _FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_4 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_1) + ")";
	private static final String _FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_2) + ")";
	private static final String _FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_6 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_S_TASKEXECUTORCLASSNAME_3) + ")";
	private static final String _FINDER_COLUMN_G_T_S_STATUS_2 = "backgroundTask.status = ?";
	private static final String _FINDER_COLUMN_G_T_S_STATUS_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_T_S_STATUS_2) + ")";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_N_T_C = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_N_T_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N_T_C =
		new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED,
			BackgroundTaskImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_N_T_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName()
			},
			BackgroundTaskModelImpl.GROUPID_COLUMN_BITMASK |
			BackgroundTaskModelImpl.NAME_COLUMN_BITMASK |
			BackgroundTaskModelImpl.TASKEXECUTORCLASSNAME_COLUMN_BITMASK |
			BackgroundTaskModelImpl.COMPLETED_COLUMN_BITMASK |
			BackgroundTaskModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_N_T_C = new FinderPath(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_N_T_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @return the matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_N_T_C(long groupId, String name,
		String taskExecutorClassName, boolean completed)
		throws SystemException {
		return findByG_N_T_C(groupId, name, taskExecutorClassName, completed,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_N_T_C(long groupId, String name,
		String taskExecutorClassName, boolean completed, int start, int end)
		throws SystemException {
		return findByG_N_T_C(groupId, name, taskExecutorClassName, completed,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findByG_N_T_C(long groupId, String name,
		String taskExecutorClassName, boolean completed, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N_T_C;
			finderArgs = new Object[] {
					groupId, name, taskExecutorClassName, completed
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_N_T_C;
			finderArgs = new Object[] {
					groupId, name, taskExecutorClassName, completed,
					
					start, end, orderByComparator
				};
		}

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BackgroundTask backgroundTask : list) {
				if ((groupId != backgroundTask.getGroupId()) ||
						!Validator.equals(name, backgroundTask.getName()) ||
						!Validator.equals(taskExecutorClassName,
							backgroundTask.getTaskExecutorClassName()) ||
						(completed != backgroundTask.getCompleted())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_N_T_C_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_T_C_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_T_C_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_N_T_C_NAME_2);
			}

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_G_N_T_C_COMPLETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
				}

				qPos.add(completed);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
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
	 * Returns the first background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_N_T_C_First(long groupId, String name,
		String taskExecutorClassName, boolean completed,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_N_T_C_First(groupId, name,
				taskExecutorClassName, completed, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(", completed=");
		msg.append(completed);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the first background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_N_T_C_First(long groupId, String name,
		String taskExecutorClassName, boolean completed,
		OrderByComparator orderByComparator) throws SystemException {
		List<BackgroundTask> list = findByG_N_T_C(groupId, name,
				taskExecutorClassName, completed, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByG_N_T_C_Last(long groupId, String name,
		String taskExecutorClassName, boolean completed,
		OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByG_N_T_C_Last(groupId, name,
				taskExecutorClassName, completed, orderByComparator);

		if (backgroundTask != null) {
			return backgroundTask;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", taskExecutorClassName=");
		msg.append(taskExecutorClassName);

		msg.append(", completed=");
		msg.append(completed);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackgroundTaskException(msg.toString());
	}

	/**
	 * Returns the last background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching background task, or <code>null</code> if a matching background task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByG_N_T_C_Last(long groupId, String name,
		String taskExecutorClassName, boolean completed,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_N_T_C(groupId, name, taskExecutorClassName,
				completed);

		if (count == 0) {
			return null;
		}

		List<BackgroundTask> list = findByG_N_T_C(groupId, name,
				taskExecutorClassName, completed, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param backgroundTaskId the primary key of the current background task
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask[] findByG_N_T_C_PrevAndNext(long backgroundTaskId,
		long groupId, String name, String taskExecutorClassName,
		boolean completed, OrderByComparator orderByComparator)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = findByPrimaryKey(backgroundTaskId);

		Session session = null;

		try {
			session = openSession();

			BackgroundTask[] array = new BackgroundTaskImpl[3];

			array[0] = getByG_N_T_C_PrevAndNext(session, backgroundTask,
					groupId, name, taskExecutorClassName, completed,
					orderByComparator, true);

			array[1] = backgroundTask;

			array[2] = getByG_N_T_C_PrevAndNext(session, backgroundTask,
					groupId, name, taskExecutorClassName, completed,
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

	protected BackgroundTask getByG_N_T_C_PrevAndNext(Session session,
		BackgroundTask backgroundTask, long groupId, String name,
		String taskExecutorClassName, boolean completed,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BACKGROUNDTASK_WHERE);

		query.append(_FINDER_COLUMN_G_N_T_C_GROUPID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_N_T_C_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_N_T_C_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_N_T_C_NAME_2);
		}

		boolean bindTaskExecutorClassName = false;

		if (taskExecutorClassName == null) {
			query.append(_FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_1);
		}
		else if (taskExecutorClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_3);
		}
		else {
			bindTaskExecutorClassName = true;

			query.append(_FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_2);
		}

		query.append(_FINDER_COLUMN_G_N_T_C_COMPLETED_2);

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
			query.append(BackgroundTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindName) {
			qPos.add(name);
		}

		if (bindTaskExecutorClassName) {
			qPos.add(taskExecutorClassName);
		}

		qPos.add(completed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backgroundTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackgroundTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_N_T_C(long groupId, String name,
		String taskExecutorClassName, boolean completed)
		throws SystemException {
		for (BackgroundTask backgroundTask : findByG_N_T_C(groupId, name,
				taskExecutorClassName, completed, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(backgroundTask);
		}
	}

	/**
	 * Returns the number of background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param taskExecutorClassName the task executor class name
	 * @param completed the completed
	 * @return the number of matching background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_N_T_C(long groupId, String name,
		String taskExecutorClassName, boolean completed)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_N_T_C;

		Object[] finderArgs = new Object[] {
				groupId, name, taskExecutorClassName, completed
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_BACKGROUNDTASK_WHERE);

			query.append(_FINDER_COLUMN_G_N_T_C_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_T_C_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_T_C_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_N_T_C_NAME_2);
			}

			boolean bindTaskExecutorClassName = false;

			if (taskExecutorClassName == null) {
				query.append(_FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_1);
			}
			else if (taskExecutorClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_3);
			}
			else {
				bindTaskExecutorClassName = true;

				query.append(_FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_G_N_T_C_COMPLETED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				if (bindTaskExecutorClassName) {
					qPos.add(taskExecutorClassName);
				}

				qPos.add(completed);

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

	private static final String _FINDER_COLUMN_G_N_T_C_GROUPID_2 = "backgroundTask.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_N_T_C_NAME_1 = "backgroundTask.name IS NULL AND ";
	private static final String _FINDER_COLUMN_G_N_T_C_NAME_2 = "backgroundTask.name = ? AND ";
	private static final String _FINDER_COLUMN_G_N_T_C_NAME_3 = "(backgroundTask.name IS NULL OR backgroundTask.name = '') AND ";
	private static final String _FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_1 = "backgroundTask.taskExecutorClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_2 = "backgroundTask.taskExecutorClassName = ? AND ";
	private static final String _FINDER_COLUMN_G_N_T_C_TASKEXECUTORCLASSNAME_3 = "(backgroundTask.taskExecutorClassName IS NULL OR backgroundTask.taskExecutorClassName = '') AND ";
	private static final String _FINDER_COLUMN_G_N_T_C_COMPLETED_2 = "backgroundTask.completed = ?";

	public BackgroundTaskPersistenceImpl() {
		setModelClass(BackgroundTask.class);
	}

	/**
	 * Caches the background task in the entity cache if it is enabled.
	 *
	 * @param backgroundTask the background task
	 */
	@Override
	public void cacheResult(BackgroundTask backgroundTask) {
		EntityCacheUtil.putResult(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskImpl.class, backgroundTask.getPrimaryKey(),
			backgroundTask);

		backgroundTask.resetOriginalValues();
	}

	/**
	 * Caches the background tasks in the entity cache if it is enabled.
	 *
	 * @param backgroundTasks the background tasks
	 */
	@Override
	public void cacheResult(List<BackgroundTask> backgroundTasks) {
		for (BackgroundTask backgroundTask : backgroundTasks) {
			if (EntityCacheUtil.getResult(
						BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
						BackgroundTaskImpl.class, backgroundTask.getPrimaryKey()) == null) {
				cacheResult(backgroundTask);
			}
			else {
				backgroundTask.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all background tasks.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(BackgroundTaskImpl.class.getName());
		}

		EntityCacheUtil.clearCache(BackgroundTaskImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the background task.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BackgroundTask backgroundTask) {
		EntityCacheUtil.removeResult(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskImpl.class, backgroundTask.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<BackgroundTask> backgroundTasks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BackgroundTask backgroundTask : backgroundTasks) {
			EntityCacheUtil.removeResult(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
				BackgroundTaskImpl.class, backgroundTask.getPrimaryKey());
		}
	}

	/**
	 * Creates a new background task with the primary key. Does not add the background task to the database.
	 *
	 * @param backgroundTaskId the primary key for the new background task
	 * @return the new background task
	 */
	@Override
	public BackgroundTask create(long backgroundTaskId) {
		BackgroundTask backgroundTask = new BackgroundTaskImpl();

		backgroundTask.setNew(true);
		backgroundTask.setPrimaryKey(backgroundTaskId);

		return backgroundTask;
	}

	/**
	 * Removes the background task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param backgroundTaskId the primary key of the background task
	 * @return the background task that was removed
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask remove(long backgroundTaskId)
		throws NoSuchBackgroundTaskException, SystemException {
		return remove((Serializable)backgroundTaskId);
	}

	/**
	 * Removes the background task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the background task
	 * @return the background task that was removed
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask remove(Serializable primaryKey)
		throws NoSuchBackgroundTaskException, SystemException {
		Session session = null;

		try {
			session = openSession();

			BackgroundTask backgroundTask = (BackgroundTask)session.get(BackgroundTaskImpl.class,
					primaryKey);

			if (backgroundTask == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBackgroundTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(backgroundTask);
		}
		catch (NoSuchBackgroundTaskException nsee) {
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
	protected BackgroundTask removeImpl(BackgroundTask backgroundTask)
		throws SystemException {
		backgroundTask = toUnwrappedModel(backgroundTask);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(backgroundTask)) {
				backgroundTask = (BackgroundTask)session.get(BackgroundTaskImpl.class,
						backgroundTask.getPrimaryKeyObj());
			}

			if (backgroundTask != null) {
				session.delete(backgroundTask);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (backgroundTask != null) {
			clearCache(backgroundTask);
		}

		return backgroundTask;
	}

	@Override
	public BackgroundTask updateImpl(
		com.liferay.portal.model.BackgroundTask backgroundTask)
		throws SystemException {
		backgroundTask = toUnwrappedModel(backgroundTask);

		boolean isNew = backgroundTask.isNew();

		BackgroundTaskModelImpl backgroundTaskModelImpl = (BackgroundTaskModelImpl)backgroundTask;

		Session session = null;

		try {
			session = openSession();

			if (backgroundTask.isNew()) {
				session.save(backgroundTask);

				backgroundTask.setNew(false);
			}
			else {
				session.merge(backgroundTask);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !BackgroundTaskModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((backgroundTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backgroundTaskModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { backgroundTaskModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((backgroundTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backgroundTaskModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { backgroundTaskModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((backgroundTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backgroundTaskModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] { backgroundTaskModelImpl.getStatus() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((backgroundTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backgroundTaskModelImpl.getOriginalGroupId(),
						backgroundTaskModelImpl.getOriginalTaskExecutorClassName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T,
					args);

				args = new Object[] {
						backgroundTaskModelImpl.getGroupId(),
						backgroundTaskModelImpl.getTaskExecutorClassName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T,
					args);
			}

			if ((backgroundTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backgroundTaskModelImpl.getOriginalGroupId(),
						backgroundTaskModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);

				args = new Object[] {
						backgroundTaskModelImpl.getGroupId(),
						backgroundTaskModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);
			}

			if ((backgroundTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backgroundTaskModelImpl.getOriginalTaskExecutorClassName(),
						backgroundTaskModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_S,
					args);

				args = new Object[] {
						backgroundTaskModelImpl.getTaskExecutorClassName(),
						backgroundTaskModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_S,
					args);
			}

			if ((backgroundTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backgroundTaskModelImpl.getOriginalGroupId(),
						backgroundTaskModelImpl.getOriginalName(),
						backgroundTaskModelImpl.getOriginalTaskExecutorClassName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_N_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N_T,
					args);

				args = new Object[] {
						backgroundTaskModelImpl.getGroupId(),
						backgroundTaskModelImpl.getName(),
						backgroundTaskModelImpl.getTaskExecutorClassName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_N_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N_T,
					args);
			}

			if ((backgroundTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backgroundTaskModelImpl.getOriginalGroupId(),
						backgroundTaskModelImpl.getOriginalTaskExecutorClassName(),
						backgroundTaskModelImpl.getOriginalCompleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_T_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T_C,
					args);

				args = new Object[] {
						backgroundTaskModelImpl.getGroupId(),
						backgroundTaskModelImpl.getTaskExecutorClassName(),
						backgroundTaskModelImpl.getCompleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_T_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T_C,
					args);
			}

			if ((backgroundTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backgroundTaskModelImpl.getOriginalGroupId(),
						backgroundTaskModelImpl.getOriginalTaskExecutorClassName(),
						backgroundTaskModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T_S,
					args);

				args = new Object[] {
						backgroundTaskModelImpl.getGroupId(),
						backgroundTaskModelImpl.getTaskExecutorClassName(),
						backgroundTaskModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_T_S,
					args);
			}

			if ((backgroundTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N_T_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backgroundTaskModelImpl.getOriginalGroupId(),
						backgroundTaskModelImpl.getOriginalName(),
						backgroundTaskModelImpl.getOriginalTaskExecutorClassName(),
						backgroundTaskModelImpl.getOriginalCompleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_N_T_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N_T_C,
					args);

				args = new Object[] {
						backgroundTaskModelImpl.getGroupId(),
						backgroundTaskModelImpl.getName(),
						backgroundTaskModelImpl.getTaskExecutorClassName(),
						backgroundTaskModelImpl.getCompleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_N_T_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N_T_C,
					args);
			}
		}

		EntityCacheUtil.putResult(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
			BackgroundTaskImpl.class, backgroundTask.getPrimaryKey(),
			backgroundTask);

		return backgroundTask;
	}

	protected BackgroundTask toUnwrappedModel(BackgroundTask backgroundTask) {
		if (backgroundTask instanceof BackgroundTaskImpl) {
			return backgroundTask;
		}

		BackgroundTaskImpl backgroundTaskImpl = new BackgroundTaskImpl();

		backgroundTaskImpl.setNew(backgroundTask.isNew());
		backgroundTaskImpl.setPrimaryKey(backgroundTask.getPrimaryKey());

		backgroundTaskImpl.setBackgroundTaskId(backgroundTask.getBackgroundTaskId());
		backgroundTaskImpl.setGroupId(backgroundTask.getGroupId());
		backgroundTaskImpl.setCompanyId(backgroundTask.getCompanyId());
		backgroundTaskImpl.setUserId(backgroundTask.getUserId());
		backgroundTaskImpl.setUserName(backgroundTask.getUserName());
		backgroundTaskImpl.setCreateDate(backgroundTask.getCreateDate());
		backgroundTaskImpl.setModifiedDate(backgroundTask.getModifiedDate());
		backgroundTaskImpl.setName(backgroundTask.getName());
		backgroundTaskImpl.setServletContextNames(backgroundTask.getServletContextNames());
		backgroundTaskImpl.setTaskExecutorClassName(backgroundTask.getTaskExecutorClassName());
		backgroundTaskImpl.setTaskContext(backgroundTask.getTaskContext());
		backgroundTaskImpl.setCompleted(backgroundTask.isCompleted());
		backgroundTaskImpl.setCompletionDate(backgroundTask.getCompletionDate());
		backgroundTaskImpl.setStatus(backgroundTask.getStatus());
		backgroundTaskImpl.setStatusMessage(backgroundTask.getStatusMessage());

		return backgroundTaskImpl;
	}

	/**
	 * Returns the background task with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the background task
	 * @return the background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBackgroundTaskException, SystemException {
		BackgroundTask backgroundTask = fetchByPrimaryKey(primaryKey);

		if (backgroundTask == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBackgroundTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return backgroundTask;
	}

	/**
	 * Returns the background task with the primary key or throws a {@link com.liferay.portal.NoSuchBackgroundTaskException} if it could not be found.
	 *
	 * @param backgroundTaskId the primary key of the background task
	 * @return the background task
	 * @throws com.liferay.portal.NoSuchBackgroundTaskException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask findByPrimaryKey(long backgroundTaskId)
		throws NoSuchBackgroundTaskException, SystemException {
		return findByPrimaryKey((Serializable)backgroundTaskId);
	}

	/**
	 * Returns the background task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the background task
	 * @return the background task, or <code>null</code> if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		BackgroundTask backgroundTask = (BackgroundTask)EntityCacheUtil.getResult(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
				BackgroundTaskImpl.class, primaryKey);

		if (backgroundTask == _nullBackgroundTask) {
			return null;
		}

		if (backgroundTask == null) {
			Session session = null;

			try {
				session = openSession();

				backgroundTask = (BackgroundTask)session.get(BackgroundTaskImpl.class,
						primaryKey);

				if (backgroundTask != null) {
					cacheResult(backgroundTask);
				}
				else {
					EntityCacheUtil.putResult(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
						BackgroundTaskImpl.class, primaryKey,
						_nullBackgroundTask);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(BackgroundTaskModelImpl.ENTITY_CACHE_ENABLED,
					BackgroundTaskImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return backgroundTask;
	}

	/**
	 * Returns the background task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param backgroundTaskId the primary key of the background task
	 * @return the background task, or <code>null</code> if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask fetchByPrimaryKey(long backgroundTaskId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)backgroundTaskId);
	}

	/**
	 * Returns all the background tasks.
	 *
	 * @return the background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the background tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the background tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> findAll(int start, int end,
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

		List<BackgroundTask> list = (List<BackgroundTask>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_BACKGROUNDTASK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BACKGROUNDTASK;

				if (pagination) {
					sql = sql.concat(BackgroundTaskModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BackgroundTask>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BackgroundTask>(list);
				}
				else {
					list = (List<BackgroundTask>)QueryUtil.list(q,
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
	 * Removes all the background tasks from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (BackgroundTask backgroundTask : findAll()) {
			remove(backgroundTask);
		}
	}

	/**
	 * Returns the number of background tasks.
	 *
	 * @return the number of background tasks
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

				Query q = session.createQuery(_SQL_COUNT_BACKGROUNDTASK);

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
	 * Initializes the background task persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portal.model.BackgroundTask")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<BackgroundTask>> listenersList = new ArrayList<ModelListener<BackgroundTask>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<BackgroundTask>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(BackgroundTaskImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_BACKGROUNDTASK = "SELECT backgroundTask FROM BackgroundTask backgroundTask";
	private static final String _SQL_SELECT_BACKGROUNDTASK_WHERE = "SELECT backgroundTask FROM BackgroundTask backgroundTask WHERE ";
	private static final String _SQL_COUNT_BACKGROUNDTASK = "SELECT COUNT(backgroundTask) FROM BackgroundTask backgroundTask";
	private static final String _SQL_COUNT_BACKGROUNDTASK_WHERE = "SELECT COUNT(backgroundTask) FROM BackgroundTask backgroundTask WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "backgroundTask.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BackgroundTask exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BackgroundTask exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(BackgroundTaskPersistenceImpl.class);
	private static BackgroundTask _nullBackgroundTask = new BackgroundTaskImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<BackgroundTask> toCacheModel() {
				return _nullBackgroundTaskCacheModel;
			}
		};

	private static CacheModel<BackgroundTask> _nullBackgroundTaskCacheModel = new CacheModel<BackgroundTask>() {
			@Override
			public BackgroundTask toEntityModel() {
				return _nullBackgroundTask;
			}
		};
}