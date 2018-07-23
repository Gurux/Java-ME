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

/**
 * X9 object identifiers.
 */
public class X9ObjectIdentifier {

	//
	// X9.62
	//
	// ansi-X9-62 OBJECT IDENTIFIER ::= { iso=1) member-body=2)
	// us=840) ansi-x962=10045) }
	//

	/**
	 * IdFieldType.
	 */
	public static final X9ObjectIdentifier IdFieldType = new X9ObjectIdentifier("1.2.840.10045.1");
	/**
	 * PrimeField
	 */
	public static final X9ObjectIdentifier PrimeField = new X9ObjectIdentifier("1.2.840.10045.1");
	/**
	 * 
	 */
	public static final X9ObjectIdentifier CharacteristicTwoField = new X9ObjectIdentifier("1.2.840.10045.1.2");
	/**
	 * 
	 */
	public static final X9ObjectIdentifier GNBasis = new X9ObjectIdentifier("1.2.840.10045.1.2.3.1");
	/**
	 * 
	 */
	public static final X9ObjectIdentifier TPBasis = new X9ObjectIdentifier("1.2.840.10045.1.2.3.2");
	/**
	 * 
	 */
	public static final X9ObjectIdentifier PPBasis = new X9ObjectIdentifier("1.2.840.10045.1.2.3.3");

	/**
	 * 
	 */
	public static final X9ObjectIdentifier ECDsaWithSha1 = new X9ObjectIdentifier("1.2.840.10045.4.1");

	/**
	 * 
	 */
	public static final X9ObjectIdentifier IdECPublicKey = new X9ObjectIdentifier("1.2.840.10045.2.1");

	/**
	 * 
	 */
	public static final X9ObjectIdentifier ECDsaWithSha2 = new X9ObjectIdentifier("1.2.840.10045.4.3");

	/**
	 * 
	 */
	public static final X9ObjectIdentifier ECDsaWithSha224 = new X9ObjectIdentifier("1.2.840.10045.4.31");
	/**
	* 
	*/
	public static final X9ObjectIdentifier ECDsaWithSha256 = new X9ObjectIdentifier("1.2.840.10045.4.32");
	/**
	* 
	*/
	public static final X9ObjectIdentifier ECDsaWithSha384 = new X9ObjectIdentifier("1.2.840.10045.4.33");
	/**
	* 
	*/
	public static final X9ObjectIdentifier ECDsaWithSha512 = new X9ObjectIdentifier("1.2.840.10045.4.34");

	/**
	 * 
	*/
	public static final X9ObjectIdentifier EllipticCurve = new X9ObjectIdentifier("1.2.840.10045.3");

	/**
	 * Two Curves
	 */
	public static final X9ObjectIdentifier CTwoCurve = new X9ObjectIdentifier("1.2.840.10045.3.0");

	/**
	 * 
	 */
	public static final X9ObjectIdentifier C2Pnb163v1 = new X9ObjectIdentifier("1.2.840.10045.3.0.1");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Pnb163v2 = new X9ObjectIdentifier("1.2.840.10045.3.0.2");
	/**
	 * 
	 */
	public static final X9ObjectIdentifier C2Pnb163v3 = new X9ObjectIdentifier("1.2.840.10045.3.0.3");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Pnb176w1 = new X9ObjectIdentifier("1.2.840.10045.3.0.4");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Tnb191v1 = new X9ObjectIdentifier("1.2.840.10045.3.0.5");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Tnb191v2 = new X9ObjectIdentifier("1.2.840.10045.3.0.6");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Tnb191v3 = new X9ObjectIdentifier("1.2.840.10045.3.0.7");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Onb191v4 = new X9ObjectIdentifier("1.2.840.10045.3.0.8");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Onb191v5 = new X9ObjectIdentifier("1.2.840.10045.3.0.9");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Pnb208w1 = new X9ObjectIdentifier("1.2.840.10045.3.0.10");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Tnb239v1 = new X9ObjectIdentifier("1.2.840.10045.3.0.11");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Tnb239v2 = new X9ObjectIdentifier("1.2.840.10045.3.0.12");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Tnb239v3 = new X9ObjectIdentifier("1.2.840.10045.3.0.13");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Onb239v4 = new X9ObjectIdentifier("1.2.840.10045.3.0.14");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Onb239v5 = new X9ObjectIdentifier("1.2.840.10045.3.0.15");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Pnb272w1 = new X9ObjectIdentifier("1.2.840.10045.3.0.16");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Pnb304w1 = new X9ObjectIdentifier("1.2.840.10045.3.0.17");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Tnb359v1 = new X9ObjectIdentifier("1.2.840.10045.3.0.18");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Pnb368w1 = new X9ObjectIdentifier("1.2.840.10045.3.0.19");
	/**
	* 
	*/
	public static final X9ObjectIdentifier C2Tnb431r1 = new X9ObjectIdentifier("1.2.840.10045.3.0.20");

	/**
	 * Prime
	 */
	public static final X9ObjectIdentifier PrimeCurve = new X9ObjectIdentifier("1.2.840.10045.3.1");
	/**
	 * 
	 */
	public static final X9ObjectIdentifier Prime192v1 = new X9ObjectIdentifier("1.2.840.10045.3.1.1");
	/**
	* 
	*/
	public static final X9ObjectIdentifier Prime192v2 = new X9ObjectIdentifier("1.2.840.10045.3.1.2");
	public static final X9ObjectIdentifier Prime192v3 = new X9ObjectIdentifier("1.2.840.10045.3.1.3");
	/**
	* 
	*/
	public static final X9ObjectIdentifier Prime239v1 = new X9ObjectIdentifier("1.2.840.10045.3.1.4");
	public static final X9ObjectIdentifier Prime239v2 = new X9ObjectIdentifier("1.2.840.10045.3.1.5");
	/**
	* 
	*/
	public static final X9ObjectIdentifier Prime239v3 = new X9ObjectIdentifier("1.2.840.10045.3.1.6");
	public static final X9ObjectIdentifier Prime256v1 = new X9ObjectIdentifier("1.2.840.10045.3.1.7");

	//
	// DSA
	//
	// dsapublicnumber OBJECT IDENTIFIER ::= { iso=1) member-body=2)
	// us=840) ansi-x957=10040) number-type=4) 1 }
	/**
	 * 
	 */
	public static final X9ObjectIdentifier IdDsa = new X9ObjectIdentifier("1.2.840.10040.4.1");

	/**
	 * id-dsa-with-sha1 OBJECT IDENTIFIER ::= { iso=1) member-body=2) us=840)
	 * x9-57 =10040) x9cm=4) 3 }
	 */
	/**
	 * 
	 */
	public static final X9ObjectIdentifier IdDsaWithSha1 = new X9ObjectIdentifier("1.2.840.10040.4.3");

	/**
	 * X9.63
	 */
	/**
	 * 
	 */
	public static final X9ObjectIdentifier X9x63Scheme = new X9ObjectIdentifier("1.3.133.16.840.63.0");

	/**
	 * 
	 */
	public static final X9ObjectIdentifier DHSinglePassStdDHSha1KdfScheme = new X9ObjectIdentifier("1.3.133.16.840.63.0.2");
	/**
	 * 
	 */
	public static final X9ObjectIdentifier DHSinglePassCofactorDHSha1KdfScheme = new X9ObjectIdentifier("1.3.133.16.840.63.0.3");
	/**
	 * 
	*/
	public static final X9ObjectIdentifier MqvSinglePassSha1KdfScheme = new X9ObjectIdentifier("1.3.133.16.840.63.0.16");

	/**
	 * X9.42
	 */
	public static final X9ObjectIdentifier ansi_x9_42 = new X9ObjectIdentifier("1.2.840.10046");

	//
	// Diffie-Hellman
	//
	// dhpublicnumber OBJECT IDENTIFIER ::= { iso=1) member-body=2)
	// us=840) ansi-x942=10046) number-type=2) 1 }
	//
	/**
	 * 
	*/
	public static final X9ObjectIdentifier DHPublicNumber = new X9ObjectIdentifier("1.2.840.10046.2.1");

	/**
	 * 
	*/
	public static final X9ObjectIdentifier X9x42Schemes = new X9ObjectIdentifier("1.2.840.10046.2.3");

	/**
	 * 
	*/
	public static final X9ObjectIdentifier DHStatic = new X9ObjectIdentifier("1.2.840.10046.2.3.1");
	/**
	* 
	*/
	public static final X9ObjectIdentifier DHEphem = new X9ObjectIdentifier("1.2.840.10046.2.3.2");
	/**
	 * 
	*/
	public static final X9ObjectIdentifier DHOneFlow = new X9ObjectIdentifier("1.2.840.10046.2.3.3");
	/**
	* 
	*/
	public static final X9ObjectIdentifier DHHybrid1 = new X9ObjectIdentifier("1.2.840.10046.2.3.4");
	public static final X9ObjectIdentifier DHHybrid2 = new X9ObjectIdentifier("1.2.840.10046.2.3.5");
	/**
	* 
	*/
	public static final X9ObjectIdentifier DHHybridOneFlow = new X9ObjectIdentifier("1.2.840.10046.2.3.6");
	/**
	 * 
	*/
	public static final X9ObjectIdentifier Mqv2 = new X9ObjectIdentifier("1.2.840.10046.2.3.7");
	/**
	* 
	*/
	public static final X9ObjectIdentifier Mqv1 = new X9ObjectIdentifier("1.2.840.10046.2.3.8");
	
	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private X9ObjectIdentifier(String value) {
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
		synchronized (X9ObjectIdentifier.class) {
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
	public static X9ObjectIdentifier forValue(String value) {
		return (X9ObjectIdentifier) getMappings().get(value);
	}
}
