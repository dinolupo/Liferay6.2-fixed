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

import com.liferay.portal.NoSuchOrganizationException;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.util.CharPool;
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
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.impl.OrganizationImpl;
import com.liferay.portal.model.impl.OrganizationModelImpl;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.service.persistence.impl.TableMapper;
import com.liferay.portal.service.persistence.impl.TableMapperFactory;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the organization service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrganizationPersistence
 * @see OrganizationUtil
 * @generated
 */
public class OrganizationPersistenceImpl extends BasePersistenceImpl<Organization>
	implements OrganizationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OrganizationUtil} to access the organization persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OrganizationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OrganizationModelImpl.UUID_COLUMN_BITMASK |
			OrganizationModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the organizations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByUuid(String uuid, int start, int end,
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

		List<Organization> list = (List<Organization>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Organization organization : list) {
				if (!Validator.equals(uuid, organization.getUuid())) {
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

			query.append(_SQL_SELECT_ORGANIZATION_WHERE);

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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
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
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Organization>(list);
				}
				else {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first organization in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByUuid_First(uuid, orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the first organization in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Organization> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last organization in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByUuid_Last(uuid, orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the last organization in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Organization> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the organizations before and after the current organization in the ordered set where uuid = &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] findByUuid_PrevAndNext(long organizationId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = getByUuid_PrevAndNext(session, organization, uuid,
					orderByComparator, true);

			array[1] = organization;

			array[2] = getByUuid_PrevAndNext(session, organization, uuid,
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

	protected Organization getByUuid_PrevAndNext(Session session,
		Organization organization, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ORGANIZATION_WHERE);

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
			query.append(OrganizationModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the organizations that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByUuid(String uuid)
		throws SystemException {
		return filterFindByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByUuid(String uuid, int start, int end)
		throws SystemException {
		return filterFindByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid(uuid, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1_SQL);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			return (List<Organization>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the organizations before and after the current organization in the ordered set of organizations that the user has permission to view where uuid = &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] filterFindByUuid_PrevAndNext(long organizationId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(organizationId, uuid,
				orderByComparator);
		}

		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(session, organization, uuid,
					orderByComparator, true);

			array[1] = organization;

			array[2] = filterGetByUuid_PrevAndNext(session, organization, uuid,
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

	protected Organization filterGetByUuid_PrevAndNext(Session session,
		Organization organization, String uuid,
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
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1_SQL);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the organizations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (Organization organization : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(organization);
		}
	}

	/**
	 * Returns the number of organizations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching organizations
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

			query.append(_SQL_COUNT_ORGANIZATION_WHERE);

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

	/**
	 * Returns the number of organizations that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByUuid(String uuid) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_ORGANIZATION_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1_SQL);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "organization.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "organization.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(organization.uuid IS NULL OR organization.uuid = '')";
	private static final String _FINDER_COLUMN_UUID_UUID_1_SQL = "organization.uuid_ IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2_SQL = "organization.uuid_ = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3_SQL = "(organization.uuid_ IS NULL OR organization.uuid_ = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			OrganizationModelImpl.UUID_COLUMN_BITMASK |
			OrganizationModelImpl.COMPANYID_COLUMN_BITMASK |
			OrganizationModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the organizations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByUuid_C(String uuid, long companyId,
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

		List<Organization> list = (List<Organization>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Organization organization : list) {
				if (!Validator.equals(uuid, organization.getUuid()) ||
						(companyId != organization.getCompanyId())) {
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

			query.append(_SQL_SELECT_ORGANIZATION_WHERE);

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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
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
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Organization>(list);
				}
				else {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first organization in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the first organization in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Organization> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last organization in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the last organization in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Organization> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the organizations before and after the current organization in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] findByUuid_C_PrevAndNext(long organizationId,
		String uuid, long companyId, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, organization, uuid,
					companyId, orderByComparator, true);

			array[1] = organization;

			array[2] = getByUuid_C_PrevAndNext(session, organization, uuid,
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

	protected Organization getByUuid_C_PrevAndNext(Session session,
		Organization organization, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ORGANIZATION_WHERE);

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
			query.append(OrganizationModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the organizations that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByUuid_C(String uuid, long companyId)
		throws SystemException {
		return filterFindByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return filterFindByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_C(uuid, companyId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1_SQL);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			qPos.add(companyId);

			return (List<Organization>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the organizations before and after the current organization in the ordered set of organizations that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] filterFindByUuid_C_PrevAndNext(long organizationId,
		String uuid, long companyId, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_C_PrevAndNext(organizationId, uuid, companyId,
				orderByComparator);
		}

		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = filterGetByUuid_C_PrevAndNext(session, organization,
					uuid, companyId, orderByComparator, true);

			array[1] = organization;

			array[2] = filterGetByUuid_C_PrevAndNext(session, organization,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Organization filterGetByUuid_C_PrevAndNext(Session session,
		Organization organization, String uuid, long companyId,
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
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1_SQL);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the organizations where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (Organization organization : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(organization);
		}
	}

	/**
	 * Returns the number of organizations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching organizations
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

			query.append(_SQL_COUNT_ORGANIZATION_WHERE);

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

	/**
	 * Returns the number of organizations that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByUuid_C(String uuid, long companyId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid_C(uuid, companyId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_ORGANIZATION_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1_SQL);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "organization.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "organization.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(organization.uuid IS NULL OR organization.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_1_SQL = "organization.uuid_ IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2_SQL = "organization.uuid_ = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3_SQL = "(organization.uuid_ IS NULL OR organization.uuid_ = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "organization.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			OrganizationModelImpl.COMPANYID_COLUMN_BITMASK |
			OrganizationModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the organizations where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the organizations where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByCompanyId(long companyId, int start,
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

		List<Organization> list = (List<Organization>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Organization organization : list) {
				if ((companyId != organization.getCompanyId())) {
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

			query.append(_SQL_SELECT_ORGANIZATION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Organization>(list);
				}
				else {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first organization in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the first organization in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Organization> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last organization in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the last organization in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<Organization> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the organizations before and after the current organization in the ordered set where companyId = &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] findByCompanyId_PrevAndNext(long organizationId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, organization,
					companyId, orderByComparator, true);

			array[1] = organization;

			array[2] = getByCompanyId_PrevAndNext(session, organization,
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

	protected Organization getByCompanyId_PrevAndNext(Session session,
		Organization organization, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ORGANIZATION_WHERE);

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
			query.append(OrganizationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the organizations that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByCompanyId(long companyId)
		throws SystemException {
		return filterFindByCompanyId(companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId(companyId, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<Organization>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the organizations before and after the current organization in the ordered set of organizations that the user has permission to view where companyId = &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] filterFindByCompanyId_PrevAndNext(
		long organizationId, long companyId, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId_PrevAndNext(organizationId, companyId,
				orderByComparator);
		}

		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(session, organization,
					companyId, orderByComparator, true);

			array[1] = organization;

			array[2] = filterGetByCompanyId_PrevAndNext(session, organization,
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

	protected Organization filterGetByCompanyId_PrevAndNext(Session session,
		Organization organization, long companyId,
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
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the organizations where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCompanyId(long companyId) throws SystemException {
		for (Organization organization : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(organization);
		}
	}

	/**
	 * Returns the number of organizations where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching organizations
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

			query.append(_SQL_COUNT_ORGANIZATION_WHERE);

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

	/**
	 * Returns the number of organizations that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByCompanyId(long companyId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_ORGANIZATION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "organization.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LOCATIONS =
		new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLocations",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOCATIONS =
		new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLocations",
			new String[] { Long.class.getName() },
			OrganizationModelImpl.COMPANYID_COLUMN_BITMASK |
			OrganizationModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LOCATIONS = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLocations",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the organizations where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByLocations(long companyId)
		throws SystemException {
		return findByLocations(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the organizations where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByLocations(long companyId, int start, int end)
		throws SystemException {
		return findByLocations(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByLocations(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOCATIONS;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LOCATIONS;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<Organization> list = (List<Organization>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Organization organization : list) {
				if ((companyId != organization.getCompanyId())) {
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

			query.append(_SQL_SELECT_ORGANIZATION_WHERE);

			query.append(_FINDER_COLUMN_LOCATIONS_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Organization>(list);
				}
				else {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first organization in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByLocations_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByLocations_First(companyId,
				orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the first organization in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByLocations_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Organization> list = findByLocations(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last organization in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByLocations_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByLocations_Last(companyId,
				orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the last organization in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByLocations_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByLocations(companyId);

		if (count == 0) {
			return null;
		}

		List<Organization> list = findByLocations(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the organizations before and after the current organization in the ordered set where companyId = &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] findByLocations_PrevAndNext(long organizationId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = getByLocations_PrevAndNext(session, organization,
					companyId, orderByComparator, true);

			array[1] = organization;

			array[2] = getByLocations_PrevAndNext(session, organization,
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

	protected Organization getByLocations_PrevAndNext(Session session,
		Organization organization, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ORGANIZATION_WHERE);

		query.append(_FINDER_COLUMN_LOCATIONS_COMPANYID_2);

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
			query.append(OrganizationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the organizations that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByLocations(long companyId)
		throws SystemException {
		return filterFindByLocations(companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByLocations(long companyId, int start,
		int end) throws SystemException {
		return filterFindByLocations(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByLocations(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByLocations(companyId, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_LOCATIONS_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<Organization>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the organizations before and after the current organization in the ordered set of organizations that the user has permission to view where companyId = &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] filterFindByLocations_PrevAndNext(
		long organizationId, long companyId, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByLocations_PrevAndNext(organizationId, companyId,
				orderByComparator);
		}

		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = filterGetByLocations_PrevAndNext(session, organization,
					companyId, orderByComparator, true);

			array[1] = organization;

			array[2] = filterGetByLocations_PrevAndNext(session, organization,
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

	protected Organization filterGetByLocations_PrevAndNext(Session session,
		Organization organization, long companyId,
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
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_LOCATIONS_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the organizations where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByLocations(long companyId) throws SystemException {
		for (Organization organization : findByLocations(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(organization);
		}
	}

	/**
	 * Returns the number of organizations where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByLocations(long companyId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LOCATIONS;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ORGANIZATION_WHERE);

			query.append(_FINDER_COLUMN_LOCATIONS_COMPANYID_2);

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

	/**
	 * Returns the number of organizations that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByLocations(long companyId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByLocations(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_ORGANIZATION_WHERE);

		query.append(_FINDER_COLUMN_LOCATIONS_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_LOCATIONS_COMPANYID_2 = "organization.companyId = ? AND organization.parentOrganizationId != 0";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_P = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_P",
			new String[] { Long.class.getName(), Long.class.getName() },
			OrganizationModelImpl.COMPANYID_COLUMN_BITMASK |
			OrganizationModelImpl.PARENTORGANIZATIONID_COLUMN_BITMASK |
			OrganizationModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_P = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_P",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the organizations where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @return the matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByC_P(long companyId,
		long parentOrganizationId) throws SystemException {
		return findByC_P(companyId, parentOrganizationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByC_P(long companyId,
		long parentOrganizationId, int start, int end)
		throws SystemException {
		return findByC_P(companyId, parentOrganizationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByC_P(long companyId,
		long parentOrganizationId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P;
			finderArgs = new Object[] { companyId, parentOrganizationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_P;
			finderArgs = new Object[] {
					companyId, parentOrganizationId,
					
					start, end, orderByComparator
				};
		}

		List<Organization> list = (List<Organization>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Organization organization : list) {
				if ((companyId != organization.getCompanyId()) ||
						(parentOrganizationId != organization.getParentOrganizationId())) {
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

			query.append(_SQL_SELECT_ORGANIZATION_WHERE);

			query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_P_PARENTORGANIZATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentOrganizationId);

				if (!pagination) {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Organization>(list);
				}
				else {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first organization in the ordered set where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByC_P_First(long companyId,
		long parentOrganizationId, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByC_P_First(companyId,
				parentOrganizationId, orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentOrganizationId=");
		msg.append(parentOrganizationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the first organization in the ordered set where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByC_P_First(long companyId,
		long parentOrganizationId, OrderByComparator orderByComparator)
		throws SystemException {
		List<Organization> list = findByC_P(companyId, parentOrganizationId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last organization in the ordered set where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByC_P_Last(long companyId,
		long parentOrganizationId, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByC_P_Last(companyId,
				parentOrganizationId, orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentOrganizationId=");
		msg.append(parentOrganizationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the last organization in the ordered set where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByC_P_Last(long companyId,
		long parentOrganizationId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_P(companyId, parentOrganizationId);

		if (count == 0) {
			return null;
		}

		List<Organization> list = findByC_P(companyId, parentOrganizationId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the organizations before and after the current organization in the ordered set where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] findByC_P_PrevAndNext(long organizationId,
		long companyId, long parentOrganizationId,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = getByC_P_PrevAndNext(session, organization, companyId,
					parentOrganizationId, orderByComparator, true);

			array[1] = organization;

			array[2] = getByC_P_PrevAndNext(session, organization, companyId,
					parentOrganizationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Organization getByC_P_PrevAndNext(Session session,
		Organization organization, long companyId, long parentOrganizationId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ORGANIZATION_WHERE);

		query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_P_PARENTORGANIZATIONID_2);

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
			query.append(OrganizationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(parentOrganizationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the organizations that the user has permission to view where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @return the matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByC_P(long companyId,
		long parentOrganizationId) throws SystemException {
		return filterFindByC_P(companyId, parentOrganizationId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations that the user has permission to view where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByC_P(long companyId,
		long parentOrganizationId, int start, int end)
		throws SystemException {
		return filterFindByC_P(companyId, parentOrganizationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations that the user has permissions to view where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByC_P(long companyId,
		long parentOrganizationId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_P(companyId, parentOrganizationId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_P_PARENTORGANIZATIONID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			qPos.add(parentOrganizationId);

			return (List<Organization>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the organizations before and after the current organization in the ordered set of organizations that the user has permission to view where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] filterFindByC_P_PrevAndNext(long organizationId,
		long companyId, long parentOrganizationId,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_P_PrevAndNext(organizationId, companyId,
				parentOrganizationId, orderByComparator);
		}

		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = filterGetByC_P_PrevAndNext(session, organization,
					companyId, parentOrganizationId, orderByComparator, true);

			array[1] = organization;

			array[2] = filterGetByC_P_PrevAndNext(session, organization,
					companyId, parentOrganizationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Organization filterGetByC_P_PrevAndNext(Session session,
		Organization organization, long companyId, long parentOrganizationId,
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
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_P_PARENTORGANIZATIONID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(parentOrganizationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the organizations where companyId = &#63; and parentOrganizationId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_P(long companyId, long parentOrganizationId)
		throws SystemException {
		for (Organization organization : findByC_P(companyId,
				parentOrganizationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(organization);
		}
	}

	/**
	 * Returns the number of organizations where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @return the number of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_P(long companyId, long parentOrganizationId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_P;

		Object[] finderArgs = new Object[] { companyId, parentOrganizationId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ORGANIZATION_WHERE);

			query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_P_PARENTORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentOrganizationId);

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
	 * Returns the number of organizations that the user has permission to view where companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @return the number of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByC_P(long companyId, long parentOrganizationId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByC_P(companyId, parentOrganizationId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_ORGANIZATION_WHERE);

		query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_P_PARENTORGANIZATIONID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			qPos.add(parentOrganizationId);

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

	private static final String _FINDER_COLUMN_C_P_COMPANYID_2 = "organization.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_P_PARENTORGANIZATIONID_2 = "organization.parentOrganizationId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_T = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_T = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_T",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the organizations where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @return the matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByC_T(long companyId, String treePath)
		throws SystemException {
		return findByC_T(companyId, treePath, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByC_T(long companyId, String treePath,
		int start, int end) throws SystemException {
		return findByC_T(companyId, treePath, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByC_T(long companyId, String treePath,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_T;
		finderArgs = new Object[] {
				companyId, treePath,
				
				start, end, orderByComparator
			};

		List<Organization> list = (List<Organization>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Organization organization : list) {
				if ((companyId != organization.getCompanyId()) ||
						!StringUtil.wildcardMatches(
							organization.getTreePath(), treePath,
							CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, true)) {
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

			query.append(_SQL_SELECT_ORGANIZATION_WHERE);

			query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

			boolean bindTreePath = false;

			if (treePath == null) {
				query.append(_FINDER_COLUMN_C_T_TREEPATH_1);
			}
			else if (treePath.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_T_TREEPATH_3);
			}
			else {
				bindTreePath = true;

				query.append(_FINDER_COLUMN_C_T_TREEPATH_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindTreePath) {
					qPos.add(treePath);
				}

				if (!pagination) {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Organization>(list);
				}
				else {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first organization in the ordered set where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByC_T_First(long companyId, String treePath,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByC_T_First(companyId, treePath,
				orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", treePath=");
		msg.append(treePath);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the first organization in the ordered set where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByC_T_First(long companyId, String treePath,
		OrderByComparator orderByComparator) throws SystemException {
		List<Organization> list = findByC_T(companyId, treePath, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last organization in the ordered set where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByC_T_Last(long companyId, String treePath,
		OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByC_T_Last(companyId, treePath,
				orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", treePath=");
		msg.append(treePath);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the last organization in the ordered set where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByC_T_Last(long companyId, String treePath,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_T(companyId, treePath);

		if (count == 0) {
			return null;
		}

		List<Organization> list = findByC_T(companyId, treePath, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the organizations before and after the current organization in the ordered set where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] findByC_T_PrevAndNext(long organizationId,
		long companyId, String treePath, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = getByC_T_PrevAndNext(session, organization, companyId,
					treePath, orderByComparator, true);

			array[1] = organization;

			array[2] = getByC_T_PrevAndNext(session, organization, companyId,
					treePath, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Organization getByC_T_PrevAndNext(Session session,
		Organization organization, long companyId, String treePath,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ORGANIZATION_WHERE);

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		boolean bindTreePath = false;

		if (treePath == null) {
			query.append(_FINDER_COLUMN_C_T_TREEPATH_1);
		}
		else if (treePath.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_T_TREEPATH_3);
		}
		else {
			bindTreePath = true;

			query.append(_FINDER_COLUMN_C_T_TREEPATH_2);
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
			query.append(OrganizationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindTreePath) {
			qPos.add(treePath);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the organizations that the user has permission to view where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @return the matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByC_T(long companyId, String treePath)
		throws SystemException {
		return filterFindByC_T(companyId, treePath, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations that the user has permission to view where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByC_T(long companyId, String treePath,
		int start, int end) throws SystemException {
		return filterFindByC_T(companyId, treePath, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations that the user has permissions to view where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByC_T(long companyId, String treePath,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_T(companyId, treePath, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		boolean bindTreePath = false;

		if (treePath == null) {
			query.append(_FINDER_COLUMN_C_T_TREEPATH_1);
		}
		else if (treePath.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_T_TREEPATH_3);
		}
		else {
			bindTreePath = true;

			query.append(_FINDER_COLUMN_C_T_TREEPATH_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (bindTreePath) {
				qPos.add(treePath);
			}

			return (List<Organization>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the organizations before and after the current organization in the ordered set of organizations that the user has permission to view where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * @param organizationId the primary key of the current organization
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization[] filterFindByC_T_PrevAndNext(long organizationId,
		long companyId, String treePath, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_T_PrevAndNext(organizationId, companyId, treePath,
				orderByComparator);
		}

		Organization organization = findByPrimaryKey(organizationId);

		Session session = null;

		try {
			session = openSession();

			Organization[] array = new OrganizationImpl[3];

			array[0] = filterGetByC_T_PrevAndNext(session, organization,
					companyId, treePath, orderByComparator, true);

			array[1] = organization;

			array[2] = filterGetByC_T_PrevAndNext(session, organization,
					companyId, treePath, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Organization filterGetByC_T_PrevAndNext(Session session,
		Organization organization, long companyId, String treePath,
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
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		boolean bindTreePath = false;

		if (treePath == null) {
			query.append(_FINDER_COLUMN_C_T_TREEPATH_1);
		}
		else if (treePath.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_T_TREEPATH_3);
		}
		else {
			bindTreePath = true;

			query.append(_FINDER_COLUMN_C_T_TREEPATH_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindTreePath) {
			qPos.add(treePath);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(organization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Organization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the organizations where companyId = &#63; and treePath LIKE &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_T(long companyId, String treePath)
		throws SystemException {
		for (Organization organization : findByC_T(companyId, treePath,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(organization);
		}
	}

	/**
	 * Returns the number of organizations where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @return the number of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_T(long companyId, String treePath)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_T;

		Object[] finderArgs = new Object[] { companyId, treePath };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ORGANIZATION_WHERE);

			query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

			boolean bindTreePath = false;

			if (treePath == null) {
				query.append(_FINDER_COLUMN_C_T_TREEPATH_1);
			}
			else if (treePath.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_T_TREEPATH_3);
			}
			else {
				bindTreePath = true;

				query.append(_FINDER_COLUMN_C_T_TREEPATH_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindTreePath) {
					qPos.add(treePath);
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
	 * Returns the number of organizations that the user has permission to view where companyId = &#63; and treePath LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param treePath the tree path
	 * @return the number of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByC_T(long companyId, String treePath)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByC_T(companyId, treePath);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_ORGANIZATION_WHERE);

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		boolean bindTreePath = false;

		if (treePath == null) {
			query.append(_FINDER_COLUMN_C_T_TREEPATH_1);
		}
		else if (treePath.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_T_TREEPATH_3);
		}
		else {
			bindTreePath = true;

			query.append(_FINDER_COLUMN_C_T_TREEPATH_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (bindTreePath) {
				qPos.add(treePath);
			}

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

	private static final String _FINDER_COLUMN_C_T_COMPANYID_2 = "organization.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_T_TREEPATH_1 = "organization.treePath LIKE NULL";
	private static final String _FINDER_COLUMN_C_T_TREEPATH_2 = "organization.treePath LIKE ?";
	private static final String _FINDER_COLUMN_C_T_TREEPATH_3 = "(organization.treePath IS NULL OR organization.treePath LIKE '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_N = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_N",
			new String[] { Long.class.getName(), String.class.getName() },
			OrganizationModelImpl.COMPANYID_COLUMN_BITMASK |
			OrganizationModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the organization where companyId = &#63; and name = &#63; or throws a {@link com.liferay.portal.NoSuchOrganizationException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByC_N(long companyId, String name)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByC_N(companyId, name);

		if (organization == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchOrganizationException(msg.toString());
		}

		return organization;
	}

	/**
	 * Returns the organization where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByC_N(long companyId, String name)
		throws SystemException {
		return fetchByC_N(companyId, name, true);
	}

	/**
	 * Returns the organization where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByC_N(long companyId, String name,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_N,
					finderArgs, this);
		}

		if (result instanceof Organization) {
			Organization organization = (Organization)result;

			if ((companyId != organization.getCompanyId()) ||
					!Validator.equals(name, organization.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ORGANIZATION_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				List<Organization> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N,
						finderArgs, list);
				}
				else {
					Organization organization = list.get(0);

					result = organization;

					cacheResult(organization);

					if ((organization.getCompanyId() != companyId) ||
							(organization.getName() == null) ||
							!organization.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N,
							finderArgs, organization);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N,
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
			return (Organization)result;
		}
	}

	/**
	 * Removes the organization where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the organization that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization removeByC_N(long companyId, String name)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = findByC_N(companyId, name);

		return remove(organization);
	}

	/**
	 * Returns the number of organizations where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_N(long companyId, String name)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N;

		Object[] finderArgs = new Object[] { companyId, name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ORGANIZATION_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_C_N_COMPANYID_2 = "organization.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_NAME_1 = "organization.name IS NULL";
	private static final String _FINDER_COLUMN_C_N_NAME_2 = "organization.name = ?";
	private static final String _FINDER_COLUMN_C_N_NAME_3 = "(organization.name IS NULL OR organization.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_O_C_P = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, OrganizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByO_C_P",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_O_C_P = new FinderPath(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByO_C_P",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the organizations where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @return the matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByO_C_P(long organizationId, long companyId,
		long parentOrganizationId) throws SystemException {
		return findByO_C_P(organizationId, companyId, parentOrganizationId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByO_C_P(long organizationId, long companyId,
		long parentOrganizationId, int start, int end)
		throws SystemException {
		return findByO_C_P(organizationId, companyId, parentOrganizationId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findByO_C_P(long organizationId, long companyId,
		long parentOrganizationId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_O_C_P;
		finderArgs = new Object[] {
				organizationId, companyId, parentOrganizationId,
				
				start, end, orderByComparator
			};

		List<Organization> list = (List<Organization>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Organization organization : list) {
				if ((organizationId >= organization.getOrganizationId()) ||
						(companyId != organization.getCompanyId()) ||
						(parentOrganizationId != organization.getParentOrganizationId())) {
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

			query.append(_SQL_SELECT_ORGANIZATION_WHERE);

			query.append(_FINDER_COLUMN_O_C_P_ORGANIZATIONID_2);

			query.append(_FINDER_COLUMN_O_C_P_COMPANYID_2);

			query.append(_FINDER_COLUMN_O_C_P_PARENTORGANIZATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				qPos.add(companyId);

				qPos.add(parentOrganizationId);

				if (!pagination) {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Organization>(list);
				}
				else {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first organization in the ordered set where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByO_C_P_First(long organizationId, long companyId,
		long parentOrganizationId, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByO_C_P_First(organizationId,
				companyId, parentOrganizationId, orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("organizationId=");
		msg.append(organizationId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", parentOrganizationId=");
		msg.append(parentOrganizationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the first organization in the ordered set where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByO_C_P_First(long organizationId, long companyId,
		long parentOrganizationId, OrderByComparator orderByComparator)
		throws SystemException {
		List<Organization> list = findByO_C_P(organizationId, companyId,
				parentOrganizationId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last organization in the ordered set where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByO_C_P_Last(long organizationId, long companyId,
		long parentOrganizationId, OrderByComparator orderByComparator)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByO_C_P_Last(organizationId,
				companyId, parentOrganizationId, orderByComparator);

		if (organization != null) {
			return organization;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("organizationId=");
		msg.append(organizationId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", parentOrganizationId=");
		msg.append(parentOrganizationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrganizationException(msg.toString());
	}

	/**
	 * Returns the last organization in the ordered set where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching organization, or <code>null</code> if a matching organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByO_C_P_Last(long organizationId, long companyId,
		long parentOrganizationId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByO_C_P(organizationId, companyId, parentOrganizationId);

		if (count == 0) {
			return null;
		}

		List<Organization> list = findByO_C_P(organizationId, companyId,
				parentOrganizationId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns all the organizations that the user has permission to view where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @return the matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByO_C_P(long organizationId,
		long companyId, long parentOrganizationId) throws SystemException {
		return filterFindByO_C_P(organizationId, companyId,
			parentOrganizationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations that the user has permission to view where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByO_C_P(long organizationId,
		long companyId, long parentOrganizationId, int start, int end)
		throws SystemException {
		return filterFindByO_C_P(organizationId, companyId,
			parentOrganizationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations that the user has permissions to view where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> filterFindByO_C_P(long organizationId,
		long companyId, long parentOrganizationId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByO_C_P(organizationId, companyId, parentOrganizationId,
				start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_O_C_P_ORGANIZATIONID_2);

		query.append(_FINDER_COLUMN_O_C_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_O_C_P_PARENTORGANIZATIONID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OrganizationModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OrganizationModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OrganizationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OrganizationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(organizationId);

			qPos.add(companyId);

			qPos.add(parentOrganizationId);

			return (List<Organization>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the organizations where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63; from the database.
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByO_C_P(long organizationId, long companyId,
		long parentOrganizationId) throws SystemException {
		for (Organization organization : findByO_C_P(organizationId, companyId,
				parentOrganizationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(organization);
		}
	}

	/**
	 * Returns the number of organizations where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @return the number of matching organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByO_C_P(long organizationId, long companyId,
		long parentOrganizationId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_O_C_P;

		Object[] finderArgs = new Object[] {
				organizationId, companyId, parentOrganizationId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ORGANIZATION_WHERE);

			query.append(_FINDER_COLUMN_O_C_P_ORGANIZATIONID_2);

			query.append(_FINDER_COLUMN_O_C_P_COMPANYID_2);

			query.append(_FINDER_COLUMN_O_C_P_PARENTORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				qPos.add(companyId);

				qPos.add(parentOrganizationId);

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
	 * Returns the number of organizations that the user has permission to view where organizationId &gt; &#63; and companyId = &#63; and parentOrganizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param companyId the company ID
	 * @param parentOrganizationId the parent organization ID
	 * @return the number of matching organizations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByO_C_P(long organizationId, long companyId,
		long parentOrganizationId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByO_C_P(organizationId, companyId, parentOrganizationId);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_ORGANIZATION_WHERE);

		query.append(_FINDER_COLUMN_O_C_P_ORGANIZATIONID_2);

		query.append(_FINDER_COLUMN_O_C_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_O_C_P_PARENTORGANIZATIONID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Organization.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(organizationId);

			qPos.add(companyId);

			qPos.add(parentOrganizationId);

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

	private static final String _FINDER_COLUMN_O_C_P_ORGANIZATIONID_2 = "organization.organizationId > ? AND ";
	private static final String _FINDER_COLUMN_O_C_P_COMPANYID_2 = "organization.companyId = ? AND ";
	private static final String _FINDER_COLUMN_O_C_P_PARENTORGANIZATIONID_2 = "organization.parentOrganizationId = ?";

	public OrganizationPersistenceImpl() {
		setModelClass(Organization.class);
	}

	/**
	 * Caches the organization in the entity cache if it is enabled.
	 *
	 * @param organization the organization
	 */
	@Override
	public void cacheResult(Organization organization) {
		EntityCacheUtil.putResult(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationImpl.class, organization.getPrimaryKey(), organization);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N,
			new Object[] { organization.getCompanyId(), organization.getName() },
			organization);

		organization.resetOriginalValues();
	}

	/**
	 * Caches the organizations in the entity cache if it is enabled.
	 *
	 * @param organizations the organizations
	 */
	@Override
	public void cacheResult(List<Organization> organizations) {
		for (Organization organization : organizations) {
			if (EntityCacheUtil.getResult(
						OrganizationModelImpl.ENTITY_CACHE_ENABLED,
						OrganizationImpl.class, organization.getPrimaryKey()) == null) {
				cacheResult(organization);
			}
			else {
				organization.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all organizations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OrganizationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OrganizationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the organization.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Organization organization) {
		EntityCacheUtil.removeResult(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationImpl.class, organization.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(organization);
	}

	@Override
	public void clearCache(List<Organization> organizations) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Organization organization : organizations) {
			EntityCacheUtil.removeResult(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
				OrganizationImpl.class, organization.getPrimaryKey());

			clearUniqueFindersCache(organization);
		}
	}

	protected void cacheUniqueFindersCache(Organization organization) {
		if (organization.isNew()) {
			Object[] args = new Object[] {
					organization.getCompanyId(), organization.getName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N, args,
				organization);
		}
		else {
			OrganizationModelImpl organizationModelImpl = (OrganizationModelImpl)organization;

			if ((organizationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						organization.getCompanyId(), organization.getName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N, args,
					organization);
			}
		}
	}

	protected void clearUniqueFindersCache(Organization organization) {
		OrganizationModelImpl organizationModelImpl = (OrganizationModelImpl)organization;

		Object[] args = new Object[] {
				organization.getCompanyId(), organization.getName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N, args);

		if ((organizationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_N.getColumnBitmask()) != 0) {
			args = new Object[] {
					organizationModelImpl.getOriginalCompanyId(),
					organizationModelImpl.getOriginalName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N, args);
		}
	}

	/**
	 * Creates a new organization with the primary key. Does not add the organization to the database.
	 *
	 * @param organizationId the primary key for the new organization
	 * @return the new organization
	 */
	@Override
	public Organization create(long organizationId) {
		Organization organization = new OrganizationImpl();

		organization.setNew(true);
		organization.setPrimaryKey(organizationId);

		String uuid = PortalUUIDUtil.generate();

		organization.setUuid(uuid);

		return organization;
	}

	/**
	 * Removes the organization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param organizationId the primary key of the organization
	 * @return the organization that was removed
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization remove(long organizationId)
		throws NoSuchOrganizationException, SystemException {
		return remove((Serializable)organizationId);
	}

	/**
	 * Removes the organization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the organization
	 * @return the organization that was removed
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization remove(Serializable primaryKey)
		throws NoSuchOrganizationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Organization organization = (Organization)session.get(OrganizationImpl.class,
					primaryKey);

			if (organization == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOrganizationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(organization);
		}
		catch (NoSuchOrganizationException nsee) {
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
	protected Organization removeImpl(Organization organization)
		throws SystemException {
		organization = toUnwrappedModel(organization);

		organizationToGroupTableMapper.deleteLeftPrimaryKeyTableMappings(organization.getPrimaryKey());

		organizationToUserTableMapper.deleteLeftPrimaryKeyTableMappings(organization.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(organization)) {
				organization = (Organization)session.get(OrganizationImpl.class,
						organization.getPrimaryKeyObj());
			}

			if (organization != null) {
				session.delete(organization);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (organization != null) {
			clearCache(organization);
		}

		return organization;
	}

	@Override
	public Organization updateImpl(
		com.liferay.portal.model.Organization organization)
		throws SystemException {
		organization = toUnwrappedModel(organization);

		boolean isNew = organization.isNew();

		OrganizationModelImpl organizationModelImpl = (OrganizationModelImpl)organization;

		if (Validator.isNull(organization.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			organization.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (organization.isNew()) {
				session.save(organization);

				organization.setNew(false);
			}
			else {
				session.merge(organization);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OrganizationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((organizationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						organizationModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { organizationModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((organizationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						organizationModelImpl.getOriginalUuid(),
						organizationModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						organizationModelImpl.getUuid(),
						organizationModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((organizationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						organizationModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { organizationModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((organizationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOCATIONS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						organizationModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LOCATIONS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOCATIONS,
					args);

				args = new Object[] { organizationModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LOCATIONS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOCATIONS,
					args);
			}

			if ((organizationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						organizationModelImpl.getOriginalCompanyId(),
						organizationModelImpl.getOriginalParentOrganizationId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_P, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P,
					args);

				args = new Object[] {
						organizationModelImpl.getCompanyId(),
						organizationModelImpl.getParentOrganizationId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_P, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P,
					args);
			}
		}

		EntityCacheUtil.putResult(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
			OrganizationImpl.class, organization.getPrimaryKey(), organization);

		clearUniqueFindersCache(organization);
		cacheUniqueFindersCache(organization);

		return organization;
	}

	protected Organization toUnwrappedModel(Organization organization) {
		if (organization instanceof OrganizationImpl) {
			return organization;
		}

		OrganizationImpl organizationImpl = new OrganizationImpl();

		organizationImpl.setNew(organization.isNew());
		organizationImpl.setPrimaryKey(organization.getPrimaryKey());

		organizationImpl.setUuid(organization.getUuid());
		organizationImpl.setOrganizationId(organization.getOrganizationId());
		organizationImpl.setCompanyId(organization.getCompanyId());
		organizationImpl.setUserId(organization.getUserId());
		organizationImpl.setUserName(organization.getUserName());
		organizationImpl.setCreateDate(organization.getCreateDate());
		organizationImpl.setModifiedDate(organization.getModifiedDate());
		organizationImpl.setParentOrganizationId(organization.getParentOrganizationId());
		organizationImpl.setTreePath(organization.getTreePath());
		organizationImpl.setName(organization.getName());
		organizationImpl.setType(organization.getType());
		organizationImpl.setRecursable(organization.isRecursable());
		organizationImpl.setRegionId(organization.getRegionId());
		organizationImpl.setCountryId(organization.getCountryId());
		organizationImpl.setStatusId(organization.getStatusId());
		organizationImpl.setComments(organization.getComments());

		return organizationImpl;
	}

	/**
	 * Returns the organization with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the organization
	 * @return the organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOrganizationException, SystemException {
		Organization organization = fetchByPrimaryKey(primaryKey);

		if (organization == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOrganizationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return organization;
	}

	/**
	 * Returns the organization with the primary key or throws a {@link com.liferay.portal.NoSuchOrganizationException} if it could not be found.
	 *
	 * @param organizationId the primary key of the organization
	 * @return the organization
	 * @throws com.liferay.portal.NoSuchOrganizationException if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization findByPrimaryKey(long organizationId)
		throws NoSuchOrganizationException, SystemException {
		return findByPrimaryKey((Serializable)organizationId);
	}

	/**
	 * Returns the organization with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the organization
	 * @return the organization, or <code>null</code> if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Organization organization = (Organization)EntityCacheUtil.getResult(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
				OrganizationImpl.class, primaryKey);

		if (organization == _nullOrganization) {
			return null;
		}

		if (organization == null) {
			Session session = null;

			try {
				session = openSession();

				organization = (Organization)session.get(OrganizationImpl.class,
						primaryKey);

				if (organization != null) {
					cacheResult(organization);
				}
				else {
					EntityCacheUtil.putResult(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
						OrganizationImpl.class, primaryKey, _nullOrganization);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(OrganizationModelImpl.ENTITY_CACHE_ENABLED,
					OrganizationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return organization;
	}

	/**
	 * Returns the organization with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param organizationId the primary key of the organization
	 * @return the organization, or <code>null</code> if a organization with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization fetchByPrimaryKey(long organizationId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)organizationId);
	}

	/**
	 * Returns all the organizations.
	 *
	 * @return the organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the organizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of organizations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Organization> findAll(int start, int end,
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

		List<Organization> list = (List<Organization>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ORGANIZATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ORGANIZATION;

				if (pagination) {
					sql = sql.concat(OrganizationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Organization>(list);
				}
				else {
					list = (List<Organization>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the organizations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Organization organization : findAll()) {
			remove(organization);
		}
	}

	/**
	 * Returns the number of organizations.
	 *
	 * @return the number of organizations
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

				Query q = session.createQuery(_SQL_COUNT_ORGANIZATION);

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
	 * Returns all the groups associated with the organization.
	 *
	 * @param pk the primary key of the organization
	 * @return the groups associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portal.model.Group> getGroups(long pk)
		throws SystemException {
		return getGroups(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the groups associated with the organization.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the organization
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of groups associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portal.model.Group> getGroups(long pk, int start,
		int end) throws SystemException {
		return getGroups(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the groups associated with the organization.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the organization
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of groups associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portal.model.Group> getGroups(long pk, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		return organizationToGroupTableMapper.getRightBaseModels(pk, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of groups associated with the organization.
	 *
	 * @param pk the primary key of the organization
	 * @return the number of groups associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getGroupsSize(long pk) throws SystemException {
		long[] pks = organizationToGroupTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the group is associated with the organization.
	 *
	 * @param pk the primary key of the organization
	 * @param groupPK the primary key of the group
	 * @return <code>true</code> if the group is associated with the organization; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsGroup(long pk, long groupPK)
		throws SystemException {
		return organizationToGroupTableMapper.containsTableMapping(pk, groupPK);
	}

	/**
	 * Returns <code>true</code> if the organization has any groups associated with it.
	 *
	 * @param pk the primary key of the organization to check for associations with groups
	 * @return <code>true</code> if the organization has any groups associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsGroups(long pk) throws SystemException {
		if (getGroupsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the organization and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param groupPK the primary key of the group
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addGroup(long pk, long groupPK) throws SystemException {
		organizationToGroupTableMapper.addTableMapping(pk, groupPK);
	}

	/**
	 * Adds an association between the organization and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param group the group
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addGroup(long pk, com.liferay.portal.model.Group group)
		throws SystemException {
		organizationToGroupTableMapper.addTableMapping(pk, group.getPrimaryKey());
	}

	/**
	 * Adds an association between the organization and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param groupPKs the primary keys of the groups
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addGroups(long pk, long[] groupPKs) throws SystemException {
		for (long groupPK : groupPKs) {
			organizationToGroupTableMapper.addTableMapping(pk, groupPK);
		}
	}

	/**
	 * Adds an association between the organization and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param groups the groups
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addGroups(long pk, List<com.liferay.portal.model.Group> groups)
		throws SystemException {
		for (com.liferay.portal.model.Group group : groups) {
			organizationToGroupTableMapper.addTableMapping(pk,
				group.getPrimaryKey());
		}
	}

	/**
	 * Clears all associations between the organization and its groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization to clear the associated groups from
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearGroups(long pk) throws SystemException {
		organizationToGroupTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the organization and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param groupPK the primary key of the group
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeGroup(long pk, long groupPK) throws SystemException {
		organizationToGroupTableMapper.deleteTableMapping(pk, groupPK);
	}

	/**
	 * Removes the association between the organization and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param group the group
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeGroup(long pk, com.liferay.portal.model.Group group)
		throws SystemException {
		organizationToGroupTableMapper.deleteTableMapping(pk,
			group.getPrimaryKey());
	}

	/**
	 * Removes the association between the organization and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param groupPKs the primary keys of the groups
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeGroups(long pk, long[] groupPKs)
		throws SystemException {
		for (long groupPK : groupPKs) {
			organizationToGroupTableMapper.deleteTableMapping(pk, groupPK);
		}
	}

	/**
	 * Removes the association between the organization and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param groups the groups
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeGroups(long pk,
		List<com.liferay.portal.model.Group> groups) throws SystemException {
		for (com.liferay.portal.model.Group group : groups) {
			organizationToGroupTableMapper.deleteTableMapping(pk,
				group.getPrimaryKey());
		}
	}

	/**
	 * Sets the groups associated with the organization, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param groupPKs the primary keys of the groups to be associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setGroups(long pk, long[] groupPKs) throws SystemException {
		Set<Long> newGroupPKsSet = SetUtil.fromArray(groupPKs);
		Set<Long> oldGroupPKsSet = SetUtil.fromArray(organizationToGroupTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeGroupPKsSet = new HashSet<Long>(oldGroupPKsSet);

		removeGroupPKsSet.removeAll(newGroupPKsSet);

		for (long removeGroupPK : removeGroupPKsSet) {
			organizationToGroupTableMapper.deleteTableMapping(pk, removeGroupPK);
		}

		newGroupPKsSet.removeAll(oldGroupPKsSet);

		for (long newGroupPK : newGroupPKsSet) {
			organizationToGroupTableMapper.addTableMapping(pk, newGroupPK);
		}
	}

	/**
	 * Sets the groups associated with the organization, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param groups the groups to be associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setGroups(long pk, List<com.liferay.portal.model.Group> groups)
		throws SystemException {
		try {
			long[] groupPKs = new long[groups.size()];

			for (int i = 0; i < groups.size(); i++) {
				com.liferay.portal.model.Group group = groups.get(i);

				groupPKs[i] = group.getPrimaryKey();
			}

			setGroups(pk, groupPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OrganizationModelImpl.MAPPING_TABLE_GROUPS_ORGS_NAME);
		}
	}

	/**
	 * Returns all the users associated with the organization.
	 *
	 * @param pk the primary key of the organization
	 * @return the users associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portal.model.User> getUsers(long pk)
		throws SystemException {
		return getUsers(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the users associated with the organization.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the organization
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of users associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portal.model.User> getUsers(long pk, int start,
		int end) throws SystemException {
		return getUsers(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the users associated with the organization.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrganizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the organization
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of users associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.portal.model.User> getUsers(long pk, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		return organizationToUserTableMapper.getRightBaseModels(pk, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of users associated with the organization.
	 *
	 * @param pk the primary key of the organization
	 * @return the number of users associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getUsersSize(long pk) throws SystemException {
		long[] pks = organizationToUserTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the user is associated with the organization.
	 *
	 * @param pk the primary key of the organization
	 * @param userPK the primary key of the user
	 * @return <code>true</code> if the user is associated with the organization; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsUser(long pk, long userPK) throws SystemException {
		return organizationToUserTableMapper.containsTableMapping(pk, userPK);
	}

	/**
	 * Returns <code>true</code> if the organization has any users associated with it.
	 *
	 * @param pk the primary key of the organization to check for associations with users
	 * @return <code>true</code> if the organization has any users associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsUsers(long pk) throws SystemException {
		if (getUsersSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the organization and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param userPK the primary key of the user
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addUser(long pk, long userPK) throws SystemException {
		organizationToUserTableMapper.addTableMapping(pk, userPK);
	}

	/**
	 * Adds an association between the organization and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param user the user
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addUser(long pk, com.liferay.portal.model.User user)
		throws SystemException {
		organizationToUserTableMapper.addTableMapping(pk, user.getPrimaryKey());
	}

	/**
	 * Adds an association between the organization and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param userPKs the primary keys of the users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addUsers(long pk, long[] userPKs) throws SystemException {
		for (long userPK : userPKs) {
			organizationToUserTableMapper.addTableMapping(pk, userPK);
		}
	}

	/**
	 * Adds an association between the organization and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param users the users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addUsers(long pk, List<com.liferay.portal.model.User> users)
		throws SystemException {
		for (com.liferay.portal.model.User user : users) {
			organizationToUserTableMapper.addTableMapping(pk,
				user.getPrimaryKey());
		}
	}

	/**
	 * Clears all associations between the organization and its users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization to clear the associated users from
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearUsers(long pk) throws SystemException {
		organizationToUserTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the organization and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param userPK the primary key of the user
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeUser(long pk, long userPK) throws SystemException {
		organizationToUserTableMapper.deleteTableMapping(pk, userPK);
	}

	/**
	 * Removes the association between the organization and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param user the user
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeUser(long pk, com.liferay.portal.model.User user)
		throws SystemException {
		organizationToUserTableMapper.deleteTableMapping(pk,
			user.getPrimaryKey());
	}

	/**
	 * Removes the association between the organization and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param userPKs the primary keys of the users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeUsers(long pk, long[] userPKs) throws SystemException {
		for (long userPK : userPKs) {
			organizationToUserTableMapper.deleteTableMapping(pk, userPK);
		}
	}

	/**
	 * Removes the association between the organization and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param users the users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeUsers(long pk, List<com.liferay.portal.model.User> users)
		throws SystemException {
		for (com.liferay.portal.model.User user : users) {
			organizationToUserTableMapper.deleteTableMapping(pk,
				user.getPrimaryKey());
		}
	}

	/**
	 * Sets the users associated with the organization, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param userPKs the primary keys of the users to be associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setUsers(long pk, long[] userPKs) throws SystemException {
		Set<Long> newUserPKsSet = SetUtil.fromArray(userPKs);
		Set<Long> oldUserPKsSet = SetUtil.fromArray(organizationToUserTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeUserPKsSet = new HashSet<Long>(oldUserPKsSet);

		removeUserPKsSet.removeAll(newUserPKsSet);

		for (long removeUserPK : removeUserPKsSet) {
			organizationToUserTableMapper.deleteTableMapping(pk, removeUserPK);
		}

		newUserPKsSet.removeAll(oldUserPKsSet);

		for (long newUserPK : newUserPKsSet) {
			organizationToUserTableMapper.addTableMapping(pk, newUserPK);
		}
	}

	/**
	 * Sets the users associated with the organization, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the organization
	 * @param users the users to be associated with the organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setUsers(long pk, List<com.liferay.portal.model.User> users)
		throws SystemException {
		try {
			long[] userPKs = new long[users.size()];

			for (int i = 0; i < users.size(); i++) {
				com.liferay.portal.model.User user = users.get(i);

				userPKs[i] = user.getPrimaryKey();
			}

			setUsers(pk, userPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OrganizationModelImpl.MAPPING_TABLE_USERS_ORGS_NAME);
		}
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the organization persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portal.model.Organization")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Organization>> listenersList = new ArrayList<ModelListener<Organization>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Organization>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		organizationToGroupTableMapper = TableMapperFactory.getTableMapper("Groups_Orgs",
				"organizationId", "groupId", this, groupPersistence);

		organizationToUserTableMapper = TableMapperFactory.getTableMapper("Users_Orgs",
				"organizationId", "userId", this, userPersistence);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(OrganizationImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("Groups_Orgs");
		TableMapperFactory.removeTableMapper("Users_Orgs");
	}

	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	protected TableMapper<Organization, com.liferay.portal.model.Group> organizationToGroupTableMapper;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	protected TableMapper<Organization, com.liferay.portal.model.User> organizationToUserTableMapper;
	private static final String _SQL_SELECT_ORGANIZATION = "SELECT organization FROM Organization organization";
	private static final String _SQL_SELECT_ORGANIZATION_WHERE = "SELECT organization FROM Organization organization WHERE ";
	private static final String _SQL_COUNT_ORGANIZATION = "SELECT COUNT(organization) FROM Organization organization";
	private static final String _SQL_COUNT_ORGANIZATION_WHERE = "SELECT COUNT(organization) FROM Organization organization WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "organization.organizationId";
	private static final String _FILTER_SQL_SELECT_ORGANIZATION_WHERE = "SELECT DISTINCT {organization.*} FROM Organization_ organization WHERE ";
	private static final String _FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {Organization_.*} FROM (SELECT DISTINCT organization.organizationId FROM Organization_ organization WHERE ";
	private static final String _FILTER_SQL_SELECT_ORGANIZATION_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN Organization_ ON TEMP_TABLE.organizationId = Organization_.organizationId";
	private static final String _FILTER_SQL_COUNT_ORGANIZATION_WHERE = "SELECT COUNT(DISTINCT organization.organizationId) AS COUNT_VALUE FROM Organization_ organization WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "organization";
	private static final String _FILTER_ENTITY_TABLE = "Organization_";
	private static final String _ORDER_BY_ENTITY_ALIAS = "organization.";
	private static final String _ORDER_BY_ENTITY_TABLE = "Organization_.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Organization exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Organization exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(OrganizationPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "type"
			});
	private static Organization _nullOrganization = new OrganizationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Organization> toCacheModel() {
				return _nullOrganizationCacheModel;
			}
		};

	private static CacheModel<Organization> _nullOrganizationCacheModel = new CacheModel<Organization>() {
			@Override
			public Organization toEntityModel() {
				return _nullOrganization;
			}
		};
}