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

import java.util.Enumeration;
import java.util.Vector;

/**
 * Security policy Enforces authentication and/or encryption algorithm provided
 * with security suite. This enumeration is used for version 1.
 */
public class SecurityPolicy1 {

	/**
	 * Security is not used.
	 */
	public static final SecurityPolicy1 NOTHING = new SecurityPolicy1(0x0,
			"NOTHING");

	/**
	 * Request is authenticated.
	 */
	public static final SecurityPolicy1 AUTHENTICATED_REQUEST = new SecurityPolicy1(
			0x20, "AUTHENTICATED_REQUEST");

	/**
	 * Request is encrypted.
	 */
	public static final SecurityPolicy1 ENCRYPTED_REQUEST = new SecurityPolicy1(
			0x10, "ENCRYPTED_REQUEST");

	/**
	 * Request is digitally signed.
	 */
	public static final SecurityPolicy1 DIGITALLY_SIGNED_REQUEST = new SecurityPolicy1(
			8, "DIGITALLY_SIGNED_REQUEST");

	/**
	 * Response authenticated.
	 */
	public static final SecurityPolicy1 AUTHENTICATED_RESPONSE = new SecurityPolicy1(
			4, "AUTHENTICATED_RESPONSE");

	/**
	 * Response encrypted.
	 */
	public static final SecurityPolicy1 ENCRYPTED_RESPONSE = new SecurityPolicy1(
			2, "ENCRYPTED_RESPONSE");

	/**
	 * Response is digitally signed.
	 */
	public static final SecurityPolicy1 DIGITALLY_SIGNED_RESPONSE = new SecurityPolicy1(
			1, "DIGITALLY_SIGNED_RESPONSE");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private SecurityPolicy1(int value, String name) {
		intValue = value;
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
	private int intValue;

	/*
	 * Collection of enumeration values.
	 */
	private static java.util.Hashtable mappings;

	/*
	 * Collection of enumeration values.
	 */
	private static java.util.Hashtable getMappings() {
		synchronized (SecurityPolicy1.class) {
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
		return intValue;
	}

	/**
	 * @return Get enumeration constant values.
	 */
	private static SecurityPolicy1[] getEnumConstants() {
		return new SecurityPolicy1[] { AUTHENTICATED_REQUEST, ENCRYPTED_REQUEST,
				DIGITALLY_SIGNED_REQUEST, AUTHENTICATED_RESPONSE,
				ENCRYPTED_RESPONSE, DIGITALLY_SIGNED_RESPONSE };

	}

	/**
	 * Get enumerator from integer value.
	 * 
	 * @param value
	 *            integer value.
	 * @return Enumerator value.
	 */
	public static Vector forValue(final int value) {
		Vector types = new Vector();
		if (value != 0) {
			SecurityPolicy1[] enums = getEnumConstants();
			for (int pos = 0; pos != enums.length; ++pos) {
				if ((enums[pos].intValue & value) == enums[pos].intValue) {
					types.addElement(enums[pos]);
				}
			}
		}
		return types;
	}

	/**
	 * Converts the enumerated value to integer value.
	 * 
	 * @param forValue
	 *            The enumerated value.
	 * @return The integer value.
	 */
	public static int toInteger(final Vector forValue) {
		int tmp = 0;
		Enumeration iterator = forValue.elements();
		while (iterator.hasMoreElements()) {
			SecurityPolicy1 it = (SecurityPolicy1) iterator.nextElement();
			tmp |= it.getValue();
		}
		return tmp;
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}