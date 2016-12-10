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

package com.liferay.portlet.messageboards.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MBThreadService}.
 *
 * @author Brian Wing Shun Chan
 * @see MBThreadService
 * @generated
 */
@ProviderType
public class MBThreadServiceWrapper implements MBThreadService,
	ServiceWrapper<MBThreadService> {
	public MBThreadServiceWrapper(MBThreadService mbThreadService) {
		_mbThreadService = mbThreadService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _mbThreadService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mbThreadService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public void deleteThread(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mbThreadService.deleteThread(threadId);
	}

	@Override
	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getGroupThreads(
		long groupId, long userId, java.util.Date modifiedDate, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreads(groupId, userId, modifiedDate,
			status, start, end);
	}

	@Override
	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, boolean subscribed,
		boolean includeAnonymous, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreads(groupId, userId, status,
			subscribed, includeAnonymous, start, end);
	}

	@Override
	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, boolean subscribed, int start,
		int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreads(groupId, userId, status,
			subscribed, start, end);
	}

	@Override
	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreads(groupId, userId, status, start,
			end);
	}

	@Override
	public int getGroupThreadsCount(long groupId, long userId,
		java.util.Date modifiedDate, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreadsCount(groupId, userId,
			modifiedDate, status);
	}

	@Override
	public int getGroupThreadsCount(long groupId, long userId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreadsCount(groupId, userId, status);
	}

	@Override
	public int getGroupThreadsCount(long groupId, long userId, int status,
		boolean subscribed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreadsCount(groupId, userId, status,
			subscribed);
	}

	@Override
	public int getGroupThreadsCount(long groupId, long userId, int status,
		boolean subscribed, boolean includeAnonymous)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreadsCount(groupId, userId, status,
			subscribed, includeAnonymous);
	}

	@Override
	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getThreads(
		long groupId, long categoryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getThreads(groupId, categoryId, status, start,
			end);
	}

	@Override
	public int getThreadsCount(long groupId, long categoryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getThreadsCount(groupId, categoryId, status);
	}

	@Override
	public com.liferay.portal.model.Lock lockThread(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.lockThread(threadId);
	}

	@Override
	public com.liferay.portlet.messageboards.model.MBThread moveThread(
		long categoryId, long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.moveThread(categoryId, threadId);
	}

	@Override
	public com.liferay.portlet.messageboards.model.MBThread moveThreadFromTrash(
		long categoryId, long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.moveThreadFromTrash(categoryId, threadId);
	}

	@Override
	public com.liferay.portlet.messageboards.model.MBThread moveThreadToTrash(
		long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.moveThreadToTrash(threadId);
	}

	@Override
	public void restoreThreadFromTrash(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mbThreadService.restoreThreadFromTrash(threadId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long groupId,
		long creatorUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.search(groupId, creatorUserId, status, start,
			end);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long groupId,
		long creatorUserId, long startDate, long endDate, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.search(groupId, creatorUserId, startDate,
			endDate, status, start, end);
	}

	@Override
	public com.liferay.portlet.messageboards.model.MBThread splitThread(
		long messageId, java.lang.String subject,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.splitThread(messageId, subject, serviceContext);
	}

	@Override
	public void unlockThread(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mbThreadService.unlockThread(threadId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public MBThreadService getWrappedMBThreadService() {
		return _mbThreadService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedMBThreadService(MBThreadService mbThreadService) {
		_mbThreadService = mbThreadService;
	}

	@Override
	public MBThreadService getWrappedService() {
		return _mbThreadService;
	}

	@Override
	public void setWrappedService(MBThreadService mbThreadService) {
		_mbThreadService = mbThreadService;
	}

	private MBThreadService _mbThreadService;
}