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

import gurux.dlms.GXDLMSSettings;
import gurux.dlms.ValueEventArgs;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.internal.GXCommon;
import gurux.dlms.objects.enums.BaudRate;

public class GXDLMSMBusMasterPortSetup extends GXDLMSObject
		implements IGXDLMSBase {
	private BaudRate commSpeed;

	/**
	 * Constructor.
	 */
	public GXDLMSMBusMasterPortSetup() {
		super(ObjectType.MBUS_MASTER_PORT_SETUP);
		commSpeed = BaudRate.BAUDRATE_2400;
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 */
	public GXDLMSMBusMasterPortSetup(final String ln) {
		super(ObjectType.MBUS_MASTER_PORT_SETUP, ln, 0);
		commSpeed = BaudRate.BAUDRATE_2400;
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 * @param sn
	 *            Short Name of the object.
	 */
	public GXDLMSMBusMasterPortSetup(final String ln, final int sn) {
		super(ObjectType.MBUS_MASTER_PORT_SETUP, ln, sn);
		commSpeed = BaudRate.BAUDRATE_2400;
	}

	/**
	 * @return The communication speed supported by the port.
	 */
	public final BaudRate getCommSpeed() {
		return commSpeed;
	}

	/**
	 * @param value
	 *            The communication speed supported by the port.
	 */
	public final void setCommSpeed(final BaudRate value) {
		commSpeed = value;
	}

	public final Object[] getValues() {
		return new Object[] { getLogicalName(), commSpeed };
	}

	public final int[] getAttributeIndexToRead() {
		Vector attributes = new Vector();
		// LN is static and read only once.
		if (getLogicalName() == null || getLogicalName().compareTo("") == 0) {
			attributes.addElement(new Integer(1));
		}
		// CommSpeed
		if (canRead(2)) {
			attributes.addElement(new Integer(2));
		}
		return GXDLMSObjectHelpers.toIntArray(attributes);
	}

	/*
	 * Returns amount of attributes.
	 */

	public final int getAttributeCount() {
		return 2;
	}

	/*
	 * Returns amount of methods.
	 */

	public final int getMethodCount() {
		return 0;
	}

	public final DataType getDataType(final int index) {
		if (index == 1) {
			return DataType.OCTET_STRING;
		}
		if (index == 2) {
			return DataType.ENUM;
		}
		throw new IllegalArgumentException(
				"getDataType failed. Invalid attribute index.");
	}

	/*
	 * Returns value of given attribute.
	 */

	public final Object getValue(final GXDLMSSettings settings,
			final ValueEventArgs e) {
		if (e.getIndex() == 1) {
			return getLogicalName();
		}
		if (e.getIndex() == 2) {
			return new Integer(commSpeed.getValue());
		}
		e.setError(ErrorCode.READ_WRITE_DENIED);
		return null;
	}

	/*
	 * Set value of given attribute.
	 */

	public final void setValue(final GXDLMSSettings settings,
			final ValueEventArgs e) {
		if (e.getIndex() == 1) {
			super.setValue(settings, e);
		} else if (e.getIndex() == 2) {
			commSpeed = BaudRate.forValue(GXCommon.intValue(e.getValue()));
		} else {
			e.setError(ErrorCode.READ_WRITE_DENIED);
		}
	}
}