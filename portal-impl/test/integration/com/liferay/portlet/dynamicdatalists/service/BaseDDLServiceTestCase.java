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

package com.liferay.portlet.dynamicdatalists.service;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordConstants;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSetConstants;
import com.liferay.portlet.dynamicdatamapping.service.BaseDDMServiceTestCase;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class BaseDDLServiceTestCase  extends BaseDDMServiceTestCase {

	protected DDLRecord addRecord(long recordSetId, Fields fields)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setUserId(TestPropsValues.getUserId());

		return DDLRecordLocalServiceUtil.addRecord(
			TestPropsValues.getUserId(), group.getGroupId(), recordSetId,
			DDLRecordConstants.DISPLAY_INDEX_DEFAULT, fields, serviceContext);
	}

	protected DDLRecordSet addRecordSet(long ddmStructureId) throws Exception {
		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(LocaleUtil.US, "Test Record Set");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return DDLRecordSetLocalServiceUtil.addRecordSet(
			TestPropsValues.getUserId(), group.getGroupId(), ddmStructureId,
			null, nameMap, null, DDLRecordSetConstants.MIN_DISPLAY_ROWS_DEFAULT,
			DDLRecordSetConstants.SCOPE_DYNAMIC_DATA_LISTS, serviceContext);
	}

	@Override
	protected String getBasePath() {
		return "com/liferay/portlet/dynamicdatalists/dependencies/";
	}

}