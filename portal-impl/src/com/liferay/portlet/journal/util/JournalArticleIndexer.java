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

package com.liferay.portlet.journal.util;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.dynamicdatamapping.StructureFieldException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.util.DDMIndexer;
import com.liferay.portlet.dynamicdatamapping.util.DDMIndexerUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleDisplay;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.permission.JournalArticlePermission;
import com.liferay.portlet.journal.service.persistence.JournalArticleActionableDynamicQuery;
import com.liferay.portlet.trash.util.TrashUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 * @author Harry Mark
 * @author Bruno Farache
 * @author Raymond Augé
 * @author Hugo Huijser
 * @author Tibor Lipusz
 */
public class JournalArticleIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {JournalArticle.class.getName()};

	public static boolean JOURNAL_ARTICLE_INDEX_ALL_VERSIONS =
		GetterUtil.getBoolean(
			PropsUtil.get("journal.articles.index.all.versions"));

	public static final String PORTLET_ID = PortletKeys.JOURNAL;

	public JournalArticleIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getPortletId() {
		return PORTLET_ID;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId)
		throws Exception {

		return JournalArticlePermission.contains(
			permissionChecker, entryClassPK, ActionKeys.VIEW);
	}

	@Override
	public boolean isVisible(long classPK, int status) throws Exception {
		List<JournalArticle> articles =
			JournalArticleLocalServiceUtil.getArticlesByResourcePrimKey(
				classPK);

		for (JournalArticle article : articles) {
			if (isVisible(article.getStatus(), status)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		Long classNameId = (Long)searchContext.getAttribute(
			Field.CLASS_NAME_ID);

		if ((classNameId != null) && (classNameId.longValue() != 0)) {
			contextQuery.addRequiredTerm("classNameId", classNameId.toString());
		}

		addStatus(contextQuery, searchContext);

		addSearchClassTypeIds(contextQuery, searchContext);

		String ddmStructureFieldName = (String)searchContext.getAttribute(
			"ddmStructureFieldName");
		Serializable ddmStructureFieldValue = searchContext.getAttribute(
			"ddmStructureFieldValue");

		if (Validator.isNotNull(ddmStructureFieldName) &&
			Validator.isNotNull(ddmStructureFieldValue)) {

			String[] ddmStructureFieldNameParts = StringUtil.split(
				ddmStructureFieldName, DDMIndexer.DDM_FIELD_SEPARATOR);

			DDMStructure structure = DDMStructureLocalServiceUtil.getStructure(
				GetterUtil.getLong(ddmStructureFieldNameParts[1]));

			String fieldName = StringUtil.replaceLast(
				ddmStructureFieldNameParts[2],
				StringPool.UNDERLINE.concat(
					LocaleUtil.toLanguageId(searchContext.getLocale())),
				StringPool.BLANK);

			try {
				ddmStructureFieldValue = DDMUtil.getIndexedFieldValue(
					ddmStructureFieldValue, structure.getFieldType(fieldName));
			}
			catch (StructureFieldException sfe) {
			}

			contextQuery.addRequiredTerm(
				ddmStructureFieldName,
				StringPool.QUOTE + ddmStructureFieldValue + StringPool.QUOTE);
		}

		String articleType = (String)searchContext.getAttribute("articleType");

		if (Validator.isNotNull(articleType)) {
			contextQuery.addRequiredTerm(Field.TYPE, articleType);
		}

		String ddmStructureKey = (String)searchContext.getAttribute(
			"ddmStructureKey");

		if (Validator.isNotNull(ddmStructureKey)) {
			contextQuery.addRequiredTerm("ddmStructureKey", ddmStructureKey);
		}

		String ddmTemplateKey = (String)searchContext.getAttribute(
			"ddmTemplateKey");

		if (Validator.isNotNull(ddmTemplateKey)) {
			contextQuery.addRequiredTerm("ddmTemplateKey", ddmTemplateKey);
		}
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, Field.CLASS_PK, false);
		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.CONTENT, false);
		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.DESCRIPTION, false);
		addSearchTerm(searchQuery, searchContext, Field.ENTRY_CLASS_PK, false);
		addSearchLocalizedTerm(searchQuery, searchContext, Field.TITLE, false);
		addSearchTerm(searchQuery, searchContext, Field.TYPE, false);
		addSearchTerm(searchQuery, searchContext, Field.USER_NAME, false);

		addSearchTerm(searchQuery, searchContext, "articleId", false);

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String)params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	protected void addDDMStructureAttributes(
			Document document, JournalArticle article)
		throws Exception {

		if (Validator.isNull(article.getStructureId())) {
			return;
		}

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
			article.getGroupId(),
			PortalUtil.getClassNameId(JournalArticle.class),
			article.getStructureId(), true);

		if (ddmStructure == null) {
			return;
		}

		document.addKeyword(Field.CLASS_TYPE_ID, ddmStructure.getStructureId());

		Fields fields = null;

		try {
			fields = JournalConverterUtil.getDDMFields(
				ddmStructure, article.getContent());
		}
		catch (Exception e) {
			return;
		}

		if (fields != null) {
			DDMIndexerUtil.addAttributes(document, ddmStructure, fields);
		}
	}

	@Override
	protected void addSearchLocalizedTerm(
			BooleanQuery searchQuery, SearchContext searchContext, String field,
			boolean like)
		throws Exception {

		if (Validator.isNull(field)) {
			return;
		}

		String value = String.valueOf(searchContext.getAttribute(field));

		if (Validator.isNull(value)) {
			value = searchContext.getKeywords();
		}

		if (Validator.isNull(value)) {
			return;
		}

		String localizedField = DocumentImpl.getLocalizedName(
			searchContext.getLocale(), field);

		if (Validator.isNull(searchContext.getKeywords())) {
			BooleanQuery localizedQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			localizedQuery.addTerm(field, value, like);
			localizedQuery.addTerm(localizedField, value, like);

			BooleanClauseOccur booleanClauseOccur = BooleanClauseOccur.SHOULD;

			if (searchContext.isAndSearch()) {
				booleanClauseOccur = BooleanClauseOccur.MUST;
			}

			searchQuery.add(localizedQuery, booleanClauseOccur);
		}
		else {
			searchQuery.addTerm(localizedField, value, like);
		}
	}

	@Override
	protected void addStatus(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		boolean showNonindexable = false;

		if (params != null) {
			showNonindexable = GetterUtil.getBoolean(
				params.get("showNonindexable"));
		}

		super.addStatus(contextQuery, searchContext);

		boolean head = GetterUtil.getBoolean(
			searchContext.getAttribute("head"), Boolean.TRUE);
		boolean relatedClassName = GetterUtil.getBoolean(
			searchContext.getAttribute("relatedClassName"));

		if (head && !relatedClassName && !showNonindexable) {
			contextQuery.addRequiredTerm("head", Boolean.TRUE);
		}

		if (!relatedClassName && showNonindexable) {
			contextQuery.addRequiredTerm("headListable", Boolean.TRUE);
		}
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		JournalArticle article = (JournalArticle)obj;

		long classPK = article.getId();

		if (!PropsValues.JOURNAL_ARTICLE_INDEX_ALL_VERSIONS) {
			if (JournalArticleLocalServiceUtil.getArticlesCount(
					article.getGroupId(), article.getArticleId()) > 0) {

				doReindex(obj);

				return;
			}
			else {
				classPK = article.getResourcePrimKey();
			}
		}

		deleteDocument(article.getCompanyId(), classPK);

		if (!article.isApproved()) {
			return;
		}

		JournalArticle latestIndexableArticle =
			JournalArticleLocalServiceUtil.fetchLatestIndexableArticle(
				article.getResourcePrimKey());

		if ((latestIndexableArticle == null) ||
			(PropsValues.JOURNAL_ARTICLE_INDEX_ALL_VERSIONS &&
		 	(latestIndexableArticle.getVersion() > article.getVersion()))) {

			return;
		}

		SearchEngineUtil.updateDocument(
			getSearchEngineId(), article.getCompanyId(),
			getDocument(latestIndexableArticle), isCommitImmediately());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		JournalArticle article = (JournalArticle)obj;

		Document document = getBaseModelDocument(PORTLET_ID, article);

		long classPK = article.getId();

		if (!PropsValues.JOURNAL_ARTICLE_INDEX_ALL_VERSIONS) {
			classPK = article.getResourcePrimKey();
		}

		document.addUID(PORTLET_ID, classPK);

		String articleDefaultLanguageId = LocalizationUtil.getDefaultLanguageId(
			article.getContent());

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		String[] languageIds = getLanguageIds(
			defaultLanguageId, article.getContent());

		for (String languageId : languageIds) {
			String content = extractContent(article, languageId);

			String description = article.getDescription(languageId);

			String title = article.getTitle(languageId);

			if (languageId.equals(articleDefaultLanguageId)) {
				document.addText(Field.CONTENT, content);
				document.addText(Field.DESCRIPTION, description);
				document.addText(Field.TITLE, title);
				document.addText("defaultLanguageId", languageId);
			}

			document.addText(
				Field.CONTENT.concat(StringPool.UNDERLINE).concat(languageId),
				content);
			document.addText(
				Field.DESCRIPTION.concat(StringPool.UNDERLINE).concat(
					languageId), description);
			document.addText(
				Field.TITLE.concat(StringPool.UNDERLINE).concat(languageId),
				title);
		}

		document.addKeyword(Field.FOLDER_ID, article.getFolderId());
		document.addKeyword(Field.LAYOUT_UUID, article.getLayoutUuid());
		document.addKeyword(
			Field.TREE_PATH,
			StringUtil.split(article.getTreePath(), CharPool.SLASH));
		document.addKeyword(Field.TYPE, article.getType());
		document.addKeyword(Field.VERSION, article.getVersion());

		String articleId = article.getArticleId();

		if (article.isInTrash()) {
			articleId = TrashUtil.getOriginalTitle(articleId);
		}

		document.addKeyword("articleId", articleId);
		document.addKeyword("ddmStructureKey", article.getStructureId());
		document.addKeyword("ddmTemplateKey", article.getTemplateId());
		document.addDate("displayDate", article.getDisplayDate());

		addDDMStructureAttributes(document, article);

		boolean head = isHead(article);
		boolean headListable = isHeadListable(article);

		document.addKeyword("head", head);
		document.addKeyword("headListable", headListable);

		return document;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		if (orderByCol.equals("display-date")) {
			return "displayDate";
		}
		else if (orderByCol.equals("id")) {
			return Field.ENTRY_CLASS_PK;
		}
		else if (orderByCol.equals("modified-date")) {
			return Field.MODIFIED_DATE;
		}
		else if (orderByCol.equals("title")) {
			return Field.TITLE;
		}
		else {
			return orderByCol;
		}
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		Locale snippetLocale = getSnippetLocale(document, locale);

		String localizedTitleName = DocumentImpl.getLocalizedName(
			locale, Field.TITLE);

		if ((snippetLocale == null) ||
			(document.getField(localizedTitleName) == null)) {

			snippetLocale = LocaleUtil.fromLanguageId(
				document.get("defaultLanguageId"));
		}

		String title = document.get(
			snippetLocale, Field.SNIPPET + StringPool.UNDERLINE + Field.TITLE,
			Field.TITLE);

		String content = StringPool.BLANK;

		String ddmStructureKey = document.get("ddmStructureKey");

		if (Validator.isNotNull(ddmStructureKey)) {
			content = getDDMContentSummary(document, snippetLocale);
		}
		else {
			content = getBasicContentSummary(document, snippetLocale);
		}

		String groupId = document.get(Field.GROUP_ID);
		String articleId = document.get("articleId");
		String version = document.get(Field.VERSION);

		portletURL.setParameter("struts_action", "/journal/edit_article");
		portletURL.setParameter("groupId", groupId);
		portletURL.setParameter("articleId", articleId);
		portletURL.setParameter("version", version);

		return new Summary(snippetLocale, title, content, portletURL);
	}

	public void doReindex(JournalArticle article, boolean allVersions)
		throws Exception {

		if (PortalUtil.getClassNameId(DDMStructure.class) ==
				article.getClassNameId()) {

			Document document = getDocument(article);

			SearchEngineUtil.deleteDocument(
				getSearchEngineId(), article.getCompanyId(),
				document.get(Field.UID), isCommitImmediately());

			return;
		}

		if (allVersions || !PropsValues.JOURNAL_ARTICLE_INDEX_ALL_VERSIONS) {
			reindexArticleVersions(article);
		}
		else {
			SearchEngineUtil.updateDocument(
				getSearchEngineId(), article.getCompanyId(),
				getDocument(article), isCommitImmediately());
		}
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		JournalArticle article = (JournalArticle)obj;

		doReindex(article, true);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		JournalArticle article =
			JournalArticleLocalServiceUtil.fetchJournalArticle(classPK);

		if (article == null) {
			article = JournalArticleLocalServiceUtil.fetchLatestArticle(
				classPK);
		}

		if (article != null) {
			doReindex(article);
		}
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexArticles(companyId);
	}

	@Override
	protected void doReindexDDMStructures(List<Long> ddmStructureIds)
		throws Exception {

		String[] ddmStructureKeys = new String[ddmStructureIds.size()];

		for (int i = 0; i < ddmStructureIds.size(); i++) {
			long structureId = ddmStructureIds.get(i);

			DDMStructure ddmStructure =
				DDMStructureLocalServiceUtil.getDDMStructure(structureId);

			ddmStructureKeys[i] = ddmStructure.getStructureKey();
		}

		List<JournalArticle> articles =
			JournalArticleLocalServiceUtil.
				getIndexableArticlesByDDMStructureKey(ddmStructureKeys);

		for (JournalArticle article : articles) {
			doReindex(article, false);
		}
	}

	protected String extractBasicContent(
		JournalArticle article, String languageId) {

		String content = article.getContentByLocale(languageId);

		content = StringUtil.replace(content, "<![CDATA[", StringPool.BLANK);
		content = StringUtil.replace(content, "]]>", StringPool.BLANK);
		content = StringUtil.replace(content, "&amp;", "&");
		content = StringUtil.replace(content, "&lt;", "<");
		content = StringUtil.replace(content, "&gt;", ">");

		content = HtmlUtil.extractText(content);

		return content;
	}

	protected String extractContent(JournalArticle article, String languageId)
		throws Exception {

		if (Validator.isNotNull(article.getStructureId())) {
			return extractDDMContent(article, languageId);
		}

		return extractBasicContent(article, languageId);
	}

	protected String extractDDMContent(
			JournalArticle article, String languageId)
		throws Exception {

		if (Validator.isNull(article.getStructureId())) {
			return StringPool.BLANK;
		}

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
			article.getGroupId(),
			PortalUtil.getClassNameId(JournalArticle.class),
			article.getStructureId(), true);

		if (ddmStructure == null) {
			return StringPool.BLANK;
		}

		Fields fields = null;

		try {
			fields = JournalConverterUtil.getDDMFields(
				ddmStructure, article.getContent());
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}

		if (fields == null) {
			return StringPool.BLANK;
		}

		return DDMIndexerUtil.extractAttributes(
			ddmStructure, fields, LocaleUtil.fromLanguageId(languageId));
	}

	protected JournalArticle fetchLatestIndexableArticleVersion(
			long resourcePrimKey)
		throws SystemException {

		JournalArticle latestIndexableArticle =
			JournalArticleLocalServiceUtil.fetchLatestArticle(
				resourcePrimKey,
				new int[] {
					WorkflowConstants.STATUS_APPROVED,
					WorkflowConstants.STATUS_IN_TRASH
				});

		if (latestIndexableArticle == null) {
			latestIndexableArticle =
				JournalArticleLocalServiceUtil.fetchLatestArticle(
					resourcePrimKey);
		}

		return latestIndexableArticle;
	}

	protected Collection<Document> getArticleVersions(JournalArticle article)
		throws PortalException, SystemException {

		Collection<Document> documents = new ArrayList<Document>();

		List<JournalArticle> articles = null;

		if (PropsValues.JOURNAL_ARTICLE_INDEX_ALL_VERSIONS) {
			articles =
				JournalArticleLocalServiceUtil.getArticlesByResourcePrimKey(
					article.getResourcePrimKey());
		}
		else {
			articles = new ArrayList<JournalArticle>();

			JournalArticle latestIndexableArticle =
				fetchLatestIndexableArticleVersion(
					article.getResourcePrimKey());

			if (latestIndexableArticle != null) {
				articles.add(latestIndexableArticle);
			}
		}

		for (JournalArticle curArticle : articles) {
			Document document = getDocument(curArticle);

			documents.add(document);
		}

		return documents;
	}

	protected String getBasicContentSummary(
		Document document, Locale snippetLocale) {

		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		String content = document.get(
			snippetLocale, prefix + Field.DESCRIPTION, prefix + Field.CONTENT);

		if (Validator.isBlank(content)) {
			content = document.get(
				snippetLocale, Field.DESCRIPTION, Field.CONTENT);
		}

		if (content.length() > 200) {
			content = StringUtil.shorten(content, 200);
		}

		return content;
	}

	protected String getDDMContentSummary(
		Document document, Locale snippetLocale) {

		String content = StringPool.BLANK;

		try {
			long groupId = GetterUtil.getLong(document.get(Field.GROUP_ID));
			String articleId = document.get("articleId");
			double version = GetterUtil.getDouble(document.get(Field.VERSION));

			JournalArticle article =
				JournalArticleLocalServiceUtil.fetchArticle(
					groupId, articleId, version);

			if (article == null) {
				return content;
			}

			JournalArticleDisplay articleDisplay =
				JournalArticleLocalServiceUtil.getArticleDisplay(
					article, null, Constants.VIEW,
					LocaleUtil.toLanguageId(snippetLocale), 1, null, null);

			content = HtmlUtil.escape(articleDisplay.getDescription());
			content = HtmlUtil.replaceNewLine(content);

			if (Validator.isNull(content)) {
				content = HtmlUtil.extractText(articleDisplay.getContent());
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		return content;
	}

	protected String[] getLanguageIds(
		String defaultLanguageId, String content) {

		String[] languageIds = LocalizationUtil.getAvailableLanguageIds(
			content);

		if (languageIds.length == 0) {
			languageIds = new String[] {defaultLanguageId};
		}

		return languageIds;
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected boolean isHead(JournalArticle article) throws SystemException {
		JournalArticle latestArticle =
			JournalArticleLocalServiceUtil.fetchLatestArticle(
				article.getResourcePrimKey(),
				new int[] {
					WorkflowConstants.STATUS_APPROVED,
					WorkflowConstants.STATUS_IN_TRASH
				});

		if ((latestArticle != null) && !latestArticle.isIndexable()) {
			return false;
		}
		else if ((latestArticle != null) &&
				 (article.getId() == latestArticle.getId())) {

			return true;
		}

		return false;
	}

	protected boolean isHeadListable(JournalArticle article)
		throws SystemException {

		JournalArticle latestArticle =
			JournalArticleLocalServiceUtil.fetchLatestArticle(
				article.getResourcePrimKey(),
				new int[] {
					WorkflowConstants.STATUS_APPROVED,
					WorkflowConstants.STATUS_IN_TRASH,
					WorkflowConstants.STATUS_SCHEDULED
				});

		if ((latestArticle != null) &&
			(article.getId() == latestArticle.getId())) {

			return true;
		}

		return false;
	}

	protected void reindexArticles(long companyId)
		throws PortalException, SystemException {

		ActionableDynamicQuery actionableDynamicQuery =
			new JournalArticleActionableDynamicQuery() {

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				JournalArticle article = (JournalArticle)object;

				if (!PropsValues.JOURNAL_ARTICLE_INDEX_ALL_VERSIONS) {
					JournalArticle latestIndexableArticle =
						fetchLatestIndexableArticleVersion(
							article.getResourcePrimKey());

					if (latestIndexableArticle == null) {
						return;
					}

					article = latestIndexableArticle;
				}

				Document document = getDocument(article);

				addDocument(document);
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		actionableDynamicQuery.performActions();
	}

	protected void reindexArticleVersions(JournalArticle article)
		throws PortalException, SystemException {

		SearchEngineUtil.updateDocuments(
			getSearchEngineId(), article.getCompanyId(),
			getArticleVersions(article), isCommitImmediately());
	}

	private static Log _log = LogFactoryUtil.getLog(
		JournalArticleIndexer.class);

}