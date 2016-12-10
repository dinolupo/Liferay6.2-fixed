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

package com.liferay.portal.image;

import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageMagick;
import com.liferay.portal.kernel.image.ImageTool;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.JavaDetector;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.impl.ImageImpl;
import com.liferay.portal.util.FileImpl;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.imageio.ImageIO;

import javax.media.jai.RenderedImageAdapter;

import net.jmge.gif.Gif89Encoder;

import org.im4java.core.IMOperation;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Shuyang Zhou
 */
@DoPrivileged
public class ImageToolImpl implements ImageTool {

	public static ImageTool getInstance() {
		return _instance;
	}

	public void afterPropertiesSet() {
		ClassLoader classLoader = getClass().getClassLoader();

		try {
			InputStream is = classLoader.getResourceAsStream(
				PropsUtil.get(PropsKeys.IMAGE_DEFAULT_SPACER));

			if (is == null) {
				_log.error("Default spacer is not available");
			}

			_defaultSpacer = getImage(is);
		}
		catch (Exception e) {
			_log.error(
				"Unable to configure the default spacer: " + e.getMessage());
		}

		try {
			InputStream is = classLoader.getResourceAsStream(
				PropsUtil.get(PropsKeys.IMAGE_DEFAULT_COMPANY_LOGO));

			if (is == null) {
				_log.error("Default company logo is not available");
			}

			_defaultCompanyLogo = getImage(is);
		}
		catch (Exception e) {
			_log.error(
				"Unable to configure the default company logo: " +
					e.getMessage());
		}

		try {
			InputStream is = classLoader.getResourceAsStream(
				PropsUtil.get(PropsKeys.IMAGE_DEFAULT_ORGANIZATION_LOGO));

			if (is == null) {
				_log.error("Default organization logo is not available");
			}

			_defaultOrganizationLogo = getImage(is);
		}
		catch (Exception e) {
			_log.error(
				"Unable to configure the default organization logo: " +
					e.getMessage());
		}

		try {
			InputStream is = classLoader.getResourceAsStream(
				PropsUtil.get(PropsKeys.IMAGE_DEFAULT_USER_FEMALE_PORTRAIT));

			if (is == null) {
				_log.error("Default user female portrait is not available");
			}

			_defaultUserFemalePortrait = getImage(is);
		}
		catch (Exception e) {
			_log.error(
				"Unable to configure the default user female portrait: " +
					e.getMessage());
		}

		try {
			InputStream is = classLoader.getResourceAsStream(
				PropsUtil.get(PropsKeys.IMAGE_DEFAULT_USER_MALE_PORTRAIT));

			if (is == null) {
				_log.error("Default user male portrait is not available");
			}

			_defaultUserMalePortrait = getImage(is);
		}
		catch (Exception e) {
			_log.error(
				"Unable to configure the default user male portrait: " +
					e.getMessage());
		}
	}

	@Override
	public Future<RenderedImage> convertCMYKtoRGB(byte[] bytes, String type) {
		ImageMagick imageMagick = getImageMagick();

		if (!imageMagick.isEnabled()) {
			return null;
		}

		File inputFile = _fileUtil.createTempFile(type);
		File outputFile = _fileUtil.createTempFile(type);

		try {
			_fileUtil.write(inputFile, bytes);

			IMOperation imOperation = new IMOperation();

			imOperation.addRawArgs("-format", "%[colorspace]");
			imOperation.addImage(inputFile.getPath());

			String[] output = imageMagick.identify(imOperation.getCmdArgs());

			if ((output.length == 1) &&
				StringUtil.equalsIgnoreCase(output[0], "CMYK")) {

				if (_log.isInfoEnabled()) {
					_log.info("The image is in the CMYK colorspace");
				}

				imOperation = new IMOperation();

				imOperation.addRawArgs("-colorspace", "RGB");
				imOperation.addImage(inputFile.getPath());
				imOperation.addImage(outputFile.getPath());

				Future<?> future = imageMagick.convert(
					imOperation.getCmdArgs());

				return new RenderedImageFuture(future, outputFile, type);
			}
		}
		catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error(e, e);
			}
		}
		finally {
			_fileUtil.delete(inputFile);
			_fileUtil.delete(outputFile);
		}

		return null;
	}

	@Override
	public BufferedImage convertImageType(BufferedImage sourceImage, int type) {
		BufferedImage targetImage = new BufferedImage(
			sourceImage.getWidth(), sourceImage.getHeight(), type);

		Graphics2D graphics = targetImage.createGraphics();

		graphics.drawRenderedImage(sourceImage, null);

		graphics.dispose();

		return targetImage;
	}

	@Override
	public void encodeGIF(RenderedImage renderedImage, OutputStream os)
		throws IOException {

		if (JavaDetector.isJDK6()) {
			ImageIO.write(renderedImage, TYPE_GIF, os);
		}
		else {
			BufferedImage bufferedImage = getBufferedImage(renderedImage);

			if (!(bufferedImage.getColorModel() instanceof IndexColorModel)) {
				bufferedImage = convertImageType(
					bufferedImage, BufferedImage.TYPE_BYTE_INDEXED);
			}

			Gif89Encoder encoder = new Gif89Encoder(bufferedImage);

			encoder.encode(os);
		}
	}

	@Override
	public void encodeWBMP(RenderedImage renderedImage, OutputStream os)
		throws IOException {

		BufferedImage bufferedImage = getBufferedImage(renderedImage);

		SampleModel sampleModel = bufferedImage.getSampleModel();

		int type = sampleModel.getDataType();

		if ((bufferedImage.getType() != BufferedImage.TYPE_BYTE_BINARY) ||
			(type < DataBuffer.TYPE_BYTE) || (type > DataBuffer.TYPE_INT) ||
			(sampleModel.getNumBands() != 1) ||
			(sampleModel.getSampleSize(0) != 1)) {

			BufferedImage binaryImage = new BufferedImage(
				bufferedImage.getWidth(), bufferedImage.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);

			Graphics graphics = binaryImage.getGraphics();

			graphics.drawImage(bufferedImage, 0, 0, null);

			renderedImage = binaryImage;
		}

		if (!ImageIO.write(renderedImage, "wbmp", os)) {

			// See http://www.jguru.com/faq/view.jsp?EID=127723

			os.write(0);
			os.write(0);
			os.write(toMultiByte(bufferedImage.getWidth()));
			os.write(toMultiByte(bufferedImage.getHeight()));

			DataBuffer dataBuffer = bufferedImage.getData().getDataBuffer();

			int size = dataBuffer.getSize();

			for (int i = 0; i < size; i++) {
				os.write((byte)dataBuffer.getElem(i));
			}
		}
	}

	@Override
	public BufferedImage getBufferedImage(RenderedImage renderedImage) {
		if (renderedImage instanceof BufferedImage) {
			return (BufferedImage)renderedImage;
		}

		RenderedImageAdapter adapter = new RenderedImageAdapter(renderedImage);

		return adapter.getAsBufferedImage();
	}

	@Override
	public byte[] getBytes(RenderedImage renderedImage, String contentType)
		throws IOException {

		UnsyncByteArrayOutputStream baos = new UnsyncByteArrayOutputStream();

		write(renderedImage, contentType, baos);

		return baos.toByteArray();
	}

	@Override
	public Image getDefaultCompanyLogo() {
		return _defaultCompanyLogo;
	}

	@Override
	public Image getDefaultOrganizationLogo() {
		return _defaultOrganizationLogo;
	}

	@Override
	public Image getDefaultSpacer() {
		return _defaultSpacer;
	}

	@Override
	public Image getDefaultUserFemalePortrait() {
		return _defaultUserFemalePortrait;
	}

	@Override
	public Image getDefaultUserMalePortrait() {
		return _defaultUserMalePortrait;
	}

	@Override
	public Image getImage(byte[] bytes) throws IOException {
		if (bytes == null) {
			return null;
		}

		ImageBag imageBag = read(bytes);

		RenderedImage renderedImage = imageBag.getRenderedImage();

		if (renderedImage == null) {
			throw new IOException("Unable to decode image");
		}

		String type = imageBag.getType();

		int height = renderedImage.getHeight();
		int width = renderedImage.getWidth();
		int size = bytes.length;

		Image image = new ImageImpl();

		image.setTextObj(bytes);
		image.setType(type);
		image.setHeight(height);
		image.setWidth(width);
		image.setSize(size);

		return image;
	}

	@Override
	public Image getImage(File file) throws IOException {
		byte[] bytes = _fileUtil.getBytes(file);

		return getImage(bytes);
	}

	@Override
	public Image getImage(InputStream is) throws IOException {
		byte[] bytes = _fileUtil.getBytes(is, -1, true);

		return getImage(bytes);
	}

	@Override
	public Image getImage(InputStream is, boolean cleanUpStream)
		throws IOException {

		byte[] bytes = _fileUtil.getBytes(is, -1, cleanUpStream);

		return getImage(bytes);
	}

	@Override
	public boolean isNullOrDefaultSpacer(byte[] bytes) {
		if (ArrayUtil.isEmpty(bytes) ||
			Arrays.equals(bytes, getDefaultSpacer().getTextObj())) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public ImageBag read(byte[] bytes) {
		RenderedImage renderedImage = null;
		String type = TYPE_NOT_AVAILABLE;

		Enumeration<ImageCodec> enu = ImageCodec.getCodecs();

		while (enu.hasMoreElements()) {
			ImageCodec codec = enu.nextElement();

			if (codec.isFormatRecognized(bytes)) {
				type = codec.getFormatName();

				renderedImage = read(bytes, type);

				break;
			}
		}

		if (type.equals("jpeg")) {
			type = TYPE_JPEG;
		}

		return new ImageBag(renderedImage, type);
	}

	@Override
	public ImageBag read(File file) throws IOException {
		return read(_fileUtil.getBytes(file));
	}

	@Override
	public ImageBag read(InputStream inputStream) throws IOException {
		return read(_fileUtil.getBytes(inputStream));
	}

	@Override
	public RenderedImage scale(RenderedImage renderedImage, int width) {
		if (width <= 0) {
			return renderedImage;
		}

		int imageHeight = renderedImage.getHeight();
		int imageWidth = renderedImage.getWidth();

		double factor = (double)width / imageWidth;

		int scaledHeight = (int)Math.round(factor * imageHeight);
		int scaledWidth = width;

		return doScale(renderedImage, scaledHeight, scaledWidth);
	}

	@Override
	public RenderedImage scale(
		RenderedImage renderedImage, int maxHeight, int maxWidth) {

		int imageHeight = renderedImage.getHeight();
		int imageWidth = renderedImage.getWidth();

		if (maxHeight == 0) {
			maxHeight = imageHeight;
		}

		if (maxWidth == 0) {
			maxWidth = imageWidth;
		}

		if ((imageHeight <= maxHeight) && (imageWidth <= maxWidth)) {
			return renderedImage;
		}

		double factor = Math.min(
			(double)maxHeight / imageHeight, (double)maxWidth / imageWidth);

		int scaledHeight = Math.max(1, (int)Math.round(factor * imageHeight));
		int scaledWidth = Math.max(1, (int)Math.round(factor * imageWidth));

		return doScale(renderedImage, scaledHeight, scaledWidth);
	}

	@Override
	public void write(
			RenderedImage renderedImage, String contentType, OutputStream os)
		throws IOException {

		if (contentType.contains(TYPE_BMP)) {
			ImageEncoder imageEncoder = ImageCodec.createImageEncoder(
				TYPE_BMP, os, null);

			imageEncoder.encode(renderedImage);
		}
		else if (contentType.contains(TYPE_GIF)) {
			encodeGIF(renderedImage, os);
		}
		else if (contentType.contains(TYPE_JPEG) ||
				 contentType.contains("jpeg")) {

			ImageIO.write(renderedImage, "jpeg", os);
		}
		else if (contentType.contains(TYPE_PNG)) {
			ImageIO.write(renderedImage, TYPE_PNG, os);
		}
		else if (contentType.contains(TYPE_TIFF) ||
				 contentType.contains("tif")) {

			ImageEncoder imageEncoder = ImageCodec.createImageEncoder(
				TYPE_TIFF, os, null);

			imageEncoder.encode(renderedImage);
		}
	}

	protected RenderedImage doScale(
		RenderedImage renderedImage, int scaledHeight, int scaledWidth) {

		// See http://www.oracle.com/technetwork/java/index-137037.html

		BufferedImage originalBufferedImage = getBufferedImage(renderedImage);

		ColorModel originalColorModel = originalBufferedImage.getColorModel();

		Graphics2D originalGraphics2D = originalBufferedImage.createGraphics();

		if (originalColorModel.hasAlpha()) {
			originalGraphics2D.setComposite(AlphaComposite.Src);
		}

		GraphicsConfiguration originalGraphicsConfiguration =
			originalGraphics2D.getDeviceConfiguration();

		BufferedImage scaledBufferedImage =
			originalGraphicsConfiguration.createCompatibleImage(
				scaledWidth, scaledHeight,
				originalBufferedImage.getTransparency());

		Graphics scaledGraphics = scaledBufferedImage.getGraphics();

		scaledGraphics.drawImage(
			originalBufferedImage.getScaledInstance(
				scaledWidth, scaledHeight, java.awt.Image.SCALE_SMOOTH),
			0, 0, null);

		originalGraphics2D.dispose();

		return scaledBufferedImage;
	}

	protected ImageMagick getImageMagick() {
		if (_imageMagick == null) {
			_imageMagick = ImageMagickImpl.getInstance();

			_imageMagick.reset();
		}

		return _imageMagick;
	}

	protected RenderedImage read(byte[] bytes, String type) {
		RenderedImage renderedImage = null;

		try {
			if (type.equals(TYPE_JPEG)) {
				type = "jpeg";
			}

			ImageDecoder imageDecoder = ImageCodec.createImageDecoder(
				type, new UnsyncByteArrayInputStream(bytes), null);

			renderedImage = imageDecoder.decodeAsRenderedImage();
		}
		catch (IOException ioe) {
			if (_log.isDebugEnabled()) {
				_log.debug(type + ": " + ioe.getMessage());
			}
		}

		return renderedImage;
	}

	protected byte[] toMultiByte(int intValue) {
		int numBits = 32;
		int mask = 0x80000000;

		while ((mask != 0) && ((intValue & mask) == 0)) {
			numBits--;
			mask >>>= 1;
		}

		int numBitsLeft = numBits;
		byte[] multiBytes = new byte[(numBitsLeft + 6) / 7];

		int maxIndex = multiBytes.length - 1;

		for (int b = 0; b <= maxIndex; b++) {
			multiBytes[b] = (byte)((intValue >>> ((maxIndex - b) * 7)) & 0x7f);

			if (b != maxIndex) {
				multiBytes[b] |= (byte)0x80;
			}
		}

		return multiBytes;
	}

	private ImageToolImpl() {
		ImageIO.setUseCache(PropsValues.IMAGE_IO_USE_DISK_CACHE);
	}

	private static Log _log = LogFactoryUtil.getLog(ImageToolImpl.class);

	private static ImageTool _instance = new ImageToolImpl();

	private static FileImpl _fileUtil = FileImpl.getInstance();
	private static ImageMagick _imageMagick;

	private Image _defaultCompanyLogo;
	private Image _defaultOrganizationLogo;
	private Image _defaultSpacer;
	private Image _defaultUserFemalePortrait;
	private Image _defaultUserMalePortrait;

	private class RenderedImageFuture implements Future<RenderedImage> {

		public RenderedImageFuture(
			Future<?> future, File outputFile, String type) {

			_future = future;
			_outputFile = outputFile;
			_type = type;
		}

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			if (_future.isCancelled() || _future.isDone()) {
				return false;
			}

			_future.cancel(true);

			return true;
		}

		@Override
		public RenderedImage get()
			throws ExecutionException, InterruptedException {

			_future.get();

			byte[] bytes = new byte[0];

			try {
				bytes = _fileUtil.getBytes(_outputFile);
			}
			catch (IOException ioe) {
				throw new ExecutionException(ioe);
			}

			ImageBag imageBag = read(bytes);

			return imageBag.getRenderedImage();
		}

		@Override
		public RenderedImage get(long timeout, TimeUnit timeUnit)
			throws ExecutionException, InterruptedException, TimeoutException {

			_future.get(timeout, timeUnit);

			byte[] bytes = new byte[0];

			try {
				bytes = _fileUtil.getBytes(_outputFile);
			}
			catch (IOException ioe) {
				throw new ExecutionException(ioe);
			}

			ImageBag imageBag = read(bytes);

			return imageBag.getRenderedImage();
		}

		@Override
		public boolean isCancelled() {
			return _future.isCancelled();
		}

		@Override
		public boolean isDone() {
			return _future.isDone();
		}

		private final Future<?> _future;
		private final File _outputFile;
		private final String _type;

	}

}