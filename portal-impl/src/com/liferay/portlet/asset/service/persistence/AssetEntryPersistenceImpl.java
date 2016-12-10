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

package com.liferay.portlet.asset.service.persistence;

import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.util.CalendarUtil;
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
import com.liferay.portal.service.persistence.impl.TableMapper;
import com.liferay.portal.service.persistence.impl.TableMapperFactory;

import com.liferay.portlet.asset.NoSuchEntryException;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.impl.AssetEntryImpl;
import com.liferay.portlet.asset.model.impl.AssetEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the asset entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryPersistence
 * @see AssetEntryUtil
 * @generated
 */
public class AssetEntryPersistenceImpl extends BasePersistenceImpl<AssetEntry>
	implements AssetEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetEntryUtil} to access the asset entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			AssetEntryModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the asset entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the asset entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @return the range of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<AssetEntry> list = (List<AssetEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetEntry assetEntry : list) {
				if ((companyId != assetEntry.getCompanyId())) {
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

			query.append(_SQL_SELECT_ASSETENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntry>(list);
				}
				else {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (assetEntry != null) {
			return assetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first asset entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetEntry> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (assetEntry != null) {
			return assetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last asset entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<AssetEntry> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entries before and after the current asset entry in the ordered set where companyId = &#63;.
	 *
	 * @param entryId the primary key of the current asset entry
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry[] findByCompanyId_PrevAndNext(long entryId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			AssetEntry[] array = new AssetEntryImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, assetEntry,
					companyId, orderByComparator, true);

			array[1] = assetEntry;

			array[2] = getByCompanyId_PrevAndNext(session, assetEntry,
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

	protected AssetEntry getByCompanyId_PrevAndNext(Session session,
		AssetEntry assetEntry, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETENTRY_WHERE);

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
			query.append(AssetEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entries where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCompanyId(long companyId) throws SystemException {
		for (AssetEntry assetEntry : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetEntry);
		}
	}

	/**
	 * Returns the number of asset entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching asset entries
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

			query.append(_SQL_COUNT_ASSETENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "assetEntry.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_VISIBLE = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByVisible",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VISIBLE =
		new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByVisible",
			new String[] { Boolean.class.getName() },
			AssetEntryModelImpl.VISIBLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_VISIBLE = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByVisible",
			new String[] { Boolean.class.getName() });

	/**
	 * Returns all the asset entries where visible = &#63;.
	 *
	 * @param visible the visible
	 * @return the matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByVisible(boolean visible)
		throws SystemException {
		return findByVisible(visible, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entries where visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param visible the visible
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @return the range of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByVisible(boolean visible, int start, int end)
		throws SystemException {
		return findByVisible(visible, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entries where visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param visible the visible
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByVisible(boolean visible, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VISIBLE;
			finderArgs = new Object[] { visible };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_VISIBLE;
			finderArgs = new Object[] { visible, start, end, orderByComparator };
		}

		List<AssetEntry> list = (List<AssetEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetEntry assetEntry : list) {
				if ((visible != assetEntry.getVisible())) {
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

			query.append(_SQL_SELECT_ASSETENTRY_WHERE);

			query.append(_FINDER_COLUMN_VISIBLE_VISIBLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(visible);

				if (!pagination) {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntry>(list);
				}
				else {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset entry in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByVisible_First(boolean visible,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByVisible_First(visible, orderByComparator);

		if (assetEntry != null) {
			return assetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("visible=");
		msg.append(visible);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first asset entry in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByVisible_First(boolean visible,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetEntry> list = findByVisible(visible, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByVisible_Last(boolean visible,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByVisible_Last(visible, orderByComparator);

		if (assetEntry != null) {
			return assetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("visible=");
		msg.append(visible);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last asset entry in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByVisible_Last(boolean visible,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByVisible(visible);

		if (count == 0) {
			return null;
		}

		List<AssetEntry> list = findByVisible(visible, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entries before and after the current asset entry in the ordered set where visible = &#63;.
	 *
	 * @param entryId the primary key of the current asset entry
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry[] findByVisible_PrevAndNext(long entryId,
		boolean visible, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			AssetEntry[] array = new AssetEntryImpl[3];

			array[0] = getByVisible_PrevAndNext(session, assetEntry, visible,
					orderByComparator, true);

			array[1] = assetEntry;

			array[2] = getByVisible_PrevAndNext(session, assetEntry, visible,
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

	protected AssetEntry getByVisible_PrevAndNext(Session session,
		AssetEntry assetEntry, boolean visible,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETENTRY_WHERE);

		query.append(_FINDER_COLUMN_VISIBLE_VISIBLE_2);

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
			query.append(AssetEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(visible);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entries where visible = &#63; from the database.
	 *
	 * @param visible the visible
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByVisible(boolean visible) throws SystemException {
		for (AssetEntry assetEntry : findByVisible(visible, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetEntry);
		}
	}

	/**
	 * Returns the number of asset entries where visible = &#63;.
	 *
	 * @param visible the visible
	 * @return the number of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByVisible(boolean visible) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_VISIBLE;

		Object[] finderArgs = new Object[] { visible };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETENTRY_WHERE);

			query.append(_FINDER_COLUMN_VISIBLE_VISIBLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(visible);

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

	private static final String _FINDER_COLUMN_VISIBLE_VISIBLE_2 = "assetEntry.visible = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLISHDATE =
		new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublishDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLISHDATE =
		new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublishDate",
			new String[] { Date.class.getName() },
			AssetEntryModelImpl.PUBLISHDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLISHDATE = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublishDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the asset entries where publishDate = &#63;.
	 *
	 * @param publishDate the publish date
	 * @return the matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByPublishDate(Date publishDate)
		throws SystemException {
		return findByPublishDate(publishDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entries where publishDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publishDate the publish date
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @return the range of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByPublishDate(Date publishDate, int start,
		int end) throws SystemException {
		return findByPublishDate(publishDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entries where publishDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publishDate the publish date
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByPublishDate(Date publishDate, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLISHDATE;
			finderArgs = new Object[] { publishDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLISHDATE;
			finderArgs = new Object[] { publishDate, start, end, orderByComparator };
		}

		List<AssetEntry> list = (List<AssetEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetEntry assetEntry : list) {
				if (!Validator.equals(publishDate, assetEntry.getPublishDate())) {
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

			query.append(_SQL_SELECT_ASSETENTRY_WHERE);

			boolean bindPublishDate = false;

			if (publishDate == null) {
				query.append(_FINDER_COLUMN_PUBLISHDATE_PUBLISHDATE_1);
			}
			else {
				bindPublishDate = true;

				query.append(_FINDER_COLUMN_PUBLISHDATE_PUBLISHDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublishDate) {
					qPos.add(CalendarUtil.getTimestamp(publishDate));
				}

				if (!pagination) {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntry>(list);
				}
				else {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset entry in the ordered set where publishDate = &#63;.
	 *
	 * @param publishDate the publish date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByPublishDate_First(Date publishDate,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByPublishDate_First(publishDate,
				orderByComparator);

		if (assetEntry != null) {
			return assetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publishDate=");
		msg.append(publishDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first asset entry in the ordered set where publishDate = &#63;.
	 *
	 * @param publishDate the publish date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByPublishDate_First(Date publishDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetEntry> list = findByPublishDate(publishDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry in the ordered set where publishDate = &#63;.
	 *
	 * @param publishDate the publish date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByPublishDate_Last(Date publishDate,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByPublishDate_Last(publishDate,
				orderByComparator);

		if (assetEntry != null) {
			return assetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publishDate=");
		msg.append(publishDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last asset entry in the ordered set where publishDate = &#63;.
	 *
	 * @param publishDate the publish date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByPublishDate_Last(Date publishDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPublishDate(publishDate);

		if (count == 0) {
			return null;
		}

		List<AssetEntry> list = findByPublishDate(publishDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entries before and after the current asset entry in the ordered set where publishDate = &#63;.
	 *
	 * @param entryId the primary key of the current asset entry
	 * @param publishDate the publish date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry[] findByPublishDate_PrevAndNext(long entryId,
		Date publishDate, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			AssetEntry[] array = new AssetEntryImpl[3];

			array[0] = getByPublishDate_PrevAndNext(session, assetEntry,
					publishDate, orderByComparator, true);

			array[1] = assetEntry;

			array[2] = getByPublishDate_PrevAndNext(session, assetEntry,
					publishDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntry getByPublishDate_PrevAndNext(Session session,
		AssetEntry assetEntry, Date publishDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETENTRY_WHERE);

		boolean bindPublishDate = false;

		if (publishDate == null) {
			query.append(_FINDER_COLUMN_PUBLISHDATE_PUBLISHDATE_1);
		}
		else {
			bindPublishDate = true;

			query.append(_FINDER_COLUMN_PUBLISHDATE_PUBLISHDATE_2);
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
			query.append(AssetEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPublishDate) {
			qPos.add(CalendarUtil.getTimestamp(publishDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entries where publishDate = &#63; from the database.
	 *
	 * @param publishDate the publish date
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByPublishDate(Date publishDate) throws SystemException {
		for (AssetEntry assetEntry : findByPublishDate(publishDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetEntry);
		}
	}

	/**
	 * Returns the number of asset entries where publishDate = &#63;.
	 *
	 * @param publishDate the publish date
	 * @return the number of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByPublishDate(Date publishDate) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLISHDATE;

		Object[] finderArgs = new Object[] { publishDate };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETENTRY_WHERE);

			boolean bindPublishDate = false;

			if (publishDate == null) {
				query.append(_FINDER_COLUMN_PUBLISHDATE_PUBLISHDATE_1);
			}
			else {
				bindPublishDate = true;

				query.append(_FINDER_COLUMN_PUBLISHDATE_PUBLISHDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublishDate) {
					qPos.add(CalendarUtil.getTimestamp(publishDate));
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

	private static final String _FINDER_COLUMN_PUBLISHDATE_PUBLISHDATE_1 = "assetEntry.publishDate IS NULL";
	private static final String _FINDER_COLUMN_PUBLISHDATE_PUBLISHDATE_2 = "assetEntry.publishDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EXPIRATIONDATE =
		new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByExpirationDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXPIRATIONDATE =
		new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByExpirationDate",
			new String[] { Date.class.getName() },
			AssetEntryModelImpl.EXPIRATIONDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EXPIRATIONDATE = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByExpirationDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the asset entries where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByExpirationDate(Date expirationDate)
		throws SystemException {
		return findByExpirationDate(expirationDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entries where expirationDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @return the range of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByExpirationDate(Date expirationDate,
		int start, int end) throws SystemException {
		return findByExpirationDate(expirationDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entries where expirationDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByExpirationDate(Date expirationDate,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXPIRATIONDATE;
			finderArgs = new Object[] { expirationDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EXPIRATIONDATE;
			finderArgs = new Object[] {
					expirationDate,
					
					start, end, orderByComparator
				};
		}

		List<AssetEntry> list = (List<AssetEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetEntry assetEntry : list) {
				if (!Validator.equals(expirationDate,
							assetEntry.getExpirationDate())) {
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

			query.append(_SQL_SELECT_ASSETENTRY_WHERE);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExpirationDate) {
					qPos.add(CalendarUtil.getTimestamp(expirationDate));
				}

				if (!pagination) {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntry>(list);
				}
				else {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset entry in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByExpirationDate_First(Date expirationDate,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByExpirationDate_First(expirationDate,
				orderByComparator);

		if (assetEntry != null) {
			return assetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expirationDate=");
		msg.append(expirationDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first asset entry in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByExpirationDate_First(Date expirationDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetEntry> list = findByExpirationDate(expirationDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByExpirationDate_Last(Date expirationDate,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByExpirationDate_Last(expirationDate,
				orderByComparator);

		if (assetEntry != null) {
			return assetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expirationDate=");
		msg.append(expirationDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last asset entry in the ordered set where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByExpirationDate_Last(Date expirationDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByExpirationDate(expirationDate);

		if (count == 0) {
			return null;
		}

		List<AssetEntry> list = findByExpirationDate(expirationDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entries before and after the current asset entry in the ordered set where expirationDate = &#63;.
	 *
	 * @param entryId the primary key of the current asset entry
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry[] findByExpirationDate_PrevAndNext(long entryId,
		Date expirationDate, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			AssetEntry[] array = new AssetEntryImpl[3];

			array[0] = getByExpirationDate_PrevAndNext(session, assetEntry,
					expirationDate, orderByComparator, true);

			array[1] = assetEntry;

			array[2] = getByExpirationDate_PrevAndNext(session, assetEntry,
					expirationDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntry getByExpirationDate_PrevAndNext(Session session,
		AssetEntry assetEntry, Date expirationDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETENTRY_WHERE);

		boolean bindExpirationDate = false;

		if (expirationDate == null) {
			query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1);
		}
		else {
			bindExpirationDate = true;

			query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2);
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
			query.append(AssetEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindExpirationDate) {
			qPos.add(CalendarUtil.getTimestamp(expirationDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entries where expirationDate = &#63; from the database.
	 *
	 * @param expirationDate the expiration date
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByExpirationDate(Date expirationDate)
		throws SystemException {
		for (AssetEntry assetEntry : findByExpirationDate(expirationDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetEntry);
		}
	}

	/**
	 * Returns the number of asset entries where expirationDate = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the number of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByExpirationDate(Date expirationDate)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EXPIRATIONDATE;

		Object[] finderArgs = new Object[] { expirationDate };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETENTRY_WHERE);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExpirationDate) {
					qPos.add(CalendarUtil.getTimestamp(expirationDate));
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

	private static final String _FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1 = "assetEntry.expirationDate IS NULL";
	private static final String _FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2 = "assetEntry.expirationDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LAYOUTUUID =
		new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLayoutUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTUUID =
		new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLayoutUuid",
			new String[] { String.class.getName() },
			AssetEntryModelImpl.LAYOUTUUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LAYOUTUUID = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLayoutUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the asset entries where layoutUuid = &#63;.
	 *
	 * @param layoutUuid the layout uuid
	 * @return the matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByLayoutUuid(String layoutUuid)
		throws SystemException {
		return findByLayoutUuid(layoutUuid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entries where layoutUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutUuid the layout uuid
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @return the range of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByLayoutUuid(String layoutUuid, int start,
		int end) throws SystemException {
		return findByLayoutUuid(layoutUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entries where layoutUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutUuid the layout uuid
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findByLayoutUuid(String layoutUuid, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTUUID;
			finderArgs = new Object[] { layoutUuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LAYOUTUUID;
			finderArgs = new Object[] { layoutUuid, start, end, orderByComparator };
		}

		List<AssetEntry> list = (List<AssetEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetEntry assetEntry : list) {
				if (!Validator.equals(layoutUuid, assetEntry.getLayoutUuid())) {
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

			query.append(_SQL_SELECT_ASSETENTRY_WHERE);

			boolean bindLayoutUuid = false;

			if (layoutUuid == null) {
				query.append(_FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_1);
			}
			else if (layoutUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_3);
			}
			else {
				bindLayoutUuid = true;

				query.append(_FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLayoutUuid) {
					qPos.add(layoutUuid);
				}

				if (!pagination) {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntry>(list);
				}
				else {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset entry in the ordered set where layoutUuid = &#63;.
	 *
	 * @param layoutUuid the layout uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByLayoutUuid_First(String layoutUuid,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByLayoutUuid_First(layoutUuid,
				orderByComparator);

		if (assetEntry != null) {
			return assetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("layoutUuid=");
		msg.append(layoutUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first asset entry in the ordered set where layoutUuid = &#63;.
	 *
	 * @param layoutUuid the layout uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByLayoutUuid_First(String layoutUuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetEntry> list = findByLayoutUuid(layoutUuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry in the ordered set where layoutUuid = &#63;.
	 *
	 * @param layoutUuid the layout uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByLayoutUuid_Last(String layoutUuid,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByLayoutUuid_Last(layoutUuid,
				orderByComparator);

		if (assetEntry != null) {
			return assetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("layoutUuid=");
		msg.append(layoutUuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last asset entry in the ordered set where layoutUuid = &#63;.
	 *
	 * @param layoutUuid the layout uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByLayoutUuid_Last(String layoutUuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByLayoutUuid(layoutUuid);

		if (count == 0) {
			return null;
		}

		List<AssetEntry> list = findByLayoutUuid(layoutUuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entries before and after the current asset entry in the ordered set where layoutUuid = &#63;.
	 *
	 * @param entryId the primary key of the current asset entry
	 * @param layoutUuid the layout uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry[] findByLayoutUuid_PrevAndNext(long entryId,
		String layoutUuid, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			AssetEntry[] array = new AssetEntryImpl[3];

			array[0] = getByLayoutUuid_PrevAndNext(session, assetEntry,
					layoutUuid, orderByComparator, true);

			array[1] = assetEntry;

			array[2] = getByLayoutUuid_PrevAndNext(session, assetEntry,
					layoutUuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntry getByLayoutUuid_PrevAndNext(Session session,
		AssetEntry assetEntry, String layoutUuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETENTRY_WHERE);

		boolean bindLayoutUuid = false;

		if (layoutUuid == null) {
			query.append(_FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_1);
		}
		else if (layoutUuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_3);
		}
		else {
			bindLayoutUuid = true;

			query.append(_FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_2);
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
			query.append(AssetEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindLayoutUuid) {
			qPos.add(layoutUuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entries where layoutUuid = &#63; from the database.
	 *
	 * @param layoutUuid the layout uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByLayoutUuid(String layoutUuid) throws SystemException {
		for (AssetEntry assetEntry : findByLayoutUuid(layoutUuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetEntry);
		}
	}

	/**
	 * Returns the number of asset entries where layoutUuid = &#63;.
	 *
	 * @param layoutUuid the layout uuid
	 * @return the number of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByLayoutUuid(String layoutUuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LAYOUTUUID;

		Object[] finderArgs = new Object[] { layoutUuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETENTRY_WHERE);

			boolean bindLayoutUuid = false;

			if (layoutUuid == null) {
				query.append(_FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_1);
			}
			else if (layoutUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_3);
			}
			else {
				bindLayoutUuid = true;

				query.append(_FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLayoutUuid) {
					qPos.add(layoutUuid);
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

	private static final String _FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_1 = "assetEntry.layoutUuid IS NULL";
	private static final String _FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_2 = "assetEntry.layoutUuid = ?";
	private static final String _FINDER_COLUMN_LAYOUTUUID_LAYOUTUUID_3 = "(assetEntry.layoutUuid IS NULL OR assetEntry.layoutUuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_CU = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_CU",
			new String[] { Long.class.getName(), String.class.getName() },
			AssetEntryModelImpl.GROUPID_COLUMN_BITMASK |
			AssetEntryModelImpl.CLASSUUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_CU = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_CU",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the asset entry where groupId = &#63; and classUuid = &#63; or throws a {@link com.liferay.portlet.asset.NoSuchEntryException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param classUuid the class uuid
	 * @return the matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByG_CU(long groupId, String classUuid)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByG_CU(groupId, classUuid);

		if (assetEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", classUuid=");
			msg.append(classUuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchEntryException(msg.toString());
		}

		return assetEntry;
	}

	/**
	 * Returns the asset entry where groupId = &#63; and classUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classUuid the class uuid
	 * @return the matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByG_CU(long groupId, String classUuid)
		throws SystemException {
		return fetchByG_CU(groupId, classUuid, true);
	}

	/**
	 * Returns the asset entry where groupId = &#63; and classUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classUuid the class uuid
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByG_CU(long groupId, String classUuid,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, classUuid };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_CU,
					finderArgs, this);
		}

		if (result instanceof AssetEntry) {
			AssetEntry assetEntry = (AssetEntry)result;

			if ((groupId != assetEntry.getGroupId()) ||
					!Validator.equals(classUuid, assetEntry.getClassUuid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ASSETENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_CU_GROUPID_2);

			boolean bindClassUuid = false;

			if (classUuid == null) {
				query.append(_FINDER_COLUMN_G_CU_CLASSUUID_1);
			}
			else if (classUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_CU_CLASSUUID_3);
			}
			else {
				bindClassUuid = true;

				query.append(_FINDER_COLUMN_G_CU_CLASSUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassUuid) {
					qPos.add(classUuid);
				}

				List<AssetEntry> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_CU,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"AssetEntryPersistenceImpl.fetchByG_CU(long, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					AssetEntry assetEntry = list.get(0);

					result = assetEntry;

					cacheResult(assetEntry);

					if ((assetEntry.getGroupId() != groupId) ||
							(assetEntry.getClassUuid() == null) ||
							!assetEntry.getClassUuid().equals(classUuid)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_CU,
							finderArgs, assetEntry);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_CU,
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
			return (AssetEntry)result;
		}
	}

	/**
	 * Removes the asset entry where groupId = &#63; and classUuid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classUuid the class uuid
	 * @return the asset entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry removeByG_CU(long groupId, String classUuid)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = findByG_CU(groupId, classUuid);

		return remove(assetEntry);
	}

	/**
	 * Returns the number of asset entries where groupId = &#63; and classUuid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classUuid the class uuid
	 * @return the number of matching asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_CU(long groupId, String classUuid)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_CU;

		Object[] finderArgs = new Object[] { groupId, classUuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_CU_GROUPID_2);

			boolean bindClassUuid = false;

			if (classUuid == null) {
				query.append(_FINDER_COLUMN_G_CU_CLASSUUID_1);
			}
			else if (classUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_CU_CLASSUUID_3);
			}
			else {
				bindClassUuid = true;

				query.append(_FINDER_COLUMN_G_CU_CLASSUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassUuid) {
					qPos.add(classUuid);
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

	private static final String _FINDER_COLUMN_G_CU_GROUPID_2 = "assetEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_CU_CLASSUUID_1 = "assetEntry.classUuid IS NULL";
	private static final String _FINDER_COLUMN_G_CU_CLASSUUID_2 = "assetEntry.classUuid = ?";
	private static final String _FINDER_COLUMN_G_CU_CLASSUUID_3 = "(assetEntry.classUuid IS NULL OR assetEntry.classUuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, AssetEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetEntryModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the asset entry where classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.portlet.asset.NoSuchEntryException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByC_C(long classNameId, long classPK)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByC_C(classNameId, classPK);

		if (assetEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchEntryException(msg.toString());
		}

		return assetEntry;
	}

	/**
	 * Returns the asset entry where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByC_C(long classNameId, long classPK)
		throws SystemException {
		return fetchByC_C(classNameId, classPK, true);
	}

	/**
	 * Returns the asset entry where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset entry, or <code>null</code> if a matching asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C,
					finderArgs, this);
		}

		if (result instanceof AssetEntry) {
			AssetEntry assetEntry = (AssetEntry)result;

			if ((classNameId != assetEntry.getClassNameId()) ||
					(classPK != assetEntry.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ASSETENTRY_WHERE);

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

				List<AssetEntry> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
						finderArgs, list);
				}
				else {
					AssetEntry assetEntry = list.get(0);

					result = assetEntry;

					cacheResult(assetEntry);

					if ((assetEntry.getClassNameId() != classNameId) ||
							(assetEntry.getClassPK() != classPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
							finderArgs, assetEntry);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
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
			return (AssetEntry)result;
		}
	}

	/**
	 * Removes the asset entry where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the asset entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry removeByC_C(long classNameId, long classPK)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = findByC_C(classNameId, classPK);

		return remove(assetEntry);
	}

	/**
	 * Returns the number of asset entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching asset entries
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

			query.append(_SQL_COUNT_ASSETENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "assetEntry.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "assetEntry.classPK = ?";

	public AssetEntryPersistenceImpl() {
		setModelClass(AssetEntry.class);
	}

	/**
	 * Caches the asset entry in the entity cache if it is enabled.
	 *
	 * @param assetEntry the asset entry
	 */
	@Override
	public void cacheResult(AssetEntry assetEntry) {
		EntityCacheUtil.putResult(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryImpl.class, assetEntry.getPrimaryKey(), assetEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_CU,
			new Object[] { assetEntry.getGroupId(), assetEntry.getClassUuid() },
			assetEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] { assetEntry.getClassNameId(), assetEntry.getClassPK() },
			assetEntry);

		assetEntry.resetOriginalValues();
	}

	/**
	 * Caches the asset entries in the entity cache if it is enabled.
	 *
	 * @param assetEntries the asset entries
	 */
	@Override
	public void cacheResult(List<AssetEntry> assetEntries) {
		for (AssetEntry assetEntry : assetEntries) {
			if (EntityCacheUtil.getResult(
						AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntryImpl.class, assetEntry.getPrimaryKey()) == null) {
				cacheResult(assetEntry);
			}
			else {
				assetEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetEntry assetEntry) {
		EntityCacheUtil.removeResult(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryImpl.class, assetEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(assetEntry);
	}

	@Override
	public void clearCache(List<AssetEntry> assetEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetEntry assetEntry : assetEntries) {
			EntityCacheUtil.removeResult(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntryImpl.class, assetEntry.getPrimaryKey());

			clearUniqueFindersCache(assetEntry);
		}
	}

	protected void cacheUniqueFindersCache(AssetEntry assetEntry) {
		if (assetEntry.isNew()) {
			Object[] args = new Object[] {
					assetEntry.getGroupId(), assetEntry.getClassUuid()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_CU, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_CU, args,
				assetEntry);

			args = new Object[] {
					assetEntry.getClassNameId(), assetEntry.getClassPK()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C, args, assetEntry);
		}
		else {
			AssetEntryModelImpl assetEntryModelImpl = (AssetEntryModelImpl)assetEntry;

			if ((assetEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_CU.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntry.getGroupId(), assetEntry.getClassUuid()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_CU, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_CU, args,
					assetEntry);
			}

			if ((assetEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntry.getClassNameId(), assetEntry.getClassPK()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C, args,
					assetEntry);
			}
		}
	}

	protected void clearUniqueFindersCache(AssetEntry assetEntry) {
		AssetEntryModelImpl assetEntryModelImpl = (AssetEntryModelImpl)assetEntry;

		Object[] args = new Object[] {
				assetEntry.getGroupId(), assetEntry.getClassUuid()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_CU, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_CU, args);

		if ((assetEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_CU.getColumnBitmask()) != 0) {
			args = new Object[] {
					assetEntryModelImpl.getOriginalGroupId(),
					assetEntryModelImpl.getOriginalClassUuid()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_CU, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_CU, args);
		}

		args = new Object[] { assetEntry.getClassNameId(), assetEntry.getClassPK() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C, args);

		if ((assetEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C.getColumnBitmask()) != 0) {
			args = new Object[] {
					assetEntryModelImpl.getOriginalClassNameId(),
					assetEntryModelImpl.getOriginalClassPK()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C, args);
		}
	}

	/**
	 * Creates a new asset entry with the primary key. Does not add the asset entry to the database.
	 *
	 * @param entryId the primary key for the new asset entry
	 * @return the new asset entry
	 */
	@Override
	public AssetEntry create(long entryId) {
		AssetEntry assetEntry = new AssetEntryImpl();

		assetEntry.setNew(true);
		assetEntry.setPrimaryKey(entryId);

		return assetEntry;
	}

	/**
	 * Removes the asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the asset entry
	 * @return the asset entry that was removed
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry remove(long entryId)
		throws NoSuchEntryException, SystemException {
		return remove((Serializable)entryId);
	}

	/**
	 * Removes the asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset entry
	 * @return the asset entry that was removed
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry remove(Serializable primaryKey)
		throws NoSuchEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetEntry assetEntry = (AssetEntry)session.get(AssetEntryImpl.class,
					primaryKey);

			if (assetEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetEntry);
		}
		catch (NoSuchEntryException nsee) {
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
	protected AssetEntry removeImpl(AssetEntry assetEntry)
		throws SystemException {
		assetEntry = toUnwrappedModel(assetEntry);

		assetEntryToAssetCategoryTableMapper.deleteLeftPrimaryKeyTableMappings(assetEntry.getPrimaryKey());

		assetEntryToAssetTagTableMapper.deleteLeftPrimaryKeyTableMappings(assetEntry.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetEntry)) {
				assetEntry = (AssetEntry)session.get(AssetEntryImpl.class,
						assetEntry.getPrimaryKeyObj());
			}

			if (assetEntry != null) {
				session.delete(assetEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetEntry != null) {
			clearCache(assetEntry);
		}

		return assetEntry;
	}

	@Override
	public AssetEntry updateImpl(
		com.liferay.portlet.asset.model.AssetEntry assetEntry)
		throws SystemException {
		assetEntry = toUnwrappedModel(assetEntry);

		boolean isNew = assetEntry.isNew();

		AssetEntryModelImpl assetEntryModelImpl = (AssetEntryModelImpl)assetEntry;

		Session session = null;

		try {
			session = openSession();

			if (assetEntry.isNew()) {
				session.save(assetEntry);

				assetEntry.setNew(false);
			}
			else {
				session.merge(assetEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntryModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { assetEntryModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((assetEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VISIBLE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntryModelImpl.getOriginalVisible()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_VISIBLE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VISIBLE,
					args);

				args = new Object[] { assetEntryModelImpl.getVisible() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_VISIBLE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VISIBLE,
					args);
			}

			if ((assetEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLISHDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntryModelImpl.getOriginalPublishDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PUBLISHDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLISHDATE,
					args);

				args = new Object[] { assetEntryModelImpl.getPublishDate() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PUBLISHDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLISHDATE,
					args);
			}

			if ((assetEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXPIRATIONDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntryModelImpl.getOriginalExpirationDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EXPIRATIONDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXPIRATIONDATE,
					args);

				args = new Object[] { assetEntryModelImpl.getExpirationDate() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EXPIRATIONDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EXPIRATIONDATE,
					args);
			}

			if ((assetEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTUUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntryModelImpl.getOriginalLayoutUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LAYOUTUUID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTUUID,
					args);

				args = new Object[] { assetEntryModelImpl.getLayoutUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LAYOUTUUID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTUUID,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryImpl.class, assetEntry.getPrimaryKey(), assetEntry);

		clearUniqueFindersCache(assetEntry);
		cacheUniqueFindersCache(assetEntry);

		return assetEntry;
	}

	protected AssetEntry toUnwrappedModel(AssetEntry assetEntry) {
		if (assetEntry instanceof AssetEntryImpl) {
			return assetEntry;
		}

		AssetEntryImpl assetEntryImpl = new AssetEntryImpl();

		assetEntryImpl.setNew(assetEntry.isNew());
		assetEntryImpl.setPrimaryKey(assetEntry.getPrimaryKey());

		assetEntryImpl.setEntryId(assetEntry.getEntryId());
		assetEntryImpl.setGroupId(assetEntry.getGroupId());
		assetEntryImpl.setCompanyId(assetEntry.getCompanyId());
		assetEntryImpl.setUserId(assetEntry.getUserId());
		assetEntryImpl.setUserName(assetEntry.getUserName());
		assetEntryImpl.setCreateDate(assetEntry.getCreateDate());
		assetEntryImpl.setModifiedDate(assetEntry.getModifiedDate());
		assetEntryImpl.setClassNameId(assetEntry.getClassNameId());
		assetEntryImpl.setClassPK(assetEntry.getClassPK());
		assetEntryImpl.setClassUuid(assetEntry.getClassUuid());
		assetEntryImpl.setClassTypeId(assetEntry.getClassTypeId());
		assetEntryImpl.setVisible(assetEntry.isVisible());
		assetEntryImpl.setStartDate(assetEntry.getStartDate());
		assetEntryImpl.setEndDate(assetEntry.getEndDate());
		assetEntryImpl.setPublishDate(assetEntry.getPublishDate());
		assetEntryImpl.setExpirationDate(assetEntry.getExpirationDate());
		assetEntryImpl.setMimeType(assetEntry.getMimeType());
		assetEntryImpl.setTitle(assetEntry.getTitle());
		assetEntryImpl.setDescription(assetEntry.getDescription());
		assetEntryImpl.setSummary(assetEntry.getSummary());
		assetEntryImpl.setUrl(assetEntry.getUrl());
		assetEntryImpl.setLayoutUuid(assetEntry.getLayoutUuid());
		assetEntryImpl.setHeight(assetEntry.getHeight());
		assetEntryImpl.setWidth(assetEntry.getWidth());
		assetEntryImpl.setPriority(assetEntry.getPriority());
		assetEntryImpl.setViewCount(assetEntry.getViewCount());

		return assetEntryImpl;
	}

	/**
	 * Returns the asset entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry
	 * @return the asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException, SystemException {
		AssetEntry assetEntry = fetchByPrimaryKey(primaryKey);

		if (assetEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetEntry;
	}

	/**
	 * Returns the asset entry with the primary key or throws a {@link com.liferay.portlet.asset.NoSuchEntryException} if it could not be found.
	 *
	 * @param entryId the primary key of the asset entry
	 * @return the asset entry
	 * @throws com.liferay.portlet.asset.NoSuchEntryException if a asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry findByPrimaryKey(long entryId)
		throws NoSuchEntryException, SystemException {
		return findByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns the asset entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry
	 * @return the asset entry, or <code>null</code> if a asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		AssetEntry assetEntry = (AssetEntry)EntityCacheUtil.getResult(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntryImpl.class, primaryKey);

		if (assetEntry == _nullAssetEntry) {
			return null;
		}

		if (assetEntry == null) {
			Session session = null;

			try {
				session = openSession();

				assetEntry = (AssetEntry)session.get(AssetEntryImpl.class,
						primaryKey);

				if (assetEntry != null) {
					cacheResult(assetEntry);
				}
				else {
					EntityCacheUtil.putResult(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntryImpl.class, primaryKey, _nullAssetEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(AssetEntryModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assetEntry;
	}

	/**
	 * Returns the asset entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the asset entry
	 * @return the asset entry, or <code>null</code> if a asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntry fetchByPrimaryKey(long entryId) throws SystemException {
		return fetchByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns all the asset entries.
	 *
	 * @return the asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @return the range of asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntry> findAll(int start, int end,
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

		List<AssetEntry> list = (List<AssetEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETENTRY;

				if (pagination) {
					sql = sql.concat(AssetEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntry>(list);
				}
				else {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the asset entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (AssetEntry assetEntry : findAll()) {
			remove(assetEntry);
		}
	}

	/**
	 * Returns the number of asset entries.
	 *
	 * @return the number of asset entries
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

				Query q = session.createQuery(_SQL_COUNT_ASSETENTRY);

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
	 * Returns all the asset categories associated with the asset entry.
	 *
	 * @param pk the primary key of the asset entry
	 * @return the asset categories associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portlet.asset.model.AssetCategory> getAssetCategories(
		long pk) throws SystemException {
		return getAssetCategories(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the asset categories associated with the asset entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the asset entry
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @return the range of asset categories associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portlet.asset.model.AssetCategory> getAssetCategories(
		long pk, int start, int end) throws SystemException {
		return getAssetCategories(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories associated with the asset entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the asset entry
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset categories associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portlet.asset.model.AssetCategory> getAssetCategories(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return assetEntryToAssetCategoryTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of asset categories associated with the asset entry.
	 *
	 * @param pk the primary key of the asset entry
	 * @return the number of asset categories associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getAssetCategoriesSize(long pk) throws SystemException {
		long[] pks = assetEntryToAssetCategoryTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the asset category is associated with the asset entry.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetCategoryPK the primary key of the asset category
	 * @return <code>true</code> if the asset category is associated with the asset entry; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsAssetCategory(long pk, long assetCategoryPK)
		throws SystemException {
		return assetEntryToAssetCategoryTableMapper.containsTableMapping(pk,
			assetCategoryPK);
	}

	/**
	 * Returns <code>true</code> if the asset entry has any asset categories associated with it.
	 *
	 * @param pk the primary key of the asset entry to check for associations with asset categories
	 * @return <code>true</code> if the asset entry has any asset categories associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsAssetCategories(long pk) throws SystemException {
		if (getAssetCategoriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the asset entry and the asset category. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetCategoryPK the primary key of the asset category
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addAssetCategory(long pk, long assetCategoryPK)
		throws SystemException {
		assetEntryToAssetCategoryTableMapper.addTableMapping(pk, assetCategoryPK);
	}

	/**
	 * Adds an association between the asset entry and the asset category. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetCategory the asset category
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addAssetCategory(long pk,
		com.liferay.portlet.asset.model.AssetCategory assetCategory)
		throws SystemException {
		assetEntryToAssetCategoryTableMapper.addTableMapping(pk,
			assetCategory.getPrimaryKey());
	}

	/**
	 * Adds an association between the asset entry and the asset categories. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetCategoryPKs the primary keys of the asset categories
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addAssetCategories(long pk, long[] assetCategoryPKs)
		throws SystemException {
		for (long assetCategoryPK : assetCategoryPKs) {
			assetEntryToAssetCategoryTableMapper.addTableMapping(pk,
				assetCategoryPK);
		}
	}

	/**
	 * Adds an association between the asset entry and the asset categories. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetCategories the asset categories
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addAssetCategories(long pk,
		List<com.liferay.portlet.asset.model.AssetCategory> assetCategories)
		throws SystemException {
		for (com.liferay.portlet.asset.model.AssetCategory assetCategory : assetCategories) {
			assetEntryToAssetCategoryTableMapper.addTableMapping(pk,
				assetCategory.getPrimaryKey());
		}
	}

	/**
	 * Clears all associations between the asset entry and its asset categories. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry to clear the associated asset categories from
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearAssetCategories(long pk) throws SystemException {
		assetEntryToAssetCategoryTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the asset entry and the asset category. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetCategoryPK the primary key of the asset category
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAssetCategory(long pk, long assetCategoryPK)
		throws SystemException {
		assetEntryToAssetCategoryTableMapper.deleteTableMapping(pk,
			assetCategoryPK);
	}

	/**
	 * Removes the association between the asset entry and the asset category. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetCategory the asset category
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAssetCategory(long pk,
		com.liferay.portlet.asset.model.AssetCategory assetCategory)
		throws SystemException {
		assetEntryToAssetCategoryTableMapper.deleteTableMapping(pk,
			assetCategory.getPrimaryKey());
	}

	/**
	 * Removes the association between the asset entry and the asset categories. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetCategoryPKs the primary keys of the asset categories
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAssetCategories(long pk, long[] assetCategoryPKs)
		throws SystemException {
		for (long assetCategoryPK : assetCategoryPKs) {
			assetEntryToAssetCategoryTableMapper.deleteTableMapping(pk,
				assetCategoryPK);
		}
	}

	/**
	 * Removes the association between the asset entry and the asset categories. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetCategories the asset categories
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAssetCategories(long pk,
		List<com.liferay.portlet.asset.model.AssetCategory> assetCategories)
		throws SystemException {
		for (com.liferay.portlet.asset.model.AssetCategory assetCategory : assetCategories) {
			assetEntryToAssetCategoryTableMapper.deleteTableMapping(pk,
				assetCategory.getPrimaryKey());
		}
	}

	/**
	 * Sets the asset categories associated with the asset entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetCategoryPKs the primary keys of the asset categories to be associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setAssetCategories(long pk, long[] assetCategoryPKs)
		throws SystemException {
		Set<Long> newAssetCategoryPKsSet = SetUtil.fromArray(assetCategoryPKs);
		Set<Long> oldAssetCategoryPKsSet = SetUtil.fromArray(assetEntryToAssetCategoryTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeAssetCategoryPKsSet = new HashSet<Long>(oldAssetCategoryPKsSet);

		removeAssetCategoryPKsSet.removeAll(newAssetCategoryPKsSet);

		for (long removeAssetCategoryPK : removeAssetCategoryPKsSet) {
			assetEntryToAssetCategoryTableMapper.deleteTableMapping(pk,
				removeAssetCategoryPK);
		}

		newAssetCategoryPKsSet.removeAll(oldAssetCategoryPKsSet);

		for (long newAssetCategoryPK : newAssetCategoryPKsSet) {
			assetEntryToAssetCategoryTableMapper.addTableMapping(pk,
				newAssetCategoryPK);
		}
	}

	/**
	 * Sets the asset categories associated with the asset entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetCategories the asset categories to be associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setAssetCategories(long pk,
		List<com.liferay.portlet.asset.model.AssetCategory> assetCategories)
		throws SystemException {
		try {
			long[] assetCategoryPKs = new long[assetCategories.size()];

			for (int i = 0; i < assetCategories.size(); i++) {
				com.liferay.portlet.asset.model.AssetCategory assetCategory = assetCategories.get(i);

				assetCategoryPKs[i] = assetCategory.getPrimaryKey();
			}

			setAssetCategories(pk, assetCategoryPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AssetEntryModelImpl.MAPPING_TABLE_ASSETENTRIES_ASSETCATEGORIES_NAME);
		}
	}

	/**
	 * Returns all the asset tags associated with the asset entry.
	 *
	 * @param pk the primary key of the asset entry
	 * @return the asset tags associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portlet.asset.model.AssetTag> getAssetTags(long pk)
		throws SystemException {
		return getAssetTags(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the asset tags associated with the asset entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the asset entry
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @return the range of asset tags associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portlet.asset.model.AssetTag> getAssetTags(
		long pk, int start, int end) throws SystemException {
		return getAssetTags(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset tags associated with the asset entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the asset entry
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset tags associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portlet.asset.model.AssetTag> getAssetTags(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return assetEntryToAssetTagTableMapper.getRightBaseModels(pk, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of asset tags associated with the asset entry.
	 *
	 * @param pk the primary key of the asset entry
	 * @return the number of asset tags associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getAssetTagsSize(long pk) throws SystemException {
		long[] pks = assetEntryToAssetTagTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the asset tag is associated with the asset entry.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetTagPK the primary key of the asset tag
	 * @return <code>true</code> if the asset tag is associated with the asset entry; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsAssetTag(long pk, long assetTagPK)
		throws SystemException {
		return assetEntryToAssetTagTableMapper.containsTableMapping(pk,
			assetTagPK);
	}

	/**
	 * Returns <code>true</code> if the asset entry has any asset tags associated with it.
	 *
	 * @param pk the primary key of the asset entry to check for associations with asset tags
	 * @return <code>true</code> if the asset entry has any asset tags associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsAssetTags(long pk) throws SystemException {
		if (getAssetTagsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the asset entry and the asset tag. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetTagPK the primary key of the asset tag
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addAssetTag(long pk, long assetTagPK) throws SystemException {
		assetEntryToAssetTagTableMapper.addTableMapping(pk, assetTagPK);
	}

	/**
	 * Adds an association between the asset entry and the asset tag. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetTag the asset tag
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addAssetTag(long pk,
		com.liferay.portlet.asset.model.AssetTag assetTag)
		throws SystemException {
		assetEntryToAssetTagTableMapper.addTableMapping(pk,
			assetTag.getPrimaryKey());
	}

	/**
	 * Adds an association between the asset entry and the asset tags. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetTagPKs the primary keys of the asset tags
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addAssetTags(long pk, long[] assetTagPKs)
		throws SystemException {
		for (long assetTagPK : assetTagPKs) {
			assetEntryToAssetTagTableMapper.addTableMapping(pk, assetTagPK);
		}
	}

	/**
	 * Adds an association between the asset entry and the asset tags. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetTags the asset tags
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addAssetTags(long pk,
		List<com.liferay.portlet.asset.model.AssetTag> assetTags)
		throws SystemException {
		for (com.liferay.portlet.asset.model.AssetTag assetTag : assetTags) {
			assetEntryToAssetTagTableMapper.addTableMapping(pk,
				assetTag.getPrimaryKey());
		}
	}

	/**
	 * Clears all associations between the asset entry and its asset tags. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry to clear the associated asset tags from
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearAssetTags(long pk) throws SystemException {
		assetEntryToAssetTagTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the asset entry and the asset tag. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetTagPK the primary key of the asset tag
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAssetTag(long pk, long assetTagPK)
		throws SystemException {
		assetEntryToAssetTagTableMapper.deleteTableMapping(pk, assetTagPK);
	}

	/**
	 * Removes the association between the asset entry and the asset tag. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetTag the asset tag
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAssetTag(long pk,
		com.liferay.portlet.asset.model.AssetTag assetTag)
		throws SystemException {
		assetEntryToAssetTagTableMapper.deleteTableMapping(pk,
			assetTag.getPrimaryKey());
	}

	/**
	 * Removes the association between the asset entry and the asset tags. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetTagPKs the primary keys of the asset tags
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAssetTags(long pk, long[] assetTagPKs)
		throws SystemException {
		for (long assetTagPK : assetTagPKs) {
			assetEntryToAssetTagTableMapper.deleteTableMapping(pk, assetTagPK);
		}
	}

	/**
	 * Removes the association between the asset entry and the asset tags. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetTags the asset tags
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAssetTags(long pk,
		List<com.liferay.portlet.asset.model.AssetTag> assetTags)
		throws SystemException {
		for (com.liferay.portlet.asset.model.AssetTag assetTag : assetTags) {
			assetEntryToAssetTagTableMapper.deleteTableMapping(pk,
				assetTag.getPrimaryKey());
		}
	}

	/**
	 * Sets the asset tags associated with the asset entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetTagPKs the primary keys of the asset tags to be associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setAssetTags(long pk, long[] assetTagPKs)
		throws SystemException {
		Set<Long> newAssetTagPKsSet = SetUtil.fromArray(assetTagPKs);
		Set<Long> oldAssetTagPKsSet = SetUtil.fromArray(assetEntryToAssetTagTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeAssetTagPKsSet = new HashSet<Long>(oldAssetTagPKsSet);

		removeAssetTagPKsSet.removeAll(newAssetTagPKsSet);

		for (long removeAssetTagPK : removeAssetTagPKsSet) {
			assetEntryToAssetTagTableMapper.deleteTableMapping(pk,
				removeAssetTagPK);
		}

		newAssetTagPKsSet.removeAll(oldAssetTagPKsSet);

		for (long newAssetTagPK : newAssetTagPKsSet) {
			assetEntryToAssetTagTableMapper.addTableMapping(pk, newAssetTagPK);
		}
	}

	/**
	 * Sets the asset tags associated with the asset entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset entry
	 * @param assetTags the asset tags to be associated with the asset entry
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setAssetTags(long pk,
		List<com.liferay.portlet.asset.model.AssetTag> assetTags)
		throws SystemException {
		try {
			long[] assetTagPKs = new long[assetTags.size()];

			for (int i = 0; i < assetTags.size(); i++) {
				com.liferay.portlet.asset.model.AssetTag assetTag = assetTags.get(i);

				assetTagPKs[i] = assetTag.getPrimaryKey();
			}

			setAssetTags(pk, assetTagPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(AssetEntryModelImpl.MAPPING_TABLE_ASSETENTRIES_ASSETTAGS_NAME);
		}
	}

	/**
	 * Initializes the asset entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.asset.model.AssetEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetEntry>> listenersList = new ArrayList<ModelListener<AssetEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AssetEntry>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		assetEntryToAssetCategoryTableMapper = TableMapperFactory.getTableMapper("AssetEntries_AssetCategories",
				"entryId", "categoryId", this, assetCategoryPersistence);

		assetEntryToAssetTagTableMapper = TableMapperFactory.getTableMapper("AssetEntries_AssetTags",
				"entryId", "tagId", this, assetTagPersistence);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(AssetEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("AssetEntries_AssetCategories");
		TableMapperFactory.removeTableMapper("AssetEntries_AssetTags");
	}

	@BeanReference(type = AssetCategoryPersistence.class)
	protected AssetCategoryPersistence assetCategoryPersistence;
	protected TableMapper<AssetEntry, com.liferay.portlet.asset.model.AssetCategory> assetEntryToAssetCategoryTableMapper;
	@BeanReference(type = AssetTagPersistence.class)
	protected AssetTagPersistence assetTagPersistence;
	protected TableMapper<AssetEntry, com.liferay.portlet.asset.model.AssetTag> assetEntryToAssetTagTableMapper;
	private static final String _SQL_SELECT_ASSETENTRY = "SELECT assetEntry FROM AssetEntry assetEntry";
	private static final String _SQL_SELECT_ASSETENTRY_WHERE = "SELECT assetEntry FROM AssetEntry assetEntry WHERE ";
	private static final String _SQL_COUNT_ASSETENTRY = "SELECT COUNT(assetEntry) FROM AssetEntry assetEntry";
	private static final String _SQL_COUNT_ASSETENTRY_WHERE = "SELECT COUNT(assetEntry) FROM AssetEntry assetEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(AssetEntryPersistenceImpl.class);
	private static AssetEntry _nullAssetEntry = new AssetEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetEntry> toCacheModel() {
				return _nullAssetEntryCacheModel;
			}
		};

	private static CacheModel<AssetEntry> _nullAssetEntryCacheModel = new CacheModel<AssetEntry>() {
			@Override
			public AssetEntry toEntityModel() {
				return _nullAssetEntry;
			}
		};
}