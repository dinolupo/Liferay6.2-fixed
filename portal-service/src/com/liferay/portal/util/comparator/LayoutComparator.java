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

package com.liferay.portal.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Layout;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"Layout.groupId ASC, Layout.layoutId ASC";

	public static final String ORDER_BY_DESC =
		"Layout.groupId DESC, Layout.layoutId DESC";

	public static final String[] ORDER_BY_FIELDS = {"groupId", "layoutId"};

	public LayoutComparator() {
		this(false);
	}

	public LayoutComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		Layout layout1 = (Layout)obj1;
		Layout layout2 = (Layout)obj2;

		Long groupId1 = new Long(layout1.getGroupId());
		Long groupId2 = new Long(layout2.getGroupId());

		int value = groupId1.compareTo(groupId2);

		if (value != 0) {
			if (_ascending) {
				return value;
			}
			else {
				return -value;
			}
		}

		Long layoutId1 = new Long(layout1.getLayoutId());
		Long layoutId2 = new Long(layout2.getLayoutId());

		value = layoutId1.compareTo(layoutId2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}