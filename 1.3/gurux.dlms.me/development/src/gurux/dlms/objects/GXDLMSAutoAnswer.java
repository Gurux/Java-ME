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
import gurux.dlms.GXDateTime;
import gurux.dlms.GXSimpleEntry;
import gurux.dlms.ValueEventArgs;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.internal.GXCommon;
import gurux.dlms.objects.enums.AutoAnswerStatus;
import gurux.dlms.objects.enums.AutoConnectMode;

public class GXDLMSAutoAnswer extends GXDLMSObject implements IGXDLMSBase {
	private AutoConnectMode mode;
	private Vector listeningWindow;
	private AutoAnswerStatus status = AutoAnswerStatus.INACTIVE;
	private int numberOfCalls;
	private int numberOfRingsInListeningWindow, numberOfRingsOutListeningWindow;

	/**
	 * Constructor.
	 */
	public GXDLMSAutoAnswer() {
		super(ObjectType.AUTO_ANSWER, "0.0.2.2.0.255", 0);
		listeningWindow = new Vector();
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 */
	public GXDLMSAutoAnswer(final String ln) {
		super(ObjectType.AUTO_ANSWER, ln, 0);
		listeningWindow = new Vector();
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 * @param sn
	 *            Short Name of the object.
	 */
	public GXDLMSAutoAnswer(final String ln, final int sn) {
		super(ObjectType.AUTO_ANSWER, ln, sn);
		listeningWindow = new Vector();
	}

	public final AutoConnectMode getMode() {
		return mode;
	}

	public final void setMode(final AutoConnectMode value) {
		mode = value;
	}

	public final Vector getListeningWindow() {
		return listeningWindow;
	}

	public final void setListeningWindow(final Vector value) {
		listeningWindow = value;
	}

	public final AutoAnswerStatus getStatus() {
		return status;
	}

	public final void setStatus(final AutoAnswerStatus value) {
		status = value;
	}

	public final int getNumberOfCalls() {
		return numberOfCalls;
	}

	public final void setNumberOfCalls(final int value) {
		numberOfCalls = value;
	}

	/*
	 * Number of rings within the window defined by ListeningWindow.
	 */
	public final int getNumberOfRingsInListeningWindow() {
		return numberOfRingsInListeningWindow;
	}

	public final void setNumberOfRingsInListeningWindow(final int value) {
		numberOfRingsInListeningWindow = value;
	}

	/*
	 * Number of rings outside the window defined by ListeningWindow.
	 */
	public final int getNumberOfRingsOutListeningWindow() {
		return numberOfRingsOutListeningWindow;
	}

	public final void setNumberOfRingsOutListeningWindow(final int value) {
		numberOfRingsOutListeningWindow = value;
	}

	public final Object[] getValues() {
		String str = String.valueOf(numberOfRingsInListeningWindow) + "/"
				+ String.valueOf(numberOfRingsOutListeningWindow);
		return new Object[] { getLogicalName(), getMode(), getListeningWindow(),
				getStatus(), new Integer(getNumberOfCalls()), str };
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
		// Mode is static and read only once.
		if (!isRead(2)) {
			attributes.addElement(new Integer(2));
		}
		// ListeningWindow is static and read only once.
		if (!isRead(3)) {
			attributes.addElement(new Integer(3));
		}
		// Status is not static.
		if (canRead(4)) {
			attributes.addElement(new Integer(4));
		}

		// NumberOfCalls is static and read only once.
		if (!isRead(5)) {
			attributes.addElement(new Integer(5));
		}
		// NumberOfRingsInListeningWindow is static and read only once.
		if (!isRead(6)) {
			attributes.addElement(new Integer(6));
		}
		return GXDLMSObjectHelpers.toIntArray(attributes);
	}

	/*
	 * Returns amount of attributes.
	 */

	public final int getAttributeCount() {
		return 6;
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
		if (index == 3) {
			return DataType.ARRAY;
		}
		if (index == 4) {
			return DataType.ENUM;
		}
		if (index == 5) {
			return DataType.UINT8;
		}
		if (index == 6) {
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
			return new Integer(getMode().getValue());
		}
		if (e.getIndex() == 3) {
			int cnt = getListeningWindow().size();
			GXByteBuffer buff = new GXByteBuffer();
			buff.setUInt8(DataType.ARRAY.getValue());
			// Add count
			GXCommon.setObjectCount(cnt, buff);
			if (cnt != 0) {
				Enumeration iterator = listeningWindow.elements();
				while (iterator.hasMoreElements()) {
					GXSimpleEntry it = (GXSimpleEntry) iterator.nextElement();
					buff.setUInt8(DataType.STRUCTURE.getValue());
					// Count
					buff.setUInt8(2);
					// Start time
					GXCommon.setData(buff, DataType.OCTET_STRING, it.getKey());
					// End time
					GXCommon.setData(buff, DataType.OCTET_STRING,
							it.getValue());
				}
			}
			return buff.array();
		}
		if (e.getIndex() == 4) {
			return new Integer(getStatus().getValue());
		}
		if (e.getIndex() == 5) {
			return new Integer(getNumberOfCalls());
		}
		if (e.getIndex() == 6) {
			GXByteBuffer buff = new GXByteBuffer();
			buff.setUInt8(DataType.STRUCTURE.getValue());
			GXCommon.setObjectCount(2, buff);
			GXCommon.setData(buff, DataType.UINT8,
					new Integer(numberOfRingsInListeningWindow));
			GXCommon.setData(buff, DataType.UINT8,
					new Integer(numberOfRingsOutListeningWindow));
			return buff.array();
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
			setMode(AutoConnectMode
					.forValue(GXCommon.intValue(e.getValue()) & 0xFF));
		} else if (e.getIndex() == 3) {
			listeningWindow.removeAllElements();
			if (e.getValue() != null) {
				for (int pos = 0; pos != ((Object[]) e
						.getValue()).length; ++pos) {
					Object[] item = (Object[]) ((Object[]) e.getValue())[pos];
					GXDateTime start = (GXDateTime) GXDLMSClient.changeType(
							(byte[]) ((Object[]) item)[0], DataType.DATETIME);
					GXDateTime end = (GXDateTime) GXDLMSClient.changeType(
							(byte[]) ((Object[]) item)[1], DataType.DATETIME);
					listeningWindow.addElement(new GXSimpleEntry(start, end));
				}
			}
		} else if (e.getIndex() == 4) {
			setStatus(
					AutoAnswerStatus.forValue(GXCommon.intValue(e.getValue())));
		} else if (e.getIndex() == 5) {
			setNumberOfCalls(GXCommon.intValue(e.getValue()));
		} else if (e.getIndex() == 6) {
			numberOfRingsInListeningWindow = 0;
			numberOfRingsOutListeningWindow = 0;
			if (e.getValue() != null) {
				numberOfRingsInListeningWindow = GXCommon
						.intValue(((Object[]) e.getValue())[0]);
				numberOfRingsOutListeningWindow = GXCommon
						.intValue(((Object[]) e.getValue())[1]);
			}
		} else {
			e.setError(ErrorCode.READ_WRITE_DENIED);
		}
	}
}