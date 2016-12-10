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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

/**
 * @author Eudaldo Alonso
 */
public class WikiPageAttachmentsUtil {

	public static WikiPage getPage(long fileEntryId)
		throws PortalException, SystemException {

		FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(
			fileEntryId);

		Folder folder = PortletFileRepositoryUtil.getPortletFolder(
			fileEntry.getFolderId());

		long resourcePrimKey = GetterUtil.getLong(folder.getName());

		return WikiPageLocalServiceUtil.getPage(resourcePrimKey);
	}

}