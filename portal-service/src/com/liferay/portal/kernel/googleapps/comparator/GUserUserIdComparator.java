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

package com.liferay.portal.kernel.googleapps.comparator;

import com.liferay.portal.kernel.googleapps.GUser;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class GUserUserIdComparator implements Comparator<GUser> {

	public GUserUserIdComparator() {
		this(true);
	}

	public GUserUserIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(GUser user1, GUser user2) {
		long userId1 = user1.getUserId();
		long userId2 = user2.getUserId();

		int value = 0;

		if (userId1 < userId2) {
			value = -1;
		}
		else if (userId1 > userId2) {
			value = 1;
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _ascending;

}