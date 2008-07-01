/**
 * 
 */
package com.googlecode.btuswphalma.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;

import com.googlecode.btuswphalma.base.IMessage;
import com.googlecode.btuswphalma.base.IReaddressableMessage;
import com.googlecode.btuswphalma.base.MessageAddresses;
import com.googlecode.btuswphalma.base.MessageType;

/**
 * @author sebastian
 * 
 */
public class Connection extends Thread {

    /**
     * Die Nummer eines Spielers
     */
    int playerId;
    /**
     * Die "Verbindung" zu einem Client
     */
    Socket socket;
    /**
     * Auf diesen Stream wird geschrieben
     */
    ObjectOutputStream outputStream;
    /**
     * Von diesem Stream wird gelesen
     */
    ObjectInputStream inputStream;
    /**
     * Eine Warteschlange, auf der Nachrichten gepuffert werden
     */
    Queue<IMessage> messageQueue;
    /**
     * Ein Object, mittels dessen man Anzeigen kann, dass eine neue Nachricht eingetroffen ist
     */
    Object notifyObject;

    /**
     * Zu dem Socket werden ObjectStreams erzeugt und der Thread gestartet.
     * 
     * @param socket
     *                die Verbindung zu dem Spieler
     * @param playerId
     *                die Nummer des Spielers
     * @param messageQueue die Nachrichtenwarteschlange
     * @param notifyObject das Benachrichtigungsobjekt
     */
    public Connection(Socket socket, int playerId,
	    Queue<IMessage> messageQueue, Object notifyObject) {
	this.socket = socket;
	this.playerId = playerId;
	this.messageQueue = messageQueue;
	this.notifyObject = notifyObject;
	try {
	    outputStream = new ObjectOutputStream(socket.getOutputStream());
	    inputStream = new ObjectInputStream(socket.getInputStream());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Nachrichten werden abgeholt, readressiert, auf die Warteschlange gelegt
     * und das notifyObject "benachrichtigt"
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
	
	
	while (!isInterrupted()) {
	    receiveMessage();
	}
	try {
	    socket.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    private void receiveMessage() {
	IReaddressableMessage msg;
	    NetMessage ntMsg;
	    try {
		ntMsg = (NetMessage) inputStream.readObject();
		msg = (IReaddressableMessage) ntMsg.getMessage();
		msg.setDestination(MessageAddresses.MANAGER_ADDRESS);
		msg.setSource(playerId);
		messageQueue.add(msg);
		synchronized (notifyObject) {
		    notifyObject.notify();
		}
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
    }
    
    public void receiveLoginMessage() throws NetworkException {
	IReaddressableMessage msg;
	    NetMessage ntMsg;
	    try {
		ntMsg = (NetMessage) inputStream.readObject();
		msg = (IReaddressableMessage) ntMsg.getMessage();
		if (msg.getType() != MessageType.MT_LOGIN) {
		    throw new NetworkException();
		}
		msg.setDestination(MessageAddresses.MANAGER_ADDRESS);
		msg.setSource(playerId);
		messageQueue.add(msg);
		synchronized (notifyObject) {
		    notifyObject.notify();
		    System.out.println("notified Login");
		}
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.getCause().printStackTrace();
		e.printStackTrace();
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
    }

    // TODO ich weiß nicht, was diese Methode soll
    /**
     * @param msg
     */
    public void putMessageOnQueue(IMessage msg) {

    }

    /**
     * @return
     */
    public int getPlayerId() {
	return playerId;
    }

    /**
     * Eine Nachricht wird mittels des ObjectOutputStreams versendet. Dazu wird
     * die Nachricht noch in eine NetMessage gepackt
     * 
     * @param msg
     */
    public void sendMessage(IMessage msg) {
	try {
	    outputStream.writeObject(new NetMessage(msg));
	    outputStream.flush();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
