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

/**
 *
 * Defines the mode controlling the auto dial functionality concerning the
 * timing.
 */
public class AutoConnectMode {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	/*
	 * No auto dialing,
	 */
	public static final AutoConnectMode NO_AUTO_DIALLING = new AutoConnectMode(
			0, "NO_AUTO_DIALLING");
	/**
	 * Auto dialing allowed any time,
	 */
	public static final AutoConnectMode AUTO_DIALLING_ALLOWED_ANYTIME = new AutoConnectMode(
			1, "AUTO_DIALLING_ALLOWED_ANYTIME");
	/**
	 * Auto dialing allowed within the validity time of the calling window.
	 */
	public static final AutoConnectMode AUTO_DIALLING_ALLOWED_CALLING_WINDOW = new AutoConnectMode(
			2, "AUTO_DIALLING_ALLOWED_CALLING_WINDOW");
	/**
	 */
	public static final AutoConnectMode REGULAR_AUTO_DIALLING_ALLOWED_CALLING_WINDOW = new AutoConnectMode(
			3, "REGULAR_AUTO_DIALLING_ALLOWED_CALLING_WINDOW");
	/**
	 * SMS sending via Public Land Mobile Network (PLMN),
	 */
	public static final AutoConnectMode SMS_SENDING_PLMN = new AutoConnectMode(
			4, "SMS_SENDING_PLMN");
	/*
	 * SMS sending via PSTN.
	 */
	public static final AutoConnectMode SMS_SENDING_PSTN = new AutoConnectMode(
			5, "SMS_SENDING_PSTN");
	/*
	 * Email sending.
	 */
	public static final AutoConnectMode EMAIL_SENDING = new AutoConnectMode(6,
			"EMAIL_SENDING");
	public static final AutoConnectMode MANUFACTURE_SPESIFIC = new AutoConnectMode(
			200, "MANUFACTURE_SPESIFIC");
	// (200..255) manufacturer specific modes

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (AutoConnectMode.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private AutoConnectMode(int value, String name) {
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
	public static AutoConnectMode forValue(int value) {
		return (AutoConnectMode) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}
