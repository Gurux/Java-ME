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

public class GXDLMSPppSetupLcpOptionType {
	public static final GXDLMSPppSetupLcpOptionType MAX_REC_UNIT = new GXDLMSPppSetupLcpOptionType(
			1, "MAX_REC_UNIT");
	public static final GXDLMSPppSetupLcpOptionType ASYNC_CONTROL_CHAR_MAP = new GXDLMSPppSetupLcpOptionType(
			2, "ASYNC_CONTROL_CHAR_MAP");
	public static final GXDLMSPppSetupLcpOptionType AUTH_PROTOCOL = new GXDLMSPppSetupLcpOptionType(
			3, "AUTH_PROTOCOL");
	public static final GXDLMSPppSetupLcpOptionType MAGIC_NUMBER = new GXDLMSPppSetupLcpOptionType(
			5, "MAGIC_NUMBER");
	public static final GXDLMSPppSetupLcpOptionType PROTOCOL_FIELD_COMPRESSION = new GXDLMSPppSetupLcpOptionType(
			7, "PROTOCOL_FIELD_COMPRESSION");
	public static final GXDLMSPppSetupLcpOptionType ADDRESS_AND_CTRL_COMPRESSION = new GXDLMSPppSetupLcpOptionType(
			8, "ADDRESS_AND_CTRL_COMPRESSION");
	public static final GXDLMSPppSetupLcpOptionType FCS_ALTERNATIVES = new GXDLMSPppSetupLcpOptionType(
			9, "FCS_ALTERNATIVES");
	public static final GXDLMSPppSetupLcpOptionType CALLBACK = new GXDLMSPppSetupLcpOptionType(
			13, "CALLBACK");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private GXDLMSPppSetupLcpOptionType(int value, String name) {
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
		synchronized (GXDLMSPppSetupLcpOptionType.class) {
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
	public static GXDLMSPppSetupLcpOptionType forValue(int value) {
		return (GXDLMSPppSetupLcpOptionType) getMappings()
				.get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}