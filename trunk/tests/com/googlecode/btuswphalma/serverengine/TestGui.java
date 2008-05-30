/**
 * 
 */
package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;

/**
 * GUI Objekt fuer Server-Engine Tests
 * 
 * @author ASM
 */
public class TestGui extends TestBaseObject implements IGuiListener {

    private IGuiCom guiCom;

    /**
     * @param g
     *                IGuiCom Objekt, welches die Nachrichten erhalten soll
     * @param n
     *                Anzahl der zu sendenden Nachrichten
     */
    public TestGui(IGuiCom g, int n) {
	super(MessageType.MT_MOVE, n, 1, -1, "TestGui");
	guiCom = g;
    }

    /**
     * @see com.googlecode.btuswphalma.base.IGuiListener#recvdMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void recvdMessage(IMessage msg) {
	trackMessage(msg);
    }

    protected void dispatchMessage(IMessage msg) {
	guiCom.recvMessage(msg);
    }
}
