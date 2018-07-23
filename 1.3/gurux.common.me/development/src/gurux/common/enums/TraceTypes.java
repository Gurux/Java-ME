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

package gurux.common.enums;

/**
 * Trace Type enumerates where trace is sent.
 * 
 * @author Gurux Ltd.
 */
public class TraceTypes {

  /**
   * Data is sent.
   */
  public static TraceTypes SENT = new TraceTypes(0);

  /**
   * Data is received.
   */
  public static TraceTypes RECEIVED = new TraceTypes(1);

  /**
   * Error has occurred.
   */
  public static TraceTypes ERROR = new TraceTypes(2);

  /**
   * Warning.
   */
  public static TraceTypes WARNING = new TraceTypes(3);

  /**
   * Info. Example Media states are notified as info.
   */
  public static TraceTypes INFO = new TraceTypes(4);

  /*
   * Constructor.
   * @param value Enumeration value.
   */
  private TraceTypes(int value) {
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
    synchronized (TraceTypes.class) {
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
  public static TraceTypes forValue(int value) {
    return (TraceTypes) getMappings().get(new Integer(value));
  }
}