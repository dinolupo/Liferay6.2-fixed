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

package com.liferay.portlet.wiki.util;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiNodeServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.permission.WikiPagePermission;
import com.liferay.portlet.wiki.service.persistence.WikiNodeActionableDynamicQuery;
import com.liferay.portlet.wiki.service.persistence.WikiPageActionableDynamicQuery;

import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 * @author Harry Mark
 * @author Bruno Farache
 * @author Raymond Augé
 */
public class WikiPageIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {WikiPage.class.getName()};

	public static final String PORTLET_ID = PortletKeys.WIKI;

	public WikiPageIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public void addRelatedEntryFields(Document document, Object obj)
		throws Exception {

		WikiPage page = null;

		try {
			if (obj instanceof DLFileEntry) {
				DLFileEntry dlFileEntry = (DLFileEntry)obj;

				page = WikiPageAttachmentsUtil.getPage(
					dlFileEntry.getFileEntryId());

				document.addKeyword(
					Field.CLASS_NAME_ID,
					PortalUtil.getClassNameId(WikiPage.class.getName()));
				document.addKeyword(Field.CLASS_PK, page.getResourcePrimKey());
			}
			else if (obj instanceof MBMessage) {
				MBMessage message = (MBMessage)obj;

				page = WikiPageLocalServiceUtil.getPage(message.getClassPK());
			}
		}
		catch (Exception e) {
			return;
		}

		document.addKeyword(Field.NODE_ID, page.getNodeId());
		document.addKeyword(Field.RELATED_ENTRY, true);
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

		WikiPage page = WikiPageLocalServiceUtil.getPage(entryClassPK);

		return WikiPagePermission.contains(
			permissionChecker, page, ActionKeys.VIEW);
	}

	@Override
	public boolean isVisible(long classPK, int status) throws Exception {
		WikiPage page = WikiPageLocalServiceUtil.getPage(classPK);

		return isVisible(page.getStatus(), status);
	}

	@Override
	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		addStatus(contextQuery, searchContext);

		long[] nodeIds = searchContext.getNodeIds();

		if (ArrayUtil.isNotEmpty(nodeIds)) {
			BooleanQuery nodeIdsQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			for (long nodeId : nodeIds) {
				try {
					WikiNodeServiceUtil.getNode(nodeId);
				}
				catch (Exception e) {
					continue;
				}

				nodeIdsQuery.addTerm(Field.NODE_ID, nodeId);
			}

			contextQuery.add(nodeIdsQuery, BooleanClauseOccur.MUST);
		}
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		SearchContext searchContext = new SearchContext();

		searchContext.setSearchEngineId(getSearchEngineId());

		if (obj instanceof Object[]) {
			Object[] array = (Object[])obj;

			long companyId = (Long)array[0];
			long nodeId = (Long)array[1];
			String title = (String)array[2];

			Document document = new DocumentImpl();

			document.addUID(PORTLET_ID, nodeId, title);

			SearchEngineUtil.deleteDocument(
				getSearchEngineId(), companyId, document.get(Field.UID));
		}
		else if (obj instanceof WikiPage) {
			WikiPage page = (WikiPage)obj;

			deleteDocument(page.getCompanyId(), page.getResourcePrimKey());
		}
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		WikiPage page = (WikiPage)obj;

		Document document = getBaseModelDocument(PORTLET_ID, page);

		document.addUID(PORTLET_ID, page.getResourcePrimKey());

		String content = HtmlUtil.extractText(
			WikiUtil.convert(page, null, null, null));

		document.addText(Field.CONTENT, content);

		document.addKeyword(Field.NODE_ID, page.getNodeId());
		document.addText(Field.TITLE, page.getTitle());

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		Summary summary = createSummary(document, Field.TITLE, Field.CONTENT);

		summary.setMaxContentLength(200);

		String nodeId = document.get("nodeId");

		portletURL.setParameter("struts_action", "/wiki/view");
		portletURL.setParameter("nodeId", nodeId);
		portletURL.setParameter("title", summary.getTitle());

		summary.setPortletURL(portletURL);

		return summary;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		WikiPage page = (WikiPage)obj;

		if (!page.isHead() || (!page.isApproved() && !page.isInTrash())) {
			return;
		}

		if (Validator.isNotNull(page.getRedirectTitle())) {
			return;
		}

		Document document = getDocument(page);

		SearchEngineUtil.updateDocument(
			getSearchEngineId(), page.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		WikiPage page = WikiPageLocalServiceUtil.fetchWikiPage(classPK);

		if (page == null) {
			page = WikiPageLocalServiceUtil.getPage(classPK, (Boolean)null);
		}

		doReindex(page);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexNodes(companyId);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindexNodes(final long companyId)
		throws PortalException, SystemException {

		ActionableDynamicQuery actionableDynamicQuery =
			new WikiNodeActionableDynamicQuery() {

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				WikiNode node = (WikiNode)object;

				reindexPages(companyId, node.getGroupId(), node.getNodeId());
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);

		actionableDynamicQuery.performActions();
	}

	protected void reindexPages(long companyId, long groupId, final long nodeId)
		throws PortalException, SystemException {

		ActionableDynamicQuery actionableDynamicQuery =
			new WikiPageActionableDynamicQuery() {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				Property nodeIdProperty = PropertyFactoryUtil.forName("nodeId");

				dynamicQuery.add(nodeIdProperty.eq(nodeId));

				Property headProperty = PropertyFactoryUtil.forName("head");

				dynamicQuery.add(headProperty.eq(true));
			}

			@Override
			protected void performAction(Object object) throws PortalException {
				WikiPage page = (WikiPage)object;

				Document document = getDocument(page);

				addDocument(document);
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setGroupId(groupId);
		actionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		actionableDynamicQuery.performActions();
	}

}