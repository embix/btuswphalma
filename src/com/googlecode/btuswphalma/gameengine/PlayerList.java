package com.googlecode.btuswphalma.gameengine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Beinhaltet die Liste mit den Spielerdaten, 1-6 Eintraege
 * 
 * @author Christoph
 * 
 */
public class PlayerList implements Serializable {

    /**
     * generierte UID
     */
    private static final long serialVersionUID = -8698942119379573898L;
    
    /**
     * ArrayList(Player), initial leer
     */
    private ArrayList<Player> players;

    /**
     * Konstruktor, Anzahl der Spieler bestimmt Feldgroesse
     * 
     * @param num
     *                int (Spieleranzahl)
     */
    public PlayerList(int num) {
	this.players = new ArrayList<Player>(num);
    }

    /**
     * leerer Konstruktor
     */
    public PlayerList() {
	players = new ArrayList<Player>();
    }

    /**
     * gibt komplette Spielerliste aus
     * 
     * @return ArrayList(Player)
     */
    public ArrayList<Player> getPlayers() {
	return this.players;
    }

    /**
     * gibt einen Spieler aus, anhand der Position im Feld
     * 
     * @param id
     *                int (zur Positionsberechnung)
     * @return Player
     */
    public Player getPlayer(int id) {
	if ((id <= 0) || (id > players.size())) {
	    System.err.println("Position ausserhalb der Liste!");
	    return null;
	} else {
	    return players.get(id - 1);
	}
    }

    /**
     * ersetzt einen Spielereintrag, dabei seine ID = Position in der Liste
     * 
     * @param play
     *                Player
     */
    public void setPlayer(Player play) {
	int pos = play.getID() - 1;
	if (pos < players.size()) {
	    this.players.remove(pos);
	    this.players.add(pos, play);
	} else {
	    play.setID(players.size() + 1);
	    this.players.add(play);
	}
    }

    /**
     * fuegt einen Spieler an das Ende der Liste an
     * 
     * @param play
     *                Player
     */
    public void addPlayer(Player play) {
	this.players.add(play);
	sortPlayers();
    }

    private void sortPlayers() {
	Comparator<Player> playerIDComperator = new Comparator<Player>() {

	    public int compare(Player player1, Player player2) {
		int idPlayer1 = player1.getID();
		int idPlayer2 = player2.getID();
		/*
		 * Kein einfaches Abziehen der Werte, da Ueberlauf (allgemein,
		 * hier eher nicht) moeglich
		 */
		if (idPlayer1 < idPlayer2) {
		    return -1;
		} else if (idPlayer1 == idPlayer2) {
		    return 0;
		} else {
		    return 1;
		}
	    }
	};
	Collections.sort(players, playerIDComperator);
    }

    /**
     * gibt Groesse der Liste aus
     * 
     * @return int
     */
    public int getSize() {
	return this.players.size();
    }
}
