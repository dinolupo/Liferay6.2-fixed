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
public class TestCaseConverter extends BaseConverter {

	public TestCaseConverter(SeleniumBuilderContext seleniumBuilderContext) {
		super(seleniumBuilderContext);
	}

	public void convert(String testCaseName) throws Exception {
		Map<String, Object> context = getContext();

		context.put("macroNameStack", new FreeMarkerStack());
		context.put("testCaseName", testCaseName);

		String javaContent = processTemplate("test_case.ftl", context);

		seleniumBuilderFileUtil.writeFile(
			seleniumBuilderContext.getTestCaseJavaFileName(testCaseName),
			javaContent, true);

		String htmlContent = processTemplate("test_case_html.ftl", context);

		seleniumBuilderFileUtil.writeFile(
			seleniumBuilderContext.getTestCaseHTMLFileName(testCaseName),
			htmlContent, false);
	}

}