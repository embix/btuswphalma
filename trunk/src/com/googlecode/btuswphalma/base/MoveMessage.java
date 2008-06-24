/**
 * 
 */
package com.googlecode.btuswphalma.base;

import com.googlecode.btuswphalma.gameengine.HalmaMove;

/**
 * MoveMessage, entsprechend MT_MOVE
 * 
 * @author embix
 * @see com.googlecode.btuswphalma.base.IMessage
 * @see com.googlecode.btuswphalma.base.MessageType
 */
public class MoveMessage extends AbstractMessage {

    /**
     * generierte UID
     */
    private static final long serialVersionUID = 8296735754599422482L;
    private HalmaMove move;
    
    /**
     * * Erzeugt eine neue Nachricht vom Typ MoveMessage
     * 
     * @param source Sender als int-ID
     * @param destination Ziel als int-ID
     * @param move gibt den zu verschickenden Spielzug an
     */
    public MoveMessage(int source, int destination, HalmaMove move) {
	super(source, destination);
	this.move = move;
    }

    /**
     * gibt den Nachrichtentyp zurueck
     * 
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	return MessageType.MT_MOVE;
    }

    /**
     * getter fuer den Nachrichteninhalt, also den
     * Spielspielzug als HalmaMove
     * @return gibt den Spielzug an
     */
    public HalmaMove getMove() {
	return move;
    }

}
