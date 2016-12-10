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

package com.liferay.portal.staging;

import com.liferay.portal.DuplicateLockException;
import com.liferay.portal.LARFileException;
import com.liferay.portal.LARFileSizeException;
import com.liferay.portal.LARTypeException;
import com.liferay.portal.LayoutPrototypeException;
import com.liferay.portal.LocaleException;
import com.liferay.portal.MissingReferenceException;
import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.NoSuchLayoutBranchException;
import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.NoSuchLayoutRevisionException;
import com.liferay.portal.PortletIdException;
import com.liferay.portal.RemoteExportException;
import com.liferay.portal.RemoteOptionsException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.lar.ExportImportHelperUtil;
import com.liferay.portal.kernel.lar.MissingReference;
import com.liferay.portal.kernel.lar.MissingReferences;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataException;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.lar.UserIdStrategy;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageStatus;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.servlet.ServletResponseConstants;
import com.liferay.portal.kernel.staging.LayoutStagingUtil;
import com.liferay.portal.kernel.staging.Staging;
import com.liferay.portal.kernel.staging.StagingConstants;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.DateRange;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.lar.backgroundtask.BackgroundTaskContextMapFactory;
import com.liferay.portal.lar.backgroundtask.LayoutRemoteStagingBackgroundTaskExecutor;
import com.liferay.portal.lar.backgroundtask.LayoutStagingBackgroundTaskExecutor;
import com.liferay.portal.lar.backgroundtask.PortletStagingBackgroundTaskExecutor;
import com.liferay.portal.messaging.LayoutsLocalPublisherRequest;
import com.liferay.portal.messaging.LayoutsRemotePublisherRequest;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutBranch;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.WorkflowInstanceLink;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.auth.RemoteAuthException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.BackgroundTaskLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutBranchLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutRevisionLocalServiceUtil;
import com.liferay.portal.service.LayoutServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LockLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.StagingLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.service.http.GroupServiceHttp;
import com.liferay.portal.service.permission.GroupPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.FileExtensionException;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.FileSizeException;
import com.liferay.portlet.layoutsadmin.lar.StagedTheme;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 * @author Bruno Farache
 * @author Wesley Gong
 * @author Zsolt Balogh
 */
@DoPrivileged
public class StagingImpl implements Staging {

	@Override
	public String buildRemoteURL(
		String remoteAddress, int remotePort, String remotePathContext,
		boolean secureConnection, long remoteGroupId, boolean privateLayout) {

		StringBundler sb = new StringBundler((remoteGroupId > 0) ? 4 : 9);

		if (secureConnection) {
			sb.append(Http.HTTPS_WITH_SLASH);
		}
		else {
			sb.append(Http.HTTP_WITH_SLASH);
		}

		sb.append(remoteAddress);

		if (remotePort > 0) {
			sb.append(StringPool.COLON);
			sb.append(remotePort);
		}

		if (Validator.isNotNull(remotePathContext)) {
			sb.append(remotePathContext);
		}

		if (remoteGroupId > 0) {
			sb.append("/c/my_sites/view?");
			sb.append("groupId=");
			sb.append(remoteGroupId);
			sb.append("&amp;privateLayout=");
			sb.append(privateLayout);
		}

		return sb.toString();
	}

	@Override
	public String buildRemoteURL(UnicodeProperties typeSettingsProperties) {
		String remoteAddress = typeSettingsProperties.getProperty(
			"remoteAddress");
		int remotePort = GetterUtil.getInteger(
			typeSettingsProperties.getProperty("remotePort"));
		String remotePathContext = typeSettingsProperties.getProperty(
			"remotePathContext");
		boolean secureConnection = GetterUtil.getBoolean(
			typeSettingsProperties.getProperty("secureConnection"));

		return buildRemoteURL(
			remoteAddress, remotePort, remotePathContext, secureConnection,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, false);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link StagingLocalServiceUtil#
	 *             checkDefaultLayoutSetBranches(long, Group, boolean, boolean,
	 *             boolean, ServiceContext))}
	 */
	@Deprecated
	@Override
	public void checkDefaultLayoutSetBranches(
			long userId, Group liveGroup, boolean branchingPublic,
			boolean branchingPrivate, boolean remote,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		StagingLocalServiceUtil.checkDefaultLayoutSetBranches(
			userId, liveGroup, branchingPublic, branchingPrivate, remote,
			serviceContext);
	}

	@Override
	public void copyFromLive(PortletRequest portletRequest) throws Exception {
		long stagingGroupId = ParamUtil.getLong(
			portletRequest, "stagingGroupId");

		Group stagingGroup = GroupLocalServiceUtil.getGroup(stagingGroupId);

		long liveGroupId = stagingGroup.getLiveGroupId();

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		publishLayouts(
			portletRequest, liveGroupId, stagingGroupId, parameterMap, false);
	}

	@Override
	public void copyFromLive(PortletRequest portletRequest, Portlet portlet)
		throws Exception {

		long plid = ParamUtil.getLong(portletRequest, "plid");

		Layout targetLayout = LayoutLocalServiceUtil.getLayout(plid);

		Group stagingGroup = targetLayout.getGroup();
		Group liveGroup = stagingGroup.getLiveGroup();

		Layout sourceLayout = LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
			targetLayout.getUuid(), liveGroup.getGroupId(),
			targetLayout.isPrivateLayout());

		copyPortlet(
			portletRequest, liveGroup.getGroupId(), stagingGroup.getGroupId(),
			sourceLayout.getPlid(), targetLayout.getPlid(),
			portlet.getPortletId());
	}

	@Override
	public void copyPortlet(
			PortletRequest portletRequest, long sourceGroupId,
			long targetGroupId, long sourcePlid, long targetPlid,
			String portletId)
		throws Exception {

		long userId = PortalUtil.getUserId(portletRequest);

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		DateRange dateRange = ExportImportHelperUtil.getDateRange(
			portletRequest, sourceGroupId, false, sourcePlid, portletId,
			"fromLastPublishDate");

		Map<String, Serializable> taskContextMap =
			BackgroundTaskContextMapFactory.buildTaskContextMap(
				userId, sourceGroupId, false, null, parameterMap,
				Constants.PUBLISH_TO_LIVE, dateRange.getStartDate(),
				dateRange.getEndDate(), StringPool.BLANK);

		taskContextMap.put("sourceGroupId", sourceGroupId);
		taskContextMap.put("sourcePlid", sourcePlid);
		taskContextMap.put("portletId", portletId);
		taskContextMap.put("targetGroupId", targetGroupId);
		taskContextMap.put("targetPlid", targetPlid);

		BackgroundTaskLocalServiceUtil.addBackgroundTask(
			userId, sourceGroupId, portletId, null,
			PortletStagingBackgroundTaskExecutor.class, taskContextMap,
			new ServiceContext());
	}

	@Override
	public void copyRemoteLayouts(
			long sourceGroupId, boolean privateLayout,
			Map<Long, Boolean> layoutIdMap, Map<String, String[]> parameterMap,
			String remoteAddress, int remotePort, String remotePathContext,
			boolean secureConnection, long remoteGroupId,
			boolean remotePrivateLayout, Date startDate, Date endDate)
		throws Exception {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		User user = permissionChecker.getUser();

		StringBundler sb = new StringBundler(4);

		if (secureConnection) {
			sb.append(Http.HTTPS_WITH_SLASH);
		}
		else {
			sb.append(Http.HTTP_WITH_SLASH);
		}

		sb.append(remoteAddress);
		sb.append(StringPool.COLON);
		sb.append(remotePort);
		sb.append(remotePathContext);

		String url = sb.toString();

		HttpPrincipal httpPrincipal = new HttpPrincipal(
			url, user.getEmailAddress(), user.getPassword(),
			user.getPasswordEncrypted());

		// Ping remote host and verify that the group exists in the same company
		// as the remote user

		try {
			GroupServiceHttp.checkRemoteStagingGroup(
				httpPrincipal, remoteGroupId);
		}
		catch (NoSuchGroupException nsge) {
			RemoteExportException ree = new RemoteExportException(
				RemoteExportException.NO_GROUP);

			ree.setGroupId(remoteGroupId);

			throw ree;
		}
		catch (RemoteAuthException rae) {
			rae.setURL(url);

			throw rae;
		}
		catch (SystemException se) {
			RemoteExportException ree = new RemoteExportException(
				RemoteExportException.BAD_CONNECTION);

			ree.setURL(url);

			throw ree;
		}

		Map<String, Serializable> taskContextMap =
			BackgroundTaskContextMapFactory.buildTaskContextMap(
				user.getUserId(), sourceGroupId, privateLayout, null,
				parameterMap, Constants.PUBLISH_TO_REMOTE, startDate, endDate,
				null);

		taskContextMap.put("httpPrincipal", httpPrincipal);

		if (layoutIdMap != null) {
			HashMap<Long, Boolean> serializableLayoutIdMap =
				new HashMap<Long, Boolean>(layoutIdMap);

			taskContextMap.put("layoutIdMap", serializableLayoutIdMap);
		}

		taskContextMap.put("remoteGroupId", remoteGroupId);

		BackgroundTaskLocalServiceUtil.addBackgroundTask(
			user.getUserId(), sourceGroupId, StringPool.BLANK, null,
			LayoutRemoteStagingBackgroundTaskExecutor.class, taskContextMap,
			new ServiceContext());
	}

	@Override
	public void deleteLastImportSettings(Group liveGroup, boolean privateLayout)
		throws PortalException, SystemException {

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			liveGroup.getGroupId(), privateLayout);

		for (Layout layout : layouts) {
			UnicodeProperties typeSettingsProperties =
				layout.getTypeSettingsProperties();

			Set<String> keys = new HashSet<String>();

			for (String key : typeSettingsProperties.keySet()) {
				if (key.startsWith("last-import-")) {
					keys.add(key);
				}
			}

			if (keys.isEmpty()) {
				continue;
			}

			for (String key : keys) {
				typeSettingsProperties.remove(key);
			}

			LayoutLocalServiceUtil.updateLayout(
				layout.getGroupId(), layout.getPrivateLayout(),
				layout.getLayoutId(), typeSettingsProperties.toString());
		}
	}

	@Override
	public void deleteRecentLayoutRevisionId(
			HttpServletRequest request, long layoutSetBranchId, long plid)
		throws SystemException {

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(request);

		deleteRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid);
	}

	@Override
	public void deleteRecentLayoutRevisionId(
			long userId, long layoutSetBranchId, long plid)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.fetchUser(userId);

		PortalPreferences portalPreferences = null;

		if (user != null) {
			portalPreferences = getPortalPreferences(user);
		}
		else {
			portalPreferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					userId, false);
		}

		deleteRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #deleteRecentLayoutRevisionId(long, long, long)}
	 */
	@Deprecated
	@Override
	public void deleteRecentLayoutRevisionId(
			User user, long layoutSetBranchId, long plid)
		throws SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		deleteRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             com.liferay.portal.service.StagingLocalService#disableStaging(
	 *             Group, ServiceContext)}
	 */
	@Override
	public void disableStaging(
			Group scopeGroup, Group liveGroup, ServiceContext serviceContext)
		throws Exception {

		disableStaging((PortletRequest)null, liveGroup, serviceContext);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             com.liferay.portal.service.StagingLocalService#disableStaging(
	 *             Group, ServiceContext)}
	 */
	@Override
	public void disableStaging(Group liveGroup, ServiceContext serviceContext)
		throws Exception {

		disableStaging((PortletRequest)null, liveGroup, serviceContext);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             com.liferay.portal.service.StagingLocalService#disableStaging(
	 *             PortletRequest, Group, ServiceContext)}
	 */
	@Override
	public void disableStaging(
			PortletRequest portletRequest, Group scopeGroup, Group liveGroup,
			ServiceContext serviceContext)
		throws Exception {

		disableStaging(portletRequest, liveGroup, serviceContext);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             com.liferay.portal.service.StagingLocalService#disableStaging(
	 *             PortletRequest, Group, ServiceContext)}
	 */
	@Override
	public void disableStaging(
			PortletRequest portletRequest, Group liveGroup,
			ServiceContext serviceContext)
		throws Exception {

		StagingLocalServiceUtil.disableStaging(
			portletRequest, liveGroup, serviceContext);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             com.liferay.portal.service.StagingLocalService#enableLocalStaging(
	 *             long, Group, boolean, boolean, ServiceContext)}
	 */
	@Override
	public void enableLocalStaging(
			long userId, Group scopeGroup, Group liveGroup,
			boolean branchingPublic, boolean branchingPrivate,
			ServiceContext serviceContext)
		throws Exception {

		StagingLocalServiceUtil.enableLocalStaging(
			userId, liveGroup, branchingPublic, branchingPrivate,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             com.liferay.portal.service.StagingLocalService#enableRemoteStaging(
	 *             long, Group, boolean, boolean, String, int, String, boolean,
	 *             long, ServiceContext)}
	 */
	@Override
	public void enableRemoteStaging(
			long userId, Group scopeGroup, Group liveGroup,
			boolean branchingPublic, boolean branchingPrivate,
			String remoteAddress, int remotePort, String remotePathContext,
			boolean secureConnection, long remoteGroupId,
			ServiceContext serviceContext)
		throws Exception {

		StagingLocalServiceUtil.enableRemoteStaging(
			userId, liveGroup, branchingPublic, branchingPrivate, remoteAddress,
			remotePort, remotePathContext, secureConnection, remoteGroupId,
			serviceContext);
	}

	@Override
	public JSONArray getErrorMessagesJSONArray(
		Locale locale, Map<String, MissingReference> missingReferences,
		Map<String, Serializable> contextMap) {

		JSONArray errorMessagesJSONArray = JSONFactoryUtil.createJSONArray();

		for (String missingReferenceDisplayName : missingReferences.keySet()) {
			MissingReference missingReference = missingReferences.get(
				missingReferenceDisplayName);

			JSONObject errorMessageJSONObject =
				JSONFactoryUtil.createJSONObject();

			String className = missingReference.getClassName();
			Map<String, String> referrers = missingReference.getReferrers();

			if (className.equals(StagedTheme.class.getName())) {
				errorMessageJSONObject.put(
					"info",
					LanguageUtil.format(
						locale,
						"the-referenced-theme-x-is-not-deployed-in-the-" +
							"current-environment",
						missingReference.getClassPK()));
			}
			else if (referrers.size() == 1) {
				Set<Map.Entry<String, String>> referrerDisplayNames =
					referrers.entrySet();

				Iterator<Map.Entry<String, String>> iterator =
					referrerDisplayNames.iterator();

				Map.Entry<String, String> entry = iterator.next();

				String referrerDisplayName = entry.getKey();
				String referrerClassName = entry.getValue();

				if (referrerClassName.equals(Portlet.class.getName())) {
					referrerDisplayName = PortalUtil.getPortletTitle(
						referrerDisplayName, locale);
				}

				errorMessageJSONObject.put(
					"info",
					LanguageUtil.format(
						locale, "referenced-by-a-x-x",
						new String[] {
							ResourceActionsUtil.getModelResource(
								locale, referrerClassName),
							referrerDisplayName
						}
					));
			}
			else {
				errorMessageJSONObject.put(
					"info",
					LanguageUtil.format(
						locale, "referenced-by-x-elements", referrers.size()));
			}

			errorMessageJSONObject.put("name", missingReferenceDisplayName);

			Group group = null;

			try {
				group = GroupLocalServiceUtil.fetchGroup(
					missingReference.getGroupId());
			}
			catch (SystemException se) {
			}

			if (group != null) {
				errorMessageJSONObject.put(
					"site",
					LanguageUtil.format(
						locale, "in-site-x", missingReference.getGroupId(),
						false));
			}

			errorMessageJSONObject.put(
				"type",
				ResourceActionsUtil.getModelResource(
					locale, missingReference.getClassName()));

			errorMessagesJSONArray.put(errorMessageJSONObject);
		}

		return errorMessagesJSONArray;
	}

	@Override
	public JSONObject getExceptionMessagesJSONObject(
		Locale locale, Exception e, Map<String, Serializable> contextMap) {

		JSONObject exceptionMessagesJSONObject =
			JSONFactoryUtil.createJSONObject();

		String errorMessage = StringPool.BLANK;
		JSONArray errorMessagesJSONArray = null;
		int errorType = 0;
		JSONArray warningMessagesJSONArray = null;

		if (e instanceof DuplicateFileException) {
			errorMessage = LanguageUtil.get(
				locale, "please-enter-a-unique-document-name");
			errorType = ServletResponseConstants.SC_DUPLICATE_FILE_EXCEPTION;
		}
		else if (e instanceof FileExtensionException) {
			errorMessage = LanguageUtil.format(
				locale,
				"document-names-must-end-with-one-of-the-following-extensions",
				".lar");
			errorType = ServletResponseConstants.SC_FILE_EXTENSION_EXCEPTION;
		}
		else if (e instanceof FileNameException) {
			errorMessage = LanguageUtil.get(
				locale, "please-enter-a-file-with-a-valid-file-name");
			errorType = ServletResponseConstants.SC_FILE_NAME_EXCEPTION;
		}
		else if (e instanceof FileSizeException ||
				 e instanceof LARFileSizeException) {

			long fileMaxSize = PropsValues.DL_FILE_MAX_SIZE;

			try {
				fileMaxSize = PrefsPropsUtil.getLong(
					PropsKeys.DL_FILE_MAX_SIZE);

				if (fileMaxSize == 0) {
					fileMaxSize = PrefsPropsUtil.getLong(
						PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
				}
			}
			catch (Exception ex) {
			}

			errorMessage = LanguageUtil.format(
				locale,
				"please-enter-a-file-with-a-valid-file-size-no-larger-than-x",
				fileMaxSize/1024);
			errorType = ServletResponseConstants.SC_FILE_SIZE_EXCEPTION;
		}
		else if (e instanceof LARTypeException) {
			LARTypeException lte = (LARTypeException)e;

			errorMessage = LanguageUtil.format(
				locale,
				"please-import-a-lar-file-of-the-correct-type-x-is-not-valid",
				lte.getMessage());
			errorType = ServletResponseConstants.SC_FILE_CUSTOM_EXCEPTION;
		}
		else if (e instanceof LARFileException) {
			errorMessage = LanguageUtil.get(
				locale, "please-specify-a-lar-file-to-import");
			errorType = ServletResponseConstants.SC_FILE_CUSTOM_EXCEPTION;
		}
		else if (e instanceof LayoutPrototypeException) {
			LayoutPrototypeException lpe = (LayoutPrototypeException)e;

			StringBundler sb = new StringBundler(4);

			sb.append("the-lar-file-could-not-be-imported-because-it-");
			sb.append("requires-page-templates-or-site-templates-that-could-");
			sb.append("not-be-found.-please-import-the-following-templates-");
			sb.append("manually");

			errorMessage = LanguageUtil.get(locale, sb.toString());

			errorMessagesJSONArray = JSONFactoryUtil.createJSONArray();

			List<Tuple> missingLayoutPrototypes =
				lpe.getMissingLayoutPrototypes();

			for (Tuple missingLayoutPrototype : missingLayoutPrototypes) {
				JSONObject errorMessageJSONObject =
					JSONFactoryUtil.createJSONObject();

				String layoutPrototypeUuid =
					(String)missingLayoutPrototype.getObject(1);

				errorMessageJSONObject.put("info", layoutPrototypeUuid);

				String layoutPrototypeName =
					(String)missingLayoutPrototype.getObject(2);

				errorMessageJSONObject.put("name", layoutPrototypeName);

				String layoutPrototypeClassName =
					(String)missingLayoutPrototype.getObject(0);

				errorMessageJSONObject.put(
					"type",
					ResourceActionsUtil.getModelResource(
						locale, layoutPrototypeClassName));

				errorMessagesJSONArray.put(errorMessageJSONObject);
			}

			errorType = ServletResponseConstants.SC_FILE_CUSTOM_EXCEPTION;
		}
		else if (e instanceof LocaleException) {
			LocaleException le = (LocaleException)e;

			errorMessage = LanguageUtil.format(
				locale,
				"the-available-languages-in-the-lar-file-x-do-not-match-the-" +
					"site's-available-languages-x",
				new String[] {
					StringUtil.merge(
						le.getSourceAvailableLocales(),
						StringPool.COMMA_AND_SPACE),
					StringUtil.merge(
						le.getTargetAvailableLocales(),
						StringPool.COMMA_AND_SPACE)
				});
			errorType = ServletResponseConstants.SC_FILE_CUSTOM_EXCEPTION;
		}
		else if (e instanceof MissingReferenceException) {
			MissingReferenceException mre = (MissingReferenceException)e;

			String cmd = null;

			if (contextMap != null) {
				cmd = (String)contextMap.get(Constants.CMD);
			}

			if (Validator.equals(cmd, Constants.PUBLISH_TO_LIVE) ||
				Validator.equals(cmd, Constants.PUBLISH_TO_REMOTE)) {

				errorMessage = LanguageUtil.get(
					locale,
					"there-are-missing-references-that-could-not-be-found-in-" +
						"the-live-environment");
			}
			else {
				errorMessage = LanguageUtil.get(
					locale,
					"there-are-missing-references-that-could-not-be-found-in-" +
						"the-current-site");
			}

			MissingReferences missingReferences = mre.getMissingReferences();

			errorMessagesJSONArray = getErrorMessagesJSONArray(
				locale, missingReferences.getDependencyMissingReferences(),
				contextMap);
			errorType = ServletResponseConstants.SC_FILE_CUSTOM_EXCEPTION;
			warningMessagesJSONArray = getWarningMessagesJSONArray(
				locale, missingReferences.getWeakMissingReferences(),
				contextMap);
		}
		else if (e instanceof PortletDataException) {
			PortletDataException pde = (PortletDataException)e;

			StagedModel stagedModel = pde.getStagedModel();

			String referrerClassName = StringPool.BLANK;
			String referrerDisplayName = StringPool.BLANK;

			if (stagedModel != null) {
				StagedModelType stagedModelType =
					stagedModel.getStagedModelType();

				referrerClassName = stagedModelType.getClassName();
				referrerDisplayName = StagedModelDataHandlerUtil.getDisplayName(
					stagedModel);
			}

			if (pde.getType() == PortletDataException.INVALID_GROUP) {
				errorMessage = LanguageUtil.format(
					locale,
					"the-x-x-could-not-be-exported-because-it-is-not-in-the-" +
						"currently-exported-group",
					new String[] {
						ResourceActionsUtil.getModelResource(
							locale, referrerClassName),
						referrerDisplayName
					}
				);
			}
			else if (pde.getType() == PortletDataException.MISSING_DEPENDENCY) {
				errorMessage = LanguageUtil.format(
					locale,
					"the-x-x-has-missing-references-that-could-not-be-found-" +
						"during-the-export",
					new String[] {
						ResourceActionsUtil.getModelResource(
							locale, referrerClassName),
						referrerDisplayName
					}
				);
			}
			else if (pde.getType() == PortletDataException.STATUS_IN_TRASH) {
				errorMessage = LanguageUtil.format(
					locale,
					"the-x-x-could-not-be-exported-because-it-is-in-the-" +
						"recycle-bin",
					new String[] {
						ResourceActionsUtil.getModelResource(
							locale, referrerClassName),
						referrerDisplayName
					}
				);
			}
			else if (pde.getType() == PortletDataException.STATUS_UNAVAILABLE) {
				errorMessage = LanguageUtil.format(
					locale,
					"the-x-x-could-not-be-exported-because-its-workflow-" +
						"status-is-not-exportable",
					new String[] {
						ResourceActionsUtil.getModelResource(
							locale, referrerClassName),
						referrerDisplayName
					}
				);
			}
			else {
				errorMessage = e.getLocalizedMessage();
			}

			errorType = ServletResponseConstants.SC_FILE_CUSTOM_EXCEPTION;
		}
		else if (e instanceof PortletIdException) {
			errorMessage = LanguageUtil.get(
				locale, "please-import-a-lar-file-for-the-current-portlet");
			errorType = ServletResponseConstants.SC_FILE_CUSTOM_EXCEPTION;
		}
		else if (e instanceof RemoteExportException) {
			RemoteExportException ree = (RemoteExportException)e;

			if (ree.getType() == RemoteExportException.NO_LAYOUTS) {
				errorMessage = LanguageUtil.get(
					locale, "no-pages-are-selected-for-export");
			}

			errorType = ServletResponseConstants.SC_FILE_CUSTOM_EXCEPTION;
		}
		else {
			errorMessage = e.getLocalizedMessage();
			errorType = ServletResponseConstants.SC_FILE_CUSTOM_EXCEPTION;
		}

		exceptionMessagesJSONObject.put("message", errorMessage);

		if ((errorMessagesJSONArray != null) &&
			(errorMessagesJSONArray.length() > 0)) {

			exceptionMessagesJSONObject.put(
				"messageListItems", errorMessagesJSONArray);
		}

		exceptionMessagesJSONObject.put("status", errorType);

		if ((warningMessagesJSONArray != null) &&
			(warningMessagesJSONArray.length() > 0)) {

			exceptionMessagesJSONObject.put(
				"warningMessages", warningMessagesJSONArray);
		}

		return exceptionMessagesJSONObject;
	}

	@Override
	public Date getLastPublishDate(LayoutSet layoutSet) throws PortalException {
		UnicodeProperties settingsProperties =
			layoutSet.getSettingsProperties();

		long lastPublishDate = GetterUtil.getLong(
			settingsProperties.getProperty(
				_LAST_PUBLISH_DATE, StringPool.BLANK));

		if (lastPublishDate == 0) {
			return null;
		}

		return new Date(lastPublishDate);
	}

	@Override
	public Date getLastPublishDate(PortletPreferences jxPortletPreferences) {
		long lastPublishDate = GetterUtil.getLong(
			jxPortletPreferences.getValue(
				_LAST_PUBLISH_DATE, StringPool.BLANK));

		if (lastPublishDate == 0) {
			return null;
		}

		return new Date(lastPublishDate);
	}

	@Override
	public Group getLiveGroup(long groupId)
		throws PortalException, SystemException {

		if (groupId == 0) {
			return null;
		}

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.isLayout()) {
			group = group.getParentGroup();
		}

		if (group.isStagingGroup()) {
			return group.getLiveGroup();
		}
		else {
			return group;
		}
	}

	@Override
	public long getLiveGroupId(long groupId)
		throws PortalException, SystemException {

		if (groupId == 0) {
			return groupId;
		}

		Group group = getLiveGroup(groupId);

		return group.getGroupId();
	}

	/**
	 * @see LayoutRemoteStagingBackgroundTaskExecutor#getMissingRemoteParentLayouts(
	 *      HttpPrincipal, Layout, long)
	 */
	@Override
	public List<Layout> getMissingParentLayouts(Layout layout, long liveGroupId)
		throws PortalException, SystemException {

		List<Layout> missingParentLayouts = new ArrayList<Layout>();

		long parentLayoutId = layout.getParentLayoutId();

		Layout parentLayout = null;

		while (parentLayoutId > 0) {
			parentLayout = LayoutLocalServiceUtil.getLayout(
				layout.getGroupId(), layout.isPrivateLayout(), parentLayoutId);

			try {
				LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
					parentLayout.getUuid(), liveGroupId,
					parentLayout.isPrivateLayout());

				// If one parent is found all others are assumed to exist

				break;
			}
			catch (NoSuchLayoutException nsle) {
				missingParentLayouts.add(parentLayout);

				parentLayoutId = parentLayout.getParentLayoutId();
			}
		}

		return missingParentLayouts;
	}

	@Override
	public long getRecentLayoutRevisionId(
			HttpServletRequest request, long layoutSetBranchId, long plid)
		throws PortalException, SystemException {

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(request);

		return getRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid);
	}

	@Override
	public long getRecentLayoutRevisionId(
			User user, long layoutSetBranchId, long plid)
		throws PortalException, SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		return getRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid);
	}

	@Override
	public long getRecentLayoutSetBranchId(
		HttpServletRequest request, long layoutSetId) {

		try {
			PortalPreferences portalPreferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(request);

			return getRecentLayoutAttribute(
				portalPreferences, getRecentLayoutSetBranchIdKey(layoutSetId));
		}
		catch (JSONException jsone) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get recent layout set branch ID with " +
						layoutSetId, jsone);
			}
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get portal preferences", se);
			}
		}

		return 0;
	}

	@Override
	public long getRecentLayoutSetBranchId(User user, long layoutSetId)
		throws SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		try {
			return getRecentLayoutAttribute(
				portalPreferences, getRecentLayoutSetBranchIdKey(layoutSetId));
		}
		catch (JSONException jsone) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get recent layout set branch ID with user " +
						user.getUserId() + " and layout set " + layoutSetId,
					jsone);
			}
		}

		return 0;
	}

	@Override
	public String getSchedulerGroupName(String destinationName, long groupId) {
		return destinationName.concat(StringPool.SLASH).concat(
			String.valueOf(groupId));
	}

	@Override
	public String getStagedPortletId(String portletId) {
		String key = portletId;

		if (key.startsWith(StagingConstants.STAGED_PORTLET)) {
			return key;
		}

		return StagingConstants.STAGED_PORTLET.concat(portletId);
	}

	@Override
	public Map<String, String[]> getStagingParameters() {
		Map<String, String[]> parameterMap =
			new LinkedHashMap<String, String[]>();

		parameterMap.put(
			PortletDataHandlerKeys.CATEGORIES,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.DATA_STRATEGY,
			new String[] {
				PortletDataHandlerKeys.DATA_STRATEGY_MIRROR_OVERWRITE});
		parameterMap.put(
			PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.DELETE_PORTLET_DATA,
			new String[] {Boolean.FALSE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.IGNORE_LAST_PUBLISH_DATE,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_LINK_ENABLED,
			new String[] {Boolean.FALSE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.LAYOUT_SET_SETTINGS,
			new String[] {Boolean.FALSE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.LOGO,
			new String[] {Boolean.FALSE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PERMISSIONS,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_CONFIGURATION,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_CONFIGURATION_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_DATA,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_DATA_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_SETUP_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.THEME_REFERENCE,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.UPDATE_LAST_PUBLISH_DATE,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.USER_ID_STRATEGY,
			new String[] {UserIdStrategy.CURRENT_USER_ID});

		return parameterMap;
	}

	@Override
	public Map<String, String[]> getStagingParameters(
		PortletRequest portletRequest) {

		Map<String, String[]> parameterMap =
			new LinkedHashMap<String, String[]>(
				portletRequest.getParameterMap());

		if (!parameterMap.containsKey(PortletDataHandlerKeys.DATA_STRATEGY)) {
			parameterMap.put(
				PortletDataHandlerKeys.DATA_STRATEGY,
				new String[] {
					PortletDataHandlerKeys.DATA_STRATEGY_MIRROR_OVERWRITE});
		}

		/*if (!parameterMap.containsKey(
				PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS)) {

			parameterMap.put(
				PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS,
				new String[] {Boolean.TRUE.toString()});
		}*/

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.DELETE_PORTLET_DATA)) {

			parameterMap.put(
				PortletDataHandlerKeys.DELETE_PORTLET_DATA,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_LINK_ENABLED)) {

			parameterMap.put(
				PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_LINK_ENABLED,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.LAYOUT_SET_SETTINGS)) {

			parameterMap.put(
				PortletDataHandlerKeys.LAYOUT_SET_SETTINGS,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(PortletDataHandlerKeys.LOGO)) {
			parameterMap.put(
				PortletDataHandlerKeys.LOGO,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.PORTLET_CONFIGURATION)) {

			parameterMap.put(
				PortletDataHandlerKeys.PORTLET_CONFIGURATION,
				new String[] {Boolean.TRUE.toString()});
		}

		if (!parameterMap.containsKey(PortletDataHandlerKeys.PORTLET_DATA)) {
			parameterMap.put(
				PortletDataHandlerKeys.PORTLET_DATA,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.PORTLET_DATA_ALL)) {

			parameterMap.put(
				PortletDataHandlerKeys.PORTLET_DATA_ALL,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(PortletDataHandlerKeys.THEME_REFERENCE)) {
			parameterMap.put(
				PortletDataHandlerKeys.THEME_REFERENCE,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.UPDATE_LAST_PUBLISH_DATE)) {

			parameterMap.put(
				PortletDataHandlerKeys.UPDATE_LAST_PUBLISH_DATE,
				new String[] {Boolean.TRUE.toString()});
		}

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.USER_ID_STRATEGY)) {

			parameterMap.put(
				PortletDataHandlerKeys.USER_ID_STRATEGY,
				new String[] {UserIdStrategy.CURRENT_USER_ID});
		}

		return parameterMap;
	}

	@Override
	public JSONArray getWarningMessagesJSONArray(
		Locale locale, Map<String, MissingReference> missingReferences,
		Map<String, Serializable> contextMap) {

		JSONArray warningMessagesJSONArray = JSONFactoryUtil.createJSONArray();

		for (String missingReferenceReferrerClassName :
				missingReferences.keySet()) {

			MissingReference missingReference = missingReferences.get(
				missingReferenceReferrerClassName);

			Map<String, String> referrers = missingReference.getReferrers();

			JSONObject errorMessageJSONObject =
				JSONFactoryUtil.createJSONObject();

			if (Validator.isNotNull(missingReference.getClassName())) {
				errorMessageJSONObject.put(
					"info",
					LanguageUtil.format(
						locale,
						"the-original-x-does-not-exist-in-the-current-" +
							"environment",
						ResourceActionsUtil.getModelResource(
							locale, missingReference.getClassName())));
			}

			errorMessageJSONObject.put("size", referrers.size());
			errorMessageJSONObject.put(
				"type",
				ResourceActionsUtil.getModelResource(
					locale, missingReferenceReferrerClassName));

			warningMessagesJSONArray.put(errorMessageJSONObject);
		}

		return warningMessagesJSONArray;
	}

	@Override
	public WorkflowTask getWorkflowTask(
			long userId, LayoutRevision layoutRevision)
		throws PortalException, SystemException {

		WorkflowInstanceLink workflowInstanceLink =
			WorkflowInstanceLinkLocalServiceUtil.fetchWorkflowInstanceLink(
				layoutRevision.getCompanyId(), layoutRevision.getGroupId(),
				LayoutRevision.class.getName(),
				layoutRevision.getLayoutRevisionId());

		if (workflowInstanceLink == null) {
			return null;
		}

		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(
				layoutRevision.getCompanyId(), userId,
				workflowInstanceLink.getWorkflowInstanceId(), false, 0, 1,
				null);

		if (!workflowTasks.isEmpty()) {
			return workflowTasks.get(0);
		}

		return null;
	}

	@Override
	public boolean hasWorkflowTask(long userId, LayoutRevision layoutRevision)
		throws PortalException, SystemException {

		WorkflowTask workflowTask = getWorkflowTask(userId, layoutRevision);

		if (workflowTask != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isIncomplete(Layout layout, long layoutSetBranchId) {
		LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(
			layout);

		if (layoutRevision == null) {
			try {
				layoutRevision =
					LayoutRevisionLocalServiceUtil.getLayoutRevision(
						layoutSetBranchId, layout.getPlid(), true);

				return false;
			}
			catch (Exception e) {
			}
		}

		try {
			layoutRevision = LayoutRevisionLocalServiceUtil.getLayoutRevision(
				layoutSetBranchId, layout.getPlid(), false);
		}
		catch (Exception e) {
		}

		if ((layoutRevision == null) ||
			(layoutRevision.getStatus() ==
				WorkflowConstants.STATUS_INCOMPLETE)) {

			return true;
		}

		return false;
	}

	@Override
	public void lockGroup(long userId, long groupId) throws Exception {
		if (!PropsValues.STAGING_LOCK_ENABLED) {
			return;
		}

		if (LockLocalServiceUtil.isLocked(Staging.class.getName(), groupId)) {
			Lock lock = LockLocalServiceUtil.getLock(
				Staging.class.getName(), groupId);

			throw new DuplicateLockException(lock);
		}

		LockLocalServiceUtil.lock(
			userId, Staging.class.getName(), String.valueOf(groupId),
			StagingImpl.class.getName(), false,
			StagingConstants.LOCK_EXPIRATION_TIME);
	}

	@Override
	public void publishLayout(
			long userId, long plid, long liveGroupId, boolean includeChildren)
		throws Exception {

		Map<String, String[]> parameterMap = getStagingParameters();

		parameterMap.put(
			PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS,
			new String[] {Boolean.FALSE.toString()});

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		List<Layout> layouts = new ArrayList<Layout>();

		layouts.add(layout);

		layouts.addAll(getMissingParentLayouts(layout, liveGroupId));

		if (includeChildren) {
			layouts.addAll(layout.getAllChildren());
		}

		long[] layoutIds = ExportImportHelperUtil.getLayoutIds(layouts);

		publishLayouts(
			userId, layout.getGroupId(), liveGroupId, layout.isPrivateLayout(),
			layoutIds, parameterMap, null, null);
	}

	@Override
	public void publishLayouts(
			long userId, long sourceGroupId, long targetGroupId,
			boolean privateLayout, long[] layoutIds,
			Map<String, String[]> parameterMap, Date startDate, Date endDate)
		throws PortalException, SystemException {

		parameterMap.put(
			PortletDataHandlerKeys.PERFORM_DIRECT_BINARY_IMPORT,
			new String[] {Boolean.TRUE.toString()});

		Map<String, Serializable> taskContextMap =
			BackgroundTaskContextMapFactory.buildTaskContextMap(
				userId, sourceGroupId, privateLayout, layoutIds, parameterMap,
				Constants.PUBLISH_TO_LIVE, startDate, endDate,
				StringPool.BLANK);

		taskContextMap.put("sourceGroupId", sourceGroupId);
		taskContextMap.put("targetGroupId", targetGroupId);

		BackgroundTaskLocalServiceUtil.addBackgroundTask(
			userId, sourceGroupId, StringPool.BLANK, null,
			LayoutStagingBackgroundTaskExecutor.class, taskContextMap,
			new ServiceContext());
	}

	@Override
	public void publishLayouts(
			long userId, long sourceGroupId, long targetGroupId,
			boolean privateLayout, Map<Long, Boolean> layoutIdMap,
			Map<String, String[]> parameterMap, Date startDate, Date endDate)
		throws PortalException, SystemException {

		List<Layout> layouts = new ArrayList<Layout>();

		for (Map.Entry<Long, Boolean> entry : layoutIdMap.entrySet()) {
			long plid = GetterUtil.getLong(String.valueOf(entry.getKey()));
			boolean includeChildren = entry.getValue();

			Layout layout = LayoutLocalServiceUtil.getLayout(plid);

			if (!layouts.contains(layout)) {
				layouts.add(layout);
			}

			List<Layout> parentLayouts = getMissingParentLayouts(
				layout, targetGroupId);

			for (Layout parentLayout : parentLayouts) {
				if (!layouts.contains(parentLayout)) {
					layouts.add(parentLayout);
				}
			}

			if (includeChildren) {
				for (Layout childLayout : layout.getAllChildren()) {
					if (!layouts.contains(childLayout)) {
						layouts.add(childLayout);
					}
				}
			}
		}

		long[] layoutIds = ExportImportHelperUtil.getLayoutIds(layouts);

		publishLayouts(
			userId, sourceGroupId, targetGroupId, privateLayout, layoutIds,
			parameterMap, startDate, endDate);
	}

	@Override
	public void publishLayouts(
			long userId, long sourceGroupId, long targetGroupId,
			boolean privateLayout, Map<String, String[]> parameterMap,
			Date startDate, Date endDate)
		throws PortalException, SystemException {

		publishLayouts(
			userId, sourceGroupId, targetGroupId, privateLayout, (long[])null,
			parameterMap, startDate, endDate);
	}

	@Override
	public void publishToLive(PortletRequest portletRequest) throws Exception {
		long groupId = ParamUtil.getLong(portletRequest, "groupId");

		Group liveGroup = GroupLocalServiceUtil.getGroup(groupId);

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		if (liveGroup.isStaged()) {
			if (liveGroup.isStagedRemotely()) {
				publishToRemote(portletRequest);
			}
			else {
				Group stagingGroup = liveGroup.getStagingGroup();

				publishLayouts(
					portletRequest, stagingGroup.getGroupId(), groupId,
					parameterMap, false);
			}
		}
	}

	@Override
	public void publishToLive(PortletRequest portletRequest, Portlet portlet)
		throws Exception {

		long plid = ParamUtil.getLong(portletRequest, "plid");

		Layout sourceLayout = LayoutLocalServiceUtil.getLayout(plid);

		Group stagingGroup = null;
		Group liveGroup = null;

		Layout targetLayout = null;

		long scopeGroupId = PortalUtil.getScopeGroupId(portletRequest);

		if (sourceLayout.isTypeControlPanel()) {
			stagingGroup = GroupLocalServiceUtil.fetchGroup(scopeGroupId);
			liveGroup = stagingGroup.getLiveGroup();

			targetLayout = sourceLayout;
		}
		else if (sourceLayout.hasScopeGroup() &&
				 (sourceLayout.getScopeGroup().getGroupId() == scopeGroupId)) {

			stagingGroup = sourceLayout.getScopeGroup();
			liveGroup = stagingGroup.getLiveGroup();

			targetLayout = LayoutLocalServiceUtil.getLayout(
				liveGroup.getClassPK());
		}
		else {
			stagingGroup = sourceLayout.getGroup();
			liveGroup = stagingGroup.getLiveGroup();

			targetLayout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(
				sourceLayout.getUuid(), liveGroup.getGroupId(),
				sourceLayout.isPrivateLayout());
		}

		copyPortlet(
			portletRequest, stagingGroup.getGroupId(), liveGroup.getGroupId(),
			sourceLayout.getPlid(), targetLayout.getPlid(),
			portlet.getPortletId());
	}

	@Override
	public void publishToRemote(PortletRequest portletRequest)
		throws Exception {

		publishToRemote(portletRequest, false);
	}

	@Override
	public void scheduleCopyFromLive(PortletRequest portletRequest)
		throws Exception {

		long stagingGroupId = ParamUtil.getLong(
			portletRequest, "stagingGroupId");

		Group stagingGroup = GroupLocalServiceUtil.getGroup(stagingGroupId);

		long liveGroupId = stagingGroup.getLiveGroupId();

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		publishLayouts(
			portletRequest, liveGroupId, stagingGroupId, parameterMap, true);
	}

	@Override
	public void schedulePublishToLive(PortletRequest portletRequest)
		throws Exception {

		long stagingGroupId = ParamUtil.getLong(
			portletRequest, "stagingGroupId");

		Group stagingGroup = GroupLocalServiceUtil.getGroup(stagingGroupId);

		long liveGroupId = stagingGroup.getLiveGroupId();

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		publishLayouts(
			portletRequest, stagingGroupId, liveGroupId, parameterMap, true);
	}

	@Override
	public void schedulePublishToRemote(PortletRequest portletRequest)
		throws Exception {

		publishToRemote(portletRequest, true);
	}

	@Override
	public void setRecentLayoutBranchId(
			HttpServletRequest request, long layoutSetBranchId, long plid,
			long layoutBranchId)
		throws SystemException {

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(request);

		setRecentLayoutBranchId(
			portalPreferences, layoutSetBranchId, plid, layoutBranchId);
	}

	@Override
	public void setRecentLayoutBranchId(
			User user, long layoutSetBranchId, long plid, long layoutBranchId)
		throws SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		setRecentLayoutBranchId(
			portalPreferences, layoutSetBranchId, plid, layoutBranchId);
	}

	@Override
	public void setRecentLayoutRevisionId(
			HttpServletRequest request, long layoutSetBranchId, long plid,
			long layoutRevisionId)
		throws SystemException {

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(request);

		setRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid, layoutRevisionId);
	}

	@Override
	public void setRecentLayoutRevisionId(
			User user, long layoutSetBranchId, long plid, long layoutRevisionId)
		throws SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		setRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid, layoutRevisionId);
	}

	@Override
	public void setRecentLayoutSetBranchId(
		HttpServletRequest request, long layoutSetId, long layoutSetBranchId) {

		try {
			PortalPreferences portalPreferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(request);

			setRecentLayoutAttribute(
				portalPreferences, getRecentLayoutSetBranchIdKey(layoutSetId),
				layoutSetBranchId);
		}
		catch (JSONException jsone) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to set recent layout set branch ID with " +
						layoutSetId + " and layout set branch " +
							layoutSetBranchId, jsone);
			}
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get portal preferences", se);
			}
		}
	}

	@Override
	public void setRecentLayoutSetBranchId(
			User user, long layoutSetId, long layoutSetBranchId)
		throws SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		try {
			setRecentLayoutAttribute(
				portalPreferences, getRecentLayoutSetBranchIdKey(layoutSetId),
				layoutSetBranchId);
		}
		catch (JSONException jsone) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to set recent layout set branch ID with user " +
						user.getUserId() + " and layout set " + layoutSetId +
							" and layout set branch " + layoutSetBranchId,
					jsone);
			}
		}
	}

	@Override
	public void unlockGroup(long groupId) throws SystemException {
		if (!PropsValues.STAGING_LOCK_ENABLED) {
			return;
		}

		LockLocalServiceUtil.unlock(Staging.class.getName(), groupId);
	}

	@Override
	public void unscheduleCopyFromLive(PortletRequest portletRequest)
		throws Exception {

		long stagingGroupId = ParamUtil.getLong(
			portletRequest, "stagingGroupId");

		String jobName = ParamUtil.getString(portletRequest, "jobName");
		String groupName = getSchedulerGroupName(
			DestinationNames.LAYOUTS_LOCAL_PUBLISHER, stagingGroupId);

		LayoutServiceUtil.unschedulePublishToLive(
			stagingGroupId, jobName, groupName);
	}

	@Override
	public void unschedulePublishToLive(PortletRequest portletRequest)
		throws Exception {

		long stagingGroupId = ParamUtil.getLong(
			portletRequest, "stagingGroupId");

		Group stagingGroup = GroupLocalServiceUtil.getGroup(stagingGroupId);

		long liveGroupId = stagingGroup.getLiveGroupId();

		String jobName = ParamUtil.getString(portletRequest, "jobName");
		String groupName = getSchedulerGroupName(
			DestinationNames.LAYOUTS_LOCAL_PUBLISHER, liveGroupId);

		LayoutServiceUtil.unschedulePublishToLive(
			liveGroupId, jobName, groupName);
	}

	@Override
	public void unschedulePublishToRemote(PortletRequest portletRequest)
		throws Exception {

		long groupId = ParamUtil.getLong(portletRequest, "groupId");

		String jobName = ParamUtil.getString(portletRequest, "jobName");
		String groupName = getSchedulerGroupName(
			DestinationNames.LAYOUTS_REMOTE_PUBLISHER, groupId);

		LayoutServiceUtil.unschedulePublishToRemote(
			groupId, jobName, groupName);
	}

	@Override
	public void updateLastImportSettings(
			Element layoutElement, Layout layout,
			PortletDataContext portletDataContext)
		throws Exception {

		Map<String, String[]> parameterMap =
			portletDataContext.getParameterMap();

		String cmd = MapUtil.getString(parameterMap, Constants.CMD);

		if (!cmd.equals("publish_to_live")) {
			return;
		}

		UnicodeProperties typeSettingsProperties =
			layout.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"last-import-date", String.valueOf(System.currentTimeMillis()));

		String layoutRevisionId = GetterUtil.getString(
			layoutElement.attributeValue("layout-revision-id"));

		typeSettingsProperties.setProperty(
			"last-import-layout-revision-id", layoutRevisionId);

		String layoutSetBranchId = MapUtil.getString(
			parameterMap, "layoutSetBranchId");

		typeSettingsProperties.setProperty(
			"last-import-layout-set-branch-id", layoutSetBranchId);

		String layoutSetBranchName = MapUtil.getString(
			parameterMap, "layoutSetBranchName");

		typeSettingsProperties.setProperty(
			"last-import-layout-set-branch-name", layoutSetBranchName);

		String lastImportUserName = MapUtil.getString(
			parameterMap, "lastImportUserName");

		typeSettingsProperties.setProperty(
			"last-import-user-name", lastImportUserName);

		String lastImportUserUuid = MapUtil.getString(
			parameterMap, "lastImportUserUuid");

		typeSettingsProperties.setProperty(
			"last-import-user-uuid", lastImportUserUuid);

		String layoutBranchId = GetterUtil.getString(
			layoutElement.attributeValue("layout-branch-id"));

		typeSettingsProperties.setProperty(
			"last-import-layout-branch-id", layoutBranchId);

		String layoutBranchName = GetterUtil.getString(
			layoutElement.attributeValue("layout-branch-name"));

		typeSettingsProperties.setProperty(
			"last-import-layout-branch-name", layoutBranchName);

		layout.setTypeSettingsProperties(typeSettingsProperties);
	}

	@Override
	public void updateLastPublishDate(
			long groupId, boolean privateLayout, Date lastPublishDate)
		throws Exception {

		updateLastPublishDate(groupId, privateLayout, null, lastPublishDate);
	}

	@Override
	public void updateLastPublishDate(
			long groupId, boolean privateLayout, DateRange dateRange,
			Date lastPublishDate)
		throws Exception {

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			groupId, privateLayout);

		Date originalLastPublishDate = getLastPublishDate(layoutSet);

		if (!isValidDateRange(dateRange, originalLastPublishDate)) {
			return;
		}

		if (lastPublishDate == null) {
			lastPublishDate = new Date();
		}

		UnicodeProperties settingsProperties =
			layoutSet.getSettingsProperties();

		settingsProperties.setProperty(
			_LAST_PUBLISH_DATE, String.valueOf(lastPublishDate.getTime()));

		LayoutSetLocalServiceUtil.updateSettings(
			layoutSet.getGroupId(), layoutSet.isPrivateLayout(),
			settingsProperties.toString());
	}

	@Override
	public void updateLastPublishDate(
			String portletId, PortletPreferences portletPreferences,
			Date lastPublishDate)
		throws Exception {

		updateLastPublishDate(
			portletId, portletPreferences, null, lastPublishDate);
	}

	@Override
	public void updateLastPublishDate(
		String portletId, PortletPreferences portletPreferences,
		DateRange dateRange, Date lastPublishDate) {

		Date originalLastPublishDate = getLastPublishDate(portletPreferences);

		if (!isValidDateRange(dateRange, originalLastPublishDate)) {
			return;
		}

		if (lastPublishDate == null) {
			lastPublishDate = new Date();
		}

		try {
			portletPreferences.setValue(
				_LAST_PUBLISH_DATE, String.valueOf(lastPublishDate.getTime()));

			portletPreferences.store();
		}
		catch (UnsupportedOperationException uoe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Not updating the portlet setup for " + portletId +
						" because no setup was returned for the current " +
							"page");
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void updateStaging(PortletRequest portletRequest, Group liveGroup)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		long userId = permissionChecker.getUserId();

		if (!GroupPermissionUtil.contains(
				permissionChecker, liveGroup.getGroupId(),
				ActionKeys.MANAGE_STAGING)) {

			return;
		}

		int stagingType = getStagingType(portletRequest, liveGroup);

		boolean branchingPublic = getBoolean(
			portletRequest, liveGroup, "branchingPublic");
		boolean branchingPrivate = getBoolean(
			portletRequest, liveGroup, "branchingPrivate");
		boolean forceDisable = ParamUtil.getBoolean(
			portletRequest, "forceDisable");

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		serviceContext.setAttribute("forceDisable", forceDisable);

		if (stagingType == StagingConstants.TYPE_NOT_STAGED) {
			if (liveGroup.hasStagingGroup() || liveGroup.isStagedRemotely()) {
				StagingLocalServiceUtil.disableStaging(
					portletRequest, liveGroup, serviceContext);
			}
		}
		else if (stagingType == StagingConstants.TYPE_LOCAL_STAGING) {
			StagingLocalServiceUtil.enableLocalStaging(
				userId, liveGroup, branchingPublic, branchingPrivate,
				serviceContext);
		}
		else if (stagingType == StagingConstants.TYPE_REMOTE_STAGING) {
			String remoteAddress = getString(
				portletRequest, liveGroup, "remoteAddress");

			remoteAddress = stripProtocolFromRemoteAddress(remoteAddress);

			int remotePort = getInteger(
				portletRequest, liveGroup, "remotePort");
			String remotePathContext = getString(
				portletRequest, liveGroup, "remotePathContext");
			boolean secureConnection = getBoolean(
				portletRequest, liveGroup, "secureConnection");
			long remoteGroupId = getLong(
				portletRequest, liveGroup, "remoteGroupId");

			StagingLocalServiceUtil.enableRemoteStaging(
				userId, liveGroup, branchingPublic, branchingPrivate,
				remoteAddress, remotePort, remotePathContext, secureConnection,
				remoteGroupId, serviceContext);
		}
	}

	@Override
	public void validateRemote(
			String remoteAddress, int remotePort, String remotePathContext,
			boolean secureConnection, long remoteGroupId)
		throws PortalException {

		RemoteOptionsException roe = null;

		if (!Validator.isDomain(remoteAddress) &&
			!Validator.isIPAddress(remoteAddress)) {

			roe = new RemoteOptionsException(
				RemoteOptionsException.REMOTE_ADDRESS);

			roe.setRemoteAddress(remoteAddress);

			throw roe;
		}

		if ((remotePort < 1) || (remotePort > 65535)) {
			roe = new RemoteOptionsException(
				RemoteOptionsException.REMOTE_PORT);

			roe.setRemotePort(remotePort);

			throw roe;
		}

		if (Validator.isNotNull(remotePathContext) &&
			(!remotePathContext.startsWith(StringPool.FORWARD_SLASH) ||
			 remotePathContext.endsWith(StringPool.FORWARD_SLASH))) {

			roe = new RemoteOptionsException(
				RemoteOptionsException.REMOTE_PATH_CONTEXT);

			roe.setRemotePathContext(remotePathContext);

			throw roe;
		}

		if (remoteGroupId <= 0) {
			roe = new RemoteOptionsException(
				RemoteOptionsException.REMOTE_GROUP_ID);

			roe.setRemoteGroupId(remoteGroupId);

			throw roe;
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		User user = permissionChecker.getUser();

		String remoteURL = buildRemoteURL(
			remoteAddress, remotePort, remotePathContext, secureConnection,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, false);

		HttpPrincipal httpPrincipal = new HttpPrincipal(
			remoteURL, user.getEmailAddress(), user.getPassword(),
			user.getPasswordEncrypted());

		// Ping remote host and verify that the group exists in the same company
		// as the remote user

		try {
			GroupServiceHttp.checkRemoteStagingGroup(
				httpPrincipal, remoteGroupId);
		}
		catch (NoSuchGroupException nsge) {
			RemoteExportException ree = new RemoteExportException(
				RemoteExportException.NO_GROUP);

			ree.setGroupId(remoteGroupId);

			throw ree;
		}
		catch (PrincipalException pe) {
			RemoteExportException ree = new RemoteExportException(
				RemoteExportException.NO_PERMISSIONS);

			ree.setGroupId(remoteGroupId);

			throw ree;
		}
		catch (RemoteAuthException rae) {
			rae.setURL(remoteURL);

			throw rae;
		}
		catch (SystemException se) {
			RemoteExportException ree = new RemoteExportException(
				RemoteExportException.BAD_CONNECTION, se.getMessage());

			ree.setURL(remoteURL);

			throw ree;
		}
	}

	protected void deleteRecentLayoutRevisionId(
		PortalPreferences portalPreferences, long layoutSetBranchId,
		long plid) {

		String oldPortalPreferences = portalPreferences.getValue(
			Staging.class.getName(),
			StagingConstants.STAGING_RECENT_LAYOUT_IDS_MAP);

		try {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			JSONArray oldJsonArray = JSONFactoryUtil.createJSONArray(
				oldPortalPreferences);

			String recentLayoutRevisionIdKey = getRecentLayoutRevisionIdKey(
				layoutSetBranchId, plid);

			for (int i = 0; i < oldJsonArray.length(); i ++) {
				JSONObject jsonObject = oldJsonArray.getJSONObject(i);

				if (Validator.isNotNull(
						jsonObject.getString(recentLayoutRevisionIdKey))) {

					continue;
				}

				jsonArray.put(jsonObject);
			}

			portalPreferences.setValue(
				Staging.class.getName(),
				StagingConstants.STAGING_RECENT_LAYOUT_IDS_MAP,
				jsonArray.toString());
		}
		catch (JSONException jsone) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to delete recent layout revision ID with layout " +
						"set branch " + layoutSetBranchId + " and PLID " + plid,
					jsone);
			}
		}
	}

	protected boolean getBoolean(
		PortletRequest portletRequest, Group group, String param) {

		return ParamUtil.getBoolean(
			portletRequest, param,
			GetterUtil.getBoolean(group.getTypeSettingsProperty(param)));
	}

	protected int getInteger(
		PortletRequest portletRequest, Group group, String param) {

		return ParamUtil.getInteger(
			portletRequest, param,
			GetterUtil.getInteger(group.getTypeSettingsProperty(param)));
	}

	protected long getLong(
		PortletRequest portletRequest, Group group, String param) {

		return ParamUtil.getLong(
			portletRequest, param,
			GetterUtil.getLong(group.getTypeSettingsProperty(param)));
	}

	protected PortalPreferences getPortalPreferences(User user)
		throws SystemException {

		boolean signedIn = !user.isDefaultUser();

		return PortletPreferencesFactoryUtil.getPortalPreferences(
			user.getUserId(), signedIn);
	}

	protected long getRecentLayoutBranchId(
		PortalPreferences portalPreferences, long layoutSetBranchId,
		long plid) {

		try {
			return getRecentLayoutAttribute(
				portalPreferences,
				getRecentLayoutBranchIdKey(layoutSetBranchId, plid));
		}
		catch (JSONException jsone) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get recent layout branch ID with layout set " +
						"branch " + layoutSetBranchId + " and PLID " + plid,
					jsone);
			}
		}

		return 0;
	}

	protected String getRecentLayoutBranchIdKey(
		long layoutSetBranchId, long plid) {

		StringBundler sb = new StringBundler(4);

		sb.append("layoutBranchId-");
		sb.append(layoutSetBranchId);
		sb.append(StringPool.DASH);
		sb.append(plid);

		return sb.toString();
	}

	protected long getRecentLayoutRevisionId(
			PortalPreferences portalPreferences, long layoutSetBranchId,
			long plid)
		throws PortalException, SystemException {

		long layoutRevisionId = 0;

		try {
			layoutRevisionId = getRecentLayoutAttribute(
				portalPreferences,
				getRecentLayoutRevisionIdKey(layoutSetBranchId, plid));
		}
		catch (JSONException jsone) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get recent layout revision ID with layout set " +
						"branch " + layoutSetBranchId + " and PLID " + plid,
					jsone);
			}
		}

		if (layoutRevisionId > 0) {
			return layoutRevisionId;
		}

		long layoutBranchId = getRecentLayoutBranchId(
			portalPreferences, layoutSetBranchId, plid);

		if (layoutBranchId > 0) {
			try {
				LayoutBranchLocalServiceUtil.getLayoutBranch(layoutBranchId);
			}
			catch (NoSuchLayoutBranchException nslbe) {
				LayoutBranch layoutBranch =
					LayoutBranchLocalServiceUtil.getMasterLayoutBranch(
						layoutSetBranchId, plid);

				layoutBranchId = layoutBranch.getLayoutBranchId();
			}
		}

		if (layoutBranchId > 0) {
			try {
				LayoutRevision layoutRevision =
					LayoutRevisionLocalServiceUtil.getLayoutRevision(
						layoutSetBranchId, layoutBranchId, plid);

				if (layoutRevision != null) {
					layoutRevisionId = layoutRevision.getLayoutRevisionId();
				}
			}
			catch (NoSuchLayoutRevisionException nslre) {
			}
		}

		return layoutRevisionId;
	}

	protected String getRecentLayoutRevisionIdKey(
		long layoutSetBranchId, long plid) {

		StringBundler sb = new StringBundler(4);

		sb.append("layoutRevisionId-");
		sb.append(layoutSetBranchId);
		sb.append(StringPool.DASH);
		sb.append(plid);

		return sb.toString();
	}

	protected String getRecentLayoutSetBranchIdKey(long layoutSetId) {
		return "layoutSetBranchId_" + layoutSetId;
	}

	protected int getStagingType(
		PortletRequest portletRequest, Group liveGroup) {

		String stagingType = portletRequest.getParameter("stagingType");

		if (stagingType != null) {
			return GetterUtil.getInteger(stagingType);
		}

		if (liveGroup.isStagedRemotely()) {
			return StagingConstants.TYPE_REMOTE_STAGING;
		}

		if (liveGroup.hasStagingGroup()) {
			return StagingConstants.TYPE_LOCAL_STAGING;
		}

		return StagingConstants.TYPE_NOT_STAGED;
	}

	protected String getString(
		PortletRequest portletRequest, Group group, String param) {

		return ParamUtil.getString(
			portletRequest, param,
			GetterUtil.getString(group.getTypeSettingsProperty(param)));
	}

	protected static boolean isValidDateRange(
		DateRange dateRange, Date originalLastPublishDate) {

		if (dateRange == null) {

			// This is a valid scenario when publishing all

			return true;
		}

		Date startDate = dateRange.getStartDate();
		Date endDate = dateRange.getEndDate();

		if (originalLastPublishDate != null) {
			if ((startDate != null) &&
				startDate.after(originalLastPublishDate)) {

				return false;
			}

			if ((endDate != null) && endDate.before(originalLastPublishDate)) {
				return false;
			}
		}
		else if ((startDate != null) || (endDate != null)) {
			return false;
		}

		return true;
	}

	protected void publishLayouts(
			PortletRequest portletRequest, long sourceGroupId,
			long targetGroupId, Map<String, String[]> parameterMap,
			boolean schedule)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String tabs1 = ParamUtil.getString(portletRequest, "tabs1");

		boolean privateLayout = true;

		if (tabs1.equals("public-pages")) {
			privateLayout = false;
		}

		String scope = ParamUtil.getString(portletRequest, "scope");

		Map<Long, Boolean> layoutIdMap = new LinkedHashMap<Long, Boolean>();

		if (scope.equals("selected-pages")) {
			layoutIdMap = ExportImportHelperUtil.getLayoutIdMap(portletRequest);
		}

		DateRange dateRange = ExportImportHelperUtil.getDateRange(
			portletRequest, sourceGroupId, privateLayout, 0, null,
			"fromLastPublishDate");

		if (schedule) {
			String groupName = getSchedulerGroupName(
				DestinationNames.LAYOUTS_LOCAL_PUBLISHER, targetGroupId);

			int recurrenceType = ParamUtil.getInteger(
				portletRequest, "recurrenceType");

			Calendar startCalendar = ExportImportHelperUtil.getCalendar(
				portletRequest, "schedulerStartDate", true);

			String cronText = SchedulerEngineHelperUtil.getCronText(
				portletRequest, startCalendar, true, recurrenceType);

			Date schedulerEndDate = null;

			int endDateType = ParamUtil.getInteger(
				portletRequest, "endDateType");

			if (endDateType == 1) {
				Calendar endCalendar = ExportImportHelperUtil.getCalendar(
					portletRequest, "schedulerEndDate", true);

				schedulerEndDate = endCalendar.getTime();
			}

			String description = ParamUtil.getString(
				portletRequest, "description");

			LayoutServiceUtil.schedulePublishToLive(
				sourceGroupId, targetGroupId, privateLayout, layoutIdMap,
				parameterMap, scope, dateRange.getStartDate(),
				dateRange.getEndDate(), groupName, cronText,
				startCalendar.getTime(), schedulerEndDate, description);
		}
		else {
			MessageStatus messageStatus = new MessageStatus();

			messageStatus.startTimer();

			String command =
				LayoutsLocalPublisherRequest.COMMAND_SELECTED_PAGES;

			try {
				if (scope.equals("all-pages")) {
					command = LayoutsLocalPublisherRequest.COMMAND_ALL_PAGES;

					publishLayouts(
						themeDisplay.getUserId(), sourceGroupId, targetGroupId,
						privateLayout, parameterMap, dateRange.getStartDate(),
						dateRange.getEndDate());
				}
				else {
					publishLayouts(
						themeDisplay.getUserId(), sourceGroupId, targetGroupId,
						privateLayout, layoutIdMap, parameterMap,
						dateRange.getStartDate(), dateRange.getEndDate());
				}
			}
			catch (Exception e) {
				messageStatus.setException(e);

				throw e;
			}
			finally {
				messageStatus.stopTimer();

				LayoutsLocalPublisherRequest publisherRequest =
					new LayoutsLocalPublisherRequest(
						command, themeDisplay.getUserId(), sourceGroupId,
						targetGroupId, privateLayout, layoutIdMap, parameterMap,
						dateRange.getStartDate(), dateRange.getEndDate());

				messageStatus.setPayload(publisherRequest);

				MessageBusUtil.sendMessage(
					DestinationNames.MESSAGE_BUS_MESSAGE_STATUS, messageStatus);
			}
		}
	}

	protected void publishToRemote(
			PortletRequest portletRequest, boolean schedule)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String tabs1 = ParamUtil.getString(portletRequest, "tabs1");

		long groupId = ParamUtil.getLong(portletRequest, "groupId");

		boolean privateLayout = true;

		if (tabs1.equals("public-pages")) {
			privateLayout = false;
		}

		String scope = ParamUtil.getString(portletRequest, "scope");

		if (Validator.isNull(scope)) {
			scope = "all-pages";
		}

		Map<Long, Boolean> layoutIdMap = null;

		if (scope.equals("selected-pages")) {
			layoutIdMap = ExportImportHelperUtil.getLayoutIdMap(portletRequest);
		}

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		parameterMap.put(
			PortletDataHandlerKeys.PUBLISH_TO_REMOTE,
			new String[] {Boolean.TRUE.toString()});

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		UnicodeProperties groupTypeSettingsProperties =
			group.getTypeSettingsProperties();

		String remoteAddress = ParamUtil.getString(
			portletRequest, "remoteAddress",
			groupTypeSettingsProperties.getProperty("remoteAddress"));

		remoteAddress = stripProtocolFromRemoteAddress(remoteAddress);

		int remotePort = ParamUtil.getInteger(
			portletRequest, "remotePort",
			GetterUtil.getInteger(
				groupTypeSettingsProperties.getProperty("remotePort")));
		String remotePathContext = ParamUtil.getString(
			portletRequest, "remotePathContext",
			groupTypeSettingsProperties.getProperty("remotePathContext"));
		boolean secureConnection = ParamUtil.getBoolean(
			portletRequest, "secureConnection",
			GetterUtil.getBoolean(
				groupTypeSettingsProperties.getProperty("secureConnection")));
		long remoteGroupId = ParamUtil.getLong(
			portletRequest, "remoteGroupId",
			GetterUtil.getLong(
				groupTypeSettingsProperties.getProperty("remoteGroupId")));
		boolean remotePrivateLayout = ParamUtil.getBoolean(
			portletRequest, "remotePrivateLayout");

		validateRemote(
			remoteAddress, remotePort, remotePathContext, secureConnection,
			remoteGroupId);

		DateRange dateRange = ExportImportHelperUtil.getDateRange(
			portletRequest, groupId, privateLayout, 0, null,
			"fromLastPublishDate");

		if (schedule) {
			String groupName = getSchedulerGroupName(
				DestinationNames.LAYOUTS_REMOTE_PUBLISHER, groupId);

			int recurrenceType = ParamUtil.getInteger(
				portletRequest, "recurrenceType");

			Calendar startCalendar = ExportImportHelperUtil.getCalendar(
				portletRequest, "schedulerStartDate", true);

			String cronText = SchedulerEngineHelperUtil.getCronText(
				portletRequest, startCalendar, true, recurrenceType);

			Date schedulerEndDate = null;

			int endDateType = ParamUtil.getInteger(
				portletRequest, "endDateType");

			if (endDateType == 1) {
				Calendar endCalendar = ExportImportHelperUtil.getCalendar(
					portletRequest, "schedulerEndDate", true);

				schedulerEndDate = endCalendar.getTime();
			}

			String description = ParamUtil.getString(
				portletRequest, "description");

			LayoutServiceUtil.schedulePublishToRemote(
				groupId, privateLayout, layoutIdMap, parameterMap,
				remoteAddress, remotePort, remotePathContext, secureConnection,
				remoteGroupId, remotePrivateLayout, dateRange.getStartDate(),
				dateRange.getEndDate(), groupName, cronText,
				startCalendar.getTime(), schedulerEndDate, description);
		}
		else {
			MessageStatus messageStatus = new MessageStatus();

			messageStatus.startTimer();

			try {
				copyRemoteLayouts(
					groupId, privateLayout, layoutIdMap, parameterMap,
					remoteAddress, remotePort, remotePathContext,
					secureConnection, remoteGroupId, remotePrivateLayout,
					dateRange.getStartDate(), dateRange.getEndDate());
			}
			catch (Exception e) {
				messageStatus.setException(e);

				throw e;
			}
			finally {
				messageStatus.stopTimer();

				LayoutsRemotePublisherRequest publisherRequest =
					new LayoutsRemotePublisherRequest(
						themeDisplay.getUserId(), groupId, privateLayout,
						layoutIdMap, parameterMap, remoteAddress, remotePort,
						remotePathContext, secureConnection, remoteGroupId,
						remotePrivateLayout, dateRange.getStartDate(),
						dateRange.getEndDate());

				messageStatus.setPayload(publisherRequest);

				MessageBusUtil.sendMessage(
					DestinationNames.MESSAGE_BUS_MESSAGE_STATUS, messageStatus);
			}
		}
	}

	protected void setRecentLayoutBranchId(
		PortalPreferences portalPreferences, long layoutSetBranchId, long plid,
		long layoutBranchId) {

		try {
			setRecentLayoutAttribute(
				portalPreferences,
				getRecentLayoutBranchIdKey(layoutSetBranchId, plid),
				layoutBranchId);
		}
		catch (JSONException jsone) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to set recent layout branch ID with layout set " +
						"branch " + layoutSetBranchId + " and PLID " + plid +
							" and layout branch " + layoutBranchId,
					jsone);
			}
		}
	}

	protected void setRecentLayoutRevisionId(
			PortalPreferences portalPreferences, long layoutSetBranchId,
			long plid, long layoutRevisionId)
		throws SystemException {

		long layoutBranchId = 0;

		try {
			LayoutRevision layoutRevision =
				LayoutRevisionLocalServiceUtil.getLayoutRevision(
					layoutRevisionId);

			layoutBranchId = layoutRevision.getLayoutBranchId();

			LayoutRevision lastLayoutRevision =
				LayoutRevisionLocalServiceUtil.getLayoutRevision(
					layoutSetBranchId, layoutBranchId, plid);

			if (lastLayoutRevision.getLayoutRevisionId() == layoutRevisionId) {
				deleteRecentLayoutRevisionId(
					portalPreferences, layoutSetBranchId, plid);
			}
			else {
				setRecentLayoutAttribute(
					portalPreferences,
					getRecentLayoutRevisionIdKey(layoutSetBranchId, plid),
					layoutRevisionId);
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to set recent layout revision ID with layout set " +
						"branch " + layoutSetBranchId + " and PLID " + plid +
							" and layout branch " + layoutBranchId,
					pe);
			}
		}

		setRecentLayoutBranchId(
			portalPreferences, layoutSetBranchId, plid, layoutBranchId);
	}

	protected String stripProtocolFromRemoteAddress(String remoteAddress) {
		if (remoteAddress.startsWith(Http.HTTP_WITH_SLASH)) {
			remoteAddress = remoteAddress.substring(
				Http.HTTP_WITH_SLASH.length());
		}
		else if (remoteAddress.startsWith(Http.HTTPS_WITH_SLASH)) {
			remoteAddress = remoteAddress.substring(
				Http.HTTPS_WITH_SLASH.length());
		}

		return remoteAddress;
	}

	protected void updateGroupTypeSettingsProperties(
			Group group, String remoteAddress, int remotePort,
			String remotePathContext, boolean secureConnection,
			long remoteGroupId)
		throws Exception {

		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		typeSettingsProperties.setProperty("remoteAddress", remoteAddress);
		typeSettingsProperties.setProperty(
			"remoteGroupId", String.valueOf(remoteGroupId));
		typeSettingsProperties.setProperty(
			"remotePathContext", remotePathContext);
		typeSettingsProperties.setProperty(
			"remotePort", String.valueOf(remotePort));
		typeSettingsProperties.setProperty(
			"secureConnection", String.valueOf(secureConnection));

		group.setTypeSettingsProperties(typeSettingsProperties);

		GroupLocalServiceUtil.updateGroup(group);
	}

	private long getRecentLayoutAttribute(
			PortalPreferences portalPreferences, String key)
		throws JSONException {

		String preferencesString = portalPreferences.getValue(
			Staging.class.getName(),
			StagingConstants.STAGING_RECENT_LAYOUT_IDS_MAP);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
			preferencesString);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			if (jsonObject.has(key)) {
				return GetterUtil.getLong(jsonObject.getString(key));
			}
		}

		return 0;
	}

	private void setRecentLayoutAttribute(
			PortalPreferences portalPreferences, String key, long value)
		throws JSONException {

		String oldPortalPreferences = portalPreferences.getValue(
			Staging.class.getName(),
			StagingConstants.STAGING_RECENT_LAYOUT_IDS_MAP);

		JSONArray oldJsonArray = JSONFactoryUtil.createJSONArray(
			oldPortalPreferences);

		boolean alreadyExists = false;

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (int i = 0; i < oldJsonArray.length(); i++) {
			JSONObject jsonObject = oldJsonArray.getJSONObject(i);

			if (Validator.isNotNull(jsonObject.getString(key))) {
				alreadyExists = true;

				jsonObject.remove(key);

				jsonObject.put(key, String.valueOf(value));
			}

			jsonArray.put(jsonObject);
		}

		if (!alreadyExists) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(key, String.valueOf(value));

			jsonArray.put(jsonObject);
		}

		portalPreferences.setValue(
			Staging.class.getName(),
			StagingConstants.STAGING_RECENT_LAYOUT_IDS_MAP,
			jsonArray.toString());
	}

	private static final String _LAST_PUBLISH_DATE = "last-publish-date";

	private static Log _log = LogFactoryUtil.getLog(StagingImpl.class);

}