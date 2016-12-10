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

package com.liferay.portlet.invitation.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Brian Wing Shun Chan
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		validateEmailMessage(actionRequest);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void validateEmailMessage(ActionRequest actionRequest) {
		String emailMessageSubject = getParameter(
			actionRequest, "emailMessageSubject");
		String emailMessageBody = getParameter(
			actionRequest, "emailMessageBody");

		if (Validator.isNull(emailMessageSubject)) {
			SessionErrors.add(actionRequest, "emailMessageSubject");
		}
		else if (Validator.isNull(emailMessageBody)) {
			SessionErrors.add(actionRequest, "emailMessageBody");
		}
	}

}