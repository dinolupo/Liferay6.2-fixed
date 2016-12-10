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

package com.liferay.portal.lar;

import com.liferay.portal.LARFileException;
import com.liferay.portal.LARTypeException;
import com.liferay.portal.LayoutImportException;
import com.liferay.portal.LayoutPrototypeException;
import com.liferay.portal.LocaleException;
import com.liferay.portal.MissingReferenceException;
import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.NoSuchLayoutPrototypeException;
import com.liferay.portal.NoSuchLayoutSetPrototypeException;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.lar.ExportImportHelperUtil;
import com.liferay.portal.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.kernel.lar.ManifestSummary;
import com.liferay.portal.kernel.lar.MissingReference;
import com.liferay.portal.kernel.lar.MissingReferences;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataContextFactoryUtil;
import com.liferay.portal.kernel.lar.PortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.lar.PortletDataHandlerStatusMessageSenderUtil;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.lar.UserIdStrategy;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionCacheUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutPrototypeLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.LayoutUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.servlet.filters.cache.CacheUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journalcontent.util.JournalContentUtil;
import com.liferay.portlet.sites.util.Sites;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Brian Wing Shun Chan
 * @author Joel Kozikowski
 * @author Charles May
 * @author Raymond Augé
 * @author Jorge Ferrer
 * @author Bruno Farache
 * @author Wesley Gong
 * @author Zsigmond Rab
 * @author Douglas Wong
 * @author Julio Camarero
 * @author Zsolt Berentey
 */
public class LayoutImporter {

	public static Map<String, Boolean> getImportPortletControlsMap(
			long companyId, String portletId,
			Map<String, String[]> parameterMap, Element portletDataElement,
			ManifestSummary manifestSummary)
		throws Exception {

		boolean importPortletConfiguration = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PORTLET_CONFIGURATION);
		boolean importPortletConfigurationAll = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PORTLET_CONFIGURATION_ALL);
		boolean importPortletData = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PORTLET_DATA);
		boolean importPortletDataAll = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PORTLET_DATA_ALL);

		if (_log.isDebugEnabled()) {
			_log.debug("Import portlet data " + importPortletData);
			_log.debug("Import all portlet data " + importPortletDataAll);
			_log.debug(
				"Import portlet configuration " + importPortletConfiguration);
		}

		boolean importCurPortletData = importPortletData;

		String rootPortletId =
			ExportImportHelperUtil.getExportableRootPortletId(
				companyId, portletId);

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			companyId, portletId);

		PortletDataHandler portletDataHandler =
			portlet.getPortletDataHandlerInstance();

		if ((portletDataHandler == null) ||
			((portletDataElement == null) &&
			 !portletDataHandler.isDisplayPortlet())) {

			importCurPortletData = false;
		}
		else if (importPortletDataAll) {
			importCurPortletData = true;
		}
		else if (rootPortletId != null) {
			importCurPortletData =
				importPortletData &&
				MapUtil.getBoolean(
					parameterMap,
					PortletDataHandlerKeys.PORTLET_DATA +
						StringPool.UNDERLINE + rootPortletId);
		}

		boolean importCurPortletArchivedSetups = importPortletConfiguration;
		boolean importCurPortletConfiguration = importPortletConfiguration;
		boolean importCurPortletSetup = importPortletConfiguration;
		boolean importCurPortletUserPreferences = importPortletConfiguration;

		if (importPortletConfigurationAll) {
			importCurPortletConfiguration = true;

			if ((manifestSummary != null) &&
				(manifestSummary.getConfigurationPortletOptions(
					rootPortletId) == null)) {

				importCurPortletConfiguration = false;
			}

			importCurPortletArchivedSetups =
				importCurPortletConfiguration &&
				MapUtil.getBoolean(
					parameterMap,
					PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS_ALL);
			importCurPortletSetup =
				importCurPortletConfiguration &&
				MapUtil.getBoolean(
					parameterMap, PortletDataHandlerKeys.PORTLET_SETUP_ALL);
			importCurPortletUserPreferences =
				importCurPortletConfiguration &&
				MapUtil.getBoolean(
					parameterMap,
					PortletDataHandlerKeys.PORTLET_USER_PREFERENCES_ALL);
		}
		else if (rootPortletId != null) {
			importCurPortletConfiguration =
				importPortletConfiguration &&
				MapUtil.getBoolean(
					parameterMap,
					PortletDataHandlerKeys.PORTLET_CONFIGURATION +
						StringPool.UNDERLINE + rootPortletId);

			importCurPortletArchivedSetups =
				importCurPortletConfiguration &&
				MapUtil.getBoolean(
					parameterMap,
					PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS +
						StringPool.UNDERLINE + rootPortletId);
			importCurPortletSetup =
				importCurPortletConfiguration &&
				MapUtil.getBoolean(
					parameterMap,
					PortletDataHandlerKeys.PORTLET_SETUP +
						StringPool.UNDERLINE + rootPortletId);
			importCurPortletUserPreferences =
				importCurPortletConfiguration &&
				MapUtil.getBoolean(
					parameterMap,
					PortletDataHandlerKeys.PORTLET_USER_PREFERENCES +
						StringPool.UNDERLINE + rootPortletId);
		}

		Map<String, Boolean> importPortletControlsMap =
			new HashMap<String, Boolean>();

		importPortletControlsMap.put(
			PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS,
			importCurPortletArchivedSetups);
		importPortletControlsMap.put(
			PortletDataHandlerKeys.PORTLET_CONFIGURATION,
			importCurPortletConfiguration);
		importPortletControlsMap.put(
			PortletDataHandlerKeys.PORTLET_DATA, importCurPortletData);
		importPortletControlsMap.put(
			PortletDataHandlerKeys.PORTLET_SETUP, importCurPortletSetup);
		importPortletControlsMap.put(
			PortletDataHandlerKeys.PORTLET_USER_PREFERENCES,
			importCurPortletUserPreferences);

		return importPortletControlsMap;
	}

	public void importLayouts(
			long userId, long groupId, boolean privateLayout,
			Map<String, String[]> parameterMap, File file)
		throws Exception {

		try {
			ExportImportThreadLocal.setLayoutImportInProcess(true);

			doImportLayouts(userId, groupId, privateLayout, parameterMap, file);
		}
		finally {
			ExportImportThreadLocal.setLayoutImportInProcess(false);

			CacheUtil.clearCache();
			JournalContentUtil.clearCache();
			PermissionCacheUtil.clearCache();
		}
	}

	public MissingReferences validateFile(
			long userId, long groupId, boolean privateLayout,
			Map<String, String[]> parameterMap, File file)
		throws Exception {

		ZipReader zipReader = null;

		try {
			ExportImportThreadLocal.setLayoutValidationInProcess(true);

			LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				groupId, privateLayout);

			zipReader = ZipReaderFactoryUtil.getZipReader(file);

			PortletDataContext portletDataContext =
				PortletDataContextFactoryUtil.createImportPortletDataContext(
					layoutSet.getCompanyId(), groupId, parameterMap, null,
					zipReader);

			validateFile(portletDataContext);

			MissingReferences missingReferences =
				ExportImportHelperUtil.validateMissingReferences(
					userId, groupId, parameterMap, file);

			Map<String, MissingReference> dependencyMissingReferences =
				missingReferences.getDependencyMissingReferences();

			if (!dependencyMissingReferences.isEmpty()) {
				throw new MissingReferenceException(missingReferences);
			}

			return missingReferences;
		}
		finally {
			ExportImportThreadLocal.setLayoutValidationInProcess(false);

			if (zipReader != null) {
				zipReader.close();
			}
		}
	}

	protected void deleteMissingLayouts(
			List<String> sourceLayoutUuids, List<Layout> previousLayouts,
			ServiceContext serviceContext)
		throws Exception {

		if (_log.isDebugEnabled() && !sourceLayoutUuids.isEmpty()) {
			_log.debug("Delete missing layouts");
		}

		for (Layout layout : previousLayouts) {
			if (!sourceLayoutUuids.contains(layout.getUuid())) {
				try {
					LayoutLocalServiceUtil.deleteLayout(
						layout, false, serviceContext);
				}
				catch (NoSuchLayoutException nsle) {
				}
			}
		}
	}

	protected void doImportLayouts(
			long userId, long groupId, boolean privateLayout,
			Map<String, String[]> parameterMap, File file)
		throws Exception {

		boolean deleteMissingLayouts = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS,
			Boolean.TRUE.booleanValue());
		boolean deletePortletData = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.DELETE_PORTLET_DATA);
		boolean importCategories = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.CATEGORIES);
		boolean importPermissions = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PERMISSIONS);
		boolean importLogo = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.LOGO);
		boolean importLayoutSetSettings = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.LAYOUT_SET_SETTINGS);

		boolean layoutSetPrototypeLinkEnabled = MapUtil.getBoolean(
			parameterMap,
			PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_LINK_ENABLED);

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.isLayoutSetPrototype()) {
			layoutSetPrototypeLinkEnabled = false;
		}

		String layoutsImportMode = MapUtil.getString(
			parameterMap, PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE,
			PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE_MERGE_BY_LAYOUT_UUID);
		String userIdStrategy = MapUtil.getString(
			parameterMap, PortletDataHandlerKeys.USER_ID_STRATEGY);

		if (_log.isDebugEnabled()) {
			_log.debug("Delete portlet data " + deletePortletData);
			_log.debug("Import categories " + importCategories);
			_log.debug("Import permissions " + importPermissions);
		}

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		LayoutCache layoutCache = new LayoutCache();

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			groupId, privateLayout);

		long companyId = layoutSet.getCompanyId();

		User user = UserUtil.findByPrimaryKey(userId);

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setSignedIn(false);
			serviceContext.setUserId(userId);

			ServiceContextThreadLocal.pushServiceContext(serviceContext);
		}

		UserIdStrategy strategy = _portletImporter.getUserIdStrategy(
			user, userIdStrategy);

		ManifestSummary manifestSummary =
			ExportImportHelperUtil.getManifestSummary(
				userId, groupId, parameterMap, file);

		ZipReader zipReader = ZipReaderFactoryUtil.getZipReader(file);

		final PortletDataContext portletDataContext =
			PortletDataContextFactoryUtil.createImportPortletDataContext(
				companyId, groupId, parameterMap, strategy, zipReader);

		portletDataContext.setManifestSummary(manifestSummary);
		portletDataContext.setPortetDataContextListener(
			new PortletDataContextListenerImpl(portletDataContext));

		portletDataContext.setPrivateLayout(privateLayout);

		// Zip

		validateFile(portletDataContext);

		// Company id

		long sourceCompanyId = GetterUtil.getLong(
			_headerElement.attributeValue("company-id"));

		portletDataContext.setSourceCompanyId(sourceCompanyId);

		// Company group id

		long sourceCompanyGroupId = GetterUtil.getLong(
			_headerElement.attributeValue("company-group-id"));

		portletDataContext.setSourceCompanyGroupId(sourceCompanyGroupId);

		// Group id

		long sourceGroupId = GetterUtil.getLong(
			_headerElement.attributeValue("group-id"));

		portletDataContext.setSourceGroupId(sourceGroupId);

		// User personal site group id

		long sourceUserPersonalSiteGroupId = GetterUtil.getLong(
			_headerElement.attributeValue("user-personal-site-group-id"));

		portletDataContext.setSourceUserPersonalSiteGroupId(
			sourceUserPersonalSiteGroupId);

		// Layout and layout set prototype

		String layoutSetPrototypeUuid = _layoutsElement.attributeValue(
			"layout-set-prototype-uuid");

		String larType = _headerElement.attributeValue("type");

		if (group.isLayoutPrototype() && larType.equals("layout-prototype")) {
			deleteMissingLayouts = false;

			LayoutPrototype layoutPrototype =
				LayoutPrototypeLocalServiceUtil.getLayoutPrototype(
					group.getClassPK());

			String layoutPrototypeUuid = GetterUtil.getString(
				_headerElement.attributeValue("type-uuid"));

			LayoutPrototype existingLayoutPrototype = null;

			if (Validator.isNotNull(layoutPrototypeUuid)) {
				try {
					existingLayoutPrototype =
						LayoutPrototypeLocalServiceUtil.
							getLayoutPrototypeByUuidAndCompanyId(
								layoutPrototypeUuid, companyId);
				}
				catch (NoSuchLayoutPrototypeException nslpe) {
				}
			}

			if (existingLayoutPrototype == null) {
				List<Layout> layouts =
					LayoutLocalServiceUtil.getLayoutsByLayoutPrototypeUuid(
						layoutPrototype.getUuid());

				layoutPrototype.setUuid(layoutPrototypeUuid);

				LayoutPrototypeLocalServiceUtil.updateLayoutPrototype(
					layoutPrototype);

				for (Layout layout : layouts) {
					layout.setLayoutPrototypeUuid(layoutPrototypeUuid);

					LayoutLocalServiceUtil.updateLayout(layout);
				}
			}
		}
		else if (group.isLayoutSetPrototype() &&
				 larType.equals("layout-set-prototype")) {

			LayoutSetPrototype layoutSetPrototype =
				LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototype(
					group.getClassPK());

			String importedLayoutSetPrototypeUuid = GetterUtil.getString(
				_headerElement.attributeValue("type-uuid"));

			LayoutSetPrototype existingLayoutSetPrototype = null;

			if (Validator.isNotNull(importedLayoutSetPrototypeUuid)) {
				try {
					existingLayoutSetPrototype =
						LayoutSetPrototypeLocalServiceUtil.
							getLayoutSetPrototypeByUuidAndCompanyId(
								importedLayoutSetPrototypeUuid, companyId);
				}
				catch (NoSuchLayoutSetPrototypeException nslspe) {
				}
			}

			if (existingLayoutSetPrototype == null) {
				layoutSetPrototype.setUuid(importedLayoutSetPrototypeUuid);

				LayoutSetPrototypeLocalServiceUtil.updateLayoutSetPrototype(
					layoutSetPrototype);
			}
		}
		else if (larType.equals("layout-set-prototype")) {
			layoutSetPrototypeUuid = GetterUtil.getString(
				_headerElement.attributeValue("type-uuid"));
		}

		if (Validator.isNotNull(layoutSetPrototypeUuid)) {
			layoutSet.setLayoutSetPrototypeUuid(layoutSetPrototypeUuid);
			layoutSet.setLayoutSetPrototypeLinkEnabled(
				layoutSetPrototypeLinkEnabled);

			LayoutSetLocalServiceUtil.updateLayoutSet(layoutSet);
		}

		Element missingReferencesElement = _rootElement.element(
			"missing-references");

		if (missingReferencesElement != null) {
			portletDataContext.setMissingReferencesElement(
				missingReferencesElement);
		}

		// Deletion system events

		_deletionSystemEventImporter.importDeletionSystemEvents(
			portletDataContext);

		// Look and feel

		if (importLogo) {
			String logoPath = _headerElement.attributeValue("logo-path");

			byte[] iconBytes = portletDataContext.getZipEntryAsByteArray(
				logoPath);

			if (ArrayUtil.isNotEmpty(iconBytes)) {
				LayoutSetLocalServiceUtil.updateLogo(
					groupId, privateLayout, true, iconBytes);
			}
			else {
				LayoutSetLocalServiceUtil.updateLogo(
					groupId, privateLayout, false, (File)null);
			}
		}

		_themeImporter.importTheme(portletDataContext, layoutSet);

		if (importLayoutSetSettings) {
			String settings = GetterUtil.getString(
				_headerElement.elementText("settings"));

			LayoutSetLocalServiceUtil.updateSettings(
				groupId, privateLayout, settings);
		}

		Element portletsElement = _rootElement.element("portlets");

		List<Element> portletElements = portletsElement.elements("portlet");

		if (BackgroundTaskThreadLocal.hasBackgroundTask()) {
			List<String> portletIds = new ArrayList<String>();

			for (Element portletElement : portletElements) {
				String portletId = portletElement.attributeValue("portlet-id");

				Portlet portlet = PortletLocalServiceUtil.getPortletById(
					portletDataContext.getCompanyId(), portletId);

				if (!portlet.isActive() || portlet.isUndeployedPortlet()) {
					continue;
				}

				portletIds.add(portletId);
			}

			PortletDataHandlerStatusMessageSenderUtil.sendStatusMessage(
				"layout", portletIds.toArray(new String[portletIds.size()]),
				manifestSummary);
		}

		// Read asset categories, asset tags, comments, locks, permissions, and
		// ratings entries to make them available to the data handlers through
		// the portlet data context

		if (importPermissions) {
			for (Element portletElement : portletElements) {
				String portletPath = portletElement.attributeValue("path");

				Document portletDocument = SAXReaderUtil.read(
					portletDataContext.getZipEntryAsString(portletPath));

				_permissionImporter.checkRoles(
					layoutCache, companyId, groupId, userId,
					portletDocument.getRootElement());
			}

			_permissionImporter.readPortletDataPermissions(portletDataContext);
		}

		_portletImporter.readAssetCategories(portletDataContext);
		_portletImporter.readAssetTags(portletDataContext);
		_portletImporter.readComments(portletDataContext);
		_portletImporter.readExpandoTables(portletDataContext);
		_portletImporter.readLocks(portletDataContext);
		_portletImporter.readRatingsEntries(portletDataContext);

		// Layouts

		List<Layout> previousLayouts = LayoutUtil.findByG_P(
			groupId, privateLayout);

		// Remove layouts that were deleted from the layout set prototype

		if (Validator.isNotNull(layoutSetPrototypeUuid) &&
			layoutSetPrototypeLinkEnabled) {

			LayoutSetPrototype layoutSetPrototype =
				LayoutSetPrototypeLocalServiceUtil.
					getLayoutSetPrototypeByUuidAndCompanyId(
						layoutSetPrototypeUuid, companyId);

			for (Layout layout : previousLayouts) {
				String sourcePrototypeLayoutUuid =
					layout.getSourcePrototypeLayoutUuid();

				if (Validator.isNull(layout.getSourcePrototypeLayoutUuid())) {
					continue;
				}

				Layout sourcePrototypeLayout = LayoutUtil.fetchByUUID_G_P(
					sourcePrototypeLayoutUuid, layoutSetPrototype.getGroupId(),
					true);

				if (sourcePrototypeLayout == null) {
					LayoutLocalServiceUtil.deleteLayout(
						layout, false, serviceContext);
				}
			}
		}

		List<String> sourceLayoutsUuids = new ArrayList<String>();
		List<Layout> newLayouts = new ArrayList<Layout>();

		if (_log.isDebugEnabled()) {
			if (_layoutElements.size() > 0) {
				_log.debug("Importing layouts");
			}
		}

		for (Element layoutElement : _layoutElements) {
			importLayout(
				portletDataContext, sourceLayoutsUuids, newLayouts,
				layoutElement);
		}

		// Delete portlet data

		Map<Long, Layout> newLayoutsMap =
			(Map<Long, Layout>)portletDataContext.getNewPrimaryKeysMap(
				Layout.class + ".layout");

		if (deletePortletData) {
			if (_log.isDebugEnabled()) {
				if (portletElements.size() > 0) {
					_log.debug("Deleting portlet data");
				}
			}

			for (Element portletElement : portletElements) {
				String portletId = portletElement.attributeValue("portlet-id");
				long layoutId = GetterUtil.getLong(
					portletElement.attributeValue("layout-id"));

				long plid = LayoutConstants.DEFAULT_PLID;

				Layout layout = newLayoutsMap.get(layoutId);

				if (layout != null) {
					plid = layout.getPlid();
				}

				portletDataContext.setPlid(plid);

				_portletImporter.deletePortletData(
					portletDataContext, portletId, plid);
			}
		}

		// Import portlets

		if (_log.isDebugEnabled()) {
			if (portletElements.size() > 0) {
				_log.debug("Importing portlets");
			}
		}

		for (Element portletElement : portletElements) {
			String portletPath = portletElement.attributeValue("path");
			String portletId = portletElement.attributeValue("portlet-id");
			long layoutId = GetterUtil.getLong(
				portletElement.attributeValue("layout-id"));
			long oldPlid = GetterUtil.getLong(
				portletElement.attributeValue("old-plid"));

			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				portletDataContext.getCompanyId(), portletId);

			if (!portlet.isActive() || portlet.isUndeployedPortlet()) {
				continue;
			}

			Layout layout = newLayoutsMap.get(layoutId);

			long plid = LayoutConstants.DEFAULT_PLID;

			if (layout != null) {
				plid = layout.getPlid();
			}

			portletDataContext.setPlid(plid);
			portletDataContext.setOldPlid(oldPlid);

			if (BackgroundTaskThreadLocal.hasBackgroundTask()) {
				PortletDataHandlerStatusMessageSenderUtil.sendStatusMessage(
					"portlet", portletId, manifestSummary);
			}

			Document portletDocument = SAXReaderUtil.read(
				portletDataContext.getZipEntryAsString(portletPath));

			portletElement = portletDocument.getRootElement();

			// The order of the import is important. You must always import the
			// portlet preferences first, then the portlet data, then the
			// portlet permissions. The import of the portlet data assumes that
			// portlet preferences already exist.

			setPortletScope(portletDataContext, portletElement);

			long portletPreferencesGroupId = groupId;

			Element portletDataElement = portletElement.element("portlet-data");

			Map<String, Boolean> importPortletControlsMap =
				getImportPortletControlsMap(
					companyId, portletId, parameterMap, portletDataElement,
					manifestSummary);

			try {
				portletDataContext.setRootPortletId(
					PortletConstants.getRootPortletId(portletId));

				if (layout != null) {
					portletPreferencesGroupId = layout.getGroupId();
				}

				// Portlet preferences

				_portletImporter.importPortletPreferences(
					portletDataContext, layoutSet.getCompanyId(),
					portletPreferencesGroupId, layout, null, portletElement,
					false,
					importPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS),
					importPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_DATA),
					importPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_SETUP),
					importPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_USER_PREFERENCES));

				// Portlet data

				if (importPortletControlsMap.get(
						PortletDataHandlerKeys.PORTLET_DATA)) {

					_portletImporter.importPortletData(
						portletDataContext, portletId, plid,
						portletDataElement);
				}
			}
			finally {
				portletDataContext.setRootPortletId(StringPool.BLANK);

				_portletImporter.resetPortletScope(
					portletDataContext, portletPreferencesGroupId);
			}

			// Portlet permissions

			if (importPermissions) {
				_permissionImporter.importPortletPermissions(
					layoutCache, companyId, groupId, userId, layout,
					portletElement, portletId);
			}

			// Archived setups

			_portletImporter.importPortletPreferences(
				portletDataContext, layoutSet.getCompanyId(), groupId, null,
				null, portletElement, false,
				importPortletControlsMap.get(
					PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS),
				importPortletControlsMap.get(
					PortletDataHandlerKeys.PORTLET_DATA),
				importPortletControlsMap.get(
					PortletDataHandlerKeys.PORTLET_SETUP),
				importPortletControlsMap.get(
					PortletDataHandlerKeys.PORTLET_USER_PREFERENCES));
		}

		// Asset links

		_portletImporter.readAssetLinks(portletDataContext);

		// Delete missing layouts

		if (deleteMissingLayouts) {
			deleteMissingLayouts(
				sourceLayoutsUuids, previousLayouts, serviceContext);
		}

		// Page count

		layoutSet = LayoutSetLocalServiceUtil.updatePageCount(
			groupId, privateLayout);

		// Site

		GroupLocalServiceUtil.updateSite(groupId, true);

		// Last merge time must be the same for merged layouts and the layout
		// set

		long lastMergeTime = System.currentTimeMillis();

		for (Layout layout : newLayouts) {
			boolean modifiedTypeSettingsProperties = false;

			UnicodeProperties typeSettingsProperties =
				layout.getTypeSettingsProperties();

			// Journal article layout type

			String articleId = typeSettingsProperties.getProperty("article-id");

			if (Validator.isNotNull(articleId)) {
				Map<String, String> articleIds =
					(Map<String, String>)portletDataContext.
						getNewPrimaryKeysMap(
							JournalArticle.class + ".articleId");

				typeSettingsProperties.setProperty(
					"article-id",
					MapUtil.getString(articleIds, articleId, articleId));

				modifiedTypeSettingsProperties = true;
			}

			// Last merge time for layout

			if (layoutsImportMode.equals(
					PortletDataHandlerKeys.
						LAYOUTS_IMPORT_MODE_CREATED_FROM_PROTOTYPE)) {

				typeSettingsProperties.setProperty(
					Sites.LAST_MERGE_TIME, String.valueOf(lastMergeTime));

				modifiedTypeSettingsProperties = true;
			}

			if (modifiedTypeSettingsProperties) {
				LayoutUtil.update(layout);
			}
		}

		// Last merge time for layout set

		if (layoutsImportMode.equals(
				PortletDataHandlerKeys.
					LAYOUTS_IMPORT_MODE_CREATED_FROM_PROTOTYPE)) {

			UnicodeProperties settingsProperties =
				layoutSet.getSettingsProperties();

			String mergeFailFriendlyURLLayouts =
				settingsProperties.getProperty(
					Sites.MERGE_FAIL_FRIENDLY_URL_LAYOUTS);

			if (Validator.isNull(mergeFailFriendlyURLLayouts)) {
				settingsProperties.setProperty(
					Sites.LAST_MERGE_TIME, String.valueOf(lastMergeTime));

				LayoutSetLocalServiceUtil.updateLayoutSet(layoutSet);
			}
		}

		// Page priorities

		updateLayoutPriorities(
			portletDataContext, _layoutElements, privateLayout);

		if (_log.isInfoEnabled()) {
			_log.info("Importing layouts takes " + stopWatch.getTime() + " ms");
		}

		zipReader.close();

		ExportImportHelperUtil.reindex(portletDataContext, userId);
	}

	protected void importLayout(
			PortletDataContext portletDataContext,
			List<String> sourceLayoutsUuids, List<Layout> newLayouts,
			Element layoutElement)
		throws Exception {

		String action = layoutElement.attributeValue("action");

		if (!action.equals(Constants.SKIP)) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, layoutElement);

			List<Layout> portletDataContextNewLayouts =
				portletDataContext.getNewLayouts();

			newLayouts.addAll(portletDataContextNewLayouts);

			portletDataContextNewLayouts.clear();
		}

		if (!action.equals(Constants.DELETE)) {
			sourceLayoutsUuids.add(layoutElement.attributeValue("uuid"));
		}
	}

	protected void readXML(PortletDataContext portletDataContext)
		throws Exception {

		if ((_rootElement != null) && (_headerElement != null) &&
			(_layoutsElement != null) && (_layoutElements != null)) {

			return;
		}

		String xml = portletDataContext.getZipEntryAsString("/manifest.xml");

		if (xml == null) {
			throw new LARFileException("manifest.xml not found in the LAR");
		}

		try {
			Document document = SAXReaderUtil.read(xml);

			_rootElement = document.getRootElement();

			portletDataContext.setImportDataRootElement(_rootElement);
		}
		catch (Exception e) {
			throw new LARFileException(e);
		}

		_headerElement = _rootElement.element("header");

		_layoutsElement = portletDataContext.getImportDataGroupElement(
			Layout.class);

		_layoutElements = _layoutsElement.elements();
	}

	protected void setPortletScope(
		PortletDataContext portletDataContext, Element portletElement) {

		// Portlet data scope

		String scopeLayoutUuid = GetterUtil.getString(
			portletElement.attributeValue("scope-layout-uuid"));
		String scopeLayoutType = GetterUtil.getString(
			portletElement.attributeValue("scope-layout-type"));

		portletDataContext.setScopeLayoutUuid(scopeLayoutUuid);
		portletDataContext.setScopeType(scopeLayoutType);

		// Layout scope

		try {
			Group scopeGroup = null;

			if (scopeLayoutType.equals("company")) {
				scopeGroup = GroupLocalServiceUtil.getCompanyGroup(
					portletDataContext.getCompanyId());
			}
			else if (Validator.isNotNull(scopeLayoutUuid)) {
				Layout scopeLayout =
					LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
						scopeLayoutUuid, portletDataContext.getGroupId(),
						portletDataContext.isPrivateLayout());

				if (scopeLayout.hasScopeGroup()) {
					scopeGroup = scopeLayout.getScopeGroup();
				}
				else {
					String name = String.valueOf(scopeLayout.getPlid());

					scopeGroup = GroupLocalServiceUtil.addGroup(
						portletDataContext.getUserId(null),
						GroupConstants.DEFAULT_PARENT_GROUP_ID,
						Layout.class.getName(), scopeLayout.getPlid(),
						GroupConstants.DEFAULT_LIVE_GROUP_ID, name, null, 0,
						true, GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION,
						null, false, true, null);
				}

				Group group = scopeLayout.getGroup();

				if (group.isStaged() && !group.isStagedRemotely()) {
					try {
						boolean privateLayout = GetterUtil.getBoolean(
							portletElement.attributeValue("private-layout"));

						Layout oldLayout =
							LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
								scopeLayoutUuid,
								portletDataContext.getSourceGroupId(),
								privateLayout);

						Group oldScopeGroup = oldLayout.getScopeGroup();

						if (group.isStagingGroup()) {
							scopeGroup.setLiveGroupId(
								oldScopeGroup.getGroupId());

							GroupLocalServiceUtil.updateGroup(scopeGroup);
						}
						else {
							oldScopeGroup.setLiveGroupId(
								scopeGroup.getGroupId());

							GroupLocalServiceUtil.updateGroup(oldScopeGroup);
						}
					}
					catch (NoSuchLayoutException nsle) {
						if (_log.isWarnEnabled()) {
							_log.warn(nsle);
						}
					}
				}
			}

			if (scopeGroup != null) {
				portletDataContext.setScopeGroupId(scopeGroup.getGroupId());
			}
		}
		catch (PortalException pe) {
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void updateLayoutPriorities(
			PortletDataContext portletDataContext, List<Element> layoutElements,
			boolean privateLayout)
		throws SystemException {

		Map<Long, Layout> layouts =
			(Map<Long, Layout>)portletDataContext.getNewPrimaryKeysMap(
				Layout.class + ".layout");

		Map<Long, Integer> layoutPriorities = new HashMap<Long, Integer>();

		int maxPriority = Integer.MIN_VALUE;

		for (Element layoutElement : layoutElements) {
			String action = layoutElement.attributeValue(Constants.ACTION);

			if (action.equals(Constants.SKIP)) {

				// We only want to update priorites if there are no elements
				// with the SKIP action

				return;
			}

			if (action.equals(Constants.ADD)) {
				long layoutId = GetterUtil.getLong(
					layoutElement.attributeValue("layout-id"));

				Layout layout = layouts.get(layoutId);

				// Layout might have not been imported due to a controlled
				// error. See SitesImpl#addMergeFailFriendlyURLLayout.

				if (layout == null) {
					continue;
				}

				int layoutPriority = GetterUtil.getInteger(
					layoutElement.attributeValue("layout-priority"));

				layoutPriorities.put(layout.getPlid(), layoutPriority);

				if (maxPriority < layoutPriority) {
					maxPriority = layoutPriority;
				}
			}
		}

		List<Layout> layoutSetLayouts = LayoutLocalServiceUtil.getLayouts(
			portletDataContext.getGroupId(), privateLayout);

		for (Layout layout : layoutSetLayouts) {
			if (layoutPriorities.containsKey(layout.getPlid())) {
				layout.setPriority(layoutPriorities.get(layout.getPlid()));
			}
			else {
				layout.setPriority(++maxPriority);
			}

			LayoutLocalServiceUtil.updateLayout(layout);
		}
	}

	protected void validateFile(PortletDataContext portletDataContext)
		throws Exception {

		// Build compatibility

		readXML(portletDataContext);

		int buildNumber = ReleaseInfo.getBuildNumber();

		int importBuildNumber = GetterUtil.getInteger(
			_headerElement.attributeValue("build-number"));

		if (buildNumber != importBuildNumber) {
			throw new LayoutImportException(
				"LAR build number " + importBuildNumber + " does not match " +
					"portal build number " + buildNumber);
		}

		// Type

		String larType = _headerElement.attributeValue("type");

		if (!larType.equals("layout-prototype") &&
			!larType.equals("layout-set") &&
			!larType.equals("layout-set-prototype")) {

			throw new LARTypeException(larType);
		}

		Group group = GroupLocalServiceUtil.fetchGroup(
			portletDataContext.getGroupId());

		String layoutsImportMode = MapUtil.getString(
			portletDataContext.getParameterMap(),
			PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE);

		if (larType.equals("layout-prototype") && !group.isLayoutPrototype() &&
			!layoutsImportMode.equals(
				PortletDataHandlerKeys.
					LAYOUTS_IMPORT_MODE_CREATED_FROM_PROTOTYPE)) {

			throw new LARTypeException(
				"A page template can only be imported to a page template");
		}

		if (larType.equals("layout-set") &&
			(group.isLayoutPrototype() || group.isLayoutSetPrototype())) {

			throw new LARTypeException("A site can only be imported to a site");
		}

		if (larType.equals("layout-set-prototype") &&
			!group.isLayoutSetPrototype() &&
			!layoutsImportMode.equals(
				PortletDataHandlerKeys.
					LAYOUTS_IMPORT_MODE_CREATED_FROM_PROTOTYPE)) {

			throw new LARTypeException(
				"A site template can only be imported to a site template");
		}

		// Available locales

		Locale[] sourceAvailableLocales = LocaleUtil.fromLanguageIds(
			StringUtil.split(
				_headerElement.attributeValue("available-locales")));

		Locale[] targetAvailableLocales = LanguageUtil.getAvailableLocales(
			portletDataContext.getScopeGroupId());

		for (Locale sourceAvailableLocale : sourceAvailableLocales) {
			if (!ArrayUtil.contains(
					targetAvailableLocales, sourceAvailableLocale)) {

				LocaleException le = new LocaleException(
					LocaleException.TYPE_EXPORT_IMPORT);

				le.setSourceAvailableLocales(sourceAvailableLocales);
				le.setTargetAvailableLocales(targetAvailableLocales);

				throw le;
			}
		}

		// Layout prototypes validity

		validateLayoutPrototypes(
			portletDataContext.getCompanyId(), _layoutsElement,
			_layoutElements);
	}

	protected void validateLayoutPrototypes(
			long companyId, Element layoutsElement,
			List<Element> layoutElements)
		throws Exception {

		List<Tuple> missingLayoutPrototypes = new ArrayList<Tuple>();

		String layoutSetPrototypeUuid = layoutsElement.attributeValue(
			"layout-set-prototype-uuid");

		if (Validator.isNotNull(layoutSetPrototypeUuid)) {
			try {
				LayoutSetPrototypeLocalServiceUtil.
					getLayoutSetPrototypeByUuidAndCompanyId(
						layoutSetPrototypeUuid, companyId);
			}
			catch (NoSuchLayoutSetPrototypeException nlspe) {
				String layoutSetPrototypeName = layoutsElement.attributeValue(
					"layout-set-prototype-name");

				missingLayoutPrototypes.add(
					new Tuple(
						LayoutSetPrototype.class.getName(),
						layoutSetPrototypeUuid, layoutSetPrototypeName));
			}
		}

		for (Element layoutElement : layoutElements) {
			String action = layoutElement.attributeValue("action");

			if (action.equals(Constants.SKIP)) {
				continue;
			}

			String layoutPrototypeUuid = GetterUtil.getString(
				layoutElement.attributeValue("layout-prototype-uuid"));

			if (Validator.isNotNull(layoutPrototypeUuid)) {
				try {
					LayoutPrototypeLocalServiceUtil.
						getLayoutPrototypeByUuidAndCompanyId(
							layoutPrototypeUuid, companyId);
				}
				catch (NoSuchLayoutPrototypeException nslpe) {
					String layoutPrototypeName = GetterUtil.getString(
						layoutElement.attributeValue("layout-prototype-name"));

					missingLayoutPrototypes.add(
						new Tuple(
							LayoutPrototype.class.getName(),
							layoutPrototypeUuid, layoutPrototypeName));
				}
			}
		}

		if (!missingLayoutPrototypes.isEmpty()) {
			throw new LayoutPrototypeException(missingLayoutPrototypes);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LayoutImporter.class);

	private DeletionSystemEventImporter _deletionSystemEventImporter =
		new DeletionSystemEventImporter();
	private Element _headerElement;
	private List<Element> _layoutElements;
	private Element _layoutsElement;
	private PermissionImporter _permissionImporter = new PermissionImporter();
	private PortletImporter _portletImporter = new PortletImporter();
	private Element _rootElement;
	private ThemeImporter _themeImporter = new ThemeImporter();

}
