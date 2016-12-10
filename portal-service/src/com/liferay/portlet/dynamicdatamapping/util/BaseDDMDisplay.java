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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Eduardo Garcia
 */
public abstract class BaseDDMDisplay implements DDMDisplay {

	@Override
	public String getAddStructureActionId() {
		return ActionKeys.ADD_STRUCTURE;
	}

	@Override
	public String getAddTemplateActionId() {
		return ActionKeys.ADD_TEMPLATE;
	}

	@Override
	public String getAvailableFields() {
		return "Liferay.FormBuilder.AVAILABLE_FIELDS.DDM_STRUCTURE";
	}

	@Override
	public String getEditStructureDefaultValuesURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			DDMStructure structure, String redirectURL, String backURL)
		throws Exception {

		return null;
	}

	@Override
	public String getEditTemplateBackURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classNameId,
			long classPK, String portletResource)
		throws Exception {

		String redirect = ParamUtil.getString(
			liferayPortletRequest, "redirect");

		if (Validator.isNull(redirect) || Validator.isNull(portletResource)) {
			return getViewTemplatesURL(
				liferayPortletRequest, liferayPortletResponse, classNameId,
				classPK);
		}

		return redirect;
	}

	@Override
	public String getEditTemplateTitle(
		DDMStructure structure, DDMTemplate template, Locale locale) {

		if ((structure != null) && (template != null)) {
			StringBundler sb = new StringBundler(5);

			sb.append(template.getName(locale));
			sb.append(StringPool.SPACE);
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(structure.getName(locale));
			sb.append(StringPool.CLOSE_PARENTHESIS);

			return sb.toString();
		}
		else if (structure != null) {
			return LanguageUtil.format(
				locale, "new-template-for-structure-x",
				structure.getName(locale), false);
		}
		else if (template != null) {
			return template.getName(locale);
		}

		return getDefaultEditTemplateTitle(locale);
	}

	@Override
	public String getEditTemplateTitle(long classNameId, Locale locale) {
		if (classNameId > 0) {
			TemplateHandler templateHandler =
				TemplateHandlerRegistryUtil.getTemplateHandler(classNameId);

			if (templateHandler != null) {
				return LanguageUtil.get(locale, "new") + StringPool.SPACE +
					templateHandler.getName(locale);
			}
		}

		return getDefaultEditTemplateTitle(locale);
	}

	@Override
	public String getResourceName(long classNameId) {
		if (classNameId > 0) {
			TemplateHandler templateHandler =
				TemplateHandlerRegistryUtil.getTemplateHandler(classNameId);

			if (templateHandler != null) {
				return templateHandler.getResourceName();
			}
		}

		return getResourceName();
	}

	@Override
	public String getStorageType() {
		return StringPool.BLANK;
	}

	@Override
	public String getStructureName(Locale locale) {
		return LanguageUtil.get(locale, "structure");
	}

	@Override
	public String getStructureType() {
		return StringPool.BLANK;
	}

	@Override
	public long[] getTemplateClassNameIds(long classNameId) {
		if (classNameId > 0) {
			return new long[] {classNameId};
		}

		return TemplateHandlerRegistryUtil.getClassNameIds();
	}

	@Override
	public long[] getTemplateClassPKs(
			long companyId, long classNameId, long classPK)
		throws Exception {

		if (classPK > 0) {
			return new long[] {classPK};
		}

		List<Long> classPKs = new ArrayList<Long>();

		classPKs.add(0L);

		List<DDMStructure> structures =
			DDMStructureLocalServiceUtil.getClassStructures(
				companyId, PortalUtil.getClassNameId(getStructureType()));

		for (DDMStructure structure : structures) {
			classPKs.add(structure.getPrimaryKey());
		}

		return ArrayUtil.toLongArray(classPKs);
	}

	@Override
	public long[] getTemplateGroupIds(
			ThemeDisplay themeDisplay, boolean showGlobalScope)
		throws Exception {

		if (showGlobalScope) {
			return PortalUtil.getSiteAndCompanyGroupIds(themeDisplay);
		}

		return new long[] {themeDisplay.getScopeGroupId()};
	}

	@Override
	public long getTemplateHandlerClassNameId(
		DDMTemplate template, long classNameId) {

		if (template != null) {
			return template.getClassNameId();
		}

		return classNameId;
	}

	@Override
	public Set<String> getTemplateLanguageTypes() {
		return _templateLanguageTypes;
	}

	@Override
	public String getTemplateMode() {
		return StringPool.BLANK;
	}

	@Override
	public String getTemplateType() {
		return StringPool.BLANK;
	}

	@Override
	public String getTemplateType(DDMTemplate template, Locale locale) {
		return LanguageUtil.get(locale, template.getType());
	}

	@Override
	public String getViewTemplatesBackURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classPK)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			getControlPanelPlid(liferayPortletRequest),
			PortletKeys.DYNAMIC_DATA_MAPPING, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("struts_action", "/dynamic_data_mapping/view");

		return portletURL.toString();
	}

	@Override
	public Set<String> getViewTemplatesExcludedColumnNames() {
		return _viewTemplateExcludedColumnNames;
	}

	@Override
	public String getViewTemplatesTitle(
		DDMStructure structure, boolean controlPanel, Locale locale) {

		if (structure != null) {
			return LanguageUtil.format(
				locale, "templates-for-structure-x", structure.getName(locale),
				false);
		}

		return getDefaultViewTemplateTitle(locale);
	}

	@Override
	public String getViewTemplatesTitle(DDMStructure structure, Locale locale) {
		return getViewTemplatesTitle(structure, false, locale);
	}

	@Override
	public boolean isShowAddStructureButton() {
		String portletId = getPortletId();

		if (portletId.equals(PortletKeys.DYNAMIC_DATA_MAPPING)) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isShowStructureSelector() {
		return false;
	}

	protected long getControlPanelPlid(
			LiferayPortletRequest liferayPortletRequest)
		throws PortalException, SystemException {

		return PortalUtil.getControlPanelPlid(liferayPortletRequest);
	}

	protected String getDefaultEditTemplateTitle(Locale locale) {
		return LanguageUtil.get(locale, "new-template");
	}

	protected String getDefaultViewTemplateTitle(Locale locale) {
		return LanguageUtil.get(locale, "templates");
	}

	protected String getViewTemplatesURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classNameId,
			long classPK)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			getControlPanelPlid(liferayPortletRequest),
			PortletKeys.DYNAMIC_DATA_MAPPING, PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"struts_action", "/dynamic_data_mapping/view_template");
		portletURL.setParameter("classNameId", String.valueOf(classNameId));
		portletURL.setParameter("classPK", String.valueOf(classPK));

		return portletURL.toString();
	}

	private static Set<String> _templateLanguageTypes =
		SetUtil.fromArray(
			new String[] {
				TemplateConstants.LANG_TYPE_FTL, TemplateConstants.LANG_TYPE_VM
			});
	private static Set<String> _viewTemplateExcludedColumnNames =
		SetUtil.fromArray(new String[] {"structure"});

}