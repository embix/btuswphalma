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
     * 
     */
    private static final long serialVersionUID = -927805455352322228L;
    /**
     * Der gewuenschte Name des Benutzers
     */
    private String name;
   
    
    public LoginMessage() {
	// TODO Auto-generated constructor stub
    }

    /**
     * Die Login Message wird erzeugt
     * 
     * @param name gewuenschter Name
     * @param source die eigene ID
     * @param destination die Ziel ID
     */
    public LoginMessage(String name, int source, int destination) {
	super(source,destination);
	this.name = name;
    }

    /**
     * Gibt den gewuenschten Namen zurueck
     * 
     * @return
     */
    public String getName() {
	return name;
    }


    

    /**
     * (non-Javadoc)
     * 
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	return MessageType.MT_LOGIN;
    }

}
