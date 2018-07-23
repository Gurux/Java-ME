//
// --------------------------------------------------------------------------
//  Gurux Ltd

package gurux.dlms.server.example;

import gurux.dlms.enums.InterfaceType;

/**
 * DLMS Server that uses Logical Name referencing with IEC 62056-46 Data link
 * layer using HDLC protocol. Example Iskraemeco and Actaris uses this.
 */
public class GXDLMSServerLN extends GXDLMSBase {
    public GXDLMSServerLN() {
        super(true, InterfaceType.HDLC);
    }
}