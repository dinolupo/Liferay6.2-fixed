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

package com.liferay.portal.language;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheMapSynchronizeUtil;
import com.liferay.portal.kernel.cache.PortalCacheMapSynchronizeUtil.Synchronizer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageWrapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletConfigFactoryUtil;

import java.io.Serializable;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 * @author Andrius Vitkauskas
 * @author Eduardo Lundgren
 */
@DoPrivileged
public class LanguageImpl implements Language, Serializable {

	@Override
	public String format(
		Locale locale, String pattern, List<Object> arguments) {

		return format(locale, pattern, arguments.toArray(), true);
	}

	@Override
	public String format(Locale locale, String pattern, Object argument) {
		return format(locale, pattern, new Object[] {argument}, true);
	}

	@Override
	public String format(
		Locale locale, String pattern, Object argument,
		boolean translateArguments) {

		return format(
			locale, pattern, new Object[] {argument}, translateArguments);
	}

	@Override
	public String format(Locale locale, String pattern, Object[] arguments) {
		return format(locale, pattern, arguments, true);
	}

	@Override
	public String format(
		Locale locale, String pattern, Object[] arguments,
		boolean translateArguments) {

		if (PropsValues.TRANSLATIONS_DISABLED) {
			return pattern;
		}

		String value = null;

		try {
			pattern = get(locale, pattern);

			if (ArrayUtil.isNotEmpty(arguments)) {
				pattern = _escapePattern(pattern);

				Object[] formattedArguments = new Object[arguments.length];

				for (int i = 0; i < arguments.length; i++) {
					if (translateArguments) {
						formattedArguments[i] = get(
							locale, arguments[i].toString());
					}
					else {
						formattedArguments[i] = arguments[i];
					}
				}

				value = MessageFormat.format(pattern, formattedArguments);
			}
			else {
				value = pattern;
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return value;
	}

	@Override
	public String format(
		PageContext pageContext, String pattern, LanguageWrapper argument) {

		return format(
			pageContext, pattern, new LanguageWrapper[] {argument}, true);
	}

	@Override
	public String format(
		PageContext pageContext, String pattern, LanguageWrapper argument,
		boolean translateArguments) {

		return format(
			pageContext, pattern, new LanguageWrapper[] {argument},
			translateArguments);
	}

	@Override
	public String format(
		PageContext pageContext, String pattern, LanguageWrapper[] arguments) {

		return format(pageContext, pattern, arguments, true);
	}

	@Override
	public String format(
		PageContext pageContext, String pattern, LanguageWrapper[] arguments,
		boolean translateArguments) {

		if (PropsValues.TRANSLATIONS_DISABLED) {
			return pattern;
		}

		String value = null;

		try {
			pattern = get(pageContext, pattern);

			if (ArrayUtil.isNotEmpty(arguments)) {
				pattern = _escapePattern(pattern);

				Object[] formattedArguments = new Object[arguments.length];

				for (int i = 0; i < arguments.length; i++) {
					if (translateArguments) {
						formattedArguments[i] =
							arguments[i].getBefore() +
							get(pageContext, arguments[i].getText()) +
							arguments[i].getAfter();
					}
					else {
						formattedArguments[i] =
							arguments[i].getBefore() +
							arguments[i].getText() +
							arguments[i].getAfter();
					}
				}

				value = MessageFormat.format(pattern, formattedArguments);
			}
			else {
				value = pattern;
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return value;
	}

	@Override
	public String format(
		PageContext pageContext, String pattern, Object argument) {

		return format(pageContext, pattern, new Object[] {argument}, true);
	}

	@Override
	public String format(
		PageContext pageContext, String pattern, Object argument,
		boolean translateArguments) {

		return format(
			pageContext, pattern, new Object[] {argument}, translateArguments);
	}

	@Override
	public String format(
		PageContext pageContext, String pattern, Object[] arguments) {

		return format(pageContext, pattern, arguments, true);
	}

	@Override
	public String format(
		PageContext pageContext, String pattern, Object[] arguments,
		boolean translateArguments) {

		if (PropsValues.TRANSLATIONS_DISABLED) {
			return pattern;
		}

		String value = null;

		try {
			pattern = get(pageContext, pattern);

			if (ArrayUtil.isNotEmpty(arguments)) {
				pattern = _escapePattern(pattern);

				Object[] formattedArguments = new Object[arguments.length];

				for (int i = 0; i < arguments.length; i++) {
					if (translateArguments) {
						formattedArguments[i] = get(
							pageContext, arguments[i].toString());
					}
					else {
						formattedArguments[i] = arguments[i];
					}
				}

				value = MessageFormat.format(pattern, formattedArguments);
			}
			else {
				value = pattern;
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return value;
	}

	@Override
	public String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object argument) {

		return format(
			portletConfig, locale, pattern, new Object[] {argument}, true);
	}

	@Override
	public String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object argument, boolean translateArguments) {

		return format(
			portletConfig, locale, pattern, new Object[] {argument},
			translateArguments);
	}

	@Override
	public String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object[] arguments) {

		return format(portletConfig, locale, pattern, arguments, true);
	}

	@Override
	public String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object[] arguments, boolean translateArguments) {

		if (PropsValues.TRANSLATIONS_DISABLED) {
			return pattern;
		}

		String value = null;

		try {
			pattern = get(portletConfig, locale, pattern);

			if (ArrayUtil.isNotEmpty(arguments)) {
				pattern = _escapePattern(pattern);

				Object[] formattedArguments = new Object[arguments.length];

				for (int i = 0; i < arguments.length; i++) {
					if (translateArguments) {
						formattedArguments[i] = get(
							locale, arguments[i].toString());
					}
					else {
						formattedArguments[i] = arguments[i];
					}
				}

				value = MessageFormat.format(pattern, formattedArguments);
			}
			else {
				value = pattern;
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return value;
	}

	@Override
	public String get(Locale locale, String key) {
		return get(locale, key, key);
	}

	@Override
	public String get(Locale locale, String key, String defaultValue) {
		if (PropsValues.TRANSLATIONS_DISABLED) {
			return key;
		}

		if (key == null) {
			return null;
		}

		String value = LanguageResources.getMessage(locale, key);

		while ((value == null) || value.equals(defaultValue)) {
			if ((key.length() > 0) &&
				(key.charAt(key.length() - 1) == CharPool.CLOSE_BRACKET)) {

				int pos = key.lastIndexOf(CharPool.OPEN_BRACKET);

				if (pos != -1) {
					key = key.substring(0, pos);

					value = LanguageResources.getMessage(locale, key);

					continue;
				}
			}

			break;
		}

		if (value == null) {
			value = defaultValue;
		}

		return value;
	}

	@Override
	public String get(PageContext pageContext, String key) {
		return get(pageContext, key, key);
	}

	@Override
	public String get(
		PageContext pageContext, String key, String defaultValue) {

		try {
			return _get(pageContext, null, null, key, defaultValue);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			return defaultValue;
		}
	}

	@Override
	public String get(PortletConfig portletConfig, Locale locale, String key) {
		return get(portletConfig, locale, key, key);
	}

	@Override
	public String get(
		PortletConfig portletConfig, Locale locale, String key,
		String defaultValue) {

		try {
			return _get(null, portletConfig, locale, key, defaultValue);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			return defaultValue;
		}
	}

	@Override
	public Locale[] getAvailableLocales() {
		return _getInstance()._locales;
	}

	@Override
	public Locale[] getAvailableLocales(long groupId) {
		if (groupId <= 0) {
			return getAvailableLocales();
		}

		try {
			if (isInheritLocales(groupId)) {
				return getAvailableLocales();
			}
		}
		catch (Exception e) {
		}

		Locale[] locales = _groupLocalesMap.get(groupId);

		if (locales != null) {
			return locales;
		}

		_initGroupLocales(groupId);

		return _groupLocalesMap.get(groupId);
	}

	@Override
	public String getBCP47LanguageId(HttpServletRequest request) {
		Locale locale = PortalUtil.getLocale(request);

		return getBCP47LanguageId(locale);
	}

	@Override
	public String getBCP47LanguageId(Locale locale) {
		return LocaleUtil.toBCP47LanguageId(locale);
	}

	@Override
	public String getBCP47LanguageId(PortletRequest portletRequest) {
		Locale locale = PortalUtil.getLocale(portletRequest);

		return getBCP47LanguageId(locale);
	}

	@Override
	public String getCharset(Locale locale) {
		return _getInstance()._getCharset(locale);
	}

	@Override
	public String getLanguageId(HttpServletRequest request) {
		String languageId = ParamUtil.getString(request, "languageId");

		if (Validator.isNotNull(languageId)) {
			if (_localesMap.containsKey(languageId) ||
				_charEncodings.containsKey(languageId)) {

				return languageId;
			}
		}

		Locale locale = PortalUtil.getLocale(request);

		return getLanguageId(locale);
	}

	@Override
	public String getLanguageId(Locale locale) {
		return LocaleUtil.toLanguageId(locale);
	}

	@Override
	public String getLanguageId(PortletRequest portletRequest) {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		return getLanguageId(request);
	}

	@Override
	public Locale getLocale(long groupId, String languageCode) {
		return _getInstance()._getLocale(groupId, languageCode);
	}

	@Override
	public Locale getLocale(String languageCode) {
		return _getInstance()._getLocale(languageCode);
	}

	@Override
	public Locale[] getSupportedLocales() {
		List<Locale> supportedLocales = new ArrayList<Locale>();

		Locale[] locales = getAvailableLocales();

		for (Locale locale : locales) {
			if (!isBetaLocale(locale)) {
				supportedLocales.add(locale);
			}
		}

		return supportedLocales.toArray(new Locale[supportedLocales.size()]);
	}

	@Override
	public String getTimeDescription(Locale locale, long milliseconds) {
		return getTimeDescription(locale, milliseconds, false);
	}

	@Override
	public String getTimeDescription(
		Locale locale, long milliseconds, boolean approximate) {

		String description = Time.getDescription(milliseconds, approximate);

		String value = null;

		try {
			int pos = description.indexOf(CharPool.SPACE);

			String x = description.substring(0, pos);

			value = x.concat(StringPool.SPACE).concat(
				get(
					locale,
					StringUtil.toLowerCase(
						description.substring(pos + 1, description.length()))));
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return value;
	}

	@Override
	public String getTimeDescription(Locale locale, Long milliseconds) {
		return getTimeDescription(locale, milliseconds.longValue());
	}

	@Override
	public String getTimeDescription(
		PageContext pageContext, long milliseconds) {

		return getTimeDescription(pageContext, milliseconds, false);
	}

	@Override
	public String getTimeDescription(
		PageContext pageContext, long milliseconds, boolean approximate) {

		String description = Time.getDescription(milliseconds, approximate);

		String value = null;

		try {
			int pos = description.indexOf(CharPool.SPACE);

			String x = description.substring(0, pos);

			value = x.concat(StringPool.SPACE).concat(
				get(
					pageContext,
					StringUtil.toLowerCase(
						description.substring(pos + 1, description.length()))));
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return value;
	}

	@Override
	public String getTimeDescription(
		PageContext pageContext, Long milliseconds) {

		return getTimeDescription(pageContext, milliseconds.longValue());
	}

	@Override
	public void init() {
		_instances.clear();
	}

	@Override
	public boolean isAvailableLanguageCode(String languageCode) {
		return _getInstance()._localesMap.containsKey(languageCode);
	}

	@Override
	public boolean isAvailableLocale(Locale locale) {
		return _getInstance()._localesSet.contains(locale);
	}

	@Override
	public boolean isAvailableLocale(long groupId, Locale locale) {
		if (groupId <= 0) {
			return isAvailableLocale(locale);
		}

		try {
			if (isInheritLocales(groupId)) {
				return isAvailableLocale(locale);
			}
		}
		catch (Exception e) {
		}

		Set<Locale> localesSet = _groupLocalesSet.get(groupId);

		if (localesSet != null) {
			return localesSet.contains(locale);
		}

		_initGroupLocales(groupId);

		localesSet = _groupLocalesSet.get(groupId);

		return localesSet.contains(locale);
	}

	@Override
	public boolean isAvailableLocale(long groupId, String languageId) {
		Locale[] locales = getAvailableLocales(groupId);

		for (Locale locale : locales) {
			if (languageId.equals(locale.toString())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isAvailableLocale(String languageId) {
		Locale[] locales = getAvailableLocales();

		for (Locale locale : locales) {
			if (languageId.equals(locale.toString())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isBetaLocale(Locale locale) {
		return _getInstance()._localesBetaSet.contains(locale);
	}

	@Override
	public boolean isDuplicateLanguageCode(String languageCode) {
		return _getInstance()._duplicateLanguageCodes.contains(languageCode);
	}

	@Override
	public boolean isInheritLocales(long groupId)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.isStagingGroup()) {
			group = group.getLiveGroup();
		}

		if (!group.isSite() || group.isCompany()) {
			return true;
		}

		return GetterUtil.getBoolean(
			group.getTypeSettingsProperty(
				GroupConstants.TYPE_SETTINGS_KEY_INHERIT_LOCALES),
			true);
	}

	@Override
	public void resetAvailableGroupLocales(long groupId) {
		_resetAvailableGroupLocales(groupId);
	}

	@Override
	public void resetAvailableLocales(long companyId) {
		_resetAvailableLocales(companyId);
	}

	@Override
	public void updateCookie(
		HttpServletRequest request, HttpServletResponse response,
		Locale locale) {

		String languageId = LocaleUtil.toLanguageId(locale);

		Cookie languageIdCookie = new Cookie(
			CookieKeys.GUEST_LANGUAGE_ID, languageId);

		languageIdCookie.setPath(StringPool.SLASH);
		languageIdCookie.setMaxAge(CookieKeys.MAX_AGE);

		CookieKeys.addCookie(request, response, languageIdCookie);
	}

	private static LanguageImpl _getInstance() {
		Long companyId = CompanyThreadLocal.getCompanyId();

		LanguageImpl instance = _instances.get(companyId);

		if (instance == null) {
			instance = new LanguageImpl(companyId);

			_instances.put(companyId, instance);
		}

		return instance;
	}

	private LanguageImpl() {
		this(CompanyConstants.SYSTEM);
	}

	private LanguageImpl(long companyId) {
		String[] languageIds = PropsValues.LOCALES;

		if (companyId != CompanyConstants.SYSTEM) {
			try {
				languageIds = PrefsPropsUtil.getStringArray(
					companyId, PropsKeys.LOCALES, StringPool.COMMA,
					PropsValues.LOCALES_ENABLED);
			}
			catch (SystemException se) {
				languageIds = PropsValues.LOCALES_ENABLED;
			}
		}

		_charEncodings = new HashMap<String, String>();
		_duplicateLanguageCodes = new HashSet<String>();
		_locales = new Locale[languageIds.length];
		_localesMap = new HashMap<String, Locale>(languageIds.length);
		_localesSet = new HashSet<Locale>(languageIds.length);

		for (int i = 0; i < languageIds.length; i++) {
			String languageId = languageIds[i];

			Locale locale = LocaleUtil.fromLanguageId(languageId, false);

			_charEncodings.put(locale.toString(), StringPool.UTF8);

			String language = languageId;

			int pos = languageId.indexOf(CharPool.UNDERLINE);

			if (pos > 0) {
				language = languageId.substring(0, pos);
			}

			if (_localesMap.containsKey(language)) {
				_duplicateLanguageCodes.add(language);
			}

			_locales[i] = locale;

			if (!_localesMap.containsKey(language)) {
				_localesMap.put(language, locale);
			}

			_localesSet.add(locale);
		}

		String[] localesBetaArray = PropsValues.LOCALES_BETA;

		_localesBetaSet = new HashSet<Locale>(localesBetaArray.length);

		for (String languageId : localesBetaArray) {
			Locale locale = LocaleUtil.fromLanguageId(languageId, false);

			_localesBetaSet.add(locale);
		}
	}

	private String _escapePattern(String pattern) {
		return StringUtil.replace(
			pattern, StringPool.APOSTROPHE, StringPool.DOUBLE_APOSTROPHE);
	}

	private String _get(
			PageContext pageContext, PortletConfig portletConfig, Locale locale,
			String key, String defaultValue)
		throws Exception {

		if (PropsValues.TRANSLATIONS_DISABLED) {
			return key;
		}

		if (key == null) {
			return null;
		}

		String value = null;

		if (pageContext != null) {
			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			if (themeDisplay != null) {
				locale = themeDisplay.getLocale();
			}
			else {
				locale = request.getLocale();

				if (!isAvailableLocale(locale)) {
					locale = LocaleUtil.getDefault();
				}
			}

			portletConfig = (PortletConfig)request.getAttribute(
				JavaConstants.JAVAX_PORTLET_CONFIG);
		}

		if (portletConfig != null) {
			ResourceBundle resourceBundle = portletConfig.getResourceBundle(
				locale);

			value = ResourceBundleUtil.getString(resourceBundle, key);

			// LEP-7393

			String portletName = portletConfig.getPortletName();

			if (((value == null) || value.equals(defaultValue)) &&
				portletName.equals(PortletKeys.PORTLET_CONFIGURATION)) {

				value = _getPortletConfigurationValue(pageContext, locale, key);
			}

			if (value != null) {
				value = LanguageResources.fixValue(value);
			}
		}

		if ((value == null) || value.equals(defaultValue)) {
			value = LanguageResources.getMessage(locale, key);
		}

		if ((value == null) || value.equals(defaultValue)) {
			if ((key.length() > 0) &&
				(key.charAt(key.length() - 1) == CharPool.CLOSE_BRACKET)) {

				int pos = key.lastIndexOf(CharPool.OPEN_BRACKET);

				if (pos != -1) {
					key = key.substring(0, pos);

					return _get(
						pageContext, portletConfig, locale, key, defaultValue);
				}
			}
		}

		if ((value == null) || value.equals(key)) {
			value = defaultValue;
		}

		return value;
	}

	private String _getCharset(Locale locale) {
		return StringPool.UTF8;
	}

	private Locale _getLocale(long groupId, String languageCode) {
		Map<String, Locale> localesMap = _groupLanguageCodeLocalesMap.get(
			groupId);

		if (localesMap == null) {
			_initGroupLocales(groupId);

			localesMap = _groupLanguageCodeLocalesMap.get(groupId);
		}

		return localesMap.get(languageCode);
	}

	private Locale _getLocale(String languageCode) {
		return _localesMap.get(languageCode);
	}

	private String _getPortletConfigurationValue(
			PageContext pageContext, Locale locale, String key)
		throws Exception {

		if (PropsValues.TRANSLATIONS_DISABLED) {
			return key;
		}

		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		String portletResource = ParamUtil.getString(
			request, "portletResource");

		long companyId = PortalUtil.getCompanyId(request);

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			companyId, portletResource);

		PortletConfig portletConfig = PortletConfigFactoryUtil.create(
			portlet, pageContext.getServletContext());

		ResourceBundle resourceBundle = portletConfig.getResourceBundle(locale);

		return ResourceBundleUtil.getString(resourceBundle, key);
	}

	private void _initGroupLocales(long groupId) {
		String[] languageIds = null;

		try {
			Group group = GroupLocalServiceUtil.getGroup(groupId);

			UnicodeProperties typeSettingsProperties =
				group.getTypeSettingsProperties();

			languageIds = StringUtil.split(
				typeSettingsProperties.getProperty(PropsKeys.LOCALES));
		}
		catch (Exception e) {
			languageIds = PropsValues.LOCALES_ENABLED;
		}

		Locale[] locales = new Locale[languageIds.length];
		Map<String, Locale> localesMap = new HashMap<String, Locale>(
			languageIds.length);
		Set<Locale> localesSet = new HashSet<Locale>(languageIds.length);

		for (int i = 0; i < languageIds.length; i++) {
			String languageId = languageIds[i];

			Locale locale = LocaleUtil.fromLanguageId(languageId, false);

			String language = languageId;

			int pos = languageId.indexOf(CharPool.UNDERLINE);

			if (pos > 0) {
				language = languageId.substring(0, pos);
			}

			locales[i] = locale;

			if (!localesMap.containsKey(language)) {
				localesMap.put(language, locale);
			}

			localesSet.add(locale);
		}

		_groupLanguageCodeLocalesMap.put(groupId, localesMap);
		_groupLocalesMap.put(groupId, locales);
		_groupLocalesSet.put(groupId, localesSet);
	}

	private void _resetAvailableGroupLocales(long groupId) {
		_groupLanguageCodeLocalesMap.remove(groupId);
		_groupLocalesMap.remove(groupId);
		_groupLocalesSet.remove(groupId);
	}

	private void _resetAvailableLocales(long companyId) {
		_portalCache.remove(companyId);
	}

	private static Log _log = LogFactoryUtil.getLog(LanguageImpl.class);

	private static Map<Long, LanguageImpl> _instances =
		new ConcurrentHashMap<Long, LanguageImpl>();
	private static PortalCache<Long, Serializable> _portalCache =
		MultiVMPoolUtil.getCache(LanguageImpl.class.getName());

	static {
		PortalCacheMapSynchronizeUtil.<Long, Serializable>synchronize(
			_portalCache, _instances,
			new Synchronizer<Long, Serializable>() {

				@Override
				public void onSynchronize(
					Map<? extends Long, ? extends Serializable> map, Long key,
					Serializable value) {

					_instances.remove(key);
				}

			});
	}

	private Map<String, String> _charEncodings;
	private Set<String> _duplicateLanguageCodes;
	private final Map<Long, Map<String, Locale>> _groupLanguageCodeLocalesMap =
		new HashMap<Long, Map<String, Locale>>();
	private Map<Long, Locale[]> _groupLocalesMap =
		new HashMap<Long, Locale[]>();
	private Map<Long, Set<Locale>> _groupLocalesSet =
		new HashMap<Long, Set<Locale>>();
	private Locale[] _locales;
	private Set<Locale> _localesBetaSet;
	private Map<String, Locale> _localesMap;
	private Set<Locale> _localesSet;

}