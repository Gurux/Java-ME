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

import java.util.Enumeration;
import java.util.Vector;

import gurux.dlms.GXByteBuffer;
import gurux.dlms.GXDLMSSettings;
import gurux.dlms.GXSimpleEntry;
import gurux.dlms.ValueEventArgs;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.internal.GXCommon;

public class GXDLMSRegisterActivation extends GXDLMSObject
		implements IGXDLMSBase {
	private Vector registerAssignment = new Vector();
	private Vector maskList = new Vector();
	private byte[] activeMask;

	/**
	 * Constructor.
	 */
	public GXDLMSRegisterActivation() {
		super(ObjectType.REGISTER_ACTIVATION);
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 */
	public GXDLMSRegisterActivation(final String ln) {
		super(ObjectType.REGISTER_ACTIVATION, ln, 0);
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 * @param sn
	 *            Short Name of the object.
	 */
	public GXDLMSRegisterActivation(final String ln, final int sn) {
		super(ObjectType.REGISTER_ACTIVATION, ln, sn);
	}

	/**
	 * @return Register assignment list.
	 */
	public final Vector getRegisterAssignment() {
		return registerAssignment;
	}

	/**
	 * @return Mask list.
	 */
	public final Vector getMaskList() {
		return maskList;
	}

	/**
	 * @return Active mask.
	 */
	public final byte[] getActiveMask() {
		return activeMask;
	}

	/**
	 * @param value
	 *            Active mask.
	 */
	public final void setActiveMask(final byte[] value) {
		activeMask = value;
	}

	/*
	 * Add register.
	 */
	final void addRegister(final GXDLMSObjectDefinition item) {
		registerAssignment.addElement(item);
	}

	/*
	 * Add mask.
	 */
	void addMask() {

	}

	/*
	 * delete mask.
	 */
	void deleteMask() {

	}

	public final Object[] getValues() {
		return new Object[] { getLogicalName(), getRegisterAssignment(),
				getMaskList(), getActiveMask() };
	}

	/*
	 * Returns collection of attributes to read. If attribute is static and
	 * already read or device is returned HW error it is not returned.
	 */

	public final int[] getAttributeIndexToRead() {
		Vector attributes = new Vector();
		// LN is static and read only once.
		if (getLogicalName() == null || getLogicalName().compareTo("") == 0) {
			attributes.addElement(new Integer(1));
		}
		// RegisterAssignment
		if (!isRead(2)) {
			attributes.addElement(new Integer(2));
		}
		// MaskList
		if (!isRead(3)) {
			attributes.addElement(new Integer(3));
		}
		// ActiveMask
		if (!isRead(4)) {
			attributes.addElement(new Integer(4));
		}
		return GXDLMSObjectHelpers.toIntArray(attributes);
	}

	/*
	 * Returns amount of attributes.
	 */

	public final int getAttributeCount() {
		return 4;
	}

	/*
	 * Returns amount of methods.
	 */

	public final int getMethodCount() {
		return 3;
	}

	public final DataType getDataType(final int index) {
		if (index == 1) {
			return DataType.OCTET_STRING;
		}
		if (index == 2) {
			return DataType.ARRAY;
		}
		if (index == 3) {
			return DataType.ARRAY;
		}
		if (index == 4) {
			return DataType.OCTET_STRING;
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
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8(DataType.ARRAY.getValue());
			data.setUInt8(getRegisterAssignment().size());
			Enumeration iterator = registerAssignment.elements();
			while (iterator.hasMoreElements()) {
				GXDLMSObjectDefinition it = (GXDLMSObjectDefinition) iterator
						.nextElement();
				data.setUInt8(DataType.STRUCTURE.getValue());
				data.setUInt8(2);
				GXCommon.setData(data, DataType.UINT16,
						new Integer(it.getClassId().getValue()));
				GXCommon.setData(data, DataType.OCTET_STRING,
						it.getLogicalName());
			}
			return data.array();
		}
		if (e.getIndex() == 3) {
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8(DataType.ARRAY.getValue());
			data.setUInt8(maskList.size());
			// for (Entry<byte[], byte[]> it : maskList) {
			Enumeration iterator = maskList.elements();
			while (iterator.hasMoreElements()) {
				GXSimpleEntry it = (GXSimpleEntry) iterator.nextElement();
				data.setUInt8(DataType.STRUCTURE.getValue());
				data.setUInt8(2);
				GXCommon.setData(data, DataType.OCTET_STRING, it.getKey());
				data.setUInt8(DataType.ARRAY.getValue());
				data.setUInt8(((byte[]) it.getValue()).length);
				data.set((byte[]) it.getValue());
			}
			return data.array();
		}
		if (e.getIndex() == 4) {
			return getActiveMask();
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
			registerAssignment.removeAllElements();
			if (e.getValue() != null) {
				Object[] arr = (Object[]) e.getValue();
				for (int pos = 0; pos != arr.length; ++pos) {
					Object it = arr[pos];
					GXDLMSObjectDefinition item = new GXDLMSObjectDefinition();
					item.setClassId(ObjectType
							.forValue(GXCommon.intValue(((Object[]) it)[0])));
					item.setLogicalName(GXDLMSObject
							.toLogicalName((byte[]) ((Object[]) it)[1]));
					registerAssignment.addElement(item);
				}
			}
		} else if (e.getIndex() == 3) {
			maskList.removeAllElements();
			if (e.getValue() != null) {
				Object[] arr1 = (Object[]) e.getValue();
				for (int pos1 = 0; pos1 != arr1.length; ++pos1) {
					Object it = arr1[pos1];
					byte[] key = (byte[]) ((Object[]) it)[0];
					Object arr = ((Object[]) it)[1];
					byte[] tmp = new byte[((Object[]) arr).length];
					for (int pos = 0; pos != tmp.length; ++pos) {
						tmp[pos] = (byte) GXCommon
								.intValue(((Object[]) arr)[pos]);
					}
					maskList.addElement(new GXSimpleEntry(key, tmp));
				}
			}
		} else if (e.getIndex() == 4) {
			if (e.getValue() == null) {
				setActiveMask(null);
			} else {
				setActiveMask((byte[]) e.getValue());
			}
		} else {
			e.setError(ErrorCode.READ_WRITE_DENIED);
		}
	}
}