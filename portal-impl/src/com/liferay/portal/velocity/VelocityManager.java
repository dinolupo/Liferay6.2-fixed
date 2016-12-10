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

package com.liferay.portal.velocity;

import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.template.BaseTemplateManager;
import com.liferay.portal.template.RestrictedTemplate;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.util.Map;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.util.introspection.SecureUberspector;

/**
 * @author Raymond Augé
 */
@DoPrivileged
public class VelocityManager extends BaseTemplateManager {

	@Override
	public void destroy() {
		if (_velocityEngine == null) {
			return;
		}

		_velocityEngine = null;

		templateContextHelper.removeAllHelperUtilities();

		templateContextHelper = null;
	}

	@Override
	public void destroy(ClassLoader classLoader) {
		templateContextHelper.removeHelperUtilities(classLoader);
	}

	@Override
	public String getName() {
		return TemplateConstants.LANG_TYPE_VM;
	}

	@Override
	public void init() throws TemplateException {
		if (_velocityEngine != null) {
			return;
		}

		_velocityEngine = new VelocityEngine();

		ExtendedProperties extendedProperties = new FastExtendedProperties();

		extendedProperties.setProperty(
			VelocityEngine.DIRECTIVE_IF_TOSTRING_NULLCHECK,
			String.valueOf(
				PropsValues.VELOCITY_ENGINE_DIRECTIVE_IF_TO_STRING_NULL_CHECK));

		extendedProperties.setProperty(
			VelocityEngine.EVENTHANDLER_METHODEXCEPTION,
			LiferayMethodExceptionEventHandler.class.getName());

		extendedProperties.setProperty(
			RuntimeConstants.INTROSPECTOR_RESTRICT_CLASSES,
			StringUtil.merge(PropsValues.VELOCITY_ENGINE_RESTRICTED_CLASSES));

		extendedProperties.setProperty(
			RuntimeConstants.INTROSPECTOR_RESTRICT_PACKAGES,
			StringUtil.merge(PropsValues.VELOCITY_ENGINE_RESTRICTED_PACKAGES));

		extendedProperties.setProperty(
			VelocityEngine.RESOURCE_LOADER, "liferay");

		boolean cacheEnabled = false;

		if (PropsValues.VELOCITY_ENGINE_RESOURCE_MODIFICATION_CHECK_INTERVAL !=
				0) {

			cacheEnabled = true;
		}

		extendedProperties.setProperty(
			"liferay." + VelocityEngine.RESOURCE_LOADER + ".cache",
			String.valueOf(cacheEnabled));

		extendedProperties.setProperty(
			"liferay." + VelocityEngine.RESOURCE_LOADER + ".class",
			LiferayResourceLoader.class.getName());

		extendedProperties.setProperty(
			VelocityEngine.RESOURCE_MANAGER_CLASS,
			LiferayResourceManager.class.getName());

		extendedProperties.setProperty(
			VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS,
			PropsUtil.get(PropsKeys.VELOCITY_ENGINE_LOGGER));

		extendedProperties.setProperty(
			VelocityEngine.RUNTIME_LOG_LOGSYSTEM + ".log4j.category",
			PropsUtil.get(PropsKeys.VELOCITY_ENGINE_LOGGER_CATEGORY));

		extendedProperties.setProperty(
			RuntimeConstants.UBERSPECT_CLASSNAME,
			SecureUberspector.class.getName());

		extendedProperties.setProperty(
			VelocityEngine.VM_LIBRARY,
			PropsUtil.get(PropsKeys.VELOCITY_ENGINE_VELOCIMACRO_LIBRARY));

		extendedProperties.setProperty(
			VelocityEngine.VM_LIBRARY_AUTORELOAD,
			String.valueOf(!cacheEnabled));

		extendedProperties.setProperty(
			VelocityEngine.VM_PERM_ALLOW_INLINE_REPLACE_GLOBAL,
			String.valueOf(!cacheEnabled));

		_velocityEngine.setExtendedProperties(extendedProperties);

		try {
			_velocityEngine.init();
		}
		catch (Exception e) {
			throw new TemplateException(e);
		}
	}

	@Override
	protected Template doGetTemplate(
		TemplateResource templateResource,
		TemplateResource errorTemplateResource, boolean restricted,
		Map<String, Object> helperUtilities, boolean privileged) {

		Template template = new VelocityTemplate(
			templateResource, errorTemplateResource, helperUtilities,
			_velocityEngine, templateContextHelper, privileged);

		if (restricted) {
			template = new RestrictedTemplate(
				template, templateContextHelper.getRestrictedVariables());
		}

		return template;
	}

	private VelocityEngine _velocityEngine;

}