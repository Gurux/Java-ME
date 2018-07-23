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

package gurux.dlms.manufacturersettings;

import java.util.Enumeration;
import java.util.Vector;

import gurux.dlms.enums.Authentication;

public class GXManufacturer {
    private InactivityMode inactivityMode = InactivityMode.KEEPALIVE;
    private boolean useIEC47;
    private boolean forceInactivity;
    private boolean useLogicalNameReferencing;
    private String identification;
    private GXObisCodeCollection obisCodes;
    private String name;
    private Vector settings;
    private Vector serverSettings;
    private int keepAliveInterval;
    private StartProtocolType startProtocol = StartProtocolType.IEC;

    /**
     * Constructor.
     */
    public GXManufacturer() {
        setInactivityMode(InactivityMode.KEEPALIVE);
        setStartProtocol(StartProtocolType.IEC);
        obisCodes = new GXObisCodeCollection();
        settings = new Vector();
        serverSettings = new Vector();
        setKeepAliveInterval(40000);
    }

    /**
     * Manufacturer Identification. Device returns manufacturer ID when
     * connection to the meter is made.
     * 
     * @return Manufacturer Identification.
     */
    public final String getIdentification() {
        return identification;
    }

    /**
     * @param value
     *            Manufacturer Identification.
     */
    public final void setIdentification(final String value) {
        identification = value;
    }

    /**
     * @return Real name of the manufacturer.
     */
    public final String getName() {
        return name;
    }

    /**
     * @param value
     *            Real name of the manufacturer.
     */
    public final void setName(final String value) {
        name = value;
    }

    /**
     * @return Is Logical name referencing used.
     */
    public final boolean getUseLogicalNameReferencing() {
        return useLogicalNameReferencing;
    }

    /**
     * @param value
     *            Is Logical name referencing used.
     */
    public final void setUseLogicalNameReferencing(final boolean value) {
        useLogicalNameReferencing = value;
    }

    /**
     * @return Is Keep alive message used.
     */
    public final InactivityMode getInactivityMode() {
        return inactivityMode;
    }

    /**
     * @param value
     *            Is Keep alive message used.
     */
    public final void setInactivityMode(final InactivityMode value) {
        inactivityMode = value;
    }

    /**
     * @return Is Keep alive message forced for network connection as well.
     */
    public final boolean getForceInactivity() {
        return forceInactivity;
    }

    /**
     * @param value
     *            Is Keep alive message forced for network connection as well.
     */
    public final void setForceInactivity(final boolean value) {
        forceInactivity = value;
    }

    /**
     * @return Collection of custom OBIS codes.
     */
    public final GXObisCodeCollection getObisCodes() {
        return obisCodes;
    }

    /**
     * @return Is IEC 62056-47 supported.
     */
    public final boolean getUseIEC47() {
        return useIEC47;
    }

    /**
     * @param value
     *            Is IEC 62056-47 supported.
     */
    public final void setUseIEC47(final boolean value) {
        useIEC47 = value;
    }

    /**
     * @return Start protocol.
     */
    public final StartProtocolType getStartProtocol() {
        return startProtocol;
    }

    /**
     * @param value
     *            Start protocol.
     */
    public final void setStartProtocol(final StartProtocolType value) {
        startProtocol = value;
    }

    /**
     * @return Keep Alive interval.
     */
    public final int getKeepAliveInterval() {
        return keepAliveInterval;
    }

    /**
     * @param value
     *            Keep Alive interval.
     */
    public final void setKeepAliveInterval(final int value) {
        keepAliveInterval = value;
    }

    /**
     * @return Initialize settings.
     */
    public final Vector getSettings() {
        return settings;
    }

    /**
     * @param value
     *            Initialize settings.
     */
    public final void
            setSettings(final Vector value) {
        settings = value;
    }

    /**
     * @return Server settings
     */
    public final Vector getServerSettings() {
        return serverSettings;
    }

    public final GXServerAddress getServer(final HDLCAddressType type) {
    	Enumeration iterator = serverSettings .elements();
        while (iterator.hasMoreElements()) {
        	GXServerAddress it = (GXServerAddress)iterator.nextElement();        	
        	if (it.getHDLCAddress() == type) {
                return it;
            }
        }
        return null;
    }

    /**
     * Get authentication settings.
     * 
     * @param authentication
     *            Authentication type.
     * @return Authentication settings.
     */
    public final GXAuthentication
            getAuthentication(final Authentication authentication) {
    	Enumeration iterator = settings .elements();
        while (iterator.hasMoreElements()) {
        	GXAuthentication it = (GXAuthentication)iterator.nextElement();
            if (it.getType() == authentication) {
                return it;
            }
        }
        return null;
    }
}