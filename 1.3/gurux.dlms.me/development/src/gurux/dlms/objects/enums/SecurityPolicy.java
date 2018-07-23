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

package gurux.dlms.objects.enums;

/**
 * Security policy Enforces authentication and/or encryption algorithm provided
 * with security_suite.
 */
public class SecurityPolicy {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	public static final SecurityPolicy NOTHING = new SecurityPolicy(0,
			"NOTHING");
	/**
	 * All messages to be authenticated.
	 */
	public static final SecurityPolicy AUTHENTICATED = new SecurityPolicy(1,
			"AUTHENTICATED");
	/**
	 * All messages to be encrypted.
	 */
	public static final SecurityPolicy ENCRYPTED = new SecurityPolicy(2,
			"ENCRYPTED");
	/**
	 * All messages to be authenticated and encrypted.
	 */
	public static final SecurityPolicy AUTHENTICATED_ENCRYPTED = new SecurityPolicy(
			3, "AUTHENTICATED_ENCRYPTED");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (SecurityPolicy.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private SecurityPolicy(int value, String name) {
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
	public static SecurityPolicy forValue(int value) {
		return (SecurityPolicy) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}