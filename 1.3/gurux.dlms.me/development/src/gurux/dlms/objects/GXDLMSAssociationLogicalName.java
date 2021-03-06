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

import java.security.Signature;
import java.util.Enumeration;
import java.util.Vector;

import com.oracle.util.logging.Level;
import com.oracle.util.logging.Logger;

import gurux.dlms.GXByteBuffer;
import gurux.dlms.GXDLMSSettings;
import gurux.dlms.ValueEventArgs;
import gurux.dlms.asn.GXx509Certificate;
import gurux.dlms.enums.AccessMode;
import gurux.dlms.enums.Authentication;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;
import gurux.dlms.enums.MethodAccessMode;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.internal.GXCommon;
import gurux.dlms.manufacturersettings.GXAttributeCollection;
import gurux.dlms.manufacturersettings.GXDLMSAttributeSettings;
import gurux.dlms.objects.enums.AssociationStatus;
import gurux.dlms.secure.GXSecure;

public class GXDLMSAssociationLogicalName extends GXDLMSObject
		implements IGXDLMSBase {
	private static final Logger LOGGER = Logger
			.getLogger(GXDLMSAssociationLogicalName.class.getName());
	private GXDLMSObjectCollection objectList;
	private short clientSAP;
	private short serverSAP;
	private GXApplicationContextName applicationContextName;
	private GXxDLMSContextType xDLMSContextInfo;
	private GXAuthenticationMechanismName authenticationMechanismMame;
	/**
	 * Secret used in Low Level Authentication.
	 */
	private byte[] llsSecret;

	/**
	 * Secret used in High Level Authentication.
	 */
	private byte[] hlsSecret;
	private AssociationStatus associationStatus = AssociationStatus.NON_ASSOCIATED;
	private String securitySetupReference;

	/**
	 * Constructor.
	 */
	public GXDLMSAssociationLogicalName() {
		this("0.0.40.0.0.255");
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 */
	public GXDLMSAssociationLogicalName(final String ln) {
		super(ObjectType.ASSOCIATION_LOGICAL_NAME, ln, 0);
		setLogicalName(ln);
		objectList = new GXDLMSObjectCollection(this);
		applicationContextName = new GXApplicationContextName();
		xDLMSContextInfo = new GXxDLMSContextType();
		authenticationMechanismMame = new GXAuthenticationMechanismName();
		llsSecret = "Gurux".getBytes();
		hlsSecret = "Gurux".getBytes();
		setVersion(1);
	}

	public final GXDLMSObjectCollection getObjectList() {
		return objectList;
	}

	public final void setObjectList(final GXDLMSObjectCollection value) {
		objectList = value;
	}

	/*
	 * Contains the identifiers of the COSEM client APs within the physical
	 * devices hosting these APs, which belong to the AA modelled by the
	 * Association LN object.
	 */
	public final short getClientSAP() {
		return clientSAP;
	}

	public final void setClientSAP(final short value) {
		clientSAP = value;
	}

	/*
	 * Contains the identifiers of the COSEM server (logical device) APs within
	 * the physical devices hosting these APs, which belong to the AA modelled
	 * by the Association LN object.
	 */
	public final short getServerSAP() {
		return serverSAP;
	}

	public final void setServerSAP(final short value) {
		serverSAP = value;
	}

	public final GXApplicationContextName getApplicationContextName() {
		return applicationContextName;
	}

	public final GXxDLMSContextType getXDLMSContextInfo() {
		return xDLMSContextInfo;
	}

	public final GXAuthenticationMechanismName getAuthenticationMechanismMame() {
		return authenticationMechanismMame;
	}

	/**
	 * @return Secret used in Low Level Authentication.
	 */
	public final byte[] getSecret() {
		return llsSecret;
	}

	/**
	 * @param value
	 *            Secret used in Low Level Authentication.
	 */
	public final void setSecret(final byte[] value) {
		llsSecret = value;
	}

	/**
	 * @return Secret used in HLS Authentication.
	 */
	public final byte[] getHlsSecret() {
		return hlsSecret;
	}

	/**
	 * @param value
	 *            Secret used in HLS Authentication.
	 */
	public final void setHlsSecret(final byte[] value) {
		hlsSecret = value;
	}

	public final AssociationStatus getAssociationStatus() {
		return associationStatus;
	}

	public final void setAssociationStatus(final AssociationStatus value) {
		associationStatus = value;
	}

	public final String getSecuritySetupReference() {
		return securitySetupReference;
	}

	public final void setSecuritySetupReference(final String value) {
		securitySetupReference = value;
	}

	public final Object[] getValues() {
		return new Object[] { getLogicalName(), getObjectList(),
				clientSAP + "/" + serverSAP, getApplicationContextName(),
				getXDLMSContextInfo(), getAuthenticationMechanismMame(),
				getSecret(), getAssociationStatus(),
				getSecuritySetupReference() };
	}

	public final byte[] invoke(final GXDLMSSettings settings,
			final ValueEventArgs e) {
		// Check reply_to_HLS_authentication
		if (e.getIndex() == 1) {
			byte[] serverChallenge = null, clientChallenge = null;
			long ic = 0;
			byte[] readSecret;
			boolean accept = false;
			if (settings.getAuthentication() == Authentication.HIGH_ECDSA) {
				try {
//					GXByteBuffer signature = new GXByteBuffer(
//							(byte[]) e.getParameters());
					Signature ver = Signature.getInstance("SHA256withECDSA");
					ver.initVerify(((GXx509Certificate) settings
							.getCertificates().elementAt(0)).getPublicKey());
					GXByteBuffer bb = new GXByteBuffer();
					bb.set(settings.getSourceSystemTitle());
					bb.set(settings.getCipher().getSystemTitle());
					bb.set(settings.getStoCChallenge());
					bb.set(settings.getCtoSChallenge());
					ver.update(bb.array(), 0, bb.size());
//					accept = ver
//							.verify(GXAsn1Converter.encode(signature.array()));

				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}
			} else {
				if (settings.getAuthentication() == Authentication.HIGH_GMAC) {
					readSecret = settings.getSourceSystemTitle();
					GXByteBuffer bb = new GXByteBuffer(
							(byte[]) e.getParameters());
					bb.getUInt8();
					ic = bb.getUInt32();
				} else {
					readSecret = hlsSecret;
				}
				serverChallenge = GXSecure.secure(settings,
						settings.getCipher(), ic, settings.getStoCChallenge(),
						readSecret);
				clientChallenge = (byte[]) e.getParameters();
				accept = GXCommon.compare(serverChallenge, clientChallenge);
			}
			if (accept) {
				if (settings.getAuthentication() == Authentication.HIGH_GMAC) {
					readSecret = settings.getCipher().getSystemTitle();
					ic = settings.getCipher().getFrameCounter();
				} else {
					readSecret = hlsSecret;
				}
				byte[] tmp = GXSecure.secure(settings, settings.getCipher(), ic,
						settings.getCtoSChallenge(), readSecret);
				settings.setConnected(true);
				return tmp;
			} else {
				LOGGER.log(Level.INFO,
						"Invalid CtoS:" + GXCommon.toHex(serverChallenge, false)
								+ "-" + GXCommon.toHex(clientChallenge, false));
				settings.setConnected(false);
				return null;
			}
		} else {
			e.setError(ErrorCode.READ_WRITE_DENIED);
			return null;
		}
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
		// ObjectList is static and read only once.
		if (!isRead(2)) {
			attributes.addElement(new Integer(2));
		}
		// associated_partners_id is static and read only once.
		if (!isRead(3)) {
			attributes.addElement(new Integer(3));
		}
		// Application Context Name is static and read only once.
		if (!isRead(4)) {
			attributes.addElement(new Integer(4));
		}
		// xDLMS Context Info
		if (!isRead(5)) {
			attributes.addElement(new Integer(5));
		}
		// Authentication Mechanism Name
		if (!isRead(6)) {
			attributes.addElement(new Integer(6));
		}
		// Secret
		if (!isRead(7)) {
			attributes.addElement(new Integer(7));
		}
		// Association Status
		if (!isRead(8)) {
			attributes.addElement(new Integer(8));
		}
		// Security Setup Reference is from version 1.
		if (getVersion() > 0 && !isRead(9)) {
			attributes.addElement(new Integer(9));
		}
		return GXDLMSObjectHelpers.toIntArray(attributes);
	}

	public final int getAttributeCount() {
		// Security Setup Reference is from version 1.
		if (getVersion() > 0) {
			return 9;
		}
		return 8;
	}

	/*
	 * Returns amount of methods.
	 */

	public final int getMethodCount() {
		return 4;
	}

	/**
	 * Returns Association View.
	 */
	private byte[] getObjects(final GXDLMSSettings settings,
			final ValueEventArgs e) {
		GXByteBuffer data = new GXByteBuffer();

		// Add count only for first time.
		if (settings.getIndex() == 0) {
			settings.setCount(objectList.size());
			data.setUInt8(DataType.ARRAY.getValue());
			GXCommon.setObjectCount(objectList.size(), data);
		}
		int pos = 0;
		Enumeration iterator = objectList.elements();
		while (iterator.hasMoreElements()) {
			GXDLMSObject it = (GXDLMSObject) iterator.nextElement();
			++pos;
			if (!(pos <= settings.getIndex())) {
				data.setUInt8(DataType.STRUCTURE.getValue());
				// Count
				data.setUInt8(4);
				// ClassID
				GXCommon.setData(data, DataType.UINT16,
						new Integer(it.getObjectType().getValue()));
				// Version
				GXCommon.setData(data, DataType.UINT8,
						new Integer(it.getVersion()));
				// LN
				GXCommon.setData(data, DataType.OCTET_STRING,
						it.getLogicalName());
				getAccessRights(it, data); // Access rights.
				settings.setIndex(settings.getIndex() + 1);
				// If PDU is full.
				if (!e.isSkipMaxPduSize()
						&& data.size() >= settings.getMaxPduSize()) {
					break;
				}
			}

		}
		return data.array();
	}

	private void getAccessRights(final GXDLMSObject item,
			final GXByteBuffer data) {
		data.setUInt8(DataType.STRUCTURE.getValue());
		data.setUInt8(2);
		data.setUInt8(DataType.ARRAY.getValue());
		GXAttributeCollection attributes = item.getAttributes();
		int cnt = item.getAttributeCount();
		data.setUInt8(cnt);
		for (int pos = 0; pos != cnt; ++pos) {
			GXDLMSAttributeSettings att = attributes.find(pos + 1);
			// attribute_access_item
			data.setUInt8(DataType.STRUCTURE.getValue());
			data.setUInt8(3);
			GXCommon.setData(data, DataType.INT8, new Integer(pos + 1));
			/// If attribute is not set return read only.
			if (att == null) {
				GXCommon.setData(data, DataType.ENUM,
						new Integer(AccessMode.READ.getValue()));
			} else {
				GXCommon.setData(data, DataType.ENUM,
						new Integer(att.getAccess().getValue()));
			}
			GXCommon.setData(data, DataType.NONE, null);
		}
		data.setUInt8(DataType.ARRAY.getValue());
		attributes = item.getMethodAttributes();
		cnt = item.getMethodCount();
		data.setUInt8(cnt);
		for (int pos = 0; pos != cnt; ++pos) {
			GXDLMSAttributeSettings att = attributes.find(pos + 1);
			// attribute_access_item
			data.setUInt8(DataType.STRUCTURE.getValue());
			data.setUInt8(2);
			GXCommon.setData(data, DataType.INT8, new Integer(pos + 1));
			// If version is 0.
			if (getVersion() == 0) {
				if (att == null) {
					GXCommon.setData(data, DataType.BOOLEAN,
							new Boolean(false));
				} else {
					GXCommon.setData(data, DataType.BOOLEAN,
							new Boolean(att.getMethodAccess().getValue() != 0));
				}
			} else {
				/// If method attribute is not set return no access.
				if (att == null) {
					GXCommon.setData(data, DataType.ENUM,
							new Integer(MethodAccessMode.NO_ACCESS.getValue()));
				} else {
					GXCommon.setData(data, DataType.ENUM,
							new Integer(att.getMethodAccess().getValue()));
				}
			}
		}
	}

	static final void updateAccessRights(final GXDLMSObject obj,
			final Object[] buff) {
		if (buff != null && buff.length != 0) {
			Object[] arr = (Object[]) buff[0];
			for (int pos = 0; pos != arr.length; ++pos) {
				Object access = arr[pos];
				Object[] attributeAccess = (Object[]) access;
				int id = GXCommon.intValue(attributeAccess[0]);
				int tmp = GXCommon.intValue(attributeAccess[1]);
				AccessMode aMode = AccessMode.forValue(tmp);
				obj.setAccess(id, aMode);
			}
			arr = (Object[]) buff[1];
			for (int pos = 0; pos != arr.length; ++pos) {
				Object access = arr[pos];
				Object[] methodAccess = (Object[]) access;
				int id = GXCommon.intValue(methodAccess[0]);
				int tmp;
				// If version is 0.
				if (methodAccess[1] instanceof Boolean) {
					if (((Boolean) methodAccess[1]).booleanValue()) {
						tmp = 1;
					} else {
						tmp = 0;
					}
				} else {
					// If version is 1.
					tmp = GXCommon.intValue(methodAccess[1]);
				}
				MethodAccessMode mode = MethodAccessMode.forValue(tmp);
				obj.setMethodAccess(id, mode);
			}
		}
	}

	public final DataType getDataType(final int index) {
		if (index == 1) {
			return DataType.OCTET_STRING;
		}
		if (index == 2) {
			return DataType.ARRAY;
		}
		if (index == 3) {
			return DataType.STRUCTURE;
		}
		if (index == 4) {
			return DataType.STRUCTURE;
		}
		if (index == 5) {
			return DataType.STRUCTURE;
		}
		if (index == 6) {
			return DataType.STRUCTURE;
		}
		if (index == 7) {
			return DataType.OCTET_STRING;
		}
		if (index == 8) {
			return DataType.ENUM;
		}
		if (index == 9) {
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
			return getObjects(settings, e);
		}
		if (e.getIndex() == 3) {
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8(DataType.ARRAY.getValue());
			// Add count
			data.setUInt8(2);
			data.setUInt8(DataType.UINT8.getValue());
			data.setUInt8(clientSAP);
			data.setUInt8(DataType.UINT16.getValue());
			data.setUInt16(serverSAP);
			return data.array();
		}
		if (e.getIndex() == 4) {
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8(DataType.STRUCTURE.getValue());
			// Add count
			data.setUInt8(0x7);
			GXCommon.setData(data, DataType.UINT8,
					new Integer(applicationContextName.getJointIsoCtt()));
			GXCommon.setData(data, DataType.UINT8,
					new Integer(applicationContextName.getCountry()));
			GXCommon.setData(data, DataType.UINT16,
					new Integer(applicationContextName.getCountryName()));
			GXCommon.setData(data, DataType.UINT8, new Integer(
					applicationContextName.getIdentifiedOrganization()));
			GXCommon.setData(data, DataType.UINT8,
					new Integer(applicationContextName.getDlmsUA()));
			GXCommon.setData(data, DataType.UINT8, new Integer(
					applicationContextName.getApplicationContext()));
			GXCommon.setData(data, DataType.UINT8,
					new Integer(applicationContextName.getContextId()));
			return data.array();
		}
		if (e.getIndex() == 5) {
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8(DataType.STRUCTURE.getValue());
			data.setUInt8(6);
			GXCommon.setData(data, DataType.BITSTRING,
					xDLMSContextInfo.getConformance());
			GXCommon.setData(data, DataType.UINT16,
					new Integer(xDLMSContextInfo.getMaxReceivePduSize()));
			GXCommon.setData(data, DataType.UINT16,
					new Integer(xDLMSContextInfo.getMaxSendPpuSize()));
			GXCommon.setData(data, DataType.UINT8,
					new Integer(xDLMSContextInfo.getDlmsVersionNumber()));
			GXCommon.setData(data, DataType.INT8,
					new Integer(xDLMSContextInfo.getQualityOfService()));
			GXCommon.setData(data, DataType.OCTET_STRING,
					xDLMSContextInfo.getCypheringInfo());
			return data.array();
		}
		if (e.getIndex() == 6) {
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8(DataType.STRUCTURE.getValue());
			// Add count
			data.setUInt8(0x7);
			GXCommon.setData(data, DataType.UINT8,
					new Integer(authenticationMechanismMame.getJointIsoCtt()));
			GXCommon.setData(data, DataType.UINT8,
					new Integer(authenticationMechanismMame.getCountry()));
			GXCommon.setData(data, DataType.UINT16,
					new Integer(authenticationMechanismMame.getCountryName()));
			GXCommon.setData(data, DataType.UINT8, new Integer(
					authenticationMechanismMame.getIdentifiedOrganization()));
			GXCommon.setData(data, DataType.UINT8,
					new Integer(authenticationMechanismMame.getDlmsUA()));
			GXCommon.setData(data, DataType.UINT8,
					new Integer(authenticationMechanismMame
							.getAuthenticationMechanismName()));
			GXCommon.setData(data, DataType.UINT8, new Integer(
					authenticationMechanismMame.getMechanismId().getValue()));
			return data.array();
		}
		if (e.getIndex() == 7) {
			return llsSecret;
		}
		if (e.getIndex() == 8) {
			return new Integer(getAssociationStatus().getValue());
		}
		if (e.getIndex() == 9) {
			return getSecuritySetupReference();
		}
		e.setError(ErrorCode.READ_WRITE_DENIED);
		return null;
	}

	static void updateObjectList(final GXDLMSSettings settings,
			final GXDLMSObjectCollection target, final Object value) {
		target.removeAllElements();
		if (value != null) {
			Object[] arr = (Object[]) value;
			for (int pos = 0; pos != arr.length; ++pos) {
				Object[] item = (Object[]) arr[pos];
				ObjectType type = ObjectType
						.forValue(GXCommon.intValue(item[0]));
				int version = GXCommon.intValue(item[1]);
				String ln = GXDLMSObject.toLogicalName((byte[]) item[2]);
				GXDLMSObject obj = settings.getObjects().findByLN(type, ln);
				if (obj == null) {
					obj = gurux.dlms.GXDLMSClient.createObject(type);
					obj.setLogicalName(ln);
					obj.setVersion(version);
				}
				// Add only known objects.
				if (obj instanceof IGXDLMSBase) {
					updateAccessRights(obj, (Object[]) item[3]);
					target.addElement(obj);
				}
			}
		}
	}

	private void updateApplicationContextName(final Object value) {
		// Value of the object identifier encoded in BER
		if (value instanceof byte[]) {
			GXByteBuffer buff = new GXByteBuffer((byte[]) value);
			if (buff.getUInt8(0) == 0x60) {
				applicationContextName.setJointIsoCtt(0);
				applicationContextName.setCountry(0);
				applicationContextName.setCountryName(0);
				buff.position(buff.position() + 3);
				applicationContextName
						.setIdentifiedOrganization(buff.getUInt8());
				applicationContextName.setDlmsUA(buff.getUInt8());
				applicationContextName.setApplicationContext(buff.getUInt8());
				applicationContextName.setContextId(buff.getUInt8());
			} else {
				// Get Tag and length.
				if (buff.getUInt8() != 2 && buff.getUInt8() != 7) {
					throw new IllegalArgumentException();
				}
				// Get tag
				if (buff.getUInt8() != 0x11) {
					throw new IllegalArgumentException();
				}
				applicationContextName.setJointIsoCtt(buff.getUInt8());
				// Get tag
				if (buff.getUInt8() != 0x11) {
					throw new IllegalArgumentException();
				}
				applicationContextName.setCountry(buff.getUInt8());
				// Get tag
				if (buff.getUInt8() != 0x12) {
					throw new IllegalArgumentException();
				}
				applicationContextName.setCountryName(buff.getUInt16());
				// Get tag
				if (buff.getUInt8() != 0x11) {
					throw new IllegalArgumentException();
				}
				applicationContextName
						.setIdentifiedOrganization(buff.getUInt8());
				// Get tag
				if (buff.getUInt8() != 0x11) {
					throw new IllegalArgumentException();
				}
				applicationContextName.setDlmsUA(buff.getUInt8());
				// Get tag
				if (buff.getUInt8() != 0x11) {
					throw new IllegalArgumentException();
				}
				applicationContextName.setApplicationContext(buff.getUInt8());
				// Get tag
				if (buff.getUInt8() != 0x11) {
					throw new IllegalArgumentException();
				}
				applicationContextName.setContextId(buff.getUInt8());
			}
		} else {
			if (value != null) {
				Object[] arr = (Object[]) value;
				applicationContextName
						.setJointIsoCtt(GXCommon.intValue(arr[0]));
				applicationContextName.setCountry(GXCommon.intValue(arr[1]));
				applicationContextName
						.setCountryName(GXCommon.intValue(arr[2]));
				applicationContextName
						.setIdentifiedOrganization(GXCommon.intValue(arr[3]));
				applicationContextName.setDlmsUA(GXCommon.intValue(arr[4]));
				applicationContextName
						.setApplicationContext(GXCommon.intValue(arr[5]));
				applicationContextName.setContextId(GXCommon.intValue(arr[6]));
			}
		}
	}

	private void updateAuthenticationMechanismMame(final Object value) {
		if (value != null) {
			// Value of the object identifier encoded in BER
			if (value instanceof byte[]) {
				GXByteBuffer buff = new GXByteBuffer((byte[]) value);
				if (buff.getUInt8(0) == 0x60) {
					authenticationMechanismMame.setJointIsoCtt(0);
					authenticationMechanismMame.setCountry(0);
					authenticationMechanismMame.setCountryName(0);
					buff.position(buff.position() + 3);
					authenticationMechanismMame
							.setIdentifiedOrganization(buff.getUInt8());
					authenticationMechanismMame.setDlmsUA(buff.getUInt8());
					authenticationMechanismMame
							.setAuthenticationMechanismName(buff.getUInt8());
					authenticationMechanismMame.setMechanismId(
							Authentication.forValue(buff.getUInt8()));
				} else {
					// Get Tag and length.
					if (buff.getUInt8() != 2 && buff.getUInt8() != 7) {
						throw new IllegalArgumentException();
					}
					// Get tag
					if (buff.getUInt8() != 0x11) {
						throw new IllegalArgumentException();
					}
					authenticationMechanismMame.setJointIsoCtt(buff.getUInt8());
					// Get tag
					if (buff.getUInt8() != 0x11) {
						throw new IllegalArgumentException();
					}
					authenticationMechanismMame.setCountry(buff.getUInt8());
					// Get tag
					if (buff.getUInt8() != 0x12) {
						throw new IllegalArgumentException();
					}
					authenticationMechanismMame
							.setCountryName(buff.getUInt16());
					// Get tag
					if (buff.getUInt8() != 0x11) {
						throw new IllegalArgumentException();
					}
					authenticationMechanismMame
							.setIdentifiedOrganization(buff.getUInt8());
					// Get tag
					if (buff.getUInt8() != 0x11) {
						throw new IllegalArgumentException();
					}
					authenticationMechanismMame.setDlmsUA(buff.getUInt8());
					// Get tag
					if (buff.getUInt8() != 0x11) {
						throw new IllegalArgumentException();
					}
					authenticationMechanismMame
							.setAuthenticationMechanismName(buff.getUInt8());
					// Get tag
					if (buff.getUInt8() != 0x11) {
						throw new IllegalArgumentException();
					}
					authenticationMechanismMame.setMechanismId(
							Authentication.forValue(buff.getUInt8()));
				}
			} else {
				if (value != null) {
					Object[] arr = (Object[]) value;
					authenticationMechanismMame
							.setJointIsoCtt(GXCommon.intValue(arr[0]));
					authenticationMechanismMame
							.setCountry(GXCommon.intValue(arr[1]));
					authenticationMechanismMame
							.setCountryName(GXCommon.intValue(arr[2]));
					authenticationMechanismMame.setIdentifiedOrganization(
							GXCommon.intValue(arr[3]));
					authenticationMechanismMame
							.setDlmsUA(GXCommon.intValue(arr[4]));
					authenticationMechanismMame.setAuthenticationMechanismName(
							GXCommon.intValue(arr[5]));
					authenticationMechanismMame.setMechanismId(
							Authentication.forValue(GXCommon.intValue(arr[6])));
				}
			}
		}
	}

	/*
	 * Set value of given attribute.
	 */

	public final void setValue(final GXDLMSSettings settings,
			final ValueEventArgs e) {
		switch (e.getIndex()) {
		case 1:
			super.setValue(settings, e);
			break;
		case 2:
			updateObjectList(settings, objectList, e.getValue());
			break;
		case 3:
			if (e.getValue() != null) {
				Object[] arr = (Object[]) e.getValue();
				clientSAP = (short) GXCommon.intValue(arr[0]);
				serverSAP = (short) GXCommon.intValue(arr[1]);
			}
			break;
		case 4:
			updateApplicationContextName(e.getValue());
			break;
		case 5:
			if (e.getValue() != null) {
				Object[] arr = (Object[]) e.getValue();
				xDLMSContextInfo.setConformance(arr[0].toString());
				xDLMSContextInfo
						.setMaxReceivePduSize(GXCommon.intValue(arr[1]));
				xDLMSContextInfo.setMaxSendPpuSize(GXCommon.intValue(arr[2]));
				xDLMSContextInfo
						.setDlmsVersionNumber(GXCommon.intValue(arr[3]));
				xDLMSContextInfo.setQualityOfService(GXCommon.intValue(arr[4]));
				xDLMSContextInfo.setCypheringInfo((byte[]) arr[5]);
			}
			break;
		case 6:
			updateAuthenticationMechanismMame(e.getValue());
			break;
		case 7:
			llsSecret = (byte[]) e.getValue();
			break;
		case 8:
			if (e.getValue() == null) {
				setAssociationStatus(AssociationStatus.NON_ASSOCIATED);
			} else {
				setAssociationStatus(AssociationStatus
						.forValue(GXCommon.intValue(e.getValue())));
			}
			break;
		case 9:
			setSecuritySetupReference(
					GXDLMSObject.toLogicalName((byte[]) e.getValue()));
			break;
		default:
			e.setError(ErrorCode.READ_WRITE_DENIED);
		}
	}
}