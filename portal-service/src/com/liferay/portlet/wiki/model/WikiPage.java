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

package com.liferay.portlet.wiki.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the WikiPage service. Represents a row in the &quot;WikiPage&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see WikiPageModel
 * @see com.liferay.portlet.wiki.model.impl.WikiPageImpl
 * @see com.liferay.portlet.wiki.model.impl.WikiPageModelImpl
 * @generated
 */
@ProviderType
public interface WikiPage extends WikiPageModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.wiki.model.impl.WikiPageImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.portal.kernel.repository.model.Folder addAttachmentsFolder()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.wiki.model.WikiPage fetchParentPage()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.wiki.model.WikiPage fetchRedirectPage()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry> getAttachmentsFileEntries()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry> getAttachmentsFileEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int getAttachmentsFileEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	public long getAttachmentsFolderId()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getChildPages();

	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry> getDeletedAttachmentsFileEntries()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry> getDeletedAttachmentsFileEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int getDeletedAttachmentsFileEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.wiki.model.WikiNode getNode();

	public long getNodeAttachmentsFolderId()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.wiki.model.WikiPage getParentPage()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getParentPages()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.wiki.model.WikiPage getRedirectPage()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getViewableChildPages();

	public com.liferay.portlet.wiki.model.WikiPage getViewableParentPage();

	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getViewableParentPages();

	public boolean isInTrashExplicitly()
		throws com.liferay.portal.kernel.exception.SystemException;

	public void setAttachmentsFolderId(long attachmentsFolderId);
}