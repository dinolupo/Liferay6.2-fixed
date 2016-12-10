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

package com.liferay.portlet.trash;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class DuplicateEntryException extends PortalException {

	public DuplicateEntryException() {
		super();
	}

	public DuplicateEntryException(String msg) {
		super(msg);
	}

	public DuplicateEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DuplicateEntryException(Throwable cause) {
		super(cause);
	}

	public long getDuplicateEntryId() {
		return _duplicateEntryId;
	}

	public String getOldName() {
		return _oldName;
	}

	public long getTrashEntryId() {
		return _trashEntryId;
	}

	public void setDuplicateEntryId(long duplicateEntryId) {
		_duplicateEntryId = duplicateEntryId;
	}

	public void setOldName(String oldName) {
		_oldName = oldName;
	}

	public void setTrashEntryId(long trashEntryId) {
		_trashEntryId = trashEntryId;
	}

	private long _duplicateEntryId;
	private String _oldName;
	private long _trashEntryId;

}