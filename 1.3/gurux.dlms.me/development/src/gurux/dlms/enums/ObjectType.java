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

package gurux.dlms.enums;

/**
 * ObjectType enumerates the usable types of DLMS objects in GuruxDLMS.
 */
public class ObjectType {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	/**
	 * When communicating with a meter, the application may demand periodical
	 * actions. If these actions are not linked to tariffication =
	 * ActivityCalendar or Schedule, use an object of type ActionSchedule =
	 * 0x16.
	 */
	public static final ObjectType ACTION_SCHEDULE = new ObjectType(22,
			"ACTION_SCHEDULE");

	/**
	 * When handling tariffication structures, you can use an object of type
	 * ActivityCalendar. It determines, when to activate specified scripts to
	 * perform certain activities in the meter. The activities, simply said,
	 * scheduled actions, are operations that are carried out on a specified
	 * day, at a specified time. ActivityCalendar can be used together with a
	 * more general object type, Schedule, and they can even overlap. If
	 * multiple actions are timed to the same moment, the actions determined in
	 * the Schedule are executed first, and then the ones determined in the
	 * ActivityCalendar. If using object type SpecialDaysTable, with
	 * ActivityCalendar, simultaneous actions determined in SpecialDaysTable are
	 * executed over the ones determined in ActivityCalendar.
	 * <p />
	 * <b>Note: </b>To make sure that tariffication is correct after a power
	 * failure, only the latest missed action from ActivityCalendar is executed,
	 * with a delay. In a case of power failure, if a Schedule object coexists,
	 * the latest missed action from ActivityCalendar has to be executed at the
	 * correct time, sequentially with actions determined in the Schedule.
	 */
	public static final ObjectType ACTIVITY_CALENDAR = new ObjectType(20,
			"ACTIVITY_CALENDAR");

	/**
	 * All object types are used.
	 */
	public static final ObjectType ALL = new ObjectType(0, "ALL");

	/**
	 * AssociationLogicalName object type is used with meters that utilize
	 * Logical Name associations within a COSEM.
	 */
	public static final ObjectType ASSOCIATION_LOGICAL_NAME = new ObjectType(15,
			"ASSOCIATION_LOGICAL_NAME");

	/**
	 * AssociationShortName object type is used with meters that utilize Short
	 * Name associations within a COSEM.
	 */
	public static final ObjectType ASSOCIATION_SHORT_NAME = new ObjectType(12,
			"ASSOCIATION_SHORT_NAME");

	/**
	 * To determine auto answering settings = for data transfer between device
	 * and modem = s to answer incoming calls, use AutoAnswer object.
	 */
	public static final ObjectType AUTO_ANSWER = new ObjectType(28,
			"AUTO_ANSWER");

	/**
	 * To determine auto connecting settings = for data transfer from the meter
	 * to defined destinations, use AutoConnect = previously known as AutoDial
	 * object.
	 */
	public static final ObjectType AUTO_CONNECT = new ObjectType(29,
			"AUTO_CONNECT");

	/**
	 * An object of type Clock is used to handle the information of a date =
	 * day, month and year and/or a time = hundredths of a second, seconds,
	 * minutes and hours.
	 */
	public static final ObjectType CLOCK = new ObjectType(8, "CLOCK");

	/**
	 * An object of type Data typically stores manufacturer specific information
	 * of the meter, for example configuration data and logical name.
	 */
	public static final ObjectType DATA = new ObjectType(1, "DATA");

	/**
	 * An object of type DemandRegister stores a value, information of the item,
	 * which the value belongs to, the status of the item, and the time of the
	 * value. DemandRegister object type enables both current, and last average,
	 * it supports both block, and sliding demand calculation, and it also
	 * provides resetting the value average, and periodic averages.
	 */
	public static final ObjectType DEMAND_REGISTER = new ObjectType(5,
			"DEMAND_REGISTER");

	/**
	 * MAC address of the physical device.
	 * 
	 * The name and the use of this interface class has been changed from
	 * Ethernet setup to MAC address setup to allow a more general use.
	 */
	public static final ObjectType MAC_ADDRESS_SETUP = new ObjectType(43,
			"MAC_ADDRESS_SETUP");

	/**
	 * When a certain occasion should cause a certain action, use an object of
	 * type Event, which the client system can enable. When enabled, and when
	 * the specified occasion occures, the event is sent the soonest possible.
	 * Information of the time of the occasion, the acknowledgement of the
	 * event, and its response time is included.
	 */
	public static final ObjectType EVENT = new ObjectType(100, "EVENT");

	/**
	 * ExtendedRegister stores a value, and understands the type of the value.
	 * Refer to an object of this type by its logical name, using the OBIS
	 * identification code.
	 */
	public static final ObjectType EXTENDED_REGISTER = new ObjectType(4,
			"EXTENDED_REGISTER");

	/**
	 * To determine the GPRS settings, use GprsSetup object.
	 */
	public static final ObjectType GPRS_SETUP = new ObjectType(45,
			"GPRS_SETUP");

	/**
	 * To determine the HDLC = High-level Data Link Control settings, use the
	 * IecHdlcSetup object.
	 */
	public static final ObjectType IEC_HDLC_SETUP = new ObjectType(23,
			"IEC_HDLC_SETUP");

	/**
	 * To determine the Local Port settings, use the IecLocalPortSetup object.
	 */
	public static final ObjectType IEC_LOCAL_PORT_SETUP = new ObjectType(19,
			"IEC_LOCAL_PORT_SETUP");

	/**
	 * To determine the Twisted Pair settings, use the IecTwistedPairSetup
	 * object.
	 */
	public static final ObjectType IEC_TWISTED_PAIR_SETUP = new ObjectType(24,
			"IEC_TWISTED_PAIR_SETUP");

	/**
	 * To determine the IP 4 settings, use the Ip4Setup object.
	 */
	public static final ObjectType IP4_SETUP = new ObjectType(42, "IP4_SETUP");

	public static final ObjectType GSM_DIAGNOSTIC = new ObjectType(47,
			"GSM_DIAGNOSTIC");

	/// To determine the IP 6 settings, use the Ip6Setup object.
	public static final ObjectType IP6_SETUP = new ObjectType(48, "IP6_SETUP");

	/**
	 * To determine the M-BUS settings, use the MbusSetup object.
	 */
	public static final ObjectType MBUS_SLAVE_PORT_SETUP = new ObjectType(25,
			"MBUS_SLAVE_PORT_SETUP");

	/**
	 * To determine modem settings, use ModemConfiguration object.
	 */
	public static final ObjectType MODEM_CONFIGURATION = new ObjectType(27,
			"MODEM_CONFIGURATION");

	/**
	 * Default value, no object type is set.
	 */
	public static final ObjectType NONE = new ObjectType(0, "NONE");

	/**
	 * To determine PPP = Point-to-Point Protocol settings, use the PppSetup
	 * object.
	 */
	public static final ObjectType PPP_SETUP = new ObjectType(44, "PPP_SETUP");

	/**
	 * ProfileGeneric determines a general way of gathering values from a
	 * profile. The data is retrieved either by a period of time, or by an
	 * occuring event. When gathering values from a profile, you need to
	 * understand the concept of the profile buffer, in which the profile data
	 * is stored. The buffer may be sorted by a register, or by a clock, within
	 * the profile, or the data can be just piled in it, in order: last in,
	 * first out. You can retrieve a part of the buffer, within a certain range
	 * of values, or by a range of entry numbers. You can also determine
	 * objects, whose values are to be retained. To determine, what to retrieve,
	 * and what to retain, you need to assign the objects to the profile. You
	 * can use static assignments, as all entries in a buffer are alike = same
	 * size, same structure.
	 * <p />
	 * <b>Note: </b>When you modify any assignment, the buffer of the
	 * corresponding profile is cleared, and all other profiles, using the
	 * modified one, will be cleared too. This is to make sure that their
	 * entries stay alike by size and structure.
	 */
	public static final ObjectType PROFILE_GENERIC = new ObjectType(7,
			"PROFILE_GENERIC");

	/**
	 * Register stores a value, and understands the type of the value. Refer to
	 * an object of this type by its logical name, using the OBIS identification
	 * code.
	 */
	public static final ObjectType REGISTER = new ObjectType(3, "REGISTER");

	/**
	 * When handling tariffication structures, you can use RegisterActivation to
	 * determine, what objects to enable, when activating a certain activation
	 * mask. The objects, assigned to the register, but not determined in the
	 * mask, are disabled.
	 * <p />
	 * <b>Note: </b>If an object is not assigned to any register, it is, by
	 * default, enabled.
	 */
	public static final ObjectType REGISTER_ACTIVATION = new ObjectType(6,
			"REGISTER_ACTIVATION");

	/**
	 * RegisterMonitor allows you to determine scripts to execute, when a
	 * register value crosses a specified threshold. To use RegisterMonitor,
	 * also ScriptTable needs to be instantiated in the same logical device.
	 */
	public static final ObjectType REGISTER_MONITOR = new ObjectType(21,
			"REGISTER_MONITOR");

	/*
	 * Instances of the Disconnect control IC manage an internal or external
	 * disconnect unit of the meter (e.g. electricity breaker, gas valve) in
	 * order to connect or disconnect â€“ partly or entirely â€“ the
	 * premises of the consumer to / from the supply.
	 */
	public static final ObjectType DISCONNECT_CONTROL = new ObjectType(70,
			"DISCONNECT_CONTROL");

	public static final ObjectType LIMITER = new ObjectType(71, "LIMITER");

	public static final ObjectType MBUS_CLIENT = new ObjectType(72,
			"MBUS_CLIENT");

	public static final ObjectType PUSH_SETUP = new ObjectType(40,
			"PUSH_SETUP");

	/*
	 * How the device manages incoming messages such as SMS, MMS, e-mail, etc.
	 * as well as the execution of dedicated actions based on the identification
	 * of the sender. The message service used is implicitly defined by the
	 * modem used.
	 */
	public static final ObjectType MESSAGE_HANDLER = new ObjectType(60,
			"MESSAGE_HANDLER");

	public static final ObjectType PARAMETER_MONITOR = new ObjectType(65,
			"PARAMETER_MONITOR");
	public static final ObjectType WIRELESS_MODE_Q_CHANNEL = new ObjectType(73,
			"WIRELESS_MODE_Q_CHANNEL");
	public static final ObjectType MBUS_MASTER_PORT_SETUP = new ObjectType(74,
			"MBUS_MASTER_PORT_SETUP");

	/**
	 * RegisterTable stores identical attributes of objects, in a selected
	 * collection of objects. All the objects in the collection need to be of
	 * the same type. Also, the value in value groups A to D and F in their
	 * logical name = OBIS identification code needs to be identical.
	 * <p />
	 * Clause 5 determines the possible values in value group E, as a table,
	 * where header = the common part, and each cell = a possible E value, of
	 * the OBIS code.
	 */
	public static final ObjectType REGISTER_TABLE = new ObjectType(61,
			"REGISTER_TABLE");

	/**
	 * Configure the behavior of a ZigBee PRO device on joining or loss of
	 * connection to the network.
	 */
	public static final ObjectType ZIG_BEE_SAS_JOIN = new ObjectType(102,
			"ZIG_BEE_SAS_JOIN");

	/**
	 * Configure a ZigBee PRO device with information necessary to create or
	 * join the network.
	 */
	public static final ObjectType ZIG_BEE_SAS_STARTUP = new ObjectType(101,
			"ZIG_BEE_SAS_STARTUP");

	/**
	 * SapAssigment stores information of assignment of the logical devices to
	 * their SAP = Service Access Points.
	 */
	public static final ObjectType SAP_ASSIGNMENT = new ObjectType(17,
			"SAP_ASSIGNMENT");

	/*
	 * Instances of the Image transfer IC model the mechanism of transferring
	 * binary files, called firmware Images to COSEM servers.
	 */
	public static final ObjectType IMAGE_TRANSFER = new ObjectType(18,
			"IMAGE_TRANSFER");

	/**
	 * To handle time and date driven actions, use Schedule, with an object of
	 * type SpecialDaysTable.
	 */
	public static final ObjectType SCHEDULE = new ObjectType(10, "SCHEDULE");

	/**
	 * To trigger a set of actions with an execute method, use object type
	 * ScriptTable. Each table entry = script includes a unique identifier, and
	 * a set of action specifications, which either execute a method, or modify
	 * the object attributes, within the logical device. The script can be
	 * triggered by other objects = within the same logical device, or from the
	 * outside.
	 */
	public static final ObjectType SCRIPT_TABLE = new ObjectType(9,
			"SCRIPT_TABLE");

	/**
	 * To determine the SMTP protocol settings, use the SmtpSetup object.
	 */
	public static final ObjectType SMTP_SETUP = new ObjectType(2, "SMTP_SETUP");

	/**
	 * With SpecialDaysTable you can determine dates to override a preset
	 * behaviour, for specific days = data item day_id. SpecialDaysTable works
	 * together with objects of Schedule, or Activity Calendar.
	 */
	public static final ObjectType SPECIAL_DAYS_TABLE = new ObjectType(11,
			"SPECIAL_DAYS_TABLE");

	/**
	 * StatusMapping object stores status words with mapping. Each bit in the
	 * status word is mapped to position = s in referencing status table.
	 */
	public static final ObjectType STATUS_MAPPING = new ObjectType(63,
			"STATUS_MAPPING");

	public static final ObjectType SECURITY_SETUP = new ObjectType(64,
			"SECURITY_SETUP");

	/**
	 * To determine Internet TCP/UDP protocol settings, use the TcpUdpSetup
	 * object.
	 */
	public static final ObjectType TCP_UDP_SETUP = new ObjectType(41,
			"TCP_UDP_SETUP");

	/**
	 * Configure the fragmentation feature of ZigBee PRO transport layer.
	 */
	public static final ObjectType ZIG_BEE_SAS_APS_FRAGMENTATION = new ObjectType(
			103, "ZIG_BEE_SAS_APS_FRAGMENTATION");

	/**
	 * In an object of type UtilityTables each "Table" = ANSI C12.19:1997 table
	 * data is represented as an instance, and identified by its logical name.
	 */
	public static final ObjectType UTILITY_TABLES = new ObjectType(26,
			"UTILITY_TABLES");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (ObjectType.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private ObjectType(int value, String name) {
		this.value = value;
		this.name = name;
		getMappings().put(new Integer(value), this);
	}

	/*
	 * Get integer value for enum.
	 */
	final public int getValue() {
		return value;
	}

	/*
	 * Convert integer for enum value.
	 */
	public static ObjectType forValue(int value) {
		return (ObjectType) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}