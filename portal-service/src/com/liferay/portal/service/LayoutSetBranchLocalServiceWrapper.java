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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link LayoutSetBranchLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetBranchLocalService
 * @generated
 */
@ProviderType
public class LayoutSetBranchLocalServiceWrapper
	implements LayoutSetBranchLocalService,
		ServiceWrapper<LayoutSetBranchLocalService> {
	public LayoutSetBranchLocalServiceWrapper(
		LayoutSetBranchLocalService layoutSetBranchLocalService) {
		_layoutSetBranchLocalService = layoutSetBranchLocalService;
	}

	/**
	* Adds the layout set branch to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranch the layout set branch
	* @return the layout set branch that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.LayoutSetBranch addLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.addLayoutSetBranch(layoutSetBranch);
	}

	/**
	* Creates a new layout set branch with the primary key. Does not add the layout set branch to the database.
	*
	* @param layoutSetBranchId the primary key for the new layout set branch
	* @return the new layout set branch
	*/
	@Override
	public com.liferay.portal.model.LayoutSetBranch createLayoutSetBranch(
		long layoutSetBranchId) {
		return _layoutSetBranchLocalService.createLayoutSetBranch(layoutSetBranchId);
	}

	/**
	* Deletes the layout set branch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranchId the primary key of the layout set branch
	* @return the layout set branch that was removed
	* @throws PortalException if a layout set branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.LayoutSetBranch deleteLayoutSetBranch(
		long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.deleteLayoutSetBranch(layoutSetBranchId);
	}

	/**
	* Deletes the layout set branch from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranch the layout set branch
	* @return the layout set branch that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.LayoutSetBranch deleteLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.deleteLayoutSetBranch(layoutSetBranch);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _layoutSetBranchLocalService.dynamicQuery();
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
		return _layoutSetBranchLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _layoutSetBranchLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _layoutSetBranchLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _layoutSetBranchLocalService.dynamicQueryCount(dynamicQuery);
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
		return _layoutSetBranchLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.model.LayoutSetBranch fetchLayoutSetBranch(
		long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.fetchLayoutSetBranch(layoutSetBranchId);
	}

	/**
	* Returns the layout set branch with the primary key.
	*
	* @param layoutSetBranchId the primary key of the layout set branch
	* @return the layout set branch
	* @throws PortalException if a layout set branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.LayoutSetBranch getLayoutSetBranch(
		long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.getLayoutSetBranch(layoutSetBranchId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the layout set branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @return the range of layout set branchs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.model.LayoutSetBranch> getLayoutSetBranchs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.getLayoutSetBranchs(start, end);
	}

	/**
	* Returns the number of layout set branchs.
	*
	* @return the number of layout set branchs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getLayoutSetBranchsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.getLayoutSetBranchsCount();
	}

	/**
	* Updates the layout set branch in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranch the layout set branch
	* @return the layout set branch that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.LayoutSetBranch updateLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.updateLayoutSetBranch(layoutSetBranch);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _layoutSetBranchLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_layoutSetBranchLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portal.model.LayoutSetBranch addLayoutSetBranch(
		long userId, long groupId, boolean privateLayout,
		java.lang.String name, java.lang.String description, boolean master,
		long copyLayoutSetBranchId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.addLayoutSetBranch(userId, groupId,
			privateLayout, name, description, master, copyLayoutSetBranchId,
			serviceContext);
	}

	@Override
	public com.liferay.portal.model.LayoutSetBranch deleteLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch,
		boolean includeMaster)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.deleteLayoutSetBranch(layoutSetBranch,
			includeMaster);
	}

	@Override
	public void deleteLayoutSetBranches(long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetBranchLocalService.deleteLayoutSetBranches(groupId,
			privateLayout);
	}

	@Override
	public void deleteLayoutSetBranches(long groupId, boolean privateLayout,
		boolean includeMaster)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetBranchLocalService.deleteLayoutSetBranches(groupId,
			privateLayout, includeMaster);
	}

	@Override
	public com.liferay.portal.model.LayoutSetBranch fetchLayoutSetBranch(
		long groupId, boolean privateLayout, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.fetchLayoutSetBranch(groupId,
			privateLayout, name);
	}

	@Override
	public com.liferay.portal.model.LayoutSetBranch getLayoutSetBranch(
		long groupId, boolean privateLayout, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.getLayoutSetBranch(groupId,
			privateLayout, name);
	}

	@Override
	public java.util.List<com.liferay.portal.model.LayoutSetBranch> getLayoutSetBranches(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.getLayoutSetBranches(groupId,
			privateLayout);
	}

	@Override
	public com.liferay.portal.model.LayoutSetBranch getMasterLayoutSetBranch(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.getMasterLayoutSetBranch(groupId,
			privateLayout);
	}

	/**
	* @deprecated As of 6.2.0, replaced by {@link #getUserLayoutSetBranch(long,
	long, boolean, long, long)}
	*/
	@Override
	public com.liferay.portal.model.LayoutSetBranch getUserLayoutSetBranch(
		long userId, long groupId, boolean privateLayout, long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.getUserLayoutSetBranch(userId,
			groupId, privateLayout, layoutSetBranchId);
	}

	@Override
	public com.liferay.portal.model.LayoutSetBranch getUserLayoutSetBranch(
		long userId, long groupId, boolean privateLayout, long layoutSetId,
		long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.getUserLayoutSetBranch(userId,
			groupId, privateLayout, layoutSetId, layoutSetBranchId);
	}

	@Override
	public com.liferay.portal.model.LayoutSetBranch mergeLayoutSetBranch(
		long layoutSetBranchId, long mergeLayoutSetBranchId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.mergeLayoutSetBranch(layoutSetBranchId,
			mergeLayoutSetBranchId, serviceContext);
	}

	@Override
	public com.liferay.portal.model.LayoutSetBranch updateLayoutSetBranch(
		long layoutSetBranchId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchLocalService.updateLayoutSetBranch(layoutSetBranchId,
			name, description, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public LayoutSetBranchLocalService getWrappedLayoutSetBranchLocalService() {
		return _layoutSetBranchLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedLayoutSetBranchLocalService(
		LayoutSetBranchLocalService layoutSetBranchLocalService) {
		_layoutSetBranchLocalService = layoutSetBranchLocalService;
	}

	@Override
	public LayoutSetBranchLocalService getWrappedService() {
		return _layoutSetBranchLocalService;
	}

	@Override
	public void setWrappedService(
		LayoutSetBranchLocalService layoutSetBranchLocalService) {
		_layoutSetBranchLocalService = layoutSetBranchLocalService;
	}

	private LayoutSetBranchLocalService _layoutSetBranchLocalService;
}