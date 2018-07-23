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

public class SingleActionScheduleType {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	/**
	 * Size of execution_time = 1. Wildcard in date allowed.
	 */
	public static final SingleActionScheduleType SingleActionScheduleType1 = new SingleActionScheduleType(
			0, "SingleActionScheduleType1");
	/**
	 * Size of execution_time = n. All time values are the same, wildcards in
	 * date not allowed.
	 */
	public static final SingleActionScheduleType SingleActionScheduleType2 = new SingleActionScheduleType(
			1, "SingleActionScheduleType2");
	/**
	 * Size of execution_time = n. All time values are the same, wildcards in
	 * date are allowed,
	 */
	public static final SingleActionScheduleType SingleActionScheduleType3 = new SingleActionScheduleType(
			2, "SingleActionScheduleType3");
	/**
	 * Size of execution_time = n. Time values may be different, wildcards in
	 * date not allowed,
	 */
	public static final SingleActionScheduleType SingleActionScheduleType4 = new SingleActionScheduleType(
			3, "SingleActionScheduleType4");
	/**
	 * Size of execution_time = n. Time values may be different, wildcards in
	 * date are allowed
	 */
	public static final SingleActionScheduleType SingleActionScheduleType5 = new SingleActionScheduleType(
			4, "SingleActionScheduleType5");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (SingleActionScheduleType.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private SingleActionScheduleType(int value, String name) {
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
	public static SingleActionScheduleType forValue(int value) {
		return (SingleActionScheduleType) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}