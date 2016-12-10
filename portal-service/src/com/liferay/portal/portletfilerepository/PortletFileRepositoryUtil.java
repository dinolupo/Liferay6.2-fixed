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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Repository;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.File;
import java.io.InputStream;

import java.util.List;

/**
 * @author Eudaldo Alonso
 * @author Alexander Chow
 */
public class PortletFileRepositoryUtil {

	public static void addPortletFileEntries(
			long groupId, long userId, String className, long classPK,
			String portletId, long folderId,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs)
		throws PortalException, SystemException {

		getPortletFileRepository().addPortletFileEntries(
			groupId, userId, className, classPK, portletId, folderId,
			inputStreamOVPs);
	}

	public static FileEntry addPortletFileEntry(
			long groupId, long userId, String className, long classPK,
			String portletId, long folderId, File file, String fileName,
			String mimeType, boolean indexingEnabled)
		throws PortalException, SystemException {

		return getPortletFileRepository().addPortletFileEntry(
			groupId, userId, className, classPK, portletId, folderId, file,
			fileName, mimeType, indexingEnabled);
	}

	public static FileEntry addPortletFileEntry(
			long groupId, long userId, String className, long classPK,
			String portletId, long folderId, InputStream inputStream,
			String fileName, String mimeType, boolean indexingEnabled)
		throws PortalException, SystemException {

		return getPortletFileRepository().addPortletFileEntry(
			groupId, userId, className, classPK, portletId, folderId,
			inputStream, fileName, mimeType, indexingEnabled);
	}

	public static Folder addPortletFolder(
			long userId, long repositoryId, long parentFolderId,
			String folderName, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getPortletFileRepository().addPortletFolder(
			userId, repositoryId, parentFolderId, folderName, serviceContext);
	}

	public static Repository addPortletRepository(
			long groupId, String portletId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getPortletFileRepository().addPortletRepository(
			groupId, portletId, serviceContext);
	}

	public static void deleteFolder(long folderId)
		throws PortalException, SystemException {

		getPortletFileRepository().deleteFolder(folderId);
	}

	public static void deletePortletFileEntries(long groupId, long folderId)
		throws PortalException, SystemException {

		getPortletFileRepository().deletePortletFileEntries(groupId, folderId);
	}

	public static void deletePortletFileEntries(
			long groupId, long folderId, int status)
		throws PortalException, SystemException {

		getPortletFileRepository().deletePortletFileEntries(
			groupId, folderId, status);
	}

	public static void deletePortletFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		getPortletFileRepository().deletePortletFileEntry(fileEntryId);
	}

	public static void deletePortletFileEntry(
			long groupId, long folderId, String fileName)
		throws PortalException, SystemException {

		getPortletFileRepository().deletePortletFileEntry(
			groupId, folderId, fileName);
	}

	public static void deletePortletRepository(long groupId, String portletId)
		throws PortalException, SystemException {

		getPortletFileRepository().deletePortletRepository(groupId, portletId);
	}

	public static Repository fetchPortletRepository(
			long groupId, String portletId)
		throws SystemException {

		return getPortletFileRepository().fetchPortletRepository(
			groupId, portletId);
	}

	public static List<FileEntry> getPortletFileEntries(
			long groupId, long folderId)
		throws SystemException {

		return getPortletFileRepository().getPortletFileEntries(
			groupId, folderId);
	}

	public static List<FileEntry> getPortletFileEntries(
			long groupId, long folderId, int status)
		throws SystemException {

		return getPortletFileRepository().getPortletFileEntries(
			groupId, folderId, status);
	}

	public static List<FileEntry> getPortletFileEntries(
			long groupId, long folderId, int status, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return getPortletFileRepository().getPortletFileEntries(
			groupId, folderId, status, start, end, obc);
	}

	public static int getPortletFileEntriesCount(long groupId, long folderId)
		throws SystemException {

		return getPortletFileRepository().getPortletFileEntriesCount(
			groupId, folderId);
	}

	public static int getPortletFileEntriesCount(
			long groupId, long folderId, int status)
		throws SystemException {

		return getPortletFileRepository().getPortletFileEntriesCount(
			groupId, folderId, status);
	}

	public static FileEntry getPortletFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		return getPortletFileRepository().getPortletFileEntry(fileEntryId);
	}

	public static FileEntry getPortletFileEntry(
			long groupId, long folderId, String fileName)
		throws PortalException, SystemException {

		return getPortletFileRepository().getPortletFileEntry(
			groupId, folderId, fileName);
	}

	public static FileEntry getPortletFileEntry(String uuid, long groupId)
		throws PortalException, SystemException {

		return getPortletFileRepository().getPortletFileEntry(uuid, groupId);
	}

	public static String getPortletFileEntryURL(
		ThemeDisplay themeDisplay, FileEntry fileEntry, String queryString) {

		return getPortletFileRepository().getPortletFileEntryURL(
			themeDisplay, fileEntry, queryString);
	}

	public static String getPortletFileEntryURL(
		ThemeDisplay themeDisplay, FileEntry fileEntry, String queryString,
		boolean absoluteURL) {

		return getPortletFileRepository().getPortletFileEntryURL(
			themeDisplay, fileEntry, queryString, absoluteURL);
	}

	public static PortletFileRepository getPortletFileRepository() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortletFileRepositoryUtil.class);

		return _portletFileRepository;
	}

	public static Folder getPortletFolder(long folderId)
		throws PortalException, SystemException {

		return getPortletFileRepository().getPortletFolder(folderId);
	}

	public static Folder getPortletFolder(
			long userId, long repositoryId, long parentFolderId,
			String folderName, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getPortletFileRepository().getPortletFolder(
			repositoryId, parentFolderId, folderName);
	}

	public static Repository getPortletRepository(
			long groupId, String portletId)
		throws PortalException, SystemException {

		return getPortletFileRepository().getPortletRepository(
			groupId, portletId);
	}

	public static FileEntry movePortletFileEntryToTrash(
			long userId, long fileEntryId)
		throws PortalException, SystemException {

		return getPortletFileRepository().movePortletFileEntryToTrash(
			userId, fileEntryId);
	}

	public static FileEntry movePortletFileEntryToTrash(
			long groupId, long userId, long folderId, String fileName)
		throws PortalException, SystemException {

		return getPortletFileRepository().movePortletFileEntryToTrash(
			groupId, userId, folderId, fileName);
	}

	public static void restorePortletFileEntryFromTrash(
			long userId, long fileEntryId)
		throws PortalException, SystemException {

		getPortletFileRepository().restorePortletFileEntryFromTrash(
			userId, fileEntryId);
	}

	public static void restorePortletFileEntryFromTrash(
			long groupId, long userId, long folderId, String fileName)
		throws PortalException, SystemException {

		getPortletFileRepository().restorePortletFileEntryFromTrash(
			groupId, userId, folderId, fileName);
	}

	public void setPortletFileRepository(
		PortletFileRepository portletFileRepository) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portletFileRepository = portletFileRepository;
	}

	private static PortletFileRepository _portletFileRepository;

}