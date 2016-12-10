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

package com.liferay.portlet.softwarecatalog.service.persistence;

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

import com.liferay.portlet.softwarecatalog.NoSuchProductVersionException;
import com.liferay.portlet.softwarecatalog.model.SCProductVersion;
import com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionImpl;
import com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the s c product version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SCProductVersionPersistence
 * @see SCProductVersionUtil
 * @generated
 */
public class SCProductVersionPersistenceImpl extends BasePersistenceImpl<SCProductVersion>
	implements SCProductVersionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SCProductVersionUtil} to access the s c product version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SCProductVersionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
			SCProductVersionModelImpl.FINDER_CACHE_ENABLED,
			SCProductVersionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
			SCProductVersionModelImpl.FINDER_CACHE_ENABLED,
			SCProductVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
			SCProductVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRODUCTENTRYID =
		new FinderPath(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
			SCProductVersionModelImpl.FINDER_CACHE_ENABLED,
			SCProductVersionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByProductEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID =
		new FinderPath(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
			SCProductVersionModelImpl.FINDER_CACHE_ENABLED,
			SCProductVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProductEntryId",
			new String[] { Long.class.getName() },
			SCProductVersionModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			SCProductVersionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRODUCTENTRYID = new FinderPath(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
			SCProductVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the s c product versions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching s c product versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SCProductVersion> findByProductEntryId(long productEntryId)
		throws SystemException {
		return findByProductEntryId(productEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the s c product versions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of s c product versions
	 * @param end the upper bound of the range of s c product versions (not inclusive)
	 * @return the range of matching s c product versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SCProductVersion> findByProductEntryId(long productEntryId,
		int start, int end) throws SystemException {
		return findByProductEntryId(productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the s c product versions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of s c product versions
	 * @param end the upper bound of the range of s c product versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching s c product versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SCProductVersion> findByProductEntryId(long productEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID;
			finderArgs = new Object[] { productEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRODUCTENTRYID;
			finderArgs = new Object[] {
					productEntryId,
					
					start, end, orderByComparator
				};
		}

		List<SCProductVersion> list = (List<SCProductVersion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SCProductVersion scProductVersion : list) {
				if ((productEntryId != scProductVersion.getProductEntryId())) {
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

			query.append(_SQL_SELECT_SCPRODUCTVERSION_WHERE);

			query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SCProductVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				if (!pagination) {
					list = (List<SCProductVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SCProductVersion>(list);
				}
				else {
					list = (List<SCProductVersion>)QueryUtil.list(q,
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
	 * Returns the first s c product version in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s c product version
	 * @throws com.liferay.portlet.softwarecatalog.NoSuchProductVersionException if a matching s c product version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion findByProductEntryId_First(long productEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchProductVersionException, SystemException {
		SCProductVersion scProductVersion = fetchByProductEntryId_First(productEntryId,
				orderByComparator);

		if (scProductVersion != null) {
			return scProductVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProductVersionException(msg.toString());
	}

	/**
	 * Returns the first s c product version in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s c product version, or <code>null</code> if a matching s c product version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion fetchByProductEntryId_First(long productEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SCProductVersion> list = findByProductEntryId(productEntryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last s c product version in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s c product version
	 * @throws com.liferay.portlet.softwarecatalog.NoSuchProductVersionException if a matching s c product version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion findByProductEntryId_Last(long productEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchProductVersionException, SystemException {
		SCProductVersion scProductVersion = fetchByProductEntryId_Last(productEntryId,
				orderByComparator);

		if (scProductVersion != null) {
			return scProductVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProductVersionException(msg.toString());
	}

	/**
	 * Returns the last s c product version in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s c product version, or <code>null</code> if a matching s c product version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion fetchByProductEntryId_Last(long productEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByProductEntryId(productEntryId);

		if (count == 0) {
			return null;
		}

		List<SCProductVersion> list = findByProductEntryId(productEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the s c product versions before and after the current s c product version in the ordered set where productEntryId = &#63;.
	 *
	 * @param productVersionId the primary key of the current s c product version
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next s c product version
	 * @throws com.liferay.portlet.softwarecatalog.NoSuchProductVersionException if a s c product version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion[] findByProductEntryId_PrevAndNext(
		long productVersionId, long productEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchProductVersionException, SystemException {
		SCProductVersion scProductVersion = findByPrimaryKey(productVersionId);

		Session session = null;

		try {
			session = openSession();

			SCProductVersion[] array = new SCProductVersionImpl[3];

			array[0] = getByProductEntryId_PrevAndNext(session,
					scProductVersion, productEntryId, orderByComparator, true);

			array[1] = scProductVersion;

			array[2] = getByProductEntryId_PrevAndNext(session,
					scProductVersion, productEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SCProductVersion getByProductEntryId_PrevAndNext(
		Session session, SCProductVersion scProductVersion,
		long productEntryId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCPRODUCTVERSION_WHERE);

		query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

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
			query.append(SCProductVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(productEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scProductVersion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SCProductVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the s c product versions where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByProductEntryId(long productEntryId)
		throws SystemException {
		for (SCProductVersion scProductVersion : findByProductEntryId(
				productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scProductVersion);
		}
	}

	/**
	 * Returns the number of s c product versions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching s c product versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByProductEntryId(long productEntryId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRODUCTENTRYID;

		Object[] finderArgs = new Object[] { productEntryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCPRODUCTVERSION_WHERE);

			query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

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

	private static final String _FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2 = "scProductVersion.productEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL = new FinderPath(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
			SCProductVersionModelImpl.FINDER_CACHE_ENABLED,
			SCProductVersionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByDirectDownloadURL",
			new String[] { String.class.getName() },
			SCProductVersionModelImpl.DIRECTDOWNLOADURL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DIRECTDOWNLOADURL = new FinderPath(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
			SCProductVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDirectDownloadURL", new String[] { String.class.getName() });

	/**
	 * Returns the s c product version where directDownloadURL = &#63; or throws a {@link com.liferay.portlet.softwarecatalog.NoSuchProductVersionException} if it could not be found.
	 *
	 * @param directDownloadURL the direct download u r l
	 * @return the matching s c product version
	 * @throws com.liferay.portlet.softwarecatalog.NoSuchProductVersionException if a matching s c product version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion findByDirectDownloadURL(String directDownloadURL)
		throws NoSuchProductVersionException, SystemException {
		SCProductVersion scProductVersion = fetchByDirectDownloadURL(directDownloadURL);

		if (scProductVersion == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("directDownloadURL=");
			msg.append(directDownloadURL);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchProductVersionException(msg.toString());
		}

		return scProductVersion;
	}

	/**
	 * Returns the s c product version where directDownloadURL = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param directDownloadURL the direct download u r l
	 * @return the matching s c product version, or <code>null</code> if a matching s c product version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion fetchByDirectDownloadURL(String directDownloadURL)
		throws SystemException {
		return fetchByDirectDownloadURL(directDownloadURL, true);
	}

	/**
	 * Returns the s c product version where directDownloadURL = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param directDownloadURL the direct download u r l
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching s c product version, or <code>null</code> if a matching s c product version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion fetchByDirectDownloadURL(String directDownloadURL,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { directDownloadURL };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL,
					finderArgs, this);
		}

		if (result instanceof SCProductVersion) {
			SCProductVersion scProductVersion = (SCProductVersion)result;

			if (!Validator.equals(directDownloadURL,
						scProductVersion.getDirectDownloadURL())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SCPRODUCTVERSION_WHERE);

			boolean bindDirectDownloadURL = false;

			if (directDownloadURL == null) {
				query.append(_FINDER_COLUMN_DIRECTDOWNLOADURL_DIRECTDOWNLOADURL_1);
			}
			else if (directDownloadURL.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DIRECTDOWNLOADURL_DIRECTDOWNLOADURL_3);
			}
			else {
				bindDirectDownloadURL = true;

				query.append(_FINDER_COLUMN_DIRECTDOWNLOADURL_DIRECTDOWNLOADURL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDirectDownloadURL) {
					qPos.add(directDownloadURL.toLowerCase());
				}

				List<SCProductVersion> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SCProductVersionPersistenceImpl.fetchByDirectDownloadURL(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					SCProductVersion scProductVersion = list.get(0);

					result = scProductVersion;

					cacheResult(scProductVersion);

					if ((scProductVersion.getDirectDownloadURL() == null) ||
							!scProductVersion.getDirectDownloadURL()
												 .equals(directDownloadURL)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL,
							finderArgs, scProductVersion);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL,
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
			return (SCProductVersion)result;
		}
	}

	/**
	 * Removes the s c product version where directDownloadURL = &#63; from the database.
	 *
	 * @param directDownloadURL the direct download u r l
	 * @return the s c product version that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion removeByDirectDownloadURL(String directDownloadURL)
		throws NoSuchProductVersionException, SystemException {
		SCProductVersion scProductVersion = findByDirectDownloadURL(directDownloadURL);

		return remove(scProductVersion);
	}

	/**
	 * Returns the number of s c product versions where directDownloadURL = &#63;.
	 *
	 * @param directDownloadURL the direct download u r l
	 * @return the number of matching s c product versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByDirectDownloadURL(String directDownloadURL)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DIRECTDOWNLOADURL;

		Object[] finderArgs = new Object[] { directDownloadURL };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCPRODUCTVERSION_WHERE);

			boolean bindDirectDownloadURL = false;

			if (directDownloadURL == null) {
				query.append(_FINDER_COLUMN_DIRECTDOWNLOADURL_DIRECTDOWNLOADURL_1);
			}
			else if (directDownloadURL.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DIRECTDOWNLOADURL_DIRECTDOWNLOADURL_3);
			}
			else {
				bindDirectDownloadURL = true;

				query.append(_FINDER_COLUMN_DIRECTDOWNLOADURL_DIRECTDOWNLOADURL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDirectDownloadURL) {
					qPos.add(directDownloadURL.toLowerCase());
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

	private static final String _FINDER_COLUMN_DIRECTDOWNLOADURL_DIRECTDOWNLOADURL_1 =
		"scProductVersion.directDownloadURL IS NULL";
	private static final String _FINDER_COLUMN_DIRECTDOWNLOADURL_DIRECTDOWNLOADURL_2 =
		"lower(scProductVersion.directDownloadURL) = ?";
	private static final String _FINDER_COLUMN_DIRECTDOWNLOADURL_DIRECTDOWNLOADURL_3 =
		"(scProductVersion.directDownloadURL IS NULL OR scProductVersion.directDownloadURL = '')";

	public SCProductVersionPersistenceImpl() {
		setModelClass(SCProductVersion.class);
	}

	/**
	 * Caches the s c product version in the entity cache if it is enabled.
	 *
	 * @param scProductVersion the s c product version
	 */
	@Override
	public void cacheResult(SCProductVersion scProductVersion) {
		EntityCacheUtil.putResult(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
			SCProductVersionImpl.class, scProductVersion.getPrimaryKey(),
			scProductVersion);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL,
			new Object[] { scProductVersion.getDirectDownloadURL() },
			scProductVersion);

		scProductVersion.resetOriginalValues();
	}

	/**
	 * Caches the s c product versions in the entity cache if it is enabled.
	 *
	 * @param scProductVersions the s c product versions
	 */
	@Override
	public void cacheResult(List<SCProductVersion> scProductVersions) {
		for (SCProductVersion scProductVersion : scProductVersions) {
			if (EntityCacheUtil.getResult(
						SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
						SCProductVersionImpl.class,
						scProductVersion.getPrimaryKey()) == null) {
				cacheResult(scProductVersion);
			}
			else {
				scProductVersion.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all s c product versions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SCProductVersionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SCProductVersionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the s c product version.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SCProductVersion scProductVersion) {
		EntityCacheUtil.removeResult(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
			SCProductVersionImpl.class, scProductVersion.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(scProductVersion);
	}

	@Override
	public void clearCache(List<SCProductVersion> scProductVersions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SCProductVersion scProductVersion : scProductVersions) {
			EntityCacheUtil.removeResult(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
				SCProductVersionImpl.class, scProductVersion.getPrimaryKey());

			clearUniqueFindersCache(scProductVersion);
		}
	}

	protected void cacheUniqueFindersCache(SCProductVersion scProductVersion) {
		if (scProductVersion.isNew()) {
			Object[] args = new Object[] { scProductVersion.getDirectDownloadURL() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DIRECTDOWNLOADURL,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL,
				args, scProductVersion);
		}
		else {
			SCProductVersionModelImpl scProductVersionModelImpl = (SCProductVersionModelImpl)scProductVersion;

			if ((scProductVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scProductVersion.getDirectDownloadURL()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DIRECTDOWNLOADURL,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL,
					args, scProductVersion);
			}
		}
	}

	protected void clearUniqueFindersCache(SCProductVersion scProductVersion) {
		SCProductVersionModelImpl scProductVersionModelImpl = (SCProductVersionModelImpl)scProductVersion;

		Object[] args = new Object[] { scProductVersion.getDirectDownloadURL() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DIRECTDOWNLOADURL,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL,
			args);

		if ((scProductVersionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL.getColumnBitmask()) != 0) {
			args = new Object[] {
					scProductVersionModelImpl.getOriginalDirectDownloadURL()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DIRECTDOWNLOADURL,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DIRECTDOWNLOADURL,
				args);
		}
	}

	/**
	 * Creates a new s c product version with the primary key. Does not add the s c product version to the database.
	 *
	 * @param productVersionId the primary key for the new s c product version
	 * @return the new s c product version
	 */
	@Override
	public SCProductVersion create(long productVersionId) {
		SCProductVersion scProductVersion = new SCProductVersionImpl();

		scProductVersion.setNew(true);
		scProductVersion.setPrimaryKey(productVersionId);

		return scProductVersion;
	}

	/**
	 * Removes the s c product version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productVersionId the primary key of the s c product version
	 * @return the s c product version that was removed
	 * @throws com.liferay.portlet.softwarecatalog.NoSuchProductVersionException if a s c product version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion remove(long productVersionId)
		throws NoSuchProductVersionException, SystemException {
		return remove((Serializable)productVersionId);
	}

	/**
	 * Removes the s c product version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the s c product version
	 * @return the s c product version that was removed
	 * @throws com.liferay.portlet.softwarecatalog.NoSuchProductVersionException if a s c product version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion remove(Serializable primaryKey)
		throws NoSuchProductVersionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SCProductVersion scProductVersion = (SCProductVersion)session.get(SCProductVersionImpl.class,
					primaryKey);

			if (scProductVersion == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProductVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(scProductVersion);
		}
		catch (NoSuchProductVersionException nsee) {
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
	protected SCProductVersion removeImpl(SCProductVersion scProductVersion)
		throws SystemException {
		scProductVersion = toUnwrappedModel(scProductVersion);

		scProductVersionToSCFrameworkVersionTableMapper.deleteLeftPrimaryKeyTableMappings(scProductVersion.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scProductVersion)) {
				scProductVersion = (SCProductVersion)session.get(SCProductVersionImpl.class,
						scProductVersion.getPrimaryKeyObj());
			}

			if (scProductVersion != null) {
				session.delete(scProductVersion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (scProductVersion != null) {
			clearCache(scProductVersion);
		}

		return scProductVersion;
	}

	@Override
	public SCProductVersion updateImpl(
		com.liferay.portlet.softwarecatalog.model.SCProductVersion scProductVersion)
		throws SystemException {
		scProductVersion = toUnwrappedModel(scProductVersion);

		boolean isNew = scProductVersion.isNew();

		SCProductVersionModelImpl scProductVersionModelImpl = (SCProductVersionModelImpl)scProductVersion;

		Session session = null;

		try {
			session = openSession();

			if (scProductVersion.isNew()) {
				session.save(scProductVersion);

				scProductVersion.setNew(false);
			}
			else {
				session.merge(scProductVersion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SCProductVersionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((scProductVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scProductVersionModelImpl.getOriginalProductEntryId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
					args);

				args = new Object[] {
						scProductVersionModelImpl.getProductEntryId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
			SCProductVersionImpl.class, scProductVersion.getPrimaryKey(),
			scProductVersion);

		clearUniqueFindersCache(scProductVersion);
		cacheUniqueFindersCache(scProductVersion);

		return scProductVersion;
	}

	protected SCProductVersion toUnwrappedModel(
		SCProductVersion scProductVersion) {
		if (scProductVersion instanceof SCProductVersionImpl) {
			return scProductVersion;
		}

		SCProductVersionImpl scProductVersionImpl = new SCProductVersionImpl();

		scProductVersionImpl.setNew(scProductVersion.isNew());
		scProductVersionImpl.setPrimaryKey(scProductVersion.getPrimaryKey());

		scProductVersionImpl.setProductVersionId(scProductVersion.getProductVersionId());
		scProductVersionImpl.setCompanyId(scProductVersion.getCompanyId());
		scProductVersionImpl.setUserId(scProductVersion.getUserId());
		scProductVersionImpl.setUserName(scProductVersion.getUserName());
		scProductVersionImpl.setCreateDate(scProductVersion.getCreateDate());
		scProductVersionImpl.setModifiedDate(scProductVersion.getModifiedDate());
		scProductVersionImpl.setProductEntryId(scProductVersion.getProductEntryId());
		scProductVersionImpl.setVersion(scProductVersion.getVersion());
		scProductVersionImpl.setChangeLog(scProductVersion.getChangeLog());
		scProductVersionImpl.setDownloadPageURL(scProductVersion.getDownloadPageURL());
		scProductVersionImpl.setDirectDownloadURL(scProductVersion.getDirectDownloadURL());
		scProductVersionImpl.setRepoStoreArtifact(scProductVersion.isRepoStoreArtifact());

		return scProductVersionImpl;
	}

	/**
	 * Returns the s c product version with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the s c product version
	 * @return the s c product version
	 * @throws com.liferay.portlet.softwarecatalog.NoSuchProductVersionException if a s c product version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProductVersionException, SystemException {
		SCProductVersion scProductVersion = fetchByPrimaryKey(primaryKey);

		if (scProductVersion == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProductVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return scProductVersion;
	}

	/**
	 * Returns the s c product version with the primary key or throws a {@link com.liferay.portlet.softwarecatalog.NoSuchProductVersionException} if it could not be found.
	 *
	 * @param productVersionId the primary key of the s c product version
	 * @return the s c product version
	 * @throws com.liferay.portlet.softwarecatalog.NoSuchProductVersionException if a s c product version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion findByPrimaryKey(long productVersionId)
		throws NoSuchProductVersionException, SystemException {
		return findByPrimaryKey((Serializable)productVersionId);
	}

	/**
	 * Returns the s c product version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the s c product version
	 * @return the s c product version, or <code>null</code> if a s c product version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SCProductVersion scProductVersion = (SCProductVersion)EntityCacheUtil.getResult(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
				SCProductVersionImpl.class, primaryKey);

		if (scProductVersion == _nullSCProductVersion) {
			return null;
		}

		if (scProductVersion == null) {
			Session session = null;

			try {
				session = openSession();

				scProductVersion = (SCProductVersion)session.get(SCProductVersionImpl.class,
						primaryKey);

				if (scProductVersion != null) {
					cacheResult(scProductVersion);
				}
				else {
					EntityCacheUtil.putResult(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
						SCProductVersionImpl.class, primaryKey,
						_nullSCProductVersion);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SCProductVersionModelImpl.ENTITY_CACHE_ENABLED,
					SCProductVersionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return scProductVersion;
	}

	/**
	 * Returns the s c product version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productVersionId the primary key of the s c product version
	 * @return the s c product version, or <code>null</code> if a s c product version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCProductVersion fetchByPrimaryKey(long productVersionId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)productVersionId);
	}

	/**
	 * Returns all the s c product versions.
	 *
	 * @return the s c product versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SCProductVersion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the s c product versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s c product versions
	 * @param end the upper bound of the range of s c product versions (not inclusive)
	 * @return the range of s c product versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SCProductVersion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the s c product versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s c product versions
	 * @param end the upper bound of the range of s c product versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of s c product versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SCProductVersion> findAll(int start, int end,
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

		List<SCProductVersion> list = (List<SCProductVersion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SCPRODUCTVERSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCPRODUCTVERSION;

				if (pagination) {
					sql = sql.concat(SCProductVersionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SCProductVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SCProductVersion>(list);
				}
				else {
					list = (List<SCProductVersion>)QueryUtil.list(q,
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
	 * Removes all the s c product versions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SCProductVersion scProductVersion : findAll()) {
			remove(scProductVersion);
		}
	}

	/**
	 * Returns the number of s c product versions.
	 *
	 * @return the number of s c product versions
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

				Query q = session.createQuery(_SQL_COUNT_SCPRODUCTVERSION);

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
	 * Returns all the s c framework versions associated with the s c product version.
	 *
	 * @param pk the primary key of the s c product version
	 * @return the s c framework versions associated with the s c product version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> getSCFrameworkVersions(
		long pk) throws SystemException {
		return getSCFrameworkVersions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the s c framework versions associated with the s c product version.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the s c product version
	 * @param start the lower bound of the range of s c product versions
	 * @param end the upper bound of the range of s c product versions (not inclusive)
	 * @return the range of s c framework versions associated with the s c product version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> getSCFrameworkVersions(
		long pk, int start, int end) throws SystemException {
		return getSCFrameworkVersions(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the s c framework versions associated with the s c product version.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the s c product version
	 * @param start the lower bound of the range of s c product versions
	 * @param end the upper bound of the range of s c product versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of s c framework versions associated with the s c product version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> getSCFrameworkVersions(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return scProductVersionToSCFrameworkVersionTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of s c framework versions associated with the s c product version.
	 *
	 * @param pk the primary key of the s c product version
	 * @return the number of s c framework versions associated with the s c product version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getSCFrameworkVersionsSize(long pk) throws SystemException {
		long[] pks = scProductVersionToSCFrameworkVersionTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the s c framework version is associated with the s c product version.
	 *
	 * @param pk the primary key of the s c product version
	 * @param scFrameworkVersionPK the primary key of the s c framework version
	 * @return <code>true</code> if the s c framework version is associated with the s c product version; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsSCFrameworkVersion(long pk, long scFrameworkVersionPK)
		throws SystemException {
		return scProductVersionToSCFrameworkVersionTableMapper.containsTableMapping(pk,
			scFrameworkVersionPK);
	}

	/**
	 * Returns <code>true</code> if the s c product version has any s c framework versions associated with it.
	 *
	 * @param pk the primary key of the s c product version to check for associations with s c framework versions
	 * @return <code>true</code> if the s c product version has any s c framework versions associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsSCFrameworkVersions(long pk)
		throws SystemException {
		if (getSCFrameworkVersionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the s c product version and the s c framework version. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the s c product version
	 * @param scFrameworkVersionPK the primary key of the s c framework version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addSCFrameworkVersion(long pk, long scFrameworkVersionPK)
		throws SystemException {
		scProductVersionToSCFrameworkVersionTableMapper.addTableMapping(pk,
			scFrameworkVersionPK);
	}

	/**
	 * Adds an association between the s c product version and the s c framework version. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the s c product version
	 * @param scFrameworkVersion the s c framework version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addSCFrameworkVersion(long pk,
		com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion scFrameworkVersion)
		throws SystemException {
		scProductVersionToSCFrameworkVersionTableMapper.addTableMapping(pk,
			scFrameworkVersion.getPrimaryKey());
	}

	/**
	 * Adds an association between the s c product version and the s c framework versions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the s c product version
	 * @param scFrameworkVersionPKs the primary keys of the s c framework versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addSCFrameworkVersions(long pk, long[] scFrameworkVersionPKs)
		throws SystemException {
		for (long scFrameworkVersionPK : scFrameworkVersionPKs) {
			scProductVersionToSCFrameworkVersionTableMapper.addTableMapping(pk,
				scFrameworkVersionPK);
		}
	}

	/**
	 * Adds an association between the s c product version and the s c framework versions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the s c product version
	 * @param scFrameworkVersions the s c framework versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addSCFrameworkVersions(long pk,
		List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> scFrameworkVersions)
		throws SystemException {
		for (com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion scFrameworkVersion : scFrameworkVersions) {
			scProductVersionToSCFrameworkVersionTableMapper.addTableMapping(pk,
				scFrameworkVersion.getPrimaryKey());
		}
	}

	/**
	 * Clears all associations between the s c product version and its s c framework versions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the s c product version to clear the associated s c framework versions from
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearSCFrameworkVersions(long pk) throws SystemException {
		scProductVersionToSCFrameworkVersionTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the s c product version and the s c framework version. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the s c product version
	 * @param scFrameworkVersionPK the primary key of the s c framework version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeSCFrameworkVersion(long pk, long scFrameworkVersionPK)
		throws SystemException {
		scProductVersionToSCFrameworkVersionTableMapper.deleteTableMapping(pk,
			scFrameworkVersionPK);
	}

	/**
	 * Removes the association between the s c product version and the s c framework version. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the s c product version
	 * @param scFrameworkVersion the s c framework version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeSCFrameworkVersion(long pk,
		com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion scFrameworkVersion)
		throws SystemException {
		scProductVersionToSCFrameworkVersionTableMapper.deleteTableMapping(pk,
			scFrameworkVersion.getPrimaryKey());
	}

	/**
	 * Removes the association between the s c product version and the s c framework versions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the s c product version
	 * @param scFrameworkVersionPKs the primary keys of the s c framework versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeSCFrameworkVersions(long pk, long[] scFrameworkVersionPKs)
		throws SystemException {
		for (long scFrameworkVersionPK : scFrameworkVersionPKs) {
			scProductVersionToSCFrameworkVersionTableMapper.deleteTableMapping(pk,
				scFrameworkVersionPK);
		}
	}

	/**
	 * Removes the association between the s c product version and the s c framework versions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the s c product version
	 * @param scFrameworkVersions the s c framework versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeSCFrameworkVersions(long pk,
		List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> scFrameworkVersions)
		throws SystemException {
		for (com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion scFrameworkVersion : scFrameworkVersions) {
			scProductVersionToSCFrameworkVersionTableMapper.deleteTableMapping(pk,
				scFrameworkVersion.getPrimaryKey());
		}
	}

	/**
	 * Sets the s c framework versions associated with the s c product version, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the s c product version
	 * @param scFrameworkVersionPKs the primary keys of the s c framework versions to be associated with the s c product version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setSCFrameworkVersions(long pk, long[] scFrameworkVersionPKs)
		throws SystemException {
		Set<Long> newSCFrameworkVersionPKsSet = SetUtil.fromArray(scFrameworkVersionPKs);
		Set<Long> oldSCFrameworkVersionPKsSet = SetUtil.fromArray(scProductVersionToSCFrameworkVersionTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeSCFrameworkVersionPKsSet = new HashSet<Long>(oldSCFrameworkVersionPKsSet);

		removeSCFrameworkVersionPKsSet.removeAll(newSCFrameworkVersionPKsSet);

		for (long removeSCFrameworkVersionPK : removeSCFrameworkVersionPKsSet) {
			scProductVersionToSCFrameworkVersionTableMapper.deleteTableMapping(pk,
				removeSCFrameworkVersionPK);
		}

		newSCFrameworkVersionPKsSet.removeAll(oldSCFrameworkVersionPKsSet);

		for (long newSCFrameworkVersionPK : newSCFrameworkVersionPKsSet) {
			scProductVersionToSCFrameworkVersionTableMapper.addTableMapping(pk,
				newSCFrameworkVersionPK);
		}
	}

	/**
	 * Sets the s c framework versions associated with the s c product version, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the s c product version
	 * @param scFrameworkVersions the s c framework versions to be associated with the s c product version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setSCFrameworkVersions(long pk,
		List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> scFrameworkVersions)
		throws SystemException {
		try {
			long[] scFrameworkVersionPKs = new long[scFrameworkVersions.size()];

			for (int i = 0; i < scFrameworkVersions.size(); i++) {
				com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion scFrameworkVersion =
					scFrameworkVersions.get(i);

				scFrameworkVersionPKs[i] = scFrameworkVersion.getPrimaryKey();
			}

			setSCFrameworkVersions(pk, scFrameworkVersionPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SCProductVersionModelImpl.MAPPING_TABLE_SCFRAMEWORKVERSI_SCPRODUCTVERS_NAME);
		}
	}

	/**
	 * Initializes the s c product version persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.softwarecatalog.model.SCProductVersion")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SCProductVersion>> listenersList = new ArrayList<ModelListener<SCProductVersion>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SCProductVersion>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		scProductVersionToSCFrameworkVersionTableMapper = TableMapperFactory.getTableMapper("SCFrameworkVersi_SCProductVers",
				"productVersionId", "frameworkVersionId", this,
				scFrameworkVersionPersistence);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SCProductVersionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("SCFrameworkVersi_SCProductVers");
	}

	@BeanReference(type = SCFrameworkVersionPersistence.class)
	protected SCFrameworkVersionPersistence scFrameworkVersionPersistence;
	protected TableMapper<SCProductVersion, com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> scProductVersionToSCFrameworkVersionTableMapper;
	private static final String _SQL_SELECT_SCPRODUCTVERSION = "SELECT scProductVersion FROM SCProductVersion scProductVersion";
	private static final String _SQL_SELECT_SCPRODUCTVERSION_WHERE = "SELECT scProductVersion FROM SCProductVersion scProductVersion WHERE ";
	private static final String _SQL_COUNT_SCPRODUCTVERSION = "SELECT COUNT(scProductVersion) FROM SCProductVersion scProductVersion";
	private static final String _SQL_COUNT_SCPRODUCTVERSION_WHERE = "SELECT COUNT(scProductVersion) FROM SCProductVersion scProductVersion WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "scProductVersion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SCProductVersion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SCProductVersion exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(SCProductVersionPersistenceImpl.class);
	private static SCProductVersion _nullSCProductVersion = new SCProductVersionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SCProductVersion> toCacheModel() {
				return _nullSCProductVersionCacheModel;
			}
		};

	private static CacheModel<SCProductVersion> _nullSCProductVersionCacheModel = new CacheModel<SCProductVersion>() {
			@Override
			public SCProductVersion toEntityModel() {
				return _nullSCProductVersion;
			}
		};
}