/**
 * 
 */
package com.googlecode.btuswphalma.base;

import com.googlecode.btuswphalma.gameengine.Board;

/**
 * BoardMessage, entsprechend MT_BOARD
 * 
 * @author embix
 * @see com.googlecode.btuswphalma.base.IMessage
 * @see com.googlecode.btusqphalme.base.MessageType
 */
public class BoardMessage extends AbstractMessage {

    private Board board;
    
    public BoardMessage() {
	// TODO Auto-generated constructor stub
    }
    
    /**
     * Erzeugt eine neue Nachricht vom Typ BoardMessage
     * 
     * @param source Sender als int-ID
     * @param destination Ziel als int-ID
     * @param board gibt die zu verschickende Auftstellung an
     */
    public BoardMessage(int source, int destination, Board board) {
	super(source, destination);
	this.board = board;
    }

    /**
     * gibt den Nachrichtentyp zurueck
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	return MessageType.MT_BOARD;
    }

    /**
     * getter fuer den Nachrichteninhalt, also die
     * Spielaufstellung als Board-Objekt
     * @return gibt das neue Board an
     */
    public Board getBoard(){
	return board;
    }
}
