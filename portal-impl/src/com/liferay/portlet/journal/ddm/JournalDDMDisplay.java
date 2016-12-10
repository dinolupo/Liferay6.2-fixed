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

package com.liferay.portlet.journal.ddm;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.util.BaseDDMDisplay;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.permission.JournalPermission;

import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Eduardo Garcia
 */
public class JournalDDMDisplay extends BaseDDMDisplay {

	@Override
	public String getAvailableFields() {
		return "Liferay.FormBuilder.AVAILABLE_FIELDS.WCM_STRUCTURE";
	}

	@Override
	public String getEditStructureDefaultValuesURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			DDMStructure structure, String redirectURL, String backURL)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			getControlPanelPlid(liferayPortletRequest), PortletKeys.JOURNAL,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("struts_action", "/journal/edit_article");
		portletURL.setParameter("redirect", redirectURL);
		portletURL.setParameter("backURL", backURL);
		portletURL.setParameter(
			"groupId", String.valueOf(structure.getGroupId()));
		portletURL.setParameter(
			"classNameId",
			String.valueOf(PortalUtil.getClassNameId(DDMStructure.class)));
		portletURL.setParameter(
			"classPK", String.valueOf(structure.getStructureId()));
		portletURL.setParameter("structureId", structure.getStructureKey());
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	@Override
	public String getEditTemplateBackURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classNameId,
			long classPK, String portletResource)
		throws Exception {

		String redirect = ParamUtil.getString(
			liferayPortletRequest, "redirect");

		if (Validator.isNull(redirect)) {
			return getViewTemplatesURL(
				liferayPortletRequest, liferayPortletResponse, classNameId,
				classPK);
		}

		return redirect;
	}

	@Override
	public String getPortletId() {
		return PortletKeys.JOURNAL;
	}

	@Override
	public String getResourceName() {
		return JournalPermission.RESOURCE_NAME;
	}

	@Override
	public String getStorageType() {
		return PropsValues.JOURNAL_ARTICLE_STORAGE_TYPE;
	}

	@Override
	public String getStructureType() {
		return JournalArticle.class.getName();
	}

	@Override
	public long getTemplateHandlerClassNameId(
		DDMTemplate template, long classNameId) {

		return PortalUtil.getClassNameId(JournalArticle.class);
	}

	@Override
	public Set<String> getTemplateLanguageTypes() {
		return _templateLanguageTypes;
	}

	@Override
	public String getTemplateType() {
		return DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY;
	}

	@Override
	public String getViewTemplatesBackURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classPK)
		throws Exception {

		if (classPK <= 0) {
			return StringPool.BLANK;
		}

		return super.getViewTemplatesBackURL(
			liferayPortletRequest, liferayPortletResponse, classPK);
	}

	@Override
	public Set<String> getViewTemplatesExcludedColumnNames() {
		return _viewTemplateExcludedColumnNames;
	}

	@Override
	public boolean isShowStructureSelector() {
		return true;
	}

	private static Set<String> _templateLanguageTypes =
		SetUtil.fromArray(
			new String[] {
				TemplateConstants.LANG_TYPE_FTL, TemplateConstants.LANG_TYPE_VM,
				TemplateConstants.LANG_TYPE_XSL
			});
	private static Set<String> _viewTemplateExcludedColumnNames =
		SetUtil.fromArray(new String[] {"mode"});

}