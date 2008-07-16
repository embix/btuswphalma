/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

import com.googlecode.btuswphalma.base.AbstractMessage;
import com.googlecode.btuswphalma.base.MessageType;

/**
 * Diese Nachricht signalisiert das Beenden des Spiels
 * 
 * @author sebastian
 *
 */
public class TerminateMessage extends AbstractMessage {

    /**
     * Eine UID
     */
    private static final long serialVersionUID = 3463833203156474797L;

    /**
     * Eine Nachricht wird erzeugt
     * 
     * @param source Die Quelle
     * @param destination Das Ziel
     */
    public TerminateMessage(int source, int destination) {
	super(source, destination);
	// TODO Auto-generated constructor stub
    }

    /**
     * Der Typ ist MT_TERMINATE
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	return MessageType.MT_TERMINATE;
    }

}
