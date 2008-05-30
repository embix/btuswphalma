package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;
import com.googlecode.btuswphalma.gameengine.IManager;

/**
 * Hauptklasse der Server-Engine
 * 
 * @author ASM
 */
public class Dispatcher extends Thread implements IDispatcher, IGuiCom {

    private IManager manager;

    private INetCom network;

    private NetworkListener netThread;

    private java.util.Vector<IGuiListener> listeners = new java.util.Vector<IGuiListener>();

    private java.util.concurrent.ArrayBlockingQueue<IMessage> msgQueue =
		new java.util.concurrent.ArrayBlockingQueue<IMessage>(32);

    private boolean die = false;

    private boolean dispatcherNetTest = false; // TODO: wird fuer TP3 nicht mehr
						// benötigt

    /**
     * Dispatcher Konstruktor Erzeugt interne Objekte
     */
    public Dispatcher() {
	// TODO: Manager erzeugen
	// TODO: Netzwerkobjekt/NetworkListener erzeugen und starten (TP 3)
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
	dispatcherNetTest = true;

	System.out.println("Starte Netzwerknachrichtenüberwachung...");
	netThread = new NetworkListener(n, manager);
	netThread.start();
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
	msgQueue.add(msg);
    }

    /**
     * Fordert den Dispatcher Thread auf, sich zu beenden
     */
    public void terminate() {
	die = true;

	// TODO: Check nicht mehr notwendig fuer TP 3
	if (dispatcherNetTest) {
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
	    // TODO: Check auf dispatcherNetTest ist fuer TP3 nicht mehr
	    // notwendig und wird da entfernt
	    if (!dispatcherNetTest || msgDest <= 1) {
		for (int i = 0; i < listeners.size(); i++) {
		    listeners.get(i).recvdMessage(msg);
		}
	    }

	    if (dispatcherNetTest && (msgDest == 0 || msgDest > 1)) {
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
