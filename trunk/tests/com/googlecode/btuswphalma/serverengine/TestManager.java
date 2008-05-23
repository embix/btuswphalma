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
public class TestManager implements IManager {

    /**
     * @see com.googlecode.btuswphalma.gameengine.IManager#acceptMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void acceptMessage(IMessage msg) {
	System.out.println("TestManager: Nachricht erhalten...\n" + "Typ: "
		+ msg.getType().toString() + "\n" + "Quelle: "
		+ msg.getSource() + "\n" + "Ziel: " + msg.getDestination());
    }

}
