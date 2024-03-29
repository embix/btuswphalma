package com.googlecode.btuswphalma.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.googlecode.btuswphalma.base.IMessage;
import com.googlecode.btuswphalma.base.INetCom;
import com.googlecode.btuswphalma.base.IReaddressableMessage;
import com.googlecode.btuswphalma.base.MessageAddresses;

/**
 * beschreibt die Klasse des Clients, die sich mit dem Server verbindet, die
 * Verbindung abhorcht und Nachrichten verwaltet
 * 
 * @author Christoph
 */
public class ClientNetCom extends Thread implements INetCom, Runnable {

    /** das Java-Object um den Thread aufzuwecken */
    private Object notifyObject;

    /** die Nachrichtenliste */
    private ConcurrentLinkedQueue<IMessage> messageQueue;

    /** der Socket des Clients */
    private Socket socket;

    /** InputStream des Sockets */
    private ObjectInputStream instream;

    /** OutputStream des Sockets */
    private ObjectOutputStream outstream;

    /** Objekt fuer Streams */
    private Object obj;

    /**
     * Konstruktor vom Client, der sich gleich zum Server verbindet
     * 
     * @param ip
     *                :int (IP-Adresse des Servers)
     * @param port
     *                :int (Port des Servers)
     */
    public ClientNetCom(InetAddress ip, int port) {
	this.notifyObject = new Object();
	this.messageQueue = new ConcurrentLinkedQueue<IMessage>();
	try {
	    this.socket = new Socket(ip, port);
	    InputStream is = socket.getInputStream();
	    this.instream = new ObjectInputStream(is);
	    OutputStream os = socket.getOutputStream();
	    this.outstream = new ObjectOutputStream(os);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.err.println(ip.getHostAddress() + ":" + port);
	}
	this.start();
	// TODO Socket aufsetzen, verbinden, run aufrufen
    }

    /**
     * horcht InputStream ab, behandelt eingehende Nachrichten
     */
    @Override
    public void run() {
	while (!this.isInterrupted()) {
	    try {
		// blockierendes Lesen
		this.obj = instream.readObject();
	    } catch (IOException e) {
	    } catch (ClassNotFoundException e) {
	    }
	    NetMessage netmsg = new NetMessage();
	    netmsg = (NetMessage) this.obj;
	    // IMessage msg = netmsg.getMessage();
	    IReaddressableMessage msg = (IReaddressableMessage) netmsg
		    .getMessage();
	    //zu Testzwecken
	    String msgType = msg.getClass().toString();
	    System.err.println(msgType);
	    //Ausgabe der angekommenen Nachrichten
	    msg.setDestination(MessageAddresses.GUI_ADDRESS);
	    this.messageQueue.add(msg);
	    synchronized (notifyObject) {
		this.notifyObject.notify();
	    }
	}
	try {
	    instream.close();
	} catch (IOException e) {
	}
	try {
	    outstream.close();
	} catch (IOException e) {
	}
	try {
	    socket.close();
	} catch (IOException e) {
	}
    }

    /**
     * sendet eine Nachricht ins Netzwerk, nutzt dabei NetMessage als "Huelle"
     * 
     * @param msg
     *                :IMessage (die zu versendende Nachricht)
     */
    public void sendMessage(IMessage msg) {
	NetMessage netmsg = new NetMessage();
	netmsg.setMessage(msg);
	this.obj = netmsg;
	try {
	    outstream.writeObject(obj);
	} catch (IOException e) {
	}
    }

    /**
     * holt sich das notifyObject, zum aufwecken
     * 
     * @return Object
     */
    public Object getNotifyObject() {
	return this.notifyObject;
    }

    /**
     * fragt ab, ob MessageQueue noch Elemente hat
     * 
     * @return boolean
     */
    public boolean hasMessage() {
	return (!(this.messageQueue.isEmpty()));
    }

    /**
     * gibt eine IMessage zurueck - oberste auf Queue
     * 
     * @return IMessage
     */
    public IMessage getMessage() {
	if (hasMessage()) {
	    IMessage msg = this.messageQueue.remove();
	    return msg;
	} else {
	    return null;
	}
    }

}
