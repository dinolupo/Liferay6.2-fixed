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

package com.liferay.portlet.documentlibrary.antivirus;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Michael C. Han
 * @author Hugo Huijser
 */
public class AntivirusScannerException extends PortalException {

	public static final int PROCESS_FAILURE = 1;

	public static final int VIRUS_DETECTED = 2;

	public AntivirusScannerException() {
		super();
	}

	public AntivirusScannerException(int type) {
		_type = type;
	}

	public AntivirusScannerException(String msg) {
		super(msg);
	}

	public AntivirusScannerException(String msg, int type) {
		super(msg);

		_type = type;
	}

	public AntivirusScannerException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AntivirusScannerException(Throwable cause) {
		super(cause);
	}

	public String getMessageKey() {
		if (_type == PROCESS_FAILURE) {
			return "unable-to-scan-file-for-viruses";
		}
		else if (_type == VIRUS_DETECTED) {
			return "a-virus-was-detected-in-the-file";
		}

		return null;
	}

	private int _type;

}