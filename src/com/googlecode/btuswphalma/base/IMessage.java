package com.googlecode.btuswphalma.base;

import java.io.Serializable;

/**
 * Basisschnittstelle fuer Nachrichtenobjekte
 * 
 * @author ASM
 */
public interface IMessage extends Serializable {
    
    /**
     * Gibt den Nachrichtentyp zurueck.
     * 
     * @return	Nachrichtentyp der Nachricht
     */
    public MessageType getType();
    
    /**
     * Gibt die ID des Empfaengers der Nachricht zurueck.
     * 
     * @return	ID des Empfaengers der Nachricht
     */
    public int getDestination();
    
    /**
     * Gibt die ID des Absenders der Nachricht zurueck.
     * 
     * @return	ID des Absenders der Nachricht.
     */
    public int getSource();

}
