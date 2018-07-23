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
import java.util.Vector;

import gurux.dlms.enums.ObjectType;

/**
 * Collection of DLMS objects.
 */
public class GXDLMSObjectCollection extends Vector {
	private Object parent;

	/**
	 * Constructor.
	 */
	public GXDLMSObjectCollection() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param forParent
	 *            Parent object.
	 */
	public GXDLMSObjectCollection(final Object forParent) {
		parent = forParent;
	}

	public final Object getParent() {
		return parent;
	}

	final void setParent(final Object value) {
		parent = value;
	}

	public final GXDLMSObjectCollection getObjects(final ObjectType type) {
		GXDLMSObjectCollection items = new GXDLMSObjectCollection();
		Enumeration iterator = this.elements();
		while (iterator.hasMoreElements()) {
			GXDLMSObject it = (GXDLMSObject) iterator.nextElement();

			if (it.getObjectType() == type) {
				items.addElement(it);
			}
		}
		return items;
	}

	public final GXDLMSObjectCollection getObjects(final ObjectType[] types) {
		GXDLMSObjectCollection items = new GXDLMSObjectCollection();
		Enumeration iterator = this.elements();
		while (iterator.hasMoreElements()) {
			GXDLMSObject it = (GXDLMSObject) iterator.nextElement();
			for (int pos = 0; pos != types.length; ++pos) {
				ObjectType type = types[pos];
				if (type == it.getObjectType()) {
					items.addElement(it);
					break;
				}
			}
		}
		return items;
	}

	public final GXDLMSObject findByLN(final ObjectType type, final String ln) {
		Enumeration iterator = this.elements();
		while (iterator.hasMoreElements()) {
			GXDLMSObject it = (GXDLMSObject) iterator.nextElement();
			if ((type == ObjectType.ALL || it.getObjectType() == type)
					&& it.getLogicalName().trim().equals(ln)) {
				return it;
			}
		}
		return null;
	}

	public final GXDLMSObject findBySN(final int sn) {
		Enumeration iterator = this.elements();
		while (iterator.hasMoreElements()) {
			GXDLMSObject it = (GXDLMSObject) iterator.nextElement();
			if (it.getShortName() == sn) {
				return it;
			}
		}
		return null;
	}

	public final String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append('[');
		Enumeration iterator = this.elements();
		while (iterator.hasMoreElements()) {
			GXDLMSObject it = (GXDLMSObject) iterator.nextElement();
			if (sb.length() != 1) {
				sb.append(", ");
			}
			sb.append(it.getName().toString());
		}
		sb.append(']');
		return sb.toString();
	}
}