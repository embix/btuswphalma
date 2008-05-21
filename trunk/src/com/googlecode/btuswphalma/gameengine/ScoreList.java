package com.googlecode.btuswphalma.gameengine;

import java.util.ArrayList;

/**
 * Beinhaltet die Spielerwertungsliste, die aus bis zu 6 Eintraegen besteht
 * @author Christoph
 */
public class ScoreList {

	/**
	 * ArrayList(ScoreEntry)
	 */
	private ArrayList<ScoreEntry> scores;

	/**
	 * Konstruktor, Anzahl der Spieler bestimmt Feldgroeﬂe
	 * @param num
	 *            int (Spieleranzahl)
	 *
	public ScoreList(int num) {
	this.scores = new ArrayList<ScoreEntry>(num);
	}*/

	/**
	 * leerer Konstruktor
	 */
	public ScoreList() { }
	
	/**
	 * gibt die komplette Spielerwertungsliste aus
	 * @return ArrayList(ScoreEntry)
	 */
	public ArrayList<ScoreEntry> getScoreList() {
	return this.scores;
	}

	/**
	 * gibt einen Eintrag, anhand der Position im Feld, aus
	 * @param rank
	 *            int
	 * @return ScoreEntry 
	 */
	public ScoreEntry getEntry(int rank) {
	return scores.get(rank - 1);
	}

	/**
	 * fuegt einen Eintrag in das Feld ein, Rang bestimmt dabei dessen Position
	 * im Feld
	 * @param entry ScoreEntry
	 */
	public void addEntry(ScoreEntry entry) {
	int pos = entry.getRanking() - 1;
	this.scores.add(pos, entry);
	}
	
	/**
	 * fuegt einen Eintrag an das Ende der Liste
	 */
	public void addEntryToEnd(ScoreEntry entry) {
	this.scores.add(entry);
	}
}
