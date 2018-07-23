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
 * Available media state changes.
 * 
 * @author Gurux Ltd.
 */
public class MediaState {
  /**
   * Media is closed.
   */
  public static MediaState CLOSED = new MediaState(0);

  /**
   * Media is open.
   */
  public static MediaState OPEN = new MediaState(1);

  /**
   * Media is opening.
   */
  public static MediaState OPENING = new MediaState(2);

  /**
   * Media is closing.
   */
  public static MediaState CLOSING = new MediaState(3);

  /**
   * GXClients Media type has changed.
   */
  public static MediaState CHANGED = new MediaState(4);

  /*
   * Constructor.
   * @param value Enumeration value.
   */
  private MediaState(int value) {
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
    synchronized (MediaState.class) {
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
  public static MediaState forValue(int value) {
    return (MediaState) getMappings().get(new Integer(value));
  }
}