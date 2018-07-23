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

public class GXDLMSIp4SetupIpOptionType {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	/**
	 * If this option is present, the device shall be allowed to send security,
	 * compartmentation, handling restrictions and TCC (closed user group)
	 * parameters within its IP Datagrams. The value of the IP-Option- Length
	 * Field must be 11, and the IP-Option-Data shall contain the value of the
	 * Security, Compartments, Handling Restrictions and Transmission Control
	 * Code values, as specified in STD0005 / RFC791.
	 */
	public static final GXDLMSIp4SetupIpOptionType Security = new GXDLMSIp4SetupIpOptionType(
			0x82, "Security");
	/**
	 * If this option is present, the device shall supply routing information to
	 * be used by the gateways in forwarding the datagram to the destination,
	 * and to record the route information. The IP-Option-length and
	 * IP-Option-Data values are specified in STD0005 / RFC 791.
	 */
	public static final GXDLMSIp4SetupIpOptionType LooseSourceAndRecordRoute = new GXDLMSIp4SetupIpOptionType(
			0x83, "LooseSourceAndRecordRoute");
	/**
	 * If this option is present, the device shall supply routing information to
	 * be used by the gateways in forwarding the datagram to the destination,
	 * and to record the route information. The IP-Option-length and
	 * IP-Option-Data values are specified in STD0005 / RFC 791.
	 */
	public static final GXDLMSIp4SetupIpOptionType StrictSourceAndRecordRoute = new GXDLMSIp4SetupIpOptionType(
			0x89, "StrictSourceAndRecordRoute");
	/**
	 * If this option is present, the device shall as well: send originated IP
	 * Datagrams with that option, providing means to record the route of these
	 * Datagrams; as a router, send routed IP Datagrams with the route option
	 * adjusted according to this option. The IP-Option-length and
	 * IP-Option-Data values are specified in STD0005 / RFC 791.
	 */
	public static final GXDLMSIp4SetupIpOptionType RecordRoute = new GXDLMSIp4SetupIpOptionType(
			0x07, "RecordRoute");
	/**
	 * If this option is present, the device shall as well: send originated IP
	 * Datagrams with that option, providing means to time-stamp the datagram in
	 * the route to its destination; as a router, send routed IP Datagrams with
	 * the time-stamp option adjusted according to this option. The
	 * IP-Option-length and IP-Option-Data values are specified in STD0005 / RFC
	 * 791.
	 */
	public static final GXDLMSIp4SetupIpOptionType InternetTimestamp = new GXDLMSIp4SetupIpOptionType(
			0x44, "InternetTimestamp");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (GXDLMSIp4SetupIpOptionType.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private GXDLMSIp4SetupIpOptionType(int value, String name) {
		this.value = value;
		this.name = name;
		getMappings().put(new Integer(value), this);
	}

	/*
	 * Get integer value for enum.
	 */
	public int getValue() {
		return value;
	}

	/*
	 * Convert integer for enum value.
	 */
	public static GXDLMSIp4SetupIpOptionType forValue(int value) {
		return (GXDLMSIp4SetupIpOptionType) getMappings()
				.get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}