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

package com.liferay.portal.kernel.upload;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Igor Spasic
 */
public class UploadException extends PortalException {

	public UploadException() {
		super();
	}

	public UploadException(String msg) {
		super(msg);
	}

	public UploadException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UploadException(Throwable cause) {
		super(cause);
	}

	public boolean isExceededLiferayFileItemSizeLimit() {
		return _exceededLiferayFileItemSizeLimit;
	}

	public boolean isExceededSizeLimit() {
		return _exceededSizeLimit;
	}

	public void setExceededLiferayFileItemSizeLimit(
		boolean exceededLiferayFileItemSizeLimit) {

		_exceededLiferayFileItemSizeLimit = exceededLiferayFileItemSizeLimit;
	}

	public void setExceededSizeLimit(boolean exceededSizeLimit) {
		_exceededSizeLimit = exceededSizeLimit;
	}

	private boolean _exceededLiferayFileItemSizeLimit;
	private boolean _exceededSizeLimit;

}