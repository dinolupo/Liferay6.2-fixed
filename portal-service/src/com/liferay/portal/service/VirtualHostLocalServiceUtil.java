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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for VirtualHost. This utility wraps
 * {@link com.liferay.portal.service.impl.VirtualHostLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see VirtualHostLocalService
 * @see com.liferay.portal.service.base.VirtualHostLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.VirtualHostLocalServiceImpl
 * @generated
 */
@ProviderType
public class VirtualHostLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.VirtualHostLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the virtual host to the database. Also notifies the appropriate model listeners.
	*
	* @param virtualHost the virtual host
	* @return the virtual host that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.VirtualHost addVirtualHost(
		com.liferay.portal.model.VirtualHost virtualHost)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addVirtualHost(virtualHost);
	}

	/**
	* Creates a new virtual host with the primary key. Does not add the virtual host to the database.
	*
	* @param virtualHostId the primary key for the new virtual host
	* @return the new virtual host
	*/
	public static com.liferay.portal.model.VirtualHost createVirtualHost(
		long virtualHostId) {
		return getService().createVirtualHost(virtualHostId);
	}

	/**
	* Deletes the virtual host with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param virtualHostId the primary key of the virtual host
	* @return the virtual host that was removed
	* @throws PortalException if a virtual host with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.VirtualHost deleteVirtualHost(
		long virtualHostId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteVirtualHost(virtualHostId);
	}

	/**
	* Deletes the virtual host from the database. Also notifies the appropriate model listeners.
	*
	* @param virtualHost the virtual host
	* @return the virtual host that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.VirtualHost deleteVirtualHost(
		com.liferay.portal.model.VirtualHost virtualHost)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteVirtualHost(virtualHost);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.VirtualHostModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.VirtualHostModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.portal.model.VirtualHost fetchVirtualHost(
		long virtualHostId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchVirtualHost(virtualHostId);
	}

	/**
	* Returns the virtual host with the primary key.
	*
	* @param virtualHostId the primary key of the virtual host
	* @return the virtual host
	* @throws PortalException if a virtual host with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.VirtualHost getVirtualHost(
		long virtualHostId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getVirtualHost(virtualHostId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the virtual hosts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.VirtualHostModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of virtual hosts
	* @param end the upper bound of the range of virtual hosts (not inclusive)
	* @return the range of virtual hosts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.VirtualHost> getVirtualHosts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getVirtualHosts(start, end);
	}

	/**
	* Returns the number of virtual hosts.
	*
	* @return the number of virtual hosts
	* @throws SystemException if a system exception occurred
	*/
	public static int getVirtualHostsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getVirtualHostsCount();
	}

	/**
	* Updates the virtual host in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param virtualHost the virtual host
	* @return the virtual host that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.VirtualHost updateVirtualHost(
		com.liferay.portal.model.VirtualHost virtualHost)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateVirtualHost(virtualHost);
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

	public static com.liferay.portal.model.VirtualHost fetchVirtualHost(
		long companyId, long layoutSetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchVirtualHost(companyId, layoutSetId);
	}

	public static com.liferay.portal.model.VirtualHost fetchVirtualHost(
		java.lang.String hostname)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchVirtualHost(hostname);
	}

	public static com.liferay.portal.model.VirtualHost getVirtualHost(
		long companyId, long layoutSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getVirtualHost(companyId, layoutSetId);
	}

	public static com.liferay.portal.model.VirtualHost getVirtualHost(
		java.lang.String hostname)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getVirtualHost(hostname);
	}

	public static com.liferay.portal.model.VirtualHost updateVirtualHost(
		long companyId, long layoutSetId, java.lang.String hostname)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateVirtualHost(companyId, layoutSetId, hostname);
	}

	public static VirtualHostLocalService getService() {
		if (_service == null) {
			_service = (VirtualHostLocalService)PortalBeanLocatorUtil.locate(VirtualHostLocalService.class.getName());

			ReferenceRegistry.registerReference(VirtualHostLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(VirtualHostLocalService service) {
	}

	private static VirtualHostLocalService _service;
}