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

package com.liferay.portal.model;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model interface for the User service. Represents a row in the &quot;User_&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see UserModel
 * @see com.liferay.portal.model.impl.UserImpl
 * @see com.liferay.portal.model.impl.UserModelImpl
 * @generated
 */
@ProviderType
public interface User extends UserModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.UserImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void addRemotePreference(
		com.liferay.portal.kernel.util.RemotePreference remotePreference);

	public java.util.List<com.liferay.portal.model.Address> getAddresses()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.Date getBirthday()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getCompanyMx()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.Contact getContact()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getDigest(java.lang.String password);

	public java.lang.String getDisplayEmailAddress();

	/**
	* Returns the user's display URL, discounting the URL of the user's default
	* intranet site home page.
	*
	* <p>
	* The logic for the display URL to return is as follows:
	* </p>
	*
	* <ol>
	* <li>
	* If the user is the guest user, return an empty string.
	* </li>
	* <li>
	* Else, if a friendly URL is available for the user's profile, return that
	* friendly URL.
	* </li>
	* <li>
	* Otherwise, return the URL of the user's default extranet site home page.
	* </li>
	* </ol>
	*
	* @param portalURL the portal's URL
	* @param mainPath the main path
	* @return the user's display URL
	* @throws PortalException if a portal exception occurred
	* @throws SystemException if a system exception occurred
	* @deprecated As of 7.0.0, replaced by {@link #getDisplayURL(ThemeDisplay)}
	*/
	@java.lang.Deprecated()
	public java.lang.String getDisplayURL(java.lang.String portalURL,
		java.lang.String mainPath)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user's display URL.
	*
	* <p>
	* The logic for the display URL to return is as follows:
	* </p>
	*
	* <ol>
	* <li>
	* If the user is the guest user, return an empty string.
	* </li>
	* <li>
	* Else, if a friendly URL is available for the user's profile, return that
	* friendly URL.
	* </li>
	* <li>
	* Else, if <code>privateLayout</code> is <code>true</code>, return the URL
	* of the user's default intranet site home page.
	* </li>
	* <li>
	* Otherwise, return the URL of the user's default extranet site home page.
	* </li>
	* </ol>
	*
	* @param portalURL the portal's URL
	* @param mainPath the main path
	* @param privateLayout whether to use the URL of the user's default
	intranet(versus extranet)  site home page, if no friendly URL
	is available for the user's profile
	* @return the user's display URL
	* @throws PortalException if a portal exception occurred
	* @throws SystemException if a system exception occurred
	* @deprecated As of 7.0.0, replaced by {@link #getDisplayURL(ThemeDisplay)}
	*/
	@java.lang.Deprecated()
	public java.lang.String getDisplayURL(java.lang.String portalURL,
		java.lang.String mainPath, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user's display URL based on the theme display, discounting
	* the URL of the user's default intranet site home page.
	*
	* <p>
	* The logic for the display URL to return is as follows:
	* </p>
	*
	* <ol>
	* <li>
	* If the user is the guest user, return an empty string.
	* </li>
	* <li>
	* Else, if a friendly URL is available for the user's profile, return that
	* friendly URL.
	* </li>
	* <li>
	* Otherwise, return the URL of the user's default extranet site home page.
	* </li>
	* </ol>
	*
	* @param themeDisplay the theme display
	* @return the user's display URL
	* @throws PortalException if a portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getDisplayURL(
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user's display URL based on the theme display.
	*
	* <p>
	* The logic for the display URL to return is as follows:
	* </p>
	*
	* <ol>
	* <li>
	* If the user is the guest user, return an empty string.
	* </li>
	* <li>
	* Else, if a friendly URL is available for the user's profile, return that
	* friendly URL.
	* </li>
	* <li>
	* Else, if <code>privateLayout</code> is <code>true</code>, return the URL
	* of the user's default intranet site home page.
	* </li>
	* <li>
	* Otherwise, return the URL of the user's default extranet site home page.
	* </li>
	* </ol>
	*
	* @param themeDisplay the theme display
	* @param privateLayout whether to use the URL of the user's default
	intranet (versus extranet) site home page, if no friendly URL is
	available for the user's profile
	* @return the user's display URL
	* @throws PortalException if a portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getDisplayURL(
		com.liferay.portal.theme.ThemeDisplay themeDisplay,
		boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user's email addresses.
	*
	* @return the user's email addresses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.EmailAddress> getEmailAddresses()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the user is female.
	*
	* @return <code>true</code> if the user is female; <code>false</code>
	otherwise
	* @throws PortalException if a portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	public boolean getFemale()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user's full name.
	*
	* @return the user's full name
	*/
	@com.liferay.portal.kernel.bean.AutoEscape()
	public java.lang.String getFullName();

	public com.liferay.portal.model.Group getGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public long getGroupId()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public long[] getGroupIds()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Group> getGroups()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.Locale getLocale();

	public java.lang.String getLogin()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the user is male.
	*
	* @return <code>true</code> if the user is male; <code>false</code>
	otherwise
	* @throws PortalException if a portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	public boolean getMale()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Group> getMySiteGroups()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Group> getMySiteGroups(
		boolean includeControlPanel, int max)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Group> getMySiteGroups(
		int max)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Group> getMySiteGroups(
		java.lang.String[] classNames, boolean includeControlPanel, int max)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Group> getMySiteGroups(
		java.lang.String[] classNames, int max)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* @deprecated As of 6.2.0, replaced by {@link #getMySiteGroups}
	*/
	public java.util.List<com.liferay.portal.model.Group> getMySites()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* @deprecated As of 6.2.0, replaced by {@link #getMySiteGroups(boolean,
	int)}
	*/
	public java.util.List<com.liferay.portal.model.Group> getMySites(
		boolean includeControlPanel, int max)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* @deprecated As of 6.2.0, replaced by {@link #getMySiteGroups(int)}
	*/
	public java.util.List<com.liferay.portal.model.Group> getMySites(int max)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* @deprecated As of 6.2.0, replaced by {@link #getMySiteGroups(String[],
	boolean, int)}
	*/
	public java.util.List<com.liferay.portal.model.Group> getMySites(
		java.lang.String[] classNames, boolean includeControlPanel, int max)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* @deprecated As of 6.2.0, replaced by {@link #getMySiteGroups(String[],
	int)}
	*/
	public java.util.List<com.liferay.portal.model.Group> getMySites(
		java.lang.String[] classNames, int max)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public long[] getOrganizationIds()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public long[] getOrganizationIds(boolean includeAdministrative)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Organization> getOrganizations()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Organization> getOrganizations(
		boolean includeAdministrative)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean getPasswordModified();

	public com.liferay.portal.model.PasswordPolicy getPasswordPolicy()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getPasswordUnencrypted();

	public java.util.List<com.liferay.portal.model.Phone> getPhones()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getPortraitURL(
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public int getPrivateLayoutsPageCount()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public int getPublicLayoutsPageCount()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.Set<java.lang.String> getReminderQueryQuestions()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.util.RemotePreference getRemotePreference(
		java.lang.String name);

	public java.lang.Iterable<com.liferay.portal.kernel.util.RemotePreference> getRemotePreferences();

	public long[] getRoleIds()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Role> getRoles()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Group> getSiteGroups()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Group> getSiteGroups(
		boolean includeAdministrative)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public long[] getTeamIds()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Team> getTeams()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.TimeZone getTimeZone();

	public long[] getUserGroupIds()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.UserGroup> getUserGroups()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Website> getWebsites()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasCompanyMx()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean hasCompanyMx(java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean hasMySites()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean hasOrganization()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean hasPrivateLayouts()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean hasPublicLayouts()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean hasReminderQuery();

	public boolean isActive();

	public boolean isEmailAddressComplete();

	public boolean isEmailAddressVerificationComplete();

	public boolean isFemale()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isMale()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isPasswordModified();

	public boolean isReminderQueryComplete();

	public boolean isSetupComplete();

	public boolean isTermsOfUseComplete();

	public void setPasswordModified(boolean passwordModified);

	public void setPasswordUnencrypted(java.lang.String passwordUnencrypted);
}