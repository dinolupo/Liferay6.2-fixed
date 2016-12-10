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

package com.liferay.portal.lar.backgroundtask;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.RemoteExportException;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.lar.ExportImportHelperUtil;
import com.liferay.portal.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.kernel.lar.MissingReferences;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.staging.StagingUtil;
import com.liferay.portal.kernel.util.DateRange;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.model.Layout;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.http.LayoutServiceHttp;
import com.liferay.portal.service.http.StagingServiceHttp;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Mate Thurzo
 */
public class LayoutRemoteStagingBackgroundTaskExecutor
	extends BaseStagingBackgroundTaskExecutor {

	public LayoutRemoteStagingBackgroundTaskExecutor() {
		setBackgroundTaskStatusMessageTranslator(
			new LayoutStagingBackgroundTaskStatusMessageTranslator());
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		Map<String, Serializable> taskContextMap =
			backgroundTask.getTaskContextMap();

		long sourceGroupId = MapUtil.getLong(taskContextMap, "groupId");
		boolean privateLayout = MapUtil.getBoolean(
			taskContextMap, "privateLayout");
		Map<Long, Boolean> layoutIdMap = (Map<Long, Boolean>)taskContextMap.get(
			"layoutIdMap");
		Map<String, String[]> parameterMap =
			(Map<String, String[]>)taskContextMap.get("parameterMap");
		long remoteGroupId = MapUtil.getLong(taskContextMap, "remoteGroupId");
		Date startDate = (Date)taskContextMap.get("startDate");
		Date endDate = (Date)taskContextMap.get("endDate");
		HttpPrincipal httpPrincipal = (HttpPrincipal)taskContextMap.get(
			"httpPrincipal");

		clearBackgroundTaskStatus(backgroundTask);

		long stagingRequestId = 0;

		File file = null;
		FileInputStream fileInputStream = null;
		MissingReferences missingReferences = null;

		try {
			ExportImportThreadLocal.setLayoutStagingInProcess(true);

			file = exportLayoutsAsFile(
				sourceGroupId, privateLayout, layoutIdMap, parameterMap,
				remoteGroupId, startDate, endDate, httpPrincipal);

			String checksum = FileUtil.getMD5Checksum(file);

			fileInputStream = new FileInputStream(file);

			stagingRequestId = StagingServiceHttp.createStagingRequest(
				httpPrincipal, remoteGroupId, checksum);

			byte[] bytes =
				new byte[PropsValues.STAGING_REMOTE_TRANSFER_BUFFER_SIZE];

			int i = 0;
			int j = 0;

			String numberFormat = String.format(
				"%%0%dd",
				String.valueOf(
					(int)(file.length() / bytes.length)).length() + 1);

			while ((i = fileInputStream.read(bytes)) >= 0) {
				String fileName =
					file.getName() + String.format(numberFormat, j++);

				if (i < PropsValues.STAGING_REMOTE_TRANSFER_BUFFER_SIZE) {
					byte[] tempBytes = new byte[i];

					System.arraycopy(bytes, 0, tempBytes, 0, i);

					StagingServiceHttp.updateStagingRequest(
						httpPrincipal, stagingRequestId, fileName, tempBytes);
				}
				else {
					StagingServiceHttp.updateStagingRequest(
						httpPrincipal, stagingRequestId, fileName, bytes);
				}

				bytes =
					new byte[PropsValues.STAGING_REMOTE_TRANSFER_BUFFER_SIZE];
			}

			backgroundTask = markBackgroundTask(backgroundTask, "exported");

			missingReferences = StagingServiceHttp.validateStagingRequest(
				httpPrincipal, stagingRequestId, privateLayout, parameterMap);

			backgroundTask = markBackgroundTask(backgroundTask, "validated");

			StagingServiceHttp.publishStagingRequest(
				httpPrincipal, stagingRequestId, privateLayout, parameterMap);

			boolean updateLastPublishDate = MapUtil.getBoolean(
				parameterMap, PortletDataHandlerKeys.UPDATE_LAST_PUBLISH_DATE);

			if (updateLastPublishDate) {
				DateRange dateRange = new DateRange(startDate, endDate);

				StagingUtil.updateLastPublishDate(
					sourceGroupId, privateLayout, dateRange, endDate);
			}
		}
		catch (Exception e) {
			if (PropsValues.STAGING_DELETE_TEMP_LAR_ON_FAILURE) {
				FileUtil.delete(file);
			}
			else if ((file != null) && _log.isErrorEnabled()) {
				_log.error("Kept temporary LAR file " + file.getAbsolutePath());
			}

			throw e;
		}
		finally {
			ExportImportThreadLocal.setLayoutStagingInProcess(false);

			StreamUtil.cleanUp(fileInputStream);

			if (stagingRequestId > 0) {
				StagingServiceHttp.cleanUpStagingRequest(
					httpPrincipal, stagingRequestId);
			}
		}

		if (PropsValues.STAGING_DELETE_TEMP_LAR_ON_SUCCESS) {
			FileUtil.delete(file);
		}
		else if ((file != null) && _log.isDebugEnabled()) {
			_log.debug("Kept temporary LAR file " + file.getAbsolutePath());
		}

		return processMissingReferences(backgroundTask, missingReferences);
	}

	protected File exportLayoutsAsFile(
			long sourceGroupId, boolean privateLayout,
			Map<Long, Boolean> layoutIdMap, Map<String, String[]> parameterMap,
			long remoteGroupId, Date startDate, Date endDate,
			HttpPrincipal httpPrincipal)
		throws Exception {

		if ((layoutIdMap == null) || layoutIdMap.isEmpty()) {
			return LayoutLocalServiceUtil.exportLayoutsAsFile(
				sourceGroupId, privateLayout, null, parameterMap, startDate,
				endDate);
		}
		else {
			List<Layout> layouts = new ArrayList<Layout>();

			for (Map.Entry<Long, Boolean> entry : layoutIdMap.entrySet()) {
				long plid = GetterUtil.getLong(String.valueOf(entry.getKey()));
				boolean includeChildren = entry.getValue();

				Layout layout = LayoutLocalServiceUtil.getLayout(plid);

				if (!layouts.contains(layout)) {
					layouts.add(layout);
				}

				List<Layout> parentLayouts = getMissingRemoteParentLayouts(
					httpPrincipal, layout, remoteGroupId);

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

			if (layoutIds.length <= 0) {
				throw new RemoteExportException(
					RemoteExportException.NO_LAYOUTS);
			}

			return LayoutLocalServiceUtil.exportLayoutsAsFile(
				sourceGroupId, privateLayout, layoutIds, parameterMap,
				startDate, endDate);
		}
	}

	/**
	 * @see com.liferay.portal.staging.StagingImpl#getMissingParentLayouts(
	 *      Layout, long)
	 */
	protected List<Layout> getMissingRemoteParentLayouts(
			HttpPrincipal httpPrincipal, Layout layout, long remoteGroupId)
		throws Exception {

		List<Layout> missingRemoteParentLayouts = new ArrayList<Layout>();

		long parentLayoutId = layout.getParentLayoutId();

		while (parentLayoutId > 0) {
			Layout parentLayout = LayoutLocalServiceUtil.getLayout(
				layout.getGroupId(), layout.isPrivateLayout(), parentLayoutId);

			try {
				LayoutServiceHttp.getLayoutByUuidAndGroupId(
					httpPrincipal, parentLayout.getUuid(), remoteGroupId,
					parentLayout.getPrivateLayout());

				// If one parent is found all others are assumed to exist

				break;
			}
			catch (NoSuchLayoutException nsle) {
				missingRemoteParentLayouts.add(parentLayout);

				parentLayoutId = parentLayout.getParentLayoutId();
			}
		}

		return missingRemoteParentLayouts;
	}

	private static Log _log = LogFactoryUtil.getLog(
		LayoutRemoteStagingBackgroundTaskExecutor.class);
}