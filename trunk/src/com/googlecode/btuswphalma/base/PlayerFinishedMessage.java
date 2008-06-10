package com.googlecode.btuswphalma.base;
/**
 * 
 */

/**
 * Eine PlayerFinishedMessage zeigt einem Client an, dass er fertig ist, und
 * nicht mehr Spielen wird.
 * 
 * @author sebastian
 * 
 */
public class PlayerFinishedMessage extends AbstractMessage {

    /**
     * @param source
     * @param destination
     */
    public PlayerFinishedMessage(int source, int destination) {
	super(source, destination);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	// TODO Auto-generated method stub
	return MessageType.MT_PLAYERFINISHED;
    }

}
