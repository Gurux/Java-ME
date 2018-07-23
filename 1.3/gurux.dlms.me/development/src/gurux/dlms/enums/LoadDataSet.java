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
 * LoadDataSet describes load dataset errors.
 */
public class LoadDataSet {

	/**
	 * Other error.
	 */
	public static final LoadDataSet OTHER = new LoadDataSet(0, "OTHER");

	/**
	 * Primitive out of sequence.
	 */
	public static final LoadDataSet PRIMITIVE_OUT_OF_SEQUENCE = new LoadDataSet(
			1, "PRIMITIVE_OUT_OF_SEQUENCE");

	/**
	 * Not loadable.
	 */
	public static final LoadDataSet NOT_LOADABLE = new LoadDataSet(2,
			"NOT_LOADABLE");

	/**
	 * Dataset size is too large.
	 */
	public static final LoadDataSet DATASET_SIZE_TOO_LARGE = new LoadDataSet(3,
			"DATASET_SIZE_TOO_LARGE");

	/**
	 * Not awaited segment.
	 */
	public static final LoadDataSet NOT_AWAITED_SEGMENT = new LoadDataSet(4,
			"NOT_AWAITED_SEGMENT");

	/**
	 * Interpretation failure.
	 */
	public static final LoadDataSet INTERPRETATION_FAILURE = new LoadDataSet(5,
			"INTERPRETATION_FAILURE");
	/**
	 * Storage failure.
	 */
	public static final LoadDataSet STORAGE_FAILURE = new LoadDataSet(6,
			"STORAGE_FAILURE");
	/**
	 * Dataset not ready.
	 */
	public static final LoadDataSet DATASET_NOT_READY = new LoadDataSet(7,
			"DATASET_NOT_READY");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private LoadDataSet(int value, String name) {
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
		synchronized (LoadDataSet.class) {
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
	public static LoadDataSet forValue(int value) {
		return (LoadDataSet) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}