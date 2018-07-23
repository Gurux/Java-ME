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

package gurux.dlms.internal;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import gurux.dlms.GXByteBuffer;
import gurux.dlms.GXDate;
import gurux.dlms.GXDateTime;
import gurux.dlms.GXTime;
import gurux.dlms.enums.ClockStatus;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.DateTimeSkips;

/*
 * <b> This class is for internal use only and is subject to changes or removal
 * in future versions of the API. Don't use it. </b>
 */
public final class GXCommon {
	private static String zeroes = "00000000000000000000000000000000";
	private static char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/*
	 * Constructor.
	 */
	private GXCommon() {

	}

	/*
	 * HDLC frame start and end character.
	 */
	public static final byte HDLC_FRAME_START_END = 0x7E;
	public static final byte[] LOGICAL_NAME_OBJECT_ID = { 0x60, (byte) 0x85,
			0x74, 0x05, 0x08, 0x01, 0x01 };
	public static final byte[] SHORT_NAME_OBJECT_ID = { 0x60, (byte) 0x85, 0x74,
			0x05, 0x08, 0x01, 0x02 };
	public static final byte[] LOGICAL_NAME_OBJECT_ID_WITH_CIPHERING = { 0x60,
			(byte) 0x85, 0x74, 0x05, 0x08, 0x01, 0x03 };
	public static final byte[] SHORT_NAME_OBJECT_ID_WITH_CIPHERING = { 0x60,
			(byte) 0x85, 0x74, 0x05, 0x08, 0x01, 0x04 };

	public static final byte[] LLC_SEND_BYTES = { (byte) 0xE6, (byte) 0xE6,
			0x00 };
	public static final byte[] LLC_REPLY_BYTES = { (byte) 0xE6, (byte) 0xE7,
			0x00 };

	/*
	 * Reserved for internal use.
	 * 
	 * @param value bit value.
	 * 
	 * @param bitMask Bit mask.
	 * 
	 * @param val Final OR mask.
	 */
	public static byte setBits(final byte value, final int bitMask,
			final boolean val) {
		byte mask = (byte) (0xFF ^ bitMask);
		byte tmp = (byte) (value & mask);
		if (val) {
			tmp |= bitMask;
		}
		return tmp;
	}

	/*
	 * Convert array to list.Mikko
	 * 
	 * @param value Converted array.
	 * 
	 * @return Converted list.
	 */
	// public static List<Object> asList(final Object[] value) {
	// List<Object> list = new ArrayList<Object>(value.length);
	// for (Object it : value) {
	// list.addElement(it);
	// }
	// return list;
	// }

	/*
	 * Add all items to the list.
	 * 
	 * @param target
	 * 
	 * @param list
	 */
	public static void addAll(final Vector target, final Object[] list) {
		for (int pos = 0; pos != list.length; ++pos) {
			target.addElement(list[pos]);
		}
	}

	public static Vector subList(final Vector target, final int start,
			final int end) {
		Vector list = new Vector();
		for (int pos = start; pos != end; ++pos) {
			list.addElement(target.elementAt(pos));
		}
		return list;
	}

	/*
	 * Add all items to the list.
	 * 
	 * @param target
	 * 
	 * @param list
	 */
	public static void addAll(final Vector target, final Vector source) {
		for (int pos = 0; pos != source.size(); ++pos) {
			target.addElement(source.elementAt(pos));
		}
	}

	/*
	 * Convert string to byte array.
	 * 
	 * @param value String value.
	 * 
	 * @return String as bytes.
	 */
	public static byte[] getBytes(final String value) {
		if (value == null) {
			return new byte[0];
		}
		return value.getBytes();

	}

	/*
	 * Convert char hex value to byte value.
	 * 
	 * @param c Character to convert hex.
	 * 
	 * @return Byte value of hex char value.
	 */
	private static byte getValue(final byte c) {
		byte value = -1;
		// If number
		if (c > 0x2F && c < 0x3A) {
			value = (byte) (c - '0');
		} else if (c > 0x40 && c < 'G') {
			// If upper case.
			value = (byte) (c - 'A' + 10);
		} else if (c > 0x60 && c < 'g') {
			// If lower case.
			value = (byte) (c - 'a' + 10);
		}
		return value;
	}

	private static boolean isHex(final byte c) {
		return getValue(c) != -1;
	}

	/**
	 * Convert string to byte array.
	 * 
	 * @param value
	 *            Hex string.
	 * @return byte array.
	 */
	public static byte[] hexToBytes(final String value) {
		if (value == null || value.length() == 0) {
			return new byte[0];
		}
		int len = value.length() / 2;
		if (value.length() % 2 != 0) {
			++len;
		}

		byte[] buffer = new byte[len];
		int lastValue = -1;
		int index = 0;
		byte[] tmp = value.getBytes();
		for (int pos = 0; pos != tmp.length; ++pos) {
			byte ch = tmp[pos];
			if (isHex(ch)) {
				if (lastValue == -1) {
					lastValue = getValue(ch);
				} else if (lastValue != -1) {
					buffer[index] = (byte) (lastValue << 4 | getValue(ch));
					lastValue = -1;
					++index;
				}
			} else if (lastValue != -1 && ch == ' ') {
				buffer[index] = getValue(ch);
				lastValue = -1;
				++index;
			} else {
				lastValue = -1;
			}
		}
		if (lastValue != -1) {
			buffer[index] = (byte) lastValue;
			++index;
		}
		// If there are no spaces in the hex string.
		if (buffer.length == index) {
			return buffer;
		}
		System.arraycopy(buffer, 0, tmp, 0, index);
		return tmp;
	}

	/*
	 * Convert byte array to hex string.
	 */
	public static String toHex(final byte[] bytes) {
		return toHex(bytes, true);
	}

	/*
	 * Convert byte array to hex string.
	 */
	public static String toHex(final byte[] bytes, final boolean addSpace) {
		if (bytes == null) {
			return "";
		}
		return toHex(bytes, addSpace, 0, bytes.length);
	}

	/*
	 * Convert byte array to hex string.
	 */
	public static String toHex(final byte[] bytes, final boolean addSpace,
			final int index, final int count) {
		if (bytes == null || bytes.length == 0 || count == 0) {
			return "";
		}
		char[] str = new char[count * 3];
		int tmp;
		int len = 0;
		for (int pos = 0; pos != count; ++pos) {
			tmp = bytes[index + pos] & 0xFF;
			str[len] = hexArray[tmp >>> 4];
			++len;
			str[len] = hexArray[tmp & 0x0F];
			++len;
			if (addSpace) {
				str[len] = ' ';
				++len;
			}
		}
		if (addSpace) {
			--len;
		}
		return new String(str, 0, len);
	}

	/*
	 * Reserved for internal use.
	 * 
	 * @param value Byte value.
	 * 
	 * @param bitMask Bit mask.
	 * 
	 * @return Get bits.
	 */
	public static boolean getBits(final byte value, final int bitMask) {
		return (value & bitMask) != 0;
	}

	public static byte getSize(final Object value) {
		if (value instanceof Byte) {
			return 1;
		}
		if (value instanceof Short) {
			return 2;
		}
		if (value instanceof Integer) {
			return 4;
		}
		if (value instanceof Long) {
			return 8;
		}
		throw new RuntimeException("Invalid object type.");
	}

	public static int intValue(Object value) {
		if (value instanceof Byte) {
			return ((Byte) value).byteValue() & 0xFF;
		}
		if (value instanceof Short) {
			return ((Short) value).shortValue() & 0xFFFF;
		}
		if (value instanceof Long) {
			return (int) ((Long) value).longValue();
		}
		if (value instanceof Double) {
			return (int) ((Double) value).doubleValue();
		}
		return ((Integer) value).intValue();
	}

	/*
	 * Get object count. If first byte is 0x80 or higger it will tell bytes
	 * count.
	 * 
	 * @param data received data.
	 * 
	 * @return Object count.
	 */
	public static int getObjectCount(final GXByteBuffer data) {
		int cnt = data.getUInt8();
		if (cnt > 0x80) {
			if (cnt == 0x81) {
				return data.getUInt8();
			} else if (cnt == 0x82) {
				return data.getUInt16();
			} else if (cnt == 0x84) {
				return (int) data.getUInt32();
			} else {
				throw new IllegalArgumentException("Invalid count.");
			}
		}
		return cnt;
	}

	/**
	 * Return how many bytes object count takes.
	 * 
	 * @param count
	 *            Value
	 * @return Value size in bytes.
	 */
	public static int getObjectCountSizeInBytes(final int count) {
		if (count < 0x80) {
			return 1;
		} else if (count < 0x100) {
			return 2;
		} else if (count < 0x10000) {
			return 3;
		} else {
			return 5;
		}
	}

	/**
	 * Add string to byte buffer.
	 * 
	 * @param value
	 *            String to add.
	 * @param bb
	 *            Byte buffer where string is added.
	 */
	public static void addString(final String value, final GXByteBuffer bb) {
		bb.setUInt8((byte) DataType.OCTET_STRING.getValue());
		if (value == null) {
			setObjectCount(0, bb);
		} else {
			setObjectCount(value.length(), bb);
			bb.set(value.getBytes());
		}
	}

	/*
	 * Set item count.
	 * 
	 * @param count
	 * 
	 * @param buff
	 */
	public static void setObjectCount(final int count,
			final GXByteBuffer buff) {
		if (count < 0x80) {
			buff.setUInt8(count);
		} else if (count < 0x100) {
			buff.setUInt8(0x81);
			buff.setUInt8(count);
		} else if (count < 0x10000) {
			buff.setUInt8(0x82);
			buff.setUInt16(count);
		} else {
			buff.setUInt8(0x84);
			buff.setUInt32(count);
		}
	}

	/*
	 * Compares, whether two given arrays are similar.
	 * 
	 * @param arr1 First array to compare.
	 * 
	 * @param index Starting index of table, for first array.
	 * 
	 * @param arr2 Second array to compare.
	 * 
	 * @return True, if arrays are similar. False, if the arrays differ.
	 */
	public static boolean compare(final byte[] arr1, final byte[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		int pos;
		for (pos = 0; pos != arr2.length; ++pos) {
			if (arr1[pos] != arr2[pos]) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Reserved for internal use.
	 */
	public static void toBitString(final StringBuffer sb, final byte value,
			final int count2) {
		int count = count2;
		if (count > 8) {
			count = 8;
		}
		char[] data = new char[count];
		for (int pos = 0; pos != count; ++pos) {
			if ((value & (1 << pos)) != 0) {
				data[count - pos - 1] = '1';
			} else {
				data[count - pos - 1] = '0';
			}
		}
		sb.append(data);
	}

	/**
	 * Get data from DLMS frame.
	 * 
	 * @param data
	 *            received data.
	 * @param info
	 *            Data info.
	 * @return Received data.
	 */
	public static Object getData(final GXByteBuffer data,
			final GXDataInfo info) {
		Object value = null;
		int startIndex = data.position();
		if (data.position() == data.size()) {
			info.setCompleate(false);
			return null;
		}
		info.setCompleate(true);
		boolean knownType = info.getType() != DataType.NONE;
		// Get data type if it is unknown.
		if (!knownType) {
			info.setType(DataType.forValue(data.getUInt8()));
		}
		if (info.getType() == DataType.NONE) {
			if (info.getXml() != null) {
				info.getXml().appendLine("<"
						+ info.getXml().getDataType(info.getType()) + " />");
			}
			return value;
		}
		if (data.position() == data.size()) {
			info.setCompleate(false);
			return null;
		}
		switch (info.getType().getValue()) {
		case 1:// DataType.ARRAY
		case 2:// DataType.STRUCTURE
			value = getArray(data, info, startIndex);
			break;
		case 3:// DataType.BOOLEAN
			value = getBoolean(data, info);
			break;
		case 4:// DataType.BITSTRING
			value = getBitString(data, info);
			break;
		case 5:// DataType.INT3
			value = getInt32(data, info);
			break;
		case 6:// DataType.UINT32
			value = getUInt32(data, info);
			break;
		case 10:// DataType.STRING
			value = getString(data, info, knownType);
			break;
		case 12:// DataType.STRING_UTF8
			value = getUtfString(data, info, knownType);
			break;
		case 9:// DataType.OCTET_STRING
			value = getOctetString(data, info, knownType);
			break;
		case 13:// DataType.BCD
			value = getBcd(data, info, knownType);
			break;
		case 15:// DataType.INT8
			value = getInt8(data, info);
			break;
		case 0x10:// DataType.INT16
			value = getInt16(data, info);
			break;
		case 0x11:// DataType.UINT8
			value = getUInt8(data, info);
			break;
		case 0x12:// DataType.UINT16
			value = getUInt16(data, info);
			break;
		case 0x13:// DataType.COMPACT_ARRAY:
			throw new RuntimeException("Invalid data type.");
		case 20:// DataType.INT64
			value = getInt64(data, info);
			break;
		case 0x15:// DataType.UINT64
			value = getUInt64(data, info);
			break;
		case 0x16:// DataType.ENUM
			value = getEnum(data, info);
			break;
		case 0x17:// DataType.FLOAT32
			value = getFloat(data, info);
			break;
		case 0x18:// DataType.FLOAT64
			value = getDouble(data, info);
			break;
		case 0x19:// DataType.DATETIME
			value = getDateTime(data, info);
			break;
		case 0x1A:// DataType.DATE
			value = getDate(data, info);
			break;
		case 0x1b:// DataType.TIME:
			value = getTime(data, info);
			break;
		default:
			throw new RuntimeException("Invalid data type.");
		}
		return value;
	}

	/*
	 * Convert value to hex string.
	 * 
	 * @param value value to convert.
	 * 
	 * @param desimals Amount of decimals.
	 * 
	 * @return
	 */
	public static String integerToHex(final Object value, final int desimals) {
		String str = Long.toString(GXCommon.intValue(value), 16).toUpperCase();
		if (desimals == 0 || str.length() == zeroes.length()) {
			return str;
		}
		return zeroes.substring(0, desimals - str.length()) + str;
	}
	
	/*
     * Convert value to hex string.
     * @param value value to convert.
     * @param desimals Amount of decimals.
     * @return
     */
    public static String integerString(int value, final int desimals) {
        String str = Long.toString(value);
        if (desimals == 0 || str.length() == zeroes.length()) {
            return str;
        }
        return zeroes.substring(0, desimals - str.length()) + str;
    }


	/**
	 * Get array from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @param index
	 *            starting index.
	 * @return Object array.
	 */
	private static Object getArray(final GXByteBuffer buff,
			final GXDataInfo info, final int index) {
		Object value;
		if (info.getCount() == 0) {
			info.setCount(GXCommon.getObjectCount(buff));
		}
		if (info.getXml() != null) {
			info.getXml().appendStartTag(
					info.getXml().getDataType(info.getType()), "Qty",
					info.getXml().integerToHex(info.getCount(), 2));
		}
		int size = buff.size() - buff.position();
		if (info.getCount() != 0 && size < 1) {
			info.setCompleate(false);
			return null;
		}
		int startIndex = index;
		Vector arr = new Vector(info.getCount() - info.getIndex());
		// Position where last row was found. Cache uses this info.
		int pos = info.getIndex();
		for (; pos != info.getCount(); ++pos) {
			GXDataInfo info2 = new GXDataInfo();
			info2.setXml(info.getXml());
			Object tmp = getData(buff, info2);
			if (!info2.isComplete()) {
				buff.position(startIndex);
				info.setCompleate(false);
				break;
			} else {
				if (info2.getCount() == info2.getIndex()) {
					startIndex = buff.position();
					arr.addElement(tmp);
				}
			}
		}
		if (info.getXml() != null) {
			info.getXml()
					.appendEndTag(info.getXml().getDataType(info.getType()));
		}
		info.setIndex(pos);
		Object[] tmp2 = new Object[arr.size()];
		arr.copyInto(tmp2);
		value = tmp2;
		return value;
	}

	/**
	 * Get time from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return Parsed time.
	 */
	private static Object getTime(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		if (buff.size() - buff.position() < 4) {
			// If there is not enough data available.
			info.setCompleate(false);
			return null;
		}
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null,
					GXCommon.toHex(buff.getData(), false, buff.position(), 4));
		}
		// Get time.
		int hour = buff.getUInt8();
		int minute = buff.getUInt8();
		int second = buff.getUInt8();
		int ms = buff.getUInt8();
		if (ms != 0xFF) {
			ms *= 10;
		}
		GXTime dt = new GXTime(hour, minute, second, ms);
		value = dt;
		return value;
	}

	/**
	 * Get date from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return Parsed date.
	 */
	private static Object getDate(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		if (buff.size() - buff.position() < 5) {
			// If there is not enough data available.
			info.setCompleate(false);
			return null;
		}
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null,
					GXCommon.toHex(buff.getData(), false, buff.position(), 5));
		}
		// Get year.
		int year = buff.getUInt16();
		// Get month
		int month = buff.getUInt8();
		// Get day
		int day = buff.getUInt8();
		GXDate dt = new GXDate(year, month, day);
		value = dt;
		// Skip week day
		if (buff.getUInt8() == 0xFF) {
			dt.getSkip().addElement(DateTimeSkips.DAY_OF_WEEK);
		}
		return value;
	}

	/**
	 * Get date and time from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return Parsed date and time.
	 */
	private static Object getDateTime(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		Vector skip = new Vector();

		// If there is not enough data available.
		if (buff.size() - buff.position() < 12) {
			info.setCompleate(false);
			return null;
		}
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null,
					GXCommon.toHex(buff.getData(), false, buff.position(), 12));
		}
		// Get year.
		int year = buff.getUInt16();
		// Get month
		int month = buff.getUInt8();
		// Get day
		int day = buff.getUInt8();
		// Skip week day
		if (buff.getUInt8() == 0xFF) {
			skip.addElement(DateTimeSkips.DAY_OF_WEEK);
		}
		// Get time.
		int hour = buff.getUInt8();
		int minute = buff.getUInt8();
		int second = buff.getUInt8();
		int ms = buff.getUInt8() & 0xFF;
		if (ms != 0xFF) {
			ms *= 10;
		} else {
			ms = -1;
		}
		int deviation = buff.getInt16();
		int status = buff.getUInt8();
		GXDateTime dt = new GXDateTime();
		dt.setStatus(ClockStatus.forValue(status));
		if (year < 1 || year == 0xFFFF) {
			skip.addElement(DateTimeSkips.YEAR);
			java.util.Calendar tm = java.util.Calendar.getInstance();
			year = tm.get(Calendar.YEAR);
		}
		dt.setDaylightSavingsBegin(month == 0xFE);
		dt.setDaylightSavingsEnd(month == 0xFD);
		if (month < 1 || month > 12) {
			skip.addElement(DateTimeSkips.MONTH);
			month = 0;
		} else {
			month -= 1;
		}
		if (day == -1 || day == 0 || day > 31) {
			skip.addElement(DateTimeSkips.DAY);
			day = 1;
		} else if (day < 0) {
			Calendar cal = Calendar.getInstance();
			// TODO: day = cal.getActualMaximum(Calendar.DATE) + day + 3;
		}
		if (hour < 0 || hour > 24) {
			skip.addElement(DateTimeSkips.HOUR);
			hour = 0;
		}
		if (minute < 0 || minute > 60) {
			skip.addElement(DateTimeSkips.MINUTE);
			minute = 0;
		}
		if (second < 0 || second > 60) {
			skip.addElement(DateTimeSkips.SECOND);
			second = 0;
		}
		// If ms is Zero it's skipped.
		if (ms < 0 || ms > 1000) {
			skip.addElement(DateTimeSkips.MILLISECOND);
			ms = 0;
		}
		java.util.Calendar tm = java.util.Calendar.getInstance();
		tm.setTime(new Date(0));
		tm.set(Calendar.YEAR, year);
		tm.set(Calendar.MONTH, month);
		tm.set(Calendar.DAY_OF_MONTH, day);
		tm.set(Calendar.HOUR_OF_DAY, hour);
		tm.set(Calendar.MINUTE, minute);
		tm.set(Calendar.SECOND, second);
		if (ms != 0) {
			tm.set(Calendar.MILLISECOND, ms);
		}
		// If summer time.
		if ((status & ClockStatus.DAYLIGHT_SAVE_ACTIVE.getValue()) != 0) {
			tm.setTime(new Date(tm.getTime().getTime() + GXDateTime.HOUR));
		}
		dt.setValue(tm.getTime(), deviation);
		dt.setSkip(skip);
		value = dt;
		return value;
	}

	/**
	 * Get double value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return Parsed double value.
	 */
	private static Object getDouble(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		// If there is not enough data available.
		if (buff.size() - buff.position() < 8) {
			info.setCompleate(false);
			return null;
		}
		value = new Double(buff.getDouble());
		if (info.getXml() != null) {
			GXByteBuffer tmp = new GXByteBuffer();
			setData(tmp, DataType.FLOAT64, value);
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null,
					GXCommon.toHex(tmp.getData(), false, 1, tmp.size() - 1));
		}
		return value;
	}

	/**
	 * Get float value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return Parsed float value.
	 */
	private static Object getFloat(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		// If there is not enough data available.
		if (buff.size() - buff.position() < 4) {
			info.setCompleate(false);
			return null;
		}
		value = new Float(buff.getFloat());
		if (info.getXml() != null) {
			GXByteBuffer tmp = new GXByteBuffer();
			setData(tmp, DataType.FLOAT32, value);
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null,
					GXCommon.toHex(tmp.getData(), false, 1, tmp.size() - 1));
		}
		return value;
	}

	/**
	 * Get enumeration value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed enumeration value.
	 */
	private static Object getEnum(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		// If there is not enough data available.
		if (buff.size() - buff.position() < 1) {
			info.setCompleate(false);
			return null;
		}
		value = new Short(buff.getUInt8());
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, info.getXml().integerToHex(value, 2));
		}
		return value;
	}

	/**
	 * Get UInt64 value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed UInt64 value.
	 */
	private static Object getUInt64(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		// If there is not enough data available.
		if (buff.size() - buff.position() < 8) {
			info.setCompleate(false);
			return null;
		}
		value = new Long(buff.getUInt64());
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, info.getXml().integerToHex(value, 16));
		}
		return value;
	}

	/**
	 * Get Int64 value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed Int64 value.
	 */
	private static Object getInt64(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		// If there is not enough data available.
		if (buff.size() - buff.position() < 8) {
			info.setCompleate(false);
			return null;
		}
		value = new Long(buff.getInt64());
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, info.getXml().integerToHex(value, 16));
		}
		return value;
	}

	/**
	 * Get UInt16 value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed UInt16 value.
	 */
	private static Object getUInt16(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		// If there is not enough data available.
		if (buff.size() - buff.position() < 2) {
			info.setCompleate(false);
			return null;
		}
		value = new Integer(buff.getUInt16());
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, info.getXml().integerToHex(value, 4));

		}
		return value;
	}

	/**
	 * Get UInt8 value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed UInt8 value.
	 */
	private static Object getUInt8(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		// If there is not enough data available.
		if (buff.size() - buff.position() < 1) {
			info.setCompleate(false);
			return null;
		}
		value = new Integer(buff.getUInt8() & 0xFF);
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, info.getXml().integerToHex(value, 2));

		}
		return value;
	}

	/**
	 * Get Int16 value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed Int16 value.
	 */
	private static Object getInt16(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		// If there is not enough data available.
		if (buff.size() - buff.position() < 2) {
			info.setCompleate(false);
			return null;
		}
		value = new Short(buff.getInt16());
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, info.getXml().integerToHex(value, 4));
		}
		return value;
	}

	/**
	 * Get Int8 value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed Int8 value.
	 */
	private static Object getInt8(final GXByteBuffer buff,
			final GXDataInfo info) {
		Object value;
		// If there is not enough data available.
		if (buff.size() - buff.position() < 1) {
			info.setCompleate(false);
			return null;
		}
		value = new Byte(buff.getInt8());
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, info.getXml().integerToHex(value, 2));
		}
		return value;
	}

	/**
	 * Get BCD value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed BCD value.
	 */
	private static Object getBcd(final GXByteBuffer buff, final GXDataInfo info,
			final boolean knownType) {
		// If there is not enough data available.
		if (buff.size() - buff.position() < 1) {
			info.setCompleate(false);
			return null;
		}
		short value = buff.getUInt8();
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, info.getXml().integerToHex(value, 2));
		}
		return new Short(value);
	}

	/**
	 * Get UTF string value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed UTF string value.
	 */
	private static Object getUtfString(final GXByteBuffer buff,
			final GXDataInfo info, final boolean knownType) {
		Object value;
		int len;
		if (knownType) {
			len = buff.size();
		} else {
			len = GXCommon.getObjectCount(buff);
			// If there is not enough data available.
			if (buff.size() - buff.position() < len) {
				info.setCompleate(false);
				return null;
			}
		}
		if (len > 0) {
			value = buff.getString(buff.position(), len, "UTF-8");
		} else {
			value = "";
		}
		if (info.getXml() != null) {
			if (info.getXml().getShowStringAsHex()) {
				info.getXml().appendLine(
						info.getXml().getDataType(info.getType()), null,
						GXCommon.toHex(buff.getData(), false,
								buff.position() - len, len));
			} else {
				info.getXml().appendLine(
						info.getXml().getDataType(info.getType()), null,
						value.toString());
			}
		}
		return value;
	}

	/**
	 * Get octet string value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed octet string value.
	 */
	private static Object getOctetString(final GXByteBuffer buff,
			final GXDataInfo info, final boolean knownType) {
		Object value;
		int len;
		if (knownType) {
			len = buff.size();
		} else {
			len = GXCommon.getObjectCount(buff);
			// If there is not enough data available.
			if (buff.size() - buff.position() < len) {
				info.setCompleate(false);
				return null;
			}
		}
		byte[] tmp = new byte[len];
		buff.get(tmp);
		value = tmp;
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, GXCommon.toHex(tmp, false));
		}
		return value;
	}

	/**
	 * Get string value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed string value.
	 */
	private static Object getString(final GXByteBuffer buff,
			final GXDataInfo info, final boolean knownType) {
		Object value;
		int len;
		if (knownType) {
			len = buff.size();
		} else {
			len = GXCommon.getObjectCount(buff);
			// If there is not enough data available.
			if (buff.size() - buff.position() < len) {
				info.setCompleate(false);
				return null;
			}
		}
		if (len > 0) {
			value = buff.getString(len);
		} else {
			value = "";
		}
		if (info.getXml() != null) {
			if (info.getXml().getShowStringAsHex()) {
				info.getXml().appendLine(
						info.getXml().getDataType(info.getType()), null,
						GXCommon.toHex(buff.getData(), false,
								buff.position() - len, len));
			} else {
				info.getXml().appendLine(
						info.getXml().getDataType(info.getType()), null,
						value.toString());
			}
		}
		return value;
	}

	/**
	 * Get UInt32 value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed UInt32 value.
	 */
	private static Object getUInt32(final GXByteBuffer buff,
			final GXDataInfo info) {
		// If there is not enough data available.
		if (buff.size() - buff.position() < 4) {
			info.setCompleate(false);
			return null;
		}
		Long value = new Long(buff.getUInt32());
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, info.getXml().integerToHex(value, 8));
		}
		return value;
	}

	/**
	 * Get Int32 value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed Int32 value.
	 */
	private static Object getInt32(final GXByteBuffer buff,
			final GXDataInfo info) {
		// If there is not enough data available.
		if (buff.size() - buff.position() < 4) {
			info.setCompleate(false);
			return null;
		}
		Integer value = new Integer(buff.getInt32());
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, info.getXml().integerToHex(value, 8));
		}
		return value;
	}

	/**
	 * Get bit string value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed bit string value.
	 */
	private static String getBitString(final GXByteBuffer buff,
			final GXDataInfo info) {
		int cnt = getObjectCount(buff);
		double t = cnt;
		t /= 8;
		if (cnt % 8 != 0) {
			++t;
		}
		int byteCnt = (int) Math.floor(t);
		// If there is not enough data available.
		if (buff.size() - buff.position() < byteCnt) {
			info.setCompleate(false);
			return null;
		}
		StringBuffer sb = new StringBuffer();
		while (cnt > 0) {
			toBitString(sb, buff.getInt8(), cnt);
			cnt -= 8;
		}
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, sb.toString());
		}
		return sb.toString();
	}

	/**
	 * Get boolean value from DLMS data.
	 * 
	 * @param buff
	 *            Received DLMS data.
	 * @param info
	 *            Data info.
	 * @return parsed boolean value.
	 */
	private static Object getBoolean(final GXByteBuffer buff,
			final GXDataInfo info) {
		// If there is not enough data available.
		if (buff.size() - buff.position() < 1) {
			info.setCompleate(false);
			return null;
		}
		Boolean value = new Boolean(buff.getUInt8() != 0);
		if (info.getXml() != null) {
			info.getXml().appendLine(info.getXml().getDataType(info.getType()),
					null, value.toString());
		}
		return value;
	}

	/**
	 * Get HDLC address from byte array.
	 * 
	 * @param buff
	 *            byte array.
	 * @return HDLC address.
	 */
	public static int getHDLCAddress(final GXByteBuffer buff) {
		int size = 0;
		for (int pos = buff.position(); pos != buff.size(); ++pos) {
			++size;
			if ((buff.getUInt8(pos) & 0x1) == 1) {
				break;
			}
		}
		if (size == 1) {
			return (byte) ((buff.getUInt8() & 0xFE) >>> 1);
		} else if (size == 2) {
			size = buff.getUInt16();
			size = ((size & 0xFE) >>> 1) | ((size & 0xFE00) >>> 2);
			return size;
		} else if (size == 4) {
			long tmp = buff.getUInt32();
			tmp = ((tmp & 0xFE) >> 1) | ((tmp & 0xFE00) >> 2)
					| ((tmp & 0xFE0000) >> 3) | ((tmp & 0xFE000000) >> 4);
			return (int) tmp;
		}
		throw new IllegalArgumentException("Wrong size.");
	}

	/**
	 * Convert object to DLMS bytes.
	 * 
	 * @param buff
	 *            Byte buffer where data is write.
	 * @param dataType
	 *            Data type.
	 * @param value
	 *            Added Value.
	 */
	public static void setData(final GXByteBuffer buff, final DataType dataType,
			final Object value) {
		DataType type = dataType;
		if ((type == DataType.ARRAY || type == DataType.STRUCTURE)
				&& value instanceof byte[]) {
			// If byte array is added do not add type.
			buff.set((byte[]) value);
			return;
		} else {
			buff.setUInt8(type.getValue());
		}
		switch (type.getValue()) {
		case 0:// DataType.NONE
			break;
		case 3:// DataType.BOOLEAN
			if (((Boolean) value).booleanValue()) {
				buff.setUInt8(1);
			} else {
				buff.setUInt8(0);
			}
			break;
		case 15:// DataType.INT8
		case 0x11:// DataType.UINT8
		case 0x16:// DataType.ENUM
			buff.setUInt8(GXCommon.intValue(value));
			break;
		case 0x10:// DataType.INT16
		case 0x12:// DataType.UINT16
			buff.setUInt16(GXCommon.intValue(value));
			break;
		case 5:// DataType.INT32
		case 6:// DataType.UINT32
			buff.setUInt32(GXCommon.intValue(value));
			break;
		case 20:// DataType.INT64
		case 0x15:// DataType.UINT64
			buff.setUInt64(GXCommon.intValue(value));
			break;
		case 0x17:// DataType.FLOAT32
			buff.setFloat(((Float) value).floatValue());
			break;
		case 0x18:// DataType.FLOAT64
			buff.setDouble(((Double) value).doubleValue());
			break;
		case 4:// DataType.BITSTRING
			setBitString(buff, value);
			break;
		case 10:// DataType.STRING
			setString(buff, value);
			break;
		case 12:// DataType.STRING_UTF8
			setUtfString(buff, value);
			break;
		case 9:// DataType.OCTET_STRING
			if (value instanceof GXDate) {
				// Add size
				buff.setUInt8(5);
				setDate(buff, value);
			} else if (value instanceof GXTime) {
				// Add size
				buff.setUInt8(4);
				setTime(buff, value);
			} else if (value instanceof GXDateTime
					|| value instanceof java.util.Date
					|| value instanceof java.util.Calendar) {
				// Date an calendar are always written as date time.
				buff.setUInt8(12);
				setDateTime(buff, value);
			} else {
				setOctetString(buff, value);
			}
			break;
		case 1:// DataType.ARRAY:
		case 2:// DataType.STRUCTURE
			setArray(buff, value);
			break;
		case 13:// DataType.BCD
			setBcd(buff, value);
			break;
		case 0x13:// DataType.COMPACT_ARRAY:
			throw new RuntimeException("Invalid data type.");
		case 0x19:// DataType.DATETIME
			setDateTime(buff, value);
			break;
		case 0x1A: // DataType.DATE
			setDate(buff, value);
			break;
		case 0x1b: // DataType.TIME
			setTime(buff, value);
			break;
		default:
			throw new RuntimeException("Invalid data type.");
		}
	}

	/**
	 * Convert time to DLMS bytes.
	 * 
	 * @param buff
	 *            Byte buffer where data is write.
	 * @param value
	 *            Added value.
	 */
	private static void setTime(final GXByteBuffer buff, final Object value) {
		Vector skip = new Vector();
		java.util.Calendar tm = java.util.Calendar.getInstance();
		if (value instanceof GXDateTime) {
			GXDateTime tmp = (GXDateTime) value;
			tm.setTime(tmp.getValue());
			skip = tmp.getSkip();
		} else if (value instanceof java.util.Date) {
			tm.setTime((java.util.Date) value);
		} else if (value instanceof java.util.Calendar) {
			tm.setTime(((java.util.Calendar) value).getTime());
		} else if (value instanceof String) {
			throw new RuntimeException(
					"Java ME is not support date time in string format.");
		} else {
			throw new RuntimeException("Invalid date format.");
		}
		// Add time.
		if (skip.contains(DateTimeSkips.HOUR)) {
			buff.setUInt8(0xFF);
		} else {
			buff.setUInt8(tm.get(java.util.Calendar.HOUR_OF_DAY));
		}
		if (skip.contains(DateTimeSkips.MINUTE)) {
			buff.setUInt8(0xFF);
		} else {
			buff.setUInt8(tm.get(java.util.Calendar.MINUTE));
		}
		if (skip.contains(DateTimeSkips.SECOND)) {
			buff.setUInt8(0xFF);
		} else {
			buff.setUInt8(tm.get(java.util.Calendar.SECOND));
		}
		if (skip.contains(DateTimeSkips.MILLISECOND)) {
			// Hundredths of second is not used.
			buff.setUInt8(0xFF);
		} else {
			buff.setUInt8(tm.get(java.util.Calendar.MILLISECOND) / 10);
		}
	}

	/**
	 * Convert date to DLMS bytes.
	 * 
	 * @param buff
	 *            Byte buffer where data is write.
	 * @param value
	 *            Added value.
	 */
	private static void setDate(final GXByteBuffer buff, final Object value) {
		GXDateTime dt;
		if (value instanceof GXDateTime) {
			dt = (GXDateTime) value;
		} else if (value instanceof java.util.Date) {
			dt = new GXDateTime((java.util.Date) value);
		} else if (value instanceof java.util.Calendar) {
			dt = new GXDateTime(((java.util.Calendar) value).getTime());
		} else if (value instanceof String) {
			throw new RuntimeException(
					"Java ME is not support date time in string format.");
		} else {
			throw new RuntimeException("Invalid date format.");
		}
		java.util.Calendar tm = java.util.Calendar.getInstance();
		tm.setTime(dt.getValue());
		// Add year.
		if (dt.getSkip().contains(DateTimeSkips.YEAR)) {
			buff.setUInt16(0xFFFF);
		} else {
			buff.setUInt16(tm.get(java.util.Calendar.YEAR));
		}
		// Add month
		if (dt.getDaylightSavingsBegin()) {
			buff.setUInt8(0xFE);
		} else if (dt.getDaylightSavingsEnd()) {
			buff.setUInt8(0xFD);
		} else if (dt.getSkip().contains(DateTimeSkips.MONTH)) {
			buff.setUInt8(0xFF);
		} else {
			buff.setUInt8((tm.get(java.util.Calendar.MONTH) + 1));
		}
		// Add day
		if (dt.getSkip().contains(DateTimeSkips.DAY)) {
			buff.setUInt8(0xFF);
		} else {
			buff.setUInt8(tm.get(java.util.Calendar.DATE));
		}
		if (dt.getSkip().contains(DateTimeSkips.DAY_OF_WEEK)) {
			// Week day is not specified.
			buff.setUInt8(0xFF);
		} else {
			int val = tm.get(java.util.Calendar.DAY_OF_WEEK);
			if (val == java.util.Calendar.SUNDAY) {
				val = 8;
			}
			buff.setUInt8(val - 1);
		}
	}

	public static GXDateTime getDateTime(final Object value) {
		GXDateTime dt;
		if (value instanceof GXDateTime) {
			dt = (GXDateTime) value;
		} else if (value instanceof java.util.Date) {
			dt = new GXDateTime((java.util.Date) value);
			dt.getSkip().addElement(DateTimeSkips.MILLISECOND);
		} else if (value instanceof java.util.Calendar) {
			java.util.Calendar tm = (java.util.Calendar) value;
			dt = new GXDateTime(tm.getTime());
			dt.setDeviation(tm.getTimeZone().getRawOffset() / 60000);
			dt.getSkip().addElement(DateTimeSkips.MILLISECOND);
		} else if (value instanceof String) {
			throw new RuntimeException(
					"Java ME is not support date time in string format.");
		} else {
			throw new RuntimeException("Invalid date format.");
		}
		return dt;
	}

	/**
	 * Convert date time to DLMS bytes.
	 * 
	 * @param buff
	 *            Byte buffer where data is write.
	 * @param value
	 *            Added value.
	 */
	private static void setDateTime(final GXByteBuffer buff,
			final Object value) {
		GXDateTime dt;
		java.util.Calendar tm = null;
		if (value instanceof GXDateTime) {
			dt = (GXDateTime) value;
		} else if (value instanceof java.util.Date) {
			dt = new GXDateTime((java.util.Date) value);
			dt.getSkip().addElement(DateTimeSkips.MILLISECOND);
		} else if (value instanceof java.util.Calendar) {
			tm = (java.util.Calendar) value;
			dt = new GXDateTime(tm.getTime());
			dt.setDeviation(tm.getTimeZone().getRawOffset() / 60000);
			dt.getSkip().addElement(DateTimeSkips.MILLISECOND);
		} else if (value instanceof String) {
			throw new RuntimeException(
					"Java ME is not support date time in string format.");
		} else {
			throw new RuntimeException("Invalid date format.");
		}
		if (tm == null) {
			tm = java.util.Calendar.getInstance();
		}
		Date date = dt.toMeterTime();
		boolean summertime = false;
		// TODO: summertime = tm.getTimeZone().inDaylightTime(date);
		tm.setTime(dt.getValue());
		// If summer time.
		if (summertime
				|| dt.getStatus().contains(ClockStatus.DAYLIGHT_SAVE_ACTIVE)) {
			tm.set(Calendar.HOUR, -1);
		}

		// Add year.
		if (dt.getSkip().contains(DateTimeSkips.YEAR)) {
			buff.setUInt16(0xFFFF);
		} else {
			buff.setUInt16(tm.get(java.util.Calendar.YEAR));
		}
		// Add month
		if (dt.getDaylightSavingsEnd()) {
			buff.setUInt8(0xFD);
		} else if (dt.getDaylightSavingsBegin()) {
			buff.setUInt8(0xFE);
		} else if (dt.getSkip().contains(DateTimeSkips.MONTH)) {
			buff.setUInt8(0xFF);
		} else {
			buff.setUInt8((tm.get(java.util.Calendar.MONTH) + 1));
		}
		// Add day
		if (dt.getSkip().contains(DateTimeSkips.DAY)) {
			buff.setUInt8(0xFF);
		} else {
			buff.setUInt8(tm.get(java.util.Calendar.DATE));
		}
		// Week day.
		if (dt.getSkip().contains(DateTimeSkips.DAY_OF_WEEK)) {
			buff.setUInt8(0xFF);
		} else {
			int val = tm.get(java.util.Calendar.DAY_OF_WEEK);
			if (val == java.util.Calendar.SUNDAY) {
				val = 8;
			}
			buff.setUInt8(val - 1);
		}

		// Add time.
		if (dt.getSkip().contains(DateTimeSkips.HOUR)) {
			buff.setUInt8(0xFF);
		} else {
			buff.setUInt8(tm.get(java.util.Calendar.HOUR_OF_DAY));
		}
		if (dt.getSkip().contains(DateTimeSkips.MINUTE)) {
			buff.setUInt8(0xFF);
		} else {
			buff.setUInt8(tm.get(java.util.Calendar.MINUTE));
		}
		if (dt.getSkip().contains(DateTimeSkips.SECOND)) {
			buff.setUInt8(0xFF);
		} else {
			buff.setUInt8(tm.get(java.util.Calendar.SECOND));
		}
		if (dt.getSkip().contains(DateTimeSkips.MILLISECOND)) {
			// Hundredth of seconds is not used.
			buff.setUInt8(0xFF);
		} else {
			int ms = tm.get(java.util.Calendar.MILLISECOND);
			if (ms != 0) {
				ms /= 10;
			}
			buff.setUInt8(ms);
		}
		// devitation not used.
		if (dt.getSkip().contains(DateTimeSkips.DEVITATION)) {
			buff.setUInt16(0x8000);
		} else {
			// Add devitation.
			buff.setUInt16(dt.getDeviation());
		}
		// Add clock_status
		if (summertime) {
			buff.setUInt8(ClockStatus.toInteger(dt.getStatus())
					| ClockStatus.DAYLIGHT_SAVE_ACTIVE.getValue());
		} else {
			buff.setUInt8(ClockStatus.toInteger(dt.getStatus()));
		}
	}

	/**
	 * Convert BCD to DLMS bytes.
	 * 
	 * @param buff
	 *            Byte buffer where data is write.
	 * @param value
	 *            Added value.
	 */
	private static void setBcd(final GXByteBuffer buff, final Object value) {
		// Standard supports only size of byte.
		buff.setUInt8(GXCommon.intValue(value));
	}

	/**
	 * Convert Array to DLMS bytes.
	 * 
	 * @param buff
	 *            Byte buffer where data is write.
	 * @param value
	 *            Added value.
	 */
	private static void setArray(final GXByteBuffer buff, final Object value) {
		if (value != null) {
			Object[] arr = ((Object[]) value);
			int len = arr.length;
			setObjectCount(len, buff);
			for (int pos = 0; pos != len; ++pos) {
				Object it = arr[pos];
				setData(buff, getValueType(it), it);
			}
		} else {
			setObjectCount(0, buff);
		}
	}

	/*
	 * Split string to array.
	 */
	public static String[] split(String str, char separator) {
		Vector arr = new Vector();
		int pos = 0, lastPos = 0;
		while ((pos = str.indexOf(separator, lastPos)) != -1) {
			arr.addElement(str.substring(lastPos, pos));
			lastPos = pos + 1;
		}
		if (str.length() > lastPos) {
			arr.addElement(str.substring(lastPos));
		} else {
			arr.addElement("");
		}
		String tmp[] = new String[arr.size()];
		arr.copyInto(tmp);
		return tmp;
	}

	public static String[] split(String str, String separator) {
		Vector arr = new Vector();
		int pos = 0, lastPos = 0;
		while ((pos = str.indexOf(separator, lastPos)) != -1) {
			arr.addElement(str.substring(lastPos, pos));
			lastPos = pos + separator.length();
		}
		if (str.length() > lastPos) {
			arr.addElement(str.substring(lastPos));
		} else {
			arr.addElement("");
		}
		String tmp[] = new String[arr.size()];
		arr.copyInto(tmp);
		return tmp;
	}

	/*
	 * Replace chars in string. Java ME is not supporting String.replaceAll
	 * method.
	 */
	public static String replaceAll(final String str, final String pattern,
			final String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		while ((e = str.indexOf(pattern, s)) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}

	/**
	 * Convert Octet string to DLMS bytes.
	 * 
	 * @param buff
	 *            Byte buffer where data is write.
	 * @param value
	 *            Added value.
	 */
	private static void setOctetString(final GXByteBuffer buff,
			final Object value) {
		// Example Logical name is octet string, so do not change to
		// string...
		if (value instanceof String) {
			String[] items = split((String) value, '.');
			// If data is string.
			if (items.length == 1) {
				byte[] tmp = ((String) value).getBytes();
				setObjectCount(tmp.length, buff);
				buff.set(tmp);
			} else {
				setObjectCount(items.length, buff);
				for (int pos = 0; pos != items.length; ++pos) {
					buff.setUInt8(Integer.parseInt(items[pos]));
				}
			}
		} else if (value instanceof byte[]) {
			setObjectCount(((byte[]) value).length, buff);
			buff.set((byte[]) value);
		} else if (value == null) {
			setObjectCount(0, buff);
		} else {
			throw new RuntimeException("Invalid data type.");
		}
	}

	/**
	 * Convert UTF string to DLMS bytes.
	 * 
	 * @param buff
	 *            Byte buffer where data is write.
	 * @param value
	 *            Added value.
	 */
	private static void setUtfString(final GXByteBuffer buff,
			final Object value) {
		if (value != null) {
			try {
				byte[] tmp = String.valueOf(value).getBytes("UTF-8");
				setObjectCount(tmp.length, buff);
				buff.set(tmp);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e.getMessage());
			}
		} else {
			buff.setUInt8(0);
		}
	}

	/**
	 * Convert ASCII string to DLMS bytes.
	 * 
	 * @param buff
	 *            Byte buffer where data is write.
	 * @param value
	 *            Added value.
	 */
	private static void setString(final GXByteBuffer buff, final Object value) {
		if (value != null) {
			String str = String.valueOf(value);
			setObjectCount(str.length(), buff);
			buff.set(GXCommon.getBytes(str));
		} else {
			buff.setUInt8(0);
		}
	}

	/**
	 * Convert Bit string to DLMS bytes.
	 * 
	 * @param buff
	 *            Byte buffer where data is write.
	 * @param value
	 *            Added value.
	 */
	private static void setBitString(final GXByteBuffer buff,
			final Object value) {
		if (value instanceof String) {
			byte val = 0;
			int index = 0;
			GXByteBuffer tmp = new GXByteBuffer();
			StringBuffer str = new StringBuffer((String) value).reverse();
			setObjectCount(str.length(), buff);
			for (int pos = 0; pos != str.length(); ++pos) {
				char it = str.charAt(pos);
				if (it == '1') {
					val |= (byte) (1 << index++);
				} else if (it == '0') {
					index++;
				} else {
					throw new RuntimeException("Not a bit string.");
				}
				if (index == 8) {
					index = 0;
					tmp.setUInt8(val);
					val = 0;
				}
			}
			if (index != 0) {
				tmp.setUInt8(val);
			}
			for (int pos = tmp.size() - 1; pos != -1; --pos) {
				buff.setUInt8(tmp.getUInt8(pos));
			}
		} else if (value instanceof byte[]) {
			byte[] arr = (byte[]) value;
			setObjectCount(arr.length, buff);
			buff.set(arr);
		} else if (value == null) {
			buff.setUInt8(0);
		} else {
			throw new RuntimeException("BitString must give as string.");
		}
	}

	public static DataType getValueType(final Object value) {
		if (value == null) {
			return DataType.NONE;
		}
		if (value instanceof byte[]) {
			return DataType.OCTET_STRING;
		}
		if (value instanceof Byte) {
			return DataType.INT8;
		}
		if (value instanceof Short) {
			return DataType.INT16;
		}
		if (value instanceof Integer) {
			return DataType.INT32;
		}
		if (value instanceof Long) {
			return DataType.INT64;
		}
		if (value instanceof GXTime) {
			return DataType.TIME;
		}
		if (value instanceof GXDate) {
			return DataType.DATE;
		}

		if (value instanceof java.util.Date || value instanceof GXDateTime) {
			return DataType.DATETIME;
		}
		if (value.getClass().isArray()) {
			return DataType.ARRAY;
		}
		if (value instanceof String) {
			return DataType.STRING;
		}
		if (value instanceof Boolean) {
			return DataType.BOOLEAN;
		}
		if (value instanceof Float) {
			return DataType.FLOAT32;
		}
		if (value instanceof Double) {
			return DataType.FLOAT64;
		}
		throw new RuntimeException("Invalid value.");
	}

	/*
	 * Calculate power of value. Java ME do not implement Math.pow.
	 */
	public static double pow(int base, int exp) {
		if (exp == 0) {
			return 1;
		}
		boolean neg = exp < 0.;
		double result = base;
		for (long i = 1; i < (neg ? -exp : exp); ++i) {
			result = result * base;
		}
		if (neg) {
			return 1. / result;
		} else {
			return result;
		}
	}

	/*
	 * Count log. Java ME do not implement Math.log.
	 */
	static public double log10(double x) {
		if (!(x > 0.)) {
			return Double.NaN;
		}
		double f = 0.0;
		int appendix = 0;
		while (x > 0.0 && x <= 1.0) {
			x *= 2.0;
			++appendix;
		}
		x /= 2.0;
		--appendix;
		double y1 = x - 1.;
		double y2 = x + 1.;
		double y = y1 / y2;
		double k = y;
		y2 = k * y;
		for (long i = 1; i < 50; i += 2) {
			f += k / i;
			k *= y2;
		}
		f *= 2.0;
		for (int i = 0; i < appendix; i++) {
			// ln(0.5)
			f += -0.6931471805599453094;
		}
		return f;
	}

	/**
	 * Convert list of byte arrays to array of byte arrays.
	 * 
	 * @param arr
	 *            List of byte arrays.
	 * @return Array of byte arrays.
	 */
	public static byte[][] toArray(Vector arr) {
		byte[][] tmp = new byte[arr.size()][];
		for (int pos = 0; pos != arr.size(); ++pos) {
			tmp[pos] = (byte[]) arr.elementAt(pos);
		}
		return tmp;
	}
}