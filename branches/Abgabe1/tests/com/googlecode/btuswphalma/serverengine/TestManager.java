/**
 * 
 */
package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;
import com.googlecode.btuswphalma.gameengine.IManager;

/**
 * Implementation des IManager Interfaces fuer Testzwecke
 * 
 * @author ASM
 */
public class TestManager extends TestBaseObject implements IManager {

    private IDispatcher dispatcher;

    /**
     * @param d
     *                Dispatcher-Objekt, an welches Nachrichten geschickt werden
     * @param t
     *                Spieler, der die Nachrichten erhalten soll
     * @param n
     *                Anzahl der zu versendenden Nachrichten
     */
    public TestManager(IDispatcher d, int t, int n) {
	super(MessageType.MT_BOARD, n, -1, t, "TestManager");
	dispatcher = d;
    }

    /**
     * @see com.googlecode.btuswphalma.gameengine.IManager#acceptMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void acceptMessage(IMessage msg) {
	trackMessage(msg);
    }

    protected void dispatchMessage(IMessage msg) {
	dispatcher.acceptMessage(msg);
    }
}
