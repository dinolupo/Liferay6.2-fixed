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

package com.liferay.portlet.messageboards.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MBCategory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBCategory
 * @generated
 */
@ProviderType
public class MBCategoryWrapper implements MBCategory, ModelWrapper<MBCategory> {
	public MBCategoryWrapper(MBCategory mbCategory) {
		_mbCategory = mbCategory;
	}

	@Override
	public Class<?> getModelClass() {
		return MBCategory.class;
	}

	@Override
	public String getModelClassName() {
		return MBCategory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("categoryId", getCategoryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentCategoryId", getParentCategoryId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("displayStyle", getDisplayStyle());
		attributes.put("threadCount", getThreadCount());
		attributes.put("messageCount", getMessageCount());
		attributes.put("lastPostDate", getLastPostDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long categoryId = (Long)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
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

		Long parentCategoryId = (Long)attributes.get("parentCategoryId");

		if (parentCategoryId != null) {
			setParentCategoryId(parentCategoryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String displayStyle = (String)attributes.get("displayStyle");

		if (displayStyle != null) {
			setDisplayStyle(displayStyle);
		}

		Integer threadCount = (Integer)attributes.get("threadCount");

		if (threadCount != null) {
			setThreadCount(threadCount);
		}

		Integer messageCount = (Integer)attributes.get("messageCount");

		if (messageCount != null) {
			setMessageCount(messageCount);
		}

		Date lastPostDate = (Date)attributes.get("lastPostDate");

		if (lastPostDate != null) {
			setLastPostDate(lastPostDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	/**
	* Returns the primary key of this message boards category.
	*
	* @return the primary key of this message boards category
	*/
	@Override
	public long getPrimaryKey() {
		return _mbCategory.getPrimaryKey();
	}

	/**
	* Sets the primary key of this message boards category.
	*
	* @param primaryKey the primary key of this message boards category
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_mbCategory.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this message boards category.
	*
	* @return the uuid of this message boards category
	*/
	@Override
	public java.lang.String getUuid() {
		return _mbCategory.getUuid();
	}

	/**
	* Sets the uuid of this message boards category.
	*
	* @param uuid the uuid of this message boards category
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_mbCategory.setUuid(uuid);
	}

	/**
	* Returns the category ID of this message boards category.
	*
	* @return the category ID of this message boards category
	*/
	@Override
	public long getCategoryId() {
		return _mbCategory.getCategoryId();
	}

	/**
	* Sets the category ID of this message boards category.
	*
	* @param categoryId the category ID of this message boards category
	*/
	@Override
	public void setCategoryId(long categoryId) {
		_mbCategory.setCategoryId(categoryId);
	}

	/**
	* Returns the group ID of this message boards category.
	*
	* @return the group ID of this message boards category
	*/
	@Override
	public long getGroupId() {
		return _mbCategory.getGroupId();
	}

	/**
	* Sets the group ID of this message boards category.
	*
	* @param groupId the group ID of this message boards category
	*/
	@Override
	public void setGroupId(long groupId) {
		_mbCategory.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this message boards category.
	*
	* @return the company ID of this message boards category
	*/
	@Override
	public long getCompanyId() {
		return _mbCategory.getCompanyId();
	}

	/**
	* Sets the company ID of this message boards category.
	*
	* @param companyId the company ID of this message boards category
	*/
	@Override
	public void setCompanyId(long companyId) {
		_mbCategory.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this message boards category.
	*
	* @return the user ID of this message boards category
	*/
	@Override
	public long getUserId() {
		return _mbCategory.getUserId();
	}

	/**
	* Sets the user ID of this message boards category.
	*
	* @param userId the user ID of this message boards category
	*/
	@Override
	public void setUserId(long userId) {
		_mbCategory.setUserId(userId);
	}

	/**
	* Returns the user uuid of this message boards category.
	*
	* @return the user uuid of this message boards category
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbCategory.getUserUuid();
	}

	/**
	* Sets the user uuid of this message boards category.
	*
	* @param userUuid the user uuid of this message boards category
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_mbCategory.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this message boards category.
	*
	* @return the user name of this message boards category
	*/
	@Override
	public java.lang.String getUserName() {
		return _mbCategory.getUserName();
	}

	/**
	* Sets the user name of this message boards category.
	*
	* @param userName the user name of this message boards category
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_mbCategory.setUserName(userName);
	}

	/**
	* Returns the create date of this message boards category.
	*
	* @return the create date of this message boards category
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _mbCategory.getCreateDate();
	}

	/**
	* Sets the create date of this message boards category.
	*
	* @param createDate the create date of this message boards category
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_mbCategory.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this message boards category.
	*
	* @return the modified date of this message boards category
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _mbCategory.getModifiedDate();
	}

	/**
	* Sets the modified date of this message boards category.
	*
	* @param modifiedDate the modified date of this message boards category
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_mbCategory.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the parent category ID of this message boards category.
	*
	* @return the parent category ID of this message boards category
	*/
	@Override
	public long getParentCategoryId() {
		return _mbCategory.getParentCategoryId();
	}

	/**
	* Sets the parent category ID of this message boards category.
	*
	* @param parentCategoryId the parent category ID of this message boards category
	*/
	@Override
	public void setParentCategoryId(long parentCategoryId) {
		_mbCategory.setParentCategoryId(parentCategoryId);
	}

	/**
	* Returns the name of this message boards category.
	*
	* @return the name of this message boards category
	*/
	@Override
	public java.lang.String getName() {
		return _mbCategory.getName();
	}

	/**
	* Sets the name of this message boards category.
	*
	* @param name the name of this message boards category
	*/
	@Override
	public void setName(java.lang.String name) {
		_mbCategory.setName(name);
	}

	/**
	* Returns the description of this message boards category.
	*
	* @return the description of this message boards category
	*/
	@Override
	public java.lang.String getDescription() {
		return _mbCategory.getDescription();
	}

	/**
	* Sets the description of this message boards category.
	*
	* @param description the description of this message boards category
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_mbCategory.setDescription(description);
	}

	/**
	* Returns the display style of this message boards category.
	*
	* @return the display style of this message boards category
	*/
	@Override
	public java.lang.String getDisplayStyle() {
		return _mbCategory.getDisplayStyle();
	}

	/**
	* Sets the display style of this message boards category.
	*
	* @param displayStyle the display style of this message boards category
	*/
	@Override
	public void setDisplayStyle(java.lang.String displayStyle) {
		_mbCategory.setDisplayStyle(displayStyle);
	}

	/**
	* Returns the thread count of this message boards category.
	*
	* @return the thread count of this message boards category
	*/
	@Override
	public int getThreadCount() {
		return _mbCategory.getThreadCount();
	}

	/**
	* Sets the thread count of this message boards category.
	*
	* @param threadCount the thread count of this message boards category
	*/
	@Override
	public void setThreadCount(int threadCount) {
		_mbCategory.setThreadCount(threadCount);
	}

	/**
	* Returns the message count of this message boards category.
	*
	* @return the message count of this message boards category
	*/
	@Override
	public int getMessageCount() {
		return _mbCategory.getMessageCount();
	}

	/**
	* Sets the message count of this message boards category.
	*
	* @param messageCount the message count of this message boards category
	*/
	@Override
	public void setMessageCount(int messageCount) {
		_mbCategory.setMessageCount(messageCount);
	}

	/**
	* Returns the last post date of this message boards category.
	*
	* @return the last post date of this message boards category
	*/
	@Override
	public java.util.Date getLastPostDate() {
		return _mbCategory.getLastPostDate();
	}

	/**
	* Sets the last post date of this message boards category.
	*
	* @param lastPostDate the last post date of this message boards category
	*/
	@Override
	public void setLastPostDate(java.util.Date lastPostDate) {
		_mbCategory.setLastPostDate(lastPostDate);
	}

	/**
	* Returns the status of this message boards category.
	*
	* @return the status of this message boards category
	*/
	@Override
	public int getStatus() {
		return _mbCategory.getStatus();
	}

	/**
	* Sets the status of this message boards category.
	*
	* @param status the status of this message boards category
	*/
	@Override
	public void setStatus(int status) {
		_mbCategory.setStatus(status);
	}

	/**
	* Returns the status by user ID of this message boards category.
	*
	* @return the status by user ID of this message boards category
	*/
	@Override
	public long getStatusByUserId() {
		return _mbCategory.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this message boards category.
	*
	* @param statusByUserId the status by user ID of this message boards category
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_mbCategory.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this message boards category.
	*
	* @return the status by user uuid of this message boards category
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbCategory.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this message boards category.
	*
	* @param statusByUserUuid the status by user uuid of this message boards category
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_mbCategory.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this message boards category.
	*
	* @return the status by user name of this message boards category
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _mbCategory.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this message boards category.
	*
	* @param statusByUserName the status by user name of this message boards category
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_mbCategory.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this message boards category.
	*
	* @return the status date of this message boards category
	*/
	@Override
	public java.util.Date getStatusDate() {
		return _mbCategory.getStatusDate();
	}

	/**
	* Sets the status date of this message boards category.
	*
	* @param statusDate the status date of this message boards category
	*/
	@Override
	public void setStatusDate(java.util.Date statusDate) {
		_mbCategory.setStatusDate(statusDate);
	}

	/**
	* Returns the trash entry created when this message boards category was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this message boards category.
	*
	* @return the trash entry created when this message boards category was moved to the Recycle Bin
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portlet.trash.model.TrashEntry getTrashEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbCategory.getTrashEntry();
	}

	/**
	* Returns the class primary key of the trash entry for this message boards category.
	*
	* @return the class primary key of the trash entry for this message boards category
	*/
	@Override
	public long getTrashEntryClassPK() {
		return _mbCategory.getTrashEntryClassPK();
	}

	/**
	* Returns the trash handler for this message boards category.
	*
	* @return the trash handler for this message boards category
	*/
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler() {
		return _mbCategory.getTrashHandler();
	}

	/**
	* Returns <code>true</code> if this message boards category is in the Recycle Bin.
	*
	* @return <code>true</code> if this message boards category is in the Recycle Bin; <code>false</code> otherwise
	*/
	@Override
	public boolean isInTrash() {
		return _mbCategory.isInTrash();
	}

	/**
	* Returns <code>true</code> if the parent of this message boards category is in the Recycle Bin.
	*
	* @return <code>true</code> if the parent of this message boards category is in the Recycle Bin; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public boolean isInTrashContainer() {
		return _mbCategory.isInTrashContainer();
	}

	/**
	* @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	*/
	@Override
	public boolean getApproved() {
		return _mbCategory.getApproved();
	}

	/**
	* Returns <code>true</code> if this message boards category is approved.
	*
	* @return <code>true</code> if this message boards category is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _mbCategory.isApproved();
	}

	/**
	* Returns <code>true</code> if this message boards category is denied.
	*
	* @return <code>true</code> if this message boards category is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _mbCategory.isDenied();
	}

	/**
	* Returns <code>true</code> if this message boards category is a draft.
	*
	* @return <code>true</code> if this message boards category is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _mbCategory.isDraft();
	}

	/**
	* Returns <code>true</code> if this message boards category is expired.
	*
	* @return <code>true</code> if this message boards category is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _mbCategory.isExpired();
	}

	/**
	* Returns <code>true</code> if this message boards category is inactive.
	*
	* @return <code>true</code> if this message boards category is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _mbCategory.isInactive();
	}

	/**
	* Returns <code>true</code> if this message boards category is incomplete.
	*
	* @return <code>true</code> if this message boards category is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _mbCategory.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this message boards category is pending.
	*
	* @return <code>true</code> if this message boards category is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _mbCategory.isPending();
	}

	/**
	* Returns <code>true</code> if this message boards category is scheduled.
	*
	* @return <code>true</code> if this message boards category is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _mbCategory.isScheduled();
	}

	/**
	* Returns the container model ID of this message boards category.
	*
	* @return the container model ID of this message boards category
	*/
	@Override
	public long getContainerModelId() {
		return _mbCategory.getContainerModelId();
	}

	/**
	* Sets the container model ID of this message boards category.
	*
	* @param containerModelId the container model ID of this message boards category
	*/
	@Override
	public void setContainerModelId(long containerModelId) {
		_mbCategory.setContainerModelId(containerModelId);
	}

	/**
	* Returns the container name of this message boards category.
	*
	* @return the container name of this message boards category
	*/
	@Override
	public java.lang.String getContainerModelName() {
		return _mbCategory.getContainerModelName();
	}

	/**
	* Returns the parent container model ID of this message boards category.
	*
	* @return the parent container model ID of this message boards category
	*/
	@Override
	public long getParentContainerModelId() {
		return _mbCategory.getParentContainerModelId();
	}

	/**
	* Sets the parent container model ID of this message boards category.
	*
	* @param parentContainerModelId the parent container model ID of this message boards category
	*/
	@Override
	public void setParentContainerModelId(long parentContainerModelId) {
		_mbCategory.setParentContainerModelId(parentContainerModelId);
	}

	@Override
	public boolean isNew() {
		return _mbCategory.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_mbCategory.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _mbCategory.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mbCategory.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _mbCategory.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _mbCategory.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_mbCategory.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _mbCategory.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_mbCategory.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_mbCategory.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_mbCategory.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new MBCategoryWrapper((MBCategory)_mbCategory.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portlet.messageboards.model.MBCategory mbCategory) {
		return _mbCategory.compareTo(mbCategory);
	}

	@Override
	public int hashCode() {
		return _mbCategory.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portlet.messageboards.model.MBCategory> toCacheModel() {
		return _mbCategory.toCacheModel();
	}

	@Override
	public com.liferay.portlet.messageboards.model.MBCategory toEscapedModel() {
		return new MBCategoryWrapper(_mbCategory.toEscapedModel());
	}

	@Override
	public com.liferay.portlet.messageboards.model.MBCategory toUnescapedModel() {
		return new MBCategoryWrapper(_mbCategory.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mbCategory.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _mbCategory.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_mbCategory.persist();
	}

	@Override
	public java.util.List<java.lang.Long> getAncestorCategoryIds()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbCategory.getAncestorCategoryIds();
	}

	@Override
	public java.util.List<com.liferay.portlet.messageboards.model.MBCategory> getAncestors()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbCategory.getAncestors();
	}

	@Override
	public com.liferay.portlet.messageboards.model.MBCategory getParentCategory()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbCategory.getParentCategory();
	}

	@Override
	public boolean isInTrashExplicitly()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbCategory.isInTrashExplicitly();
	}

	@Override
	public boolean isRoot() {
		return _mbCategory.isRoot();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MBCategoryWrapper)) {
			return false;
		}

		MBCategoryWrapper mbCategoryWrapper = (MBCategoryWrapper)obj;

		if (Validator.equals(_mbCategory, mbCategoryWrapper._mbCategory)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _mbCategory.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public MBCategory getWrappedMBCategory() {
		return _mbCategory;
	}

	@Override
	public MBCategory getWrappedModel() {
		return _mbCategory;
	}

	@Override
	public void resetOriginalValues() {
		_mbCategory.resetOriginalValues();
	}

	private MBCategory _mbCategory;
}