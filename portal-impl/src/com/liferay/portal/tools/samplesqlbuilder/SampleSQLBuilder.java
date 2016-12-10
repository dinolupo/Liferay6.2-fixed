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

package com.liferay.portal.tools.samplesqlbuilder;

import com.liferay.portal.dao.db.MySQLDB;
import com.liferay.portal.freemarker.FreeMarkerUtil;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.io.CharPipe;
import com.liferay.portal.kernel.io.OutputStreamWriter;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedWriter;
import com.liferay.portal.kernel.io.unsync.UnsyncTeeWriter;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.SortedProperties;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.InitUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import java.nio.channels.FileChannel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class SampleSQLBuilder {

	public static void main(String[] args) {
		InitUtil.initWithSpring();

		Reader reader = null;

		try {
			Properties properties = new SortedProperties();

			reader = new FileReader(args[0]);

			properties.load(reader);

			DataFactory dataFactory = new DataFactory(properties);

			new SampleSQLBuilder(properties, dataFactory);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	public SampleSQLBuilder(Properties properties, DataFactory dataFactory)
		throws Exception {

		_dbType = properties.getProperty("sample.sql.db.type");

		_csvFileNames = StringUtil.split(
			properties.getProperty("sample.sql.output.csv.file.names"));
		_optimizeBufferSize = GetterUtil.getInteger(
			properties.getProperty("sample.sql.optimize.buffer.size"));
		_outputDir = properties.getProperty("sample.sql.output.dir");
		_script = properties.getProperty("sample.sql.script");

		_dataFactory = dataFactory;

		// Generic

		Reader reader = generateSQL();

		File tempDir = new File(_outputDir, "temp");

		tempDir.mkdirs();

		try {

			// Specific

			compressSQL(reader, tempDir);

			// Merge

			boolean outputMerge = GetterUtil.getBoolean(
				properties.getProperty("sample.sql.output.merge"));

			if (outputMerge) {
				File sqlFile = new File(
					_outputDir, "sample-" + _dbType + ".sql");

				FileUtil.delete(sqlFile);

				mergeSQL(tempDir, sqlFile);
			}
			else {
				File outputDir = new File(_outputDir, "output");

				FileUtil.deltree(outputDir);

				if (!tempDir.renameTo(outputDir)) {

					// This will only happen when temp and output directories
					// are on different file systems

					FileUtil.copyDirectory(tempDir, outputDir);
				}
			}
		}
		finally {
			FileUtil.deltree(tempDir);
		}

		StringBundler sb = new StringBundler();

		for (String key : properties.stringPropertyNames()) {
			if (!key.startsWith("sample.sql")) {
				continue;
			}

			String value = properties.getProperty(key);

			sb.append(key);
			sb.append(StringPool.EQUAL);
			sb.append(value);
			sb.append(StringPool.NEW_LINE);
		}

		FileUtil.write(
			new File(_outputDir, "benchmarks-actual.properties"),
			sb.toString());
	}

	protected void compressSQL(
			DB db, File directory, Map<String, Writer> insertSQLWriters,
			Map<String, StringBundler> sqls, String insertSQL)
		throws IOException {

		String tableName = insertSQL.substring(0, insertSQL.indexOf(' '));

		int index = insertSQL.indexOf(" values ") + 8;

		StringBundler sb = sqls.get(tableName);

		if ((sb == null) || (sb.index() == 0)) {
			sb = new StringBundler();

			sqls.put(tableName, sb);

			sb.append("insert into ");
			sb.append(insertSQL.substring(0, index));
			sb.append("\n");
		}
		else {
			sb.append(",\n");
		}

		String values = insertSQL.substring(index, insertSQL.length() - 1);

		sb.append(values);

		if (sb.index() >= _optimizeBufferSize) {
			sb.append(";\n");

			insertSQL = db.buildSQL(sb.toString());

			sb.setIndex(0);

			writeToInsertSQLFile(
				directory, tableName, insertSQLWriters, insertSQL);
		}
	}

	protected void compressSQL(Reader reader, File dir) throws IOException {
		DB db = DBFactoryUtil.getDB(_dbType);

		if (db instanceof MySQLDB) {
			db = new SampleMySQLDB();
		}

		Map<String, Writer> insertSQLWriters = new HashMap<String, Writer>();
		Map<String, StringBundler> insertSQLs =
			new HashMap<String, StringBundler>();
		List<String> miscSQLs = new ArrayList<String>();

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			reader);

		String s = null;

		while ((s = unsyncBufferedReader.readLine()) != null) {
			s = s.trim();

			if (s.length() > 0) {
				if (s.startsWith("insert into ")) {
					compressSQL(
						db, dir, insertSQLWriters, insertSQLs, s.substring(12));
				}
				else {
					miscSQLs.add(s);
				}
			}
		}

		unsyncBufferedReader.close();

		for (Map.Entry<String, StringBundler> entry : insertSQLs.entrySet()) {
			String tableName = entry.getKey();
			StringBundler sb = entry.getValue();

			if (sb.index() == 0) {
				continue;
			}

			String insertSQL = db.buildSQL(sb.toString());

			writeToInsertSQLFile(dir, tableName, insertSQLWriters, insertSQL);

			Writer insertSQLWriter = insertSQLWriters.remove(tableName);

			insertSQLWriter.write(";\n");

			insertSQLWriter.close();
		}

		Writer miscSQLWriter = new FileWriter(new File(dir, "misc.sql"));

		for (String miscSQL : miscSQLs) {
			miscSQL = db.buildSQL(miscSQL);

			miscSQLWriter.write(miscSQL);
			miscSQLWriter.write(StringPool.NEW_LINE);
		}

		miscSQLWriter.close();
	}

	protected Writer createFileWriter(File file) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(file);

		Writer writer = new OutputStreamWriter(fileOutputStream);

		return createUnsyncBufferedWriter(writer);
	}

	protected Writer createUnsyncBufferedWriter(Writer writer) {
		return new UnsyncBufferedWriter(writer, _WRITER_BUFFER_SIZE) {

			@Override
			public void flush() {

				// Disable FreeMarker from flushing

			}

		};
	}

	protected Reader generateSQL() {
		final CharPipe charPipe = new CharPipe(_PIPE_BUFFER_SIZE);

		Thread thread = new Thread() {

			@Override
			public void run() {
				try {
					Writer sampleSQLWriter = new UnsyncTeeWriter(
						createUnsyncBufferedWriter(charPipe.getWriter()),
						createFileWriter(new File(_outputDir, "sample.sql")));

					Map<String, Object> context = getContext();

					FreeMarkerUtil.process(_script, context, sampleSQLWriter);

					for (String csvFileName : _csvFileNames) {
						Writer csvWriter = (Writer)context.get(
							csvFileName + "CSVWriter");

						csvWriter.close();
					}

					sampleSQLWriter.close();

					charPipe.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}

		};

		thread.start();

		return charPipe.getReader();
	}

	protected Map<String, Object> getContext() throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();

		context.put("dataFactory", _dataFactory);

		for (String csvFileName : _csvFileNames) {
			Writer csvWriter = createFileWriter(
				new File(_outputDir, csvFileName + ".csv"));

			context.put(csvFileName + "CSVWriter", csvWriter);
		}

		return context;
	}

	protected Properties getProperties(String[] args) throws Exception {
		Reader reader = null;

		try {
			Properties properties = new SortedProperties();

			reader = new FileReader(args[0]);

			properties.load(reader);

			return properties;
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	protected void mergeSQL(File inputDir, File outputSQLFile)
		throws IOException {

		FileOutputStream outputSQLFileOutputStream = new FileOutputStream(
			outputSQLFile);

		FileChannel outputFileChannel = outputSQLFileOutputStream.getChannel();

		File miscSQLFile = null;

		for (File inputFile : inputDir.listFiles()) {
			String inputFileName = inputFile.getName();

			if (inputFileName.equals("misc.sql")) {
				miscSQLFile = inputFile;

				continue;
			}

			mergeSQL(inputFile, outputFileChannel);
		}

		if (miscSQLFile != null) {
			mergeSQL(miscSQLFile, outputFileChannel);
		}

		outputFileChannel.close();
	}

	protected void mergeSQL(File inputFile, FileChannel outputFileChannel)
		throws IOException {

		FileInputStream inputFileInputStream = new FileInputStream(inputFile);

		FileChannel inputFileChannel = inputFileInputStream.getChannel();

		inputFileChannel.transferTo(
			0, inputFileChannel.size(), outputFileChannel);

		inputFileChannel.close();

		inputFile.delete();
	}

	protected void writeToInsertSQLFile(
			File dir, String tableName, Map<String, Writer> insertSQLWriters,
			String insertSQL)
		throws IOException {

		Writer insertSQLWriter = insertSQLWriters.get(tableName);

		if (insertSQLWriter == null) {
			File file = new File(dir, tableName + ".sql");

			insertSQLWriter = createFileWriter(file);

			insertSQLWriters.put(tableName, insertSQLWriter);
		}

		insertSQLWriter.write(insertSQL);
	}

	private static final int _PIPE_BUFFER_SIZE = 16 * 1024 * 1024;

	private static final int _WRITER_BUFFER_SIZE = 16 * 1024;

	private String[] _csvFileNames;
	private DataFactory _dataFactory;
	private String _dbType;
	private int _optimizeBufferSize;
	private String _outputDir;
	private String _script;

}