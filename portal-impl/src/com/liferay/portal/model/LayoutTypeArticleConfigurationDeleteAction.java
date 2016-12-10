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

package com.liferay.portal.model;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalContentSearchLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public class LayoutTypeArticleConfigurationDeleteAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			long groupId = ParamUtil.getLong(request, "groupId");
			boolean privateLayout = ParamUtil.getBoolean(
				request, "privateLayout");
			long layoutId = ParamUtil.getLong(request, "layoutId");

			Layout layout = LayoutLocalServiceUtil.getLayout(
				groupId, privateLayout, layoutId);

			UnicodeProperties typeSettingsProperties =
				layout.getTypeSettingsProperties();

			String articleId = typeSettingsProperties.getProperty("article-id");

			if (Validator.isNull(articleId)) {
				return;
			}

			JournalContentSearchLocalServiceUtil.deleteArticleContentSearch(
				layout.getGroupId(), layout.isPrivateLayout(),
				layout.getLayoutId(), StringPool.BLANK, articleId);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

}