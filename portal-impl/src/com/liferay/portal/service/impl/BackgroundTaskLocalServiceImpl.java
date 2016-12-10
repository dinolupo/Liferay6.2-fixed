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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistry;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.User;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.BackgroundTaskLocalServiceBaseImpl;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Daniel Kocsis
 * @author Michael C. Han
 */
public class BackgroundTaskLocalServiceImpl
	extends BackgroundTaskLocalServiceBaseImpl {

	@Override
	public BackgroundTask addBackgroundTask(
			long userId, long groupId, String name,
			String[] servletContextNames, Class<?> taskExecutorClass,
			Map<String, Serializable> taskContextMap,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		final long backgroundTaskId = counterLocalService.increment();

		BackgroundTask backgroundTask = backgroundTaskPersistence.create(
			backgroundTaskId);

		backgroundTask.setCompanyId(user.getCompanyId());
		backgroundTask.setCreateDate(serviceContext.getCreateDate(now));
		backgroundTask.setGroupId(groupId);
		backgroundTask.setModifiedDate(serviceContext.getModifiedDate(now));
		backgroundTask.setUserId(userId);
		backgroundTask.setUserName(user.getFullName());
		backgroundTask.setName(name);
		backgroundTask.setServletContextNames(
			StringUtil.merge(servletContextNames));
		backgroundTask.setTaskExecutorClassName(taskExecutorClass.getName());

		if (taskContextMap != null) {
			String taskContext = JSONFactoryUtil.serialize(taskContextMap);

			backgroundTask.setTaskContext(taskContext);
		}

		backgroundTask.setStatus(BackgroundTaskConstants.STATUS_NEW);

		backgroundTaskPersistence.update(backgroundTask);

		TransactionCommitCallbackRegistryUtil.registerCallback(
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					backgroundTaskLocalService.triggerBackgroundTask(
						backgroundTaskId);

					return null;
				}

			});

		return backgroundTask;
	}

	@Override
	public void addBackgroundTaskAttachment(
			long userId, long backgroundTaskId, String fileName, File file)
		throws PortalException, SystemException {

		BackgroundTask backgroundTask = getBackgroundTask(backgroundTaskId);

		Folder folder = backgroundTask.addAttachmentsFolder();

		PortletFileRepositoryUtil.addPortletFileEntry(
			backgroundTask.getGroupId(), userId, BackgroundTask.class.getName(),
			backgroundTask.getPrimaryKey(), PortletKeys.BACKGROUND_TASK,
			folder.getFolderId(), file, fileName, ContentTypes.APPLICATION_ZIP,
			false);
	}

	@Override
	public void addBackgroundTaskAttachment(
			long userId, long backgroundTaskId, String fileName,
			InputStream inputStream)
		throws PortalException, SystemException {

		BackgroundTask backgroundTask = getBackgroundTask(backgroundTaskId);

		Folder folder = backgroundTask.addAttachmentsFolder();

		PortletFileRepositoryUtil.addPortletFileEntry(
			backgroundTask.getGroupId(), userId, BackgroundTask.class.getName(),
			backgroundTask.getPrimaryKey(), PortletKeys.BACKGROUND_TASK,
			folder.getFolderId(), inputStream, fileName,
			ContentTypes.APPLICATION_ZIP, false);
	}

	@Override
	public BackgroundTask amendBackgroundTask(
			long backgroundTaskId, Map<String, Serializable> taskContextMap,
			int status, ServiceContext serviceContext)
		throws SystemException {

		return amendBackgroundTask(
			backgroundTaskId, taskContextMap, status, null, serviceContext);
	}

	@Override
	public BackgroundTask amendBackgroundTask(
			long backgroundTaskId, Map<String, Serializable> taskContextMap,
			int status, String statusMessage, ServiceContext serviceContext)
		throws SystemException {

		Date now = new Date();

		BackgroundTask backgroundTask =
			backgroundTaskPersistence.fetchByPrimaryKey(backgroundTaskId);

		if (backgroundTask == null) {
			return null;
		}

		backgroundTask.setModifiedDate(serviceContext.getModifiedDate(now));

		if (taskContextMap != null) {
			String taskContext = JSONFactoryUtil.serialize(taskContextMap);

			backgroundTask.setTaskContext(taskContext);
		}

		if ((status == BackgroundTaskConstants.STATUS_FAILED) ||
			(status == BackgroundTaskConstants.STATUS_SUCCESSFUL)) {

			backgroundTask.setCompleted(true);
			backgroundTask.setCompletionDate(now);
		}

		backgroundTask.setStatus(status);

		if (Validator.isNotNull(statusMessage)) {
			backgroundTask.setStatusMessage(statusMessage);
		}

		backgroundTaskPersistence.update(backgroundTask);

		return backgroundTask;
	}

	@Clusterable(onMaster = true)
	@Override
	public void cleanUpBackgroundTask(
		final BackgroundTask backgroundTask, final int status) {

		try {
			Lock lock = lockLocalService.getLock(
				BackgroundTaskExecutor.class.getName(),
				backgroundTask.getTaskExecutorClassName());

			String owner =
				backgroundTask.getName() + StringPool.POUND +
					backgroundTask.getBackgroundTaskId();

			if (owner.equals(lock.getOwner())) {
				lockLocalService.unlock(
					BackgroundTaskExecutor.class.getName(),
					backgroundTask.getTaskExecutorClassName());
			}
		}
		catch (Exception e) {
		}

		TransactionCommitCallbackRegistryUtil.registerCallback(
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					Message message = new Message();

					message.put(
						"backgroundTaskId",
						backgroundTask.getBackgroundTaskId());
					message.put("name", backgroundTask.getName());
					message.put("status", status);
					message.put(
						"taskExecutorClassName",
						backgroundTask.getTaskExecutorClassName());

					MessageBusUtil.sendMessage(
						DestinationNames.BACKGROUND_TASK_STATUS, message);

					return null;
				}

			}
		);
	}

	@Clusterable(onMaster = true)
	@Override
	public void cleanUpBackgroundTasks() throws SystemException {
		List<BackgroundTask> backgroundTasks =
			backgroundTaskPersistence.findByStatus(
				BackgroundTaskConstants.STATUS_IN_PROGRESS);

		for (BackgroundTask backgroundTask : backgroundTasks) {
			backgroundTask.setStatus(BackgroundTaskConstants.STATUS_FAILED);

			cleanUpBackgroundTask(
				backgroundTask, BackgroundTaskConstants.STATUS_FAILED);
		}
	}

	@Override
	public BackgroundTask deleteBackgroundTask(BackgroundTask backgroundTask)
		throws PortalException, SystemException {

		long folderId = backgroundTask.getAttachmentsFolderId();

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			PortletFileRepositoryUtil.deleteFolder(folderId);
		}

		if (backgroundTask.getStatus() ==
				BackgroundTaskConstants.STATUS_IN_PROGRESS) {

			cleanUpBackgroundTask(
				backgroundTask, BackgroundTaskConstants.STATUS_CANCELLED);
		}

		return backgroundTaskPersistence.remove(backgroundTask);
	}

	@Override
	public BackgroundTask deleteBackgroundTask(long backgroundTaskId)
		throws PortalException, SystemException {

		BackgroundTask backgroundTask =
			backgroundTaskPersistence.findByPrimaryKey(backgroundTaskId);

		return deleteBackgroundTask(backgroundTask);
	}

	@Override
	public void deleteCompanyBackgroundTasks(long companyId)
		throws PortalException, SystemException {

		List<BackgroundTask> backgroundTasks =
			backgroundTaskPersistence.findByCompanyId(companyId);

		for (BackgroundTask backgroundTask : backgroundTasks) {
			deleteBackgroundTask(backgroundTask);
		}
	}

	@Override
	public void deleteGroupBackgroundTasks(long groupId)
		throws PortalException, SystemException {

		List<BackgroundTask> backgroundTasks =
			backgroundTaskPersistence.findByGroupId(groupId);

		for (BackgroundTask backgroundTask : backgroundTasks) {
			deleteBackgroundTask(backgroundTask);
		}
	}

	@Override
	public BackgroundTask fetchBackgroundTask(long backgroundTaskId)
		throws SystemException {

		return backgroundTaskPersistence.fetchByPrimaryKey(backgroundTaskId);
	}

	@Override
	public BackgroundTask fetchFirstBackgroundTask(
			long groupId, String taskExecutorClassName, boolean completed,
			OrderByComparator orderByComparator)
		throws SystemException {

		return backgroundTaskPersistence.fetchByG_T_C_First(
			groupId, taskExecutorClassName, completed, orderByComparator);
	}

	@Override
	public BackgroundTask fetchFirstBackgroundTask(
			String taskExecutorClassName, int status)
		throws SystemException {

		return fetchFirstBackgroundTask(taskExecutorClassName, status, null);
	}

	@Override
	public BackgroundTask fetchFirstBackgroundTask(
			String taskExecutorClassName, int status,
			OrderByComparator orderByComparator)
		throws SystemException {

		return backgroundTaskPersistence.fetchByT_S_First(
			taskExecutorClassName, status, orderByComparator);
	}

	@Override
	public BackgroundTask getBackgroundTask(long backgroundTaskId)
		throws PortalException, SystemException {

		return backgroundTaskPersistence.findByPrimaryKey(backgroundTaskId);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(long groupId, int status)
		throws SystemException {

		return backgroundTaskPersistence.findByG_S(groupId, status);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			long groupId, String taskExecutorClassName)
		throws SystemException {

		return backgroundTaskPersistence.findByG_T(
			groupId, taskExecutorClassName);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			long groupId, String taskExecutorClassName, int status)
		throws SystemException {

		return backgroundTaskPersistence.findByG_T_S(
			groupId, taskExecutorClassName, status);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			long groupId, String taskExecutorClassName, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return backgroundTaskPersistence.findByG_T(
			groupId, taskExecutorClassName, start, end, orderByComparator);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			long groupId, String name, String taskExecutorClassName, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		return backgroundTaskPersistence.findByG_N_T(
			groupId, name, taskExecutorClassName, start, end,
			orderByComparator);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			long groupId, String[] taskExecutorClassNames)
		throws SystemException {

		return backgroundTaskPersistence.findByG_T(
			groupId, taskExecutorClassNames);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			long groupId, String[] taskExecutorClassNames, int status)
		throws SystemException {

		return backgroundTaskPersistence.findByG_T_S(
			groupId, taskExecutorClassNames, status);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			long groupId, String[] taskExecutorClassNames, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return backgroundTaskPersistence.findByG_T(
			groupId, taskExecutorClassNames, start, end, orderByComparator);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			String taskExecutorClassName, int status)
		throws SystemException {

		return backgroundTaskPersistence.findByT_S(
			taskExecutorClassName, status);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			String taskExecutorClassName, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return backgroundTaskPersistence.findByT_S(
			taskExecutorClassName, status, start, end, orderByComparator);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			String[] taskExecutorClassNames, int status)
		throws SystemException {

		return backgroundTaskPersistence.findByT_S(
			taskExecutorClassNames, status);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			String[] taskExecutorClassNames, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return backgroundTaskPersistence.findByT_S(
			taskExecutorClassNames, status, start, end, orderByComparator);
	}

	@Override
	public int getBackgroundTasksCount(
			long groupId, String taskExecutorClassName)
		throws SystemException {

		return backgroundTaskPersistence.countByG_T(
			groupId, taskExecutorClassName);
	}

	@Override
	public int getBackgroundTasksCount(
			long groupId, String taskExecutorClassName, boolean completed)
		throws SystemException {

		return backgroundTaskPersistence.countByG_T_C(
			groupId, taskExecutorClassName, completed);
	}

	@Override
	public int getBackgroundTasksCount(
			long groupId, String name, String taskExecutorClassName)
		throws SystemException {

		return backgroundTaskPersistence.countByG_N_T(
			groupId, name, taskExecutorClassName);
	}

	@Override
	public int getBackgroundTasksCount(
			long groupId, String name, String taskExecutorClassName,
			boolean completed)
		throws SystemException {

		return backgroundTaskPersistence.countByG_N_T_C(
			groupId, name, taskExecutorClassName, completed);
	}

	@Override
	public int getBackgroundTasksCount(
			long groupId, String[] taskExecutorClassNames)
		throws SystemException {

		return backgroundTaskPersistence.countByG_T(
			groupId, taskExecutorClassNames);
	}

	@Override
	public int getBackgroundTasksCount(
			long groupId, String[] taskExecutorClassNames, boolean completed)
		throws SystemException {

		return backgroundTaskPersistence.countByG_T_C(
			groupId, taskExecutorClassNames, completed);
	}

	@Clusterable(onMaster = true)
	@Override
	public String getBackgroundTaskStatusJSON(long backgroundTaskId) {
		BackgroundTaskStatus backgroundTaskStatus =
			_backgroundTaskStatusRegistry.getBackgroundTaskStatus(
				backgroundTaskId);

		if (backgroundTaskStatus != null) {
			return backgroundTaskStatus.getAttributesJSON();
		}

		return StringPool.BLANK;
	}

	@Clusterable(onMaster = true)
	@Override
	public void resumeBackgroundTask(long backgroundTaskId)
		throws SystemException {

		BackgroundTask backgroundTask =
			backgroundTaskPersistence.fetchByPrimaryKey(backgroundTaskId);

		if ((backgroundTask == null) ||
			(backgroundTask.getStatus() !=
				BackgroundTaskConstants.STATUS_QUEUED)) {

			return;
		}

		Message message = new Message();

		message.put("backgroundTaskId", backgroundTaskId);

		MessageBusUtil.sendMessage(DestinationNames.BACKGROUND_TASK, message);
	}

	@Clusterable(onMaster = true)
	@Override
	public void triggerBackgroundTask(long backgroundTaskId) {
		Message message = new Message();

		message.put("backgroundTaskId", backgroundTaskId);

		MessageBusUtil.sendMessage(DestinationNames.BACKGROUND_TASK, message);
	}

	@BeanReference(type = BackgroundTaskStatusRegistry.class)
	private BackgroundTaskStatusRegistry _backgroundTaskStatusRegistry;

}