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

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RepositoryEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RepositoryEntry
 * @generated
 */
@ProviderType
public class RepositoryEntryWrapper implements RepositoryEntry,
	ModelWrapper<RepositoryEntry> {
	public RepositoryEntryWrapper(RepositoryEntry repositoryEntry) {
		_repositoryEntry = repositoryEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return RepositoryEntry.class;
	}

	@Override
	public String getModelClassName() {
		return RepositoryEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("repositoryEntryId", getRepositoryEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("repositoryId", getRepositoryId());
		attributes.put("mappedId", getMappedId());
		attributes.put("manualCheckInRequired", getManualCheckInRequired());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long repositoryEntryId = (Long)attributes.get("repositoryEntryId");

		if (repositoryEntryId != null) {
			setRepositoryEntryId(repositoryEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long repositoryId = (Long)attributes.get("repositoryId");

		if (repositoryId != null) {
			setRepositoryId(repositoryId);
		}

		String mappedId = (String)attributes.get("mappedId");

		if (mappedId != null) {
			setMappedId(mappedId);
		}

		Boolean manualCheckInRequired = (Boolean)attributes.get(
				"manualCheckInRequired");

		if (manualCheckInRequired != null) {
			setManualCheckInRequired(manualCheckInRequired);
		}
	}

	/**
	* Returns the primary key of this repository entry.
	*
	* @return the primary key of this repository entry
	*/
	@Override
	public long getPrimaryKey() {
		return _repositoryEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this repository entry.
	*
	* @param primaryKey the primary key of this repository entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_repositoryEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this repository entry.
	*
	* @return the uuid of this repository entry
	*/
	@Override
	public java.lang.String getUuid() {
		return _repositoryEntry.getUuid();
	}

	/**
	* Sets the uuid of this repository entry.
	*
	* @param uuid the uuid of this repository entry
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_repositoryEntry.setUuid(uuid);
	}

	/**
	* Returns the repository entry ID of this repository entry.
	*
	* @return the repository entry ID of this repository entry
	*/
	@Override
	public long getRepositoryEntryId() {
		return _repositoryEntry.getRepositoryEntryId();
	}

	/**
	* Sets the repository entry ID of this repository entry.
	*
	* @param repositoryEntryId the repository entry ID of this repository entry
	*/
	@Override
	public void setRepositoryEntryId(long repositoryEntryId) {
		_repositoryEntry.setRepositoryEntryId(repositoryEntryId);
	}

	/**
	* Returns the group ID of this repository entry.
	*
	* @return the group ID of this repository entry
	*/
	@Override
	public long getGroupId() {
		return _repositoryEntry.getGroupId();
	}

	/**
	* Sets the group ID of this repository entry.
	*
	* @param groupId the group ID of this repository entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_repositoryEntry.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this repository entry.
	*
	* @return the company ID of this repository entry
	*/
	@Override
	public long getCompanyId() {
		return _repositoryEntry.getCompanyId();
	}

	/**
	* Sets the company ID of this repository entry.
	*
	* @param companyId the company ID of this repository entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_repositoryEntry.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this repository entry.
	*
	* @return the user ID of this repository entry
	*/
	@Override
	public long getUserId() {
		return _repositoryEntry.getUserId();
	}

	/**
	* Sets the user ID of this repository entry.
	*
	* @param userId the user ID of this repository entry
	*/
	@Override
	public void setUserId(long userId) {
		_repositoryEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this repository entry.
	*
	* @return the user uuid of this repository entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _repositoryEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this repository entry.
	*
	* @param userUuid the user uuid of this repository entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_repositoryEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this repository entry.
	*
	* @return the user name of this repository entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _repositoryEntry.getUserName();
	}

	/**
	* Sets the user name of this repository entry.
	*
	* @param userName the user name of this repository entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_repositoryEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this repository entry.
	*
	* @return the create date of this repository entry
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _repositoryEntry.getCreateDate();
	}

	/**
	* Sets the create date of this repository entry.
	*
	* @param createDate the create date of this repository entry
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_repositoryEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this repository entry.
	*
	* @return the modified date of this repository entry
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _repositoryEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this repository entry.
	*
	* @param modifiedDate the modified date of this repository entry
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_repositoryEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the repository ID of this repository entry.
	*
	* @return the repository ID of this repository entry
	*/
	@Override
	public long getRepositoryId() {
		return _repositoryEntry.getRepositoryId();
	}

	/**
	* Sets the repository ID of this repository entry.
	*
	* @param repositoryId the repository ID of this repository entry
	*/
	@Override
	public void setRepositoryId(long repositoryId) {
		_repositoryEntry.setRepositoryId(repositoryId);
	}

	/**
	* Returns the mapped ID of this repository entry.
	*
	* @return the mapped ID of this repository entry
	*/
	@Override
	public java.lang.String getMappedId() {
		return _repositoryEntry.getMappedId();
	}

	/**
	* Sets the mapped ID of this repository entry.
	*
	* @param mappedId the mapped ID of this repository entry
	*/
	@Override
	public void setMappedId(java.lang.String mappedId) {
		_repositoryEntry.setMappedId(mappedId);
	}

	/**
	* Returns the manual check in required of this repository entry.
	*
	* @return the manual check in required of this repository entry
	*/
	@Override
	public boolean getManualCheckInRequired() {
		return _repositoryEntry.getManualCheckInRequired();
	}

	/**
	* Returns <code>true</code> if this repository entry is manual check in required.
	*
	* @return <code>true</code> if this repository entry is manual check in required; <code>false</code> otherwise
	*/
	@Override
	public boolean isManualCheckInRequired() {
		return _repositoryEntry.isManualCheckInRequired();
	}

	/**
	* Sets whether this repository entry is manual check in required.
	*
	* @param manualCheckInRequired the manual check in required of this repository entry
	*/
	@Override
	public void setManualCheckInRequired(boolean manualCheckInRequired) {
		_repositoryEntry.setManualCheckInRequired(manualCheckInRequired);
	}

	@Override
	public boolean isNew() {
		return _repositoryEntry.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_repositoryEntry.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _repositoryEntry.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_repositoryEntry.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _repositoryEntry.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _repositoryEntry.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_repositoryEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _repositoryEntry.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_repositoryEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_repositoryEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_repositoryEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new RepositoryEntryWrapper((RepositoryEntry)_repositoryEntry.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.model.RepositoryEntry repositoryEntry) {
		return _repositoryEntry.compareTo(repositoryEntry);
	}

	@Override
	public int hashCode() {
		return _repositoryEntry.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.RepositoryEntry> toCacheModel() {
		return _repositoryEntry.toCacheModel();
	}

	@Override
	public com.liferay.portal.model.RepositoryEntry toEscapedModel() {
		return new RepositoryEntryWrapper(_repositoryEntry.toEscapedModel());
	}

	@Override
	public com.liferay.portal.model.RepositoryEntry toUnescapedModel() {
		return new RepositoryEntryWrapper(_repositoryEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _repositoryEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _repositoryEntry.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_repositoryEntry.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RepositoryEntryWrapper)) {
			return false;
		}

		RepositoryEntryWrapper repositoryEntryWrapper = (RepositoryEntryWrapper)obj;

		if (Validator.equals(_repositoryEntry,
					repositoryEntryWrapper._repositoryEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _repositoryEntry.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public RepositoryEntry getWrappedRepositoryEntry() {
		return _repositoryEntry;
	}

	@Override
	public RepositoryEntry getWrappedModel() {
		return _repositoryEntry;
	}

	@Override
	public void resetOriginalValues() {
		_repositoryEntry.resetOriginalValues();
	}

	private RepositoryEntry _repositoryEntry;
}