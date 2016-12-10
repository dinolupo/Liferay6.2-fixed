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

package com.liferay.portlet.dynamicdatamapping.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletURLImpl;
import com.liferay.portlet.dynamicdatamapping.NoSuchTemplateException;
import com.liferay.portlet.dynamicdatamapping.RequiredTemplateException;
import com.liferay.portlet.dynamicdatamapping.TemplateNameException;
import com.liferay.portlet.dynamicdatamapping.TemplateScriptException;
import com.liferay.portlet.dynamicdatamapping.TemplateSmallImageNameException;
import com.liferay.portlet.dynamicdatamapping.TemplateSmallImageSizeException;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateServiceUtil;

import java.io.File;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Eduardo Lundgren
 */
public class EditTemplateAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		DDMTemplate template = null;

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				template = updateTemplate(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteTemplates(actionRequest);
			}

			if (Validator.isNotNull(cmd)) {
				String redirect = ParamUtil.getString(
					actionRequest, "redirect");
				String closeRedirect = ParamUtil.getString(
					actionRequest, "closeRedirect");

				if (Validator.isNotNull(closeRedirect)) {
					redirect = HttpUtil.setParameter(
						redirect, "closeRedirect", closeRedirect);

					SessionMessages.add(
						actionRequest,
						PortalUtil.getPortletId(actionRequest) +
							SessionMessages.KEY_SUFFIX_CLOSE_REDIRECT,
						closeRedirect);
				}

				if (template != null) {
					boolean saveAndContinue = ParamUtil.getBoolean(
						actionRequest, "saveAndContinue");

					if (saveAndContinue) {
						redirect = getSaveAndContinueRedirect(
							portletConfig, actionRequest, template, redirect);
					}
				}

				sendRedirect(actionRequest, actionResponse, redirect);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchTemplateException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				setForward(actionRequest, "portlet.dynamic_data_mapping.error");
			}
			else if (e instanceof RequiredTemplateException ||
					 e instanceof TemplateNameException ||
					 e instanceof TemplateScriptException ||
					 e instanceof TemplateSmallImageNameException ||
					 e instanceof TemplateSmallImageSizeException) {

				SessionErrors.add(actionRequest, e.getClass(), e);

				if (e instanceof RequiredTemplateException) {
					String redirect = PortalUtil.escapeRedirect(
						ParamUtil.getString(actionRequest, "redirect"));

					if (Validator.isNotNull(redirect)) {
						actionResponse.sendRedirect(redirect);
					}
				}
			}
			else {
				throw e;
			}
		}
	}

	@Override
	public ActionForward render(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		try {
			ActionUtil.getStructure(renderRequest);
			ActionUtil.getTemplate(renderRequest);
		}
		catch (Exception e) {
			if (e instanceof NoSuchTemplateException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return actionMapping.findForward(
					"portlet.dynamic_data_mapping.error");
			}
			else {
				throw e;
			}
		}

		return actionMapping.findForward(
			getForward(
				renderRequest, "portlet.dynamic_data_mapping.edit_template"));
	}

	protected void deleteTemplates(ActionRequest actionRequest)
		throws Exception {

		long[] deleteTemplateIds = null;

		long templateId = ParamUtil.getLong(actionRequest, "templateId");

		if (templateId > 0) {
			deleteTemplateIds = new long[] {templateId};
		}
		else {
			deleteTemplateIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "deleteTemplateIds"), 0L);
		}

		for (long deleteTemplateId : deleteTemplateIds) {
			DDMTemplateServiceUtil.deleteTemplate(deleteTemplateId);
		}
	}

	protected String getSaveAndContinueRedirect(
			PortletConfig portletConfig, ActionRequest actionRequest,
			DDMTemplate template, String redirect)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletResourceNamespace = ParamUtil.getString(
			actionRequest, "portletResourceNamespace");
		long classNameId = ParamUtil.getLong(actionRequest, "classNameId");
		long classPK = ParamUtil.getLong(actionRequest, "classPK");
		String structureAvailableFields = ParamUtil.getString(
			actionRequest, "structureAvailableFields");

		PortletURLImpl portletURL = new PortletURLImpl(
			actionRequest, portletConfig.getPortletName(),
			themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		portletURL.setParameter(Constants.CMD, Constants.UPDATE, false);
		portletURL.setParameter(
			"struts_action", "/dynamic_data_mapping/edit_template");
		portletURL.setParameter("redirect", redirect, false);
		portletURL.setParameter(
			"portletResourceNamespace", portletResourceNamespace, false);
		portletURL.setParameter(
			"templateId", String.valueOf(template.getTemplateId()), false);
		portletURL.setParameter(
			"groupId", String.valueOf(template.getGroupId()), false);
		portletURL.setParameter(
			"classNameId", String.valueOf(classNameId), false);
		portletURL.setParameter("classPK", String.valueOf(classPK), false);
		portletURL.setParameter("type", template.getType(), false);
		portletURL.setParameter(
			"structureAvailableFields", structureAvailableFields, false);
		portletURL.setWindowState(actionRequest.getWindowState());

		return portletURL.toString();
	}

	protected String getScript(UploadPortletRequest uploadPortletRequest)
		throws Exception {

		String scriptContent = ParamUtil.getString(
			uploadPortletRequest, "scriptContent");

		File file = uploadPortletRequest.getFile("script");

		if (file == null) {
			return scriptContent;
		}

		String script = FileUtil.read(file);

		if (Validator.isNotNull(script) && !isValidFile(file)) {
			throw new TemplateScriptException();
		}

		return GetterUtil.getString(script, scriptContent);
	}

	protected boolean isValidFile(File file) {
		String contentType = MimeTypesUtil.getContentType(file);

		if (contentType.equals(ContentTypes.APPLICATION_XSLT_XML) ||
			contentType.startsWith(ContentTypes.TEXT)) {

			return true;
		}

		return false;
	}

	protected DDMTemplate updateTemplate(ActionRequest actionRequest)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long templateId = ParamUtil.getLong(uploadPortletRequest, "templateId");

		long groupId = ParamUtil.getLong(uploadPortletRequest, "groupId");
		long classNameId = ParamUtil.getLong(
			uploadPortletRequest, "classNameId");
		long classPK = ParamUtil.getLong(uploadPortletRequest, "classPK");
		String templateKey = ParamUtil.getString(
			uploadPortletRequest, "templateKey");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			uploadPortletRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(
				uploadPortletRequest, "description");
		String type = ParamUtil.getString(uploadPortletRequest, "type");
		String mode = ParamUtil.getString(uploadPortletRequest, "mode");
		String language = ParamUtil.getString(
			uploadPortletRequest, "language", TemplateConstants.LANG_TYPE_VM);

		String script = getScript(uploadPortletRequest);

		boolean cacheable = ParamUtil.getBoolean(
			uploadPortletRequest, "cacheable");
		boolean smallImage = ParamUtil.getBoolean(
			uploadPortletRequest, "smallImage");
		String smallImageURL = ParamUtil.getString(
			uploadPortletRequest, "smallImageURL");
		File smallImageFile = uploadPortletRequest.getFile("smallImageFile");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDMTemplate.class.getName(), uploadPortletRequest);

		DDMTemplate template = null;

		if (templateId <= 0) {
			template = DDMTemplateServiceUtil.addTemplate(
				groupId, classNameId, classPK, templateKey, nameMap,
				descriptionMap, type, mode, language, script, cacheable,
				smallImage, smallImageURL, smallImageFile, serviceContext);
		}
		else {
			template = DDMTemplateServiceUtil.updateTemplate(
				templateId, classPK, nameMap, descriptionMap, type, mode,
				language, script, cacheable, smallImage, smallImageURL,
				smallImageFile, serviceContext);
		}

		PortletPreferences portletPreferences = getStrictPortletSetup(
			actionRequest);

		if (portletPreferences != null) {
			if (type.equals(DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY)) {
				portletPreferences.setValue(
					"displayDDMTemplateId",
					String.valueOf(template.getTemplateId()));
			}
			else {
				portletPreferences.setValue(
					"formDDMTemplateId",
					String.valueOf(template.getTemplateId()));
			}

			portletPreferences.store();
		}

		return template;
	}

}