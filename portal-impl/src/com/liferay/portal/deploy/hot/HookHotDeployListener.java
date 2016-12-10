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

package com.liferay.portal.deploy.hot;

import com.liferay.portal.captcha.CaptchaImpl;
import com.liferay.portal.events.EventsProcessorUtil;
import com.liferay.portal.kernel.bean.BeanLocatorException;
import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.captcha.Captcha;
import com.liferay.portal.kernel.captcha.CaptchaUtil;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.deploy.auto.AutoDeployDir;
import com.liferay.portal.kernel.deploy.auto.AutoDeployListener;
import com.liferay.portal.kernel.deploy.auto.AutoDeployUtil;
import com.liferay.portal.kernel.deploy.hot.BaseHotDeployListener;
import com.liferay.portal.kernel.deploy.hot.HotDeployEvent;
import com.liferay.portal.kernel.deploy.hot.HotDeployException;
import com.liferay.portal.kernel.deploy.hot.HotDeployListener;
import com.liferay.portal.kernel.deploy.hot.HotDeployUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.InvokerAction;
import com.liferay.portal.kernel.events.InvokerSessionAction;
import com.liferay.portal.kernel.events.InvokerSimpleAction;
import com.liferay.portal.kernel.events.SessionAction;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.format.PhoneNumberFormat;
import com.liferay.portal.kernel.format.PhoneNumberFormatUtil;
import com.liferay.portal.kernel.format.PhoneNumberFormatWrapper;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.lock.LockListener;
import com.liferay.portal.kernel.lock.LockListenerRegistryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.log.SanitizerLogWrapper;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.kernel.sanitizer.SanitizerUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.pacl.PACLConstants;
import com.liferay.portal.kernel.security.pacl.permission.PortalHookPermission;
import com.liferay.portal.kernel.servlet.DirectServletRegistryUtil;
import com.liferay.portal.kernel.servlet.LiferayFilter;
import com.liferay.portal.kernel.servlet.LiferayFilterTracker;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.servlet.TryFilter;
import com.liferay.portal.kernel.servlet.TryFinallyFilter;
import com.liferay.portal.kernel.servlet.WrapHttpServletRequestFilter;
import com.liferay.portal.kernel.servlet.WrapHttpServletResponseFilter;
import com.liferay.portal.kernel.servlet.filters.invoker.FilterMapping;
import com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterConfig;
import com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterHelper;
import com.liferay.portal.kernel.servlet.taglib.FileAvailabilityUtil;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.UnsecureSAXReaderUtil;
import com.liferay.portal.language.LanguageResources;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.Release;
import com.liferay.portal.repository.util.RepositoryFactory;
import com.liferay.portal.repository.util.RepositoryFactoryImpl;
import com.liferay.portal.repository.util.RepositoryFactoryUtil;
import com.liferay.portal.sanitizer.SanitizerImpl;
import com.liferay.portal.security.auth.AuthFailure;
import com.liferay.portal.security.auth.AuthPipeline;
import com.liferay.portal.security.auth.AuthToken;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.security.auth.AuthTokenWhitelistUtil;
import com.liferay.portal.security.auth.AuthTokenWrapper;
import com.liferay.portal.security.auth.AuthVerifier;
import com.liferay.portal.security.auth.AuthVerifierConfiguration;
import com.liferay.portal.security.auth.AuthVerifierPipeline;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.security.auth.AutoLogin;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.auth.EmailAddressGenerator;
import com.liferay.portal.security.auth.EmailAddressGeneratorFactory;
import com.liferay.portal.security.auth.EmailAddressValidator;
import com.liferay.portal.security.auth.EmailAddressValidatorFactory;
import com.liferay.portal.security.auth.FullNameGenerator;
import com.liferay.portal.security.auth.FullNameGeneratorFactory;
import com.liferay.portal.security.auth.FullNameValidator;
import com.liferay.portal.security.auth.FullNameValidatorFactory;
import com.liferay.portal.security.auth.InterruptedPortletRequestWhitelistUtil;
import com.liferay.portal.security.auth.ScreenNameGenerator;
import com.liferay.portal.security.auth.ScreenNameGeneratorFactory;
import com.liferay.portal.security.auth.ScreenNameValidator;
import com.liferay.portal.security.auth.ScreenNameValidatorFactory;
import com.liferay.portal.security.lang.DoPrivilegedBean;
import com.liferay.portal.security.ldap.AttributesTransformer;
import com.liferay.portal.security.ldap.AttributesTransformerFactory;
import com.liferay.portal.security.membershippolicy.OrganizationMembershipPolicy;
import com.liferay.portal.security.membershippolicy.OrganizationMembershipPolicyFactoryImpl;
import com.liferay.portal.security.membershippolicy.OrganizationMembershipPolicyFactoryUtil;
import com.liferay.portal.security.membershippolicy.RoleMembershipPolicy;
import com.liferay.portal.security.membershippolicy.RoleMembershipPolicyFactoryImpl;
import com.liferay.portal.security.membershippolicy.RoleMembershipPolicyFactoryUtil;
import com.liferay.portal.security.membershippolicy.SiteMembershipPolicy;
import com.liferay.portal.security.membershippolicy.SiteMembershipPolicyFactoryImpl;
import com.liferay.portal.security.membershippolicy.SiteMembershipPolicyFactoryUtil;
import com.liferay.portal.security.membershippolicy.UserGroupMembershipPolicy;
import com.liferay.portal.security.membershippolicy.UserGroupMembershipPolicyFactoryImpl;
import com.liferay.portal.security.membershippolicy.UserGroupMembershipPolicyFactoryUtil;
import com.liferay.portal.security.pwd.PwdToolkitUtil;
import com.liferay.portal.security.pwd.Toolkit;
import com.liferay.portal.security.pwd.ToolkitWrapper;
import com.liferay.portal.service.ReleaseLocalServiceUtil;
import com.liferay.portal.service.ServiceWrapper;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.servlet.filters.autologin.AutoLoginFilter;
import com.liferay.portal.servlet.filters.cache.CacheUtil;
import com.liferay.portal.spring.aop.ServiceBeanAopCacheManagerUtil;
import com.liferay.portal.spring.aop.ServiceBeanAopProxy;
import com.liferay.portal.struts.AuthPublicPathRegistry;
import com.liferay.portal.struts.StrutsActionRegistryUtil;
import com.liferay.portal.upgrade.UpgradeProcessUtil;
import com.liferay.portal.util.CustomJspRegistryUtil;
import com.liferay.portal.util.JavaScriptBundleUtil;
import com.liferay.portal.util.LayoutSettings;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.ControlPanelEntry;
import com.liferay.portlet.DefaultControlPanelEntryFactory;
import com.liferay.portlet.assetpublisher.util.AssetEntryQueryProcessor;
import com.liferay.portlet.assetpublisher.util.AssetPublisherUtil;
import com.liferay.portlet.documentlibrary.antivirus.AntivirusScanner;
import com.liferay.portlet.documentlibrary.antivirus.AntivirusScannerUtil;
import com.liferay.portlet.documentlibrary.antivirus.AntivirusScannerWrapper;
import com.liferay.portlet.documentlibrary.store.Store;
import com.liferay.portlet.documentlibrary.store.StoreFactory;
import com.liferay.portlet.documentlibrary.util.DLProcessor;
import com.liferay.portlet.documentlibrary.util.DLProcessorRegistryUtil;

import java.io.File;
import java.io.InputStream;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.target.SingletonTargetSource;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 * @author Wesley Gong
 * @author Ryan Park
 * @author Mika Koivisto
 */
public class HookHotDeployListener
	extends BaseHotDeployListener implements PropsKeys {

	public static final String[] SUPPORTED_PROPERTIES = {
		"admin.default.group.names", "admin.default.role.names",
		"admin.default.user.group.names",
		"asset.publisher.asset.entry.query.processors",
		"asset.publisher.display.styles",
		"asset.publisher.query.form.configuration", "auth.forward.by.last.path",
		"auth.public.paths", "auth.verifier.pipeline", "auto.deploy.listeners",
		"application.startup.events", "auth.failure", "auth.max.failures",
		"auth.token.ignore.actions", "auth.token.ignore.origins",
		"auth.token.ignore.portlets", "auth.token.impl", "auth.pipeline.post",
		"auth.pipeline.pre", "auto.login.hooks",
		"captcha.check.portal.create_account", "captcha.engine.impl",
		"company.default.locale", "company.default.time.zone",
		"company.settings.form.authentication",
		"company.settings.form.configuration",
		"company.settings.form.identification",
		"company.settings.form.miscellaneous",
		"control.panel.entry.class.default", "convert.processes",
		"default.landing.page.path", "default.regular.color.scheme.id",
		"default.regular.theme.id", "default.wap.color.scheme.id",
		"default.wap.theme.id", "dl.file.entry.drafts.enabled",
		"dl.file.entry.open.in.ms.office.manual.check.in.required",
		"dl.file.entry.processors", "dl.repository.impl",
		"dl.store.antivirus.impl", "dl.store.impl", "dockbar.add.portlets",
		"field.enable.com.liferay.portal.model.Contact.birthday",
		"field.enable.com.liferay.portal.model.Contact.male",
		"field.enable.com.liferay.portal.model.Organization.status",
		"hot.deploy.listeners", "javascript.fast.load",
		"journal.article.form.add", "journal.article.form.translate",
		"journal.article.form.update", "layout.form.add", "layout.form.update",
		"layout.set.form.update", "layout.static.portlets.all",
		"layout.template.cache.enabled", "layout.types",
		"layout.user.private.layouts.auto.create",
		"layout.user.private.layouts.enabled",
		"layout.user.private.layouts.power.user.required",
		"layout.user.public.layouts.auto.create",
		"layout.user.public.layouts.enabled",
		"layout.user.public.layouts.power.user.required",
		"ldap.attrs.transformer.impl", "locales", "locales.beta",
		"locales.enabled", "lock.listeners",
		"login.create.account.allow.custom.password", "login.dialog.disabled",
		"login.events.post", "login.events.pre", "login.form.navigation.post",
		"login.form.navigation.pre", "logout.events.post", "logout.events.pre",
		"mail.hook.impl", "my.sites.show.private.sites.with.no.layouts",
		"my.sites.show.public.sites.with.no.layouts",
		"my.sites.show.user.private.sites.with.no.layouts",
		"my.sites.show.user.public.sites.with.no.layouts",
		"organizations.form.add.identification", "organizations.form.add.main",
		"organizations.form.add.miscellaneous",
		"passwords.passwordpolicytoolkit.generator",
		"passwords.passwordpolicytoolkit.static", "phone.number.format.impl",
		"phone.number.format.international.regexp",
		"phone.number.format.usa.regexp",
		"portlet.add.default.resource.check.enabled",
		"portlet.add.default.resource.check.whitelist",
		"portlet.add.default.resource.check.whitelist.actions",
		"rss.feeds.enabled", "sanitizer.impl", "servlet.session.create.events",
		"servlet.session.destroy.events", "servlet.service.events.post",
		"servlet.service.events.pre", "session.max.allowed",
		"session.phishing.protected.attributes", "session.store.password",
		"sites.form.add.advanced", "sites.form.add.main", "sites.form.add.seo",
		"sites.form.update.advanced", "sites.form.update.main",
		"sites.form.update.seo", "social.activity.sets.bundling.enabled",
		"social.activity.sets.enabled", "social.activity.sets.selector",
		"social.bookmark.*", "staging.xstream.class.whitelist",
		"terms.of.use.required", "theme.css.fast.load",
		"theme.images.fast.load", "theme.jsp.override.enabled",
		"theme.loader.new.theme.id.on.import", "theme.portlet.decorate.default",
		"theme.portlet.sharing.default", "theme.shortcut.icon", "time.zones",
		"upgrade.processes", "user.notification.event.confirmation.enabled",
		"users.email.address.generator", "users.email.address.validator",
		"users.email.address.required", "users.form.add.identification",
		"users.form.add.main", "users.form.add.miscellaneous",
		"users.form.my.account.identification", "users.form.my.account.main",
		"users.form.my.account.miscellaneous",
		"users.form.update.identification", "users.form.update.main",
		"users.form.update.miscellaneous", "users.full.name.generator",
		"users.full.name.validator", "users.image.max.height",
		"users.image.max.width", "users.screen.name.always.autogenerate",
		"users.screen.name.generator", "users.screen.name.validator",
		"value.object.listener.*"
	};

	public HookHotDeployListener() {
		for (String key : _PROPS_VALUES_MERGE_STRING_ARRAY) {
			_mergeStringArraysContainerMap.put(
				key, new MergeStringArraysContainer(key));
		}

		for (String key : _PROPS_VALUES_OVERRIDE_STRING_ARRAY) {
			_overrideStringArraysContainerMap.put(
				key, new OverrideStringArraysContainer(key));
		}
	}

	@Override
	public void invokeDeploy(HotDeployEvent hotDeployEvent)
		throws HotDeployException {

		try {
			doInvokeDeploy(hotDeployEvent);
		}
		catch (Throwable t) {
			throwHotDeployException(
				hotDeployEvent,
				"Error registering hook for " +
					hotDeployEvent.getServletContextName(),
				t);
		}
	}

	@Override
	public void invokeUndeploy(HotDeployEvent hotDeployEvent)
		throws HotDeployException {

		try {
			doInvokeUndeploy(hotDeployEvent);
		}
		catch (Throwable t) {
			throwHotDeployException(
				hotDeployEvent,
				"Error unregistering hook for " +
					hotDeployEvent.getServletContextName(),
				t);
		}
	}

	protected boolean checkPermission(
		String name, ClassLoader portletClassLoader, Object subject,
		String message) {

		try {
			PortalHookPermission.checkPermission(
				name, portletClassLoader, subject);
		}
		catch (SecurityException se) {
			if (_log.isInfoEnabled()) {
				_log.info(message);
			}

			return false;
		}

		return true;
	}

	protected boolean containsKey(Properties portalProperties, String key) {
		if (_log.isDebugEnabled()) {
			return true;
		}
		else {
			return portalProperties.containsKey(key);
		}
	}

	protected void destroyCustomJspBag(
			String servletContextName, CustomJspBag customJspBag)
		throws Exception {

		String customJspDir = customJspBag.getCustomJspDir();
		boolean customJspGlobal = customJspBag.isCustomJspGlobal();
		List<String> customJsps = customJspBag.getCustomJsps();

		String portalWebDir = PortalUtil.getPortalWebDir();

		for (String customJsp : customJsps) {
			int pos = customJsp.indexOf(customJspDir);

			String portalJsp = customJsp.substring(pos + customJspDir.length());

			if (customJspGlobal) {
				File portalJspFile = new File(portalWebDir + portalJsp);
				File portalJspBackupFile = getPortalJspBackupFile(
					portalJspFile);

				if (portalJspBackupFile.exists()) {
					FileUtil.copyFile(portalJspBackupFile, portalJspFile);

					portalJspBackupFile.delete();
				}
				else if (portalJspFile.exists()) {
					portalJspFile.delete();
				}
			}
			else {
				portalJsp = CustomJspRegistryUtil.getCustomJspFileName(
					servletContextName, portalJsp);

				File portalJspFile = new File(portalWebDir + portalJsp);

				if (portalJspFile.exists()) {
					portalJspFile.delete();
				}
			}
		}

		if (!customJspGlobal) {
			CustomJspRegistryUtil.unregisterServletContextName(
				servletContextName);
		}
	}

	protected void destroyPortalProperties(
			String servletContextName, Properties portalProperties)
		throws Exception {

		PropsUtil.removeProperties(portalProperties);

		if (_log.isDebugEnabled() && portalProperties.containsKey(LOCALES)) {
			_log.debug(
				"Portlet locales " + portalProperties.getProperty(LOCALES));
			_log.debug("Original locales " + PropsUtil.get(LOCALES));
			_log.debug(
				"Original locales array length " +
					PropsUtil.getArray(LOCALES).length);
		}

		resetPortalProperties(servletContextName, portalProperties, false);

		if (portalProperties.containsKey(
				PropsKeys.ASSET_PUBLISHER_ASSET_ENTRY_QUERY_PROCESSORS)) {

			String[] assetQueryProcessorClassNames = StringUtil.split(
				portalProperties.getProperty(
					PropsKeys.ASSET_PUBLISHER_ASSET_ENTRY_QUERY_PROCESSORS));

			for (String assetQueryProcessorClassName :
					assetQueryProcessorClassNames) {

				AssetPublisherUtil.unregisterAssetQueryProcessor(
					assetQueryProcessorClassName);

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Unregistered asset query processor " +
							assetQueryProcessorClassName);
				}
			}
		}

		if (portalProperties.containsKey(PropsKeys.AUTH_TOKEN_IMPL)) {
			AuthTokenWrapper authTokenWrapper =
				(AuthTokenWrapper)AuthTokenUtil.getAuthToken();

			authTokenWrapper.setAuthToken(null);
		}

		if (portalProperties.containsKey(PropsKeys.CAPTCHA_ENGINE_IMPL)) {
			CaptchaImpl captchaImpl = null;

			Captcha captcha = CaptchaUtil.getCaptcha();

			if (captcha instanceof DoPrivilegedBean) {
				DoPrivilegedBean doPrivilegedBean = (DoPrivilegedBean)captcha;

				captchaImpl = (CaptchaImpl)doPrivilegedBean.getActualBean();
			}
			else {
				captchaImpl = (CaptchaImpl)captcha;
			}

			captchaImpl.setCaptcha(null);
		}

		if (portalProperties.containsKey(
				PropsKeys.CONTROL_PANEL_DEFAULT_ENTRY_CLASS)) {

			DefaultControlPanelEntryFactory.setInstance(null);
		}

		if (portalProperties.containsKey(PropsKeys.DL_FILE_ENTRY_PROCESSORS)) {
			DLFileEntryProcessorContainer dlFileEntryProcessorContainer =
				_dlFileEntryProcessorContainerMap.remove(servletContextName);

			dlFileEntryProcessorContainer.unregisterDLProcessors();
		}

		if (portalProperties.containsKey(PropsKeys.DL_REPOSITORY_IMPL)) {
			DLRepositoryContainer dlRepositoryContainer =
				_dlRepositoryContainerMap.remove(servletContextName);

			dlRepositoryContainer.unregisterRepositoryFactories();
		}

		if (portalProperties.containsKey(PropsKeys.DL_STORE_ANTIVIRUS_IMPL)) {
			AntivirusScannerWrapper antivirusScannerWrapper =
				(AntivirusScannerWrapper)
					AntivirusScannerUtil.getAntivirusScanner();

			antivirusScannerWrapper.setAntivirusScanner(null);
		}

		if (portalProperties.containsKey(PropsKeys.DL_STORE_IMPL)) {
			StoreFactory.setInstance(null);
		}

		if (portalProperties.containsKey(
				PropsKeys.LDAP_ATTRS_TRANSFORMER_IMPL)) {

			AttributesTransformerFactory.setInstance(null);
		}

		if (portalProperties.containsKey(LOCK_LISTENERS)) {
			LockListenerContainer lockListenerContainer =
				_lockListenerContainerMap.remove(servletContextName);

			if (lockListenerContainer != null) {
				lockListenerContainer.unregisterLockListeners();
			}
		}

		if (portalProperties.containsKey(PropsKeys.MAIL_HOOK_IMPL)) {
			com.liferay.mail.util.HookFactory.setInstance(null);
		}

		if (portalProperties.containsKey(
				PropsKeys.MEMBERSHIP_POLICY_ORGANIZATIONS)) {

			OrganizationMembershipPolicyFactoryImpl
				organizationMembershipPolicyFactoryImpl =
					(OrganizationMembershipPolicyFactoryImpl)
						OrganizationMembershipPolicyFactoryUtil.
							getOrganizationMembershipPolicyFactory();

			organizationMembershipPolicyFactoryImpl.
				setOrganizationMembershipPolicy(null);
		}

		if (portalProperties.containsKey(PropsKeys.MEMBERSHIP_POLICY_ROLES)) {
			RoleMembershipPolicyFactoryImpl roleMembershipPolicyFactoryImpl =
				(RoleMembershipPolicyFactoryImpl)
					RoleMembershipPolicyFactoryUtil.
						getRoleMembershipPolicyFactory();

			roleMembershipPolicyFactoryImpl.setRoleMembershipPolicy(null);
		}

		if (portalProperties.containsKey(PropsKeys.MEMBERSHIP_POLICY_SITES)) {
			SiteMembershipPolicyFactoryImpl siteMembershipPolicyFactoryImpl =
				(SiteMembershipPolicyFactoryImpl)
					SiteMembershipPolicyFactoryUtil.
						getSiteMembershipPolicyFactory();

			siteMembershipPolicyFactoryImpl.setSiteMembershipPolicy(null);
		}

		if (portalProperties.containsKey(
				PropsKeys.MEMBERSHIP_POLICY_USER_GROUPS)) {

			UserGroupMembershipPolicyFactoryImpl
				userGroupMembershipPolicyFactoryImpl =
					(UserGroupMembershipPolicyFactoryImpl)
						UserGroupMembershipPolicyFactoryUtil.
							getUserGroupMembershipPolicyFactory();

			userGroupMembershipPolicyFactoryImpl.setUserGroupMembershipPolicy(
				null);
		}

		if (portalProperties.containsKey(PropsKeys.PASSWORDS_TOOLKIT)) {
			ToolkitWrapper toolkitWrapper =
				(ToolkitWrapper)PwdToolkitUtil.getToolkit();

			toolkitWrapper.setToolkit(null);
		}

		if (portalProperties.containsKey(PropsKeys.PHONE_NUMBER_FORMAT_IMPL)) {
			PhoneNumberFormatWrapper phoneNumberFormatWrapper =
				(PhoneNumberFormatWrapper)
					PhoneNumberFormatUtil.getPhoneNumberFormat();

			phoneNumberFormatWrapper.setPhoneNumberFormat(null);
		}

		if (portalProperties.containsKey(PropsKeys.SANITIZER_IMPL)) {
			SanitizerContainer sanitizerContainer =
				_sanitizerContainerMap.remove(servletContextName);

			if (sanitizerContainer != null) {
				sanitizerContainer.unregisterSanitizers();
			}
		}

		if (portalProperties.containsKey(
				PropsKeys.USERS_EMAIL_ADDRESS_GENERATOR)) {

			EmailAddressGeneratorFactory.setInstance(null);
		}

		if (portalProperties.containsKey(
				PropsKeys.USERS_EMAIL_ADDRESS_VALIDATOR)) {

			EmailAddressValidatorFactory.setInstance(null);
		}

		if (portalProperties.containsKey(PropsKeys.USERS_FULL_NAME_GENERATOR)) {
			FullNameGeneratorFactory.setInstance(null);
		}

		if (portalProperties.containsKey(PropsKeys.USERS_FULL_NAME_VALIDATOR)) {
			FullNameValidatorFactory.setInstance(null);
		}

		if (portalProperties.containsKey(
				PropsKeys.USERS_SCREEN_NAME_GENERATOR)) {

			ScreenNameGeneratorFactory.setInstance(null);
		}

		if (portalProperties.containsKey(
				PropsKeys.USERS_SCREEN_NAME_VALIDATOR)) {

			ScreenNameValidatorFactory.setInstance(null);
		}

		Set<String> liferayFilterClassNames =
			LiferayFilterTracker.getClassNames();

		for (String liferayFilterClassName : liferayFilterClassNames) {
			if (!portalProperties.containsKey(liferayFilterClassName)) {
				continue;
			}

			boolean filterEnabled = GetterUtil.getBoolean(
				PropsUtil.get(liferayFilterClassName));

			Set<LiferayFilter> liferayFilters =
				LiferayFilterTracker.getLiferayFilters(liferayFilterClassName);

			for (LiferayFilter liferayFilter : liferayFilters) {
				liferayFilter.setFilterEnabled(filterEnabled);
			}
		}
	}

	protected void destroyServices(String servletContextName) throws Exception {
		Map<String, ServiceBag> serviceBags = _servicesContainer._serviceBags;

		for (Map.Entry<String, ServiceBag> entry : serviceBags.entrySet()) {
			String serviceType = entry.getKey();
			ServiceBag serviceBag = entry.getValue();

			Map<String, List<ServiceConstructor>> serviceConstructors =
				serviceBag._serviceConstructors;

			if (serviceConstructors.remove(servletContextName) == null) {
				continue;
			}

			Object serviceProxy = PortalBeanLocatorUtil.locate(serviceType);

			AdvisedSupport advisedSupport =
				ServiceBeanAopProxy.getAdvisedSupport(serviceProxy);

			Object previousService = serviceBag.getCustomService();

			TargetSource previousTargetSource = new SingletonTargetSource(
				previousService);

			advisedSupport.setTargetSource(previousTargetSource);
		}
	}

	protected void doInvokeDeploy(HotDeployEvent hotDeployEvent)
		throws Exception {

		ServletContext servletContext = hotDeployEvent.getServletContext();

		String servletContextName = servletContext.getServletContextName();

		if (_log.isDebugEnabled()) {
			_log.debug("Invoking deploy for " + servletContextName);
		}

		String xml = HttpUtil.URLtoString(
			servletContext.getResource("/WEB-INF/liferay-hook.xml"));

		if (xml == null) {
			return;
		}

		if (isRTLHook(hotDeployEvent.getPluginPackage())) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Skipping RTL hook since it is now part of the portal");
			}

			HotDeployUtil.fireUndeployEvent(
				new HotDeployEvent(
					servletContext, hotDeployEvent.getContextClassLoader()));

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Registering hook for " + servletContextName);
		}

		_servletContextNames.add(servletContextName);

		Document document = UnsecureSAXReaderUtil.read(xml, true);

		Element rootElement = document.getRootElement();

		ClassLoader portletClassLoader = hotDeployEvent.getContextClassLoader();

		initPortalProperties(
			servletContextName, portletClassLoader, rootElement);

		initLanguageProperties(
			servletContextName, portletClassLoader, rootElement);

		try {
			initCustomJspDir(
				servletContext, servletContextName, portletClassLoader,
				hotDeployEvent.getPluginPackage(), rootElement);
		}
		catch (DuplicateCustomJspException dcje) {
			if (_log.isWarnEnabled()) {
				_log.warn(servletContextName + " will be undeployed");
			}

			HotDeployUtil.fireUndeployEvent(
				new HotDeployEvent(servletContext, portletClassLoader));

			DeployManagerUtil.undeploy(servletContextName);

			return;
		}

		initIndexerPostProcessors(
			servletContextName, portletClassLoader, rootElement);

		List<Element> serviceElements = rootElement.elements("service");

		for (Element serviceElement : serviceElements) {
			String serviceType = serviceElement.elementText("service-type");
			String serviceImpl = serviceElement.elementText("service-impl");

			if (!checkPermission(
					PACLConstants.PORTAL_HOOK_PERMISSION_SERVICE,
					portletClassLoader, serviceType,
					"Rejecting service " + serviceImpl)) {

				continue;
			}

			Class<?> serviceTypeClass = portletClassLoader.loadClass(
				serviceType);
			Class<?> serviceImplClass = portletClassLoader.loadClass(
				serviceImpl);

			Constructor<?> serviceImplConstructor =
				serviceImplClass.getConstructor(
					new Class<?>[] {serviceTypeClass});

			Object serviceProxy = PortalBeanLocatorUtil.locate(serviceType);

			if (ProxyUtil.isProxyClass(serviceProxy.getClass())) {
				initServices(
					servletContextName, portletClassLoader, serviceType,
					serviceTypeClass, serviceImplConstructor, serviceProxy);
			}
			else {
				_log.error(
					"Service hooks require Spring to be configured to use " +
						"JdkDynamicProxy and will not work with CGLIB");
			}
		}

		initServletFilters(
			servletContext, servletContextName, portletClassLoader,
			rootElement);

		initStrutsActions(servletContextName, portletClassLoader, rootElement);

		// Begin backwards compatibility for 5.1.0

		ModelListenersContainer modelListenersContainer =
			_modelListenersContainerMap.get(servletContextName);

		if (modelListenersContainer == null) {
			modelListenersContainer = new ModelListenersContainer();

			_modelListenersContainerMap.put(
				servletContextName, modelListenersContainer);
		}

		List<Element> modelListenerElements = rootElement.elements(
			"model-listener");

		for (Element modelListenerElement : modelListenerElements) {
			String modelName = modelListenerElement.elementText("model-name");
			String modelListenerClassName = modelListenerElement.elementText(
				"model-listener-class");

			ModelListener<BaseModel<?>> modelListener = initModelListener(
				servletContextName, portletClassLoader, modelName,
				modelListenerClassName);

			if (modelListener != null) {
				modelListenersContainer.registerModelListener(
					modelName, modelListener);
			}
		}

		EventsContainer eventsContainer = _eventsContainerMap.get(
			servletContextName);

		if (eventsContainer == null) {
			eventsContainer = new EventsContainer();

			_eventsContainerMap.put(servletContextName, eventsContainer);
		}

		List<Element> eventElements = rootElement.elements("event");

		for (Element eventElement : eventElements) {
			String eventName = eventElement.elementText("event-type");
			String eventClassName = eventElement.elementText("event-class");

			Object obj = initEvent(
				eventName, eventClassName, portletClassLoader);

			if (obj != null) {
				eventsContainer.registerEvent(eventName, obj);
			}
		}

		// End backwards compatibility for 5.1.0

		registerClpMessageListeners(servletContext, portletClassLoader);

		DirectServletRegistryUtil.clearServlets();
		FileAvailabilityUtil.reset();

		if (_log.isInfoEnabled()) {
			_log.info(
				"Hook for " + servletContextName + " is available for use");
		}
	}

	protected void doInvokeUndeploy(HotDeployEvent hotDeployEvent)
		throws Exception {

		ServletContext servletContext = hotDeployEvent.getServletContext();

		String servletContextName = servletContext.getServletContextName();

		if (_log.isDebugEnabled()) {
			_log.debug("Invoking undeploy for " + servletContextName);
		}

		if (!_servletContextNames.remove(servletContextName)) {
			return;
		}

		AuthenticatorsContainer authenticatorsContainer =
			_authenticatorsContainerMap.remove(servletContextName);

		if (authenticatorsContainer != null) {
			authenticatorsContainer.unregisterAuthenticators();
		}

		AuthFailuresContainer authFailuresContainer =
			_authFailuresContainerMap.remove(servletContextName);

		if (authFailuresContainer != null) {
			authFailuresContainer.unregisterAuthFailures();
		}

		AuthPublicPathsContainer authPublicPathsContainer =
			_authPublicPathsContainerMap.remove(servletContextName);

		if (authPublicPathsContainer != null) {
			authPublicPathsContainer.unregisterPaths();
		}

		AuthVerifierConfigurationContainer authVerifierConfigurationContainer =
			_authVerifierConfigurationContainerMap.remove(servletContextName);

		if (authVerifierConfigurationContainer != null) {
			authVerifierConfigurationContainer.unregisterConfigurations();
		}

		AutoDeployListenersContainer autoDeployListenersContainer =
			_autoDeployListenersContainerMap.remove(servletContextName);

		if (autoDeployListenersContainer != null) {
			autoDeployListenersContainer.unregisterAutoDeployListeners();
		}

		AutoLoginsContainer autoLoginsContainer =
			_autoLoginsContainerMap.remove(servletContextName);

		if (autoLoginsContainer != null) {
			autoLoginsContainer.unregisterAutoLogins();
		}

		CustomJspBag customJspBag = _customJspBagsMap.remove(
			servletContextName);

		if (customJspBag != null) {
			destroyCustomJspBag(servletContextName, customJspBag);
		}

		EventsContainer eventsContainer = _eventsContainerMap.remove(
			servletContextName);

		if (eventsContainer != null) {
			eventsContainer.unregisterEvents();
		}

		HotDeployListenersContainer hotDeployListenersContainer =
			_hotDeployListenersContainerMap.remove(servletContextName);

		if (hotDeployListenersContainer != null) {
			hotDeployListenersContainer.unregisterHotDeployListeners();
		}

		IndexerPostProcessorContainer indexerPostProcessorContainer =
			_indexerPostProcessorContainerMap.remove(servletContextName);

		if (indexerPostProcessorContainer != null) {
			indexerPostProcessorContainer.unregisterIndexerPostProcessor();
		}

		LanguagesContainer languagesContainer = _languagesContainerMap.remove(
			servletContextName);

		if (languagesContainer != null) {
			languagesContainer.unregisterLanguages();
		}

		ModelListenersContainer modelListenersContainer =
			_modelListenersContainerMap.remove(servletContextName);

		if (modelListenersContainer != null) {
			modelListenersContainer.unregisterModelListeners(
				servletContextName);
		}

		Properties portalProperties = _portalPropertiesMap.remove(
			servletContextName);

		if (portalProperties != null) {
			destroyPortalProperties(servletContextName, portalProperties);
		}

		destroyServices(servletContextName);

		ServletFiltersContainer servletFiltersContainer =
			_servletFiltersContainerMap.remove(servletContextName);

		if (servletFiltersContainer != null) {
			servletFiltersContainer.unregisterFilterMappings();
		}

		StrutsActionsContainer strutsActionContainer =
			_strutsActionsContainerMap.remove(servletContextName);

		if (strutsActionContainer != null) {
			strutsActionContainer.unregisterStrutsActions();
		}

		unregisterClpMessageListeners(servletContext);

		if (_log.isInfoEnabled()) {
			_log.info("Hook for " + servletContextName + " was unregistered");
		}
	}

	protected void getCustomJsps(
		ServletContext servletContext, String webDir, String resourcePath,
		List<String> customJsps) {

		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcePath);

		if ((resourcePaths == null) || resourcePaths.isEmpty()) {
			return;
		}

		for (String curResourcePath : resourcePaths) {
			if (curResourcePath.endsWith(StringPool.SLASH)) {
				getCustomJsps(
					servletContext, webDir, curResourcePath, customJsps);
			}
			else {
				String customJsp = webDir + curResourcePath;

				customJsp = StringUtil.replace(
					customJsp, StringPool.DOUBLE_SLASH, StringPool.SLASH);

				customJsps.add(customJsp);
			}
		}
	}

	protected Locale getLocale(String languagePropertiesLocation) {
		int x = languagePropertiesLocation.indexOf(CharPool.UNDERLINE);
		int y = languagePropertiesLocation.indexOf(".properties");

		Locale locale = null;

		if ((x != -1) && (y != 1)) {
			String localeKey = languagePropertiesLocation.substring(x + 1, y);

			locale = LocaleUtil.fromLanguageId(localeKey, true, false);
		}

		return locale;
	}

	protected BasePersistence<?> getPersistence(
		String servletContextName, String modelName) {

		int pos = modelName.lastIndexOf(CharPool.PERIOD);

		String entityName = modelName.substring(pos + 1);

		pos = modelName.lastIndexOf(".model.");

		String packagePath = modelName.substring(0, pos);

		String beanName =
			packagePath + ".service.persistence." + entityName + "Persistence";

		try {
			return (BasePersistence<?>)PortalBeanLocatorUtil.locate(beanName);
		}
		catch (BeanLocatorException ble) {
			return (BasePersistence<?>)PortletBeanLocatorUtil.locate(
				servletContextName, beanName);
		}
	}

	protected String getPortalJsp(String customJsp, String customJspDir) {
		if (Validator.isNull(customJsp) || Validator.isNull(customJspDir)) {
			return null;
		}

		int pos = customJsp.indexOf(customJspDir);

		return customJsp.substring(pos + customJspDir.length());
	}

	protected File getPortalJspBackupFile(File portalJspFile) {
		String fileName = portalJspFile.getName();
		String filePath = portalJspFile.toString();

		int fileNameIndex = fileName.lastIndexOf(CharPool.PERIOD);

		if (fileNameIndex > 0) {
			int filePathIndex = filePath.lastIndexOf(fileName);

			fileName =
				fileName.substring(0, fileNameIndex) + ".portal" +
					fileName.substring(fileNameIndex);

			filePath = filePath.substring(0, filePathIndex) + fileName;
		}
		else {
			filePath += ".portal";
		}

		return new File(filePath);
	}

	protected void initAuthenticators(
			ClassLoader portletClassLoader, Properties portalProperties,
			String key, AuthenticatorsContainer authenticatorsContainer)
		throws Exception {

		String[] authenticatorClassNames = StringUtil.split(
			portalProperties.getProperty(key));

		for (String authenticatorClassName : authenticatorClassNames) {
			Authenticator authenticator = (Authenticator)newInstance(
				portletClassLoader, Authenticator.class,
				authenticatorClassName);

			authenticatorsContainer.registerAuthenticator(key, authenticator);
		}
	}

	protected void initAuthenticators(
			String servletContextName, ClassLoader portletClassLoader,
			Properties portalProperties)
		throws Exception {

		AuthenticatorsContainer authenticatorsContainer =
			new AuthenticatorsContainer();

		_authenticatorsContainerMap.put(
			servletContextName, authenticatorsContainer);

		initAuthenticators(
			portletClassLoader, portalProperties, AUTH_PIPELINE_PRE,
			authenticatorsContainer);
		initAuthenticators(
			portletClassLoader, portalProperties, AUTH_PIPELINE_POST,
			authenticatorsContainer);
	}

	protected void initAuthFailures(
			ClassLoader portletClassLoader, Properties portalProperties,
			String key, AuthFailuresContainer authFailuresContainer)
		throws Exception {

		String[] authFailureClassNames = StringUtil.split(
			portalProperties.getProperty(key));

		for (String authFailureClassName : authFailureClassNames) {
			AuthFailure authFailure = (AuthFailure)newInstance(
				portletClassLoader, AuthFailure.class, authFailureClassName);

			authFailuresContainer.registerAuthFailure(key, authFailure);
		}
	}

	protected void initAuthFailures(
			String servletContextName, ClassLoader portletClassLoader,
			Properties portalProperties)
		throws Exception {

		AuthFailuresContainer authFailuresContainer =
			new AuthFailuresContainer();

		_authFailuresContainerMap.put(
			servletContextName, authFailuresContainer);

		initAuthFailures(
			portletClassLoader, portalProperties, AUTH_FAILURE,
			authFailuresContainer);
		initAuthFailures(
			portletClassLoader, portalProperties, AUTH_MAX_FAILURES,
			authFailuresContainer);
	}

	protected void initAuthPublicPaths(
			String servletContextName, Properties portalProperties)
		throws Exception {

		AuthPublicPathsContainer authPublicPathsContainer =
			new AuthPublicPathsContainer();

		_authPublicPathsContainerMap.put(
			servletContextName, authPublicPathsContainer);

		String[] publicPaths = StringUtil.split(
			portalProperties.getProperty(AUTH_PUBLIC_PATHS));

		authPublicPathsContainer.registerPaths(publicPaths);
	}

	protected void initAuthVerifiers(
			String servletContextName, ClassLoader portletClassLoader,
			Properties portalProperties)
		throws Exception {

		AuthVerifierConfigurationContainer authVerifierConfigurationContainer =
			new AuthVerifierConfigurationContainer();

		_authVerifierConfigurationContainerMap.put(
			servletContextName, authVerifierConfigurationContainer);

		String[] authVerifierClassNames = StringUtil.split(
			portalProperties.getProperty(AUTH_VERIFIER_PIPELINE));

		for (String authVerifierClassName : authVerifierClassNames) {
			AuthVerifierConfiguration authVerifierConfiguration =
				new AuthVerifierConfiguration();

			AuthVerifier authVerifier = (AuthVerifier)newInstance(
				portletClassLoader, AuthVerifier.class, authVerifierClassName);

			authVerifierConfiguration.setAuthVerifier(authVerifier);

			authVerifierConfiguration.setAuthVerifierClassName(
				authVerifierClassName);

			Properties properties = PropertiesUtil.getProperties(
				portalProperties,
				AuthVerifierPipeline.getAuthVerifierPropertyName(
					authVerifierClassName),
				true);

			authVerifierConfiguration.setProperties(properties);

			authVerifierConfigurationContainer.
				registerAuthVerifierConfiguration(authVerifierConfiguration);
		}
	}

	protected void initAutoDeployListeners(
			String servletContextName, ClassLoader portletClassLoader,
			Properties portalProperties)
		throws Exception {

		String[] autoDeployListenerClassNames = StringUtil.split(
			portalProperties.getProperty(PropsKeys.AUTO_DEPLOY_LISTENERS));

		if (autoDeployListenerClassNames.length == 0) {
			return;
		}

		AutoDeployListenersContainer autoDeployListenersContainer =
			new AutoDeployListenersContainer();

		_autoDeployListenersContainerMap.put(
			servletContextName, autoDeployListenersContainer);

		for (String autoDeployListenerClassName :
				autoDeployListenerClassNames) {

			AutoDeployListener autoDeployListener =
				(AutoDeployListener)newInstance(
					portletClassLoader, AutoDeployListener.class,
					autoDeployListenerClassName);

			autoDeployListenersContainer.registerAutoDeployListener(
				autoDeployListener);
		}
	}

	protected void initAutoLogins(
			String servletContextName, ClassLoader portletClassLoader,
			Properties portalProperties)
		throws Exception {

		AutoLoginsContainer autoLoginsContainer = new AutoLoginsContainer();

		_autoLoginsContainerMap.put(servletContextName, autoLoginsContainer);

		String[] autoLoginClassNames = StringUtil.split(
			portalProperties.getProperty(AUTO_LOGIN_HOOKS));

		for (String autoLoginClassName : autoLoginClassNames) {
			AutoLogin autoLogin = (AutoLogin)newInstance(
				portletClassLoader, AutoLogin.class, autoLoginClassName);

			autoLoginsContainer.registerAutoLogin(autoLogin);
		}
	}

	protected void initCustomJspBag(
			String servletContextName, String displayName,
			CustomJspBag customJspBag)
		throws Exception {

		String customJspDir = customJspBag.getCustomJspDir();
		boolean customJspGlobal = customJspBag.isCustomJspGlobal();
		List<String> customJsps = customJspBag.getCustomJsps();

		String portalWebDir = PortalUtil.getPortalWebDir();

		for (String customJsp : customJsps) {
			String portalJsp = getPortalJsp(customJsp, customJspDir);

			if (customJspGlobal) {
				File portalJspFile = new File(portalWebDir + portalJsp);
				File portalJspBackupFile = getPortalJspBackupFile(
					portalJspFile);

				if (portalJspFile.exists() && !portalJspBackupFile.exists()) {
					FileUtil.copyFile(portalJspFile, portalJspBackupFile);
				}
			}
			else {
				portalJsp = CustomJspRegistryUtil.getCustomJspFileName(
					servletContextName, portalJsp);
			}

			FileUtil.copyFile(customJsp, portalWebDir + portalJsp);
		}

		if (!customJspGlobal) {
			CustomJspRegistryUtil.registerServletContextName(
				servletContextName, displayName);
		}
	}

	protected void initCustomJspDir(
			ServletContext servletContext, String servletContextName,
			ClassLoader portletClassLoader, PluginPackage pluginPackage,
			Element rootElement)
		throws Exception {

		String customJspDir = rootElement.elementText("custom-jsp-dir");

		if (Validator.isNull(customJspDir)) {
			return;
		}

		if (!checkPermission(
				PACLConstants.PORTAL_HOOK_PERMISSION_CUSTOM_JSP_DIR,
				portletClassLoader, null, "Rejecting custom JSP directory")) {

			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Custom JSP directory: " + customJspDir);
		}

		boolean customJspGlobal = GetterUtil.getBoolean(
			rootElement.elementText("custom-jsp-global"), true);

		List<String> customJsps = new ArrayList<String>();

		String webDir = servletContext.getRealPath(StringPool.SLASH);

		getCustomJsps(servletContext, webDir, customJspDir, customJsps);

		if (customJsps.isEmpty()) {
			return;
		}

		CustomJspBag customJspBag = new CustomJspBag(
			customJspDir, customJspGlobal, customJsps);

		if (_log.isDebugEnabled()) {
			StringBundler sb = new StringBundler(customJsps.size() * 2);

			sb.append("Custom JSP files:\n");

			for (int i = 0; i < customJsps.size(); i++) {
				String customJsp = customJsps.get(0);

				sb.append(customJsp);

				if ((i + 1) < customJsps.size()) {
					sb.append(StringPool.NEW_LINE);
				}
			}

			Log log = SanitizerLogWrapper.allowCRLF(_log);

			log.debug(sb.toString());
		}

		if (PropsValues.HOT_DEPLOY_HOOK_CUSTOM_JSP_VERIFICATION_ENABLED &&
			customJspGlobal && !_customJspBagsMap.isEmpty() &&
			!_customJspBagsMap.containsKey(servletContextName)) {

			verifyCustomJsps(servletContextName, customJspBag);
		}

		_customJspBagsMap.put(servletContextName, customJspBag);

		initCustomJspBag(
			servletContextName, pluginPackage.getName(), customJspBag);
	}

	protected Object initEvent(
			String eventName, String eventClassName,
			ClassLoader portletClassLoader)
		throws Exception {

		if (eventName.equals(APPLICATION_STARTUP_EVENTS)) {
			Class<?> clazz = portletClassLoader.loadClass(eventClassName);

			SimpleAction simpleAction = (SimpleAction)clazz.newInstance();

			simpleAction = new InvokerSimpleAction(
				simpleAction, portletClassLoader);

			Long companyId = CompanyThreadLocal.getCompanyId();

			try {
				long[] companyIds = PortalInstances.getCompanyIds();

				for (long curCompanyId : companyIds) {
					CompanyThreadLocal.setCompanyId(curCompanyId);

					simpleAction.run(
						new String[] {String.valueOf(curCompanyId)});
				}
			}
			finally {
				CompanyThreadLocal.setCompanyId(companyId);
			}

			return null;
		}

		if (_propsKeysEvents.contains(eventName)) {
			Class<?> clazz = portletClassLoader.loadClass(eventClassName);

			Action action = (Action)clazz.newInstance();

			action = new InvokerAction(action, portletClassLoader);

			EventsProcessorUtil.registerEvent(eventName, action);

			return action;
		}

		if (_propsKeysSessionEvents.contains(eventName)) {
			Class<?> clazz = portletClassLoader.loadClass(eventClassName);

			SessionAction sessionAction = (SessionAction)clazz.newInstance();

			sessionAction = new InvokerSessionAction(
				sessionAction, portletClassLoader);

			EventsProcessorUtil.registerEvent(eventName, sessionAction);

			return sessionAction;
		}

		return null;
	}

	protected void initEvents(
			String servletContextName, ClassLoader portletClassLoader,
			Properties portalProperties)
		throws Exception {

		EventsContainer eventsContainer = new EventsContainer();

		_eventsContainerMap.put(servletContextName, eventsContainer);

		for (Map.Entry<Object, Object> entry : portalProperties.entrySet()) {
			String key = (String)entry.getKey();

			if (!key.equals(APPLICATION_STARTUP_EVENTS) &&
				!_propsKeysEvents.contains(key) &&
				!_propsKeysSessionEvents.contains(key)) {

				continue;
			}

			String eventName = key;
			String[] eventClassNames = StringUtil.split(
				(String)entry.getValue());

			for (String eventClassName : eventClassNames) {
				Object obj = initEvent(
					eventName, eventClassName, portletClassLoader);

				if (obj == null) {
					continue;
				}

				eventsContainer.registerEvent(eventName, obj);
			}
		}
	}

	protected void initHotDeployListeners(
			String servletContextName, ClassLoader portletClassLoader,
			Properties portalProperties)
		throws Exception {

		String[] hotDeployListenerClassNames = StringUtil.split(
			portalProperties.getProperty(PropsKeys.HOT_DEPLOY_LISTENERS));

		if (hotDeployListenerClassNames.length == 0) {
			return;
		}

		HotDeployListenersContainer hotDeployListenersContainer =
			new HotDeployListenersContainer();

		_hotDeployListenersContainerMap.put(
			servletContextName, hotDeployListenersContainer);

		for (String hotDeployListenerClassName : hotDeployListenerClassNames) {
			HotDeployListener hotDeployListener =
				(HotDeployListener)newInstance(
					portletClassLoader, HotDeployListener.class,
					hotDeployListenerClassName);

			hotDeployListenersContainer.registerHotDeployListener(
				hotDeployListener);
		}
	}

	protected void initIndexerPostProcessors(
			String servletContextName, ClassLoader portletClassLoader,
			Element parentElement)
		throws Exception {

		IndexerPostProcessorContainer indexerPostProcessorContainer =
			_indexerPostProcessorContainerMap.get(servletContextName);

		if (indexerPostProcessorContainer == null) {
			indexerPostProcessorContainer = new IndexerPostProcessorContainer();

			_indexerPostProcessorContainerMap.put(
				servletContextName, indexerPostProcessorContainer);
		}

		List<Element> indexerPostProcessorElements = parentElement.elements(
			"indexer-post-processor");

		for (Element indexerPostProcessorElement :
				indexerPostProcessorElements) {

			String indexerClassName = indexerPostProcessorElement.elementText(
				"indexer-class-name");

			if (!checkPermission(
					PACLConstants.PORTAL_HOOK_PERMISSION_INDEXER,
					portletClassLoader, indexerClassName,
					"Rejecting indexer " + indexerClassName)) {

				continue;
			}

			String indexerPostProcessorImpl =
				indexerPostProcessorElement.elementText(
					"indexer-post-processor-impl");

			Indexer indexer = IndexerRegistryUtil.getIndexer(indexerClassName);

			if (indexer == null) {
				_log.error("No indexer for " + indexerClassName + " was found");

				continue;
			}

			IndexerPostProcessor indexerPostProcessor =
				(IndexerPostProcessor)InstanceFactory.newInstance(
					portletClassLoader, indexerPostProcessorImpl);

			indexer.registerIndexerPostProcessor(indexerPostProcessor);

			indexerPostProcessorContainer.registerIndexerPostProcessor(
				indexerClassName, indexerPostProcessor);
		}
	}

	protected void initLanguageProperties(
			String servletContextName, ClassLoader portletClassLoader,
			Element parentElement)
		throws Exception {

		LanguagesContainer languagesContainer = new LanguagesContainer();

		_languagesContainerMap.put(servletContextName, languagesContainer);

		List<Element> languagePropertiesElements = parentElement.elements(
			"language-properties");

		Map<String, String> baseLanguageMap = null;

		for (Element languagePropertiesElement : languagePropertiesElements) {
			Properties properties = null;

			String languagePropertiesLocation =
				languagePropertiesElement.getText();

			Locale locale = getLocale(languagePropertiesLocation);

			if (locale != null) {
				if (!checkPermission(
						PACLConstants.
							PORTAL_HOOK_PERMISSION_LANGUAGE_PROPERTIES_LOCALE,
						portletClassLoader, locale,
						"Rejecting locale " + locale)) {

					continue;
				}
			}

			try {
				URL url = portletClassLoader.getResource(
					languagePropertiesLocation);

				if (url == null) {
					continue;
				}

				InputStream is = url.openStream();

				properties = PropertiesUtil.load(is, StringPool.UTF8);

				is.close();
			}
			catch (Exception e) {
				_log.error("Unable to read " + languagePropertiesLocation, e);

				continue;
			}

			Map<String, String> languageMap = new HashMap<String, String>();

			if (baseLanguageMap != null) {
				languageMap.putAll(baseLanguageMap);
			}

			for (Map.Entry<Object, Object> entry : properties.entrySet()) {
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();

				value = LanguageResources.fixValue(value);

				languageMap.put(key, value);
			}

			if (locale != null) {
				languagesContainer.addLanguage(locale, languageMap);
			}
			else if (!languageMap.isEmpty()) {
				baseLanguageMap = languageMap;
			}
		}

		if (baseLanguageMap != null) {
			Locale locale = new Locale(StringPool.BLANK);

			languagesContainer.addLanguage(locale, baseLanguageMap);
		}
	}

	@SuppressWarnings("rawtypes")
	protected ModelListener<BaseModel<?>> initModelListener(
			String servletContextName, ClassLoader portletClassLoader,
			String modelName, String modelListenerClassName)
		throws Exception {

		ModelListener<BaseModel<?>> modelListener =
			(ModelListener<BaseModel<?>>)newInstance(
				portletClassLoader, ModelListener.class,
				modelListenerClassName);

		BasePersistence persistence = getPersistence(
			servletContextName, modelName);

		persistence.registerListener(modelListener);

		return modelListener;
	}

	protected void initModelListeners(
			String servletContextName, ClassLoader portletClassLoader,
			Properties portalProperties)
		throws Exception {

		ModelListenersContainer modelListenersContainer =
			new ModelListenersContainer();

		_modelListenersContainerMap.put(
			servletContextName, modelListenersContainer);

		for (Map.Entry<Object, Object> entry : portalProperties.entrySet()) {
			String key = (String)entry.getKey();

			if (!key.startsWith(VALUE_OBJECT_LISTENER)) {
				continue;
			}

			String modelName = key.substring(VALUE_OBJECT_LISTENER.length());

			String[] modelListenerClassNames = StringUtil.split(
				(String)entry.getValue());

			for (String modelListenerClassName : modelListenerClassNames) {
				ModelListener<BaseModel<?>> modelListener = initModelListener(
					servletContextName, portletClassLoader, modelName,
					modelListenerClassName);

				if (modelListener != null) {
					modelListenersContainer.registerModelListener(
						modelName, modelListener);
				}
			}
		}
	}

	protected void initPortalProperties(
			String servletContextName, ClassLoader portletClassLoader,
			Element parentElement)
		throws Exception {

		String portalPropertiesLocation = parentElement.elementText(
			"portal-properties");

		if (Validator.isNull(portalPropertiesLocation)) {
			return;
		}

		Configuration portalPropertiesConfiguration = null;

		try {
			String name = portalPropertiesLocation;

			int pos = name.lastIndexOf(".properties");

			if (pos != -1) {
				name = name.substring(0, pos);
			}

			portalPropertiesConfiguration =
				ConfigurationFactoryUtil.getConfiguration(
					portletClassLoader, name);
		}
		catch (Exception e) {
			_log.error("Unable to read " + portalPropertiesLocation, e);
		}

		if (portalPropertiesConfiguration == null) {
			return;
		}

		Properties portalProperties =
			portalPropertiesConfiguration.getProperties();

		if (portalProperties.isEmpty()) {
			return;
		}

		Set<Object> set = portalProperties.keySet();

		Iterator<Object> iterator = set.iterator();

		while (iterator.hasNext()) {
			String key = (String)iterator.next();

			if (!checkPermission(
					PACLConstants.PORTAL_HOOK_PERMISSION_PORTAL_PROPERTIES_KEY,
					portletClassLoader, key,
					"Rejecting portal.properties key " + key)) {

				iterator.remove();
			}
		}

		Properties unfilteredPortalProperties =
			(Properties)portalProperties.clone();

		portalProperties.remove(PropsKeys.RELEASE_INFO_BUILD_NUMBER);
		portalProperties.remove(PropsKeys.RELEASE_INFO_PREVIOUS_BUILD_NUMBER);
		portalProperties.remove(PropsKeys.UPGRADE_PROCESSES);

		_portalPropertiesMap.put(servletContextName, portalProperties);

		// Initialize properties, auto logins, model listeners, and events in
		// that specific order. Events have to be loaded last because they may
		// require model listeners to have been registered.

		initPortalProperties(
			servletContextName, portletClassLoader, portalProperties,
			unfilteredPortalProperties);
		initAuthFailures(
			servletContextName, portletClassLoader, portalProperties);
		initAutoDeployListeners(
			servletContextName, portletClassLoader, portalProperties);
		initAutoLogins(
			servletContextName, portletClassLoader, portalProperties);
		initAuthenticators(
			servletContextName, portletClassLoader, portalProperties);
		initAuthVerifiers(
			servletContextName, portletClassLoader, portalProperties);
		initHotDeployListeners(
			servletContextName, portletClassLoader, portalProperties);
		initModelListeners(
			servletContextName, portletClassLoader, portalProperties);
		initEvents(servletContextName, portletClassLoader, portalProperties);
	}

	protected void initPortalProperties(
			String servletContextName, ClassLoader portletClassLoader,
			Properties portalProperties, Properties unfilteredPortalProperties)
		throws Exception {

		PropsUtil.addProperties(portalProperties);

		if (_log.isDebugEnabled() && portalProperties.containsKey(LOCALES)) {
			_log.debug(
				"Portlet locales " + portalProperties.getProperty(LOCALES));
			_log.debug("Merged locales " + PropsUtil.get(LOCALES));
			_log.debug(
				"Merged locales array length " +
					PropsUtil.getArray(LOCALES).length);
		}

		for (String key : _PROPS_VALUES_OBSOLETE) {
			if (_log.isInfoEnabled() && portalProperties.contains(key)) {
				_log.info("Portal property \"" + key + "\" is obsolete");
			}
		}

		resetPortalProperties(servletContextName, portalProperties, true);

		if (portalProperties.containsKey(
				PropsKeys.ASSET_PUBLISHER_ASSET_ENTRY_QUERY_PROCESSORS)) {

			String[] assetQueryProcessorClassNames = StringUtil.split(
				portalProperties.getProperty(
					PropsKeys.ASSET_PUBLISHER_ASSET_ENTRY_QUERY_PROCESSORS));

			for (String assetQueryProcessorClassName :
					assetQueryProcessorClassNames) {

				AssetEntryQueryProcessor assetQueryProcessor =
					(AssetEntryQueryProcessor)newInstance(
						portletClassLoader, AssetEntryQueryProcessor.class,
						assetQueryProcessorClassName);

				AssetPublisherUtil.registerAssetQueryProcessor(
					assetQueryProcessorClassName, assetQueryProcessor);

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Registered asset query processor " +
							assetQueryProcessorClassName);
				}
			}
		}

		if (portalProperties.containsKey(PropsKeys.AUTH_PUBLIC_PATHS)) {
			initAuthPublicPaths(servletContextName, portalProperties);
		}

		if (portalProperties.containsKey(PropsKeys.AUTH_TOKEN_IMPL)) {
			String authTokenClassName = portalProperties.getProperty(
				PropsKeys.AUTH_TOKEN_IMPL);

			AuthToken authToken = (AuthToken)newInstance(
				portletClassLoader, AuthToken.class, authTokenClassName);

			AuthTokenWrapper authTokenWrapper =
				(AuthTokenWrapper)AuthTokenUtil.getAuthToken();

			authTokenWrapper.setAuthToken(authToken);
		}

		if (portalProperties.containsKey(PropsKeys.CAPTCHA_ENGINE_IMPL)) {
			String captchaClassName = portalProperties.getProperty(
				PropsKeys.CAPTCHA_ENGINE_IMPL);

			Captcha captcha = (Captcha)newInstance(
				portletClassLoader, Captcha.class, captchaClassName);

			CaptchaImpl captchaImpl = null;

			Captcha currentCaptcha = CaptchaUtil.getCaptcha();

			if (currentCaptcha instanceof DoPrivilegedBean) {
				DoPrivilegedBean doPrivilegedBean =
					(DoPrivilegedBean)currentCaptcha;

				captchaImpl = (CaptchaImpl)doPrivilegedBean.getActualBean();
			}
			else {
				captchaImpl = (CaptchaImpl)currentCaptcha;
			}

			captchaImpl.setCaptcha(captcha);
		}

		if (portalProperties.containsKey(
				PropsKeys.CONTROL_PANEL_DEFAULT_ENTRY_CLASS)) {

			String controlPanelEntryClassName = portalProperties.getProperty(
				PropsKeys.CONTROL_PANEL_DEFAULT_ENTRY_CLASS);

			ControlPanelEntry controlPanelEntry =
				(ControlPanelEntry)newInstance(
					portletClassLoader, ControlPanelEntry.class,
					controlPanelEntryClassName);

			DefaultControlPanelEntryFactory.setInstance(controlPanelEntry);
		}

		if (portalProperties.containsKey(PropsKeys.DL_FILE_ENTRY_PROCESSORS)) {
			String[] dlProcessorClassNames = StringUtil.split(
				portalProperties.getProperty(
					PropsKeys.DL_FILE_ENTRY_PROCESSORS));

			DLFileEntryProcessorContainer dlFileEntryProcessorContainer =
				new DLFileEntryProcessorContainer();

			_dlFileEntryProcessorContainerMap.put(
				servletContextName, dlFileEntryProcessorContainer);

			for (String dlProcessorClassName : dlProcessorClassNames) {
				DLProcessor dlProcessor =
					(DLProcessor)InstanceFactory.newInstance(
						portletClassLoader, dlProcessorClassName);

				dlProcessor = (DLProcessor)newInstance(
					portletClassLoader,
					ReflectionUtil.getInterfaces(
						dlProcessor, portletClassLoader),
					dlProcessorClassName);

				dlFileEntryProcessorContainer.registerDLProcessor(dlProcessor);
			}
		}

		if (portalProperties.containsKey(PropsKeys.DL_REPOSITORY_IMPL)) {
			String[] dlRepositoryClassNames = StringUtil.split(
				portalProperties.getProperty(PropsKeys.DL_REPOSITORY_IMPL));

			DLRepositoryContainer dlRepositoryContainer =
				new DLRepositoryContainer();

			_dlRepositoryContainerMap.put(
				servletContextName, dlRepositoryContainer);

			for (String dlRepositoryClassName : dlRepositoryClassNames) {
				RepositoryFactory repositoryFactory = new RepositoryFactoryImpl(
					dlRepositoryClassName, portletClassLoader);

				dlRepositoryContainer.registerRepositoryFactory(
					dlRepositoryClassName, repositoryFactory);
			}
		}

		if (portalProperties.containsKey(PropsKeys.DL_STORE_ANTIVIRUS_IMPL)) {
			String antivirusScannerClassName = portalProperties.getProperty(
				PropsKeys.DL_STORE_ANTIVIRUS_IMPL);

			AntivirusScanner antivirusScanner = (AntivirusScanner)newInstance(
				portletClassLoader, AntivirusScanner.class,
				antivirusScannerClassName);

			AntivirusScannerWrapper antivirusScannerWrapper =
				(AntivirusScannerWrapper)
					AntivirusScannerUtil.getAntivirusScanner();

			antivirusScannerWrapper.setAntivirusScanner(antivirusScanner);
		}

		if (portalProperties.containsKey(PropsKeys.DL_STORE_IMPL)) {
			String storeClassName = portalProperties.getProperty(
				PropsKeys.DL_STORE_IMPL);

			Store store = (Store)newInstance(
				portletClassLoader, Store.class, storeClassName);

			StoreFactory.setInstance(store);
		}

		if (portalProperties.containsKey(
				PropsKeys.LDAP_ATTRS_TRANSFORMER_IMPL)) {

			String attributesTransformerClassName =
				portalProperties.getProperty(
					PropsKeys.LDAP_ATTRS_TRANSFORMER_IMPL);

			AttributesTransformer attributesTransformer =
				(AttributesTransformer)newInstance(
					portletClassLoader, AttributesTransformer.class,
					attributesTransformerClassName);

			AttributesTransformerFactory.setInstance(attributesTransformer);
		}

		if (portalProperties.containsKey(LOCK_LISTENERS)) {
			LockListenerContainer lockListenerContainer =
				_lockListenerContainerMap.get(servletContextName);

			if (lockListenerContainer == null) {
				lockListenerContainer = new LockListenerContainer();

				_lockListenerContainerMap.put(
					servletContextName, lockListenerContainer);
			}

			String[] lockListenerClassNames = StringUtil.split(
				portalProperties.getProperty(LOCK_LISTENERS));

			for (String lockListenerClassName : lockListenerClassNames) {
				LockListener lockListener = (LockListener)newInstance(
					portletClassLoader, LockListener.class,
					lockListenerClassName);

				lockListenerContainer.registerLockListener(lockListener);
			}
		}

		if (portalProperties.containsKey(PropsKeys.MAIL_HOOK_IMPL)) {
			String mailHookClassName = portalProperties.getProperty(
				PropsKeys.MAIL_HOOK_IMPL);

			com.liferay.mail.util.Hook mailHook =
				(com.liferay.mail.util.Hook)newInstance(
					portletClassLoader, com.liferay.mail.util.Hook.class,
					mailHookClassName);

			com.liferay.mail.util.HookFactory.setInstance(mailHook);
		}

		if (portalProperties.containsKey(
				PropsKeys.MEMBERSHIP_POLICY_ORGANIZATIONS)) {

			String organizationMembershipPolicyClassName =
				portalProperties.getProperty(
					PropsKeys.MEMBERSHIP_POLICY_ORGANIZATIONS);

			OrganizationMembershipPolicyFactoryImpl
				organizationMembershipPolicyFactoryImpl =
					(OrganizationMembershipPolicyFactoryImpl)
						OrganizationMembershipPolicyFactoryUtil.
							getOrganizationMembershipPolicyFactory();

			OrganizationMembershipPolicy organizationMembershipPolicy =
				(OrganizationMembershipPolicy)newInstance(
					portletClassLoader, OrganizationMembershipPolicy.class,
					organizationMembershipPolicyClassName);

			organizationMembershipPolicyFactoryImpl.
				setOrganizationMembershipPolicy(organizationMembershipPolicy);

			if (PropsValues.MEMBERSHIP_POLICY_AUTO_VERIFY) {
				organizationMembershipPolicy.verifyPolicy();
			}
		}

		if (portalProperties.containsKey(PropsKeys.MEMBERSHIP_POLICY_ROLES)) {
			String roleMembershipPolicyClassName = portalProperties.getProperty(
				PropsKeys.MEMBERSHIP_POLICY_ROLES);

			RoleMembershipPolicyFactoryImpl roleMembershipPolicyFactoryImpl =
				(RoleMembershipPolicyFactoryImpl)
					RoleMembershipPolicyFactoryUtil.
						getRoleMembershipPolicyFactory();

			RoleMembershipPolicy roleMembershipPolicy =
				(RoleMembershipPolicy)newInstance(
					portletClassLoader, RoleMembershipPolicy.class,
					roleMembershipPolicyClassName);

			roleMembershipPolicyFactoryImpl.setRoleMembershipPolicy(
				roleMembershipPolicy);

			if (PropsValues.MEMBERSHIP_POLICY_AUTO_VERIFY) {
				roleMembershipPolicy.verifyPolicy();
			}
		}

		if (portalProperties.containsKey(PropsKeys.MEMBERSHIP_POLICY_SITES)) {
			String siteMembershipPolicyClassName = portalProperties.getProperty(
				PropsKeys.MEMBERSHIP_POLICY_SITES);

			SiteMembershipPolicyFactoryImpl siteMembershipPolicyFactoryImpl =
				(SiteMembershipPolicyFactoryImpl)
					SiteMembershipPolicyFactoryUtil.
						getSiteMembershipPolicyFactory();

			SiteMembershipPolicy siteMembershipPolicy =
				(SiteMembershipPolicy)newInstance(
					portletClassLoader, SiteMembershipPolicy.class,
					siteMembershipPolicyClassName);

			siteMembershipPolicyFactoryImpl.setSiteMembershipPolicy(
				siteMembershipPolicy);

			if (PropsValues.MEMBERSHIP_POLICY_AUTO_VERIFY) {
				siteMembershipPolicy.verifyPolicy();
			}
		}

		if (portalProperties.containsKey(
				PropsKeys.MEMBERSHIP_POLICY_USER_GROUPS)) {

			String userGroupMembershipPolicyClassName =
				portalProperties.getProperty(
					PropsKeys.MEMBERSHIP_POLICY_USER_GROUPS);

			UserGroupMembershipPolicyFactoryImpl
				userGroupMembershipPolicyFactoryImpl =
					(UserGroupMembershipPolicyFactoryImpl)
						UserGroupMembershipPolicyFactoryUtil.
							getUserGroupMembershipPolicyFactory();

			UserGroupMembershipPolicy userGroupMembershipPolicy =
				(UserGroupMembershipPolicy)newInstance(
					portletClassLoader, UserGroupMembershipPolicy.class,
					userGroupMembershipPolicyClassName);

			userGroupMembershipPolicyFactoryImpl.setUserGroupMembershipPolicy(
				userGroupMembershipPolicy);

			if (PropsValues.MEMBERSHIP_POLICY_AUTO_VERIFY) {
				userGroupMembershipPolicy.verifyPolicy();
			}
		}

		if (portalProperties.containsKey(PropsKeys.PASSWORDS_TOOLKIT)) {
			String toolkitClassName = portalProperties.getProperty(
				PropsKeys.PASSWORDS_TOOLKIT);

			Toolkit toolkit = (Toolkit)newInstance(
				portletClassLoader, Toolkit.class, toolkitClassName);

			ToolkitWrapper toolkitWrapper =
				(ToolkitWrapper)PwdToolkitUtil.getToolkit();

			toolkitWrapper.setToolkit(toolkit);
		}

		if (portalProperties.containsKey(PropsKeys.PHONE_NUMBER_FORMAT_IMPL)) {
			String phoneNumberFormatClassName = portalProperties.getProperty(
				PropsKeys.PHONE_NUMBER_FORMAT_IMPL);

			PhoneNumberFormat phoneNumberFormat =
				(PhoneNumberFormat)newInstance(
					portletClassLoader, PhoneNumberFormat.class,
					phoneNumberFormatClassName);

			PhoneNumberFormatWrapper phoneNumberFormatWrapper =
				(PhoneNumberFormatWrapper)
					PhoneNumberFormatUtil.getPhoneNumberFormat();

			phoneNumberFormatWrapper.setPhoneNumberFormat(phoneNumberFormat);
		}

		if (portalProperties.containsKey(PropsKeys.SANITIZER_IMPL)) {
			String[] sanitizerClassNames = StringUtil.split(
				portalProperties.getProperty(PropsKeys.SANITIZER_IMPL));

			SanitizerContainer sanitizerContainer = new SanitizerContainer();

			_sanitizerContainerMap.put(servletContextName, sanitizerContainer);

			for (String sanitizerClassName : sanitizerClassNames) {
				Sanitizer sanitizer = (Sanitizer)newInstance(
					portletClassLoader, Sanitizer.class, sanitizerClassName);

				sanitizerContainer.registerSanitizer(sanitizer);
			}
		}

		if (portalProperties.containsKey(
				PropsKeys.USERS_EMAIL_ADDRESS_GENERATOR)) {

			String emailAddressGeneratorClassName =
				portalProperties.getProperty(
					PropsKeys.USERS_EMAIL_ADDRESS_GENERATOR);

			EmailAddressGenerator emailAddressGenerator =
				(EmailAddressGenerator)newInstance(
					portletClassLoader, EmailAddressGenerator.class,
					emailAddressGeneratorClassName);

			EmailAddressGeneratorFactory.setInstance(emailAddressGenerator);
		}

		if (portalProperties.containsKey(
				PropsKeys.USERS_EMAIL_ADDRESS_VALIDATOR)) {

			String emailAddressValidatorClassName =
				portalProperties.getProperty(
					PropsKeys.USERS_EMAIL_ADDRESS_VALIDATOR);

			EmailAddressValidator emailAddressValidator =
				(EmailAddressValidator)newInstance(
					portletClassLoader, EmailAddressValidator.class,
					emailAddressValidatorClassName);

			EmailAddressValidatorFactory.setInstance(emailAddressValidator);
		}

		if (portalProperties.containsKey(PropsKeys.USERS_FULL_NAME_GENERATOR)) {
			String fullNameGeneratorClassName = portalProperties.getProperty(
				PropsKeys.USERS_FULL_NAME_GENERATOR);

			FullNameGenerator fullNameGenerator =
				(FullNameGenerator)newInstance(
					portletClassLoader, FullNameGenerator.class,
					fullNameGeneratorClassName);

			FullNameGeneratorFactory.setInstance(fullNameGenerator);
		}

		if (portalProperties.containsKey(PropsKeys.USERS_FULL_NAME_VALIDATOR)) {
			String fullNameValidatorClassName = portalProperties.getProperty(
				PropsKeys.USERS_FULL_NAME_VALIDATOR);

			FullNameValidator fullNameValidator =
				(FullNameValidator)newInstance(
					portletClassLoader, FullNameValidator.class,
					fullNameValidatorClassName);

			FullNameValidatorFactory.setInstance(fullNameValidator);
		}

		if (portalProperties.containsKey(
				PropsKeys.USERS_SCREEN_NAME_GENERATOR)) {

			String screenNameGeneratorClassName = portalProperties.getProperty(
				PropsKeys.USERS_SCREEN_NAME_GENERATOR);

			ScreenNameGenerator screenNameGenerator =
				(ScreenNameGenerator)newInstance(
					portletClassLoader, ScreenNameGenerator.class,
					screenNameGeneratorClassName);

			ScreenNameGeneratorFactory.setInstance(screenNameGenerator);
		}

		if (portalProperties.containsKey(
				PropsKeys.USERS_SCREEN_NAME_VALIDATOR)) {

			String screenNameValidatorClassName = portalProperties.getProperty(
				PropsKeys.USERS_SCREEN_NAME_VALIDATOR);

			ScreenNameValidator screenNameValidator =
				(ScreenNameValidator)newInstance(
					portletClassLoader, ScreenNameValidator.class,
					screenNameValidatorClassName);

			ScreenNameValidatorFactory.setInstance(screenNameValidator);
		}

		Set<String> liferayFilterClassNames =
			LiferayFilterTracker.getClassNames();

		for (String liferayFilterClassName : liferayFilterClassNames) {
			if (!portalProperties.containsKey(liferayFilterClassName)) {
				continue;
			}

			boolean filterEnabled = GetterUtil.getBoolean(
				portalProperties.getProperty(liferayFilterClassName));

			Set<LiferayFilter> liferayFilters =
				LiferayFilterTracker.getLiferayFilters(liferayFilterClassName);

			for (LiferayFilter liferayFilter : liferayFilters) {
				liferayFilter.setFilterEnabled(filterEnabled);
			}
		}

		if (unfilteredPortalProperties.containsKey(
				PropsKeys.RELEASE_INFO_BUILD_NUMBER) ||
			unfilteredPortalProperties.containsKey(
				PropsKeys.UPGRADE_PROCESSES)) {

			updateRelease(
				servletContextName, portletClassLoader,
				unfilteredPortalProperties);
		}
	}

	protected void initServices(
			String servletContextName, ClassLoader portletClassLoader,
			String serviceType, Class<?> serviceTypeClass,
			Constructor<?> serviceImplConstructor, Object serviceProxy)
		throws Exception {

		AdvisedSupport advisedSupport = ServiceBeanAopProxy.getAdvisedSupport(
			serviceProxy);

		TargetSource targetSource = advisedSupport.getTargetSource();

		Object previousService = targetSource.getTarget();

		if (ProxyUtil.isProxyClass(previousService.getClass())) {
			InvocationHandler invocationHandler =
				ProxyUtil.getInvocationHandler(previousService);

			if (invocationHandler instanceof ClassLoaderBeanHandler) {
				ClassLoaderBeanHandler classLoaderBeanHandler =
					(ClassLoaderBeanHandler)invocationHandler;

				previousService = classLoaderBeanHandler.getBean();
			}
		}

		if (!(previousService instanceof ServiceWrapper)) {
			ClassLoader portalClassLoader =
				PortalClassLoaderUtil.getClassLoader();

			previousService = ProxyUtil.newProxyInstance(
				portalClassLoader, new Class<?>[] {serviceTypeClass},
				new ClassLoaderBeanHandler(previousService, portalClassLoader));
		}

		Object nextService = serviceImplConstructor.newInstance(
			previousService);

		Object nextTarget = ProxyUtil.newProxyInstance(
			portletClassLoader, new Class<?>[] {serviceTypeClass},
			new ClassLoaderBeanHandler(nextService, portletClassLoader));

		TargetSource nextTargetSource = new SingletonTargetSource(nextTarget);

		advisedSupport.setTargetSource(nextTargetSource);

		_servicesContainer.addServiceBag(
			servletContextName, portletClassLoader, serviceType,
			serviceTypeClass, serviceImplConstructor, previousService);

		ServiceBeanAopCacheManagerUtil.reset();
	}

	protected Filter initServletFilter(
			String filterClassName, ClassLoader portletClassLoader)
		throws Exception {

		Filter filter = (Filter)InstanceFactory.newInstance(
			portletClassLoader, filterClassName);

		List<Class<?>> interfaces = new ArrayList<Class<?>>();

		if (filter instanceof TryFilter) {
			interfaces.add(TryFilter.class);
		}

		if (filter instanceof TryFinallyFilter) {
			interfaces.add(TryFinallyFilter.class);
		}

		if (filter instanceof WrapHttpServletRequestFilter) {
			interfaces.add(WrapHttpServletRequestFilter.class);
		}

		if (filter instanceof WrapHttpServletResponseFilter) {
			interfaces.add(WrapHttpServletResponseFilter.class);
		}

		if (filter instanceof LiferayFilter) {
			interfaces.add(LiferayFilter.class);
		}
		else {
			interfaces.add(Filter.class);
		}

		filter = (Filter)ProxyUtil.newProxyInstance(
			portletClassLoader,
			interfaces.toArray(new Class[interfaces.size()]),
			new ClassLoaderBeanHandler(filter, portletClassLoader));

		return filter;
	}

	protected void initServletFilters(
			ServletContext servletContext, String servletContextName,
			ClassLoader portletClassLoader, Element parentElement)
		throws Exception {

		ServletFiltersContainer servletFiltersContainer =
			_servletFiltersContainerMap.get(servletContextName);

		if (servletFiltersContainer == null) {
			servletFiltersContainer = new ServletFiltersContainer();

			_servletFiltersContainerMap.put(
				servletContextName, servletFiltersContainer);
		}

		List<Element> servletFilterElements = parentElement.elements(
			"servlet-filter");

		if (!servletFilterElements.isEmpty() &&
			!checkPermission(
				PACLConstants.PORTAL_HOOK_PERMISSION_SERVLET_FILTERS,
				portletClassLoader, null, "Rejecting servlet filters")) {

			return;
		}

		for (Element servletFilterElement : servletFilterElements) {
			String servletFilterName = servletFilterElement.elementText(
				"servlet-filter-name");
			String servletFilterImpl = servletFilterElement.elementText(
				"servlet-filter-impl");

			List<Element> initParamElements = servletFilterElement.elements(
				"init-param");

			Map<String, String> initParameterMap =
				new HashMap<String, String>();

			for (Element initParamElement : initParamElements) {
				String paramName = initParamElement.elementText("param-name");
				String paramValue = initParamElement.elementText("param-value");

				initParameterMap.put(paramName, paramValue);
			}

			Filter filter = initServletFilter(
				servletFilterImpl, portletClassLoader);

			FilterConfig filterConfig = new InvokerFilterConfig(
				servletContext, servletFilterName, initParameterMap);

			filter.init(filterConfig);

			servletFiltersContainer.registerFilter(
				servletFilterName, filter, filterConfig);
		}

		List<Element> servletFilterMappingElements = parentElement.elements(
			"servlet-filter-mapping");

		for (Element servletFilterMappingElement :
				servletFilterMappingElements) {

			String servletFilterName = servletFilterMappingElement.elementText(
				"servlet-filter-name");
			String afterFilter = servletFilterMappingElement.elementText(
				"after-filter");
			String beforeFilter = servletFilterMappingElement.elementText(
				"before-filter");

			String positionFilterName = beforeFilter;
			boolean after = false;

			if (Validator.isNotNull(afterFilter)) {
				positionFilterName = afterFilter;
				after = true;
			}

			List<Element> urlPatternElements =
				servletFilterMappingElement.elements("url-pattern");

			List<String> urlPatterns = new ArrayList<String>();

			for (Element urlPatternElement : urlPatternElements) {
				String urlPattern = urlPatternElement.getTextTrim();

				urlPatterns.add(urlPattern);
			}

			List<Element> dispatcherElements =
				servletFilterMappingElement.elements("dispatcher");

			List<String> dispatchers = new ArrayList<String>();

			for (Element dispatcherElement : dispatcherElements) {
				String dispatcher = dispatcherElement.getTextTrim();

				dispatcher = StringUtil.toUpperCase(dispatcher);

				dispatchers.add(dispatcher);
			}

			servletFiltersContainer.registerFilterMapping(
				servletFilterName, urlPatterns, dispatchers, positionFilterName,
				after);
		}
	}

	protected Object initStrutsAction(
			String strutsActionClassName, ClassLoader portletClassLoader)
		throws Exception {

		Object strutsAction = InstanceFactory.newInstance(
			portletClassLoader, strutsActionClassName);

		if (strutsAction instanceof StrutsAction) {
			return ProxyUtil.newProxyInstance(
				portletClassLoader, new Class[] {StrutsAction.class},
				new ClassLoaderBeanHandler(strutsAction, portletClassLoader));
		}
		else {
			return ProxyUtil.newProxyInstance(
				portletClassLoader, new Class[] {StrutsPortletAction.class},
				new ClassLoaderBeanHandler(strutsAction, portletClassLoader));
		}
	}

	protected void initStrutsActions(
			String servletContextName, ClassLoader portletClassLoader,
			Element parentElement)
		throws Exception {

		StrutsActionsContainer strutsActionContainer =
			_strutsActionsContainerMap.get(servletContextName);

		if (strutsActionContainer == null) {
			strutsActionContainer = new StrutsActionsContainer();

			_strutsActionsContainerMap.put(
				servletContextName, strutsActionContainer);
		}

		List<Element> strutsActionElements = parentElement.elements(
			"struts-action");

		for (Element strutsActionElement : strutsActionElements) {
			String strutsActionPath = strutsActionElement.elementText(
				"struts-action-path");

			if (!checkPermission(
					PACLConstants.PORTAL_HOOK_PERMISSION_STRUTS_ACTION_PATH,
					portletClassLoader, strutsActionPath,
					"Rejecting struts action path " + strutsActionPath)) {

				continue;
			}

			String strutsActionImpl = strutsActionElement.elementText(
				"struts-action-impl");

			Object strutsAction = initStrutsAction(
				strutsActionImpl, portletClassLoader);

			strutsActionContainer.registerStrutsAction(
				strutsActionPath, strutsAction);
		}
	}

	protected boolean isRTLHook(PluginPackage pluginPackage) {
		String moduleGroupId = pluginPackage.getGroupId();

		if (!moduleGroupId.equals(_RTL_HOOK_MODULE_GROUP_ID)) {
			return false;
		}

		// LRDCOM-9735

		List<String> tags = pluginPackage.getTags();

		if (tags.contains(_RTL_HOOK_TAG)) {
			return true;
		}

		// LPS-43009

		String name = pluginPackage.getName();

		if (name.startsWith(_RTL_HOOK_NAME)) {
			return true;
		}

		return false;
	}

	protected void resetPortalProperties(
			String servletContextName, Properties portalProperties,
			boolean initPhase)
		throws Exception {

		for (String key : _PROPS_VALUES_BOOLEAN) {
			String fieldName = StringUtil.replace(
				StringUtil.toUpperCase(key), CharPool.PERIOD,
				CharPool.UNDERLINE);

			if (!containsKey(portalProperties, key)) {
				continue;
			}

			try {
				Field field = PropsValues.class.getField(fieldName);

				Boolean value = Boolean.valueOf(
					GetterUtil.getBoolean(PropsUtil.get(key)));

				field.setBoolean(null, value);
			}
			catch (Exception e) {
				_log.error(
					"Error setting field " + fieldName + ": " + e.getMessage());
			}
		}

		for (String key : _PROPS_VALUES_INTEGER) {
			String fieldName = StringUtil.replace(
				StringUtil.toUpperCase(key), CharPool.PERIOD,
				CharPool.UNDERLINE);

			if (!containsKey(portalProperties, key)) {
				continue;
			}

			try {
				Field field = PropsValues.class.getField(fieldName);

				Integer value = Integer.valueOf(
					GetterUtil.getInteger(PropsUtil.get(key)));

				field.setInt(null, value);
			}
			catch (Exception e) {
				_log.error(
					"Error setting field " + fieldName + ": " + e.getMessage());
			}
		}

		for (String key : _PROPS_VALUES_LONG) {
			String fieldName = StringUtil.replace(
				StringUtil.toUpperCase(key), CharPool.PERIOD,
				CharPool.UNDERLINE);

			if (!containsKey(portalProperties, key)) {
				continue;
			}

			try {
				Field field = PropsValues.class.getField(fieldName);

				Long value = Long.valueOf(
					GetterUtil.getLong(PropsUtil.get(key)));

				field.setLong(null, value);
			}
			catch (Exception e) {
				_log.error(
					"Error setting field " + fieldName + ": " + e.getMessage());
			}
		}

		for (String key : _PROPS_VALUES_STRING) {
			String fieldName = StringUtil.replace(
				StringUtil.toUpperCase(key), CharPool.PERIOD,
				CharPool.UNDERLINE);

			if (!containsKey(portalProperties, key)) {
				continue;
			}

			try {
				Field field = PropsValues.class.getField(fieldName);

				String value = GetterUtil.getString(PropsUtil.get(key));

				field.set(null, value);
			}
			catch (Exception e) {
				_log.error(
					"Error setting field " + fieldName + ": " + e.getMessage());
			}
		}

		resetPortalPropertiesStringArray(
			servletContextName, portalProperties, initPhase,
			_PROPS_VALUES_MERGE_STRING_ARRAY, _mergeStringArraysContainerMap);

		resetPortalPropertiesStringArray(
			servletContextName, portalProperties, initPhase,
			_PROPS_VALUES_OVERRIDE_STRING_ARRAY,
			_overrideStringArraysContainerMap);

		if (containsKey(portalProperties, LOCALES) ||
			containsKey(portalProperties, LOCALES_BETA)) {

			PropsValues.LOCALES = PropsUtil.getArray(LOCALES);

			LanguageUtil.init();
		}

		if (containsKey(portalProperties, LOCALES_ENABLED)) {
			PropsValues.LOCALES_ENABLED = PropsUtil.getArray(LOCALES_ENABLED);

			LanguageUtil.init();
		}

		if (containsKey(portalProperties, AUTH_TOKEN_IGNORE_ACTIONS)) {
			AuthTokenWhitelistUtil.resetPortletCSRFWhitelistActions();
		}

		if (containsKey(portalProperties, AUTH_TOKEN_IGNORE_ORIGINS)) {
			AuthTokenWhitelistUtil.resetOriginCSRFWhitelist();
		}

		if (containsKey(portalProperties, AUTH_TOKEN_IGNORE_PORTLETS)) {
			AuthTokenWhitelistUtil.resetPortletCSRFWhitelist();
		}

		if (containsKey(
				portalProperties,
				PORTLET_ADD_DEFAULT_RESOURCE_CHECK_WHITELIST)) {

			AuthTokenWhitelistUtil.resetPortletInvocationWhitelist();
		}

		if (containsKey(
				portalProperties,
				PORTLET_ADD_DEFAULT_RESOURCE_CHECK_WHITELIST_ACTIONS)) {

			AuthTokenWhitelistUtil.resetPortletInvocationWhitelistActions();
		}

		if (containsKey(
				portalProperties, PORTLET_INTERRUPTED_REQUEST_WHITELIST)) {

			InterruptedPortletRequestWhitelistUtil.
				resetPortletInvocationWhitelist();
		}

		if (containsKey(
				portalProperties,
				PORTLET_INTERRUPTED_REQUEST_WHITELIST_ACTIONS)) {

			InterruptedPortletRequestWhitelistUtil.
				resetPortletInvocationWhitelistActions();
		}

		CacheUtil.clearCache();

		JavaScriptBundleUtil.clearCache();
	}

	protected void resetPortalPropertiesStringArray(
		String servletContextName, Properties portalProperties,
		boolean initPhase, String[] propsValuesStringArray,
		Map<String, StringArraysContainer> stringArraysContainerMap) {

		for (String key : propsValuesStringArray) {
			String fieldName = StringUtil.replace(
				StringUtil.toUpperCase(key), CharPool.PERIOD,
				CharPool.UNDERLINE);

			if (!containsKey(portalProperties, key)) {
				continue;
			}

			try {
				resetPortalPropertiesStringArray(
					servletContextName, portalProperties, initPhase,
					propsValuesStringArray, stringArraysContainerMap, key,
					fieldName);
			}
			catch (Exception e) {
				_log.error(
					"Error setting field " + fieldName + ": " + e.getMessage());
			}
		}
	}

	protected void resetPortalPropertiesStringArray(
			String servletContextName, Properties portalProperties,
			boolean initPhase, String[] propsValuesStringArray,
			Map<String, StringArraysContainer> stringArraysContainerMap,
			String key, String fieldName)
		throws Exception {

		Field field = PropsValues.class.getField(fieldName);

		StringArraysContainer stringArraysContainer =
			stringArraysContainerMap.get(key);

		String[] value = null;

		if (initPhase) {
			if (stringArraysContainer
					instanceof OverrideStringArraysContainer) {

				OverrideStringArraysContainer overrideStringArraysContainer =
					(OverrideStringArraysContainer)stringArraysContainer;

				if (overrideStringArraysContainer.isOverridden()) {
					_log.error("Error setting overridden field " + fieldName);

					return;
				}

				value = StringUtil.split(portalProperties.getProperty(key));
			}
			else {
				value = PropsUtil.getArray(key);
			}
		}

		stringArraysContainer.setPluginStringArray(servletContextName, value);

		value = stringArraysContainer.getStringArray();

		field.set(null, value);

		if (key.equals(PropsKeys.LAYOUT_TYPES)) {
			Map<String, LayoutSettings> layoutSettingsMap =
				LayoutSettings.getLayoutSettingsMap();

			Set<Map.Entry<String, LayoutSettings>> set =
				layoutSettingsMap.entrySet();

			Iterator<Map.Entry<String, LayoutSettings>> iterator =
				set.iterator();

			while (iterator.hasNext()) {
				Map.Entry<String, LayoutSettings> entry = iterator.next();

				String layoutType = entry.getKey();

				if (!layoutType.equals(LayoutConstants.TYPE_CONTROL_PANEL) &&
					!ArrayUtil.contains(value, layoutType)) {

					iterator.remove();
				}
			}

			for (String type : value) {
				if (!layoutSettingsMap.containsKey(type)) {
					LayoutSettings.addLayoutSetting(type);
				}
			}
		}
	}

	protected void updateRelease(
			String servletContextName, ClassLoader portletClassLoader,
			Properties unfilteredPortalProperties)
		throws Exception {

		int buildNumber = GetterUtil.getInteger(
			unfilteredPortalProperties.getProperty(
				PropsKeys.RELEASE_INFO_BUILD_NUMBER));

		if (buildNumber <= 0) {
			_log.error(
				"Skipping upgrade processes for " + servletContextName +
					" because \"release.info.build.number\" is not specified");

			return;
		}

		Release release = ReleaseLocalServiceUtil.fetchRelease(
			servletContextName);

		if (release == null) {
			int previousBuildNumber = GetterUtil.getInteger(
				unfilteredPortalProperties.getProperty(
					PropsKeys.RELEASE_INFO_PREVIOUS_BUILD_NUMBER),
				buildNumber);

			release = ReleaseLocalServiceUtil.addRelease(
				servletContextName, previousBuildNumber);
		}

		if (buildNumber == release.getBuildNumber()) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Skipping upgrade processes for " + servletContextName +
						" because it is already up to date");
			}
		}
		else if (buildNumber < release.getBuildNumber()) {
			throw new UpgradeException(
				"Skipping upgrade processes for " + servletContextName +
					" because you are trying to upgrade with an older version");
		}
		else {
			String[] upgradeProcessClassNames = StringUtil.split(
				unfilteredPortalProperties.getProperty(
					PropsKeys.UPGRADE_PROCESSES));

			boolean indexOnUpgrade = GetterUtil.getBoolean(
				unfilteredPortalProperties.getProperty(
					PropsKeys.INDEX_ON_UPGRADE),
				PropsValues.INDEX_ON_UPGRADE);

			UpgradeProcessUtil.upgradeProcess(
				release.getBuildNumber(), upgradeProcessClassNames,
				portletClassLoader, indexOnUpgrade);
		}

		ReleaseLocalServiceUtil.updateRelease(
			release.getReleaseId(), buildNumber, null, true);
	}

	protected void verifyCustomJsps(
			String servletContextName, CustomJspBag customJspBag)
		throws DuplicateCustomJspException {

		Set<String> customJsps = new HashSet<String>();

		for (String customJsp : customJspBag.getCustomJsps()) {
			String portalJsp = getPortalJsp(
				customJsp, customJspBag.getCustomJspDir());

			customJsps.add(portalJsp);
		}

		Map<String, String> conflictingCustomJsps =
			new HashMap<String, String>();

		for (Map.Entry<String, CustomJspBag> entry :
				_customJspBagsMap.entrySet()) {

			CustomJspBag currentCustomJspBag = entry.getValue();

			if (!currentCustomJspBag.isCustomJspGlobal()) {
				continue;
			}

			String currentServletContextName = entry.getKey();

			List<String> currentCustomJsps =
				currentCustomJspBag.getCustomJsps();

			for (String currentCustomJsp : currentCustomJsps) {
				String currentPortalJsp = getPortalJsp(
					currentCustomJsp, currentCustomJspBag.getCustomJspDir());

				if (customJsps.contains(currentPortalJsp)) {
					conflictingCustomJsps.put(
						currentPortalJsp, currentServletContextName);
				}
			}
		}

		if (conflictingCustomJsps.isEmpty()) {
			return;
		}

		_log.error(servletContextName + " conflicts with the installed hooks");

		if (_log.isDebugEnabled()) {
			Log log = SanitizerLogWrapper.allowCRLF(_log);

			StringBundler sb = new StringBundler(
				conflictingCustomJsps.size() * 4 + 2);

			sb.append("Colliding JSP files in ");
			sb.append(servletContextName);
			sb.append(StringPool.NEW_LINE);

			int i = 0;

			for (Map.Entry<String, String> entry :
					conflictingCustomJsps.entrySet()) {

				sb.append(entry.getKey());
				sb.append(" with ");
				sb.append(entry.getValue());

				if ((i + 1) < conflictingCustomJsps.size()) {
					sb.append(StringPool.NEW_LINE);
				}

				i++;
			}

			log.debug(sb.toString());
		}

		throw new DuplicateCustomJspException();
	}

	private static final String _RTL_HOOK_MODULE_GROUP_ID = "liferay";

	private static final String _RTL_HOOK_NAME = "RTL";

	private static final String _RTL_HOOK_TAG = "rtl";

	private static final String[] _PROPS_KEYS_EVENTS = {
		LOGIN_EVENTS_POST, LOGIN_EVENTS_PRE, LOGOUT_EVENTS_POST,
		LOGOUT_EVENTS_PRE, SERVLET_SERVICE_EVENTS_POST,
		SERVLET_SERVICE_EVENTS_PRE
	};

	private static final String[] _PROPS_KEYS_SESSION_EVENTS = {
		SERVLET_SESSION_CREATE_EVENTS, SERVLET_SESSION_DESTROY_EVENTS
	};

	private static final String[] _PROPS_VALUES_BOOLEAN = {
		"auth.forward.by.last.path", "captcha.check.portal.create_account",
		"dl.file.entry.drafts.enabled",
		"dl.file.entry.open.in.ms.office.manual.check.in.required",
		"field.enable.com.liferay.portal.model.Contact.birthday",
		"field.enable.com.liferay.portal.model.Contact.male",
		"field.enable.com.liferay.portal.model.Organization.status",
		"javascript.fast.load", "layout.template.cache.enabled",
		"layout.user.private.layouts.auto.create",
		"layout.user.private.layouts.enabled",
		"layout.user.private.layouts.power.user.required",
		"layout.user.public.layouts.auto.create",
		"layout.user.public.layouts.enabled",
		"layout.user.public.layouts.power.user.required",
		"login.create.account.allow.custom.password", "login.dialog.disabled",
		"my.sites.show.private.sites.with.no.layouts",
		"my.sites.show.public.sites.with.no.layouts",
		"my.sites.show.user.private.sites.with.no.layouts",
		"my.sites.show.user.public.sites.with.no.layouts",
		"portlet.add.default.resource.check.enabled", "rss.feeds.enabled",
		"session.store.password", "social.activity.sets.bundling.enabled",
		"social.activity.sets.enabled", "terms.of.use.required",
		"theme.css.fast.load", "theme.images.fast.load",
		"theme.jsp.override.enabled", "theme.loader.new.theme.id.on.import",
		"theme.portlet.decorate.default", "theme.portlet.sharing.default",
		"user.notification.event.confirmation.enabled",
		"users.email.address.required", "users.screen.name.always.autogenerate"
	};

	private static final String[] _PROPS_VALUES_INTEGER = {
		"session.max.allowed", "users.image.max.height",
		"users.image.max.width",
	};

	private static final String[] _PROPS_VALUES_LONG = {
	};

	private static final String[] _PROPS_VALUES_MERGE_STRING_ARRAY = {
		"asset.publisher.query.form.configuration", "auth.token.ignore.actions",
		"auth.token.ignore.origins", "auth.token.ignore.portlets",
		"admin.default.group.names", "admin.default.role.names",
		"admin.default.user.group.names", "asset.publisher.display.styles",
		"company.settings.form.authentication",
		"company.settings.form.configuration",
		"company.settings.form.identification",
		"company.settings.form.miscellaneous", "convert.processes",
		"dockbar.add.portlets", "journal.article.form.add",
		"journal.article.form.translate", "journal.article.form.update",
		"layout.form.add", "layout.form.update", "layout.set.form.update",
		"layout.static.portlets.all", "layout.types",
		"login.form.navigation.post", "login.form.navigation.pre",
		"organizations.form.add.identification", "organizations.form.add.main",
		"organizations.form.add.miscellaneous",
		"portlet.add.default.resource.check.whitelist",
		"portlet.add.default.resource.check.whitelist.actions",
		"portlet.interrupted.request.whitelist",
		"portlet.interrupted.request.whitelist.actions",
		"session.phishing.protected.attributes", "sites.form.add.advanced",
		"sites.form.add.main", "sites.form.add.seo",
		"sites.form.update.advanced", "sites.form.update.main",
		"sites.form.update.seo", "staging.xstream.class.whitelist",
		"users.form.add.identification", "users.form.add.main",
		"users.form.add.miscellaneous", "users.form.my.account.identification",
		"users.form.my.account.main", "users.form.my.account.miscellaneous",
		"users.form.update.identification", "users.form.update.main",
		"users.form.update.miscellaneous"
	};

	private static final String[] _PROPS_VALUES_OBSOLETE = {
		"layout.user.private.layouts.modifiable",
		"layout.user.public.layouts.modifiable"
	};

	private static final String[] _PROPS_VALUES_OVERRIDE_STRING_ARRAY = {
		"locales.beta"
	};

	private static final String[] _PROPS_VALUES_STRING = {
		"company.default.locale", "company.default.time.zone",
		"default.landing.page.path", "default.regular.color.scheme.id",
		"default.regular.theme.id", "default.wap.color.scheme.id",
		"default.wap.theme.id", "passwords.passwordpolicytoolkit.generator",
		"passwords.passwordpolicytoolkit.static",
		"phone.number.format.international.regexp",
		"phone.number.format.usa.regexp", "social.activity.sets.selector",
		"theme.shortcut.icon"
	};

	private static Log _log = LogFactoryUtil.getLog(
		HookHotDeployListener.class);

	private Map<String, AuthenticatorsContainer> _authenticatorsContainerMap =
		new HashMap<String, AuthenticatorsContainer>();
	private Map<String, AuthFailuresContainer> _authFailuresContainerMap =
		new HashMap<String, AuthFailuresContainer>();
	private Map<String, AuthPublicPathsContainer> _authPublicPathsContainerMap =
		new HashMap<String, AuthPublicPathsContainer>();
	private Map<String, AuthVerifierConfigurationContainer>
		_authVerifierConfigurationContainerMap =
			new HashMap<String, AuthVerifierConfigurationContainer>();
	private Map<String, AutoDeployListenersContainer>
		_autoDeployListenersContainerMap =
			new HashMap<String, AutoDeployListenersContainer>();
	private Map<String, AutoLoginsContainer> _autoLoginsContainerMap =
		new HashMap<String, AutoLoginsContainer>();
	private Map<String, CustomJspBag> _customJspBagsMap =
		new HashMap<String, CustomJspBag>();
	private Map<String, DLFileEntryProcessorContainer>
		_dlFileEntryProcessorContainerMap =
			new HashMap<String, DLFileEntryProcessorContainer>();
	private Map<String, DLRepositoryContainer> _dlRepositoryContainerMap =
		new HashMap<String, DLRepositoryContainer>();
	private Map<String, EventsContainer> _eventsContainerMap =
		new HashMap<String, EventsContainer>();
	private Map<String, HotDeployListenersContainer>
		_hotDeployListenersContainerMap =
			new HashMap<String, HotDeployListenersContainer>();
	private Map<String, IndexerPostProcessorContainer>
		_indexerPostProcessorContainerMap =
			new HashMap<String, IndexerPostProcessorContainer>();
	private Map<String, LanguagesContainer> _languagesContainerMap =
		new HashMap<String, LanguagesContainer>();
	private Map<String, LockListenerContainer> _lockListenerContainerMap =
		new HashMap<String, LockListenerContainer>();
	private Map<String, StringArraysContainer> _mergeStringArraysContainerMap =
		new HashMap<String, StringArraysContainer>();
	private Map<String, ModelListenersContainer> _modelListenersContainerMap =
		new HashMap<String, ModelListenersContainer>();
	private Map<String, StringArraysContainer>
		_overrideStringArraysContainerMap =
			new HashMap<String, StringArraysContainer>();
	private Map<String, Properties> _portalPropertiesMap =
		new HashMap<String, Properties>();
	private Set<String> _propsKeysEvents = SetUtil.fromArray(
		_PROPS_KEYS_EVENTS);
	private Set<String> _propsKeysSessionEvents = SetUtil.fromArray(
		_PROPS_KEYS_SESSION_EVENTS);
	private Map<String, SanitizerContainer> _sanitizerContainerMap =
		new HashMap<String, SanitizerContainer>();
	private ServicesContainer _servicesContainer = new ServicesContainer();
	private Set<String> _servletContextNames = new HashSet<String>();
	private Map<String, ServletFiltersContainer> _servletFiltersContainerMap =
		new HashMap<String, ServletFiltersContainer>();
	private Map<String, StrutsActionsContainer> _strutsActionsContainerMap =
		new HashMap<String, StrutsActionsContainer>();

	private class AuthenticatorsContainer {

		public void registerAuthenticator(
			String key, Authenticator authenticator) {

			List<Authenticator> authenticators = _authenticators.get(key);

			if (authenticators == null) {
				authenticators = new ArrayList<Authenticator>();

				_authenticators.put(key, authenticators);
			}

			AuthPipeline.registerAuthenticator(key, authenticator);

			authenticators.add(authenticator);
		}

		public void unregisterAuthenticators() {
			for (Map.Entry<String, List<Authenticator>> entry :
					_authenticators.entrySet()) {

				String key = entry.getKey();
				List<Authenticator> authenticators = entry.getValue();

				for (Authenticator authenticator : authenticators) {
					AuthPipeline.unregisterAuthenticator(key, authenticator);
				}
			}
		}

		private Map<String, List<Authenticator>> _authenticators =
			new HashMap<String, List<Authenticator>>();

	}

	private class AuthFailuresContainer {

		public void registerAuthFailure(String key, AuthFailure authFailure) {
			List<AuthFailure> authFailures = _authFailures.get(key);

			if (authFailures == null) {
				authFailures = new ArrayList<AuthFailure>();

				_authFailures.put(key, authFailures);
			}

			AuthPipeline.registerAuthFailure(key, authFailure);

			authFailures.add(authFailure);
		}

		public void unregisterAuthFailures() {
			for (Map.Entry<String, List<AuthFailure>> entry :
					_authFailures.entrySet()) {

				String key = entry.getKey();
				List<AuthFailure> authFailures = entry.getValue();

				for (AuthFailure authFailure : authFailures) {
					AuthPipeline.unregisterAuthFailure(key, authFailure);
				}
			}
		}

		private Map<String, List<AuthFailure>> _authFailures =
			new HashMap<String, List<AuthFailure>>();

	}

	private class AuthPublicPathsContainer {

		public void registerPaths(String[] paths) {
			for (String path : paths) {
				_paths.add(path);
			}

			AuthPublicPathRegistry.register(paths);
		}

		public void unregisterPaths() {
			for (String path : _paths) {
				AuthPublicPathRegistry.unregister(path);
			}

			_paths.clear();
		}

		private Set<String> _paths = new HashSet<String>();

	}

	private class AuthVerifierConfigurationContainer {

		public void registerAuthVerifierConfiguration(
			AuthVerifierConfiguration authVerifierConfiguration) {

			AuthVerifierPipeline.register(authVerifierConfiguration);

			_authVerifierConfigurations.add(authVerifierConfiguration);
		}

		public void unregisterConfigurations() {
			for (AuthVerifierConfiguration authVerifierConfiguration :
					_authVerifierConfigurations) {

				AuthVerifierPipeline.unregister(authVerifierConfiguration);
			}
		}

		private List<AuthVerifierConfiguration> _authVerifierConfigurations =
			new ArrayList<AuthVerifierConfiguration>();

	}

	private class AutoDeployListenersContainer {

		public void registerAutoDeployListener(
			AutoDeployListener autoDeployListener) {

			AutoDeployDir autoDeployDir = AutoDeployUtil.getDir(
				AutoDeployDir.DEFAULT_NAME);

			if (autoDeployDir == null) {
				return;
			}

			autoDeployDir.registerListener(autoDeployListener);

			_autoDeployListeners.add(autoDeployListener);
		}

		public void unregisterAutoDeployListeners() {
			AutoDeployDir autoDeployDir = AutoDeployUtil.getDir(
				AutoDeployDir.DEFAULT_NAME);

			if (autoDeployDir == null) {
				return;
			}

			for (AutoDeployListener autoDeployListener : _autoDeployListeners) {
				autoDeployDir.unregisterListener(autoDeployListener);
			}
		}

		private List<AutoDeployListener> _autoDeployListeners =
			new ArrayList<AutoDeployListener>();

	}

	private class AutoLoginsContainer {

		public void registerAutoLogin(AutoLogin autoLogin) {
			AutoLoginFilter.registerAutoLogin(autoLogin);

			_autoLogins.add(autoLogin);
		}

		public void unregisterAutoLogins() {
			for (AutoLogin autoLogin : _autoLogins) {
				AutoLoginFilter.unregisterAutoLogin(autoLogin);
			}
		}

		private List<AutoLogin> _autoLogins = new ArrayList<AutoLogin>();

	}

	private class CustomJspBag {

		public CustomJspBag(
			String customJspDir, boolean customJspGlobal,
			List<String> customJsps) {

			_customJspDir = customJspDir;
			_customJspGlobal = customJspGlobal;
			_customJsps = customJsps;
		}

		public String getCustomJspDir() {
			return _customJspDir;
		}

		public List<String> getCustomJsps() {
			return _customJsps;
		}

		public boolean isCustomJspGlobal() {
			return _customJspGlobal;
		}

		private String _customJspDir;
		private boolean _customJspGlobal;
		private List<String> _customJsps;

	}

	private class DLFileEntryProcessorContainer {

		public void registerDLProcessor(DLProcessor dlProcessor) {
			DLProcessorRegistryUtil.register(dlProcessor);

			_dlProcessors.add(dlProcessor);
		}

		public void unregisterDLProcessors() {
			for (DLProcessor dlProcessor : _dlProcessors) {
				DLProcessorRegistryUtil.unregister(dlProcessor);
			}

			_dlProcessors.clear();
		}

		private List<DLProcessor> _dlProcessors = new ArrayList<DLProcessor>();

	}

	private class DLRepositoryContainer {

		public void registerRepositoryFactory(
			String className, RepositoryFactory repositoryFactory) {

			RepositoryFactoryUtil.registerRepositoryFactory(
				className, repositoryFactory);

			_classNames.add(className);
		}

		public void unregisterRepositoryFactories() {
			for (String className : _classNames) {
				RepositoryFactoryUtil.unregisterRepositoryFactory(className);
			}

			_classNames.clear();
		}

		private List<String> _classNames = new ArrayList<String>();

	}

	private class EventsContainer {

		public void registerEvent(String eventName, Object event) {
			List<Object> events = _eventsMap.get(eventName);

			if (events == null) {
				events = new ArrayList<Object>();

				_eventsMap.put(eventName, events);
			}

			events.add(event);
		}

		public void unregisterEvents() {
			for (Map.Entry<String, List<Object>> entry :
					_eventsMap.entrySet()) {

				String eventName = entry.getKey();
				List<Object> events = entry.getValue();

				for (Object event : events) {
					EventsProcessorUtil.unregisterEvent(eventName, event);
				}
			}
		}

		private Map<String, List<Object>> _eventsMap =
			new HashMap<String, List<Object>>();

	}

	private class HotDeployListenersContainer {

		public void registerHotDeployListener(
			HotDeployListener hotDeployListener) {

			HotDeployUtil.registerListener(hotDeployListener);

			_hotDeployListeners.add(hotDeployListener);
		}

		public void unregisterHotDeployListeners() {
			for (HotDeployListener hotDeployListener : _hotDeployListeners) {
				HotDeployUtil.unregisterListener(hotDeployListener);
			}
		}

		private List<HotDeployListener> _hotDeployListeners =
			new ArrayList<HotDeployListener>();

	}

	private class IndexerPostProcessorContainer {

		public void registerIndexerPostProcessor(
			String indexerClassName,
			IndexerPostProcessor indexerPostProcessor) {

			List<IndexerPostProcessor> indexerPostProcessors =
				_indexerPostProcessors.get(indexerClassName);

			if (indexerPostProcessors == null) {
				indexerPostProcessors = new ArrayList<IndexerPostProcessor>();

				_indexerPostProcessors.put(
					indexerClassName, indexerPostProcessors);
			}

			indexerPostProcessors.add(indexerPostProcessor);
		}

		public void unregisterIndexerPostProcessor() {
			for (Map.Entry<String, List<IndexerPostProcessor>> entry :
					_indexerPostProcessors.entrySet()) {

				String indexerClassName = entry.getKey();
				List<IndexerPostProcessor> indexerPostProcessors =
					entry.getValue();

				Indexer indexer = IndexerRegistryUtil.getIndexer(
					indexerClassName);

				for (IndexerPostProcessor indexerPostProcessor :
						indexerPostProcessors) {

					indexer.unregisterIndexerPostProcessor(
						indexerPostProcessor);
				}
			}
		}

		private Map<String, List<IndexerPostProcessor>> _indexerPostProcessors =
			new HashMap<String, List<IndexerPostProcessor>>();

	}

	private class LanguagesContainer {

		public void addLanguage(
			Locale locale, Map<String, String> languageMap) {

			Map<String, String> oldLanguageMap =
				LanguageResources.putLanguageMap(locale, languageMap);

			_languagesMap.put(locale, oldLanguageMap);
		}

		public void unregisterLanguages() {
			for (Map.Entry<Locale, Map<String, String>> entry :
					_languagesMap.entrySet()) {

				Locale locale = entry.getKey();
				Map<String, String> languageMap = entry.getValue();

				LanguageResources.putLanguageMap(locale, languageMap);
			}
		}

		private Map<Locale, Map<String, String>> _languagesMap =
			new HashMap<Locale, Map<String, String>>();

	}

	private class LockListenerContainer {

		public void registerLockListener(LockListener lockListener) {
			LockListenerRegistryUtil.register(lockListener);

			_lockListeners.add(lockListener);
		}

		public void unregisterLockListeners() {
			for (LockListener lockListener : _lockListeners) {
				LockListenerRegistryUtil.unregister(lockListener);
			}

			_lockListeners.clear();
		}

		private List<LockListener> _lockListeners =
			new ArrayList<LockListener>();

	}

	private class MergeStringArraysContainer implements StringArraysContainer {

		private MergeStringArraysContainer(String key) {
			_portalStringArray = PropsUtil.getArray(key);
		}

		@Override
		public String[] getStringArray() {
			List<String> mergedStringList = new UniqueList<String>();

			mergedStringList.addAll(ListUtil.fromArray(_portalStringArray));

			for (Map.Entry<String, String[]> entry :
					_pluginStringArrayMap.entrySet()) {

				String[] pluginStringArray = entry.getValue();

				mergedStringList.addAll(ListUtil.fromArray(pluginStringArray));
			}

			return mergedStringList.toArray(
				new String[mergedStringList.size()]);
		}

		@Override
		public void setPluginStringArray(
			String servletContextName, String[] pluginStringArray) {

			if (pluginStringArray != null) {
				_pluginStringArrayMap.put(
					servletContextName, pluginStringArray);
			}
			else {
				_pluginStringArrayMap.remove(servletContextName);
			}
		}

		private String[] _portalStringArray;
		private Map<String, String[]> _pluginStringArrayMap =
			new HashMap<String, String[]>();

	}

	private class ModelListenersContainer {

		public void registerModelListener(
			String modelName, ModelListener<BaseModel<?>> modelListener) {

			List<ModelListener<BaseModel<?>>> modelListeners =
				_modelListenersMap.get(modelName);

			if (modelListeners == null) {
				modelListeners = new ArrayList<ModelListener<BaseModel<?>>>();

				_modelListenersMap.put(modelName, modelListeners);
			}

			modelListeners.add(modelListener);
		}

		@SuppressWarnings("rawtypes")
		public void unregisterModelListeners(String servletContextName) {
			for (Map.Entry<String, List<ModelListener<BaseModel<?>>>> entry :
					_modelListenersMap.entrySet()) {

				String modelName = entry.getKey();
				List<ModelListener<BaseModel<?>>> modelListeners =
					entry.getValue();

				BasePersistence persistence = getPersistence(
					servletContextName, modelName);

				for (ModelListener<BaseModel<?>> modelListener :
						modelListeners) {

					persistence.unregisterListener(modelListener);
				}
			}
		}

		private Map<String, List<ModelListener<BaseModel<?>>>>
			_modelListenersMap =
				new HashMap<String, List<ModelListener<BaseModel<?>>>>();

	}

	private class OverrideStringArraysContainer
		implements StringArraysContainer {

		private OverrideStringArraysContainer(String key) {
			_portalStringArray = PropsUtil.getArray(key);
		}

		@Override
		public String[] getStringArray() {
			if (_pluginStringArray != null) {
				return _pluginStringArray;
			}

			return _portalStringArray;
		}

		public boolean isOverridden() {
			if (Validator.isNotNull(_servletContextName)) {
				return true;
			}
			else {
				return false;
			}
		}

		@Override
		public void setPluginStringArray(
			String servletContextName, String[] pluginStringArray) {

			if (pluginStringArray != null) {
				if (!isOverridden()) {
					_servletContextName = servletContextName;
					_pluginStringArray = pluginStringArray;
				}
			}
			else {
				if (_servletContextName.equals(servletContextName)) {
					_servletContextName = null;
					_pluginStringArray = null;
				}
			}
		}

		private String[] _pluginStringArray;
		private String[] _portalStringArray;
		private String _servletContextName;

	}

	private class SanitizerContainer {

		public void registerSanitizer(Sanitizer sanitizer) {
			_sanitizers.add(sanitizer);

			SanitizerImpl sanitizerImpl =
				(SanitizerImpl)SanitizerUtil.getSanitizer();

			sanitizerImpl.registerSanitizer(sanitizer);
		}

		public void unregisterSanitizers() {
			SanitizerImpl sanitizerImpl =
				(SanitizerImpl)SanitizerUtil.getSanitizer();

			for (Sanitizer sanitizer : _sanitizers) {
				sanitizerImpl.unregisterSanitizer(sanitizer);
			}
		}

		private List<Sanitizer> _sanitizers = new ArrayList<Sanitizer>();

	}

	private class ServiceBag {

		public ServiceBag(Object originalService) {
			_originalService = originalService;
		}

		public void addCustomServiceConstructor(
			String servletContextName, ClassLoader portletClassLoader,
			Class<?> serviceTypeClass, Constructor<?> serviceImplConstructor) {

			List<ServiceConstructor> serviceConstructors =
				_serviceConstructors.get(servletContextName);

			if (serviceConstructors == null) {
				serviceConstructors = new ArrayList<ServiceConstructor>();

				_serviceConstructors.put(
					servletContextName, serviceConstructors);
			}

			ServiceConstructor serviceConstructor = new ServiceConstructor(
				portletClassLoader, serviceTypeClass, serviceImplConstructor);

			serviceConstructors.add(serviceConstructor);
		}

		public Object getCustomService() throws Exception {
			List<ServiceConstructor> serviceConstructors =
				new ArrayList<ServiceConstructor>();

			for (Map.Entry<String, List<ServiceConstructor>> entry :
					_serviceConstructors.entrySet()) {

				serviceConstructors.addAll(entry.getValue());
			}

			Object customService = _originalService;

			for (ServiceConstructor serviceConstructor : serviceConstructors) {
				ClassLoader portletClassLoader =
					serviceConstructor._portletClassLoader;
				Class<?> serviceTypeClass =
					serviceConstructor._serviceTypeClass;
				Constructor<?> serviceImplConstructor =
					serviceConstructor._serviceImplConstructor;

				customService = serviceImplConstructor.newInstance(
					customService);

				customService = ProxyUtil.newProxyInstance(
					portletClassLoader, new Class<?>[] {serviceTypeClass},
					new ClassLoaderBeanHandler(
						customService, portletClassLoader));
			}

			if ((customService == _originalService) &&
				ProxyUtil.isProxyClass(customService.getClass())) {

				InvocationHandler invocationHandler =
					ProxyUtil.getInvocationHandler(customService);

				if (invocationHandler instanceof ClassLoaderBeanHandler) {
					ClassLoaderBeanHandler classLoaderBeanHandler =
						(ClassLoaderBeanHandler)invocationHandler;

					customService = classLoaderBeanHandler.getBean();
				}
			}

			return customService;
		}

		private Object _originalService;
		private Map<String, List<ServiceConstructor>> _serviceConstructors =
			new HashMap<String, List<ServiceConstructor>>();

	}

	private class ServiceConstructor {

		public ServiceConstructor(
			ClassLoader portletClassLoader, Class<?> serviceTypeClass,
			Constructor<?> serviceImplConstructor) {

			_portletClassLoader = portletClassLoader;
			_serviceTypeClass = serviceTypeClass;
			_serviceImplConstructor = serviceImplConstructor;
		}

		private ClassLoader _portletClassLoader;
		private Constructor<?> _serviceImplConstructor;
		private Class<?> _serviceTypeClass;

	}

	private class ServicesContainer {

		public void addServiceBag(
			String servletContextName, ClassLoader portletClassLoader,
			String serviceType, Class<?> serviceTypeClass,
			Constructor<?> serviceImplConstructor, Object wrappedService) {

			ServiceBag serviceBag = _serviceBags.get(serviceType);

			if (serviceBag == null) {
				serviceBag = new ServiceBag(wrappedService);

				_serviceBags.put(serviceType, serviceBag);
			}

			serviceBag.addCustomServiceConstructor(
				servletContextName, portletClassLoader, serviceTypeClass,
				serviceImplConstructor);
		}

		private Map<String, ServiceBag> _serviceBags =
			new HashMap<String, ServiceBag>();

	}

	private class ServletFiltersContainer {

		public InvokerFilterHelper getInvokerFilterHelper() {
			ServletContext portalServletContext = ServletContextPool.get(
				PortalUtil.getServletContextName());

			InvokerFilterHelper invokerFilterHelper =
				(InvokerFilterHelper)portalServletContext.getAttribute(
					InvokerFilterHelper.class.getName());

			return invokerFilterHelper;
		}

		public void registerFilter(
			String filterName, Filter filter, FilterConfig filterConfig) {

			InvokerFilterHelper invokerFilterHelper = getInvokerFilterHelper();

			Filter previousFilter = invokerFilterHelper.registerFilter(
				filterName, filter);

			_filterConfigs.put(filterName, filterConfig);
			_filters.put(filterName, previousFilter);
		}

		public void registerFilterMapping(
			String filterName, List<String> urlPatterns,
			List<String> dispatchers, String positionFilterName,
			boolean after) {

			InvokerFilterHelper invokerFilterHelper = getInvokerFilterHelper();

			Filter filter = invokerFilterHelper.getFilter(filterName);

			FilterConfig filterConfig = _filterConfigs.get(filterName);

			if (filterConfig == null) {
				filterConfig = invokerFilterHelper.getFilterConfig(filterName);
			}

			if (filter == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No filter exists with filter mapping " + filterName);
				}

				return;
			}

			FilterMapping filterMapping = new FilterMapping(
				filter, filterConfig, urlPatterns, dispatchers);

			invokerFilterHelper.registerFilterMapping(
				filterMapping, positionFilterName, after);
		}

		public void unregisterFilterMappings() {
			InvokerFilterHelper invokerFilterHelper = getInvokerFilterHelper();

			for (String filterName : _filters.keySet()) {
				Filter filter = _filters.get(filterName);

				Filter previousFilter = invokerFilterHelper.registerFilter(
					filterName, filter);

				previousFilter.destroy();
			}

			for (FilterMapping filterMapping : _filterMappings) {
				invokerFilterHelper.unregisterFilterMapping(filterMapping);

				filterMapping.setFilter(null);
			}
		}

		private Map<String, FilterConfig> _filterConfigs =
			new HashMap<String, FilterConfig>();
		private List<FilterMapping> _filterMappings =
			new ArrayList<FilterMapping>();
		private Map<String, Filter> _filters = new HashMap<String, Filter>();

	}

	private interface StringArraysContainer {

		public String[] getStringArray();

		public void setPluginStringArray(
			String servletContextName, String[] pluginStringArray);

	}

	private class StrutsActionsContainer {

		public void registerStrutsAction(String path, Object strutsAction) {
			if (strutsAction instanceof StrutsAction) {
				StrutsActionRegistryUtil.register(
					path, (StrutsAction)strutsAction);
			}
			else {
				StrutsActionRegistryUtil.register(
					path, (StrutsPortletAction)strutsAction);
			}

			_paths.add(path);
		}

		public void unregisterStrutsActions() {
			for (String path : _paths) {
				StrutsActionRegistryUtil.unregister(path);
			}

			_paths.clear();
		}

		private List<String> _paths = new ArrayList<String>();

	}

}