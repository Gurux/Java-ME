//
// --------------------------------------------------------------------------
//  Gurux Ltd
package gurux.dlms.asn;

import java.security.PublicKey;

import gurux.dlms.asn.enums.PkcsObjectIdentifier;
import gurux.dlms.asn.enums.X9ObjectIdentifier;

/**
 * Pkcs10 certification request.
 */
public class GXPkcs10 {

    /**
     * Certificate version.
     */
    private byte version;

    /**
     * Subject.
     */
    private String subject;

    /**
     * Algorithm.
     */
    private PkcsObjectIdentifier algorithm;

    /**
     * Parameters.
     */
    private Object parameters;

    /**
     * public key info.
     */
    private GXAsn1BitString publicKeyInfo;

    /**
     * Subject public key.
     */
    private PublicKey publicKey;

    /**
     * Signature algorithm.
     */
    private PkcsObjectIdentifier signatureAlgorithm;

    /**
     * Signature parameters.
     */
    private Object signatureParameters;

    /**
     * Signature.
     */
    private byte[] signature;

    /**
     * Constructor.
     * 
     * @param data
     *            Encoded bytes.
     */
    public GXPkcs10(final byte[] data) {
        init(data);
    }

    /*
     * CertificationRequestInfo ::= Sequence { version Integer { v1(0) }
     * (v1,...), subject Name, subjectPKInfo SubjectPublicKeyInfo{{
     * PKInfoAlgorithms }}, attributes [0] Attributes{{ CRIAttributes }} }
     */
    private void init(final byte[] data) {
        GXAsn1Sequence seq =
                (GXAsn1Sequence) GXAsn1Converter.fromByteArray(data);
        if (seq.size() != 3) {
            throw new IllegalArgumentException(
                    "Wrong number of elements in sequence.");
        }
        // Certification request info.
        GXAsn1Sequence reqInfo = (GXAsn1Sequence) seq.get(0);
        version = ((Number) reqInfo.get(0)).byteValue();
        subject = GXAsn1Converter.getSubject((GXAsn1Sequence) reqInfo.get(1));
        // subject Public key info.
        GXAsn1Sequence subjectPKInfo = (GXAsn1Sequence) reqInfo.get(2);
        GXAsn1Sequence tmp = (GXAsn1Sequence) subjectPKInfo.get(0);
        algorithm = PkcsObjectIdentifier.forValue(tmp.get(0).toString());
        parameters = tmp.get(1);
        publicKeyInfo = (GXAsn1BitString) subjectPKInfo.get(1);
        // Make public key.
        publicKey = GXAsn1Converter.getPublicKey(
                X9ObjectIdentifier.IdECPublicKey, X9ObjectIdentifier.Prime256v1,
                new GXAsn1PublicKey((GXAsn1BitString) subjectPKInfo.get(1)));
        // signatureAlgorithm
        GXAsn1Sequence sign = (GXAsn1Sequence) seq.get(1);
        signatureAlgorithm =
                PkcsObjectIdentifier.forValue(sign.get(0).toString());
        signatureParameters = (String) sign.get(1);
        // signature
        signature = ((GXAsn1BitString) seq.get(2)).getValue();
    }

    /**
     * @return Certificate version.
     */
    public final byte getVersion() {
        return version;
    }

    /**
     * @param value
     *            Certificate version.
     */
    public final void setVersion(final byte value) {
        version = value;
    }

    /**
     * @return Subject.
     */
    public final String getSubject() {
        return subject;
    }

    /**
     * @param value
     *            Subject.
     */
    public final void setSubject(final String value) {
        subject = value;
    }

    /**
     * @return Subject public key info.
     */
    public final PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * @param value
     *            Subject public key info.
     */
    public final void setPublicKey(final PublicKey value) {
        publicKey = value;
    }

    /**
     * @return Algorithm
     */
    public final PkcsObjectIdentifier getAlgorithm() {
        return algorithm;
    }

    /**
     * @param value
     *            Algorithm
     */
    public final void setAlgorithm(final PkcsObjectIdentifier value) {
        algorithm = value;
    }

    /**
     * @return Parameters.
     */
    public final Object getParameters() {
        return parameters;
    }

    /**
     * @param value
     *            Parameters.
     */
    public final void setParameters(final Object value) {
        parameters = value;
    }

    public final byte[] getEncoded() {
        // Certification request info.
        // subject Public key info.
        GXAsn1ObjectIdentifier sa =
                new GXAsn1ObjectIdentifier(signatureAlgorithm.getValue());
        GXAsn1ObjectIdentifier a =
                new GXAsn1ObjectIdentifier(algorithm.getValue());
        Object[] list = new Object[] {
                new Object[] { new Byte(version),
                        GXAsn1Converter.encodeSubject(subject),
                        new Object[] { new Object[] { a, parameters, },
                                publicKeyInfo },
                        new GXAsn1Context() },
                new Object[] { sa, signatureParameters },
                new GXAsn1BitString(signature, 0) };
        return GXAsn1Converter.toByteArray(list);
    }

    /**
     * @return Signature algorithm.
     */
    public final PkcsObjectIdentifier getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    /**
     * @param value
     *            Signature algorithm.
     */
    public final void setSignatureAlgorithm(final PkcsObjectIdentifier value) {
        signatureAlgorithm = value;
    }

    /**
     * @return Signature parameters.
     */
    public final Object getSignatureParameters() {
        return signatureParameters;
    }

    /**
     * @param value
     *            Signature parameters.
     */
    public final void setSignatureParameters(final Object value) {
        signatureParameters = value;
    }

    /**
     * @return Signature.
     */
    public final byte[] getSignature() {
        return signature;
    }

    /**
     * @param value
     *            Signature.
     */
    public final void setSignature(final byte[] value) {
        signature = value;
    }
}
