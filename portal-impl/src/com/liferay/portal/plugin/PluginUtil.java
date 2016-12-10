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

package com.liferay.portal.plugin;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Plugin;
import com.liferay.portal.model.PluginSetting;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PluginSettingLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class PluginUtil {

	public static <P extends Plugin> List<P> restrictPlugins(
			List<P> plugins, long companyId, long userId)
		throws SystemException {

		List<P> visiblePlugins = new ArrayList<P>(plugins.size());

		for (P plugin : plugins) {
			PluginSetting pluginSetting =
				PluginSettingLocalServiceUtil.getPluginSetting(
					companyId, plugin.getPluginId(), plugin.getPluginType());

			if (pluginSetting.isActive() &&
				pluginSetting.hasPermission(userId)) {

				visiblePlugins.add(plugin);
			}
		}

		return visiblePlugins;
	}

	public static <P extends Plugin> List<P> restrictPlugins(
			List<P> plugins, User user)
		throws SystemException {

		return restrictPlugins(plugins, user.getCompanyId(), user.getUserId());
	}

}