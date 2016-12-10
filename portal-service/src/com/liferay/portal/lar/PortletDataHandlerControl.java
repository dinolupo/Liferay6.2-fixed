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

package com.liferay.portal.lar;

/**
 * @author     Raymond Augé
 * @deprecated As of 6.2.0, moved to {@link
 *             com.liferay.portal.kernel.lar.PortletDataHandlerControl}
 */
public class PortletDataHandlerControl
	extends com.liferay.portal.kernel.lar.PortletDataHandlerControl {

	public PortletDataHandlerControl(String namespace, String controlName) {
		super(namespace, controlName);
	}

	public PortletDataHandlerControl(
		String namespace, String controlName, boolean disabled) {

		super(namespace, controlName, disabled);
	}

}