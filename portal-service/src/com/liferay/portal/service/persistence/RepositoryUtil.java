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

package com.liferay.portal.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.model.Repository;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the repository service. This utility wraps {@link RepositoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RepositoryPersistence
 * @see RepositoryPersistenceImpl
 * @generated
 */
@ProviderType
public class RepositoryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Repository repository) {
		getPersistence().clearCache(repository);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Repository> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Repository> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Repository> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Repository update(Repository repository)
		throws SystemException {
		return getPersistence().update(repository);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Repository update(Repository repository,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(repository, serviceContext);
	}

	/**
	* Returns all the repositories where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the repositories where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @return the range of matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the repositories where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first repository in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first repository in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository, or <code>null</code> if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last repository in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last repository in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository, or <code>null</code> if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the repositories before and after the current repository in the ordered set where uuid = &#63;.
	*
	* @param repositoryId the primary key of the current repository
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a repository with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository[] findByUuid_PrevAndNext(
		long repositoryId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(repositoryId, uuid, orderByComparator);
	}

	/**
	* Removes all the repositories where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of repositories where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the repository where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.portal.NoSuchRepositoryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the repository where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching repository, or <code>null</code> if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the repository where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching repository, or <code>null</code> if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the repository where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the repository that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of repositories where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the repositories where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the repositories where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @return the range of matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the repositories where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first repository in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first repository in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository, or <code>null</code> if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last repository in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last repository in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository, or <code>null</code> if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the repositories before and after the current repository in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param repositoryId the primary key of the current repository
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a repository with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository[] findByUuid_C_PrevAndNext(
		long repositoryId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(repositoryId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the repositories where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of repositories where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the repositories where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the repositories where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @return the range of matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the repositories where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first repository in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first repository in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository, or <code>null</code> if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last repository in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last repository in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository, or <code>null</code> if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the repositories before and after the current repository in the ordered set where groupId = &#63;.
	*
	* @param repositoryId the primary key of the current repository
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a repository with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository[] findByGroupId_PrevAndNext(
		long repositoryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(repositoryId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the repositories where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of repositories where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the repository where groupId = &#63; and name = &#63; and portletId = &#63; or throws a {@link com.liferay.portal.NoSuchRepositoryException} if it could not be found.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @return the matching repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository findByG_N_P(
		long groupId, java.lang.String name, java.lang.String portletId)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_N_P(groupId, name, portletId);
	}

	/**
	* Returns the repository where groupId = &#63; and name = &#63; and portletId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @return the matching repository, or <code>null</code> if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository fetchByG_N_P(
		long groupId, java.lang.String name, java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_N_P(groupId, name, portletId);
	}

	/**
	* Returns the repository where groupId = &#63; and name = &#63; and portletId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching repository, or <code>null</code> if a matching repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository fetchByG_N_P(
		long groupId, java.lang.String name, java.lang.String portletId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_N_P(groupId, name, portletId, retrieveFromCache);
	}

	/**
	* Removes the repository where groupId = &#63; and name = &#63; and portletId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @return the repository that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository removeByG_N_P(
		long groupId, java.lang.String name, java.lang.String portletId)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByG_N_P(groupId, name, portletId);
	}

	/**
	* Returns the number of repositories where groupId = &#63; and name = &#63; and portletId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @return the number of matching repositories
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_N_P(long groupId, java.lang.String name,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_N_P(groupId, name, portletId);
	}

	/**
	* Caches the repository in the entity cache if it is enabled.
	*
	* @param repository the repository
	*/
	public static void cacheResult(
		com.liferay.portal.model.Repository repository) {
		getPersistence().cacheResult(repository);
	}

	/**
	* Caches the repositories in the entity cache if it is enabled.
	*
	* @param repositories the repositories
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.Repository> repositories) {
		getPersistence().cacheResult(repositories);
	}

	/**
	* Creates a new repository with the primary key. Does not add the repository to the database.
	*
	* @param repositoryId the primary key for the new repository
	* @return the new repository
	*/
	public static com.liferay.portal.model.Repository create(long repositoryId) {
		return getPersistence().create(repositoryId);
	}

	/**
	* Removes the repository with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param repositoryId the primary key of the repository
	* @return the repository that was removed
	* @throws com.liferay.portal.NoSuchRepositoryException if a repository with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository remove(long repositoryId)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(repositoryId);
	}

	public static com.liferay.portal.model.Repository updateImpl(
		com.liferay.portal.model.Repository repository)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(repository);
	}

	/**
	* Returns the repository with the primary key or throws a {@link com.liferay.portal.NoSuchRepositoryException} if it could not be found.
	*
	* @param repositoryId the primary key of the repository
	* @return the repository
	* @throws com.liferay.portal.NoSuchRepositoryException if a repository with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository findByPrimaryKey(
		long repositoryId)
		throws com.liferay.portal.NoSuchRepositoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(repositoryId);
	}

	/**
	* Returns the repository with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param repositoryId the primary key of the repository
	* @return the repository, or <code>null</code> if a repository with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Repository fetchByPrimaryKey(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(repositoryId);
	}

	/**
	* Returns all the repositories.
	*
	* @return the repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @return the range of repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of repositories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Repository> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the repositories from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of repositories.
	*
	* @return the number of repositories
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static RepositoryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (RepositoryPersistence)PortalBeanLocatorUtil.locate(RepositoryPersistence.class.getName());

			ReferenceRegistry.registerReference(RepositoryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(RepositoryPersistence persistence) {
	}

	private static RepositoryPersistence _persistence;
}