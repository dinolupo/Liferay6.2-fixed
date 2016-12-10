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

import com.liferay.portal.model.Ticket;

/**
 * The persistence interface for the ticket service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketPersistenceImpl
 * @see TicketUtil
 * @generated
 */
@ProviderType
public interface TicketPersistence extends BasePersistence<Ticket> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketUtil} to access the ticket persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the ticket where key = &#63; or throws a {@link com.liferay.portal.NoSuchTicketException} if it could not be found.
	*
	* @param key the key
	* @return the matching ticket
	* @throws com.liferay.portal.NoSuchTicketException if a matching ticket could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket findByKey(java.lang.String key)
		throws com.liferay.portal.NoSuchTicketException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching ticket, or <code>null</code> if a matching ticket could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket fetchByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching ticket, or <code>null</code> if a matching ticket could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket fetchByKey(java.lang.String key,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the ticket where key = &#63; from the database.
	*
	* @param key the key
	* @return the ticket that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket removeByKey(java.lang.String key)
		throws com.liferay.portal.NoSuchTicketException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tickets where key = &#63;.
	*
	* @param key the key
	* @return the number of matching tickets
	* @throws SystemException if a system exception occurred
	*/
	public int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tickets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching tickets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Ticket> findByC_C_T(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the tickets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.TicketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of tickets
	* @param end the upper bound of the range of tickets (not inclusive)
	* @return the range of matching tickets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Ticket> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the tickets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.TicketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of tickets
	* @param end the upper bound of the range of tickets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tickets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Ticket> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket
	* @throws com.liferay.portal.NoSuchTicketException if a matching ticket could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket findByC_C_T_First(long classNameId,
		long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchTicketException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket, or <code>null</code> if a matching ticket could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket fetchByC_C_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket
	* @throws com.liferay.portal.NoSuchTicketException if a matching ticket could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket findByC_C_T_Last(long classNameId,
		long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchTicketException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket, or <code>null</code> if a matching ticket could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket fetchByC_C_T_Last(long classNameId,
		long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the tickets before and after the current ticket in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param ticketId the primary key of the current ticket
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket
	* @throws com.liferay.portal.NoSuchTicketException if a ticket with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket[] findByC_C_T_PrevAndNext(
		long ticketId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchTicketException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the tickets where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tickets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching tickets
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the ticket in the entity cache if it is enabled.
	*
	* @param ticket the ticket
	*/
	public void cacheResult(com.liferay.portal.model.Ticket ticket);

	/**
	* Caches the tickets in the entity cache if it is enabled.
	*
	* @param tickets the tickets
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.model.Ticket> tickets);

	/**
	* Creates a new ticket with the primary key. Does not add the ticket to the database.
	*
	* @param ticketId the primary key for the new ticket
	* @return the new ticket
	*/
	public com.liferay.portal.model.Ticket create(long ticketId);

	/**
	* Removes the ticket with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketId the primary key of the ticket
	* @return the ticket that was removed
	* @throws com.liferay.portal.NoSuchTicketException if a ticket with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket remove(long ticketId)
		throws com.liferay.portal.NoSuchTicketException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.Ticket updateImpl(
		com.liferay.portal.model.Ticket ticket)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket with the primary key or throws a {@link com.liferay.portal.NoSuchTicketException} if it could not be found.
	*
	* @param ticketId the primary key of the ticket
	* @return the ticket
	* @throws com.liferay.portal.NoSuchTicketException if a ticket with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket findByPrimaryKey(long ticketId)
		throws com.liferay.portal.NoSuchTicketException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketId the primary key of the ticket
	* @return the ticket, or <code>null</code> if a ticket with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Ticket fetchByPrimaryKey(long ticketId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tickets.
	*
	* @return the tickets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Ticket> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the tickets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.TicketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tickets
	* @param end the upper bound of the range of tickets (not inclusive)
	* @return the range of tickets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Ticket> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the tickets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.TicketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tickets
	* @param end the upper bound of the range of tickets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of tickets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Ticket> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the tickets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tickets.
	*
	* @return the number of tickets
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}