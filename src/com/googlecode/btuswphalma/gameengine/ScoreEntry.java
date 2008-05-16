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
	 * @param rank
	 *            Rang in Spielwertung
	 * @param spname
	 *            Spielername
	 * @param runden
	 *            Runde in der der Spieler gewonnen hat
	 */
	public ScoreEntry(int rank, String spname, int runden) {
		this.ranking = rank;
		this.name = spname;
		this.rounds = runden;
	}

	/**
	 * @return Ausgabe des Ranges
	 */
	public int getRanking() {
		return this.ranking;
	}

	
	/**
	 * @return Ausgabe des Spielernamens
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return Ausgabe der Runden die der Spieler gebraucht hat
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
	 
	 public void setName(String spname) { 
	 	this.name = spname; 
	 }
	  
	 public void setRounds(int runden) { 
	 	this.rounds = runden; 
	 }*/
}
