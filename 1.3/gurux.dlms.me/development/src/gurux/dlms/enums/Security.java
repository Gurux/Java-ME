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

/**
 * Used security model.
 */
public class Security {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	/**
	 * Transport security is not used.
	 */
	public static final Security NONE = new Security(0, "NONE");
	/**
	 * Authentication security is used.
	 */
	public static final Security AUTHENTICATION = new Security(0x10,
			"AUTHENTICATION");
	/**
	 * Encryption security is used.
	 */
	public static final Security ENCRYPTION = new Security(0x20, "ENCRYPTION");
	/**
	 * Authentication and Encryption security are used.
	 */
	public static final Security AUTHENTICATION_ENCRYPTION = new Security(0x30,
			"AUTHENTICATION_ENCRYPTION");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (Security.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private Security(int value, String name) {
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
	public static Security forValue(int value) {
		return (Security) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}