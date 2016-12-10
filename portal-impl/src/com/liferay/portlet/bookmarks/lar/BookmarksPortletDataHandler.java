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

package com.liferay.portlet.bookmarks.lar;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.model.BookmarksFolderConstants;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalServiceUtil;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.portlet.bookmarks.service.permission.BookmarksPermission;
import com.liferay.portlet.bookmarks.service.persistence.BookmarksEntryExportActionableDynamicQuery;
import com.liferay.portlet.bookmarks.service.persistence.BookmarksFolderExportActionableDynamicQuery;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Jorge Ferrer
 * @author Bruno Farache
 * @author Raymond Augé
 * @author Juan Fernández
 * @author Mate Thurzo
 * @author Daniel Kocsis
 */
public class BookmarksPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "bookmarks";

	public BookmarksPortletDataHandler() {
		setDataPortletPreferences("rootFolderId");
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(BookmarksEntry.class),
			new StagedModelType(BookmarksFolder.class));
		setExportControls(
			new PortletDataHandlerBoolean(
				NAMESPACE, "entries", true, false, null,
				BookmarksEntry.class.getName()));
		setImportControls(getExportControls());
		setPublishToLiveByDefault(
			PropsValues.BOOKMARKS_PUBLISH_TO_LIVE_BY_DEFAULT);
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				BookmarksPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		BookmarksFolderLocalServiceUtil.deleteFolders(
			portletDataContext.getScopeGroupId());

		BookmarksEntryLocalServiceUtil.deleteEntries(
			portletDataContext.getScopeGroupId(),
			BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			final PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		Element rootElement = addExportDataRootElement(portletDataContext);

		if (!portletDataContext.getBooleanParameter(NAMESPACE, "entries")) {
			return getExportDataRootElementString(rootElement);
		}

		portletDataContext.addPortletPermissions(
			BookmarksPermission.RESOURCE_NAME);

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		ActionableDynamicQuery folderActionableDynamicQuery =
			new BookmarksFolderExportActionableDynamicQuery(portletDataContext);

		folderActionableDynamicQuery.performActions();

		ActionableDynamicQuery entryActionableDynamicQuery =
			new BookmarksEntryExportActionableDynamicQuery(portletDataContext);

		entryActionableDynamicQuery.performActions();

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		if (!portletDataContext.getBooleanParameter(NAMESPACE, "entries")) {
			return null;
		}

		portletDataContext.importPortletPermissions(
			BookmarksPermission.RESOURCE_NAME);

		Element foldersElement = portletDataContext.getImportDataGroupElement(
			BookmarksFolder.class);

		List<Element> folderElements = foldersElement.elements();

		for (Element folderElement : folderElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, folderElement);
		}

		Element entriesElement = portletDataContext.getImportDataGroupElement(
			BookmarksEntry.class);

		List<Element> entryElements = entriesElement.elements();

		for (Element entryElement : entryElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, entryElement);
		}

		return null;
	}

	@Override
	protected void doPrepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		ActionableDynamicQuery entryExportActionableDynamicQuery =
			new BookmarksEntryExportActionableDynamicQuery(portletDataContext);

		entryExportActionableDynamicQuery.performCount();

		ActionableDynamicQuery folderExportActionableDynamicQuery =
			new BookmarksFolderExportActionableDynamicQuery(portletDataContext);

		folderExportActionableDynamicQuery.performCount();
	}

	@Override
	protected PortletPreferences doProcessExportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		long rootFolderId = GetterUtil.getLong(
			portletPreferences.getValue("rootFolderId", null));

		if (rootFolderId != BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			BookmarksFolder folder = BookmarksFolderLocalServiceUtil.getFolder(
				rootFolderId);

			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				portletDataContext.getCompanyId(), portletId);

			portletDataContext.addReferenceElement(
				portlet, portletDataContext.getExportDataRootElement(), folder,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY,
				!portletDataContext.getBooleanParameter(NAMESPACE, "entries"));
		}

		return portletPreferences;
	}

	@Override
	protected PortletPreferences doProcessImportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		long rootFolderId = GetterUtil.getLong(
			portletPreferences.getValue("rootFolderId", null));

		if (rootFolderId > 0) {
			String rootFolderPath = ExportImportPathUtil.getModelPath(
				portletDataContext, BookmarksFolder.class.getName(),
				rootFolderId);

			BookmarksFolder folder =
				(BookmarksFolder)portletDataContext.getZipEntryAsObject(
					rootFolderPath);

			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, folder);

			Map<Long, Long> folderIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					BookmarksFolder.class);

			rootFolderId = MapUtil.getLong(
				folderIds, rootFolderId, rootFolderId);

			portletPreferences.setValue(
				"rootFolderId", String.valueOf(rootFolderId));
		}

		return portletPreferences;
	}

}