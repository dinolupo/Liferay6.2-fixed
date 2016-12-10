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

package com.liferay.portlet.documentlibrary.store;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.ByteArrayFileInputStream;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermQueryFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryThreadLocal;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.DirectoryNameException;
import com.liferay.portlet.documentlibrary.FileExtensionException;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.FileSizeException;
import com.liferay.portlet.documentlibrary.FolderNameException;
import com.liferay.portlet.documentlibrary.InvalidFileVersionException;
import com.liferay.portlet.documentlibrary.SourceFileNameException;
import com.liferay.portlet.documentlibrary.antivirus.AntivirusScannerUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.permission.DLFolderPermission;
import com.liferay.portlet.documentlibrary.util.DLUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Edward Han
 */
public class DLStoreImpl implements DLStore {

	@Override
	public void addDirectory(long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		if (!isValidName(dirName) || dirName.equals("/")) {
			throw new DirectoryNameException(dirName);
		}

		store.addDirectory(companyId, repositoryId, dirName);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName,
			boolean validateFileExtension, byte[] bytes)
		throws PortalException, SystemException {

		validate(fileName, validateFileExtension, bytes);

		if (PropsValues.DL_STORE_ANTIVIRUS_ENABLED) {
			AntivirusScannerUtil.scan(bytes);
		}

		store.addFile(companyId, repositoryId, fileName, bytes);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName,
			boolean validateFileExtension, File file)
		throws PortalException, SystemException {

		validate(fileName, validateFileExtension, file);

		if (PropsValues.DL_STORE_ANTIVIRUS_ENABLED) {
			AntivirusScannerUtil.scan(file);
		}

		store.addFile(companyId, repositoryId, fileName, file);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName,
			boolean validateFileExtension, InputStream is)
		throws PortalException, SystemException {

		if (is instanceof ByteArrayFileInputStream) {
			ByteArrayFileInputStream byteArrayFileInputStream =
				(ByteArrayFileInputStream)is;

			File file = byteArrayFileInputStream.getFile();

			addFile(
				companyId, repositoryId, fileName, validateFileExtension, file);

			return;
		}

		validate(fileName, validateFileExtension, is);

		if (!PropsValues.DL_STORE_ANTIVIRUS_ENABLED ||
			!AntivirusScannerUtil.isActive()) {

			store.addFile(companyId, repositoryId, fileName, is);
		}
		else {
			File tempFile = null;

			try {
				if (is.markSupported()) {
					is.mark(is.available() + 1);

					AntivirusScannerUtil.scan(is);

					is.reset();

					store.addFile(companyId, repositoryId, fileName, is);
				}
				else {
					tempFile = FileUtil.createTempFile();

					FileUtil.write(tempFile, is);

					AntivirusScannerUtil.scan(tempFile);

					store.addFile(companyId, repositoryId, fileName, tempFile);
				}
			}
			catch (IOException ioe) {
				throw new SystemException(
					"Unable to scan file " + fileName, ioe);
			}
			finally {
				if (tempFile != null) {
					tempFile.delete();
				}
			}
		}
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName, byte[] bytes)
		throws PortalException, SystemException {

		addFile(companyId, repositoryId, fileName, true, bytes);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName, File file)
		throws PortalException, SystemException {

		addFile(companyId, repositoryId, fileName, true, file);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName, InputStream is)
		throws PortalException, SystemException {

		addFile(companyId, repositoryId, fileName, true, is);
	}

	@Override
	public void checkRoot(long companyId) throws SystemException {
		store.checkRoot(companyId);
	}

	@Override
	public void copyFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws PortalException, SystemException {

		store.copyFileVersion(
			companyId, repositoryId, fileName, fromVersionLabel,
			toVersionLabel);
	}

	@Override
	public void deleteDirectory(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		store.deleteDirectory(companyId, repositoryId, dirName);
	}

	@Override
	public void deleteFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		validate(fileName, false);

		store.deleteFile(companyId, repositoryId, fileName);
	}

	@Override
	public void deleteFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		validate(fileName, false, versionLabel);

		store.deleteFile(companyId, repositoryId, fileName, versionLabel);
	}

	@Override
	public File getFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		validate(fileName, false);

		return store.getFile(companyId, repositoryId, fileName);
	}

	@Override
	public File getFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		validate(fileName, false, versionLabel);

		return store.getFile(companyId, repositoryId, fileName, versionLabel);
	}

	@Override
	public byte[] getFileAsBytes(
			long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		validate(fileName, false);

		return store.getFileAsBytes(companyId, repositoryId, fileName);
	}

	@Override
	public byte[] getFileAsBytes(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		validate(fileName, false, versionLabel);

		return store.getFileAsBytes(
			companyId, repositoryId, fileName, versionLabel);
	}

	@Override
	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		validate(fileName, false);

		return store.getFileAsStream(companyId, repositoryId, fileName);
	}

	@Override
	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		validate(fileName, false, versionLabel);

		return store.getFileAsStream(
			companyId, repositoryId, fileName, versionLabel);
	}

	@Override
	public String[] getFileNames(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		if (!isValidName(dirName)) {
			throw new DirectoryNameException(dirName);
		}

		return store.getFileNames(companyId, repositoryId, dirName);
	}

	@Override
	public long getFileSize(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		validate(fileName, false);

		return store.getFileSize(companyId, repositoryId, fileName);
	}

	@Override
	public boolean hasDirectory(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		if (!isValidName(dirName)) {
			throw new DirectoryNameException(dirName);
		}

		return store.hasDirectory(companyId, repositoryId, dirName);
	}

	@Override
	public boolean hasFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		validate(fileName, false);

		return store.hasFile(companyId, repositoryId, fileName);
	}

	@Override
	public boolean hasFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		validate(fileName, false, versionLabel);

		return store.hasFile(companyId, repositoryId, fileName, versionLabel);
	}

	@Override
	public boolean isValidName(String name) {
		if (Validator.isNull(name)) {
			return false;
		}

		for (String blacklistChar : PropsValues.DL_CHAR_BLACKLIST) {
			if (name.contains(blacklistChar)) {
				return false;
			}
		}

		for (String blacklistLastChar : PropsValues.DL_CHAR_LAST_BLACKLIST) {
			if (blacklistLastChar.startsWith(_UNICODE_PREFIX)) {
				blacklistLastChar = UnicodeFormatter.parseString(
					blacklistLastChar);
			}

			if (name.endsWith(blacklistLastChar)) {
				return false;
			}
		}

		String nameWithoutExtension = name;

		if (name.contains(StringPool.PERIOD)) {
			int index = name.lastIndexOf(StringPool.PERIOD);

			nameWithoutExtension = name.substring(0, index);
		}

		for (String blacklistName : PropsValues.DL_NAME_BLACKLIST) {
			if (StringUtil.equalsIgnoreCase(
					nameWithoutExtension, blacklistName)) {

				return false;
			}
		}

		return true;
	}

	@Override
	public void move(String srcDir, String destDir) throws SystemException {
		store.move(srcDir, destDir);
	}

	public Hits search(
			long companyId, long userId, String portletId, long groupId,
			long[] repositoryIds, String keywords, int start, int end)
		throws SystemException {

		try {
			SearchContext searchContext = new SearchContext();

			searchContext.setCompanyId(companyId);
			searchContext.setEnd(end);
			searchContext.setEntryClassNames(
				new String[] {DLFileEntryConstants.getClassName()});
			searchContext.setGroupIds(new long[] {groupId});

			Indexer indexer = IndexerRegistryUtil.getIndexer(
				DLFileEntryConstants.getClassName());

			searchContext.setSearchEngineId(indexer.getSearchEngineId());

			searchContext.setStart(start);
			searchContext.setUserId(userId);

			BooleanQuery contextQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			contextQuery.addRequiredTerm(Field.PORTLET_ID, portletId);

			if (groupId > 0) {
				Group group = groupLocalService.getGroup(groupId);

				if (group.isLayout()) {
					contextQuery.addRequiredTerm(Field.SCOPE_GROUP_ID, groupId);

					groupId = group.getParentGroupId();
				}

				contextQuery.addRequiredTerm(Field.GROUP_ID, groupId);
			}

			if (ArrayUtil.isNotEmpty(repositoryIds)) {
				BooleanQuery repositoryIdsQuery =
					BooleanQueryFactoryUtil.create(searchContext);

				for (long repositoryId : repositoryIds) {
					try {
						if (userId > 0) {
							PermissionChecker permissionChecker =
								PermissionThreadLocal.getPermissionChecker();

							DLFolderPermission.check(
								permissionChecker, groupId, repositoryId,
								ActionKeys.VIEW);
						}

						if (repositoryId ==
								DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

							repositoryId = groupId;
						}

						TermQuery termQuery = TermQueryFactoryUtil.create(
							searchContext, "repositoryId", repositoryId);

						repositoryIdsQuery.add(
							termQuery, BooleanClauseOccur.SHOULD);
					}
					catch (Exception e) {
					}
				}

				contextQuery.add(repositoryIdsQuery, BooleanClauseOccur.MUST);
			}

			BooleanQuery searchQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			searchQuery.addTerms(_KEYWORDS_FIELDS, keywords);

			BooleanQuery fullQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			fullQuery.add(contextQuery, BooleanClauseOccur.MUST);

			if (searchQuery.clauses().size() > 0) {
				fullQuery.add(searchQuery, BooleanClauseOccur.MUST);
			}

			return SearchEngineUtil.search(searchContext, fullQuery);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, long newRepositoryId,
			String fileName)
		throws PortalException, SystemException {

		store.updateFile(companyId, repositoryId, newRepositoryId, fileName);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String newFileName)
		throws PortalException, SystemException {

		store.updateFile(companyId, repositoryId, fileName, newFileName);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String fileExtension, boolean validateFileExtension,
			String versionLabel, String sourceFileName, File file)
		throws PortalException, SystemException {

		validate(
			fileName, fileExtension, sourceFileName, validateFileExtension,
			file, versionLabel);

		if (PropsValues.DL_STORE_ANTIVIRUS_ENABLED) {
			AntivirusScannerUtil.scan(file);
		}

		store.updateFile(companyId, repositoryId, fileName, versionLabel, file);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String fileExtension, boolean validateFileExtension,
			String versionLabel, String sourceFileName, InputStream is)
		throws PortalException, SystemException {

		if (is instanceof ByteArrayFileInputStream) {
			ByteArrayFileInputStream byteArrayFileInputStream =
				(ByteArrayFileInputStream)is;

			File file = byteArrayFileInputStream.getFile();

			updateFile(
				companyId, repositoryId, fileName, fileExtension,
				validateFileExtension, versionLabel, sourceFileName, file);

			return;
		}

		validate(
			fileName, fileExtension, sourceFileName, validateFileExtension, is,
			versionLabel);

		if (!PropsValues.DL_STORE_ANTIVIRUS_ENABLED ||
			!AntivirusScannerUtil.isActive()) {

			store.updateFile(
				companyId, repositoryId, fileName, versionLabel, is);
		}
		else {
			File tempFile = null;

			try {
				if (is.markSupported()) {
					is.mark(is.available() + 1);

					AntivirusScannerUtil.scan(is);

					is.reset();

					store.updateFile(
						companyId, repositoryId, fileName, versionLabel, is);
				}
				else {
					tempFile = FileUtil.createTempFile();

					FileUtil.write(tempFile, is);

					AntivirusScannerUtil.scan(tempFile);

					store.updateFile(
						companyId, repositoryId, fileName, versionLabel,
						tempFile);
				}
			}
			catch (IOException ioe) {
				throw new SystemException(
					"Unable to scan file " + fileName, ioe);
			}
			finally {
				if (tempFile != null) {
					tempFile.delete();
				}
			}
		}
	}

	@Override
	public void updateFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws PortalException, SystemException {

		store.updateFileVersion(
			companyId, repositoryId, fileName, fromVersionLabel,
			toVersionLabel);
	}

	@Override
	public void validate(String fileName, boolean validateFileExtension)
		throws PortalException, SystemException {

		if (!isValidName(fileName)) {
			throw new FileNameException(fileName);
		}

		if (!validateFileExtension) {
			return;
		}

		Thread currentThread = Thread.currentThread();

		StackTraceElement[] stackTraceElements = currentThread.getStackTrace();

		for (StackTraceElement stackTraceElement : stackTraceElements) {
			String className = stackTraceElement.getClassName();

			if (className.startsWith("com.liferay.") &&
				(className.endsWith("EditLayoutSetAction") ||
				 className.endsWith("EditUserPortraitAction") ||
				 className.endsWith("LogoAction") ||
				 className.endsWith("PortletFileRepositoryImpl")||
				 className.endsWith("TempFileUtil"))) {

				return;
			}
		}

		boolean validFileExtension = false;

		String[] fileExtensions = PrefsPropsUtil.getStringArray(
			PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA);

		for (String fileExtension : fileExtensions) {
			if (StringPool.STAR.equals(fileExtension) ||
				StringUtil.endsWith(fileName, fileExtension)) {

				validFileExtension = true;

				break;
			}
		}

		if (!validFileExtension) {
			throw new FileExtensionException(fileName);
		}
	}

	@Override
	public void validate(
			String fileName, boolean validateFileExtension, byte[] bytes)
		throws PortalException, SystemException {

		validate(fileName, validateFileExtension);

		if (PortletFileRepositoryThreadLocal.isFileMaxSizeCheckEnabled() &&
			(PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
			((bytes == null) ||
			 (bytes.length >
				 PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE)))) {

			throw new FileSizeException(fileName);
		}
	}

	@Override
	public void validate(
			String fileName, boolean validateFileExtension, File file)
		throws PortalException, SystemException {

		validate(fileName, validateFileExtension);

		if (PortletFileRepositoryThreadLocal.isFileMaxSizeCheckEnabled() &&
			(PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
			((file == null) ||
			 (file.length() >
				PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE)))) {

			throw new FileSizeException(fileName);
		}
	}

	@Override
	public void validate(
			String fileName, boolean validateFileExtension, InputStream is)
		throws PortalException, SystemException {

		validate(fileName, validateFileExtension);

		// LEP-4851

		try {
			if (PortletFileRepositoryThreadLocal.isFileMaxSizeCheckEnabled() &&
				((is == null) ||
				 ((PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
				  (is.available() >
					PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE))))) {

				throw new FileSizeException(fileName);
			}
		}
		catch (IOException ioe) {
			throw new FileSizeException(ioe.getMessage());
		}
	}

	@Override
	public void validate(
			String fileName, String fileExtension, String sourceFileName,
			boolean validateFileExtension, File file)
		throws PortalException, SystemException {

		validate(
			fileName, fileExtension, sourceFileName, validateFileExtension,
			StringPool.BLANK);

		if (PortletFileRepositoryThreadLocal.isFileMaxSizeCheckEnabled() &&
			(file != null) &&
			(PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
			(file.length() >
				PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE))) {

			throw new FileSizeException(fileName);
		}
	}

	@Override
	public void validate(
			String fileName, String fileExtension, String sourceFileName,
			boolean validateFileExtension, InputStream is)
		throws PortalException, SystemException {

		validate(
			fileName, fileExtension, sourceFileName, validateFileExtension,
			StringPool.BLANK);

		try {
			if (PortletFileRepositoryThreadLocal.isFileMaxSizeCheckEnabled() &&
				(is != null) &&
				(PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
				(is.available() >
					PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE))) {

				throw new FileSizeException(fileName);
			}
		}
		catch (IOException ioe) {
			throw new FileSizeException(ioe.getMessage());
		}
	}

	@Override
	public void validateDirectoryName(String directoryName)
		throws PortalException {

		if (!isValidName(directoryName)) {
			throw new FolderNameException(directoryName);
		}
	}

	protected void isValidVersion(String versionLabel) throws PortalException {
		if (Validator.isNull(versionLabel)) {
			return;
		}

		if (!DLUtil.isValidVersion(versionLabel)) {
			throw new InvalidFileVersionException();
		}
	}

	protected void validate(
			String fileName, boolean validateFileExtension, String versionLabel)
		throws PortalException, SystemException {

		isValidVersion(versionLabel);

		validate(fileName, validateFileExtension);
	}

	protected void validate(
			String fileName, String fileExtension, String sourceFileName,
			boolean validateFileExtension, File file, String versionLabel)
		throws PortalException, SystemException {

		isValidVersion(versionLabel);

		validate(
			fileName, fileExtension, sourceFileName, validateFileExtension,
			file);
	}

	protected void validate(
			String fileName, String fileExtension, String sourceFileName,
			boolean validateFileExtension, InputStream is, String versionLabel)
		throws PortalException, SystemException {

		isValidVersion(versionLabel);

		validate(
			fileName, fileExtension, sourceFileName, validateFileExtension, is);
	}

	protected void validate(
			String fileName, String fileExtension, String sourceFileName,
			boolean validateFileExtension, String versionLabel)
		throws PortalException, SystemException {

		String sourceFileExtension = FileUtil.getExtension(sourceFileName);

		if (Validator.isNotNull(sourceFileName) &&
			PropsValues.DL_FILE_EXTENSIONS_STRICT_CHECK &&
			!fileExtension.equals(sourceFileExtension)) {

			throw new SourceFileNameException(sourceFileExtension);
		}

		validate(fileName, validateFileExtension, versionLabel);
	}

	@BeanReference(type = GroupLocalService.class)
	protected GroupLocalService groupLocalService;

	@BeanReference(type = Store.class)
	protected Store store;

	private static final String[] _KEYWORDS_FIELDS = {
		Field.ASSET_TAG_NAMES, Field.CONTENT, Field.PROPERTIES
	};

	private static final String _UNICODE_PREFIX = "\\u";

}