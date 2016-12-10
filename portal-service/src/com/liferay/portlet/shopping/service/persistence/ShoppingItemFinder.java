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

package com.liferay.portlet.shopping.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface ShoppingItemFinder {
	public int countByG_C(long groupId,
		java.util.List<java.lang.Long> categoryIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByFeatured(long groupId, long[] categoryIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKeywords(long groupId, long[] categoryIds,
		java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countBySale(long groupId, long[] categoryIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByG_C(long groupId,
		java.util.List<java.lang.Long> categoryIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.shopping.model.ShoppingItem> findByFeatured(
		long groupId, long[] categoryIds, int numOfItems)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.shopping.model.ShoppingItem> findByKeywords(
		long groupId, long[] categoryIds, java.lang.String keywords, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.shopping.model.ShoppingItem> findBySale(
		long groupId, long[] categoryIds, int numOfItems)
		throws com.liferay.portal.kernel.exception.SystemException;
}