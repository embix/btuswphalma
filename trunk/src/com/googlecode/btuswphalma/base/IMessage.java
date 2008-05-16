/**
 * Basisschnittstelle für Nachrichtenobjekte
 * 
 */

package com.googlecode.btuswphalma.base;

public interface IMessage {
    
    public MessageType getType();
    
    public int getDestination();
    
    public int getSource();

}
