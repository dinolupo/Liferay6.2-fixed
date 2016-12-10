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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portlet.PortletPreferencesImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.portlet.MockPortletRequest;

/**
 * @author Connor McKay
 * @author Peter Borkuti
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class LocalizationImplTest {

	@Before
	public void setUp() throws Exception {
		StringBundler sb = new StringBundler();

		sb.append("<?xml version='1.0' encoding='UTF-8'?>");

		sb.append("<root available-locales=\"en_US,es_ES\" ");
		sb.append("default-locale=\"en_US\">");
		sb.append("<static-content language-id=\"es_ES\">");
		sb.append("foo&amp;bar");
		sb.append("</static-content>");
		sb.append("<static-content language-id=\"en_US\">");
		sb.append("<![CDATA[Example in English]]>");
		sb.append("</static-content>");
		sb.append("</root>");

		_xml = sb.toString();
	}

	@Test
	public void testChunkedText() {
		String translation = LocalizationUtil.getLocalization(_xml, "es_ES");

		Assert.assertNotNull(translation);
		Assert.assertEquals("foo&bar", translation);
		Assert.assertEquals(7, translation.length());

		translation = LocalizationUtil.getLocalization(_xml, "en_US");

		Assert.assertNotNull(translation);
		Assert.assertEquals(18, translation.length());
	}

	@Test
	public void testGetAvailableLanguageIds() throws DocumentException {
		Document document = SAXReaderUtil.read(_xml);

		String[] documentAvailableLanguageIds =
			LocalizationUtil.getAvailableLanguageIds(document);

		Assert.assertEquals(documentAvailableLanguageIds.length, 2);

		String[] xmlAvailableLanguageIds =
			LocalizationUtil.getAvailableLanguageIds(_xml);

		Assert.assertEquals(xmlAvailableLanguageIds.length, 2);

		Arrays.sort(documentAvailableLanguageIds);
		Arrays.sort(xmlAvailableLanguageIds);

		Assert.assertTrue(
			"Available language IDs between the document and XML do not match",
			Arrays.equals(
				documentAvailableLanguageIds, xmlAvailableLanguageIds));
	}

	@Test
	public void testGetDefaultLanguageId() throws DocumentException {
		Document document = SAXReaderUtil.read(_xml);

		String languageIdsFromDoc = LocalizationUtil.getDefaultLanguageId(
			document);
		String languageIdsFromXml = LocalizationUtil.getDefaultLanguageId(_xml);

		Assert.assertEquals(
			"The default language ids from Document and XML don't match",
			languageIdsFromDoc, languageIdsFromXml);
	}

	@Test
	public void testLocalizationsXML() {
		String xml = StringPool.BLANK;

		xml = LocalizationUtil.updateLocalization(
			xml, "greeting", _englishHello, _englishLanguageId,
			_englishLanguageId);
		xml = LocalizationUtil.updateLocalization(
			xml, "greeting", _germanHello, _germanLanguageId,
			_englishLanguageId);

		Assert.assertEquals(
			_englishHello,
			LocalizationUtil.getLocalization(xml, _englishLanguageId));
		Assert.assertEquals(
			_germanHello,
			LocalizationUtil.getLocalization(xml, _germanLanguageId));
	}

	@Test
	public void testLongTranslationText() {
		StringBundler sb = new StringBundler();

		sb.append("<?xml version='1.0' encoding='UTF-8'?>");

		sb.append("<root available-locales=\"en_US,es_ES\" ");
		sb.append("default-locale=\"en_US\">");
		sb.append("<static-content language-id=\"es_ES\">");
		sb.append("<![CDATA[");

		int loops = 2000000;

		for (int i = 0; i < loops; i++) {
			sb.append("1234567890");
		}

		sb.append("]]>");
		sb.append("</static-content>");
		sb.append("<static-content language-id=\"en_US\">");
		sb.append("<![CDATA[Example in English]]>");
		sb.append("</static-content>");
		sb.append("</root>");

		int totalSize = loops * 10;

		Assert.assertTrue(sb.length() > totalSize);

		String translation = LocalizationUtil.getLocalization(
			sb.toString(), "es_ES");

		Assert.assertNotNull(translation);
		Assert.assertEquals(totalSize, translation.length());

		translation = LocalizationUtil.getLocalization(sb.toString(), "en_US");

		Assert.assertNotNull(translation);
		Assert.assertEquals(18, translation.length());
	}

	@Test
	public void testPreferencesLocalization() throws Exception {
		PortletPreferences preferences = new PortletPreferencesImpl();

		LocalizationUtil.setPreferencesValue(
			preferences, "greeting", _englishLanguageId, _englishHello);
		LocalizationUtil.setPreferencesValue(
			preferences, "greeting", _germanLanguageId, _germanHello);

		Assert.assertEquals(
			_englishHello,
			LocalizationUtil.getPreferencesValue(
				preferences, "greeting", _englishLanguageId));
		Assert.assertEquals(
			_germanHello,
			LocalizationUtil.getPreferencesValue(
				preferences, "greeting", _germanLanguageId));
	}

	@Test
	public void testSetLocalizedPreferencesValues() throws Exception {
		MockPortletRequest request = new MockPortletRequest();

		request.setParameter("greeting_" + _englishLanguageId, _englishHello);
		request.setParameter("greeting_" + _germanLanguageId, _germanHello);

		PortletPreferences preferences = new PortletPreferencesImpl();

		LocalizationUtil.setLocalizedPreferencesValues(
			request, preferences, "greeting");

		Assert.assertEquals(
			_englishHello,
			LocalizationUtil.getPreferencesValue(
				preferences, "greeting", _englishLanguageId));
		Assert.assertEquals(
			_germanHello,
			LocalizationUtil.getPreferencesValue(
				preferences, "greeting", _germanLanguageId));
	}

	@Test
	public void testUpdateLocalization() {
		Map<Locale, String>localizationMap = new HashMap<Locale, String>();

		localizationMap.put(LocaleUtil.US, _englishHello);

		StringBundler sb = new StringBundler();

		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root available-locales=\"en_US,de_DE\" ");
		sb.append("default-locale=\"en_US\">");
		sb.append("<greeting language-id=\"de_DE\">");
		sb.append("<![CDATA[Beispiel auf Deutsch]]>");
		sb.append("</greeting>");
		sb.append("<greeting language-id=\"en_US\">");
		sb.append("<![CDATA[Example in English]]>");
		sb.append("</greeting>");
		sb.append("</root>");

		String xml = LocalizationUtil.updateLocalization(
			localizationMap, sb.toString(), "greeting",
			LocaleUtil.toLanguageId(LocaleUtil.getDefault()));

		Assert.assertEquals(
			_englishHello,
			LocalizationUtil.getLocalization(xml, _englishLanguageId, false));
		Assert.assertEquals(
			StringPool.BLANK,
			LocalizationUtil.getLocalization(xml, _germanLanguageId, false));
	}

	private String _englishHello = "Hello World";
	private String _englishLanguageId = LocaleUtil.toLanguageId(LocaleUtil.US);
	private String _germanHello = "Hallo Welt";
	private String _germanLanguageId = LocaleUtil.toLanguageId(
		LocaleUtil.GERMANY);
	private String _xml;

}