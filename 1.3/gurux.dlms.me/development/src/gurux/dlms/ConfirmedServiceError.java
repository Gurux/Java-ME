//
// --------------------------------------------------------------------------
//  Gurux Ltd

package gurux.dlms;

/**
 * Confirmed service error tells when error has occurred.
 */
public class ConfirmedServiceError {
	/**
	 * Error has occurred on initialize.
	 */
	static final ConfirmedServiceError INITIATE_ERROR = new ConfirmedServiceError(
			1, "INITIATE_ERROR");
	/**
	 * Error has occurred on read.
	 */
	static final ConfirmedServiceError READ = new ConfirmedServiceError(5,
			"READ");
	/**
	 * Error has occurred on write.
	 */
	static final ConfirmedServiceError WRITE = new ConfirmedServiceError(6,
			"WRITE");

	/*
	 * Constructor.
	 * 
	 * @param value Enumeration value.
	 * 
	 * @param name Enumeration name.
	 */
	private ConfirmedServiceError(int value, String name) {
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
		synchronized (ConfirmedServiceError.class) {
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

	/*
	 * Convert integer to enumeration value.
	 */
	public static ConfirmedServiceError forValue(int value) {
		return (ConfirmedServiceError) getMappings().get(new Integer(value));
	}

	/**
	 * Convert enumeration value to string.
	 */
	public String toString() {
		return name;
	}
}
