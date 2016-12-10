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

package com.liferay.portlet.mobiledevicerules.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.model.impl.MDRRuleGroupImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Edward Han
 * @author Eduardo Lundgren
 * @author Manuel de la Peña
 */
public class MDRRuleGroupFinderImpl extends BasePersistenceImpl<MDRRuleGroup>
	implements MDRRuleGroupFinder {

	public static final String COUNT_BY_G_N =
		MDRRuleGroupFinder.class.getName() + ".countByG_N";

	public static final String FIND_BY_G_N =
		MDRRuleGroupFinder.class.getName() + ".findByG_N";

	@Override
	public int countByKeywords(
			long groupId, String keywords, LinkedHashMap<String, Object> params)
		throws SystemException {

		String[] names = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByG_N(groupId, names, params, andOperator);
	}

	@Override
	public int countByG_N(
			long groupId, String name, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws SystemException {

		String[] names = CustomSQLUtil.keywords(name);

		return countByG_N(groupId, names, params, andOperator);
	}

	@Override
	public int countByG_N(
			long groupId, String[] names, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);

		if (params == null) {
			params = _emptyLinkedHashMap;
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_G_N);

			sql = StringUtil.replace(sql, "[$GROUP_ID$]", getGroupIds(params));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(MDRRuleGroup.name)", StringPool.LIKE, true, names);
			sql = CustomSQLUtil.replaceAndOperator(sql, true);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setGroupIds(qPos, groupId, params);

			qPos.add(names, 2);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<MDRRuleGroup> findByKeywords(
			long groupId, String keywords, LinkedHashMap<String, Object> params,
			int start, int end)
		throws SystemException {

		String[] names = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByG_N(groupId, names, params, andOperator, start, end);
	}

	@Override
	public List<MDRRuleGroup> findByG_N(
			long groupId, String name, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws SystemException {

		return findByG_N(
			groupId, name, params, andOperator, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	@Override
	public List<MDRRuleGroup> findByG_N(
			long groupId, String name, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end)
		throws SystemException {

		String[] names = CustomSQLUtil.keywords(name);

		return findByG_N(groupId, names, params, andOperator, start, end);
	}

	@Override
	public List<MDRRuleGroup> findByG_N(
			long groupId, String[] names, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);

		if (params == null) {
			params = _emptyLinkedHashMap;
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_G_N);

			sql = StringUtil.replace(sql, "[$GROUP_ID$]", getGroupIds(params));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(MDRRuleGroup.name)", StringPool.LIKE, true, names);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("MDRRuleGroup", MDRRuleGroupImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setGroupIds(qPos, groupId, params);

			qPos.add(names, 2);

			return (List<MDRRuleGroup>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getGroupIds(Map<String, Object> params) {
		Boolean includeGlobalScope = (Boolean)params.get("includeGlobalScope");

		if ((includeGlobalScope != null) && includeGlobalScope) {
			return "((groupId = ?) OR (groupId = ?))";
		}
		else {
			return "(groupId = ?)";
		}
	}

	protected void setGroupIds(
			QueryPos qPos, long groupId, Map<String, Object> params)
		throws PortalException, SystemException {

		Boolean includeGlobalScope = (Boolean)params.get("includeGlobalScope");

		if ((includeGlobalScope != null) && includeGlobalScope) {
			qPos.add(groupId);

			Group group = GroupLocalServiceUtil.getGroup(groupId);

			Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
				group.getCompanyId());

			qPos.add(companyGroup.getGroupId());
		}
		else {
			qPos.add(groupId);
		}
	}

	private LinkedHashMap<String, Object> _emptyLinkedHashMap =
		new LinkedHashMap<String, Object>(0);

}