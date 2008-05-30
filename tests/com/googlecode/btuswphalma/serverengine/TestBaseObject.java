/**
 * 
 */
package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;

/**
 * Basisobjekt für Server-Engine Test
 * 
 * @author ASM
 */
public abstract class TestBaseObject extends Thread {

    private MessageType msgType;
    private int numMsg; // Anzahl der zu sendenden Nachrichten
    private int src; // Quelle
    private int dst; // Ziel
    private String objectName; // Name des Objekts

    private int numRecvMsg; // Anzahl der empfangenen Nachrichten

    protected TestBaseObject(MessageType mt, int nm, int s, int d, String n) {
	msgType = mt;
	numMsg = nm;
	src = s;
	dst = d;
	objectName = n;

	numRecvMsg = 0;
    }

    protected void trackMessage(IMessage msg) {
	numRecvMsg++;

	System.out.println(objectName + ": Nachricht erhalten...\n" + "  Typ: "
		+ msg.getType().toString() + "\n" + "  Quelle: "
		+ msg.getSource() + "\n" + "  Ziel: " + msg.getDestination());
    }

    // Abstrahiert die eigentliche Versendemethode
    protected abstract void dispatchMessage(IMessage msg);

    /**
     * Gibt Objektstatistiken aus
     */
    public void printStats() {
	System.out.println(objectName + " received " + numRecvMsg
		+ " messages.");
    }

    /**
     * @see java.lang.Thread#run()
     */
    public void run() {
	IMessage msg = new TestGenericMessage(msgType, src, dst);

	for (int i = 0; i < numMsg; i++) {
	    dispatchMessage(msg);
	    try {
		sleep(500);
	    } catch (InterruptedException e) {
	    }
	}
    }
}
