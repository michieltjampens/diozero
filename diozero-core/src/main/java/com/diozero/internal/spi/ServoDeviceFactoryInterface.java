package com.diozero.internal.spi;

/*
 * #%L
 * Organisation: diozero
 * Project:      diozero - Core
 * Filename:     ServoDeviceFactoryInterface.java
 * 
 * This file is part of the diozero project. More information about this project
 * can be found at https://www.diozero.com/.
 * %%
 * Copyright (C) 2016 - 2021 diozero
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

import com.diozero.api.DeviceAlreadyOpenedException;
import com.diozero.api.DeviceMode;
import com.diozero.api.InvalidModeException;
import com.diozero.api.NoSuchDeviceException;
import com.diozero.api.PinInfo;
import com.diozero.api.RuntimeIOException;

public interface ServoDeviceFactoryInterface extends DeviceFactoryInterface {
	int getBoardServoFrequency();

	void setBoardServoFrequency(int pwmFrequency);

	default InternalServoDeviceInterface provisionServoDevice(PinInfo pinInfo, int frequencyHz, int minPulseWidthUs,
			int maxPulseWidthUs, int initialPulseWidthUs) throws RuntimeIOException {
		if (pinInfo == null) {
			throw new NoSuchDeviceException("No such device - pinInfo was null");
		}

		if (!pinInfo.isSupported(DeviceMode.SERVO)) {
			throw new InvalidModeException("Invalid mode (Servo) for GPIO " + pinInfo);
		}

		String key = createPinKey(pinInfo);

		// Check if this pin is already provisioned
		if (isDeviceOpened(key)) {
			throw new DeviceAlreadyOpenedException("Device " + key + " is already in use");
		}

		InternalServoDeviceInterface device = createServoDevice(key, pinInfo, frequencyHz, minPulseWidthUs,
				maxPulseWidthUs, initialPulseWidthUs);
		deviceOpened(device);

		return device;
	}

	InternalServoDeviceInterface createServoDevice(String key, PinInfo pinInfo, int frequencyHz, int minPulseWidthUs,
			int maxPulseWidthUs, int initialPulseWidthUs);
}