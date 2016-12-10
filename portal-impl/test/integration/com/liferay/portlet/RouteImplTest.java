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

package com.liferay.portlet;

import com.liferay.portal.kernel.portlet.Route;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Connor McKay
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class RouteImplTest {

	@Test
	public void testNonMatchingRoute() {
		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put("action", "view");
		parameters.put("id", "bob");

		Map<String, String> originalParameters = new HashMap<String, String>(
			parameters);

		Route route = new RouteImpl("{action}/{id:\\d+}");

		String url = route.parametersToUrl(parameters);

		Assert.assertNull(url);
		Assert.assertEquals(originalParameters, parameters);
	}

}