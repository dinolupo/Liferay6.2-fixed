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

package com.liferay.portlet.journal.action;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.util.DDMUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleConstants;
import com.liferay.portlet.journal.model.JournalFeed;
import com.liferay.portlet.journal.model.JournalFolder;
import com.liferay.portlet.journal.model.JournalFolderConstants;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalArticleServiceUtil;
import com.liferay.portlet.journal.service.JournalFeedServiceUtil;
import com.liferay.portlet.journal.service.JournalFolderServiceUtil;
import com.liferay.portlet.journal.service.permission.JournalPermission;
import com.liferay.portlet.journal.util.JournalConverterUtil;
import com.liferay.portlet.journal.util.JournalUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionUtil {

	public static void deleteArticle(
			ActionRequest actionRequest, String deleteArticleId)
		throws Exception {

		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		String articleId = deleteArticleId;
		String articleURL = ParamUtil.getString(actionRequest, "articleURL");
		double version = 0;

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			JournalArticle.class.getName(), actionRequest);

		int pos = deleteArticleId.lastIndexOf(
			EditArticleAction.VERSION_SEPARATOR);

		if (pos == -1) {
			JournalArticleServiceUtil.deleteArticle(
				groupId, articleId, articleURL, serviceContext);
		}
		else {
			articleId = articleId.substring(0, pos);
			version = GetterUtil.getDouble(
				deleteArticleId.substring(
					pos + EditArticleAction.VERSION_SEPARATOR.length()));

			JournalArticleServiceUtil.deleteArticle(
				groupId, articleId, version, articleURL, serviceContext);
		}

		JournalUtil.removeRecentArticle(actionRequest, articleId, version);
	}

	public static void expireArticle(
			ActionRequest actionRequest, String expireArticleId)
		throws Exception {

		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		String articleId = expireArticleId;
		String articleURL = ParamUtil.getString(actionRequest, "articleURL");
		double version = 0;

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			JournalArticle.class.getName(), actionRequest);

		int pos = expireArticleId.lastIndexOf(
			EditArticleAction.VERSION_SEPARATOR);

		if (pos == -1) {
			JournalArticleServiceUtil.expireArticle(
				groupId, articleId, articleURL, serviceContext);
		}
		else {
			articleId = articleId.substring(0, pos);
			version = GetterUtil.getDouble(
				expireArticleId.substring(
					pos + EditArticleAction.VERSION_SEPARATOR.length()));

			JournalArticleServiceUtil.expireArticle(
				groupId, articleId, version, articleURL, serviceContext);
		}

		JournalUtil.removeRecentArticle(actionRequest, articleId, version);
	}

	public static void expireFolder(
			long groupId, long parentFolderId, ServiceContext serviceContext)
		throws Exception {

		List<JournalFolder> folders = JournalFolderServiceUtil.getFolders(
			groupId, parentFolderId);

		for (JournalFolder folder : folders) {
			expireFolder(groupId, folder.getFolderId(), serviceContext);
		}

		List<JournalArticle> articles = JournalArticleServiceUtil.getArticles(
			groupId, parentFolderId);

		for (JournalArticle article : articles) {
			JournalArticleServiceUtil.expireArticle(
				groupId, article.getArticleId(), null, serviceContext);
		}
	}

	public static void getArticle(HttpServletRequest request) throws Exception {
		String cmd = ParamUtil.getString(request, Constants.CMD);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
		long groupId = ParamUtil.getLong(
			request, "groupId", themeDisplay.getScopeGroupId());
		long classNameId = ParamUtil.getLong(request, "classNameId");
		long classPK = ParamUtil.getLong(request, "classPK");
		String articleId = ParamUtil.getString(request, "articleId");
		String structureId = ParamUtil.getString(request, "structureId");
		int status = ParamUtil.getInteger(
			request, "status", WorkflowConstants.STATUS_ANY);

		JournalArticle article = null;

		if (cmd.equals(Constants.ADD) && (resourcePrimKey != 0)) {
			article = JournalArticleLocalServiceUtil.getLatestArticle(
				resourcePrimKey, status, false);
		}
		else if (!cmd.equals(Constants.ADD) && Validator.isNotNull(articleId)) {
			article = JournalArticleServiceUtil.getLatestArticle(
				groupId, articleId, status);
		}
		else if ((classNameId > 0) &&
				 (classPK > JournalArticleConstants.CLASSNAME_ID_DEFAULT)) {

			String className = PortalUtil.getClassName(classNameId);

			article = JournalArticleServiceUtil.getLatestArticle(
				groupId, className, classPK);
		}
		else if (Validator.isNotNull(structureId)) {
			DDMStructure ddmStructure = DDMStructureServiceUtil.fetchStructure(
				groupId, PortalUtil.getClassNameId(JournalArticle.class),
				structureId, true);

			if (ddmStructure == null) {
				return;
			}

			article = JournalArticleServiceUtil.getArticle(
				ddmStructure.getGroupId(), DDMStructure.class.getName(),
				ddmStructure.getStructureId());

			article.setNew(true);

			article.setId(0);
			article.setGroupId(groupId);
			article.setClassNameId(
				JournalArticleConstants.CLASSNAME_ID_DEFAULT);
			article.setClassPK(0);
			article.setArticleId(null);
			article.setVersion(0);
		}

		request.setAttribute(WebKeys.JOURNAL_ARTICLE, article);
	}

	public static void getArticle(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getArticle(request);

		JournalArticle article = (JournalArticle)portletRequest.getAttribute(
			WebKeys.JOURNAL_ARTICLE);

		JournalUtil.addRecentArticle(portletRequest, article);
	}

	public static void getArticles(HttpServletRequest request)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<JournalArticle> articles = new ArrayList<JournalArticle>();

		String[] articleIds = StringUtil.split(
			ParamUtil.getString(request, "articleIds"));

		for (String articleId : articleIds) {
			JournalArticle article = JournalArticleServiceUtil.fetchArticle(
				themeDisplay.getScopeGroupId(), articleId);

			if (article != null) {
				articles.add(article);
			}
		}

		request.setAttribute(WebKeys.JOURNAL_ARTICLES, articles);
	}

	public static void getArticles(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getArticles(request);
	}

	public static Object[] getContentAndImages(
			DDMStructure ddmStructure, Locale locale,
			ServiceContext serviceContext)
		throws Exception {

		Fields fields = DDMUtil.getFields(
			ddmStructure.getStructureId(), serviceContext);

		Map<String, byte[]> images = getImages(fields, locale);

		return new Object[] {
			JournalConverterUtil.getContent(ddmStructure, fields), images
		};
	}

	public static void getFeed(HttpServletRequest request) throws Exception {
		long groupId = ParamUtil.getLong(request, "groupId");
		String feedId = ParamUtil.getString(request, "feedId");

		JournalFeed feed = null;

		if (Validator.isNotNull(feedId)) {
			feed = JournalFeedServiceUtil.getFeed(groupId, feedId);
		}

		request.setAttribute(WebKeys.JOURNAL_FEED, feed);
	}

	public static void getFeed(PortletRequest portletRequest) throws Exception {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getFeed(request);
	}

	public static void getFolder(HttpServletRequest request) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long folderId = ParamUtil.getLong(request, "folderId");

		JournalFolder folder = null;

		if ((folderId > 0) &&
			(folderId != JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID)) {

			folder = JournalFolderServiceUtil.getFolder(folderId);
		}
		else {
			JournalPermission.check(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), ActionKeys.VIEW);
		}

		request.setAttribute(WebKeys.JOURNAL_FOLDER, folder);
	}

	public static void getFolder(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getFolder(request);
	}

	public static void getFolders(HttpServletRequest request) throws Exception {
		long[] folderIds = StringUtil.split(
			ParamUtil.getString(request, "folderIds"), 0L);

		List<JournalFolder> folders = new ArrayList<JournalFolder>();

		for (long folderId : folderIds) {
			JournalFolder folder = JournalFolderServiceUtil.fetchFolder(
				folderId);

			if (folder != null) {
				folders.add(folder);
			}
		}

		request.setAttribute(WebKeys.JOURNAL_FOLDERS, folders);
	}

	public static void getFolders(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getFolders(request);
	}

	public static void getStructure(HttpServletRequest request)
		throws Exception {

		long groupId = ParamUtil.getLong(request, "groupId");
		long classNameId = ParamUtil.getLong(request, "classNameId");
		String structureId = ParamUtil.getString(request, "structureId");

		DDMStructure ddmStructure = null;

		if (Validator.isNotNull(structureId)) {
			ddmStructure = DDMStructureServiceUtil.getStructure(
				groupId, classNameId, structureId);
		}

		request.setAttribute(WebKeys.JOURNAL_STRUCTURE, ddmStructure);
	}

	public static void getStructure(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getStructure(request);

		DDMStructure ddmStructure = (DDMStructure)portletRequest.getAttribute(
			WebKeys.JOURNAL_STRUCTURE);

		JournalUtil.addRecentDDMStructure(portletRequest, ddmStructure);
	}

	public static void getTemplate(HttpServletRequest request)
		throws Exception {

		long groupId = ParamUtil.getLong(request, "groupId");
		String templateId = ParamUtil.getString(request, "templateId");

		DDMTemplate ddmTemplate = null;

		if (Validator.isNotNull(templateId)) {
			ddmTemplate = DDMTemplateServiceUtil.getTemplate(
				groupId, PortalUtil.getClassNameId(DDMStructure.class),
				templateId, true);
		}

		request.setAttribute(WebKeys.JOURNAL_TEMPLATE, ddmTemplate);
	}

	public static void getTemplate(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getTemplate(request);

		DDMTemplate ddmTemplate = (DDMTemplate)portletRequest.getAttribute(
			WebKeys.JOURNAL_TEMPLATE);

		JournalUtil.addRecentDDMTemplate(portletRequest, ddmTemplate);
	}

	protected static Map<String, byte[]> getImages(Fields fields, Locale locale)
		throws Exception {

		Map<String, byte[]> images = new HashMap<String, byte[]>();

		for (Field field : fields) {
			String dataType = field.getDataType();

			if (!dataType.equals(FieldConstants.IMAGE)) {
				continue;
			}

			List<Serializable> values = field.getValues(locale);

			for (int i = 0; i < values.size(); i++) {
				StringBundler sb = new StringBundler(6);

				sb.append(StringPool.UNDERLINE);
				sb.append(field.getName());
				sb.append(StringPool.UNDERLINE);
				sb.append(i);
				sb.append(StringPool.UNDERLINE);
				sb.append(LanguageUtil.getLanguageId(locale));

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					(String)values.get(i));

				String content = jsonObject.getString("data");

				images.put(sb.toString(), UnicodeFormatter.hexToBytes(content));
			}
		}

		return images;
	}

	protected static boolean hasArticle(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String articleId = ParamUtil.getString(actionRequest, "articleId");

		if (Validator.isNull(articleId)) {
			String[] articleIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "articleIds"));

			if (articleIds.length <= 0) {
				return false;
			}

			articleId = articleIds[0];
		}

		int pos = articleId.lastIndexOf(EditArticleAction.VERSION_SEPARATOR);

		if (pos != -1) {
			articleId = articleId.substring(0, pos);
		}

		JournalArticle article = JournalArticleLocalServiceUtil.fetchArticle(
			themeDisplay.getScopeGroupId(), articleId);

		if (article == null) {
			return false;
		}

		return true;
	}

}