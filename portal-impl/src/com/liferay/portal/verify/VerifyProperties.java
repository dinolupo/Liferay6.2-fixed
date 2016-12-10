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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.security.ldap.LDAPSettingsUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portlet.documentlibrary.store.StoreFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class VerifyProperties extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {

		// system.properties

		for (String[] keys : _MIGRATED_SYSTEM_KEYS) {
			String oldKey = keys[0];
			String newKey = keys[1];

			verifyMigratedSystemProperty(oldKey, newKey);
		}

		for (String[] keys : _RENAMED_SYSTEM_KEYS) {
			String oldKey = keys[0];
			String newKey = keys[1];

			verifyRenamedSystemProperty(oldKey, newKey);
		}

		for (String key : _OBSOLETE_SYSTEM_KEYS) {
			verifyObsoleteSystemProperty(key);
		}

		// portal.properties

		Properties portalProperties = loadPortalProperties();

		for (String[] keys : _MIGRATED_PORTAL_KEYS) {
			String oldKey = keys[0];
			String newKey = keys[1];

			verifyMigratedPortalProperty(portalProperties, oldKey, newKey);
		}

		for (String[] keys : _RENAMED_PORTAL_KEYS) {
			String oldKey = keys[0];
			String newKey = keys[1];

			verifyRenamedPortalProperty(portalProperties, oldKey, newKey);
		}

		for (String key : _OBSOLETE_PORTAL_KEYS) {
			verifyObsoletePortalProperty(portalProperties, key);
		}

		// Document library

		StoreFactory.checkProperties();

		// LDAP

		verifyLDAPProperties();
	}

	protected InputStream getPropertiesResourceAsStream(String resourceName)
		throws FileNotFoundException {

		File propertyFile = new File(resourceName);

		if (propertyFile.exists()) {
			return new FileInputStream(propertyFile);
		}

		ClassLoader classLoader = VerifyProperties.class.getClassLoader();

		return classLoader.getResourceAsStream(resourceName);
	}

	protected Properties loadPortalProperties() {
		Properties properties = new Properties();

		List<String> propertiesResourceNames = ListUtil.fromArray(
			PropsUtil.getArray("include-and-override"));

		propertiesResourceNames.add(0, "portal.properties");

		for (String propertyResourceName : propertiesResourceNames) {
			InputStream inputStream = null;

			try {
				inputStream = getPropertiesResourceAsStream(
					propertyResourceName);

				if (inputStream != null) {
					properties.load(inputStream);
				}
			}
			catch (IOException ioe) {
				_log.error(
					"Unable to load property " + propertyResourceName, ioe);
			}
			finally {
				StreamUtil.cleanUp(inputStream);
			}
		}

		return properties;
	}

	protected void verifyLDAPProperties() throws Exception {
		long[] companyIds = PortalInstances.getCompanyIdsBySQL();

		for (long companyId : companyIds) {
			UnicodeProperties properties = new UnicodeProperties();

			long[] ldapServerIds = StringUtil.split(
				PrefsPropsUtil.getString(companyId, "ldap.server.ids"), 0L);

			for (long ldapServerId : ldapServerIds) {
				String postfix = LDAPSettingsUtil.getPropertyPostfix(
					ldapServerId);

				for (String key : _LDAP_KEYS) {
					String value = PrefsPropsUtil.getString(
						companyId, key + postfix, null);

					if (value == null) {
						properties.put(key + postfix, StringPool.BLANK);
					}
				}
			}

			if (!properties.isEmpty()) {
				CompanyLocalServiceUtil.updatePreferences(
					companyId, properties);
			}
		}
	}

	protected void verifyMigratedPortalProperty(
			Properties portalProperties, String oldKey, String newKey)
		throws Exception {

		if (portalProperties.containsKey(oldKey)) {
			_log.error(
				"Portal property \"" + oldKey +
					"\" was migrated to the system property \"" + newKey +
						"\"");
		}
	}

	protected void verifyMigratedSystemProperty(String oldKey, String newKey)
		throws Exception {

		String value = SystemProperties.get(oldKey);

		if (value != null) {
			_log.error(
				"System property \"" + oldKey +
					"\" was migrated to the portal property \"" + newKey +
						"\"");
		}
	}

	protected void verifyObsoletePortalProperty(
			Properties portalProperties, String key)
		throws Exception {

		if (portalProperties.containsKey(key)) {
			_log.error("Portal property \"" + key + "\" is obsolete");
		}
	}

	protected void verifyObsoleteSystemProperty(String key) throws Exception {
		String value = SystemProperties.get(key);

		if (value != null) {
			_log.error("System property \"" + key + "\" is obsolete");
		}
	}

	protected void verifyRenamedPortalProperty(
			Properties portalProperties, String oldKey, String newKey)
		throws Exception {

		if (portalProperties.containsKey(oldKey)) {
			_log.error(
				"Portal property \"" + oldKey + "\" was renamed to \"" +
					newKey + "\"");
		}
	}

	protected void verifyRenamedSystemProperty(String oldKey, String newKey)
		throws Exception {

		String value = SystemProperties.get(oldKey);

		if (value != null) {
			_log.error(
				"System property \"" + oldKey + "\" was renamed to \"" +
					newKey + "\"");
		}
	}

	private static final String[] _LDAP_KEYS = {
		PropsKeys.LDAP_CONTACT_CUSTOM_MAPPINGS, PropsKeys.LDAP_CONTACT_MAPPINGS,
		PropsKeys.LDAP_USER_CUSTOM_MAPPINGS
	};

	private static final String[][] _MIGRATED_PORTAL_KEYS = new String[][] {
		new String[] {
			"cookie.http.only.names.excludes", "cookie.http.only.names.excludes"
		},
		new String[] {
			"finalize.manager.thread.enabled",
			"com.liferay.portal.kernel.memory.FinalizeManager.thread.enabled"
		},
		new String[] {
			"http.header.secure.x.content.type.options",
			"http.header.secure.x.content.type.options"
		},
		new String[] {
			"http.header.secure.x.content.type.options.urls.excludes",
			"http.header.secure.x.content.type.options.urls.excludes"
		},
		new String[] {
			"http.header.secure.x.frame.options",
			"http.header.secure.x.frame.options"
		},
		new String[] {
			"http.header.secure.x.frame.options.255",
			"http.header.secure.x.frame.options.255"
		},
		new String[] {
			"http.header.secure.x.xss.protection",
			"http.header.secure.x.xss.protection"
		}
	};

	private static final String[][] _MIGRATED_SYSTEM_KEYS = new String[][] {
		new String[] {
			"com.liferay.filters.compression.CompressionFilter",
			"com.liferay.portal.servlet.filters.gzip.GZipFilter"
		},
		new String[] {
			"com.liferay.filters.strip.StripFilter",
			"com.liferay.portal.servlet.filters.strip.StripFilter"
		},
		new String[] {
			"com.liferay.util.Http.max.connections.per.host",
			"com.liferay.portal.util.HttpImpl.max.connections.per.host"
		},
		new String[] {
			"com.liferay.util.Http.max.total.connections",
			"com.liferay.portal.util.HttpImpl.max.total.connections"
		},
		new String[] {
			"com.liferay.util.Http.proxy.auth.type",
			"com.liferay.portal.util.HttpImpl.proxy.auth.type"
		},
		new String[] {
			"com.liferay.util.Http.proxy.ntlm.domain",
			"com.liferay.portal.util.HttpImpl.proxy.ntlm.domain"
		},
		new String[] {
			"com.liferay.util.Http.proxy.ntlm.host",
			"com.liferay.portal.util.HttpImpl.proxy.ntlm.host"
		},
		new String[] {
			"com.liferay.util.Http.proxy.password",
			"com.liferay.portal.util.HttpImpl.proxy.password"
		},
		new String[] {
			"com.liferay.util.Http.proxy.username",
			"com.liferay.portal.util.HttpImpl.proxy.username"
		},
		new String[] {
			"com.liferay.util.Http.timeout",
			"com.liferay.portal.util.HttpImpl.timeout"
		},
		new String[] {
			"com.liferay.util.format.PhoneNumberFormat",
			"phone.number.format.impl"
		},
		new String[] {
			"com.liferay.util.servlet.UploadServletRequest.max.size",
			"com.liferay.portal.upload.UploadServletRequestImpl.max.size"
		},
		new String[] {
			"com.liferay.util.servlet.UploadServletRequest.temp.dir",
			"com.liferay.portal.upload.UploadServletRequestImpl.temp.dir"
		},
		new String[] {
			"com.liferay.util.servlet.fileupload.LiferayFileItem." +
				"threshold.size",
			"com.liferay.portal.upload.LiferayFileItem.threshold.size"
		},
		new String[] {
			"com.liferay.util.servlet.fileupload.LiferayInputStream." +
				"threshold.size",
			"com.liferay.portal.upload.LiferayInputStream.threshold.size"
		}
	};

	private static final String[] _OBSOLETE_PORTAL_KEYS = new String[] {
		"asset.entry.increment.view.counter.enabled", "auth.max.failures.limit",
		"buffered.increment.parallel.queue.size",
		"buffered.increment.serial.queue.size", "cas.validate.url",
		"cluster.executor.heartbeat.interval",
		"com.liferay.filters.doubleclick.DoubleClickFilter",
		"com.liferay.portal.servlet.filters.doubleclick.DoubleClickFilter",
		"commons.pool.enabled", "dl.file.entry.read.count.enabled",
		"dynamic.data.lists.template.language.parser[ftl]",
		"dynamic.data.lists.template.language.parser[vm]",
		"dynamic.data.lists.template.language.parser[xsl]",
		"dynamic.data.mapping.template.language.types",
		"ehcache.statistics.enabled", "jbi.workflow.url",
		"journal.template.language.parser[css]",
		"journal.template.language.parser[ftl]",
		"journal.template.language.parser[vm]",
		"journal.template.language.parser[xsl]",
		"journal.template.language.types", "lucene.analyzer",
		"lucene.store.jdbc.auto.clean.up",
		"lucene.store.jdbc.auto.clean.up.enabled",
		"lucene.store.jdbc.auto.clean.up.interval",
		"lucene.store.jdbc.dialect.db2", "lucene.store.jdbc.dialect.derby",
		"lucene.store.jdbc.dialect.hsqldb", "lucene.store.jdbc.dialect.jtds",
		"lucene.store.jdbc.dialect.microsoft",
		"lucene.store.jdbc.dialect.mysql", "lucene.store.jdbc.dialect.oracle",
		"lucene.store.jdbc.dialect.postgresql",
		"memory.cluster.scheduler.lock.cache.enabled",
		"message.boards.thread.locking.enabled", "portal.ctx",
		"portal.security.manager.enable", "permissions.user.check.algorithm",
		"scheduler.classes", "schema.run.minimal", "shard.available.names",
		"velocity.engine.resource.manager",
		"velocity.engine.resource.manager.cache.enabled",
		"webdav.storage.class", "webdav.storage.show.edit.url",
		"webdav.storage.show.view.url", "webdav.storage.tokens", "xss.allow"
	};

	private static final String[] _OBSOLETE_SYSTEM_KEYS = new String[] {
		"com.liferay.util.Http.proxy.host", "com.liferay.util.Http.proxy.port",
		"com.liferay.util.XSSUtil.regexp.pattern"
	};

	private static final String[][] _RENAMED_PORTAL_KEYS = new String[][] {
		new String[] {
			"amazon.license.0", "amazon.access.key.id"
		},
		new String[] {
			"amazon.license.1", "amazon.access.key.id"
		},
		new String[] {
			"amazon.license.2", "amazon.access.key.id"
		},
		new String[] {
			"amazon.license.3", "amazon.access.key.id"
		},
		new String[] {
			"cdn.host", "cdn.host.http"
		},
		new String[] {
			"com.liferay.portal.servlet.filters.compression.CompressionFilter",
			"com.liferay.portal.servlet.filters.gzip.GZipFilter"
		},
		new String[] {
			"default.guest.friendly.url",
			"default.guest.public.layout.friendly.url"
		},
		new String[] {
			"default.guest.layout.column", "default.guest.public.layout.column"
		},
		new String[] {
			"default.guest.layout.name", "default.guest.public.layout.name"
		},
		new String[] {
			"default.guest.layout.template.id",
			"default.guest.public.layout.template.id"
		},
		new String[] {
			"default.user.layout.column", "default.user.public.layout.column"
		},
		new String[] {
			"default.user.layout.name", "default.user.public.layout.name"
		},
		new String[] {
			"default.user.layout.template.id",
			"default.user.public.layout.template.id"
		},
		new String[] {
			"default.user.private.layout.lar",
			"default.user.private.layouts.lar"
		},
		new String[] {
			"default.user.public.layout.lar", "default.user.public.layouts.lar"
		},
		new String[] {
			"dl.hook.cmis.credentials.password",
			"dl.store.cmis.credentials.password"
		},
		new String[] {
			"dl.hook.cmis.credentials.username",
			"dl.store.cmis.credentials.username"
		},
		new String[] {
			"dl.hook.cmis.repository.url", "dl.store.cmis.repository.url"
		},
		new String[] {
			"dl.hook.cmis.system.root.dir", "dl.store.cmis.system.root.dir"
		},
		new String[] {
			"dl.hook.file.system.root.dir", "dl.store.file.system.root.dir"
		},
		new String[] {
			"dl.hook.impl", "dl.store.impl"
		},
		new String[] {
			"dl.hook.jcr.fetch.delay", "dl.store.jcr.fetch.delay"
		},
		new String[] {
			"dl.hook.jcr.fetch.max.failures", "dl.store.jcr.fetch.max.failures"
		},
		new String[] {
			"dl.hook.jcr.move.version.labels",
			"dl.store.jcr.move.version.labels"
		},
		new String[] {
			"dl.hook.s3.access.key", "dl.store.s3.access.key"
		},
		new String[] {
			"dl.hook.s3.bucket.name", "dl.store.s3.bucket.name"
		},
		new String[] {
			"dl.hook.s3.secret.key", "dl.store.s3.secret.key"
		},
		new String[] {
			"editor.wysiwyg.portal-web.docroot.html.portlet.calendar." +
				"edit_configuration.jsp",
			"editor.wysiwyg.portal-web.docroot.html.portlet.calendar." +
				"configuration.jsp"
		},
		new String[] {
			"editor.wysiwyg.portal-web.docroot.html.portlet.invitation." +
				"edit_configuration.jsp",
			"editor.wysiwyg.portal-web.docroot.html.portlet.invitation." +
				"configuration.jsp"
		},
		new String[] {
			"editor.wysiwyg.portal-web.docroot.html.portlet.journal." +
				"edit_configuration.jsp",
			"editor.wysiwyg.portal-web.docroot.html.portlet.journal." +
				"configuration.jsp"
		},
		new String[] {
			"editor.wysiwyg.portal-web.docroot.html.portlet.message_boards." +
				"edit_configuration.jsp",
			"editor.wysiwyg.portal-web.docroot.html.portlet.message_boards." +
				"configuration.jsp"
		},
		new String[] {
			"editor.wysiwyg.portal-web.docroot.html.portlet.shopping." +
				"edit_configuration.jsp",
			"editor.wysiwyg.portal-web.docroot.html.portlet.shopping." +
				"configuration.jsp"
		},
		new String[] {
			"field.editable.com.liferay.portal.model.User.emailAddress",
			"field.editable.user.types"
		},
		new String[] {
			"field.editable.com.liferay.portal.model.User.screenName",
			"field.editable.user.types"
		},
		new String[] {
			"journal.error.template.freemarker", "journal.error.template[ftl]"
		},
		new String[] {
			"journal.error.template.velocity", "journal.error.template[vm]"
		},
		new String[] {
			"journal.error.template.xsl", "journal.error.template[xsl]"
		},
		new String[] {
			"journal.template.freemarker.restricted.variables",
			"freemarker.engine.restricted.variables"
		},
		new String[] {
			"journal.template.velocity.restricted.variables",
			"velocity.engine.restricted.variables"
		},
		new String[] {
			"referer.url.domains.allowed", "redirect.url.domains.allowed"
		},
		new String[] {
			"referer.url.ips.allowed", "redirect.url.ips.allowed"
		},
		new String[] {
			"referer.url.security.mode", "redirect.url.security.mode"
		},
		new String[] {
			"tags.asset.increment.view.counter.enabled",
			"asset.entry.increment.view.counter.enabled"
		}
	};

	private static final String[][] _RENAMED_SYSTEM_KEYS = new String[][] {
		new String[] {
			"com.liferay.portal.kernel.util.StringBundler.unsafe.create." +
				"threshold",
			"com.liferay.portal.kernel.util.StringBundler.threadlocal.buffer." +
				"limit",
		}
	};

	private static Log _log = LogFactoryUtil.getLog(VerifyProperties.class);

}