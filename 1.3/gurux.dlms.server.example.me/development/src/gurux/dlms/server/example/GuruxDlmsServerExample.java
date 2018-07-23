//
// --------------------------------------------------------------------------
//  Gurux Ltd

package gurux.dlms.server.example;

import java.io.IOException;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class GuruxDlmsServerExample extends MIDlet {
    GXDLMSServerSN SNServer;
    GXDLMSServerLN LNServer;
    GXDLMSServerSN_47 SN_47Server;
    GXDLMSServerLN_47 LN_47Server;

    public GuruxDlmsServerExample() {
        // TODO Auto-generated constructor stub
    }

    protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

    }

    protected void pauseApp() {

    }

    protected void startApp() throws MIDletStateChangeException {
        // Create servers and start listen events.
        try {
            ///////////////////////////////////////////////////////////////////////
            // Create Gurux DLMS server component for Short Name
            // and start listen events.
            SNServer = new GXDLMSServerSN();
            SNServer.Initialize(4060);
            System.out.println("Short Name DLMS Server in port 4060");
            ///////////////////////////////////////////////////////////////////////
            // Create Gurux DLMS server component for Logical Name
            // and start listen events.
            LNServer = new GXDLMSServerLN();
            LNServer.Initialize(4061);
            System.out.println("Logical Name DLMS Server in port 4061");
            ///////////////////////////////////////////////////////////////////////
            // Create Gurux DLMS server component for Short Name
            // and start listen events.
            SN_47Server = new GXDLMSServerSN_47();
            SN_47Server.Initialize(4062);
            System.out.println(
                    "Short Name DLMS Server with IEC 62056-47 in port 4062");
            ///////////////////////////////////////////////////////////////////////
            // Create Gurux DLMS server component for Logical Name
            // and start listen events.
            LN_47Server = new GXDLMSServerLN_47();
            LN_47Server.Initialize(4063);
            System.out.println(
                    "Logical Name DLMS Server with IEC 62056-47 in port 4063");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
