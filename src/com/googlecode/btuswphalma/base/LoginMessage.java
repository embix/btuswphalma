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
public class LoginMessage implements IMessage {

    /**
     * Der gewuenschte Name des Benutzers
     */
    private String name;
    /**
     * Die ID des Quell-Clients
     */
    private int source;
    /**
     * Die ID des Ziel-Clients
     */
    private int destination;

    /**
     * Die Login Message wird erzeugt
     * 
     * @param name gewuenschter Name
     * @param source die eigene ID
     * @param destination die Ziel ID
     */
    public LoginMessage(String name, int source, int destination) {
	super();
	this.name = name;
	this.source = source;
	this.destination = destination;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.googlecode.btuswphalma.base.IMessage#getDestination()
     */
    public int getDestination() {
	return destination;
    }

    /**
     * Gibt den gewuenschten Namen zurueck
     * 
     * @return
     */
    public String getName() {
	return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.googlecode.btuswphalma.base.IMessage#getSource()
     */
    public int getSource() {
	return source;
    }

    /**
     * Mit dieser Methode wird die Quelle einer Nachricht gesetzt. Dies ist
     * noetig, da ein Client beim anmelden seine Nummer noch nicht kennt und
     * also nicht die richtige Nummer angeben kann.
     * 
     * @param source
     *                the source to set
     */
    public void setSource(int source) {
	this.source = source;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.googlecode.btuswphalma.base.IMessage#getType()
     */
    public MessageType getType() {
	return MessageType.MT_LOGIN;
    }

}
