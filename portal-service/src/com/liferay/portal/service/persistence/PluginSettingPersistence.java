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

import com.liferay.portal.model.PluginSetting;

/**
 * The persistence interface for the plugin setting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PluginSettingPersistenceImpl
 * @see PluginSettingUtil
 * @generated
 */
@ProviderType
public interface PluginSettingPersistence extends BasePersistence<PluginSetting> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PluginSettingUtil} to access the plugin setting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the plugin settings where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching plugin settings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.PluginSetting> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the plugin settings where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PluginSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of plugin settings
	* @param end the upper bound of the range of plugin settings (not inclusive)
	* @return the range of matching plugin settings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.PluginSetting> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the plugin settings where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PluginSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of plugin settings
	* @param end the upper bound of the range of plugin settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching plugin settings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.PluginSetting> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first plugin setting in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching plugin setting
	* @throws com.liferay.portal.NoSuchPluginSettingException if a matching plugin setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchPluginSettingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first plugin setting in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching plugin setting, or <code>null</code> if a matching plugin setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last plugin setting in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching plugin setting
	* @throws com.liferay.portal.NoSuchPluginSettingException if a matching plugin setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchPluginSettingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last plugin setting in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching plugin setting, or <code>null</code> if a matching plugin setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the plugin settings before and after the current plugin setting in the ordered set where companyId = &#63;.
	*
	* @param pluginSettingId the primary key of the current plugin setting
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next plugin setting
	* @throws com.liferay.portal.NoSuchPluginSettingException if a plugin setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting[] findByCompanyId_PrevAndNext(
		long pluginSettingId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchPluginSettingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the plugin settings where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of plugin settings where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching plugin settings
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the plugin setting where companyId = &#63; and pluginId = &#63; and pluginType = &#63; or throws a {@link com.liferay.portal.NoSuchPluginSettingException} if it could not be found.
	*
	* @param companyId the company ID
	* @param pluginId the plugin ID
	* @param pluginType the plugin type
	* @return the matching plugin setting
	* @throws com.liferay.portal.NoSuchPluginSettingException if a matching plugin setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting findByC_I_T(long companyId,
		java.lang.String pluginId, java.lang.String pluginType)
		throws com.liferay.portal.NoSuchPluginSettingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the plugin setting where companyId = &#63; and pluginId = &#63; and pluginType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param pluginId the plugin ID
	* @param pluginType the plugin type
	* @return the matching plugin setting, or <code>null</code> if a matching plugin setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting fetchByC_I_T(long companyId,
		java.lang.String pluginId, java.lang.String pluginType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the plugin setting where companyId = &#63; and pluginId = &#63; and pluginType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param pluginId the plugin ID
	* @param pluginType the plugin type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching plugin setting, or <code>null</code> if a matching plugin setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting fetchByC_I_T(long companyId,
		java.lang.String pluginId, java.lang.String pluginType,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the plugin setting where companyId = &#63; and pluginId = &#63; and pluginType = &#63; from the database.
	*
	* @param companyId the company ID
	* @param pluginId the plugin ID
	* @param pluginType the plugin type
	* @return the plugin setting that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting removeByC_I_T(
		long companyId, java.lang.String pluginId, java.lang.String pluginType)
		throws com.liferay.portal.NoSuchPluginSettingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of plugin settings where companyId = &#63; and pluginId = &#63; and pluginType = &#63;.
	*
	* @param companyId the company ID
	* @param pluginId the plugin ID
	* @param pluginType the plugin type
	* @return the number of matching plugin settings
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_I_T(long companyId, java.lang.String pluginId,
		java.lang.String pluginType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the plugin setting in the entity cache if it is enabled.
	*
	* @param pluginSetting the plugin setting
	*/
	public void cacheResult(
		com.liferay.portal.model.PluginSetting pluginSetting);

	/**
	* Caches the plugin settings in the entity cache if it is enabled.
	*
	* @param pluginSettings the plugin settings
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.model.PluginSetting> pluginSettings);

	/**
	* Creates a new plugin setting with the primary key. Does not add the plugin setting to the database.
	*
	* @param pluginSettingId the primary key for the new plugin setting
	* @return the new plugin setting
	*/
	public com.liferay.portal.model.PluginSetting create(long pluginSettingId);

	/**
	* Removes the plugin setting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pluginSettingId the primary key of the plugin setting
	* @return the plugin setting that was removed
	* @throws com.liferay.portal.NoSuchPluginSettingException if a plugin setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting remove(long pluginSettingId)
		throws com.liferay.portal.NoSuchPluginSettingException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.PluginSetting updateImpl(
		com.liferay.portal.model.PluginSetting pluginSetting)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the plugin setting with the primary key or throws a {@link com.liferay.portal.NoSuchPluginSettingException} if it could not be found.
	*
	* @param pluginSettingId the primary key of the plugin setting
	* @return the plugin setting
	* @throws com.liferay.portal.NoSuchPluginSettingException if a plugin setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting findByPrimaryKey(
		long pluginSettingId)
		throws com.liferay.portal.NoSuchPluginSettingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the plugin setting with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pluginSettingId the primary key of the plugin setting
	* @return the plugin setting, or <code>null</code> if a plugin setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting fetchByPrimaryKey(
		long pluginSettingId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the plugin settings.
	*
	* @return the plugin settings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.PluginSetting> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the plugin settings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PluginSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of plugin settings
	* @param end the upper bound of the range of plugin settings (not inclusive)
	* @return the range of plugin settings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.PluginSetting> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the plugin settings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PluginSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of plugin settings
	* @param end the upper bound of the range of plugin settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of plugin settings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.PluginSetting> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the plugin settings from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of plugin settings.
	*
	* @return the number of plugin settings
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}