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

package com.liferay.portal.monitoring.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.monitoring.statistics.DataSample;
import com.liferay.portal.kernel.monitoring.statistics.DataSampleProcessor;

import java.util.List;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class MonitoringMessageListener extends BaseMessageListener {

	public void setDataSampleProcessor(
		DataSampleProcessor<DataSample> dataSampleProcessor) {

		_dataSampleProcessor = dataSampleProcessor;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		List<DataSample> dataSamples = (List<DataSample>)message.getPayload();

		if ((dataSamples != null) && !dataSamples.isEmpty()) {
			for (DataSample dataSample : dataSamples) {
				_dataSampleProcessor.processDataSample(dataSample);
			}
		}
	}

	private DataSampleProcessor<DataSample> _dataSampleProcessor;

}