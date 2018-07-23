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
 * Defines the minimum time between the reception of a request (end of request
 * telegram) and the transmission of the response (begin of response telegram).
 */
public class LocalPortResponseTime {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	/**
	 * Minimium time is 20 ms.
	 */
	public static final LocalPortResponseTime ms20 = new LocalPortResponseTime(
			0, "ms20");
	/**
	 * Minimium time is 200 ms.
	 */
	public static final LocalPortResponseTime ms200 = new LocalPortResponseTime(
			1, "ms200");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (LocalPortResponseTime.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private LocalPortResponseTime(int value, String name) {
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
	public static LocalPortResponseTime forValue(int value) {
		return (LocalPortResponseTime) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}