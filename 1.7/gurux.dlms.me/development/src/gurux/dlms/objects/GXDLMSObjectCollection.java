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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import gurux.dlms.enums.ObjectType;

/**
 * Collection of DLMS objects.
 */
public class GXDLMSObjectCollection implements java.util.List<GXDLMSObject> {
    private List<GXDLMSObject> base = new ArrayList<GXDLMSObject>();
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
        for (GXDLMSObject it : this) {
            if (it.getObjectType() == type) {
                items.add(it);
            }
        }
        return items;
    }

    public final GXDLMSObjectCollection getObjects(final ObjectType[] types) {
        GXDLMSObjectCollection items = new GXDLMSObjectCollection();
        for (GXDLMSObject it : this) {
            for (ObjectType type : types) {
                if (type == it.getObjectType()) {
                    items.add(it);
                    break;
                }
            }
        }
        return items;
    }

    public final GXDLMSObject findByLN(final ObjectType type, final String ln) {
        for (GXDLMSObject it : this) {
            if ((type == ObjectType.ALL || it.getObjectType() == type)
                    && it.getLogicalName().trim().equals(ln)) {
                return it;
            }
        }
        return null;
    }

    public final GXDLMSObject findBySN(final int sn) {
        for (GXDLMSObject it : this) {
            if (it.getShortName() == sn) {
                return it;
            }
        }
        return null;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (GXDLMSObject it : this) {
            if (sb.length() != 1) {
                sb.append(", ");
            }
            sb.append(it.getName().toString());
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public boolean add(GXDLMSObject e) {
        return base.add(e);
    }

    @Override
    public void add(int index, GXDLMSObject element) {
        base.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends GXDLMSObject> c) {
        return base.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends GXDLMSObject> c) {
        return base.addAll(index, c);
    }

    @Override
    public void clear() {
        base.clear();

    }

    @Override
    public boolean contains(Object o) {
        return base.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return base.containsAll(c);
    }

    @Override
    public GXDLMSObject get(int index) {
        return base.get(index);
    }

    @Override
    public int indexOf(Object o) {
        return base.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return base.isEmpty();
    }

    @Override
    public Iterator<GXDLMSObject> iterator() {
        return base.iterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        return base.lastIndexOf(o);
    }

    @Override
    public ListIterator<GXDLMSObject> listIterator() {
        return base.listIterator();
    }

    @Override
    public ListIterator<GXDLMSObject> listIterator(int index) {
        return base.listIterator(index);
    }

    @Override
    public boolean remove(Object o) {
        return base.remove(o);
    }

    @Override
    public GXDLMSObject remove(int index) {
        return base.remove(index);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return base.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return base.retainAll(c);
    }

    @Override
    public GXDLMSObject set(int index, GXDLMSObject element) {
        return base.set(index, element);
    }

    @Override
    public int size() {
        return base.size();
    }

    @Override
    public List<GXDLMSObject> subList(int fromIndex, int toIndex) {
        return base.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return base.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return base.toArray(a);
    }
}