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

package gurux.net.enums;

/**
 * Describes available settings for the media.
 */
public class AvailableMediaSettings {
    /**
     * All network properties are shown.
     */
    public static AvailableMediaSettings ALL = new AvailableMediaSettings(-1);

    /**
     * Is Server is shown.
     */
    public static AvailableMediaSettings SERVER =
            new AvailableMediaSettings(0x1);

    /**
     * Network protocol is shown.
     */
    public static AvailableMediaSettings PROTOCOL =
            new AvailableMediaSettings(0x2);

    /**
     * Port number is shown.
     */
    public static AvailableMediaSettings PORT = new AvailableMediaSettings(0x4);

    /**
     * Host Name is shown.
     */
    public static AvailableMediaSettings HOST = new AvailableMediaSettings(0x8);

    /*
     * Constructor.
     * @param value Enumeration value.
     */
    private AvailableMediaSettings(int value) {
        this.value = value;
        getMappings().put(new Integer(value), this);
    }

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
        synchronized (AvailableMediaSettings.class) {
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
    public static AvailableMediaSettings forValue(int value) {
        return (AvailableMediaSettings) getMappings().get(new Integer(value));
    }
}