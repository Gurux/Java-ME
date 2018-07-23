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
 * SourceDiagnostic enumerates the error codes for reasons that can cause the
 * server to reject the client.
 */
public class SourceDiagnostic {
	/**
	 * OK.
	 */
	public static final SourceDiagnostic NONE = new SourceDiagnostic(0, "NONE");
	/*
	 * No reason is given.
	 */
	public static final SourceDiagnostic NO_REASON_GIVEN = new SourceDiagnostic(
			1, "NO_REASON_GIVEN");
	/*
	 * The application context name is not supported.
	 */
	public static final SourceDiagnostic NOT_SUPPORTED = new SourceDiagnostic(2,
			"NOT_SUPPORTED");
	/*
	 * The authentication mechanism name is not recognized.
	 */
	public static final SourceDiagnostic NOT_RECOGNISED = new SourceDiagnostic(
			11, "NOT_RECOGNISED");
	/*
	 * Authentication mechanism name is required.
	 */
	public static final SourceDiagnostic MECHANISM_NAME_REGUIRED = new SourceDiagnostic(
			12, "MECHANISM_NAME_REGUIRED");

	/*
	 * Authentication failure.
	 */
	public static final SourceDiagnostic AUTHENTICATION_FAILURE = new SourceDiagnostic(
			13, "AUTHENTICATION_FAILURE");

	/*
	 * Authentication is required.
	 */
	public static final SourceDiagnostic AUTHENTICATION_REQUIRED = new SourceDiagnostic(
			14, "AUTHENTICATION_REQUIRED");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private SourceDiagnostic(int value, String name) {
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
		synchronized (SourceDiagnostic.class) {
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
	public static SourceDiagnostic forValue(int value) {
		return (SourceDiagnostic) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}