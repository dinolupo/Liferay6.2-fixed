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

package com.liferay.portal.security.xml;

import com.liferay.portal.kernel.util.ArrayUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * @author Tomas Polesovsky
 */
public class StripDoctypeFilter {

	public StripDoctypeFilter(InputStream inputStream) {
		_inputStream = inputStream;
	}

	public StripDoctypeFilter(Reader reader) {
		_reader = reader;
	}

	public int read() throws IOException {
		if (_bufferLength > 0) {
			return readFromBuffer();
		}

		int c = readFromSource();

		if (_documentStarted) {
			return c;
		}

		if (c == '<') {
			int[] buffer = new int[2];

			buffer[0] = readFromSource();
			buffer[1] = readFromSource();

			if (buffer[0] == '?') {
				setBuffer(buffer);

				return c;
			}

			if ((buffer[0] == '!') && (buffer[1] == '-')) {
				setBuffer(buffer);

				return c;
			}

			if ((buffer[0] == '!') && (buffer[1] == 'D')) {
				while (true) {
					int doctypeContent = readFromSource();

					if (doctypeContent == '[') {
						entityDeclaration = true;
					}
					else if (doctypeContent == ']') {
						entityDeclaration = false;
					}
					else if (doctypeContent == '>') {
						if (!entityDeclaration) {
							_documentStarted = true;

							return readFromSource();
						}
					}
				}
			}

			setBuffer(buffer);

			_documentStarted = true;
		}

		return c;
	}

	public int read(byte[] bytes, int offset, int length) throws IOException {
		int read = 0;

		for (read = 0; read < length; read++) {
			int c = read();

			if (c == -1) {
				if (read == 0) {
					return -1;
				}

				return read;
			}

			bytes[offset + read] = (byte) (c & 0xFF);
		}

		return read;
	}

	public int read(char[] chars, int offset, int length) throws IOException {
		int read = 0;

		for (read = 0; read < length; read++) {
			int c = read();

			if (c == -1) {
				if (read == 0) {
					return -1;
				}

				return read;
			}

			chars[offset + read] = (char)c;
		}

		return read;
	}

	protected int readFromBuffer() {
		_bufferLength--;

		return _buffer[_bufferLength];
	}

	protected int readFromSource() throws IOException {
		if (_inputStream != null) {
			return _inputStream.read();
		}

		if (_reader != null) {
			return _reader.read();
		}

		throw new IllegalStateException("No underlying source available");
	}

	protected void setBuffer(int[] buffer) {
		_buffer = buffer;

		ArrayUtil.reverse(_buffer);

		_bufferLength = _buffer.length;
	}

	private int[] _buffer;
	private int _bufferLength;
	private boolean _documentStarted;
	private InputStream _inputStream;
	private Reader _reader;
	private boolean entityDeclaration;

}