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
    private OutputStream outstrem;
    
    /**
     * Konstruktor vom Client, der sich gleich zum Server verbindet
     * @param ip :int (IP-Adresse des Servers)
     * @param port :int (Port des Servers)
     */
    public ClientNetCom(InetAddress ip, int port) throws IOException {
    	this.notifyObject = new Object();
    	this.messageQueue = new ConcurrentLinkedQueue<IMessage>();
    	this.socket = new Socket(ip, port);
    	this.instream = socket.getInputStream();
    	this.outstrem = socket.getOutputStream();
    	new Thread (this).start();
    	//TODO Socket aufsetzen, verbinden, run aufrufen
    }
    
    /**
     * horcht InputStream ab, behandelt eingehende Nachrichten
     */
    public void run() {
    	//run-Methode, horcht Stream ab
    }
	
    /**
     * sendet eine Nachricht ins Netzwerk, nutzt dabei
     * NetMessage als "Huelle"
     * @param msg :IMessage (die zu versendende Nachricht)
     */
	public void sendMessage(IMessage msg) {
		//TODO eine Nachricht versenden
	}
	
	/**
	 * holt sich das notifyObject, zum aufwecken
	 * @return Object
	 */
	public Object getNotifyObject() {
		//TODO das notifyObject holen
		return this.notifyObject;
	}
	
	/**
	 * fragt ab, ob MessageQueue noch Elemente hat
	 * @return boolean
	 */
	public boolean hasMessage() {
		//TODO Liste abfragen, ob leer oder nicht
		return (!(this.messageQueue.isEmpty()));
	}

	/**
	 * gibt eine IMessage zurueck - oberste auf Queue
	 * @return IMessage
	 */
	public IMessage getMessage() {
		//TODO oberste Msg von der Liste zurueck geben
		IMessage msg = this.messageQueue.remove();
		return msg;
	}
	
}
