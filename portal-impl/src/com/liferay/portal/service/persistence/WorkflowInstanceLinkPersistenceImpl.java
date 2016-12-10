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

import com.liferay.portal.NoSuchWorkflowInstanceLinkException;
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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.WorkflowInstanceLink;
import com.liferay.portal.model.impl.WorkflowInstanceLinkImpl;
import com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the workflow instance link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowInstanceLinkPersistence
 * @see WorkflowInstanceLinkUtil
 * @generated
 */
public class WorkflowInstanceLinkPersistenceImpl extends BasePersistenceImpl<WorkflowInstanceLink>
	implements WorkflowInstanceLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkflowInstanceLinkUtil} to access the workflow instance link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowInstanceLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowInstanceLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowInstanceLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C_C_C = new FinderPath(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowInstanceLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_C_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C_C =
		new FinderPath(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowInstanceLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_C_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			WorkflowInstanceLinkModelImpl.GROUPID_COLUMN_BITMASK |
			WorkflowInstanceLinkModelImpl.COMPANYID_COLUMN_BITMASK |
			WorkflowInstanceLinkModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			WorkflowInstanceLinkModelImpl.CLASSPK_COLUMN_BITMASK |
			WorkflowInstanceLinkModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C_C_C = new FinderPath(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the workflow instance links where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching workflow instance links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowInstanceLink> findByG_C_C_C(long groupId,
		long companyId, long classNameId, long classPK)
		throws SystemException {
		return findByG_C_C_C(groupId, companyId, classNameId, classPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow instance links where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of workflow instance links
	 * @param end the upper bound of the range of workflow instance links (not inclusive)
	 * @return the range of matching workflow instance links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowInstanceLink> findByG_C_C_C(long groupId,
		long companyId, long classNameId, long classPK, int start, int end)
		throws SystemException {
		return findByG_C_C_C(groupId, companyId, classNameId, classPK, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the workflow instance links where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of workflow instance links
	 * @param end the upper bound of the range of workflow instance links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow instance links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowInstanceLink> findByG_C_C_C(long groupId,
		long companyId, long classNameId, long classPK, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C_C;
			finderArgs = new Object[] { groupId, companyId, classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C_C_C;
			finderArgs = new Object[] {
					groupId, companyId, classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<WorkflowInstanceLink> list = (List<WorkflowInstanceLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkflowInstanceLink workflowInstanceLink : list) {
				if ((groupId != workflowInstanceLink.getGroupId()) ||
						(companyId != workflowInstanceLink.getCompanyId()) ||
						(classNameId != workflowInstanceLink.getClassNameId()) ||
						(classPK != workflowInstanceLink.getClassPK())) {
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

			query.append(_SQL_SELECT_WORKFLOWINSTANCELINK_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_COMPANYID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowInstanceLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<WorkflowInstanceLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowInstanceLink>(list);
				}
				else {
					list = (List<WorkflowInstanceLink>)QueryUtil.list(q,
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
	 * Returns the first workflow instance link in the ordered set where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow instance link
	 * @throws com.liferay.portal.NoSuchWorkflowInstanceLinkException if a matching workflow instance link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowInstanceLink findByG_C_C_C_First(long groupId,
		long companyId, long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowInstanceLinkException, SystemException {
		WorkflowInstanceLink workflowInstanceLink = fetchByG_C_C_C_First(groupId,
				companyId, classNameId, classPK, orderByComparator);

		if (workflowInstanceLink != null) {
			return workflowInstanceLink;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowInstanceLinkException(msg.toString());
	}

	/**
	 * Returns the first workflow instance link in the ordered set where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow instance link, or <code>null</code> if a matching workflow instance link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowInstanceLink fetchByG_C_C_C_First(long groupId,
		long companyId, long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<WorkflowInstanceLink> list = findByG_C_C_C(groupId, companyId,
				classNameId, classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow instance link in the ordered set where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow instance link
	 * @throws com.liferay.portal.NoSuchWorkflowInstanceLinkException if a matching workflow instance link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowInstanceLink findByG_C_C_C_Last(long groupId,
		long companyId, long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowInstanceLinkException, SystemException {
		WorkflowInstanceLink workflowInstanceLink = fetchByG_C_C_C_Last(groupId,
				companyId, classNameId, classPK, orderByComparator);

		if (workflowInstanceLink != null) {
			return workflowInstanceLink;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowInstanceLinkException(msg.toString());
	}

	/**
	 * Returns the last workflow instance link in the ordered set where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow instance link, or <code>null</code> if a matching workflow instance link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowInstanceLink fetchByG_C_C_C_Last(long groupId,
		long companyId, long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_C_C_C(groupId, companyId, classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<WorkflowInstanceLink> list = findByG_C_C_C(groupId, companyId,
				classNameId, classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow instance links before and after the current workflow instance link in the ordered set where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param workflowInstanceLinkId the primary key of the current workflow instance link
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow instance link
	 * @throws com.liferay.portal.NoSuchWorkflowInstanceLinkException if a workflow instance link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowInstanceLink[] findByG_C_C_C_PrevAndNext(
		long workflowInstanceLinkId, long groupId, long companyId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchWorkflowInstanceLinkException, SystemException {
		WorkflowInstanceLink workflowInstanceLink = findByPrimaryKey(workflowInstanceLinkId);

		Session session = null;

		try {
			session = openSession();

			WorkflowInstanceLink[] array = new WorkflowInstanceLinkImpl[3];

			array[0] = getByG_C_C_C_PrevAndNext(session, workflowInstanceLink,
					groupId, companyId, classNameId, classPK,
					orderByComparator, true);

			array[1] = workflowInstanceLink;

			array[2] = getByG_C_C_C_PrevAndNext(session, workflowInstanceLink,
					groupId, companyId, classNameId, classPK,
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

	protected WorkflowInstanceLink getByG_C_C_C_PrevAndNext(Session session,
		WorkflowInstanceLink workflowInstanceLink, long groupId,
		long companyId, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWINSTANCELINK_WHERE);

		query.append(_FINDER_COLUMN_G_C_C_C_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_C_C_COMPANYID_2);

		query.append(_FINDER_COLUMN_G_C_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_G_C_C_C_CLASSPK_2);

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
			query.append(WorkflowInstanceLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(companyId);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowInstanceLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowInstanceLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow instance links where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_C_C_C(long groupId, long companyId, long classNameId,
		long classPK) throws SystemException {
		for (WorkflowInstanceLink workflowInstanceLink : findByG_C_C_C(
				groupId, companyId, classNameId, classPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(workflowInstanceLink);
		}
	}

	/**
	 * Returns the number of workflow instance links where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching workflow instance links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_C_C_C(long groupId, long companyId, long classNameId,
		long classPK) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_C_C_C;

		Object[] finderArgs = new Object[] {
				groupId, companyId, classNameId, classPK
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_WORKFLOWINSTANCELINK_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_COMPANYID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_G_C_C_C_GROUPID_2 = "workflowInstanceLink.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_C_COMPANYID_2 = "workflowInstanceLink.companyId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_C_CLASSNAMEID_2 = "workflowInstanceLink.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_C_CLASSPK_2 = "workflowInstanceLink.classPK = ?";

	public WorkflowInstanceLinkPersistenceImpl() {
		setModelClass(WorkflowInstanceLink.class);
	}

	/**
	 * Caches the workflow instance link in the entity cache if it is enabled.
	 *
	 * @param workflowInstanceLink the workflow instance link
	 */
	@Override
	public void cacheResult(WorkflowInstanceLink workflowInstanceLink) {
		EntityCacheUtil.putResult(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceLinkImpl.class,
			workflowInstanceLink.getPrimaryKey(), workflowInstanceLink);

		workflowInstanceLink.resetOriginalValues();
	}

	/**
	 * Caches the workflow instance links in the entity cache if it is enabled.
	 *
	 * @param workflowInstanceLinks the workflow instance links
	 */
	@Override
	public void cacheResult(List<WorkflowInstanceLink> workflowInstanceLinks) {
		for (WorkflowInstanceLink workflowInstanceLink : workflowInstanceLinks) {
			if (EntityCacheUtil.getResult(
						WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowInstanceLinkImpl.class,
						workflowInstanceLink.getPrimaryKey()) == null) {
				cacheResult(workflowInstanceLink);
			}
			else {
				workflowInstanceLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all workflow instance links.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WorkflowInstanceLinkImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WorkflowInstanceLinkImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the workflow instance link.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkflowInstanceLink workflowInstanceLink) {
		EntityCacheUtil.removeResult(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceLinkImpl.class, workflowInstanceLink.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WorkflowInstanceLink> workflowInstanceLinks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkflowInstanceLink workflowInstanceLink : workflowInstanceLinks) {
			EntityCacheUtil.removeResult(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowInstanceLinkImpl.class,
				workflowInstanceLink.getPrimaryKey());
		}
	}

	/**
	 * Creates a new workflow instance link with the primary key. Does not add the workflow instance link to the database.
	 *
	 * @param workflowInstanceLinkId the primary key for the new workflow instance link
	 * @return the new workflow instance link
	 */
	@Override
	public WorkflowInstanceLink create(long workflowInstanceLinkId) {
		WorkflowInstanceLink workflowInstanceLink = new WorkflowInstanceLinkImpl();

		workflowInstanceLink.setNew(true);
		workflowInstanceLink.setPrimaryKey(workflowInstanceLinkId);

		return workflowInstanceLink;
	}

	/**
	 * Removes the workflow instance link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workflowInstanceLinkId the primary key of the workflow instance link
	 * @return the workflow instance link that was removed
	 * @throws com.liferay.portal.NoSuchWorkflowInstanceLinkException if a workflow instance link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowInstanceLink remove(long workflowInstanceLinkId)
		throws NoSuchWorkflowInstanceLinkException, SystemException {
		return remove((Serializable)workflowInstanceLinkId);
	}

	/**
	 * Removes the workflow instance link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the workflow instance link
	 * @return the workflow instance link that was removed
	 * @throws com.liferay.portal.NoSuchWorkflowInstanceLinkException if a workflow instance link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowInstanceLink remove(Serializable primaryKey)
		throws NoSuchWorkflowInstanceLinkException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WorkflowInstanceLink workflowInstanceLink = (WorkflowInstanceLink)session.get(WorkflowInstanceLinkImpl.class,
					primaryKey);

			if (workflowInstanceLink == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkflowInstanceLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workflowInstanceLink);
		}
		catch (NoSuchWorkflowInstanceLinkException nsee) {
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
	protected WorkflowInstanceLink removeImpl(
		WorkflowInstanceLink workflowInstanceLink) throws SystemException {
		workflowInstanceLink = toUnwrappedModel(workflowInstanceLink);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(workflowInstanceLink)) {
				workflowInstanceLink = (WorkflowInstanceLink)session.get(WorkflowInstanceLinkImpl.class,
						workflowInstanceLink.getPrimaryKeyObj());
			}

			if (workflowInstanceLink != null) {
				session.delete(workflowInstanceLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (workflowInstanceLink != null) {
			clearCache(workflowInstanceLink);
		}

		return workflowInstanceLink;
	}

	@Override
	public WorkflowInstanceLink updateImpl(
		com.liferay.portal.model.WorkflowInstanceLink workflowInstanceLink)
		throws SystemException {
		workflowInstanceLink = toUnwrappedModel(workflowInstanceLink);

		boolean isNew = workflowInstanceLink.isNew();

		WorkflowInstanceLinkModelImpl workflowInstanceLinkModelImpl = (WorkflowInstanceLinkModelImpl)workflowInstanceLink;

		Session session = null;

		try {
			session = openSession();

			if (workflowInstanceLink.isNew()) {
				session.save(workflowInstanceLink);

				workflowInstanceLink.setNew(false);
			}
			else {
				session.merge(workflowInstanceLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WorkflowInstanceLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((workflowInstanceLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workflowInstanceLinkModelImpl.getOriginalGroupId(),
						workflowInstanceLinkModelImpl.getOriginalCompanyId(),
						workflowInstanceLinkModelImpl.getOriginalClassNameId(),
						workflowInstanceLinkModelImpl.getOriginalClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C_C,
					args);

				args = new Object[] {
						workflowInstanceLinkModelImpl.getGroupId(),
						workflowInstanceLinkModelImpl.getCompanyId(),
						workflowInstanceLinkModelImpl.getClassNameId(),
						workflowInstanceLinkModelImpl.getClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C_C,
					args);
			}
		}

		EntityCacheUtil.putResult(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceLinkImpl.class,
			workflowInstanceLink.getPrimaryKey(), workflowInstanceLink);

		return workflowInstanceLink;
	}

	protected WorkflowInstanceLink toUnwrappedModel(
		WorkflowInstanceLink workflowInstanceLink) {
		if (workflowInstanceLink instanceof WorkflowInstanceLinkImpl) {
			return workflowInstanceLink;
		}

		WorkflowInstanceLinkImpl workflowInstanceLinkImpl = new WorkflowInstanceLinkImpl();

		workflowInstanceLinkImpl.setNew(workflowInstanceLink.isNew());
		workflowInstanceLinkImpl.setPrimaryKey(workflowInstanceLink.getPrimaryKey());

		workflowInstanceLinkImpl.setWorkflowInstanceLinkId(workflowInstanceLink.getWorkflowInstanceLinkId());
		workflowInstanceLinkImpl.setGroupId(workflowInstanceLink.getGroupId());
		workflowInstanceLinkImpl.setCompanyId(workflowInstanceLink.getCompanyId());
		workflowInstanceLinkImpl.setUserId(workflowInstanceLink.getUserId());
		workflowInstanceLinkImpl.setUserName(workflowInstanceLink.getUserName());
		workflowInstanceLinkImpl.setCreateDate(workflowInstanceLink.getCreateDate());
		workflowInstanceLinkImpl.setModifiedDate(workflowInstanceLink.getModifiedDate());
		workflowInstanceLinkImpl.setClassNameId(workflowInstanceLink.getClassNameId());
		workflowInstanceLinkImpl.setClassPK(workflowInstanceLink.getClassPK());
		workflowInstanceLinkImpl.setWorkflowInstanceId(workflowInstanceLink.getWorkflowInstanceId());

		return workflowInstanceLinkImpl;
	}

	/**
	 * Returns the workflow instance link with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow instance link
	 * @return the workflow instance link
	 * @throws com.liferay.portal.NoSuchWorkflowInstanceLinkException if a workflow instance link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowInstanceLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorkflowInstanceLinkException, SystemException {
		WorkflowInstanceLink workflowInstanceLink = fetchByPrimaryKey(primaryKey);

		if (workflowInstanceLink == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorkflowInstanceLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return workflowInstanceLink;
	}

	/**
	 * Returns the workflow instance link with the primary key or throws a {@link com.liferay.portal.NoSuchWorkflowInstanceLinkException} if it could not be found.
	 *
	 * @param workflowInstanceLinkId the primary key of the workflow instance link
	 * @return the workflow instance link
	 * @throws com.liferay.portal.NoSuchWorkflowInstanceLinkException if a workflow instance link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowInstanceLink findByPrimaryKey(long workflowInstanceLinkId)
		throws NoSuchWorkflowInstanceLinkException, SystemException {
		return findByPrimaryKey((Serializable)workflowInstanceLinkId);
	}

	/**
	 * Returns the workflow instance link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow instance link
	 * @return the workflow instance link, or <code>null</code> if a workflow instance link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowInstanceLink fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		WorkflowInstanceLink workflowInstanceLink = (WorkflowInstanceLink)EntityCacheUtil.getResult(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowInstanceLinkImpl.class, primaryKey);

		if (workflowInstanceLink == _nullWorkflowInstanceLink) {
			return null;
		}

		if (workflowInstanceLink == null) {
			Session session = null;

			try {
				session = openSession();

				workflowInstanceLink = (WorkflowInstanceLink)session.get(WorkflowInstanceLinkImpl.class,
						primaryKey);

				if (workflowInstanceLink != null) {
					cacheResult(workflowInstanceLink);
				}
				else {
					EntityCacheUtil.putResult(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowInstanceLinkImpl.class, primaryKey,
						_nullWorkflowInstanceLink);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(WorkflowInstanceLinkModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowInstanceLinkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return workflowInstanceLink;
	}

	/**
	 * Returns the workflow instance link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workflowInstanceLinkId the primary key of the workflow instance link
	 * @return the workflow instance link, or <code>null</code> if a workflow instance link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowInstanceLink fetchByPrimaryKey(long workflowInstanceLinkId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)workflowInstanceLinkId);
	}

	/**
	 * Returns all the workflow instance links.
	 *
	 * @return the workflow instance links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowInstanceLink> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow instance links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow instance links
	 * @param end the upper bound of the range of workflow instance links (not inclusive)
	 * @return the range of workflow instance links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowInstanceLink> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow instance links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow instance links
	 * @param end the upper bound of the range of workflow instance links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workflow instance links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkflowInstanceLink> findAll(int start, int end,
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

		List<WorkflowInstanceLink> list = (List<WorkflowInstanceLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WORKFLOWINSTANCELINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKFLOWINSTANCELINK;

				if (pagination) {
					sql = sql.concat(WorkflowInstanceLinkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorkflowInstanceLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkflowInstanceLink>(list);
				}
				else {
					list = (List<WorkflowInstanceLink>)QueryUtil.list(q,
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
	 * Removes all the workflow instance links from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (WorkflowInstanceLink workflowInstanceLink : findAll()) {
			remove(workflowInstanceLink);
		}
	}

	/**
	 * Returns the number of workflow instance links.
	 *
	 * @return the number of workflow instance links
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

				Query q = session.createQuery(_SQL_COUNT_WORKFLOWINSTANCELINK);

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
	 * Initializes the workflow instance link persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portal.model.WorkflowInstanceLink")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WorkflowInstanceLink>> listenersList = new ArrayList<ModelListener<WorkflowInstanceLink>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WorkflowInstanceLink>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(WorkflowInstanceLinkImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_WORKFLOWINSTANCELINK = "SELECT workflowInstanceLink FROM WorkflowInstanceLink workflowInstanceLink";
	private static final String _SQL_SELECT_WORKFLOWINSTANCELINK_WHERE = "SELECT workflowInstanceLink FROM WorkflowInstanceLink workflowInstanceLink WHERE ";
	private static final String _SQL_COUNT_WORKFLOWINSTANCELINK = "SELECT COUNT(workflowInstanceLink) FROM WorkflowInstanceLink workflowInstanceLink";
	private static final String _SQL_COUNT_WORKFLOWINSTANCELINK_WHERE = "SELECT COUNT(workflowInstanceLink) FROM WorkflowInstanceLink workflowInstanceLink WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workflowInstanceLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkflowInstanceLink exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WorkflowInstanceLink exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(WorkflowInstanceLinkPersistenceImpl.class);
	private static WorkflowInstanceLink _nullWorkflowInstanceLink = new WorkflowInstanceLinkImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WorkflowInstanceLink> toCacheModel() {
				return _nullWorkflowInstanceLinkCacheModel;
			}
		};

	private static CacheModel<WorkflowInstanceLink> _nullWorkflowInstanceLinkCacheModel =
		new CacheModel<WorkflowInstanceLink>() {
			@Override
			public WorkflowInstanceLink toEntityModel() {
				return _nullWorkflowInstanceLink;
			}
		};
}