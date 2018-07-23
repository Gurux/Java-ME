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
import java.util.Hashtable;
import java.util.Vector;

import gurux.dlms.GXByteBuffer;
import gurux.dlms.GXDLMSClient;
import gurux.dlms.GXDLMSSettings;
import gurux.dlms.GXSimpleEntry;
import gurux.dlms.ValueEventArgs;
import gurux.dlms.asn.GXx509Certificate;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.enums.Security;
import gurux.dlms.internal.GXCommon;
import gurux.dlms.objects.enums.CertificateEntity;
import gurux.dlms.objects.enums.CertificateType;
import gurux.dlms.objects.enums.GlobalKeyType;
import gurux.dlms.objects.enums.SecurityPolicy;
import gurux.dlms.objects.enums.SecurityPolicy1;
import gurux.dlms.objects.enums.SecuritySuite;
import gurux.dlms.secure.GXDLMSSecureClient;

public class GXDLMSSecuritySetup extends GXDLMSObject implements IGXDLMSBase {
	/**
	 * Asymmetric key pair as required by the security suite.
	 */
	private Hashtable keys = new Hashtable();
	/**
	 * Security policy.
	 */
	private int securityPolicy = 0;

	/**
	 * Security suite.
	 */
	private SecuritySuite securitySuite;

	/**
	 * Server system title.
	 */
	private byte[] serverSystemTitle;

	/**
	 * Client system title.
	 */
	private byte[] clientSystemTitle;

	/**
	 * Available certificates.
	 */
	private Vector certificates;

	/**
	 * Constructor.
	 */
	public GXDLMSSecuritySetup() {
		this("0.0.43.0.0.255");
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 */
	public GXDLMSSecuritySetup(final String ln) {
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
	public GXDLMSSecuritySetup(final String ln, final int sn) {
		super(ObjectType.SECURITY_SETUP, ln, sn);
		securityPolicy = 0;
		securitySuite = SecuritySuite.AES_GCM_128;
		certificates = new Vector();
	}

	/**
	 * @return Security policy for version 1.
	 */
	public final Vector getSecurityPolicy1() {
		return SecurityPolicy1.forValue(securityPolicy);
	}

	/**
	 * @param value
	 *            Security policy for version 1.
	 */
	public final void setSecurityPolicy1(final Vector value) {
		securityPolicy = SecurityPolicy1.toInteger(value);
	}

	/**
	 * @return Security policy for version 0.
	 */
	public final SecurityPolicy getSecurityPolicy() {
		return SecurityPolicy.forValue(securityPolicy);
	}

	/**
	 * @param value
	 *            Security policy for version 0.
	 */
	public final void setSecurityPolicy(final SecurityPolicy value) {
		switch (value.getValue()) {
		case 0:// SecurityPolicy.NOTHING:
		case 1:// SecurityPolicy.AUTHENTICATED:
		case 2:// SecurityPolicy.ENCRYPTED:
		case 3:// SecurityPolicy.AUTHENTICATED_ENCRYPTED:
			securityPolicy = value.getValue();
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @return Security suite.
	 */
	public final SecuritySuite getSecuritySuite() {
		return securitySuite;
	}

	/**
	 * @param value
	 *            Security suite.
	 */
	public final void setSecuritySuite(final SecuritySuite value) {
		securitySuite = value;
	}

	/**
	 * @return Client system title.
	 */
	public final byte[] getClientSystemTitle() {
		return clientSystemTitle;
	}

	/**
	 * @param value
	 *            Client system title.
	 */
	public final void setClientSystemTitle(final byte[] value) {
		clientSystemTitle = value;
	}

	/**
	 * @return Server system title.
	 */
	public final byte[] getServerSystemTitle() {
		return serverSystemTitle;
	}

	/**
	 * @param value
	 *            Server system title.
	 */
	public final void setServerSystemTitle(final byte[] value) {
		serverSystemTitle = value;
	}

	/**
	 * @return Available certificates.
	 */
	public final Vector getCertificates() {
		return certificates;
	}

	public final Object[] getValues() {
		return new Object[] { getLogicalName(), new Integer(securityPolicy),
				securitySuite, clientSystemTitle, serverSystemTitle,
				certificates };
	}

	/**
	 * Activates and strengthens the security policy for version 0 Security
	 * Setup Object.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param security
	 *            New security level.
	 * @return Generated action.
	 */
	public final byte[][] activate(final GXDLMSClient client,
			final SecurityPolicy security) {
		return client.method(this, 1, new Integer(security.getValue()),
				DataType.ENUM);
	}

	/**
	 * Activates and strengthens the security policy for version 1 Security
	 * Setup Object.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param security
	 *            New security level.
	 * @return Generated action.
	 */
	public final byte[][] activate(final GXDLMSClient client,
			final Vector security) {
		return client.method(this, 1,
				new Integer(SecurityPolicy1.toInteger(security)),
				DataType.ENUM);
	}

	/**
	 * Updates one or more global keys.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param kek
	 *            Master key, also known as Key Encrypting Key.
	 * @param list
	 *            List of Global key types and keys.
	 * @return Generated action.
	 */
	public final byte[][] globalKeyTransfer(final GXDLMSClient client,
			final byte[] kek, final Vector list) {
		if (list == null || list.size() == 0) {
			throw new IllegalArgumentException("Invalid list. It is empty.");
		}
		GXByteBuffer bb = new GXByteBuffer();
		bb.setUInt8(DataType.ARRAY.getValue());
		bb.setUInt8((byte) list.size());
		byte[] tmp;
		Enumeration iterator = list.elements();
		while (iterator.hasMoreElements()) {
			GXSimpleEntry it = (GXSimpleEntry) iterator.nextElement();
			bb.setUInt8(DataType.STRUCTURE.getValue());
			bb.setUInt8(2);
			GXCommon.setData(bb, DataType.ENUM,
					new Integer(((GlobalKeyType) it.getKey()).getValue()));
			tmp = GXDLMSSecureClient.encrypt(kek, (byte[]) it.getValue());
			GXCommon.setData(bb, DataType.OCTET_STRING, tmp);
		}
		return client.method(this, 2, bb.array(), DataType.ARRAY);
	}

	/**
	 * Agree on one or more symmetric keys using the key agreement algorithm.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param list
	 *            List of keys.
	 * @return Generated action.
	 */
	public final byte[][] keyAgreement(final GXDLMSClient client,
			final Vector list) {
		if (list == null || list.size() == 0) {
			throw new IllegalArgumentException("Invalid list. It is empty.");
		}
		GXByteBuffer bb = new GXByteBuffer();
		bb.setUInt8(DataType.ARRAY.getValue());
		bb.setUInt8((byte) list.size());
		Enumeration iterator = list.elements();
		while (iterator.hasMoreElements()) {
			GXSimpleEntry it = (GXSimpleEntry) iterator.nextElement();
			bb.setUInt8(DataType.STRUCTURE.getValue());
			bb.setUInt8(2);
			GXCommon.setData(bb, DataType.ENUM,
					new Integer(((GlobalKeyType) it.getKey()).getValue()));
			GXCommon.setData(bb, DataType.OCTET_STRING, (byte[]) it.getValue());
		}
		return client.method(this, 3, bb.array(), DataType.ARRAY);
	}

	/**
	 * Generates an asymmetric key pair as required by the security suite.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param type
	 *            New certificate type.
	 * @return Generated action.
	 */
	public final byte[][] generateKeyPair(final GXDLMSClient client,
			final CertificateType type) {
		return client.method(this, 4, new Integer(type.getValue()),
				DataType.ENUM);
	}

	/**
	 * Ask Server sends the Certificate Signing Request (CSR) data.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param type
	 *            identifies the key pair for which the certificate will be
	 *            requested.
	 * @return Generated action.
	 */
	public final byte[][] generateCertificate(final GXDLMSClient client,
			final CertificateType type) {
		return client.method(this, 5, new Integer(type.getValue()),
				DataType.ENUM);
	}

	/**
	 * Imports an X.509 v3 certificate of a public key.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param certificate
	 *            X.509 v3 certificate.
	 * @return Generated action.
	 */
	public final byte[][] importCertificate(final GXDLMSClient client,
			final GXx509Certificate certificate) {
		return importCertificate(client, certificate.getEncoded());
	}

	/**
	 * Imports an X.509 v3 certificate of a public key.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param key
	 *            Public key.
	 * @return Generated action.
	 */
	public final byte[][] importCertificate(final GXDLMSClient client,
			final byte[] key) {
		GXByteBuffer bb = new GXByteBuffer();
		bb.setUInt8(DataType.OCTET_STRING.getValue());
		GXCommon.setObjectCount(key.length, bb);
		bb.set(key);
		return client.method(this, 6, bb.array(), DataType.OCTET_STRING);
	}

	/**
	 * Exports an X.509 v3 certificate from the server using entity information.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param entity
	 *            Certificate entity.
	 * @param type
	 *            Certificate type.
	 * @param systemTitle
	 *            System title.
	 * @return Generated action.
	 */
	public final byte[][] exportCertificateByEntity(final GXDLMSClient client,
			final CertificateEntity entity, final CertificateType type,
			final byte[] systemTitle) {
		GXByteBuffer bb = new GXByteBuffer();
		bb.setUInt8(DataType.STRUCTURE.getValue());
		bb.setUInt8(2);
		// Add enum
		bb.setUInt8(DataType.ENUM.getValue());
		bb.setUInt8(0);
		// Add certificate_identification_by_entity
		bb.setUInt8(DataType.STRUCTURE.getValue());
		bb.setUInt8(3);
		// Add certificate_entity
		bb.setUInt8(DataType.ENUM.getValue());
		bb.setUInt8(entity.getValue());
		// Add certificate_type
		bb.setUInt8(DataType.ENUM.getValue());
		bb.setUInt8(type.getValue());
		// system_title
		GXCommon.setData(bb, DataType.OCTET_STRING, systemTitle);
		return client.method(this, 7, bb.array(), DataType.OCTET_STRING);
	}

	/**
	 * Exports an X.509 v3 certificate from the server using serial information.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param serialNumber
	 *            Serial number.
	 * @param issuer
	 *            Issuer
	 * @return Generated action.
	 */
	public final byte[][] exportCertificateBySerial(final GXDLMSClient client,
			final String serialNumber, final String issuer) {
		GXByteBuffer bb = new GXByteBuffer();
		bb.setUInt8(DataType.STRUCTURE.getValue());
		bb.setUInt8(2);
		// Add enum
		bb.setUInt8(DataType.ENUM.getValue());
		bb.setUInt8(1);
		// Add certificate_identification_by_entity
		bb.setUInt8(DataType.STRUCTURE.getValue());
		bb.setUInt8(2);
		// serialNumber
		GXCommon.setData(bb, DataType.OCTET_STRING, serialNumber.getBytes());
		// issuer
		GXCommon.setData(bb, DataType.OCTET_STRING, issuer.getBytes());
		return client.method(this, 7, bb.array(), DataType.OCTET_STRING);
	}

	/**
	 * Removes X.509 v3 certificate from the server using entity.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param entity
	 *            Certificate entity type.
	 * @param type
	 *            Certificate type.
	 * @param systemTitle
	 *            System title.
	 * @return Generated action.
	 */
	public final byte[][] removeCertificateByEntity(final GXDLMSClient client,
			final CertificateEntity entity, final CertificateType type,
			final byte[] systemTitle) {
		GXByteBuffer bb = new GXByteBuffer();
		bb.setUInt8(DataType.STRUCTURE.getValue());
		bb.setUInt8(2);
		// Add enum
		bb.setUInt8(DataType.ENUM.getValue());
		bb.setUInt8(0);
		// Add certificate_identification_by_entity
		bb.setUInt8(DataType.STRUCTURE.getValue());
		bb.setUInt8(3);
		// Add certificate_entity
		bb.setUInt8(DataType.ENUM.getValue());
		bb.setUInt8(entity.getValue());
		// Add certificate_type
		bb.setUInt8(DataType.ENUM.getValue());
		bb.setUInt8(type.getValue());
		// system_title
		GXCommon.setData(bb, DataType.OCTET_STRING, systemTitle);
		return client.method(this, 8, bb.array(), DataType.OCTET_STRING);
	}

	/**
	 * Removes X.509 v3 certificate from the server using serial number.
	 * 
	 * @param client
	 *            DLMS client that is used to generate action.
	 * @param serialNumber
	 *            Serial number.
	 * @param issuer
	 *            Issuer.
	 * @return Generated action.
	 */
	public final byte[][] removeCertificateBySerial(final GXDLMSClient client,
			final String serialNumber, final String issuer) {
		GXByteBuffer bb = new GXByteBuffer();
		bb.setUInt8(DataType.STRUCTURE.getValue());
		bb.setUInt8(2);
		// Add enum
		bb.setUInt8(DataType.ENUM.getValue());
		bb.setUInt8(1);
		// Add certificate_identification_by_entity
		bb.setUInt8(DataType.STRUCTURE.getValue());
		bb.setUInt8(2);
		// serialNumber
		GXCommon.setData(bb, DataType.OCTET_STRING, serialNumber.getBytes());
		// issuer
		GXCommon.setData(bb, DataType.OCTET_STRING, issuer.getBytes());
		return client.method(this, 8, bb.array(), DataType.OCTET_STRING);
	}

	public final byte[] invoke(final GXDLMSSettings settings,
			final ValueEventArgs e) {
		// Increase security level.
		if (e.getIndex() == 1) {
			Security security = settings.getCipher().getSecurity();
			if (getVersion() == 0) {
				SecurityPolicy policy = SecurityPolicy
						.forValue(GXCommon.intValue(e.getParameters()));
				setSecurityPolicy(policy);
				if (policy == SecurityPolicy.AUTHENTICATED) {
					settings.getCipher().setSecurity(Security.AUTHENTICATION);
				} else if (policy == SecurityPolicy.ENCRYPTED) {
					settings.getCipher().setSecurity(Security.ENCRYPTION);
				} else if (policy == SecurityPolicy.AUTHENTICATED_ENCRYPTED) {
					settings.getCipher()
							.setSecurity(Security.AUTHENTICATION_ENCRYPTION);
				}
			} else if (getVersion() == 1) {
				Vector policy = SecurityPolicy1
						.forValue(GXCommon.intValue(e.getParameters()));
				setSecurityPolicy1(policy);
				if (policy.contains(SecurityPolicy1.AUTHENTICATED_RESPONSE)) {
					security = Security.forValue(security.getValue()
							| Security.AUTHENTICATION.getValue());
					settings.getCipher().setSecurity(security);
				}
				if (policy.contains(SecurityPolicy1.ENCRYPTED_RESPONSE)) {
					security = Security.forValue(security.getValue()
							| Security.ENCRYPTION.getValue());
					settings.getCipher().setSecurity(security);
				}
			}
		} else if (e.getIndex() == 2) {
			Object[] arr = (Object[]) e.getParameters();
			for (int pos = 0; pos != arr.length; ++pos) {
				Object[] item = (Object[]) arr[pos];
				byte type = (byte) GXCommon.intValue(item[0]);
				byte[] data = (byte[]) item[1];
				switch (type) {
				case 0:// GlobalKeyType.UNICAST_ENCRYPTION:
				case 1:// GlobalKeyType.BROADCAST_ENCRYPTION:
						// Invalid type
					e.setError(ErrorCode.READ_WRITE_DENIED);
				case 2:// GlobalKeyType.AUTHENTICATION:
						// if settings.Cipher is null non secure server is used.
					settings.getCipher().setAuthenticationKey(GXDLMSSecureClient
							.decrypt(settings.getKek(), data));
					break;
				case 3:// GlobalKeyType.KEK:
					settings.setKek(GXDLMSSecureClient
							.decrypt(settings.getKek(), data));
					break;
				default:
					e.setError(ErrorCode.READ_WRITE_DENIED);
				}
			}
		} else if (e.getIndex() == 3) {
			// key_agreement
			e.setError(ErrorCode.HARDWARE_FAULT);
		} else if (e.getIndex() == 4) {
			// generate_key_pair
			e.setError(ErrorCode.HARDWARE_FAULT);
		} else if (e.getIndex() == 5) {
			// generate_certificate_request
			e.setError(ErrorCode.READ_WRITE_DENIED);
		} else if (e.getIndex() == 6) {
			// import_certificate
			e.setError(ErrorCode.READ_WRITE_DENIED);
		} else if (e.getIndex() == 7) {
			// export_certificate
			e.setError(ErrorCode.READ_WRITE_DENIED);
		} else if (e.getIndex() == 8) {
			// remove_certificate
			e.setError(ErrorCode.READ_WRITE_DENIED);
		} else {
			// Invalid type
			e.setError(ErrorCode.READ_WRITE_DENIED);
		}
		// Return standard reply.
		return null;
	}

	public final int[] getAttributeIndexToRead() {
		Vector attributes = new Vector();
		// LN is static and read only once.
		if (getLogicalName() == null || getLogicalName().compareTo("") == 0) {
			attributes.addElement(new Integer(1));
		}
		// SecurityPolicy
		if (canRead(2)) {
			attributes.addElement(new Integer(2));
		}
		// SecuritySuite
		if (canRead(3)) {
			attributes.addElement(new Integer(3));
		}
		if (getVersion() > 0) {
			// ClientSystemTitle
			if (canRead(4)) {
				attributes.addElement(new Integer(4));
			}
			// ServerSystemTitle
			if (canRead(5)) {
				attributes.addElement(new Integer(5));
			}
			// Certificates
			if (canRead(6)) {
				attributes.addElement(new Integer(6));
			}
		}
		return GXDLMSObjectHelpers.toIntArray(attributes);
	}

	/*
	 * Returns amount of attributes.
	 */

	public final int getAttributeCount() {
		if (getVersion() == 0) {
			return 5;
		}
		return 6;
	}

	/*
	 * Returns amount of methods.
	 */

	public final int getMethodCount() {
		if (getVersion() == 0) {
			return 2;
		}
		return 8;
	}

	public final DataType getDataType(final int index) {
		if (index == 1) {
			return DataType.OCTET_STRING;
		}
		if (index == 2) {
			return DataType.ENUM;
		}
		if (index == 3) {
			return DataType.ENUM;
		}
		if (index == 4) {
			return DataType.OCTET_STRING;
		}
		if (index == 5) {
			return DataType.OCTET_STRING;
		}
		if (getVersion() > 0) {
			if (index == 6) {
				return DataType.ARRAY;
			}
			throw new IllegalArgumentException(
					"getDataType failed. Invalid attribute index.");
		} else {
			throw new IllegalArgumentException(
					"getDataType failed. Invalid attribute index.");
		}
	}

	/*
	 * Get certificates as byte buffer.
	 */
	private byte[] getCertificatesByteArray(final GXDLMSSettings settings) {
		GXByteBuffer bb = new GXByteBuffer();
		bb.setUInt8((byte) DataType.ARRAY.getValue());
		GXCommon.setObjectCount(settings.getCertificates().size(), bb);
		Enumeration iterator = settings.getCertificates().elements();
		while (iterator.hasMoreElements()) {
			GXx509Certificate it = (GXx509Certificate) iterator.nextElement();
			bb.setUInt8((byte) DataType.STRUCTURE.getValue());
			GXCommon.setObjectCount(6, bb);
			bb.setUInt8((byte) DataType.ENUM.getValue());
			// certificate_entity: enum:
			bb.setUInt8((byte) CertificateEntity.SERVER.getValue());
			bb.setUInt8((byte) DataType.ENUM.getValue());
			// digital signature
			bb.setUInt8((byte) CertificateType.DIGITAL_SIGNATURE.getValue());
			bb.set(it.getSerialNumber().getByteArray());
			// GXCommon.addString(it.getIssuerDN().getName(), bb);
			// GXCommon.addString(it.getSubjectDN().getName(), bb);
			// try {
			// if (it.getSubjectAlternativeNames() != null
			// && !it.getSubjectAlternativeNames().isEmpty()) {
			// GXCommon.addString(it.getSubjectAlternativeNames()
			// .toArray(null).toString(), bb);
			// } else {
			// GXCommon.addString("", bb);
			// }
			// } catch (CertificateParsingException e) {
			// GXCommon.addString("", bb);
			// }
		}
		return bb.array();
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
			return new Integer(securityPolicy);
		}
		if (e.getIndex() == 3) {
			return new Integer(getSecuritySuite().getValue());
		}
		if (e.getIndex() == 4) {
			return getClientSystemTitle();
		}
		if (e.getIndex() == 5) {
			return getServerSystemTitle();
		}
		if (e.getIndex() == 6) {
			return getCertificatesByteArray(settings);
		}
		e.setError(ErrorCode.READ_WRITE_DENIED);
		return null;
	}

	private void updateSertificates(final Object[] list) {
		certificates.removeAllElements();
		for (int pos = 0; pos != list.length; ++pos) {
			Object[] it = (Object[]) list[pos];
			GXDLMSCertificateInfo info = new GXDLMSCertificateInfo();
			info.setEntity(
					CertificateEntity.forValue(GXCommon.intValue(it[0])));
			info.setType(CertificateType.forValue(GXCommon.intValue(it[1])));
			info.setSerialNumber(new String((byte[]) it[2]));
			info.setIssuer(new String((byte[]) it[3]));
			info.setSubject(new String((byte[]) it[4]));
			info.setSubjectAltName(new String((byte[]) it[5]));
			certificates.addElement(info);
		}
	}

	/*
	 * Set value of given attribute.
	 */

	public final void setValue(final GXDLMSSettings settings,
			final ValueEventArgs e) {
		if (e.getIndex() == 1) {
			super.setValue(settings, e);
		} else if (e.getIndex() == 2) {
			// Security level is set with action.
			if (settings.isServer()) {
				e.setError(ErrorCode.READ_WRITE_DENIED);
			} else {
				this.securityPolicy = ((Short) e.getValue()).shortValue();
			}
		} else if (e.getIndex() == 3) {
			setSecuritySuite(
					SecuritySuite.forValue(GXCommon.intValue(e.getValue())));
		} else if (e.getIndex() == 4) {
			setClientSystemTitle((byte[]) e.getValue());
		} else if (e.getIndex() == 5) {
			setServerSystemTitle((byte[]) e.getValue());
		} else if (e.getIndex() == 6) {
			updateSertificates((Object[]) e.getValue());
		} else {
			e.setError(ErrorCode.READ_WRITE_DENIED);
		}
	}
}