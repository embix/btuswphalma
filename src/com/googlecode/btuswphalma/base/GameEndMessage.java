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

    /**
     * generierte UID
     */
    private static final long serialVersionUID = -5447593640952354229L;

    /**
     * Der Type ist MT_GAMEEND
     * 
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
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
