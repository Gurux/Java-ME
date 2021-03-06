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
 * VdeState error describes Vde state errors.
 */
public class VdeStateError {
	/**
	 * Other error.
	 */
	public static final VdeStateError OTHER = new VdeStateError(0, "OTHER");
	/**
	 * No DLMS context.
	 */
	public static final VdeStateError NO_DLMS_CONTEXT = new VdeStateError(1,
			"NO_DLMS_CONTEXT");
	/**
	 * Loading dataset error.
	 */
	public static final VdeStateError LOADING_DATASET = new VdeStateError(2,
			"LOADING_DATASET");
	/**
	 * Status No change.
	 */
	public static final VdeStateError STATUS_NO_CHANGE = new VdeStateError(3,
			"STATUS_NO_CHANGE");
	/**
	 * Status Inoperable.
	 */
	public static final VdeStateError STATUS_INOPERABLE = new VdeStateError(4,
			"STATUS_INOPERABLE");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private VdeStateError(int value, String name) {
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
		synchronized (VdeStateError.class) {
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
	public static VdeStateError forValue(int value) {
		return (VdeStateError) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}