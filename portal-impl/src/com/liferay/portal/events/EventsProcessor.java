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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
public interface EventsProcessor {

	public void process(
			String key, String[] classes, String[] ids,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session)
		throws ActionException;

	public void processEvent(
			Object event, String[] ids, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
		throws ActionException;

	public void registerEvent(String key, Object event);

	public void unregisterEvent(String key, Object event);

}