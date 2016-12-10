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

package com.liferay.portal.model;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model interface for the UserTracker service. Represents a row in the &quot;UserTracker&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see UserTrackerModel
 * @see com.liferay.portal.model.impl.UserTrackerImpl
 * @see com.liferay.portal.model.impl.UserTrackerModelImpl
 * @generated
 */
@ProviderType
public interface UserTracker extends UserTrackerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.UserTrackerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void addPath(com.liferay.portal.model.UserTrackerPath path);

	public java.lang.String getEmailAddress();

	public java.lang.String getFullName();

	public int getHits();

	public java.util.List<com.liferay.portal.model.UserTrackerPath> getPaths();
}