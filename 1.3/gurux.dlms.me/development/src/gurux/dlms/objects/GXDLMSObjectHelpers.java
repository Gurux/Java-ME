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

package gurux.dlms.objects;

import java.util.Vector;

final class GXDLMSObjectHelpers {

	/**
	 * Constructor.
	 */
	private GXDLMSObjectHelpers() {

	}

	public static int[] toIntArray(final Vector list) {
		int[] ret = new int[list.size()];
		for (int pos = 0; pos != list.size(); ++pos) {
			ret[pos] = ((Integer) list.elementAt(pos)).intValue();
		}
		return ret;
	}

	public static long[] toLongArray(final Vector list) {
		long[] ret = new long[list.size()];
		for (int pos = 0; pos != list.size(); ++pos) {
			ret[pos] = ((Long) list.elementAt(pos)).longValue();
		}
		return ret;
	}
}
