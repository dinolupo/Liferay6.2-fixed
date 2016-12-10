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

package com.liferay.portal.dao.db;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.Index;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Chow
 * @author Sandeep Soni
 * @author Ganesh Ram
 */
public class PostgreSQLDB extends BaseDB {

	public static DB getInstance() {
		return _instance;
	}

	@Override
	public String buildSQL(String template) throws IOException {
		template = convertTimestamp(template);
		template = replaceTemplate(template, getTemplate());

		template = reword(template);

		return template;
	}

	@Override
	public List<Index> getIndexes(Connection con) throws SQLException {
		List<Index> indexes = new ArrayList<Index>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBundler sb = new StringBundler(3);

			sb.append("select indexname, tablename, indexdef from pg_indexes ");
			sb.append("where indexname like 'liferay_%' or indexname like ");
			sb.append("'ix_%'");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				String indexName = rs.getString("indexname");
				String tableName = rs.getString("tablename");
				String indexSQL = StringUtil.toLowerCase(
					rs.getString("indexdef").trim());

				boolean unique = true;

				if (indexSQL.startsWith("create index ")) {
					unique = false;
				}

				indexes.add(new Index(indexName, tableName, unique));
			}
		}
		finally {
			DataAccess.cleanUp(null, ps, rs);
		}

		return indexes;
	}

	@Override
	public boolean isSupportsQueryingAfterException() {
		return _SUPPORTS_QUERYING_AFTER_EXCEPTION;
	}

	protected PostgreSQLDB() {
		super(TYPE_POSTGRESQL);
	}

	@Override
	protected String buildCreateFileContent(
			String sqlDir, String databaseName, int population)
		throws IOException {

		String suffix = getSuffix(population);

		StringBundler sb = new StringBundler(14);

		sb.append("drop database ");
		sb.append(databaseName);
		sb.append(";\n");
		sb.append("create database ");
		sb.append(databaseName);
		sb.append(" encoding = 'UNICODE';\n");

		if (population != BARE) {
			sb.append("\\c ");
			sb.append(databaseName);
			sb.append(";\n\n");
			sb.append(getCreateTablesContent(sqlDir, suffix));
			sb.append("\n\n");
			sb.append(readFile(sqlDir + "/indexes/indexes-postgresql.sql"));
			sb.append("\n\n");
			sb.append(readFile(sqlDir + "/sequences/sequences-postgresql.sql"));
		}

		return sb.toString();
	}

	@Override
	protected String getServerName() {
		return "postgresql";
	}

	@Override
	protected String[] getTemplate() {
		return _POSTGRESQL;
	}

	@Override
	protected String reword(String data) throws IOException {
		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(data));

		StringBundler sb = new StringBundler();

		String line = null;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			if (line.startsWith(ALTER_COLUMN_NAME)) {
				String[] template = buildColumnNameTokens(line);

				line = StringUtil.replace(
					"alter table @table@ rename @old-column@ to @new-column@;",
					REWORD_TEMPLATE, template);
			}
			else if (line.startsWith(ALTER_COLUMN_TYPE)) {
				String[] template = buildColumnTypeTokens(line);

				line = StringUtil.replace(
					"alter table @table@ alter @old-column@ type @type@ " +
						"using @old-column@::@type@;",
					REWORD_TEMPLATE, template);
			}
			else if (line.startsWith(ALTER_TABLE_NAME)) {
				String[] template = buildTableNameTokens(line);

				line = StringUtil.replace(
					"alter table @old-table@ rename to @new-table@;",
					RENAME_TABLE_TEMPLATE, template);
			}
			else if (line.contains(DROP_INDEX)) {
				String[] tokens = StringUtil.split(line, ' ');

				line = StringUtil.replace(
					"drop index @index@;", "@index@", tokens[2]);
			}
			else if (line.contains(DROP_PRIMARY_KEY)) {
				String[] tokens = StringUtil.split(line, ' ');

				line = StringUtil.replace(
					"alter table @table@ drop constraint @table@_pkey;",
					"@table@", tokens[2]);
			}
			else if (line.contains("\\\'")) {
				line = StringUtil.replace(line, "\\\'", "\'\'");
			}

			sb.append(line);
			sb.append("\n");
		}

		unsyncBufferedReader.close();

		return sb.toString();
	}

	private static final String[] _POSTGRESQL = {
		"--", "true", "false", "'01/01/1970'", "current_timestamp", " oid",
		" bytea", " bool", " timestamp", " double precision", " integer",
		" bigint", " text", " text", " varchar", "", "commit"
	};

	private static final boolean _SUPPORTS_QUERYING_AFTER_EXCEPTION = false;

	private static PostgreSQLDB _instance = new PostgreSQLDB();

}