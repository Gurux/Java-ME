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

public class GXDLMSMessageHandler extends GXDLMSObject implements IGXDLMSBase {
	private Vector listeningWindow;
	private String[] allowedSenders;
	private final Vector sendersAndActions;

	/**
	 * Constructor.
	 */
	public GXDLMSMessageHandler() {
		super(ObjectType.MESSAGE_HANDLER);
		listeningWindow = new Vector();
		sendersAndActions = new Vector();
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 */
	public GXDLMSMessageHandler(final String ln) {
		super(ObjectType.MESSAGE_HANDLER, ln, 0);
		listeningWindow = new Vector();
		sendersAndActions = new Vector();
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 * @param sn
	 *            Short Name of the object.
	 */
	public GXDLMSMessageHandler(final String ln, final int sn) {
		super(ObjectType.MESSAGE_HANDLER, ln, sn);
		listeningWindow = new Vector();
		sendersAndActions = new Vector();
	}

	/**
	 * @return Listening Window.
	 */
	public final Vector getListeningWindow() {
		return listeningWindow;
	}

	/**
	 * @return List of allowed Senders.
	 */
	public final String[] getAllowedSenders() {
		return allowedSenders;
	}

	/**
	 * @param value
	 *            List of allowed Senders.
	 */
	public final void setAllowedSenders(final String[] value) {
		allowedSenders = value;
	}

	/**
	 * @return Contains the logical name of a "Script table" object and the
	 *         script selector of the script to be executed if an empty message
	 *         is received from a matching sender.
	 */
	public final Vector getSendersAndActions() {
		return sendersAndActions;
	}

	public final Object[] getValues() {
		return new Object[] { getLogicalName(), listeningWindow, allowedSenders,
				sendersAndActions };
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
		// ListeningWindow
		if (canRead(2)) {
			attributes.addElement(new Integer(2));
		}
		// AllowedSenders
		if (canRead(3)) {
			attributes.addElement(new Integer(3));
		}
		// SendersAndActions
		if (canRead(4)) {
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
		return 0;
	}

	public final DataType getDataType(final int index) {
		if (index == 1) {
			return DataType.OCTET_STRING;
		}
		// ListeningWindow
		if (index == 2) {
			return DataType.ARRAY;
		}
		// AllowedSenders
		if (index == 3) {
			return DataType.ARRAY;
		}
		// SendersAndActions
		if (index == 4) {
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
			GXByteBuffer buff = new GXByteBuffer();
			buff.setUInt8(DataType.ARRAY.getValue());
			GXCommon.setObjectCount(listeningWindow.size(), buff);
			Enumeration iterator = listeningWindow.elements();
			while (iterator.hasMoreElements()) {
				GXSimpleEntry it = (GXSimpleEntry) iterator.nextElement();
				buff.setUInt8(DataType.STRUCTURE.getValue());
				buff.setUInt8(2);
				GXCommon.setData(buff, DataType.OCTET_STRING, it.getKey());
				GXCommon.setData(buff, DataType.OCTET_STRING, it.getValue());
			}
			return buff.array();
		}
		if (e.getIndex() == 3) {
			GXByteBuffer buff = new GXByteBuffer();
			buff.setUInt8(DataType.ARRAY.getValue());
			GXCommon.setObjectCount(allowedSenders.length, buff);
			for (int pos = 0; pos != allowedSenders.length; ++pos) {
				String it = allowedSenders[pos];
				GXCommon.setData(buff, DataType.OCTET_STRING, it.getBytes());
			}
			return buff.array();
		}
		if (e.getIndex() == 4) {
			GXByteBuffer buff = new GXByteBuffer();
			buff.setUInt8(DataType.ARRAY.getValue());
			GXCommon.setObjectCount(sendersAndActions.size(), buff);
			Enumeration iterator = sendersAndActions.elements();
			while (iterator.hasMoreElements()) {
				GXSimpleEntry it = (GXSimpleEntry) iterator.nextElement();
				buff.setUInt8(DataType.STRUCTURE.getValue());
				buff.setUInt8(2);
				GXCommon.setData(buff, DataType.OCTET_STRING,
						((String) it.getKey()).getBytes());
				// TODO: GXCommon.SetData(buff, DataType.OCTET_STRING,
			}
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
			listeningWindow.removeAllElements();
			if (e.getValue() instanceof Object[]) {
				Object[] arr = (Object[]) e.getValue();
				for (int pos = 0; pos != arr.length; ++pos) {
					Object[] tmp = (Object[]) arr[pos];
					GXDateTime start = (GXDateTime) GXDLMSClient
							.changeType((byte[]) tmp[0], DataType.DATETIME);
					GXDateTime end = (GXDateTime) GXDLMSClient
							.changeType((byte[]) tmp[1], DataType.DATETIME);
					listeningWindow.addElement(new GXSimpleEntry(start, end));
				}
			}

		} else if (e.getIndex() == 3) {
			if (e.getValue() instanceof Object[]) {
				Object[] arr = (Object[]) e.getValue();
				allowedSenders = new String[arr.length];
				for (int pos = 0; pos != arr.length; ++pos) {
					allowedSenders[pos] = (new String((byte[]) arr[pos]));
				}
			} else {
				allowedSenders = new String[0];
			}
		} else if (e.getIndex() == 4) {
			sendersAndActions.removeAllElements();
			// TODO:
			/*
			 * if (value instanceof Object[]) { for (Object it : (Object[])
			 * value) { Object[] tmp = (Object[]) it; String id = new
			 * String((byte[]) tmp[0]); Object[] tmp2 = (Object[]) tmp[1];
			 * KeyValuePair<int, GXDLMSScriptAction> executed_script = new
			 * KeyValuePair<int, GXDLMSScriptAction>(Convert.ToInt32(tmp2[1],
			 * tmp2[2])); SendersAndActions.addElement(new KeyValuePair<string,
			 * KeyValuePair<int, GXDLMSScriptAction>>(id, tmp[1] as
			 * GXDateTime)); } }
			 */
		} else {
			e.setError(ErrorCode.READ_WRITE_DENIED);
		}
	}
}