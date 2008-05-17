package com.googlecode.btuswphalma.base;

/**
 * Basisschnittstelle fuer das Netzwerksystem
 * 
 * @author ASM
 */
public interface INetCom {

    /**
     * Versendet Nachrichten über ein Netzwerkobjekt
     * 
     * @param msg
     *                Zu sendende Nachricht
     */
    public void sendMessage(IMessage msg);

    /**
     * Gibt ein Notifikationsobjekt zurück, über welches Listener über neue
     * Nachrichten vom Netzwerk informiert werden.
     * 
     * @return Notifikationsobjekt
     */
    public Object getNotifyObject();

    /**
     * Prüft, ob Netwerknachrichten in der Warteschlange sind
     * 
     * @return true, falls Nachrichtenobjekte in der Warteschlange stehen; false
     *         sonst
     */
    public boolean hasMessage();

    /**
     * Gibt das nächste in der Warteschlange stehende Objekt zurück
     * 
     * @return Das nächste Nachrichtenobjekt
     */
    public IMessage getMessage();

}
