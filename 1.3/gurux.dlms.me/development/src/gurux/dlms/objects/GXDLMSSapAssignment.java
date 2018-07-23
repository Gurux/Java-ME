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
import gurux.dlms.GXDLMSClient;
import gurux.dlms.GXDLMSSettings;
import gurux.dlms.GXSimpleEntry;
import gurux.dlms.ValueEventArgs;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.internal.GXCommon;

public class GXDLMSSapAssignment extends GXDLMSObject implements IGXDLMSBase {
	private Vector sapAssignmentList;

	/**
	 * Constructor.
	 */
	public GXDLMSSapAssignment() {
		super(ObjectType.SAP_ASSIGNMENT, "0.0.41.0.0.255", 0);
		sapAssignmentList = new Vector();
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 */
	public GXDLMSSapAssignment(final String ln) {
		super(ObjectType.SAP_ASSIGNMENT, ln, 0);
		sapAssignmentList = new Vector();
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 * @param sn
	 *            Short Name of the object.
	 */
	public GXDLMSSapAssignment(final String ln, final int sn) {
		super(ObjectType.SAP_ASSIGNMENT, ln, sn);
		sapAssignmentList = new Vector();
	}

	public final Vector getSapAssignmentList() {
		return sapAssignmentList;
	}

	public final void setSapAssignmentList(final Vector value) {
		sapAssignmentList = value;
	}

	public final Object[] getValues() {
		return new Object[] { getLogicalName(), getSapAssignmentList() };
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
		// SapAssignmentList
		if (!isRead(2)) {
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
		return 1;
	}

	public final DataType getDataType(final int index) {
		if (index == 1) {
			return DataType.OCTET_STRING;
		}
		if (index == 2) {
			return DataType.ARRAY;
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
			int cnt = 0;
			if (sapAssignmentList != null) {
				cnt = sapAssignmentList.size();
			}
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8(DataType.ARRAY.getValue());
			// Add count
			GXCommon.setObjectCount(cnt, data);
			if (cnt != 0) {
				Enumeration iterator = sapAssignmentList.elements();
				while (iterator.hasMoreElements()) {
					GXSimpleEntry it = (GXSimpleEntry) iterator.nextElement();
					data.setUInt8(DataType.STRUCTURE.getValue());
					data.setUInt8(2); // Count
					GXCommon.setData(data, DataType.UINT16, it.getKey());
					GXCommon.setData(data, DataType.OCTET_STRING,
							GXCommon.getBytes((String) it.getValue()));
				}
			}
			return data.array();
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
			sapAssignmentList.removeAllElements();
			if (e.getValue() != null) {
				Object[] arr = (Object[]) e.getValue();
				for (int pos = 0; pos != arr.length; ++pos) {
					Object[] item = (Object[]) arr[pos];
					String str;
					Object tmp = item[0];
					if (tmp instanceof byte[]) {
						str = GXDLMSClient
								.changeType((byte[]) tmp, DataType.STRING)
								.toString();
					} else {
						str = tmp.toString();
					}
					sapAssignmentList.addElement(new GXSimpleEntry(
							new Integer(GXCommon.intValue(item[1])), str));
				}
			}
		} else {
			e.setError(ErrorCode.READ_WRITE_DENIED);
		}
	}
}