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

package com.liferay.portlet.ratings.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RatingsEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see RatingsEntryService
 * @generated
 */
@ProviderType
public class RatingsEntryServiceWrapper implements RatingsEntryService,
	ServiceWrapper<RatingsEntryService> {
	public RatingsEntryServiceWrapper(RatingsEntryService ratingsEntryService) {
		_ratingsEntryService = ratingsEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _ratingsEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ratingsEntryService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public void deleteEntry(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ratingsEntryService.deleteEntry(className, classPK);
	}

	@Override
	public com.liferay.portlet.ratings.model.RatingsEntry updateEntry(
		java.lang.String className, long classPK, double score)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ratingsEntryService.updateEntry(className, classPK, score);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public RatingsEntryService getWrappedRatingsEntryService() {
		return _ratingsEntryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedRatingsEntryService(
		RatingsEntryService ratingsEntryService) {
		_ratingsEntryService = ratingsEntryService;
	}

	@Override
	public RatingsEntryService getWrappedService() {
		return _ratingsEntryService;
	}

	@Override
	public void setWrappedService(RatingsEntryService ratingsEntryService) {
		_ratingsEntryService = ratingsEntryService;
	}

	private RatingsEntryService _ratingsEntryService;
}