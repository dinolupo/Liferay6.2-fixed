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

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.util.Validator;

import java.net.URL;

/**
 * @author Raymond Augé
 */
public class URLWrapper {

	public URLWrapper(URL url) {
		_url = url;

		_urlString = _url.toString();

		_hashCode = _urlString.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof URLWrapper)) {
			return false;
		}

		URLWrapper urlWrapper = (URLWrapper)obj;

		if ((_url == urlWrapper._url) ||
			Validator.equals(_urlString, urlWrapper._urlString)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return _hashCode;
	}

	private int _hashCode;
	private URL _url;
	private String _urlString;

}