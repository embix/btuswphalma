package com.googlecode.btuswphalma.gameengine;

/**
 * Beinhaltet einen Spielwertungseintrag (score entry) mit den Werten:
 * Spielername, Spielerwertung (Rang) und benoetigte Runden
 * @author Christoph 
 */
public class ScoreEntry {

	private int ranking;

	private String name;

	private int rounds;

	/**
	 * Konstruktor fuer einen Eintrag mit folgenden Werten:
	 * @param rank int (Rang)
	 * @param pname String (Spielername)
	 * @param round int (benoetigte Runden)
	 */
	public ScoreEntry(int rank, String pname, int round) {
		this.ranking = rank;
		this.name = pname;
		this.rounds = round;
	}

	/**
	 * gibt Spielerrang aus
	 * @return int
	 */
	public int getRanking() {
		return this.ranking;
	}

	
	/**
	 * gibt Spielernamen aus
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * gibt Runden aus, die der Spieler gebraucht hat 
	 * @return int
	 */
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
	 
	 public void setName(String pname) { 
	 	this.name = pname; 
	 }
	  
	 public void setRounds(int round) { 
	 	this.rounds = round; 
	 }*/
}
