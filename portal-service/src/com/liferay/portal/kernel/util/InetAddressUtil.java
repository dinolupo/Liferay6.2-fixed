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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

import java.util.Enumeration;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class InetAddressUtil {

	public static String getLocalHostName() throws Exception {
		return LocalHostNameHolder._localHostName;
	}

	public static InetAddress getLocalInetAddress() throws Exception {
		String clusterNodeListenAddress = StringPool.BLANK;

		try {
			clusterNodeListenAddress = GetterUtil.getString(
				PropsUtil.get("cluster.node.listen.address"));
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		if (Validator.isNotNull(clusterNodeListenAddress)) {
			InetAddress inetAddress = InetAddress.getByName(
				clusterNodeListenAddress);

			return inetAddress;
		}

		Enumeration<NetworkInterface> enu1 =
			NetworkInterface.getNetworkInterfaces();

		while (enu1.hasMoreElements()) {
			NetworkInterface networkInterface = enu1.nextElement();

			Enumeration<InetAddress> enu2 = networkInterface.getInetAddresses();

			while (enu2.hasMoreElements()) {
				InetAddress inetAddress = enu2.nextElement();

				if (!inetAddress.isLoopbackAddress() &&
					(inetAddress instanceof Inet4Address)) {

					return inetAddress;
				}
			}
		}

		throw new SystemException("No local internet address");
	}

	public static InetAddress getLoopbackInetAddress()
		throws UnknownHostException {

		return InetAddress.getByName("127.0.0.1");
	}

	private static Log _log = LogFactoryUtil.getLog(InetAddressUtil.class);

	private static class LocalHostNameHolder {

		private static final String _localHostName;

		static {
			try {
				InetAddress inetAddress = getLocalInetAddress();

				_localHostName = inetAddress.getHostName();
			}
			catch (Exception e) {
				throw new ExceptionInInitializerError(e);
			}
		}

	}

}