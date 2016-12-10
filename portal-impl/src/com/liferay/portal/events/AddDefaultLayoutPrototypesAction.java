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

package com.liferay.portal.events;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.LayoutTypePortletConstants;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutPrototypeLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.wiki.model.WikiPage;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Sergio González
 * @author Juan Fernández
 */
public class AddDefaultLayoutPrototypesAction
	extends BaseDefaultLayoutPrototypesAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void addBlogPage(
			long companyId, long defaultUserId,
			List<LayoutPrototype> layoutPrototypes)
		throws Exception {

		Layout layout = addLayoutPrototype(
			companyId, defaultUserId, "Blog",
			"Create, edit, and view blogs from this page. Explore topics " +
				"using tags, and connect with other members that blog.",
			"2_columns_iii", layoutPrototypes);

		if (layout == null) {
			return;
		}

		addPortletId(layout, PortletKeys.BLOGS, "column-1");

		String portletId = addPortletId(
			layout, PortletKeys.TAGS_CLOUD, "column-2");

		Map<String, String> preferences = new HashMap<String, String>();

		preferences.put(
			"classNameId",
			String.valueOf(PortalUtil.getClassNameId(BlogsEntry.class)));
		preferences.put("showAssetCount", Boolean.TRUE.toString());

		updatePortletSetup(layout, portletId, preferences);

		addPortletId(layout, PortletKeys.RECENT_BLOGGERS, "column-2");
	}

	protected Layout addLayoutPrototype(
			long companyId, long defaultUserId, String name, String description,
			String layouteTemplateId, List<LayoutPrototype> layoutPrototypes)
		throws Exception {

		for (LayoutPrototype layoutPrototype : layoutPrototypes) {
			String curName = layoutPrototype.getName(LocaleUtil.getDefault());
			String curDescription = layoutPrototype.getDescription();

			if (name.equals(curName) && description.equals(curDescription)) {
				return null;
			}
		}

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(LocaleUtil.getDefault(), name);

		LayoutPrototype layoutPrototype =
			LayoutPrototypeLocalServiceUtil.addLayoutPrototype(
				defaultUserId, companyId, nameMap, description, true,
				new ServiceContext());

		Layout layout = layoutPrototype.getLayout();

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, layouteTemplateId, false);

		return layout;
	}

	protected void addWebContentPage(
			long companyId, long defaultUserId,
			List<LayoutPrototype> layoutPrototypes)
		throws Exception {

		Layout layout = addLayoutPrototype(
			companyId, defaultUserId, "Content Display Page",
			"Create, edit, and explore web content with this page. Search " +
				"available content, explore related content with tags, and " +
					"browse content categories.",
			"2_columns_ii", layoutPrototypes);

		if (layout == null) {
			return;
		}

		addPortletId(layout, PortletKeys.ASSET_TAGS_NAVIGATION, "column-1");
		addPortletId(
			layout, PortletKeys.ASSET_CATEGORIES_NAVIGATION, "column-1");
		addPortletId(layout, PortletKeys.SEARCH, "column-2");
		String portletId = addPortletId(
			layout, PortletKeys.ASSET_PUBLISHER, "column-2");

		UnicodeProperties typeSettingsProperties =
			layout.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			LayoutTypePortletConstants.DEFAULT_ASSET_PUBLISHER_PORTLET_ID,
			portletId);

		layout = LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

	protected void addWikiPage(
			long companyId, long defaultUserId,
			List<LayoutPrototype> layoutPrototypes)
		throws Exception {

		Layout layout = addLayoutPrototype(
			companyId, defaultUserId, "Wiki",
			"Collaborate with members through the wiki on this page. " +
				"Discover related content through tags, and navigate quickly " +
					"and easily with categories.",
			"2_columns_iii", layoutPrototypes);

		if (layout == null) {
			return;
		}

		addPortletId(layout, PortletKeys.WIKI, "column-1");
		addPortletId(
			layout, PortletKeys.ASSET_CATEGORIES_NAVIGATION, "column-2");

		String portletId = addPortletId(
			layout, PortletKeys.ASSET_TAGS_NAVIGATION, "column-2");

		Map<String, String> preferences = new HashMap<String, String>();

		preferences.put(
			"classNameId",
			String.valueOf(PortalUtil.getClassNameId(WikiPage.class)));
		preferences.put("showAssetCount", Boolean.TRUE.toString());

		updatePortletSetup(layout, portletId, preferences);
	}

	protected void doRun(long companyId) throws Exception {
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		List<LayoutPrototype> layoutPrototypes =
			LayoutPrototypeLocalServiceUtil.search(
				companyId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		addBlogPage(companyId, defaultUserId, layoutPrototypes);
		addWebContentPage(companyId, defaultUserId, layoutPrototypes);
		addWikiPage(companyId, defaultUserId, layoutPrototypes);
	}

}