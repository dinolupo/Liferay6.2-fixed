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

package com.liferay.portal.kernel.search.facet.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Raymond Augé
 */
public class RangeParserUtil {

	public static String[] parserRange(String range) {
		range = StringUtil.replace(
			range,
			new String[] {
				StringPool.OPEN_CURLY_BRACE, StringPool.CLOSE_CURLY_BRACE
			},
			new String[] {
				StringPool.OPEN_BRACKET, StringPool.CLOSE_BRACKET
			}
		);

		int x = range.indexOf(StringPool.OPEN_BRACKET);
		int y = range.indexOf(" TO ");
		int z = range.indexOf(StringPool.CLOSE_BRACKET);

		String lower = range.substring(x + 1, y).trim();
		String upper = range.substring(y + 4, z).trim();

		return new String[] {lower, upper};
	}

}