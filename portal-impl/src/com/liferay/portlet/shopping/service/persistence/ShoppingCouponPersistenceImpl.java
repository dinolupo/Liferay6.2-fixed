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

package com.liferay.portlet.shopping.service.persistence;

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
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.shopping.NoSuchCouponException;
import com.liferay.portlet.shopping.model.ShoppingCoupon;
import com.liferay.portlet.shopping.model.impl.ShoppingCouponImpl;
import com.liferay.portlet.shopping.model.impl.ShoppingCouponModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the shopping coupon service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCouponPersistence
 * @see ShoppingCouponUtil
 * @generated
 */
public class ShoppingCouponPersistenceImpl extends BasePersistenceImpl<ShoppingCoupon>
	implements ShoppingCouponPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ShoppingCouponUtil} to access the shopping coupon persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ShoppingCouponImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED,
			ShoppingCouponImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED,
			ShoppingCouponImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED,
			ShoppingCouponImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED,
			ShoppingCouponImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ShoppingCouponModelImpl.GROUPID_COLUMN_BITMASK |
			ShoppingCouponModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the shopping coupons where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching shopping coupons
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShoppingCoupon> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the shopping coupons where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.shopping.model.impl.ShoppingCouponModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of shopping coupons
	 * @param end the upper bound of the range of shopping coupons (not inclusive)
	 * @return the range of matching shopping coupons
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShoppingCoupon> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the shopping coupons where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.shopping.model.impl.ShoppingCouponModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of shopping coupons
	 * @param end the upper bound of the range of shopping coupons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching shopping coupons
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShoppingCoupon> findByGroupId(long groupId, int start, int end,
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

		List<ShoppingCoupon> list = (List<ShoppingCoupon>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ShoppingCoupon shoppingCoupon : list) {
				if ((groupId != shoppingCoupon.getGroupId())) {
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

			query.append(_SQL_SELECT_SHOPPINGCOUPON_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ShoppingCouponModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ShoppingCoupon>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ShoppingCoupon>(list);
				}
				else {
					list = (List<ShoppingCoupon>)QueryUtil.list(q,
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
	 * Returns the first shopping coupon in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching shopping coupon
	 * @throws com.liferay.portlet.shopping.NoSuchCouponException if a matching shopping coupon could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchCouponException, SystemException {
		ShoppingCoupon shoppingCoupon = fetchByGroupId_First(groupId,
				orderByComparator);

		if (shoppingCoupon != null) {
			return shoppingCoupon;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCouponException(msg.toString());
	}

	/**
	 * Returns the first shopping coupon in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching shopping coupon, or <code>null</code> if a matching shopping coupon could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ShoppingCoupon> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last shopping coupon in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching shopping coupon
	 * @throws com.liferay.portlet.shopping.NoSuchCouponException if a matching shopping coupon could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchCouponException, SystemException {
		ShoppingCoupon shoppingCoupon = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (shoppingCoupon != null) {
			return shoppingCoupon;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCouponException(msg.toString());
	}

	/**
	 * Returns the last shopping coupon in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching shopping coupon, or <code>null</code> if a matching shopping coupon could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ShoppingCoupon> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the shopping coupons before and after the current shopping coupon in the ordered set where groupId = &#63;.
	 *
	 * @param couponId the primary key of the current shopping coupon
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next shopping coupon
	 * @throws com.liferay.portlet.shopping.NoSuchCouponException if a shopping coupon with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon[] findByGroupId_PrevAndNext(long couponId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchCouponException, SystemException {
		ShoppingCoupon shoppingCoupon = findByPrimaryKey(couponId);

		Session session = null;

		try {
			session = openSession();

			ShoppingCoupon[] array = new ShoppingCouponImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, shoppingCoupon,
					groupId, orderByComparator, true);

			array[1] = shoppingCoupon;

			array[2] = getByGroupId_PrevAndNext(session, shoppingCoupon,
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

	protected ShoppingCoupon getByGroupId_PrevAndNext(Session session,
		ShoppingCoupon shoppingCoupon, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SHOPPINGCOUPON_WHERE);

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
			query.append(ShoppingCouponModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(shoppingCoupon);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ShoppingCoupon> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the shopping coupons where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (ShoppingCoupon shoppingCoupon : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(shoppingCoupon);
		}
	}

	/**
	 * Returns the number of shopping coupons where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching shopping coupons
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

			query.append(_SQL_COUNT_SHOPPINGCOUPON_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "shoppingCoupon.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_CODE = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED,
			ShoppingCouponImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByCode",
			new String[] { String.class.getName() },
			ShoppingCouponModelImpl.CODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CODE = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCode",
			new String[] { String.class.getName() });

	/**
	 * Returns the shopping coupon where code = &#63; or throws a {@link com.liferay.portlet.shopping.NoSuchCouponException} if it could not be found.
	 *
	 * @param code the code
	 * @return the matching shopping coupon
	 * @throws com.liferay.portlet.shopping.NoSuchCouponException if a matching shopping coupon could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon findByCode(String code)
		throws NoSuchCouponException, SystemException {
		ShoppingCoupon shoppingCoupon = fetchByCode(code);

		if (shoppingCoupon == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("code=");
			msg.append(code);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCouponException(msg.toString());
		}

		return shoppingCoupon;
	}

	/**
	 * Returns the shopping coupon where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param code the code
	 * @return the matching shopping coupon, or <code>null</code> if a matching shopping coupon could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon fetchByCode(String code) throws SystemException {
		return fetchByCode(code, true);
	}

	/**
	 * Returns the shopping coupon where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param code the code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching shopping coupon, or <code>null</code> if a matching shopping coupon could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon fetchByCode(String code, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { code };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CODE,
					finderArgs, this);
		}

		if (result instanceof ShoppingCoupon) {
			ShoppingCoupon shoppingCoupon = (ShoppingCoupon)result;

			if (!Validator.equals(code, shoppingCoupon.getCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SHOPPINGCOUPON_WHERE);

			boolean bindCode = false;

			if (code == null) {
				query.append(_FINDER_COLUMN_CODE_CODE_1);
			}
			else if (code.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CODE_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_CODE_CODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCode) {
					qPos.add(code);
				}

				List<ShoppingCoupon> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
						finderArgs, list);
				}
				else {
					ShoppingCoupon shoppingCoupon = list.get(0);

					result = shoppingCoupon;

					cacheResult(shoppingCoupon);

					if ((shoppingCoupon.getCode() == null) ||
							!shoppingCoupon.getCode().equals(code)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
							finderArgs, shoppingCoupon);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE,
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
			return (ShoppingCoupon)result;
		}
	}

	/**
	 * Removes the shopping coupon where code = &#63; from the database.
	 *
	 * @param code the code
	 * @return the shopping coupon that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon removeByCode(String code)
		throws NoSuchCouponException, SystemException {
		ShoppingCoupon shoppingCoupon = findByCode(code);

		return remove(shoppingCoupon);
	}

	/**
	 * Returns the number of shopping coupons where code = &#63;.
	 *
	 * @param code the code
	 * @return the number of matching shopping coupons
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCode(String code) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CODE;

		Object[] finderArgs = new Object[] { code };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SHOPPINGCOUPON_WHERE);

			boolean bindCode = false;

			if (code == null) {
				query.append(_FINDER_COLUMN_CODE_CODE_1);
			}
			else if (code.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CODE_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_CODE_CODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCode) {
					qPos.add(code);
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

	private static final String _FINDER_COLUMN_CODE_CODE_1 = "shoppingCoupon.code IS NULL";
	private static final String _FINDER_COLUMN_CODE_CODE_2 = "shoppingCoupon.code = ?";
	private static final String _FINDER_COLUMN_CODE_CODE_3 = "(shoppingCoupon.code IS NULL OR shoppingCoupon.code = '')";

	public ShoppingCouponPersistenceImpl() {
		setModelClass(ShoppingCoupon.class);
	}

	/**
	 * Caches the shopping coupon in the entity cache if it is enabled.
	 *
	 * @param shoppingCoupon the shopping coupon
	 */
	@Override
	public void cacheResult(ShoppingCoupon shoppingCoupon) {
		EntityCacheUtil.putResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponImpl.class, shoppingCoupon.getPrimaryKey(),
			shoppingCoupon);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
			new Object[] { shoppingCoupon.getCode() }, shoppingCoupon);

		shoppingCoupon.resetOriginalValues();
	}

	/**
	 * Caches the shopping coupons in the entity cache if it is enabled.
	 *
	 * @param shoppingCoupons the shopping coupons
	 */
	@Override
	public void cacheResult(List<ShoppingCoupon> shoppingCoupons) {
		for (ShoppingCoupon shoppingCoupon : shoppingCoupons) {
			if (EntityCacheUtil.getResult(
						ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
						ShoppingCouponImpl.class, shoppingCoupon.getPrimaryKey()) == null) {
				cacheResult(shoppingCoupon);
			}
			else {
				shoppingCoupon.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all shopping coupons.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ShoppingCouponImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ShoppingCouponImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the shopping coupon.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ShoppingCoupon shoppingCoupon) {
		EntityCacheUtil.removeResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponImpl.class, shoppingCoupon.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(shoppingCoupon);
	}

	@Override
	public void clearCache(List<ShoppingCoupon> shoppingCoupons) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ShoppingCoupon shoppingCoupon : shoppingCoupons) {
			EntityCacheUtil.removeResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
				ShoppingCouponImpl.class, shoppingCoupon.getPrimaryKey());

			clearUniqueFindersCache(shoppingCoupon);
		}
	}

	protected void cacheUniqueFindersCache(ShoppingCoupon shoppingCoupon) {
		if (shoppingCoupon.isNew()) {
			Object[] args = new Object[] { shoppingCoupon.getCode() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE, args,
				shoppingCoupon);
		}
		else {
			ShoppingCouponModelImpl shoppingCouponModelImpl = (ShoppingCouponModelImpl)shoppingCoupon;

			if ((shoppingCouponModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_CODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { shoppingCoupon.getCode() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE, args,
					shoppingCoupon);
			}
		}
	}

	protected void clearUniqueFindersCache(ShoppingCoupon shoppingCoupon) {
		ShoppingCouponModelImpl shoppingCouponModelImpl = (ShoppingCouponModelImpl)shoppingCoupon;

		Object[] args = new Object[] { shoppingCoupon.getCode() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE, args);

		if ((shoppingCouponModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CODE.getColumnBitmask()) != 0) {
			args = new Object[] { shoppingCouponModelImpl.getOriginalCode() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE, args);
		}
	}

	/**
	 * Creates a new shopping coupon with the primary key. Does not add the shopping coupon to the database.
	 *
	 * @param couponId the primary key for the new shopping coupon
	 * @return the new shopping coupon
	 */
	@Override
	public ShoppingCoupon create(long couponId) {
		ShoppingCoupon shoppingCoupon = new ShoppingCouponImpl();

		shoppingCoupon.setNew(true);
		shoppingCoupon.setPrimaryKey(couponId);

		return shoppingCoupon;
	}

	/**
	 * Removes the shopping coupon with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param couponId the primary key of the shopping coupon
	 * @return the shopping coupon that was removed
	 * @throws com.liferay.portlet.shopping.NoSuchCouponException if a shopping coupon with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon remove(long couponId)
		throws NoSuchCouponException, SystemException {
		return remove((Serializable)couponId);
	}

	/**
	 * Removes the shopping coupon with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the shopping coupon
	 * @return the shopping coupon that was removed
	 * @throws com.liferay.portlet.shopping.NoSuchCouponException if a shopping coupon with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon remove(Serializable primaryKey)
		throws NoSuchCouponException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ShoppingCoupon shoppingCoupon = (ShoppingCoupon)session.get(ShoppingCouponImpl.class,
					primaryKey);

			if (shoppingCoupon == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCouponException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(shoppingCoupon);
		}
		catch (NoSuchCouponException nsee) {
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
	protected ShoppingCoupon removeImpl(ShoppingCoupon shoppingCoupon)
		throws SystemException {
		shoppingCoupon = toUnwrappedModel(shoppingCoupon);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(shoppingCoupon)) {
				shoppingCoupon = (ShoppingCoupon)session.get(ShoppingCouponImpl.class,
						shoppingCoupon.getPrimaryKeyObj());
			}

			if (shoppingCoupon != null) {
				session.delete(shoppingCoupon);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (shoppingCoupon != null) {
			clearCache(shoppingCoupon);
		}

		return shoppingCoupon;
	}

	@Override
	public ShoppingCoupon updateImpl(
		com.liferay.portlet.shopping.model.ShoppingCoupon shoppingCoupon)
		throws SystemException {
		shoppingCoupon = toUnwrappedModel(shoppingCoupon);

		boolean isNew = shoppingCoupon.isNew();

		ShoppingCouponModelImpl shoppingCouponModelImpl = (ShoppingCouponModelImpl)shoppingCoupon;

		Session session = null;

		try {
			session = openSession();

			if (shoppingCoupon.isNew()) {
				session.save(shoppingCoupon);

				shoppingCoupon.setNew(false);
			}
			else {
				session.merge(shoppingCoupon);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ShoppingCouponModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((shoppingCouponModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						shoppingCouponModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { shoppingCouponModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		EntityCacheUtil.putResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponImpl.class, shoppingCoupon.getPrimaryKey(),
			shoppingCoupon);

		clearUniqueFindersCache(shoppingCoupon);
		cacheUniqueFindersCache(shoppingCoupon);

		return shoppingCoupon;
	}

	protected ShoppingCoupon toUnwrappedModel(ShoppingCoupon shoppingCoupon) {
		if (shoppingCoupon instanceof ShoppingCouponImpl) {
			return shoppingCoupon;
		}

		ShoppingCouponImpl shoppingCouponImpl = new ShoppingCouponImpl();

		shoppingCouponImpl.setNew(shoppingCoupon.isNew());
		shoppingCouponImpl.setPrimaryKey(shoppingCoupon.getPrimaryKey());

		shoppingCouponImpl.setCouponId(shoppingCoupon.getCouponId());
		shoppingCouponImpl.setGroupId(shoppingCoupon.getGroupId());
		shoppingCouponImpl.setCompanyId(shoppingCoupon.getCompanyId());
		shoppingCouponImpl.setUserId(shoppingCoupon.getUserId());
		shoppingCouponImpl.setUserName(shoppingCoupon.getUserName());
		shoppingCouponImpl.setCreateDate(shoppingCoupon.getCreateDate());
		shoppingCouponImpl.setModifiedDate(shoppingCoupon.getModifiedDate());
		shoppingCouponImpl.setCode(shoppingCoupon.getCode());
		shoppingCouponImpl.setName(shoppingCoupon.getName());
		shoppingCouponImpl.setDescription(shoppingCoupon.getDescription());
		shoppingCouponImpl.setStartDate(shoppingCoupon.getStartDate());
		shoppingCouponImpl.setEndDate(shoppingCoupon.getEndDate());
		shoppingCouponImpl.setActive(shoppingCoupon.isActive());
		shoppingCouponImpl.setLimitCategories(shoppingCoupon.getLimitCategories());
		shoppingCouponImpl.setLimitSkus(shoppingCoupon.getLimitSkus());
		shoppingCouponImpl.setMinOrder(shoppingCoupon.getMinOrder());
		shoppingCouponImpl.setDiscount(shoppingCoupon.getDiscount());
		shoppingCouponImpl.setDiscountType(shoppingCoupon.getDiscountType());

		return shoppingCouponImpl;
	}

	/**
	 * Returns the shopping coupon with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the shopping coupon
	 * @return the shopping coupon
	 * @throws com.liferay.portlet.shopping.NoSuchCouponException if a shopping coupon with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCouponException, SystemException {
		ShoppingCoupon shoppingCoupon = fetchByPrimaryKey(primaryKey);

		if (shoppingCoupon == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCouponException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return shoppingCoupon;
	}

	/**
	 * Returns the shopping coupon with the primary key or throws a {@link com.liferay.portlet.shopping.NoSuchCouponException} if it could not be found.
	 *
	 * @param couponId the primary key of the shopping coupon
	 * @return the shopping coupon
	 * @throws com.liferay.portlet.shopping.NoSuchCouponException if a shopping coupon with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon findByPrimaryKey(long couponId)
		throws NoSuchCouponException, SystemException {
		return findByPrimaryKey((Serializable)couponId);
	}

	/**
	 * Returns the shopping coupon with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the shopping coupon
	 * @return the shopping coupon, or <code>null</code> if a shopping coupon with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ShoppingCoupon shoppingCoupon = (ShoppingCoupon)EntityCacheUtil.getResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
				ShoppingCouponImpl.class, primaryKey);

		if (shoppingCoupon == _nullShoppingCoupon) {
			return null;
		}

		if (shoppingCoupon == null) {
			Session session = null;

			try {
				session = openSession();

				shoppingCoupon = (ShoppingCoupon)session.get(ShoppingCouponImpl.class,
						primaryKey);

				if (shoppingCoupon != null) {
					cacheResult(shoppingCoupon);
				}
				else {
					EntityCacheUtil.putResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
						ShoppingCouponImpl.class, primaryKey,
						_nullShoppingCoupon);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
					ShoppingCouponImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return shoppingCoupon;
	}

	/**
	 * Returns the shopping coupon with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param couponId the primary key of the shopping coupon
	 * @return the shopping coupon, or <code>null</code> if a shopping coupon with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingCoupon fetchByPrimaryKey(long couponId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)couponId);
	}

	/**
	 * Returns all the shopping coupons.
	 *
	 * @return the shopping coupons
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShoppingCoupon> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the shopping coupons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.shopping.model.impl.ShoppingCouponModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of shopping coupons
	 * @param end the upper bound of the range of shopping coupons (not inclusive)
	 * @return the range of shopping coupons
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShoppingCoupon> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the shopping coupons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.shopping.model.impl.ShoppingCouponModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of shopping coupons
	 * @param end the upper bound of the range of shopping coupons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of shopping coupons
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShoppingCoupon> findAll(int start, int end,
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

		List<ShoppingCoupon> list = (List<ShoppingCoupon>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SHOPPINGCOUPON);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SHOPPINGCOUPON;

				if (pagination) {
					sql = sql.concat(ShoppingCouponModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ShoppingCoupon>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ShoppingCoupon>(list);
				}
				else {
					list = (List<ShoppingCoupon>)QueryUtil.list(q,
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
	 * Removes all the shopping coupons from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ShoppingCoupon shoppingCoupon : findAll()) {
			remove(shoppingCoupon);
		}
	}

	/**
	 * Returns the number of shopping coupons.
	 *
	 * @return the number of shopping coupons
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

				Query q = session.createQuery(_SQL_COUNT_SHOPPINGCOUPON);

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
	 * Initializes the shopping coupon persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.shopping.model.ShoppingCoupon")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ShoppingCoupon>> listenersList = new ArrayList<ModelListener<ShoppingCoupon>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ShoppingCoupon>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ShoppingCouponImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SHOPPINGCOUPON = "SELECT shoppingCoupon FROM ShoppingCoupon shoppingCoupon";
	private static final String _SQL_SELECT_SHOPPINGCOUPON_WHERE = "SELECT shoppingCoupon FROM ShoppingCoupon shoppingCoupon WHERE ";
	private static final String _SQL_COUNT_SHOPPINGCOUPON = "SELECT COUNT(shoppingCoupon) FROM ShoppingCoupon shoppingCoupon";
	private static final String _SQL_COUNT_SHOPPINGCOUPON_WHERE = "SELECT COUNT(shoppingCoupon) FROM ShoppingCoupon shoppingCoupon WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "shoppingCoupon.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ShoppingCoupon exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ShoppingCoupon exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(ShoppingCouponPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"code", "active"
			});
	private static ShoppingCoupon _nullShoppingCoupon = new ShoppingCouponImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ShoppingCoupon> toCacheModel() {
				return _nullShoppingCouponCacheModel;
			}
		};

	private static CacheModel<ShoppingCoupon> _nullShoppingCouponCacheModel = new CacheModel<ShoppingCoupon>() {
			@Override
			public ShoppingCoupon toEntityModel() {
				return _nullShoppingCoupon;
			}
		};
}