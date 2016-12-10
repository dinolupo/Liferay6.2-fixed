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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.ResourcePermissionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.http.ResourcePermissionServiceSoap
 * @generated
 */
public class ResourcePermissionSoap implements Serializable {
	public static ResourcePermissionSoap toSoapModel(ResourcePermission model) {
		ResourcePermissionSoap soapModel = new ResourcePermissionSoap();

		soapModel.setResourcePermissionId(model.getResourcePermissionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setName(model.getName());
		soapModel.setScope(model.getScope());
		soapModel.setPrimKey(model.getPrimKey());
		soapModel.setRoleId(model.getRoleId());
		soapModel.setOwnerId(model.getOwnerId());
		soapModel.setActionIds(model.getActionIds());

		return soapModel;
	}

	public static ResourcePermissionSoap[] toSoapModels(
		ResourcePermission[] models) {
		ResourcePermissionSoap[] soapModels = new ResourcePermissionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ResourcePermissionSoap[][] toSoapModels(
		ResourcePermission[][] models) {
		ResourcePermissionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ResourcePermissionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ResourcePermissionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ResourcePermissionSoap[] toSoapModels(
		List<ResourcePermission> models) {
		List<ResourcePermissionSoap> soapModels = new ArrayList<ResourcePermissionSoap>(models.size());

		for (ResourcePermission model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ResourcePermissionSoap[soapModels.size()]);
	}

	public ResourcePermissionSoap() {
	}

	public long getPrimaryKey() {
		return _resourcePermissionId;
	}

	public void setPrimaryKey(long pk) {
		setResourcePermissionId(pk);
	}

	public long getResourcePermissionId() {
		return _resourcePermissionId;
	}

	public void setResourcePermissionId(long resourcePermissionId) {
		_resourcePermissionId = resourcePermissionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getScope() {
		return _scope;
	}

	public void setScope(int scope) {
		_scope = scope;
	}

	public String getPrimKey() {
		return _primKey;
	}

	public void setPrimKey(String primKey) {
		_primKey = primKey;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	public long getOwnerId() {
		return _ownerId;
	}

	public void setOwnerId(long ownerId) {
		_ownerId = ownerId;
	}

	public long getActionIds() {
		return _actionIds;
	}

	public void setActionIds(long actionIds) {
		_actionIds = actionIds;
	}

	private long _resourcePermissionId;
	private long _companyId;
	private String _name;
	private int _scope;
	private String _primKey;
	private long _roleId;
	private long _ownerId;
	private long _actionIds;
}