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
 * Provides a wrapper for {@link PollsVoteLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PollsVoteLocalService
 * @generated
 */
@ProviderType
public class PollsVoteLocalServiceWrapper implements PollsVoteLocalService,
	ServiceWrapper<PollsVoteLocalService> {
	public PollsVoteLocalServiceWrapper(
		PollsVoteLocalService pollsVoteLocalService) {
		_pollsVoteLocalService = pollsVoteLocalService;
	}

	/**
	* Adds the polls vote to the database. Also notifies the appropriate model listeners.
	*
	* @param pollsVote the polls vote
	* @return the polls vote that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.polls.model.PollsVote addPollsVote(
		com.liferay.portlet.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.addPollsVote(pollsVote);
	}

	/**
	* Creates a new polls vote with the primary key. Does not add the polls vote to the database.
	*
	* @param voteId the primary key for the new polls vote
	* @return the new polls vote
	*/
	@Override
	public com.liferay.portlet.polls.model.PollsVote createPollsVote(
		long voteId) {
		return _pollsVoteLocalService.createPollsVote(voteId);
	}

	/**
	* Deletes the polls vote with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param voteId the primary key of the polls vote
	* @return the polls vote that was removed
	* @throws PortalException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.polls.model.PollsVote deletePollsVote(
		long voteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.deletePollsVote(voteId);
	}

	/**
	* Deletes the polls vote from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsVote the polls vote
	* @return the polls vote that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.polls.model.PollsVote deletePollsVote(
		com.liferay.portlet.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.deletePollsVote(pollsVote);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _pollsVoteLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.portlet.polls.model.PollsVote fetchPollsVote(long voteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.fetchPollsVote(voteId);
	}

	/**
	* Returns the polls vote with the matching UUID and company.
	*
	* @param uuid the polls vote's UUID
	* @param companyId the primary key of the company
	* @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.polls.model.PollsVote fetchPollsVoteByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.fetchPollsVoteByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the polls vote matching the UUID and group.
	*
	* @param uuid the polls vote's UUID
	* @param groupId the primary key of the group
	* @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.polls.model.PollsVote fetchPollsVoteByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.fetchPollsVoteByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the polls vote with the primary key.
	*
	* @param voteId the primary key of the polls vote
	* @return the polls vote
	* @throws PortalException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.polls.model.PollsVote getPollsVote(long voteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsVote(voteId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the polls vote with the matching UUID and company.
	*
	* @param uuid the polls vote's UUID
	* @param companyId the primary key of the company
	* @return the matching polls vote
	* @throws PortalException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.polls.model.PollsVote getPollsVoteByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsVoteByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the polls vote matching the UUID and group.
	*
	* @param uuid the polls vote's UUID
	* @param groupId the primary key of the group
	* @return the matching polls vote
	* @throws PortalException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.polls.model.PollsVote getPollsVoteByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsVoteByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the polls votes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of polls votes
	* @param end the upper bound of the range of polls votes (not inclusive)
	* @return the range of polls votes
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portlet.polls.model.PollsVote> getPollsVotes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsVotes(start, end);
	}

	/**
	* Returns the number of polls votes.
	*
	* @return the number of polls votes
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getPollsVotesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsVotesCount();
	}

	/**
	* Updates the polls vote in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pollsVote the polls vote
	* @return the polls vote that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.polls.model.PollsVote updatePollsVote(
		com.liferay.portlet.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.updatePollsVote(pollsVote);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _pollsVoteLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pollsVoteLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portlet.polls.model.PollsVote addVote(long userId,
		long questionId, long choiceId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.addVote(userId, questionId, choiceId,
			serviceContext);
	}

	@Override
	public java.util.List<com.liferay.portlet.polls.model.PollsVote> getChoiceVotes(
		long choiceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getChoiceVotes(choiceId, start, end);
	}

	@Override
	public int getChoiceVotesCount(long choiceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getChoiceVotesCount(choiceId);
	}

	@Override
	public java.util.List<com.liferay.portlet.polls.model.PollsVote> getQuestionVotes(
		long questionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getQuestionVotes(questionId, start, end);
	}

	@Override
	public int getQuestionVotesCount(long questionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getQuestionVotesCount(questionId);
	}

	@Override
	public com.liferay.portlet.polls.model.PollsVote getVote(long questionId,
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getVote(questionId, userId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public PollsVoteLocalService getWrappedPollsVoteLocalService() {
		return _pollsVoteLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedPollsVoteLocalService(
		PollsVoteLocalService pollsVoteLocalService) {
		_pollsVoteLocalService = pollsVoteLocalService;
	}

	@Override
	public PollsVoteLocalService getWrappedService() {
		return _pollsVoteLocalService;
	}

	@Override
	public void setWrappedService(PollsVoteLocalService pollsVoteLocalService) {
		_pollsVoteLocalService = pollsVoteLocalService;
	}

	private PollsVoteLocalService _pollsVoteLocalService;
}