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

package com.liferay.portlet.documentlibrary.search;

import com.liferay.portal.NoSuchRepositoryEntryException;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFileShortcutException;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.permission.DLFileEntryPermission;
import com.liferay.portlet.documentlibrary.service.permission.DLFileShortcutPermission;
import com.liferay.portlet.documentlibrary.service.permission.DLFolderPermission;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergio González
 */
public class EntriesChecker extends RowChecker {

	public EntriesChecker(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		super(liferayPortletResponse);

		_liferayPortletResponse = liferayPortletResponse;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		_permissionChecker = themeDisplay.getPermissionChecker();

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String portletName = portletDisplay.getPortletName();

		if (portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY)) {
			_documentLibraryDisplayPortlet = true;
		}
	}

	@Override
	public String getAllRowsCheckBox() {
		if (_documentLibraryDisplayPortlet) {
			return getAllRowsCheckbox(null, getAllRowIds(), getEntryRowIds());
		}

		return null;
	}

	@Override
	public String getAllRowsCheckBox(HttpServletRequest request) {
		if (_documentLibraryDisplayPortlet) {
			return getAllRowsCheckbox(
				request, getAllRowIds(), getEntryRowIds());
		}

		return null;
	}

	@Override
	public String getRowCheckBox(
		HttpServletRequest request, boolean checked, boolean disabled,
		String primaryKey) {

		DLFileShortcut dlFileShortcut = null;
		FileEntry fileEntry = null;
		Folder folder = null;

		long entryId = GetterUtil.getLong(primaryKey);

		try {
			fileEntry = DLAppServiceUtil.getFileEntry(entryId);
		}
		catch (Exception e1) {
			if (e1 instanceof NoSuchFileEntryException ||
				e1 instanceof NoSuchRepositoryEntryException) {

				try {
					dlFileShortcut = DLAppServiceUtil.getFileShortcut(entryId);
				}
				catch (Exception e2) {
					if (e2 instanceof NoSuchFileShortcutException) {
						try {
							folder = DLAppServiceUtil.getFolder(entryId);
						}
						catch (Exception e3) {
							return StringPool.BLANK;
						}
					}
					else {
						return StringPool.BLANK;
					}
				}
			}
			else {
				return StringPool.BLANK;
			}
		}

		boolean showInput = false;

		String name = null;

		if (fileEntry != null) {
			name = FileEntry.class.getSimpleName();

			try {
				if (DLFileEntryPermission.contains(
						_permissionChecker, fileEntry, ActionKeys.DELETE) ||
					DLFileEntryPermission.contains(
						_permissionChecker, fileEntry, ActionKeys.UPDATE)) {

					showInput = true;
				}
			}
			catch (Exception e) {
			}
		}
		else if (dlFileShortcut != null) {
			name = DLFileShortcut.class.getSimpleName();

			try {
				if (DLFileShortcutPermission.contains(
						_permissionChecker, dlFileShortcut,
						ActionKeys.DELETE) ||
					DLFileShortcutPermission.contains(
						_permissionChecker, dlFileShortcut,
						ActionKeys.UPDATE)) {

					showInput = true;
				}
			}
			catch (Exception e) {
			}
		}
		else if (folder != null) {
			name = Folder.class.getSimpleName();

			try {
				if (DLFolderPermission.contains(
						_permissionChecker, folder, ActionKeys.DELETE) ||
					DLFolderPermission.contains(
						_permissionChecker, folder, ActionKeys.UPDATE)) {

					showInput = true;
				}
			}
			catch (Exception e) {
			}
		}

		if (!showInput) {
			return StringPool.BLANK;
		}

		String checkBoxRowIds = getEntryRowIds();

		String checkBoxAllRowIds = StringPool.BLANK;
		String checkBoxPostOnClick = StringPool.BLANK;

		if (_documentLibraryDisplayPortlet) {
			checkBoxAllRowIds = "'" + getAllRowIds() + "'";
		}
		else {
			checkBoxAllRowIds = "'#" + getAllRowIds() + "Checkbox'";
			checkBoxPostOnClick =
				_liferayPortletResponse.getNamespace() +
					"toggleActionsButton();";
		}

		return getRowCheckBox(
			request, checked, disabled,
			_liferayPortletResponse.getNamespace() + RowChecker.ROW_IDS +
				name + "Checkbox",
			primaryKey, checkBoxRowIds, checkBoxAllRowIds, checkBoxPostOnClick);
	}

	protected String getEntryRowIds() {
		StringBundler sb = new StringBundler(13);

		sb.append("['");
		sb.append(_liferayPortletResponse.getNamespace());
		sb.append(RowChecker.ROW_IDS);
		sb.append(Folder.class.getSimpleName());
		sb.append("Checkbox', '");
		sb.append(_liferayPortletResponse.getNamespace());
		sb.append(RowChecker.ROW_IDS);
		sb.append(DLFileShortcut.class.getSimpleName());
		sb.append("Checkbox', '");
		sb.append(_liferayPortletResponse.getNamespace());
		sb.append(RowChecker.ROW_IDS);
		sb.append(FileEntry.class.getSimpleName());
		sb.append("Checkbox']");

		return sb.toString();
	}

	private boolean _documentLibraryDisplayPortlet;
	private LiferayPortletResponse _liferayPortletResponse;
	private PermissionChecker _permissionChecker;

}