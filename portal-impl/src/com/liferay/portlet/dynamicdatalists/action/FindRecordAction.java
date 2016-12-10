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

package com.liferay.portlet.dynamicdatalists.action;

import com.liferay.portal.struts.FindAction;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class FindRecordAction extends FindAction {

	@Override
	protected long getGroupId(long primaryKey) throws Exception {
		DDLRecord record = DDLRecordLocalServiceUtil.getRecord(primaryKey);

		return record.getGroupId();
	}

	@Override
	protected String getPrimaryKeyParameterName() {
		return "recordId";
	}

	@Override
	protected String getStrutsAction(
		HttpServletRequest request, String portletId) {

		return "/dynamic_data_list_display/view_record";
	}

	@Override
	protected String[] initPortletIds() {
		return new String[] {PortletKeys.DYNAMIC_DATA_LIST_DISPLAY};
	}

}