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

package com.liferay.portlet.dynamicdatamapping.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DDMTemplate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DDMTemplate
 * @generated
 */
public class DDMTemplateCacheModel implements CacheModel<DDMTemplate>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", templateId=");
		sb.append(templateId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", templateKey=");
		sb.append(templateKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append(", mode=");
		sb.append(mode);
		sb.append(", language=");
		sb.append(language);
		sb.append(", script=");
		sb.append(script);
		sb.append(", cacheable=");
		sb.append(cacheable);
		sb.append(", smallImage=");
		sb.append(smallImage);
		sb.append(", smallImageId=");
		sb.append(smallImageId);
		sb.append(", smallImageURL=");
		sb.append(smallImageURL);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DDMTemplate toEntityModel() {
		DDMTemplateImpl ddmTemplateImpl = new DDMTemplateImpl();

		if (uuid == null) {
			ddmTemplateImpl.setUuid(StringPool.BLANK);
		}
		else {
			ddmTemplateImpl.setUuid(uuid);
		}

		ddmTemplateImpl.setTemplateId(templateId);
		ddmTemplateImpl.setGroupId(groupId);
		ddmTemplateImpl.setCompanyId(companyId);
		ddmTemplateImpl.setUserId(userId);

		if (userName == null) {
			ddmTemplateImpl.setUserName(StringPool.BLANK);
		}
		else {
			ddmTemplateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ddmTemplateImpl.setCreateDate(null);
		}
		else {
			ddmTemplateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ddmTemplateImpl.setModifiedDate(null);
		}
		else {
			ddmTemplateImpl.setModifiedDate(new Date(modifiedDate));
		}

		ddmTemplateImpl.setClassNameId(classNameId);
		ddmTemplateImpl.setClassPK(classPK);

		if (templateKey == null) {
			ddmTemplateImpl.setTemplateKey(StringPool.BLANK);
		}
		else {
			ddmTemplateImpl.setTemplateKey(templateKey);
		}

		if (name == null) {
			ddmTemplateImpl.setName(StringPool.BLANK);
		}
		else {
			ddmTemplateImpl.setName(name);
		}

		if (description == null) {
			ddmTemplateImpl.setDescription(StringPool.BLANK);
		}
		else {
			ddmTemplateImpl.setDescription(description);
		}

		if (type == null) {
			ddmTemplateImpl.setType(StringPool.BLANK);
		}
		else {
			ddmTemplateImpl.setType(type);
		}

		if (mode == null) {
			ddmTemplateImpl.setMode(StringPool.BLANK);
		}
		else {
			ddmTemplateImpl.setMode(mode);
		}

		if (language == null) {
			ddmTemplateImpl.setLanguage(StringPool.BLANK);
		}
		else {
			ddmTemplateImpl.setLanguage(language);
		}

		if (script == null) {
			ddmTemplateImpl.setScript(StringPool.BLANK);
		}
		else {
			ddmTemplateImpl.setScript(script);
		}

		ddmTemplateImpl.setCacheable(cacheable);
		ddmTemplateImpl.setSmallImage(smallImage);
		ddmTemplateImpl.setSmallImageId(smallImageId);

		if (smallImageURL == null) {
			ddmTemplateImpl.setSmallImageURL(StringPool.BLANK);
		}
		else {
			ddmTemplateImpl.setSmallImageURL(smallImageURL);
		}

		ddmTemplateImpl.resetOriginalValues();

		return ddmTemplateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		templateId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		templateKey = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		type = objectInput.readUTF();
		mode = objectInput.readUTF();
		language = objectInput.readUTF();
		script = objectInput.readUTF();
		cacheable = objectInput.readBoolean();
		smallImage = objectInput.readBoolean();
		smallImageId = objectInput.readLong();
		smallImageURL = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(templateId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);

		if (templateKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(templateKey);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (mode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(mode);
		}

		if (language == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(language);
		}

		if (script == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(script);
		}

		objectOutput.writeBoolean(cacheable);
		objectOutput.writeBoolean(smallImage);
		objectOutput.writeLong(smallImageId);

		if (smallImageURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(smallImageURL);
		}
	}

	public String uuid;
	public long templateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String templateKey;
	public String name;
	public String description;
	public String type;
	public String mode;
	public String language;
	public String script;
	public boolean cacheable;
	public boolean smallImage;
	public long smallImageId;
	public String smallImageURL;
}