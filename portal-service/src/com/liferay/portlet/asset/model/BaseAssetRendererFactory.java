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

package com.liferay.portlet.asset.model;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

/**
 * @author Jorge Ferrer
 * @author Juan Fernández
 * @author Raymond Augé
 * @author Sergio González
 */
public abstract class BaseAssetRendererFactory implements AssetRendererFactory {

	@Override
	public AssetEntry getAssetEntry(long assetEntryId)
		throws PortalException, SystemException {

		return AssetEntryLocalServiceUtil.getEntry(assetEntryId);
	}

	@Override
	public AssetEntry getAssetEntry(String className, long classPK)
		throws PortalException, SystemException {

		return AssetEntryLocalServiceUtil.getEntry(className, classPK);
	}

	@Override
	public AssetRenderer getAssetRenderer(long classPK)
		throws PortalException, SystemException {

		return getAssetRenderer(classPK, TYPE_LATEST_APPROVED);
	}

	@Override
	@SuppressWarnings("unused")
	public AssetRenderer getAssetRenderer(long groupId, String urlTitle)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public long getClassNameId() {
		return PortalUtil.getClassNameId(_className);
	}

	@Override
	public List<Tuple> getClassTypeFieldNames(
			long classTypeId, Locale locale, int start, int end)
		throws Exception {

		return Collections.emptyList();
	}

	@Override
	public int getClassTypeFieldNamesCount(long classTypeId, Locale locale)
		throws Exception {

		return 0;
	}

	@Override
	public Map<Long, String> getClassTypes(long[] groupId, Locale locale)
		throws Exception {

		return Collections.emptyMap();
	}

	@Override
	public String getIconPath(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return getIconPath(themeDisplay);
	}

	@Override
	public String getPortletId() {
		return _portletId;
	}

	@Override
	public String getTypeName(Locale locale, boolean hasSubtypes) {
		return ResourceActionsUtil.getModelResource(locale, getClassName());
	}

	@Override
	@SuppressWarnings("unused")
	public PortletURL getURLAdd(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	@SuppressWarnings("unused")
	public PortletURL getURLView(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public boolean hasAddPermission(
			PermissionChecker permissionChecker, long groupId, long classTypeId)
		throws Exception {

		return false;
	}

	@Override
	public boolean hasClassTypeFieldNames(long classTypeId, Locale locale)
		throws Exception {

		List<Tuple> classTypeFieldNames = getClassTypeFieldNames(
			classTypeId, locale, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		return !classTypeFieldNames.isEmpty();
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return _PERMISSION;
	}

	@Override
	public boolean isActive(long companyId) {
		if (Validator.isNull(getPortletId())) {
			return true;
		}

		Portlet portlet = null;

		try {
			portlet = PortletLocalServiceUtil.getPortletById(
				companyId, getPortletId());
		}
		catch (SystemException se) {
			portlet = PortletLocalServiceUtil.getPortletById(getPortletId());
		}

		if (portlet == null) {
			return false;
		}

		return portlet.isActive();
	}

	@Override
	public boolean isCategorizable() {
		return true;
	}

	@Override
	public boolean isLinkable() {
		return _LINKABLE;
	}

	@Override
	public boolean isListable(long classPK) throws SystemException {
		return true;
	}

	@Override
	public boolean isSelectable() {
		return _SELECTABLE;
	}

	@Override
	public void setClassName(String className) {
		_className = className;
	}

	@Override
	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	protected long getControlPanelPlid(ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		return PortalUtil.getControlPanelPlid(themeDisplay.getCompanyId());
	}

	protected List<Tuple> getDDMStructureFieldNames(
			DDMStructure ddmStructure, Locale locale)
		throws Exception {

		List<Tuple> fields = new ArrayList<Tuple>();

		Map<String, Map<String, String>> fieldsMap = ddmStructure.getFieldsMap(
			LocaleUtil.toLanguageId(locale));

		for (Map<String, String> fieldMap : fieldsMap.values()) {
			String indexType = fieldMap.get("indexType");
			boolean privateField = GetterUtil.getBoolean(
				fieldMap.get("private"));

			String type = fieldMap.get("type");

			if (Validator.isNull(indexType) || privateField ||
				!ArrayUtil.contains(_SELECTABLE_DDM_STRUCTURE_FIELDS, type)) {

				continue;
			}

			String label = fieldMap.get("label");
			String name = fieldMap.get("name");

			fields.add(
				new Tuple(label, name, type, ddmStructure.getStructureId()));
		}

		return fields;
	}

	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/page.png";
	}

	private static final boolean _LINKABLE = false;

	private static final boolean _PERMISSION = true;

	private static final boolean _SELECTABLE = true;

	private static final String[] _SELECTABLE_DDM_STRUCTURE_FIELDS = {
		"checkbox", "ddm-date", "ddm-decimal", "ddm-integer", "ddm-number",
		"radio", "select", "text"
	};

	private String _className;
	private String _portletId;

}