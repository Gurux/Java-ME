//
// --------------------------------------------------------------------------
//  Gurux Ltd
// 
//
//
// Filename:        $HeadURL:  $
//
// Version:         $Revision: $,
//                  $Date:  $
//                  $Author: $
//
// Copyright (c) Gurux Ltd
//
//---------------------------------------------------------------------------
//
//  DESCRIPTION
//
// This file is a part of Gurux Device Framework.
//
// Gurux Device Framework is Open Source software; you can redistribute it
// and/or modify it under the terms of the GNU General Public License 
// as published by the Free Software Foundation; version 2 of the License.
// Gurux Device Framework is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
// See the GNU General Public License for more details.
//
// More information of Gurux DLMS/COSEM Director: http://www.gurux.org/GXDLMSDirector
//
// This code is licensed under the GNU General Public License v2. 
// Full text may be retrieved at http://www.gnu.org/licenses/gpl-2.0.txt
//---------------------------------------------------------------------------

package gurux.dlms.objects.enums;

/**
 *
 * Defines the baud rates.
 */
public class BaudRate {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	/**
	 * Baudrate is 300.
	 */
	public static final BaudRate BAUDRATE_300 = new BaudRate(0, "BAUDRATE_300");
	/**
	 * Baudrate is 600.
	 */
	public static final BaudRate BAUDRATE_600 = new BaudRate(1, "BAUDRATE_600");
	/**
	 * Baudrate is 1200.
	 */
	public static final BaudRate BAUDRATE_1200 = new BaudRate(2,
			"BAUDRATE_1200");
	/**
	 * Baudrate is 2400.
	 */
	public static final BaudRate BAUDRATE_2400 = new BaudRate(3,
			"BAUDRATE_2400");
	/**
	 * Baudrate is 4800.
	 */
	public static final BaudRate BAUDRATE_4800 = new BaudRate(4,
			"BAUDRATE_4800");
	/**
	 * Baudrate is 9600.
	 */
	public static final BaudRate BAUDRATE_9600 = new BaudRate(5,
			"BAUDRATE_9600");
	/**
	 * Baudrate is 19200.
	 */
	public static final BaudRate BAUDRATE_19200 = new BaudRate(6,
			"BAUDRATE_19200");
	/**
	 * Baudrate is 38400.
	 */
	public static final BaudRate BAUDRATE_38400 = new BaudRate(7,
			"BAUDRATE_38400");
	/**
	 * Baudrate is 57600.
	 */
	public static final BaudRate BAUDRATE_57600 = new BaudRate(8,
			"BAUDRATE_57600");
	/**
	 * Baudrate is 115200.
	 */
	public static final BaudRate BAUDRATE_115200 = new BaudRate(9,
			"BAUDRATE_115200");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (BaudRate.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private BaudRate(int value, String name) {
		this.value = value;
		this.name = name;
		getMappings().put(new Integer(value), this);
	}

	/*
	 * Get integer value for enum.
	 */
	public int getValue() {
		return value;
	}

	/*
	 * Convert integer for enum value.
	 */
	public static BaudRate forValue(int value) {
		return (BaudRate) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}
