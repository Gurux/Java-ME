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

import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import gurux.dlms.GXByteBuffer;
import gurux.dlms.GXDLMSClient;
import gurux.dlms.GXDLMSException;
import gurux.dlms.GXDLMSServer;
import gurux.dlms.GXDLMSSettings;
import gurux.dlms.GXDateTime;
import gurux.dlms.GXSimpleEntry;
import gurux.dlms.ValueEventArgs;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.internal.GXCommon;
import gurux.dlms.internal.GXDataInfo;
import gurux.dlms.objects.enums.SortMethod;

public class GXDLMSProfileGeneric extends GXDLMSObject implements IGXDLMSBase {
	private Vector buffer = new Vector();
	private Vector captureObjects;
	private int capturePeriod;
	private SortMethod sortMethod;
	private GXDLMSObject sortObject;
	private int sortObjectAttributeIndex;
	private int sortObjectDataIndex;
	private int entriesInUse;
	private int profileEntries;

	/**
	 * Constructor.
	 */
	public GXDLMSProfileGeneric() {
		this(null, (short) 0);
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 */
	public GXDLMSProfileGeneric(final String ln) {
		this(ln, (short) 0);
	}

	/**
	 * Constructor.
	 * 
	 * @param ln
	 *            Logical Name of the object.
	 * @param sn
	 *            Short Name of the object.
	 */
	public GXDLMSProfileGeneric(final String ln, final int sn) {
		super(ObjectType.PROFILE_GENERIC, ln, sn);
		captureObjects = new Vector();
	}

	/**
	 * @return Data of profile generic.
	 */
	public final Object[] getBuffer() {
		Object[] tmp = new Object[buffer.size()];
		buffer.copyInto(tmp);
		return tmp;
	}

	/**
	 * @param value
	 *            Data of profile generic.
	 */
	public final void setBuffer(final Object[][] value) {
		buffer.removeAllElements();
		GXCommon.addAll(buffer, value);
		entriesInUse = buffer.size();
	}

	/**
	 * @param value
	 *            Add new row to Profile Generic data buffer.
	 */
	public final void addRow(final Object[] value) {
		buffer.addElement(value);
		entriesInUse = buffer.size();
	}

	/**
	 * @param value
	 *            Data of profile generic.
	 */
	public final void addBuffer(final Object[][] value) {
		GXCommon.addAll(buffer, value);
		entriesInUse = buffer.size();
	}

	/**
	 * @param value
	 *            Data of profile generic.
	 */
	public final void addBuffer(final Vector value) {
		Enumeration iterator = value.elements();
		while (iterator.hasMoreElements()) {
			buffer.addElement(iterator.nextElement());
		}
		entriesInUse = buffer.size();
	}

	/**
	 * Clear profile generic buffer.
	 */
	public final void clearBuffer() {
		buffer.removeAllElements();
		entriesInUse = 0;
	}

	/*
	 * Add new capture object (column) to the profile generic.
	 */
	public final void addCaptureObject(final GXDLMSObject item,
			final int attributeIndex, final int dataIndex) {
		if (item == null) {
			throw new RuntimeException("Invalid Object");
		}
		if (attributeIndex < 1) {
			throw new RuntimeException("Invalid attribute index");
		}
		if (dataIndex < 0) {
			throw new RuntimeException("Invalid data index");
		}
		GXDLMSCaptureObject co = new GXDLMSCaptureObject(attributeIndex,
				dataIndex);
		captureObjects.addElement(new GXSimpleEntry(item, co));
	}

	/**
	 * @return Captured Objects.
	 */
	public final Vector getCaptureObjects() {
		return captureObjects;
	}

	/**
	 * @return How often values are captured.
	 */
	public final int getCapturePeriod() {
		return capturePeriod;
	}

	/**
	 * @param value
	 *            How often values are captured.
	 */
	public final void setCapturePeriod(final int value) {
		capturePeriod = value;
	}

	/**
	 * @return How columns are sorted.
	 */
	public final SortMethod getSortMethod() {
		return sortMethod;
	}

	/**
	 * @param value
	 *            How columns are sorted.
	 */
	public final void setSortMethod(final SortMethod value) {
		sortMethod = value;
	}

	/**
	 * @return Column that is used for sorting.
	 */
	public final GXDLMSObject getSortObject() {
		return sortObject;
	}

	/**
	 * @param value
	 *            Column that is used for sorting.
	 */
	public final void setSortObject(final GXDLMSObject value) {
		sortObject = value;
	}

	/**
	 * @return Attribute index of sort object.
	 */
	public final int getSortObjectAttributeIndex() {
		return sortObjectAttributeIndex;
	}

	/**
	 * @param value
	 *            Attribute index of sort object.
	 */
	public final void setSortObjectAttributeIndex(final int value) {
		sortObjectAttributeIndex = value;
	}

	/**
	 * @return Data index of sort object.
	 */
	public final int getSortObjectDataIndex() {
		return sortObjectDataIndex;
	}

	/**
	 * @param value
	 *            Data index of sort object.
	 */
	public final void setSortObject(final int value) {
		sortObjectDataIndex = value;
	}

	/**
	 * @return Entries (rows) in Use.
	 */
	public final int getEntriesInUse() {
		return entriesInUse;
	}

	/**
	 * @return Entries (rows) in Use.
	 */
	public final int setEntriesInUse() {
		return entriesInUse;
	}

	/**
	 * @return Maximum Entries (rows) count.
	 */
	public final int getProfileEntries() {
		return profileEntries;
	}

	/**
	 * @param value
	 *            Maximum Entries (rows) count.
	 */
	public final void setProfileEntries(final int value) {
		profileEntries = value;
	}

	public final Object[] getValues() {
		return new Object[] { getLogicalName(), getBuffer(),
				getCaptureObjects(), new Integer(getCapturePeriod()),
				getSortMethod(), getSortObject(),
				new Integer(getEntriesInUse()),
				new Integer(getProfileEntries()) };
	}

	/*
	 * Returns collection of attributes to read. If attribute is static and
	 * already read or device is returned HW error it is not returned.
	 */

	public final int[] getAttributeIndexToRead() {
		Vector attributes = new Vector();
		// LN is static and read only once.
		if (getLogicalName() == null || getLogicalName().compareTo("") == 0) {
			attributes.addElement(new Integer(1));
		}
		// Buffer
		if (!isRead(2)) {
			attributes.addElement(new Integer(2));
		}
		// CaptureObjects
		if (!isRead(3)) {
			attributes.addElement(new Integer(3));
		}
		// CapturePeriod
		if (!isRead(4)) {
			attributes.addElement(new Integer(4));
		}
		// SortMethod
		if (!isRead(5)) {
			attributes.addElement(new Integer(5));
		}
		// SortObject
		if (!isRead(6)) {
			attributes.addElement(new Integer(6));
		}
		// EntriesInUse
		if (!isRead(7)) {
			attributes.addElement(new Integer(7));
		}
		// ProfileEntries
		if (!isRead(8)) {
			attributes.addElement(new Integer(8));
		}
		return GXDLMSObjectHelpers.toIntArray(attributes);
	}

	/*
	 * Returns amount of attributes.
	 */

	public final int getAttributeCount() {
		return 8;
	}

	/*
	 * Returns amount of methods.
	 */

	public final int getMethodCount() {
		return 2;
	}

	/**
	 * @return Returns captured columns.
	 */
	private byte[] getColumns() {
		int cnt = captureObjects.size();
		GXByteBuffer data = new GXByteBuffer();
		data.setUInt8(DataType.ARRAY.getValue());
		// Add count
		GXCommon.setObjectCount(cnt, data);
		Enumeration iterator = captureObjects.elements();
		while (iterator.hasMoreElements()) {
			GXSimpleEntry it = (GXSimpleEntry) iterator.nextElement();
			data.setUInt8(DataType.STRUCTURE.getValue());
			// Count
			data.setUInt8(4); 
			// ClassID
			GXCommon.setData(data, DataType.UINT16, new Integer(
					((GXDLMSObject) it.getKey()).getObjectType().getValue()));
			// LN
			GXCommon.setData(data, DataType.OCTET_STRING,
					((GXDLMSObject) it.getKey()).getLogicalName());
			// Attribute Index
			GXCommon.setData(data, DataType.INT8, new Integer(
					((GXDLMSCaptureObject) it.getValue()).getAttributeIndex()));
			// Data Index
			GXCommon.setData(data, DataType.UINT16, new Integer(
					((GXDLMSCaptureObject) it.getValue()).getDataIndex()));
		}
		return data.array();
	}

	/**
	 * @param table
	 *            Table where rows are get.
	 * @param columns
	 *            Columns to get. Null if not used.
	 * @return Buffer rows as byte array.
	 */
	private byte[] getData(final Vector table, final Vector columns) {
		GXByteBuffer data = new GXByteBuffer();
		data.setUInt8((byte) DataType.ARRAY.getValue());
		GXCommon.setObjectCount(table.size(), data);
		DataType[] types = new DataType[captureObjects.size()];
		int row = -1, col;
		Enumeration iterator = captureObjects.elements();
		while (iterator.hasMoreElements()) {
			GXSimpleEntry it = (GXSimpleEntry) iterator.nextElement();
			types[++row] = ((GXDLMSObject) it.getKey()).getDataType(
					((GXDLMSCaptureObject) it.getValue()).getAttributeIndex());
		}
		DataType tp;
		for (row = 0; row != table.size(); ++row) {
			Object[] items = (Object[]) table.elementAt(row);
			data.setUInt8(DataType.STRUCTURE.getValue());
			if (columns == null || columns.size() == 0) {
				GXCommon.setObjectCount(items.length, data);
			} else {
				GXCommon.setObjectCount(columns.size(), data);
			}
			for (col = 0; col != items.length; ++col) {
				Object value = items[col];
				tp = DataType.NONE;
				if (types.length == items.length) {
					tp = types[col];
				}
				if (columns == null
						|| columns.contains(captureObjects.elementAt(col))) {
					if (tp == DataType.NONE) {
						tp = GXCommon.getValueType(value);
						if (types.length == items.length) {
							types[col] = tp;
						}
					}
					GXCommon.setData(data, tp, value);
				}
			}
		}
		return data.array();

	}

	/**
	 * Get selected columns.
	 * 
	 * @param cols
	 *            selected columns.
	 * @return Selected columns.
	 */
	private Vector getColumns(final Object[] cols) {
		Vector columns = null;
		if (cols != null && cols.length != 0) {
			columns = new Vector();
			for (int pos = 0; pos != cols.length; ++pos) {
				Object[] tmp = (Object[]) cols[pos];
				ObjectType ot = ObjectType.forValue(GXCommon.intValue(tmp[0]));
				String ln = GXDLMSObject.toLogicalName((byte[]) tmp[1]);
				byte attributeIndex = (byte) GXCommon.intValue(tmp[2]);
				int dataIndex = GXCommon.intValue(tmp[3]);
				// Find columns and update only them.
				Enumeration iterator = captureObjects.elements();
				while (iterator.hasMoreElements()) {
					GXSimpleEntry c = (GXSimpleEntry) iterator.nextElement();
					if (((GXDLMSObject) c.getKey()).getObjectType() == ot
							&& ((GXDLMSCaptureObject) c.getValue())
									.getAttributeIndex() == attributeIndex
							&& ((GXDLMSCaptureObject) c.getValue())
									.getDataIndex() == dataIndex
							&& ((GXDLMSObject) c.getKey()).getLogicalName()
									.compareTo(ln) == 0) {
						columns.addElement(c);
						break;
					}
				}
			}
		} else {
			// Return all rows.
			return captureObjects;
		}
		return columns;
	}

	/**
	 * Get selected columns from parameters.
	 * 
	 * @param selector
	 *            Is read by entry or range.
	 * @param parameters
	 *            Received parameters where columns information is found.
	 * @return Selected columns.
	 */
	public final Vector getSelectedColumns(final int selector,
			final Object parameters) {
		if (selector == 0) {
			// Return all rows.
			return captureObjects;
		} else if (selector == 1) {
			return getColumns((Object[]) ((Object[]) parameters)[3]);
		} else if (selector == 2) {
			Object[] arr = (Object[]) parameters;
			int colStart = 1;
			int colCount = 0;
			if (arr.length > 2) {
				colStart = GXCommon.intValue(arr[2]);
			}
			if (arr.length > 3) {
				colCount = GXCommon.intValue(arr[3]);
			} else if (colStart != 1) {
				colCount = captureObjects.size();
			}
			if (colStart != 1 || colCount != 0) {
				GXCommon.subList(captureObjects, colStart - 1,
						colStart + colCount - 1);
			}
			// Return all rows.
			return captureObjects;
		} else {
			throw new IllegalArgumentException("Invalid selector.");
		}
	}

	final byte[] getProfileGenericData(final int selector,
			final Object parameters) {
		Vector columns = null;
		// If all data is read.
		if (selector == 0 || parameters == null) {
			return getData(buffer, columns);
		}
		Object[] arr = (Object[]) parameters;
		columns = getSelectedColumns(selector, arr);
		Vector table = new Vector();
		// Read by range
		if (selector == 1) {
			GXDataInfo info = new GXDataInfo();
			info.setType(DataType.DATETIME);
			java.util.Date start = ((GXDateTime) GXCommon
					.getData(new GXByteBuffer((byte[]) arr[1]), info))
							.getValue();
			info.clear();
			info.setType(DataType.DATETIME);
			java.util.Date end = ((GXDateTime) GXCommon
					.getData(new GXByteBuffer((byte[]) arr[2]), info))
							.getValue();
			Enumeration iterator = buffer.elements();
			while (iterator.hasMoreElements()) {
				Object row = iterator.nextElement();
				java.util.Date tm;
				Object tmp = ((Object[]) row)[0];
				if (tmp instanceof GXDateTime) {
					tm = ((GXDateTime) tmp).getValue();
				} else {
					tm = (java.util.Date) tmp;
				}
				if (tm.getTime() >= start.getTime()
						&& tm.getTime() <= end.getTime()) {
					table.addElement((Object[]) row);
				}
			}
		} else if (selector == 2) {
			// Read by entry.
			int start = GXCommon.intValue(arr[0]);
			if (start == 0) {
				start = 1;
			}
			int count = GXCommon.intValue(arr[1]);
			if (count == 0) {
				count = getBuffer().length;
			}
			if (start + count > getBuffer().length + 1) {
				count = getBuffer().length;
			}
			// Starting index is 1.
			for (int pos = 0; pos < count; ++pos) {
				if (pos + start - 1 == getBuffer().length) {
					break;
				}
				table.addElement((Object[]) getBuffer()[start + pos - 1]);
			}
		} else {
			throw new IllegalArgumentException("Invalid selector.");
		}
		return getData(table, columns);
	}

	public final DataType getDataType(final int index) {
		if (index == 1) {
			return DataType.OCTET_STRING;
		}
		if (index == 2) {
			return DataType.ARRAY;
		}
		if (index == 3) {
			return DataType.ARRAY;
		}
		if (index == 4) {
			return DataType.UINT32;
		}
		if (index == 5) {
			return DataType.ENUM;
		}
		if (index == 6) {
			return DataType.ARRAY;
		}
		if (index == 7) {
			return DataType.UINT32;
		}
		if (index == 8) {
			return DataType.UINT32;
		}
		throw new IllegalArgumentException(
				"getDataType failed. Invalid attribute index.");
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
			return getProfileGenericData(e.getSelector(), e.getParameters());
		}
		if (e.getIndex() == 3) {
			return getColumns();
		}
		if (e.getIndex() == 4) {
			return new Integer(getCapturePeriod());
		}
		if (e.getIndex() == 5) {
			return new Integer(getSortMethod().getValue());
		}
		if (e.getIndex() == 6) {
			GXByteBuffer data = new GXByteBuffer();
			data.setUInt8((byte) DataType.STRUCTURE.getValue());
			data.setUInt8((byte) 4); // Count
			if (sortObject == null) {
				// ClassID
				GXCommon.setData(data, DataType.UINT16, new Integer(0));
				// LN
				GXCommon.setData(data, DataType.OCTET_STRING, new byte[6]);
				// Selected Attribute Index
				GXCommon.setData(data, DataType.INT8, new Integer(0));
				// Selected Data Index
				GXCommon.setData(data, DataType.UINT16, new Integer(0));
			} else {
				// ClassID
				GXCommon.setData(data, DataType.UINT16,
						new Integer(sortObject.getObjectType().getValue()));
				// LN
				GXCommon.setData(data, DataType.OCTET_STRING,
						sortObject.getLogicalName());
				// Attribute Index
				GXCommon.setData(data, DataType.INT8,
						new Integer(sortObjectAttributeIndex));
				// Data Index
				GXCommon.setData(data, DataType.UINT16,
						new Integer(sortObjectDataIndex));
			}
			return data.array();
		}
		if (e.getIndex() == 7) {
			return new Integer(getEntriesInUse());
		}
		if (e.getIndex() == 8) {
			return new Integer(getProfileEntries());
		}
		e.setError(ErrorCode.READ_WRITE_DENIED);
		return null;
	}

	/**
	 * Set value of given attribute.
	 */

	public final void setValue(final GXDLMSSettings settings,
			final ValueEventArgs e) {
		if (e.getIndex() == 1) {
			super.setValue(settings, e);
		} else if (e.getIndex() == 2) {
			setBuffer(e);
		} else if (e.getIndex() == 3) {
			captureObjects.removeAllElements();
			buffer.removeAllElements();
			entriesInUse = buffer.size();
			if (e.getValue() != null) {
				Object[] arr = (Object[]) e.getValue();
				for (int pos = 0; pos != arr.length; ++pos) {
					Object[] tmp = (Object[]) arr[pos];
					if (tmp.length != 4) {
						throw new GXDLMSException("Invalid structure format.");
					}
					ObjectType type = ObjectType
							.forValue(GXCommon.intValue(tmp[0]));
					String ln = GXDLMSObject.toLogicalName((byte[]) tmp[1]);
					GXDLMSObject obj = null;
					if (settings != null && settings.getObjects() != null) {
						obj = settings.getObjects().findByLN(type, ln);
					}
					if (obj == null) {
						obj = gurux.dlms.GXDLMSClient.createObject(type);
						obj.setLogicalName(ln);
					}
					addCaptureObject(obj, GXCommon.intValue(tmp[2]),
							GXCommon.intValue(tmp[3]));
				}
			}
		} else if (e.getIndex() == 4) {
			if (e.getValue() == null) {
				capturePeriod = 0;
			} else {
				capturePeriod = GXCommon.intValue(e.getValue());
			}

		} else if (e.getIndex() == 5) {
			if (e.getValue() == null) {
				sortMethod = SortMethod.FIFO;
			} else {
				sortMethod = SortMethod
						.forValue(GXCommon.intValue(e.getValue()));
			}

		} else if (e.getIndex() == 6) {
			if (e.getValue() == null) {
				sortObject = null;
			} else {
				Object[] tmp = (Object[]) e.getValue();
				if (tmp.length != 4) {
					throw new IllegalArgumentException(
							"Invalid structure format.");
				}
				ObjectType type = ObjectType
						.forValue(GXCommon.intValue(tmp[0]));
				String ln = GXDLMSObject.toLogicalName((byte[]) tmp[1]);
				int attributeIndex = GXCommon.intValue(tmp[2]);
				int dataIndex = GXCommon.intValue(tmp[3]);
				sortObject = settings.getObjects().findByLN(type, ln);
				if (sortObject == null) {
					sortObject = gurux.dlms.GXDLMSClient.createObject(type);
					sortObject.setLogicalName(ln);
				}
				sortObjectAttributeIndex = attributeIndex;
				sortObjectDataIndex = dataIndex;
			}
		} else if (e.getIndex() == 7) {
			if (e.getValue() == null) {
				entriesInUse = 0;
			} else {
				entriesInUse = GXCommon.intValue(e.getValue());
			}
		} else if (e.getIndex() == 8) {
			if (e.getValue() == null) {
				profileEntries = 0;
			} else {
				profileEntries = GXCommon.intValue(e.getValue());
			}
		} else {
			e.setError(ErrorCode.READ_WRITE_DENIED);
		}
	}

	/**
	 * Update buffer.
	 * 
	 * @param value
	 *            Received data.
	 */
	private void setBuffer(final ValueEventArgs e) {

		Vector cols = null;
		if (e.getParameters() instanceof Vector) {
			cols = (Vector) e.getParameters();
		}

		if (cols == null) {
			cols = captureObjects;
		}
		if (cols == null || cols.size() == 0) {
			throw new RuntimeException("Read capture objects first.");
		}	
		if (e.getValue() != null) {
			java.util.Calendar lastDate = java.util.Calendar.getInstance();
			DataType[] types = new DataType[cols.size()];
			int colIndex = -1;
			Enumeration iterator = cols.elements();
			while (iterator.hasMoreElements()) {
				GXSimpleEntry it = (GXSimpleEntry) iterator.nextElement();
				types[++colIndex] = ((GXDLMSObject) it.getKey())
						.getUIDataType(((GXDLMSCaptureObject) it.getValue())
								.getAttributeIndex());
			}
			Object[] arr = (Object[]) e.getValue();
			for (int pos = 0; pos != arr.length; ++pos) {
				Object[] row = (Object[]) arr[pos];
				if (row.length != cols.size()) {
					throw new RuntimeException(
							"Number of columns do not match.");
				}
				for (colIndex = 0; colIndex < row.length; ++colIndex) {
					Object data = row[colIndex];
					DataType type = types[colIndex];
					if (type != DataType.NONE && type != null
							&& data instanceof byte[]) {
						data = GXDLMSClient.changeType((byte[]) data, type);
						if (data instanceof GXDateTime) {
							GXDateTime dt = (GXDateTime) data;
							lastDate.setTime(dt.getValue());
						}
						row[colIndex] = data;
					} else if (type == DataType.DATETIME && data == null
							&& capturePeriod != 0) {
						if (lastDate.getTime().getTime() == 0
								&& !buffer.isEmpty()) {
							lastDate.setTime(((GXDateTime) ((Object[]) buffer
									.elementAt(buffer.size() - 1))[colIndex])
											.getValue());
						}
						if (lastDate.getTime().getTime() != 0) {
							lastDate.setTime(
									new Date(lastDate.getTime().getTime()
											+ capturePeriod * 1000));
							row[colIndex] = new GXDateTime(lastDate.getTime());
						}
					}
					GXSimpleEntry item = (GXSimpleEntry) cols
							.elementAt(colIndex);
					if (item.getKey() instanceof GXDLMSRegister
							&& ((GXDLMSCaptureObject) item.getValue())
									.getAttributeIndex() == 2) {
						double scaler = ((GXDLMSRegister) item.getKey())
								.getScaler();
						if (scaler != 1 && data != null) {
							try {
								data = new Double(GXCommon.intValue(data) * scaler);
								row[colIndex] = data;
							} catch (Exception ex) {
								System.out.println("Scalar failed for: "
										+ ((GXDLMSObject) item.getKey())
												.getLogicalName());
								// Skip error
							}
						}
					}else if (item.getKey() instanceof GXDLMSDemandRegister
                            && (((GXDLMSCaptureObject) item.getValue()).getAttributeIndex() == 2 || 
                            ((GXDLMSCaptureObject) item.getValue()).getAttributeIndex() == 3)) {
		                double scaler = ((GXDLMSDemandRegister) item.getKey()).getScaler();
		                if (scaler != 1 && data != null) {
		                    try {
		                    	data = new Double(GXCommon.intValue(data) * scaler);
		                        row[colIndex] = data;
		                    } catch (Exception ex) {
		                    	System.out.println("Scalar failed for: "
										+ ((GXDLMSObject) item.getKey())
												.getLogicalName());
		                        // Skip error
		                    }
		                }
					}	
				}
				buffer.addElement((Object[]) row);
			}
			entriesInUse = buffer.size();
		}
	}

	/**
	 * Clears the buffer.
	 */
	public final void reset() {
		synchronized (this) {
			buffer.removeAllElements();
			entriesInUse = 0;
		}
	}

	/*
	 * Copies the values of the objects to capture into the buffer by reading
	 * capture objects.
	 */
	public final void capture(final GXDLMSServer server) {
		synchronized (this) {
			Object[] values = new Object[captureObjects.size()];
			int pos = -1;
			Enumeration iterator = captureObjects.elements();
			while (iterator.hasMoreElements()) {
				GXSimpleEntry obj = (GXSimpleEntry) iterator.nextElement();
				ValueEventArgs e = new ValueEventArgs(server.getSettings(),
						((GXDLMSObject) obj.getKey()),
						((GXDLMSCaptureObject) obj.getValue())
								.getAttributeIndex(),
						0, null);
				server.read(new ValueEventArgs[] { e });
				if (e.getHandled()) {
					values[++pos] = e.getValue();
				} else {
					values[++pos] = ((GXDLMSObject) obj.getKey()).getValue(null,
							e);
				}
			}
			synchronized (this) {
				// Remove first items if buffer is full.
				if (getProfileEntries() == getBuffer().length) {
					buffer.removeElementAt(0);
				}
				buffer.addElement(values);
				entriesInUse = buffer.size();
			}
		}
	}
}