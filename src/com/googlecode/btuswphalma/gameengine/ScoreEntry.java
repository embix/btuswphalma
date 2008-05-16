/** 
 * ScoreEntry.java
 * 
 * Beinhaltet einen Spielwertungseintrag (score entry) mit den Werten:
 * Spielername, Spielerwertung (Rang) und benoetigte Runden
 * 
 * @author Christoph
 */
package com.googlecode.btuswphalma.gameengine;

public class ScoreEntry {

	private int ranking;

	private String name;

	private int rounds;

	/* der Konstruktor ScoreEntry der mit festen Parametern initialisiert wird */
	public ScoreEntry(int rank, String spname, int runden) {
		this.ranking = rank;
		this.name = spname;
		this.rounds = runden;
	}

	/* Ausgabe des Ranges dieses Eintrages */
	public int getRanking() {
		return this.ranking;
	}

	/* Ausgabe des Spielernamens dieses Eintrages */
	public String getName() {
		return this.name;
	}

	/* Ausgabe der Runden die der Spieler gebraucht hat (aktueller Eintrag) */
	public int getRounds() {
		return this.rounds;
	}

	/*
	 * Setz-Methoden die den aktuellen Eintrag nach dessen Erstellung veraendern
	 * koennen 
	 * Verwendung wahrscheinlich nicht noetig 
	 public void setRanking(int rank) { 
	 	this.ranking = rank; 
	 }
	 
	 public void setName(String spname) { 
	 	this.name = spname; 
	 }
	  
	 public void setRounds(int runden) { 
	 	this.rounds = runden; 
	 }*/
}
