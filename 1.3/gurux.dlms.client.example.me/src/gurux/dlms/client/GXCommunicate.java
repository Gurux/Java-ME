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

import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import gurux.common.GXCommon;
import gurux.common.IGXMedia;
import gurux.common.ReceiveParameters;
import gurux.dlms.GXDLMSConverter;
import gurux.dlms.GXDLMSException;
import gurux.dlms.GXReplyData;
import gurux.dlms.GXSimpleEntry;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ErrorCode;
import gurux.dlms.enums.InterfaceType;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.enums.RequestTypes;
import gurux.dlms.objects.GXDLMSDemandRegister;
import gurux.dlms.objects.GXDLMSObject;
import gurux.dlms.objects.GXDLMSObjectCollection;
import gurux.dlms.objects.GXDLMSProfileGeneric;
import gurux.dlms.objects.GXDLMSRegister;
import gurux.dlms.objects.IGXDLMSBase;
import gurux.dlms.secure.GXDLMSSecureClient;
import gurux.io.Parity;
import gurux.io.StopBits;
import gurux.net.GXNet;
import gurux.serial.GXSerial;

public class GXCommunicate {
    IGXMedia Media;
    public boolean Trace = false;
    public GXDLMSSecureClient dlms;
    boolean iec;
    java.nio.ByteBuffer replyBuff;
    int WaitTime = 10000;

    static void trace(String text) {
        System.out.print(text);
    }

    static void traceLn(String text) {
        System.out.print(text + "\r\n");
    }

    public GXCommunicate(int waitTime, GXDLMSSecureClient dlms, boolean iec,
            IGXMedia media) {
        Media = media;
        Media.setSynchronous(true);
        WaitTime = waitTime;
        this.dlms = dlms;
        this.iec = iec;
        System.out.println("Authentication: " + dlms.getAuthentication());
        System.out.println("ClientAddress: 0x"
                + Integer.toHexString(dlms.getClientAddress()));
        System.out.println("ServerAddress: 0x"
                + Integer.toHexString(dlms.getServerAddress()));
        if (dlms.getInterfaceType() == InterfaceType.WRAPPER) {
            replyBuff = java.nio.ByteBuffer.allocateDirect(8 + 1024);
        } else {
            replyBuff = java.nio.ByteBuffer.allocateDirect(100);
        }
    }

    void close() throws Exception {
        if (Media != null) {
            System.out.println("DisconnectRequest");
            GXReplyData reply = new GXReplyData();
            readDLMSPacket(dlms.disconnectRequest(), reply);
            Media.close();
        }
    }

    String now() {
        return java.util.Calendar.getInstance().getTime().toString();
    }

    void writeTrace(String line) {
        if (Trace) {
            System.out.println(line);
        }
    }

    public void readDLMSPacket(byte[][] data) throws Exception {
        GXReplyData reply = new GXReplyData();   
        for (int pos = 0; pos != data.length; ++pos) {
            reply.clear();
            readDLMSPacket(data[pos], reply);
        }
    }

    /**
     * Read DLMS Data from the device. If access is denied return null.
     */
    public void readDLMSPacket(byte[] data, GXReplyData reply)
            throws Exception {
        if (data == null || data.length == 0) {
            return;
        }
        reply.setError((short) 0);
        Object eop = new Byte((byte) 0x7E);
        // In network connection terminator is not used.
        if (dlms.getInterfaceType() == InterfaceType.WRAPPER
                && Media instanceof GXNet) {
            eop = null;
        }
        int pos = 0;
        boolean succeeded = false;
        ReceiveParameters p =
                new ReceiveParameters();
        p.setEop(eop);
        p.setCount(5);
        p.setWaitTime(WaitTime);
        while (!succeeded) {
            writeTrace("<- " + now() + "\t" + GXCommon.bytesToHex(data));
            Media.send(data, null);
            if (p.getEop() == null) {
                p.setCount(1);
            }
            succeeded = Media.receive(p);
            if (!succeeded) {
                // Try to read again...
                if (pos++ == 3) {
                    throw new RuntimeException(
                            "Failed to receive reply from the device in given time.");
                }
                System.out.println(
                        "Data send failed. Try to resend " + pos + "/3");
            }
        }
        // Loop until whole DLMS packet is received.
        try {
            while (!dlms.getData(p.getReply(), reply)) {
                if (p.getEop() == null) {
                    p.setCount(1);
                }
                if (!Media.receive(p)) {
                    // If echo.
                    if (reply.isEcho()) {
                        Media.send(data, null);
                    }
                    // Try to read again...
                    if (++pos == 3) {
                        throw new Exception(
                                "Failed to receive reply from the device in given time.");
                    }
                    System.out.println(
                            "Data send failed. Try to resend " + pos + "/3");
                }
            }
        } catch (Exception e) {
            writeTrace(
                    "-> " + now() + "\t" + GXCommon.bytesToHex(p.getReply()));
            throw e;
        }
        writeTrace("-> " + now() + "\t" + GXCommon.bytesToHex(p.getReply()));
        if (reply.getError() != 0) {
            if (reply.getError() == ErrorCode.REJECTED.getValue()) {
                Thread.sleep(1000);
                readDLMSPacket(data, reply);
            } else {
                throw new GXDLMSException(reply.getError());
            }
        }
    }

    void readDataBlock(byte[][] data, GXReplyData reply) throws Exception {
        for (int pos = 0; pos != data.length;++pos) {
            reply.clear();
            readDataBlock(data[pos], reply);
        }
    }

    /**
     * Reads next data block.
     * 
     * @param data
     * @return
     * @throws Exception
     */
    void readDataBlock(byte[] data, GXReplyData reply) throws Exception {
        RequestTypes rt;
        if (data.length != 0) {
            readDLMSPacket(data, reply);
            while (reply.isMoreData()) {
                rt = reply.getMoreData();
                data = dlms.receiverReady(rt);
                readDLMSPacket(data, reply);
            }
        }
    }

    /**
     * Initializes connection.
     * 
     * @param port
     * @throws InterruptedException
     * @throws Exception
     */
    void initializeConnection() throws Exception, InterruptedException {
        Media.open();
        if (Media instanceof GXSerial) {
            GXSerial serial = (GXSerial) Media;
            if (iec) {
                ReceiveParameters p =
                        new ReceiveParameters();
                p.setAllData(false);
                p.setEop(new Byte((byte) '\n'));
                p.setWaitTime(WaitTime);
                String data;
                String replyStr;

                data = "/?!\r\n";
                writeTrace("<- " + now() + "\t"
                        + GXCommon.bytesToHex(data.getBytes()));
                Media.send(data, null);
                if (!Media.receive(p)) {
                    throw new Exception("Invalid meter type.");
                }
                writeTrace("->" + now() + "\t"
                        + GXCommon.bytesToHex(p.getReply()));
                // If echo is used.
                replyStr = new String(p.getReply());
                if (data.equals(replyStr)) {
                    p.setReply(null);
                    if (!Media.receive(p)) {
                        throw new Exception("Invalid meter type.");
                    }
                    writeTrace("-> " + now() + "\t"
                            + GXCommon.bytesToHex(p.getReply()));
                    replyStr = new String(p.getReply());
                }

                if (replyStr.length() == 0 || replyStr.charAt(0) != '/') {
                    throw new Exception("Invalid responce.");
                }
                String manufactureID = replyStr.substring(1, 4);
                System.out.println("Manufacturer:" + manufactureID);
                int bitrate = 0;
                char baudrate = replyStr.charAt(4);
                switch (baudrate) {
                case '0':
                    bitrate = 300;
                    break;
                case '1':
                    bitrate = 600;
                    break;
                case '2':
                    bitrate = 1200;
                    break;
                case '3':
                    bitrate = 2400;
                    break;
                case '4':
                    bitrate = 4800;
                    break;
                case '5':
                    bitrate = 9600;
                    break;
                case '6':
                    bitrate = 19200;
                    break;
                default:
                    throw new Exception("Unknown baud rate.");
                }
                System.out.println("Bitrate is : " + bitrate);
                // Send ACK
                // Send Protocol control character
                byte controlCharacter = (byte) '2';// "2" HDLC protocol
                // procedure (Mode E)
                // Send Baudrate character
                // Mode control character
                byte ModeControlCharacter = (byte) '2';// "2" //(HDLC protocol
                // procedure) (Binary
                // mode)
                // Set mode E.
                byte[] tmp = new byte[] { 0x06, controlCharacter,
                        (byte) baudrate, ModeControlCharacter, 13, 10 };
                p.setReply(null);

                Media.send(tmp, null);
                writeTrace("<- " + now() + "\t" + GXCommon.bytesToHex(tmp));
                p.setWaitTime(100);
                if (Media.receive(p)) {
                    writeTrace("-> " + now() + "\t"
                            + GXCommon.bytesToHex(p.getReply()));
                }
                Media.close();
                // This sleep make sure that all meters can be read.
                Thread.sleep(400);
                serial.setDataBits(8);
                serial.setParity(Parity.NONE);
                serial.setStopBits(StopBits.ONE);
                serial.setBaudRate(bitrate);
                Media.open();
                Thread.sleep(400);
            }
        }

        GXReplyData reply = new GXReplyData();
        byte[] data = dlms.snrmRequest();
        if (data.length != 0) {
            readDLMSPacket(data, reply);
            // Has server accepted client.
            dlms.parseUAResponse(reply.getData());

            // Allocate buffer to same size as transmit buffer of the meter.
            // Size of replyBuff is payload and frame (Bop, EOP, crc).
            int size = (int) ((((Number) dlms.getLimits().getMaxInfoTX())
                    .intValue() & 0xFFFFFFFFL) + 40);
            replyBuff = java.nio.ByteBuffer.allocateDirect(size);
        }
        reply.clear();
        // Generate AARQ request.
        // Split requests to multiple packets if needed.
        // If password is used all data might not fit to one packet.
        byte[][] buff = dlms.aarqRequest();
        for (int pos = 0; pos != buff.length;++pos ) {
            readDLMSPacket(buff[pos], reply);
        }
        // Parse reply.
        dlms.parseAareResponse(reply.getData());
        reply.clear();

        // Get challenge Is HLS authentication is used.
        if (dlms.getIsAuthenticationRequired()) {
            buff = dlms.getApplicationAssociationRequest();
            for (int pos = 0; pos != buff.length;++pos ) {
                readDLMSPacket(buff[pos], reply);
            }
            dlms.parseApplicationAssociationResponse(reply.getData());
        }
    }

    /**
     * Reads selected DLMS object with selected attribute index.
     * 
     * @param item
     * @param attributeIndex
     * @return
     * @throws Exception
     */
    public Object readObject(GXDLMSObject item, int attributeIndex)
            throws Exception {
        byte[] data = dlms.read(item.getName(), item.getObjectType(),
                attributeIndex)[0];
        GXReplyData reply = new GXReplyData();

        readDataBlock(data, reply);
        // Update data type on read.
        if (item.getDataType(attributeIndex) == DataType.NONE) {
            item.setDataType(attributeIndex, reply.getValueType());
        }
        return dlms.updateValue(item, attributeIndex, reply.getValue());
    }

    /*
     * /// Read list of attributes.
     */
    public void readList(Vector list)
            throws Exception {
        byte[][] data = dlms.readList(list);
        GXReplyData reply = new GXReplyData();
        readDataBlock(data, reply);
        dlms.updateValues(list, (Object[]) reply.getValue());
    }

    /**
     * Writes value to DLMS object with selected attribute index.
     * 
     * @param item
     * @param attributeIndex
     * @throws Exception
     */
    public void writeObject(GXDLMSObject item, int attributeIndex)
            throws Exception {
        byte[][] data = dlms.write(item, attributeIndex);
        readDLMSPacket(data);
    }

    /*
     * Returns columns of profile Generic.
     */
    public Vector
            GetColumns(GXDLMSProfileGeneric pg) throws Exception {
        Object entries = readObject(pg, 7);
        System.out.println("Reading Profile Generic: " + pg.getLogicalName()
                + " " + pg.getDescription() + " entries:" + entries.toString());
        GXReplyData reply = new GXReplyData();
        byte[] data = dlms.read(pg.getName(), pg.getObjectType(), 3)[0];
        readDataBlock(data, reply);
        dlms.updateValue((GXDLMSObject) pg, 3, reply.getValue());
        return pg.getCaptureObjects();
    }

    /**
     * Read Profile Generic's data by entry start and count.
     * 
     * @param pg
     * @param index
     * @param count
     * @return
     * @throws Exception
     */
    public Object[] readRowsByEntry(GXDLMSProfileGeneric pg, int index,
            int count) throws Exception {
        byte[][] data = dlms.readRowsByEntry(pg, index, count);
        GXReplyData reply = new GXReplyData();
        readDataBlock(data, reply);
        return (Object[]) dlms.updateValue(pg, 2, reply.getValue());
    }

    /**
     * Read Profile Generic's data by range (start and end time).
     * 
     * @param pg
     * @param sortedItem
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public Object[] readRowsByRange(final GXDLMSProfileGeneric pg,
            final Date start, final Date end) throws Exception {
        GXReplyData reply = new GXReplyData();
        byte[][] data = dlms.readRowsByRange(pg, start, end);
        readDataBlock(data, reply);
        return (Object[]) dlms.updateValue(pg, 2, reply.getValue());
    }

    /*
     * Read Scalers and units from the register objects.
     */
    void readScalerAndUnits(final GXDLMSObjectCollection objects) {
        GXDLMSObjectCollection objs = objects.getObjects(new ObjectType[] {
                ObjectType.REGISTER, ObjectType.DEMAND_REGISTER,
                ObjectType.EXTENDED_REGISTER });

        try {
            Vector list =
                    new Vector();
            Enumeration iterator = objs.elements();
            while (iterator.hasMoreElements()) {
                GXDLMSObject it = (GXDLMSObject) iterator.nextElement();
              
                if (it instanceof GXDLMSRegister) {
                    list.add(new GXSimpleEntry(it, new Integer(3)));
                }
                if (it instanceof GXDLMSDemandRegister) {
                    list.add(new GXSimpleEntry(it, new Integer(4)));
                }
            }
            readList(list);
        } catch (Exception ex) {
            Enumeration iterator = objs.elements();
            while (iterator.hasMoreElements()) {
                GXDLMSObject it = (GXDLMSObject) iterator.nextElement();
                                try {
                    if (it instanceof GXDLMSRegister) {
                        readObject(it, 3);
                    } else if (it instanceof GXDLMSDemandRegister) {
                        readObject(it, 4);
                    }
                } catch (Exception e) {
                    traceLn("Err! Failed to read scaler and unit value: "
                            + e.getMessage());
                    // Continue reading.
                }
            }
        }
    }

    /*
     * Read profile generic columns from the meter.
     */
    void readProfileGenericColumns(final GXDLMSObjectCollection objects) {
        GXDLMSObjectCollection profileGenerics =
                objects.getObjects(ObjectType.PROFILE_GENERIC);
        Enumeration iterator = profileGenerics.elements();
        while (iterator.hasMoreElements()) {
            GXDLMSProfileGeneric pg = (GXDLMSProfileGeneric) iterator.nextElement();
            traceLn("Profile Generic " + pg.getName() + " Columns:");
            // Read columns.
            try {
                readObject(pg, 3);
                boolean first = true;
                StringBuilder sb = new StringBuilder();
                Enumeration iterator2 =  pg
                        .getCaptureObjects().elements();
                while (iterator2.hasMoreElements()) {
                    GXSimpleEntry col = (GXSimpleEntry) iterator2.nextElement();
                    if (!first) {
                        sb.append(" | ");
                    }
                    sb.append(((GXDLMSObject)col.getKey()).getName());
                    sb.append(" ");
                    String desc = ((GXDLMSObject)col.getKey()).getDescription();
                    if (desc != null) {
                        sb.append(desc);
                    }
                    first = false;
                }
                traceLn(sb.toString());
            } catch (Exception ex) {
                traceLn("Err! Failed to read columns:" + ex.getMessage());
                // Continue reading.
            }
        }
    }

    /**
     * Read all data from the meter except profile generic (Historical) data.
     */
    void readValues(final GXDLMSObjectCollection objects) {
        Enumeration iterator = objects.elements();
        while (iterator.hasMoreElements()) {
            GXDLMSObject it = (GXDLMSObject) iterator.nextElement();
            if (!(it instanceof IGXDLMSBase)) {
                // If interface is not implemented.
                System.out.println(
                        "Unknown Interface: " + it.getObjectType().toString());
                continue;
            }

            if (it instanceof GXDLMSProfileGeneric) {
                // Profile generic are read later
                // because it might take so long time
                // and this is only a example.
                continue;
            }
            traceLn("-------- Reading " + it.getClass().getName() + " "
                    + it.getName().toString() + " " + it.getDescription());
            int[] arr2 = ((IGXDLMSBase) it).getAttributeIndexToRead();
           for (int a = 0; a != arr2.length; ++a) {
               int pos = arr2[a];
                try {
                    
                    Object val = readObject(it, pos);
                    if (val instanceof byte[]) {
                        val = GXCommon.bytesToHex((byte[]) val);
                    } else if (val instanceof Double) {
                        val = String.valueOf(val);
                    } else if (val instanceof Object[]) {
                        String str = "";
                        Object[] arr = (Object[]) val;
                        for (int pos2 = 0; pos2 != arr.length; ++pos2) {
                            if (!str.equals("")) {
                                str += ", ";
                            }
                            Object tmp = arr[pos2];
                            if (tmp instanceof byte[]) {
                                str += GXCommon.bytesToHex((byte[]) tmp);
                            } else {
                                str += String.valueOf(tmp);
                            }
                        }
                        val = str;
                    }
                    traceLn("Index: " + pos + " Value: " + String.valueOf(val));
                } catch (Exception ex) {
                    traceLn("Error! Index: " + pos + " " + ex.getMessage());
                    // Continue reading.
                }
            }
        }
    }

    /**
     * Read profile generic (Historical) data.
     */
    void readProfileGenerics(final GXDLMSObjectCollection objects)
            throws Exception {
        Object[] cells;
        GXDLMSObjectCollection profileGenerics =
                objects.getObjects(ObjectType.PROFILE_GENERIC);
        Enumeration iterator =  profileGenerics.elements();
        while (iterator.hasMoreElements()) {
            GXDLMSObject it = (GXDLMSObject) iterator.nextElement();
            traceLn("-------- Reading " + it.getClass().getName() + " "
                    + it.getName().toString() + " " + it.getDescription());

            long entriesInUse = ((Number) readObject(it, 7)).longValue();
            long entries = ((Number) readObject(it, 8)).longValue();
            traceLn("Entries: " + String.valueOf(entriesInUse) + "/"
                    + String.valueOf(entries));
            GXDLMSProfileGeneric pg = (GXDLMSProfileGeneric) it;
            // If there are no columns.
            if (entriesInUse == 0 || pg.getCaptureObjects().size() == 0) {
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // Read first item.
            try {
                cells = readRowsByEntry(pg, 1, 1);
                for (int c = 0; c != cells.length;++c) {
                    Object[] rows = (Object[]) cells[c];
                    for (int r = 0; r != rows.length;++r) {
                        Object cell = rows[r];     
                        if (cell instanceof byte[]) {
                            trace(GXCommon.bytesToHex((byte[]) cell) + " | ");
                        } else {
                            trace(cell + " | ");
                        }
                    }
                    traceLn("");
                }
            } catch (Exception ex) {
                traceLn("Error! Failed to read first row: " + ex.getMessage());
                // Continue reading if device returns access denied error.
            }
            ///////////////////////////////////////////////////////////////////
            // Read last day.
            try {
                java.util.Calendar start = java.util.Calendar
                        .getInstance(java.util.TimeZone.getTimeZone("UTC"));
                start.set(java.util.Calendar.HOUR_OF_DAY, 0); // set hour to
                                                              // midnight
                start.set(java.util.Calendar.MINUTE, 0); // set minute in
                                                         // hour
                start.set(java.util.Calendar.SECOND, 0); // set second in
                                                         // minute
                start.set(java.util.Calendar.MILLISECOND, 0);
                start.add(java.util.Calendar.DATE, -1);

                java.util.Calendar end = java.util.Calendar.getInstance();
                end.set(java.util.Calendar.MINUTE, 0); // set minute in hour
                end.set(java.util.Calendar.SECOND, 0); // set second in
                                                       // minute
                end.set(java.util.Calendar.MILLISECOND, 0);
                cells = readRowsByRange((GXDLMSProfileGeneric) it,
                        start.getTime(), end.getTime());
                for (int c = 0; c != cells.length;++c) {
                    Object[] rows = (Object[]) cells[c];
                    for (int r = 0; r != rows.length;++r) {
                        Object cell = rows[r];     
                        if (cell instanceof byte[]) {
                            System.out.print(
                                    GXCommon.bytesToHex((byte[]) cell) + " | ");
                        } else {
                            trace(cell + " | ");
                        }
                    }
                    traceLn("");
                }
            } catch (Exception ex) {
                traceLn("Error! Failed to read last day: " + ex.getMessage());
                // Continue reading if device returns access denied error.
            }
        }
    }

    /*
     * Read all objects from the meter. This is only example. Usually there is
     * no need to read all data from the meter.
     */
    void readAllObjects() throws Exception {
        System.out.println("Reading association view");
        GXReplyData reply = new GXReplyData();
        // Get Association view from the meter.
        readDataBlock(dlms.getObjectsRequest(), reply);
        GXDLMSObjectCollection objects =
                dlms.parseObjects(reply.getData(), true);
        // Get description of the objects.
        GXDLMSConverter converter = new GXDLMSConverter();
        converter.updateOBISCodeInformation(objects);

        // Read Scalers and units from the register objects.
        readScalerAndUnits(objects);
        // Read Profile Generic columns.
        readProfileGenericColumns(objects);
        // Read all attributes from all objects.
        readValues(objects);
        // Read historical data.
        readProfileGenerics(objects);
    }
}