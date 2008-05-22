package com.googlecode.btuswphalma.gameengine;

import java.util.ArrayList;

/**
 * Beinhaltet die Liste mit den Spielerdaten, 1-6 Eintraege
 * @author Christoph
 *
 */
public class PlayerList {
	
	/**
	 * ArrayList(Player), initial leer
	 */
	private ArrayList<Player> players = new ArrayList<Player>();

	/**
	 * Konstruktor, Anzahl der Spieler bestimmt Feldgroeﬂe 
	 * @param num int (Spieleranzahl)
	 */
	public PlayerList(int num) {
	this.players = new ArrayList<Player>(num);
	}
	
	/**
	 * leerer Konstruktor
	 */
	public PlayerList() { }

	/**
	 * gibt komplette Spielerliste aus
	 * @return ArrayList(Player)
	 */
	public ArrayList<Player> getPlayers() {
	return this.players;
	}
	
	/**
	 * gibt einen Spieler aus, anhand der Position im Feld
	 * @param id int (zur Positionsberechnung)
	 * @return Player
	 */
	public Player getPlayer(int id) {
	if ((id<=0) || (id>players.size())) {
		System.err.println("Position ausserhalb der Liste!");
		return null;
	} else {
		return players.get(id - 1);
	}
	}
	
	/**
	 * ersetzt einen Spielereintrag, dabei seine ID = 
	 * Position in der Liste
	 * @param play Player
	 */
	public void setPlayer(Player play) {
	int pos = play.getID() - 1;
	if (pos < players.size()) {
		this.players.remove(pos);
		this.players.add(pos, play);
	} else {
		play.setID(pos+1);
		this.players.add(play);
	}
	}
	
	/**
	 * fuegt einen Spieler an das Ende der Liste an
	 * @param play Player
	 */
	public void addPlayer(Player play) {
	this.players.add(play);
	}
	
	/**
	 * gibt Groeﬂe der Liste aus
	 * @return int
	 */
	public int getSize() {
		return this.players.size();
	}
}
