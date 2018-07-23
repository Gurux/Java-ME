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

/*
 * DataType enumerates skipped fields from date time.
 */
public class DateTimeSkips {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	/**
	 * Nothing is skipped from date time.
	 */
	public static final DateTimeSkips NONE = new DateTimeSkips(0, "NONE");
	/*
	 * Year part of date time is skipped.
	 */
	public static final DateTimeSkips YEAR = new DateTimeSkips(1, "YEAR");
	/*
	 * Month part of date time is skipped.
	 */
	public static final DateTimeSkips MONTH = new DateTimeSkips(2, "MONTH");
	/*
	 * Day part is skipped.
	 */
	public static final DateTimeSkips DAY = new DateTimeSkips(4, "DAY");
	/*
	 * Day of week part of date time is skipped.
	 */
	public static final DateTimeSkips DAY_OF_WEEK = new DateTimeSkips(8,
			"DAY_OF_WEEK");
	/*
	 * Hours part of date time is skipped.
	 */
	public static final DateTimeSkips HOUR = new DateTimeSkips(0x10, "HOUR");
	/*
	 * Minute part of date time is skipped.
	 */
	public static final DateTimeSkips MINUTE = new DateTimeSkips(0x20,
			"MINUTE");
	/*
	 * Second part of date time is skipped.
	 */
	public static final DateTimeSkips SECOND = new DateTimeSkips(0x40,
			"SECOND");
	/*
	 * Hundreds of seconds part of date time is skipped.
	 */
	public static final DateTimeSkips MILLISECOND = new DateTimeSkips(0x80,
			"MILLISECOND");
	/*
	 * Devitation is now used on write.
	 */
	public static final DateTimeSkips DEVITATION = new DateTimeSkips(0x100,
			"DEVITATION");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (DateTimeSkips.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private DateTimeSkips(int value, String name) {
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

	public String toString() {
		return name;
	}

	/**
	 * Converts the integer value to enumerated value.
	 * 
	 * @param value
	 *            The integer value, which is read from the device.
	 * @return The enumerated value, which represents the integer.
	 * @throws Exception
	 *             If the interger has no representative in enumerated values,
	 *             an exception is thrown.
	 */
	public static DateTimeSkips[] forValue(int value) {
		java.util.Vector types = new java.util.Vector();
		if (value == 0) {
			types.addElement(NONE);
		}
		if ((value & 1) != 0) {
			types.addElement(YEAR);
		}
		if ((value & 2) != 0) {
			types.addElement(MONTH);
		}
		if ((value & 4) != 0) {
			types.addElement(DAY);
		}
		if ((value & 8) != 0) {
			types.addElement(DAY_OF_WEEK);
		}
		if ((value & 0x10) != 0) {
			types.addElement(HOUR);
		}
		if ((value & 0x20) != 0) {
			types.addElement(MINUTE);
		}
		if ((value & 0x40) != 0) {
			types.addElement(SECOND);
		}
		if ((value & 0x80) != 0) {
			types.addElement(MILLISECOND);
		}
		if ((value & 0x100) != 0) {
			types.addElement(DEVITATION);
		}
		DateTimeSkips[] tmp = new DateTimeSkips[types.size()];
		types.copyInto(tmp);
		return tmp;
	}
}
