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

package com.liferay.portal.repository.cmis;

import com.liferay.portal.NoSuchRepositoryEntryException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.cmis.BaseCmisRepository;
import com.liferay.portal.kernel.repository.cmis.CMISRepositoryHandler;
import com.liferay.portal.kernel.repository.cmis.search.CMISSearchQueryBuilderUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsImpl;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.repository.cmis.model.CMISFileEntry;
import com.liferay.portal.repository.cmis.model.CMISFileVersion;
import com.liferay.portal.repository.cmis.model.CMISFolder;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.RepositoryEntryLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.persistence.RepositoryEntryUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.DuplicateFolderNameException;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFileVersionException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.persistence.DLFolderUtil;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelCreateDateComparator;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelModifiedDateComparator;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelNameComparator;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelSizeComparator;

import java.io.InputStream;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.AllowableActions;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.data.PropertyData;
import org.apache.chemistry.opencmis.commons.data.RepositoryCapabilities;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.Action;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.enums.CapabilityQuery;
import org.apache.chemistry.opencmis.commons.enums.UnfileObject;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisPermissionDeniedException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisRuntimeException;
import org.apache.chemistry.opencmis.commons.impl.Base64;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;

/**
 * CMIS does not provide vendor neutral support for workflow, metadata, tags,
 * categories, etc. They will be ignored in this implementation.
 *
 * @author Alexander Chow
 * @see    <a href="http://wiki.oasis-open.org/cmis/Candidate%20v2%20topics">
 *         Candidate v2 topics</a>
 * @see    <a href="http://wiki.oasis-open.org/cmis/Mixin_Proposal">Mixin /
 *         Aspect Support</a>
 * @see    <a
 *         href="http://www.oasis-open.org/committees/document.php?document_id=39631">
 *         CMIS Type Mutability proposal</a>
 */
public class CMISRepository extends BaseCmisRepository {

	public CMISRepository(CMISRepositoryHandler cmisRepositoryHandler) {
		_cmisRepositoryHandler = cmisRepositoryHandler;
	}

	@Override
	public FileEntry addFileEntry(
			long folderId, String sourceFileName, String mimeType, String title,
			String description, String changeLog, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (Validator.isNull(title)) {
			if (size == 0) {
				throw new FileNameException();
			}
			else {
				title = sourceFileName;
			}
		}

		try {
			Session session = getSession();

			validateTitle(session, folderId, title);

			org.apache.chemistry.opencmis.client.api.Folder cmisFolder =
				getCmisFolder(session, folderId);

			Map<String, Object> properties = new HashMap<String, Object>();

			properties.put(PropertyIds.NAME, title);
			properties.put(
				PropertyIds.OBJECT_TYPE_ID, BaseTypeId.CMIS_DOCUMENT.value());

			ContentStream contentStream = new ContentStreamImpl(
				title, BigInteger.valueOf(size), mimeType, is);

			Document document = null;

			if (_cmisRepositoryDetector.isNuxeo5_5OrHigher()) {
				document = cmisFolder.createDocument(
					properties, contentStream, VersioningState.NONE);

				document.checkIn(
					true, Collections.EMPTY_MAP, null, StringPool.BLANK);
			}
			else {
				document = cmisFolder.createDocument(
					properties, contentStream, null);
			}

			return toFileEntry(document);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public Folder addFolder(
			long parentFolderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			validateTitle(session, parentFolderId, title);

			org.apache.chemistry.opencmis.client.api.Folder cmisFolder =
				getCmisFolder(session, parentFolderId);

			Map<String, Object> properties = new HashMap<String, Object>();

			properties.put(PropertyIds.NAME, title);
			properties.put(
				PropertyIds.OBJECT_TYPE_ID, BaseTypeId.CMIS_FOLDER.value());

			return toFolder(cmisFolder.createFolder(properties));
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public FileVersion cancelCheckOut(long fileEntryId) throws SystemException {
		Document draftDocument = null;

		try {
			Session session = getSession();

			String versionSeriesId = toFileEntryId(fileEntryId);

			Document document = (Document)session.getObject(versionSeriesId);

			document.refresh();

			String versionSeriesCheckedOutId =
				document.getVersionSeriesCheckedOutId();

			if (Validator.isNotNull(versionSeriesCheckedOutId)) {
				draftDocument = (Document)session.getObject(
					versionSeriesCheckedOutId);

				draftDocument.cancelCheckOut();

				document = (Document)session.getObject(versionSeriesId);

				document.refresh();
			}
		}
		catch (Exception e) {
			_log.error(
				"Unable to cancel checkout for file entry with {fileEntryId=" +
					fileEntryId + "}",
				e);
		}

		if (draftDocument != null) {
			return toFileVersion(draftDocument);
		}

		return null;
	}

	@Override
	public void checkInFileEntry(
		long fileEntryId, boolean major, String changeLog,
		ServiceContext serviceContext) {

		try {
			clearManualCheckInRequired(fileEntryId, serviceContext);

			Session session = getSession();

			String versionSeriesId = toFileEntryId(fileEntryId);

			Document document = (Document)session.getObject(versionSeriesId);

			document.refresh();

			String versionSeriesCheckedOutId =
				document.getVersionSeriesCheckedOutId();

			if (Validator.isNotNull(versionSeriesCheckedOutId)) {
				if (!isSupportsMinorVersions()) {
					major = true;
				}

				document = (Document)session.getObject(
					versionSeriesCheckedOutId);

				document.checkIn(major, null, null, changeLog);

				document = (Document)session.getObject(versionSeriesId);

				document.refresh();
			}
		}
		catch (Exception e) {
			_log.error(
				"Unable to check in file entry with {fileEntryId=" +
				fileEntryId + "}", e);
		}
	}

	@Override
	public void checkInFileEntry(
		long fileEntryId, String lockUuid, ServiceContext serviceContext) {

		checkInFileEntry(fileEntryId, false, StringPool.BLANK, serviceContext);
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			setManualCheckInRequired(fileEntryId, serviceContext);

			Session session = getSession();

			String versionSeriesId = toFileEntryId(fileEntryId);

			Document document = (Document)session.getObject(versionSeriesId);

			document.refresh();

			document.checkOut();

			document = (Document)session.getObject(versionSeriesId);

			document.refresh();
		}
		catch (Exception e) {
			_log.error(
				"Unable checkout file entry with {fileEntryId=" + fileEntryId +
					"}",
				e);
		}

		return getFileEntry(fileEntryId);
	}

	@Override
	public FileEntry checkOutFileEntry(
		long fileEntryId, String owner, long expirationTime,
		ServiceContext serviceContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry copyFileEntry(
			long groupId, long fileEntryId, long destFolderId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			Document document = getDocument(session, fileEntryId);

			validateTitle(session, destFolderId, document.getName());

			String destFolderObjectId = toFolderId(session, destFolderId);

			Document newDocument = document.copy(
				new ObjectIdImpl(destFolderObjectId));

			return toFileEntry(newDocument);
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFolderException(
				"No CMIS folder with {folderId=" + destFolderId + "}", confe);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			Document document = getDocument(session, fileEntryId);

			deleteMappedFileEntry(document);

			document.deleteAllVersions();
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			org.apache.chemistry.opencmis.client.api.Folder cmisFolder =
				getCmisFolder(session, folderId);

			deleteMappedFolder(cmisFolder);

			cmisFolder.deleteTree(true, UnfileObject.DELETE, false);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws SystemException {

		List<FileEntry> fileEntries = getFileEntries(folderId);

		return subList(fileEntries, start, end, obc);
	}

	@Override
	public List<FileEntry> getFileEntries(
		long folderId, long fileEntryTypeId, int start, int end,
		OrderByComparator obc) {

		return new ArrayList<FileEntry>();
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		Map<Long, List<FileEntry>> fileEntriesCache = _fileEntriesCache.get();

		List<FileEntry> fileEntries = fileEntriesCache.get(folderId);

		if ((fileEntries == null) || (mimeTypes != null)) {
			fileEntries = new ArrayList<FileEntry>();

			List<String> documentIds = getDocumentIds(
				getSession(), folderId, mimeTypes);

			for (String documentId : documentIds) {
				FileEntry fileEntry = toFileEntry(documentId);

				fileEntries.add(fileEntry);
			}

			if (mimeTypes == null) {
				fileEntriesCache.put(folderId, fileEntries);
			}
		}

		return subList(fileEntries, start, end, obc);
	}

	@Override
	public int getFileEntriesCount(long folderId) throws SystemException {
		List<FileEntry> fileEntries = getFileEntries(folderId);

		return fileEntries.size();
	}

	@Override
	public int getFileEntriesCount(long folderId, long fileEntryTypeId) {
		List<FileEntry> fileEntries = getFileEntries(folderId, fileEntryTypeId);

		return fileEntries.size();
	}

	@Override
	public int getFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		Session session = getSession();

		List<String> documentIds = getDocumentIds(session, folderId, mimeTypes);

		return documentIds.size();
	}

	@Override
	public FileEntry getFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			Document document = getDocument(session, fileEntryId);

			return toFileEntry(document);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public FileEntry getFileEntry(long folderId, String title)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			String objectId = getObjectId(session, folderId, true, title);

			if (objectId != null) {
				CmisObject cmisObject = session.getObject(objectId);

				Document document = (Document)cmisObject;

				return toFileEntry(document);
			}
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFileEntryException(
				"No CMIS file entry with {folderId=" + folderId + ", title=" +
					title + "}",
				confe);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}

		throw new NoSuchFileEntryException(
			"No CMIS file entry with {folderId=" + folderId + ", title=" +
				title + "}");
	}

	@Override
	public FileEntry getFileEntryByUuid(String uuid)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			RepositoryEntry repositoryEntry = RepositoryEntryUtil.findByUUID_G(
				uuid, getGroupId());

			String objectId = repositoryEntry.getMappedId();

			return toFileEntry((Document)session.getObject(objectId));
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFileEntryException(
				"No CMIS file entry with {uuid=" + uuid + "}", confe);
		}
		catch (NoSuchRepositoryEntryException nsree) {
			throw new NoSuchFileEntryException(nsree);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public FileVersion getFileVersion(long fileVersionId)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			return getFileVersion(session, fileVersionId);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public Folder getFolder(long folderId)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			return getFolder(session, folderId);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public Folder getFolder(long parentFolderId, String title)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			String objectId = getObjectId(
				session, parentFolderId, false, title);

			if (objectId != null) {
				CmisObject cmisObject = session.getObject(objectId);

				return toFolder(
					(org.apache.chemistry.opencmis.client.api.Folder)
						cmisObject);
			}
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFolderException(
				"No CMIS folder with {parentFolderId=" + parentFolderId +
					", title=" + title + "}",
				confe);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}

		throw new NoSuchFolderException(
			"No CMIS folder with {parentFolderId=" + parentFolderId +
				", title=" + title + "}");
	}

	@Override
	public List<Folder> getFolders(
			long parentFolderId, boolean includeMountfolders, int start,
			int end, OrderByComparator obc)
		throws PortalException, SystemException {

		List<Folder> folders = getFolders(parentFolderId);

		return subList(folders, start, end, obc);
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws SystemException {

		List<Object> foldersAndFileEntries = getFoldersAndFileEntries(folderId);

		return subList(foldersAndFileEntries, start, end, obc);
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		Map<Long, List<Object>> foldersAndFileEntriesCache =
			_foldersAndFileEntriesCache.get();

		List<Object> foldersAndFileEntries = foldersAndFileEntriesCache.get(
			folderId);

		if ((foldersAndFileEntries == null) || (mimeTypes != null)) {
			foldersAndFileEntries = new ArrayList<Object>(getFolders(folderId));

			List<FileEntry> fileEntries = getFileEntries(
				folderId, mimeTypes, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null);

			foldersAndFileEntries.addAll(fileEntries);

			if (mimeTypes == null) {
				foldersAndFileEntriesCache.put(folderId, foldersAndFileEntries);
			}
		}

		return subList(foldersAndFileEntries, start, end, obc);
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId)
		throws SystemException {

		List<Object> foldersAndFileEntries = getFoldersAndFileEntries(folderId);

		return foldersAndFileEntries.size();
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		if (ArrayUtil.isNotEmpty(mimeTypes)) {
			List<Folder> folders = getFolders(folderId);

			Session session = getSession();

			List<String> documentIds = getDocumentIds(
				session, folderId, mimeTypes);

			return folders.size() + documentIds.size();
		}

		List<Object> foldersAndFileEntries = getFoldersAndFileEntries(folderId);

		return foldersAndFileEntries.size();
	}

	@Override
	public int getFoldersCount(long parentFolderId, boolean includeMountfolders)
		throws PortalException, SystemException {

		List<Folder> folders = getFolders(parentFolderId);

		return folders.size();
	}

	@Override
	public int getFoldersFileEntriesCount(List<Long> folderIds, int status)
		throws SystemException {

		int count = 0;

		for (long folderId : folderIds) {
			List<FileEntry> fileEntries = getFileEntries(folderId);

			count += fileEntries.size();
		}

		return count;
	}

	@Override
	public String getLatestVersionId(String objectId) throws SystemException {
		try {
			Session session = getSession();

			Document document = (Document)session.getObject(objectId);

			List<Document> documentVersions = document.getAllVersions();

			document = documentVersions.get(0);

			return document.getId();
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public List<Folder> getMountFolders(
		long parentFolderId, int start, int end, OrderByComparator obc) {

		return new ArrayList<Folder>();
	}

	@Override
	public int getMountFoldersCount(long parentFolderId) {
		return 0;
	}

	@Override
	public String getObjectName(String objectId)
		throws PortalException, SystemException {

		Session session = getSession();

		CmisObject cmisObject = session.getObject(objectId);

		return cmisObject.getName();
	}

	@Override
	public List<String> getObjectPaths(String objectId)
		throws PortalException, SystemException {

		Session session = getSession();

		CmisObject cmisObject = session.getObject(objectId);

		if (cmisObject instanceof FileableCmisObject) {
			FileableCmisObject fileableCmisObject =
				(FileableCmisObject)cmisObject;

			return fileableCmisObject.getPaths();
		}

		throw new RepositoryException(
			"CMIS object is unfileable for id " + objectId);
	}

	public Session getSession() throws PortalException, SystemException {
		Session session = getCachedSession();

		if (session == null) {
			SessionImpl sessionImpl =
				(SessionImpl)_cmisRepositoryHandler.getSession();

			session = sessionImpl.getSession();

			setCachedSession(session);
		}

		if (_cmisRepositoryDetector == null) {
			RepositoryInfo repositoryInfo = session.getRepositoryInfo();

			_cmisRepositoryDetector = new CMISRepositoryDetector(
				repositoryInfo);
		}

		return session;
	}

	@Override
	public void getSubfolderIds(List<Long> folderIds, long folderId)
		throws SystemException {

		try {
			List<Folder> subfolders = getFolders(
				folderId, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			getSubfolderIds(folderIds, subfolders, true);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public List<Long> getSubfolderIds(long folderId, boolean recurse)
		throws SystemException {

		try {
			List<Long> subfolderIds = new ArrayList<Long>();

			List<Folder> subfolders = getFolders(
				folderId, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			getSubfolderIds(subfolderIds, subfolders, recurse);

			return subfolderIds;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public String[] getSupportedConfigurations() {
		return _cmisRepositoryHandler.getSupportedConfigurations();
	}

	@Override
	public String[][] getSupportedParameters() {
		return _cmisRepositoryHandler.getSupportedParameters();
	}

	@Override
	public void initRepository() throws PortalException, SystemException {
		try {
			_sessionKey =
				Session.class.getName().concat(StringPool.POUND).concat(
					String.valueOf(getRepositoryId()));

			Session session = getSession();

			session.getRepositoryInfo();
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(
				"Unable to initialize CMIS session for repository with " +
					"{repositoryId=" + getRepositoryId() + "}",
				e);
		}
	}

	@Override
	public boolean isCancelCheckOutAllowable(String objectId)
		throws PortalException, SystemException {

		return isActionAllowable(objectId, Action.CAN_CANCEL_CHECK_OUT);
	}

	@Override
	public boolean isCheckInAllowable(String objectId)
		throws PortalException, SystemException {

		return isActionAllowable(objectId, Action.CAN_CHECK_IN);
	}

	@Override
	public boolean isCheckOutAllowable(String objectId)
		throws PortalException, SystemException {

		return isActionAllowable(objectId, Action.CAN_CHECK_OUT);
	}

	public boolean isDocumentRetrievableByVersionSeriesId() {
		return _cmisRepositoryHandler.isDocumentRetrievableByVersionSeriesId();
	}

	public boolean isRefreshBeforePermissionCheck() {
		return _cmisRepositoryHandler.isRefreshBeforePermissionCheck();
	}

	@Override
	public boolean isSupportsMinorVersions()
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			RepositoryInfo repositoryInfo = session.getRepositoryInfo();

			String productName = repositoryInfo.getProductName();

			return _cmisRepositoryHandler.isSupportsMinorVersions(productName);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public Lock lockFolder(long folderId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Lock lockFolder(
		long folderId, String owner, boolean inheritable, long expirationTime) {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			String newFolderObjectId = toFolderId(session, newFolderId);

			Document document = getDocument(session, fileEntryId);

			validateTitle(session, newFolderId, document.getName());

			String oldFolderObjectId = document.getParents().get(0).getId();

			if (oldFolderObjectId.equals(newFolderObjectId)) {
				return toFileEntry(document);
			}

			document = (Document)document.move(
				new ObjectIdImpl(oldFolderObjectId),
				new ObjectIdImpl(newFolderObjectId));

			String versionSeriesId = toFileEntryId(fileEntryId);

			String newObjectId = document.getVersionSeriesId();

			if (!versionSeriesId.equals(newObjectId)) {
				document = (Document)session.getObject(newObjectId);

				updateMappedId(fileEntryId, document.getVersionSeriesId());
			}

			FileEntry fileEntry = toFileEntry(document);

			document = null;

			return fileEntry;
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFolderException(
				"No CMIS folder with {folderId=" + newFolderId + "}", confe);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public Folder moveFolder(
			long folderId, long parentFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			org.apache.chemistry.opencmis.client.api.Folder cmisFolder =
				getCmisFolder(session, folderId);

			validateTitle(session, parentFolderId, cmisFolder.getName());

			org.apache.chemistry.opencmis.client.api.Folder parentCmisFolder =
				cmisFolder.getFolderParent();

			if (parentCmisFolder == null) {
				throw new RepositoryException(
					"Unable to move CMIS root folder with {folderId=" +
						folderId + "}");
			}

			String objectId = toFolderId(session, folderId);

			String sourceFolderId = parentCmisFolder.getId();

			String targetFolderId = toFolderId(session, parentFolderId);

			if (!sourceFolderId.equals(targetFolderId) &&
				!targetFolderId.equals(objectId)) {

				cmisFolder =
					(org.apache.chemistry.opencmis.client.api.Folder)
						cmisFolder.move(
							new ObjectIdImpl(sourceFolderId),
							new ObjectIdImpl(targetFolderId));
			}

			return toFolder(cmisFolder);
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFolderException(
				"No CMIS folder with {folderId=" + parentFolderId + "}", confe);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public Lock refreshFileEntryLock(
		String lockUuid, long companyId, long expirationTime) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Lock refreshFolderLock(
		String lockUuid, long companyId, long expirationTime) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void revertFileEntry(
			long fileEntryId, String version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			Document document = getDocument(session, fileEntryId);

			Document oldVersion = null;

			List<Document> documentVersions = document.getAllVersions();

			for (Document currentVersion : documentVersions) {
				String currentVersionLabel = currentVersion.getVersionLabel();

				if (Validator.isNull(currentVersionLabel)) {
					currentVersionLabel = DLFileEntryConstants.VERSION_DEFAULT;
				}

				if (currentVersionLabel.equals(version)) {
					oldVersion = currentVersion;

					break;
				}
			}

			String mimeType = oldVersion.getContentStreamMimeType();
			String changeLog = LanguageUtil.format(
				serviceContext.getLocale(), "reverted-to-x", version, false);
			String title = oldVersion.getName();
			ContentStream contentStream = oldVersion.getContentStream();

			updateFileEntry(
				fileEntryId, contentStream.getFileName(), mimeType, title,
				StringPool.BLANK, changeLog, true, contentStream.getStream(),
				contentStream.getLength(), serviceContext);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public Hits search(long creatorUserId, int status, int start, int end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Hits search(
		long creatorUserId, long folderId, String[] mimeTypes, int status,
		int start, int end) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		try {
			QueryConfig queryConfig = searchContext.getQueryConfig();

			queryConfig.setScoreEnabled(false);

			return doSearch(searchContext, query);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public FileEntry toFileEntry(Document document)
		throws PortalException, SystemException {

		return toFileEntry(document, false);
	}

	@Override
	public FileEntry toFileEntry(String objectId)
		throws PortalException, SystemException {

		return toFileEntry(objectId, false);
	}

	public FileVersion toFileVersion(Document version) throws SystemException {
		Object[] ids = getRepositoryEntryIds(version.getId());

		long fileVersionId = (Long)ids[0];
		String uuid = (String)ids[1];

		return new CMISFileVersion(this, uuid, fileVersionId, version);
	}

	public Folder toFolder(
			org.apache.chemistry.opencmis.client.api.Folder cmisFolder)
		throws SystemException {

		Object[] ids = getRepositoryEntryIds(cmisFolder.getId());

		long folderId = (Long)ids[0];
		String uuid = (String)ids[1];

		return new CMISFolder(this, uuid, folderId, cmisFolder);
	}

	@Override
	public Folder toFolder(String objectId)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			org.apache.chemistry.opencmis.client.api.Folder cmisFolder =
				(org.apache.chemistry.opencmis.client.api.Folder)
					session.getObject(objectId);

			return toFolder(cmisFolder);
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFolderException(
				"No CMIS folder with {objectId=" + objectId + "}", confe);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public void unlockFolder(long folderId, String lockUuid) {
		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Document document = null;

		ObjectId checkOutDocumentObjectId = null;

		try {
			Session session = getSession();

			document = getDocument(session, fileEntryId);

			String versionSeriesCheckedOutId =
				document.getVersionSeriesCheckedOutId();

			if (Validator.isNotNull(versionSeriesCheckedOutId)) {
				document = (Document)session.getObject(
					versionSeriesCheckedOutId);

				document.refresh();
			}

			String currentTitle = document.getName();

			AllowableActions allowableActions = document.getAllowableActions();

			Set<Action> allowableActionsSet =
				allowableActions.getAllowableActions();

			if (allowableActionsSet.contains(Action.CAN_CHECK_OUT)) {
				checkOutDocumentObjectId = document.checkOut();

				document = (Document)session.getObject(
					checkOutDocumentObjectId);
			}

			Map<String, Object> properties = null;

			ContentStream contentStream = null;

			if (Validator.isNotNull(title) && !title.equals(currentTitle)) {
				properties = new HashMap<String, Object>();

				properties.put(PropertyIds.NAME, title);
			}

			if (is != null) {
				contentStream = new ContentStreamImpl(
					sourceFileName, BigInteger.valueOf(size), mimeType, is);
			}

			checkUpdatable(allowableActionsSet, properties, contentStream);

			if (checkOutDocumentObjectId != null) {
				if (!isSupportsMinorVersions()) {
					majorVersion = true;
				}

				document.checkIn(
					majorVersion, properties, contentStream, changeLog);

				checkOutDocumentObjectId = null;
			}
			else {
				if (properties != null) {
					document = (Document)document.updateProperties(properties);
				}

				if (contentStream != null) {
					document.setContentStream(contentStream, true, false);
				}
			}

			String versionSeriesId = toFileEntryId(fileEntryId);

			document = (Document)session.getObject(versionSeriesId);

			return toFileEntry(document);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
		finally {
			if (checkOutDocumentObjectId != null) {
				document.cancelCheckOut();
			}
		}
	}

	@Override
	public FileEntry updateFileEntry(
			String objectId, String mimeType, Map<String, Object> properties,
			InputStream is, String sourceFileName, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			Document document = (Document)session.getObject(objectId);

			AllowableActions allowableActions = document.getAllowableActions();

			Set<Action> allowableActionsSet =
				allowableActions.getAllowableActions();

			ContentStream contentStream = null;

			if (is != null) {
				is = new Base64.InputStream(is, Base64.ENCODE);

				contentStream = new ContentStreamImpl(
					sourceFileName, BigInteger.valueOf(size), mimeType, is);
			}

			checkUpdatable(allowableActionsSet, properties, contentStream);

			if (properties != null) {
				document = (Document)document.updateProperties(properties);
			}

			if (contentStream != null) {
				document.setContentStream(contentStream, true, false);
			}

			return toFileEntry(document);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public Folder updateFolder(
			long folderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			String objectId = toFolderId(session, folderId);

			org.apache.chemistry.opencmis.client.api.Folder cmisFolder =
				(org.apache.chemistry.opencmis.client.api.Folder)
					session.getObject(objectId);

			String currentTitle = cmisFolder.getName();

			Map<String, Object> properties = new HashMap<String, Object>();

			if (Validator.isNotNull(title) && !title.equals(currentTitle)) {
				properties.put(PropertyIds.NAME, title);
			}

			ObjectId cmisFolderObjectId = cmisFolder.updateProperties(
				properties, true);

			String newObjectId = cmisFolderObjectId.getId();

			if (!objectId.equals(newObjectId)) {
				cmisFolder =
					(org.apache.chemistry.opencmis.client.api.Folder)
						session.getObject(newObjectId);

				updateMappedId(folderId, newObjectId);
			}

			return toFolder(cmisFolder);
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFolderException(
				"No CMIS folder with {folderId=" + folderId + "}", confe);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public boolean verifyFileEntryCheckOut(long fileEntryId, String lockUuid) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean verifyInheritableLock(long folderId, String lockUuid) {
		throw new UnsupportedOperationException();
	}

	protected void cacheFoldersAndFileEntries(long folderId)
		throws SystemException {

		try {
			Map<Long, List<Object>> foldersAndFileEntriesCache =
				_foldersAndFileEntriesCache.get();

			if (foldersAndFileEntriesCache.containsKey(folderId)) {
				return;
			}

			List<Object> foldersAndFileEntries = new ArrayList<Object>();
			List<Folder> folders = new ArrayList<Folder>();
			List<FileEntry> fileEntries = new ArrayList<FileEntry>();

			Session session = getSession();

			org.apache.chemistry.opencmis.client.api.Folder cmisParentFolder =
				getCmisFolder(session, folderId);

			Folder parentFolder = toFolder(cmisParentFolder);

			ItemIterable<CmisObject> cmisObjects =
				cmisParentFolder.getChildren();

			for (CmisObject cmisObject : cmisObjects) {
				if (cmisObject instanceof
						org.apache.chemistry.opencmis.client.api.Folder) {

					CMISFolder cmisFolder = (CMISFolder)toFolder(
						(org.apache.chemistry.opencmis.client.api.Folder)
							cmisObject);

					cmisFolder.setParentFolder(parentFolder);

					foldersAndFileEntries.add(cmisFolder);
					folders.add(cmisFolder);
				}
				else if (cmisObject instanceof Document) {
					CMISFileEntry cmisFileEntry = (CMISFileEntry)toFileEntry(
						(Document)cmisObject);

					cmisFileEntry.setParentFolder(parentFolder);

					foldersAndFileEntries.add(cmisFileEntry);
					fileEntries.add(cmisFileEntry);
				}
			}

			foldersAndFileEntriesCache.put(folderId, foldersAndFileEntries);

			Map<Long, List<Folder>> foldersCache = _foldersCache.get();

			foldersCache.put(folderId, folders);

			Map<Long, List<FileEntry>> fileEntriesCache =
				_fileEntriesCache.get();

			fileEntriesCache.put(folderId, fileEntries);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	protected void checkUpdatable(
			Set<Action> allowableActionsSet, Map<String, Object> properties,
			ContentStream contentStream)
		throws PrincipalException {

		if (properties != null) {
			if (!allowableActionsSet.contains(Action.CAN_UPDATE_PROPERTIES)) {
				throw new PrincipalException();
			}
		}

		if (contentStream != null) {
			if (!allowableActionsSet.contains(Action.CAN_SET_CONTENT_STREAM)) {
				throw new PrincipalException();
			}
		}
	}

	protected void deleteMappedFileEntry(Document document)
		throws SystemException {

		if (PropsValues.DL_REPOSITORY_CMIS_DELETE_DEPTH == _DELETE_NONE) {
			return;
		}

		List<Document> documentVersions = document.getAllVersions();

		for (Document version : documentVersions) {
			try {
				RepositoryEntryUtil.removeByR_M(
					getRepositoryId(), version.getId());
			}
			catch (NoSuchRepositoryEntryException nsree) {
			}
		}

		try {
			RepositoryEntryUtil.removeByR_M(
				getRepositoryId(), document.getId());
		}
		catch (NoSuchRepositoryEntryException nsree) {
		}
	}

	protected void deleteMappedFolder(
			org.apache.chemistry.opencmis.client.api.Folder cmisFolder)
		throws SystemException {

		if (PropsValues.DL_REPOSITORY_CMIS_DELETE_DEPTH == _DELETE_NONE) {
			return;
		}

		ItemIterable<CmisObject> cmisObjects = cmisFolder.getChildren();

		for (CmisObject cmisObject : cmisObjects) {
			if (cmisObject instanceof Document) {
				Document document = (Document)cmisObject;

				deleteMappedFileEntry(document);
			}
			else if (cmisObject instanceof
						org.apache.chemistry.opencmis.client.api.Folder) {

				org.apache.chemistry.opencmis.client.api.Folder cmisSubfolder =
					(org.apache.chemistry.opencmis.client.api.Folder)cmisObject;

				try {
					RepositoryEntryUtil.removeByR_M(
						getRepositoryId(), cmisObject.getId());

					if (PropsValues.DL_REPOSITORY_CMIS_DELETE_DEPTH ==
							_DELETE_DEEP) {

						deleteMappedFolder(cmisSubfolder);
					}
				}
				catch (NoSuchRepositoryEntryException nsree) {
				}
			}
		}
	}

	protected Hits doSearch(SearchContext searchContext, Query query)
		throws Exception {

		long startTime = System.currentTimeMillis();

		Session session = getSession();

		RepositoryInfo repositoryInfo = session.getRepositoryInfo();

		RepositoryCapabilities repositoryCapabilities =
			repositoryInfo.getCapabilities();

		QueryConfig queryConfig = searchContext.getQueryConfig();

		CapabilityQuery capabilityQuery =
			repositoryCapabilities.getQueryCapability();

		queryConfig.setAttribute("capabilityQuery", capabilityQuery.value());

		String productName = repositoryInfo.getProductName();
		String productVersion = repositoryInfo.getProductVersion();

		queryConfig.setAttribute("repositoryProductName", productName);
		queryConfig.setAttribute("repositoryProductVersion", productVersion);

		String queryString = CMISSearchQueryBuilderUtil.buildQuery(
			searchContext, query);

		if (_cmisRepositoryDetector.isNuxeo5_4()) {
			queryString +=
				" AND (" + PropertyIds.IS_LATEST_VERSION + " = true)";
		}

		if (_log.isDebugEnabled()) {
			_log.debug("CMIS search query: " + queryString);
		}

		ItemIterable<QueryResult> queryResults = null;

		if (_cmisRepositoryDetector.isNuxeo5_5OrHigher()) {
			queryResults = session.query(queryString, true);
		}
		else {
			queryResults = session.query(queryString, false);
		}

		int start = searchContext.getStart();
		int end = searchContext.getEnd();

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
			start = 0;
		}

		int total = 0;

		List<com.liferay.portal.kernel.search.Document> documents =
			new ArrayList<com.liferay.portal.kernel.search.Document>();
		List<String> snippets = new ArrayList<String>();
		List<Float> scores = new ArrayList<Float>();

		for (QueryResult queryResult : queryResults) {
			total++;

			if (total <= start) {
				continue;
			}

			if ((total > end) && (end != QueryUtil.ALL_POS)) {
				continue;
			}

			com.liferay.portal.kernel.search.Document document =
				new DocumentImpl();

			String objectId = queryResult.getPropertyValueByQueryName(
				PropertyIds.OBJECT_ID);

			if (_log.isDebugEnabled()) {
				_log.debug("Search result object ID " + objectId);
			}

			FileEntry fileEntry = null;

			try {
				fileEntry = toFileEntry(objectId, true);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					Throwable cause = e.getCause();

					if (cause != null) {
						cause = cause.getCause();
					}

					if (cause instanceof CmisObjectNotFoundException) {
						_log.debug(
							"Search result ignored for CMIS document which " +
								"has a version with an invalid object ID " +
									cause.getMessage());
					}
					else {
						_log.debug(
							"Search result ignored for invalid object ID", e);
					}
				}

				total--;

				continue;
			}

			document.addKeyword(
				Field.ENTRY_CLASS_NAME, fileEntry.getModelClassName());
			document.addKeyword(
				Field.ENTRY_CLASS_PK, fileEntry.getFileEntryId());
			document.addKeyword(Field.TITLE, fileEntry.getTitle());

			documents.add(document);

			if (queryConfig.isScoreEnabled()) {
				Object scoreObj = queryResult.getPropertyValueByQueryName(
					"HITS");

				if (scoreObj != null) {
					scores.add(Float.valueOf(scoreObj.toString()));
				}
				else {
					scores.add(1.0f);
				}
			}
			else {
				scores.add(1.0f);
			}

			snippets.add(StringPool.BLANK);
		}

		float searchTime =
			(float)(System.currentTimeMillis() - startTime) / Time.SECOND;

		Hits hits = new HitsImpl();

		hits.setDocs(
			documents.toArray(
				new com.liferay.portal.kernel.search.Document[
					documents.size()]));
		hits.setLength(total);
		hits.setQuery(query);
		hits.setQueryTerms(new String[0]);
		hits.setScores(scores.toArray(new Float[scores.size()]));
		hits.setSearchTime(searchTime);
		hits.setSnippets(snippets.toArray(new String[snippets.size()]));
		hits.setStart(startTime);

		return hits;
	}

	protected Session getCachedSession() {
		HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

		if (httpSession == null) {
			return null;
		}

		TransientValue<Session> transientValue =
			(TransientValue<Session>)httpSession.getAttribute(_sessionKey);

		if (transientValue == null) {
			return null;
		}

		return transientValue.getValue();
	}

	protected org.apache.chemistry.opencmis.client.api.Folder getCmisFolder(
			Session session, long folderId)
		throws PortalException, SystemException {

		Folder folder = getFolder(session, folderId);

		org.apache.chemistry.opencmis.client.api.Folder cmisFolder =
			(org.apache.chemistry.opencmis.client.api.Folder)folder.getModel();

		return cmisFolder;
	}

	protected List<String> getCmisFolderIds(Session session, long folderId)
		throws PortalException, SystemException {

		StringBundler sb = new StringBundler(4);

		sb.append("SELECT cmis:objectId FROM cmis:folder");

		if (folderId > 0) {
			sb.append(" WHERE IN_FOLDER(");

			String objectId = toFolderId(session, folderId);

			sb.append(StringUtil.quote(objectId));
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		String query = sb.toString();

		if (_log.isDebugEnabled()) {
			_log.debug("Calling query " + query);
		}

		ItemIterable<QueryResult> queryResults = session.query(
			query, isAllVersionsSearchableSupported(session));

		List<String> cmsFolderIds = new ArrayList<String>();

		for (QueryResult queryResult : queryResults) {
			PropertyData<String> propertyData = queryResult.getPropertyById(
				PropertyIds.OBJECT_ID);

			List<String> values = propertyData.getValues();

			String value = values.get(0);

			cmsFolderIds.add(value);
		}

		return cmsFolderIds;
	}

	protected Document getDocument(Session session, long fileEntryId)
		throws PortalException, SystemException {

		try {
			String versionSeriesId = toFileEntryId(fileEntryId);

			Document document = (Document)session.getObject(versionSeriesId);

			return document;
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFileEntryException(
				"No CMIS file entry with {fileEntryId=" + fileEntryId+ "}",
				confe);
		}
	}

	protected List<String> getDocumentIds(
			Session session, long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		StringBundler sb = new StringBundler();

		sb.append("SELECT cmis:objectId FROM cmis:document");

		if (ArrayUtil.isNotEmpty(mimeTypes)) {
			sb.append(" WHERE cmis:contentStreamMimeType IN (");

			for (int i = 0; i < mimeTypes.length; i++) {
				sb.append(StringUtil.quote(mimeTypes[i]));

				if ((i + 1) < mimeTypes.length) {
					sb.append(", ");
				}
			}

			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		if (folderId > 0) {
			if (ArrayUtil.isNotEmpty(mimeTypes)) {
				sb.append(" AND ");
			}
			else {
				sb.append(" WHERE ");
			}

			sb.append("IN_FOLDER(");

			String objectId = toFolderId(session, folderId);

			sb.append(StringUtil.quote(objectId));
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		String query = sb.toString();

		if (_log.isDebugEnabled()) {
			_log.debug("Calling query " + query);
		}

		ItemIterable<QueryResult> queryResults = session.query(query, false);

		List<String> cmisDocumentIds = new ArrayList<String>();

		for (QueryResult queryResult : queryResults) {
			String objectId = queryResult.getPropertyValueByQueryName(
				PropertyIds.OBJECT_ID);

			cmisDocumentIds.add(objectId);
		}

		return cmisDocumentIds;
	}

	protected List<FileEntry> getFileEntries(long folderId)
		throws SystemException {

		cacheFoldersAndFileEntries(folderId);

		Map<Long, List<FileEntry>> fileEntriesCache = _fileEntriesCache.get();

		return fileEntriesCache.get(folderId);
	}

	protected List<FileEntry> getFileEntries(long folderId, long repositoryId) {
		return new ArrayList<FileEntry>();
	}

	protected FileVersion getFileVersion(Session session, long fileVersionId)
		throws PortalException, SystemException {

		try {
			String objectId = toFileVersionId(fileVersionId);

			return toFileVersion((Document)session.getObject(objectId));
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFileVersionException(
				"No CMIS file version with {fileVersionId=" + fileVersionId +
					"}",
				confe);
		}
	}

	protected Folder getFolder(Session session, long folderId)
		throws PortalException, SystemException {

		try {
			String objectId = toFolderId(session, folderId);

			CmisObject cmisObject = session.getObject(objectId);

			return (Folder)toFolderOrFileEntry(cmisObject);
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFolderException(
				"No CMIS folder with {folderId=" + folderId + "}", confe);
		}
	}

	protected List<Folder> getFolders(long parentFolderId)
		throws PortalException, SystemException {

		Map<Long, List<Folder>> foldersCache = _foldersCache.get();

		List<Folder> folders = foldersCache.get(parentFolderId);

		if (folders == null) {
			List<String> folderIds = getCmisFolderIds(
				getSession(), parentFolderId);

			folders = new ArrayList<Folder>(folderIds.size());

			for (String folderId : folderIds) {
				folders.add(toFolder(folderId));
			}

			foldersCache.put(parentFolderId, folders);
		}

		return folders;
	}

	protected List<Object> getFoldersAndFileEntries(long folderId)
		throws SystemException {

		cacheFoldersAndFileEntries(folderId);

		Map<Long, List<Object>> foldersAndFileEntriesCache =
			_foldersAndFileEntriesCache.get();

		return foldersAndFileEntriesCache.get(folderId);
	}

	protected String getObjectId(
			Session session, long folderId, boolean fileEntry, String name)
		throws PortalException, SystemException {

		String objectId = toFolderId(session, folderId);

		StringBundler sb = new StringBundler(7);

		sb.append("SELECT cmis:objectId FROM ");

		if (fileEntry) {
			sb.append("cmis:document ");
		}
		else {
			sb.append("cmis:folder ");
		}

		sb.append("WHERE cmis:name = '");
		sb.append(name);
		sb.append("' AND IN_FOLDER('");
		sb.append(objectId);
		sb.append("')");

		String query = sb.toString();

		if (_log.isDebugEnabled()) {
			_log.debug("Calling query " + query);
		}

		ItemIterable<QueryResult> queryResults = session.query(query, false);

		Iterator<QueryResult> itr = queryResults.iterator();

		if (itr.hasNext()) {
			QueryResult queryResult = itr.next();

			PropertyData<String> propertyData = queryResult.getPropertyById(
				PropertyIds.OBJECT_ID);

			List<String> values = propertyData.getValues();

			return values.get(0);
		}

		return null;
	}

	protected void getSubfolderIds(
			List<Long> subfolderIds, List<Folder> subfolders, boolean recurse)
		throws PortalException, SystemException {

		for (Folder subfolder : subfolders) {
			long subfolderId = subfolder.getFolderId();

			subfolderIds.add(subfolderId);

			if (recurse) {
				List<Folder> subSubFolders = getFolders(
					subfolderId, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null);

				getSubfolderIds(subfolderIds, subSubFolders, recurse);
			}
		}
	}

	protected boolean isActionAllowable(String objectId, Action action)
		throws PortalException, SystemException {

		Session session = getSession();

		Document document = (Document)session.getObject(objectId);

		AllowableActions allowableActions = document.getAllowableActions();

		Set<Action> allowableActionsSet =
			allowableActions.getAllowableActions();

		if (allowableActionsSet.contains(action)) {
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isAllVersionsSearchableSupported(Session session) {
		RepositoryInfo repositoryInfo = session.getRepositoryInfo();

		RepositoryCapabilities repositoryCapabilities =
			repositoryInfo.getCapabilities();

		return repositoryCapabilities.isAllVersionsSearchableSupported();
	}

	protected void processException(Exception e) throws PortalException {
		if ((e instanceof CmisRuntimeException &&
			 e.getMessage().contains("authorized")) ||
			(e instanceof CmisPermissionDeniedException)) {

			String message = e.getMessage();

			try {
				message =
					"Unable to login with user " +
						_cmisRepositoryHandler.getLogin();
			}
			catch (Exception e2) {
			}

			throw new PrincipalException(message, e);
		}
	}

	protected void setCachedSession(Session session) {
		HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

		if (httpSession == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get HTTP session");
			}

			return;
		}

		httpSession.setAttribute(
			_sessionKey, new TransientValue<Session>(session));
	}

	protected <E> List<E> subList(
		List<E> list, int start, int end, OrderByComparator obc) {

		if ((obc != null) &&
			((obc instanceof RepositoryModelCreateDateComparator) ||
			 (obc instanceof RepositoryModelModifiedDateComparator) ||
			 (obc instanceof RepositoryModelNameComparator) ||
			 (obc instanceof RepositoryModelSizeComparator))) {

			list = ListUtil.sort(list, obc);
		}

		return ListUtil.subList(list, start, end);
	}

	protected FileEntry toFileEntry(Document document, boolean strict)
		throws PortalException, SystemException {

		Object[] ids = null;

		if (isDocumentRetrievableByVersionSeriesId()) {
			ids = getRepositoryEntryIds(document.getVersionSeriesId());
		}
		else {
			ids = getRepositoryEntryIds(document.getId());
		}

		long fileEntryId = (Long)ids[0];
		String uuid = (String)ids[1];

		FileEntry fileEntry = new CMISFileEntry(
			this, uuid, fileEntryId, document);

		FileVersion fileVersion = null;

		try {
			fileVersion = fileEntry.getFileVersion();
		}
		catch (Exception e) {
			if (strict) {
				if ((Boolean)ids[2]) {
					RepositoryEntryUtil.remove(fileEntryId);
				}

				if (e instanceof CmisObjectNotFoundException) {
					throw new NoSuchFileVersionException(
						"No CMIS file version with CMIS file entry {objectId=" +
							document.getId() + "}",
						e);
				}
				else if (e instanceof SystemException) {
					throw (SystemException)e;
				}
				else {
					processException(e);

					throw new RepositoryException(e);
				}
			}
			else {
				_log.error("Unable to update asset", e);
			}
		}

		dlAppHelperLocalService.checkAssetEntry(
			PrincipalThreadLocal.getUserId(), fileEntry, fileVersion);

		return fileEntry;
	}

	protected FileEntry toFileEntry(String objectId, boolean strict)
		throws PortalException, SystemException {

		try {
			Session session = getSession();

			Document document = (Document)session.getObject(objectId);

			return toFileEntry(document, strict);
		}
		catch (CmisObjectNotFoundException confe) {
			throw new NoSuchFileEntryException(
				"No CMIS file entry with {objectId=" + objectId + "}", confe);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	protected String toFileEntryId(long fileEntryId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			fileEntryId);

		if (repositoryEntry == null) {
			throw new NoSuchFileEntryException(
				"No CMIS file entry with {fileEntryId=" + fileEntryId + "}");
		}

		return repositoryEntry.getMappedId();
	}

	protected String toFileVersionId(long fileVersionId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			fileVersionId);

		if (repositoryEntry == null) {
			throw new NoSuchFileVersionException(
				"No CMIS file version with {fileVersionId=" + fileVersionId +
					"}");
		}

		return repositoryEntry.getMappedId();
	}

	protected String toFolderId(Session session, long folderId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			folderId);

		if (repositoryEntry != null) {
			return repositoryEntry.getMappedId();
		}

		DLFolder dlFolder = DLFolderUtil.fetchByPrimaryKey(folderId);

		if (dlFolder == null) {
			throw new NoSuchFolderException(
				"No CMIS folder with {folderId=" + folderId + "}");
		}
		else if (!dlFolder.isMountPoint()) {
			throw new RepositoryException(
				"CMIS repository should not be used with {folderId=" +
					folderId + "}");
		}

		RepositoryInfo repositoryInfo = session.getRepositoryInfo();

		String rootFolderId = repositoryInfo.getRootFolderId();

		repositoryEntry = RepositoryEntryUtil.fetchByR_M(
			getRepositoryId(), rootFolderId);

		if (repositoryEntry == null) {
			repositoryEntry =
				RepositoryEntryLocalServiceUtil.addRepositoryEntry(
					dlFolder.getUserId(), getGroupId(), getRepositoryId(),
					rootFolderId, new ServiceContext());
		}

		return repositoryEntry.getMappedId();
	}

	protected Object toFolderOrFileEntry(CmisObject cmisObject)
		throws PortalException, SystemException {

		if (cmisObject instanceof Document) {
			FileEntry fileEntry = toFileEntry((Document)cmisObject);

			return fileEntry;
		}
		else if (cmisObject instanceof
					org.apache.chemistry.opencmis.client.api.Folder) {

			org.apache.chemistry.opencmis.client.api.Folder cmisFolder =
				(org.apache.chemistry.opencmis.client.api.Folder)cmisObject;

			Folder folder = toFolder(cmisFolder);

			return folder;
		}
		else {
			return null;
		}
	}

	protected void updateMappedId(long repositoryEntryId, String mappedId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.findByPrimaryKey(
			repositoryEntryId);

		if (!mappedId.equals(repositoryEntry.getMappedId())) {
			RepositoryEntryLocalServiceUtil.updateRepositoryEntry(
				repositoryEntryId, mappedId);
		}
	}

	protected void validateTitle(Session session, long folderId, String title)
		throws PortalException, SystemException {

		String objectId = getObjectId(session, folderId, true, title);

		if (objectId != null) {
			throw new DuplicateFileException(title);
		}

		objectId = getObjectId(session, folderId, false, title);

		if (objectId != null) {
			throw new DuplicateFolderNameException(title);
		}
	}

	private static final int _DELETE_DEEP = -1;

	private static final int _DELETE_NONE = 0;

	private static Log _log = LogFactoryUtil.getLog(CMISRepository.class);

	private static ThreadLocal<Map<Long, List<FileEntry>>> _fileEntriesCache =
		new AutoResetThreadLocal<Map<Long, List<FileEntry>>>(
			CMISRepository.class + "._fileEntriesCache",
			new HashMap<Long, List<FileEntry>>());
	private static ThreadLocal<Map<Long, List<Object>>>
		_foldersAndFileEntriesCache =
			new AutoResetThreadLocal<Map<Long, List<Object>>>(
				CMISRepository.class + "._foldersAndFileEntriesCache",
				new HashMap<Long, List<Object>>());
	private static ThreadLocal<Map<Long, List<Folder>>> _foldersCache =
		new AutoResetThreadLocal<Map<Long, List<Folder>>>(
			CMISRepository.class + "._foldersCache",
			new HashMap<Long, List<Folder>>());

	private CMISRepositoryDetector _cmisRepositoryDetector;
	private CMISRepositoryHandler _cmisRepositoryHandler;
	private String _sessionKey;

}