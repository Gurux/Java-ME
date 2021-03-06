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

import gurux.dlms.GXByteBuffer;
import gurux.dlms.GXDLMSClient;
import gurux.dlms.GXDLMSSettings;
import gurux.dlms.GXDateTime;
import gurux.dlms.ValueEventArgs;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.internal.GXCommon;

public class GXDLMSLimiter extends GXDLMSObject implements IGXDLMSBase {
	private GXDLMSObject monitoredValue;
	private int monitoredAttributeIndex;
	private Object thresholdActive;
	private Object thresholdNormal;
	private Object thresholdEmergency;
	private long minOverThresholdDuration;
	private long minUnderThresholdDuration;
	private GXDLMSEmergencyProfile emergencyProfile;
	private int[] emergencyProfileGroupIDs;
	private boolean emergencyProfileActive;
	private GXDLMSActionItem actionOverThreshold;
	private GXDLMSActionItem actionUnderThreshold;

	/**
	 * Constructor.
	 */
	public GXDLMSLimiter() {
		super(ObjectType.LIMITER);
		emergencyProfile = new GXDLMSEmergencyProfile();
		actionOverThreshold = new GXDLMSActionItem();
		actionUnderThreshold = new GXDLMSActionItem();
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 */
	public GXDLMSLimiter(final String ln) {
		super(ObjectType.LIMITER, ln, 0);
		emergencyProfile = new GXDLMSEmergencyProfile();
		actionOverThreshold = new GXDLMSActionItem();
		actionUnderThreshold = new GXDLMSActionItem();
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 * @param sn
	 *            Short Name of the object.
	 */
	public GXDLMSLimiter(final String ln, final int sn) {
		super(ObjectType.LIMITER, ln, sn);
		emergencyProfile = new GXDLMSEmergencyProfile();
		actionOverThreshold = new GXDLMSActionItem();
		actionUnderThreshold = new GXDLMSActionItem();
	}

	/**
	 * @return Defines an attribute of an object to be monitored.
	 */
	public final GXDLMSObject getMonitoredValue() {
		return monitoredValue;
	}

	/**
	 * @param value
	 *            Defines an attribute of an object to be monitored.
	 */
	public final void setMonitoredValue(final GXDLMSObject value) {
		monitoredValue = value;
	}

	public final int getmonitoredAttributeIndex() {
		return monitoredAttributeIndex;
	}

	public final void setmonitoredAttributeIndex(final int value) {
		monitoredAttributeIndex = value;
	}

	/**
	 * @return Provides the active threshold value to which the attribute
	 *         monitored is compared.
	 */
	public final Object getThresholdActive() {
		return thresholdActive;
	}

	/**
	 * @param value
	 *            Provides the active threshold value to which the attribute
	 *            monitored is compared.
	 */
	public final void setThresholdActive(final Object value) {
		thresholdActive = value;
	}

	/**
	 * @return Provides the threshold value to which the attribute monitored is
	 *         compared when in normal operation.
	 */
	public final Object getThresholdNormal() {
		return thresholdNormal;
	}

	/**
	 * @param value
	 *            Provides the threshold value to which the attribute monitored
	 *            is compared when in normal operation.
	 */
	public final void setThresholdNormal(final Object value) {
		thresholdNormal = value;
	}

	/**
	 * @return Provides the threshold value to which the attribute monitored is
	 *         compared when an emergency profile is active.
	 */
	public final Object getThresholdEmergency() {
		return thresholdEmergency;
	}

	/**
	 * @param value
	 *            Provides the threshold value to which the attribute monitored
	 *            is compared when an emergency profile is active.
	 */
	public final void setThresholdEmergency(final Object value) {
		thresholdEmergency = value;
	}

	/**
	 * @return Defines minimal over threshold duration in seconds required to
	 *         execute the over threshold action.
	 */
	public final long getMinOverThresholdDuration() {
		return minOverThresholdDuration;
	}

	/**
	 * @param value
	 *            Defines minimal over threshold duration in seconds required to
	 *            execute the over threshold action.
	 */
	public final void setMinOverThresholdDuration(final long value) {
		minOverThresholdDuration = value;
	}

	/**
	 * @return Defines minimal under threshold duration in seconds required to
	 *         execute the under threshold action.
	 */
	public final long getMinUnderThresholdDuration() {
		return minUnderThresholdDuration;
	}

	/**
	 * @param value
	 *            Defines minimal under threshold duration in seconds required
	 *            to execute the under threshold action.
	 */
	public final void setMinUnderThresholdDuration(final long value) {
		minUnderThresholdDuration = value;
	}

	public final GXDLMSEmergencyProfile getEmergencyProfile() {
		return emergencyProfile;
	}

	public final int[] getEmergencyProfileGroupIDs() {
		return emergencyProfileGroupIDs;
	}

	public final void setEmergencyProfileGroupIDs(final int[] value) {
		emergencyProfileGroupIDs = value;
	}

	/**
	 * @return Is Emergency Profile active.
	 */
	public final boolean getEmergencyProfileActive() {
		return emergencyProfileActive;
	}

	/**
	 * @param value
	 *            Is Emergency Profile active.
	 */
	public final void setEmergencyProfileActive(final boolean value) {
		emergencyProfileActive = value;
	}

	/**
	 * @return Defines the scripts to be executed when the monitored value
	 *         crosses the threshold for minimal duration time.
	 */
	public final GXDLMSActionItem getActionOverThreshold() {
		return actionOverThreshold;
	}

	/**
	 * @param value
	 *            Defines the scripts to be executed when the monitored value
	 *            crosses the threshold for minimal duration time.
	 */
	public final void setActionOverThreshold(final GXDLMSActionItem value) {
		actionOverThreshold = value;
	}

	/**
	 * @return Defines the scripts to be executed when the monitored value
	 *         crosses the threshold for minimal duration time.
	 */
	public final GXDLMSActionItem getActionUnderThreshold() {
		return actionUnderThreshold;
	}

	/**
	 * @param value
	 *            Defines the scripts to be executed when the monitored value
	 *            crosses the threshold for minimal duration time.
	 */
	public final void setActionUnderThreshold(final GXDLMSActionItem value) {
		actionUnderThreshold = value;
	}

	public final Object[] getValues() {
		return new Object[] { getLogicalName(), monitoredValue, thresholdActive,
				thresholdNormal, thresholdEmergency,
				new Long(minOverThresholdDuration),
				new Long(minUnderThresholdDuration), emergencyProfile,
				emergencyProfileGroupIDs, new Boolean(emergencyProfileActive),
				new Object[] { actionOverThreshold, actionUnderThreshold } };
	}

	public final int[] getAttributeIndexToRead() {
		Vector attributes = new Vector();
		// LN is static and read only once.
		if (getLogicalName() == null || getLogicalName().compareTo("") == 0) {
			attributes.addElement(new Integer(1));
		}
		// MonitoredValue
		if (canRead(2)) {
			attributes.addElement(new Integer(2));
		}

		// ThresholdActive
		if (canRead(3)) {
			attributes.addElement(new Integer(3));
		}

		// ThresholdNormal
		if (canRead(4)) {
			attributes.addElement(new Integer(4));
		}

		// ThresholdEmergency
		if (canRead(5)) {
			attributes.addElement(new Integer(5));
		}

		// MinOverThresholdDuration
		if (canRead(6)) {
			attributes.addElement(new Integer(6));
		}

		// MinUnderThresholdDuration
		if (canRead(7)) {
			attributes.addElement(new Integer(7));
		}

		// EmergencyProfile
		if (canRead(8)) {
			attributes.addElement(new Integer(8));
		}
		// EmergencyProfileGroup
		if (canRead(9)) {
			attributes.addElement(new Integer(9));
		}

		// EmergencyProfileActive
		if (canRead(10)) {
			attributes.addElement(new Integer(10));
		}
		// Actions
		if (canRead(11)) {
			attributes.addElement(new Integer(11));
		}
		return GXDLMSObjectHelpers.toIntArray(attributes);
	}

	/*
	 * Returns amount of attributes.
	 */

	public final int getAttributeCount() {
		return 11;
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
			return DataType.STRUCTURE;
		}
		if (index == 3) {
			return super.getDataType(index);
		}
		if (index == 4) {
			return super.getDataType(index);
		}
		if (index == 5) {
			return super.getDataType(index);
		}
		if (index == 6) {
			return DataType.UINT32;
		}
		if (index == 7) {
			return DataType.UINT32;
		}
		if (index == 8) {
			return DataType.STRUCTURE;
		}
		if (index == 9) {
			return DataType.ARRAY;
		}
		if (index == 10) {
			return DataType.BOOLEAN;
		}
		if (index == 11) {
			return DataType.STRUCTURE;
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
		} else if (e.getIndex() == 2) {
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8(DataType.STRUCTURE.getValue());
			data.setUInt8(3);
			GXCommon.setData(data, DataType.INT16,
					new Integer(monitoredValue.getObjectType().getValue()));
			GXCommon.setData(data, DataType.OCTET_STRING,
					monitoredValue.getLogicalName());
			// TODO: GXCommon.setData(data, DataType.UINT8,
			// MonitoredValue.getSelectedAttributeIndex());
			return data.array();
		} else if (e.getIndex() == 3) {
			return thresholdActive;
		} else if (e.getIndex() == 4) {
			return thresholdNormal;
		} else if (e.getIndex() == 5) {
			return thresholdEmergency;
		} else if (e.getIndex() == 6) {
			return new Long(minOverThresholdDuration);
		} else if (e.getIndex() == 7) {
			return new Long(minUnderThresholdDuration);
		} else if (e.getIndex() == 8) {
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8(DataType.STRUCTURE.getValue());
			data.setUInt8(3);
			GXCommon.setData(data, DataType.UINT16,
					new Integer(emergencyProfile.getID()));
			GXCommon.setData(data, DataType.OCTET_STRING,
					emergencyProfile.getActivationTime());
			GXCommon.setData(data, DataType.UINT32,
					new Long(emergencyProfile.getDuration()));
			return data.array();
		} else if (e.getIndex() == 9) {
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8((byte) DataType.ARRAY.getValue());
			data.setUInt8((byte) emergencyProfileGroupIDs.length);
			for (int pos = 0; pos != emergencyProfileGroupIDs.length; ++pos) {
				Object it = new Integer(emergencyProfileGroupIDs[pos]);
				GXCommon.setData(data, DataType.UINT16, it);
			}
			return data.array();
		} else if (e.getIndex() == 10) {
			return new Boolean(emergencyProfileActive);
		} else if (e.getIndex() == 11) {
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8(DataType.STRUCTURE.getValue());
			data.setUInt8(2);
			data.setUInt8(DataType.STRUCTURE.getValue());
			data.setUInt8(2);
			GXCommon.setData(data, DataType.OCTET_STRING,
					actionOverThreshold.getLogicalName());
			GXCommon.setData(data, DataType.UINT16,
					new Integer(actionOverThreshold.getScriptSelector()));
			data.setUInt8(DataType.STRUCTURE.getValue());
			data.setUInt8(2);
			GXCommon.setData(data, DataType.OCTET_STRING,
					actionUnderThreshold.getLogicalName());
			GXCommon.setData(data, DataType.UINT16,
					new Integer(actionUnderThreshold.getScriptSelector()));
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
			ObjectType ot = ObjectType.forValue(
					GXCommon.intValue((((Object[]) e.getValue())[0])));
			String ln = GXDLMSClient
					.changeType((byte[]) ((Object[]) e.getValue())[1],
							DataType.OCTET_STRING)
					.toString();
			monitoredAttributeIndex = GXCommon
					.intValue((((Object[]) e.getValue())[2]));
			monitoredValue = settings.getObjects().findByLN(ot, ln);
		} else if (e.getIndex() == 3) {
			thresholdActive = e.getValue();
		} else if (e.getIndex() == 4) {
			thresholdNormal = e.getValue();
		} else if (e.getIndex() == 5) {
			thresholdEmergency = e.getValue();
		} else if (e.getIndex() == 6) {
			minOverThresholdDuration = GXCommon.intValue(e.getValue());
		} else if (e.getIndex() == 7) {
			minUnderThresholdDuration = GXCommon.intValue(e.getValue());
		} else if (e.getIndex() == 8) {
			Object[] tmp = (Object[]) e.getValue();
			emergencyProfile.setID(GXCommon.intValue(tmp[0]));
			emergencyProfile.setActivationTime((GXDateTime) GXDLMSClient
					.changeType((byte[]) tmp[1], DataType.DATETIME));
			emergencyProfile.setDuration(((Long) tmp[2]).longValue());
		} else if (e.getIndex() == 9) {
			Vector list = new Vector();
			if (e.getValue() != null) {
				Object[] arr = (Object[]) e.getValue();
				for (int pos = 0; pos != arr.length; ++pos) {
					list.addElement(new Integer(GXCommon.intValue(arr[pos])));
				}
			}
			emergencyProfileGroupIDs = GXDLMSObjectHelpers.toIntArray(list);
		} else if (e.getIndex() == 10) {
			emergencyProfileActive = ((Boolean) e.getValue()).booleanValue();
		} else if (e.getIndex() == 11) {
			Object[] tmp = (Object[]) e.getValue();
			Object[] tmp1 = (Object[]) tmp[0];
			Object[] tmp2 = (Object[]) tmp[1];
			actionOverThreshold.setLogicalName(GXDLMSClient
					.changeType((byte[]) tmp1[0], DataType.OCTET_STRING)
					.toString());
			actionOverThreshold.setScriptSelector(GXCommon.intValue((tmp1[1])));
			actionUnderThreshold.setLogicalName(GXDLMSClient
					.changeType((byte[]) tmp2[0], DataType.OCTET_STRING)
					.toString());
			actionUnderThreshold
					.setScriptSelector(GXCommon.intValue((tmp2[1])));
		} else {
			e.setError(ErrorCode.READ_WRITE_DENIED);
		}
	}
}