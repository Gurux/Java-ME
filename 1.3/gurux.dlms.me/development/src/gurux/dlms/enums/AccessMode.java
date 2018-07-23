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

public class AccessMode {
	/**
	 * No access.
	 */
	public static final AccessMode NO_ACCESS = new AccessMode(0, "NO_ACCESS");
	/**
	 * The client is allowed only reading from the server.
	 */
	public static final AccessMode READ = new AccessMode(1, "READ");
	/**
	 * The client is allowed only writing to the server.
	 */
	public static final AccessMode WRITE = new AccessMode(2, "WRITE");
	/**
	 * The client is allowed both reading from the server and writing to it.
	 */
	public static final AccessMode READ_WRITE = new AccessMode(3, "READ_WRITE");
	public static final AccessMode AUTHENTICATED_READ = new AccessMode(4,
			"AUTHENTICATED_READ");
	public static final AccessMode AUTHENTICATED_WRITE = new AccessMode(5,
			"AUTHENTICATED_WRITE");
	public static final AccessMode AUTHENTICATED_READ_WRITE = new AccessMode(6,
			"AUTHENTICATED_READ_WRITE");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private AccessMode(int value, String name) {
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
		synchronized (AccessMode.class) {
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
	public static AccessMode forValue(int value) {
		return (AccessMode) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}