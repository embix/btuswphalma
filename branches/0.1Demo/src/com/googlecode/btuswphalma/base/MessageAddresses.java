/**
 * 
 */
package com.googlecode.btuswphalma.base;

import com.googlecode.btuswphalma.serverengine.Dispatcher;

/**
 * Hier werden an zentraler Stelle einige spezielle Adressen bereitgehalten
 * 
 * @author sebastian
 * 
 */
public class MessageAddresses {
    /**
     * Nachrichten mit dieser Adresse werden vom {@link Dispatcher} an den
     * Manger weitergeleitet. Das Netzwerk kann mit dieser Adresse nichts
     * Anfangen.
     */
    public static final int MANAGER_ADDRESS = -1;
    /**
     * Nachrichten mit dieser Adresse werden vom Dispatcher an alle im bekannten
     * Empfaenger weitergeleitet. Das Netzwerk schickt an alle ihm bekannten
     * Teilnehmer
     */
    public static final int BROADCAST_ADDRESS = 0;
    /**
     * Der Dispatcher schickt Nachrichten mit dieser Adresse stets an die
     * "angeschlossene" GUI. Fuer das Netzwerk ist kein Verhalten spezifiziert.
     */
    public static final int GUI_ADDRESS = 1;
}
