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

package com.liferay.portal.sharepoint.dws;

import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Role;

/**
 * @author Bruno Farache
 */
public class RoleResponseElement implements ResponseElement {

	public RoleResponseElement(Role role) {
		_name = role.getName();
		_description = role.getDescription();
		_type = role.getTypeLabel();
	}

	@Override
	public void addElement(Element rootEl) {
		Element el = rootEl.addElement("Role");

		el.addAttribute("Name", _name);
		el.addAttribute("Description", _description);
		el.addAttribute("Type", _type);
	}

	private String _description;
	private String _name;
	private String _type;

}