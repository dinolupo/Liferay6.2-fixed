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

package com.liferay.portal.kernel.trash;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.ClassedModel;
import com.liferay.portal.model.ContainerModel;
import com.liferay.portal.model.SystemEvent;
import com.liferay.portal.model.TrashedModel;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.trash.model.TrashEntry;

import java.util.List;

import javax.portlet.PortletRequest;

/**
 * The interface for managing the basic trash operations of the Recycle Bin,
 * which include:
 *
 * <ul>
 * <li>
 * Deleting trash entries
 * </li>
 * <li>
 * Moving trash entries out of the Recycle Bin to new destinations
 * </li>
 * <li>
 * Restoring trash entries to their original locations
 * </li>
 * </ul>
 *
 * <p>
 * These operations are supported for the following entities via their
 * respective trash handlers:
 * </p>
 *
 * <ul>
 * <li>
 * BlogsEntry via {@link com.liferay.portlet.blogs.trash.BlogsEntryTrashHandler}
 * </li>
 * <li>
 * BookmarksEntry via {@link
 * com.liferay.portlet.bookmarks.trash.BookmarksEntryTrashHandler}
 * </li>
 * <li>
 * DLFileEntry via {@link
 * com.liferay.portlet.documentlibrary.trash.DLFileEntryTrashHandler}
 * </li>
 * <li>
 * DLFileShortcut via {@link
 * com.liferay.portlet.documentlibrary.trash.DLFileShortcutTrashHandler}
 * </li>
 * <li>
 * DLFolder via {@link
 * com.liferay.portlet.documentlibrary.trash.DLFolderTrashHandler}
 * </li>
 * <li>
 * MBThread via {@link
 * com.liferay.portlet.messageboards.trash.MBThreadTrashHandler}
 * </li>
 * <li>
 * WikiNode via {@link
 * com.liferay.portlet.wiki.trash.WikiNodeTrashHandler}
 * </li>
 * <li>
 * WikiPage via {@link
 * com.liferay.portlet.wiki.trash.WikiPageTrashHandler}
 * </li>
 * </ul>
 *
 * @author Alexander Chow
 * @author Zsolt Berentey
 */
public interface TrashHandler {

	public SystemEvent addDeletionSystemEvent(
			long userId, long groupId, long classPK, String classUuid,
			String referrerClassName)
		throws PortalException, SystemException;

	public void checkDuplicateEntry(
			long classPK, long containerModelId, String newName)
		throws PortalException, SystemException;

	/**
	 * Checks if a duplicate trash entry already exists in the destination
	 * container.
	 *
	 * <p>
	 * This method is used to check for duplicates when a trash entry is being
	 * restored or moved out of the Recycle Bin.
	 * </p>
	 *
	 * @param  trashEntry the trash entry to check
	 * @param  containerModelId the primary key of the destination (e.g. folder)
	 * @param  newName the new name to be assigned to the trash entry
	 *         (optionally <code>null</code> to forego renaming the trash entry)
	 * @throws PortalException if a duplicate trash entry already existed in the
	 *         destination container
	 * @throws SystemException if a system exception occurred
	 */
	public void checkDuplicateTrashEntry(
			TrashEntry trashEntry, long containerModelId, String newName)
		throws PortalException, SystemException;

	/**
	 * Deletes the model entity with the primary key.
	 *
	 * @param  classPK the primary key of the model entity to delete
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTrashEntry(long classPK)
		throws PortalException, SystemException;

	/**
	 * Returns the class name handled by this trash handler.
	 *
	 * @return the class name handled by this trash handler
	 */
	public String getClassName();

	/**
	 * Returns the container model with the primary key.
	 *
	 * @param  containerModelId the primary key of the container model
	 * @return the container model with the primary key
	 * @throws PortalException if a container model with the primary key could
	 *         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ContainerModel getContainerModel(long containerModelId)
		throws PortalException, SystemException;

	/**
	 * Returns the parent container model's class name.
	 */
	public String getContainerModelClassName();

	/**
	 * Returns the name of the container model (e.g. folder name).
	 *
	 * @return the name of the container model
	 */
	public String getContainerModelName();

	/**
	 * Returns a range of all the container models that are children of the
	 * parent container model identified by the container model ID. These
	 * container models must be able to contain the model entity identified by
	 * the primary key.
	 *
	 * <p>
	 * This method checks for the view permission when retrieving the container
	 * models.
	 * </p>
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. The <code>start</code> and <code>end</code>
	 * values are not primary keys but, rather, indexes in the result set. Thus,
	 * <code>0</code> refers to the first result in the set. Setting both
	 * <code>start</code> and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  classPK the primary key of a model entity the container models
	 *         must be able to contain
	 * @param  containerModelId the primary key of the parent container model
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @return the range of matching container models
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContainerModel> getContainerModels(
			long classPK, long containerModelId, int start, int end)
		throws PortalException, SystemException;

	/**
	 * Returns the number of container models that are children of the parent
	 * container model identified by the container model ID. These container
	 * models must be able to contain the model entity identified by the primary
	 * key.
	 *
	 * <p>
	 * This method checks for the view permission when counting the container
	 * models.
	 * </p>
	 *
	 * @param  classPK the primary key of a model entity the container models
	 *         must be able to contain
	 * @param  containerModelId the primary key of the parent container model
	 * @return the number of matching container models
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public int getContainerModelsCount(long classPK, long containerModelId)
		throws PortalException, SystemException;

	/**
	 * Returns the language key to the localized message to display next to a
	 * trash entry listed in a search result, indicating that the trash entry
	 * was found in a trashed container (e.g. folder or message board thread)
	 * this trash handler is associated with.
	 *
	 * <p>
	 * If the language key (e.g. <code>found-in-deleted-folder-x</code>) used
	 * accepts a single parameter, the trash framework replaces that parameter
	 * with the trashed container's name.
	 * </p>
	 *
	 * @return the language key to the localized message to display next to a
	 *         trash entry listed in a search result
	 */
	public String getDeleteMessage();

	/**
	 * Returns the parent container model of the model entity with the primary
	 * key.
	 *
	 * @param  classPK the primary key of a model entity the container models
	 *         must be able to contain
	 * @return the parent container model of the model entity with the primary
	 *         key
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public ContainerModel getParentContainerModel(long classPK)
		throws PortalException, SystemException;

	public ContainerModel getParentContainerModel(TrashedModel trashedModel)
		throws PortalException, SystemException;

	/**
	 * Returns all the parent container models of the model entity with the
	 * primary key ordered by hierarchy.
	 *
	 * <p>
	 * For example, if the primary key is for a file entry inside folder C,
	 * which is inside folder B, which is inside folder A; this method returns
	 * container models for folders A, B, and C.
	 * </p>
	 *
	 * @param  classPK the primary key of a model entity the container models
	 *         must be able to contain
	 * @return all the matching parent container models of the model entity
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public List<ContainerModel> getParentContainerModels(long classPK)
		throws PortalException, SystemException;

	public String getRestoreContainedModelLink(
			PortletRequest portletRequest, long classPK)
		throws PortalException, SystemException;

	/**
	 * Returns the link to the location to which the model entity was restored.
	 *
	 * @param  portletRequest the portlet request
	 * @param  classPK the primary key of the restored model entity
	 * @return the restore link
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public String getRestoreContainerModelLink(
			PortletRequest portletRequest, long classPK)
		throws PortalException, SystemException;

	/**
	 * Returns the message describing the location to which the model entity was
	 * restored.
	 *
	 * @param  portletRequest the portlet request
	 * @param  classPK the primary key of the restored model entity
	 * @return the restore message
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public String getRestoreMessage(PortletRequest portletRequest, long classPK)
		throws PortalException, SystemException;

	/**
	 * Returns the name of the root container (e.g. "home").
	 *
	 * @return the name of the root container
	 */
	public String getRootContainerModelName();

	/**
	 * Returns the name of the subcontainer model (e.g. for a folder the
	 * subcontainer model name may be "subfolder").
	 *
	 * @return the name of the subcontainer model
	 */
	public String getSubcontainerModelName();

	public String getSystemEventClassName();

	/**
	 * Returns the name of the contained model.
	 *
	 * <p>
	 * For example, "files" may be the model name for a folder and "pages" may
	 * be the model name for a wiki node.
	 * </p>
	 *
	 * @return the name of the contained model
	 */
	public String getTrashContainedModelName();

	/**
	 * Returns the number of model entities (excluding container model entities)
	 * that are children of the parent container model identified by the primary
	 * key.
	 *
	 * <p>
	 * For example, for a folder with subfolders and documents, the number of
	 * documents (excluding those explicitely moved to the recycle bin) is
	 * returned.
	 * </p>
	 *
	 * @param  classPK the primary key of a container model
	 * @return the number of model entities that are children of the parent
	 *         container model identified by the primary key
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public int getTrashContainedModelsCount(long classPK)
		throws PortalException, SystemException;

	/**
	 * Returns a range of all the trash renderers of model entities (excluding
	 * container models) that are children of the parent container model
	 * identified by the primary key.
	 *
	 * <p>
	 * For example, for a folder with subfolders and documents, a range of all
	 * the trash renderers of documents (excluding those explicitly moved to the
	 * recycle bin) is returned.
	 * </p>
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. The <code>start</code> and <code>end</code>
	 * values are not primary keys but, rather, indexes in the result set. Thus,
	 * <code>0</code> refers to the first result in the set. Setting both
	 * <code>start</code> and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  classPK the primary key of a container model
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @return the range of trash renderers of model entities (excluding
	 *         container models) that are children of the parent container model
	 *         identified by the primary key
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrashRenderer> getTrashContainedModelTrashRenderers(
			long classPK, int start, int end)
		throws PortalException, SystemException;

	/**
	 * Returns the name of the container model.
	 *
	 * <p>
	 * For example, "folder" may be the container model name for a file entry.
	 * </p>
	 *
	 * @return the name of the container model
	 */
	public String getTrashContainerModelName();

	/**
	 * Returns the number of container models that are children of the parent
	 * container model identified by the primary key.
	 *
	 * <p>
	 * For example, for a folder with subfolders and documents, the number of
	 * folders (excluding those explicitly moved to the recycle bin) is
	 * returned.
	 * </p>
	 *
	 * @param  classPK the primary key of a container model
	 * @return the number of container models that are children of the parent
	 *         container model identified by the primary key
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public int getTrashContainerModelsCount(long classPK)
		throws PortalException, SystemException;

	/**
	 * Returns a range of all the trash renderers of model entities that are
	 * children of the parent container model identified by the primary key.
	 *
	 * <p>
	 * For example, for a folder with subfolders and documents, the range of
	 * renderers representing folders (excluding those explicitly moved to the
	 * recycle bin) is returned.
	 * </p>
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. The <code>start</code> and <code>end</code>
	 * values are not primary keys but, rather, indexes in the result set. Thus,
	 * <code>0</code> refers to the first result in the set. Setting both
	 * <code>start</code> and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  classPK the primary key of a container model
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @return the range of matching trash renderers of model entities
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrashRenderer> getTrashContainerModelTrashRenderers(
			long classPK, int start, int end)
		throws PortalException, SystemException;

	public TrashEntry getTrashEntry(long classPK)
		throws PortalException, SystemException;

	/**
	 * Returns the trash renderer associated to the model entity with the
	 * primary key.
	 *
	 * @param  classPK the primary key of the model entity
	 * @return the trash renderer associated to the model entity
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrashRenderer getTrashRenderer(long classPK)
		throws PortalException, SystemException;

	/**
	 * Returns <code>true</code> if the user has the required permission to
	 * perform the trash action on the model entity with the primary key.
	 *
	 * <p>
	 * This method is a mapper for special Recycle Bin operations that are not
	 * real permissions. The implementations of this method should translate
	 * these virtual permissions to real permission checks.
	 * </p>
	 *
	 * @param  permissionChecker the permission checker
	 * @param  groupId the primary key of the group
	 * @param  classPK the primary key of the model entity
	 * @param  trashActionId the trash action permission to check
	 * @return <code>true</code> if the user has the required permission;
	 *         <code>false</code> otherwise
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public boolean hasTrashPermission(
			PermissionChecker permissionChecker, long groupId, long classPK,
			String trashActionId)
		throws PortalException, SystemException;

	/**
	 * Returns <code>true</code> if the entity is a container model.
	 *
	 * @return <code>true</code> if the entity is a container model;
	 *         <code>false</code> otherwise
	 */
	public boolean isContainerModel();

	/**
	 * Returns <code>true</code> if the entity can be deleted from the Recycle
	 * Bin.
	 *
	 * @return <code>true</code> if the entity can be deleted from the Recycle
	 *         Bin.
	 */
	public boolean isDeletable();

	/**
	 * Returns <code>true</code> if the model entity with the primary key is in
	 * the Recycle Bin.
	 *
	 * @param  classPK the primary key of the model entity
	 * @return <code>true</code> if the model entity is in the Recycle Bin;
	 *         <code>false</code> otherwise
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found in the portal
	 * @throws SystemException if a system exception occurred
	 */
	public boolean isInTrash(long classPK)
		throws PortalException, SystemException;

	/**
	 * Returns <code>true</code> if the model entity with the primary key is in
	 * a container that is in the Recycle Bin.
	 *
	 * @param  classPK the primary key of the model entity
	 * @return <code>true</code> if the model entity with the primary key is in
	 *         a container that is in the Recycle Bin; <code>false</code>
	 *         otherwise
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found in the portal
	 * @throws SystemException if a system exception occurred
	 */
	public boolean isInTrashContainer(long classPK)
		throws PortalException, SystemException;

	/**
	 * Returns <code>true</code> if the entity can be moved from one container
	 * model (such as a folder) to another.
	 *
	 * @return <code>true</code> if the entity can be moved from one container
	 *         model to another; <code>false</code> otherwise
	 */
	public boolean isMovable();

	/**
	 * Returns <code>true</code> if the model entity can be restored to its
	 * original location.
	 *
	 * <p>
	 * This method usually returns <code>false</code> if the container (e.g.
	 * folder) of the model entity is no longer available (e.g. moved to the
	 * Recycle Bin or deleted).
	 * </p>
	 *
	 * @param  classPK the primary key of the model entity
	 * @return <code>true</code> if the model entity can be restored to its
	 *         original location; <code>false</code> otherwise
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public boolean isRestorable(long classPK)
		throws PortalException, SystemException;

	public boolean isTrashEntry(
		TrashEntry trashEntry, ClassedModel classedModel);

	/**
	 * Moves the entity with the class primary key to the container model with
	 * the class primary key
	 *
	 * @param  userId the user ID
	 * @param  classPK the primary key of the model entity
	 * @param  containerModelId the primary key of the destination container
	 *         model
	 * @param  serviceContext the service context to be applied
	 * @throws PortalException if a model entity with the primary key or the
	 *         destination container model with the primary key could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public void moveEntry(
			long userId, long classPK, long containerModelId,
			ServiceContext serviceContext)
		throws PortalException, SystemException;

	/**
	 * Moves the model entity with the primary key out of the Recycle Bin to a
	 * new destination identified by the container model ID.
	 *
	 * @param  userId the user ID
	 * @param  classPK the primary key of the model entity
	 * @param  containerModelId the primary key of the destination container
	 *         model
	 * @param  serviceContext the service context to be applied
	 * @throws PortalException if a model entity with the primary key or the
	 *         destination container model with the primary key could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public void moveTrashEntry(
			long userId, long classPK, long containerModelId,
			ServiceContext serviceContext)
		throws PortalException, SystemException;

	/**
	 * Restores the model entity that is related to the model entity with the
	 * class name and class PK. For example, {@link
	 * com.liferay.portlet.wiki.trash.WikiPageTrashHandler#restoreRelatedTrashEntry(
	 * String, long)} restores the attachment related to the wiki page with the
	 * class name and class PK.
	 *
	 * @param  className the class name of the model entity with a related model
	 *         entity to restore
	 * @param  classPK the primary key of the model entity with a related model
	 *         entity to restore
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public void restoreRelatedTrashEntry(String className, long classPK)
		throws PortalException, SystemException;

	/**
	 * Restores the model entity with the primary key.
	 *
	 * @param  userId the user ID
	 * @param  classPK the primary key of the model entity to restore
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public void restoreTrashEntry(long userId, long classPK)
		throws PortalException, SystemException;

	/**
	 * Updates the title of the model entity with the primary key. This method
	 * is called by {@link com.liferay.portlet.trash.action.EditEntryAction}
	 * before restoring the model entity via its restore rename action.
	 *
	 * @param  classPK the primary key of the model entity
	 * @param  title the title to be assigned
	 * @throws PortalException if a model entity with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public void updateTitle(long classPK, String title)
		throws PortalException, SystemException;

}