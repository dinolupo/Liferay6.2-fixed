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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.portal.upgrade.util.UpgradePortletId;
import com.liferay.portal.util.PortletKeys;

/**
 * @author Eduardo Garcia
 */
public class UpgradeImageGallery extends UpgradePortletId {

	@Override
	protected String[] getUninstanceablePortletIds() {
		return new String[] {PortletKeys.MEDIA_GALLERY_DISPLAY};
	}

}