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

package gurux.dlms;

import java.util.Enumeration;

/**
 * RequestTypes enumerates the replies of the server to a client's request,
 * indicating the request type.
 */
class ReleaseRequestReason {
	/**
	 * Client closes connection as normal.
	 */
	public static ReleaseRequestReason NORMAL = new ReleaseRequestReason(0,
			"NORMAL");
	/**
	 * Client closes connection as urgent.
	 */
	public static ReleaseRequestReason URGENT = new ReleaseRequestReason(1,
			"URGENT");
	/**
	 * Client closes connection user defined reason.
	 */
	public static ReleaseRequestReason USER_DEFINED = new ReleaseRequestReason(
			30, "USER_DEFINED");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private ReleaseRequestReason(int value, String name) {
		this.value = value;
		this.name = name;
		getMappings().put(new Integer(value), this);
	}

	/*
	 * Enumeration name.
	 */
	private final String name;

	/*
	 * Enumeration integer value.
	 */
	private int value;

	/*
	 * Collection of enumeration values.
	 */
	private static java.util.Hashtable mappings;

	/*
	 * Collection of enumeration values.
	 */
	private static java.util.Hashtable getMappings() {
		synchronized (ReleaseRequestReason.class) {
			if (mappings == null) {
				mappings = new java.util.Hashtable();
			}
		}
		return mappings;
	}

	/*
	 * Get integer value for enumeration.
	 */
	public int getValue() {
		return value;
	}

	/*
	 * Convert integer to enumeration value.
	 */
	public static ReleaseRequestReason forValue(int value) {
		return (ReleaseRequestReason) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}

	public static ReleaseRequestReason valueOf(String value) {
		ReleaseRequestReason val = null;
		Enumeration iterator = getMappings().elements();
		while (iterator.hasMoreElements()) {
			ReleaseRequestReason it = (ReleaseRequestReason) iterator
					.nextElement();
			if (it.name.equalsIgnoreCase(value)) {
				val = it;
				break;
			}
		}
		return val;
	}
}
