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

package com.liferay.portlet.documentlibrary.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.documentlibrary.model.DLFolder;

/**
 * @author Shinn Lok
 */
public class FolderIdComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "DLFolder.folderId ASC";

	public static final String ORDER_BY_DESC = "DLFolder.folderId DESC";

	public static final String[] ORDER_BY_FIELDS = {"folderId"};

	public FolderIdComparator() {
		this(false);
	}

	public FolderIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		DLFolder folder1 = (DLFolder)obj1;
		DLFolder folder2 = (DLFolder)obj2;

		long folderId1 = folder1.getFolderId();
		long folderId2 = folder2.getFolderId();

		int value = 0;

		if (folderId1 < folderId2) {
			value = -1;
		}
		else if (folderId1 > folderId2) {
			value = 1;
		}

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