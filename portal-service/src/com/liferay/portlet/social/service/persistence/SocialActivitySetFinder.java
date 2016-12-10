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

package com.liferay.portlet.social.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface SocialActivitySetFinder {
	public int countByRelation(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByRelationType(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByUser(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByUserGroups(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByRelation(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByRelationType(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByUser(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByUserGroups(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}