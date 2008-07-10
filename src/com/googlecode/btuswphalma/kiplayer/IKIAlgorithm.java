/**
 * 
 */
package com.googlecode.btuswphalma.kiplayer;

import com.googlecode.btuswphalma.gameengine.Board;
import com.googlecode.btuswphalma.gameengine.HalmaMove;
import com.googlecode.btuswphalma.gameengine.PlayerList;

/**
 * Ein KI-Algorithmus berechnet fuer einen Spieler einen gueltigen Zug.
 * Verschiedene Algorithmen sollen von dem selben KIController benutzt werden
 * koennen.
 * 
 * @author sebastian
 * 
 */
public interface IKIAlgorithm {

    /**
     * Aus den Eingaben wird ein gueltiger Spielzug fuer den Spieler berechnet.
     * 
     * @param board
     *                das aktuelle Spielbrett
     * @param playerList
     *                die aktuelle Spielerliste
     * @param activePlayer
     *                der aktive Spieler
     * @return ein gueltiger Spielzug fuer den aktiven Spieler
     */
    public HalmaMove computeMove(Board board, PlayerList playerList,
	    int activePlayer);

}
