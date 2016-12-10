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
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the user service. This utility wraps {@link UserPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserPersistence
 * @see UserPersistenceImpl
 * @generated
 */
@ProviderType
public class UserUtil {
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
	public static void clearCache(User user) {
		getPersistence().clearCache(user);
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
	public static List<User> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<User> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<User> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static User update(User user) throws SystemException {
		return getPersistence().update(user);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static User update(User user, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(user, serviceContext);
	}

	/**
	* Returns all the users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the users before and after the current user in the ordered set where uuid = &#63;.
	*
	* @param userId the primary key of the current user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user
	* @throws com.liferay.portal.NoSuchUserException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User[] findByUuid_PrevAndNext(
		long userId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(userId, uuid, orderByComparator);
	}

	/**
	* Removes all the users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the users before and after the current user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param userId the primary key of the current user
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user
	* @throws com.liferay.portal.NoSuchUserException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User[] findByUuid_C_PrevAndNext(
		long userId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(userId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the users where uuid = &#63; and companyId = &#63; from the database.
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
	* Returns the number of users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the users where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the users where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the users where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the users before and after the current user in the ordered set where companyId = &#63;.
	*
	* @param userId the primary key of the current user
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user
	* @throws com.liferay.portal.NoSuchUserException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User[] findByCompanyId_PrevAndNext(
		long userId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(userId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the users where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of users where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the user where contactId = &#63; or throws a {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	*
	* @param contactId the contact ID
	* @return the matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByContactId(long contactId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByContactId(contactId);
	}

	/**
	* Returns the user where contactId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param contactId the contact ID
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByContactId(long contactId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByContactId(contactId);
	}

	/**
	* Returns the user where contactId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param contactId the contact ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByContactId(
		long contactId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByContactId(contactId, retrieveFromCache);
	}

	/**
	* Removes the user where contactId = &#63; from the database.
	*
	* @param contactId the contact ID
	* @return the user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User removeByContactId(
		long contactId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByContactId(contactId);
	}

	/**
	* Returns the number of users where contactId = &#63;.
	*
	* @param contactId the contact ID
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByContactId(long contactId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByContactId(contactId);
	}

	/**
	* Returns all the users where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @return the matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByEmailAddress(
		java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEmailAddress(emailAddress);
	}

	/**
	* Returns a range of all the users where emailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param emailAddress the email address
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByEmailAddress(
		java.lang.String emailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEmailAddress(emailAddress, start, end);
	}

	/**
	* Returns an ordered range of all the users where emailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param emailAddress the email address
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByEmailAddress(
		java.lang.String emailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEmailAddress(emailAddress, start, end,
			orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByEmailAddress_First(
		java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEmailAddress_First(emailAddress, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByEmailAddress_First(
		java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByEmailAddress_First(emailAddress, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByEmailAddress_Last(
		java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEmailAddress_Last(emailAddress, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByEmailAddress_Last(
		java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByEmailAddress_Last(emailAddress, orderByComparator);
	}

	/**
	* Returns the users before and after the current user in the ordered set where emailAddress = &#63;.
	*
	* @param userId the primary key of the current user
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user
	* @throws com.liferay.portal.NoSuchUserException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User[] findByEmailAddress_PrevAndNext(
		long userId, java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEmailAddress_PrevAndNext(userId, emailAddress,
			orderByComparator);
	}

	/**
	* Removes all the users where emailAddress = &#63; from the database.
	*
	* @param emailAddress the email address
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEmailAddress(java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByEmailAddress(emailAddress);
	}

	/**
	* Returns the number of users where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEmailAddress(java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByEmailAddress(emailAddress);
	}

	/**
	* Returns the user where portraitId = &#63; or throws a {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	*
	* @param portraitId the portrait ID
	* @return the matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByPortraitId(
		long portraitId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPortraitId(portraitId);
	}

	/**
	* Returns the user where portraitId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param portraitId the portrait ID
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByPortraitId(
		long portraitId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPortraitId(portraitId);
	}

	/**
	* Returns the user where portraitId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param portraitId the portrait ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByPortraitId(
		long portraitId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPortraitId(portraitId, retrieveFromCache);
	}

	/**
	* Removes the user where portraitId = &#63; from the database.
	*
	* @param portraitId the portrait ID
	* @return the user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User removeByPortraitId(
		long portraitId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByPortraitId(portraitId);
	}

	/**
	* Returns the number of users where portraitId = &#63;.
	*
	* @param portraitId the portrait ID
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPortraitId(long portraitId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPortraitId(portraitId);
	}

	/**
	* Returns the user where companyId = &#63; and userId = &#63; or throws a {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_U(long companyId,
		long userId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_U(companyId, userId);
	}

	/**
	* Returns the user where companyId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_U(long companyId,
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_U(companyId, userId);
	}

	/**
	* Returns the user where companyId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_U(long companyId,
		long userId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_U(companyId, userId, retrieveFromCache);
	}

	/**
	* Removes the user where companyId = &#63; and userId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User removeByC_U(long companyId,
		long userId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_U(companyId, userId);
	}

	/**
	* Returns the number of users where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_U(long companyId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_U(companyId, userId);
	}

	/**
	* Returns all the users where companyId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @return the matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_CD(
		long companyId, java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_CD(companyId, createDate);
	}

	/**
	* Returns a range of all the users where companyId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_CD(
		long companyId, java.util.Date createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_CD(companyId, createDate, start, end);
	}

	/**
	* Returns an ordered range of all the users where companyId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_CD(
		long companyId, java.util.Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_CD(companyId, createDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where companyId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_CD_First(
		long companyId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_CD_First(companyId, createDate, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where companyId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_CD_First(
		long companyId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_CD_First(companyId, createDate, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where companyId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_CD_Last(
		long companyId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_CD_Last(companyId, createDate, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where companyId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_CD_Last(
		long companyId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_CD_Last(companyId, createDate, orderByComparator);
	}

	/**
	* Returns the users before and after the current user in the ordered set where companyId = &#63; and createDate = &#63;.
	*
	* @param userId the primary key of the current user
	* @param companyId the company ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user
	* @throws com.liferay.portal.NoSuchUserException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User[] findByC_CD_PrevAndNext(
		long userId, long companyId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_CD_PrevAndNext(userId, companyId, createDate,
			orderByComparator);
	}

	/**
	* Removes all the users where companyId = &#63; and createDate = &#63; from the database.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_CD(long companyId, java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_CD(companyId, createDate);
	}

	/**
	* Returns the number of users where companyId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_CD(long companyId, java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_CD(companyId, createDate);
	}

	/**
	* Returns all the users where companyId = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedDate the modified date
	* @return the matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_MD(
		long companyId, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_MD(companyId, modifiedDate);
	}

	/**
	* Returns a range of all the users where companyId = &#63; and modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_MD(
		long companyId, java.util.Date modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_MD(companyId, modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the users where companyId = &#63; and modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_MD(
		long companyId, java.util.Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_MD(companyId, modifiedDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where companyId = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_MD_First(
		long companyId, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_MD_First(companyId, modifiedDate, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where companyId = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_MD_First(
		long companyId, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_MD_First(companyId, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where companyId = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_MD_Last(
		long companyId, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_MD_Last(companyId, modifiedDate, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where companyId = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_MD_Last(
		long companyId, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_MD_Last(companyId, modifiedDate, orderByComparator);
	}

	/**
	* Returns the users before and after the current user in the ordered set where companyId = &#63; and modifiedDate = &#63;.
	*
	* @param userId the primary key of the current user
	* @param companyId the company ID
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user
	* @throws com.liferay.portal.NoSuchUserException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User[] findByC_MD_PrevAndNext(
		long userId, long companyId, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_MD_PrevAndNext(userId, companyId, modifiedDate,
			orderByComparator);
	}

	/**
	* Removes all the users where companyId = &#63; and modifiedDate = &#63; from the database.
	*
	* @param companyId the company ID
	* @param modifiedDate the modified date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_MD(long companyId, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_MD(companyId, modifiedDate);
	}

	/**
	* Returns the number of users where companyId = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedDate the modified date
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_MD(long companyId, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_MD(companyId, modifiedDate);
	}

	/**
	* Returns the user where companyId = &#63; and defaultUser = &#63; or throws a {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	*
	* @param companyId the company ID
	* @param defaultUser the default user
	* @return the matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_DU(long companyId,
		boolean defaultUser)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_DU(companyId, defaultUser);
	}

	/**
	* Returns the user where companyId = &#63; and defaultUser = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param defaultUser the default user
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_DU(long companyId,
		boolean defaultUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_DU(companyId, defaultUser);
	}

	/**
	* Returns the user where companyId = &#63; and defaultUser = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param defaultUser the default user
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_DU(long companyId,
		boolean defaultUser, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_DU(companyId, defaultUser, retrieveFromCache);
	}

	/**
	* Removes the user where companyId = &#63; and defaultUser = &#63; from the database.
	*
	* @param companyId the company ID
	* @param defaultUser the default user
	* @return the user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User removeByC_DU(long companyId,
		boolean defaultUser)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_DU(companyId, defaultUser);
	}

	/**
	* Returns the number of users where companyId = &#63; and defaultUser = &#63;.
	*
	* @param companyId the company ID
	* @param defaultUser the default user
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_DU(long companyId, boolean defaultUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_DU(companyId, defaultUser);
	}

	/**
	* Returns the user where companyId = &#63; and screenName = &#63; or throws a {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	*
	* @param companyId the company ID
	* @param screenName the screen name
	* @return the matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_SN(long companyId,
		java.lang.String screenName)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_SN(companyId, screenName);
	}

	/**
	* Returns the user where companyId = &#63; and screenName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param screenName the screen name
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_SN(long companyId,
		java.lang.String screenName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_SN(companyId, screenName);
	}

	/**
	* Returns the user where companyId = &#63; and screenName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param screenName the screen name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_SN(long companyId,
		java.lang.String screenName, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_SN(companyId, screenName, retrieveFromCache);
	}

	/**
	* Removes the user where companyId = &#63; and screenName = &#63; from the database.
	*
	* @param companyId the company ID
	* @param screenName the screen name
	* @return the user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User removeByC_SN(long companyId,
		java.lang.String screenName)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_SN(companyId, screenName);
	}

	/**
	* Returns the number of users where companyId = &#63; and screenName = &#63;.
	*
	* @param companyId the company ID
	* @param screenName the screen name
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_SN(long companyId, java.lang.String screenName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_SN(companyId, screenName);
	}

	/**
	* Returns the user where companyId = &#63; and emailAddress = &#63; or throws a {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	*
	* @param companyId the company ID
	* @param emailAddress the email address
	* @return the matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_EA(long companyId,
		java.lang.String emailAddress)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_EA(companyId, emailAddress);
	}

	/**
	* Returns the user where companyId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param emailAddress the email address
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_EA(long companyId,
		java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_EA(companyId, emailAddress);
	}

	/**
	* Returns the user where companyId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param emailAddress the email address
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_EA(long companyId,
		java.lang.String emailAddress, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_EA(companyId, emailAddress, retrieveFromCache);
	}

	/**
	* Removes the user where companyId = &#63; and emailAddress = &#63; from the database.
	*
	* @param companyId the company ID
	* @param emailAddress the email address
	* @return the user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User removeByC_EA(long companyId,
		java.lang.String emailAddress)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_EA(companyId, emailAddress);
	}

	/**
	* Returns the number of users where companyId = &#63; and emailAddress = &#63;.
	*
	* @param companyId the company ID
	* @param emailAddress the email address
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_EA(long companyId, java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_EA(companyId, emailAddress);
	}

	/**
	* Returns the user where companyId = &#63; and facebookId = &#63; or throws a {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	*
	* @param companyId the company ID
	* @param facebookId the facebook ID
	* @return the matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_FID(long companyId,
		long facebookId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_FID(companyId, facebookId);
	}

	/**
	* Returns the user where companyId = &#63; and facebookId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param facebookId the facebook ID
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_FID(long companyId,
		long facebookId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_FID(companyId, facebookId);
	}

	/**
	* Returns the user where companyId = &#63; and facebookId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param facebookId the facebook ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_FID(long companyId,
		long facebookId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_FID(companyId, facebookId, retrieveFromCache);
	}

	/**
	* Removes the user where companyId = &#63; and facebookId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param facebookId the facebook ID
	* @return the user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User removeByC_FID(long companyId,
		long facebookId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_FID(companyId, facebookId);
	}

	/**
	* Returns the number of users where companyId = &#63; and facebookId = &#63;.
	*
	* @param companyId the company ID
	* @param facebookId the facebook ID
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_FID(long companyId, long facebookId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_FID(companyId, facebookId);
	}

	/**
	* Returns the user where companyId = &#63; and openId = &#63; or throws a {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	*
	* @param companyId the company ID
	* @param openId the open ID
	* @return the matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_O(long companyId,
		java.lang.String openId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_O(companyId, openId);
	}

	/**
	* Returns the user where companyId = &#63; and openId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param openId the open ID
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_O(long companyId,
		java.lang.String openId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_O(companyId, openId);
	}

	/**
	* Returns the user where companyId = &#63; and openId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param openId the open ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_O(long companyId,
		java.lang.String openId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_O(companyId, openId, retrieveFromCache);
	}

	/**
	* Removes the user where companyId = &#63; and openId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param openId the open ID
	* @return the user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User removeByC_O(long companyId,
		java.lang.String openId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_O(companyId, openId);
	}

	/**
	* Returns the number of users where companyId = &#63; and openId = &#63;.
	*
	* @param companyId the company ID
	* @param openId the open ID
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_O(long companyId, java.lang.String openId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_O(companyId, openId);
	}

	/**
	* Returns all the users where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_S(
		long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_S(companyId, status);
	}

	/**
	* Returns a range of all the users where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_S(
		long companyId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_S(companyId, status, start, end);
	}

	/**
	* Returns an ordered range of all the users where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_S(
		long companyId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S(companyId, status, start, end, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_S_First(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_First(companyId, status, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_S_First(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_S_First(companyId, status, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_S_Last(long companyId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_Last(companyId, status, orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_S_Last(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_S_Last(companyId, status, orderByComparator);
	}

	/**
	* Returns the users before and after the current user in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param userId the primary key of the current user
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user
	* @throws com.liferay.portal.NoSuchUserException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User[] findByC_S_PrevAndNext(
		long userId, long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_PrevAndNext(userId, companyId, status,
			orderByComparator);
	}

	/**
	* Removes all the users where companyId = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_S(long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_S(companyId, status);
	}

	/**
	* Returns the number of users where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_S(long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_S(companyId, status);
	}

	/**
	* Returns all the users where companyId = &#63; and createDate = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_CD_MD(
		long companyId, java.util.Date createDate, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_CD_MD(companyId, createDate, modifiedDate);
	}

	/**
	* Returns a range of all the users where companyId = &#63; and createDate = &#63; and modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_CD_MD(
		long companyId, java.util.Date createDate, java.util.Date modifiedDate,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_CD_MD(companyId, createDate, modifiedDate, start,
			end);
	}

	/**
	* Returns an ordered range of all the users where companyId = &#63; and createDate = &#63; and modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findByC_CD_MD(
		long companyId, java.util.Date createDate, java.util.Date modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_CD_MD(companyId, createDate, modifiedDate, start,
			end, orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where companyId = &#63; and createDate = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_CD_MD_First(
		long companyId, java.util.Date createDate, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_CD_MD_First(companyId, createDate, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the first user in the ordered set where companyId = &#63; and createDate = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_CD_MD_First(
		long companyId, java.util.Date createDate, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_CD_MD_First(companyId, createDate, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where companyId = &#63; and createDate = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user
	* @throws com.liferay.portal.NoSuchUserException if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByC_CD_MD_Last(
		long companyId, java.util.Date createDate, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_CD_MD_Last(companyId, createDate, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the last user in the ordered set where companyId = &#63; and createDate = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user, or <code>null</code> if a matching user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByC_CD_MD_Last(
		long companyId, java.util.Date createDate, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_CD_MD_Last(companyId, createDate, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the users before and after the current user in the ordered set where companyId = &#63; and createDate = &#63; and modifiedDate = &#63;.
	*
	* @param userId the primary key of the current user
	* @param companyId the company ID
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user
	* @throws com.liferay.portal.NoSuchUserException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User[] findByC_CD_MD_PrevAndNext(
		long userId, long companyId, java.util.Date createDate,
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_CD_MD_PrevAndNext(userId, companyId, createDate,
			modifiedDate, orderByComparator);
	}

	/**
	* Removes all the users where companyId = &#63; and createDate = &#63; and modifiedDate = &#63; from the database.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_CD_MD(long companyId,
		java.util.Date createDate, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_CD_MD(companyId, createDate, modifiedDate);
	}

	/**
	* Returns the number of users where companyId = &#63; and createDate = &#63; and modifiedDate = &#63;.
	*
	* @param companyId the company ID
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_CD_MD(long companyId, java.util.Date createDate,
		java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_CD_MD(companyId, createDate, modifiedDate);
	}

	/**
	* Caches the user in the entity cache if it is enabled.
	*
	* @param user the user
	*/
	public static void cacheResult(com.liferay.portal.model.User user) {
		getPersistence().cacheResult(user);
	}

	/**
	* Caches the users in the entity cache if it is enabled.
	*
	* @param users the users
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.User> users) {
		getPersistence().cacheResult(users);
	}

	/**
	* Creates a new user with the primary key. Does not add the user to the database.
	*
	* @param userId the primary key for the new user
	* @return the new user
	*/
	public static com.liferay.portal.model.User create(long userId) {
		return getPersistence().create(userId);
	}

	/**
	* Removes the user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the user
	* @return the user that was removed
	* @throws com.liferay.portal.NoSuchUserException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User remove(long userId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(userId);
	}

	public static com.liferay.portal.model.User updateImpl(
		com.liferay.portal.model.User user)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(user);
	}

	/**
	* Returns the user with the primary key or throws a {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	*
	* @param userId the primary key of the user
	* @return the user
	* @throws com.liferay.portal.NoSuchUserException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User findByPrimaryKey(long userId)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(userId);
	}

	/**
	* Returns the user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userId the primary key of the user
	* @return the user, or <code>null</code> if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.User fetchByPrimaryKey(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(userId);
	}

	/**
	* Returns all the users.
	*
	* @return the users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.User> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the users from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of users.
	*
	* @return the number of users
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the groups associated with the user.
	*
	* @param pk the primary key of the user
	* @return the groups associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getGroups(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getGroups(pk);
	}

	/**
	* Returns a range of all the groups associated with the user.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of groups associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getGroups(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getGroups(pk, start, end);
	}

	/**
	* Returns an ordered range of all the groups associated with the user.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of groups associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Group> getGroups(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getGroups(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of groups associated with the user.
	*
	* @param pk the primary key of the user
	* @return the number of groups associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static int getGroupsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getGroupsSize(pk);
	}

	/**
	* Returns <code>true</code> if the group is associated with the user.
	*
	* @param pk the primary key of the user
	* @param groupPK the primary key of the group
	* @return <code>true</code> if the group is associated with the user; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsGroup(long pk, long groupPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsGroup(pk, groupPK);
	}

	/**
	* Returns <code>true</code> if the user has any groups associated with it.
	*
	* @param pk the primary key of the user to check for associations with groups
	* @return <code>true</code> if the user has any groups associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsGroups(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsGroups(pk);
	}

	/**
	* Adds an association between the user and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param groupPK the primary key of the group
	* @throws SystemException if a system exception occurred
	*/
	public static void addGroup(long pk, long groupPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addGroup(pk, groupPK);
	}

	/**
	* Adds an association between the user and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param group the group
	* @throws SystemException if a system exception occurred
	*/
	public static void addGroup(long pk, com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addGroup(pk, group);
	}

	/**
	* Adds an association between the user and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param groupPKs the primary keys of the groups
	* @throws SystemException if a system exception occurred
	*/
	public static void addGroups(long pk, long[] groupPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addGroups(pk, groupPKs);
	}

	/**
	* Adds an association between the user and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param groups the groups
	* @throws SystemException if a system exception occurred
	*/
	public static void addGroups(long pk,
		java.util.List<com.liferay.portal.model.Group> groups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addGroups(pk, groups);
	}

	/**
	* Clears all associations between the user and its groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user to clear the associated groups from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearGroups(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearGroups(pk);
	}

	/**
	* Removes the association between the user and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param groupPK the primary key of the group
	* @throws SystemException if a system exception occurred
	*/
	public static void removeGroup(long pk, long groupPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeGroup(pk, groupPK);
	}

	/**
	* Removes the association between the user and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param group the group
	* @throws SystemException if a system exception occurred
	*/
	public static void removeGroup(long pk, com.liferay.portal.model.Group group)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeGroup(pk, group);
	}

	/**
	* Removes the association between the user and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param groupPKs the primary keys of the groups
	* @throws SystemException if a system exception occurred
	*/
	public static void removeGroups(long pk, long[] groupPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeGroups(pk, groupPKs);
	}

	/**
	* Removes the association between the user and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param groups the groups
	* @throws SystemException if a system exception occurred
	*/
	public static void removeGroups(long pk,
		java.util.List<com.liferay.portal.model.Group> groups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeGroups(pk, groups);
	}

	/**
	* Sets the groups associated with the user, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param groupPKs the primary keys of the groups to be associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static void setGroups(long pk, long[] groupPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setGroups(pk, groupPKs);
	}

	/**
	* Sets the groups associated with the user, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param groups the groups to be associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static void setGroups(long pk,
		java.util.List<com.liferay.portal.model.Group> groups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setGroups(pk, groups);
	}

	/**
	* Returns all the organizations associated with the user.
	*
	* @param pk the primary key of the user
	* @return the organizations associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Organization> getOrganizations(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getOrganizations(pk);
	}

	/**
	* Returns a range of all the organizations associated with the user.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of organizations associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Organization> getOrganizations(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getOrganizations(pk, start, end);
	}

	/**
	* Returns an ordered range of all the organizations associated with the user.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of organizations associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Organization> getOrganizations(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getOrganizations(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of organizations associated with the user.
	*
	* @param pk the primary key of the user
	* @return the number of organizations associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static int getOrganizationsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getOrganizationsSize(pk);
	}

	/**
	* Returns <code>true</code> if the organization is associated with the user.
	*
	* @param pk the primary key of the user
	* @param organizationPK the primary key of the organization
	* @return <code>true</code> if the organization is associated with the user; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsOrganization(long pk, long organizationPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsOrganization(pk, organizationPK);
	}

	/**
	* Returns <code>true</code> if the user has any organizations associated with it.
	*
	* @param pk the primary key of the user to check for associations with organizations
	* @return <code>true</code> if the user has any organizations associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsOrganizations(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsOrganizations(pk);
	}

	/**
	* Adds an association between the user and the organization. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param organizationPK the primary key of the organization
	* @throws SystemException if a system exception occurred
	*/
	public static void addOrganization(long pk, long organizationPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOrganization(pk, organizationPK);
	}

	/**
	* Adds an association between the user and the organization. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param organization the organization
	* @throws SystemException if a system exception occurred
	*/
	public static void addOrganization(long pk,
		com.liferay.portal.model.Organization organization)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOrganization(pk, organization);
	}

	/**
	* Adds an association between the user and the organizations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param organizationPKs the primary keys of the organizations
	* @throws SystemException if a system exception occurred
	*/
	public static void addOrganizations(long pk, long[] organizationPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOrganizations(pk, organizationPKs);
	}

	/**
	* Adds an association between the user and the organizations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param organizations the organizations
	* @throws SystemException if a system exception occurred
	*/
	public static void addOrganizations(long pk,
		java.util.List<com.liferay.portal.model.Organization> organizations)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOrganizations(pk, organizations);
	}

	/**
	* Clears all associations between the user and its organizations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user to clear the associated organizations from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearOrganizations(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearOrganizations(pk);
	}

	/**
	* Removes the association between the user and the organization. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param organizationPK the primary key of the organization
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOrganization(long pk, long organizationPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOrganization(pk, organizationPK);
	}

	/**
	* Removes the association between the user and the organization. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param organization the organization
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOrganization(long pk,
		com.liferay.portal.model.Organization organization)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOrganization(pk, organization);
	}

	/**
	* Removes the association between the user and the organizations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param organizationPKs the primary keys of the organizations
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOrganizations(long pk, long[] organizationPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOrganizations(pk, organizationPKs);
	}

	/**
	* Removes the association between the user and the organizations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param organizations the organizations
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOrganizations(long pk,
		java.util.List<com.liferay.portal.model.Organization> organizations)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOrganizations(pk, organizations);
	}

	/**
	* Sets the organizations associated with the user, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param organizationPKs the primary keys of the organizations to be associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static void setOrganizations(long pk, long[] organizationPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setOrganizations(pk, organizationPKs);
	}

	/**
	* Sets the organizations associated with the user, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param organizations the organizations to be associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static void setOrganizations(long pk,
		java.util.List<com.liferay.portal.model.Organization> organizations)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setOrganizations(pk, organizations);
	}

	/**
	* Returns all the roles associated with the user.
	*
	* @param pk the primary key of the user
	* @return the roles associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Role> getRoles(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getRoles(pk);
	}

	/**
	* Returns a range of all the roles associated with the user.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of roles associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Role> getRoles(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getRoles(pk, start, end);
	}

	/**
	* Returns an ordered range of all the roles associated with the user.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of roles associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Role> getRoles(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getRoles(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of roles associated with the user.
	*
	* @param pk the primary key of the user
	* @return the number of roles associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static int getRolesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getRolesSize(pk);
	}

	/**
	* Returns <code>true</code> if the role is associated with the user.
	*
	* @param pk the primary key of the user
	* @param rolePK the primary key of the role
	* @return <code>true</code> if the role is associated with the user; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsRole(long pk, long rolePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsRole(pk, rolePK);
	}

	/**
	* Returns <code>true</code> if the user has any roles associated with it.
	*
	* @param pk the primary key of the user to check for associations with roles
	* @return <code>true</code> if the user has any roles associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsRoles(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsRoles(pk);
	}

	/**
	* Adds an association between the user and the role. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param rolePK the primary key of the role
	* @throws SystemException if a system exception occurred
	*/
	public static void addRole(long pk, long rolePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addRole(pk, rolePK);
	}

	/**
	* Adds an association between the user and the role. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param role the role
	* @throws SystemException if a system exception occurred
	*/
	public static void addRole(long pk, com.liferay.portal.model.Role role)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addRole(pk, role);
	}

	/**
	* Adds an association between the user and the roles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param rolePKs the primary keys of the roles
	* @throws SystemException if a system exception occurred
	*/
	public static void addRoles(long pk, long[] rolePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addRoles(pk, rolePKs);
	}

	/**
	* Adds an association between the user and the roles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param roles the roles
	* @throws SystemException if a system exception occurred
	*/
	public static void addRoles(long pk,
		java.util.List<com.liferay.portal.model.Role> roles)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addRoles(pk, roles);
	}

	/**
	* Clears all associations between the user and its roles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user to clear the associated roles from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearRoles(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearRoles(pk);
	}

	/**
	* Removes the association between the user and the role. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param rolePK the primary key of the role
	* @throws SystemException if a system exception occurred
	*/
	public static void removeRole(long pk, long rolePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeRole(pk, rolePK);
	}

	/**
	* Removes the association between the user and the role. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param role the role
	* @throws SystemException if a system exception occurred
	*/
	public static void removeRole(long pk, com.liferay.portal.model.Role role)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeRole(pk, role);
	}

	/**
	* Removes the association between the user and the roles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param rolePKs the primary keys of the roles
	* @throws SystemException if a system exception occurred
	*/
	public static void removeRoles(long pk, long[] rolePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeRoles(pk, rolePKs);
	}

	/**
	* Removes the association between the user and the roles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param roles the roles
	* @throws SystemException if a system exception occurred
	*/
	public static void removeRoles(long pk,
		java.util.List<com.liferay.portal.model.Role> roles)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeRoles(pk, roles);
	}

	/**
	* Sets the roles associated with the user, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param rolePKs the primary keys of the roles to be associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static void setRoles(long pk, long[] rolePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setRoles(pk, rolePKs);
	}

	/**
	* Sets the roles associated with the user, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param roles the roles to be associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static void setRoles(long pk,
		java.util.List<com.liferay.portal.model.Role> roles)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setRoles(pk, roles);
	}

	/**
	* Returns all the teams associated with the user.
	*
	* @param pk the primary key of the user
	* @return the teams associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Team> getTeams(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getTeams(pk);
	}

	/**
	* Returns a range of all the teams associated with the user.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of teams associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Team> getTeams(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getTeams(pk, start, end);
	}

	/**
	* Returns an ordered range of all the teams associated with the user.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of teams associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Team> getTeams(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getTeams(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of teams associated with the user.
	*
	* @param pk the primary key of the user
	* @return the number of teams associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static int getTeamsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getTeamsSize(pk);
	}

	/**
	* Returns <code>true</code> if the team is associated with the user.
	*
	* @param pk the primary key of the user
	* @param teamPK the primary key of the team
	* @return <code>true</code> if the team is associated with the user; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsTeam(long pk, long teamPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsTeam(pk, teamPK);
	}

	/**
	* Returns <code>true</code> if the user has any teams associated with it.
	*
	* @param pk the primary key of the user to check for associations with teams
	* @return <code>true</code> if the user has any teams associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsTeams(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsTeams(pk);
	}

	/**
	* Adds an association between the user and the team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param teamPK the primary key of the team
	* @throws SystemException if a system exception occurred
	*/
	public static void addTeam(long pk, long teamPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addTeam(pk, teamPK);
	}

	/**
	* Adds an association between the user and the team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param team the team
	* @throws SystemException if a system exception occurred
	*/
	public static void addTeam(long pk, com.liferay.portal.model.Team team)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addTeam(pk, team);
	}

	/**
	* Adds an association between the user and the teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param teamPKs the primary keys of the teams
	* @throws SystemException if a system exception occurred
	*/
	public static void addTeams(long pk, long[] teamPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addTeams(pk, teamPKs);
	}

	/**
	* Adds an association between the user and the teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param teams the teams
	* @throws SystemException if a system exception occurred
	*/
	public static void addTeams(long pk,
		java.util.List<com.liferay.portal.model.Team> teams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addTeams(pk, teams);
	}

	/**
	* Clears all associations between the user and its teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user to clear the associated teams from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearTeams(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearTeams(pk);
	}

	/**
	* Removes the association between the user and the team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param teamPK the primary key of the team
	* @throws SystemException if a system exception occurred
	*/
	public static void removeTeam(long pk, long teamPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeTeam(pk, teamPK);
	}

	/**
	* Removes the association between the user and the team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param team the team
	* @throws SystemException if a system exception occurred
	*/
	public static void removeTeam(long pk, com.liferay.portal.model.Team team)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeTeam(pk, team);
	}

	/**
	* Removes the association between the user and the teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param teamPKs the primary keys of the teams
	* @throws SystemException if a system exception occurred
	*/
	public static void removeTeams(long pk, long[] teamPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeTeams(pk, teamPKs);
	}

	/**
	* Removes the association between the user and the teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param teams the teams
	* @throws SystemException if a system exception occurred
	*/
	public static void removeTeams(long pk,
		java.util.List<com.liferay.portal.model.Team> teams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeTeams(pk, teams);
	}

	/**
	* Sets the teams associated with the user, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param teamPKs the primary keys of the teams to be associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static void setTeams(long pk, long[] teamPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setTeams(pk, teamPKs);
	}

	/**
	* Sets the teams associated with the user, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param teams the teams to be associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static void setTeams(long pk,
		java.util.List<com.liferay.portal.model.Team> teams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setTeams(pk, teams);
	}

	/**
	* Returns all the user groups associated with the user.
	*
	* @param pk the primary key of the user
	* @return the user groups associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroup> getUserGroups(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getUserGroups(pk);
	}

	/**
	* Returns a range of all the user groups associated with the user.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of user groups associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroup> getUserGroups(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getUserGroups(pk, start, end);
	}

	/**
	* Returns an ordered range of all the user groups associated with the user.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user groups associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroup> getUserGroups(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getUserGroups(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of user groups associated with the user.
	*
	* @param pk the primary key of the user
	* @return the number of user groups associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static int getUserGroupsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getUserGroupsSize(pk);
	}

	/**
	* Returns <code>true</code> if the user group is associated with the user.
	*
	* @param pk the primary key of the user
	* @param userGroupPK the primary key of the user group
	* @return <code>true</code> if the user group is associated with the user; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsUserGroup(long pk, long userGroupPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsUserGroup(pk, userGroupPK);
	}

	/**
	* Returns <code>true</code> if the user has any user groups associated with it.
	*
	* @param pk the primary key of the user to check for associations with user groups
	* @return <code>true</code> if the user has any user groups associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsUserGroups(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsUserGroups(pk);
	}

	/**
	* Adds an association between the user and the user group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param userGroupPK the primary key of the user group
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroup(long pk, long userGroupPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addUserGroup(pk, userGroupPK);
	}

	/**
	* Adds an association between the user and the user group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param userGroup the user group
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroup(long pk,
		com.liferay.portal.model.UserGroup userGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addUserGroup(pk, userGroup);
	}

	/**
	* Adds an association between the user and the user groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param userGroupPKs the primary keys of the user groups
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroups(long pk, long[] userGroupPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addUserGroups(pk, userGroupPKs);
	}

	/**
	* Adds an association between the user and the user groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param userGroups the user groups
	* @throws SystemException if a system exception occurred
	*/
	public static void addUserGroups(long pk,
		java.util.List<com.liferay.portal.model.UserGroup> userGroups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addUserGroups(pk, userGroups);
	}

	/**
	* Clears all associations between the user and its user groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user to clear the associated user groups from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearUserGroups(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearUserGroups(pk);
	}

	/**
	* Removes the association between the user and the user group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param userGroupPK the primary key of the user group
	* @throws SystemException if a system exception occurred
	*/
	public static void removeUserGroup(long pk, long userGroupPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeUserGroup(pk, userGroupPK);
	}

	/**
	* Removes the association between the user and the user group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param userGroup the user group
	* @throws SystemException if a system exception occurred
	*/
	public static void removeUserGroup(long pk,
		com.liferay.portal.model.UserGroup userGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeUserGroup(pk, userGroup);
	}

	/**
	* Removes the association between the user and the user groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param userGroupPKs the primary keys of the user groups
	* @throws SystemException if a system exception occurred
	*/
	public static void removeUserGroups(long pk, long[] userGroupPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeUserGroups(pk, userGroupPKs);
	}

	/**
	* Removes the association between the user and the user groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param userGroups the user groups
	* @throws SystemException if a system exception occurred
	*/
	public static void removeUserGroups(long pk,
		java.util.List<com.liferay.portal.model.UserGroup> userGroups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeUserGroups(pk, userGroups);
	}

	/**
	* Sets the user groups associated with the user, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param userGroupPKs the primary keys of the user groups to be associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static void setUserGroups(long pk, long[] userGroupPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setUserGroups(pk, userGroupPKs);
	}

	/**
	* Sets the user groups associated with the user, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the user
	* @param userGroups the user groups to be associated with the user
	* @throws SystemException if a system exception occurred
	*/
	public static void setUserGroups(long pk,
		java.util.List<com.liferay.portal.model.UserGroup> userGroups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setUserGroups(pk, userGroups);
	}

	public static UserPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (UserPersistence)PortalBeanLocatorUtil.locate(UserPersistence.class.getName());

			ReferenceRegistry.registerReference(UserUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(UserPersistence persistence) {
	}

	private static UserPersistence _persistence;
}