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

public class GXDLMSPppSetupIPCPOptionType {
	/* 
	 * 
	 */
	public static final GXDLMSPppSetupIPCPOptionType IP_COMPRESSION_PROTOCOL = new GXDLMSPppSetupIPCPOptionType(
			2, "IP_COMPRESSION_PROTOCOL");

	/*
	 * 
	 */
	public static final GXDLMSPppSetupIPCPOptionType PREF_LOCAL_IP = new GXDLMSPppSetupIPCPOptionType(
			3, "PREF_LOCAL_IP");

	/*
	 * 
	 */
	public static final GXDLMSPppSetupIPCPOptionType PREF_PEER_IP = new GXDLMSPppSetupIPCPOptionType(
			20, "PREF_PEER_IP");

	/*
	 * 
	 */
	public static final GXDLMSPppSetupIPCPOptionType GAO = new GXDLMSPppSetupIPCPOptionType(
			21, "GAO");

	/*
	 * 
	 */
	public static final GXDLMSPppSetupIPCPOptionType USIP = new GXDLMSPppSetupIPCPOptionType(
			22, "USIP");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private GXDLMSPppSetupIPCPOptionType(int value, String name) {
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
		synchronized (GXDLMSPppSetupIPCPOptionType.class) {
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
	public static GXDLMSPppSetupIPCPOptionType forValue(int value) {
		return (GXDLMSPppSetupIPCPOptionType) getMappings()
				.get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}