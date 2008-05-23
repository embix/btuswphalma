package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;
import com.googlecode.btuswphalma.gameengine.IManager;

/**
 * Hauptklasse der Server-Engine
 * 
 * Warnungen da noch nicht vollständig.
 * 
 * @author ASM
 */
public class Dispatcher extends Thread implements IDispatcher, IGuiCom {

    private IManager manager;

    private INetCom network;

    private NetworkListener netThread;

    private java.util.Vector<IGuiListener> listeners = new java.util.Vector<IGuiListener>();

    private java.util.concurrent.ArrayBlockingQueue<IMessage> msgQueue
    		= new java.util.concurrent.ArrayBlockingQueue<IMessage>(8);

    private boolean die = false;

    /**
     * Dispatcher Konstruktor Erzeugt interne Objekte
     */
    public Dispatcher() {
	// TODO: IManager holen
	// TODO: Netzwerkobjekt erzeugen
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
    }

    /**
     * Leitet eine Nachricht an den entsprechenden Empfänger
     * 
     * @param msg
     *                Zu verteilende Nachricht
     */
    private void dispatchMessage(IMessage msg) {
	int msgDest = msg.getDestination();

	// TP2: Sämtliche Spieler relevanten Nachrichten werden an die GUI
	// geschickt.
	// Daher auch die "seltsamen" Konstrukte if( true ) usw...
	// Das muss für TP3 angepasst werden!

	// msgDest == -1: Nachricht für Spielengine
	// msgDest == 0: Broadcast
	// msgDest > 0: Nachricht für entsprechenden Spieler
	if (msgDest == -1) {
	    manager.acceptMessage(msg);
	} else {
	    if (/* msgDest <= 1 */true) {
		for (int i = 0; i < listeners.size(); i++) {
		    listeners.get(i).recvdMessage(msg);
		}
	    }

	    if (/* msgDest == 0 || msgDest > 1 */false) {
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
