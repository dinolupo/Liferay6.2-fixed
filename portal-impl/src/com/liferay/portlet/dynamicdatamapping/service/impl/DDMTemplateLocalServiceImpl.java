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

package com.liferay.portlet.dynamicdatamapping.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.persistence.ImageUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portlet.dynamicdatamapping.NoSuchTemplateException;
import com.liferay.portlet.dynamicdatamapping.RequiredTemplateException;
import com.liferay.portlet.dynamicdatamapping.TemplateDuplicateTemplateKeyException;
import com.liferay.portlet.dynamicdatamapping.TemplateNameException;
import com.liferay.portlet.dynamicdatamapping.TemplateScriptException;
import com.liferay.portlet.dynamicdatamapping.TemplateSmallImageNameException;
import com.liferay.portlet.dynamicdatamapping.TemplateSmallImageSizeException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.service.base.DDMTemplateLocalServiceBaseImpl;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleConstants;
import com.liferay.portlet.journal.service.persistence.JournalArticleUtil;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the local service for accessing, adding, copying, deleting, and
 * updating dynamic data mapping (DDM) templates.
 *
 * <p>
 * DDM templates (templates) are used in Liferay to render templated content
 * like applications, forms, dynamic data lists, or web contents.
 * </p>
 *
 * <p>
 * Templates support a variety of templating languages, like Velocity or
 * FreeMarker. They also support multi-language names and descriptions.
 * </p>
 *
 * <p>
 * Templates can be related to many models in Liferay, such as those for
 * structures, dynamic data lists, and applications. This relationship can be
 * established via the class name ID and class PK.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 * @author Marcellus Tavares
 */
public class DDMTemplateLocalServiceImpl
	extends DDMTemplateLocalServiceBaseImpl {

	/**
	 * Adds a template.
	 *
	 * @param  userId the primary key of the template's creator/owner
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  nameMap the template's locales and localized names
	 * @param  descriptionMap the template's locales and localized descriptions
	 * @param  type the template's type. For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode. For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  language the template's script language. For more information,
	 *         see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  script the template's script
	 * @param  serviceContext the service context to be applied. Can set the
	 *         UUID, creation date, modification date, guest permissions, and
	 *         group permissions for the template.
	 * @return the template
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDMTemplate addTemplate(
			long userId, long groupId, long classNameId, long classPK,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String type, String mode, String language, String script,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return addTemplate(
			userId, groupId, classNameId, classPK, null, nameMap,
			descriptionMap, type, mode, language, script, false, false, null,
			null, serviceContext);
	}

	/**
	 * Adds a template with additional parameters.
	 *
	 * @param  userId the primary key of the template's creator/owner
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  templateKey the unique string identifying the template
	 *         (optionally <code>null</code>)
	 * @param  nameMap the template's locales and localized names
	 * @param  descriptionMap the template's locales and localized descriptions
	 * @param  type the template's type. For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode. For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  language the template's script language. For more information,
	 *         see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  script the template's script
	 * @param  cacheable whether the template is cacheable
	 * @param  smallImage whether the template has a small image
	 * @param  smallImageURL the template's small image URL (optionally
	 *         <code>null</code>)
	 * @param  smallImageFile the template's small image file (optionally
	 *         <code>null</code>)
	 * @param  serviceContext the service context to be applied. Can set the
	 *         UUID, creation date, modification date, guest permissions, and
	 *         group permissions for the template.
	 * @return the template
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDMTemplate addTemplate(
			long userId, long groupId, long classNameId, long classPK,
			String templateKey, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, String mode,
			String language, String script, boolean cacheable,
			boolean smallImage, String smallImageURL, File smallImageFile,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Template

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		if (Validator.isNull(templateKey)) {
			templateKey = String.valueOf(counterLocalService.increment());
		}
		else {
			templateKey = StringUtil.toUpperCase(templateKey.trim());
		}

		script = formatScript(type, language, script);

		byte[] smallImageBytes = null;

		if (smallImage) {
			try {
				smallImageBytes = FileUtil.getBytes(smallImageFile);
			}
			catch (IOException ioe) {
			}

			if ((smallImageBytes == null) || Validator.isUrl(smallImageURL)) {
				smallImage = false;
			}
		}

		validate(
			groupId, classNameId, templateKey, nameMap, script, smallImage,
			smallImageURL, smallImageFile, smallImageBytes);

		long templateId = counterLocalService.increment();

		DDMTemplate template = ddmTemplatePersistence.create(templateId);

		template.setUuid(serviceContext.getUuid());
		template.setGroupId(groupId);
		template.setCompanyId(user.getCompanyId());
		template.setUserId(user.getUserId());
		template.setUserName(user.getFullName());
		template.setCreateDate(serviceContext.getCreateDate(now));
		template.setModifiedDate(serviceContext.getModifiedDate(now));
		template.setClassNameId(classNameId);
		template.setClassPK(classPK);
		template.setTemplateKey(templateKey);
		template.setNameMap(nameMap);
		template.setDescriptionMap(descriptionMap);
		template.setType(type);
		template.setMode(mode);
		template.setLanguage(language);
		template.setScript(script);
		template.setCacheable(cacheable);
		template.setSmallImage(smallImage);
		template.setSmallImageId(counterLocalService.increment());
		template.setSmallImageURL(smallImageURL);

		ddmTemplatePersistence.update(template);

		// Resources

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			addTemplateResources(
				template, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			addTemplateResources(
				template, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Small image

		saveImages(
			smallImage, template.getSmallImageId(), smallImageFile,
			smallImageBytes);

		return template;
	}

	/**
	 * Adds the resources to the template.
	 *
	 * @param  template the template to add resources to
	 * @param  addGroupPermissions whether to add group permissions
	 * @param  addGuestPermissions whether to add guest permissions
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addTemplateResources(
			DDMTemplate template, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			template.getCompanyId(), template.getGroupId(),
			template.getUserId(), DDMTemplate.class.getName(),
			template.getTemplateId(), false, addGroupPermissions,
			addGuestPermissions);
	}

	/**
	 * Adds the model resources with the permissions to the template.
	 *
	 * @param  template the template to add resources to
	 * @param  groupPermissions the group permissions to be added
	 * @param  guestPermissions the guest permissions to be added
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addTemplateResources(
			DDMTemplate template, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			template.getCompanyId(), template.getGroupId(),
			template.getUserId(), DDMTemplate.class.getName(),
			template.getTemplateId(), groupPermissions, guestPermissions);
	}

	/**
	 * Copies the template, creating a new template with all the values
	 * extracted from the original one. This method supports defining a new name
	 * and description.
	 *
	 * @param  userId the primary key of the template's creator/owner
	 * @param  templateId the primary key of the template to be copied
	 * @param  nameMap the new template's locales and localized names
	 * @param  descriptionMap the new template's locales and localized
	 *         descriptions
	 * @param  serviceContext the service context to be applied. Can set the
	 *         UUID, creation date, modification date, guest permissions, and
	 *         group permissions for the template.
	 * @return the new template
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDMTemplate copyTemplate(
			long userId, long templateId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDMTemplate template = ddmTemplatePersistence.findByPrimaryKey(
			templateId);

		return copyTemplate(
			userId, template, template.getClassPK(), nameMap, descriptionMap,
			serviceContext);
	}

	@Override
	public DDMTemplate copyTemplate(
			long userId, long templateId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDMTemplate template = ddmTemplatePersistence.findByPrimaryKey(
			templateId);

		return copyTemplate(
			userId, template, template.getClassPK(), template.getNameMap(),
			template.getDescriptionMap(), serviceContext);
	}

	/**
	 * Copies all the templates matching the class name ID, class PK, and type.
	 * This method creates new templates, extracting all the values from the old
	 * ones and updating their class PKs.
	 *
	 * @param  userId the primary key of the template's creator/owner
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  oldClassPK the primary key of the old template's related entity
	 * @param  newClassPK the primary key of the new template's related entity
	 * @param  type the template's type. For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  serviceContext the service context to be applied. Can set the
	 *         creation date, modification date, guest permissions, and group
	 *         permissions for the new templates.
	 * @return the new templates
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMTemplate> copyTemplates(
			long userId, long classNameId, long oldClassPK, long newClassPK,
			String type, ServiceContext serviceContext)
		throws PortalException, SystemException {

		List<DDMTemplate> newTemplates = new ArrayList<DDMTemplate>();

		List<DDMTemplate> oldTemplates = ddmTemplatePersistence.findByC_C_T(
			classNameId, oldClassPK, type);

		for (DDMTemplate oldTemplate : oldTemplates) {
			DDMTemplate newTemplate = copyTemplate(
				userId, oldTemplate, newClassPK, oldTemplate.getNameMap(),
				oldTemplate.getDescriptionMap(), serviceContext);

			newTemplates.add(newTemplate);
		}

		return newTemplates;
	}

	/**
	 * Deletes the template and its resources.
	 *
	 * @param  template the template to be deleted
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public void deleteTemplate(DDMTemplate template)
		throws PortalException, SystemException {

		// Template

		if (template.getClassNameId() ==
				PortalUtil.getClassNameId(DDMStructure.class.getName())) {

			DDMStructure structure = ddmStructureLocalService.fetchDDMStructure(
				template.getClassPK());

			if ((structure != null) &&
				(structure.getClassNameId() ==
					PortalUtil.getClassNameId(
						JournalArticle.class.getName()))) {

				Group companyGroup = groupLocalService.getCompanyGroup(
					template.getCompanyId());

				if (template.getGroupId() == companyGroup.getGroupId()) {
					if (JournalArticleUtil.countByC_T(
							JournalArticleConstants.CLASSNAME_ID_DEFAULT,
							template.getTemplateKey()) > 0) {

						throw new RequiredTemplateException();
					}
				}
				else {
					if (JournalArticleUtil.countByG_C_T(
							template.getGroupId(),
							JournalArticleConstants.CLASSNAME_ID_DEFAULT,
							template.getTemplateKey()) > 0) {

						throw new RequiredTemplateException();
					}
				}
			}
		}

		ddmTemplatePersistence.remove(template);

		// Resources

		resourceLocalService.deleteResource(
			template.getCompanyId(), DDMTemplate.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, template.getTemplateId());
	}

	/**
	 * Deletes the template and its resources.
	 *
	 * @param  templateId the primary key of the template to be deleted
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteTemplate(long templateId)
		throws PortalException, SystemException {

		DDMTemplate template = ddmTemplatePersistence.findByPrimaryKey(
			templateId);

		ddmTemplateLocalService.deleteTemplate(template);
	}

	/**
	 * Deletes all the templates of the group.
	 *
	 * @param  groupId the primary key of the group
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteTemplates(long groupId)
		throws PortalException, SystemException {

		List<DDMTemplate> templates = ddmTemplatePersistence.findByGroupId(
			groupId);

		for (DDMTemplate template : templates) {
			ddmTemplateLocalService.deleteTemplate(template);
		}
	}

	@Override
	public void deleteTemplates(long groupId, long classNameId)
		throws PortalException, SystemException {

		List<DDMTemplate> templates = ddmTemplatePersistence.findByG_C(
			groupId, classNameId);

		for (DDMTemplate template : templates) {
			ddmTemplateLocalService.deleteTemplate(template);
		}
	}

	/**
	 * Returns the template matching the group and template key.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  templateKey the unique string identifying the template
	 * @return the matching template, or <code>null</code> if a matching
	 *         template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDMTemplate fetchTemplate(
			long groupId, long classNameId, String templateKey)
		throws SystemException {

		templateKey = StringUtil.toUpperCase(templateKey.trim());

		return ddmTemplatePersistence.fetchByG_C_T(
			groupId, classNameId, templateKey);
	}

	/**
	 * Returns the template matching the group and template key, optionally in
	 * the global scope.
	 *
	 * <p>
	 * This method first searches in the given group. If the template is still
	 * not found and <code>includeGlobalTemplates</code> is set to
	 * <code>true</code>, this method searches the global group.
	 * </p>
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  templateKey the unique string identifying the template
	 * @param  includeGlobalTemplates whether to include the global scope in the
	 *         search
	 * @return the matching template, or <code>null</code> if a matching
	 *         template could not be found
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDMTemplate fetchTemplate(
			long groupId, long classNameId, String templateKey,
			boolean includeGlobalTemplates)
		throws PortalException, SystemException {

		templateKey = StringUtil.toUpperCase(templateKey.trim());

		DDMTemplate template = ddmTemplatePersistence.fetchByG_C_T(
			groupId, classNameId, templateKey);

		if ((template != null) || !includeGlobalTemplates) {
			return template;
		}

		Group group = groupPersistence.findByPrimaryKey(groupId);

		Group companyGroup = groupLocalService.getCompanyGroup(
			group.getCompanyId());

		return ddmTemplatePersistence.fetchByG_C_T(
			companyGroup.getGroupId(), classNameId, templateKey);
	}

	/**
	 * Returns the template with the ID.
	 *
	 * @param  templateId the primary key of the template
	 * @return the template with the ID
	 * @throws PortalException if a matching template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDMTemplate getTemplate(long templateId)
		throws PortalException, SystemException {

		return ddmTemplatePersistence.findByPrimaryKey(templateId);
	}

	/**
	 * Returns the template matching the group and template key.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  templateKey the unique string identifying the template
	 * @return the matching template
	 * @throws PortalException if a matching template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDMTemplate getTemplate(
			long groupId, long classNameId, String templateKey)
		throws PortalException, SystemException {

		templateKey = StringUtil.toUpperCase(templateKey.trim());

		return ddmTemplatePersistence.findByG_C_T(
			groupId, classNameId, templateKey);
	}

	/**
	 * Returns the template matching the group and template key, optionally in
	 * the global scope.
	 *
	 * <p>
	 * This method first searches in the group. If the template is still not
	 * found and <code>includeGlobalTemplates</code> is set to
	 * <code>true</code>, this method searches the global group.
	 * </p>
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  templateKey the unique string identifying the template
	 * @param  includeGlobalTemplates whether to include the global scope in the
	 *         search
	 * @return the matching template
	 * @throws PortalException if a matching template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDMTemplate getTemplate(
			long groupId, long classNameId, String templateKey,
			boolean includeGlobalTemplates)
		throws PortalException, SystemException {

		templateKey = StringUtil.toUpperCase(templateKey.trim());

		DDMTemplate template = ddmTemplatePersistence.fetchByG_C_T(
			groupId, classNameId, templateKey);

		if (template != null) {
			return template;
		}

		if (!includeGlobalTemplates) {
			throw new NoSuchTemplateException(
				"No DDMTemplate exists with the template key " + templateKey);
		}

		Group group = groupPersistence.findByPrimaryKey(groupId);

		Group companyGroup = groupLocalService.getCompanyGroup(
			group.getCompanyId());

		return ddmTemplatePersistence.findByG_C_T(
			companyGroup.getGroupId(), classNameId, templateKey);
	}

	@Override
	public DDMTemplate getTemplateBySmallImageId(long smallImageId)
		throws PortalException, SystemException {

		return ddmTemplatePersistence.findBySmallImageId(smallImageId);
	}

	/**
	 * Returns all the templates with the class PK.
	 *
	 * @param  classPK the primary key of the template's related entity
	 * @return the templates with the class PK
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMTemplate> getTemplates(long classPK) throws SystemException {
		return ddmTemplatePersistence.findByClassPK(classPK);
	}

	/**
	 * Returns all the templates matching the group and class name ID.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @return the matching templates
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMTemplate> getTemplates(long groupId, long classNameId)
		throws SystemException {

		return ddmTemplatePersistence.findByG_C(groupId, classNameId);
	}

	/**
	 * Returns all the templates matching the group, class name ID, and class
	 * PK.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @return the matching templates
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMTemplate> getTemplates(
			long groupId, long classNameId, long classPK)
		throws SystemException {

		return ddmTemplatePersistence.findByG_C_C(
			groupId, classNameId, classPK);
	}

	/**
	 * Returns all the templates matching the group, class name ID, class PK,
	 * and type.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  type the template's type. For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @return the matching templates
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMTemplate> getTemplates(
			long groupId, long classNameId, long classPK, String type)
		throws SystemException {

		return ddmTemplatePersistence.findByG_C_C_T(
			groupId, classNameId, classPK, type);
	}

	/**
	 * Returns all the templates matching the group, class name ID, class PK,
	 * type, and mode.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  type the template's type. For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode. For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @return the matching templates
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMTemplate> getTemplates(
			long groupId, long classNameId, long classPK, String type,
			String mode)
		throws SystemException {

		return ddmTemplatePersistence.findByG_C_C_T_M(
			groupId, classNameId, classPK, type, mode);
	}

	@Override
	public List<DDMTemplate> getTemplatesByClassPK(long groupId, long classPK)
		throws SystemException {

		return ddmTemplatePersistence.findByG_CPK(groupId, classPK);
	}

	@Override
	public List<DDMTemplate> getTemplatesByClassPK(
			long groupId, long classPK, int start, int end)
		throws SystemException {

		return ddmTemplatePersistence.findByG_CPK(groupId, classPK, start, end);
	}

	@Override
	public List<DDMTemplate> getTemplatesByClassPK(
			long[] groupIds, long classPK)
		throws SystemException {

		return ddmTemplatePersistence.findByG_CPK(groupIds, classPK);
	}

	/**
	 * Returns the number of templates matching the group and class PK.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classPK the primary key of the template's related entity
	 * @return the number of templates belonging to the group and class PK
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getTemplatesByClassPKCount(long groupId, long classPK)
		throws SystemException {

		return ddmTemplatePersistence.countByG_CPK(groupId, classPK);
	}

	/**
	 * Returns an ordered range of all the templates matching the group and
	 * structure class name ID.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  groupId the primary key of the group
	 * @param  structureClassNameId the primary key of the class name for the
	 *         template's related structure
	 * @param  start the lower bound of the range of templates to return
	 * @param  end the upper bound of the range of templates to return (not
	 *         inclusive)
	 * @param  orderByComparator the comparator to order the templates
	 *         (optionally <code>null</code>)
	 * @return the range of matching templates ordered by the comparator
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMTemplate> getTemplatesByStructureClassNameId(
			long groupId, long structureClassNameId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return ddmTemplateFinder.findByG_SC(
			groupId, structureClassNameId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of templates matching the group and structure class
	 * name ID, including Generic Templates.
	 *
	 * @param  groupId the primary key of the group
	 * @param  structureClassNameId the primary key of the class name for the
	 *         template's related structure
	 * @return the number of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getTemplatesByStructureClassNameIdCount(
			long groupId, long structureClassNameId)
		throws SystemException {

		return ddmTemplateFinder.countByG_SC(groupId, structureClassNameId);
	}

	/**
	 * Returns the number of templates belonging to the group.
	 *
	 * @param  groupId the primary key of the group
	 * @return the number of templates belonging to the group
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getTemplatesCount(long groupId) throws SystemException {
		return ddmTemplatePersistence.countByGroupId(groupId);
	}

	/**
	 * Returns the number of templates matching the group and class name ID.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @return the number of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getTemplatesCount(long groupId, long classNameId)
		throws SystemException {

		return ddmTemplatePersistence.countByG_C(groupId, classNameId);
	}

	/**
	 * Returns the number of templates matching the group, class name ID, and
	 * class PK.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @return the number of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getTemplatesCount(long groupId, long classNameId, long classPK)
		throws SystemException {

		return ddmTemplatePersistence.countByG_C_C(
			groupId, classNameId, classPK);
	}

	/**
	 * Returns an ordered range of all the templates matching the group, class
	 * name ID, class PK, type, and mode, and matching the keywords in the
	 * template names and descriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         template's name or description (optionally <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  start the lower bound of the range of templates to return
	 * @param  end the upper bound of the range of templates to return (not
	 *         inclusive)
	 * @param  orderByComparator the comparator to order the templates
	 *         (optionally <code>null</code>)
	 * @return the range of matching templates ordered by the comparator
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMTemplate> search(
			long companyId, long groupId, long classNameId, long classPK,
			String keywords, String type, String mode, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return ddmTemplateFinder.findByKeywords(
			companyId, groupId, classNameId, classPK, keywords, type, mode,
			start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the templates matching the group, class
	 * name ID, class PK, name keyword, description keyword, type, mode, and
	 * language.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  name the name keywords (optionally <code>null</code>)
	 * @param  description the description keywords (optionally
	 *         <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  language the template's script language (optionally
	 *         <code>null</code>). For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field
	 * @param  start the lower bound of the range of templates to return
	 * @param  end the upper bound of the range of templates to return (not
	 *         inclusive)
	 * @param  orderByComparator the comparator to order the templates
	 *         (optionally <code>null</code>)
	 * @return the range of matching templates ordered by the comparator
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMTemplate> search(
			long companyId, long groupId, long classNameId, long classPK,
			String name, String description, String type, String mode,
			String language, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return ddmTemplateFinder.findByC_G_C_C_N_D_T_M_L(
			companyId, groupId, classNameId, classPK, name, description, type,
			mode, language, andOperator, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the templates matching the group IDs,
	 * class Name IDs, class PK, type, and mode, and include the keywords on its
	 * names and descriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupIds the primary keys of the groups
	 * @param  classNameIds the primary keys of the entity's instances the
	 *         templates are related to
	 * @param  classPKs the primary keys of the template's related entities
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         template's name or description (optionally <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  start the lower bound of the range of templates to return
	 * @param  end the upper bound of the range of templates to return (not
	 *         inclusive)
	 * @param  orderByComparator the comparator to order the templates
	 *         (optionally <code>null</code>)
	 * @return the range of matching templates ordered by the comparator
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMTemplate> search(
			long companyId, long[] groupIds, long[] classNameIds,
			long[] classPKs, String keywords, String type, String mode,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		return ddmTemplateFinder.findByKeywords(
			companyId, groupIds, classNameIds, classPKs, keywords, type, mode,
			start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the templates matching the group IDs,
	 * class name IDs, class PK, name keyword, description keyword, type, mode,
	 * and language.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupIds the primary keys of the groups
	 * @param  classNameIds the primary keys of the entity's instances the
	 *         templates are related to
	 * @param  classPKs the primary keys of the template's related entities
	 * @param  name the name keywords (optionally <code>null</code>)
	 * @param  description the description keywords (optionally
	 *         <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  language the template's script language (optionally
	 *         <code>null</code>). For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field.
	 * @param  start the lower bound of the range of templates to return
	 * @param  end the upper bound of the range of templates to return (not
	 *         inclusive)
	 * @param  orderByComparator the comparator to order the templates
	 *         (optionally <code>null</code>)
	 * @return the range of matching templates ordered by the comparator
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMTemplate> search(
			long companyId, long[] groupIds, long[] classNameIds,
			long[] classPKs, String name, String description, String type,
			String mode, String language, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		return ddmTemplateFinder.findByC_G_C_C_N_D_T_M_L(
			companyId, groupIds, classNameIds, classPKs, name, description,
			type, mode, language, andOperator, start, end, orderByComparator);
	}

	/**
	 * Returns the number of templates matching the group, class name ID, class
	 * PK, type, and matching the keywords in the template names and
	 * descriptions.
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         template's name or description (optionally <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @return the number of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int searchCount(
			long companyId, long groupId, long classNameId, long classPK,
			String keywords, String type, String mode)
		throws SystemException {

		return ddmTemplateFinder.countByKeywords(
			companyId, groupId, classNameId, classPK, keywords, type, mode);
	}

	/**
	 * Returns the number of templates matching the group, class name ID, class
	 * PK, name keyword, description keyword, type, mode, and language.
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  name the name keywords (optionally <code>null</code>)
	 * @param  description the description keywords (optionally
	 *         <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  language the template's script language (optionally
	 *         <code>null</code>). For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field.
	 * @return the number of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int searchCount(
			long companyId, long groupId, long classNameId, long classPK,
			String name, String description, String type, String mode,
			String language, boolean andOperator)
		throws SystemException {

		return ddmTemplateFinder.countByC_G_C_C_N_D_T_M_L(
			companyId, groupId, classNameId, classPK, name, description, type,
			mode, language, andOperator);
	}

	/**
	 * Returns the number of templates matching the group IDs, class name IDs,
	 * class PK, type, and mode, and matching the keywords in the template names
	 * and descriptions.
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupIds the primary keys of the groups
	 * @param  classNameIds the primary keys of the entity's instance the
	 *         templates are related to
	 * @param  classPKs the primary keys of the template's related entities
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         template's name or description (optionally <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @return the number of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int searchCount(
			long companyId, long[] groupIds, long[] classNameIds,
			long[] classPKs, String keywords, String type, String mode)
		throws SystemException {

		return ddmTemplateFinder.countByKeywords(
			companyId, groupIds, classNameIds, classPKs, keywords, type, mode);
	}

	/**
	 * Returns the number of templates matching the group IDs, class name IDs,
	 * class PKs, name keyword, description keyword, type, mode, and language.
	 *
	 * @param  companyId the primary key of the templates company
	 * @param  groupIds the primary keys of the groups
	 * @param  classNameIds the primary keys of the entity's instance the
	 *         templates are related to
	 * @param  classPKs the primary keys of the template's related entities
	 * @param  name the name keywords (optionally <code>null</code>)
	 * @param  description the description keywords (optionally
	 *         <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  language the template's script language (optionally
	 *         <code>null</code>). For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field.
	 * @return the number of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int searchCount(
			long companyId, long[] groupIds, long[] classNameIds,
			long[] classPKs, String name, String description, String type,
			String mode, String language, boolean andOperator)
		throws SystemException {

		return ddmTemplateFinder.countByC_G_C_C_N_D_T_M_L(
			companyId, groupIds, classNameIds, classPKs, name, description,
			type, mode, language, andOperator);
	}

	/**
	 * Updates the template matching the ID.
	 *
	 * @param  templateId the primary key of the template
	 * @param  classPK the primary key of the template's related entity
	 * @param  nameMap the template's new locales and localized names
	 * @param  descriptionMap the template's new locales and localized
	 *         description
	 * @param  type the template's type. For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  mode the template's mode. For more information, see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  language the template's script language. For more information,
	 *         see {@link
	 *         com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants}.
	 * @param  script the template's script
	 * @param  cacheable whether the template is cacheable
	 * @param  smallImage whether the template has a small image
	 * @param  smallImageURL the template's small image URL (optionally
	 *         <code>null</code>)
	 * @param  smallImageFile the template's small image file (optionally
	 *         <code>null</code>)
	 * @param  serviceContext the service context to be applied. Can set the
	 *         modification date.
	 * @return the updated template
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDMTemplate updateTemplate(
			long templateId, long classPK, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, String mode,
			String language, String script, boolean cacheable,
			boolean smallImage, String smallImageURL, File smallImageFile,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		script = formatScript(type, language, script);

		byte[] smallImageBytes = null;

		try {
			smallImageBytes = FileUtil.getBytes(smallImageFile);
		}
		catch (IOException ioe) {
		}

		validate(
			nameMap, script, smallImage, smallImageURL, smallImageFile,
			smallImageBytes);

		DDMTemplate template = ddmTemplateLocalService.getDDMTemplate(
			templateId);

		template.setModifiedDate(serviceContext.getModifiedDate(null));

		if ((template.getClassPK() == 0) && (classPK > 0)) {

			// Allow users to set the structure if and only if it currently does
			// not have one. Otherwise, you can have bad data because there may
			// be an existing content that has chosen to use a structure and
			// template combination that no longer exists.

			template.setClassPK(classPK);
		}

		template.setNameMap(nameMap);
		template.setDescriptionMap(descriptionMap);
		template.setType(type);
		template.setMode(mode);
		template.setLanguage(language);
		template.setScript(script);
		template.setCacheable(cacheable);
		template.setSmallImage(smallImage);
		template.setSmallImageURL(smallImageURL);

		ddmTemplatePersistence.update(template);

		// Small image

		saveImages(
			smallImage, template.getSmallImageId(), smallImageFile,
			smallImageBytes);

		return template;
	}

	protected File copySmallImage(DDMTemplate template) throws SystemException {
		File smallImageFile = null;

		if (template.isSmallImage() &&
			Validator.isNull(template.getSmallImageURL())) {

			Image smallImage = ImageUtil.fetchByPrimaryKey(
				template.getSmallImageId());

			if (smallImage != null) {
				smallImageFile = FileUtil.createTempFile(smallImage.getType());

				try {
					FileUtil.write(smallImageFile, smallImage.getTextObj());
				}
				catch (IOException ioe) {
					_log.error(ioe, ioe);
				}
			}
		}

		return smallImageFile;
	}

	protected DDMTemplate copyTemplate(
			long userId, DDMTemplate template, long classPK,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		File smallImageFile = copySmallImage(template);

		return addTemplate(
			userId, template.getGroupId(), template.getClassNameId(), classPK,
			null, nameMap, descriptionMap, template.getType(),
			template.getMode(), template.getLanguage(), template.getScript(),
			template.isCacheable(), template.isSmallImage(),
			template.getSmallImageURL(), smallImageFile, serviceContext);
	}

	protected String formatScript(String type, String language, String script)
		throws PortalException {

		if (type.equals(DDMTemplateConstants.TEMPLATE_TYPE_FORM) ||
			language.equals(TemplateConstants.LANG_TYPE_XSL)) {

			try {
				script = DDMXMLUtil.validateXML(script);
				script = DDMXMLUtil.formatXML(script);
			}
			catch (Exception e) {
				throw new TemplateScriptException();
			}
		}

		return script;
	}

	protected void saveImages(
			boolean smallImage, long smallImageId, File smallImageFile,
			byte[] smallImageBytes)
		throws PortalException, SystemException {

		if (smallImage) {
			if ((smallImageFile != null) && (smallImageBytes != null)) {
				imageLocalService.updateImage(smallImageId, smallImageBytes);
			}
		}
		else {
			imageLocalService.deleteImage(smallImageId);
		}
	}

	protected void validate(
			long groupId, long classNameId, String templateKey,
			Map<Locale, String> nameMap, String script, boolean smallImage,
			String smallImageURL, File smallImageFile, byte[] smallImageBytes)
		throws PortalException, SystemException {

		templateKey = StringUtil.toUpperCase(templateKey.trim());

		DDMTemplate template = ddmTemplatePersistence.fetchByG_C_T(
			groupId, classNameId, templateKey);

		if (template != null) {
			throw new TemplateDuplicateTemplateKeyException();
		}

		validate(
			nameMap, script, smallImage, smallImageURL, smallImageFile,
			smallImageBytes);
	}

	protected void validate(
			Map<Locale, String> nameMap, String script, boolean smallImage,
			String smallImageURL, File smallImageFile, byte[] smallImageBytes)
		throws PortalException, SystemException {

		validateName(nameMap);

		if (Validator.isNull(script)) {
			throw new TemplateScriptException();
		}

		String[] imageExtensions = PrefsPropsUtil.getStringArray(
			PropsKeys.DYNAMIC_DATA_MAPPING_IMAGE_EXTENSIONS, StringPool.COMMA);

		if (!smallImage || Validator.isNotNull(smallImageURL) ||
			(smallImageFile == null) || (smallImageBytes == null)) {

			return;
		}

		String smallImageName = smallImageFile.getName();

		if (smallImageName != null) {
			boolean validSmallImageExtension = false;

			for (int i = 0; i < imageExtensions.length; i++) {
				if (StringPool.STAR.equals(imageExtensions[i]) ||
					StringUtil.endsWith(
						smallImageName, imageExtensions[i])) {

					validSmallImageExtension = true;

					break;
				}
			}

			if (!validSmallImageExtension) {
				throw new TemplateSmallImageNameException(smallImageName);
			}
		}

		long smallImageMaxSize = PrefsPropsUtil.getLong(
			PropsKeys.DYNAMIC_DATA_MAPPING_IMAGE_SMALL_MAX_SIZE);

		if ((smallImageMaxSize > 0) &&
			((smallImageBytes == null) ||
			 (smallImageBytes.length > smallImageMaxSize))) {

			throw new TemplateSmallImageSizeException();
		}
	}

	protected void validateName(Map<Locale, String> nameMap)
		throws PortalException {

		String name = nameMap.get(LocaleUtil.getSiteDefault());

		if (Validator.isNull(name)) {
			throw new TemplateNameException();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DDMTemplateLocalServiceImpl.class);

}