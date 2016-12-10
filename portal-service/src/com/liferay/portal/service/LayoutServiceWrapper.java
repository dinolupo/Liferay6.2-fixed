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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link LayoutService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutService
 * @generated
 */
@ProviderType
public class LayoutServiceWrapper implements LayoutService,
	ServiceWrapper<LayoutService> {
	public LayoutServiceWrapper(LayoutService layoutService) {
		_layoutService = layoutService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _layoutService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_layoutService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Adds a layout with additional parameters.
	*
	* <p>
	* This method handles the creation of the layout including its resources,
	* metadata, and internal data structures. It is not necessary to make
	* subsequent calls to any methods to setup default groups, resources, ...
	* etc.
	* </p>
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param parentLayoutId the primary key of the parent layout
	(optionally {@link
	com.liferay.portal.model.LayoutConstants#DEFAULT_PARENT_LAYOUT_ID})
	* @param localeNamesMap the layout's locales and localized names
	* @param localeTitlesMap the layout's locales and localized titles
	* @param descriptionMap the layout's locales and localized
	descriptions
	* @param keywordsMap the layout's locales and localized keywords
	* @param robotsMap the layout's locales and localized robots
	* @param type the layout's type (optionally {@link
	com.liferay.portal.model.LayoutConstants#TYPE_PORTLET}). The
	possible types can be found in {@link
	com.liferay.portal.model.LayoutConstants}.
	* @param hidden whether the layout is hidden
	* @param friendlyURL the layout's locales and localized friendly URLs.
	To see how the URL is normalized when accessed, see {@link
	com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	String)}.
	* @param serviceContext the service context to be applied. Must set
	the UUID for the layout. Can set the creation date,
	modification date, and expando bridge attributes for the
	layout. For layouts that belong to a layout set prototype, an
	attribute named <code>layoutUpdateable</code> can be used to
	specify whether site administrators can modify this page
	within their site.
	* @return the layout
	* @throws PortalException if a group with the primary key could not be
	found, if the group did not have permission to manage the
	layouts involved, if layout values were invalid, or if a
	portal exception occurred
	* @throws SystemException if a system exception occurred
	* @deprecated As of 6.2.0, replaced by {@link #addLayout(long, boolean,
	long, Map, Map, Map, Map, Map, String, String, boolean, Map,
	ServiceContext)}
	*/
	@Override
	public com.liferay.portal.model.Layout addLayout(long groupId,
		boolean privateLayout, long parentLayoutId,
		java.util.Map<java.util.Locale, java.lang.String> localeNamesMap,
		java.util.Map<java.util.Locale, java.lang.String> localeTitlesMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Map<java.util.Locale, java.lang.String> keywordsMap,
		java.util.Map<java.util.Locale, java.lang.String> robotsMap,
		java.lang.String type, boolean hidden, java.lang.String friendlyURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.addLayout(groupId, privateLayout, parentLayoutId,
			localeNamesMap, localeTitlesMap, descriptionMap, keywordsMap,
			robotsMap, type, hidden, friendlyURL, serviceContext);
	}

	/**
	* Adds a layout with additional parameters.
	*
	* <p>
	* This method handles the creation of the layout including its resources,
	* metadata, and internal data structures. It is not necessary to make
	* subsequent calls to any methods to setup default groups, resources, ...
	* etc.
	* </p>
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param parentLayoutId the primary key of the parent layout (optionally
	{@link
	com.liferay.portal.model.LayoutConstants#DEFAULT_PARENT_LAYOUT_ID})
	* @param localeNamesMap the layout's locales and localized names
	* @param localeTitlesMap the layout's locales and localized titles
	* @param descriptionMap the layout's locales and localized descriptions
	* @param keywordsMap the layout's locales and localized keywords
	* @param robotsMap the layout's locales and localized robots
	* @param type the layout's type (optionally {@link
	com.liferay.portal.model.LayoutConstants#TYPE_PORTLET}). The
	possible types can be found in {@link
	com.liferay.portal.model.LayoutConstants}.
	* @param typeSettings the settings to load the unicode properties object.
	See {@link com.liferay.portal.kernel.util.UnicodeProperties
	#fastLoad(String)}.
	* @param hidden whether the layout is hidden
	* @param friendlyURLMap the layout's locales and localized friendly URLs.
	To see how the URL is normalized when accessed, see {@link
	com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	String)}.
	* @param serviceContext the service context to be applied. Must set the
	UUID for the layout. Can set the creation date, modification
	date, and expando bridge attributes for the layout. For layouts
	that belong to a layout set prototype, an attribute named
	<code>layoutUpdateable</code> can be used to specify whether site
	administrators can modify this page within their site.
	* @return the layout
	* @throws PortalException if a group with the primary key could not be
	found, if the group did not have permission to manage the layouts
	involved, if layout values were invalid, or if a portal exception
	occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout addLayout(long groupId,
		boolean privateLayout, long parentLayoutId,
		java.util.Map<java.util.Locale, java.lang.String> localeNamesMap,
		java.util.Map<java.util.Locale, java.lang.String> localeTitlesMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Map<java.util.Locale, java.lang.String> keywordsMap,
		java.util.Map<java.util.Locale, java.lang.String> robotsMap,
		java.lang.String type, java.lang.String typeSettings, boolean hidden,
		java.util.Map<java.util.Locale, java.lang.String> friendlyURLMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.addLayout(groupId, privateLayout, parentLayoutId,
			localeNamesMap, localeTitlesMap, descriptionMap, keywordsMap,
			robotsMap, type, typeSettings, hidden, friendlyURLMap,
			serviceContext);
	}

	/**
	* Adds a layout with single entry maps for name, title, and description to
	* the default locale.
	*
	* <p>
	* This method handles the creation of the layout including its resources,
	* metadata, and internal data structures. It is not necessary to make
	* subsequent calls to any methods to setup default groups, resources, ...
	* etc.
	* </p>
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param parentLayoutId the primary key of the parent layout (optionally
	{@link
	com.liferay.portal.model.LayoutConstants#DEFAULT_PARENT_LAYOUT_ID})
	* @param name Map the layout's locales and localized names
	* @param title Map the layout's locales and localized titles
	* @param description Map the layout's locales and localized descriptions
	* @param type the layout's type (optionally {@link
	com.liferay.portal.model.LayoutConstants#TYPE_PORTLET}). The
	possible types can be found in {@link
	com.liferay.portal.model.LayoutConstants}.
	* @param hidden whether the layout is hidden
	* @param friendlyURL the layout's locales and localized friendly URLs. To
	see how the URL is normalized when accessed, see {@link
	com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	String)}.
	* @param serviceContext the service context to be applied. Must set the
	UUID for the layout. Can specify the creation date, modification
	date, and expando bridge attributes for the layout. For layouts
	that belong to a layout set prototype, an attribute named
	<code>layoutUpdateable</code> can be used to specify whether site
	administrators can modify this page within their site.
	* @return the layout
	* @throws PortalException if a group with the primary key could not be
	found, if the group did not have permission to manage the layouts
	involved, if layout values were invalid, or if a portal exception
	occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout addLayout(long groupId,
		boolean privateLayout, long parentLayoutId, java.lang.String name,
		java.lang.String title, java.lang.String description,
		java.lang.String type, boolean hidden, java.lang.String friendlyURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.addLayout(groupId, privateLayout, parentLayoutId,
			name, title, description, type, hidden, friendlyURL, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry addTempFileEntry(
		long groupId, java.lang.String fileName,
		java.lang.String tempFolderName, java.io.InputStream inputStream,
		java.lang.String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.addTempFileEntry(groupId, fileName,
			tempFolderName, inputStream, mimeType);
	}

	/**
	* Deletes the layout with the primary key, also deleting the layout's child
	* layouts, and associated resources.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutId the primary key of the layout
	* @param serviceContext the service context to be applied
	* @throws PortalException if the user did not have permission to delete the
	layout, if a matching layout could not be found , or if some
	other portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteLayout(long groupId, boolean privateLayout,
		long layoutId, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.deleteLayout(groupId, privateLayout, layoutId,
			serviceContext);
	}

	/**
	* Deletes the layout with the plid, also deleting the layout's child
	* layouts, and associated resources.
	*
	* @param plid the primary key of the layout
	* @param serviceContext the service context to be applied
	* @throws PortalException if the user did not have permission to delete the
	layout, if a layout with the primary key could not be found , or
	if some other portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void deleteLayout(long plid,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.deleteLayout(plid, serviceContext);
	}

	@Override
	public void deleteTempFileEntry(long groupId, java.lang.String fileName,
		java.lang.String tempFolderName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.deleteTempFileEntry(groupId, fileName, tempFolderName);
	}

	/**
	* Exports the layouts that match the primary keys and the criteria as a
	* byte array.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutIds the primary keys of the layouts to be exported
	* @param parameterMap the mapping of parameters indicating which
	information to export. For information on the keys used in the
	map see {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	* @param startDate the export's start date
	* @param endDate the export's end date
	* @return the layouts as a byte array
	* @throws PortalException if a group or any layout with the primary key
	could not be found, if the group did not have permission to
	manage the layouts, or if some other portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public byte[] exportLayouts(long groupId, boolean privateLayout,
		long[] layoutIds,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.exportLayouts(groupId, privateLayout, layoutIds,
			parameterMap, startDate, endDate);
	}

	/**
	* Exports all layouts that match the criteria as a byte array.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param parameterMap the mapping of parameters indicating which
	information to export. For information on the keys used in the
	map see {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	* @param startDate the export's start date
	* @param endDate the export's end date
	* @return the layout as a byte array
	* @throws PortalException if a group with the primary key could not be
	found, if the group did not have permission to manage the
	layouts, or if some other portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public byte[] exportLayouts(long groupId, boolean privateLayout,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.exportLayouts(groupId, privateLayout,
			parameterMap, startDate, endDate);
	}

	/**
	* Exports all layouts that match the primary keys and criteria as a file.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutIds the primary keys of the layouts to be exported
	(optionally <code>null</code>)
	* @param parameterMap the mapping of parameters indicating which
	information to export. For information on the keys used in the
	map see {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	* @param startDate the export's start date
	* @param endDate the export's end date
	* @return the layouts as a File
	* @throws PortalException if a group or any layout with the primary key
	could not be found, it the group did not have permission to
	manage the layouts, or if some other portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.io.File exportLayoutsAsFile(long groupId,
		boolean privateLayout, long[] layoutIds,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.exportLayoutsAsFile(groupId, privateLayout,
			layoutIds, parameterMap, startDate, endDate);
	}

	@Override
	public long exportLayoutsAsFileInBackground(java.lang.String taskName,
		long groupId, boolean privateLayout, long[] layoutIds,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.exportLayoutsAsFileInBackground(taskName,
			groupId, privateLayout, layoutIds, parameterMap, startDate,
			endDate, fileName);
	}

	/**
	* Exports the portlet information (categories, permissions, ... etc.) as a
	* byte array.
	*
	* @param plid the primary key of the layout
	* @param groupId the primary key of the group
	* @param portletId the primary key of the portlet
	* @param parameterMap the mapping of parameters indicating which
	information to export. For information on the keys used in the
	map see {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	* @param startDate the export's start date
	* @param endDate the export's end date
	* @return the portlet information as a byte array
	* @throws PortalException if a layout, group, or portlet with the primary
	key could not be found, if the group did not have permission to
	manage the layouts involved, or if some other portal exception
	occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public byte[] exportPortletInfo(long plid, long groupId,
		java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.exportPortletInfo(plid, groupId, portletId,
			parameterMap, startDate, endDate);
	}

	@Override
	public byte[] exportPortletInfo(long companyId, java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.exportPortletInfo(companyId, portletId,
			parameterMap, startDate, endDate);
	}

	/**
	* Exports the portlet information (categories, permissions, ... etc.) as a
	* file.
	*
	* @param plid the primary key of the layout
	* @param groupId the primary key of the group
	* @param portletId the primary key of the portlet
	* @param parameterMap the mapping of parameters indicating which
	information to export. For information on the keys used in the
	map see {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	* @param startDate the export's start date
	* @param endDate the export's end date
	* @return the portlet information as a file
	* @throws PortalException if a layout, group, or portlet with the primary
	key could not be found, it the group did not have permission to
	manage the layouts involved, or if some other portal exception
	occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.io.File exportPortletInfoAsFile(long plid, long groupId,
		java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.exportPortletInfoAsFile(plid, groupId, portletId,
			parameterMap, startDate, endDate);
	}

	@Override
	public java.io.File exportPortletInfoAsFile(java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.exportPortletInfoAsFile(portletId, parameterMap,
			startDate, endDate);
	}

	@Override
	public long exportPortletInfoAsFileInBackground(java.lang.String taskName,
		long plid, long groupId, java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.exportPortletInfoAsFileInBackground(taskName,
			plid, groupId, portletId, parameterMap, startDate, endDate, fileName);
	}

	@Override
	public long exportPortletInfoAsFileInBackground(java.lang.String taskName,
		java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.exportPortletInfoAsFileInBackground(taskName,
			portletId, parameterMap, startDate, endDate, fileName);
	}

	/**
	* Returns all the ancestor layouts of the layout.
	*
	* @param plid the primary key of the layout
	* @return the ancestor layouts of the layout
	* @throws PortalException if a matching layout could not be found or if a
	portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.model.Layout> getAncestorLayouts(
		long plid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.getAncestorLayouts(plid);
	}

	/**
	* Returns the primary key of the default layout for the group.
	*
	* @param groupId the primary key of the group
	* @param scopeGroupId the primary key of the scope group. See {@link
	com.liferay.portal.service.ServiceContext#getScopeGroupId()}.
	* @param privateLayout whether the layout is private to the group
	* @param portletId the primary key of the portlet
	* @return Returns the primary key of the default layout group; {@link
	com.liferay.portal.model.LayoutConstants#DEFAULT_PLID} otherwise
	* @throws PortalException if a group, layout, or portlet with the primary
	key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long getDefaultPlid(long groupId, long scopeGroupId,
		boolean privateLayout, java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.getDefaultPlid(groupId, scopeGroupId,
			privateLayout, portletId);
	}

	@Override
	public long getDefaultPlid(long groupId, long scopeGroupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.getDefaultPlid(groupId, scopeGroupId, portletId);
	}

	/**
	* Returns the layout matching the UUID, group, and privacy.
	*
	* @param uuid the layout's UUID
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @return the matching layout
	* @throws PortalException if a matching layout could not be found, if the
	user did not have permission to view the layout, or if some other
	portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout getLayoutByUuidAndGroupId(
		java.lang.String uuid, long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.getLayoutByUuidAndGroupId(uuid, groupId,
			privateLayout);
	}

	/**
	* Returns the name of the layout.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutId the primary key of the layout
	* @param languageId the primary key of the language. For more information
	See {@link java.util.Locale}.
	* @return the layout's name
	* @throws PortalException if a matching layout could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getLayoutName(long groupId, boolean privateLayout,
		long layoutId, java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.getLayoutName(groupId, privateLayout, layoutId,
			languageId);
	}

	/**
	* Returns the layout references for all the layouts that belong to the
	* company and belong to the portlet that matches the preferences.
	*
	* @param companyId the primary key of the company
	* @param portletId the primary key of the portlet
	* @param preferencesKey the portlet's preference key
	* @param preferencesValue the portlet's preference value
	* @return the layout references of the matching layouts
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.LayoutReference[] getLayoutReferences(
		long companyId, java.lang.String portletId,
		java.lang.String preferencesKey, java.lang.String preferencesValue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.getLayoutReferences(companyId, portletId,
			preferencesKey, preferencesValue);
	}

	@Override
	public java.util.List<com.liferay.portal.model.Layout> getLayouts(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.getLayouts(groupId, privateLayout);
	}

	@Override
	public java.util.List<com.liferay.portal.model.Layout> getLayouts(
		long groupId, boolean privateLayout, long parentLayoutId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.getLayouts(groupId, privateLayout, parentLayoutId);
	}

	@Override
	public java.util.List<com.liferay.portal.model.Layout> getLayouts(
		long groupId, boolean privateLayout, long parentLayoutId,
		boolean incomplete, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.getLayouts(groupId, privateLayout,
			parentLayoutId, incomplete, start, end);
	}

	@Override
	public int getLayoutsCount(long groupId, boolean privateLayout,
		long parentLayoutId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.getLayoutsCount(groupId, privateLayout,
			parentLayoutId);
	}

	@Override
	public java.lang.String[] getTempFileEntryNames(long groupId,
		java.lang.String tempFolderName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.getTempFileEntryNames(groupId, tempFolderName);
	}

	/**
	* Imports the layouts from the byte array.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param parameterMap the mapping of parameters indicating which
	information will be imported. For information on the keys used in
	the map see {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	* @param bytes the byte array with the data
	* @throws PortalException if a group with the primary key could not be
	found, if the group did not have permission to manage the
	layouts, or if some other portal exception occurred
	* @throws SystemException if a system exception occurred
	* @see com.liferay.portal.lar.LayoutImporter
	*/
	@Override
	public void importLayouts(long groupId, boolean privateLayout,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.importLayouts(groupId, privateLayout, parameterMap, bytes);
	}

	/**
	* Imports the layouts from the file.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param parameterMap the mapping of parameters indicating which
	information will be imported. For information on the keys used in
	the map see {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	* @param file the LAR file with the data
	* @throws PortalException if a group with the primary key could not be
	found, if the group did not have permission to manage the layouts
	and publish, or if some other portal exception occurred
	* @throws SystemException if a system exception occurred
	* @see com.liferay.portal.lar.LayoutImporter
	*/
	@Override
	public void importLayouts(long groupId, boolean privateLayout,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.importLayouts(groupId, privateLayout, parameterMap, file);
	}

	/**
	* Imports the layouts from the input stream.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param parameterMap the mapping of parameters indicating which
	information will be imported. For information on the keys used in
	the map see {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	* @param is the input stream
	* @throws PortalException if a group with the primary key could not be
	found, if the group did not have permission to manage the
	layouts, or if some other portal exception occurred
	* @throws SystemException if a system exception occurred
	* @see com.liferay.portal.lar.LayoutImporter
	*/
	@Override
	public void importLayouts(long groupId, boolean privateLayout,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.importLayouts(groupId, privateLayout, parameterMap, is);
	}

	@Override
	public long importLayoutsInBackground(java.lang.String taskName,
		long groupId, boolean privateLayout,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.importLayoutsInBackground(taskName, groupId,
			privateLayout, parameterMap, file);
	}

	@Override
	public long importLayoutsInBackground(java.lang.String taskName,
		long groupId, boolean privateLayout,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.importLayoutsInBackground(taskName, groupId,
			privateLayout, parameterMap, inputStream);
	}

	/**
	* Imports the portlet information (categories, permissions, ... etc.) from
	* the file.
	*
	* @param plid the primary key of the layout
	* @param groupId the primary key of the group
	* @param portletId the primary key of the portlet
	* @param parameterMap the mapping of parameters indicating which
	information will be imported. For information on the keys used in
	the map see {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	* @param file the LAR file with the data
	* @throws PortalException if a group, layout, or portlet with the primary
	key could not be found, or if the group did not have permission
	to manage the layouts
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void importPortletInfo(long plid, long groupId,
		java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.importPortletInfo(plid, groupId, portletId,
			parameterMap, file);
	}

	/**
	* Imports the portlet information (categories, permissions, ... etc.) from
	* the input stream.
	*
	* @param plid the primary key of the layout
	* @param groupId the primary key of the group
	* @param portletId the primary key of the portlet
	* @param parameterMap the mapping of parameters indicating which
	information will be imported. For information on the keys used in
	the map see {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	* @param is the input stream
	* @throws PortalException if a group, portlet, or layout with the primary
	key could not be found or if the group did not have permission to
	manage the layouts
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void importPortletInfo(long plid, long groupId,
		java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.importPortletInfo(plid, groupId, portletId,
			parameterMap, is);
	}

	@Override
	public void importPortletInfo(java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.importPortletInfo(portletId, parameterMap, file);
	}

	@Override
	public void importPortletInfo(java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.importPortletInfo(portletId, parameterMap, is);
	}

	@Override
	public long importPortletInfoInBackground(java.lang.String taskName,
		long plid, long groupId, java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.importPortletInfoInBackground(taskName, plid,
			groupId, portletId, parameterMap, file);
	}

	@Override
	public long importPortletInfoInBackground(java.lang.String taskName,
		long plid, long groupId, java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.importPortletInfoInBackground(taskName, plid,
			groupId, portletId, parameterMap, is);
	}

	@Override
	public void importPortletInfoInBackground(java.lang.String taskName,
		java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.importPortletInfoInBackground(taskName, portletId,
			parameterMap, file);
	}

	@Override
	public void importPortletInfoInBackground(java.lang.String taskName,
		java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.importPortletInfoInBackground(taskName, portletId,
			parameterMap, is);
	}

	/**
	* Schedules a range of layouts to be published.
	*
	* @param sourceGroupId the primary key of the source group
	* @param targetGroupId the primary key of the target group
	* @param privateLayout whether the layout is private to the group
	* @param layoutIdMap the layouts considered for publishing, specified by
	the layout IDs and booleans indicating whether they have children
	* @param parameterMap the mapping of parameters indicating which
	information will be used. See {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}
	* @param scope the scope of the pages. It can be <code>all-pages</code> or
	<code>selected-pages</code>.
	* @param startDate the start date
	* @param endDate the end date
	* @param groupName the group name (optionally {@link
	com.liferay.portal.kernel.messaging.DestinationNames#LAYOUTS_LOCAL_PUBLISHER}).
	See {@link com.liferay.portal.kernel.messaging.DestinationNames}.
	* @param cronText the cron text. See {@link
	com.liferay.portal.kernel.cal.RecurrenceSerializer #toCronText}
	* @param schedulerStartDate the scheduler start date
	* @param schedulerEndDate the scheduler end date
	* @param description the scheduler description
	* @throws PortalException if the group did not have permission to manage
	and publish
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void schedulePublishToLive(long sourceGroupId, long targetGroupId,
		boolean privateLayout,
		java.util.Map<java.lang.Long, java.lang.Boolean> layoutIdMap,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.lang.String scope, java.util.Date startDate,
		java.util.Date endDate, java.lang.String groupName,
		java.lang.String cronText, java.util.Date schedulerStartDate,
		java.util.Date schedulerEndDate, java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.schedulePublishToLive(sourceGroupId, targetGroupId,
			privateLayout, layoutIdMap, parameterMap, scope, startDate,
			endDate, groupName, cronText, schedulerStartDate, schedulerEndDate,
			description);
	}

	/**
	* Schedules a range of layouts to be stored.
	*
	* @param sourceGroupId the primary key of the source group
	* @param privateLayout whether the layout is private to the group
	* @param layoutIdMap the layouts considered for publishing, specified by
	the layout IDs and booleans indicating whether they have children
	* @param parameterMap the mapping of parameters indicating which
	information will be used. See {@link
	com.liferay.portal.kernel.lar.PortletDataHandlerKeys}
	* @param remoteAddress the remote address
	* @param remotePort the remote port
	* @param remotePathContext the remote path context
	* @param secureConnection whether the connection is secure
	* @param remoteGroupId the primary key of the remote group
	* @param remotePrivateLayout whether remote group's layout is private
	* @param startDate the start date
	* @param endDate the end date
	* @param groupName the group name. Optionally {@link
	com.liferay.portal.kernel.messaging.DestinationNames#LAYOUTS_LOCAL_PUBLISHER}).
	See {@link com.liferay.portal.kernel.messaging.DestinationNames}.
	* @param cronText the cron text. See {@link
	com.liferay.portal.kernel.cal.RecurrenceSerializer #toCronText}
	* @param schedulerStartDate the scheduler start date
	* @param schedulerEndDate the scheduler end date
	* @param description the scheduler description
	* @throws PortalException if a group with the source group primary key was
	not found or if the group did not have permission to publish
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void schedulePublishToRemote(long sourceGroupId,
		boolean privateLayout,
		java.util.Map<java.lang.Long, java.lang.Boolean> layoutIdMap,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.lang.String remoteAddress, int remotePort,
		java.lang.String remotePathContext, boolean secureConnection,
		long remoteGroupId, boolean remotePrivateLayout,
		java.util.Date startDate, java.util.Date endDate,
		java.lang.String groupName, java.lang.String cronText,
		java.util.Date schedulerStartDate, java.util.Date schedulerEndDate,
		java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.schedulePublishToRemote(sourceGroupId, privateLayout,
			layoutIdMap, parameterMap, remoteAddress, remotePort,
			remotePathContext, secureConnection, remoteGroupId,
			remotePrivateLayout, startDate, endDate, groupName, cronText,
			schedulerStartDate, schedulerEndDate, description);
	}

	/**
	* Sets the layouts for the group, replacing and prioritizing all layouts of
	* the parent layout.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param parentLayoutId the primary key of the parent layout
	* @param layoutIds the primary keys of the layouts
	* @param serviceContext the service context to be applied
	* @throws PortalException if a group or layout with the primary key could
	not be found, if the group did not have permission to manage the
	layouts, if no layouts were specified, if the first layout was
	not page-able, if the first layout was hidden, or if some other
	portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void setLayouts(long groupId, boolean privateLayout,
		long parentLayoutId, long[] layoutIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.setLayouts(groupId, privateLayout, parentLayoutId,
			layoutIds, serviceContext);
	}

	/**
	* Deletes the job from the scheduler's queue.
	*
	* @param groupId the primary key of the group
	* @param jobName the job name
	* @param groupName the group name (optionally {@link
	com.liferay.portal.kernel.messaging.DestinationNames#LAYOUTS_LOCAL_PUBLISHER}).
	See {@link com.liferay.portal.kernel.messaging.DestinationNames}.
	* @throws PortalException if the group did not permission to manage staging
	and publish
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void unschedulePublishToLive(long groupId, java.lang.String jobName,
		java.lang.String groupName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.unschedulePublishToLive(groupId, jobName, groupName);
	}

	/**
	* Deletes the job from the scheduler's persistent queue.
	*
	* @param groupId the primary key of the group
	* @param jobName the job name
	* @param groupName the group name (optionally {@link
	com.liferay.portal.kernel.messaging.DestinationNames#LAYOUTS_LOCAL_PUBLISHER}).
	See {@link com.liferay.portal.kernel.messaging.DestinationNames}.
	* @throws PortalException if a group with the primary key could not be
	found or if the group did not have permission to publish
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void unschedulePublishToRemote(long groupId,
		java.lang.String jobName, java.lang.String groupName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutService.unschedulePublishToRemote(groupId, jobName, groupName);
	}

	/**
	* Updates the layout with additional parameters.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutId the primary key of the layout
	* @param parentLayoutId the primary key of the layout's new parent layout
	* @param localeNamesMap the layout's locales and localized names
	* @param localeTitlesMap the layout's locales and localized titles
	* @param descriptionMap the locales and localized descriptions to merge
	(optionally <code>null</code>)
	* @param keywordsMap the locales and localized keywords to merge
	(optionally <code>null</code>)
	* @param robotsMap the locales and localized robots to merge (optionally
	<code>null</code>)
	* @param type the layout's new type (optionally {@link
	com.liferay.portal.model.LayoutConstants#TYPE_PORTLET})
	* @param hidden whether the layout is hidden
	* @param friendlyURLMap the layout's locales and localized friendly URLs.
	To see how the URL is normalized when accessed see {@link
	com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	String)}.
	* @param iconImage whether the icon image will be updated
	* @param iconBytes the byte array of the layout's new icon image
	* @param serviceContext the service context to be applied. Can set the
	modification date and expando bridge attributes for the layout.
	* @return the updated layout
	* @throws PortalException if a group or layout with the primary key could
	not be found, if the user did not have permission to update the
	layout, if a unique friendly URL could not be generated, if a
	valid parent layout ID to use could not be found, or if the
	layout parameters were invalid
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout updateLayout(long groupId,
		boolean privateLayout, long layoutId, long parentLayoutId,
		java.util.Map<java.util.Locale, java.lang.String> localeNamesMap,
		java.util.Map<java.util.Locale, java.lang.String> localeTitlesMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Map<java.util.Locale, java.lang.String> keywordsMap,
		java.util.Map<java.util.Locale, java.lang.String> robotsMap,
		java.lang.String type, boolean hidden,
		java.util.Map<java.util.Locale, java.lang.String> friendlyURLMap,
		java.lang.Boolean iconImage, byte[] iconBytes,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updateLayout(groupId, privateLayout, layoutId,
			parentLayoutId, localeNamesMap, localeTitlesMap, descriptionMap,
			keywordsMap, robotsMap, type, hidden, friendlyURLMap, iconImage,
			iconBytes, serviceContext);
	}

	/**
	* Updates the layout with additional parameters.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutId the primary key of the layout
	* @param parentLayoutId the primary key of the layout's new parent
	layout
	* @param localeNamesMap the layout's locales and localized names
	* @param localeTitlesMap the layout's locales and localized titles
	* @param descriptionMap the locales and localized descriptions to
	merge (optionally <code>null</code>)
	* @param keywordsMap the locales and localized keywords to merge
	(optionally <code>null</code>)
	* @param robotsMap the locales and localized robots to merge
	(optionally <code>null</code>)
	* @param type the layout's new type (optionally {@link
	com.liferay.portal.model.LayoutConstants#TYPE_PORTLET})
	* @param hidden whether the layout is hidden
	* @param friendlyURL the layout's locales and new friendly URLs. To
	see how the URL is normalized when accessed, see {@link
	com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	String)}.
	* @param iconImage whether the icon image will be updated
	* @param iconBytes the byte array of the layout's new icon image
	* @param serviceContext the service context to be applied. Can set the
	modification date and expando bridge attributes for the
	layout.
	* @return the updated layout
	* @throws PortalException if a group or layout with the primary key
	could not be found, if the user did not have permission to
	update the layout, if a unique friendly URL could not be
	generated, if a valid parent layout ID to use could not be
	found, or if the layout parameters were invalid
	* @throws SystemException if a system exception occurred
	* @deprecated As of 6.2.0, replaced by {@link #updateLayout(long, boolean,
	long, long, Map, Map, Map, Map, Map, String, boolean, Map,
	Boolean, byte[], ServiceContext)}
	*/
	@Override
	public com.liferay.portal.model.Layout updateLayout(long groupId,
		boolean privateLayout, long layoutId, long parentLayoutId,
		java.util.Map<java.util.Locale, java.lang.String> localeNamesMap,
		java.util.Map<java.util.Locale, java.lang.String> localeTitlesMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Map<java.util.Locale, java.lang.String> keywordsMap,
		java.util.Map<java.util.Locale, java.lang.String> robotsMap,
		java.lang.String type, boolean hidden, java.lang.String friendlyURL,
		java.lang.Boolean iconImage, byte[] iconBytes,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updateLayout(groupId, privateLayout, layoutId,
			parentLayoutId, localeNamesMap, localeTitlesMap, descriptionMap,
			keywordsMap, robotsMap, type, hidden, friendlyURL, iconImage,
			iconBytes, serviceContext);
	}

	/**
	* Updates the layout replacing its type settings.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutId the primary key of the layout
	* @param typeSettings the settings to load the unicode properties object.
	See {@link com.liferay.portal.kernel.util.UnicodeProperties
	#fastLoad(String)}.
	* @return the updated layout
	* @throws PortalException if a matching layout could not be found or if the
	user did not have permission to update the layout
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout updateLayout(long groupId,
		boolean privateLayout, long layoutId, java.lang.String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updateLayout(groupId, privateLayout, layoutId,
			typeSettings);
	}

	/**
	* Updates the look and feel of the layout.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutId the primary key of the layout
	* @param themeId the primary key of the layout's new theme
	* @param colorSchemeId the primary key of the layout's new color scheme
	* @param css the layout's new CSS
	* @param wapTheme whether the theme is for WAP browsers
	* @return the updated layout
	* @throws PortalException if a matching layout could not be found, or if
	the user did not have permission to update the layout and
	permission to apply the theme
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout updateLookAndFeel(long groupId,
		boolean privateLayout, long layoutId, java.lang.String themeId,
		java.lang.String colorSchemeId, java.lang.String css, boolean wapTheme)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updateLookAndFeel(groupId, privateLayout,
			layoutId, themeId, colorSchemeId, css, wapTheme);
	}

	/**
	* Updates the name of the layout matching the group, layout ID, and
	* privacy.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutId the primary key of the layout
	* @param name the layout's new name
	* @param languageId the primary key of the language. For more information
	see {@link java.util.Locale}.
	* @return the updated layout
	* @throws PortalException if a matching layout could not be found, if the
	user did not have permission to update the layout, or if the new
	name was <code>null</code>
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout updateName(long groupId,
		boolean privateLayout, long layoutId, java.lang.String name,
		java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updateName(groupId, privateLayout, layoutId,
			name, languageId);
	}

	/**
	* Updates the name of the layout matching the primary key.
	*
	* @param plid the primary key of the layout
	* @param name the name to be assigned
	* @param languageId the primary key of the language. For more information
	see {@link java.util.Locale}.
	* @return the updated layout
	* @throws PortalException if a layout with the primary key could not be
	found, or if the user did not have permission to update the
	layout, or if the name was <code>null</code>
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout updateName(long plid,
		java.lang.String name, java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updateName(plid, name, languageId);
	}

	/**
	* Updates the parent layout ID of the layout matching the group, layout ID,
	* and privacy.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutId the primary key of the layout
	* @param parentLayoutId the primary key to be assigned to the parent
	layout
	* @return the matching layout
	* @throws PortalException if a valid parent layout ID to use could not be
	found, if a matching layout could not be found, or if the user
	did not have permission to update the layout
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout updateParentLayoutId(long groupId,
		boolean privateLayout, long layoutId, long parentLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updateParentLayoutId(groupId, privateLayout,
			layoutId, parentLayoutId);
	}

	/**
	* Updates the parent layout ID of the layout matching the primary key. If a
	* layout matching the parent primary key is found, the layout ID of that
	* layout is assigned, otherwise {@link
	* com.liferay.portal.model.LayoutConstants#DEFAULT_PARENT_LAYOUT_ID} is
	* assigned.
	*
	* @param plid the primary key of the layout
	* @param parentPlid the primary key of the parent layout
	* @return the layout matching the primary key
	* @throws PortalException if a layout with the primary key could not be
	found, if the user did not have permission to update the layout,
	or if a valid parent layout ID to use could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout updateParentLayoutId(long plid,
		long parentPlid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updateParentLayoutId(plid, parentPlid);
	}

	@Override
	public com.liferay.portal.model.Layout updateParentLayoutIdAndPriority(
		long plid, long parentPlid, int priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updateParentLayoutIdAndPriority(plid, parentPlid,
			priority);
	}

	/**
	* Updates the priority of the layout matching the group, layout ID, and
	* privacy.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutId the primary key of the layout
	* @param priority the layout's new priority
	* @return the updated layout
	* @throws PortalException if a matching layout could not be found or if the
	user did not have permission to update the layout
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout updatePriority(long groupId,
		boolean privateLayout, long layoutId, int priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updatePriority(groupId, privateLayout, layoutId,
			priority);
	}

	/**
	* Updates the priority of the layout matching the group, layout ID, and
	* privacy, setting the layout's priority based on the priorities of the
	* next and previous layouts.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout is private to the group
	* @param layoutId the primary key of the layout
	* @param nextLayoutId the primary key of the next layout
	* @param previousLayoutId the primary key of the previous layout
	* @return the updated layout
	* @throws PortalException if a matching layout could not be found or if the
	user did not have permission to update the layout
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout updatePriority(long groupId,
		boolean privateLayout, long layoutId, long nextLayoutId,
		long previousLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updatePriority(groupId, privateLayout, layoutId,
			nextLayoutId, previousLayoutId);
	}

	/**
	* Updates the priority of the layout matching the primary key.
	*
	* @param plid the primary key of the layout
	* @param priority the layout's new priority
	* @return the updated layout
	* @throws PortalException if a layout with the primary key could not be
	found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.Layout updatePriority(long plid,
		int priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.updatePriority(plid, priority);
	}

	@Override
	public com.liferay.portal.kernel.lar.MissingReferences validateImportLayoutsFile(
		long groupId, boolean privateLayout,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.validateImportLayoutsFile(groupId, privateLayout,
			parameterMap, file);
	}

	@Override
	public com.liferay.portal.kernel.lar.MissingReferences validateImportLayoutsFile(
		long groupId, boolean privateLayout,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.validateImportLayoutsFile(groupId, privateLayout,
			parameterMap, inputStream);
	}

	@Override
	public com.liferay.portal.kernel.lar.MissingReferences validateImportPortletInfo(
		long plid, long groupId, java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.validateImportPortletInfo(plid, groupId,
			portletId, parameterMap, file);
	}

	@Override
	public com.liferay.portal.kernel.lar.MissingReferences validateImportPortletInfo(
		long plid, long groupId, java.lang.String portletId,
		java.util.Map<java.lang.String, java.lang.String[]> parameterMap,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutService.validateImportPortletInfo(plid, groupId,
			portletId, parameterMap, inputStream);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public LayoutService getWrappedLayoutService() {
		return _layoutService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedLayoutService(LayoutService layoutService) {
		_layoutService = layoutService;
	}

	@Override
	public LayoutService getWrappedService() {
		return _layoutService;
	}

	@Override
	public void setWrappedService(LayoutService layoutService) {
		_layoutService = layoutService;
	}

	private LayoutService _layoutService;
}