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

package gurux.dlms.asn.enums;

public class PkcsObjectIdentifier {
    /**
     * pkcs-1 OBJECT IDENTIFIER ::= { iso(1) member-body(2) us(840)
     * rsadsi(113549) pkcs(1) 1 }
     */
    /**
     * Rsa encryption.
     */
	public static final PkcsObjectIdentifier RsaEncryption = new PkcsObjectIdentifier("1.2.840.113549.1.1.1");
    /**
     * 
     */
			public static final PkcsObjectIdentifier MD2WithRsaEncryption = new PkcsObjectIdentifier("1.2.840.113549.1.1.2");
    /**
     * 
     */
			public static final PkcsObjectIdentifier MD4WithRsaEncryption = new PkcsObjectIdentifier("1.2.840.113549.1.1.3");
    /**
     * 
     */
			public static final PkcsObjectIdentifier MD5WithRsaEncryption = new PkcsObjectIdentifier("1.2.840.113549.1.1.4");
    /**
     * 
     */
			public static final PkcsObjectIdentifier Sha1WithRsaEncryption = new PkcsObjectIdentifier("1.2.840.113549.1.1.5");
    /**
     * 
     */
			public static final PkcsObjectIdentifier SrsaOaepEncryptionSet = new PkcsObjectIdentifier("1.2.840.113549.1.1.6");
    /**
     * 
     */
			public static final PkcsObjectIdentifier IdRsaesOaep = new PkcsObjectIdentifier("1.2.840.113549.1.1.7");
    /**
     * 
     */
			public static final PkcsObjectIdentifier IdMgf1 = new PkcsObjectIdentifier("1.2.840.113549.1.1.8");
    /**
     * 
     */
			public static final PkcsObjectIdentifier IdPSpecified = new PkcsObjectIdentifier("1.2.840.113549.1.1.9");
    /**
     * 
     */
			public static final PkcsObjectIdentifier IdRsassaPss = new PkcsObjectIdentifier("1.2.840.113549.1.1.10");
    /**
     * 
     */
			public static final PkcsObjectIdentifier Sha256WithRsaEncryption = new PkcsObjectIdentifier("1.2.840.113549.1.1.11");
    /**
     * 
     */
			public static final PkcsObjectIdentifier Sha384WithRsaEncryption = new PkcsObjectIdentifier("1.2.840.113549.1.1.12");
    /**
     * 
     */
			public static final PkcsObjectIdentifier Sha512WithRsaEncryption = new PkcsObjectIdentifier("1.2.840.113549.1.1.13");
    /**
     * 
     */
			public static final PkcsObjectIdentifier Sha224WithRsaEncryption = new PkcsObjectIdentifier("1.2.840.113549.1.1.14");
    //
    // pkcs-3 OBJECT IDENTIFIER ::= {
    // iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 3 }
    //

    /**
     * 
     */
			public static final PkcsObjectIdentifier DhKeyAgree1ment = new PkcsObjectIdentifier("1.2.840.113549.1.3.1");
    //
    // pkcs-5 OBJECT IDENTIFIER ::= {
    // iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 5 }
    //

    /**
     * 
     */
			public static final PkcsObjectIdentifier PbeWithMD2AndDesCbc = new PkcsObjectIdentifier("1.2.840.113549.1.5.1");
    /**
     * 
     */
			public static final PkcsObjectIdentifier PbeWithMD2AndRC2Cbc = new PkcsObjectIdentifier("1.2.840.113549.1.5.4");
    /**
     * 
     */
			public static final PkcsObjectIdentifier PbeWithMD5AndDesCbc = new PkcsObjectIdentifier("1.2.840.113549.1.5.3");
    /**
     * 
     */
			public static final PkcsObjectIdentifier PbeWithMD5AndRC2Cbc = new PkcsObjectIdentifier("1.2.840.113549.1.5.6");
    /**
     * 
     */
			public static final PkcsObjectIdentifier PbeWithSha1AndDesCbc = new PkcsObjectIdentifier("1.2.840.113549.1.5.10");
    /**
     * 
     */
			public static final PkcsObjectIdentifier PbeWithSha1AndRC2Cbc = new PkcsObjectIdentifier("1.2.840.113549.1.5.11");
    /**
     * 
     */
			public static final PkcsObjectIdentifier IdPbeS2 = new PkcsObjectIdentifier("1.2.840.113549.1.5.13");
    /**
     * 
     */
			public static final PkcsObjectIdentifier IdPbkdf2 = new PkcsObjectIdentifier("1.2.840.113549.1.5.12");

    //
    // encryptionAlgorithm OBJECT IDENTIFIER ::= {
    // iso(1) member-body(2) us(840) rsadsi(113549) 3 }
    //
			public static final PkcsObjectIdentifier DesEde3Cbc = new PkcsObjectIdentifier("1.2.840.113549.3.7"); 
			public static final PkcsObjectIdentifier RC2Cbc = new PkcsObjectIdentifier("1.2.840.113549.3.2");

    // md2 OBJECT IDENTIFIER ::=
    // {iso(1) member-body(2) US(840) rsadsi(113549) DigestAlgorithm(2) 2}
    //
			public static final PkcsObjectIdentifier MD2 = new PkcsObjectIdentifier("1.2.840.113549.2.2");

    //
    // md4 OBJECT IDENTIFIER ::=
    // {iso(1) member-body(2) US(840) rsadsi(113549) DigestAlgorithm(2) 4}
    //
			public static final PkcsObjectIdentifier MD4 = new PkcsObjectIdentifier("1.2.840.113549.2.4");

    //
    // md5 OBJECT IDENTIFIER ::=
    // {iso(1) member-body(2) US(840) rsadsi(113549) DigestAlgorithm(2) 5}
    //
			public static final PkcsObjectIdentifier MD5 = new PkcsObjectIdentifier("1.2.840.113549.2.5");

			public static final PkcsObjectIdentifier IdHmacWithSha1 = new PkcsObjectIdentifier("1.2.840.113549.2.7");
			public static final PkcsObjectIdentifier IdHmacWithSha224 = new PkcsObjectIdentifier("1.2.840.113549.2.8");
			public static final PkcsObjectIdentifier IdHmacWithSha256 = new PkcsObjectIdentifier("1.2.840.113549.2.9");
			public static final PkcsObjectIdentifier IdHmacWithSha384 = new PkcsObjectIdentifier("1.2.840.113549.2.10");
			public static final PkcsObjectIdentifier IdHmacWithSha512 = new PkcsObjectIdentifier("1.2.840.113549.2.11");

    //
    // pkcs-7 OBJECT IDENTIFIER ::= {
    // iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 7 }
    //

			public static final PkcsObjectIdentifier Data = new PkcsObjectIdentifier("1.2.840.113549.1.7.1"); 
			public static final PkcsObjectIdentifier SignedData = new PkcsObjectIdentifier("1.2.840.113549.1.7.2");
			public static final PkcsObjectIdentifier EnvelopedData = new PkcsObjectIdentifier("1.2.840.113549.1.7.3");
			public static final PkcsObjectIdentifier SignedAndEnvelopedData = new PkcsObjectIdentifier("1.2.840.113549.1.7.4");
			public static final PkcsObjectIdentifier DigestedData = new PkcsObjectIdentifier("1.2.840.113549.1.7.5"); 
			public static final PkcsObjectIdentifier EncryptedData = new PkcsObjectIdentifier("1.2.840.113549.1.7.6");

    //
    // pkcs-9 OBJECT IDENTIFIER ::= {
    // iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 9 }
    //

			public static final PkcsObjectIdentifier Pkcs9AtEmailAddress = new PkcsObjectIdentifier("1.2.840.113549.1.9.1");
			public static final PkcsObjectIdentifier Pkcs9AtUnstructuredName = new PkcsObjectIdentifier("1.2.840.113549.1.9.2");
			public static final PkcsObjectIdentifier Pkcs9AtContentType = new PkcsObjectIdentifier("1.2.840.113549.1.9.3");
			public static final PkcsObjectIdentifier Pkcs9AtMessageDigest = new PkcsObjectIdentifier("1.2.840.113549.1.9.4");
			public static final PkcsObjectIdentifier Pkcs9AtSigningTime = new PkcsObjectIdentifier("1.2.840.113549.1.9.5");
			public static final PkcsObjectIdentifier Pkcs9AtCounterSignature = new PkcsObjectIdentifier("1.2.840.113549.1.9.6");
			public static final PkcsObjectIdentifier Pkcs9AtChallengePassword = new PkcsObjectIdentifier("1.2.840.113549.1.9.7");
			public static final PkcsObjectIdentifier Pkcs9AtUnstructuredAddress = new PkcsObjectIdentifier("1.2.840.113549.1.9.8");
			public static final PkcsObjectIdentifier Pkcs9AtExtendedCertificateAttributes = new PkcsObjectIdentifier("1.2.840.113549.1.9.9");
			public static final PkcsObjectIdentifier Pkcs9AtSigningDescription = new PkcsObjectIdentifier("1.2.840.113549.1.9.13");
			public static final PkcsObjectIdentifier Pkcs9AtExtensionRequest = new PkcsObjectIdentifier("1.2.840.113549.1.9.14");
			public static final PkcsObjectIdentifier Pkcs9AtSmimeCapabilities = new PkcsObjectIdentifier("1.2.840.113549.1.9.15");
			public static final PkcsObjectIdentifier IdSmime = new PkcsObjectIdentifier("1.2.840.113549.1.9.16");

			public static final PkcsObjectIdentifier Pkcs9AtFriendlyName = new PkcsObjectIdentifier("1.2.840.113549.1.9.20");
			public static final PkcsObjectIdentifier Pkcs9AtLocalKeyID = new PkcsObjectIdentifier("1.2.840.113549.1.9.21");

			public static final PkcsObjectIdentifier X509CertType = new PkcsObjectIdentifier("1.2.840.113549.1.9.22.1");

			public static final PkcsObjectIdentifier X509Certificate = new PkcsObjectIdentifier("1.2.840.113549.1.9.22.1");
			public static final PkcsObjectIdentifier SdsiCertificate = new PkcsObjectIdentifier("1.2.840.113549.1.9.22.2");

			public static final PkcsObjectIdentifier X509Crl = new PkcsObjectIdentifier("1.2.840.113549.1.9.23.1");

			public static final PkcsObjectIdentifier IdAlg = new PkcsObjectIdentifier(IdSmime + ".3");

			public static final PkcsObjectIdentifier IdAlgEsdh = new PkcsObjectIdentifier(IdAlg + "5"); 
			public static final PkcsObjectIdentifier IdAlgCms3DesWrap = new PkcsObjectIdentifier(IdAlg + ".6");
			public static final PkcsObjectIdentifier IdAlgCmsRC2Wrap = new PkcsObjectIdentifier(IdAlg + "7"); 
			public static final PkcsObjectIdentifier IdAlgPwriKek = new PkcsObjectIdentifier(IdAlg + ".9");
			public static final PkcsObjectIdentifier IdAlgSsdh = new PkcsObjectIdentifier(IdAlg + ".10");

    /*
     * <pre> -- RSA-KEM Key Transport Algorithm id-rsa-kem OID ::= { iso(1)
     * member-body(2) us(840) rsadsi(113549) pkcs(1) pkcs-9(9) smime(16) alg(3)
     * 14 } </pre>
     */
			public static final PkcsObjectIdentifier IdRsaKem = new PkcsObjectIdentifier(IdAlg + ".14");

    //
    // SMIME capability sub oids.
    //
			public static final PkcsObjectIdentifier PreferSignedData = new PkcsObjectIdentifier(Pkcs9AtSmimeCapabilities + ".1");
			public static final PkcsObjectIdentifier CannotDecryptAny = new PkcsObjectIdentifier(Pkcs9AtSmimeCapabilities + ".2");
			public static final PkcsObjectIdentifier SmimeCapabilitiesVersions = new PkcsObjectIdentifier(Pkcs9AtSmimeCapabilities + ".3");

    //
    // other SMIME attributes
    //
			public static final PkcsObjectIdentifier IdAAReceiptRequest = new PkcsObjectIdentifier(IdSmime + ".2.1");

    //
    // id-ct OBJECT IDENTIFIER ::= {iso(1) member-body(2) usa(840)
    // rsadsi(113549) pkcs(1) pkcs-9(9) smime(16) ct(1)}
    //
			public static final PkcsObjectIdentifier IdCTAuthData = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.1.2");
			public static final PkcsObjectIdentifier IdCTTstInfo = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.1.4");
			public static final PkcsObjectIdentifier IdCTCompressedData = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.1.9");
			public static final PkcsObjectIdentifier IdCTAuthEnvelopedData = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.1.23");
			public static final PkcsObjectIdentifier IdCTTimestampedData = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.1.31");

    //
    // id-cti OBJECT IDENTIFIER ::= {iso(1) member-body(2) usa(840)
    // rsadsi(113549) pkcs(1) pkcs-9(9) smime(16) cti(6)}
    //
			public static final PkcsObjectIdentifier IdCtiEtsProofOfOrigin = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.6.1");
			public static final PkcsObjectIdentifier IdCtiEtsProofOfReceipt = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.6.2");
			public static final PkcsObjectIdentifier IdCtiEtsProofOfDelivery = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.6.3");
			public static final PkcsObjectIdentifier IdCtiEtsProofOfSender = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.6.4");
			public static final PkcsObjectIdentifier IdCtiEtsProofOfApproval = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.6.5");
			public static final PkcsObjectIdentifier IdCtiEtsProofOfCreation = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.6.6");

    //
    // id-aa OBJECT IDENTIFIER ::= {iso(1) member-body(2) usa(840)
    // rsadsi(113549) pkcs(1) pkcs-9(9) smime(16) attributes(2)}
    //
			public static final PkcsObjectIdentifier IdAAContentHint = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.4"); // See RFC 2634
			public static final PkcsObjectIdentifier IdAAMsgSigDigest = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.5");
			public static final PkcsObjectIdentifier IdAAContentReference = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.10");

    /*
     * id-aa-encrypKeyPref OBJECT IDENTIFIER ::= {id-aa 11}
     */
			public static final PkcsObjectIdentifier IdAAEncrypKeyPref = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.11");
			public static final PkcsObjectIdentifier IdAASigningCertificate = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.12");
			public static final PkcsObjectIdentifier IdAASigningCertificateV2 = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.47");

			public static final PkcsObjectIdentifier IdAAContentIdentifier = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.7"); // See RFC 2634

    /*
     * RFC 3126
     */
			public static final PkcsObjectIdentifier IdAASignatureTimeStampToken = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.14");

			public static final PkcsObjectIdentifier IdAAEtsSigPolicyID = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.15");
			public static final PkcsObjectIdentifier IdAAEtsCommitmentType = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.16");
			public static final PkcsObjectIdentifier IdAAEtsSignerLocation = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.17");
			public static final PkcsObjectIdentifier IdAAEtsSignerAttr = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.18");
			public static final PkcsObjectIdentifier IdAAEtsOtherSigCert = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.19");
			public static final PkcsObjectIdentifier IdAAEtsContentTimestamp = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.20");
			public static final PkcsObjectIdentifier IdAAEtsCertificateRefs = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.21");
			public static final PkcsObjectIdentifier IdAAEtsRevocationRefs = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.22");
			public static final PkcsObjectIdentifier IdAAEtsCertValues = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.23");
			public static final PkcsObjectIdentifier IdAAEtsRevocationValues = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.24");
			public static final PkcsObjectIdentifier IdAAEtsEscTimeStamp = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.25");
			public static final PkcsObjectIdentifier IdAAEtsCertCrlTimestamp = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.26");
			public static final PkcsObjectIdentifier IdAAEtsArchiveTimestamp = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.2.27");
    //
    // id-spq OBJECT IDENTIFIER ::= {iso(1) member-body(2) usa(840)
    // rsadsi(113549) pkcs(1) pkcs-9(9) smime(16) id-spq(5)}
    //

			public static final PkcsObjectIdentifier IdSpqEtsUri = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.5.1");
			public static final PkcsObjectIdentifier IdSpqEtsUNotice = new PkcsObjectIdentifier("1.2.840.113549.1.9.16.5.2");

    //
    // pkcs-12 OBJECT IDENTIFIER ::= {
    // iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 12 }
    //

			public static final PkcsObjectIdentifier KeyBag = new PkcsObjectIdentifier("1.2.840.113549.1.12.10.1.1");
			public static final PkcsObjectIdentifier Pkcs8ShroudedKeyBag = new PkcsObjectIdentifier("1.2.840.113549.1.12.10.1.2");
			public static final PkcsObjectIdentifier CertBag = new PkcsObjectIdentifier("1.2.840.113549.1.12.10.1.3"); 
			public static final PkcsObjectIdentifier CrlBag = new PkcsObjectIdentifier("1.2.840.113549.1.12.10.1.4");
			public static final PkcsObjectIdentifier SecretBag = new PkcsObjectIdentifier("1.2.840.113549.1.12.10.1.5");
			public static final PkcsObjectIdentifier SafeContentsBag = new PkcsObjectIdentifier("1.2.840.113549.1.12.10.1.6");

			public static final PkcsObjectIdentifier PbeWithShaAnd128BitRC4 = new PkcsObjectIdentifier("1.2.840.113549.1.12.1.1");
			public static final PkcsObjectIdentifier PbeWithShaAnd40BitRC4 = new PkcsObjectIdentifier("1.2.840.113549.1.12.1.2");
			public static final PkcsObjectIdentifier PbeWithShaAnd3KeyTripleDesCbc = new PkcsObjectIdentifier("1.2.840.113549.1.12.1.3");
			public static final PkcsObjectIdentifier PbeWithShaAnd2KeyTripleDesCbc = new PkcsObjectIdentifier("1.2.840.113549.1.12.1.4");
			public static final PkcsObjectIdentifier PbeWithShaAnd128BitRC2Cbc = new PkcsObjectIdentifier("1.2.840.113549.1.12.1.5");
			public static final PkcsObjectIdentifier PbewithShaAnd40BitRC2Cbc = new PkcsObjectIdentifier("1.2.840.113549.1.12.1.6");

    /*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private PkcsObjectIdentifier(String value) {
		strValue = value;
		getMappings().put(value, this);
	}

	/*
	 * Enumeration value.
	 */
	private final String strValue;

	/*
	 * Collection of enumeration values.
	 */
	private static java.util.Hashtable mappings;

	/*
	 * Collection of enumeration values.
	 */
	private static java.util.Hashtable getMappings() {
		synchronized (PkcsObjectIdentifier.class) {
			if (mappings == null) {
				mappings = new java.util.Hashtable();
			}
		}
		return mappings;
	}

	/*
	 * Get integer value for enumeration.
	 */
	public String getValue() {
		return strValue;
	}

	/*
	 * Convert integer to enumeration value.
	 */
	public static PkcsObjectIdentifier forValue(String value) {
		return (PkcsObjectIdentifier) getMappings().get(value);
	}
}
