package com.googlecode.btuswphalma.network;

import java.util.concurrent.ConcurrentLinkedQueue;
import com.googlecode.btuswphalma.base.IMessage;
import com.googlecode.btuswphalma.base.INetCom;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress; 
import java.net.Socket;

/**
 * beschreibt die Klasse des Clients, die sich mit dem Server verbindet,
 * die Verbindung abhorcht und Nachrichten verwaltet
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
    private InputStream instream;
    /** OutputStream des Sockets */
    private OutputStream outstream;
    /** Objekt fuer Streams */
    private Object obj;
    
    /**
     * Konstruktor vom Client, der sich gleich zum Server verbindet
     * @param ip :int (IP-Adresse des Servers)
     * @param port :int (Port des Servers)
     */
    public ClientNetCom(InetAddress ip, int port) {
  	this.notifyObject = new Object();
   	this.messageQueue = new ConcurrentLinkedQueue<IMessage>();
   	try {
   		this.socket = new Socket(ip, port);
   	} catch (IOException e) {}
   	try {
   		this.instream = socket.getInputStream();
   	} catch (IOException e) {}
   	try {
   		this.outstream = socket.getOutputStream();
   	} catch (IOException e) {}
   	new Thread (this).start();
   	//TODO Socket aufsetzen, verbinden, run aufrufen
    }
    
    /**
     * horcht InputStream ab, behandelt eingehende Nachrichten
     */
    @Override
    public void run() {
   	while (!this.isInterrupted()) {
   		try {
   			//blockierendes Lesen
   			this.obj = (Object)instream.read();
   		} catch (IOException e) {}
   		NetMessage netmsg = new NetMessage();
   		netmsg = (NetMessage)this.obj;
   		IMessage msg = netmsg.getMessage();
   		//TODO msg.setDestination(1);
   		this.messageQueue.add(msg);
   		this.notifyObject.notify();
   	}
   	try {
   		instream.close();
   	} catch (IOException e) {}
   	try {
   		outstream.close();
   	} catch (IOException e) {}
   	try {
   		socket.close();
   	} catch (IOException e) {}
    }
	
    /**
     * sendet eine Nachricht ins Netzwerk, nutzt dabei
     * NetMessage als "Huelle"
     * @param msg :IMessage (die zu versendende Nachricht)
     */
	public void sendMessage(IMessage msg) {
	NetMessage netmsg = new NetMessage();
	netmsg.setMessage(msg);
	this.obj = (Object)netmsg;
	try {
		outstream.write((byte[])obj);
	} catch (IOException e) {}
	}
	
	/**
	 * holt sich das notifyObject, zum aufwecken
	 * @return Object
	 */
	public Object getNotifyObject() {
	return this.notifyObject;
	}
	
	/**
	 * fragt ab, ob MessageQueue noch Elemente hat
	 * @return boolean
	 */
	public boolean hasMessage() {
	return (!(this.messageQueue.isEmpty()));
	}

	/**
	 * gibt eine IMessage zurueck - oberste auf Queue
	 * @return IMessage
	 */
	public IMessage getMessage() {
	IMessage msg = this.messageQueue.remove();
	return msg;
	}
	
}
