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
     * Der gewuenschte Name des Benutzers
     */
    private String name;
    

    /**
     * Die Login Message wird erzeugt
     * 
     * @param name gewuenschter Name
     * @param source die eigene ID
     * @param destination die Ziel ID
     */
    public LoginMessage(String name, int source, int destination) {
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
