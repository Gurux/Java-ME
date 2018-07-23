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

import java.util.Enumeration;

/**
 * Authentication enumerates the authentication levels.
 */
public class Authentication {
	/**
	 * No authentication is used.
	 */
	public static final Authentication NONE = new Authentication(0, "NONE");
	/**
	 * Low authentication is used.
	 */
	public static final Authentication LOW = new Authentication(1, "LOW");
	/**
	 * High authentication is used.
	 */
	public static final Authentication HIGH = new Authentication(2, "HIGH");
	/*
	 * High authentication is used. Password is hashed with MD5.
	 */
	public static final Authentication HIGH_MD5 = new Authentication(3,
			"HIGH_MD5");
	/*
	 * High authentication is used. Password is hashed with SHA1.
	 */
	public static final Authentication HIGH_SHA1 = new Authentication(4,
			"HIGH_SHA1");
	/*
	 * High authentication is used. Password is hashed with GMAC.
	 */
	public static final Authentication HIGH_GMAC = new Authentication(5,
			"HIGH_GMAC");

	/*
	 * High authentication is used. Password is hashed with SHA256.
	 */
	public static final Authentication HIGH_SHA256 = new Authentication(6,
			"HIGH_SHA256");

	/*
	 * High authentication is used. Password is hashed with ECDSA.
	 */
	public static final Authentication HIGH_ECDSA = new Authentication(7,
			"HIGH_ECDSA");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private Authentication(int value, String name) {
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
		synchronized (Authentication.class) {
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
	public static Authentication forValue(int value) {
		return (Authentication) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}

	public static Authentication valueOf(String value) {
		Authentication val = null;
		Enumeration iterator = getMappings().elements();
		while (iterator.hasMoreElements()) {
			Authentication it = (Authentication) iterator.nextElement();
			if (it.name.equalsIgnoreCase(value)) {
				val = it;
				break;
			}
		}
		return val;
	}
}