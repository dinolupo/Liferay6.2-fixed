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

package com.liferay.portal.module.framework;

import java.io.IOException;

import java.net.URL;
import java.net.URLClassLoader;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * @author Miguel Pastor
 */
public class ModuleFrameworkClassLoader extends URLClassLoader {

	public ModuleFrameworkClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);

		// Some application servers include their own OSGi framework in the
		// bootstrap class loader

		//_systemClassLoader = getSystemClassLoader();
	}

	@Override
	public URL getResource(String name) {
		URL url = null;

		if (_systemClassLoader != null) {
			url = _systemClassLoader.getResource(name);
		}

		if (url == null) {
			url = findResource(name);

			if (url == null) {
				url = super.getResource(name);
			}
		}

		return url;
	}

	@Override
	public Enumeration<URL> getResources(String name) throws IOException {
		final List<URL> urls = new ArrayList<URL>();

		Enumeration<URL> systemURLs = null;

		if (_systemClassLoader != null) {
			systemURLs = _systemClassLoader.getResources(name);
		}

		urls.addAll(_buildURLs(systemURLs));

		Enumeration<URL> localURLs = findResources(name);

		urls.addAll(_buildURLs(localURLs));

		Enumeration<URL> parentURLs = null;

		ClassLoader parentClassLoader = getParent();

		if (parentClassLoader != null) {
			parentURLs = parentClassLoader.getResources(name);
		}

		urls.addAll(_buildURLs(parentURLs));

		return new Enumeration<URL>() {
			final Iterator<URL> iterator = urls.iterator();

			@Override
			public boolean hasMoreElements() {
				return iterator.hasNext();
			}

			@Override
			public URL nextElement() {
				return iterator.next();
			}

		};
	}

	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve)
		throws ClassNotFoundException {

		Class<?> clazz = findLoadedClass(name);

		if (clazz == null) {
			if (_systemClassLoader != null) {
				try {
					clazz = _systemClassLoader.loadClass(name);
				}
				catch (ClassNotFoundException cnfe) {
				}
			}

			if (clazz == null) {
				try {
					clazz = findClass(name);
				}
				catch (ClassNotFoundException cnfe) {
					clazz = super.loadClass(name, resolve);
				}
			}
		}

		if (resolve) {
			resolveClass(clazz);
		}

		return clazz;
	}

	private List<URL> _buildURLs(Enumeration<URL> url) {
		if (url == null) {
			return new ArrayList<URL>();
		}

		List<URL> urls = new ArrayList<URL>();

		while (url.hasMoreElements()) {
			urls.add(url.nextElement());
		}

		return urls;
	}

	private ClassLoader _systemClassLoader;

}