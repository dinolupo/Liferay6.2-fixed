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

package com.liferay.portal.repository.proxy;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.Map;

/**
 * @author Mika Koivisto
 */
public class FileVersionProxyBean
	extends RepositoryModelProxyBean implements FileVersion {

	public FileVersionProxyBean(
		FileVersion fileVersion, ClassLoader classLoader) {

		super(classLoader);

		_fileVersion = fileVersion;
	}

	@Override
	public Object clone() {
		FileVersionProxyBean fileVersionProxyBean = newFileVersionProxyBean(
			_fileVersion);

		fileVersionProxyBean.setCompanyId(getCompanyId());
		fileVersionProxyBean.setCreateDate(getCreateDate());
		fileVersionProxyBean.setGroupId(getGroupId());
		fileVersionProxyBean.setModifiedDate(getModifiedDate());
		fileVersionProxyBean.setPrimaryKeyObj(getPrimaryKeyObj());
		fileVersionProxyBean.setUserId(getUserId());
		fileVersionProxyBean.setUserName(getUserName());

		try {
			fileVersionProxyBean.setUserUuid(getUserUuid());
		}
		catch (SystemException se) {
		}

		fileVersionProxyBean.setUuid(getUuid());

		return fileVersionProxyBean;
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return _fileVersion.getAttributes();
	}

	@Override
	public String getChangeLog() {
		return _fileVersion.getChangeLog();
	}

	@Override
	public long getCompanyId() {
		return _fileVersion.getCompanyId();
	}

	@Override
	public InputStream getContentStream(boolean incrementCounter)
		throws PortalException, SystemException {

		return _fileVersion.getContentStream(incrementCounter);
	}

	@Override
	public Date getCreateDate() {
		return _fileVersion.getCreateDate();
	}

	@Override
	public String getDescription() {
		return _fileVersion.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		ExpandoBridge expandoBridge = _fileVersion.getExpandoBridge();

		return (ExpandoBridge)newProxyInstance(
			expandoBridge, ExpandoBridge.class);
	}

	@Override
	public String getExtension() {
		return _fileVersion.getExtension();
	}

	@Override
	public String getExtraSettings() {
		return _fileVersion.getExtraSettings();
	}

	@Override
	public FileEntry getFileEntry() throws PortalException, SystemException {
		FileEntry fileEntry = _fileVersion.getFileEntry();

		return newFileEntryProxyBean(fileEntry);
	}

	@Override
	public long getFileEntryId() {
		return _fileVersion.getFileEntryId();
	}

	@Override
	public long getFileVersionId() {
		return _fileVersion.getFileVersionId();
	}

	@Override
	public long getGroupId() {
		return _fileVersion.getGroupId();
	}

	@Override
	public String getIcon() {
		return _fileVersion.getIcon();
	}

	@Override
	public String getMimeType() {
		return _fileVersion.getMimeType();
	}

	@Override
	public Object getModel() {
		return _fileVersion.getModel();
	}

	@Override
	public Class<?> getModelClass() {
		return _fileVersion.getModelClass();
	}

	@Override
	public String getModelClassName() {
		return _fileVersion.getModelClassName();
	}

	@Override
	public Date getModifiedDate() {
		return _fileVersion.getModifiedDate();
	}

	@Override
	public long getPrimaryKey() {
		return _fileVersion.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileVersion.getPrimaryKeyObj();
	}

	@Override
	public long getRepositoryId() {
		return _fileVersion.getRepositoryId();
	}

	@Override
	public long getSize() {
		return _fileVersion.getSize();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _fileVersion.getStagedModelType();
	}

	@Override
	public int getStatus() {
		return _fileVersion.getStatus();
	}

	@Override
	public long getStatusByUserId() {
		return _fileVersion.getStatusByUserId();
	}

	@Override
	public String getStatusByUserName() {
		return _fileVersion.getStatusByUserName();
	}

	@Override
	public String getStatusByUserUuid() throws SystemException {
		return _fileVersion.getStatusByUserUuid();
	}

	@Override
	public Date getStatusDate() {
		return _fileVersion.getStatusDate();
	}

	@Override
	public String getTitle() {
		return _fileVersion.getTitle();
	}

	@Override
	public long getUserId() {
		return _fileVersion.getUserId();
	}

	@Override
	public String getUserName() {
		return _fileVersion.getUserName();
	}

	@Override
	public String getUserUuid() throws SystemException {
		return _fileVersion.getUserUuid();
	}

	@Override
	public String getUuid() {
		return _fileVersion.getUuid();
	}

	@Override
	public String getVersion() {
		return _fileVersion.getVersion();
	}

	@Override
	public boolean isApproved() {
		return _fileVersion.isApproved();
	}

	@Override
	public boolean isDefaultRepository() {
		return _fileVersion.isDefaultRepository();
	}

	@Override
	public boolean isDraft() {
		return _fileVersion.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _fileVersion.isEscapedModel();
	}

	@Override
	public boolean isExpired() {
		return _fileVersion.isExpired();
	}

	@Override
	public boolean isPending() {
		return _fileVersion.isPending();
	}

	@Override
	public void setCompanyId(long companyId) {
		_fileVersion.setCompanyId(companyId);
	}

	@Override
	public void setCreateDate(Date date) {
		_fileVersion.setCreateDate(date);
	}

	@Override
	public void setGroupId(long groupId) {
		_fileVersion.setGroupId(groupId);
	}

	@Override
	public void setModifiedDate(Date date) {
		_fileVersion.setModifiedDate(date);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_fileVersion.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public void setUserId(long userId) {
		_fileVersion.setUserId(userId);
	}

	@Override
	public void setUserName(String userName) {
		_fileVersion.setUserName(userName);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_fileVersion.setUserUuid(userUuid);
	}

	@Override
	public void setUuid(String uuid) {
		_fileVersion.setUuid(uuid);
	}

	@Override
	public FileVersion toEscapedModel() {
		FileVersion fileVersion = _fileVersion.toEscapedModel();

		return newFileVersionProxyBean(fileVersion);
	}

	@Override
	public FileVersion toUnescapedModel() {
		FileVersion fileVersion = _fileVersion.toUnescapedModel();

		return newFileVersionProxyBean(fileVersion);
	}

	private FileVersion _fileVersion;

}