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

package com.liferay.portal.kernel.template;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.UnsecureSAXReaderUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author Jorge Ferrer
 */
public abstract class BaseTemplateHandler implements TemplateHandler {

	@Override
	public List<Element> getDefaultTemplateElements() throws Exception {
		String templatesConfigPath = getTemplatesConfigPath();

		if (Validator.isNull(templatesConfigPath)) {
			return Collections.emptyList();
		}

		Class<?> clazz = getClass();

		String xml = StringUtil.read(
			clazz.getClassLoader(), templatesConfigPath, false);

		Document document = UnsecureSAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		return rootElement.elements("template");
	}

	@Override
	public String[] getRestrictedVariables(String language) {
		if (language.equals(TemplateConstants.LANG_TYPE_FTL)) {
			return PropsUtil.getArray(
				PropsKeys.FREEMARKER_ENGINE_RESTRICTED_VARIABLES);
		}
		else if (language.equals(TemplateConstants.LANG_TYPE_VM)) {
			return PropsUtil.getArray(
				PropsKeys.VELOCITY_ENGINE_RESTRICTED_VARIABLES);
		}

		return new String[0];
	}

	@Override
	public String getTemplatesHelpPath(String language) {
		return PropsUtil.get(
			getTemplatesHelpPropertyKey(), new Filter(language));
	}

	@Override
	public String getTemplatesHelpPropertyKey() {
		return PropsKeys.PORTLET_DISPLAY_TEMPLATES_HELP;
	}

	protected String getTemplatesConfigPath() {
		return null;
	}

}