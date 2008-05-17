package com.googlecode.btuswphalma.base;

/**
 * Basisschnittstelle fuer Nachrichtenobjekte
 * 
 * @author ASM
 */
public interface IMessage {
    
    /**
     * Gibt den Nachrichtentyp zurück.
     * 
     * @return	Nachrichtentyp der Nachricht
     */
    public MessageType getType();
    
    /**
     * Gibt die ID des Empfängers der Nachricht zurück.
     * 
     * @return	ID des Empfängers der Nachricht
     */
    public int getDestination();
    
    /**
     * Gibt die ID des Absenders der Nachricht zurück.
     * 
     * @return	ID des Absanders der Nachricht.
     */
    public int getSource();

}
