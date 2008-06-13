/**
 * 
 */
package com.googlecode.btuswphalma.base;

/**
 * Eine GameEndMessage zeigt den Clients an, dass das Spiel vorbei ist.
 * @author sebastian
 *
 */
public class GameEndMessage extends AbstractMessage {

    /* (non-Javadoc)
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    @Override
    public MessageType getType() {
	return MessageType.MT_GAMEEND;
    }

    /**
     * Es wird nur der Konstruktor der Superklasse aufgerufen.
     * @param source
     * @param destination
     */
    public GameEndMessage(int source, int destination) {
	super(source, destination);
    }

}
