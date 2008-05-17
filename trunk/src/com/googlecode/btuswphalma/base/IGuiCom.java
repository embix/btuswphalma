package com.googlecode.btuswphalma.base;

/**
 * Basisschnittstelle der Server-Engine fuer GUIs
 * 
 * @author ASM
 */
public interface IGuiCom {

    /**
     * Registriert ein Abhoerobjekt für Engine-Nachrichten
     * 
     * @param lst
     *                Zu registrierendes Objekt
     */
    public void registerGuiListener(IGuiListener lst);

    /**
     * Löscht ein zuvor registriertes Abhoerobjekt
     * 
     * @param lst
     *                Zu loeschendes Objekt
     */
    public void unregisterGuiListener(IGuiListener lst);

    /**
     * Sendet eine Nachricht an die Server-Engine
     * 
     * @param msg
     *                Zu sendende Nachricht
     */
    public void recvMessage(IMessage msg);

}
