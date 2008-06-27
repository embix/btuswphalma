/**
 * 
 */
package com.googlecode.btuswphalma.gui;

import java.net.InetAddress;

/**
 * ClientGameData kapselt die Daten der Spieler, die nicht das Spiel leiten,
 * welche zum Spielbeginn benoetigt werden. Das Objekt ist vorerst nicht
 * gekapselt, da es momentan noch nicht sinnvoll erscheint.
 * 
 * @author embix
 * @author ASM
 * 
 */
public class ClientGameData {

    /**
     * Name des Spielers
     */
    public String playerName;
    
    /**
     * Server IP Adresse
     */
    public InetAddress ip;
    
    /**
     * Port des Servers
     */
    public int port;

}
