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

package com.liferay.portlet.polls.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PollsChoiceService}.
 *
 * @author Brian Wing Shun Chan
 * @see PollsChoiceService
 * @generated
 */
@ProviderType
public class PollsChoiceServiceWrapper implements PollsChoiceService,
	ServiceWrapper<PollsChoiceService> {
	public PollsChoiceServiceWrapper(PollsChoiceService pollsChoiceService) {
		_pollsChoiceService = pollsChoiceService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _pollsChoiceService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pollsChoiceService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public PollsChoiceService getWrappedPollsChoiceService() {
		return _pollsChoiceService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedPollsChoiceService(
		PollsChoiceService pollsChoiceService) {
		_pollsChoiceService = pollsChoiceService;
	}

	@Override
	public PollsChoiceService getWrappedService() {
		return _pollsChoiceService;
	}

	@Override
	public void setWrappedService(PollsChoiceService pollsChoiceService) {
		_pollsChoiceService = pollsChoiceService;
	}

	private PollsChoiceService _pollsChoiceService;
}