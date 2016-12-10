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

package com.liferay.portlet.social.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.portlet.social.model.SocialActivitySet;

/**
 * The persistence interface for the social activity set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySetPersistenceImpl
 * @see SocialActivitySetUtil
 * @generated
 */
@ProviderType
public interface SocialActivitySetPersistence extends BasePersistence<SocialActivitySet> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SocialActivitySetUtil} to access the social activity set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the social activity sets where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activity sets where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @return the range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activity sets where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first social activity set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the first social activity set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last social activity set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the last social activity set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activity sets before and after the current social activity set in the ordered set where groupId = &#63;.
	*
	* @param activitySetId the primary key of the current social activity set
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet[] findByGroupId_PrevAndNext(
		long activitySetId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Removes all the social activity sets where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activity sets where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the social activity sets where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activity sets where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @return the range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activity sets where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first social activity set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the first social activity set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last social activity set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the last social activity set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activity sets before and after the current social activity set in the ordered set where userId = &#63;.
	*
	* @param activitySetId the primary key of the current social activity set
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet[] findByUserId_PrevAndNext(
		long activitySetId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Removes all the social activity sets where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activity sets where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the social activity sets where groupId = &#63; and userId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param type the type
	* @return the matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByG_U_T(
		long groupId, long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activity sets where groupId = &#63; and userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @return the range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByG_U_T(
		long groupId, long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activity sets where groupId = &#63; and userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByG_U_T(
		long groupId, long userId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first social activity set in the ordered set where groupId = &#63; and userId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByG_U_T_First(
		long groupId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the first social activity set in the ordered set where groupId = &#63; and userId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByG_U_T_First(
		long groupId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last social activity set in the ordered set where groupId = &#63; and userId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByG_U_T_Last(
		long groupId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the last social activity set in the ordered set where groupId = &#63; and userId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByG_U_T_Last(
		long groupId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activity sets before and after the current social activity set in the ordered set where groupId = &#63; and userId = &#63; and type = &#63;.
	*
	* @param activitySetId the primary key of the current social activity set
	* @param groupId the group ID
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet[] findByG_U_T_PrevAndNext(
		long activitySetId, long groupId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Removes all the social activity sets where groupId = &#63; and userId = &#63; and type = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_U_T(long groupId, long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activity sets where groupId = &#63; and userId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param type the type
	* @return the number of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_U_T(long groupId, long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the social activity sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByC_C_T(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activity sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @return the range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activity sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first social activity set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByC_C_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the first social activity set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByC_C_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last social activity set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByC_C_T_Last(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the last social activity set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByC_C_T_Last(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activity sets before and after the current social activity set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param activitySetId the primary key of the current social activity set
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet[] findByC_C_T_PrevAndNext(
		long activitySetId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Removes all the social activity sets where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activity sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the social activity sets where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param type the type
	* @return the matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByG_U_C_T(
		long groupId, long userId, long classNameId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activity sets where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param type the type
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @return the range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByG_U_C_T(
		long groupId, long userId, long classNameId, int type, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activity sets where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param type the type
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByG_U_C_T(
		long groupId, long userId, long classNameId, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first social activity set in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByG_U_C_T_First(
		long groupId, long userId, long classNameId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the first social activity set in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByG_U_C_T_First(
		long groupId, long userId, long classNameId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last social activity set in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByG_U_C_T_Last(
		long groupId, long userId, long classNameId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the last social activity set in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByG_U_C_T_Last(
		long groupId, long userId, long classNameId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activity sets before and after the current social activity set in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	*
	* @param activitySetId the primary key of the current social activity set
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet[] findByG_U_C_T_PrevAndNext(
		long activitySetId, long groupId, long userId, long classNameId,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Removes all the social activity sets where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_U_C_T(long groupId, long userId, long classNameId,
		int type) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activity sets where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param type the type
	* @return the number of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_U_C_T(long groupId, long userId, long classNameId,
		int type) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the social activity sets where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByU_C_C_T(
		long userId, long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activity sets where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @return the range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByU_C_C_T(
		long userId, long classNameId, long classPK, int type, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activity sets where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByU_C_C_T(
		long userId, long classNameId, long classPK, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first social activity set in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByU_C_C_T_First(
		long userId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the first social activity set in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByU_C_C_T_First(
		long userId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last social activity set in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByU_C_C_T_Last(
		long userId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the last social activity set in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByU_C_C_T_Last(
		long userId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activity sets before and after the current social activity set in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param activitySetId the primary key of the current social activity set
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet[] findByU_C_C_T_PrevAndNext(
		long activitySetId, long userId, long classNameId, long classPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Removes all the social activity sets where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_C_C_T(long userId, long classNameId, long classPK,
		int type) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activity sets where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_C_C_T(long userId, long classNameId, long classPK,
		int type) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the social activity set in the entity cache if it is enabled.
	*
	* @param socialActivitySet the social activity set
	*/
	public void cacheResult(
		com.liferay.portlet.social.model.SocialActivitySet socialActivitySet);

	/**
	* Caches the social activity sets in the entity cache if it is enabled.
	*
	* @param socialActivitySets the social activity sets
	*/
	public void cacheResult(
		java.util.List<com.liferay.portlet.social.model.SocialActivitySet> socialActivitySets);

	/**
	* Creates a new social activity set with the primary key. Does not add the social activity set to the database.
	*
	* @param activitySetId the primary key for the new social activity set
	* @return the new social activity set
	*/
	public com.liferay.portlet.social.model.SocialActivitySet create(
		long activitySetId);

	/**
	* Removes the social activity set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set that was removed
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet remove(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	public com.liferay.portlet.social.model.SocialActivitySet updateImpl(
		com.liferay.portlet.social.model.SocialActivitySet socialActivitySet)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activity set with the primary key or throws a {@link com.liferay.portlet.social.NoSuchActivitySetException} if it could not be found.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set
	* @throws com.liferay.portlet.social.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet findByPrimaryKey(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySetException;

	/**
	* Returns the social activity set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set, or <code>null</code> if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivitySet fetchByPrimaryKey(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the social activity sets.
	*
	* @return the social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activity sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @return the range of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activity sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the social activity sets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activity sets.
	*
	* @return the number of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}