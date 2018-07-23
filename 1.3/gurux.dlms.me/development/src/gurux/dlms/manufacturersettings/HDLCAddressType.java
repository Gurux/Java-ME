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

public class HDLCAddressType 
{
	private final String name;
    private int value;    
    private static java.util.Hashtable mappings;
    
    /*
     * Default HDLC addressing is used.
     */
    public static final HDLCAddressType DEFAULT = new HDLCAddressType(0, "DEFAULT");
    /*
     * Serial number is used to generate HDLC address.
     */
    public static final HDLCAddressType SERIAL_NUMBER = new HDLCAddressType(1, "SERIAL_NUMBER");
    /*
     * Custom HDLC addressing is used. 
     */
    public static final HDLCAddressType CUSTOM = new HDLCAddressType(2, "CUSTOM");

    private static java.util.Hashtable getMappings()
    {
    	if (mappings == null)
        { 
	        synchronized (HDLCAddressType.class)
	        {            
	            if (mappings == null)
	            {                
	                mappings = new java.util.Hashtable();
	            }
	        }
        }
        return mappings;
    }
    
    private HDLCAddressType(int value, String name)
    {
        this.value = value;
        this.name = name;
        getMappings().put(new Integer(value), this);
    }

    /*
     * Get integer value for enum.
     */
    public int getValue()
    {
        return value;
    }
    
    /*
     * Convert integer for enum value.
     */
    public static HDLCAddressType forValue(int value)
    {
        return (HDLCAddressType) getMappings().get(new Integer(value));
    }
    
    public String toString()
	{
        return name;
    }
}