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

package com.liferay.portlet.social.service.persistence;

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

import com.liferay.portlet.social.NoSuchActivityException;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.impl.SocialActivityImpl;
import com.liferay.portlet.social.model.impl.SocialActivityModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the social activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityPersistence
 * @see SocialActivityUtil
 * @generated
 */
public class SocialActivityPersistenceImpl extends BasePersistenceImpl<SocialActivity>
	implements SocialActivityPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SocialActivityUtil} to access the social activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SocialActivityImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			SocialActivityModelImpl.GROUPID_COLUMN_BITMASK |
			SocialActivityModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the social activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @return the range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByGroupId(long groupId, int start, int end,
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

		List<SocialActivity> list = (List<SocialActivity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivity socialActivity : list) {
				if ((groupId != socialActivity.getGroupId())) {
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

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<SocialActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SocialActivity>(list);
				}
				else {
					list = (List<SocialActivity>)QueryUtil.list(q,
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
	 * Returns the first social activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByGroupId_First(groupId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the first social activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SocialActivity> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the last social activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<SocialActivity> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activities before and after the current social activity in the ordered set where groupId = &#63;.
	 *
	 * @param activityId the primary key of the current social activity
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity[] findByGroupId_PrevAndNext(long activityId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			SocialActivity[] array = new SocialActivityImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, socialActivity,
					groupId, orderByComparator, true);

			array[1] = socialActivity;

			array[2] = getByGroupId_PrevAndNext(session, socialActivity,
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

	protected SocialActivity getByGroupId_PrevAndNext(Session session,
		SocialActivity socialActivity, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

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
			query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social activities where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (SocialActivity socialActivity : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(socialActivity);
		}
	}

	/**
	 * Returns the number of social activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching social activities
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

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "socialActivity.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			SocialActivityModelImpl.COMPANYID_COLUMN_BITMASK |
			SocialActivityModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the social activities where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the social activities where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @return the range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activities where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByCompanyId(long companyId, int start,
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

		List<SocialActivity> list = (List<SocialActivity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivity socialActivity : list) {
				if ((companyId != socialActivity.getCompanyId())) {
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

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<SocialActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SocialActivity>(list);
				}
				else {
					list = (List<SocialActivity>)QueryUtil.list(q,
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
	 * Returns the first social activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the first social activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SocialActivity> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the last social activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<SocialActivity> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activities before and after the current social activity in the ordered set where companyId = &#63;.
	 *
	 * @param activityId the primary key of the current social activity
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity[] findByCompanyId_PrevAndNext(long activityId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			SocialActivity[] array = new SocialActivityImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, socialActivity,
					companyId, orderByComparator, true);

			array[1] = socialActivity;

			array[2] = getByCompanyId_PrevAndNext(session, socialActivity,
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

	protected SocialActivity getByCompanyId_PrevAndNext(Session session,
		SocialActivity socialActivity, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

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
			query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social activities where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCompanyId(long companyId) throws SystemException {
		for (SocialActivity socialActivity : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(socialActivity);
		}
	}

	/**
	 * Returns the number of social activities where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching social activities
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

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "socialActivity.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			SocialActivityModelImpl.USERID_COLUMN_BITMASK |
			SocialActivityModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the social activities where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activities where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @return the range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activities where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<SocialActivity> list = (List<SocialActivity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivity socialActivity : list) {
				if ((userId != socialActivity.getUserId())) {
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

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<SocialActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SocialActivity>(list);
				}
				else {
					list = (List<SocialActivity>)QueryUtil.list(q,
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
	 * Returns the first social activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByUserId_First(userId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the first social activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SocialActivity> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByUserId_Last(userId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the last social activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<SocialActivity> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activities before and after the current social activity in the ordered set where userId = &#63;.
	 *
	 * @param activityId the primary key of the current social activity
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity[] findByUserId_PrevAndNext(long activityId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			SocialActivity[] array = new SocialActivityImpl[3];

			array[0] = getByUserId_PrevAndNext(session, socialActivity, userId,
					orderByComparator, true);

			array[1] = socialActivity;

			array[2] = getByUserId_PrevAndNext(session, socialActivity, userId,
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

	protected SocialActivity getByUserId_PrevAndNext(Session session,
		SocialActivity socialActivity, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social activities where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserId(long userId) throws SystemException {
		for (SocialActivity socialActivity : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(socialActivity);
		}
	}

	/**
	 * Returns the number of social activities where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserId(long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "socialActivity.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSETID =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByActivitySetId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSETID =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActivitySetId",
			new String[] { Long.class.getName() },
			SocialActivityModelImpl.ACTIVITYSETID_COLUMN_BITMASK |
			SocialActivityModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVITYSETID = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActivitySetId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the social activities where activitySetId = &#63;.
	 *
	 * @param activitySetId the activity set ID
	 * @return the matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByActivitySetId(long activitySetId)
		throws SystemException {
		return findByActivitySetId(activitySetId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activities where activitySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param activitySetId the activity set ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @return the range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByActivitySetId(long activitySetId,
		int start, int end) throws SystemException {
		return findByActivitySetId(activitySetId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activities where activitySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param activitySetId the activity set ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByActivitySetId(long activitySetId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSETID;
			finderArgs = new Object[] { activitySetId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSETID;
			finderArgs = new Object[] {
					activitySetId,
					
					start, end, orderByComparator
				};
		}

		List<SocialActivity> list = (List<SocialActivity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivity socialActivity : list) {
				if ((activitySetId != socialActivity.getActivitySetId())) {
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

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_ACTIVITYSETID_ACTIVITYSETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(activitySetId);

				if (!pagination) {
					list = (List<SocialActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SocialActivity>(list);
				}
				else {
					list = (List<SocialActivity>)QueryUtil.list(q,
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
	 * Returns the first social activity in the ordered set where activitySetId = &#63;.
	 *
	 * @param activitySetId the activity set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByActivitySetId_First(long activitySetId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByActivitySetId_First(activitySetId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("activitySetId=");
		msg.append(activitySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the first social activity in the ordered set where activitySetId = &#63;.
	 *
	 * @param activitySetId the activity set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByActivitySetId_First(long activitySetId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SocialActivity> list = findByActivitySetId(activitySetId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity in the ordered set where activitySetId = &#63;.
	 *
	 * @param activitySetId the activity set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByActivitySetId_Last(long activitySetId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByActivitySetId_Last(activitySetId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("activitySetId=");
		msg.append(activitySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the last social activity in the ordered set where activitySetId = &#63;.
	 *
	 * @param activitySetId the activity set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByActivitySetId_Last(long activitySetId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByActivitySetId(activitySetId);

		if (count == 0) {
			return null;
		}

		List<SocialActivity> list = findByActivitySetId(activitySetId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activities before and after the current social activity in the ordered set where activitySetId = &#63;.
	 *
	 * @param activityId the primary key of the current social activity
	 * @param activitySetId the activity set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity[] findByActivitySetId_PrevAndNext(long activityId,
		long activitySetId, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			SocialActivity[] array = new SocialActivityImpl[3];

			array[0] = getByActivitySetId_PrevAndNext(session, socialActivity,
					activitySetId, orderByComparator, true);

			array[1] = socialActivity;

			array[2] = getByActivitySetId_PrevAndNext(session, socialActivity,
					activitySetId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialActivity getByActivitySetId_PrevAndNext(Session session,
		SocialActivity socialActivity, long activitySetId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_ACTIVITYSETID_ACTIVITYSETID_2);

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
			query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(activitySetId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social activities where activitySetId = &#63; from the database.
	 *
	 * @param activitySetId the activity set ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByActivitySetId(long activitySetId)
		throws SystemException {
		for (SocialActivity socialActivity : findByActivitySetId(
				activitySetId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(socialActivity);
		}
	}

	/**
	 * Returns the number of social activities where activitySetId = &#63;.
	 *
	 * @param activitySetId the activity set ID
	 * @return the number of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByActivitySetId(long activitySetId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVITYSETID;

		Object[] finderArgs = new Object[] { activitySetId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_ACTIVITYSETID_ACTIVITYSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(activitySetId);

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

	private static final String _FINDER_COLUMN_ACTIVITYSETID_ACTIVITYSETID_2 = "socialActivity.activitySetId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_MIRRORACTIVITYID = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByMirrorActivityId", new String[] { Long.class.getName() },
			SocialActivityModelImpl.MIRRORACTIVITYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MIRRORACTIVITYID = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByMirrorActivityId", new String[] { Long.class.getName() });

	/**
	 * Returns the social activity where mirrorActivityId = &#63; or throws a {@link com.liferay.portlet.social.NoSuchActivityException} if it could not be found.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @return the matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByMirrorActivityId(long mirrorActivityId)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByMirrorActivityId(mirrorActivityId);

		if (socialActivity == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("mirrorActivityId=");
			msg.append(mirrorActivityId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchActivityException(msg.toString());
		}

		return socialActivity;
	}

	/**
	 * Returns the social activity where mirrorActivityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @return the matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByMirrorActivityId(long mirrorActivityId)
		throws SystemException {
		return fetchByMirrorActivityId(mirrorActivityId, true);
	}

	/**
	 * Returns the social activity where mirrorActivityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByMirrorActivityId(long mirrorActivityId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { mirrorActivityId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MIRRORACTIVITYID,
					finderArgs, this);
		}

		if (result instanceof SocialActivity) {
			SocialActivity socialActivity = (SocialActivity)result;

			if ((mirrorActivityId != socialActivity.getMirrorActivityId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_MIRRORACTIVITYID_MIRRORACTIVITYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(mirrorActivityId);

				List<SocialActivity> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MIRRORACTIVITYID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SocialActivityPersistenceImpl.fetchByMirrorActivityId(long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					SocialActivity socialActivity = list.get(0);

					result = socialActivity;

					cacheResult(socialActivity);

					if ((socialActivity.getMirrorActivityId() != mirrorActivityId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MIRRORACTIVITYID,
							finderArgs, socialActivity);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MIRRORACTIVITYID,
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
			return (SocialActivity)result;
		}
	}

	/**
	 * Removes the social activity where mirrorActivityId = &#63; from the database.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @return the social activity that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity removeByMirrorActivityId(long mirrorActivityId)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByMirrorActivityId(mirrorActivityId);

		return remove(socialActivity);
	}

	/**
	 * Returns the number of social activities where mirrorActivityId = &#63;.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @return the number of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByMirrorActivityId(long mirrorActivityId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MIRRORACTIVITYID;

		Object[] finderArgs = new Object[] { mirrorActivityId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_MIRRORACTIVITYID_MIRRORACTIVITYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(mirrorActivityId);

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

	private static final String _FINDER_COLUMN_MIRRORACTIVITYID_MIRRORACTIVITYID_2 =
		"socialActivity.mirrorActivityId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEID =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByClassNameId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByClassNameId",
			new String[] { Long.class.getName() },
			SocialActivityModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SocialActivityModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEID = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByClassNameId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the social activities where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @return the matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByClassNameId(long classNameId)
		throws SystemException {
		return findByClassNameId(classNameId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activities where classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @return the range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByClassNameId(long classNameId, int start,
		int end) throws SystemException {
		return findByClassNameId(classNameId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activities where classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByClassNameId(long classNameId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID;
			finderArgs = new Object[] { classNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEID;
			finderArgs = new Object[] { classNameId, start, end, orderByComparator };
		}

		List<SocialActivity> list = (List<SocialActivity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivity socialActivity : list) {
				if ((classNameId != socialActivity.getClassNameId())) {
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

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				if (!pagination) {
					list = (List<SocialActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SocialActivity>(list);
				}
				else {
					list = (List<SocialActivity>)QueryUtil.list(q,
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
	 * Returns the first social activity in the ordered set where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByClassNameId_First(long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByClassNameId_First(classNameId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the first social activity in the ordered set where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByClassNameId_First(long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SocialActivity> list = findByClassNameId(classNameId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity in the ordered set where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByClassNameId_Last(long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByClassNameId_Last(classNameId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the last social activity in the ordered set where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByClassNameId_Last(long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByClassNameId(classNameId);

		if (count == 0) {
			return null;
		}

		List<SocialActivity> list = findByClassNameId(classNameId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activities before and after the current social activity in the ordered set where classNameId = &#63;.
	 *
	 * @param activityId the primary key of the current social activity
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity[] findByClassNameId_PrevAndNext(long activityId,
		long classNameId, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			SocialActivity[] array = new SocialActivityImpl[3];

			array[0] = getByClassNameId_PrevAndNext(session, socialActivity,
					classNameId, orderByComparator, true);

			array[1] = socialActivity;

			array[2] = getByClassNameId_PrevAndNext(session, socialActivity,
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

	protected SocialActivity getByClassNameId_PrevAndNext(Session session,
		SocialActivity socialActivity, long classNameId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2);

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
			query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social activities where classNameId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByClassNameId(long classNameId) throws SystemException {
		for (SocialActivity socialActivity : findByClassNameId(classNameId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(socialActivity);
		}
	}

	/**
	 * Returns the number of social activities where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @return the number of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByClassNameId(long classNameId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSNAMEID;

		Object[] finderArgs = new Object[] { classNameId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2 = "socialActivity.classNameId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RECEIVERUSERID =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByReceiverUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByReceiverUserId",
			new String[] { Long.class.getName() },
			SocialActivityModelImpl.RECEIVERUSERID_COLUMN_BITMASK |
			SocialActivityModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RECEIVERUSERID = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByReceiverUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the social activities where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @return the matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByReceiverUserId(long receiverUserId)
		throws SystemException {
		return findByReceiverUserId(receiverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activities where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @return the range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByReceiverUserId(long receiverUserId,
		int start, int end) throws SystemException {
		return findByReceiverUserId(receiverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activities where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByReceiverUserId(long receiverUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID;
			finderArgs = new Object[] { receiverUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RECEIVERUSERID;
			finderArgs = new Object[] {
					receiverUserId,
					
					start, end, orderByComparator
				};
		}

		List<SocialActivity> list = (List<SocialActivity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivity socialActivity : list) {
				if ((receiverUserId != socialActivity.getReceiverUserId())) {
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

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(receiverUserId);

				if (!pagination) {
					list = (List<SocialActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SocialActivity>(list);
				}
				else {
					list = (List<SocialActivity>)QueryUtil.list(q,
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
	 * Returns the first social activity in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByReceiverUserId_First(long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByReceiverUserId_First(receiverUserId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("receiverUserId=");
		msg.append(receiverUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the first social activity in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByReceiverUserId_First(long receiverUserId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SocialActivity> list = findByReceiverUserId(receiverUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByReceiverUserId_Last(long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByReceiverUserId_Last(receiverUserId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("receiverUserId=");
		msg.append(receiverUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the last social activity in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByReceiverUserId_Last(long receiverUserId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByReceiverUserId(receiverUserId);

		if (count == 0) {
			return null;
		}

		List<SocialActivity> list = findByReceiverUserId(receiverUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activities before and after the current social activity in the ordered set where receiverUserId = &#63;.
	 *
	 * @param activityId the primary key of the current social activity
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity[] findByReceiverUserId_PrevAndNext(long activityId,
		long receiverUserId, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			SocialActivity[] array = new SocialActivityImpl[3];

			array[0] = getByReceiverUserId_PrevAndNext(session, socialActivity,
					receiverUserId, orderByComparator, true);

			array[1] = socialActivity;

			array[2] = getByReceiverUserId_PrevAndNext(session, socialActivity,
					receiverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialActivity getByReceiverUserId_PrevAndNext(Session session,
		SocialActivity socialActivity, long receiverUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

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
			query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(receiverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social activities where receiverUserId = &#63; from the database.
	 *
	 * @param receiverUserId the receiver user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByReceiverUserId(long receiverUserId)
		throws SystemException {
		for (SocialActivity socialActivity : findByReceiverUserId(
				receiverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(socialActivity);
		}
	}

	/**
	 * Returns the number of social activities where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @return the number of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByReceiverUserId(long receiverUserId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RECEIVERUSERID;

		Object[] finderArgs = new Object[] { receiverUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(receiverUserId);

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

	private static final String _FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2 = "socialActivity.receiverUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			SocialActivityModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SocialActivityModelImpl.CLASSPK_COLUMN_BITMASK |
			SocialActivityModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the social activities where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByC_C(long classNameId, long classPK)
		throws SystemException {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activities where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @return the range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByC_C(long classNameId, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activities where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] { classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] {
					classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<SocialActivity> list = (List<SocialActivity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivity socialActivity : list) {
				if ((classNameId != socialActivity.getClassNameId()) ||
						(classPK != socialActivity.getClassPK())) {
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

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<SocialActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SocialActivity>(list);
				}
				else {
					list = (List<SocialActivity>)QueryUtil.list(q,
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
	 * Returns the first social activity in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByC_C_First(classNameId, classPK,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the first social activity in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<SocialActivity> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByC_C_Last(classNameId, classPK,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the last social activity in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<SocialActivity> list = findByC_C(classNameId, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activities before and after the current social activity in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param activityId the primary key of the current social activity
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity[] findByC_C_PrevAndNext(long activityId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			SocialActivity[] array = new SocialActivityImpl[3];

			array[0] = getByC_C_PrevAndNext(session, socialActivity,
					classNameId, classPK, orderByComparator, true);

			array[1] = socialActivity;

			array[2] = getByC_C_PrevAndNext(session, socialActivity,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialActivity getByC_C_PrevAndNext(Session session,
		SocialActivity socialActivity, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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
			query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social activities where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK)
		throws SystemException {
		for (SocialActivity socialActivity : findByC_C(classNameId, classPK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(socialActivity);
		}
	}

	/**
	 * Returns the number of social activities where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "socialActivity.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "socialActivity.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_M_C_C = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByM_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_C_C = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByM_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			SocialActivityModelImpl.MIRRORACTIVITYID_COLUMN_BITMASK |
			SocialActivityModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SocialActivityModelImpl.CLASSPK_COLUMN_BITMASK |
			SocialActivityModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_M_C_C = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByM_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the social activities where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByM_C_C(long mirrorActivityId,
		long classNameId, long classPK) throws SystemException {
		return findByM_C_C(mirrorActivityId, classNameId, classPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activities where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @return the range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByM_C_C(long mirrorActivityId,
		long classNameId, long classPK, int start, int end)
		throws SystemException {
		return findByM_C_C(mirrorActivityId, classNameId, classPK, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the social activities where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByM_C_C(long mirrorActivityId,
		long classNameId, long classPK, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_C_C;
			finderArgs = new Object[] { mirrorActivityId, classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_M_C_C;
			finderArgs = new Object[] {
					mirrorActivityId, classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<SocialActivity> list = (List<SocialActivity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivity socialActivity : list) {
				if ((mirrorActivityId != socialActivity.getMirrorActivityId()) ||
						(classNameId != socialActivity.getClassNameId()) ||
						(classPK != socialActivity.getClassPK())) {
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

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_M_C_C_MIRRORACTIVITYID_2);

			query.append(_FINDER_COLUMN_M_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_M_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(mirrorActivityId);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<SocialActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SocialActivity>(list);
				}
				else {
					list = (List<SocialActivity>)QueryUtil.list(q,
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
	 * Returns the first social activity in the ordered set where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByM_C_C_First(long mirrorActivityId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByM_C_C_First(mirrorActivityId,
				classNameId, classPK, orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mirrorActivityId=");
		msg.append(mirrorActivityId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the first social activity in the ordered set where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByM_C_C_First(long mirrorActivityId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws SystemException {
		List<SocialActivity> list = findByM_C_C(mirrorActivityId, classNameId,
				classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity in the ordered set where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByM_C_C_Last(long mirrorActivityId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByM_C_C_Last(mirrorActivityId,
				classNameId, classPK, orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mirrorActivityId=");
		msg.append(mirrorActivityId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the last social activity in the ordered set where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByM_C_C_Last(long mirrorActivityId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByM_C_C(mirrorActivityId, classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<SocialActivity> list = findByM_C_C(mirrorActivityId, classNameId,
				classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activities before and after the current social activity in the ordered set where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param activityId the primary key of the current social activity
	 * @param mirrorActivityId the mirror activity ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity[] findByM_C_C_PrevAndNext(long activityId,
		long mirrorActivityId, long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			SocialActivity[] array = new SocialActivityImpl[3];

			array[0] = getByM_C_C_PrevAndNext(session, socialActivity,
					mirrorActivityId, classNameId, classPK, orderByComparator,
					true);

			array[1] = socialActivity;

			array[2] = getByM_C_C_PrevAndNext(session, socialActivity,
					mirrorActivityId, classNameId, classPK, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialActivity getByM_C_C_PrevAndNext(Session session,
		SocialActivity socialActivity, long mirrorActivityId, long classNameId,
		long classPK, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_M_C_C_MIRRORACTIVITYID_2);

		query.append(_FINDER_COLUMN_M_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_M_C_C_CLASSPK_2);

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
			query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(mirrorActivityId);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social activities where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByM_C_C(long mirrorActivityId, long classNameId,
		long classPK) throws SystemException {
		for (SocialActivity socialActivity : findByM_C_C(mirrorActivityId,
				classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(socialActivity);
		}
	}

	/**
	 * Returns the number of social activities where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param mirrorActivityId the mirror activity ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByM_C_C(long mirrorActivityId, long classNameId,
		long classPK) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_M_C_C;

		Object[] finderArgs = new Object[] {
				mirrorActivityId, classNameId, classPK
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_M_C_C_MIRRORACTIVITYID_2);

			query.append(_FINDER_COLUMN_M_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_M_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(mirrorActivityId);

				qPos.add(classNameId);

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

	private static final String _FINDER_COLUMN_M_C_C_MIRRORACTIVITYID_2 = "socialActivity.mirrorActivityId = ? AND ";
	private static final String _FINDER_COLUMN_M_C_C_CLASSNAMEID_2 = "socialActivity.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_M_C_C_CLASSPK_2 = "socialActivity.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			SocialActivityModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SocialActivityModelImpl.CLASSPK_COLUMN_BITMASK |
			SocialActivityModelImpl.TYPE_COLUMN_BITMASK |
			SocialActivityModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_T = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the social activities where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByC_C_T(long classNameId, long classPK,
		int type) throws SystemException {
		return findByC_C_T(classNameId, classPK, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activities where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @return the range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end) throws SystemException {
		return findByC_C_T(classNameId, classPK, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activities where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T;
			finderArgs = new Object[] { classNameId, classPK, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T;
			finderArgs = new Object[] {
					classNameId, classPK, type,
					
					start, end, orderByComparator
				};
		}

		List<SocialActivity> list = (List<SocialActivity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivity socialActivity : list) {
				if ((classNameId != socialActivity.getClassNameId()) ||
						(classPK != socialActivity.getClassPK()) ||
						(type != socialActivity.getType())) {
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

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				if (!pagination) {
					list = (List<SocialActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SocialActivity>(list);
				}
				else {
					list = (List<SocialActivity>)QueryUtil.list(q,
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
	 * Returns the first social activity in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByC_C_T_First(classNameId,
				classPK, type, orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the first social activity in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		List<SocialActivity> list = findByC_C_T(classNameId, classPK, type, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByC_C_T_Last(classNameId, classPK,
				type, orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the last social activity in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_T(classNameId, classPK, type);

		if (count == 0) {
			return null;
		}

		List<SocialActivity> list = findByC_C_T(classNameId, classPK, type,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activities before and after the current social activity in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param activityId the primary key of the current social activity
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity[] findByC_C_T_PrevAndNext(long activityId,
		long classNameId, long classPK, int type,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			SocialActivity[] array = new SocialActivityImpl[3];

			array[0] = getByC_C_T_PrevAndNext(session, socialActivity,
					classNameId, classPK, type, orderByComparator, true);

			array[1] = socialActivity;

			array[2] = getByC_C_T_PrevAndNext(session, socialActivity,
					classNameId, classPK, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialActivity getByC_C_T_PrevAndNext(Session session,
		SocialActivity socialActivity, long classNameId, long classPK,
		int type, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

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
			query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social activities where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_C_T(long classNameId, long classPK, int type)
		throws SystemException {
		for (SocialActivity socialActivity : findByC_C_T(classNameId, classPK,
				type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(socialActivity);
		}
	}

	/**
	 * Returns the number of social activities where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the number of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_C_T(long classNameId, long classPK, int type)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_T;

		Object[] finderArgs = new Object[] { classNameId, classPK, type };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

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

	private static final String _FINDER_COLUMN_C_C_T_CLASSNAMEID_2 = "socialActivity.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_CLASSPK_2 = "socialActivity.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TYPE_2 = "socialActivity.type = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_C_C_T_R =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_U_C_C_T_R",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_C_C_T_R =
		new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U_C_C_T_R",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName()
			},
			SocialActivityModelImpl.GROUPID_COLUMN_BITMASK |
			SocialActivityModelImpl.USERID_COLUMN_BITMASK |
			SocialActivityModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SocialActivityModelImpl.CLASSPK_COLUMN_BITMASK |
			SocialActivityModelImpl.TYPE_COLUMN_BITMASK |
			SocialActivityModelImpl.RECEIVERUSERID_COLUMN_BITMASK |
			SocialActivityModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U_C_C_T_R = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_C_C_T_R",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the social activities where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByG_U_C_C_T_R(long groupId, long userId,
		long classNameId, long classPK, int type, long receiverUserId)
		throws SystemException {
		return findByG_U_C_C_T_R(groupId, userId, classNameId, classPK, type,
			receiverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activities where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @return the range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByG_U_C_C_T_R(long groupId, long userId,
		long classNameId, long classPK, int type, long receiverUserId,
		int start, int end) throws SystemException {
		return findByG_U_C_C_T_R(groupId, userId, classNameId, classPK, type,
			receiverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activities where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findByG_U_C_C_T_R(long groupId, long userId,
		long classNameId, long classPK, int type, long receiverUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_C_C_T_R;
			finderArgs = new Object[] {
					groupId, userId, classNameId, classPK, type, receiverUserId
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_C_C_T_R;
			finderArgs = new Object[] {
					groupId, userId, classNameId, classPK, type, receiverUserId,
					
					start, end, orderByComparator
				};
		}

		List<SocialActivity> list = (List<SocialActivity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivity socialActivity : list) {
				if ((groupId != socialActivity.getGroupId()) ||
						(userId != socialActivity.getUserId()) ||
						(classNameId != socialActivity.getClassNameId()) ||
						(classPK != socialActivity.getClassPK()) ||
						(type != socialActivity.getType()) ||
						(receiverUserId != socialActivity.getReceiverUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(8 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(8);
			}

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_USERID_2);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_CLASSPK_2);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_TYPE_2);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_RECEIVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				qPos.add(receiverUserId);

				if (!pagination) {
					list = (List<SocialActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SocialActivity>(list);
				}
				else {
					list = (List<SocialActivity>)QueryUtil.list(q,
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
	 * Returns the first social activity in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByG_U_C_C_T_R_First(long groupId, long userId,
		long classNameId, long classPK, int type, long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByG_U_C_C_T_R_First(groupId,
				userId, classNameId, classPK, type, receiverUserId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(", receiverUserId=");
		msg.append(receiverUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the first social activity in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByG_U_C_C_T_R_First(long groupId, long userId,
		long classNameId, long classPK, int type, long receiverUserId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SocialActivity> list = findByG_U_C_C_T_R(groupId, userId,
				classNameId, classPK, type, receiverUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByG_U_C_C_T_R_Last(long groupId, long userId,
		long classNameId, long classPK, int type, long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByG_U_C_C_T_R_Last(groupId,
				userId, classNameId, classPK, type, receiverUserId,
				orderByComparator);

		if (socialActivity != null) {
			return socialActivity;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(", receiverUserId=");
		msg.append(receiverUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivityException(msg.toString());
	}

	/**
	 * Returns the last social activity in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByG_U_C_C_T_R_Last(long groupId, long userId,
		long classNameId, long classPK, int type, long receiverUserId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_U_C_C_T_R(groupId, userId, classNameId, classPK,
				type, receiverUserId);

		if (count == 0) {
			return null;
		}

		List<SocialActivity> list = findByG_U_C_C_T_R(groupId, userId,
				classNameId, classPK, type, receiverUserId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activities before and after the current social activity in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	 *
	 * @param activityId the primary key of the current social activity
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity[] findByG_U_C_C_T_R_PrevAndNext(long activityId,
		long groupId, long userId, long classNameId, long classPK, int type,
		long receiverUserId, OrderByComparator orderByComparator)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByPrimaryKey(activityId);

		Session session = null;

		try {
			session = openSession();

			SocialActivity[] array = new SocialActivityImpl[3];

			array[0] = getByG_U_C_C_T_R_PrevAndNext(session, socialActivity,
					groupId, userId, classNameId, classPK, type,
					receiverUserId, orderByComparator, true);

			array[1] = socialActivity;

			array[2] = getByG_U_C_C_T_R_PrevAndNext(session, socialActivity,
					groupId, userId, classNameId, classPK, type,
					receiverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialActivity getByG_U_C_C_T_R_PrevAndNext(Session session,
		SocialActivity socialActivity, long groupId, long userId,
		long classNameId, long classPK, int type, long receiverUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_G_U_C_C_T_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_C_C_T_R_USERID_2);

		query.append(_FINDER_COLUMN_G_U_C_C_T_R_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_G_U_C_C_T_R_CLASSPK_2);

		query.append(_FINDER_COLUMN_G_U_C_C_T_R_TYPE_2);

		query.append(_FINDER_COLUMN_G_U_C_C_T_R_RECEIVERUSERID_2);

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
			query.append(SocialActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(type);

		qPos.add(receiverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social activities where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_U_C_C_T_R(long groupId, long userId,
		long classNameId, long classPK, int type, long receiverUserId)
		throws SystemException {
		for (SocialActivity socialActivity : findByG_U_C_C_T_R(groupId, userId,
				classNameId, classPK, type, receiverUserId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(socialActivity);
		}
	}

	/**
	 * Returns the number of social activities where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the number of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_U_C_C_T_R(long groupId, long userId, long classNameId,
		long classPK, int type, long receiverUserId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U_C_C_T_R;

		Object[] finderArgs = new Object[] {
				groupId, userId, classNameId, classPK, type, receiverUserId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_USERID_2);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_CLASSPK_2);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_TYPE_2);

			query.append(_FINDER_COLUMN_G_U_C_C_T_R_RECEIVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				qPos.add(receiverUserId);

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

	private static final String _FINDER_COLUMN_G_U_C_C_T_R_GROUPID_2 = "socialActivity.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_C_C_T_R_USERID_2 = "socialActivity.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_C_C_T_R_CLASSNAMEID_2 = "socialActivity.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_C_C_T_R_CLASSPK_2 = "socialActivity.classPK = ? AND ";
	private static final String _FINDER_COLUMN_G_U_C_C_T_R_TYPE_2 = "socialActivity.type = ? AND ";
	private static final String _FINDER_COLUMN_G_U_C_C_T_R_RECEIVERUSERID_2 = "socialActivity.receiverUserId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED,
			SocialActivityImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_U_CD_C_C_T_R",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Long.class.getName()
			},
			SocialActivityModelImpl.GROUPID_COLUMN_BITMASK |
			SocialActivityModelImpl.USERID_COLUMN_BITMASK |
			SocialActivityModelImpl.CREATEDATE_COLUMN_BITMASK |
			SocialActivityModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SocialActivityModelImpl.CLASSPK_COLUMN_BITMASK |
			SocialActivityModelImpl.TYPE_COLUMN_BITMASK |
			SocialActivityModelImpl.RECEIVERUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U_CD_C_C_T_R = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_CD_C_C_T_R",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the social activity where groupId = &#63; and userId = &#63; and createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; or throws a {@link com.liferay.portlet.social.NoSuchActivityException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the matching social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByG_U_CD_C_C_T_R(long groupId, long userId,
		long createDate, long classNameId, long classPK, int type,
		long receiverUserId) throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByG_U_CD_C_C_T_R(groupId, userId,
				createDate, classNameId, classPK, type, receiverUserId);

		if (socialActivity == null) {
			StringBundler msg = new StringBundler(16);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(", createDate=");
			msg.append(createDate);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", type=");
			msg.append(type);

			msg.append(", receiverUserId=");
			msg.append(receiverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchActivityException(msg.toString());
		}

		return socialActivity;
	}

	/**
	 * Returns the social activity where groupId = &#63; and userId = &#63; and createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByG_U_CD_C_C_T_R(long groupId, long userId,
		long createDate, long classNameId, long classPK, int type,
		long receiverUserId) throws SystemException {
		return fetchByG_U_CD_C_C_T_R(groupId, userId, createDate, classNameId,
			classPK, type, receiverUserId, true);
	}

	/**
	 * Returns the social activity where groupId = &#63; and userId = &#63; and createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching social activity, or <code>null</code> if a matching social activity could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByG_U_CD_C_C_T_R(long groupId, long userId,
		long createDate, long classNameId, long classPK, int type,
		long receiverUserId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, userId, createDate, classNameId, classPK, type,
				receiverUserId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R,
					finderArgs, this);
		}

		if (result instanceof SocialActivity) {
			SocialActivity socialActivity = (SocialActivity)result;

			if ((groupId != socialActivity.getGroupId()) ||
					(userId != socialActivity.getUserId()) ||
					(createDate != socialActivity.getCreateDate()) ||
					(classNameId != socialActivity.getClassNameId()) ||
					(classPK != socialActivity.getClassPK()) ||
					(type != socialActivity.getType()) ||
					(receiverUserId != socialActivity.getReceiverUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(9);

			query.append(_SQL_SELECT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_USERID_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_CREATEDATE_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_CLASSPK_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_TYPE_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_RECEIVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(createDate);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				qPos.add(receiverUserId);

				List<SocialActivity> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R,
						finderArgs, list);
				}
				else {
					SocialActivity socialActivity = list.get(0);

					result = socialActivity;

					cacheResult(socialActivity);

					if ((socialActivity.getGroupId() != groupId) ||
							(socialActivity.getUserId() != userId) ||
							(socialActivity.getCreateDate() != createDate) ||
							(socialActivity.getClassNameId() != classNameId) ||
							(socialActivity.getClassPK() != classPK) ||
							(socialActivity.getType() != type) ||
							(socialActivity.getReceiverUserId() != receiverUserId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R,
							finderArgs, socialActivity);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R,
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
			return (SocialActivity)result;
		}
	}

	/**
	 * Removes the social activity where groupId = &#63; and userId = &#63; and createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the social activity that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity removeByG_U_CD_C_C_T_R(long groupId, long userId,
		long createDate, long classNameId, long classPK, int type,
		long receiverUserId) throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = findByG_U_CD_C_C_T_R(groupId, userId,
				createDate, classNameId, classPK, type, receiverUserId);

		return remove(socialActivity);
	}

	/**
	 * Returns the number of social activities where groupId = &#63; and userId = &#63; and createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the number of matching social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_U_CD_C_C_T_R(long groupId, long userId,
		long createDate, long classNameId, long classPK, int type,
		long receiverUserId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U_CD_C_C_T_R;

		Object[] finderArgs = new Object[] {
				groupId, userId, createDate, classNameId, classPK, type,
				receiverUserId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_COUNT_SOCIALACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_USERID_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_CREATEDATE_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_CLASSPK_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_TYPE_2);

			query.append(_FINDER_COLUMN_G_U_CD_C_C_T_R_RECEIVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(createDate);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				qPos.add(receiverUserId);

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

	private static final String _FINDER_COLUMN_G_U_CD_C_C_T_R_GROUPID_2 = "socialActivity.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_CD_C_C_T_R_USERID_2 = "socialActivity.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_CD_C_C_T_R_CREATEDATE_2 = "socialActivity.createDate = ? AND ";
	private static final String _FINDER_COLUMN_G_U_CD_C_C_T_R_CLASSNAMEID_2 = "socialActivity.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_CD_C_C_T_R_CLASSPK_2 = "socialActivity.classPK = ? AND ";
	private static final String _FINDER_COLUMN_G_U_CD_C_C_T_R_TYPE_2 = "socialActivity.type = ? AND ";
	private static final String _FINDER_COLUMN_G_U_CD_C_C_T_R_RECEIVERUSERID_2 = "socialActivity.receiverUserId = ?";

	public SocialActivityPersistenceImpl() {
		setModelClass(SocialActivity.class);
	}

	/**
	 * Caches the social activity in the entity cache if it is enabled.
	 *
	 * @param socialActivity the social activity
	 */
	@Override
	public void cacheResult(SocialActivity socialActivity) {
		EntityCacheUtil.putResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityImpl.class, socialActivity.getPrimaryKey(),
			socialActivity);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MIRRORACTIVITYID,
			new Object[] { socialActivity.getMirrorActivityId() },
			socialActivity);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R,
			new Object[] {
				socialActivity.getGroupId(), socialActivity.getUserId(),
				socialActivity.getCreateDate(), socialActivity.getClassNameId(),
				socialActivity.getClassPK(), socialActivity.getType(),
				socialActivity.getReceiverUserId()
			}, socialActivity);

		socialActivity.resetOriginalValues();
	}

	/**
	 * Caches the social activities in the entity cache if it is enabled.
	 *
	 * @param socialActivities the social activities
	 */
	@Override
	public void cacheResult(List<SocialActivity> socialActivities) {
		for (SocialActivity socialActivity : socialActivities) {
			if (EntityCacheUtil.getResult(
						SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
						SocialActivityImpl.class, socialActivity.getPrimaryKey()) == null) {
				cacheResult(socialActivity);
			}
			else {
				socialActivity.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all social activities.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SocialActivityImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SocialActivityImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the social activity.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SocialActivity socialActivity) {
		EntityCacheUtil.removeResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityImpl.class, socialActivity.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(socialActivity);
	}

	@Override
	public void clearCache(List<SocialActivity> socialActivities) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SocialActivity socialActivity : socialActivities) {
			EntityCacheUtil.removeResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
				SocialActivityImpl.class, socialActivity.getPrimaryKey());

			clearUniqueFindersCache(socialActivity);
		}
	}

	protected void cacheUniqueFindersCache(SocialActivity socialActivity) {
		if (socialActivity.isNew()) {
			Object[] args = new Object[] { socialActivity.getMirrorActivityId() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MIRRORACTIVITYID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MIRRORACTIVITYID,
				args, socialActivity);

			args = new Object[] {
					socialActivity.getGroupId(), socialActivity.getUserId(),
					socialActivity.getCreateDate(),
					socialActivity.getClassNameId(), socialActivity.getClassPK(),
					socialActivity.getType(), socialActivity.getReceiverUserId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_U_CD_C_C_T_R,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R,
				args, socialActivity);
		}
		else {
			SocialActivityModelImpl socialActivityModelImpl = (SocialActivityModelImpl)socialActivity;

			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_MIRRORACTIVITYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivity.getMirrorActivityId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MIRRORACTIVITYID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MIRRORACTIVITYID,
					args, socialActivity);
			}

			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivity.getGroupId(), socialActivity.getUserId(),
						socialActivity.getCreateDate(),
						socialActivity.getClassNameId(),
						socialActivity.getClassPK(), socialActivity.getType(),
						socialActivity.getReceiverUserId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_U_CD_C_C_T_R,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R,
					args, socialActivity);
			}
		}
	}

	protected void clearUniqueFindersCache(SocialActivity socialActivity) {
		SocialActivityModelImpl socialActivityModelImpl = (SocialActivityModelImpl)socialActivity;

		Object[] args = new Object[] { socialActivity.getMirrorActivityId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MIRRORACTIVITYID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MIRRORACTIVITYID, args);

		if ((socialActivityModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_MIRRORACTIVITYID.getColumnBitmask()) != 0) {
			args = new Object[] {
					socialActivityModelImpl.getOriginalMirrorActivityId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MIRRORACTIVITYID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MIRRORACTIVITYID,
				args);
		}

		args = new Object[] {
				socialActivity.getGroupId(), socialActivity.getUserId(),
				socialActivity.getCreateDate(), socialActivity.getClassNameId(),
				socialActivity.getClassPK(), socialActivity.getType(),
				socialActivity.getReceiverUserId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_CD_C_C_T_R, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R, args);

		if ((socialActivityModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R.getColumnBitmask()) != 0) {
			args = new Object[] {
					socialActivityModelImpl.getOriginalGroupId(),
					socialActivityModelImpl.getOriginalUserId(),
					socialActivityModelImpl.getOriginalCreateDate(),
					socialActivityModelImpl.getOriginalClassNameId(),
					socialActivityModelImpl.getOriginalClassPK(),
					socialActivityModelImpl.getOriginalType(),
					socialActivityModelImpl.getOriginalReceiverUserId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_CD_C_C_T_R,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_U_CD_C_C_T_R,
				args);
		}
	}

	/**
	 * Creates a new social activity with the primary key. Does not add the social activity to the database.
	 *
	 * @param activityId the primary key for the new social activity
	 * @return the new social activity
	 */
	@Override
	public SocialActivity create(long activityId) {
		SocialActivity socialActivity = new SocialActivityImpl();

		socialActivity.setNew(true);
		socialActivity.setPrimaryKey(activityId);

		return socialActivity;
	}

	/**
	 * Removes the social activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityId the primary key of the social activity
	 * @return the social activity that was removed
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity remove(long activityId)
		throws NoSuchActivityException, SystemException {
		return remove((Serializable)activityId);
	}

	/**
	 * Removes the social activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the social activity
	 * @return the social activity that was removed
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity remove(Serializable primaryKey)
		throws NoSuchActivityException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SocialActivity socialActivity = (SocialActivity)session.get(SocialActivityImpl.class,
					primaryKey);

			if (socialActivity == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActivityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(socialActivity);
		}
		catch (NoSuchActivityException nsee) {
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
	protected SocialActivity removeImpl(SocialActivity socialActivity)
		throws SystemException {
		socialActivity = toUnwrappedModel(socialActivity);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(socialActivity)) {
				socialActivity = (SocialActivity)session.get(SocialActivityImpl.class,
						socialActivity.getPrimaryKeyObj());
			}

			if (socialActivity != null) {
				session.delete(socialActivity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (socialActivity != null) {
			clearCache(socialActivity);
		}

		return socialActivity;
	}

	@Override
	public SocialActivity updateImpl(
		com.liferay.portlet.social.model.SocialActivity socialActivity)
		throws SystemException {
		socialActivity = toUnwrappedModel(socialActivity);

		boolean isNew = socialActivity.isNew();

		SocialActivityModelImpl socialActivityModelImpl = (SocialActivityModelImpl)socialActivity;

		Session session = null;

		try {
			session = openSession();

			if (socialActivity.isNew()) {
				session.save(socialActivity);

				socialActivity.setNew(false);
			}
			else {
				session.merge(socialActivity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SocialActivityModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivityModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { socialActivityModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivityModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { socialActivityModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivityModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { socialActivityModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivityModelImpl.getOriginalActivitySetId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYSETID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSETID,
					args);

				args = new Object[] { socialActivityModelImpl.getActivitySetId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYSETID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSETID,
					args);
			}

			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivityModelImpl.getOriginalClassNameId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID,
					args);

				args = new Object[] { socialActivityModelImpl.getClassNameId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID,
					args);
			}

			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivityModelImpl.getOriginalReceiverUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RECEIVERUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID,
					args);

				args = new Object[] { socialActivityModelImpl.getReceiverUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RECEIVERUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID,
					args);
			}

			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivityModelImpl.getOriginalClassNameId(),
						socialActivityModelImpl.getOriginalClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						socialActivityModelImpl.getClassNameId(),
						socialActivityModelImpl.getClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivityModelImpl.getOriginalMirrorActivityId(),
						socialActivityModelImpl.getOriginalClassNameId(),
						socialActivityModelImpl.getOriginalClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_M_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_C_C,
					args);

				args = new Object[] {
						socialActivityModelImpl.getMirrorActivityId(),
						socialActivityModelImpl.getClassNameId(),
						socialActivityModelImpl.getClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_M_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_C_C,
					args);
			}

			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivityModelImpl.getOriginalClassNameId(),
						socialActivityModelImpl.getOriginalClassPK(),
						socialActivityModelImpl.getOriginalType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);

				args = new Object[] {
						socialActivityModelImpl.getClassNameId(),
						socialActivityModelImpl.getClassPK(),
						socialActivityModelImpl.getType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);
			}

			if ((socialActivityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_C_C_T_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						socialActivityModelImpl.getOriginalGroupId(),
						socialActivityModelImpl.getOriginalUserId(),
						socialActivityModelImpl.getOriginalClassNameId(),
						socialActivityModelImpl.getOriginalClassPK(),
						socialActivityModelImpl.getOriginalType(),
						socialActivityModelImpl.getOriginalReceiverUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_C_C_T_R,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_C_C_T_R,
					args);

				args = new Object[] {
						socialActivityModelImpl.getGroupId(),
						socialActivityModelImpl.getUserId(),
						socialActivityModelImpl.getClassNameId(),
						socialActivityModelImpl.getClassPK(),
						socialActivityModelImpl.getType(),
						socialActivityModelImpl.getReceiverUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_C_C_T_R,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_C_C_T_R,
					args);
			}
		}

		EntityCacheUtil.putResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivityImpl.class, socialActivity.getPrimaryKey(),
			socialActivity);

		clearUniqueFindersCache(socialActivity);
		cacheUniqueFindersCache(socialActivity);

		return socialActivity;
	}

	protected SocialActivity toUnwrappedModel(SocialActivity socialActivity) {
		if (socialActivity instanceof SocialActivityImpl) {
			return socialActivity;
		}

		SocialActivityImpl socialActivityImpl = new SocialActivityImpl();

		socialActivityImpl.setNew(socialActivity.isNew());
		socialActivityImpl.setPrimaryKey(socialActivity.getPrimaryKey());

		socialActivityImpl.setActivityId(socialActivity.getActivityId());
		socialActivityImpl.setGroupId(socialActivity.getGroupId());
		socialActivityImpl.setCompanyId(socialActivity.getCompanyId());
		socialActivityImpl.setUserId(socialActivity.getUserId());
		socialActivityImpl.setCreateDate(socialActivity.getCreateDate());
		socialActivityImpl.setActivitySetId(socialActivity.getActivitySetId());
		socialActivityImpl.setMirrorActivityId(socialActivity.getMirrorActivityId());
		socialActivityImpl.setClassNameId(socialActivity.getClassNameId());
		socialActivityImpl.setClassPK(socialActivity.getClassPK());
		socialActivityImpl.setParentClassNameId(socialActivity.getParentClassNameId());
		socialActivityImpl.setParentClassPK(socialActivity.getParentClassPK());
		socialActivityImpl.setType(socialActivity.getType());
		socialActivityImpl.setExtraData(socialActivity.getExtraData());
		socialActivityImpl.setReceiverUserId(socialActivity.getReceiverUserId());

		return socialActivityImpl;
	}

	/**
	 * Returns the social activity with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the social activity
	 * @return the social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActivityException, SystemException {
		SocialActivity socialActivity = fetchByPrimaryKey(primaryKey);

		if (socialActivity == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActivityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return socialActivity;
	}

	/**
	 * Returns the social activity with the primary key or throws a {@link com.liferay.portlet.social.NoSuchActivityException} if it could not be found.
	 *
	 * @param activityId the primary key of the social activity
	 * @return the social activity
	 * @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity findByPrimaryKey(long activityId)
		throws NoSuchActivityException, SystemException {
		return findByPrimaryKey((Serializable)activityId);
	}

	/**
	 * Returns the social activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the social activity
	 * @return the social activity, or <code>null</code> if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SocialActivity socialActivity = (SocialActivity)EntityCacheUtil.getResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
				SocialActivityImpl.class, primaryKey);

		if (socialActivity == _nullSocialActivity) {
			return null;
		}

		if (socialActivity == null) {
			Session session = null;

			try {
				session = openSession();

				socialActivity = (SocialActivity)session.get(SocialActivityImpl.class,
						primaryKey);

				if (socialActivity != null) {
					cacheResult(socialActivity);
				}
				else {
					EntityCacheUtil.putResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
						SocialActivityImpl.class, primaryKey,
						_nullSocialActivity);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
					SocialActivityImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return socialActivity;
	}

	/**
	 * Returns the social activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param activityId the primary key of the social activity
	 * @return the social activity, or <code>null</code> if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivity fetchByPrimaryKey(long activityId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)activityId);
	}

	/**
	 * Returns all the social activities.
	 *
	 * @return the social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @return the range of social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of social activities
	 * @param end the upper bound of the range of social activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of social activities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SocialActivity> findAll(int start, int end,
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

		List<SocialActivity> list = (List<SocialActivity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SOCIALACTIVITY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SOCIALACTIVITY;

				if (pagination) {
					sql = sql.concat(SocialActivityModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SocialActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SocialActivity>(list);
				}
				else {
					list = (List<SocialActivity>)QueryUtil.list(q,
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
	 * Removes all the social activities from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SocialActivity socialActivity : findAll()) {
			remove(socialActivity);
		}
	}

	/**
	 * Returns the number of social activities.
	 *
	 * @return the number of social activities
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

				Query q = session.createQuery(_SQL_COUNT_SOCIALACTIVITY);

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
	 * Initializes the social activity persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.social.model.SocialActivity")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SocialActivity>> listenersList = new ArrayList<ModelListener<SocialActivity>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SocialActivity>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SocialActivityImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SOCIALACTIVITY = "SELECT socialActivity FROM SocialActivity socialActivity";
	private static final String _SQL_SELECT_SOCIALACTIVITY_WHERE = "SELECT socialActivity FROM SocialActivity socialActivity WHERE ";
	private static final String _SQL_COUNT_SOCIALACTIVITY = "SELECT COUNT(socialActivity) FROM SocialActivity socialActivity";
	private static final String _SQL_COUNT_SOCIALACTIVITY_WHERE = "SELECT COUNT(socialActivity) FROM SocialActivity socialActivity WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "socialActivity.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SocialActivity exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SocialActivity exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(SocialActivityPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
	private static SocialActivity _nullSocialActivity = new SocialActivityImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SocialActivity> toCacheModel() {
				return _nullSocialActivityCacheModel;
			}
		};

	private static CacheModel<SocialActivity> _nullSocialActivityCacheModel = new CacheModel<SocialActivity>() {
			@Override
			public SocialActivity toEntityModel() {
				return _nullSocialActivity;
			}
		};
}