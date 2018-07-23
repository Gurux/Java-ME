//
// --------------------------------------------------------------------------
//  Gurux Ltd


package gurux.dlms.server.example;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

import gurux.common.IGXMediaListener;
import gurux.common.MediaStateEventArgs;
import gurux.common.PropertyChangedEventArgs;
import gurux.common.ReceiveEventArgs;
import gurux.common.TraceEventArgs;
import gurux.common.enums.TraceLevel;
import gurux.dlms.GXDLMSConnectionEventArgs;
import gurux.dlms.GXDate;
import gurux.dlms.GXDateTime;
import gurux.dlms.GXSimpleEntry;
import gurux.dlms.GXTime;
import gurux.dlms.ValueEventArgs;
import gurux.dlms.enums.AccessMode;
import gurux.dlms.enums.Authentication;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.InterfaceType;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.enums.SourceDiagnostic;
import gurux.dlms.objects.GXDLMSActionSchedule;
import gurux.dlms.objects.GXDLMSActivityCalendar;
import gurux.dlms.objects.GXDLMSAssociationLogicalName;
import gurux.dlms.objects.GXDLMSAssociationShortName;
import gurux.dlms.objects.GXDLMSAutoAnswer;
import gurux.dlms.objects.GXDLMSAutoConnect;
import gurux.dlms.objects.GXDLMSCaptureObject;
import gurux.dlms.objects.GXDLMSClock;
import gurux.dlms.objects.GXDLMSData;
import gurux.dlms.objects.GXDLMSDayProfile;
import gurux.dlms.objects.GXDLMSDayProfileAction;
import gurux.dlms.objects.GXDLMSDemandRegister;
import gurux.dlms.objects.GXDLMSIECOpticalPortSetup;
import gurux.dlms.objects.GXDLMSImageTransfer;
import gurux.dlms.objects.GXDLMSIp4Setup;
import gurux.dlms.objects.GXDLMSMacAddressSetup;
import gurux.dlms.objects.GXDLMSModemConfiguration;
import gurux.dlms.objects.GXDLMSModemInitialisation;
import gurux.dlms.objects.GXDLMSObject;
import gurux.dlms.objects.GXDLMSProfileGeneric;
import gurux.dlms.objects.GXDLMSPushSetup;
import gurux.dlms.objects.GXDLMSRegister;
import gurux.dlms.objects.GXDLMSRegisterMonitor;
import gurux.dlms.objects.GXDLMSSapAssignment;
import gurux.dlms.objects.GXDLMSSeasonProfile;
import gurux.dlms.objects.GXDLMSTcpUdpSetup;
import gurux.dlms.objects.GXDLMSWeekProfile;
import gurux.dlms.objects.enums.AutoAnswerStatus;
import gurux.dlms.objects.enums.AutoConnectMode;
import gurux.dlms.objects.enums.BaudRate;
import gurux.dlms.objects.enums.LocalPortResponseTime;
import gurux.dlms.objects.enums.OpticalProtocolMode;
import gurux.dlms.objects.enums.SingleActionScheduleType;
import gurux.dlms.objects.enums.SortMethod;
import gurux.dlms.secure.GXDLMSSecureServer;
import gurux.net.GXNet;
import gurux.net.enums.NetworkType;

/**
 * All example servers are using same objects.
 */
public class GXDLMSBase extends GXDLMSSecureServer
        implements IGXMediaListener, gurux.net.IGXNetListener {

    boolean Trace = false;
    private GXNet media;

    public GXDLMSBase(boolean logicalNameReferencing,
            InterfaceType interfaceType) {
        super(logicalNameReferencing, interfaceType);
    }

    /*
     * Add Logical Device Name. 123456 is meter serial number.
     */
    void addLogicalDeviceName() {
        GXDLMSData d = new GXDLMSData("0.0.42.0.0.255");
        d.setValue("Gurux123456");
        // Set access right. Client can't change Device name.
        d.setAccess(2, AccessMode.READ);
        d.setDataType(2, DataType.OCTET_STRING);
        d.setUIDataType(2, DataType.STRING);
        getItems().addElement(d);
    }

    /*
     * Add firmware version.
     */
    void addFirmwareVersion() {
        GXDLMSData d = new GXDLMSData("1.0.0.2.0.255");
        d.setValue("Gurux FW 0.0.1");
        getItems().addElement(d);
    }

    GXDLMSRegister addRegister() {
        // Add Last average.
        GXDLMSRegister r = new GXDLMSRegister("1.1.21.25.0.255");
        // Set access right. Client can't change Device name.
        r.setAccess(2, AccessMode.READ);
        getItems().addElement(r);
        return r;
    }

    /**
     * Add default clock. Clock's Logical Name is 0.0.1.0.0.255.
     */
    GXDLMSClock addClock() {
        GXDLMSClock clock = new GXDLMSClock();
        clock.setBegin(new GXDateTime(-1, 9, 1, -1, -1, -1, -1));
        clock.setEnd(new GXDateTime(-1, 3, 1, -1, -1, -1, -1));
        clock.setDeviation(0);
        getItems().addElement(clock);
        return clock;
    }

    /**
     * Add TCP/IP UDP setup object.
     */
    void addTcpUdpSetup() {
        // Add TCP/IP UDP setup. Default Logical Name is 0.0.25.0.0.255.
        GXDLMSTcpUdpSetup tcp = new GXDLMSTcpUdpSetup();
        getItems().addElement(tcp);
    }

    /*
     * Add profile generic (historical) object.
     */
    GXDLMSProfileGeneric addProfileGeneric(final GXDLMSClock clock,
            final GXDLMSRegister register) {
        GXDLMSProfileGeneric pg = new GXDLMSProfileGeneric("1.0.99.1.0.255");
        // Set capture period to 60 second.
        pg.setCapturePeriod(60);
        // Maximum row count.
        pg.setProfileEntries(100);
        pg.setSortMethod(SortMethod.FIFO);
        pg.setSortObject(clock);
        // Add columns.
        pg.addCaptureObject(clock, 2, 0);
        pg.addCaptureObject(register, 2, 0);
        getItems().addElement(pg);
        return pg;
    }

    /*
     * Add Auto connect object.
     */
    void addAutoConnect() {
        GXDLMSAutoConnect ac = new GXDLMSAutoConnect();
        ac.setMode(AutoConnectMode.AUTO_DIALLING_ALLOWED_ANYTIME);
        ac.setRepetitions(10);
        ac.setRepetitionDelay(60);
        // Calling is allowed between 1am to 6am.
        ac.getCallingWindow().addElement(new GXSimpleEntry(
                new GXTime(1, 0, 0, -1), new GXTime(6, 0, 0, -1)));
        ac.setDestinations(new String[] { "www.gurux.org" });
        getItems().addElement(ac);
    }

    /*
     * Add Activity Calendar object.
     */
    void addActivityCalendar() {
        java.util.Calendar tm = java.util.Calendar.getInstance();
        java.util.Date now = tm.getTime();

        GXDLMSActivityCalendar activity = new GXDLMSActivityCalendar();
        activity.setCalendarNameActive("Active");
        activity.setSeasonProfileActive(
                new GXDLMSSeasonProfile[] { new GXDLMSSeasonProfile(
                        "Summer time", new GXDate(-1, 3, 31), "") });
        GXDLMSWeekProfile wp = new GXDLMSWeekProfile();
        wp.setName("Monday");
        wp.setMonday(1);
        wp.setTuesday(1);
        wp.setWednesday(1);
        wp.setThursday(1);
        wp.setFriday(1);
        wp.setSaturday(1);
        wp.setSunday(1);
        activity.setWeekProfileTableActive(new GXDLMSWeekProfile[] { wp });
        activity.setDayProfileTableActive(
                new GXDLMSDayProfile[] { new GXDLMSDayProfile(1,
                        new GXDLMSDayProfileAction[] {
                                new GXDLMSDayProfileAction(new GXTime(now),
                                        "test", 1) }) });
        activity.setCalendarNamePassive("Passive");
        activity.setSeasonProfilePassive(
                new GXDLMSSeasonProfile[] { new GXDLMSSeasonProfile(
                        "Winter time", new GXDate(-1, 10, 30), "") });
        wp = new GXDLMSWeekProfile();
        wp.setName("Tuesday");
        wp.setMonday(1);
        wp.setTuesday(1);
        wp.setWednesday(1);
        wp.setThursday(1);
        wp.setFriday(1);
        wp.setSaturday(1);
        wp.setSunday(1);
        activity.setWeekProfileTablePassive(new GXDLMSWeekProfile[] { wp });
        activity.setDayProfileTablePassive(
                new GXDLMSDayProfile[] { new GXDLMSDayProfile(1,
                        new GXDLMSDayProfileAction[] {
                                new GXDLMSDayProfileAction(new GXTime(now),
                                        "0.0.1.0.0.255", 1) }) });
        activity.setTime(new GXDateTime(now));
        getItems().addElement(activity);
    }

    /*
     * Add Optical Port Setup object.
     */
    void addOpticalPortSetup() {
        GXDLMSIECOpticalPortSetup optical = new GXDLMSIECOpticalPortSetup();
        optical.setDefaultMode(OpticalProtocolMode.DEFAULT);
        optical.setProposedBaudrate(BaudRate.BAUDRATE_9600);
        optical.setDefaultBaudrate(BaudRate.BAUDRATE_300);
        optical.setResponseTime(LocalPortResponseTime.ms200);
        optical.setDeviceAddress("Gurux");
        optical.setPassword1("Gurux1");
        optical.setPassword2("Gurux2");
        optical.setPassword5("Gurux5");
        getItems().addElement(optical);
    }

    /*
     * Add Demand Register object.
     */
    void addDemandRegister() {
        java.util.Calendar tm = java.util.Calendar.getInstance();
        java.util.Date now = tm.getTime();
        GXDLMSDemandRegister dr = new GXDLMSDemandRegister();
        dr.setLogicalName("0.0.1.0.0.255");
        dr.setCurrentAvarageValue(new Integer(10));
        dr.setLastAvarageValue(new Integer(20));
        dr.setStatus(new Integer(1));
        dr.setStartTimeCurrent(new GXDateTime(now));
        dr.setCaptureTime(new GXDateTime(now));
        dr.setPeriod(10);
        dr.setNumberOfPeriods(1);
        getItems().addElement(dr);
    }

    /*
     * Add Register Monitor object.
     */
    void addRegisterMonitor(GXDLMSRegister register) {
        GXDLMSRegisterMonitor rm = new GXDLMSRegisterMonitor();
        rm.setLogicalName("0.0.1.0.0.255");
        rm.setThresholds(null);
        rm.getMonitoredValue().update(register, 2);
        getItems().addElement(rm);
    }

    /*
     * Add action schedule object.
     */
    void addActionSchedule() {
        Vector tmp = new Vector();
        tmp.addElement(new GXDateTime(java.util.Calendar
                .getInstance(java.util.TimeZone.getTimeZone("UTC")).getTime()));
        GXDLMSActionSchedule actionS = new GXDLMSActionSchedule();
        actionS.setLogicalName("0.0.1.0.0.255");
        actionS.setExecutedScriptLogicalName("1.2.3.4.5.6");
        actionS.setExecutedScriptSelector(1);
        actionS.setType(SingleActionScheduleType.SingleActionScheduleType1);
        actionS.setExecutionTime(tmp);
        getItems().addElement(actionS);
    }

    /*
     * Add SAP Assignment object.
     */
    void addSapAssignment() {
        GXDLMSSapAssignment sap = new GXDLMSSapAssignment();
        sap.getSapAssignmentList()
                .addElement(new GXSimpleEntry(new Integer(1), "Gurux"));
        sap.getSapAssignmentList()
                .addElement(new GXSimpleEntry(new Integer(16), "Gurux-2"));
        getItems().addElement(sap);
    }

    /**
     * Add Auto Answer object.
     */
    void addAutoAnswer() {
        GXDLMSAutoAnswer aa = new GXDLMSAutoAnswer();
        aa.setMode(AutoConnectMode.EMAIL_SENDING);
        aa.getListeningWindow()
                .addElement(new GXSimpleEntry(
                        new GXDateTime(-1, -1, -1, 6, -1, -1, -1),
                        new GXDateTime(-1, -1, -1, 8, -1, -1, -1)));
        aa.setStatus(AutoAnswerStatus.INACTIVE);
        aa.setNumberOfCalls(0);
        aa.setNumberOfRingsInListeningWindow(1);
        aa.setNumberOfRingsOutListeningWindow(2);
        getItems().addElement(aa);
    }

    /*
     * Add Modem Configuration object.
     */
    void addModemConfiguration() {
        GXDLMSModemConfiguration mc = new GXDLMSModemConfiguration();
        mc.setCommunicationSpeed(BaudRate.BAUDRATE_600);
        GXDLMSModemInitialisation init = new GXDLMSModemInitialisation();
        init.setRequest("AT");
        init.setResponse("OK");
        init.setDelay(0);
        mc.setInitialisationStrings(new GXDLMSModemInitialisation[] { init });
        getItems().addElement(mc);
    }

    /**
     * Add MAC Address Setup object.
     */
    void addMacAddressSetup() {
        GXDLMSMacAddressSetup mac = new GXDLMSMacAddressSetup();
        mac.setMacAddress("11:22:33:44:55:66");
        getItems().addElement(mac);
    }

    /**
     * Add Image transfer object.
     */
    void addImageTransfer() {
        GXDLMSImageTransfer i = new GXDLMSImageTransfer();
        getItems().addElement(i);
    }

    /**
     * Add IP4 setup object.
     */
    void addIp4Setup() {
        GXDLMSIp4Setup ip4 = new GXDLMSIp4Setup();
        SocketConnection c = null;
        try {
            c = (SocketConnection) Connector.open("socket://:4060");
            ip4.setIPAddress(c.getLocalAddress());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    // Ignore errors.
                }
            }
        }

        getItems().addElement(ip4);
    }

    /**
     * Add Push Setup. (On Connectivity object).
     */
    void addPushSetup() {
        GXDLMSPushSetup push = new GXDLMSPushSetup("0.0.25.9.0.255");
        getItems().addElement(push);
        // Add push object itself. This is needed to tell structure of data to
        // the Push listener.
        push.getPushObjectList().addElement(
                new GXSimpleEntry(push, new GXDLMSCaptureObject(2, 0)));
        // Add logical device name.
        GXDLMSObject ldn =
                getItems().findByLN(ObjectType.DATA, "0.0.42.0.0.255");
        push.getPushObjectList().addElement(
                new GXSimpleEntry(ldn, new GXDLMSCaptureObject(2, 0)));
        // Add .0.0.25.1.0.255 Ch. 0 IPv4 setup IP address.
        GXDLMSObject ip4 =
                getItems().findByLN(ObjectType.IP4_SETUP, "0.0.25.1.0.255");
        push.getPushObjectList().addElement(
                new GXSimpleEntry(ip4, new GXDLMSCaptureObject(3, 0)));
    }

    /**
     * Add available objects.
     * 
     * @param server
     * @throws IOException
     */
    public void Initialize(int port) throws IOException {
        media = new gurux.net.GXNet(NetworkType.TCP, port);
        media.setTrace(TraceLevel.VERBOSE);
        media.addListener(this);
        media.open();
        ///////////////////////////////////////////////////////////////////////
        // Add objects of the meter.
        addLogicalDeviceName();
        // Add firmware version.
        addFirmwareVersion();
        // Add example register object.
        GXDLMSRegister register = addRegister();
        // Add default clock object.
        GXDLMSClock clock = addClock();
        // Add TCP/IP UDP setup object.
        addTcpUdpSetup();
        // Add profile generic object.
        GXDLMSProfileGeneric pg = addProfileGeneric(clock, register);
        // Add Auto connect object.
        addAutoConnect();
        // Add Activity Calendar object.
        addActivityCalendar();

        // Add Optical Port Setup object.
        addOpticalPortSetup();

        // Add Demand Register object.
        addDemandRegister();

        // Add Register Monitor object.
        addRegisterMonitor(register);

        // Add action schedule object.
        addActionSchedule();

        // Add SAP Assignment object.
        addSapAssignment();
        // Add Auto Answer object.
        addAutoAnswer();

        // Add Modem Configuration object.
        addModemConfiguration();
        // Add MAC Address Setup object.
        addMacAddressSetup();
        // Add Image transfer object.
        addImageTransfer();

        // Add IP4 Setup object.
        addIp4Setup();

        // Add Push Setup. (On Connectivity object)
        addPushSetup();
        ///////////////////////////////////////////////////////////////////////
        // Server must initialize after all objects are added.
        initialize();
        // Add rows after Initialize.
        Object[][] rows = new Object[][] { new Object[] {
                java.util.Calendar.getInstance().getTime(), new Integer(10) } };
        pg.setBuffer((Object[][]) rows);
    }

    public void read(ValueEventArgs[] args) {

        for (int pos = 0; pos != args.length; ++pos) {
            ValueEventArgs e = args[pos];
            // Framework will handle Association objects automatically.
            if (e.getTarget() instanceof GXDLMSAssociationLogicalName
                    || e.getTarget() instanceof GXDLMSAssociationShortName
                    || e.getTarget() instanceof GXDLMSProfileGeneric)
            // Framework will handle profile generic automatically.
            {
                continue;
            }
            System.out
                    .println("Client Read value from " + e.getTarget().getName()
                            + " attribute: " + +e.getIndex() + ".");
            if ((e.getTarget().getUIDataType(e.getIndex()) == DataType.DATETIME
                    || e.getTarget()
                            .getDataType(e.getIndex()) == DataType.DATETIME)
                    && !(e.getTarget() instanceof GXDLMSClock)) {
                e.setValue(java.util.Calendar
                        .getInstance(java.util.TimeZone.getTimeZone("UTC"))
                        .getTime());
                e.setHandled(true);
            } else if (e.getTarget() instanceof GXDLMSClock) {
                // Implement specific clock handling here.
                // Otherwise initial values are used.
                if (e.getIndex() == 2) {
                    e.setValue(java.util.Calendar
                            .getInstance(java.util.TimeZone.getTimeZone("UTC"))
                            .getTime());
                    e.setHandled(true);
                }
            } else if (e.getTarget() instanceof GXDLMSRegisterMonitor
                    && e.getIndex() == 2) {
                // Update Register Monitor Thresholds values.
                e.setValue(new Object[] {
                        new Integer(new java.util.Random().nextInt(1000)) });
                e.setHandled(true);
            } else {
                // If data is not assigned and value type is unknown return
                // number.
                Object[] values = e.getTarget().getValues();
                if (e.getIndex() <= values.length) {
                    if (values[e.getIndex() - 1] == null) {
                        DataType tp = e.getTarget().getDataType(e.getIndex());
                        if (tp == DataType.NONE || tp == DataType.INT8
                                || tp == DataType.INT16 || tp == DataType.INT32
                                || tp == DataType.INT64 || tp == DataType.UINT8
                                || tp == DataType.UINT16
                                || tp == DataType.UINT32
                                || tp == DataType.UINT64) {
                            e.setValue(new Integer(
                                    new java.util.Random().nextInt(1000)));
                            e.setHandled(true);
                        }
                        if (tp == DataType.STRING) {
                            e.setValue("Gurux");
                            e.setHandled(true);
                        }
                    }
                }
            }
        }
    }

    public void write(ValueEventArgs[] args) {
        for (int pos = 0; pos != args.length; ++pos) {
            ValueEventArgs e = args[pos];
            System.out.println("Client Write new value " + e.getValue()
                    + " to object: " + e.getTarget().getName() + ".");
        }
    }

    public void action(ValueEventArgs[] args) {

    }

    public void onError(Object sender, RuntimeException ex) {
        System.out.println("Error has occurred:" + ex.getMessage());
    }

    /*
     * Client has send data.
     */

    public void onReceived(Object sender, ReceiveEventArgs e) {
        try {
            synchronized (this) {
                if (Trace) {
                    System.out.println("<- " + gurux.common.GXCommon
                            .bytesToHex((byte[]) e.getData()));
                }
                byte[] reply = handleRequest((byte[]) e.getData());
                // Reply is null if we do not want to send any data to the
                // client.
                // This is done if client try to make connection with wrong
                // server or client address.
                if (reply != null) {
                    if (Trace) {
                        System.out.println("-> "
                                + gurux.common.GXCommon.bytesToHex(reply));
                    }
                    media.send(reply, e.getSenderInfo());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void onMediaStateChange(Object sender, MediaStateEventArgs e) {

    }

    /*
     * Client has made connection.
     */

    public void onClientConnected(Object sender,
            gurux.net.ConnectionEventArgs e) {
        System.out.println("Client Connected.");
    }

    /*
     * Client has close connection.
     */

    public void onClientDisconnected(Object sender,
            gurux.net.ConnectionEventArgs e) {
        // Reset server settings when connection closed.
        // this.reset();
        System.out.println("Client Disconnected.");
    }

    public void onTrace(Object sender, TraceEventArgs e) {
        // System.out.println(e.toString());
    }

    public void onPropertyChanged(Object sender, PropertyChangedEventArgs e) {

    }

    public GXDLMSObject onFindObject(ObjectType objectType, int sn, String ln) {
        return null;
    }

    /**
     * Example server accepts all connections.
     * 
     * @param serverAddress
     *            Server address.
     * @param clientAddress
     *            Client address.
     * @return True.
     */

    public final boolean isTarget(final int serverAddress,
            final int clientAddress) {
        return true;
    }

    public final SourceDiagnostic validateAuthentication(
            final Authentication authentication, final byte[] password) {
        // Accept all passwords.
        // return SourceDiagnostic.NONE;
        // Uncomment checkPassword if you want to check password.
        return checkPassword(authentication, password);
    }

    SourceDiagnostic checkPassword(final Authentication authentication,
            final byte[] password) {
        // Default password is Gurux.
        byte[] expectedPassword = "Gurux".getBytes();
        if (authentication == Authentication.LOW) {
            if (!new String(password).equals(new String(expectedPassword))) {
                String actual = "";
                if (getSettings().getPassword() != null) {
                    actual = new String(password);
                }
                String expected = new String(expectedPassword);
                System.out.println("Password does not match. Actual: '" + actual
                        + "' Expected: '" + expected + "'");
                return SourceDiagnostic.AUTHENTICATION_FAILURE;
            }
        }
        // Other authentication levels are check on phase two.
        return SourceDiagnostic.NONE;
    }

    protected void connected(GXDLMSConnectionEventArgs connectionInfo) {

    }

    protected void invalidConnection(GXDLMSConnectionEventArgs connectionInfo) {

    }

    protected void disconnected(GXDLMSConnectionEventArgs connectionInfo) {

    }
}