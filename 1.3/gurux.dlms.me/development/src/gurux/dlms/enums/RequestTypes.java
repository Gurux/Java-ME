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

/**
 * Reserved for internal use.
 */
package gurux.dlms.enums;

/**
 * RequestTypes enumerates the replies of the server to a client's request,
 * indicating the request type.
 */
public class RequestTypes {
	/**
	 * No more data is available for the request.
	 * <p />
	 * Integer value=0
	 */
	public static final RequestTypes NONE = new RequestTypes(0, "NONE");

	/**
	 * More data blocks are available for the request.
	 * <p />
	 * Integer value=1
	 */
	public static final RequestTypes DATABLOCK = new RequestTypes(1,
			"DATABLOCK");

	/**
	 * More data frames are available for the request.
	 * <p />
	 * Integer value=2
	 */
	public static final RequestTypes FRAME = new RequestTypes(2, "FRAME");

	/*
	 * Send Both.
	 */
	public static final RequestTypes BOTH = new RequestTypes(3, "BOTH");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private RequestTypes(int value, String name) {
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
		synchronized (RequestTypes.class) {
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
	public static RequestTypes forValue(int value) {
		return (RequestTypes) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}