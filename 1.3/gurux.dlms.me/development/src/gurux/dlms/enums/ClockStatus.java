package gurux.dlms.enums;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Defines Clock status.
 */
public class ClockStatus {
	/**
	 * OK.
	 */
	public static final ClockStatus OK = new ClockStatus(0, "OK");
	/**
	 * Invalid value.
	 */
	public static final ClockStatus INVALID_VALUE = new ClockStatus(1,
			"INVALID_VALUE");
	/**
	 * Doubtful b value.
	 */
	public static final ClockStatus DOUBTFUL_VALUE = new ClockStatus(2,
			"DOUBTFUL_VALUE");
	/**
	 * Different clock base c.
	 */
	public static final ClockStatus DIFFERENT_CLOCK_BASE = new ClockStatus(4,
			"DIFFERENT_CLOCK_BASE");
	/**
	 * Invalid clock status d.
	 */
	public static final ClockStatus INVALID_CLOCK_STATUS = new ClockStatus(8,
			"INVALID_CLOCK_STATUS");
	/**
	 * Reserved.
	 */
	public static final ClockStatus RESERVED2 = new ClockStatus(0x10,
			"RESERVED2");
	/**
	 * Reserved.
	 */
	public static final ClockStatus RESERVED3 = new ClockStatus(0x20,
			"RESERVED3");
	/**
	 * Reserved.
	 */
	public static final ClockStatus RESERVED4 = new ClockStatus(0x40,
			"RESERVED4");
	/**
	 * Daylight saving active.
	 */
	public static final ClockStatus DAYLIGHT_SAVE_ACTIVE = new ClockStatus(0x80,
			"DAYLIGHT_SAVE_ACTIVE");

	/**
	 * Clock status is skipped.
	 */
	public static final ClockStatus SKIPPED = new ClockStatus(0xFF, "SKIPPED");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private ClockStatus(int value, String name) {
		this.value = value;
		this.name = name;
		getMappings().put(new Integer(value), this);
	}

	/*
	 * Enumeration name.
	 */
	private final String name;

	/*
	 * Enumeration integer value.
	 */
	private int value;

	/*
	 * Collection of enumeration values.
	 */
	private static java.util.Hashtable mappings;

	/*
	 * Collection of enumeration values.
	 */
	private static java.util.Hashtable getMappings() {
		synchronized (ClockStatus.class) {
			if (mappings == null) {
				mappings = new java.util.Hashtable();
			}
		}
		return mappings;
	}

	/*
	 * Get integer value for enumeration.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return Get enumeration constant values.
	 */
	private static ClockStatus[] getEnumConstants() {
		return new ClockStatus[] { OK, INVALID_VALUE, DOUBTFUL_VALUE,
				DIFFERENT_CLOCK_BASE, INVALID_CLOCK_STATUS, RESERVED2,
				RESERVED3, RESERVED4, DAYLIGHT_SAVE_ACTIVE, SKIPPED };

	}

	/**
	 * Converts the integer value to enumerated value.
	 * 
	 * @param value
	 *            The integer value, which is read from the device.
	 * @return The enumerated value, which represents the integer.
	 */
	public static Vector forValue(final int value) {
		Vector types = new Vector();
		if (value == 0) {
			types.addElement(ClockStatus.OK);
		} else {
			ClockStatus[] enums = getEnumConstants();
			for (int pos = 0; pos != enums.length; ++pos) {
				if (enums[pos] != ClockStatus.OK
						&& (enums[pos].value & value) == enums[pos].value) {
					types.addElement(enums[pos]);
				}
			}
		}
		return types;
	}

	/**
	 * Converts the enumerated value to integer value.
	 * 
	 * @param value
	 *            The enumerated value.
	 * @return The integer value.
	 */
	public static int toInteger(final Vector value) {
		int tmp = 0;
		Enumeration iterator = value.elements();
		while (iterator.hasMoreElements()) {
			ClockStatus it = (ClockStatus) iterator.nextElement();
			tmp |= it.getValue();
		}
		return tmp;
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
};