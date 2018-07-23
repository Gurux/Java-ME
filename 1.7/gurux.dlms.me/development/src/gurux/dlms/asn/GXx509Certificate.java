//
// --------------------------------------------------------------------------
//  Gurux Ltd
package gurux.dlms.asn;

import java.security.PublicKey;
import java.util.Date;

import com.oracle.crypto.keypair.PrivateKey;

import gurux.dlms.asn.enums.PkcsObjectIdentifier;
import gurux.dlms.asn.enums.X9ObjectIdentifier;

/**
 * Pkcs10 certification request.
 */
public class GXx509Certificate {
    private GXAsn1Sequence subjectPKInfo;
    /**
     * Subject algorithm.
     */
    private PkcsObjectIdentifier subjectAlgorithm;
    private Object subjectParameters;

    private Object params;

    /**
     * Constructor.
     */
    public GXx509Certificate() {
        version = 2;
    }

    private void init(final byte[] data) {
        GXAsn1Sequence seq =
                (GXAsn1Sequence) GXAsn1Converter.fromByteArray(data);
        if (seq.size() != 3) {
            throw new IllegalArgumentException(
                    "Wrong number of elements in sequence.");
        }
        GXAsn1Sequence reqInfo = (GXAsn1Sequence) seq.get(0);
        version =
                ((Number) ((GXAsn1Context) reqInfo.get(0)).get(0)).byteValue();
        serialNumber = (GXAsn1Integer) reqInfo.get(1);
        // Signature Algorithm
        algorithm = PkcsObjectIdentifier
                .forValue(((GXAsn1Sequence) reqInfo.get(2)).get(0).toString());

        parameters = ((GXAsn1Sequence) reqInfo.get(2)).get(1);
        // Issuer
        issuer = GXAsn1Converter.getSubject((GXAsn1Sequence) reqInfo.get(3));
        // Validity
        validFrom = (Date) ((GXAsn1Sequence) reqInfo.get(4)).get(0);
        validTo = (Date) ((GXAsn1Sequence) reqInfo.get(4)).get(1);
        subject = GXAsn1Converter.getSubject((GXAsn1Sequence) reqInfo.get(5));
        // subject public key Info
        subjectPKInfo = (GXAsn1Sequence) reqInfo.get(6);
        subjectAlgorithm = PkcsObjectIdentifier.forValue(
                ((GXAsn1Sequence) subjectPKInfo.get(0)).get(0).toString());
        subjectParameters = ((GXAsn1Sequence) subjectPKInfo.get(0)).get(1);
        // Make public key.
        publicKey = GXAsn1Converter.getPublicKey(
                X9ObjectIdentifier.IdECPublicKey, X9ObjectIdentifier.Prime256v1,
                new GXAsn1PublicKey((GXAsn1BitString) subjectPKInfo.get(1)));

        params = seq.get(1);
        // signature
        signature = ((GXAsn1BitString) seq.get(2)).getValue();
    }

    /**
     * Constructor.
     * 
     * @param data
     *            Encoded bytes.
     */
    public GXx509Certificate(final byte[] data) {
        init(data);
    }

    /**
     * Public key.
     */
    private PublicKey publicKey;

    /**
     * Algorithm.
     */
    private PkcsObjectIdentifier algorithm;

    /**
     * Parameters.
     */
    private Object parameters;

    /**
     * Signature.
     */
    private byte[] signature;

    /**
     * Subject. Example: "CN=Test O=Gurux, L=Tampere, C=FI".
     */
    private String subject;

    /**
     * Issuer. Example: "CN=Test O=Gurux, L=Tampere, C=FI".
     */
    private String issuer;

    /**
     * Serial number.
     */
    private GXAsn1Integer serialNumber;

    /**
     * Version.
     */
    private byte version;
    /**
     * Validity from.
     */
    private Date validFrom;
    /**
     * Validity to.
     */
    private Date validTo;

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
     * @return Issuer.
     */
    public final String getIssuer() {
        return issuer;
    }

    /**
     * @param value
     *            Issuer.
     */
    public final void setIssuer(final String value) {
        issuer = value;
    }

    /**
     * Serial number.
     */
    public final GXAsn1Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param value
     *            Serial number.
     */
    public final void setSerialNumber(final GXAsn1Integer value) {
        serialNumber = value;
    }

    /**
     * @return Version number.
     */
    public final byte getVersion() {
        return version;
    }

    /**
     * @param version
     *            Version number.
     */
    public final void setVersion(final byte value) {
        version = value;
    }

    /**
     * @return Validity from.
     */
    public final Date getValidFrom() {
        return validFrom;
    }

    /**
     * @param validFrom
     *            Validity from.
     */
    public final void setValidFrom(final Date value) {
        validFrom = value;
    }

    /**
     * @return Validity to.
     */
    public final Date getValidTo() {
        return validTo;
    }

    /**
     * @param value
     *            Validity to.
     */
    public final void setValidTo(final Date value) {
        validTo = value;
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

    /**
     * @return Public key.
     */
    public final PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * @param value
     *            Public key.
     */
    public final void setPublicKey(final PublicKey value) {
        publicKey = value;
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

    public final byte[] getEncoded() {
        GXAsn1ObjectIdentifier a =
                new GXAsn1ObjectIdentifier(algorithm.getValue());
        GXAsn1Context p = new GXAsn1Context();
        p.add(new Byte(version));
        Object[] list = new Object[] {
                new Object[] { p, serialNumber, new Object[] { a, parameters },
                        GXAsn1Converter.encodeSubject(issuer),
                        new Object[] { validFrom, validTo },
                        GXAsn1Converter.encodeSubject(subject), subjectPKInfo },
                params, new GXAsn1BitString(signature, 0) };
        return GXAsn1Converter.toByteArray(list);
    }

    /**
     * Sign certificate.
     * 
     * @param key
     *            Private key.
     */
    public final void sign(final PrivateKey key) {

    }
}
