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

package com.liferay.portlet.messageboards.pop;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.pop.MessageListener;
import com.liferay.portal.kernel.pop.MessageListenerException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionCheckerUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageConstants;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;
import com.liferay.portlet.messageboards.util.MBMailMessage;
import com.liferay.portlet.messageboards.util.MBUtil;

import java.io.InputStream;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Michael C. Han
 */
public class MessageListenerImpl implements MessageListener {

	@Override
	public boolean accept(String from, String recipient, Message message) {
		try {
			if (isAutoReply(message)) {
				return false;
			}

			String messageIdString = getMessageIdString(recipient, message);

			if ((messageIdString == null) ||
				!messageIdString.startsWith(
					MBUtil.MESSAGE_POP_PORTLET_PREFIX,
					MBUtil.getMessageIdStringOffset())) {

				return false;
			}

			Company company = getCompany(messageIdString);
			long categoryId = MBUtil.getCategoryId(messageIdString);

			MBCategory category = MBCategoryLocalServiceUtil.getCategory(
				categoryId);

			if ((category.getCompanyId() != company.getCompanyId()) &&
				!category.isRoot()) {

				return false;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Check to see if user " + from + " exists");
			}

			String pop3User = PrefsPropsUtil.getString(
				PropsKeys.MAIL_SESSION_MAIL_POP3_USER,
				PropsValues.MAIL_SESSION_MAIL_POP3_USER);

			if (StringUtil.equalsIgnoreCase(from, pop3User)) {
				return false;
			}

			UserLocalServiceUtil.getUserByEmailAddress(
				company.getCompanyId(), from);

			return true;
		}
		catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to process message: " + message, e);
			}

			return false;
		}
	}

	@Override
	public void deliver(String from, String recipient, Message message)
		throws MessageListenerException {

		List<ObjectValuePair<String, InputStream>> inputStreamOVPs = null;

		try {
			StopWatch stopWatch = new StopWatch();

			stopWatch.start();

			if (_log.isDebugEnabled()) {
				_log.debug("Deliver message from " + from + " to " + recipient);
			}

			String messageIdString = getMessageIdString(recipient, message);

			Company company = getCompany(messageIdString);

			if (_log.isDebugEnabled()) {
				_log.debug("Message id " + messageIdString);
			}

			long parentMessageId = MBUtil.getMessageId(messageIdString);

			if (_log.isDebugEnabled()) {
				_log.debug("Parent message id " + parentMessageId);
			}

			MBMessage parentMessage = null;

			if (parentMessageId > 0) {
				parentMessage = MBMessageLocalServiceUtil.fetchMBMessage(
					parentMessageId);
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Parent message " + parentMessage);
			}

			long groupId = 0;
			long categoryId = MBUtil.getCategoryId(messageIdString);

			MBCategory category = MBCategoryLocalServiceUtil.fetchMBCategory(
				categoryId);

			if (category == null) {
				categoryId = MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID;

				if (parentMessage != null) {
					groupId = parentMessage.getGroupId();
				}
			}
			else {
				groupId = category.getGroupId();
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Group id " + groupId);
				_log.debug("Category id " + categoryId);
			}

			User user = UserLocalServiceUtil.getUserByEmailAddress(
				company.getCompanyId(), from);

			String subject = null;

			if (parentMessage != null) {
				subject = MBUtil.getSubjectForEmail(parentMessage);
			}

			MBMailMessage mbMailMessage = new MBMailMessage();

			MBUtil.collectPartContent(message, mbMailMessage);

			inputStreamOVPs = mbMailMessage.getInputStreamOVPs();

			PermissionCheckerUtil.setThreadValues(user);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAttribute("propagatePermissions", Boolean.TRUE);
			serviceContext.setLayoutFullURL(
				PortalUtil.getLayoutFullURL(
					groupId, PortletKeys.MESSAGE_BOARDS,
					StringUtil.equalsIgnoreCase(
						Http.HTTPS, PropsValues.WEB_SERVER_PROTOCOL)));
			serviceContext.setScopeGroupId(groupId);

			if (parentMessage == null) {
				MBMessageServiceUtil.addMessage(
					groupId, categoryId, subject, mbMailMessage.getBody(),
					MBMessageConstants.DEFAULT_FORMAT, inputStreamOVPs, false,
					0.0, true, serviceContext);
			}
			else {
				MBMessageServiceUtil.addMessage(
					parentMessage.getMessageId(), subject,
					mbMailMessage.getBody(), MBMessageConstants.DEFAULT_FORMAT,
					inputStreamOVPs, false, 0.0, true, serviceContext);
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Delivering message takes " + stopWatch.getTime() + " ms");
			}
		}
		catch (PrincipalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug("Prevented unauthorized post from " + from);
			}

			throw new MessageListenerException(pe);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new MessageListenerException(e);
		}
		finally {
			if (inputStreamOVPs != null) {
				for (ObjectValuePair<String, InputStream> inputStreamOVP :
						inputStreamOVPs) {

					InputStream inputStream = inputStreamOVP.getValue();

					StreamUtil.cleanUp(inputStream);
				}
			}

			PermissionCheckerUtil.setThreadValues(null);
		}
	}

	@Override
	public String getId() {
		return MessageListenerImpl.class.getName();
	}

	protected Company getCompany(String messageIdString) throws Exception {
		int pos =
			messageIdString.indexOf(CharPool.AT) +
				PropsValues.POP_SERVER_SUBDOMAIN.length() + 1;

		if (PropsValues.POP_SERVER_SUBDOMAIN.length() > 0) {
			pos++;
		}

		int endPos = messageIdString.indexOf(CharPool.GREATER_THAN, pos);

		if (endPos == -1) {
			endPos = messageIdString.length();
		}

		String mx = messageIdString.substring(pos, endPos);

		return CompanyLocalServiceUtil.getCompanyByMx(mx);
	}

	protected String getMessageIdString(String recipient, Message message)
		throws Exception {

		if (PropsValues.POP_SERVER_SUBDOMAIN.length() > 0) {
			return recipient;
		}
		else {
			return MBUtil.getParentMessageIdString(message);
		}
	}

	protected boolean isAutoReply(Message message) throws MessagingException {
		String[] autoReply = message.getHeader("X-Autoreply");

		if (ArrayUtil.isNotEmpty(autoReply)) {
			return true;
		}

		String[] autoReplyFrom = message.getHeader("X-Autoreply-From");

		if (ArrayUtil.isNotEmpty(autoReplyFrom)) {
			return true;
		}

		String[] mailAutoReply = message.getHeader("X-Mail-Autoreply");

		if (ArrayUtil.isNotEmpty(mailAutoReply)) {
			return true;
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(MessageListenerImpl.class);

}