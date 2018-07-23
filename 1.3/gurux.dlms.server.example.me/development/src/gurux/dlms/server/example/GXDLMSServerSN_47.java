//
// --------------------------------------------------------------------------
//  Gurux Ltd

package gurux.dlms.server.example;

import gurux.dlms.enums.InterfaceType;

/**
 * DLMS Server that uses Short Same referencing with IEC 62056-47 COSEM
 * transport layers for IPv4 networks. Example Iskraemeco uses this. Note! For
 * serial port communication is used GXDLMSServerSN.
 */
public class GXDLMSServerSN_47 extends GXDLMSBase {
    public GXDLMSServerSN_47() {
        super(false, InterfaceType.WRAPPER);
    }
}