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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.BaseService;

/**
 * Provides the remote service interface for PollsQuestion. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see PollsQuestionServiceUtil
 * @see com.liferay.portlet.polls.service.base.PollsQuestionServiceBaseImpl
 * @see com.liferay.portlet.polls.service.impl.PollsQuestionServiceImpl
 * @generated
 */
@ProviderType
@AccessControlled
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PollsQuestionService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PollsQuestionServiceUtil} to access the polls question remote service. Add custom service methods to {@link com.liferay.portlet.polls.service.impl.PollsQuestionServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public com.liferay.portlet.polls.model.PollsQuestion addQuestion(
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		java.util.List<com.liferay.portlet.polls.model.PollsChoice> choices,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteQuestion(long questionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portlet.polls.model.PollsQuestion getQuestion(
		long questionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.polls.model.PollsQuestion updateQuestion(
		long questionId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		java.util.List<com.liferay.portlet.polls.model.PollsChoice> choices,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}