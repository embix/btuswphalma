package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;
import com.googlecode.btuswphalma.gameengine.IManager;
import com.googlecode.btuswphalma.gameengine.Manager;
import com.googlecode.btuswphalma.network.ServerNetCom;
import com.googlecode.btuswphalma.network.ClientNetCom;
import java.net.InetAddress;

/**
 * Hauptklasse der Server-Engine
 * 
 * @author ASM
 */
public class Dispatcher extends Thread implements IDispatcher, IGuiCom {

    private IManager manager;
    
    private Thread managerThread;

    private INetCom network;

    private NetworkListener netThread;

    private java.util.Vector<IGuiListener> listeners = new java.util.Vector<IGuiListener>();

    private java.util.concurrent.ArrayBlockingQueue<IMessage> msgQueue =
		new java.util.concurrent.ArrayBlockingQueue<IMessage>(128);

    private boolean die = false;
    
    private boolean server;
    
    private boolean hotseat;

    /**
     * Dispatcher Konstruktor Erzeugt interne Objekte
     */
    public Dispatcher() {
    }

    /**
     * Nur für Testzwecke
     * 
     * @param m
     */
    public void setManager(IManager m) {
	manager = m;
    }

    /**
     * Nur für Testzwecke
     * 
     * @param n
     */
    public void setNetwork(INetCom n) {
	network = n;
	hotseat = true;

	System.out.println("Starte Netzwerknachrichtenüberwachung...");
	netThread = new NetworkListener(n, this);
	netThread.start();
    }
    
    /**
     * Instanziert den Manager der Spielengine
     * 
     * @param numberOfPlayers
     * 				Anzahl der teilnehmenden Spieler
     * @param server
     * 				Server- oder Clientengine instanzieren
     * @param hs
     * 				Hotseat oder Netzwerkmodus
     */
    public void createManager(int numberOfPlayers, boolean server, boolean hs) {
	if(server) {
	   manager = new Manager(numberOfPlayers,this); 
	}
	else {
	    manager = null;
	}
	
	this.server = server;
	this.hotseat = hs;
	
	// Manager müssen immer von Runnable abgeleitet werden
	managerThread = new Thread((Runnable)manager);
	managerThread.start();
    }
    
    /**
     * Instanziert das Netzwerksystem
     * @param ip
     * 				IP Adresse des Servers (nur notwendig wenn server)
     * @param port
     * 				Port zu dem verbunden bzw. an dem gelaucht werden soll
     * 
     * @param numOfPlayers
     * 				Anzahl der teilnehmenden Spieler (nur notwendig wenn server)
     * @param server
     * 				Gibt an, ob das Server- oder Clientnetzwerk
     * 				instanziert werden soll
     */
    public void createNetwork(InetAddress ip, int port, int numOfPlayers, boolean server) {
	if(server) {
	    network = new ServerNetCom(port, numOfPlayers);
	}
	else {
	    network = new ClientNetCom(ip, port);
	}
	
	netThread = new NetworkListener(network, this);
	netThread.start();
    }
    
    private void dispatchToGui(IMessage msg) {
	for (int i = 0; i < listeners.size(); i++) {
	    listeners.get(i).recvdMessage(msg);
	}
    }
    
    /**
     * Verteilt Netzwerknachrichten
     * 
     * @param msg 	Zu verteilende Nachricht
     */
    public void dispatchNetworkMessage(IMessage msg) {
	if(server) {
	    manager.acceptMessage(msg);
	}
	else {
	    dispatchToGui(msg);
	}
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.googlecode.btuswphalma.serverengine.IDispatcher#acceptMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void acceptMessage(IMessage msg) {
	msgQueue.add(msg);
    }

    /**
     * @see com.googlecode.btuswphalma.base.IGuiCom#registerGuiListener(com.googlecode.btuswphalma.base.IGuiListener)
     */
    public void registerGuiListener(IGuiListener lst) {
	listeners.add(lst);
    }

    /**
     * @see com.googlecode.btuswphalma.base.IGuiCom#unregisterGuiListener(com.googlecode.btuswphalma.base.IGuiListener)
     */
    public void unregisterGuiListener(IGuiListener lst) {
	listeners.remove(lst);
    }

    /**
     * @see com.googlecode.btuswphalma.base.IGuiCom#recvMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void recvMessage(IMessage msg) {
	if(server) {
	    msgQueue.add(msg);
	}
	else {
	    // Als Client können die Nachrichten direkt an das Netzwerk geleitet werden
	    network.sendMessage(msg);
	}
    }

    /**
     * Fordert den Dispatcher Thread auf, sich zu beenden
     */
    public void terminate() {
	die = true;

	if (!hotseat) {
	    netThread.terminate();
	}
    }

    /**
     * Leitet eine Nachricht an den entsprechenden Empfänger
     * 
     * @param msg
     *                Zu verteilende Nachricht
     */
    private void dispatchMessage(IMessage msg) {
	int msgDest = msg.getDestination();

	if (msgDest == -1) {
	    manager.acceptMessage(msg);
	} else {
	    if (hotseat || msgDest <= 1) {
		dispatchToGui(msg);
	    }

	    if (!hotseat && (msgDest == 0 || msgDest > 1)) {
		network.sendMessage(msg);
	    }
	}
    }

    /**
     * @see java.lang.Thread#run()
     */
    public void run() {
	IMessage msg = null;

	while (!die) {
	    try {
		msg = msgQueue.poll(250,
			java.util.concurrent.TimeUnit.MILLISECONDS);
	    } catch (InterruptedException e) {
		die = true;
	    }

	    if (msg != null) {
		dispatchMessage(msg);
	    }
	}
    }
}
