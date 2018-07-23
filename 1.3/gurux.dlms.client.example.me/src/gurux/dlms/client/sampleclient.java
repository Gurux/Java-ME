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
package gurux.dlms.client;

import gurux.dlms.enums.Authentication;
import gurux.dlms.enums.InterfaceType;
import gurux.dlms.secure.GXDLMSSecureClient;
import gurux.net.GXNet;
import gurux.net.enums.NetworkType;

public class sampleclient {

    public sampleclient() {

    }

    public static void main(String[] args) {
        // Connection settings.
        GXNet media = new GXNet(NetworkType.TCP, "localhost", 4060);
        // Reply wait time in ms.
        int waitTime = 5000;
        GXDLMSSecureClient cl = new GXDLMSSecureClient(false, 1, 16,
                Authentication.NONE, null, InterfaceType.HDLC);
        GXCommunicate com = new GXCommunicate(waitTime, cl, false, media);
        try {
            com.initializeConnection();
            com.readAllObjects();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                ///////////////////////////////////////////////////////////////
                // Disconnect.
                if (com != null) {
                    com.close();
                }
            } catch (Exception Ex2) {
                System.out.println(Ex2.toString());
            }
        }
        System.out.println("Done!");
    }
}
