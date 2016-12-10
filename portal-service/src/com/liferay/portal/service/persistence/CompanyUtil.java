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
import com.liferay.portal.model.Company;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the company service. This utility wraps {@link CompanyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CompanyPersistence
 * @see CompanyPersistenceImpl
 * @generated
 */
@ProviderType
public class CompanyUtil {
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
	public static void clearCache(Company company) {
		getPersistence().clearCache(company);
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
	public static List<Company> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Company> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Company> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Company update(Company company) throws SystemException {
		return getPersistence().update(company);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Company update(Company company, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(company, serviceContext);
	}

	/**
	* Returns the company where webId = &#63; or throws a {@link com.liferay.portal.NoSuchCompanyException} if it could not be found.
	*
	* @param webId the web ID
	* @return the matching company
	* @throws com.liferay.portal.NoSuchCompanyException if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company findByWebId(
		java.lang.String webId)
		throws com.liferay.portal.NoSuchCompanyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByWebId(webId);
	}

	/**
	* Returns the company where webId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param webId the web ID
	* @return the matching company, or <code>null</code> if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company fetchByWebId(
		java.lang.String webId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByWebId(webId);
	}

	/**
	* Returns the company where webId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param webId the web ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching company, or <code>null</code> if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company fetchByWebId(
		java.lang.String webId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByWebId(webId, retrieveFromCache);
	}

	/**
	* Removes the company where webId = &#63; from the database.
	*
	* @param webId the web ID
	* @return the company that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company removeByWebId(
		java.lang.String webId)
		throws com.liferay.portal.NoSuchCompanyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByWebId(webId);
	}

	/**
	* Returns the number of companies where webId = &#63;.
	*
	* @param webId the web ID
	* @return the number of matching companies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByWebId(java.lang.String webId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByWebId(webId);
	}

	/**
	* Returns the company where mx = &#63; or throws a {@link com.liferay.portal.NoSuchCompanyException} if it could not be found.
	*
	* @param mx the mx
	* @return the matching company
	* @throws com.liferay.portal.NoSuchCompanyException if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company findByMx(java.lang.String mx)
		throws com.liferay.portal.NoSuchCompanyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMx(mx);
	}

	/**
	* Returns the company where mx = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param mx the mx
	* @return the matching company, or <code>null</code> if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company fetchByMx(
		java.lang.String mx)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByMx(mx);
	}

	/**
	* Returns the company where mx = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param mx the mx
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching company, or <code>null</code> if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company fetchByMx(
		java.lang.String mx, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByMx(mx, retrieveFromCache);
	}

	/**
	* Removes the company where mx = &#63; from the database.
	*
	* @param mx the mx
	* @return the company that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company removeByMx(
		java.lang.String mx)
		throws com.liferay.portal.NoSuchCompanyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByMx(mx);
	}

	/**
	* Returns the number of companies where mx = &#63;.
	*
	* @param mx the mx
	* @return the number of matching companies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMx(java.lang.String mx)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByMx(mx);
	}

	/**
	* Returns the company where logoId = &#63; or throws a {@link com.liferay.portal.NoSuchCompanyException} if it could not be found.
	*
	* @param logoId the logo ID
	* @return the matching company
	* @throws com.liferay.portal.NoSuchCompanyException if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company findByLogoId(long logoId)
		throws com.liferay.portal.NoSuchCompanyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByLogoId(logoId);
	}

	/**
	* Returns the company where logoId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param logoId the logo ID
	* @return the matching company, or <code>null</code> if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company fetchByLogoId(long logoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByLogoId(logoId);
	}

	/**
	* Returns the company where logoId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param logoId the logo ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching company, or <code>null</code> if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company fetchByLogoId(long logoId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByLogoId(logoId, retrieveFromCache);
	}

	/**
	* Removes the company where logoId = &#63; from the database.
	*
	* @param logoId the logo ID
	* @return the company that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company removeByLogoId(long logoId)
		throws com.liferay.portal.NoSuchCompanyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByLogoId(logoId);
	}

	/**
	* Returns the number of companies where logoId = &#63;.
	*
	* @param logoId the logo ID
	* @return the number of matching companies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByLogoId(long logoId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByLogoId(logoId);
	}

	/**
	* Returns all the companies where system = &#63;.
	*
	* @param system the system
	* @return the matching companies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Company> findBySystem(
		boolean system)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySystem(system);
	}

	/**
	* Returns a range of all the companies where system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.CompanyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param system the system
	* @param start the lower bound of the range of companies
	* @param end the upper bound of the range of companies (not inclusive)
	* @return the range of matching companies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Company> findBySystem(
		boolean system, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySystem(system, start, end);
	}

	/**
	* Returns an ordered range of all the companies where system = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.CompanyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param system the system
	* @param start the lower bound of the range of companies
	* @param end the upper bound of the range of companies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching companies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Company> findBySystem(
		boolean system, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySystem(system, start, end, orderByComparator);
	}

	/**
	* Returns the first company in the ordered set where system = &#63;.
	*
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company
	* @throws com.liferay.portal.NoSuchCompanyException if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company findBySystem_First(
		boolean system,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchCompanyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySystem_First(system, orderByComparator);
	}

	/**
	* Returns the first company in the ordered set where system = &#63;.
	*
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company, or <code>null</code> if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company fetchBySystem_First(
		boolean system,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBySystem_First(system, orderByComparator);
	}

	/**
	* Returns the last company in the ordered set where system = &#63;.
	*
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company
	* @throws com.liferay.portal.NoSuchCompanyException if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company findBySystem_Last(
		boolean system,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchCompanyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySystem_Last(system, orderByComparator);
	}

	/**
	* Returns the last company in the ordered set where system = &#63;.
	*
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company, or <code>null</code> if a matching company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company fetchBySystem_Last(
		boolean system,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBySystem_Last(system, orderByComparator);
	}

	/**
	* Returns the companies before and after the current company in the ordered set where system = &#63;.
	*
	* @param companyId the primary key of the current company
	* @param system the system
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company
	* @throws com.liferay.portal.NoSuchCompanyException if a company with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company[] findBySystem_PrevAndNext(
		long companyId, boolean system,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchCompanyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySystem_PrevAndNext(companyId, system,
			orderByComparator);
	}

	/**
	* Removes all the companies where system = &#63; from the database.
	*
	* @param system the system
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySystem(boolean system)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySystem(system);
	}

	/**
	* Returns the number of companies where system = &#63;.
	*
	* @param system the system
	* @return the number of matching companies
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySystem(boolean system)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySystem(system);
	}

	/**
	* Caches the company in the entity cache if it is enabled.
	*
	* @param company the company
	*/
	public static void cacheResult(com.liferay.portal.model.Company company) {
		getPersistence().cacheResult(company);
	}

	/**
	* Caches the companies in the entity cache if it is enabled.
	*
	* @param companies the companies
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.Company> companies) {
		getPersistence().cacheResult(companies);
	}

	/**
	* Creates a new company with the primary key. Does not add the company to the database.
	*
	* @param companyId the primary key for the new company
	* @return the new company
	*/
	public static com.liferay.portal.model.Company create(long companyId) {
		return getPersistence().create(companyId);
	}

	/**
	* Removes the company with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyId the primary key of the company
	* @return the company that was removed
	* @throws com.liferay.portal.NoSuchCompanyException if a company with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company remove(long companyId)
		throws com.liferay.portal.NoSuchCompanyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(companyId);
	}

	public static com.liferay.portal.model.Company updateImpl(
		com.liferay.portal.model.Company company)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(company);
	}

	/**
	* Returns the company with the primary key or throws a {@link com.liferay.portal.NoSuchCompanyException} if it could not be found.
	*
	* @param companyId the primary key of the company
	* @return the company
	* @throws com.liferay.portal.NoSuchCompanyException if a company with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company findByPrimaryKey(
		long companyId)
		throws com.liferay.portal.NoSuchCompanyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(companyId);
	}

	/**
	* Returns the company with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyId the primary key of the company
	* @return the company, or <code>null</code> if a company with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.Company fetchByPrimaryKey(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(companyId);
	}

	/**
	* Returns all the companies.
	*
	* @return the companies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Company> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the companies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.CompanyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of companies
	* @param end the upper bound of the range of companies (not inclusive)
	* @return the range of companies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Company> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the companies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.CompanyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of companies
	* @param end the upper bound of the range of companies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of companies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.Company> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the companies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of companies.
	*
	* @return the number of companies
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CompanyPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CompanyPersistence)PortalBeanLocatorUtil.locate(CompanyPersistence.class.getName());

			ReferenceRegistry.registerReference(CompanyUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(CompanyPersistence persistence) {
	}

	private static CompanyPersistence _persistence;
}