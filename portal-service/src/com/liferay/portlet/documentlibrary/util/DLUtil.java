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

package com.liferay.portlet.documentlibrary.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.documentlibrary.model.DLFileEntryType;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class DLUtil {

	public static void addPortletBreadcrumbEntries(
			DLFileShortcut dlFileShortcut, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		getDL().addPortletBreadcrumbEntries(
			dlFileShortcut, request, renderResponse);
	}

	public static void addPortletBreadcrumbEntries(
			FileEntry fileEntry, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		getDL().addPortletBreadcrumbEntries(fileEntry, request, renderResponse);
	}

	public static void addPortletBreadcrumbEntries(
			Folder folder, HttpServletRequest request,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		getDL().addPortletBreadcrumbEntries(
			folder, request, liferayPortletResponse);
	}

	public static void addPortletBreadcrumbEntries(
			Folder folder, HttpServletRequest request, PortletURL portletURL)
		throws Exception {

		getDL().addPortletBreadcrumbEntries(folder, request, portletURL);
	}

	public static void addPortletBreadcrumbEntries(
			Folder folder, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		getDL().addPortletBreadcrumbEntries(folder, request, renderResponse);
	}

	public static void addPortletBreadcrumbEntries(
			long folderId, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		getDL().addPortletBreadcrumbEntries(folderId, request, renderResponse);
	}

	public static int compareVersions(String version1, String version2) {
		return getDL().compareVersions(version1, version2);
	}

	public static String getAbsolutePath(
			PortletRequest portletRequest, long folderId)
		throws PortalException, SystemException {

		return getDL().getAbsolutePath(portletRequest, folderId);
	}

	public static Set<String> getAllMediaGalleryMimeTypes() {
		return getDL().getAllMediaGalleryMimeTypes();
	}

	public static String getDDMStructureKey(DLFileEntryType dlFileEntryType) {
		return getDL().getDDMStructureKey(dlFileEntryType);
	}

	public static String getDDMStructureKey(String fileEntryTypeUuid) {
		return getDL().getDDMStructureKey(fileEntryTypeUuid);
	}

	public static String getDeprecatedDDMStructureKey(
		DLFileEntryType dlFileEntryType) {

		return getDL().getDeprecatedDDMStructureKey(dlFileEntryType);
	}

	public static String getDeprecatedDDMStructureKey(long fileEntryTypeId) {
		return getDL().getDeprecatedDDMStructureKey(fileEntryTypeId);
	}

	public static String getDividedPath(long id) {
		return getDL().getDividedPath(id);
	}

	public static DL getDL() {
		PortalRuntimePermission.checkGetBeanProperty(DLUtil.class);

		return _dl;
	}

	public static String getDLFileEntryControlPanelLink(
			PortletRequest portletRequest, long fileEntryId)
		throws PortalException, SystemException {

		return getDL().getDLFileEntryControlPanelLink(
			portletRequest, fileEntryId);
	}

	public static String getDLFolderControlPanelLink(
			PortletRequest portletRequest, long folderId)
		throws PortalException, SystemException {

		return getDL().getDLFolderControlPanelLink(portletRequest, folderId);
	}

	public static Map<Locale, String> getEmailFileEntryAddedBodyMap(
		PortletPreferences preferences) {

		return getDL().getEmailFileEntryAddedBodyMap(preferences);
	}

	public static boolean getEmailFileEntryAddedEnabled(
		PortletPreferences preferences) {

		return getDL().getEmailFileEntryAddedEnabled(preferences);
	}

	public static Map<Locale, String> getEmailFileEntryAddedSubjectMap(
		PortletPreferences preferences) {

		return getDL().getEmailFileEntryAddedSubjectMap(preferences);
	}

	public static Map<Locale, String> getEmailFileEntryUpdatedBodyMap(
		PortletPreferences preferences) {

		return getDL().getEmailFileEntryUpdatedBodyMap(preferences);
	}

	public static boolean getEmailFileEntryUpdatedEnabled(
		PortletPreferences preferences) {

		return getDL().getEmailFileEntryUpdatedEnabled(preferences);
	}

	public static Map<Locale, String> getEmailFileEntryUpdatedSubjectMap(
		PortletPreferences preferences) {

		return getDL().getEmailFileEntryUpdatedSubjectMap(preferences);
	}

	public static String getEmailFromAddress(
			PortletPreferences preferences, long companyId)
		throws SystemException {

		return getDL().getEmailFromAddress(preferences, companyId);
	}

	public static String getEmailFromName(
			PortletPreferences preferences, long companyId)
		throws SystemException {

		return getDL().getEmailFromName(preferences, companyId);
	}

	public static List<Object> getEntries(Hits hits) {
		return getDL().getEntries(hits);
	}

	public static String getFileEntryImage(
		FileEntry fileEntry, ThemeDisplay themeDisplay) {

		return getDL().getFileEntryImage(fileEntry, themeDisplay);
	}

	public static Set<Long> getFileEntryTypeSubscriptionClassPKs(long userId)
		throws SystemException {

		return getDL().getFileEntryTypeSubscriptionClassPKs(userId);
	}

	public static String getFileIcon(String extension) {
		return getDL().getFileIcon(extension);
	}

	public static String getGenericName(String extension) {
		return getDL().getGenericName(extension);
	}

	public static String getImagePreviewURL(
			FileEntry fileEntry, FileVersion fileVersion,
			ThemeDisplay themeDisplay)
		throws Exception {

		return getDL().getImagePreviewURL(fileEntry, fileVersion, themeDisplay);
	}

	public static String getImagePreviewURL(
			FileEntry fileEntry, ThemeDisplay themeDisplay)
		throws Exception {

		return getDL().getImagePreviewURL(fileEntry, themeDisplay);
	}

	public static String[] getMediaGalleryMimeTypes(
		PortletPreferences portletPreferences, PortletRequest portletRequest) {

		return getDL().getMediaGalleryMimeTypes(
			portletPreferences, portletRequest);
	}

	public static String getPreviewURL(
		FileEntry fileEntry, FileVersion fileVersion, ThemeDisplay themeDisplay,
		String queryString) {

		return getDL().getPreviewURL(
			fileEntry, fileVersion, themeDisplay, queryString);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getPreviewURL(FileEntry,
	 *             FileVersion, ThemeDisplay, String, boolean, boolean)}
	 */
	public static String getPreviewURL(
		FileEntry fileEntry, FileVersion fileVersion, ThemeDisplay themeDisplay,
		String queryString, boolean appendToken) {

		return getDL().getPreviewURL(
			fileEntry, fileVersion, themeDisplay, queryString, appendToken);
	}

	public static String getPreviewURL(
		FileEntry fileEntry, FileVersion fileVersion, ThemeDisplay themeDisplay,
		String queryString, boolean appendVersion, boolean absoluteURL) {

		return getDL().getPreviewURL(
			fileEntry, fileVersion, themeDisplay, queryString, appendVersion,
			absoluteURL);
	}

	public static OrderByComparator getRepositoryModelOrderByComparator(
		String orderByCol, String orderByType) {

		return getDL().getRepositoryModelOrderByComparator(
			orderByCol, orderByType);
	}

	public static String getTempFileId(long id, String version) {
		return getDL().getTempFileId(id, version);
	}

	public static String getTempFileId(
		long id, String version, String languageId) {

		return getDL().getTempFileId(id, version, languageId);
	}

	public static String getThumbnailSrc(
			FileEntry fileEntry, DLFileShortcut dlFileShortcut,
			ThemeDisplay themeDisplay)
		throws Exception {

		return getDL().getThumbnailSrc(fileEntry, dlFileShortcut, themeDisplay);
	}

	public static String getThumbnailSrc(
			FileEntry fileEntry, FileVersion fileVersion,
			DLFileShortcut dlFileShortcut, ThemeDisplay themeDisplay)
		throws Exception {

		return getDL().getThumbnailSrc(
			fileEntry, fileVersion, dlFileShortcut, themeDisplay);
	}

	public static String getThumbnailStyle() throws Exception {
		return getDL().getThumbnailStyle();
	}

	public static String getThumbnailStyle(boolean max, int margin)
		throws Exception {

		return getDL().getThumbnailStyle(max, margin);
	}

	public static String getTitleWithExtension(FileEntry fileEntry) {
		return getDL().getTitleWithExtension(fileEntry);
	}

	public static String getTitleWithExtension(String title, String extension) {
		return getDL().getTitleWithExtension(title, extension);
	}

	public static String getWebDavURL(
			ThemeDisplay themeDisplay, Folder folder, FileEntry fileEntry)
		throws PortalException, SystemException {

		return getDL().getWebDavURL(themeDisplay, folder, fileEntry);
	}

	public static String getWebDavURL(
			ThemeDisplay themeDisplay, Folder folder, FileEntry fileEntry,
			boolean manualCheckInRequired)
		throws PortalException, SystemException {

		return getDL().getWebDavURL(
			themeDisplay, folder, fileEntry, manualCheckInRequired);
	}

	public static String getWebDavURL(
			ThemeDisplay themeDisplay, Folder folder, FileEntry fileEntry,
			boolean manualCheckInRequired, boolean officeExtensionRequired)
		throws PortalException, SystemException {

		return getDL().getWebDavURL(
			themeDisplay, folder, fileEntry, manualCheckInRequired,
			officeExtensionRequired);
	}

	public static boolean hasWorkflowDefinitionLink(
			long companyId, long groupId, long folderId, long fileEntryTypeId)
		throws Exception {

		return getDL().hasWorkflowDefinitionLink(
			companyId, groupId, folderId, fileEntryTypeId);
	}

	public static boolean isAutoGeneratedDLFileEntryTypeDDMStructureKey(
		String ddmStructureKey) {

		return getDL().isAutoGeneratedDLFileEntryTypeDDMStructureKey(
			ddmStructureKey);
	}

	public static boolean isOfficeExtension(String extension) {
		return getDL().isOfficeExtension(extension);
	}

	public static boolean isSubscribedToFileEntryType(
			long companyId, long groupId, long userId, long fileEntryTypeId)
		throws SystemException {

		return getDL().isSubscribedToFileEntryType(
			companyId, groupId, userId, fileEntryTypeId);
	}

	public static boolean isSubscribedToFolder(
			long companyId, long groupId, long userId, long folderId)
		throws PortalException, SystemException {

		return getDL().isSubscribedToFolder(
			companyId, groupId, userId, folderId);
	}

	public static boolean isSubscribedToFolder(
			long companyId, long groupId, long userId, long folderId,
			boolean recursive)
		throws PortalException, SystemException {

		return getDL().isSubscribedToFolder(
			companyId, groupId, userId, folderId, recursive);
	}

	public static boolean isValidVersion(String version) {
		return getDL().isValidVersion(version);
	}

	public void setDL(DL dl) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_dl = dl;
	}

	private static DL _dl;

}