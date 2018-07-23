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

package gurux.dlms.manufacturersettings;

/**
 * Enumerates inactivity modes that are used, when communicating with IEC using
 * serial port connection.
 */
public class InactivityMode {
    public static final InactivityMode NONE = new InactivityMode(0, "NONE");
     /*
     * Keep alive message is sent, only if there is no traffic on the active
     * connection.
     */
    public static final InactivityMode KEEPALIVE = new InactivityMode(1, "KEEPALIVE");
    /*
     * Connection is reopened, if there is no traffic on the active connection.
     */
    public static final InactivityMode REOPEN = new InactivityMode(2, "REOPEN");
    /*
     * Connection is reopened, even if there is traffic on the active
     * connection.
     */
    public static final InactivityMode REOPENACTIVE = new InactivityMode(3, "REOPENACTIVE");
    /*
     * Closes connection, if there is no traffic on the active connection.
     */
    public static final InactivityMode DISCONNECT = new InactivityMode(4, "DISCONNECT");

    /*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private InactivityMode(int value, String name) {
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
		synchronized (InactivityMode.class) {
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
	public static InactivityMode forValue(int value) {
		return (InactivityMode) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}