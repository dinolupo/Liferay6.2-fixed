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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;

/**
 * @author Juan Fernández
 */
public class AddDefaultDataAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		SimpleAction addDefaultDocumentLibraryStructuresAction =
			new AddDefaultDocumentLibraryStructuresAction();

		addDefaultDocumentLibraryStructuresAction.run(ids);

		SimpleAction addDefaultLayoutPrototypesAction =
			new AddDefaultLayoutPrototypesAction();

		addDefaultLayoutPrototypesAction.run(ids);

		SimpleAction addDefaultLayoutSetPrototypesAction =
			new AddDefaultLayoutSetPrototypesAction();

		addDefaultLayoutSetPrototypesAction.run(ids);

		SimpleAction addDefaultDDMStructuresAction =
			new AddDefaultDDMStructuresAction();

		addDefaultDDMStructuresAction.run(ids);

		SimpleAction addDefaultDDMTemplatesAction =
			new AddDefaultDDMTemplatesAction();

		addDefaultDDMTemplatesAction.run(ids);
	}

}