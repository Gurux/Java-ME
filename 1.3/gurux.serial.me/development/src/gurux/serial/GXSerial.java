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

package gurux.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.CommConnection;
import javax.microedition.io.Connector;

import gurux.common.GXCommon;
import gurux.common.GXSynchronousMediaBase;
import gurux.common.IGXMedia;
import gurux.common.IGXMediaListener;
import gurux.common.MediaStateEventArgs;
import gurux.common.PropertyChangedEventArgs;
import gurux.common.ReceiveEventArgs;
import gurux.common.ReceiveParameters;
import gurux.common.TraceEventArgs;
import gurux.common.enums.MediaState;
import gurux.common.enums.TraceLevel;
import gurux.common.enums.TraceTypes;
import gurux.io.BaudRate;
import gurux.io.Parity;
import gurux.io.StopBits;
import gurux.serial.enums.AvailableMediaSettings;

/**
 * The GXSerial component determines methods that make the communication
 * possible using serial port connection.
 */
public class GXSerial implements IGXMedia {

    /**
     * Read buffer size.
     */
    static final int DEFUALT_READ_BUFFER_SIZE = 256;

    /**
     * Default baud rate.
     */
    static final int DEFAULT_BAUD_RATE = 9600;
    /**
     * Amount of default data bits.
     */
    static final int DEFAULT_DATA_BITS = 8;

    // Values are saved if port is not open and user try to set them.
    /**
     * Serial port baud rate.
     */
    private int baudRate = DEFAULT_BAUD_RATE;
    /**
     * Used data bits.
     */
    private int dataBits = DEFAULT_DATA_BITS;
    /**
     * Stop bits.
     */
    private StopBits stopBits = StopBits.ONE;
    /**
     * Used parity.
     */
    private Parity parity = Parity.NONE;

    /**
     * Write timeout.
     */
    private int writeTimeout;
    /**
     * Read timeout.
     */
    private int readTimeout;

    /**
     * Read buffer size.
     */
    private int readBufferSize;
    /**
     * Receiver thread.
     */
    private GXReceiveThread receiver;
    /**
     * Serial port connection.
     */
    private CommConnection hWnd;
    /**
     * Name of serial port.
     */
    private String portName;
    /**
     * Synchronously class.
     */
    private GXSynchronousMediaBase syncBase;
    /**
     * Amount of bytes sent.
     */
    private long bytesSend = 0;
    /**
     * Is Synchronous.
     */
    private boolean synchronous = false;
    /**
     * Trace level.
     */
    private TraceLevel trace = TraceLevel.OFF;
    /**
     * End of packet.
     */
    private Object eop;
    /**
     * Configurable settings.
     */
    private int configurableSettings;
    /**
     * Media listeners.
     */
    private Vector mediaListeners =
            new Vector();

    /**
     * Input stream.
     */
    private InputStream is = null;

    /**
     * Output stream.
     */
    private OutputStream out = null;

    /**
     * Constructor.
     */
    public GXSerial() {
        readBufferSize = DEFUALT_READ_BUFFER_SIZE;
        syncBase = new GXSynchronousMediaBase(readBufferSize);
        setConfigurableSettings(AvailableMediaSettings.ALL.getValue());
    }

    /**
     * Returns synchronous class used to communicate synchronously.
     * 
     * @return Synchronous class.
     */
    final GXSynchronousMediaBase getSyncBase() {
        return syncBase;
    }

    /**
     * Split string to array.
     * 
     * @param str
     *            String to split.
     * @param separator
     *            Separator.
     * @return Split values.
     */
    private static String[] split(final String str, final char separator) {
        Vector arr = new Vector();
        int pos = 0, lastPos = 0;
        while ((pos = str.indexOf(separator, lastPos)) != -1) {
            arr.addElement(str.substring(lastPos, pos));
            lastPos = pos + 1;
        }
        if (str.length() > lastPos) {
            arr.addElement(str.substring(lastPos));
        } else {
            arr.addElement("");
        }
        String[] tmp = new String[arr.size()];
        arr.copyInto(tmp);
        return tmp;
    }

    /**
     * Gets an array of serial port names for the current computer.
     * 
     * @return Collection of available serial ports.
     */
    public static String[] getPortNames() {
        return split(System.getProperty("microedition.commports"), ',');
    }

    /**
     * Get baud rates supported by given serial port.
     * 
     * @param portName
     *            Name of serial port.
     * @return Collection of available baud rates.
     */
    public static final int[] getAvailableBaudRates(final String portName) {
        return new int[] { BaudRate.BAUD_RATE_300.getValue(),
                BaudRate.BAUD_RATE_600.getValue(),
                BaudRate.BAUD_RATE_1800.getValue(),
                BaudRate.BAUD_RATE_2400.getValue(),
                BaudRate.BAUD_RATE_4800.getValue(),
                BaudRate.BAUD_RATE_9600.getValue(),
                BaudRate.BAUD_RATE_19200.getValue(),
                BaudRate.BAUD_RATE_38400.getValue() };
    }

    public final TraceLevel getTrace() {
        return trace;
    }

    public final void setTrace(final TraceLevel value) {
        trace = value;
        syncBase.setTrace(value);
    }

    /**
     * Notify that property has changed.
     * 
     * @param info
     *            Name of changed property.
     */
    private void notifyPropertyChanged(final String info) {
        Enumeration iterator = mediaListeners.elements();
        while (iterator.hasMoreElements()) {
            IGXMediaListener it = (IGXMediaListener) iterator.nextElement();
            it.onPropertyChanged(this, new PropertyChangedEventArgs(info));
        }
        
    }

    /**
     * Notify clients from error occurred.
     * 
     * @param ex
     *            Occurred error.
     */
    final void notifyError(final RuntimeException ex) {
        Enumeration iterator = mediaListeners.elements();
        while (iterator.hasMoreElements()) {
            IGXMediaListener it = (IGXMediaListener) iterator.nextElement();
            it.onError(this, ex);
            if (trace.getValue() >= TraceLevel.ERROR.getValue()) {
                it.onTrace(this, new TraceEventArgs(TraceTypes.ERROR, ex));
            }
        }
    }

    /**
     * Notify clients from new data received.
     * 
     * @param e
     *            Received event argument.
     */
    final void notifyReceived(final ReceiveEventArgs e) {
        Enumeration iterator = mediaListeners.elements();
        while (iterator.hasMoreElements()) {
            IGXMediaListener it = (IGXMediaListener) iterator.nextElement();
            it.onReceived(this, e);
        }
    }

    /**
     * Notify clients from trace events.
     * 
     * @param e
     *            Trace event argument.
     */
    final void notifyTrace(final TraceEventArgs e) {
        Enumeration iterator = mediaListeners.elements();
        while (iterator.hasMoreElements()) {
            IGXMediaListener it = (IGXMediaListener) iterator.nextElement();
            it.onTrace(this, e);
        }
    }

    public final int getConfigurableSettings() {
        return configurableSettings;
    }

    public final void setConfigurableSettings(final int value) {
        configurableSettings = value;
    }

    public final void send(final Object data, final String target)
            throws Exception {
        if (hWnd == null) {
            throw new RuntimeException("Serial port is not open.");
        }
        if (trace == TraceLevel.VERBOSE) {
            notifyTrace(new TraceEventArgs(TraceTypes.SENT, data));
        }
        // Reset last position if end of packet is used.
        synchronized (syncBase.getSync()) {
            syncBase.resetLastPosition();
        }
        byte[] buff = GXSynchronousMediaBase.getAsByteArray(data);
        if (buff == null) {
            throw new IllegalArgumentException(
                    "Data send failed. Invalid data.");
        }
        out.write(buff);
        this.bytesSend += buff.length;
    }

    /**
     * Notify client from media state change.
     * 
     * @param state
     *            New media state.
     */
    private void notifyMediaStateChange(final MediaState state) {
        Enumeration iterator = mediaListeners.elements();
        while (iterator.hasMoreElements()) {
            IGXMediaListener it = (IGXMediaListener) iterator.nextElement();
            if (trace.getValue() >= TraceLevel.ERROR.getValue()) {
                it.onTrace(this, new TraceEventArgs(TraceTypes.INFO, state));
            }
            it.onMediaStateChange(this, new MediaStateEventArgs(state));
        }
    }

    public final void open() throws Exception {
        close();
        try {
            if (portName == null || portName == "") {
                throw new IllegalArgumentException(
                        "Serial port is not selected.");
            }
            synchronized (syncBase.getSync()) {
                syncBase.resetLastPosition();
            }
            notifyMediaStateChange(MediaState.OPENING);
            if (trace.getValue() >= TraceLevel.INFO.getValue()) {
                String eopString = "None";
                if (getEop() instanceof byte[]) {
                    eopString = GXCommon.bytesToHex((byte[]) getEop());
                } else if (getEop() != null) {
                    eopString = getEop().toString();
                }
                notifyTrace(new TraceEventArgs(TraceTypes.INFO,
                        "Settings: Port: " + this.getPortName() + " Baud Rate: "
                                + getBaudRate() + " Data Bits: "
                                + (new Integer(getDataBits())).toString()
                                + " Parity: " + getParity().toString()
                                + " Stop Bits: " + getStopBits().toString()
                                + " Eop:" + eopString));
            }
            hWnd = (CommConnection) Connector
                    .open("comm:" + portName + ";baudrate=" + getBaudRate()
                            + ";bitsperchar=" + getDataBits() + ";stopbits="
                            + getStopBits() + ";parity=" + getParity());

            is = hWnd.openInputStream();
            out = hWnd.openOutputStream();
            receiver = new GXReceiveThread(this, is);
            receiver.start();
            notifyMediaStateChange(MediaState.OPEN);
        } catch (Exception ex) {
            close();
            throw ex;
        }
    }

    
    public final void close() {
        if (hWnd != null) {
            if (receiver != null) {
                receiver.interrupt();
                receiver = null;
            }
            try {
                notifyMediaStateChange(MediaState.CLOSING);
            } catch (RuntimeException ex) {
                notifyError(ex);
                throw ex;
            } finally {
                try {
                    hWnd.close();
                } catch (IOException e) {
                    notifyError(new RuntimeException(e.getMessage()));
                }
                hWnd = null;
                try {
                    is.close();
                } catch (IOException e) {
                    // It's OK if this fails.
                }
                is = null;
                try {
                    out.close();
                } catch (IOException e) {
                    // It's OK if this fails.
                }
                out = null;
                notifyMediaStateChange(MediaState.CLOSED);
                bytesSend = 0;
                syncBase.resetReceivedSize();
            }
        }
    }

    /**
     * Used baud rate for communication. Can be changed without disconnecting.
     * 
     * @return Used baud rate.
     */
    public final int getBaudRate() {
        return baudRate;
    }

    /**
     * Set new baud rate.
     * 
     * @param value
     *            New baud rate.
     */
    public final void setBaudRate(final int value) {
        boolean change = getBaudRate() != value;
        if (change) {
            baudRate = value;
            notifyPropertyChanged("BaudRate");
        }
    }

    /**
     * Gets the number of bytes in the receive buffer.
     * 
     * @return Amount of read bytes.
     */
    public final int getBytesToRead() {
        try {
            return is.available();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Gets the standard length of data bits per byte.
     * 
     * @return Amount of data bits.
     */
    public final int getDataBits() {
        return dataBits;
    }

    /**
     * Sets the standard length of data bits per byte.
     * 
     * @param value
     *            Amount of data bits.
     */
    public final void setDataBits(final int value) {
        boolean change;
        change = getDataBits() != value;
        if (change) {
            dataBits = value;
            notifyPropertyChanged("DataBits");
        }
    }

    
    public final boolean isOpen() {
        return hWnd != null;
    }

    /**
     * Gets the parity-checking protocol.
     * 
     * @return Used parity.
     */
    public final Parity getParity() {
        return parity;
    }

    /**
     * Sets the parity-checking protocol.
     * 
     * @param value
     *            Used parity.
     */
    public final void setParity(final Parity value) {
        boolean change;
        change = getParity() != value;
        if (change) {
            parity = value;
            notifyPropertyChanged("Parity");
        }
    }

    /**
     * Gets the port for communications, including but not limited to all
     * available COM ports.
     * 
     * @return Used serial port
     */
    public final String getPortName() {
        return portName;
    }

    /**
     * Sets the port for communications, including but not limited to all
     * available COM ports.
     * 
     * @param value
     *            Used serial port.
     */
    public final void setPortName(final String value) {
        boolean change;
        change = !value.equals(portName);
        portName = value;
        if (change) {
            notifyPropertyChanged("PortName");
        }
    }

    /**
     * Gets the size of the serial port input buffer.
     * 
     * @return Size of input buffer.
     */
    public final int getReadBufferSize() {
        return readBufferSize;
    }

    /**
     * Sets the size of the serial port input buffer.
     * 
     * @param value
     *            Size of input buffer.
     */
    public final void setReadBufferSize(final int value) {
        boolean change;
        change = getReadBufferSize() != value;
        if (change) {
            readBufferSize = value;
            notifyPropertyChanged("ReadBufferSize");
        }
    }

    /**
     * Gets the number of milliseconds before a time-out occurs when a read
     * operation does not finish.
     * 
     * @return Read timeout.
     */
    public final int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Sets the number of milliseconds before a time-out occurs when a read
     * operation does not finish.
     * 
     * @param value
     *            Read timeout.
     */
    public final void setReadTimeout(final int value) {
        boolean change = readTimeout != value;
        readTimeout = value;
        if (change) {
            notifyPropertyChanged("ReadTimeout");
        }
    }

    /**
     * Gets the standard number of stop bits per byte.
     * 
     * @return Used stop bits.
     */
    public final StopBits getStopBits() {
        return stopBits;
    }

    /**
     * Sets the standard number of stop bits per byte.
     * 
     * @param value
     *            Used stop bits.
     */
    public final void setStopBits(final StopBits value) {
        boolean change;
        change = getStopBits() != value;
        if (change) {
            stopBits = value;
            notifyPropertyChanged("StopBits");
        }
    }

    /**
     * Gets the number of milliseconds before a time-out occurs when a write
     * operation does not finish.
     * 
     * @return Used time out.
     */
    public final int getWriteTimeout() {
        return writeTimeout;
    }

    /**
     * Sets the number of milliseconds before a time-out occurs when a write
     * operation does not finish.
     * 
     * @param value
     *            Used time out.
     */
    public final void setWriteTimeout(final int value) {
        boolean change = writeTimeout != value;
        if (change) {
            writeTimeout = value;
            notifyPropertyChanged("WriteTimeout");
        }
    }
  
    public final  boolean receive(final ReceiveParameters args) {
        return syncBase.receive(args);
    }

    
    public final long getBytesSent() {
        return bytesSend;
    }

    
    public final long getBytesReceived() {
        return receiver.getBytesReceived();
    }

    
    public final void resetByteCounters() {
        bytesSend = 0;
        receiver.resetBytesReceived();
    }
    
    public final String getSettings() {
        // TODO:
        return null;
    }

    
    public final void setSettings(final String value) {
        // TODO:
    }

    
    public final void copy(final Object target) {
        GXSerial tmp = (GXSerial) target;
        setPortName(tmp.getPortName());
        setBaudRate(tmp.getBaudRate());
        setStopBits(tmp.getStopBits());
        setParity(tmp.getParity());
        setDataBits(tmp.getDataBits());
    }

    
    public final String getName() {
        return getPortName();
    }

    
    public final String getMediaType() {
        return "Serial";
    }

    
    public final boolean isSynchronous() {
        synchronized (this) {
            return synchronous;
        }
    }

    
    public final void setSynchronous(final boolean value) {
        synchronized (this) {
            synchronous = value;
        }
    }

    
    public final void resetSynchronousBuffer() {
        synchronized (syncBase.getSync()) {
            syncBase.resetReceivedSize();
        }
    }

    
    public final void validate() {
        if (getPortName() == null || getPortName().length() == 0) {
            throw new RuntimeException("Invalid port name.");
        }
    }

    
    public final Object getEop() {
        return eop;
    }

    
    public final void setEop(final Object value) {
        eop = value;
    }

    
    public final void addListener(final IGXMediaListener listener) {
        mediaListeners.addElement(listener);
    }

    
    public final void removeListener(final IGXMediaListener listener) {
        mediaListeners.removeElement(listener);
    }
}