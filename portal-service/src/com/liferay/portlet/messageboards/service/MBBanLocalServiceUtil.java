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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for MBBan. This utility wraps
 * {@link com.liferay.portlet.messageboards.service.impl.MBBanLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MBBanLocalService
 * @see com.liferay.portlet.messageboards.service.base.MBBanLocalServiceBaseImpl
 * @see com.liferay.portlet.messageboards.service.impl.MBBanLocalServiceImpl
 * @generated
 */
@ProviderType
public class MBBanLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.messageboards.service.impl.MBBanLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the message boards ban to the database. Also notifies the appropriate model listeners.
	*
	* @param mbBan the message boards ban
	* @return the message boards ban that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBBan addMBBan(
		com.liferay.portlet.messageboards.model.MBBan mbBan)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addMBBan(mbBan);
	}

	/**
	* Creates a new message boards ban with the primary key. Does not add the message boards ban to the database.
	*
	* @param banId the primary key for the new message boards ban
	* @return the new message boards ban
	*/
	public static com.liferay.portlet.messageboards.model.MBBan createMBBan(
		long banId) {
		return getService().createMBBan(banId);
	}

	/**
	* Deletes the message boards ban with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param banId the primary key of the message boards ban
	* @return the message boards ban that was removed
	* @throws PortalException if a message boards ban with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBBan deleteMBBan(
		long banId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteMBBan(banId);
	}

	/**
	* Deletes the message boards ban from the database. Also notifies the appropriate model listeners.
	*
	* @param mbBan the message boards ban
	* @return the message boards ban that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBBan deleteMBBan(
		com.liferay.portlet.messageboards.model.MBBan mbBan)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteMBBan(mbBan);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portlet.messageboards.model.MBBan fetchMBBan(
		long banId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchMBBan(banId);
	}

	/**
	* Returns the message boards ban with the matching UUID and company.
	*
	* @param uuid the message boards ban's UUID
	* @param companyId the primary key of the company
	* @return the matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBBan fetchMBBanByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchMBBanByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the message boards ban matching the UUID and group.
	*
	* @param uuid the message boards ban's UUID
	* @param groupId the primary key of the group
	* @return the matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBBan fetchMBBanByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchMBBanByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the message boards ban with the primary key.
	*
	* @param banId the primary key of the message boards ban
	* @return the message boards ban
	* @throws PortalException if a message boards ban with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBBan getMBBan(
		long banId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMBBan(banId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the message boards ban with the matching UUID and company.
	*
	* @param uuid the message boards ban's UUID
	* @param companyId the primary key of the company
	* @return the matching message boards ban
	* @throws PortalException if a matching message boards ban could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBBan getMBBanByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMBBanByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the message boards ban matching the UUID and group.
	*
	* @param uuid the message boards ban's UUID
	* @param groupId the primary key of the group
	* @return the matching message boards ban
	* @throws PortalException if a matching message boards ban could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBBan getMBBanByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMBBanByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the message boards bans.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @return the range of message boards bans
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.messageboards.model.MBBan> getMBBans(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMBBans(start, end);
	}

	/**
	* Returns the number of message boards bans.
	*
	* @return the number of message boards bans
	* @throws SystemException if a system exception occurred
	*/
	public static int getMBBansCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMBBansCount();
	}

	/**
	* Updates the message boards ban in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mbBan the message boards ban
	* @return the message boards ban that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBBan updateMBBan(
		com.liferay.portlet.messageboards.model.MBBan mbBan)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateMBBan(mbBan);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.portlet.messageboards.model.MBBan addBan(
		long userId, long banUserId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addBan(userId, banUserId, serviceContext);
	}

	public static void checkBan(long groupId, long banUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().checkBan(groupId, banUserId);
	}

	public static void deleteBan(long banId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteBan(banId);
	}

	public static void deleteBan(long banUserId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteBan(banUserId, serviceContext);
	}

	public static void deleteBan(
		com.liferay.portlet.messageboards.model.MBBan ban)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteBan(ban);
	}

	public static void deleteBansByBanUserId(long banUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteBansByBanUserId(banUserId);
	}

	public static void deleteBansByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteBansByGroupId(groupId);
	}

	public static void expireBans()
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().expireBans();
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBBan> getBans(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBans(groupId, start, end);
	}

	public static int getBansCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBansCount(groupId);
	}

	public static boolean hasBan(long groupId, long banUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasBan(groupId, banUserId);
	}

	public static MBBanLocalService getService() {
		if (_service == null) {
			_service = (MBBanLocalService)PortalBeanLocatorUtil.locate(MBBanLocalService.class.getName());

			ReferenceRegistry.registerReference(MBBanLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(MBBanLocalService service) {
	}

	private static MBBanLocalService _service;
}