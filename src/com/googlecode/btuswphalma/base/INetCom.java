package com.googlecode.btuswphalma.base;

/**
 * Basisschnittstelle fuer das Netzwerksystem
 * 
 * @author ASM
 */
public interface INetCom {

    /**
     * Versendet Nachrichten ueber ein Netzwerkobjekt
     * 
     * @param msg
     *                Zu sendende Nachricht
     */
    public void sendMessage(IMessage msg);

    /**
     * Gibt ein Notifikationsobjekt zurueck, ueber welches Listener ueber neue
     * Nachrichten vom Netzwerk informiert werden.
     * 
     * @return Notifikationsobjekt
     */
    public Object getNotifyObject();

    /**
     * Prueft, ob Netzwerknachrichten in der Warteschlange sind
     * 
     * @return true, falls Nachrichtenobjekte in der Warteschlange stehen; false
     *         sonst
     */
    public boolean hasMessage();

    /**
     * Gibt das naechste in der Warteschlange stehende Objekt zuraeck
     * 
     * @return Das naechste Nachrichtenobjekt
     */
    public IMessage getMessage();

}
