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

package com.liferay.portlet.blogs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class BlogsStatsUserFinderUtil {
	public static int countByOrganizationId(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByOrganizationId(organizationId);
	}

	public static int countByOrganizationIds(
		java.util.List<java.lang.Long> organizationIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByOrganizationIds(organizationIds);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> findByGroupIds(
		long companyId, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByGroupIds(companyId, groupId, start, end);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> findByOrganizationId(
		long organizationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByOrganizationId(organizationId, start, end, obc);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> findByOrganizationIds(
		java.util.List<java.lang.Long> organizationIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByOrganizationIds(organizationIds, start, end, obc);
	}

	public static BlogsStatsUserFinder getFinder() {
		if (_finder == null) {
			_finder = (BlogsStatsUserFinder)PortalBeanLocatorUtil.locate(BlogsStatsUserFinder.class.getName());

			ReferenceRegistry.registerReference(BlogsStatsUserFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(BlogsStatsUserFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(BlogsStatsUserFinderUtil.class,
			"_finder");
	}

	private static BlogsStatsUserFinder _finder;
}