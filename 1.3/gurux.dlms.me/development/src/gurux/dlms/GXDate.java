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

package gurux.dlms;

import java.util.Date;

import gurux.dlms.enums.DateTimeSkips;

public class GXDate extends GXDateTime {
	/**
	 * Constructor.
	 */
	public GXDate() {
		super();
		getSkip().addElement(DateTimeSkips.HOUR);
		getSkip().addElement(DateTimeSkips.MINUTE);
		getSkip().addElement(DateTimeSkips.SECOND);
		getSkip().addElement(DateTimeSkips.MILLISECOND);
	}

	/**
	 * Constructor.
	 * 
	 * @param forvalue
	 *            Date value.
	 */
	public GXDate(final Date forvalue) {
		super(forvalue);
		getSkip().addElement(DateTimeSkips.HOUR);
		getSkip().addElement(DateTimeSkips.MINUTE);
		getSkip().addElement(DateTimeSkips.SECOND);
		getSkip().addElement(DateTimeSkips.MILLISECOND);
	}

	/**
	 * Constructor.
	 * 
	 * @param year
	 *            Used year.
	 * @param month
	 *            Used month.
	 * @param day
	 *            Used day.
	 */
	public GXDate(final int year, final int month, final int day) {
		super(year, month, day, -1, -1, -1, -1);
	}
}