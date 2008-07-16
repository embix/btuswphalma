/**
 * 
 */
package com.googlecode.btuswphalma.base;

import com.googlecode.btuswphalma.gameengine.ScoreList;

/**
 * ScoreMessage, entsprechend MT_SCORES
 * 
 * @author embix
 * @see com.googlecode.btuswphalma.base.IMessage
 * @see com.googlecode.btuswphalma.base.MessageType
 */
public class ScoreMessage extends AbstractMessage {

    /**
     * generierte UID
     */
    private static final long serialVersionUID = -3469696818605366797L;
    private ScoreList scores;
    
    /**
     * Erzeugt eine neue Nachricht vom Typ ScoreMessage
     * 
     * @param source Sender als int-ID
     * @param destination Ziel als int-ID
     * @param scores die zu uebertragenen Spielergebnisse
     */
    public ScoreMessage(int source, int destination, ScoreList scores) {
	super(source, destination);
	this.scores = scores;
    }

    /**
     * Nachrichtentyp zurueckgeben
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	return MessageType.MT_SCORES;
    }

    /**
     * Getter fuer die scores
     * @return gibt die ScoreList mit Spielergebnissen
     */
    public ScoreList getScoreList(){
	return scores;
    }
}
