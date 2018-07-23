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
 * Hardware resource describes hardware errors.
 */
public class HardwareResource {
	/**
	 * Other hardware resource error.
	 */
	public static final HardwareResource OTHER = new HardwareResource(0,
			"OTHER");

	/**
	 * Memory is unavailable.
	 */
	public static final HardwareResource MEMORY_UNAVAILABLE = new HardwareResource(
			1, "MEMORY_UNAVAILABLE");

	/**
	 * Processor resource is unavailable.
	 */
	public static final HardwareResource PROCESSOR_RESOURCE_UNAVAILABLE = new HardwareResource(
			2, "PROCESSOR_RESOURCE_UNAVAILABLE");

	/**
	 * Mass storage is unavailable.
	 */
	public static final HardwareResource MASS_STORAGE_UNAVAILABLE = new HardwareResource(
			3, "MASS_STORAGE_UNAVAILABLE");

	/**
	 * Other resource is unavailable.
	 */
	public static final HardwareResource OTHER_RESOURCE_UNAVAILABLE = new HardwareResource(
			4, "OTHER_RESOURCE_UNAVAILABLE");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private HardwareResource(int value, String name) {
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
		synchronized (HardwareResource.class) {
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
	public static HardwareResource forValue(int value) {
		return (HardwareResource) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}