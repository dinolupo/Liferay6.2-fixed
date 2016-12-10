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

package com.liferay.portal.portletfilerepository;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileEntryWrapper;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Repository;
import com.liferay.portal.model.User;
import com.liferay.portal.repository.liferayrepository.LiferayRepository;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.webserver.WebServerServlet;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLAppHelperThreadLocal;
import com.liferay.portlet.trash.util.TrashUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eudaldo Alonso
 * @author Alexander Chow
 */
@DoPrivileged
public class PortletFileRepositoryImpl implements PortletFileRepository {

	private static class PortletFileEntryWrapper extends FileEntryWrapper {

		public PortletFileEntryWrapper(FileEntry fileEntry) {
			super(fileEntry);
		}

		@Override
		public InputStream getContentStream()
			throws PortalException, SystemException {

			return getContentStream(getVersion());
		}

		@Override
		public InputStream getContentStream(String version)
			throws PortalException, SystemException {

			return DLFileEntryServiceUtil.getFileAsStream(
				getFileEntryId(), version, false);
		}

	}

	@Override
	public void addPortletFileEntries(
			long groupId, long userId, String className, long classPK,
			String portletId, long folderId,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs)
		throws PortalException, SystemException {

		for (int i = 0; i < inputStreamOVPs.size(); i++) {
			ObjectValuePair<String, InputStream> inputStreamOVP =
				inputStreamOVPs.get(i);

			InputStream inputStream = inputStreamOVP.getValue();
			String fileName = inputStreamOVP.getKey();

			addPortletFileEntry(
				groupId, userId, className, classPK, portletId, folderId,
				inputStream, fileName, StringPool.BLANK, true);
		}
	}

	@Override
	public FileEntry addPortletFileEntry(
			long groupId, long userId, String className, long classPK,
			String portletId, long folderId, File file, String fileName,
			String mimeType, boolean indexingEnabled)
		throws PortalException, SystemException {

		if (Validator.isNull(fileName)) {
			return null;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = addPortletRepository(
			groupId, portletId, serviceContext);

		serviceContext.setAttribute("className", className);
		serviceContext.setAttribute("classPK", String.valueOf(classPK));
		serviceContext.setIndexingEnabled(indexingEnabled);

		if (Validator.isNull(mimeType) ||
			mimeType.equals(ContentTypes.APPLICATION_OCTET_STREAM)) {

			mimeType = MimeTypesUtil.getContentType(file, fileName);
		}

		boolean dlAppHelperEnabled = DLAppHelperThreadLocal.isEnabled();
		boolean fileMaxSizeCheckEnabled =
			PortletFileRepositoryThreadLocal.isFileMaxSizeCheckEnabled();

		try {
			DLAppHelperThreadLocal.setEnabled(false);

			if ((portletId != null) &&
				portletId.equals(PortletKeys.BACKGROUND_TASK)) {

				PortletFileRepositoryThreadLocal.setFileMaxSizeCheckEnabled(
					false);
			}

			FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
				userId, repository.getRepositoryId(), folderId, fileName,
				mimeType, fileName, StringPool.BLANK, StringPool.BLANK, file,
				serviceContext);

			return new PortletFileEntryWrapper(fileEntry);
		}
		finally {
			DLAppHelperThreadLocal.setEnabled(dlAppHelperEnabled);
			PortletFileRepositoryThreadLocal.setFileMaxSizeCheckEnabled(
				fileMaxSizeCheckEnabled);
		}
	}

	@Override
	public FileEntry addPortletFileEntry(
			long groupId, long userId, String className, long classPK,
			String portletId, long folderId, InputStream inputStream,
			String fileName, String mimeType, boolean indexingEnabled)
		throws PortalException, SystemException {

		if (inputStream == null) {
			return null;
		}

		File file = null;

		try {
			file = FileUtil.createTempFile(inputStream);

			return addPortletFileEntry(
				groupId, userId, className, classPK, portletId, folderId, file,
				fileName, mimeType, indexingEnabled);
		}
		catch (IOException ioe) {
			throw new SystemException("Unable to write temporary file", ioe);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	@Override
	public Folder addPortletFolder(
			long userId, long repositoryId, long parentFolderId,
			String folderName, ServiceContext serviceContext)
		throws PortalException, SystemException {

		boolean dlAppHelperEnabled = DLAppHelperThreadLocal.isEnabled();

		try {
			DLAppHelperThreadLocal.setEnabled(false);

			return DLAppLocalServiceUtil.getFolder(
				repositoryId, parentFolderId, folderName);
		}
		catch (NoSuchFolderException nsfe) {
			return DLAppLocalServiceUtil.addFolder(
				userId, repositoryId, parentFolderId, folderName,
				StringPool.BLANK, serviceContext);
		}
		finally {
			DLAppHelperThreadLocal.setEnabled(dlAppHelperEnabled);
		}
	}

	@Override
	public Repository addPortletRepository(
			long groupId, String portletId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Repository repository = RepositoryLocalServiceUtil.fetchRepository(
			groupId, portletId);

		if (repository != null) {
			return repository;
		}

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		User user = UserLocalServiceUtil.getDefaultUser(group.getCompanyId());

		long classNameId = PortalUtil.getClassNameId(
			LiferayRepository.class.getName());

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		boolean dlAppHelperEnabled = DLAppHelperThreadLocal.isEnabled();

		try {
			DLAppHelperThreadLocal.setEnabled(false);

			return RepositoryLocalServiceUtil.addRepository(
				user.getUserId(), groupId, classNameId,
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, portletId,
				StringPool.BLANK, portletId, typeSettingsProperties, true,
				serviceContext);
		}
		finally {
			DLAppHelperThreadLocal.setEnabled(dlAppHelperEnabled);
		}
	}

	@Override
	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		boolean dlAppHelperEnabled = DLAppHelperThreadLocal.isEnabled();

		try {
			DLAppHelperThreadLocal.setEnabled(false);

			DLAppLocalServiceUtil.deleteFolder(folderId);
		}
		finally {
			DLAppHelperThreadLocal.setEnabled(dlAppHelperEnabled);
		}
	}

	@Override
	public void deletePortletFileEntries(long groupId, long folderId)
		throws PortalException, SystemException {

		List<DLFileEntry> dlFileEntries =
			DLFileEntryLocalServiceUtil.getFileEntries(groupId, folderId);

		for (DLFileEntry dlFileEntry : dlFileEntries) {
			deletePortletFileEntry(dlFileEntry.getFileEntryId());
		}
	}

	@Override
	public void deletePortletFileEntries(
			long groupId, long folderId, int status)
		throws PortalException, SystemException {

		List<DLFileEntry> dlFileEntries =
			DLFileEntryLocalServiceUtil.getFileEntries(
				groupId, folderId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null);

		for (DLFileEntry dlFileEntry : dlFileEntries) {
			deletePortletFileEntry(dlFileEntry.getFileEntryId());
		}
	}

	@Override
	public void deletePortletFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		boolean dlAppHelperEnabled = DLAppHelperThreadLocal.isEnabled();

		try {
			DLAppHelperThreadLocal.setEnabled(false);

			DLAppLocalServiceUtil.deleteFileEntry(fileEntryId);
		}
		finally {
			DLAppHelperThreadLocal.setEnabled(dlAppHelperEnabled);
		}
	}

	@Override
	public void deletePortletFileEntry(
			long groupId, long folderId, String fileName)
		throws PortalException, SystemException {

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			groupId, folderId, fileName);

		deletePortletFileEntry(fileEntry.getFileEntryId());
	}

	@Override
	public void deletePortletRepository(long groupId, String portletId)
		throws PortalException, SystemException {

		Repository repository = RepositoryLocalServiceUtil.fetchRepository(
			groupId, portletId);

		if (repository != null) {
			RepositoryLocalServiceUtil.deleteRepository(
				repository.getRepositoryId());
		}
	}

	@Override
	public Repository fetchPortletRepository(long groupId, String portletId)
		throws SystemException {

		return RepositoryLocalServiceUtil.fetchRepository(groupId, portletId);
	}

	@Override
	public List<FileEntry> getPortletFileEntries(long groupId, long folderId)
		throws SystemException {

		return toFileEntries(
			DLFileEntryLocalServiceUtil.getFileEntries(groupId, folderId));
	}

	@Override
	public List<FileEntry> getPortletFileEntries(
			long groupId, long folderId, int status)
		throws SystemException {

		return getPortletFileEntries(
			groupId, folderId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	@Override
	public List<FileEntry> getPortletFileEntries(
			long groupId, long folderId, int status, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return toFileEntries(
			DLFileEntryLocalServiceUtil.getFileEntries(
				groupId, folderId, status, start, end, obc));
	}

	@Override
	public int getPortletFileEntriesCount(long groupId, long folderId)
		throws SystemException {

		return DLFileEntryLocalServiceUtil.getFileEntriesCount(
			groupId, folderId);
	}

	@Override
	public int getPortletFileEntriesCount(
			long groupId, long folderId, int status)
		throws SystemException {

		return DLFileEntryLocalServiceUtil.getFileEntriesCount(
			groupId, folderId, status);
	}

	@Override
	public FileEntry getPortletFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		return new PortletFileEntryWrapper(
			DLAppLocalServiceUtil.getFileEntry(fileEntryId));
	}

	@Override
	public FileEntry getPortletFileEntry(
			long groupId, long folderId, String fileName)
		throws PortalException, SystemException {

		return new PortletFileEntryWrapper(
			DLAppLocalServiceUtil.getFileEntry(groupId, folderId, fileName));
	}

	@Override
	public FileEntry getPortletFileEntry(String uuid, long groupId)
		throws PortalException, SystemException {

		return new PortletFileEntryWrapper(
			DLAppLocalServiceUtil.getFileEntryByUuidAndGroupId(uuid, groupId));
	}

	@Override
	public String getPortletFileEntryURL(
		ThemeDisplay themeDisplay, FileEntry fileEntry, String queryString) {

		return getPortletFileEntryURL(
			themeDisplay, fileEntry, queryString, true);
	}

	@Override
	public String getPortletFileEntryURL(
		ThemeDisplay themeDisplay, FileEntry fileEntry, String queryString,
		boolean absoluteURL) {

		StringBundler sb = new StringBundler(10);

		if (themeDisplay != null) {
			if (absoluteURL) {
				sb.append(themeDisplay.getPortalURL());
			}
		}

		sb.append(PortalUtil.getPathContext());
		sb.append("/documents/");
		sb.append(WebServerServlet.PATH_PORTLET_FILE_ENTRY);
		sb.append(StringPool.SLASH);
		sb.append(fileEntry.getGroupId());
		sb.append(StringPool.SLASH);

		String title = fileEntry.getTitle();

		if (fileEntry.isInTrash()) {
			title = TrashUtil.getOriginalTitle(fileEntry.getTitle());
		}

		sb.append(HttpUtil.encodeURL(HtmlUtil.unescape(title)));

		sb.append(StringPool.SLASH);
		sb.append(HttpUtil.encodeURL(fileEntry.getUuid()));

		if (themeDisplay != null) {
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			if (portletDisplay != null) {
				String portletId = portletDisplay.getId();

				if (portletId.equals(PortletKeys.TRASH) &&
					!queryString.contains("status=")) {

					if (Validator.isNotNull(queryString)) {
						queryString += StringPool.AMPERSAND;
					}

					queryString +=
						"status=" + WorkflowConstants.STATUS_IN_TRASH;
				}
			}
		}

		if (Validator.isNotNull(queryString)) {
			sb.append(StringPool.QUESTION);
			sb.append(queryString);
		}

		String portletFileEntryURL = sb.toString();

		if ((themeDisplay != null) && themeDisplay.isAddSessionIdToURL()) {
			return PortalUtil.getURLWithSessionId(
				portletFileEntryURL, themeDisplay.getSessionId());
		}

		return portletFileEntryURL;
	}

	@Override
	public Folder getPortletFolder(long folderId)
		throws PortalException, SystemException {

		return DLAppLocalServiceUtil.getFolder(folderId);
	}

	@Override
	public Folder getPortletFolder(
			long repositoryId, long parentFolderId, String folderName)
		throws PortalException, SystemException {

		return DLAppLocalServiceUtil.getFolder(
			repositoryId, parentFolderId, folderName);
	}

	@Override
	public Repository getPortletRepository(long groupId, String portletId)
		throws PortalException, SystemException {

		return RepositoryLocalServiceUtil.getRepository(groupId, portletId);
	}

	@Override
	public FileEntry movePortletFileEntryToTrash(long userId, long fileEntryId)
		throws PortalException, SystemException {

		boolean dlAppHelperEnabled = DLAppHelperThreadLocal.isEnabled();

		try {
			DLAppHelperThreadLocal.setEnabled(false);

			return new PortletFileEntryWrapper(
				DLAppLocalServiceUtil.moveFileEntryToTrash(
					userId, fileEntryId));
		}
		finally {
			DLAppHelperThreadLocal.setEnabled(dlAppHelperEnabled);
		}
	}

	@Override
	public FileEntry movePortletFileEntryToTrash(
			long groupId, long userId, long folderId, String fileName)
		throws PortalException, SystemException {

		FileEntry fileEntry = new PortletFileEntryWrapper(
			DLAppLocalServiceUtil.getFileEntry(groupId, folderId, fileName));

		return movePortletFileEntryToTrash(userId, fileEntry.getFileEntryId());
	}

	@Override
	public void restorePortletFileEntryFromTrash(long userId, long fileEntryId)
		throws PortalException, SystemException {

		boolean dlAppHelperEnabled = DLAppHelperThreadLocal.isEnabled();

		try {
			DLAppHelperThreadLocal.setEnabled(false);

			DLAppLocalServiceUtil.restoreFileEntryFromTrash(
				userId, fileEntryId);
		}
		finally {
			DLAppHelperThreadLocal.setEnabled(dlAppHelperEnabled);
		}
	}

	@Override
	public void restorePortletFileEntryFromTrash(
			long groupId, long userId, long folderId, String fileName)
		throws PortalException, SystemException {

		FileEntry fileEntry = new PortletFileEntryWrapper(
			DLAppLocalServiceUtil.getFileEntry(groupId, folderId, fileName));

		restorePortletFileEntryFromTrash(userId, fileEntry.getFileEntryId());
	}

	/**
	 * @see com.liferay.portal.repository.liferayrepository.util.LiferayBase#toFileEntries
	 */
	protected List<FileEntry> toFileEntries(List<DLFileEntry> dlFileEntries) {
		List<FileEntry> fileEntries = new ArrayList<FileEntry>(
			dlFileEntries.size());

		for (DLFileEntry dlFileEntry : dlFileEntries) {
			FileEntry fileEntry = new LiferayFileEntry(dlFileEntry);

			fileEntries.add(new PortletFileEntryWrapper(fileEntry));
		}

		if (dlFileEntries instanceof UnmodifiableList) {
			return new UnmodifiableList<FileEntry>(fileEntries);
		}
		else {
			return fileEntries;
		}
	}

}