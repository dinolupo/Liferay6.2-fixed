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

package com.liferay.portlet.polls.service.persistence;

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
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.polls.NoSuchVoteException;
import com.liferay.portlet.polls.model.PollsVote;
import com.liferay.portlet.polls.model.impl.PollsVoteImpl;
import com.liferay.portlet.polls.model.impl.PollsVoteModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the polls vote service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PollsVotePersistence
 * @see PollsVoteUtil
 * @generated
 */
public class PollsVotePersistenceImpl extends BasePersistenceImpl<PollsVote>
	implements PollsVotePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PollsVoteUtil} to access the polls vote persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PollsVoteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PollsVoteModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the polls votes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls votes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @return the range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls votes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByUuid(String uuid, int start, int end,
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

		List<PollsVote> list = (List<PollsVote>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PollsVote pollsVote : list) {
				if (!Validator.equals(uuid, pollsVote.getUuid())) {
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

			query.append(_SQL_SELECT_POLLSVOTE_WHERE);

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
				query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
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
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PollsVote>(list);
				}
				else {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first polls vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByUuid_First(uuid, orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the first polls vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<PollsVote> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last polls vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByUuid_Last(uuid, orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the last polls vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PollsVote> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the polls votes before and after the current polls vote in the ordered set where uuid = &#63;.
	 *
	 * @param voteId the primary key of the current polls vote
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote[] findByUuid_PrevAndNext(long voteId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = findByPrimaryKey(voteId);

		Session session = null;

		try {
			session = openSession();

			PollsVote[] array = new PollsVoteImpl[3];

			array[0] = getByUuid_PrevAndNext(session, pollsVote, uuid,
					orderByComparator, true);

			array[1] = pollsVote;

			array[2] = getByUuid_PrevAndNext(session, pollsVote, uuid,
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

	protected PollsVote getByUuid_PrevAndNext(Session session,
		PollsVote pollsVote, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POLLSVOTE_WHERE);

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
			query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pollsVote);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsVote> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the polls votes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (PollsVote pollsVote : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(pollsVote);
		}
	}

	/**
	 * Returns the number of polls votes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching polls votes
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

			query.append(_SQL_COUNT_POLLSVOTE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "pollsVote.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "pollsVote.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(pollsVote.uuid IS NULL OR pollsVote.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PollsVoteModelImpl.UUID_COLUMN_BITMASK |
			PollsVoteModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the polls vote where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.portlet.polls.NoSuchVoteException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByUUID_G(String uuid, long groupId)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByUUID_G(uuid, groupId);

		if (pollsVote == null) {
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

			throw new NoSuchVoteException(msg.toString());
		}

		return pollsVote;
	}

	/**
	 * Returns the polls vote where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the polls vote where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PollsVote) {
			PollsVote pollsVote = (PollsVote)result;

			if (!Validator.equals(uuid, pollsVote.getUuid()) ||
					(groupId != pollsVote.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_POLLSVOTE_WHERE);

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

				List<PollsVote> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PollsVote pollsVote = list.get(0);

					result = pollsVote;

					cacheResult(pollsVote);

					if ((pollsVote.getUuid() == null) ||
							!pollsVote.getUuid().equals(uuid) ||
							(pollsVote.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, pollsVote);
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
			return (PollsVote)result;
		}
	}

	/**
	 * Removes the polls vote where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the polls vote that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote removeByUUID_G(String uuid, long groupId)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = findByUUID_G(uuid, groupId);

		return remove(pollsVote);
	}

	/**
	 * Returns the number of polls votes where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching polls votes
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

			query.append(_SQL_COUNT_POLLSVOTE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "pollsVote.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "pollsVote.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(pollsVote.uuid IS NULL OR pollsVote.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "pollsVote.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PollsVoteModelImpl.UUID_COLUMN_BITMASK |
			PollsVoteModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the polls votes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls votes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @return the range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByUuid_C(String uuid, long companyId, int start,
		int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls votes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<PollsVote> list = (List<PollsVote>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PollsVote pollsVote : list) {
				if (!Validator.equals(uuid, pollsVote.getUuid()) ||
						(companyId != pollsVote.getCompanyId())) {
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

			query.append(_SQL_SELECT_POLLSVOTE_WHERE);

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
				query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
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
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PollsVote>(list);
				}
				else {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first polls vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the first polls vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PollsVote> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last polls vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the last polls vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<PollsVote> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the polls votes before and after the current polls vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param voteId the primary key of the current polls vote
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote[] findByUuid_C_PrevAndNext(long voteId, String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = findByPrimaryKey(voteId);

		Session session = null;

		try {
			session = openSession();

			PollsVote[] array = new PollsVoteImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, pollsVote, uuid,
					companyId, orderByComparator, true);

			array[1] = pollsVote;

			array[2] = getByUuid_C_PrevAndNext(session, pollsVote, uuid,
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

	protected PollsVote getByUuid_C_PrevAndNext(Session session,
		PollsVote pollsVote, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POLLSVOTE_WHERE);

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
			query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pollsVote);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsVote> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the polls votes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (PollsVote pollsVote : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pollsVote);
		}
	}

	/**
	 * Returns the number of polls votes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching polls votes
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

			query.append(_SQL_COUNT_POLLSVOTE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "pollsVote.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "pollsVote.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(pollsVote.uuid IS NULL OR pollsVote.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "pollsVote.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_QUESTIONID =
		new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByQuestionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONID =
		new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByQuestionId",
			new String[] { Long.class.getName() },
			PollsVoteModelImpl.QUESTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_QUESTIONID = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByQuestionId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the polls votes where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @return the matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByQuestionId(long questionId)
		throws SystemException {
		return findByQuestionId(questionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls votes where questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @return the range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByQuestionId(long questionId, int start, int end)
		throws SystemException {
		return findByQuestionId(questionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls votes where questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByQuestionId(long questionId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONID;
			finderArgs = new Object[] { questionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_QUESTIONID;
			finderArgs = new Object[] { questionId, start, end, orderByComparator };
		}

		List<PollsVote> list = (List<PollsVote>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PollsVote pollsVote : list) {
				if ((questionId != pollsVote.getQuestionId())) {
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

			query.append(_SQL_SELECT_POLLSVOTE_WHERE);

			query.append(_FINDER_COLUMN_QUESTIONID_QUESTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(questionId);

				if (!pagination) {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PollsVote>(list);
				}
				else {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first polls vote in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByQuestionId_First(long questionId,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByQuestionId_First(questionId,
				orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("questionId=");
		msg.append(questionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the first polls vote in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByQuestionId_First(long questionId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PollsVote> list = findByQuestionId(questionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last polls vote in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByQuestionId_Last(long questionId,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByQuestionId_Last(questionId,
				orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("questionId=");
		msg.append(questionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the last polls vote in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByQuestionId_Last(long questionId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByQuestionId(questionId);

		if (count == 0) {
			return null;
		}

		List<PollsVote> list = findByQuestionId(questionId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the polls votes before and after the current polls vote in the ordered set where questionId = &#63;.
	 *
	 * @param voteId the primary key of the current polls vote
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote[] findByQuestionId_PrevAndNext(long voteId,
		long questionId, OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = findByPrimaryKey(voteId);

		Session session = null;

		try {
			session = openSession();

			PollsVote[] array = new PollsVoteImpl[3];

			array[0] = getByQuestionId_PrevAndNext(session, pollsVote,
					questionId, orderByComparator, true);

			array[1] = pollsVote;

			array[2] = getByQuestionId_PrevAndNext(session, pollsVote,
					questionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PollsVote getByQuestionId_PrevAndNext(Session session,
		PollsVote pollsVote, long questionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POLLSVOTE_WHERE);

		query.append(_FINDER_COLUMN_QUESTIONID_QUESTIONID_2);

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
			query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(questionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pollsVote);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsVote> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the polls votes where questionId = &#63; from the database.
	 *
	 * @param questionId the question ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByQuestionId(long questionId) throws SystemException {
		for (PollsVote pollsVote : findByQuestionId(questionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pollsVote);
		}
	}

	/**
	 * Returns the number of polls votes where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @return the number of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByQuestionId(long questionId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_QUESTIONID;

		Object[] finderArgs = new Object[] { questionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POLLSVOTE_WHERE);

			query.append(_FINDER_COLUMN_QUESTIONID_QUESTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(questionId);

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

	private static final String _FINDER_COLUMN_QUESTIONID_QUESTIONID_2 = "pollsVote.questionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CHOICEID = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByChoiceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHOICEID =
		new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByChoiceId",
			new String[] { Long.class.getName() },
			PollsVoteModelImpl.CHOICEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CHOICEID = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByChoiceId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the polls votes where choiceId = &#63;.
	 *
	 * @param choiceId the choice ID
	 * @return the matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByChoiceId(long choiceId)
		throws SystemException {
		return findByChoiceId(choiceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the polls votes where choiceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param choiceId the choice ID
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @return the range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByChoiceId(long choiceId, int start, int end)
		throws SystemException {
		return findByChoiceId(choiceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls votes where choiceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param choiceId the choice ID
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findByChoiceId(long choiceId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHOICEID;
			finderArgs = new Object[] { choiceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CHOICEID;
			finderArgs = new Object[] { choiceId, start, end, orderByComparator };
		}

		List<PollsVote> list = (List<PollsVote>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PollsVote pollsVote : list) {
				if ((choiceId != pollsVote.getChoiceId())) {
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

			query.append(_SQL_SELECT_POLLSVOTE_WHERE);

			query.append(_FINDER_COLUMN_CHOICEID_CHOICEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(choiceId);

				if (!pagination) {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PollsVote>(list);
				}
				else {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first polls vote in the ordered set where choiceId = &#63;.
	 *
	 * @param choiceId the choice ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByChoiceId_First(long choiceId,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByChoiceId_First(choiceId, orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("choiceId=");
		msg.append(choiceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the first polls vote in the ordered set where choiceId = &#63;.
	 *
	 * @param choiceId the choice ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByChoiceId_First(long choiceId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PollsVote> list = findByChoiceId(choiceId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last polls vote in the ordered set where choiceId = &#63;.
	 *
	 * @param choiceId the choice ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByChoiceId_Last(long choiceId,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByChoiceId_Last(choiceId, orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("choiceId=");
		msg.append(choiceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the last polls vote in the ordered set where choiceId = &#63;.
	 *
	 * @param choiceId the choice ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByChoiceId_Last(long choiceId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByChoiceId(choiceId);

		if (count == 0) {
			return null;
		}

		List<PollsVote> list = findByChoiceId(choiceId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the polls votes before and after the current polls vote in the ordered set where choiceId = &#63;.
	 *
	 * @param voteId the primary key of the current polls vote
	 * @param choiceId the choice ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote[] findByChoiceId_PrevAndNext(long voteId, long choiceId,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = findByPrimaryKey(voteId);

		Session session = null;

		try {
			session = openSession();

			PollsVote[] array = new PollsVoteImpl[3];

			array[0] = getByChoiceId_PrevAndNext(session, pollsVote, choiceId,
					orderByComparator, true);

			array[1] = pollsVote;

			array[2] = getByChoiceId_PrevAndNext(session, pollsVote, choiceId,
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

	protected PollsVote getByChoiceId_PrevAndNext(Session session,
		PollsVote pollsVote, long choiceId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POLLSVOTE_WHERE);

		query.append(_FINDER_COLUMN_CHOICEID_CHOICEID_2);

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
			query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(choiceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pollsVote);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsVote> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the polls votes where choiceId = &#63; from the database.
	 *
	 * @param choiceId the choice ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByChoiceId(long choiceId) throws SystemException {
		for (PollsVote pollsVote : findByChoiceId(choiceId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(pollsVote);
		}
	}

	/**
	 * Returns the number of polls votes where choiceId = &#63;.
	 *
	 * @param choiceId the choice ID
	 * @return the number of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByChoiceId(long choiceId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CHOICEID;

		Object[] finderArgs = new Object[] { choiceId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POLLSVOTE_WHERE);

			query.append(_FINDER_COLUMN_CHOICEID_CHOICEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(choiceId);

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

	private static final String _FINDER_COLUMN_CHOICEID_CHOICEID_2 = "pollsVote.choiceId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_Q_U = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByQ_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			PollsVoteModelImpl.QUESTIONID_COLUMN_BITMASK |
			PollsVoteModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_Q_U = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByQ_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the polls vote where questionId = &#63; and userId = &#63; or throws a {@link com.liferay.portlet.polls.NoSuchVoteException} if it could not be found.
	 *
	 * @param questionId the question ID
	 * @param userId the user ID
	 * @return the matching polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByQ_U(long questionId, long userId)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByQ_U(questionId, userId);

		if (pollsVote == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("questionId=");
			msg.append(questionId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchVoteException(msg.toString());
		}

		return pollsVote;
	}

	/**
	 * Returns the polls vote where questionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param questionId the question ID
	 * @param userId the user ID
	 * @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByQ_U(long questionId, long userId)
		throws SystemException {
		return fetchByQ_U(questionId, userId, true);
	}

	/**
	 * Returns the polls vote where questionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param questionId the question ID
	 * @param userId the user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByQ_U(long questionId, long userId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { questionId, userId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_Q_U,
					finderArgs, this);
		}

		if (result instanceof PollsVote) {
			PollsVote pollsVote = (PollsVote)result;

			if ((questionId != pollsVote.getQuestionId()) ||
					(userId != pollsVote.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_POLLSVOTE_WHERE);

			query.append(_FINDER_COLUMN_Q_U_QUESTIONID_2);

			query.append(_FINDER_COLUMN_Q_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(questionId);

				qPos.add(userId);

				List<PollsVote> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_Q_U,
						finderArgs, list);
				}
				else {
					PollsVote pollsVote = list.get(0);

					result = pollsVote;

					cacheResult(pollsVote);

					if ((pollsVote.getQuestionId() != questionId) ||
							(pollsVote.getUserId() != userId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_Q_U,
							finderArgs, pollsVote);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_Q_U,
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
			return (PollsVote)result;
		}
	}

	/**
	 * Removes the polls vote where questionId = &#63; and userId = &#63; from the database.
	 *
	 * @param questionId the question ID
	 * @param userId the user ID
	 * @return the polls vote that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote removeByQ_U(long questionId, long userId)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = findByQ_U(questionId, userId);

		return remove(pollsVote);
	}

	/**
	 * Returns the number of polls votes where questionId = &#63; and userId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param userId the user ID
	 * @return the number of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByQ_U(long questionId, long userId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_Q_U;

		Object[] finderArgs = new Object[] { questionId, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POLLSVOTE_WHERE);

			query.append(_FINDER_COLUMN_Q_U_QUESTIONID_2);

			query.append(_FINDER_COLUMN_Q_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(questionId);

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

	private static final String _FINDER_COLUMN_Q_U_QUESTIONID_2 = "pollsVote.questionId = ? AND ";
	private static final String _FINDER_COLUMN_Q_U_USERID_2 = "pollsVote.userId = ?";

	public PollsVotePersistenceImpl() {
		setModelClass(PollsVote.class);
	}

	/**
	 * Caches the polls vote in the entity cache if it is enabled.
	 *
	 * @param pollsVote the polls vote
	 */
	@Override
	public void cacheResult(PollsVote pollsVote) {
		EntityCacheUtil.putResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteImpl.class, pollsVote.getPrimaryKey(), pollsVote);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { pollsVote.getUuid(), pollsVote.getGroupId() },
			pollsVote);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_Q_U,
			new Object[] { pollsVote.getQuestionId(), pollsVote.getUserId() },
			pollsVote);

		pollsVote.resetOriginalValues();
	}

	/**
	 * Caches the polls votes in the entity cache if it is enabled.
	 *
	 * @param pollsVotes the polls votes
	 */
	@Override
	public void cacheResult(List<PollsVote> pollsVotes) {
		for (PollsVote pollsVote : pollsVotes) {
			if (EntityCacheUtil.getResult(
						PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
						PollsVoteImpl.class, pollsVote.getPrimaryKey()) == null) {
				cacheResult(pollsVote);
			}
			else {
				pollsVote.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all polls votes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PollsVoteImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PollsVoteImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the polls vote.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PollsVote pollsVote) {
		EntityCacheUtil.removeResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteImpl.class, pollsVote.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(pollsVote);
	}

	@Override
	public void clearCache(List<PollsVote> pollsVotes) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PollsVote pollsVote : pollsVotes) {
			EntityCacheUtil.removeResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
				PollsVoteImpl.class, pollsVote.getPrimaryKey());

			clearUniqueFindersCache(pollsVote);
		}
	}

	protected void cacheUniqueFindersCache(PollsVote pollsVote) {
		if (pollsVote.isNew()) {
			Object[] args = new Object[] {
					pollsVote.getUuid(), pollsVote.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				pollsVote);

			args = new Object[] { pollsVote.getQuestionId(), pollsVote.getUserId() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_Q_U, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_Q_U, args, pollsVote);
		}
		else {
			PollsVoteModelImpl pollsVoteModelImpl = (PollsVoteModelImpl)pollsVote;

			if ((pollsVoteModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsVote.getUuid(), pollsVote.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					pollsVote);
			}

			if ((pollsVoteModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_Q_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsVote.getQuestionId(), pollsVote.getUserId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_Q_U, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_Q_U, args,
					pollsVote);
			}
		}
	}

	protected void clearUniqueFindersCache(PollsVote pollsVote) {
		PollsVoteModelImpl pollsVoteModelImpl = (PollsVoteModelImpl)pollsVote;

		Object[] args = new Object[] { pollsVote.getUuid(), pollsVote.getGroupId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((pollsVoteModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					pollsVoteModelImpl.getOriginalUuid(),
					pollsVoteModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] { pollsVote.getQuestionId(), pollsVote.getUserId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_Q_U, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_Q_U, args);

		if ((pollsVoteModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_Q_U.getColumnBitmask()) != 0) {
			args = new Object[] {
					pollsVoteModelImpl.getOriginalQuestionId(),
					pollsVoteModelImpl.getOriginalUserId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_Q_U, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_Q_U, args);
		}
	}

	/**
	 * Creates a new polls vote with the primary key. Does not add the polls vote to the database.
	 *
	 * @param voteId the primary key for the new polls vote
	 * @return the new polls vote
	 */
	@Override
	public PollsVote create(long voteId) {
		PollsVote pollsVote = new PollsVoteImpl();

		pollsVote.setNew(true);
		pollsVote.setPrimaryKey(voteId);

		String uuid = PortalUUIDUtil.generate();

		pollsVote.setUuid(uuid);

		return pollsVote;
	}

	/**
	 * Removes the polls vote with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param voteId the primary key of the polls vote
	 * @return the polls vote that was removed
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote remove(long voteId)
		throws NoSuchVoteException, SystemException {
		return remove((Serializable)voteId);
	}

	/**
	 * Removes the polls vote with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the polls vote
	 * @return the polls vote that was removed
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote remove(Serializable primaryKey)
		throws NoSuchVoteException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PollsVote pollsVote = (PollsVote)session.get(PollsVoteImpl.class,
					primaryKey);

			if (pollsVote == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pollsVote);
		}
		catch (NoSuchVoteException nsee) {
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
	protected PollsVote removeImpl(PollsVote pollsVote)
		throws SystemException {
		pollsVote = toUnwrappedModel(pollsVote);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pollsVote)) {
				pollsVote = (PollsVote)session.get(PollsVoteImpl.class,
						pollsVote.getPrimaryKeyObj());
			}

			if (pollsVote != null) {
				session.delete(pollsVote);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (pollsVote != null) {
			clearCache(pollsVote);
		}

		return pollsVote;
	}

	@Override
	public PollsVote updateImpl(
		com.liferay.portlet.polls.model.PollsVote pollsVote)
		throws SystemException {
		pollsVote = toUnwrappedModel(pollsVote);

		boolean isNew = pollsVote.isNew();

		PollsVoteModelImpl pollsVoteModelImpl = (PollsVoteModelImpl)pollsVote;

		if (Validator.isNull(pollsVote.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			pollsVote.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (pollsVote.isNew()) {
				session.save(pollsVote);

				pollsVote.setNew(false);
			}
			else {
				session.merge(pollsVote);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PollsVoteModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((pollsVoteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsVoteModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { pollsVoteModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((pollsVoteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsVoteModelImpl.getOriginalUuid(),
						pollsVoteModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						pollsVoteModelImpl.getUuid(),
						pollsVoteModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((pollsVoteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsVoteModelImpl.getOriginalQuestionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_QUESTIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONID,
					args);

				args = new Object[] { pollsVoteModelImpl.getQuestionId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_QUESTIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONID,
					args);
			}

			if ((pollsVoteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHOICEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsVoteModelImpl.getOriginalChoiceId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHOICEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHOICEID,
					args);

				args = new Object[] { pollsVoteModelImpl.getChoiceId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CHOICEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CHOICEID,
					args);
			}
		}

		EntityCacheUtil.putResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteImpl.class, pollsVote.getPrimaryKey(), pollsVote);

		clearUniqueFindersCache(pollsVote);
		cacheUniqueFindersCache(pollsVote);

		return pollsVote;
	}

	protected PollsVote toUnwrappedModel(PollsVote pollsVote) {
		if (pollsVote instanceof PollsVoteImpl) {
			return pollsVote;
		}

		PollsVoteImpl pollsVoteImpl = new PollsVoteImpl();

		pollsVoteImpl.setNew(pollsVote.isNew());
		pollsVoteImpl.setPrimaryKey(pollsVote.getPrimaryKey());

		pollsVoteImpl.setUuid(pollsVote.getUuid());
		pollsVoteImpl.setVoteId(pollsVote.getVoteId());
		pollsVoteImpl.setGroupId(pollsVote.getGroupId());
		pollsVoteImpl.setCompanyId(pollsVote.getCompanyId());
		pollsVoteImpl.setUserId(pollsVote.getUserId());
		pollsVoteImpl.setUserName(pollsVote.getUserName());
		pollsVoteImpl.setCreateDate(pollsVote.getCreateDate());
		pollsVoteImpl.setModifiedDate(pollsVote.getModifiedDate());
		pollsVoteImpl.setQuestionId(pollsVote.getQuestionId());
		pollsVoteImpl.setChoiceId(pollsVote.getChoiceId());
		pollsVoteImpl.setVoteDate(pollsVote.getVoteDate());

		return pollsVoteImpl;
	}

	/**
	 * Returns the polls vote with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the polls vote
	 * @return the polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByPrimaryKey(primaryKey);

		if (pollsVote == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return pollsVote;
	}

	/**
	 * Returns the polls vote with the primary key or throws a {@link com.liferay.portlet.polls.NoSuchVoteException} if it could not be found.
	 *
	 * @param voteId the primary key of the polls vote
	 * @return the polls vote
	 * @throws com.liferay.portlet.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByPrimaryKey(long voteId)
		throws NoSuchVoteException, SystemException {
		return findByPrimaryKey((Serializable)voteId);
	}

	/**
	 * Returns the polls vote with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the polls vote
	 * @return the polls vote, or <code>null</code> if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		PollsVote pollsVote = (PollsVote)EntityCacheUtil.getResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
				PollsVoteImpl.class, primaryKey);

		if (pollsVote == _nullPollsVote) {
			return null;
		}

		if (pollsVote == null) {
			Session session = null;

			try {
				session = openSession();

				pollsVote = (PollsVote)session.get(PollsVoteImpl.class,
						primaryKey);

				if (pollsVote != null) {
					cacheResult(pollsVote);
				}
				else {
					EntityCacheUtil.putResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
						PollsVoteImpl.class, primaryKey, _nullPollsVote);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
					PollsVoteImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return pollsVote;
	}

	/**
	 * Returns the polls vote with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param voteId the primary key of the polls vote
	 * @return the polls vote, or <code>null</code> if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByPrimaryKey(long voteId) throws SystemException {
		return fetchByPrimaryKey((Serializable)voteId);
	}

	/**
	 * Returns all the polls votes.
	 *
	 * @return the polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls votes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @return the range of polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls votes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of polls votes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsVote> findAll(int start, int end,
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

		List<PollsVote> list = (List<PollsVote>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_POLLSVOTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_POLLSVOTE;

				if (pagination) {
					sql = sql.concat(PollsVoteModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PollsVote>(list);
				}
				else {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the polls votes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (PollsVote pollsVote : findAll()) {
			remove(pollsVote);
		}
	}

	/**
	 * Returns the number of polls votes.
	 *
	 * @return the number of polls votes
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

				Query q = session.createQuery(_SQL_COUNT_POLLSVOTE);

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
	 * Initializes the polls vote persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.polls.model.PollsVote")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PollsVote>> listenersList = new ArrayList<ModelListener<PollsVote>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PollsVote>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(PollsVoteImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_POLLSVOTE = "SELECT pollsVote FROM PollsVote pollsVote";
	private static final String _SQL_SELECT_POLLSVOTE_WHERE = "SELECT pollsVote FROM PollsVote pollsVote WHERE ";
	private static final String _SQL_COUNT_POLLSVOTE = "SELECT COUNT(pollsVote) FROM PollsVote pollsVote";
	private static final String _SQL_COUNT_POLLSVOTE_WHERE = "SELECT COUNT(pollsVote) FROM PollsVote pollsVote WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pollsVote.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PollsVote exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PollsVote exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(PollsVotePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static PollsVote _nullPollsVote = new PollsVoteImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PollsVote> toCacheModel() {
				return _nullPollsVoteCacheModel;
			}
		};

	private static CacheModel<PollsVote> _nullPollsVoteCacheModel = new CacheModel<PollsVote>() {
			@Override
			public PollsVote toEntityModel() {
				return _nullPollsVote;
			}
		};
}