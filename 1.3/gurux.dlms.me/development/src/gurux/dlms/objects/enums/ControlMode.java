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

/*
 * Configures the behaviour of the disconnect control object for all
triggers, i.e. the possible state transitions.
 */
public class ControlMode {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	/*
	 * The disconnect control object is always in 'connected' state,
	 */
	public static final ControlMode NONE = new ControlMode(0, "NONE");
	/*
	 * Disconnection: Remote (b, c), manual (f), local (g) Reconnection: Remote
	 * (d), manual (e).
	 */
	public static final ControlMode MODE_1 = new ControlMode(1, "MODE_1");
	/*
	 * Disconnection: Remote (b, c), manual (f), local (g) Reconnection: Remote
	 * (a), manual (e).
	 */
	public static final ControlMode MODE_2 = new ControlMode(2, "MODE_2");
	/*
	 * Disconnection: Remote (b, c), manual (-), local (g) Reconnection: Remote
	 * (d), manual (e).
	 */
	public static final ControlMode MODE_3 = new ControlMode(3, "MODE_3");
	/*
	 * Disconnection: Remote (b, c), manual (-), local (g) Reconnection: Remote
	 * (a), manual (e)
	 */
	public static final ControlMode MODE_4 = new ControlMode(4, "MODE_4");
	/*
	 * Disconnection: Remote (b, c), manual (f), local (g) Reconnection: Remote
	 * (d), manual (e), local (h),
	 */
	public static final ControlMode MODE_5 = new ControlMode(5, "MODE_5");
	/*
	 * Disconnection: Remote (b, c), manual (-), local (g) Reconnection: Remote
	 * (d), manual (e), local (h)
	 */
	public static final ControlMode MODE_6 = new ControlMode(6, "MODE_6");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (ControlMode.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private ControlMode(int value, String name) {
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
	public static ControlMode forValue(int value) {
		return (ControlMode) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}
