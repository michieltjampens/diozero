package com.diozero.internal.provider.test;

/*-
 * #%L
 * Organisation: diozero
 * Project:      diozero - Core
 * Filename:     TestI2CDevice.java
 * 
 * This file is part of the diozero project. More information about this project
 * can be found at https://www.diozero.com/.
 * %%
 * Copyright (C) 2016 - 2024 diozero
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import org.tinylog.Logger;

import com.diozero.api.I2CConstants;
import com.diozero.api.I2CDevice;
import com.diozero.api.RuntimeIOException;
import com.diozero.internal.spi.AbstractDevice;
import com.diozero.internal.spi.DeviceFactoryInterface;
import com.diozero.internal.spi.InternalI2CDeviceInterface;
import com.diozero.util.Hex;

public class TestI2CDevice extends AbstractDevice implements InternalI2CDeviceInterface {
	private int controller;
	private int address;

	public TestI2CDevice(String key, DeviceFactoryInterface deviceFactory, int controller, int address,
			I2CConstants.AddressSize addressSize) {
		super(key, deviceFactory);

		this.controller = controller;
		this.address = address;
	}

	@Override
	protected void closeDevice() throws RuntimeIOException {
		Logger.trace("closeDevice() {}", getKey());
	}

	@Override
	public boolean probe(I2CDevice.ProbeMode mode) {
		return true;
	}

	@Override
	public int getController() {
		return controller;
	}

	@Override
	public int getAddress() {
		return address;
	}

	@Override
	public void writeQuick(byte bit) {
		// TODO Auto-generated method stub

	}

	@Override
	public byte readByte() throws RuntimeIOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeByte(byte b) throws RuntimeIOException {
		Logger.info("0x{}", Hex.encode(b));
	}

	@Override
	public byte readByteData(int register) throws RuntimeIOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeByteData(int register, byte b) throws RuntimeIOException {
		Logger.info("{}, 0x{}", Integer.valueOf(register), Hex.encode(b));
	}

	@Override
	public short readWordData(int register) throws RuntimeIOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeWordData(int register, short data) throws RuntimeIOException {
		Logger.info("{}, 0x{}", Integer.valueOf(register), Hex.encode(data));
	}

	@Override
	public short processCall(int register, short data) throws RuntimeIOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] readBlockData(int register) throws RuntimeIOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeBlockData(int register, byte... data) throws RuntimeIOException {
		Logger.info("{}, 0x{}", Integer.valueOf(register), Hex.encodeHexString(data));
	}

	@Override
	public byte[] blockProcessCall(int register, byte... data) throws RuntimeIOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int readI2CBlockData(int register, byte[] buffer) throws RuntimeIOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeI2CBlockData(int register, byte... data) throws RuntimeIOException {
		Logger.info("{}, 0x{}", Integer.valueOf(register), Hex.encodeHexString(data));
	}

	@Override
	public int readBytes(byte[] buffer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeBytes(byte... data) throws RuntimeIOException {
		Logger.info("{}, 0x{}", Hex.encodeHexString(data));
	}

	@Override
	public void readWrite(I2CMessage[] messages, byte[] buffer) {
		throw new UnsupportedOperationException("I2C readWrite not supported");
	}
}
