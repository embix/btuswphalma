/**
 * 
 */
package com.googlecode.btuswphalma.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.googlecode.btuswphalma.base.IMessage;
import com.googlecode.btuswphalma.base.INetCom;
import com.googlecode.btuswphalma.base.MessageAddresses;

/**
 * @author sebastian
 * 
 */
public class ServerNetCom extends Thread implements INetCom {

    /**
     * Die minimale Adresse fuer Spieler im Netzwerk ist immer 1
     */
    private static final int MIN_ADDRESS = 2;
    /**
     * Die Anzahl der Spieler, fuer die dieses ServerNetCom gedacht ist.
     * Tatsaechliche Verbindungen: playerNum-1
     */
    private final int playerNum;
    /**
     * Der Port, auf den der serverSockert horchen soll
     */
    private int port;
    /**
     * In der Map werden die Connections gespeichert.
     */
    private HashMap<Integer, Connection> connectionMap;
    /**
     * In der Warteschlange werden Nachrichten gepuffert
     */
    private ConcurrentLinkedQueue<IMessage> messageQueue;
    /**
     * Durch das notifyObject kann man wartende Threads benachrichtigen
     */
    private Object notifyObject;
    /**
     * Mit dem serverSocket werden Verbindungen angenommen
     */
    private ServerSocket serverSocket;

    /**
     * Eine Server fuer eine bestimmte Anzahl an Verbindungen wird erstellt.
     * Die Anzahl der Verbindungen ist um eins geringer als die uebergebene
     * Anzahl von Spielern
     * @param port die Portnummer
     * @param playerNum die Anzahl der Halmaspieler. Einer mehr als Verbindungen
     */
    public ServerNetCom(int port, int playerNum) {
	// TODO Fehler bei falschen Werten
	this.playerNum = playerNum;
	this.port = port;
	connectionMap = new HashMap<Integer, Connection>(playerNum);
	messageQueue = new ConcurrentLinkedQueue<IMessage>();
	notifyObject = new Object();
	// TODO muss hier der Thread gestartet werden, oder wird er gestartet
    }

    /**
     * Das vorderste Objekt auf der Warteschlange wird zurueckgegeben, null,
     * falls die Queue leer ist
     * 
     * @see com.googlecode.btuswphalma.base.INetCom#getMessage()
     */
    public IMessage getMessage() {
	//die Warteschlange gibt selbst null zurueck, wenn leer
	return messageQueue.poll();
    }

    /**
     * Als Horchobjekt dient ein {@link Object}
     * 
     * @see com.googlecode.btuswphalma.base.INetCom#getNotifyObject()
     */
    public Object getNotifyObject() {
	return notifyObject;
    }

    /**
     * Leere wird nach dem Fuellstand der Queue beurteilt.
     * 
     * @see com.googlecode.btuswphalma.base.INetCom#hasMessage()
     */
    public boolean hasMessage() {
	return !messageQueue.isEmpty();
    }

    /**
     * Verschickt die Nachricht. Unterscheidet beim Senden nach Adresstypen
     * 
     * @see com.googlecode.btuswphalma.base.INetCom#sendMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void sendMessage(IMessage msg) {

	int dest = msg.getDestination();
	if (dest == MessageAddresses.BROADCAST_ADDRESS) {
	    broadcastMessage(msg);
	} else if (dest >= MIN_ADDRESS && dest <= playerNum) {
	    if (connectionMap.containsKey(dest)) {
		connectionMap.get(dest).sendMessage(msg);
	    } // TODO was wenn keine Connection?
	} else {
	    // Fehler
	}

    }

    /**
     * Die Nachricht wird an alle verbundenen Clients geschickt
     * 
     * @param msg
     *                die zu verschickende Nachricht
     */
    private void broadcastMessage(IMessage msg) {
	for (int i = MIN_ADDRESS; i <= playerNum; i++) {
	    if (connectionMap.containsKey(i)) {
		connectionMap.get(i).sendMessage(msg);
	    }
	}
    }

    /**
     * Verbindungen mit Sockets werden aufgebaut
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {

	createServerSocket();
	acceptSockets();
	closeServerSocket();
    }

    /**
     * Ein ServerSocket wird erzeugt
     */
    private void createServerSocket() {
	try {
	    serverSocket = new ServerSocket(port);
	} catch (IOException e) {
	    // TODO Wie Server beenden
	    e.printStackTrace();
	}
    }

    /**
     * Der ServerSocket wird geschlossen
     */
    private void closeServerSocket() {
	try {
	    serverSocket.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /**
     * Es werden Sockets angenommen und "verarbeitet".
     */
    private void acceptSockets() {
	int currentPlayerNum = 2;
	Socket newSocket;
	while (currentPlayerNum <= playerNum && !isInterrupted()) {
	    try {
		newSocket = serverSocket.accept();
		handleNewSocket(newSocket, currentPlayerNum);
		currentPlayerNum++;
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    /**
     * Ein neuer Socket wird bearbeitet. Zunaechst wird die erwartete
     * LoginMessage abgerufen, danach eine Connection erzeugt und gespeichert.
     * 
     * @param newSocket
     * @param playerId
     */
    private void handleNewSocket(Socket newSocket, int playerId) {

	Connection connection = new Connection(newSocket, playerId,
		messageQueue, notifyObject);
	try {
	    connection.receiveLoginMessage();
	} catch (NetworkException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	connection.start();
	connectionMap.put(playerId, connection);
    }

    
    /**
     * Alle zugehoerigen Threads sollen gestoppt werden
     */
    //FIXME in das Interface?
    public void stopServer() {
	this.interrupt();
	for (Connection con : connectionMap.values()) {
	    con.interrupt();
	}
    }

}
