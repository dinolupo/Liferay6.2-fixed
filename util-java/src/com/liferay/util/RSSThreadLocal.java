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

package com.liferay.util;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

/**
 * @author Shuyang Zhou
 */
public class RSSThreadLocal {

	public static boolean isExportRSS() {
		return _exportRSS.get();
	}

	public static void setExportRSS(boolean exportRSS) {
		_exportRSS.set(exportRSS);
	}

	private static ThreadLocal<Boolean> _exportRSS =
		new AutoResetThreadLocal<Boolean>(
			RSSThreadLocal.class + "._exportRSS", false);

}