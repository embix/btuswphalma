/**
 * 
 */
package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;

/**
 * Implementation des INetCom Interfaces fuer Testzwecke
 * 
 * @author ASM
 */
public class TestNetwork implements INetCom {

    /**
     * @see com.googlecode.btuswphalma.base.INetCom#getMessage()
     */
    public IMessage getMessage() {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * @see com.googlecode.btuswphalma.base.INetCom#getNotifyObject()
     */
    public Object getNotifyObject() {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * @see com.googlecode.btuswphalma.base.INetCom#hasMessage()
     */
    public boolean hasMessage() {
	// TODO Auto-generated method stub
	return false;
    }

    /**
     * @see com.googlecode.btuswphalma.base.INetCom#sendMessage(com.googlecode.btuswphalma.base.IMessage)
     */
    public void sendMessage(IMessage msg) {
	System.out.println("TestNetwork: Nachricht erhalten...\n" + "Typ: "
		+ msg.getType().toString() + "\n" + "Quelle: "
		+ msg.getSource() + "\n" + "Ziel: " + msg.getDestination());
    }

}
