/**
 * 
 */
package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;

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
    
    private Dispatcher dispatcher;

    private boolean die = false; // wird gesetzt wenn der Thread beenden soll

    /**
     * Konstruiert ein NetworkListener
     * 
     * @param n
     *                Die Netzwerkkomponente, von der Nachrichten abzuholen sind
     * @param d	
     * 			Dispatcher ueber den Nachrichten versendet werden
     */
    public NetworkListener(INetCom n, Dispatcher d) {
	network = n;
	dispatcher = d;

	netNotifyObject = n.getNotifyObject();
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
		synchronized (netNotifyObject) {
		    netNotifyObject.wait(250);
		}
	    } catch (InterruptedException e) {
		die = true;
	    }

	    while (network.hasMessage()) {
		IMessage msg = network.getMessage();
		
		dispatcher.dispatchNetworkMessage(msg);
	    }
	}
    }
}
