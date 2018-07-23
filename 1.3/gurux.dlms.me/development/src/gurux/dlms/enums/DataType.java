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
 * DataType enumerates usable types of data in GuruxDLMS.
 */
public class DataType {

	/**
	 * Data type is Array.
	 */
	public static final DataType ARRAY = new DataType(1, "ARRAY");

	/**
	 * Data type is Binary coded decimal.
	 */
	public static final DataType BCD = new DataType(13, "BCD");

	/**
	 * Data type is Bit string.
	 */
	public static final DataType BITSTRING = new DataType(4, "BITSTRING");

	/**
	 * Data type is Boolean.
	 */
	public static final DataType BOOLEAN = new DataType(3, "BOOLEAN");

	/**
	 * Data type is Compact array.
	 */
	public static final DataType COMPACT_ARRAY = new DataType(0x13,
			"COMPACT_ARRAY");

	/**
	 * Data type is Date.
	 */
	public static final DataType DATE = new DataType(0x1A, "DATE");

	/**
	 * Data type is DateTime.
	 */
	public static final DataType DATETIME = new DataType(0x19, "DATETIME");

	/**
	 * Data type is Enum.
	 */
	public static final DataType ENUM = new DataType(0x16, "ENUM");

	/**
	 * Data type is Float32.
	 */
	public static final DataType FLOAT32 = new DataType(0x17, "FLOAT32");

	/**
	 * Data type is Float64.
	 */
	public static final DataType FLOAT64 = new DataType(0x18, "FLOAT64");

	/**
	 * Data type is Int16.
	 */
	public static final DataType INT16 = new DataType(0x10, "INT16");

	/**
	 * Data type is Int32.
	 */
	public static final DataType INT32 = new DataType(5, "INT32");

	/**
	 * Data type is Int64.
	 */
	public static final DataType INT64 = new DataType(20, "INT64");

	/**
	 * Data type is Int8.
	 */
	public static final DataType INT8 = new DataType(15, "INT8");

	/**
	 * By default, no data type is set.
	 */
	public static final DataType NONE = new DataType(0, "NONE");

	/**
	 * Data type is Octet string.
	 */
	public static final DataType OCTET_STRING = new DataType(9, "OCTET_STRING");

	/**
	 * Data type is String.
	 */
	public static final DataType STRING = new DataType(10, "STRING");

	/*
	 * Data type is UTF8 String.
	 */
	public static final DataType STRING_UTF8 = new DataType(12, "STRING_UTF8");

	/**
	 * Data type is Structure.
	 */
	public static final DataType STRUCTURE = new DataType(2, "STRUCTURE");

	/**
	 * Data type is Time.
	 */
	public static final DataType TIME = new DataType(0x1b, "TIME");

	/**
	 * Data type is UInt16.
	 */
	public static final DataType UINT16 = new DataType(0x12, "UINT16");

	/**
	 * Data type is UInt32.
	 */
	public static final DataType UINT32 = new DataType(6, "UINT32");

	/**
	 * Data type is UInt64.
	 */
	public static final DataType UINT64 = new DataType(0x15, "UINT64");

	/**
	 * Data type is UInt8.
	 */
	public static final DataType UINT8 = new DataType(0x11, "UINT8");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private DataType(int value, String name) {
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
		synchronized (DataType.class) {
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
	public static DataType forValue(int value) {
		return (DataType) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}