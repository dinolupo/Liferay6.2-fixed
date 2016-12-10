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

package com.liferay.portlet.social;

import com.liferay.portal.events.ServicePreAction;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.model.SocialActivityInterpreter;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.trash.util.TrashUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Zsolt Berentey
 */
public abstract class BaseSocialActivityInterpreterTestCase
	extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		FinderCacheUtil.clearCache();

		group = GroupTestUtil.addGroup();

		HttpServletRequest request = new MockHttpServletRequest();

		request.setAttribute(
			WebKeys.COMPANY_ID, TestPropsValues.getCompanyId());
		request.setAttribute(
			WebKeys.CURRENT_URL, "http://localhost:80/web/guest/home");
		request.setAttribute(WebKeys.USER, TestPropsValues.getUser());

		ServicePreAction servicePreAction = new ServicePreAction();

		ThemeDisplay themeDisplay = servicePreAction.initThemeDisplay(
			request, new MockHttpServletResponse());

		request.setAttribute(WebKeys.THEME_DISPLAY, themeDisplay);

		serviceContext = ServiceContextFactory.getInstance(request);
	}

	@After
	public void tearDown() throws Exception {
		GroupLocalServiceUtil.deleteGroup(group);
	}

	@Test
	@Transactional
	public void testActivityInterpreter() throws Exception {
		addActivities();

		long time = System.currentTimeMillis();

		renameModels();

		if (isSupportsTrash()) {
			moveModelsToTrash();

			checkLinks();

			restoreModelsFromTrash();
		}

		checkInterpret(time);
	}

	protected abstract void addActivities() throws Exception;

	protected void checkInterpret(long time) throws Exception {
		List<SocialActivity> activities = getActivities();

		Assert.assertFalse(activities.isEmpty());

		Map<String, String> entryTitles = new HashMap<String, String>();

		SocialActivityInterpreter activityInterpreter =
			getActivityInterpreter();

		for (SocialActivity activity : activities) {
			String title = activity.getExtraDataValue(
				"title", serviceContext.getLocale());

			if (isSupportsRename(activity.getClassName()) &&
				Validator.isNotNull(title)) {

				if (activity.getCreateDate() < time) {
					entryTitles.put(activity.getClassName(), title);
				}
				else {
					Assert.assertNotNull(
						entryTitles.get(activity.getClassName()));
					Assert.assertNotEquals(
						entryTitles.get(activity.getClassName()), title);
				}
			}

			if (hasClassName(activityInterpreter, activity.getClassName()) &&
				hasActivityType(activity.getType())) {

				SocialActivityFeedEntry activityFeedEntry =
					activityInterpreter.interpret(activity, serviceContext);

				Assert.assertNotNull(activityFeedEntry);

				title = activityFeedEntry.getTitle();

				if (title.matches("\\{\\d\\}")) {
					Assert.fail("Title contains parameters: " + title);
				}
			}
		}
	}

	protected void checkLinks() throws Exception {
		List<SocialActivity> activities = getActivities();

		Assert.assertFalse(activities.isEmpty());

		SocialActivityInterpreter activityInterpreter =
			getActivityInterpreter();

		for (SocialActivity activity : activities) {
			if (hasClassName(activityInterpreter, activity.getClassName()) &&
				hasActivityType(activity.getType())) {

				SocialActivityFeedEntry activityFeedEntry =
					activityInterpreter.interpret(activity, serviceContext);

				PortletURL portletURL = TrashUtil.getViewContentURL(
					serviceContext.getRequest(), activity.getClassName(),
					activity.getClassPK());

				if (Validator.isNull(activityFeedEntry.getLink()) &&
					(portletURL == null)) {

					continue;
				}

				Assert.assertEquals(
					portletURL.toString(), activityFeedEntry.getLink());
			}
		}
	}

	protected List<SocialActivity> getActivities() throws Exception {
		List<SocialActivity> activities =
			new ArrayList<SocialActivity>(
				SocialActivityLocalServiceUtil.getGroupActivities(
					group.getGroupId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		Collections.reverse(activities);

		return activities;
	}

	protected abstract SocialActivityInterpreter getActivityInterpreter();

	protected abstract int[] getActivityTypes();

	protected boolean hasActivityType(int activityType) {
		for (int curActivityType : getActivityTypes()) {
			if (curActivityType == activityType) {
				return true;
			}
		}

		return false;
	}

	protected boolean hasClassName(
		SocialActivityInterpreter activityInterpreter, String className) {

		for (String curClassName : activityInterpreter.getClassNames()) {
			if (curClassName.equals(className)) {
				return true;
			}
		}

		return false;
	}

	protected boolean isSupportsRename(String className) {
		return true;
	}

	protected boolean isSupportsTrash() {
		return true;
	}

	protected abstract void moveModelsToTrash() throws Exception;

	protected abstract void renameModels() throws Exception;

	protected abstract void restoreModelsFromTrash() throws Exception;

	protected Group group;
	protected ServiceContext serviceContext;

}