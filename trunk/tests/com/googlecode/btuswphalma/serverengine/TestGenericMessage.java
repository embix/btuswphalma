/**
 * 
 */
package com.googlecode.btuswphalma.serverengine;

import com.googlecode.btuswphalma.base.*;

/**
 * Allgemeine Nachrichtenklasse fuer den Server-Engine Test
 * 
 * @author ASM
 */
public class TestGenericMessage implements IMessage {

    private MessageType type;

    private int src;

    private int dst;

    /**
     * @param msgType
     * @param msgSrc
     * @param msgDst
     * @param d
     */
    public TestGenericMessage(MessageType msgType, int msgSrc, int msgDst) {
	type = msgType;
	src = msgSrc;
	dst = msgDst;

	// data = d;
    }

    /**
     * @see com.googlecode.btuswphalma.base.IMessage#getDestination()
     */
    public int getDestination() {
	return dst;
    }

    /**
     * @see com.googlecode.btuswphalma.base.IMessage#getSource()
     */
    public int getSource() {
	return src;
    }

    /**
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	return type;
    }
}
