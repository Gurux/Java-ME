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

package gurux.dlms.objects.enums;

/**
 *
 * Holds the status of the Image transfer process.
 */
public class ImageTransferStatus {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	public static final ImageTransferStatus IMAGE_TRANSFER_NOT_INITIATED = new ImageTransferStatus(
			0, "IMAGE_TRANSFER_NOT_INITIATED");
	public static final ImageTransferStatus IMAGE_TRANSFER_INITIATED = new ImageTransferStatus(
			1, "IMAGE_TRANSFER_INITIATED");
	public static final ImageTransferStatus IMAGE_VERIFICATION_INITIATED = new ImageTransferStatus(
			2, "IMAGE_VERIFICATION_INITIATED");
	public static final ImageTransferStatus IMAGE_VERIFICATION_SUCCESSFUL = new ImageTransferStatus(
			3, "IMAGE_VERIFICATION_SUCCESSFUL");
	public static final ImageTransferStatus IMAGE_VERIFICATION_FAILED = new ImageTransferStatus(
			4, "IMAGE_VERIFICATION_FAILED");
	public static final ImageTransferStatus IMAGE_ACTIVATION_INITIATED = new ImageTransferStatus(
			5, "IMAGE_ACTIVATION_INITIATED");
	public static final ImageTransferStatus IMAGE_ACTIVATION_SUCCESSFUL = new ImageTransferStatus(
			6, "IMAGE_ACTIVATION_SUCCESSFUL");
	public static final ImageTransferStatus IMAGE_ACTIVATION_FAILED = new ImageTransferStatus(
			7, "IMAGE_ACTIVATION_FAILED");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (ImageTransferStatus.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private ImageTransferStatus(int value, String name) {
		this.value = value;
		this.name = name;
		getMappings().put(new Integer(value), this);
	}

	/*
	 * Get integer value for enum.
	 */
	public int getValue() {
		return value;
	}

	/*
	 * Convert integer for enum value.
	 */
	public static ImageTransferStatus forValue(int value) {
		return (ImageTransferStatus) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}
