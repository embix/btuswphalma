package com.googlecode.btuswphalma.gameengine;

import java.util.ArrayList;

/**
 * Beinhaltet die Liste mit den Spielerdaten, 1-6 Eintraege
 * @author Christoph
 *
 */
public class PlayerList {
	
	/**
	 * ArrayList(Player)
	 */
	private ArrayList<Player> players;

	/**
	 * Konstruktor, Anzahl der Spieler bestimmt Feldgroeﬂe 
	 * @param num int (Spieleranzahl)
	 */
	public PlayerList(int num) {
	this.players = new ArrayList<Player>(num);
	}

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
	return players.get(id - 1);
	}
	
	/**
	 * fuegt einen Spieler, anhand seiner ID, in das Feld ein
	 * @param play Player
	 */
	public void addPlayer(Player play) {
	int pos = play.getID() + 1;
	this.players.add(pos, play);
	}
}
