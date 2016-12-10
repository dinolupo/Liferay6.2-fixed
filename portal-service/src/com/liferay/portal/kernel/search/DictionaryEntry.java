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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Michael C. Han
 */
public class DictionaryEntry {

	public DictionaryEntry(String line) {
		String[] values = StringUtil.split(line, StringPool.SPACE);

		_word = values[0];

		if (values.length == 2) {
			_weight = GetterUtil.getFloat(values[1]);
		}
	}

	public float getWeight() {
		return _weight;
	}

	public String getWord() {
		return _word;
	}

	private float _weight;
	private String _word;

}