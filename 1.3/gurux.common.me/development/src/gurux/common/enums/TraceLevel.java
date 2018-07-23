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
 * Specifies trace levels.
 * 
 * @author Gurux Ltd.
 */
public class TraceLevel {
  /**
   * Output no tracing and debugging messages.
   */
  public static TraceLevel OFF = new TraceLevel(0);

  /**
   * Output error-handling messages.
   */
  public static TraceLevel ERROR = new TraceLevel(1);

  /**
   * Output warnings and error-handling messages.
   */
  public static TraceLevel WARNING = new TraceLevel(2);

  /**
   * Output informational messages, warnings, and error-handling messages.
   */
  public static TraceLevel INFO = new TraceLevel(3);

  /**
   * Output all debugging and tracing messages.
   */
  public static TraceLevel VERBOSE = new TraceLevel(4);

  /*
   * Constructor.
   * @param value Enumeration value.
   */
  private TraceLevel(int value) {
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
    synchronized (TraceLevel.class) {
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
  public static TraceLevel forValue(int value) {
    return (TraceLevel) getMappings().get(new Integer(value));
  }
}