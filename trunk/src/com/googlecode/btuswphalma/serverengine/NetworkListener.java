/**
 * 
 */
package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;
import com.googlecode.btuswphalma.gameengine.IManager;

/**
 * NetworkListener stellt einen Thread zur verfügung, der das Notifyobjekt der
 * Netzwerkkomponente überwacht und bei ankommenden Nachrichten diese an die
 * Spielengine verschickt.
 * 
 * @author ASM
 */
public class NetworkListener extends Thread {

    private Object netNotifyObject;

    private INetCom network;

    private IManager manager;

    private boolean die = false; // wird gesetzt wenn der Thread beenden soll

    /**
     * Konstruiert ein NetworkListener
     * 
     * @param o
     *                Das Notifyobjekt der Netzwerkkomponente
     * @param n
     *                Die Netzwerkkomponente, von der Nachrichten abzuholen sind
     * @param m
     *                Der Manager, an den Netzwerknachrichten gesendet werden
     *                sollen.
     */
    public NetworkListener(Object o, INetCom n, IManager m) {
	netNotifyObject = o;
	network = n;
	manager = m;
    }

    /**
     * Fordert den Thread auf sich zu beenden.
     */
    public void terminate() {
	die = true;
    }

    /**
     * @see java.lang.Thread#run()
     */
    public void run() {
	while (!die) {
	    try {
		netNotifyObject.wait(250);
	    } catch (InterruptedException e) {
		die = true;
	    }

	    while (network.hasMessage()) {
		IMessage msg = network.getMessage();
		manager.acceptMessage(msg);
	    }
	}
    }
}
