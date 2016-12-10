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

package com.liferay.portlet.assetpublisher.util;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.util.JournalTestUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.portlet.MockPortletPreferences;
import org.springframework.mock.web.portlet.MockPortletRequest;

/**
 * @author Roberto Díaz
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class AssetPublisherServiceTest {

	@Before
	public void setUp() throws Exception {
		_assetEntries = addAssetEntries(
			_NO_ASSET_CATEGORY_IDS, _NO_ASSET_TAG_NAMES, 5, true);
		_permissionChecker = PermissionCheckerFactoryUtil.create(
			TestPropsValues.getUser());
	}

	@Test
	@Transactional
	public void testGetAssetEntries() throws Exception {
		List<AssetEntry> assetEntries = AssetPublisherUtil.getAssetEntries(
			new MockPortletRequest(), new MockPortletPreferences(),
			_permissionChecker, new long[] {TestPropsValues.getGroupId()},
			_assetEntryXmls, false, false);

		Assert.assertEquals(_assetEntries, assetEntries);
	}

	@Test
	@Transactional
	public void testGetAssetEntriesFilteredByAssetCategoryIds()
		throws Exception {

		addAssetVocabulary();

		long[] allAssetCategoryIds =
			{_assetCategoryIds[0], _assetCategoryIds[1], _assetCategoryIds[2]};

		List<AssetEntry> expectedAssetEntries = addAssetEntries(
			allAssetCategoryIds, _NO_ASSET_TAG_NAMES, 2, true);

		List<AssetEntry> assetEntries = AssetPublisherUtil.getAssetEntries(
			new MockPortletRequest(), new MockPortletPreferences(),
			_permissionChecker, new long[] {TestPropsValues.getGroupId()},
			_assetEntryXmls, false, false);

		Assert.assertEquals(
			_assetEntries.size() + expectedAssetEntries.size(),
			assetEntries.size());

		List<AssetEntry> filteredAsssetEntries =
			AssetPublisherUtil.getAssetEntries(
				new MockPortletRequest(), new MockPortletPreferences(),
				_permissionChecker, new long[] {TestPropsValues.getGroupId()},
				allAssetCategoryIds, _assetEntryXmls, _NO_ASSET_TAG_NAMES,
				false, false);

		Assert.assertEquals(expectedAssetEntries, filteredAsssetEntries);
	}

	@Test
	@Transactional
	public void testGetAssetEntriesFilteredByAssetCategoryIdsAndAssetTagNames()
		throws Exception {

		addAssetVocabulary();

		long[] allCategoyIds =
			{_assetCategoryIds[0], _assetCategoryIds[1], _assetCategoryIds[2],
				_assetCategoryIds[3]};

		String[] allAssetTagNames = {_ASSET_TAG_NAMES[0], _ASSET_TAG_NAMES[1]};

		List<AssetEntry> expectedAssetEntries = addAssetEntries(
			allCategoyIds, allAssetTagNames, 2, true);

		List<AssetEntry> assetEntries = AssetPublisherUtil.getAssetEntries(
			new MockPortletRequest(), new MockPortletPreferences(),
			_permissionChecker, new long[] {TestPropsValues.getGroupId()},
			_assetEntryXmls, false, false);

		Assert.assertEquals(
			_assetEntries.size() + expectedAssetEntries.size(),
			assetEntries.size());

		List<AssetEntry> filteredAssetEntries =
			AssetPublisherUtil.getAssetEntries(
				new MockPortletRequest(), new MockPortletPreferences(),
				_permissionChecker, new long[] {TestPropsValues.getGroupId()},
				allCategoyIds, _assetEntryXmls, allAssetTagNames, false, false);

		Assert.assertEquals(expectedAssetEntries, filteredAssetEntries);
	}

	@Test
	@Transactional
	public void testGetAssetEntriesFilteredByAssetTagNames() throws Exception {
		String[] allAssetTagNames = {_ASSET_TAG_NAMES[0], _ASSET_TAG_NAMES[1]};

		List<AssetEntry> expectedAssetEntries = addAssetEntries(
			_NO_ASSET_CATEGORY_IDS, allAssetTagNames, 2, true);

		List<AssetEntry> assetEntries = AssetPublisherUtil.getAssetEntries(
			new MockPortletRequest(), new MockPortletPreferences(),
			_permissionChecker, new long[] {TestPropsValues.getGroupId()},
			_assetEntryXmls, false, false);

		Assert.assertEquals(
			_assetEntries.size() + expectedAssetEntries.size(),
			assetEntries.size());

		List<AssetEntry> filteredAssetEntries =
			AssetPublisherUtil.getAssetEntries(
				new MockPortletRequest(), new MockPortletPreferences(),
				_permissionChecker, new long[] {TestPropsValues.getGroupId()},
				_NO_ASSET_CATEGORY_IDS, _assetEntryXmls, allAssetTagNames,
				false, false);

		Assert.assertEquals(expectedAssetEntries, filteredAssetEntries);
	}

	protected void addAssetCategories(long vocabularyId) throws Exception {
		for (String assetCategoryName : _ASSET_CATEGORY_NAMES) {
			AssetCategory assetCategory =
				AssetCategoryLocalServiceUtil.addCategory(
					TestPropsValues.getUserId(), assetCategoryName,
					vocabularyId, ServiceTestUtil.getServiceContext());

			_assetCategoryIds = ArrayUtil.append(
				_assetCategoryIds, assetCategory.getCategoryId());
		}
	}

	protected List<AssetEntry> addAssetEntries(
			long[] assetCategoryIds, String[] assetTagNames, int count,
			boolean manualMode)
		throws Exception {

		List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();

		for (int i = 0; i < count; i++) {
			JournalArticle article = JournalTestUtil.addArticle(
				TestPropsValues.getGroupId(), ServiceTestUtil.randomString(),
				ServiceTestUtil.randomString(100));

			JournalArticleLocalServiceUtil.updateAsset(
				TestPropsValues.getUserId(), article, assetCategoryIds,
				assetTagNames, null);

			AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
				JournalArticle.class.getName(), article.getResourcePrimKey());

			assetEntries.add(assetEntry);

			if (manualMode) {
				StringBuilder sb = new StringBuilder(6);

				sb.append("<?xml version=\"1.0\"?><asset-entry>");
				sb.append("<asset-entry-type>");
				sb.append(JournalArticle.class.getName());
				sb.append("</asset-entry-type><asset-entry-uuid>");
				sb.append(assetEntry.getClassUuid());
				sb.append("</asset-entry-uuid></asset-entry>");

				_assetEntryXmls = ArrayUtil.append(
					_assetEntryXmls, sb.toString());
			}
		}

		return assetEntries;
	}

	protected void addAssetVocabulary() throws Exception {
		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			TestPropsValues.getGroupId());

		serviceContext.setAddGroupPermissions(false);
		serviceContext.setAddGuestPermissions(false);

		AssetVocabulary assetVocabulary =
			AssetVocabularyLocalServiceUtil.addVocabulary(
				TestPropsValues.getUserId(), ServiceTestUtil.randomString(),
				ServiceTestUtil.getServiceContext(
					TestPropsValues.getGroupId()));

		addAssetCategories(assetVocabulary.getVocabularyId());
	}

	private static final String[] _ASSET_CATEGORY_NAMES =
		{"Athletic", "Barcelona", "RealMadrid", "Sevilla", "Sporting"};

	private static final String[] _ASSET_TAG_NAMES =
		{"basketball", "football", "tennis"};

	private static final long[] _NO_ASSET_CATEGORY_IDS = new long[0];

	private static final String[] _NO_ASSET_TAG_NAMES = new String[0];

	private long[] _assetCategoryIds = new long[0];
	private List<AssetEntry> _assetEntries = new ArrayList<AssetEntry>();
	private String[] _assetEntryXmls = new String[0];
	private PermissionChecker _permissionChecker;

}