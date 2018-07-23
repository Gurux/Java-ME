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

package gurux.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;
import javax.microedition.io.DatagramConnection;
import javax.microedition.io.SocketConnection;

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
import gurux.net.enums.AvailableMediaSettings;
import gurux.net.enums.NetworkType;

/**
 * The GXNet component determines methods that make the communication possible
 * using Internet.
 */
public class GXNet implements IGXMedia, AutoCloseable {
    /**
     * Used protocol.
     */
    private NetworkType protocol = NetworkType.TCP;
    /**
     * Host name.
     */
    private String hostName;
    /**
     * Used port.
     */
    private int port;
    /**
     * Is server or client.
     */
    private boolean server;
    /**
     * Synchronous data handler.
     */
    private GXSynchronousMediaBase syncBase;
    /**
     * Created socket.
     */
    private Closeable socket = null;
    OutputStream os = null;
    /**
     * Connected TCP/IP clients.
     */
    private List<Closeable> tcpIpClients = new ArrayList<Closeable>();

    /**
     * Amount of sent bytes.
     */
    private long bytesSent = 0;
    /**
     * Is media operating in synchronous mode.
     */
    private boolean synchronous = false;
    /**
     * Trace level.
     */
    private TraceLevel trace = TraceLevel.OFF;
    /**
     * Maximum client count.
     */
    private int maxClientCount;
    /**
     * Used end of packet.
     */
    private Object eop;
    /**
     * Configurable settings.
     */
    private int configurableSettings;
    /**
     * Media listeners.
     */
    private List<IGXMediaListener> listeners =
            new ArrayList<IGXMediaListener>();

    /**
     * Network listeners.
     */
    private List<IGXNetListener> netListeners = new ArrayList<IGXNetListener>();
    /**
     * Received thread.
     */
    private ReceiveThread receiverThread = null;

    /**
     * Listener thread.
     */
    private ListenerThread listenerThread = null;

    /**
     * Constructor.
     */
    public GXNet() {
        syncBase =
                new GXSynchronousMediaBase(ReceiveThread.RECEIVE_BUFFER_SIZE);
        setConfigurableSettings(AvailableMediaSettings.ALL.getValue());
        setProtocol(NetworkType.TCP);
    }

    /**
     * Client Constructor.
     * 
     * @param networkType
     *            Used protocol.
     * @param name
     *            Host name.
     * @param portNo
     *            Client port number.
     */
    public GXNet(final NetworkType networkType, final String name,
            final int portNo) {
        this();
        setProtocol(networkType);
        setHostName(name);
        setPort(portNo);
    }

    /**
     * Constructor used when server is started.
     * 
     * @param networkType
     *            Used protocol.
     * @param portNo
     *            Server port.
     */
    public GXNet(final NetworkType networkType, final int portNo) {
        this(networkType, null, portNo);
        this.setServer(true);
    }

    /**
     * @return Gets connected TCP/IP clients.
     */
    final List<Closeable> getTcpIpClients() {
        return tcpIpClients;
    }

    /**
     * Returns synchronous class used to communicate synchronously.
     * 
     * @return Synchronous class.
     */
    final GXSynchronousMediaBase getSyncBase() {
        return syncBase;
    }

    @Override
    public final TraceLevel getTrace() {
        return trace;
    }

    @Override
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
        for (IGXMediaListener it : listeners) {
            it.onPropertyChanged(this, new PropertyChangedEventArgs(info));
        }
    }

    /**
     * Notify that client has connected.
     * 
     * @param e
     *            Connection events argument.
     */
    final void notifyClientConnected(final ConnectionEventArgs e) {
        for (IGXNetListener it : netListeners) {
            it.onClientConnected(this, e);
        }
        if (trace.ordinal() >= TraceLevel.INFO.ordinal()) {
            for (IGXMediaListener it : listeners) {
                it.onTrace(this, new TraceEventArgs(TraceTypes.INFO,
                        "Client connected."));
            }
        }

    }

    /**
     * Notifies clients that client is disconnected.
     * 
     * @param e
     *            Connection event argument.
     */
    final void notifyClientDisconnected(final ConnectionEventArgs e) {
        for (IGXNetListener it : netListeners) {
            it.onClientDisconnected(this, e);
        }
        if (trace.ordinal() >= TraceLevel.INFO.ordinal()) {
            for (IGXMediaListener it : listeners) {
                it.onTrace(this, new TraceEventArgs(TraceTypes.INFO,
                        "Client disconnected."));
            }
        }
    }

    /**
     * Notify clients from error occurred.
     * 
     * @param ex
     *            Occurred error.
     */
    final void notifyError(final RuntimeException ex) {
        for (IGXMediaListener it : listeners) {
            it.onError(this, ex);
            if (trace.ordinal() >= TraceLevel.ERROR.ordinal()) {
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
        for (IGXMediaListener it : listeners) {
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
        for (IGXMediaListener it : listeners) {
            it.onTrace(this, e);
        }
    }

    @Override
    public final int getConfigurableSettings() {
        return configurableSettings;
    }

    @Override
    public final void setConfigurableSettings(final int value) {
        this.configurableSettings = value;
    }

    /**
     * Displays the copyright of the control, user license, and version
     * information, in a dialog box.
     */
    public final void aboutBox() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param target
     *            IP address of the receiver (optional). Reply data is received
     *            through OnReceived event.
     */
    @Override
    public final void send(final Object data, final String target)
            throws Exception {
        if (socket == null) {
            throw new RuntimeException("Invalid connection.");
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
        if (getServer()) {
            if (getProtocol() == NetworkType.TCP) {
                SocketConnection s;
                for (Closeable it : tcpIpClients) {
                    if (it instanceof SocketConnection) {
                        s = (SocketConnection) it;
                        String str = s.getAddress() + ":" + s.getPort();
                        if (str.equals(target)) {
                            OutputStream os =
                                    ((SocketConnection) it).openOutputStream();
                            os.write(buff);
                        } else {
                            System.out.println(target);
                            System.out.println(str);
                        }
                    }
                }
            } else {
                String info;
                if (target.startsWith("/")) {
                    info = target.substring(1);
                } else {
                    info = target;
                }
                DatagramConnection c = (DatagramConnection) socket;
                Datagram d = c.newDatagram(buff, buff.length, info);
                c.send(d);
            }
        } else {
            if (getProtocol() == NetworkType.TCP) {
                os.write(buff);
            } else if (getProtocol() == NetworkType.UDP) {
                DatagramConnection c = (DatagramConnection) socket;
                Datagram d = c.newDatagram(buff, buff.length,
                        getHostName() + ":" + getPort());
                c.send(d);
            }
        }
        this.bytesSent += buff.length;

    }

    /**
     * Notify client from media state change.
     * 
     * @param state
     *            New media state.
     */
    private void notifyMediaStateChange(final MediaState state) {
        for (IGXMediaListener it : listeners) {
            if (trace.ordinal() >= TraceLevel.ERROR.ordinal()) {
                it.onTrace(this, new TraceEventArgs(TraceTypes.INFO, state));
            }
            it.onMediaStateChange(this, new MediaStateEventArgs(state));
        }
    }

    /**
     * Opens the connection. Protocol, Port and HostName must be set, before
     * calling the Open method.
     * 
     * @see #port
     * @see #hostName
     * @see #protocol
     * @see #server
     * @see #close
     */
    @Override
    public final void open() throws IOException {
        close();
        try {
            synchronized (syncBase.getSync()) {
                syncBase.resetLastPosition();
            }
            notifyMediaStateChange(MediaState.OPENING);
            if (this.getServer()) {
                if (trace.ordinal() >= TraceLevel.INFO.ordinal()) {
                    notifyTrace(new TraceEventArgs(TraceTypes.INFO,
                            "Server settings: Protocol: "
                                    + this.getProtocol().toString() + " Port: "
                                    + (new Integer(getPort())).toString()));
                }
                if (getProtocol() == NetworkType.TCP) {
                    socket = Connector.open("socket://:" + getPort());
                    listenerThread = new ListenerThread(this, socket);
                    listenerThread.start();

                } else if (getProtocol() == NetworkType.UDP) {
                    socket = Connector.open("datagram://:" + getPort());
                    receiverThread = new ReceiveThread(this, socket);
                    receiverThread.start();
                    // receiverThread.waitUntilRun();
                }

            } else {
                // Create a stream-based, TCP socket using the InterNetwork
                // Address Family.
                if (getProtocol() == NetworkType.TCP) {
                    SocketConnection s = (SocketConnection) Connector.open(
                            "socket://" + getHostName() + ":" + getPort());
                    os = s.openOutputStream();
                    socket = s;
                } else if (getProtocol() == NetworkType.UDP) {
                    socket = Connector.open(
                            "datagram://" + getHostName() + ":" + getPort());
                } else {
                    throw new IllegalArgumentException("Protocol");
                }
                if (trace.ordinal() >= TraceLevel.INFO.ordinal()) {
                    notifyTrace(new TraceEventArgs(TraceTypes.INFO,
                            "Client settings: Protocol: "
                                    + this.getProtocol().toString() + " Host: "
                                    + getHostName() + " Port: "
                                    + (new Integer(getPort())).toString()));
                }
                receiverThread = new ReceiveThread(this, socket);
                receiverThread.start();
            }
            notifyMediaStateChange(MediaState.OPEN);
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    @Override
    public final void close() {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                // It's OK if this fails.
            }
            os = null;
        }
        if (socket != null) {
            if (getServer() && listenerThread != null) {
                // Close all active sockets.
                for (Closeable it : tcpIpClients) {
                    try {
                        it.close();
                    } catch (IOException e) {
                        // It's OK if this fails.
                    }
                }
                listenerThread.interrupt();
                listenerThread = null;
            } else if (receiverThread != null) {
                receiverThread.interrupt();
                receiverThread = null;
            }
            try {
                notifyMediaStateChange(MediaState.CLOSING);
            } catch (RuntimeException ex) {
                notifyError(ex);
                throw ex;
            } finally {
                try {
                    socket.close();
                } catch (java.lang.Exception e) {
                    // Ignore all errors on close.
                }
                socket = null;
                notifyMediaStateChange(MediaState.CLOSED);
                bytesSent = 0;
                if (receiverThread != null) {
                    receiverThread.resetBytesReceived();
                }
                syncBase.resetReceivedSize();
            }
        }
    }

    @Override
    public final boolean isOpen() {
        return socket != null;
    }

    /**
     * Retrieves the used protocol.
     * 
     * @return Protocol in use.
     */
    public final NetworkType getProtocol() {
        return protocol;
    }

    /**
     * Sets the used protocol.
     * 
     * @param value
     *            Used protocol.
     */
    public final void setProtocol(final NetworkType value) {
        if (protocol != value) {
            protocol = value;
            notifyPropertyChanged("Protocol");
        }
    }

    /**
     * Retrieves the name or IP address of the host.
     * 
     * @return The name of the host.
     * @see #open
     * @see #port
     * @see #protocol
     */
    public final String getHostName() {
        return hostName;
    }

    /**
     * Sets the name or IP address of the host.
     * 
     * @param value
     *            The name of the host.
     */
    public final void setHostName(final String value) {
        if (hostName == null || !hostName.equals(value)) {
            hostName = value;
            notifyPropertyChanged("HostName");
        }
    }

    /**
     * Retrieves or sets the host or server port number.
     * 
     * @return Host or server port number.
     * @see #open
     * @see #hostName
     * @see #protocol
     */
    public final int getPort() {
        return port;
    }

    /**
     * Retrieves or sets the host or server port number.
     * 
     * @param value
     *            Host or server port number
     * @see #open
     * @see #hostName
     * @see #protocol
     */
    public final void setPort(final int value) {
        if (port != value) {
            port = value;
            notifyPropertyChanged("Port");
        }
    }

    /**
     * Is server mode used.
     * 
     * @see #open
     * @return Is server mode used.
     */
    public final boolean getServer() {
        return server;
    }

    /**
     * Is server mode used.
     * 
     * @param value
     *            Is server mode used.
     * @see #open
     */
    public final void setServer(final boolean value) {
        if (server != value) {
            server = value;
            notifyPropertyChanged("Server");
        }
    }

    @Override
    public final <T> boolean receive(final ReceiveParameters<T> args) {
        return syncBase.receive(args);
    }

    /**
     * Sent byte count.
     * 
     * @see #getBytesReceived
     * @see #resetByteCounters
     */
    @Override
    public final long getBytesSent() {
        return bytesSent;
    }

    /**
     * Received byte count.
     * 
     * @see #bytesSent
     * @see #resetByteCounters
     */
    @Override
    public final long getBytesReceived() {
        if (receiverThread == null) {
            return 0;
        }
        return receiverThread.getBytesReceived();
    }

    /**
     * Resets BytesReceived and BytesSent counters.
     * 
     * @see #bytesSent
     * @see #getBytesReceived
     */
    @Override
    public final void resetByteCounters() {
        bytesSent = 0;
        if (receiverThread != null) {
            receiverThread.resetBytesReceived();
        }
    }

    /**
     * Retrieves maximum count of connected clients.
     * 
     * @return Maximum count of connected clients.
     */
    public final int getMaxClientCount() {
        return maxClientCount;
    }

    /**
     * Sets maximum count of connected clients.
     * 
     * @param value
     *            Maximum count of connected clients.
     */
    public final void setMaxClientCount(final int value) {
        maxClientCount = value;
    }

    /**
     * Media settings as a XML string.
     */
    @Override
    public final String getSettings() {
        // TODO:
        return null;
    }

    @Override
    public final void setSettings(final String value) {
        // TODO:
    }

    @Override
    public final void copy(final Object target) {
        GXNet tmp = (GXNet) target;
        setPort(tmp.getPort());
        setHostName(tmp.getHostName());
        setProtocol(tmp.getProtocol());
    }

    @Override
    public final String getName() {
        String tmp;
        tmp = getHostName() + " " + getPort();
        if (getProtocol() == NetworkType.UDP) {
            tmp += "UDP";
        } else {
            tmp += "TCP/IP";
        }
        return tmp;
    }

    @Override
    public final String getMediaType() {
        return "Net";
    }

    @Override
    public void setSynchronous(boolean value) {
        synchronized (this) {
            synchronous = true;
        }
    }

    @Override
    public final boolean isSynchronous() {
        synchronized (this) {
            return synchronous;
        }
    }

    @Override
    public final void resetSynchronousBuffer() {
        synchronized (syncBase.getSync()) {
            syncBase.resetReceivedSize();
        }
    }

    @Override
    public final void validate() {
        if (getPort() == 0) {
            throw new RuntimeException("Invalid port name.");
        }
        if (getHostName() == null || "".equals(getHostName())) {
            throw new RuntimeException("Invalid host name.");
        }
    }

    @Override
    public final Object getEop() {
        return eop;
    }

    @Override
    public final void setEop(final Object value) {
        eop = value;
    }

    @Override
    public final void addListener(final IGXMediaListener listener) {
        listeners.add(listener);
        if (listener instanceof IGXNetListener) {
            netListeners.add((IGXNetListener) listener);
        }
    }

    @Override
    public final void removeListener(final IGXMediaListener listener) {
        listeners.remove(listener);
        if (listener instanceof IGXNetListener) {
            netListeners.remove((IGXNetListener) listener);
        }
    }
}