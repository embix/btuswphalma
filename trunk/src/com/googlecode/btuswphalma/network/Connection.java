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
import com.googlecode.btuswphalma.base.NetMessage;

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
	this.start();
    }

    /**
     * Nachrichten werden abgeholt, readressiert, auf die Warteschlange gelegt
     * und das notifyObject "benachrichtigt"
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
	IReaddressableMessage msg;
	NetMessage ntMsg;
	while (!isInterrupted()) {
	    try {
		ntMsg = (NetMessage) inputStream.readObject();
		msg = (IReaddressableMessage) ntMsg.getMessage();
		msg.setDestination(MessageAddresses.MANAGER_ADDRESS);
		msg.setSource(playerId);
		messageQueue.add(msg);
		notifyObject.notify();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	}
	try {
	    socket.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    // TODO ich weiß nicht, was diese Methode soll
    public void putMessageOnQueue(IMessage msg) {

    }

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
