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

public class Unit {
	private final String name;
	private int value;
	private static java.util.Hashtable mappings;

	public static final Unit NONE = new Unit(0, "NONE");
	public static final Unit YEAR = new Unit(1, "YEAR");
	public static final Unit MONTH = new Unit(2, "MONTH");
	public static final Unit WEEK = new Unit(3, "WEEK");
	public static final Unit DAY = new Unit(4, "DAY");
	public static final Unit HOUR = new Unit(5, "HOUR");
	public static final Unit MINUTE = new Unit(6, "MINUTE");
	public static final Unit SECOND = new Unit(7, "SECOND");
	public static final Unit PHASE_ANGLEGEGREE = new Unit(8,
			"PHASE_ANGLEGEGREE"); // Phase angle degree rad*180/p
	public static final Unit TEMPERATURE = new Unit(9, "TEMPERATURE"); // Temperature
																		// T
																		// degree
																		// centigrade
	public static final Unit LOCAL_CURRENCY = new Unit(10, "LOCAL_CURRENCY"); // Local
																				// currency
	public static final Unit LENGTH = new Unit(11, "LENGTH"); // Length l meter
																// m
	public static final Unit SPEED = new Unit(12, "SPEED"); // "Speed v m/s
	public static final Unit VOLUME_CUBIC_METER = new Unit(13,
			"VOLUME_CUBIC_METER"); // Volume V m3
	public static final Unit CORRECTED_VOLUME = new Unit(14,
			"CORRECTED_VOLUME"); // Corrected volume m3
	public static final Unit VOLUME_FLUX_HOUR = new Unit(15,
			"VOLUME_FLUX_HOUR"); // Volume flux m3/60*60s
	public static final Unit CORRECTED_VOLUME_FLUX_HOUR = new Unit(16,
			"CORRECTED_VOLUME_FLUX_HOUR"); // Corrected volume flux m3/60*60s
	public static final Unit VOLUME_FLUXDAY = new Unit(17, "VOLUME_FLUXDAY"); // Volume
																				// flux
																				// m3/24*60*60s
	public static final Unit CORRECTE_VOLUME_FLUX_DAY = new Unit(18,
			"CORRECTE_VOLUME_FLUX_DAY"); // Corrected volume flux m3/24*60*60s
	public static final Unit VOLUME_LITER = new Unit(19, "VOLUME_LITER"); // Volume
																			// 10-3
																			// m3
	public static final Unit MASS_KG = new Unit(20, "MASS_KG"); // Mass m
																// kilogram kg
	public static final Unit FORCE = new Unit(21, "FORCE"); // return "Force F
															// newton N
	public static final Unit ENERGY = new Unit(22, "ENERGY"); // Energy
																// newtonmeter J
																// = Nm = Ws
	public static final Unit PRESSURE_PASCAL = new Unit(23, "PRESSURE_PASCAL"); // Pressure
																				// p
																				// pascal
																				// N/m2
	public static final Unit PRESSURE_BAR = new Unit(24, "PRESSURE_BAR"); // Pressure
																			// p
																			// bar
																			// 10-5
																			// N/m2
	public static final Unit ENERGY_JOULE = new Unit(25, "ENERGY_JOULE"); // Energy
																			// joule
																			// J
																			// =
																			// Nm
																			// =
																			// Ws
	public static final Unit THERMAL_POWER = new Unit(26, "THERMAL_POWER"); // Thermal
																			// power
																			// J/60*60s
	public static final Unit ACTIVE_POWER = new Unit(27, "ACTIVE_POWER"); // Active
																			// power
																			// P
																			// watt
																			// W
																			// =
																			// J/s
	public static final Unit APPARENT_POWER = new Unit(28, "APPARENT_POWER"); // Apparent
																				// power
																				// S
	public static final Unit REACTIVE_POWER = new Unit(29, "REACTIVE_POWER");// Reactive
																				// power
																				// Q
	public static final Unit ACTIVE_ENERGY = new Unit(30, "ACTIVE_ENERGY"); // Active
																			// energy
																			// W*60*60s
	public static final Unit APPARENT_ENERGY = new Unit(31, "APPARENT_ENERGY"); // Apparent
																				// energy
																				// VA*60*60s
	public static final Unit REACTIVE_ENERGY = new Unit(32, "REACTIVE_ENERGY"); // Reactive
																				// energy
																				// var*60*60s
	public static final Unit CURRENT = new Unit(33, "CURRENT"); // Current I
																// ampere A
	public static final Unit ELECTRICAL_CHARGE = new Unit(34,
			"ELECTRICAL_CHARGE"); // Electrical charge Q coulomb C = As
	public static final Unit VOLTAGE = new Unit(35, "VOLTAGE"); // Voltage
	public static final Unit ELECTRICAL_FIELD_STRENGTH = new Unit(36,
			"ELECTRICAL_FIELD_STRENGTH"); // Electrical field strength E V/m
	public static final Unit CAPACITY = new Unit(37, "CAPACITY"); // Capacity C
																	// farad C/V
																	// = As/V
	public static final Unit RESISTANCE = new Unit(38, "RESISTANCE"); // Resistance
																		// R ohm
																		// = V/A
	public static final Unit RESISTIVITY = new Unit(39, "RESISTIVITY"); // Resistivity
	public static final Unit MAGNETIC_FLUX = new Unit(40, "MAGNETIC_FLUX"); // Magnetic
																			// flux
																			// F
																			// weber
																			// Wb
																			// =
																			// Vs
	public static final Unit INDUCTION = new Unit(41, "INDUCTION"); // Induction
																	// T tesla
																	// Wb/m2
	public static final Unit MAGNETIC = new Unit(42, "MAGNETIC"); // Magnetic
																	// field
																	// strength
																	// H A/m
	public static final Unit INDUCTIVITY = new Unit(43, "INDUCTIVITY"); // Inductivity
																		// L
																		// henry
																		// H =
																		// Wb/A
	public static final Unit FREQUENCY = new Unit(44, "FREQUENCY");// Frequency
																	// f
	public static final Unit ACTIVE = new Unit(45, "ACTIVE"); // Active energy
																// meter
																// constant 1/Wh
	public static final Unit REACTIVE = new Unit(46, "REACTIVE"); // Reactive
																	// energy
																	// meter
																	// constant
	public static final Unit APPARENT = new Unit(47, "APPARENT"); // Apparent
																	// energy
																	// meter
																	// constant
	public static final Unit V260 = new Unit(48, "V260"); // V260*60s
	public static final Unit A260 = new Unit(49, "A260"); // A260*60s
	public static final Unit MASS_KG_PER_SECOND = new Unit(50,
			"MASS_KG_PER_SECOND"); // Mass flux kg/s
	/**
	 * Unit is Conductance siemens 1/ohm.
	 */
	public static final Unit CONDUCTANCE = new Unit(51, "CONDUCTANCE");
	/**
	 * Temperature in Kelvin.
	 */
	public static final Unit KELVIN = new Unit(52, "KELVIN");
	/**
	 * 1/(V2h) RU2h , volt-squared hour meter constant or pulse value.
	 */
	public static final Unit V2H = new Unit(53, "V2H");
	/**
	 * 1/(A2h) RI2h , ampere-squared hour meter constant or pulse value.
	 */
	public static final Unit A2H = new Unit(54, "A2H");
	/**
	 * 1/m3 RV , meter constant or pulse value (volume).
	 */
	public static final Unit CUBIC_METER_RV = new Unit(55, "CUBIC_METER_RV");
	/**
	 * Percentage
	 */
	public static final Unit PERCENTAGE = new Unit(56, "PERCENTAGE");
	// Ah ampere-hours
	public static final Unit AMPERE_HOURS = new Unit(57, "AMPERE_HOURS");
	/**
	 * Wh/m3 energy per volume 3,6*103 J/m3.
	 */
	public static final Unit ENERGY_PER_VOLUME = new Unit(60,
			"ENERGY_PER_VOLUME");
	/**
	 * J/m3 calorific value, wobbe.
	 */
	public static final Unit WOBBE = new Unit(61, "WOBBE");
	/**
	 * Mol % molar fraction of gas composition mole percent (Basic gas
	 * composition unit)
	 */
	public static final Unit MOLE_PERCENT = new Unit(62, "MOLE_PERCENT");
	/**
	 * g/m3 mass density, quantity of material.
	 */
	public static final Unit MASS_DENSITY = new Unit(63, "MASS_DENSITY");
	/**
	 * Dynamic viscosity Pascal second (Characteristic of gas stream).
	 */
	public static final Unit PASCAL_SECOND = new Unit(64, "PASCAL_SECOND");
	/**
	 * J/kg Specific energy NOTE The amount of energy per unit of mass of a
	 * substance Joule / kilogram m2 . kg . s -2 / kg = m2 . s –2
	 */
	public static final Unit JOULE_KILOGRAM = new Unit(65, "JOULE_KILOGRAM");
	/**
	 * dBm Signal strength (e.g. of GSM radio systems)
	 */
	public static final Unit SIGNAL_STRENGTH = new Unit(70, "SIGNAL_STRENGTH");
	/*
	 * Other Unit
	 */
	public static final Unit OTHER_UNIT = new Unit(254, "OTHER_UNIT");
	/*
	 * No Unit
	 */
	public static final Unit NO_UNIT = new Unit(255, "NO_UNIT");

	private static java.util.Hashtable getMappings() {
		if (mappings == null) {
			synchronized (Unit.class) {
				if (mappings == null) {
					mappings = new java.util.Hashtable();
				}
			}
		}
		return mappings;
	}

	private Unit(int value, String name) {
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
	public static Unit forValue(int value) {
		return (Unit) getMappings().get(new Integer(value));
	}

	public String toString() {
		return name;
	}
}