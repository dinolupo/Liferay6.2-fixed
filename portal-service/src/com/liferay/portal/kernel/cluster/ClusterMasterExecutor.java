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

package com.liferay.portal.kernel.cluster;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.MethodHandler;

import java.util.concurrent.Future;

/**
 * @author Michael C. Han
 */
public interface ClusterMasterExecutor {

	public <T> Future<T> executeOnMaster(MethodHandler methodHandler)
		throws SystemException;

	public void initialize();

	public boolean isEnabled();

	public boolean isMaster();

	public void registerClusterMasterTokenTransitionListener(
		ClusterMasterTokenTransitionListener
			clusterMasterTokenAcquisitionListener);

	public void unregisterClusterMasterTokenTransitionListener(
		ClusterMasterTokenTransitionListener
			clusterMasterTokenAcquisitionListener);

}