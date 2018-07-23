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

package gurux.dlms;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.TimeZone;
import java.util.Vector;

import gurux.dlms.enums.ClockStatus;
import gurux.dlms.enums.DateTimeSkips;
import gurux.dlms.internal.GXCommon;

public class GXDateTime {
	public static final long SECOND = 1000;
	public static final long MINUTE = 60 * SECOND;
	public static final long HOUR = 60 * MINUTE;

	/**
	 * Clock status.
	 */
	private Vector status;
	/**
	 * Clock time.
	 */
	private java.util.Date time = new java.util.Date(0);
	/**
	 * Skipped fields.
	 */
	private Vector skip;
	/**
	 * Daylight savings begin.
	 */
	private boolean daylightSavingsBegin;
	/**
	 * Daylight savings end.
	 */
	private boolean daylightSavingsEnd;
	/**
	 * Deviation.
	 */
	private int deviation;

	/**
	 * Constructor.
	 */
	public GXDateTime() {
		skip = new Vector();
		deviation = 0x8000;
		status = new Vector();
		status.addElement(ClockStatus.OK);
	}

	/**
	 * Constructor.
	 * 
	 * @param forvalue
	 *            Date value.
	 */
	public GXDateTime(final Date forvalue) {
		skip = new Vector();
		setValue(forvalue);
		status = new Vector();
		status.addElement(ClockStatus.OK);
	}

	/**
	 * Constructor.
	 * 
	 * @param year
	 *            Used year.
	 * @param month
	 *            Used month.
	 * @param day
	 *            Used day.
	 * @param hour
	 *            Used hour.
	 * @param minute
	 *            Used minute.
	 * @param second
	 *            Used second.
	 * @param millisecond
	 *            Used millisecond.
	 */
	public GXDateTime(final int year, final int month, final int day,
			final int hour, final int minute, final int second,
			final int millisecond) {
		int y = year;
		int m = month;
		int d = day;
		int h = hour;
		int min = minute;
		int s = second;
		int ms = millisecond;
		skip = new Vector();
		status = new Vector();
		status.addElement(ClockStatus.OK);
		if (y < 1 || y == 0xFFFF) {
			skip.addElement(DateTimeSkips.YEAR);
			java.util.Calendar tm = java.util.Calendar.getInstance();
			y = tm.get(Calendar.YEAR);
		}
		daylightSavingsBegin = m == 0xFE;
		daylightSavingsEnd = m == 0xFD;
		if (m < 1 || m > 12) {
			skip.addElement(DateTimeSkips.MONTH);
			m = 0;
		} else {
			m -= 1;
		}

		if (d == -1 || d == 0 || d > 31) {
			skip.addElement(DateTimeSkips.DAY);
			d = 1;
		} else if (d < 0) {
			Calendar cal = Calendar.getInstance();
			// TODO: //d = cal.getActualMaximum(Calendar.DATE) + d + 3;
		}
		if (h < 0 || h > 24) {
			skip.addElement(DateTimeSkips.HOUR);
			h = 0;
		}
		if (min < 0 || min > 60) {
			skip.addElement(DateTimeSkips.MINUTE);
			min = 0;
		}
		if (s < 0 || s > 60) {
			skip.addElement(DateTimeSkips.SECOND);
			s = 0;
		}
		// If ms is Zero it's skipped.
		if (ms < 1 || ms > 1000) {
			skip.addElement(DateTimeSkips.MILLISECOND);
			ms = 0;
		}
		java.util.Calendar tm = java.util.Calendar.getInstance();
		tm.set(Calendar.YEAR, year);
		tm.set(Calendar.MONTH, month);
		tm.set(Calendar.DAY_OF_MONTH, day);
		tm.set(Calendar.HOUR_OF_DAY, hour);
		tm.set(Calendar.MINUTE, minute);
		tm.set(Calendar.SECOND, second);
		if (ms != 0) {
			tm.set(Calendar.MILLISECOND, ms);
		}
		setValue(tm.getTime());
	}

	/**
	 * @return Used date time value.
	 */
	public final java.util.Date getValue() {
		return time;
	}

	/**
	 * Set date time value.
	 * 
	 * @param forvalue
	 *            Used date time value.
	 */
	public final void setValue(final java.util.Date forvalue) {
		Calendar c = java.util.Calendar.getInstance();
		int t = java.util.Calendar.getInstance().getTimeZone().getOffset(1,
				c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.DAY_OF_WEEK),
				c.get(Calendar.MILLISECOND)) / 60000;
		setValue(forvalue, t);

	}

	/**
	 * Set date time value.
	 * 
	 * @param forvalue
	 *            Used date time value.
	 * @param forDeviation
	 *            Used deviation.
	 */
	public final void setValue(final java.util.Date forvalue,
			final int forDeviation) {
		time = forvalue;
		deviation = forDeviation;
	}

	/**
	 * @return Skipped date time fields.
	 */
	public final Vector getSkip() {
		return skip;
	}

	/**
	 * @param forValue
	 *            Skipped date time fields.
	 */
	public final void setSkip(final Vector forValue) {
		skip = forValue;
	}

	public final void setUsed(final Vector forValue) {
		int val = 0;
		Enumeration iterator = forValue.elements();
		while (iterator.hasMoreElements()) {
			DateTimeSkips it = (DateTimeSkips) iterator.nextElement();
			val |= it.getValue();
		}
		int tmp = (-1 & ~val);
		GXCommon.addAll(skip, DateTimeSkips.forValue(tmp));
	}

	/**
	 * @return Daylight savings begin.
	 */
	public final boolean getDaylightSavingsBegin() {
		return daylightSavingsBegin;
	}

	/**
	 * @param forValue
	 *            Daylight savings begin.
	 */
	public final void setDaylightSavingsBegin(final boolean forValue) {
		daylightSavingsBegin = forValue;
	}

	/**
	 * @return Daylight savings end.
	 */
	public final boolean getDaylightSavingsEnd() {
		return daylightSavingsEnd;
	}

	/**
	 * @param forValue
	 *            Daylight savings end.
	 */
	public final void setDaylightSavingsEnd(final boolean forValue) {
		daylightSavingsEnd = forValue;
	}

	/**
	 * @return Deviation.
	 */
	public final int getDeviation() {
		return deviation;
	}

	/**
	 * @param forValue
	 *            Deviation.
	 */
	public final void setDeviation(final int forValue) {
		deviation = forValue;
	}

	/*
	 * Status of the clock.
	 */
	public final Vector getStatus() {
		return status;
	}

	public final void setStatus(final Vector forValue) {
		status = forValue;
	}

	public final String toString() {
		return meterTimeToLocalTime(getMeterCalendar()).toString();
	}

	public static Date meterTimeToLocalTime(final Calendar calendar) {
		Calendar tm = Calendar.getInstance();
		int offset = tm.getTimeZone().getRawOffset();
		offset -= calendar.getTimeZone().getRawOffset();
		offset /= 60000;
		tm.setTime(calendar.getTime());
		tm.setTime(new Date(tm.getTime().getTime() + MINUTE * offset));
		return tm.getTime();
	}

	private static String getTimeZone(final int forDeviation) {
		// TODO: Mikko
		String tmp = String.valueOf(forDeviation / 60) + ":"
				+ String.valueOf(forDeviation % 60);
		if (forDeviation == 0) {
			return "GMT";
		} else if (forDeviation > 0) {
			return "GMT+" + tmp;
		}
		return "GMT" + tmp;
	}

	/**
	 * Gets a calendar using the meter's time zone.
	 * 
	 * @return Meter's calendar.
	 */
	public final Calendar getMeterCalendar() {
		// Add deviation.
		TimeZone tz;
		tz = TimeZone.getTimeZone(getTimeZone(deviation));
		java.util.Calendar tm = java.util.Calendar.getInstance(tz);
		tm.setTime(time);
		// Convert to GMT if time zone not found.
		if (tz.getRawOffset() / 60000 != deviation) {
			tm.setTime(new Date(tm.getTime().getTime() + MINUTE * -deviation));
		}
		return tm;
	}

	/**
	 * @return Date time as local time.
	 */
	public final Date toLocalTime() {
		return meterTimeToLocalTime(getMeterCalendar());
	}

	/**
	 * @return Date time as local time.
	 */
	public final Date toMeterTime() {
		return getMeterCalendar().getTime();
	}
}