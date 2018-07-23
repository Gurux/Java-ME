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
 * Sort methods.
 */
public class SortMethod {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	/**
	 * First in first out
	 * 
	 * When circle buffer is full first item is removed.
	 */
	public static final SortMethod FIFO = new SortMethod(0, "FIFO");
	/**
	 * Last in first out.
	 * 
	 * When circle buffer is full last item is removed.
	 */
	public static final SortMethod LIFO = new SortMethod(1, "LIFO");
	/**
	 * Largest is first.
	 */
	public static final SortMethod LARGEST = new SortMethod(2, "LARGEST");
	/**
	 * Smallest is first.
	 */
	public static final SortMethod SMALLEST = new SortMethod(3, "SMALLEST");
	/**
	 * Nearst to zero is first.
	 */
	public static final SortMethod NEAREST_TO_ZERO = new SortMethod(4,
			"NEAREST_TO_ZERO");
	/**
	 * Farest from zero is first.
	 */
	public static final SortMethod FAREST_FROM_ZERO = new SortMethod(5,
			"FAREST_FROM_ZERO");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (SortMethod.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private SortMethod(int value, String name) {
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
	public static SortMethod forValue(int value) {
		return (SortMethod) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}