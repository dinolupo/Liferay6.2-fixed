<%--
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
--%>

<%@ include file="/html/taglib/aui/nav_item/init.jsp" %>

<%@ page import="javax.servlet.jsp.tagext.BodyContent" %>

<%
BodyContent bodyContent = (BodyContent)request.getAttribute("aui:nav-item:bodyContent");

String bodyContentString = StringPool.BLANK;

if (bodyContent != null) {
	bodyContentString = bodyContent.getString();
}

if (Validator.isNull(title)) {
	title = HtmlUtil.stripHtml(LanguageUtil.get(pageContext, label));
}
%>

<c:if test="<%= !dropdown || Validator.isNotNull(bodyContentString.trim()) %>">
	<li class="<%= cssClass %><%= selected ? " active " : StringPool.SPACE %><%= state %>" id="<%= id %>" role="presentation" <%= AUIUtil.buildData(data) %> <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
		<c:if test="<%= Validator.isNotNull(iconCssClass) || Validator.isNotNull(label) %>">
			<c:if test="<%= Validator.isNotNull(href) %>">
				<a <%= Validator.isNotNull(ariaLabel) ? "aria-label=\"" + ariaLabel + "\"" : StringPool.BLANK %> class="<%= anchorCssClass %>" <%= AUIUtil.buildData(anchorData) %> href="<%= href %>" id="<%= anchorId %>" role="<%= Validator.isNull(ariaRole) ? "menuitem" : ariaRole %>" title="<liferay-ui:message key="<%= title %>" />">

				<c:if test="<%= useDialog %>">
					<aui:script>
						Liferay.delegateClick('<%= anchorId %>', Liferay.Util.openInDialog);
					</aui:script>
				</c:if>
			</c:if>
					<c:if test="<%= Validator.isNotNull(iconCssClass) %>">
						<i class="<%= iconCssClass %>"></i>
					</c:if>

					<span class="nav-item-label">
						<liferay-ui:message key="<%= label %>" localizeKey="<%= localizeLabel %>" />
					</span>

					<c:if test="<%= dropdown %>">
						<i class="icon-caret-down"></i>
					</c:if>
			<c:if test="<%= Validator.isNotNull(href) %>">
				</a>
			</c:if>
		</c:if>

		<c:if test="<%= dropdown %>">
			<aui:script use="aui-base,event-move,event-outside,liferay-menu-toggle,liferay-store">
				var toggleMenu = new Liferay.MenuToggle(
					{
						content: '#<%= id %>',
						toggle: <%= toggle %>,
						toggleTouch: true,
						trigger: '#<%= id %> a'
					}
				);
			</aui:script>

			<c:if test="<%= wrapDropDownMenu %>">
				<ul class="dropdown-menu">
			</c:if>
		</c:if>

		<c:if test="<%= Validator.isNotNull(bodyContentString) %>">
			<%= bodyContentString %>
		</c:if>

		<c:if test="<%= dropdown && wrapDropDownMenu %>">
			</ul>
		</c:if>
	</li>
</c:if>