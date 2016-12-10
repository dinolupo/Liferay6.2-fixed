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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.taglib.util.IncludeTag;

import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * @author Brian Wing Shun Chan
 */
public class SectionTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		try {
			_tabsTag = (TabsTag)findAncestorWithClass(this, TabsTag.class);

			if (_tabsTag == null) {
				throw new JspException();
			}

			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			PortletResponse portletResponse =
				(PortletResponse)request.getAttribute(
					JavaConstants.JAVAX_PORTLET_RESPONSE);

			String namespace = StringPool.BLANK;

			if (portletResponse != null) {
				namespace = portletResponse.getNamespace();
			}

			String sectionParam = _tabsTag.getParam();
			String sectionName = _tabsTag.getSectionName();
			_sectionSelected = Boolean.valueOf(_tabsTag.getSectionSelected());
			String sectionScroll = namespace + sectionParam + "TabsScroll";
			String sectionRedirectParams =
				"&scroll=" + sectionScroll + "&" + sectionParam + "=" +
					sectionName;

			_tabsTag.incrementSection();

			request.setAttribute("liferay-ui:section:param", sectionParam);
			request.setAttribute("liferay-ui:section:name", sectionName);
			request.setAttribute(
				"liferay-ui:section:selected", _sectionSelected);
			request.setAttribute("liferay-ui:section:scroll", sectionScroll);

			pageContext.setAttribute("sectionSelected", _sectionSelected);
			pageContext.setAttribute("sectionParam", sectionParam);
			pageContext.setAttribute("sectionName", sectionName);
			pageContext.setAttribute("sectionScroll", sectionScroll);
			pageContext.setAttribute(
				"sectionRedirectParams", sectionRedirectParams);

			include(getStartPage());

			if (!_tabsTag.isRefresh() || _sectionSelected.booleanValue()) {
				return EVAL_BODY_INCLUDE;
			}
			else {
				return EVAL_PAGE;
			}
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected int processEndTag() throws Exception {
		JspWriter jspWriter = pageContext.getOut();

		jspWriter.write("</div>");

		return EVAL_PAGE;
	}

	private static final String _END_PAGE = "/html/taglib/ui/section/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ui/section/start.jsp";

	private Boolean _sectionSelected = Boolean.FALSE;
	private TabsTag _tabsTag = null;

}