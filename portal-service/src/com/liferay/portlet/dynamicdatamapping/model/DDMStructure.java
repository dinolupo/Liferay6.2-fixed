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

package com.liferay.portlet.dynamicdatamapping.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the DDMStructure service. Represents a row in the &quot;DDMStructure&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureModel
 * @see com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureImpl
 * @see com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureModelImpl
 * @generated
 */
@ProviderType
public interface DDMStructure extends DDMStructureModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.List<java.lang.String> getChildrenFieldNames(
		java.lang.String fieldName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getCompleteXsd()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.xml.Document getDocument();

	public java.lang.String getFieldDataType(java.lang.String fieldName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getFieldLabel(java.lang.String fieldName,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getFieldLabel(java.lang.String fieldName,
		java.lang.String locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.Set<java.lang.String> getFieldNames()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getFieldProperty(java.lang.String fieldName,
		java.lang.String property)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getFieldProperty(java.lang.String fieldName,
		java.lang.String property, java.lang.String locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean getFieldRepeatable(java.lang.String fieldName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean getFieldRequired(java.lang.String fieldName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.Map<java.lang.String, java.lang.String> getFields(
		java.lang.String fieldName, java.lang.String attributeName,
		java.lang.String attributeValue);

	public java.util.Map<java.lang.String, java.lang.String> getFields(
		java.lang.String fieldName, java.lang.String attributeName,
		java.lang.String attributeValue, java.lang.String locale);

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> getFieldsMap()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> getFieldsMap(
		boolean includeTransientFields)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> getFieldsMap(
		java.lang.String locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> getFieldsMap(
		java.lang.String locale, boolean includeTransientFields)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getFieldTip(java.lang.String fieldName,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getFieldTip(java.lang.String fieldName,
		java.lang.String locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getFieldType(java.lang.String fieldName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> getLocalizedFieldsMap();

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> getLocalizedPersistentFieldsMap();

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> getLocalizedTransientFieldsMap();

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> getPersistentFieldsMap(
		java.lang.String locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.String> getRootFieldNames()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> getTemplates()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> getTransientFieldsMap(
		java.lang.String locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the WebDAV URL to access the structure.
	*
	* @param themeDisplay the theme display needed to build the URL. It can
	set HTTPS access, the server name, the server port, the path
	context, and the scope group.
	* @param webDAVToken the WebDAV token for the URL
	* @return the WebDAV URL
	*/
	public java.lang.String getWebDavURL(
		com.liferay.portal.theme.ThemeDisplay themeDisplay,
		java.lang.String webDAVToken);

	public boolean hasField(java.lang.String fieldName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isFieldPrivate(java.lang.String fieldName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isFieldRepeatable(java.lang.String fieldName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isFieldTransient(java.lang.String fieldName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void setDocument(com.liferay.portal.kernel.xml.Document document);

	public void setLocalizedFieldsMap(
		java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> localizedFieldsMap);

	public void setLocalizedPersistentFieldsMap(
		java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> localizedPersistentFieldsMap);

	public void setLocalizedTransientFieldsMap(
		java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> localizedTransientFieldsMap);
}