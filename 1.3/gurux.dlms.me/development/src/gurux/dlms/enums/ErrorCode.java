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
 * Enumerated DLMS error codes.
 * 
 * @author Gurux Ltd.
 */
public class ErrorCode {

	/**
	 * Connection is rejected.
	 */
	public static final ErrorCode REJECTED = new ErrorCode(-2, "REJECTED");

	/**
	 * No error has occurred.
	 */
	public static final ErrorCode OK = new ErrorCode(0, "OK");

	/**
	 * Access Error : Device reports a hardware fault.
	 */
	public static final ErrorCode HARDWARE_FAULT = new ErrorCode(1,
			"HARDWARE_FAULT");

	/**
	 * Access Error : Device reports a temporary failure.
	 */
	public static final ErrorCode TEMPORARY_FAILURE = new ErrorCode(2,
			"TEMPORARY_FAILURE");

	/**
	 * Access Error : Device reports Read-Write denied.
	 */
	public static final ErrorCode READ_WRITE_DENIED = new ErrorCode(3,
			"READ_WRITE_DENIED");

	/**
	 * Access Error : Device reports a undefined object
	 */
	public static final ErrorCode UNDEFINED_OBJECT = new ErrorCode(4,
			"UNDEFINED_OBJECT");

	/**
	 * Access Error : Device reports a inconsistent Class or object
	 */
	public static final ErrorCode INCONSISTENT_CLASS = new ErrorCode(9,
			"INCONSISTENT_CLASS");

	/**
	 * Access Error : Device reports a unavailable object
	 */
	public static final ErrorCode UNAVAILABLE_OBJECT = new ErrorCode(11,
			"UNAVAILABLE_OBJECT");

	/**
	 * Access Error : Device reports a unmatched type
	 */
	public static final ErrorCode UNMATCHED_TYPE = new ErrorCode(12,
			"UNMATCHED_TYPE");

	/**
	 * Access Error : Device reports scope of access violated
	 */
	public static final ErrorCode ACCESS_VIOLATED = new ErrorCode(13,
			"ACCESS_VIOLATED");

	/**
	 * Access Error : Data Block Unavailable.
	 */
	public static final ErrorCode DATA_BLOCK_UNAVAILABLE = new ErrorCode(14,
			"DATA_BLOCK_UNAVAILABLE");

	/**
	 * Access Error : Long Get Or Read Aborted.
	 */
	public static final ErrorCode LONG_GET_OR_READ_ABORTED = new ErrorCode(15,
			"LONG_GET_OR_READ_ABORTED");

	/**
	 * Access Error : No Long Get Or Read In Progress.
	 */
	public static final ErrorCode NO_LONG_GET_OR_READ_IN_PROGRESS = new ErrorCode(
			16, "NO_LONG_GET_OR_READ_IN_PROGRESS");

	/**
	 * Access Error : Long Set Or Write Aborted.
	 */
	public static final ErrorCode LONG_SET_OR_WRITE_ABORTED = new ErrorCode(17,
			"LONG_SET_OR_WRITE_ABORTED");

	/**
	 * Access Error : No Long Set Or Write In Progress.
	 */
	public static final ErrorCode NO_LONG_SET_OR_WRITE_IN_PROGRESS = new ErrorCode(
			18, "NO_LONG_SET_OR_WRITE_IN_PROGRESS");

	/**
	 * Access Error : Data Block Number Invalid.
	 */
	public static final ErrorCode DATA_BLOCK_NUMBER_INVALID = new ErrorCode(19,
			"DATA_BLOCK_NUMBER_INVALID");

	/**
	 * Access Error : Other Reason.
	 */
	public static final ErrorCode OTHER_REASON = new ErrorCode(250,
			"OTHER_REASON");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private ErrorCode(int value, String name) {
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
		synchronized (ErrorCode.class) {
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
	public static ErrorCode forValue(int value) {
		return (ErrorCode) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}
