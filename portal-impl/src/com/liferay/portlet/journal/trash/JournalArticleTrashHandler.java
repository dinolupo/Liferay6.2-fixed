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

package com.liferay.portlet.journal.trash;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.trash.TrashActionKeys;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ContainerModel;
import com.liferay.portal.model.TrashedModel;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.journal.asset.JournalArticleAssetRenderer;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalArticleResourceLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portlet.journal.service.permission.JournalArticlePermission;
import com.liferay.portlet.journal.service.permission.JournalFolderPermission;
import com.liferay.portlet.journal.util.JournalUtil;
import com.liferay.portlet.trash.DuplicateEntryException;
import com.liferay.portlet.trash.model.TrashEntry;

import javax.portlet.PortletRequest;

/**
 * Implements trash handling for the journal article entity.
 *
 * @author Levente Hudák
 * @author Sergio González
 * @author Zsolt Berentey
 */
public class JournalArticleTrashHandler extends JournalBaseTrashHandler {

	@Override
	public void checkDuplicateEntry(
			long classPK, long containerModelId, String newName)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		checkDuplicateEntry(
			classPK, 0, containerModelId, article.getArticleId(), newName);
	}

	@Override
	public void checkDuplicateTrashEntry(
			TrashEntry trashEntry, long containerModelId, String newName)
		throws PortalException, SystemException {

		checkDuplicateEntry(
			trashEntry.getClassPK(), trashEntry.getEntryId(), containerModelId,
			trashEntry.getTypeSettingsProperty("title"), newName);
	}

	@Override
	public void deleteTrashEntry(long classPK)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		JournalArticleLocalServiceUtil.deleteArticle(
			article.getGroupId(), article.getArticleId(), null);
	}

	@Override
	public String getClassName() {
		return JournalArticle.class.getName();
	}

	@Override
	public ContainerModel getParentContainerModel(long classPK)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		long parentFolderId = article.getFolderId();

		if (parentFolderId <= 0) {
			return null;
		}

		return getContainerModel(parentFolderId);
	}

	@Override
	public ContainerModel getParentContainerModel(TrashedModel trashedModel)
		throws PortalException, SystemException {

		JournalArticle article = (JournalArticle)trashedModel;

		return getContainerModel(article.getFolderId());
	}

	@Override
	public String getRestoreContainerModelLink(
			PortletRequest portletRequest, long classPK)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		return JournalUtil.getJournalControlPanelLink(
			portletRequest, article.getFolderId());
	}

	@Override
	public String getRestoreMessage(PortletRequest portletRequest, long classPK)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		return JournalUtil.getAbsolutePath(
			portletRequest, article.getFolderId());
	}

	@Override
	public TrashEntry getTrashEntry(long classPK)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		return article.getTrashEntry();
	}

	@Override
	public TrashRenderer getTrashRenderer(long classPK)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		return new JournalArticleAssetRenderer(article);
	}

	@Override
	public boolean hasTrashPermission(
			PermissionChecker permissionChecker, long groupId, long classPK,
			String trashActionId)
		throws PortalException, SystemException {

		if (trashActionId.equals(TrashActionKeys.MOVE)) {
			return JournalFolderPermission.contains(
				permissionChecker, groupId, classPK, ActionKeys.ADD_ARTICLE);
		}

		return super.hasTrashPermission(
			permissionChecker, groupId, classPK, trashActionId);
	}

	@Override
	public boolean isInTrash(long classPK)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		return article.isInTrash();
	}

	@Override
	public boolean isInTrashContainer(long classPK)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		return article.isInTrashContainer();
	}

	@Override
	public boolean isRestorable(long classPK)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		if ((article.getFolderId() > 0) &&
			(JournalFolderLocalServiceUtil.fetchFolder(
				article.getFolderId()) == null)) {

			return false;
		}

		return !article.isInTrashContainer();
	}

	@Override
	public void moveEntry(
			long userId, long classPK, long containerModelId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		JournalArticleLocalServiceUtil.moveArticle(
			article.getGroupId(), article.getArticleId(), containerModelId);
	}

	@Override
	public void moveTrashEntry(
			long userId, long classPK, long containerId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		JournalArticleLocalServiceUtil.moveArticleFromTrash(
			userId, article.getGroupId(), article, containerId, serviceContext);
	}

	@Override
	public void restoreTrashEntry(long userId, long classPK)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		JournalArticleLocalServiceUtil.restoreArticleFromTrash(userId, article);
	}

	@Override
	public void updateTitle(long classPK, String name)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		article.setArticleId(name);

		JournalArticleLocalServiceUtil.updateJournalArticle(article);

		JournalArticleResource articleResource =
			JournalArticleResourceLocalServiceUtil.getArticleResource(
				article.getResourcePrimKey());

		articleResource.setArticleId(name);

		JournalArticleResourceLocalServiceUtil.updateJournalArticleResource(
			articleResource);
	}

	protected void checkDuplicateEntry(
			long classPK, long trashEntryId, long containerModelId,
			String originalTitle, String newName)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		JournalArticleResource journalArticleResource =
			article.getArticleResource();

		if (Validator.isNotNull(newName)) {
			originalTitle = newName;
		}

		JournalArticleResource originalArticleResource =
			JournalArticleResourceLocalServiceUtil.fetchArticleResource(
				article.getGroupId(), originalTitle);

		if ((originalArticleResource != null) &&
			(journalArticleResource.getPrimaryKey() !=
				originalArticleResource.getPrimaryKey())) {

			DuplicateEntryException dee = new DuplicateEntryException();

			JournalArticle duplicateArticle =
				JournalArticleLocalServiceUtil.getArticle(
					originalArticleResource.getGroupId(), originalTitle);

			dee.setDuplicateEntryId(duplicateArticle.getResourcePrimKey());
			dee.setOldName(duplicateArticle.getArticleId());
			dee.setTrashEntryId(trashEntryId);

			throw dee;
		}
	}

	@Override
	protected long getGroupId(long classPK)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(classPK);

		return article.getGroupId();
	}

	@Override
	protected boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException, SystemException {

		return JournalArticlePermission.contains(
			permissionChecker, classPK, actionId);
	}

}