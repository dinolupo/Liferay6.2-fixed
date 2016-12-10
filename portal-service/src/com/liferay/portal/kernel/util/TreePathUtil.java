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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.TreeModel;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Shinn Lok
 */
public class TreePathUtil {

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #rebuildTree(long, long,
	 *             String, TreeModelFinder<?>)}
	 */
	@Deprecated
	public static void rebuildTree(
			long companyId, long defaultParentPrimaryKey,
			TreeModelFinder<?> treeModelFinder)
		throws SystemException {

		try {
			TreePathUtil.rebuildTree(
				companyId, defaultParentPrimaryKey, StringPool.SLASH,
				treeModelFinder);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}
	}

	public static void rebuildTree(
			long companyId, long parentPrimaryKey, String parentTreePath,
			TreeModelFinder<?> treeModelFinder)
		throws PortalException, SystemException {

		List<TreeModel> modifiedTreeModels = new ArrayList<TreeModel>();

		int size = GetterUtil.getInteger(
			PropsUtil.get(
				PropsKeys.MODEL_TREE_REBUILD_QUERY_RESULTS_BATCH_SIZE));

		Deque<Object[]> traces = new LinkedList<Object[]>();

		traces.push(new Object[] {parentPrimaryKey, parentTreePath, 0L});

		Object[] trace = null;

		while ((trace = traces.poll()) != null) {
			Long curParentPrimaryKey = (Long)trace[0];
			String curParentTreePath = (String)trace[1];
			Long previousPrimaryKey = (Long)trace[2];

			if (curParentTreePath.equals(StringPool.SLASH)) {
				treeModelFinder.rebuildDependentModelsTreePaths(
					curParentPrimaryKey, "/0/");
			}
			else {
				treeModelFinder.rebuildDependentModelsTreePaths(
					curParentPrimaryKey, curParentTreePath);
			}

			List<? extends TreeModel> treeModels =
				treeModelFinder.findTreeModels(
					previousPrimaryKey, companyId, curParentPrimaryKey, size);

			if (treeModels.isEmpty()) {
				continue;
			}

			if (treeModels.size() == size) {
				TreeModel treeModel = treeModels.get(treeModels.size() - 1);

				trace[2] = treeModel.getPrimaryKeyObj();

				traces.push(trace);
			}

			for (TreeModel treeModel : treeModels) {
				String treePath = curParentTreePath.concat(
					String.valueOf(treeModel.getPrimaryKeyObj())).concat(
						StringPool.SLASH);

				treeModel.updateTreePath(treePath);

				traces.push(
					new Object[] {treeModel.getPrimaryKeyObj(), treePath, 0L});

				modifiedTreeModels.add(treeModel);
			}
		}

		treeModelFinder.reindexTreeModels(modifiedTreeModels);
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static void rebuildTree(
		Session session, long companyId, String tableName,
		String parentTableName, String parentPrimaryKeyColumnName,
		boolean statusColumn) {

		rebuildTree(
			session, companyId, tableName, parentTableName,
			parentPrimaryKeyColumnName, statusColumn, false);
		rebuildTree(
			session, companyId, tableName, parentTableName,
			parentPrimaryKeyColumnName, statusColumn, true);
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	protected static void rebuildTree(
		Session session, long companyId, String tableName,
		String parentTableName, String parentPrimaryKeyColumnName,
		boolean statusColumn, boolean rootParent) {

		StringBundler sb = new StringBundler(26);

		sb.append("update ");
		sb.append(tableName);
		sb.append(" set ");

		if (rootParent) {
			sb.append("treePath = '/0/' ");
		}
		else {
			sb.append("treePath = (select ");
			sb.append(parentTableName);
			sb.append(".treePath from ");
			sb.append(parentTableName);
			sb.append(" where ");
			sb.append(parentTableName);
			sb.append(".");
			sb.append(parentPrimaryKeyColumnName);
			sb.append(" = ");
			sb.append(tableName);
			sb.append(".");
			sb.append(parentPrimaryKeyColumnName);
			sb.append(") ");
		}

		sb.append("where (");
		sb.append(tableName);
		sb.append(".companyId = ?) and (");
		sb.append(tableName);
		sb.append(".");
		sb.append(parentPrimaryKeyColumnName);

		if (rootParent) {
			sb.append(" = 0)");
		}
		else {
			sb.append(" != 0)");
		}

		if (statusColumn) {
			sb.append(" and (");
			sb.append(tableName);
			sb.append(".status != ?)");
		}

		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());

		QueryPos qPos = QueryPos.getInstance(sqlQuery);

		qPos.add(companyId);

		if (statusColumn) {
			qPos.add(WorkflowConstants.STATUS_IN_TRASH);
		}

		sqlQuery.executeUpdate();
	}

}