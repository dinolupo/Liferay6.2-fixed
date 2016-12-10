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

package com.liferay.portal.dao.orm.jpa;

import com.liferay.portal.kernel.dao.orm.LockMode;

import javax.persistence.LockModeType;

/**
 * @author Prashant Dighe
 * @author Brian Wing Shun Chan
 */
public class LockModeTranslator {

	public static LockModeType translate(LockMode lockMode) {
		if (lockMode == LockMode.READ) {
			return LockModeType.READ;
		}
		else if (lockMode == LockMode.WRITE) {
			return LockModeType.WRITE;
		}
		else {
			return null;
		}
	}

}