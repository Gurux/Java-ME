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

package gurux.dlms.secure;

import java.util.Vector;

import gurux.dlms.GXDLMSClient;
import gurux.dlms.asn.GXx509Certificate;
import gurux.dlms.enums.Authentication;
import gurux.dlms.enums.InterfaceType;

/**
 * GXDLMSSecureClient implements secure client where all messages are secured
 * using transport security.
 * 
 * @author Gurux Ltd.
 */
public class GXDLMSSecureClient extends GXDLMSClient {

	/**
	 * Client certificate.
	 */
	private GXx509Certificate clientCertificate;
	/**
	 * Server certificate.
	 */
	private GXx509Certificate serverCertificate;

	/**
	 * Ciphering settings.
	 */
	private GXCiphering ciphering;

	/**
	 * Constructor.
	 */
	public GXDLMSSecureClient() {
		ciphering = new GXCiphering("ABCDEFGH".getBytes());
		setCipher(ciphering);
	}

	/**
	 * Constructor.
	 * 
	 * @param useLogicalNameReferencing
	 *            Is Logical Name referencing used.
	 * @param clientAddress
	 *            Server address.
	 * @param serverAddress
	 *            Client address.
	 * @param forAuthentication
	 *            Authentication type.
	 * @param password
	 *            Password if authentication is used.
	 * @param interfaceType
	 *            Object type.
	 */
	public GXDLMSSecureClient(final boolean useLogicalNameReferencing,
			final int clientAddress, final int serverAddress,
			final Authentication forAuthentication, final String password,
			final InterfaceType interfaceType) {
		super(useLogicalNameReferencing, clientAddress, serverAddress,
				forAuthentication, password, interfaceType);
		ciphering = new GXCiphering("ABCDEFGH".getBytes());
		setCipher(ciphering);
	}

	/**
	 * @return Ciphering settings.
	 */
	public final GXCiphering getCiphering() {
		return ciphering;
	}

	/**
	 * Encrypt data using Key Encrypting Key.
	 * 
	 * @param kek
	 *            Key Encrypting Key, also known as Master key.
	 * @param data
	 *            Data to encrypt.
	 * @return Encrypt data.
	 */
	public static byte[] encrypt(final byte[] kek, final byte[] data) {
		if (kek == null) {
			throw new NullPointerException("Key Encrypting Key");
		}
		if (kek.length < 16) {
			throw new IllegalArgumentException("Key Encrypting Key");
		}
		if (kek.length % 8 != 0) {
			throw new IllegalArgumentException("Key Encrypting Key");
		}
		GXDLMSChipperingStream gcm = new GXDLMSChipperingStream(true, kek);
		return gcm.encryptAes(data);
	}

	/**
	 * Decrypt data using Key Encrypting Key.
	 * 
	 * @param kek
	 *            Key Encrypting Key, also known as Master key.
	 * @param data
	 *            Data to decrypt.
	 * @return Decrypted data.
	 */
	public static byte[] decrypt(final byte[] kek, final byte[] data) {
		if (kek == null) {
			throw new NullPointerException("Key Encrypting Key");
		}
		if (kek.length < 16) {
			throw new IllegalArgumentException("Key Encrypting Key");
		}
		if (kek.length % 8 != 0) {
			throw new IllegalArgumentException("Key Encrypting Key");
		}
		if (data == null) {
			throw new NullPointerException("data");
		}
		if (data.length < 16) {
			throw new IllegalArgumentException("data");
		}
		if (data.length % 8 != 0) {
			throw new IllegalArgumentException("data");
		}
		GXDLMSChipperingStream gcm = new GXDLMSChipperingStream(false, kek);
		return gcm.decryptAes(data);
	}

	/**
	 * @return Client certificate.
	 */
	public final GXx509Certificate getClientCertificate() {
		return clientCertificate;
	}

	/**
	 * @param value
	 *            Client certificate.
	 */
	public final void setClientCertificate(final GXx509Certificate value) {
		clientCertificate = value;
	}

	/**
	 * @return Server certificate.
	 */
	public final GXx509Certificate getServerCertificate() {
		return serverCertificate;
	}

	/**
	 * @param value
	 *            Server certificate.
	 */
	public final void setServerCertificate(final GXx509Certificate value) {
		serverCertificate = value;
	}

	/**
	 * @return Available certificates.
	 */
	public final Vector getCertificates() {
		return getSettings().getCertificates();
	}
}
