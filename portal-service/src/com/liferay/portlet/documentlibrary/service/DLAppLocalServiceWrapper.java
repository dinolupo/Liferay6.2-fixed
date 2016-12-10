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

package com.liferay.portlet.documentlibrary.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DLAppLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DLAppLocalService
 * @generated
 */
@ProviderType
public class DLAppLocalServiceWrapper implements DLAppLocalService,
	ServiceWrapper<DLAppLocalService> {
	public DLAppLocalServiceWrapper(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dlAppLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dlAppLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Adds a file entry and associated metadata based on a byte array.
	*
	* <p>
	* This method takes two file names, the <code>sourceFileName</code> and the
	* <code>title</code>. The <code>sourceFileName</code> corresponds to the
	* name of the actual file being uploaded. The <code>title</code>
	* corresponds to a name the client wishes to assign this file after it has
	* been uploaded to the portal. If it is <code>null</code>, the <code>
	* sourceFileName</code> will be used.
	* </p>
	*
	* @param userId the primary key of the file entry's creator/owner
	* @param repositoryId the primary key of the file entry's repository
	* @param folderId the primary key of the file entry's parent folder
	* @param sourceFileName the original file's name
	* @param mimeType the file's MIME type
	* @param title the name to be assigned to the file (optionally <code>null
	</code>)
	* @param description the file's description
	* @param changeLog the file's version change log
	* @param bytes the file's data (optionally <code>null</code>)
	* @param serviceContext the service context to be applied. Can set the
	asset category IDs, asset tag names, and expando bridge
	attributes for the file entry. In a Liferay repository, it may
	include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	type </li> <li> fieldsMap - mapping for fields associated with a
	custom file entry type </li> </ul>
	* @return the file entry
	* @throws PortalException if the parent folder could not be found or if the
	file entry's information was invalid
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry addFileEntry(
		long userId, long repositoryId, long folderId,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, byte[] bytes,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.addFileEntry(userId, repositoryId, folderId,
			sourceFileName, mimeType, title, description, changeLog, bytes,
			serviceContext);
	}

	/**
	* Adds a file entry and associated metadata based on a {@link java.io.File}
	* object.
	*
	* <p>
	* This method takes two file names, the <code>sourceFileName</code> and the
	* <code>title</code>. The <code>sourceFileName</code> corresponds to the
	* name of the actual file being uploaded. The <code>title</code>
	* corresponds to a name the client wishes to assign this file after it has
	* been uploaded to the portal. If it is <code>null</code>, the <code>
	* sourceFileName</code> will be used.
	* </p>
	*
	* @param userId the primary key of the file entry's creator/owner
	* @param repositoryId the primary key of the repository
	* @param folderId the primary key of the file entry's parent folder
	* @param sourceFileName the original file's name
	* @param mimeType the file's MIME type
	* @param title the name to be assigned to the file (optionally <code>null
	</code>)
	* @param description the file's description
	* @param changeLog the file's version change log
	* @param file the file's data (optionally <code>null</code>)
	* @param serviceContext the service context to be applied. Can set the
	asset category IDs, asset tag names, and expando bridge
	attributes for the file entry. In a Liferay repository, it may
	include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	type </li> <li> fieldsMap - mapping for fields associated with a
	custom file entry type </li> </ul>
	* @return the file entry
	* @throws PortalException if the parent folder could not be found or if the
	file entry's information was invalid
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry addFileEntry(
		long userId, long repositoryId, long folderId,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, java.io.File file,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.addFileEntry(userId, repositoryId, folderId,
			sourceFileName, mimeType, title, description, changeLog, file,
			serviceContext);
	}

	/**
	* Adds a file entry and associated metadata based on an {@link InputStream}
	* object.
	*
	* <p>
	* This method takes two file names, the <code>sourceFileName</code> and the
	* <code>title</code>. The <code>sourceFileName</code> corresponds to the
	* name of the actual file being uploaded. The <code>title</code>
	* corresponds to a name the client wishes to assign this file after it has
	* been uploaded to the portal. If it is <code>null</code>, the <code>
	* sourceFileName</code> will be used.
	* </p>
	*
	* @param userId the primary key of the file entry's creator/owner
	* @param repositoryId the primary key of the repository
	* @param folderId the primary key of the file entry's parent folder
	* @param sourceFileName the original file's name
	* @param mimeType the file's MIME type
	* @param title the name to be assigned to the file (optionally <code>null
	</code>)
	* @param description the file's description
	* @param changeLog the file's version change log
	* @param is the file's data (optionally <code>null</code>)
	* @param size the file's size (optionally <code>0</code>)
	* @param serviceContext the service context to be applied. Can set the
	asset category IDs, asset tag names, and expando bridge
	attributes for the file entry. In a Liferay repository, it may
	include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	type </li> <li> fieldsMap - mapping for fields associated with a
	custom file entry type </li> </ul>
	* @return the file entry
	* @throws PortalException if the parent folder could not be found or if the
	file entry's information was invalid
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry addFileEntry(
		long userId, long repositoryId, long folderId,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, java.io.InputStream is, long size,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.addFileEntry(userId, repositoryId, folderId,
			sourceFileName, mimeType, title, description, changeLog, is, size,
			serviceContext);
	}

	/**
	* Adds the file rank to the existing file entry. This method is only
	* supported by the Liferay repository.
	*
	* @param repositoryId the primary key of the repository
	* @param companyId the primary key of the company
	* @param userId the primary key of the file rank's creator/owner
	* @param fileEntryId the primary key of the file entry
	* @param serviceContext the service context to be applied
	* @return the file rank
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.documentlibrary.model.DLFileRank addFileRank(
		long repositoryId, long companyId, long userId, long fileEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.addFileRank(repositoryId, companyId, userId,
			fileEntryId, serviceContext);
	}

	/**
	* Adds the file shortcut to the existing file entry. This method is only
	* supported by the Liferay repository.
	*
	* @param userId the primary key of the file shortcut's creator/owner
	* @param repositoryId the primary key of the repository
	* @param folderId the primary key of the file shortcut's parent folder
	* @param toFileEntryId the primary key of the file entry to point to
	* @param serviceContext the service context to be applied. Can set the
	asset category IDs, asset tag names, and expando bridge
	attributes for the file entry.
	* @return the file shortcut
	* @throws PortalException if the parent folder or file entry could not be
	found, or if the file shortcut's information was invalid
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.documentlibrary.model.DLFileShortcut addFileShortcut(
		long userId, long repositoryId, long folderId, long toFileEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.addFileShortcut(userId, repositoryId,
			folderId, toFileEntryId, serviceContext);
	}

	/**
	* Adds a folder.
	*
	* @param userId the primary key of the folder's creator/owner
	* @param repositoryId the primary key of the repository
	* @param parentFolderId the primary key of the folder's parent folder
	* @param name the folder's name
	* @param description the folder's description
	* @param serviceContext the service context to be applied. In a Liferay
	repository, it may include mountPoint which is a boolean
	specifying whether the folder is a facade for mounting a
	third-party repository
	* @return the folder
	* @throws PortalException if the parent folder could not be found or if the
	new folder's information was invalid
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.Folder addFolder(
		long userId, long repositoryId, long parentFolderId,
		java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.addFolder(userId, repositoryId,
			parentFolderId, name, description, serviceContext);
	}

	/**
	* Delete all data associated to the given repository. This method is only
	* supported by the Liferay repository.
	*
	* @param repositoryId the primary key of the data's repository
	* @throws PortalException if the repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteAll(long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.deleteAll(repositoryId);
	}

	/**
	* Deletes the file entry.
	*
	* @param fileEntryId the primary key of the file entry
	* @throws PortalException if the file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteFileEntry(long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.deleteFileEntry(fileEntryId);
	}

	/**
	* Deletes the file ranks associated to a given file entry. This method is
	* only supported by the Liferay repository.
	*
	* @param fileEntryId the primary key of the file entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteFileRanksByFileEntryId(long fileEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.deleteFileRanksByFileEntryId(fileEntryId);
	}

	/**
	* Deletes the file ranks associated to a given user. This method is only
	* supported by the Liferay repository.
	*
	* @param userId the primary key of the user
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteFileRanksByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.deleteFileRanksByUserId(userId);
	}

	/**
	* Deletes the file shortcut. This method is only supported by the Liferay
	* repository.
	*
	* @param dlFileShortcut the file shortcut
	* @throws PortalException if the file shortcut could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteFileShortcut(
		com.liferay.portlet.documentlibrary.model.DLFileShortcut dlFileShortcut)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.deleteFileShortcut(dlFileShortcut);
	}

	/**
	* Deletes the file shortcut. This method is only supported by the Liferay
	* repository.
	*
	* @param fileShortcutId the primary key of the file shortcut
	* @throws PortalException if the file shortcut could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteFileShortcut(long fileShortcutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.deleteFileShortcut(fileShortcutId);
	}

	/**
	* Deletes all file shortcuts associated to the file entry. This method is
	* only supported by the Liferay repository.
	*
	* @param toFileEntryId the primary key of the associated file entry
	* @throws PortalException if the file shortcut for the file entry could not
	be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteFileShortcuts(long toFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.deleteFileShortcuts(toFileEntryId);
	}

	/**
	* Deletes the folder and all of its subfolders and file entries.
	*
	* @param folderId the primary key of the folder
	* @throws PortalException if the folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.deleteFolder(folderId);
	}

	/**
	* Returns the file entry with the primary key.
	*
	* @param fileEntryId the primary key of the file entry
	* @return the file entry with the primary key
	* @throws PortalException if the file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry getFileEntry(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.getFileEntry(fileEntryId);
	}

	/**
	* Returns the file entry with the title in the folder.
	*
	* @param groupId the primary key of the file entry's group
	* @param folderId the primary key of the file entry's folder
	* @param title the file entry's title
	* @return the file entry with the title in the folder
	* @throws PortalException if the file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry getFileEntry(
		long groupId, long folderId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.getFileEntry(groupId, folderId, title);
	}

	/**
	* Returns the file entry with the UUID and group.
	*
	* @param uuid the file entry's UUID
	* @param groupId the primary key of the file entry's group
	* @return the file entry with the UUID and group
	* @throws PortalException if the file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry getFileEntryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.getFileEntryByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the file ranks from the user. This method is only supported by
	* the Liferay repository.
	*
	* @param repositoryId the primary key of the repository
	* @param userId the primary key of the user
	* @return the file ranks from the user
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileRank> getFileRanks(
		long repositoryId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.getFileRanks(repositoryId, userId);
	}

	/**
	* Returns the file shortcut with the primary key. This method is only
	* supported by the Liferay repository.
	*
	* @param fileShortcutId the primary key of the file shortcut
	* @return the file shortcut with the primary key
	* @throws PortalException if the file shortcut could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.documentlibrary.model.DLFileShortcut getFileShortcut(
		long fileShortcutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.getFileShortcut(fileShortcutId);
	}

	/**
	* Returns the file version with the primary key.
	*
	* @param fileVersionId the primary key of the file version
	* @return the file version with the primary key
	* @throws PortalException if the file version could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileVersion getFileVersion(
		long fileVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.getFileVersion(fileVersionId);
	}

	/**
	* Returns the folder with the primary key.
	*
	* @param folderId the primary key of the folder
	* @return the folder with the primary key
	* @throws PortalException if the folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.Folder getFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.getFolder(folderId);
	}

	/**
	* Returns the folder with the name in the parent folder.
	*
	* @param repositoryId the primary key of the folder's repository
	* @param parentFolderId the primary key of the folder's parent folder
	* @param name the folder's name
	* @return the folder with the name in the parent folder
	* @throws PortalException if the folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.Folder getFolder(
		long repositoryId, long parentFolderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.getFolder(repositoryId, parentFolderId, name);
	}

	/**
	* Returns the mount folder of the repository with the primary key. This
	* method is only supported by the Liferay repository.
	*
	* @param repositoryId the primary key of the repository
	* @return the folder used for mounting third-party repositories
	* @throws PortalException if the repository or mount folder could not be
	found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.Folder getMountFolder(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.getMountFolder(repositoryId);
	}

	/**
	* Moves the file entry to the new folder.
	*
	* @param userId the primary key of the user
	* @param fileEntryId the primary key of the file entry
	* @param newFolderId the primary key of the new folder
	* @param serviceContext the service context to be applied
	* @return the file entry
	* @throws PortalException if the file entry or the new folder could not be
	found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry moveFileEntry(
		long userId, long fileEntryId, long newFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.moveFileEntry(userId, fileEntryId,
			newFolderId, serviceContext);
	}

	/**
	* Moves the file entry with the primary key to the trash portlet.
	*
	* @param userId the primary key of the user
	* @param fileEntryId the primary key of the file entry
	* @return the file entry
	* @throws PortalException if the file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry moveFileEntryToTrash(
		long userId, long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.moveFileEntryToTrash(userId, fileEntryId);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder moveFolder(
		long userId, long folderId, long parentFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.moveFolder(userId, folderId, parentFolderId,
			serviceContext);
	}

	/**
	* Restores the file entry with the primary key from the trash portlet.
	*
	* @param userId the primary key of the user
	* @param fileEntryId the primary key of the file entry
	* @throws PortalException if the file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void restoreFileEntryFromTrash(long userId, long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.restoreFileEntryFromTrash(userId, fileEntryId);
	}

	/**
	* Subscribe the user to changes in documents of the file entry type. This
	* method is only supported by the Liferay repository.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the file entry type's group
	* @param fileEntryTypeId the primary key of the file entry type
	* @throws PortalException if the user or group could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void subscribeFileEntryType(long userId, long groupId,
		long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.subscribeFileEntryType(userId, groupId,
			fileEntryTypeId);
	}

	/**
	* Subscribe the user to document changes in the folder. This method is only
	* supported by the Liferay repository.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the folder's group
	* @param folderId the primary key of the folder
	* @throws PortalException if the user or group could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void subscribeFolder(long userId, long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.subscribeFolder(userId, groupId, folderId);
	}

	/**
	* Unsubscribe the user from changes in documents of the file entry type.
	* This method is only supported by the Liferay repository.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the file entry type's group
	* @param fileEntryTypeId the primary key of the file entry type
	* @throws PortalException if the user or group could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void unsubscribeFileEntryType(long userId, long groupId,
		long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.unsubscribeFileEntryType(userId, groupId,
			fileEntryTypeId);
	}

	/**
	* Unsubscribe the user from document changes in the folder. This method is
	* only supported by the Liferay repository.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the folder's group
	* @param folderId the primary key of the folder
	* @throws PortalException if the user or group could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void unsubscribeFolder(long userId, long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.unsubscribeFolder(userId, groupId, folderId);
	}

	/**
	* Updates the file entry's asset replacing its asset categories, tags, and
	* links.
	*
	* @param userId the primary key of the user
	* @param fileEntry the file entry to update
	* @param fileVersion the file version to update
	* @param assetCategoryIds the primary keys of the new asset categories
	* @param assetTagNames the new asset tag names
	* @param assetLinkEntryIds the primary keys of the new asset link entries
	* @throws PortalException if the file entry or version could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void updateAsset(long userId,
		com.liferay.portal.kernel.repository.model.FileEntry fileEntry,
		com.liferay.portal.kernel.repository.model.FileVersion fileVersion,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.updateAsset(userId, fileEntry, fileVersion,
			assetCategoryIds, assetTagNames, assetLinkEntryIds);
	}

	/**
	* Updates a file entry and associated metadata based on a byte array
	* object. If the file data is <code>null</code>, then only the associated
	* metadata (i.e., <code>title</code>, <code>description</code>, and
	* parameters in the <code>serviceContext</code>) will be updated.
	*
	* <p>
	* This method takes two file names, the <code>sourceFileName</code> and the
	* <code>title</code>. The <code>sourceFileName</code> corresponds to the
	* name of the actual file being uploaded. The <code>title</code>
	* corresponds to a name the client wishes to assign this file after it has
	* been uploaded to the portal.
	* </p>
	*
	* @param userId the primary key of the user
	* @param fileEntryId the primary key of the file entry
	* @param sourceFileName the original file's name (optionally
	<code>null</code>)
	* @param mimeType the file's MIME type (optionally <code>null</code>)
	* @param title the new name to be assigned to the file (optionally <code>
	<code>null</code></code>)
	* @param description the file's new description
	* @param changeLog the file's version change log (optionally
	<code>null</code>)
	* @param majorVersion whether the new file version is a major version
	* @param bytes the file's data (optionally <code>null</code>)
	* @param serviceContext the service context to be applied. Can set the
	asset category IDs, asset tag names, and expando bridge
	attributes for the file entry. In a Liferay repository, it may
	include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	type </li> <li> fieldsMap - mapping for fields associated with a
	custom file entry type </li> </ul>
	* @return the file entry
	* @throws PortalException if the file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry updateFileEntry(
		long userId, long fileEntryId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		boolean majorVersion, byte[] bytes,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.updateFileEntry(userId, fileEntryId,
			sourceFileName, mimeType, title, description, changeLog,
			majorVersion, bytes, serviceContext);
	}

	/**
	* Updates a file entry and associated metadata based on a {@link
	* java.io.File} object. If the file data is <code>null</code>, then only
	* the associated metadata (i.e., <code>title</code>,
	* <code>description</code>, and parameters in the
	* <code>serviceContext</code>) will be updated.
	*
	* <p>
	* This method takes two file names, the <code>sourceFileName</code> and the
	* <code>title</code>. The <code>sourceFileName</code> corresponds to the
	* name of the actual file being uploaded. The <code>title</code>
	* corresponds to a name the client wishes to assign this file after it has
	* been uploaded to the portal.
	* </p>
	*
	* @param userId the primary key of the user
	* @param fileEntryId the primary key of the file entry
	* @param sourceFileName the original file's name (optionally
	<code>null</code>)
	* @param mimeType the file's MIME type (optionally <code>null</code>)
	* @param title the new name to be assigned to the file (optionally <code>
	<code>null</code></code>)
	* @param description the file's new description
	* @param changeLog the file's version change log (optionally
	<code>null</code>)
	* @param majorVersion whether the new file version is a major version
	* @param file the file's data (optionally <code>null</code>)
	* @param serviceContext the service context to be applied. Can set the
	asset category IDs, asset tag names, and expando bridge
	attributes for the file entry. In a Liferay repository, it may
	include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	type </li> <li> fieldsMap - mapping for fields associated with a
	custom file entry type </li> </ul>
	* @return the file entry
	* @throws PortalException if the file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry updateFileEntry(
		long userId, long fileEntryId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		boolean majorVersion, java.io.File file,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.updateFileEntry(userId, fileEntryId,
			sourceFileName, mimeType, title, description, changeLog,
			majorVersion, file, serviceContext);
	}

	/**
	* Updates a file entry and associated metadata based on an {@link
	* InputStream} object. If the file data is <code>null</code>, then only the
	* associated metadata (i.e., <code>title</code>, <code>description</code>,
	* and parameters in the <code>serviceContext</code>) will be updated.
	*
	* <p>
	* This method takes two file names, the <code>sourceFileName</code> and the
	* <code>title</code>. The <code>sourceFileName</code> corresponds to the
	* name of the actual file being uploaded. The <code>title</code>
	* corresponds to a name the client wishes to assign this file after it has
	* been uploaded to the portal.
	* </p>
	*
	* @param userId the primary key of the user
	* @param fileEntryId the primary key of the file entry
	* @param sourceFileName the original file's name (optionally
	<code>null</code>)
	* @param mimeType the file's MIME type (optionally <code>null</code>)
	* @param title the new name to be assigned to the file (optionally <code>
	<code>null</code></code>)
	* @param description the file's new description
	* @param changeLog the file's version change log (optionally
	<code>null</code>)
	* @param majorVersion whether the new file version is a major version
	* @param is the file's data (optionally <code>null</code>)
	* @param size the file's size (optionally <code>0</code>)
	* @param serviceContext the service context to be applied. Can set the
	asset category IDs, asset tag names, and expando bridge
	attributes for the file entry. In a Liferay repository, it may
	include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	type </li> <li> fieldsMap - mapping for fields associated with a
	custom file entry type </li> </ul>
	* @return the file entry
	* @throws PortalException if the file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry updateFileEntry(
		long userId, long fileEntryId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		boolean majorVersion, java.io.InputStream is, long size,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.updateFileEntry(userId, fileEntryId,
			sourceFileName, mimeType, title, description, changeLog,
			majorVersion, is, size, serviceContext);
	}

	/**
	* Updates a file rank to the existing file entry. This method is only
	* supported by the Liferay repository.
	*
	* @param repositoryId the primary key of the file rank's repository
	* @param companyId the primary key of the file rank's company
	* @param userId the primary key of the file rank's creator/owner
	* @param fileEntryId the primary key of the file rank's file entry
	* @param serviceContext the service context to be applied
	* @return the file rank
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.documentlibrary.model.DLFileRank updateFileRank(
		long repositoryId, long companyId, long userId, long fileEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.updateFileRank(repositoryId, companyId,
			userId, fileEntryId, serviceContext);
	}

	/**
	* Updates a file shortcut to the existing file entry. This method is only
	* supported by the Liferay repository.
	*
	* @param userId the primary key of the file shortcut's creator/owner
	* @param fileShortcutId the primary key of the file shortcut
	* @param folderId the primary key of the file shortcut's parent folder
	* @param toFileEntryId the primary key of the file shortcut's file entry
	* @param serviceContext the service context to be applied. Can set the
	asset category IDs, asset tag names, and expando bridge
	attributes for the file entry.
	* @return the file shortcut
	* @throws PortalException if the file shortcut, folder, or file entry could
	not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.documentlibrary.model.DLFileShortcut updateFileShortcut(
		long userId, long fileShortcutId, long folderId, long toFileEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.updateFileShortcut(userId, fileShortcutId,
			folderId, toFileEntryId, serviceContext);
	}

	/**
	* Updates all file shortcuts to the existing file entry to the new file
	* entry. This method is only supported by the Liferay repository.
	*
	* @param toRepositoryId the primary key of the repository
	* @param oldToFileEntryId the primary key of the old file entry pointed to
	* @param newToFileEntryId the primary key of the new file entry to point
	to
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void updateFileShortcuts(long toRepositoryId, long oldToFileEntryId,
		long newToFileEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlAppLocalService.updateFileShortcuts(toRepositoryId,
			oldToFileEntryId, newToFileEntryId);
	}

	/**
	* Updates the folder.
	*
	* @param folderId the primary key of the folder
	* @param parentFolderId the primary key of the folder's new parent folder
	* @param name the folder's new name
	* @param description the folder's new description
	* @param serviceContext the service context to be applied. In a Liferay
	repository, it may include:  <ul> <li> defaultFileEntryTypeId -
	the file entry type to default all Liferay file entries to </li>
	<li> dlFileEntryTypesSearchContainerPrimaryKeys - a
	comma-delimited list of file entry type primary keys allowed in
	the given folder and all descendants </li> <li>
	overrideFileEntryTypes - boolean specifying whether to override
	ancestral folder's restriction of file entry types allowed </li>
	<li> workflowDefinitionXYZ - the workflow definition name
	specified per file entry type. The parameter name must be the
	string <code>workflowDefinition</code> appended by the <code>
	fileEntryTypeId</code> (optionally <code>0</code>). </li> </ul>
	* @return the folder
	* @throws PortalException if the current or new parent folder could not be
	found, or if the new parent folder's information was invalid
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.repository.model.Folder updateFolder(
		long folderId, long parentFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlAppLocalService.updateFolder(folderId, parentFolderId, name,
			description, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public DLAppLocalService getWrappedDLAppLocalService() {
		return _dlAppLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedDLAppLocalService(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	@Override
	public DLAppLocalService getWrappedService() {
		return _dlAppLocalService;
	}

	@Override
	public void setWrappedService(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	private DLAppLocalService _dlAppLocalService;
}