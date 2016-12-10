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

package com.liferay.portlet.journal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for JournalFolder. This utility wraps
 * {@link com.liferay.portlet.journal.service.impl.JournalFolderServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see JournalFolderService
 * @see com.liferay.portlet.journal.service.base.JournalFolderServiceBaseImpl
 * @see com.liferay.portlet.journal.service.impl.JournalFolderServiceImpl
 * @generated
 */
@ProviderType
public class JournalFolderServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.journal.service.impl.JournalFolderServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.portlet.journal.model.JournalFolder addFolder(
		long groupId, long parentFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addFolder(groupId, parentFolderId, name, description,
			serviceContext);
	}

	public static void deleteFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFolder(folderId);
	}

	public static void deleteFolder(long folderId, boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFolder(folderId, includeTrashedEntries);
	}

	public static com.liferay.portlet.journal.model.JournalFolder fetchFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchFolder(folderId);
	}

	public static com.liferay.portlet.journal.model.JournalFolder getFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolder(folderId);
	}

	public static java.util.List<java.lang.Long> getFolderIds(long groupId,
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolderIds(groupId, folderId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolders(groupId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolders(groupId, parentFolderId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolders(groupId, parentFolderId, status);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolders(groupId, parentFolderId, start, end);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getFolders(groupId, parentFolderId, status, start, end);
	}

	public static java.util.List<java.lang.Object> getFoldersAndArticles(
		long groupId, long folderId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getFoldersAndArticles(groupId, folderId, status, start,
			end, obc);
	}

	public static java.util.List<java.lang.Object> getFoldersAndArticles(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getFoldersAndArticles(groupId, folderId, start, end, obc);
	}

	public static java.util.List<java.lang.Object> getFoldersAndArticles(
		long groupId, long userId, long folderId, int status, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getFoldersAndArticles(groupId, userId, folderId, status,
			start, end, obc);
	}

	public static int getFoldersAndArticlesCount(long groupId,
		java.util.List<java.lang.Long> folderIds, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getFoldersAndArticlesCount(groupId, folderIds, status);
	}

	public static int getFoldersAndArticlesCount(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoldersAndArticlesCount(groupId, folderId);
	}

	public static int getFoldersAndArticlesCount(long groupId, long folderId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoldersAndArticlesCount(groupId, folderId, status);
	}

	public static int getFoldersAndArticlesCount(long groupId, long userId,
		long folderId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getFoldersAndArticlesCount(groupId, userId, folderId, status);
	}

	public static int getFoldersCount(long groupId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoldersCount(groupId, parentFolderId);
	}

	public static int getFoldersCount(long groupId, long parentFolderId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoldersCount(groupId, parentFolderId, status);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getSubfolderIds(List, long,
	long, boolean)}
	*/
	public static void getSubfolderIds(
		java.util.List<java.lang.Long> folderIds, long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().getSubfolderIds(folderIds, groupId, folderId);
	}

	public static void getSubfolderIds(
		java.util.List<java.lang.Long> folderIds, long groupId, long folderId,
		boolean recurse)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().getSubfolderIds(folderIds, groupId, folderId, recurse);
	}

	public static java.util.List<java.lang.Long> getSubfolderIds(long groupId,
		long folderId, boolean recurse)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSubfolderIds(groupId, folderId, recurse);
	}

	public static com.liferay.portlet.journal.model.JournalFolder moveFolder(
		long folderId, long parentFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().moveFolder(folderId, parentFolderId, serviceContext);
	}

	public static com.liferay.portlet.journal.model.JournalFolder moveFolderFromTrash(
		long folderId, long parentFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .moveFolderFromTrash(folderId, parentFolderId, serviceContext);
	}

	public static com.liferay.portlet.journal.model.JournalFolder moveFolderToTrash(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().moveFolderToTrash(folderId);
	}

	public static void restoreFolderFromTrash(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().restoreFolderFromTrash(folderId);
	}

	public static com.liferay.portlet.journal.model.JournalFolder updateFolder(
		long folderId, long parentFolderId, java.lang.String name,
		java.lang.String description, boolean mergeWithParentFolder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateFolder(folderId, parentFolderId, name, description,
			mergeWithParentFolder, serviceContext);
	}

	public static JournalFolderService getService() {
		if (_service == null) {
			_service = (JournalFolderService)PortalBeanLocatorUtil.locate(JournalFolderService.class.getName());

			ReferenceRegistry.registerReference(JournalFolderServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(JournalFolderService service) {
	}

	private static JournalFolderService _service;
}