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

package com.liferay.portal.tools.seleniumbuilder;

import java.util.Map;

/**
 * @author Michael Hashimoto
 */
public class FunctionConverter extends BaseConverter {

	public FunctionConverter(SeleniumBuilderContext seleniumBuilderContext) {
		super(seleniumBuilderContext);
	}

	public void convert(String functionName) throws Exception {
		Map<String, Object> context = getContext();

		context.put("functionName", functionName);

		String content = processTemplate("function.ftl", context);

		seleniumBuilderFileUtil.writeFile(
			seleniumBuilderContext.getFunctionJavaFileName(functionName),
			content, true);
	}

}