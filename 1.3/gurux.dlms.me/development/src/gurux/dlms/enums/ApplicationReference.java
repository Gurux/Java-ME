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
 * Application reference describes application errors.
 */
public class ApplicationReference {
	/**
	 * Other error is occurred.
	 */
	public static final ApplicationReference OTHER = new ApplicationReference(0,
			"OTHER");
	/**
	 * Time elapsed.
	 */
	public static final ApplicationReference TIME_ELAPSED = new ApplicationReference(
			1, "TIME_ELAPSED");
	/**
	 * Application unreachable.
	 */
	public static final ApplicationReference APPLICATION_UNREACHABLE = new ApplicationReference(
			2, "APPLICATION_UNREACHABLE");
	/**
	 * Application reference is invalid.
	 */
	public static final ApplicationReference APPLICATION_REFERENCE_INVALID = new ApplicationReference(
			3, "APPLICATION_REFERENCE_INVALID");
	/**
	 * Application context unsupported.
	 */
	public static final ApplicationReference APPLICATION_CONTEXT_UNSUPPORTED = new ApplicationReference(
			4, "APPLICATION_CONTEXT_UNSUPPORTED");
	/**
	 * Provider communication error.
	 */
	public static final ApplicationReference PROVIDER_COMMUNICATION_ERROR = new ApplicationReference(
			5, "PROVIDER_COMMUNICATION_ERROR");
	/**
	 * Deciphering error.
	 */
	public static final ApplicationReference DECIPHERING_ERROR = new ApplicationReference(
			6, "DECIPHERING_ERROR");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private ApplicationReference(int value, String name) {
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
		synchronized (ApplicationReference.class) {
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
	public static ApplicationReference forValue(int value) {
		return (ApplicationReference) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}