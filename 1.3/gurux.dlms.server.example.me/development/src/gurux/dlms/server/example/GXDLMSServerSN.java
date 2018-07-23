//
// --------------------------------------------------------------------------
//  Gurux Ltd

package gurux.dlms.server.example;

import gurux.dlms.enums.InterfaceType;

/**
 * DLMS Server that uses Short Name referencing with IEC 62056-46 Data link
 * layer using HDLC protocol. Example Landis+Gyr uses this.
 */
public class GXDLMSServerSN extends GXDLMSBase {
    public GXDLMSServerSN() {
        super(false, InterfaceType.HDLC);
    }
}