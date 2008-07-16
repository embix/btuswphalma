package com.googlecode.btuswphalma.base;

/**
 * Basisschnittstelle fuer Engine-Abhoer Objekte fuer GUIs
 * 
 * @author ASM
 */
public interface IGuiListener {

    /**
     * Sendet eine Nachricht
     * 
     * @param msg
     *                Zu sendende Nachricht
     */
    public void recvdMessage(IMessage msg);

}
