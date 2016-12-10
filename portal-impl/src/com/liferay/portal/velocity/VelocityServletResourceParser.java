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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.template.URLResourceParser;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import java.net.URL;

import javax.servlet.ServletContext;

/**
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class VelocityServletResourceParser extends URLResourceParser {

	@Override
	public URL getURL(String source) throws IOException {
		int pos = source.indexOf(TemplateConstants.SERVLET_SEPARATOR);

		if (pos == -1) {
			return null;
		}

		String servletContextName = source.substring(0, pos);

		if (servletContextName.equals(PortalUtil.getPathContext())) {
			servletContextName = PortalUtil.getServletContextName();
		}

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		if (servletContext == null) {
			_log.error(
				source + " is not valid because " + servletContextName +
					" does not map to a servlet context");

			return null;
		}

		String name = source.substring(
			pos + TemplateConstants.SERVLET_SEPARATOR.length());

		if (_log.isDebugEnabled()) {
			_log.debug(
				name + " is associated with the servlet context " +
					servletContextName + " " + servletContext);
		}

		URL url = servletContext.getResource(name);

		if ((url == null) && name.endsWith("/init_custom.vm")) {
			if (_log.isWarnEnabled()) {
				_log.warn("The template " + name + " should be created");
			}

			ServletContext portalServletContext = ServletContextPool.get(
				PortalUtil.getServletContextName());

			url = portalServletContext.getResource(
				"/html/themes/_unstyled/templates/init_custom.vm");
		}

		return url;
	}

	private static Log _log = LogFactoryUtil.getLog(
		VelocityServletResourceParser.class);

}