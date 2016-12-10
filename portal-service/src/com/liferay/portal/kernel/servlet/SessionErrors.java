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

package com.liferay.portal.kernel.servlet;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class SessionErrors {

	public static void add(HttpServletRequest request, Class<?> clazz) {
		add(request.getSession(false), clazz.getName());
	}

	public static void add(
		HttpServletRequest request, Class<?> clazz, Object value) {

		add(request.getSession(false), clazz.getName(), value);
	}

	public static void add(HttpServletRequest request, String key) {
		add(request.getSession(false), key);
	}

	public static void add(
		HttpServletRequest request, String key, Object value) {

		add(request.getSession(false), key, value);
	}

	public static void add(HttpSession session, Class<?> clazz) {
		add(session, clazz.getName());
	}

	public static void add(HttpSession session, Class<?> clazz, Object value) {
		add(session, clazz.getName(), value);
	}

	public static void add(HttpSession session, String key) {
		Map<String, Object> map = _getMap(session, true);

		if (map == null) {
			return;
		}

		map.put(key, key);
	}

	public static void add(HttpSession session, String key, Object value) {
		Map<String, Object> map = _getMap(session, true);

		if (map == null) {
			return;
		}

		map.put(key, value);
	}

	public static void add(PortletRequest portletRequest, Class<?> clazz) {
		add(portletRequest.getPortletSession(false), clazz.getName());
	}

	public static void add(
		PortletRequest portletRequest, Class<?> clazz, Object value) {

		add(portletRequest.getPortletSession(false), clazz.getName(), value);
	}

	public static void add(PortletRequest portletRequest, String key) {
		add(portletRequest.getPortletSession(false), key);
	}

	public static void add(
		PortletRequest portletRequest, String key, Object value) {

		add(portletRequest.getPortletSession(false), key, value);
	}

	public static void add(PortletSession portletSession, Class<?> clazz) {
		add(portletSession, clazz.getName());
	}

	public static void add(
		PortletSession portletSession, Class<?> clazz, Object value) {

		add(portletSession, clazz.getName(), value);
	}

	public static void add(PortletSession portletSession, String key) {
		Map<String, Object> map = _getMap(portletSession, true);

		if (map == null) {
			return;
		}

		map.put(key, key);
	}

	public static void add(
		PortletSession portletSession, String key, Object value) {

		Map<String, Object> map = _getMap(portletSession, true);

		if (map == null) {
			return;
		}

		map.put(key, value);
	}

	public static void clear(HttpServletRequest request) {
		clear(request.getSession(false));
	}

	public static void clear(HttpSession session) {
		Map<String, Object> map = _getMap(session, false);

		if (map != null) {
			map.clear();
		}
	}

	public static void clear(PortletRequest portletRequest) {
		clear(portletRequest.getPortletSession(false));
	}

	public static void clear(PortletSession portletSession) {
		Map<String, Object> map = _getMap(portletSession, false);

		if (map != null) {
			map.clear();
		}
	}

	public static boolean contains(HttpServletRequest request, Class<?> clazz) {
		return contains(request.getSession(false), clazz.getName());
	}

	public static boolean contains(HttpServletRequest request, String key) {
		return contains(request.getSession(false), key);
	}

	public static boolean contains(HttpSession session, Class<?> clazz) {
		return contains(session, clazz.getName());
	}

	public static boolean contains(HttpSession session, String key) {
		Map<String, Object> map = _getMap(session, false);

		if (map == null) {
			return false;
		}

		return map.containsKey(key);
	}

	public static boolean contains(
		PortletRequest portletRequest, Class<?> clazz) {

		return contains(
			portletRequest.getPortletSession(false), clazz.getName());
	}

	public static boolean contains(PortletRequest portletRequest, String key) {
		return contains(portletRequest.getPortletSession(false), key);
	}

	public static boolean contains(
		PortletSession portletSession, Class<?> clazz) {

		return contains(portletSession, clazz.getName());
	}

	public static boolean contains(PortletSession portletSession, String key) {
		Map<String, Object> map = _getMap(portletSession, false);

		if (map == null) {
			return false;
		}

		return map.containsKey(key);
	}

	public static Object get(HttpServletRequest request, Class<?> clazz) {
		return get(request.getSession(false), clazz.getName());
	}

	public static Object get(HttpServletRequest request, String key) {
		return get(request.getSession(false), key);
	}

	public static Object get(HttpSession session, Class<?> clazz) {
		return get(session, clazz.getName());
	}

	public static Object get(HttpSession session, String key) {
		Map<String, Object> map = _getMap(session, false);

		if (map == null) {
			return null;
		}

		return map.get(key);
	}

	public static Object get(PortletRequest portletRequest, Class<?> clazz) {
		return get(portletRequest.getPortletSession(false), clazz.getName());
	}

	public static Object get(PortletRequest portletRequest, String key) {
		return get(portletRequest.getPortletSession(false), key);
	}

	public static Object get(PortletSession portletSession, Class<?> clazz) {
		return get(portletSession, clazz.getName());
	}

	public static Object get(PortletSession portletSession, String key) {
		Map<String, Object> map = _getMap(portletSession, false);

		if (map == null) {
			return null;
		}

		return map.get(key);
	}

	public static boolean isEmpty(HttpServletRequest request) {
		return isEmpty(request.getSession(false));
	}

	public static boolean isEmpty(HttpSession session) {
		Map<String, Object> map = _getMap(session, false);

		if (map == null) {
			return true;
		}

		return map.isEmpty();
	}

	public static boolean isEmpty(PortletRequest portletRequest) {
		return isEmpty(portletRequest.getPortletSession(false));
	}

	public static boolean isEmpty(PortletSession portletSession) {
		Map<String, Object> map = _getMap(portletSession, false);

		if (map == null) {
			return true;
		}

		return map.isEmpty();
	}

	public static Iterator<String> iterator(HttpServletRequest request) {
		return iterator(request.getSession(false));
	}

	public static Iterator<String> iterator(HttpSession session) {
		Map<String, Object> map = _getMap(session, false);

		if (map == null) {
			List<String> list = Collections.<String>emptyList();

			return list.iterator();
		}

		Set<String> set = Collections.unmodifiableSet(map.keySet());

		return set.iterator();
	}

	public static Iterator<String> iterator(PortletRequest portletRequest) {
		return iterator(portletRequest.getPortletSession(false));
	}

	public static Iterator<String> iterator(PortletSession portletSession) {
		Map<String, Object> map = _getMap(portletSession, false);

		if (map == null) {
			List<String> list = Collections.<String>emptyList();

			return list.iterator();
		}

		Set<String> set = Collections.unmodifiableSet(map.keySet());

		return set.iterator();
	}

	public static Set<String> keySet(HttpServletRequest request) {
		return keySet(request.getSession(false));
	}

	public static Set<String> keySet(HttpSession session) {
		Map<String, Object> map = _getMap(session, false);

		if (map == null) {
			return Collections.emptySet();
		}

		return Collections.unmodifiableSet(map.keySet());
	}

	public static Set<String> keySet(PortletRequest portletRequest) {
		return keySet(portletRequest.getPortletSession(false));
	}

	public static Set<String> keySet(PortletSession portletSession) {
		Map<String, Object> map = _getMap(portletSession, false);

		if (map == null) {
			return Collections.emptySet();
		}

		return Collections.unmodifiableSet(map.keySet());
	}

	public static void print(HttpServletRequest request) {
		print(request.getSession(false));
	}

	public static void print(HttpSession session) {
		Iterator<String> itr = iterator(session);

		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public static void print(PortletRequest portletRequest) {
		print(portletRequest.getPortletSession(false));
	}

	public static void print(PortletSession portletSession) {
		Iterator<String> itr = iterator(portletSession);

		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public static int size(HttpServletRequest request) {
		return size(request.getSession(false));
	}

	public static int size(HttpSession session) {
		Map<String, Object> map = _getMap(session, false);

		if (map == null) {
			return 0;
		}

		return map.size();
	}

	public static int size(PortletRequest portletRequest) {
		return size(portletRequest.getPortletSession(false));
	}

	public static int size(PortletSession portletSession) {
		Map<String, Object> map = _getMap(portletSession, false);

		if (map == null) {
			return 0;
		}

		return map.size();
	}

	private static Map<String, Object> _getMap(
		HttpSession session, boolean createIfAbsent) {

		if (session == null) {
			return null;
		}

		Map<String, Object> map = null;

		try {
			map = (Map<String, Object>)session.getAttribute(_CLASS_NAME);

			if ((map == null) && createIfAbsent) {
				map = new LinkedHashMap<String, Object>();

				session.setAttribute(_CLASS_NAME, map);
			}
		}
		catch (IllegalStateException ise) {

			// Session is already invalidated, just return a null map

		}

		return map;
	}

	private static Map<String, Object> _getMap(
		PortletSession portletSession, boolean createIfAbsent) {

		if (portletSession == null) {
			return null;
		}

		Map<String, Object> map = null;

		try {
			map = (Map<String, Object>)portletSession.getAttribute(_CLASS_NAME);

			if ((map == null) && createIfAbsent) {
				map = new LinkedHashMap<String, Object>();

				portletSession.setAttribute(_CLASS_NAME, map);
			}
		}
		catch (IllegalStateException ise) {

			// Session is already invalidated, just return a null map

		}

		return map;
	}

	private static final String _CLASS_NAME = SessionErrors.class.getName();

}