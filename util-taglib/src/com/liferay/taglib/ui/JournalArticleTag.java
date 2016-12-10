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

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 */
public class JournalArticleTag extends IncludeTag {

	public void setArticleId(String articleId) {
		_articleId = articleId;
	}

	public void setArticlePage(int articlePage) {
		_articlePage = articlePage;
	}

	public void setArticleResourcePrimKey(long articleResourcePrimKey) {
		_articleResourcePrimKey = articleResourcePrimKey;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	public void setShowAvailableLocales(boolean showAvailableLocales) {
		_showAvailableLocales = showAvailableLocales;
	}

	public void setShowTitle(boolean showTitle) {
		_showTitle = showTitle;
	}

	public void setTemplateId(String templateId) {
		_templateId = templateId;
	}

	public void setXmlRequest(String xmlRequest) {
		_xmlRequest = xmlRequest;
	}

	@Override
	protected void cleanUp() {
		_articleId = null;
		_articlePage = 1;
		_articleResourcePrimKey = 0;
		_groupId = 0;
		_languageId = null;
		_showAvailableLocales = false;
		_showTitle = false;
		_templateId = null;
		_xmlRequest = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:journal-article:articleId", _articleId);
		request.setAttribute(
			"liferay-ui:journal-article:articlePage",
			String.valueOf(_articlePage));
		request.setAttribute(
			"liferay-ui:journal-article:articleResourcePrimKey",
			String.valueOf(_articleResourcePrimKey));
		request.setAttribute(
			"liferay-ui:journal-article:groupId", String.valueOf(_groupId));
		request.setAttribute(
			"liferay-ui:journal-article:languageId", _languageId);
		request.setAttribute(
			"liferay-ui:journal-article:showAvailableLocales",
			String.valueOf(_showAvailableLocales));
		request.setAttribute(
			"liferay-ui:journal-article:showTitle", String.valueOf(_showTitle));
		request.setAttribute(
			"liferay-ui:journal-article:templateId", _templateId);
		request.setAttribute(
			"liferay-ui:journal-article:xmlRequest", _xmlRequest);
	}

	private static final String _PAGE =
		"/html/taglib/ui/journal_article/page.jsp";

	private String _articleId;
	private int _articlePage = 1;
	private long _articleResourcePrimKey;
	private long _groupId;
	private String _languageId;
	private boolean _showAvailableLocales;
	private boolean _showTitle;
	private String _templateId;
	private String _xmlRequest;

}