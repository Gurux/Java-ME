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
 * Initiate describes onitiate errors.
 */
public class Initiate {
	/**
	 * Other error.
	 */
	public static final Initiate OTHER = new Initiate(0, "OTHER");
	/**
	 * Dlms version is too low.
	 */
	public static final Initiate DLMS_VERSION_TOO_LOW = new Initiate(1,
			"DLMS_VERSION_TOO_LOW");
	/**
	 * Incompatible conformance.
	 */
	public static final Initiate INCOMPATIBLE_CONFORMANCE = new Initiate(2,
			"INCOMPATIBLE_CONFORMANCE");
	/**
	 * PDU size is too short.
	 */
	public static final Initiate PDU_SIZE_TOOSHORT = new Initiate(3,
			"PDU_SIZE_TOOSHORT");
	/**
	 * Refused by the VDE handler.
	 */
	public static final Initiate REFUSED_BY_THE_VDE_HANDLER = new Initiate(4,
			"REFUSED_BY_THE_VDE_HANDLER");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private Initiate(int value, String name) {
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
		synchronized (Initiate.class) {
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
	public static Initiate forValue(int value) {
		return (Initiate) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}