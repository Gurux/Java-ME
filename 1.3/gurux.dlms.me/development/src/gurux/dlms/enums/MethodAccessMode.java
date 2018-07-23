//
// --------------------------------------------------------------------------
//  Gurux Ltd
// 
//
//
// Filename:        $HeadURL$
//
// Version:         $Revision$,
//                  $Date$
//                  $Author$
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
// More information of Gurux products: http://www.gurux.org
//
// This code is licensed under the GNU General Public License v2. 
// Full text may be retrieved at http://www.gnu.org/licenses/gpl-2.0.txt
//---------------------------------------------------------------------------

package gurux.dlms.enums;

public class MethodAccessMode {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	/**
	 * No access.
	 */
	public static final MethodAccessMode NO_ACCESS = new MethodAccessMode(0,
			"NO_ACCESS");
	/**
	 * access.
	 */
	public static final MethodAccessMode ACCESS = new MethodAccessMode(1,
			"ACCESS");
	/**
	 * Authenticated access.
	 */
	public static final MethodAccessMode AUTHENTICATED_ACCESS = new MethodAccessMode(
			2, "AUTHENTICATED_ACCESS");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (MethodAccessMode.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private MethodAccessMode(int value, String name) {
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
	public static MethodAccessMode forValue(int value) {
		return (MethodAccessMode) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}