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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for BackgroundTask. This utility wraps
 * {@link com.liferay.portal.service.impl.BackgroundTaskLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see BackgroundTaskLocalService
 * @see com.liferay.portal.service.base.BackgroundTaskLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.BackgroundTaskLocalServiceImpl
 * @generated
 */
@ProviderType
public class BackgroundTaskLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.BackgroundTaskLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the background task to the database. Also notifies the appropriate model listeners.
	*
	* @param backgroundTask the background task
	* @return the background task that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.BackgroundTask addBackgroundTask(
		com.liferay.portal.model.BackgroundTask backgroundTask)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addBackgroundTask(backgroundTask);
	}

	/**
	* Creates a new background task with the primary key. Does not add the background task to the database.
	*
	* @param backgroundTaskId the primary key for the new background task
	* @return the new background task
	*/
	public static com.liferay.portal.model.BackgroundTask createBackgroundTask(
		long backgroundTaskId) {
		return getService().createBackgroundTask(backgroundTaskId);
	}

	/**
	* Deletes the background task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param backgroundTaskId the primary key of the background task
	* @return the background task that was removed
	* @throws PortalException if a background task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.BackgroundTask deleteBackgroundTask(
		long backgroundTaskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteBackgroundTask(backgroundTaskId);
	}

	/**
	* Deletes the background task from the database. Also notifies the appropriate model listeners.
	*
	* @param backgroundTask the background task
	* @return the background task that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.BackgroundTask deleteBackgroundTask(
		com.liferay.portal.model.BackgroundTask backgroundTask)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteBackgroundTask(backgroundTask);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.model.BackgroundTask fetchBackgroundTask(
		long backgroundTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchBackgroundTask(backgroundTaskId);
	}

	/**
	* Returns the background task with the primary key.
	*
	* @param backgroundTaskId the primary key of the background task
	* @return the background task
	* @throws PortalException if a background task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.BackgroundTask getBackgroundTask(
		long backgroundTaskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getBackgroundTask(backgroundTaskId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the background tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of background tasks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBackgroundTasks(start, end);
	}

	/**
	* Returns the number of background tasks.
	*
	* @return the number of background tasks
	* @throws SystemException if a system exception occurred
	*/
	public static int getBackgroundTasksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBackgroundTasksCount();
	}

	/**
	* Updates the background task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param backgroundTask the background task
	* @return the background task that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.BackgroundTask updateBackgroundTask(
		com.liferay.portal.model.BackgroundTask backgroundTask)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateBackgroundTask(backgroundTask);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.portal.model.BackgroundTask addBackgroundTask(
		long userId, long groupId, java.lang.String name,
		java.lang.String[] servletContextNames,
		java.lang.Class<?> taskExecutorClass,
		java.util.Map<java.lang.String, java.io.Serializable> taskContextMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addBackgroundTask(userId, groupId, name,
			servletContextNames, taskExecutorClass, taskContextMap,
			serviceContext);
	}

	public static void addBackgroundTaskAttachment(long userId,
		long backgroundTaskId, java.lang.String fileName, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addBackgroundTaskAttachment(userId, backgroundTaskId, fileName,
			file);
	}

	public static void addBackgroundTaskAttachment(long userId,
		long backgroundTaskId, java.lang.String fileName,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addBackgroundTaskAttachment(userId, backgroundTaskId, fileName,
			inputStream);
	}

	public static com.liferay.portal.model.BackgroundTask amendBackgroundTask(
		long backgroundTaskId,
		java.util.Map<java.lang.String, java.io.Serializable> taskContextMap,
		int status, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .amendBackgroundTask(backgroundTaskId, taskContextMap,
			status, serviceContext);
	}

	public static com.liferay.portal.model.BackgroundTask amendBackgroundTask(
		long backgroundTaskId,
		java.util.Map<java.lang.String, java.io.Serializable> taskContextMap,
		int status, java.lang.String statusMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .amendBackgroundTask(backgroundTaskId, taskContextMap,
			status, statusMessage, serviceContext);
	}

	public static void cleanUpBackgroundTask(
		com.liferay.portal.model.BackgroundTask backgroundTask, int status) {
		getService().cleanUpBackgroundTask(backgroundTask, status);
	}

	public static void cleanUpBackgroundTasks()
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().cleanUpBackgroundTasks();
	}

	public static void deleteCompanyBackgroundTasks(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyBackgroundTasks(companyId);
	}

	public static void deleteGroupBackgroundTasks(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteGroupBackgroundTasks(groupId);
	}

	public static com.liferay.portal.model.BackgroundTask fetchFirstBackgroundTask(
		long groupId, java.lang.String taskExecutorClassName,
		boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchFirstBackgroundTask(groupId, taskExecutorClassName,
			completed, orderByComparator);
	}

	public static com.liferay.portal.model.BackgroundTask fetchFirstBackgroundTask(
		java.lang.String taskExecutorClassName, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchFirstBackgroundTask(taskExecutorClassName, status);
	}

	public static com.liferay.portal.model.BackgroundTask fetchFirstBackgroundTask(
		java.lang.String taskExecutorClassName, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchFirstBackgroundTask(taskExecutorClassName, status,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBackgroundTasks(groupId, status);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		long groupId, java.lang.String taskExecutorClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBackgroundTasks(groupId, taskExecutorClassName);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		long groupId, java.lang.String taskExecutorClassName, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasks(groupId, taskExecutorClassName, status);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		long groupId, java.lang.String taskExecutorClassName, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasks(groupId, taskExecutorClassName, start,
			end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		long groupId, java.lang.String name,
		java.lang.String taskExecutorClassName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasks(groupId, name, taskExecutorClassName,
			start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		long groupId, java.lang.String[] taskExecutorClassNames)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBackgroundTasks(groupId, taskExecutorClassNames);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		long groupId, java.lang.String[] taskExecutorClassNames, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasks(groupId, taskExecutorClassNames, status);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		long groupId, java.lang.String[] taskExecutorClassNames, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasks(groupId, taskExecutorClassNames, start,
			end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		java.lang.String taskExecutorClassName, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBackgroundTasks(taskExecutorClassName, status);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		java.lang.String taskExecutorClassName, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasks(taskExecutorClassName, status, start,
			end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		java.lang.String[] taskExecutorClassNames, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBackgroundTasks(taskExecutorClassNames, status);
	}

	public static java.util.List<com.liferay.portal.model.BackgroundTask> getBackgroundTasks(
		java.lang.String[] taskExecutorClassNames, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasks(taskExecutorClassNames, status, start,
			end, orderByComparator);
	}

	public static int getBackgroundTasksCount(long groupId,
		java.lang.String taskExecutorClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasksCount(groupId, taskExecutorClassName);
	}

	public static int getBackgroundTasksCount(long groupId,
		java.lang.String taskExecutorClassName, boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasksCount(groupId, taskExecutorClassName,
			completed);
	}

	public static int getBackgroundTasksCount(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasksCount(groupId, name, taskExecutorClassName);
	}

	public static int getBackgroundTasksCount(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasksCount(groupId, name,
			taskExecutorClassName, completed);
	}

	public static int getBackgroundTasksCount(long groupId,
		java.lang.String[] taskExecutorClassNames)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasksCount(groupId, taskExecutorClassNames);
	}

	public static int getBackgroundTasksCount(long groupId,
		java.lang.String[] taskExecutorClassNames, boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasksCount(groupId, taskExecutorClassNames,
			completed);
	}

	public static java.lang.String getBackgroundTaskStatusJSON(
		long backgroundTaskId) {
		return getService().getBackgroundTaskStatusJSON(backgroundTaskId);
	}

	public static void resumeBackgroundTask(long backgroundTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().resumeBackgroundTask(backgroundTaskId);
	}

	public static void triggerBackgroundTask(long backgroundTaskId) {
		getService().triggerBackgroundTask(backgroundTaskId);
	}

	public static BackgroundTaskLocalService getService() {
		if (_service == null) {
			_service = (BackgroundTaskLocalService)PortalBeanLocatorUtil.locate(BackgroundTaskLocalService.class.getName());

			ReferenceRegistry.registerReference(BackgroundTaskLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(BackgroundTaskLocalService service) {
	}

	private static BackgroundTaskLocalService _service;
}