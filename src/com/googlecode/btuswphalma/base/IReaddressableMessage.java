/**
 * 
 */
package com.googlecode.btuswphalma.base;

/**
 * Mit Dieser Erweiterung der {@link IMessage} ist kann man Sender und
 * Empfaenger setzen
 * 
 * @author sebastian
 * 
 */
public interface IReaddressableMessage extends IMessage {

    /**
     * Setzt die ID des Empfaengers der Nachricht
     * 
     * @param destination
     *                ID des Empfaengers der Nachricht
     * 
     */
    public void setDestination(int destination);

    /**
     * Setzt die ID des Empfaengers der Nachricht
     * 
     * @param source
     *                ID des Empfaengers der Nachricht
     */
    public void setSource(int source);

}
