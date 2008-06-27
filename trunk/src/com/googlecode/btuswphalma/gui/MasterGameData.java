/**
 * 
 */
package com.googlecode.btuswphalma.gui;

/**
 * MasterGameData kapselt die Daten des Spielleiters, die fuer den Spielbeginn
 * benoetigt werden. Das Objekt ist vorerst nicht gekapselt, da es momentan noch
 * nicht sinnvoll erscheint.
 * 
 * @author embix
 * 
 */
public class MasterGameData {

    /**
     * Name des Spielers
     */
    public String playerName;

    /**
     * Gesamtzahl der Spieler (Slots)
     */
    public int playerCount;

    /**
     * Spielmodus, Netzwerkspiel wird erst spaeter (TP3) implementiert.
     */
    public GameMode gmod = GameMode.HOT_SEAT;

    /**
     * Anteil der Spielerslots, die durch Computergegner besetzt werden
     */
    public int aiCount = 0; // fuer TP2 und TP3 keine AI
    
    /**
     * Port an dem der Server lauschen soll
     */
    public int port;

}
