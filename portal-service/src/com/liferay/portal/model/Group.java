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
 * The extended model interface for the Group service. Represents a row in the &quot;Group_&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see GroupModel
 * @see com.liferay.portal.model.impl.GroupImpl
 * @see com.liferay.portal.model.impl.GroupModelImpl
 * @generated
 */
@ProviderType
public interface Group extends GroupModel, PersistedModel, TreeModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.GroupImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void clearStagingGroup();

	public java.util.List<com.liferay.portal.model.Group> getAncestors()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Group> getChildren(
		boolean site)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @deprecated As of 7.0.0, replaced by {@link
	#getChildrenWithLayouts(boolean, int, int, OrderByComparator}
	*/
	@java.lang.Deprecated()
	public java.util.List<com.liferay.portal.model.Group> getChildrenWithLayouts(
		boolean site, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.Group> getChildrenWithLayouts(
		boolean site, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int getChildrenWithLayoutsCount(boolean site)
		throws com.liferay.portal.kernel.exception.SystemException;

	public long getDefaultPrivatePlid();

	public long getDefaultPublicPlid();

	public java.lang.String getDescriptiveName()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getDescriptiveName(java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getIconURL(
		com.liferay.portal.theme.ThemeDisplay themeDisplay);

	public java.lang.String getLayoutRootNodeName(boolean privateLayout,
		java.util.Locale locale);

	public com.liferay.portal.model.Group getLiveGroup();

	public java.lang.String getLiveParentTypeSettingsProperty(
		java.lang.String key);

	public long getOrganizationId();

	public com.liferay.portal.model.Group getParentGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.util.UnicodeProperties getParentLiveGroupTypeSettingsProperties();

	public java.lang.String getPathFriendlyURL(boolean privateLayout,
		com.liferay.portal.theme.ThemeDisplay themeDisplay);

	public com.liferay.portal.model.LayoutSet getPrivateLayoutSet();

	public int getPrivateLayoutsPageCount();

	public com.liferay.portal.model.LayoutSet getPublicLayoutSet();

	public int getPublicLayoutsPageCount();

	public java.lang.String getScopeDescriptiveName(
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getScopeLabel(
		com.liferay.portal.theme.ThemeDisplay themeDisplay);

	public com.liferay.portal.model.Group getStagingGroup();

	public java.lang.String getTypeLabel();

	public com.liferay.portal.kernel.util.UnicodeProperties getTypeSettingsProperties();

	public java.lang.String getTypeSettingsProperty(java.lang.String key);

	public boolean hasAncestor(long groupId);

	public boolean hasLocalOrRemoteStagingGroup();

	public boolean hasPrivateLayouts();

	public boolean hasPublicLayouts();

	public boolean hasStagingGroup();

	/**
	* @deprecated As of 7.0.0, replaced by {@link #hasAncestor}
	*/
	public boolean isChild(long groupId);

	/**
	* @deprecated As of 6.1.0, renamed to {@link #isRegularSite}
	*/
	public boolean isCommunity();

	public boolean isCompany();

	public boolean isCompanyStagingGroup();

	public boolean isControlPanel();

	public boolean isGuest();

	public boolean isInStagingPortlet(java.lang.String portletId);

	public boolean isLayout();

	public boolean isLayoutPrototype();

	public boolean isLayoutSetPrototype();

	public boolean isLimitedToParentSiteMembers();

	public boolean isOrganization();

	public boolean isRegularSite();

	public boolean isRoot();

	public boolean isShowSite(
		com.liferay.portal.security.permission.PermissionChecker permissionChecker,
		boolean privateSite)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isStaged();

	public boolean isStagedPortlet(java.lang.String portletId);

	public boolean isStagedRemotely();

	public boolean isStagingGroup();

	public boolean isUser();

	public boolean isUserGroup();

	public boolean isUserPersonalSite();

	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties);
}