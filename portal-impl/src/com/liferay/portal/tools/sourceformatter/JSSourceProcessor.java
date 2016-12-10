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

package com.liferay.portal.tools.sourceformatter;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class JSSourceProcessor extends BaseSourceProcessor {

	@Override
	protected void format() throws Exception {
		String[] excludes = {
			"**\\js\\aui\\**", "**\\js\\editor\\**", "**\\js\\misc\\**",
			"**\\tools\\**", "**\\VAADIN\\**"
		};
		String[] includes = {"**\\*.js"};

		List<String> fileNames = getFileNames(excludes, includes);

		for (String fileName : fileNames) {
			format(fileName);
		}
	}

	@Override
	protected String format(String fileName) throws Exception {
		File file = new File(BASEDIR + fileName);

		fileName = StringUtil.replace(
			fileName, StringPool.BACK_SLASH, StringPool.SLASH);

		String content = fileUtil.read(file);

		String newContent = trimContent(content, false);

		newContent = StringUtil.replace(
			newContent,
			new String[] {
				"else{", "for(", "function (", "if(", "while(", "){\n",
				"= new Array();", "= new Object();"
			},
			new String[] {
				"else {", "for (", "function(", "if (", "while (", ") {\n",
				"= [];", "= {};"
			});

		while (true) {
			Matcher matcher = _multipleVarsOnSingleLinePattern.matcher(
				newContent);

			if (!matcher.find()) {
				break;
			}

			String match = matcher.group();

			int pos = match.indexOf("var ");

			StringBundler sb = new StringBundler(4);

			sb.append(match.substring(0, match.length() - 2));
			sb.append(StringPool.SEMICOLON);
			sb.append("\n");
			sb.append(match.substring(0, pos + 4));

			newContent = StringUtil.replace(newContent, match, sb.toString());
		}

		if (newContent.endsWith("\n")) {
			newContent = newContent.substring(0, newContent.length() - 1);
		}

		checkLanguageKeys(fileName, newContent, languageKeyPattern);

		if (isAutoFix() && (newContent != null) &&
			!content.equals(newContent)) {

			fileUtil.write(file, newContent);

			sourceFormatterHelper.printError(fileName, file);
		}

		return newContent;
	}

	private Pattern _multipleVarsOnSingleLinePattern = Pattern.compile(
		"\t+var \\w+\\, ");

}