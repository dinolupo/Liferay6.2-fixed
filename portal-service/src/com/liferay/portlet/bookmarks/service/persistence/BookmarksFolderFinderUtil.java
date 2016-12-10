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

package com.liferay.portlet.bookmarks.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class BookmarksFolderFinderUtil {
	public static int countF_E_ByG_F(long groupId, long folderId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countF_E_ByG_F(groupId, folderId, queryDefinition);
	}

	public static int filterCountF_E_ByG_F(long groupId, long folderId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountF_E_ByG_F(groupId, folderId, queryDefinition);
	}

	public static java.util.List<java.lang.Object> filterFindBF_E_ByG_F(
		long groupId, long folderId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindBF_E_ByG_F(groupId, folderId, queryDefinition);
	}

	public static java.util.List<com.liferay.portlet.bookmarks.model.BookmarksFolder> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoAssets();
	}

	public static java.util.List<java.lang.Object> findF_E_ByG_F(long groupId,
		long folderId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findF_E_ByG_F(groupId, folderId, queryDefinition);
	}

	public static BookmarksFolderFinder getFinder() {
		if (_finder == null) {
			_finder = (BookmarksFolderFinder)PortalBeanLocatorUtil.locate(BookmarksFolderFinder.class.getName());

			ReferenceRegistry.registerReference(BookmarksFolderFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(BookmarksFolderFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(BookmarksFolderFinderUtil.class,
			"_finder");
	}

	private static BookmarksFolderFinder _finder;
}