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

package gurux.io;

/**
 * Specifies the parity bit for a System.IO.Ports.SerialPort object.
 * 
 * @author Gurux Ltd.
 */
public class Parity {
  /**
   * No parity check occurs.
   */
  public static Parity NONE = new Parity(0);
  /**
   * Sets the parity bit so that the count of bits set is an odd number.
   */
  public static Parity ODD = new Parity(1);
  /**
   * Sets the parity bit so that the count of bits set is an even number.
   */
  public static Parity EVEN = new Parity(2);
  /**
   * Leaves the parity bit set to 1.
   */
  public static Parity MARK = new Parity(3);
  /**
   * Leaves the parity bit set to 0.
   */
  public static Parity SPACE = new Parity(4);

  /*
   * Constructor.
   * @param value Enumeration value.
   */
  private Parity(int value) {
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
    synchronized (Parity.class) {
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
  public static Parity forValue(int value) {
    return (Parity) getMappings().get(new Integer(value));
  }
}
