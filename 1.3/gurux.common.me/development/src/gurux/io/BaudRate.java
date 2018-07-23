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
// Copyright  (c) Gurux Ltd
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

package gurux.io;

/**
 * Defines a list of commonly supported serial communication rates =baud rates).
 * 
 * @author Gurux Ltd.
 */
public class BaudRate {
  /**
   * 38,400 baud.
   */
  public static BaudRate BAUD_RATE_38400 = new BaudRate(38400);

  /**
   * 19,200 baud.
   */
  public static BaudRate BAUD_RATE_19200 = new BaudRate(19200);

  /**
   * 9,600 baud.
   */
  public static BaudRate BAUD_RATE_9600 = new BaudRate(9600);

  /**
   * 4,800 baud.
   */
  public static BaudRate BAUD_RATE_4800 = new BaudRate(4800);

  /**
   * 2,400 baud.
   */
  public static BaudRate BAUD_RATE_2400 = new BaudRate(2400);

  /**
   * 1,800 baud.
   */
  public static BaudRate BAUD_RATE_1800 = new BaudRate(1800);

  /**
   * 600 baud.
   */
  public static BaudRate BAUD_RATE_600 = new BaudRate(600);

  /**
   * 300 baud.
   */
  public static BaudRate BAUD_RATE_300 = new BaudRate(300);

  /*
   * Constructor.
   * @param value Enumeration value.
   */
  private BaudRate(int value) {
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
    synchronized (BaudRate.class) {
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
  public static BaudRate forValue(int value) {
    return (BaudRate) getMappings().get(new Integer(value));
  }
}