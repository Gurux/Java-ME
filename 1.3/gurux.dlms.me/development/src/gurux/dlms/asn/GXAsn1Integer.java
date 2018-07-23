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

package gurux.dlms.asn;

import gurux.dlms.GXByteBuffer;

/**
 * ASN1 bit string
 */
public class GXAsn1Integer {
    /**
     * Bit string.
     */
    private byte[] value;

    /**
     * Constructor.
     */
    public GXAsn1Integer() {

    }

    /**
     * Constructor
     * 
     * @param str
     *            Integer.
     */
    public GXAsn1Integer(final byte[] data) {
        if (data == null) {
            throw new IllegalArgumentException("data");
        }
        value = data;
    }

    /**
     * @return Bit string.
     */
    public final byte[] getByteArray() {
        return value;
    }

    /**
     * @return Get integer value as int.
     */
    public final byte toByte() {
        GXByteBuffer bb = new GXByteBuffer(value);
        return bb.getInt8();
    }

    /**
     * @return Get integer value as short.
     */
    public final short toShort() {
        GXByteBuffer bb = new GXByteBuffer(value);
        return bb.getInt16();
    }

    /**
     * @return Get integer value as int.
     */
    public final int toInt() {
        GXByteBuffer bb = new GXByteBuffer(value);
        return bb.getInt32();
    }

    /**
     * @return Get integer value as long.
     */
    public final long toLong() {
        GXByteBuffer bb = new GXByteBuffer(value);
        return bb.getInt64();
    }

    public final String toString() {
        if (value == null) {
            return "";
        }
        return "";
    }
}
