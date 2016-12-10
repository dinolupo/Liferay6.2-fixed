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

import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.storage.StorageType;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Marcellus Tavares
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
@Transactional
public class DDLRecordServiceTest extends BaseDDLServiceTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();

		DDMStructure ddmStructure = addStructure(
			PortalUtil.getClassNameId(DDLRecordSet.class), null,
			"Test Structure", readText("test-structure.xsd"),
			StorageType.XML.getValue(), DDMStructureConstants.TYPE_DEFAULT);

		recordSet = addRecordSet(ddmStructure.getStructureId());

		addRecord("Joe Bloggs", "Simple description");
		addRecord("Bloggs","Another description example");
	}

	@Test
	public void testSearchByTextAreaField() throws Exception {
		SearchContext searchContext = getSearchContext("example");

		Hits hits = DDLRecordLocalServiceUtil.search(searchContext);

		Assert.assertEquals(1, hits.getLength());

		searchContext.setKeywords("description");

		hits = DDLRecordLocalServiceUtil.search(searchContext);

		Assert.assertEquals(2, hits.getLength());
	}

	@Test
	public void testSearchByTextField() throws Exception {
		SearchContext searchContext = getSearchContext("\"Joe Bloggs\"");

		Hits hits = DDLRecordLocalServiceUtil.search(searchContext);

		Assert.assertEquals(1, hits.getLength());

		searchContext.setKeywords("Bloggs");

		hits = DDLRecordLocalServiceUtil.search(searchContext);

		Assert.assertEquals(1, hits.getLength());
	}

	protected void addRecord(String name, String description) throws Exception {
		Fields fields = new Fields();

		Field nameField = new Field(
			recordSet.getDDMStructureId(), "name", name);

		fields.put(nameField);

		Field descriptionField = new Field(
			recordSet.getDDMStructureId(), "description", description);

		fields.put(descriptionField);

		addRecord(recordSet.getRecordSetId(), fields);
	}

	protected SearchContext getSearchContext(String keywords) throws Exception {
		SearchContext searchContext = ServiceTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setAttribute("recordSetId", recordSet.getRecordSetId());
		searchContext.setAttribute("status", WorkflowConstants.STATUS_ANY);
		searchContext.setKeywords(keywords);

		return searchContext;
	}

	protected DDLRecordSet recordSet;

}