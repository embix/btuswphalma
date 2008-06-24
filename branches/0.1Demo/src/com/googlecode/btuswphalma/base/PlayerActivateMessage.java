/**
 * 
 */
package com.googlecode.btuswphalma.base;

/**
 * Durch den Empfang einer Nachricht diesen Typs, weiss ein Client
 * dass jetzt er einen Zug uebermittlen soll.
 * @author sebastian
 *
 */
public class PlayerActivateMessage extends AbstractMessage {

    /**
     * @param source die Quelladresse
     * @param destination die Zieladresse
     */
    public PlayerActivateMessage(int source, int destination) {
	super(source, destination);
    }

    /** (non-Javadoc)
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	return MessageType.MT_PLAYERACTIVATE;
    }

}
