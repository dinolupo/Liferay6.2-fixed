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

package com.liferay.portal.security.ldap;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.log.LogUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Binding;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.OperationNotSupportedException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.PagedResultsControl;
import javax.naming.ldap.PagedResultsResponseControl;

/**
 * @author Michael Young
 * @author Brian Wing Shun Chan
 * @author Jerry Niu
 * @author Scott Lee
 * @author Hervé Ménage
 * @author Samuel Kong
 * @author Ryan Park
 * @author Wesley Gong
 * @author Marcellus Tavares
 * @author Hugo Huijser
 */
public class PortalLDAPUtil {

	public static LdapContext getContext(long ldapServerId, long companyId)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		String baseProviderURL = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_BASE_PROVIDER_URL + postfix);
		String pricipal = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_SECURITY_PRINCIPAL + postfix);
		String credentials = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_SECURITY_CREDENTIALS + postfix);

		return getContext(companyId, baseProviderURL, pricipal, credentials);
	}

	public static LdapContext getContext(
			long companyId, String providerURL, String principal,
			String credentials)
		throws Exception {

		Properties environmentProperties = new Properties();

		environmentProperties.put(
			Context.INITIAL_CONTEXT_FACTORY,
			PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_FACTORY_INITIAL));
		environmentProperties.put(Context.PROVIDER_URL, providerURL);
		environmentProperties.put(Context.SECURITY_PRINCIPAL, principal);
		environmentProperties.put(Context.SECURITY_CREDENTIALS, credentials);
		environmentProperties.put(
			Context.REFERRAL,
			PrefsPropsUtil.getString(companyId, PropsKeys.LDAP_REFERRAL));

		Properties ldapConnectionProperties = PropsUtil.getProperties(
			PropsKeys.LDAP_CONNECTION_PROPERTY_PREFIX, true);

		PropertiesUtil.merge(environmentProperties, ldapConnectionProperties);

		LogUtil.debug(_log, environmentProperties);

		LdapContext ldapContext = null;

		try {
			ldapContext = new InitialLdapContext(environmentProperties, null);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Failed to bind to the LDAP server");
			}

			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		return ldapContext;
	}

	public static Binding getGroup(
			long ldapServerId, long companyId, String groupName)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		LdapContext ldapContext = getContext(ldapServerId, companyId);

		NamingEnumeration<SearchResult> enu = null;

		try {
			if (ldapContext == null) {
				return null;
			}

			String baseDN = PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_BASE_DN + postfix);

			String groupFilter = PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER + postfix);

			StringBundler sb = new StringBundler(
				Validator.isNotNull(groupFilter) ? 11 : 5);

			if (Validator.isNotNull(groupFilter)) {
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(StringPool.AMPERSAND);
			}

			sb.append(StringPool.OPEN_PARENTHESIS);

			Properties groupMappings = LDAPSettingsUtil.getGroupMappings(
				ldapServerId, companyId);

			sb.append(groupMappings.getProperty("groupName"));

			sb.append(StringPool.EQUAL);
			sb.append(groupName);
			sb.append(StringPool.CLOSE_PARENTHESIS);

			if (Validator.isNotNull(groupFilter)) {
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(groupFilter);
				sb.append(StringPool.CLOSE_PARENTHESIS);
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}

			SearchControls searchControls = new SearchControls(
				SearchControls.SUBTREE_SCOPE, 1, 0, null, false, false);

			enu = ldapContext.search(baseDN, sb.toString(), searchControls);

			if (enu.hasMoreElements()) {
				return enu.nextElement();
			}

			return null;
		}
		finally {
			if (enu != null) {
				enu.close();
			}

			if (ldapContext != null) {
				ldapContext.close();
			}
		}
	}

	public static Attributes getGroupAttributes(
			long ldapServerId, long companyId, LdapContext ldapContext,
			String fullDistinguishedName)
		throws Exception {

		return getGroupAttributes(
			ldapServerId, companyId, ldapContext, fullDistinguishedName, false);
	}

	public static Attributes getGroupAttributes(
			long ldapServerId, long companyId, LdapContext ldapContext,
			String fullDistinguishedName, boolean includeReferenceAttributes)
		throws Exception {

		Properties groupMappings = LDAPSettingsUtil.getGroupMappings(
			ldapServerId, companyId);

		List<String> mappedGroupAttributeIds = new ArrayList<String>();

		mappedGroupAttributeIds.add(groupMappings.getProperty("groupName"));
		mappedGroupAttributeIds.add(groupMappings.getProperty("description"));

		if (includeReferenceAttributes) {
			mappedGroupAttributeIds.add(groupMappings.getProperty("user"));
		}

		Attributes attributes = _getAttributes(
			ldapContext, fullDistinguishedName,
			mappedGroupAttributeIds.toArray(
				new String[mappedGroupAttributeIds.size()]));

		if (_log.isDebugEnabled()) {
			for (String attributeId : mappedGroupAttributeIds) {
				Attribute attribute = attributes.get(attributeId);

				if (attribute == null) {
					continue;
				}

				_log.debug("LDAP group attribute " + attribute.toString());
			}
		}

		return attributes;
	}

	public static byte[] getGroups(
			long companyId, LdapContext ldapContext, byte[] cookie,
			int maxResults, String baseDN, String groupFilter,
			List<SearchResult> searchResults)
		throws Exception {

		return searchLDAP(
			companyId, ldapContext, cookie, maxResults, baseDN, groupFilter,
			null, searchResults);
	}

	public static byte[] getGroups(
			long companyId, LdapContext ldapContext, byte[] cookie,
			int maxResults, String baseDN, String groupFilter,
			String[] attributeIds, List<SearchResult> searchResults)
		throws Exception {

		return searchLDAP(
			companyId, ldapContext, cookie, maxResults, baseDN, groupFilter,
			attributeIds, searchResults);
	}

	public static byte[] getGroups(
			long ldapServerId, long companyId, LdapContext ldapContext,
			byte[] cookie, int maxResults, List<SearchResult> searchResults)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		String baseDN = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_BASE_DN + postfix);
		String groupFilter = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER + postfix);

		return getGroups(
			companyId, ldapContext, cookie, maxResults, baseDN, groupFilter,
			searchResults);
	}

	public static byte[] getGroups(
			long ldapServerId, long companyId, LdapContext ldapContext,
			byte[] cookie, int maxResults, String[] attributeIds,
			List<SearchResult> searchResults)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		String baseDN = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_BASE_DN + postfix);
		String groupFilter = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER + postfix);

		return getGroups(
			companyId, ldapContext, cookie, maxResults, baseDN, groupFilter,
			attributeIds, searchResults);
	}

	public static String getGroupsDN(long ldapServerId, long companyId)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		return PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_GROUPS_DN + postfix);
	}

	public static long getLdapServerId(
			long companyId, String screenName, String emailAddress)
		throws Exception {

		long preferredLDAPServerId = LDAPSettingsUtil.getPreferredLDAPServerId(
			companyId, screenName);

		if ((preferredLDAPServerId >= 0) &&
			hasUser(
				preferredLDAPServerId, companyId, screenName, emailAddress)) {

			return preferredLDAPServerId;
		}

		long[] ldapServerIds = StringUtil.split(
			PrefsPropsUtil.getString(companyId, "ldap.server.ids"), 0L);

		for (long ldapServerId : ldapServerIds) {
			if (hasUser(ldapServerId, companyId, screenName, emailAddress)) {
				return ldapServerId;
			}
		}

		boolean hasProperties = false;

		for (int ldapServerId = 0;; ldapServerId++) {
			String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

			String providerUrl = PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_BASE_PROVIDER_URL + postfix);

			if (Validator.isNull(providerUrl)) {
				break;
			}

			hasProperties = true;

			if (hasUser(ldapServerId, companyId, screenName, emailAddress)) {
				return ldapServerId;
			}
		}

		if (hasProperties || (ldapServerIds.length <= 0)) {
			return 0;
		}

		return ldapServerIds[0];
	}

	public static Attribute getMultivaluedAttribute(
			long companyId, LdapContext ldapContext, String baseDN,
			String filter, Attribute attribute)
		throws Exception {

		if (attribute.size() > 0) {
			return attribute;
		}

		String[] attributeIds = {_getNextRange(attribute.getID())};

		while (true) {
			List<SearchResult> searchResults = new ArrayList<SearchResult>();

			searchLDAP(
				companyId, ldapContext, new byte[0], 0, baseDN, filter,
				attributeIds, searchResults);

			if (searchResults.size() != 1) {
				break;
			}

			SearchResult searchResult = searchResults.get(0);

			Attributes attributes = searchResult.getAttributes();

			if (attributes.size() != 1) {
				break;
			}

			NamingEnumeration<? extends Attribute> enu = null;

			try {
				enu = attributes.getAll();

				if (!enu.hasMoreElements()) {
					break;
				}

				Attribute curAttribute = enu.nextElement();

				for (int i = 0; i < curAttribute.size(); i++) {
					attribute.add(curAttribute.get(i));
				}

				if (StringUtil.endsWith(
						curAttribute.getID(), StringPool.STAR) ||
					(curAttribute.size() < PropsValues.LDAP_RANGE_SIZE)) {

					break;
				}
			}
			finally {
				if (enu != null) {
					enu.close();
				}
			}

			attributeIds[0] = _getNextRange(attributeIds[0]);
		}

		return attribute;
	}

	public static String getNameInNamespace(
			long ldapServerId, long companyId, Binding binding)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		String baseDN = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_BASE_DN + postfix);

		String name = binding.getName();

		if (name.startsWith(StringPool.QUOTE) &&
			name.endsWith(StringPool.QUOTE)) {

			name = name.substring(1, name.length() - 1);
		}

		if (Validator.isNull(baseDN)) {
			return name;
		}
		else {
			return name.concat(StringPool.COMMA).concat(baseDN);
		}
	}

	public static Binding getUser(
			long ldapServerId, long companyId, String screenName,
			String emailAddress)
		throws Exception {

		return getUser(
			ldapServerId, companyId, screenName, emailAddress, false);
	}

	public static Binding getUser(
			long ldapServerId, long companyId, String screenName,
			String emailAddress, boolean checkOriginalEmail)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		LdapContext ldapContext = getContext(ldapServerId, companyId);

		NamingEnumeration<SearchResult> enu = null;

		try {
			if (ldapContext == null) {
				return null;
			}

			String baseDN = PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_BASE_DN + postfix);

			String userFilter = PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_IMPORT_USER_SEARCH_FILTER + postfix);

			StringBundler sb = new StringBundler(
				Validator.isNotNull(userFilter) ? 11 : 5);

			if (Validator.isNotNull(userFilter)) {
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(StringPool.AMPERSAND);
			}

			sb.append(StringPool.OPEN_PARENTHESIS);

			String loginMapping = null;
			String login = null;

			Properties userMappings = LDAPSettingsUtil.getUserMappings(
				ldapServerId, companyId);

			String authType = PrefsPropsUtil.getString(
				companyId, PropsKeys.COMPANY_SECURITY_AUTH_TYPE,
				PropsValues.COMPANY_SECURITY_AUTH_TYPE);

			if (authType.equals(CompanyConstants.AUTH_TYPE_SN) &&
				!PrefsPropsUtil.getBoolean(
					companyId,
					PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE)) {

				loginMapping = userMappings.getProperty("screenName");
				login = screenName;
			}
			else {
				loginMapping = userMappings.getProperty("emailAddress");
				login = emailAddress;
			}

			sb.append(loginMapping);
			sb.append(StringPool.EQUAL);
			sb.append(login);

			sb.append(StringPool.CLOSE_PARENTHESIS);

			if (Validator.isNotNull(userFilter)) {
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(userFilter);
				sb.append(StringPool.CLOSE_PARENTHESIS);
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}

			SearchControls searchControls = new SearchControls(
				SearchControls.SUBTREE_SCOPE, 1, 0, null, false, false);

			enu = ldapContext.search(baseDN, sb.toString(), searchControls);

			if (enu.hasMoreElements()) {
				return enu.nextElement();
			}

			if (checkOriginalEmail) {
				String originalEmailAddress =
					LDAPUserTransactionThreadLocal.getOriginalEmailAddress();

				if (Validator.isNotNull(originalEmailAddress) &&
					!emailAddress.equals(originalEmailAddress)) {

					return PortalLDAPUtil.getUser(
						ldapServerId, companyId, screenName,
						originalEmailAddress, false);
				}
			}

			return null;
		}
		finally {
			if (enu != null) {
				enu.close();
			}

			if (ldapContext != null) {
				ldapContext.close();
			}
		}
	}

	public static Attributes getUserAttributes(
			long ldapServerId, long companyId, LdapContext ldapContext,
			String fullDistinguishedName)
		throws Exception {

		Properties userMappings = LDAPSettingsUtil.getUserMappings(
			ldapServerId, companyId);
		Properties userExpandoMappings =
			LDAPSettingsUtil.getUserExpandoMappings(ldapServerId, companyId);

		PropertiesUtil.merge(userMappings, userExpandoMappings);

		Properties contactMappings = LDAPSettingsUtil.getContactMappings(
			ldapServerId, companyId);
		Properties contactExpandoMappings =
			LDAPSettingsUtil.getContactExpandoMappings(ldapServerId, companyId);

		PropertiesUtil.merge(contactMappings, contactExpandoMappings);

		PropertiesUtil.merge(userMappings, contactMappings);

		String[] mappedUserAttributeIds = ArrayUtil.toStringArray(
			userMappings.values().toArray(new Object[userMappings.size()]));

		Attributes attributes = _getAttributes(
			ldapContext, fullDistinguishedName, mappedUserAttributeIds);

		if (_log.isDebugEnabled()) {
			for (String attributeId : mappedUserAttributeIds) {
				Attribute attribute = attributes.get(attributeId);

				if (attribute == null) {
					continue;
				}

				_log.debug("LDAP user attribute " + attribute.toString());
			}
		}

		return attributes;
	}

	public static byte[] getUsers(
			long companyId, LdapContext ldapContext, byte[] cookie,
			int maxResults, String baseDN, String userFilter,
			List<SearchResult> searchResults)
		throws Exception {

		return searchLDAP(
			companyId, ldapContext, cookie, maxResults, baseDN, userFilter,
			null, searchResults);
	}

	public static byte[] getUsers(
			long companyId, LdapContext ldapContext, byte[] cookie,
			int maxResults, String baseDN, String userFilter,
			String[] attributeIds, List<SearchResult> searchResults)
		throws Exception {

		return searchLDAP(
			companyId, ldapContext, cookie, maxResults, baseDN, userFilter,
			attributeIds, searchResults);
	}

	public static byte[] getUsers(
			long ldapServerId, long companyId, LdapContext ldapContext,
			byte[] cookie, int maxResults, List<SearchResult> searchResults)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		String baseDN = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_BASE_DN + postfix);
		String userFilter = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_IMPORT_USER_SEARCH_FILTER + postfix);

		return getUsers(
			companyId, ldapContext, cookie, maxResults, baseDN, userFilter,
			searchResults);
	}

	public static byte[] getUsers(
			long ldapServerId, long companyId, LdapContext ldapContext,
			byte[] cookie, int maxResults, String[] attributeIds,
			List<SearchResult> searchResults)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		String baseDN = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_BASE_DN + postfix);
		String userFilter = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_IMPORT_USER_SEARCH_FILTER + postfix);

		return getUsers(
			companyId, ldapContext, cookie, maxResults, baseDN, userFilter,
			attributeIds, searchResults);
	}

	public static String getUsersDN(long ldapServerId, long companyId)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		return PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_USERS_DN + postfix);
	}

	public static boolean hasUser(
			long ldapServerId, long companyId, String screenName,
			String emailAddress)
		throws Exception {

		if (getUser(
				ldapServerId, companyId, screenName, emailAddress) != null) {

			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isGroupMember(
			long ldapServerId, long companyId, String groupDN, String userDN)
		throws Exception {

		LdapContext ldapContext = getContext(ldapServerId, companyId);

		NamingEnumeration<SearchResult> enu = null;

		try {
			if (ldapContext == null) {
				return false;
			}

			Properties groupMappings = LDAPSettingsUtil.getGroupMappings(
				ldapServerId, companyId);

			StringBundler filter = new StringBundler(5);

			filter.append(StringPool.OPEN_PARENTHESIS);
			filter.append(groupMappings.getProperty(GroupConverterKeys.USER));
			filter.append(StringPool.EQUAL);
			filter.append(userDN);
			filter.append(StringPool.CLOSE_PARENTHESIS);

			SearchControls searchControls = new SearchControls(
				SearchControls.SUBTREE_SCOPE, 1, 0, null, false, false);

			enu = ldapContext.search(
				groupDN, filter.toString(), searchControls);

			if (enu.hasMoreElements()) {
				return true;
			}
		}
		catch (NameNotFoundException nnfe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to determine if user DN " + userDN +
						" is a member of group DN " + groupDN,
					nnfe);
			}
		}
		finally {
			if (enu != null) {
				enu.close();
			}

			if (ldapContext != null) {
				ldapContext.close();
			}
		}

		return false;
	}

	public static boolean isUserGroupMember(
			long ldapServerId, long companyId, String groupDN, String userDN)
		throws Exception {

		LdapContext ldapContext = getContext(ldapServerId, companyId);

		NamingEnumeration<SearchResult> enu = null;

		try {
			if (ldapContext == null) {
				return false;
			}

			Properties userMappings = LDAPSettingsUtil.getUserMappings(
				ldapServerId, companyId);

			StringBundler filter = new StringBundler(5);

			filter.append(StringPool.OPEN_PARENTHESIS);
			filter.append(userMappings.getProperty(UserConverterKeys.GROUP));
			filter.append(StringPool.EQUAL);
			filter.append(groupDN);
			filter.append(StringPool.CLOSE_PARENTHESIS);

			SearchControls searchControls = new SearchControls(
				SearchControls.SUBTREE_SCOPE, 1, 0, null, false, false);

			enu = ldapContext.search(userDN, filter.toString(), searchControls);

			if (enu.hasMoreElements()) {
				return true;
			}
		}
		catch (NameNotFoundException nnfe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to determine if group DN " + groupDN +
						" is a member of user DN " + userDN,
					nnfe);
			}
		}
		finally {
			if (enu != null) {
				enu.close();
			}

			if (ldapContext != null) {
				ldapContext.close();
			}
		}

		return false;
	}

	public static byte[] searchLDAP(
			long companyId, LdapContext ldapContext, byte[] cookie,
			int maxResults, String baseDN, String filter, String[] attributeIds,
			List<SearchResult> searchResults)
		throws Exception {

		SearchControls searchControls = new SearchControls(
			SearchControls.SUBTREE_SCOPE, maxResults, 0, attributeIds, false,
			false);

		NamingEnumeration<SearchResult> enu = null;

		try {
			if (cookie != null) {
				if (cookie.length == 0) {
					ldapContext.setRequestControls(
						new Control[] {
							new PagedResultsControl(
								PropsValues.LDAP_PAGE_SIZE, Control.CRITICAL)
						});
				}
				else {
					ldapContext.setRequestControls(
						new Control[] {
							new PagedResultsControl(
								PropsValues.LDAP_PAGE_SIZE, cookie,
								Control.CRITICAL)
						});
				}

				enu = ldapContext.search(baseDN, filter, searchControls);

				while (enu.hasMoreElements()) {
					searchResults.add(enu.nextElement());
				}

				return _getCookie(ldapContext.getResponseControls());
			}
		}
		catch (OperationNotSupportedException onse) {
			if (enu != null) {
				enu.close();
			}

			ldapContext.setRequestControls(null);

			enu = ldapContext.search(baseDN, filter, searchControls);

			while (enu.hasMoreElements()) {
				searchResults.add(enu.nextElement());
			}
		}
		finally {
			if (enu != null) {
				enu.close();
			}

			ldapContext.setRequestControls(null);
		}

		return null;
	}

	private static Attributes _getAttributes(
			LdapContext ldapContext, String fullDistinguishedName,
			String[] attributeIds)
		throws Exception {

		Name fullDN = new CompositeName().add(fullDistinguishedName);

		Attributes attributes = null;

		String[] auditAttributeIds = {
			"creatorsName", "createTimestamp", "modifiersName",
			"modifyTimestamp"
		};

		if (attributeIds == null) {

			// Get complete listing of LDAP attributes (slow)

			attributes = ldapContext.getAttributes(fullDN);

			NamingEnumeration<? extends Attribute> enu = null;

			try {
				Attributes auditAttributes = ldapContext.getAttributes(
					fullDN, auditAttributeIds);

				enu = auditAttributes.getAll();

				while (enu.hasMoreElements()) {
					attributes.put(enu.nextElement());
				}
			}
			finally {
				if (enu != null) {
					enu.close();
				}
			}
		}
		else {

			// Get specified LDAP attributes

			int attributeCount = attributeIds.length + auditAttributeIds.length;

			String[] allAttributeIds = new String[attributeCount];

			System.arraycopy(
				attributeIds, 0, allAttributeIds, 0, attributeIds.length);
			System.arraycopy(
				auditAttributeIds, 0, allAttributeIds, attributeIds.length,
				auditAttributeIds.length);

			attributes = ldapContext.getAttributes(fullDN, allAttributeIds);
		}

		return attributes;
	}

	private static byte[] _getCookie(Control[] controls) {
		if (controls == null) {
			return null;
		}

		for (Control control : controls) {
			if (control instanceof PagedResultsResponseControl) {
				PagedResultsResponseControl pagedResultsResponseControl =
					(PagedResultsResponseControl)control;

				return pagedResultsResponseControl.getCookie();
			}
		}

		return null;
	}

	private static String _getNextRange(String attributeId) {
		String originalAttributeId = null;
		int start = 0;
		int end = 0;

		int x = attributeId.indexOf(CharPool.SEMICOLON);

		if (x < 0) {
			originalAttributeId = attributeId;
			end = PropsValues.LDAP_RANGE_SIZE - 1;
		}
		else {
			int y = attributeId.indexOf(CharPool.EQUAL, x);
			int z = attributeId.indexOf(CharPool.DASH, y);

			originalAttributeId = attributeId.substring(0, x);
			start = GetterUtil.getInteger(attributeId.substring(y + 1, z));
			end = GetterUtil.getInteger(attributeId.substring(z + 1));

			start += PropsValues.LDAP_RANGE_SIZE;
			end += PropsValues.LDAP_RANGE_SIZE;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(originalAttributeId);
		sb.append(StringPool.SEMICOLON);
		sb.append("range=");
		sb.append(start);
		sb.append(StringPool.DASH);
		sb.append(end);

		return sb.toString();
	}

	private static Log _log = LogFactoryUtil.getLog(PortalLDAPUtil.class);

}