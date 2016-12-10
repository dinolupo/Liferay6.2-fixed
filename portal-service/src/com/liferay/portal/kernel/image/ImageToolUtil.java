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

package com.liferay.portal.kernel.image;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.Image;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.concurrent.Future;

/**
 * The Image utility class.
 *
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class ImageToolUtil {

	/**
	 * Returns the CMYK image converted to RGB using ImageMagick. This must be
	 * run against the original <code>byte[]</code> and not one extracted from a
	 * {@link java.awt.image.RenderedImage}. The latter may potentially have
	 * been already been read incorrectly.
	 *
	 * @param  bytes the image to convert
	 * @param  type the image type (e.g., "gif", "jpg", etc.)
	 * @return the asynchronous process converting the image or <code>null
	 *         </code> if ImageMagick was disabled or if the conversion could
	 *         not be completed. The conversion may not complete if (1) the
	 *         image was not in the CMYK colorspace to begin with or (2) there
	 *         was an error in the conversion process.
	 */
	public static Future<RenderedImage> convertCMYKtoRGB(
		byte[] bytes, String type) {

		return getImageTool().convertCMYKtoRGB(bytes, type);
	}

	/**
	 * Returns the image converted to the type.
	 *
	 * @param  sourceImage the image to convert
	 * @param  type the image type to convert to (e.g., "gif", "jpg", etc.)
	 * @return the converted image
	 */
	public static BufferedImage convertImageType(
		BufferedImage sourceImage, int type) {

		return getImageTool().convertImageType(sourceImage, type);
	}

	/**
	 * Encodes the image using the GIF format.
	 *
	 * @param  renderedImage the image to encode
	 * @param  os the stream to write to
	 * @throws IOException if an IO exception occurred
	 */
	public static void encodeGIF(RenderedImage renderedImage, OutputStream os)
		throws IOException {

		getImageTool().encodeGIF(renderedImage, os);
	}

	/**
	 * Encodes the image using the WBMP format.
	 *
	 * @param  renderedImage the image to encode
	 * @param  os the stream to write to
	 * @throws IOException if an IO exception occurred
	 */
	public static void encodeWBMP(RenderedImage renderedImage, OutputStream os)
		throws IOException {

		getImageTool().encodeWBMP(renderedImage, os);
	}

	/**
	 * Returns the rendered image as a {@link java.awt.image.BufferedImage}.
	 *
	 * @param  renderedImage the original image
	 * @return the converted image
	 */
	public static BufferedImage getBufferedImage(RenderedImage renderedImage) {
		return getImageTool().getBufferedImage(renderedImage);
	}

	/**
	 * Returns the image as a <code>byte[]</code>.
	 *
	 * @param  renderedImage the image to read
	 * @param  contentType the content type (e.g., "image/jpeg") or image type
	 *         (e.g., "jpg") to use during encoding
	 * @return the encoded image
	 * @throws IOException if an IO exception occurred
	 */
	public static byte[] getBytes(
			RenderedImage renderedImage, String contentType)
		throws IOException {

		return getImageTool().getBytes(renderedImage, contentType);
	}

	public static Image getDefaultCompanyLogo() {
		return getImageTool().getDefaultCompanyLogo();
	}

	public static Image getDefaultOrganizationLogo() {
		return getImageTool().getDefaultOrganizationLogo();
	}

	public static Image getDefaultSpacer() {
		return getImageTool().getDefaultSpacer();
	}

	public static Image getDefaultUserFemalePortrait() {
		return getImageTool().getDefaultUserFemalePortrait();
	}

	public static Image getDefaultUserMalePortrait() {
		return getImageTool().getDefaultUserMalePortrait();
	}

	public static Image getImage(byte[] bytes) throws IOException {
		return getImageTool().getImage(bytes);
	}

	public static Image getImage(File file) throws IOException {

		return getImageTool().getImage(file);
	}

	public static Image getImage(InputStream is) throws IOException {

		return getImageTool().getImage(is);
	}

	public static Image getImage(InputStream is, boolean cleanUpStream)
		throws IOException {

		return getImageTool().getImage(is, cleanUpStream);
	}

	public static ImageTool getImageTool() {
		PortalRuntimePermission.checkGetBeanProperty(ImageToolUtil.class);

		return _imageTool;
	}

	public static boolean isNullOrDefaultSpacer(byte[] bytes) {
		return getImageTool().isNullOrDefaultSpacer(bytes);
	}

	/**
	 * Detects the image format and creates an {@link
	 * com.liferay.portal.kernel.image.ImageBag} containing the {@link
	 * java.awt.image.RenderedImage} and image type.
	 *
	 * @param  bytes the bytes to read
	 * @return the {@link com.liferay.portal.kernel.image.ImageBag}
	 * @throws IOException if an IO exception occurred
	 */
	public static ImageBag read(byte[] bytes) throws IOException {
		return getImageTool().read(bytes);
	}

	/**
	 * Detects the image format and creates an {@link
	 * com.liferay.portal.kernel.image.ImageBag} containing the {@link
	 * java.awt.image.RenderedImage} and image type.
	 *
	 * @param  file the file to read
	 * @return the {@link com.liferay.portal.kernel.image.ImageBag}
	 * @throws IOException if an IO exception occurred
	 */
	public static ImageBag read(File file) throws IOException {
		return getImageTool().read(file);
	}

	public static ImageBag read(InputStream inputStream) throws IOException {
		return getImageTool().read(inputStream);
	}

	/**
	 * Returns the scaled image based on the given width with the height
	 * calculated to preserve aspect ratio.
	 *
	 * @param  renderedImage the image to scale
	 * @param  width the new width; also used to calculate the new height
	 * @return the scaled image
	 */
	public static RenderedImage scale(RenderedImage renderedImage, int width) {
		return getImageTool().scale(renderedImage, width);
	}

	/**
	 * Returns the scaled image based on the maximum height and width given
	 * while preserving the aspect ratio. If the image is already larger in both
	 * dimensions, the image will not be scaled.
	 *
	 * @param  renderedImage the image to scale
	 * @param  maxHeight the maximum height allowed for image
	 * @param  maxWidth the maximum width allowed for image
	 * @return the scaled image
	 */
	public static RenderedImage scale(
		RenderedImage renderedImage, int maxHeight, int maxWidth) {

		return getImageTool().scale(renderedImage, maxHeight, maxWidth);
	}

	/**
	 * Encodes the image using the content or image type.
	 *
	 * @param  renderedImage the image to encode
	 * @param  contentType the content type (e.g., "image/jpeg") or image type
	 *         (e.g., "jpg") to use during encoding
	 * @param  os the stream to write to
	 * @throws IOException if an IO exception occurred
	 */
	public static void write(
			RenderedImage renderedImage, String contentType, OutputStream os)
		throws IOException {

		getImageTool().write(renderedImage, contentType, os);
	}

	public void setImageTool(ImageTool imageTool) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_imageTool = imageTool;
	}

	private static ImageTool _imageTool;

}