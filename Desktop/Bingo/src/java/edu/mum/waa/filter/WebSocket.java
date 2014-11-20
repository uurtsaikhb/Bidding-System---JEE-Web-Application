/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author mandal
 */

@ServerEndpoint("/websocket")
public class WebSocket {

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void onMessage(String message, Session session)
            throws IOException 
    {
    System.out.println("Received: " + message);

        synchronized (clients) 
        {
            for (Session client : clients) {
                if (!client.equals(session)) {
                    client.getBasicRemote().sendText(message);
                }
            }
        }

    }

    @OnOpen
    public void onOpen(Session session) {
    System.out.println("Client connected");
        clients.add(session);
    }

    @OnClose
    public void onClose(Session session) {
    System.out.println("Client closed");
        clients.remove(session);
    }
}
