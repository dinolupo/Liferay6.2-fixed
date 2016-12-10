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

package com.liferay.portal.tools.servicebuilder;

import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ResourceActionsUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class Entity {

	public static final String DEFAULT_DATA_SOURCE = "liferayDataSource";

	public static final String DEFAULT_SESSION_FACTORY =
		"liferaySessionFactory";

	public static final String DEFAULT_TX_MANAGER = "liferayTransactionManager";

	public static final Accessor<Entity, String> NAME_ACCESSOR =
		new Accessor<Entity, String>() {

			@Override
			public String get(Entity entity) {
				return entity.getName();
			}

		};

	public static EntityColumn getColumn(
		String name, List<EntityColumn> columnList) {

		for (EntityColumn col : columnList) {
			if (name.equals(col.getName())) {
				return col;
			}
		}

		throw new RuntimeException("Column " + name + " not found");
	}

	public static boolean hasColumn(
		String name, List<EntityColumn> columnList) {

		return hasColumn(name, null, columnList);
	}

	public static boolean hasColumn(
		String name, String type, List<EntityColumn> columnList) {

		int index = columnList.indexOf(new EntityColumn(name));

		if (index != -1) {
			EntityColumn col = columnList.get(index);

			if ((type == null) || type.equals(col.getType())) {
				return true;
			}
		}

		return false;
	}

	public Entity(String name) {
		this(
			null, null, null, name, null, null, null, false, false, false, true,
			null, null, null, null, null, true, false, false, false, null, null,
			null, null, null, null, null, null, null);
	}

	public Entity(
		String packagePath, String portletName, String portletShortName,
		String name, String humanName, String table, String alias, boolean uuid,
		boolean uuidAccessor, boolean localService, boolean remoteService,
		String persistenceClass, String finderClass, String dataSource,
		String sessionFactory, String txManager, boolean cacheEnabled,
		boolean jsonEnabled, boolean trashEnabled, boolean deprecated,
		List<EntityColumn> pkList, List<EntityColumn> regularColList,
		List<EntityColumn> blobList, List<EntityColumn> collectionList,
		List<EntityColumn> columnList, EntityOrder order,
		List<EntityFinder> finderList, List<Entity> referenceList,
		List<String> txRequiredList) {

		_packagePath = packagePath;
		_portletName = portletName;
		_portletShortName = portletShortName;
		_name = name;
		_humanName = GetterUtil.getString(
			humanName, ServiceBuilder.toHumanName(name));
		_table = table;
		_alias = alias;
		_uuid = uuid;
		_uuidAccessor = uuidAccessor;
		_localService = localService;
		_remoteService = remoteService;
		_persistenceClass = persistenceClass;
		_finderClass = finderClass;
		_dataSource = GetterUtil.getString(dataSource, DEFAULT_DATA_SOURCE);
		_sessionFactory = GetterUtil.getString(
			sessionFactory, DEFAULT_SESSION_FACTORY);
		_txManager = GetterUtil.getString(txManager, DEFAULT_TX_MANAGER);
		_cacheEnabled = cacheEnabled;
		_jsonEnabled = jsonEnabled;
		_trashEnabled = trashEnabled;
		_deprecated = deprecated;
		_pkList = pkList;
		_regularColList = regularColList;
		_blobList = blobList;
		_collectionList = collectionList;
		_columnList = columnList;
		_order = order;
		_finderList = finderList;
		_referenceList = referenceList;
		_txRequiredList = txRequiredList;

		if (_finderList != null) {
			Set<EntityColumn> finderColumns = new HashSet<EntityColumn>();

			for (EntityFinder entityFinder : _finderList) {
				finderColumns.addAll(entityFinder.getColumns());
			}

			_finderColumnsList = new ArrayList<EntityColumn>(finderColumns);

			Collections.sort(_finderColumnsList);
		}
		else {
			_finderColumnsList = Collections.emptyList();
		}

		if ((_blobList != null) && !_blobList.isEmpty()) {
			for (EntityColumn col : _blobList) {
				if (!col.isLazy()) {
					_cacheEnabled = false;

					break;
				}
			}
		}

		if ((_columnList != null) && !_columnList.isEmpty()) {
			for (EntityColumn col : _columnList) {
				if (col.isContainerModel() || col.isParentContainerModel()) {
					_containerModel = true;

					break;
				}
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Entity)) {
			return false;
		}

		Entity entity = (Entity)obj;

		String name = entity.getName();

		if (_name.equals(name)) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getAlias() {
		return _alias;
	}

	public List<EntityColumn> getBadNamedColumnsList() {
		List<EntityColumn> badNamedColumnsList = ListUtil.copy(_columnList);

		Iterator<EntityColumn> itr = badNamedColumnsList.iterator();

		while (itr.hasNext()) {
			EntityColumn col = itr.next();

			String name = col.getName();

			if (name.equals(col.getDBName())) {
				itr.remove();
			}
		}

		return badNamedColumnsList;
	}

	public List<EntityColumn> getBlobList() {
		return _blobList;
	}

	public List<EntityFinder> getCollectionFinderList() {
		List<EntityFinder> finderList = ListUtil.copy(_finderList);

		Iterator<EntityFinder> itr = finderList.iterator();

		while (itr.hasNext()) {
			EntityFinder finder = itr.next();

			if (!finder.isCollection()) {
				itr.remove();
			}
		}

		return finderList;
	}

	public List<EntityColumn> getCollectionList() {
		return _collectionList;
	}

	public EntityColumn getColumn(String name) {
		return getColumn(name, _columnList);
	}

	public EntityColumn getColumnByMappingTable(String mappingTable) {
		for (EntityColumn col : _columnList) {
			if ((col.getMappingTable() != null) &&
				col.getMappingTable().equals(mappingTable)) {

				return col;
			}
		}

		return null;
	}

	public List<EntityColumn> getColumnList() {
		return _columnList;
	}

	public String getDataSource() {
		return _dataSource;
	}

	public EntityColumn getFilterPKColumn() {
		for (EntityColumn col : _columnList) {
			if (col.isFilterPrimary()) {
				return col;
			}
		}

		return _getPKColumn();
	}

	public String getFinderClass() {
		return _finderClass;
	}

	public List<EntityColumn> getFinderColumnsList() {
		return _finderColumnsList;
	}

	public List<EntityFinder> getFinderList() {
		return _finderList;
	}

	public String getHumanName() {
		return _humanName;
	}

	public String getHumanNames() {
		return TextFormatter.formatPlural(_humanName);
	}

	public String getName() {
		return _name;
	}

	public String getNames() {
		return TextFormatter.formatPlural(_name);
	}

	public EntityOrder getOrder() {
		return _order;
	}

	public String getPackagePath() {
		return _packagePath;
	}

	public List<String> getParentTransients() {
		return _parentTransients;
	}

	public String getPersistenceClass() {
		return _persistenceClass;
	}

	public String getPKClassName() {
		if (hasCompoundPK()) {
			return _name + "PK";
		}

		EntityColumn col = _getPKColumn();

		return col.getType();
	}

	public String getPKDBName() {
		if (hasCompoundPK()) {
			return getVarName() + "PK";
		}

		EntityColumn col = _getPKColumn();

		return col.getDBName();
	}

	public List<EntityColumn> getPKList() {
		return _pkList;
	}

	public String getPKVarName() {
		if (hasCompoundPK()) {
			return getVarName() + "PK";
		}

		EntityColumn col = _getPKColumn();

		return col.getName();
	}

	public String getPKVarNames() {
		if (hasCompoundPK()) {
			return getVarName() + "PKs";
		}

		EntityColumn col = _getPKColumn();

		return col.getNames();
	}

	public String getPortletName() {
		return _portletName;
	}

	public String getPortletShortName() {
		return _portletShortName;
	}

	public List<Entity> getReferenceList() {
		return _referenceList;
	}

	public List<EntityColumn> getRegularColList() {
		return _regularColList;
	}

	public String getSessionFactory() {
		return _sessionFactory;
	}

	public String getShortName() {
		if (_name.startsWith(_portletShortName)) {
			return _name.substring(_portletShortName.length());
		}
		else {
			return _name;
		}
	}

	public String getSpringPropertyName() {
		return TextFormatter.format(_name, TextFormatter.L);
	}

	public String getTable() {
		return _table;
	}

	public List<String> getTransients() {
		return _transients;
	}

	public String getTXManager() {
		return _txManager;
	}

	public List<String> getTxRequiredList() {
		return _txRequiredList;
	}

	public List<EntityFinder> getUniqueFinderList() {
		List<EntityFinder> finderList = ListUtil.copy(_finderList);

		Iterator<EntityFinder> itr = finderList.iterator();

		while (itr.hasNext()) {
			EntityFinder finder = itr.next();

			if (finder.isCollection() && !finder.isUnique()) {
				itr.remove();
			}
		}

		return finderList;
	}

	public String getVarName() {
		return TextFormatter.format(_name, TextFormatter.I);
	}

	public String getVarNames() {
		return TextFormatter.formatPlural(getVarName());
	}

	public boolean hasActionableDynamicQuery() {
		if (hasColumns() && hasLocalService()) {
			if (hasCompoundPK()) {
				EntityColumn col = _pkList.get(0);

				return col.isPrimitiveType();
			}
			else {
				return hasPrimitivePK();
			}
		}

		return false;
	}

	public boolean hasArrayableOperator() {
		for (EntityFinder finder : _finderList) {
			if (finder.hasArrayableOperator()) {
				return true;
			}
		}

		return false;
	}

	public boolean hasColumn(String name) {
		return hasColumn(name, _columnList);
	}

	public boolean hasColumn(String name, String type) {
		return hasColumn(name, type, _columnList);
	}

	public boolean hasColumns() {
		if ((_columnList == null) || (_columnList.size() == 0)) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasCompoundPK() {
		if (_pkList.size() > 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasEagerBlobColumn() {
		if ((_blobList == null) || _blobList.isEmpty()) {
			return false;
		}

		for (EntityColumn col : _blobList) {
			if (!col.isLazy()) {
				return true;
			}
		}

		return false;
	}

	public boolean hasFinderClass() {
		if (Validator.isNull(_finderClass)) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

	public boolean hasLazyBlobColumn() {
		if ((_blobList == null) || _blobList.isEmpty()) {
			return false;
		}

		for (EntityColumn col : _blobList) {
			if (col.isLazy()) {
				return true;
			}
		}

		return false;
	}

	public boolean hasLocalizedColumn() {
		for (EntityColumn col : _columnList) {
			if (col.isLocalized()) {
				return true;
			}
		}

		return false;
	}

	public boolean hasLocalService() {
		return _localService;
	}

	public boolean hasPrimitivePK() {
		return hasPrimitivePK(true);
	}

	public boolean hasPrimitivePK(boolean includeWrappers) {
		if (hasCompoundPK()) {
			return false;
		}

		EntityColumn col = _getPKColumn();

		if (col.isPrimitiveType(includeWrappers)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasRemoteService() {
		return _remoteService;
	}

	public boolean hasUuid() {
		return _uuid;
	}

	public boolean hasUuidAccessor() {
		return _uuidAccessor;
	}

	public boolean isAttachedModel() {
		if (!isTypedModel()) {
			return false;
		}

		if (hasColumn("classPK")) {
			EntityColumn classPKCol = getColumn("classPK");

			String classPKColType = classPKCol.getType();

			if (classPKColType.equals("long")) {
				return true;
			}
		}

		return false;
	}

	public boolean isAuditedModel() {
		if (hasColumn("companyId") && hasColumn("createDate", "Date") &&
			hasColumn("modifiedDate", "Date") && hasColumn("userId") &&
			hasColumn("userName")) {

			return true;
		}

		return false;
	}

	public boolean isCacheEnabled() {
		return _cacheEnabled;
	}

	public boolean isContainerModel() {
		return _containerModel;
	}

	public boolean isDefaultDataSource() {
		if (_dataSource.equals(DEFAULT_DATA_SOURCE)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDefaultSessionFactory() {
		if (_sessionFactory.equals(DEFAULT_SESSION_FACTORY)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDefaultTXManager() {
		if (_txManager.equals(DEFAULT_TX_MANAGER)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDeprecated() {
		return _deprecated;
	}

	public boolean isGroupedModel() {
		String pkVarName = getPKVarName();

		if (isAuditedModel() && hasColumn("groupId") &&
			!pkVarName.equals("groupId")) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isHierarchicalTree() {
		if (!hasPrimitivePK()) {
			return false;
		}

		EntityColumn col = _getPKColumn();

		if ((_columnList.indexOf(
				new EntityColumn("parent" + col.getMethodName())) != -1) &&
			(_columnList.indexOf(
				new EntityColumn("left" + col.getMethodName())) != -1) &&
			(_columnList.indexOf(
				new EntityColumn("right" + col.getMethodName())) != -1)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isJsonEnabled() {
		return _jsonEnabled;
	}

	public boolean isOrdered() {
		if (_order != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPermissionCheckEnabled() {
		for (EntityFinder finder : _finderList) {
			if (isPermissionCheckEnabled(finder)) {
				return true;
			}
		}

		return false;
	}

	public boolean isPermissionCheckEnabled(EntityFinder finder) {
		if (_name.equals("Group") || _name.equals("User") ||
			finder.getName().equals("UUID_G") || !finder.isCollection() ||
			!hasPrimitivePK() ||
			!ResourceActionsUtil.hasModelResourceActions(
				_packagePath + ".model." + _name)) {

			return false;
		}

		if (hasColumn("groupId") && !finder.hasColumn("groupId")) {
			return false;
		}

		return true;
	}

	public boolean isPermissionedModel() {
		if (hasColumn("resourceBlockId")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPortalReference() {
		return _portalReference;
	}

	public boolean isResourcedModel() {
		String pkVarName = getPKVarName();

		if (hasColumn("resourcePrimKey") &&
			!pkVarName.equals("resourcePrimKey")) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isStagedAuditedModel() {
		if (isAuditedModel() && isStagedModel()) {
			return true;
		}

		return false;
	}

	public boolean isStagedGroupedModel() {
		if (isGroupedModel() && isStagedModel()) {
			return true;
		}

		return false;
	}

	public boolean isStagedModel() {
		if (hasUuid() && hasColumn("companyId") &&
			hasColumn("createDate", "Date") &&
			hasColumn("modifiedDate", "Date")) {

			return true;
		}

		return false;
	}

	public boolean isTrashEnabled() {
		return _trashEnabled;
	}

	public boolean isTreeModel() {
		if (hasColumn("treePath")) {
			return true;
		}

		return false;
	}

	public boolean isTypedModel() {
		if (hasColumn("classNameId")) {
			EntityColumn classNameIdCol = getColumn("classNameId");

			String classNameIdColType = classNameIdCol.getType();

			if (classNameIdColType.equals("long")) {
				return true;
			}
		}

		return false;
	}

	public boolean isWorkflowEnabled() {
		if (hasColumn("status") && hasColumn("statusByUserId") &&
			hasColumn("statusByUserName") && hasColumn("statusDate")) {

			return true;
		}
		else {
			return false;
		}
	}

	public void setParentTransients(List<String> transients) {
		_parentTransients = transients;
	}

	public void setPortalReference(boolean portalReference) {
		_portalReference = portalReference;
	}

	public void setTransients(List<String> transients) {
		_transients = transients;
	}

	private EntityColumn _getPKColumn() {
		if (_pkList.isEmpty()) {
			throw new RuntimeException(
				"There is no primary key for entity " + _name);
		}

		return _pkList.get(0);
	}

	private String _alias;
	private List<EntityColumn> _blobList;
	private boolean _cacheEnabled;
	private List<EntityColumn> _collectionList;
	private List<EntityColumn> _columnList;
	private boolean _containerModel;
	private String _dataSource;
	private boolean _deprecated;
	private String _finderClass;
	private List<EntityColumn> _finderColumnsList;
	private List<EntityFinder> _finderList;
	private String _humanName;
	private boolean _jsonEnabled;
	private boolean _localService;
	private String _name;
	private EntityOrder _order;
	private String _packagePath;
	private List<String> _parentTransients;
	private String _persistenceClass;
	private List<EntityColumn> _pkList;
	private boolean _portalReference;
	private String _portletName;
	private String _portletShortName;
	private List<Entity> _referenceList;
	private List<EntityColumn> _regularColList;
	private boolean _remoteService;
	private String _sessionFactory;
	private String _table;
	private List<String> _transients;
	private boolean _trashEnabled;
	private String _txManager;
	private List<String> _txRequiredList;
	private boolean _uuid;
	private boolean _uuidAccessor;

}