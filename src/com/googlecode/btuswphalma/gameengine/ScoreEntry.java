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
	if ((rank<0) || (rank>6)) {
		System.err.println("Rang nicht innerhalb von 1-6, setze default=6.");
		this.ranking = 6;
	} else {
		this.ranking = rank;
	}
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

	/**
	 * setz den Rang des Eintrages
	 * @param rank int 
	 */
	public void setRanking(int rank) { 
	if ((rank<0) || (rank>6)) {
		System.err.println("Rang nicht innerhalb von 1-6, setze default=6.");
		this.ranking = 6;
	} else {
		this.ranking = rank;
	}
	}
	
	/**
	 * setzt den Spielernamen des Eintrages
	 * @param pname String
	 */
	public void setName(String pname) { 
	this.name = pname; 
	}
	 
	/**
	 * setz die Rundenanzahl des Eintrages
	 * @param round int
	 */
	public void setRounds(int round) { 
	this.rounds = round; 
	}
}
