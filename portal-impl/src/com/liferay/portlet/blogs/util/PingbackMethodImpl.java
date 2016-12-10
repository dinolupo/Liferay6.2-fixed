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

package com.liferay.portlet.blogs.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapperThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xmlrpc.Method;
import com.liferay.portal.kernel.xmlrpc.Response;
import com.liferay.portal.kernel.xmlrpc.XmlRpcConstants;
import com.liferay.portal.kernel.xmlrpc.XmlRpcUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageDisplay;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

import java.io.IOException;

import java.net.URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;
import net.htmlparser.jericho.TextExtractor;

/**
 * @author Alexander Chow
 */
public class PingbackMethodImpl implements Method {

	public static final int ACCESS_DENIED = 49;

	public static final int GENERIC_FAULT = 0;

	public static final int PINGBACK_ALREADY_REGISTERED = 48;

	public static final int SERVER_ERROR = 50;

	public static final int SOURCE_URI_DOES_NOT_EXIST = 16;

	public static final int SOURCE_URI_INVALID = 17;

	public static final int TARGET_URI_DOES_NOT_EXIST = 32;

	public static final int TARGET_URI_INVALID = 33;

	@Override
	public Response execute(long companyId) {
		if (!PropsValues.BLOGS_PINGBACK_ENABLED) {
			return XmlRpcUtil.createFault(
				XmlRpcConstants.REQUESTED_METHOD_NOT_FOUND,
				"Pingbacks are disabled");
		}

		Response response = validateSource();

		if (response != null) {
			return response;
		}

		try {
			BlogsEntry entry = getBlogsEntry(companyId);

			if (!entry.isAllowPingbacks()) {
				return XmlRpcUtil.createFault(
					XmlRpcConstants.REQUESTED_METHOD_NOT_FOUND,
					"Pingbacks are disabled");
			}

			long userId = UserLocalServiceUtil.getDefaultUserId(companyId);
			long groupId = entry.getGroupId();
			String className = BlogsEntry.class.getName();
			long classPK = entry.getEntryId();

			MBMessageDisplay messageDisplay =
				MBMessageLocalServiceUtil.getDiscussionMessageDisplay(
					userId, groupId, className, classPK,
					WorkflowConstants.STATUS_APPROVED);

			MBThread thread = messageDisplay.getThread();

			long threadId = thread.getThreadId();
			long parentMessageId = thread.getRootMessageId();
			String body =
				"[...] " + getExcerpt() + " [...] [url=" + _sourceUri + "]" +
					LanguageUtil.get(LocaleUtil.getSiteDefault(), "read-more") +
						"[/url]";

			List<MBMessage> messages =
				MBMessageLocalServiceUtil.getThreadMessages(
					threadId, WorkflowConstants.STATUS_APPROVED);

			for (MBMessage message : messages) {
				if (message.getBody().equals(body)) {
					return XmlRpcUtil.createFault(
						PINGBACK_ALREADY_REGISTERED,
						"Pingback previously registered");
				}
			}

			ServiceContext serviceContext = new ServiceContext();

			String pingbackUserName = LanguageUtil.get(
				LocaleUtil.getSiteDefault(), "pingback");

			serviceContext.setAttribute("pingbackUserName", pingbackUserName);

			StringBundler sb = new StringBundler(5);

			String layoutFullURL = PortalUtil.getLayoutFullURL(
				groupId, PortletKeys.BLOGS);

			sb.append(layoutFullURL);

			sb.append(Portal.FRIENDLY_URL_SEPARATOR);

			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				companyId, PortletKeys.BLOGS);

			sb.append(portlet.getFriendlyURLMapping());
			sb.append(StringPool.SLASH);
			sb.append(entry.getUrlTitle());

			serviceContext.setAttribute("redirect", sb.toString());

			serviceContext.setLayoutFullURL(layoutFullURL);

			MBMessageLocalServiceUtil.addDiscussionMessage(
				userId, StringPool.BLANK, groupId, className, classPK, threadId,
				parentMessageId, StringPool.BLANK, body, serviceContext);

			return XmlRpcUtil.createSuccess("Pingback accepted");
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			return XmlRpcUtil.createFault(
				TARGET_URI_INVALID, "Error parsing target URI");
		}
	}

	@Override
	public String getMethodName() {
		return "pingback.ping";
	}

	@Override
	public String getToken() {
		return "pingback";
	}

	@Override
	public boolean setArguments(Object[] arguments) {
		try {
			_sourceUri = (String)arguments[0];
			_targetUri = (String)arguments[1];

			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	protected BlogsEntry getBlogsEntry(long companyId) throws Exception {
		BlogsEntry entry = null;

		URL url = new URL(_targetUri);

		String friendlyURL = url.getPath();

		int end = friendlyURL.indexOf(Portal.FRIENDLY_URL_SEPARATOR);

		if (end != -1) {
			friendlyURL = friendlyURL.substring(0, end);
		}

		long plid = PortalUtil.getPlidFromFriendlyURL(companyId, friendlyURL);
		long groupId = PortalUtil.getScopeGroupId(plid);

		Map<String, String[]> params = new HashMap<String, String[]>();

		FriendlyURLMapperThreadLocal.setPRPIdentifiers(
			new HashMap<String, String>());

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			PortletKeys.BLOGS);

		FriendlyURLMapper friendlyURLMapper =
			portlet.getFriendlyURLMapperInstance();

		friendlyURL = url.getPath();

		end = friendlyURL.indexOf(Portal.FRIENDLY_URL_SEPARATOR);

		if (end != -1) {
			friendlyURL = friendlyURL.substring(
				end + Portal.FRIENDLY_URL_SEPARATOR.length() - 1);
		}

		Map<String, Object> requestContext = new HashMap<String, Object>();

		friendlyURLMapper.populateParams(friendlyURL, params, requestContext);

		String param = getParam(params, "entryId");

		if (Validator.isNotNull(param)) {
			long entryId = GetterUtil.getLong(param);

			entry = BlogsEntryLocalServiceUtil.getEntry(entryId);
		}
		else {
			String urlTitle = getParam(params, "urlTitle");

			entry = BlogsEntryLocalServiceUtil.getEntry(groupId, urlTitle);
		}

		return entry;
	}

	protected String getExcerpt() throws IOException {
		String html = HttpUtil.URLtoString(_sourceUri);

		Source source = new Source(html);

		source.fullSequentialParse();

		List<Element> elements = source.getAllElements("a");

		for (Element element : elements) {
			String href = GetterUtil.getString(
				element.getAttributeValue("href"));

			if (href.equals(_targetUri)) {
				element = element.getParentElement();

				TextExtractor textExtractor = new TextExtractor(element);

				String body = textExtractor.toString();

				if (body.length() < PropsValues.BLOGS_LINKBACK_EXCERPT_LENGTH) {
					element = element.getParentElement();

					if (element != null) {
						textExtractor = new TextExtractor(element);

						body = textExtractor.toString();
					}
				}

				return StringUtil.shorten(
					body, PropsValues.BLOGS_LINKBACK_EXCERPT_LENGTH);
			}
		}

		return StringPool.BLANK;
	}

	protected String getParam(Map<String, String[]> params, String name) {
		String[] paramArray = params.get(name);

		if (paramArray == null) {
			String namespace = PortalUtil.getPortletNamespace(
				PortletKeys.BLOGS);

			paramArray = params.get(namespace + name);
		}

		if (ArrayUtil.isNotEmpty(paramArray)) {
			return paramArray[0];
		}
		else {
			return null;
		}
	}

	protected Response validateSource() {
		Source source = null;

		try {
			String html = HttpUtil.URLtoString(_sourceUri);

			source = new Source(html);
		}
		catch (Exception e) {
			return XmlRpcUtil.createFault(
				SOURCE_URI_DOES_NOT_EXIST, "Error accessing source URI");
		}

		List<StartTag> startTags = source.getAllStartTags("a");

		for (StartTag startTag : startTags) {
			String href = GetterUtil.getString(
				startTag.getAttributeValue("href"));

			if (href.equals(_targetUri)) {
				return null;
			}
		}

		return XmlRpcUtil.createFault(
			SOURCE_URI_INVALID, "Could not find target URI in source");
	}

	private static Log _log = LogFactoryUtil.getLog(PingbackMethodImpl.class);

	private String _sourceUri;
	private String _targetUri;

}