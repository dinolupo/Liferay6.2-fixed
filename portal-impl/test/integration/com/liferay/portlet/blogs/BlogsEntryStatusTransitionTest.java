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

package com.liferay.portlet.blogs;

import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portal.util.UserTestUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.blogs.social.BlogsActivityKeys;
import com.liferay.portlet.blogs.util.BlogsTestUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class BlogsEntryStatusTransitionTest extends BaseBlogsEntryTestCase {

	@Before
	public void setUp() throws Exception {
		FinderCacheUtil.clearCache();

		group = GroupTestUtil.addGroup();

		User user = UserTestUtil.addUser(
			ServiceTestUtil.randomString(), group.getGroupId());

		entry = BlogsTestUtil.addEntry(user.getUserId(), group, false);
	}

	@After
	public void tearDown() throws Exception {
		GroupLocalServiceUtil.deleteGroup(group);
	}

	@Test
	public void testApprovedToDraft() throws Exception {
		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_DRAFT,
			getServiceContext(entry));

		Assert.assertFalse(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(0, searchBlogsEntriesCount(group.getGroupId()));
	}

	@Test
	public void testApprovedToTrash() throws Exception {
		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_IN_TRASH,
			getServiceContext(entry));

		Assert.assertFalse(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(0, searchBlogsEntriesCount(group.getGroupId()));
	}

	@Test
	public void testDraftToApprovedByAdd() throws Exception {
		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		Assert.assertTrue(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(1, searchBlogsEntriesCount(group.getGroupId()));

		checkSocialActivity(BlogsActivityKeys.ADD_ENTRY, 1);
	}

	@Test
	public void testDraftToApprovedByUpdate() throws Exception {
		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_DRAFT,
			getServiceContext(entry));

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		Assert.assertTrue(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(1, searchBlogsEntriesCount(group.getGroupId()));

		checkSocialActivity(BlogsActivityKeys.UPDATE_ENTRY, 1);
	}

	@Test
	public void testDraftToScheduledByAdd() throws Exception {
		Calendar displayDate = new GregorianCalendar();

		displayDate.add(Calendar.DATE, 1);

		entry.setDisplayDate(displayDate.getTime());

		BlogsEntryLocalServiceUtil.updateBlogsEntry(entry);

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		Assert.assertFalse(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(0, searchBlogsEntriesCount(group.getGroupId()));

		checkSocialActivity(BlogsActivityKeys.ADD_ENTRY, 1);

		AssetEntry assetEntry = fetchAssetEntry(entry.getEntryId());

		Assert.assertNull(assetEntry.getPublishDate());
	}

	@Test
	public void testDraftToScheduledUpdate() throws Exception {
		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		entry = BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_DRAFT,
			getServiceContext(entry));

		Calendar displayDate = new GregorianCalendar();

		displayDate.add(Calendar.DATE, 1);

		entry.setDisplayDate(displayDate.getTime());

		BlogsEntryLocalServiceUtil.updateBlogsEntry(entry);

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		Assert.assertFalse(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(0, searchBlogsEntriesCount(group.getGroupId()));

		checkSocialActivity(BlogsActivityKeys.UPDATE_ENTRY, 1);

		AssetEntry assetEntry = fetchAssetEntry(entry.getEntryId());

		Assert.assertNotNull(assetEntry.getPublishDate());
	}

	@Test
	public void testDraftToTrash() throws Exception {
		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_IN_TRASH,
			getServiceContext(entry));

		Assert.assertFalse(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(0, searchBlogsEntriesCount(group.getGroupId()));
	}

	@Test
	public void testScheduledByAddToApproved() throws Exception {
		Calendar displayDate = new GregorianCalendar();

		displayDate.add(Calendar.DATE, 1);

		entry.setDisplayDate(displayDate.getTime());

		BlogsEntryLocalServiceUtil.updateBlogsEntry(entry);

		entry = BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		displayDate.add(Calendar.DATE, -2);

		entry.setDisplayDate(displayDate.getTime());

		BlogsEntryLocalServiceUtil.updateBlogsEntry(entry);

		BlogsEntryLocalServiceUtil.checkEntries();

		Assert.assertTrue(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(1, searchBlogsEntriesCount(group.getGroupId()));
	}

	@Test
	public void testScheduledByUpdateToApproved() throws Exception {
		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		Calendar displayDate = new GregorianCalendar();

		displayDate.add(Calendar.DATE, 1);

		entry.setDisplayDate(displayDate.getTime());
		entry.setStatus(WorkflowConstants.STATUS_DRAFT);

		BlogsEntryLocalServiceUtil.updateBlogsEntry(entry);

		entry = BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		checkSocialActivity(BlogsActivityKeys.UPDATE_ENTRY, 1);

		displayDate.add(Calendar.DATE, -2);

		entry.setDisplayDate(displayDate.getTime());

		BlogsEntryLocalServiceUtil.updateBlogsEntry(entry);

		BlogsEntryLocalServiceUtil.checkEntries();

		Assert.assertTrue(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(1, searchBlogsEntriesCount(group.getGroupId()));

		checkSocialActivity(BlogsActivityKeys.UPDATE_ENTRY, 1);
	}

	@Test
	public void testTrashToApproved() throws Exception {
		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_IN_TRASH,
			getServiceContext(entry));

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		Assert.assertTrue(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(1, searchBlogsEntriesCount(group.getGroupId()));

		checkSocialActivity(BlogsActivityKeys.ADD_ENTRY, 1);
	}

	@Test
	public void testTrashToDraft() throws Exception {
		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_IN_TRASH,
			getServiceContext(entry));

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_DRAFT,
			getServiceContext(entry));

		Assert.assertFalse(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(0, searchBlogsEntriesCount(group.getGroupId()));

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_APPROVED,
			getServiceContext(entry));

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_DRAFT,
			getServiceContext(entry));

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_IN_TRASH,
			getServiceContext(entry));

		BlogsEntryLocalServiceUtil.updateStatus(
			getUserId(), entry.getEntryId(), WorkflowConstants.STATUS_DRAFT,
			getServiceContext(entry));

		Assert.assertFalse(isAssetEntryVisible(entry.getEntryId()));
		Assert.assertEquals(0, searchBlogsEntriesCount(group.getGroupId()));
	}

	protected void checkSocialActivity(int activityType, int expectedCount)
		throws Exception {

		Thread.sleep(500 * TestPropsValues.JUNIT_DELAY_FACTOR);

		List<SocialActivity> socialActivities =
			SocialActivityLocalServiceUtil.getGroupActivities(
				group.getGroupId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		int count = 0;

		for (SocialActivity socialActivity : socialActivities) {
			if ((activityType == ACTIVITY_KEY_ANY) ||
				(activityType == socialActivity.getType())) {

				count = count + 1;
			}
		}

		Assert.assertEquals(expectedCount, count);
	}

	protected static final int ACTIVITY_KEY_ANY = -1;

	protected BlogsEntry entry;
	protected Group group;

}