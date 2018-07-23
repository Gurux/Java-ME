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

import java.io.IOException;

import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.SocketConnection;
import javax.microedition.io.StreamConnection;

/**
 * Receive thread listens socket and sends received data to the listeners.
 * 
 * @author Gurux Ltd.
 */
class ListenerThread extends Thread {

    /**
     * Server socket.
     */
    private final ServerSocketConnection serverSocket;
    /**
     * Parent component where notifies are send.
     */
    private GXNet parentMedia;

    /**
     * Constructor.
     * 
     * @param parent
     *            Parent media.
     * @param socket
     *            Socket to listen.
     */
    ListenerThread(final GXNet parent, final Object socket) {
        super("GXNet " + socket.toString());
        parentMedia = parent;
        serverSocket = (ServerSocketConnection) socket;
    }

    /**
     * Wait until thread is started.
     * 
     * @return true, if thread started.
     */
    public boolean waitUntilRun() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Receive data from the server using the established socket connection.
     * 
     * @return The data received from the server
     */
    public final void run() {
        // Notify caller that thread is started.
        synchronized (this) {
            notifyAll();
        }
        StreamConnection socket;
        while (Thread.currentThread().isAlive()) {
            try {
                socket = serverSocket.acceptAndOpen();
                SocketConnection s = (SocketConnection) socket;
                ReceiveThread receiver = new ReceiveThread(parentMedia, socket);
                receiver.start();
                parentMedia.getTcpIpClients().addElement(socket);
                String info = String.valueOf(s.getAddress());
                parentMedia
                        .notifyClientConnected(new ConnectionEventArgs(info));
            } catch (IOException ex) {
                // If connection is not closed.
                if (!Thread.currentThread().isAlive()) {
                    parentMedia
                            .notifyError(new RuntimeException(ex.getMessage()));
                }
                continue;
            }
        }
    }
}