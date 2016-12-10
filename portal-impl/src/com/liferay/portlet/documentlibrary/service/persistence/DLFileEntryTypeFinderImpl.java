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

package com.liferay.portlet.documentlibrary.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.documentlibrary.model.DLFileEntryType;
import com.liferay.portlet.documentlibrary.model.impl.DLFileEntryTypeImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Sergio González
 * @author Connor McKay
 * @author Alexander Chow
 */
public class DLFileEntryTypeFinderImpl
	extends BasePersistenceImpl<DLFileEntryType>
	implements DLFileEntryTypeFinder {

	public static final String COUNT_BY_C_G_N_D_S =
		DLFileEntryTypeFinder.class.getName() + ".countByC_G_N_D_S";

	public static final String FIND_BY_C_G_N_D_S =
		DLFileEntryTypeFinder.class.getName() + ".findByC_G_N_D_S";

	public static final String JOIN_BY_FILE_ENTRY_TYPE =
		DLFileEntryTypeFinder.class.getName() + ".joinByFileEntryType";

	@Override
	public int countByKeywords(
			long companyId, long[] groupIds, String keywords,
			boolean includeBasicFileEntryType)
		throws SystemException {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return doCountByC_G_N_D_S(
			companyId, groupIds, names, descriptions, andOperator,
			includeBasicFileEntryType, false);
	}

	@Override
	public int filterCountByKeywords(
			long companyId, long[] groupIds, String keywords,
			boolean includeBasicFileEntryType)
		throws SystemException {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return doCountByC_G_N_D_S(
			companyId, groupIds, names, descriptions, andOperator,
			includeBasicFileEntryType, true);
	}

	@Override
	public List<DLFileEntryType> filterFindByKeywords(
			long companyId, long[] groupIds, String keywords,
			boolean includeBasicFileEntryType, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return doFindByC_G_N_D_S(
			companyId, groupIds, names, descriptions, andOperator,
			includeBasicFileEntryType, start, end, orderByComparator, true);
	}

	@Override
	public List<DLFileEntryType> findByKeywords(
			long companyId, long[] groupIds, String keywords,
			boolean includeBasicFileEntryType, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return doFindByC_G_N_D_S(
			companyId, groupIds, names, descriptions, andOperator,
			includeBasicFileEntryType, start, end, orderByComparator, false);
	}

	protected int doCountByC_G_N_D_S(
			long companyId, long[] groupIds, String[] names,
			String[] descriptions, boolean andOperator,
			boolean includeBasicFileEntryType, boolean inlineSQLHelper)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_N_D_S);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, DLFileEntryType.class.getName(),
					"DLFileEntryType.fileEntryTypeId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(DLFileEntryType.name)", StringPool.LIKE, false,
				names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, true, descriptions);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			int countValue = 0;

			if (includeBasicFileEntryType) {
				countValue = 1;
			}

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return countValue + count.intValue();
				}
			}

			return countValue;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<DLFileEntryType> doFindByC_G_N_D_S(
			long companyId, long[] groupIds, String[] names,
			String[] descriptions, boolean andOperator,
			boolean includeBasicFileEntryType, int start, int end,
			OrderByComparator orderByComparator, boolean inlineSQLHelper)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_N_D_S);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, DLFileEntryType.class.getName(),
					"DLFileEntryType.fileEntryTypeId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$BASIC_DOCUMENT$]",
				getBasicDocument(includeBasicFileEntryType));
			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(DLFileEntryType.name)", StringPool.LIKE, false,
				names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, true, descriptions);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			if (orderByComparator != null) {
				String orderByFields = StringUtil.merge(
					orderByComparator.getOrderByFields(), StringPool.COMMA);

				sql = StringUtil.replace(
					sql, "DLFileEntryType.name ASC",
					orderByFields.concat(" DESC"));
			}

			if (includeBasicFileEntryType) {
				sql = sql.concat(StringPool.CLOSE_PARENTHESIS);
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("DLFileEntryType", DLFileEntryTypeImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (includeBasicFileEntryType) {
				qPos.add(names, 2);
				qPos.add(descriptions, 2);
			}

			qPos.add(companyId);
			qPos.add(groupIds);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			return (List<DLFileEntryType>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getBasicDocument(boolean includeBasicFileEntryType) {
		if (!includeBasicFileEntryType) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(6);

		sb.append("(SELECT {DLFileEntryType.*} From DLFileEntryType WHERE ");
		sb.append("((DLFileEntryType.companyId = 0) AND (groupId = 0) AND (");
		sb.append(
			"(lower(DLFileEntryType.name) LIKE ? [$AND_OR_NULL_CHECK$]) ");
		sb.append("[$AND_OR_CONNECTOR$] ");
		sb.append("(description LIKE ? [$AND_OR_NULL_CHECK$]) ");
		sb.append("))) UNION ALL (");

		return sb.toString();
	}

	protected String getGroupIds(long[] groupIds) {
		if (groupIds.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(groupIds.length * 2);

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < groupIds.length; i++) {
			sb.append("groupId = ?");

			if ((i + 1) < groupIds.length) {
				sb.append(" OR ");
			}
		}

		sb.append(") AND");

		return sb.toString();
	}

}