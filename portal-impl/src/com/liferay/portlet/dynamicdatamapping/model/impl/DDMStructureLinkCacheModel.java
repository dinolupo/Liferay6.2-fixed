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
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.dynamicdatamapping.model.DDMStructureLink;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DDMStructureLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureLink
 * @generated
 */
public class DDMStructureLinkCacheModel implements CacheModel<DDMStructureLink>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{structureLinkId=");
		sb.append(structureLinkId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", structureId=");
		sb.append(structureId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DDMStructureLink toEntityModel() {
		DDMStructureLinkImpl ddmStructureLinkImpl = new DDMStructureLinkImpl();

		ddmStructureLinkImpl.setStructureLinkId(structureLinkId);
		ddmStructureLinkImpl.setClassNameId(classNameId);
		ddmStructureLinkImpl.setClassPK(classPK);
		ddmStructureLinkImpl.setStructureId(structureId);

		ddmStructureLinkImpl.resetOriginalValues();

		return ddmStructureLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		structureLinkId = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		structureId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(structureLinkId);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);
		objectOutput.writeLong(structureId);
	}

	public long structureLinkId;
	public long classNameId;
	public long classPK;
	public long structureId;
}