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
import gurux.dlms.ValueEventArgs;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.internal.GXCommon;
import gurux.dlms.objects.enums.GXDLMSIp4SetupIpOptionType;

public class GXDLMSIp4Setup extends GXDLMSObject implements IGXDLMSBase {
	private String dataLinkLayerReference;
	private String ipAddress;
	private long[] multicastIPAddress;
	private GXDLMSIp4SetupIpOption[] ipOptions;
	private long subnetMask;
	private long gatewayIPAddress;
	private boolean useDHCP;
	private long primaryDNSAddress;
	private long secondaryDNSAddress;

	/**
	 * Constructor.
	 */
	public GXDLMSIp4Setup() {
		this("0.0.25.1.0.255");
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 */
	public GXDLMSIp4Setup(final String ln) {
		this(ln, 0);
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 * @param sn
	 *            Short Name of the object.
	 */
	public GXDLMSIp4Setup(final String ln, final int sn) {
		super(ObjectType.IP4_SETUP, ln, sn);
	}

	public final String getDataLinkLayerReference() {
		return dataLinkLayerReference;
	}

	public final void setDataLinkLayerReference(final String value) {
		dataLinkLayerReference = value;
	}

	public final String getIPAddress() {
		return ipAddress;
	}

	public final void setIPAddress(final String value) {
		ipAddress = value;
	}

	public final long[] getMulticastIPAddress() {
		return multicastIPAddress;
	}

	public final void setMulticastIPAddress(final long[] value) {
		multicastIPAddress = value;
	}

	public final GXDLMSIp4SetupIpOption[] getIPOptions() {
		return ipOptions;
	}

	public final void setIPOptions(final GXDLMSIp4SetupIpOption[] value) {
		ipOptions = value;
	}

	public final long getSubnetMask() {
		return subnetMask;
	}

	public final void setSubnetMask(final long value) {
		subnetMask = value;
	}

	public final long getGatewayIPAddress() {
		return gatewayIPAddress;
	}

	public final void setGatewayIPAddress(final long value) {
		gatewayIPAddress = value;
	}

	public final boolean getUseDHCP() {
		return useDHCP;
	}

	public final void setUseDHCP(final boolean value) {
		useDHCP = value;
	}

	public final long getPrimaryDNSAddress() {
		return primaryDNSAddress;
	}

	public final void setPrimaryDNSAddress(final long value) {
		primaryDNSAddress = value;
	}

	public final long getSecondaryDNSAddress() {
		return secondaryDNSAddress;
	}

	public final void setSecondaryDNSAddress(final long value) {
		secondaryDNSAddress = value;
	}

	public final Object[] getValues() {
		return new Object[] { getLogicalName(), getDataLinkLayerReference(),
				getIPAddress(), getMulticastIPAddress(), getIPOptions(),
				new Long(getSubnetMask()), new Long(getGatewayIPAddress()),
				new Boolean(getUseDHCP()), new Long(getPrimaryDNSAddress()),
				new Long(getSecondaryDNSAddress()) };
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
		// DataLinkLayerReference
		if (!isRead(2)) {
			attributes.addElement(new Integer(2));
		}
		// IPAddress
		if (canRead(3)) {
			attributes.addElement(new Integer(3));
		}
		// MulticastIPAddress
		if (canRead(4)) {
			attributes.addElement(new Integer(4));
		}
		// IPOptions
		if (canRead(5)) {
			attributes.addElement(new Integer(5));
		}
		// SubnetMask
		if (canRead(6)) {
			attributes.addElement(new Integer(6));
		}
		// GatewayIPAddress
		if (canRead(7)) {
			attributes.addElement(new Integer(7));
		}
		// UseDHCP
		if (!isRead(8)) {
			attributes.addElement(new Integer(8));
		}
		// PrimaryDNSAddress
		if (canRead(9)) {
			attributes.addElement(new Integer(9));
		}
		// SecondaryDNSAddress
		if (canRead(10)) {
			attributes.addElement(new Integer(10));
		}
		return GXDLMSObjectHelpers.toIntArray(attributes);
	}

	/*
	 * Returns amount of attributes.
	 */

	public final int getAttributeCount() {
		return 10;
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
			return DataType.OCTET_STRING;
		}
		if (index == 3) {
			return DataType.UINT32;
		}
		if (index == 4) {
			return DataType.ARRAY;
		}
		if (index == 5) {
			return DataType.ARRAY;
		}
		if (index == 6) {
			return DataType.UINT32;
		}
		if (index == 7) {
			return DataType.UINT32;
		}
		if (index == 8) {
			return DataType.BOOLEAN;
		}
		if (index == 9) {
			return DataType.UINT32;
		}
		if (index == 10) {
			return DataType.UINT32;
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
			return this.getDataLinkLayerReference();
		}
		if (e.getIndex() == 3) {
			if (getIPAddress() == null || getIPAddress().length() == 0) {
				return new Integer(0);
			}
			// TODO: Java ME is not supporting InetAddress.
			throw new RuntimeException("Invalid IP address.");

		}
		if (e.getIndex() == 4) {
			return this.getMulticastIPAddress();
		}
		if (e.getIndex() == 5) {
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8(DataType.ARRAY.getValue());
			if (ipOptions == null) {
				data.setUInt8(0);
			} else {
				GXCommon.setObjectCount(ipOptions.length, data);
				for (int pos = 0; pos != ipOptions.length; ++pos) {
					GXDLMSIp4SetupIpOption it = ipOptions[pos];
					data.setUInt8(DataType.STRUCTURE.getValue());
					data.setUInt8(3);
					GXCommon.setData(data, DataType.UINT8, it.getType());
					GXCommon.setData(data, DataType.UINT8,
							new Integer(it.getLength()));
					GXCommon.setData(data, DataType.OCTET_STRING, it.getData());
				}
			}
			return data.array();
		}
		if (e.getIndex() == 6) {
			return new Long(this.getSubnetMask());
		}
		if (e.getIndex() == 7) {
			return new Long(this.getGatewayIPAddress());
		}
		if (e.getIndex() == 8) {
			return new Boolean(this.getUseDHCP());
		}
		if (e.getIndex() == 9) {
			return new Long(this.getPrimaryDNSAddress());
		}
		if (e.getIndex() == 10) {
			return new Long(this.getSecondaryDNSAddress());
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
			if (e.getValue() instanceof String) {
				this.setDataLinkLayerReference(e.getValue().toString());
			} else {
				this.setDataLinkLayerReference(
						GXDLMSClient.changeType((byte[]) e.getValue(),
								DataType.OCTET_STRING).toString());
			}
		} else if (e.getIndex() == 3) {
			GXByteBuffer bb = new GXByteBuffer();
			bb.setUInt32(GXCommon.intValue(e.getValue()));
			bb.reverse();
			// TODO: Java ME is not support this.
			// setIPAddress(InetAddress.getByAddress(bb.array()).toString());
		} else if (e.getIndex() == 4) {
			Vector data = new Vector();
			if (e.getValue() != null) {
				Object[] arr = (Object[]) e.getValue();
				for (int pos = 0; pos != arr.length; ++pos) {
					Object it = arr[pos];
					data.addElement(new Long(GXCommon.intValue(it)));
				}
			}
			setMulticastIPAddress(GXDLMSObjectHelpers.toLongArray(data));
		} else if (e.getIndex() == 5) {
			Vector data = new Vector();
			if (e.getValue() != null) {
				Object[] arr = (Object[]) e.getValue();
				for (int pos = 0; pos != arr.length; ++pos) {
					Object it = arr[pos];
					GXDLMSIp4SetupIpOption item = new GXDLMSIp4SetupIpOption();
					item.setType(GXDLMSIp4SetupIpOptionType
							.forValue(GXCommon.intValue(((Object[]) it)[0])));
					item.setLength(
							(short) GXCommon.intValue((((Object[]) it)[1])));
					item.setData((byte[]) ((Object[]) it)[2]);
					data.addElement(item);
				}
			}
			ipOptions = new GXDLMSIp4SetupIpOption[data.size()];
			data.copyInto(ipOptions);
		} else if (e.getIndex() == 6) {
			setSubnetMask(GXCommon.intValue(e.getValue()));
		} else if (e.getIndex() == 7) {
			setGatewayIPAddress(GXCommon.intValue(e.getValue()));
		} else if (e.getIndex() == 8) {
			setUseDHCP(((Boolean) e.getValue()).booleanValue());
		} else if (e.getIndex() == 9) {
			setPrimaryDNSAddress(GXCommon.intValue(e.getValue()));
		} else if (e.getIndex() == 10) {
			setSecondaryDNSAddress(GXCommon.intValue(e.getValue()));
		} else {
			e.setError(ErrorCode.READ_WRITE_DENIED);
		}
	}
}