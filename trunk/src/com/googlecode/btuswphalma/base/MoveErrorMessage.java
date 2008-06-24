/**
 * 
 */
package com.googlecode.btuswphalma.base;

/**
 * MoveErrorMessage, entsprechend MT_MOVEERROR
 * 
 * @author embix
 * @see com.googlecode.btuswphalma.base.IMessage
 * @see com.googlecode.btuswphalma.base.MessageType
 */
public class MoveErrorMessage
	extends AbstractMessage {
    
    /**
     * generierte UID
     */
    private static final long serialVersionUID = 4648219282672628053L;
    private String errorString;
    
    /**
     * Erzeugt eine neue Nachricht vom Typ MoveErrorMessage
     * 
     * @param source Sender als int-ID
     * @param destination Ziel als int-ID
     * @param errStr die zu sendende Fehlermeldung
     */
    public MoveErrorMessage(int source, int destination, String errStr) {
	super(source, destination);
	this.errorString = errStr;
    }


    
    /**
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	return MessageType.MT_MOVEERROR;
    }

    /**
     * Getter fuer den errorString
     * @return gibt die Fehlermeldung als String zurueck
     */
    public String getErrorString(){
	return errorString;
    }
}
