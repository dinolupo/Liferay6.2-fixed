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

package com.liferay.portal.kernel.servlet.taglib;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BodyContentWrapper;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-13878.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class BaseBodyTagSupport extends TagSupport {

	@SuppressWarnings("unused")
	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}

	@SuppressWarnings("unused")
	public void doInitBody() throws JspException {
	}

	@Override
	@SuppressWarnings("unused")
	public int doStartTag() throws JspException {
		return BodyTag.EVAL_BODY_BUFFERED;
	}

	public BodyContent getBodyContent() {
		return bodyContent;
	}

	public StringBundler getBodyContentAsStringBundler() {
		if (!(this instanceof BodyTag)) {
			throw new RuntimeException(
				getClass().getName() + " must implement " +
					BodyTag.class.getName());
		}

		BodyContent bodyContent = getBodyContent();

		if (bodyContent instanceof BodyContentWrapper) {
			BodyContentWrapper bodyContentWrapper =
				(BodyContentWrapper)bodyContent;

			return bodyContentWrapper.getStringBundler();
		}

		if (bodyContent == null) {
			return new StringBundler();
		}

		if (ServerDetector.isTomcat() && _log.isWarnEnabled()) {
			_log.warn(
				"BodyContent is not BodyContentWrapper. Check " +
					"JspFactorySwapper.");
		}

		String bodyContentString = bodyContent.getString();

		if (bodyContentString == null) {
			return new StringBundler();
		}

		return new StringBundler(bodyContentString);
	}

	@Override
	public void release() {
		bodyContent = null;

		super.release();
	}

	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
	}

	public void writeBodyContent(Writer writer) throws IOException {
		StringBundler sb = getBodyContentAsStringBundler();

		sb.writeTo(writer);
	}

	protected BodyContent bodyContent;

	private static Log _log = LogFactoryUtil.getLog(BaseBodyTagSupport.class);

}