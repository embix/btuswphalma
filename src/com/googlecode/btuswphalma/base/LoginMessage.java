/**
 * 
 */
package com.googlecode.btuswphalma.base;

/**
 * Die LoginMessage wird von einem Client (oder der Server GUI) geschickt, um
 * sich an einem Spiel anzumelden.
 * 
 * @author sebastian
 * 
 */
public class LoginMessage extends AbstractMessage {

    /**
     * generierte UID
     */
    private static final long serialVersionUID = 4028803987744953231L;
    /**
     * Der gewuenschte Name des Benutzers
     */
    private String name;
    

    /**
     * Die Login Message wird erzeugt
     * @param source die eigene ID
     * @param destination die Ziel ID
     * @param name gewuenschter Name
     */
    public LoginMessage(int source, int destination, String name) {
	super(source, destination);
	this.name = name;
    }

    

    /**
     * Gibt den gewuenschten Namen zurueck
     * 
     * @return der gewuenschte Name
     */
    public String getName() {
	return name;
    }


    
    /**
     * Vom Typ Login
     * 
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	return MessageType.MT_LOGIN;
    }

}
