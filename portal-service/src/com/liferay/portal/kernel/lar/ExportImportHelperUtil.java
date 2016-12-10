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

package com.liferay.portal.kernel.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.DateRange;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.File;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

/**
 * @author Zsolt Berentey
 */
public class ExportImportHelperUtil {

	public static Calendar getCalendar(
		PortletRequest portletRequest, String paramPrefix,
		boolean timeZoneSensitive) {

		return getExportImportHelper().getCalendar(
			portletRequest, paramPrefix, timeZoneSensitive);
	}

	public static void reindex(
			PortletDataContext portletDataContext, long userId)
		throws PortalException, SystemException {

		getExportImportHelper().reindex(portletDataContext, userId);
	}

	public static DateRange getDateRange(
			PortletRequest portletRequest, long groupId, boolean privateLayout,
			long plid, String portletId, String defaultRange)
		throws Exception {

		return getExportImportHelper().getDateRange(
			portletRequest, groupId, privateLayout, plid, portletId,
			defaultRange);
	}

	public static Layout getExportableLayout(ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		return getExportImportHelper().getExportableLayout(themeDisplay);
	}

	public static String getExportableRootPortletId(
			long companyId, String portletId)
		throws Exception {

		return getExportImportHelper().getExportableRootPortletId(
			companyId, portletId);
	}

	public static ExportImportHelper getExportImportHelper() {
		PortalRuntimePermission.checkGetBeanProperty(
			ExportImportHelperUtil.class);

		return _exportImportHelper;
	}

	public static Map<Long, Boolean> getLayoutIdMap(
			PortletRequest portletRequest)
		throws Exception {

		return getExportImportHelper().getLayoutIdMap(portletRequest);
	}

	public static long[] getLayoutIds(List<Layout> layouts) {
		return getExportImportHelper().getLayoutIds(layouts);
	}

	public static ZipWriter getLayoutSetZipWriter(long groupId) {
		return getExportImportHelper().getLayoutSetZipWriter(groupId);
	}

	public static ManifestSummary getManifestSummary(
			long userId, long groupId, Map<String, String[]> parameterMap,
			File file)
		throws Exception {

		return getExportImportHelper().getManifestSummary(
			userId, groupId, parameterMap, file);
	}

	public static ManifestSummary getManifestSummary(
			long userId, long groupId, Map<String, String[]> parameterMap,
			FileEntry fileEntry)
		throws Exception {

		return getExportImportHelper().getManifestSummary(
			userId, groupId, parameterMap, fileEntry);
	}

	public static long getModelDeletionCount(
			final PortletDataContext portletDataContext,
			final StagedModelType stagedModelType)
		throws PortalException, SystemException {

		return getExportImportHelper().getModelDeletionCount(
			portletDataContext, stagedModelType);
	}

	public static ZipWriter getPortletZipWriter(String portletId) {
		return getExportImportHelper().getPortletZipWriter(portletId);
	}

	public static String getSelectedLayoutsJSON(
			long groupId, boolean privateLayout, String selectedNodes)
		throws SystemException {

		return getExportImportHelper().getSelectedLayoutsJSON(
			groupId, privateLayout, selectedNodes);
	}

	public static FileEntry getTempFileEntry(
			long groupId, long userId, String folderName)
		throws PortalException, SystemException {

		return getExportImportHelper().getTempFileEntry(
			groupId, userId, folderName);
	}

	public static String replaceExportContentReferences(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, Element entityElement,
			String content, boolean exportReferencedContent)
		throws Exception {

		return getExportImportHelper().replaceExportContentReferences(
			portletDataContext, entityStagedModel, entityElement, content,
			exportReferencedContent);
	}

	public static String replaceExportDLReferences(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, Element entityElement,
			String content, boolean exportReferencedContent)
		throws Exception {

		return getExportImportHelper().replaceExportDLReferences(
			portletDataContext, entityStagedModel, entityElement, content,
			exportReferencedContent);
	}

	public static String replaceExportLayoutReferences(
			PortletDataContext portletDataContext, String content,
			boolean exportReferencedContent)
		throws Exception {

		return getExportImportHelper().replaceExportLayoutReferences(
			portletDataContext, content, exportReferencedContent);
	}

	public static String replaceExportLinksToLayouts(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, Element entityElement,
			String content, boolean exportReferencedContent)
		throws Exception {

		return getExportImportHelper().replaceExportLinksToLayouts(
			portletDataContext, entityStagedModel, entityElement, content,
			exportReferencedContent);
	}

	public static String replaceImportContentReferences(
			PortletDataContext portletDataContext, Element entityElement,
			String content, boolean importReferencedContent)
		throws Exception {

		return getExportImportHelper().replaceImportContentReferences(
			portletDataContext, entityElement, content,
			importReferencedContent);
	}

	public static String replaceImportDLReferences(
			PortletDataContext portletDataContext, Element entityElement,
			String content, boolean importReferencedContent)
		throws Exception {

		return getExportImportHelper().replaceImportDLReferences(
			portletDataContext, entityElement, content,
			importReferencedContent);
	}

	public static String replaceImportLayoutReferences(
			PortletDataContext portletDataContext, String content,
			boolean importReferencedContent)
		throws Exception {

		return getExportImportHelper().replaceImportLayoutReferences(
			portletDataContext, content, importReferencedContent);
	}

	public static String replaceImportLinksToLayouts(
			PortletDataContext portletDataContext, String content,
			boolean importReferencedContent)
		throws Exception {

		return getExportImportHelper().replaceImportLinksToLayouts(
			portletDataContext, content, importReferencedContent);
	}

	public static void updateExportPortletPreferencesClassPKs(
			PortletDataContext portletDataContext, Portlet portlet,
			PortletPreferences portletPreferences, String key, String className,
			Element rootElement)
		throws Exception {

		getExportImportHelper().updateExportPortletPreferencesClassPKs(
			portletDataContext, portlet, portletPreferences, key, className,
			rootElement);
	}

	public static void updateImportPortletPreferencesClassPKs(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences, String key, Class<?> clazz,
			long companyGroupId)
		throws Exception {

		getExportImportHelper().updateImportPortletPreferencesClassPKs(
			portletDataContext, portletPreferences, key, clazz, companyGroupId);
	}

	public static MissingReferences validateMissingReferences(
			long userId, long groupId, Map<String, String[]> parameterMap,
			File file)
		throws Exception {

		return getExportImportHelper().validateMissingReferences(
			userId, groupId, parameterMap, file);
	}

	public static void writeManifestSummary(
		Document document, ManifestSummary manifestSummary) {

		getExportImportHelper().writeManifestSummary(document, manifestSummary);
	}

	public void setExportImportHelper(ExportImportHelper exportImportHelper) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_exportImportHelper = exportImportHelper;
	}

	private static ExportImportHelper _exportImportHelper;

}