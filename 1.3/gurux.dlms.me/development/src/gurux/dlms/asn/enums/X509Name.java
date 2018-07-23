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
 * X509 names.
 */
public class X509Name {

	/**
	 * country code - StringType=SIZE=2))
	 */
	public static final X509Name C = new X509Name("2.5.4.6");

	/**
	 * organization - StringType=SIZE=1..64))
	 */
	public static final X509Name O = new X509Name("2.5.4.10");

	/**
	 * organizational unit name - StringType=SIZE=1..64))
	 */
	public static final X509Name OU = new X509Name("2.5.4.11");

	/**
	 * Title
	 */
	public static final X509Name T = new X509Name("2.5.4.12");

	/**
	 * common name - StringType=SIZE=1..64))
	 */
	public static final X509Name CN = new X509Name("2.5.4.3");

	/**
	 * street - StringType=SIZE=1..64))
	 */
	public static final X509Name STREET = new X509Name("2.5.4.9");

	/**
	 * device serial number name - StringType=SIZE=1..64))
	 */
	public static final X509Name SERIAL_NUMBER = new X509Name("2.5.4.5");

	/**
	 * locality name - StringType=SIZE=1..64))
	 */
	public static final X509Name L = new X509Name("2.5.4.7");

	/**
	 * state, or province name - StringType=SIZE=1..64))
	 */
	public static final X509Name ST = new X509Name("2.5.4.8");

	/**
	 * Naming attributes of type X520name
	 */
	public static final X509Name SUR_NAME = new X509Name("2.5.4.4");
	/**
	 * Given name.
	 */
	public static final X509Name GIVEN_NAME = new X509Name("2.5.4.42");

	/**
	 * Initials.
	 */
	public static final X509Name INITIALS = new X509Name("2.5.4.43");

	/**
	 * Generation.
	 */
	public static final X509Name GENERATION = new X509Name("2.5.4.44");

	/**
	 * Unique identifier.
	 */
	public static final X509Name UNIQUE_IDENTIFIER = new X509Name("2.5.4.45");

	/**
	 * businessCategory - DirectoryString=SIZE=1..128))
	 */
	public static final X509Name BUSINESS_CATEGORY = new X509Name("2.5.4.15");

	/**
	 * postalCode - DirectoryString=SIZE=1..40))
	 */
	public static final X509Name POSTAL_CODE = new X509Name("2.5.4.17");

	/**
	 * dnQualifier - DirectoryString=SIZE=1..64))
	 */
	public static final X509Name DN_QUALIFIER = new X509Name("2.5.4.46");

	/**
	 * RFC 3039 Pseudonym - DirectoryString=SIZE=1..64))
	 */
	public static final X509Name PSEUDONYM = new X509Name("2.5.4.65");

	/**
	 * RFC 3039 DateOfBirth - GeneralizedTime - YYYYMMDD000000Z
	 */
	public static final X509Name DATE_OF_BIRTH = new X509Name("1.3.6.1.5.5.7.9.1");

	/**
	 * RFC 3039 PlaceOfBirth - DirectoryString=SIZE=1..128))
	 */
	public static final X509Name PLACE_OF_BIRTH = new X509Name("1.3.6.1.5.5.7.9.2");

	/**
	 * RFC 3039 DateOfBirth - PrintableString =SIZE=1 -- "M", "F", "m" or "f")
	 */
	public static final X509Name GENDER = new X509Name("1.3.6.1.5.5.7.9.3");

	/**
	 * RFC 3039 CountryOfCitizenship - PrintableString =SIZE =2 -- ISO 3166))
	 * codes only
	 */
	public static final X509Name COUNTRY_OF_CITIZENSHIP = new X509Name("1.3.6.1.5.5.7.9.4");

	/**
	 * RFC 3039 CountryOfCitizenship - PrintableString =SIZE =2 -- ISO 3166))
	 * codes only
	 */
	public static final X509Name COUNTRY_OF_RESIDENCE = new X509Name("1.3.6.1.5.5.7.9.5");

	/**
	 * ISIS-MTT NameAtBirth - DirectoryString=SIZE=1..64))
	 */
	public static final X509Name NAME_AT_BIRTH = new X509Name("1.3.36.8.3.14");

	/**
	 * RFC 3039 PostalAddress - SEQUENCE SIZE =1..6 OF
	 * DirectoryString=SIZE=1..30)))
	 */
	public static final X509Name POSTAL_ADDRESS = new X509Name("2.5.4.16");

	/**
	 * RFC 2256 dmdName
	 */
	public static final X509Name DMD_NAME = new X509Name("2.5.4.54");

	/**
	 * id-at-telephoneNumber
	 */
	public static final X509Name TelephoneNumber = new X509Name("2.5.4.20");

	/**
	 * id-at-name
	 */
	public static final X509Name Name = new X509Name("2.5.4.41");

	/**
	 * email address in Verisign certificates
	 */
	public static final X509Name E = new X509Name("1.2.840.113549.1.9.1");

	public static final X509Name DC = new X509Name("0.9.2342.19200300.100.1.25");

	/**
	 * LDAP User id.
	 */
	public static final X509Name UID = new X509Name("0.9.2342.19200300.100.1.1");
	
	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private X509Name(String value) {
		strValue = value;
		getMappings().put(strValue, this);
	}

	/*
	 * Enumeration name.
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
		synchronized (X509Name.class) {
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
	public static X509Name valueOf(String value) {
		return (X509Name) getMappings().get(value);
	}
}
