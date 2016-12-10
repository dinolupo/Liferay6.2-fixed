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

package com.liferay.portal.test;

import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.reflect.Field;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author Shuyang Zhou
 */
public class CaptureAppender extends AppenderSkeleton {

	public CaptureAppender(Logger logger) {
		_logger = logger;

		_parentCategory = logger.getParent();

		try {
			_parentField.set(_logger, null);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() {
		_logger.removeAppender(this);

		try {
			_parentField.set(_logger, _parentCategory);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<LoggingEvent> getLoggingEvents() {
		return _loggingEvents;
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	protected void append(LoggingEvent loggingEvent) {
		_loggingEvents.add(loggingEvent);
	}

	private static Field _parentField;

	static {
		try {
			_parentField = ReflectionUtil.getDeclaredField(
				Category.class, "parent");
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	private Logger _logger;
	private List<LoggingEvent> _loggingEvents =
		new CopyOnWriteArrayList<LoggingEvent>();
	private Category _parentCategory;

}