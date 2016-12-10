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

package com.liferay.portlet.documentlibrary.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;

/**
 * Document library processor responsible for the generation of raw metadata
 * associated with all of the the files stored in the document library.
 *
 * <p>
 * This processor automatically and assynchronously extracts the metadata from
 * all of the files stored in the document library. The metadata extraction is
 * done with the help of {@link
 * com.liferay.portal.metadata.TikaRawMetadataProcessor}
 * </p>
 *
 * @author Alexander Chow
 * @author Mika Koivisto
 * @author Miguel Pastor
 */
public interface RawMetadataProcessor {

	public void cleanUp(FileEntry fileEntry);

	public void cleanUp(FileVersion fileVersion);

	/**
	 * Generates the raw metadata associated with the file entry.
	 *
	 * @param  fileVersion the file version from which the raw metatada is to be
	 *         generated
	 * @throws PortalException if an error occurred in the metadata extraction
	 * @throws SystemException if a system exception occurred
	 */
	public void generateMetadata(FileVersion fileVersion)
		throws PortalException, SystemException;

	public boolean isSupported(FileVersion fileVersion);

	public boolean isSupported(String mimeType);

	/**
	 * Saves the raw metadata present in the file version.
	 *
	 * <p>
	 * The raw metadata present in the file version is extracted and persisted
	 * using {@link com.liferay.portal.metadata.TikaRawMetadataProcessor}.
	 * </p>
	 *
	 * @param  fileVersion the file version from which the raw metatada is to be
	 *         extracted and persisted
	 * @throws PortalException if an error occurred in the metadata extraction
	 * @throws SystemException if a system exception occurred
	 */
	public void saveMetadata(FileVersion fileVersion)
		throws PortalException, SystemException;

	/**
	 * Launches extraction of raw metadata from the file version.
	 *
	 * <p>
	 * The raw metadata extraction is done asynchronously.
	 * </p>
	 *
	 * @param fileVersion the latest file version from which the raw metadata is
	 *        to be generated
	 */
	public void trigger(FileVersion fileVersion);

}