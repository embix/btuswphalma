package com.googlecode.btuswphalma.gameengine;

import java.util.ArrayList;

/**
 * Beinhaltet die Spielerwertungsliste die aus bis zu 6 Eintraegen besteht
 * @author Christoph
 */
public class ScoreList {

	//erzeugen einer ArrayList
	private ArrayList<ScoreEntry> scores;

	/**
	 * @param anz
	 *            Spieleranzahl, bestimmt Feldgroeﬂe
	 */
	public ScoreList(int anz) {
		this.scores = new ArrayList<ScoreEntry>(anz);
	}

	/**
	 * @return gibt die komplette Spielerwertungsliste aus
	 */
	public ArrayList<ScoreEntry> getScoreList() {
		return this.scores;
	}

	/**
	 * @param rank
	 *            Rang des Spielers bestimmt Position im Feld
	 * @return gibt einen Eintrag aus der Liste, anhand der Position im Feld
	 */
	public ScoreEntry getEntry(int rank) {
		ScoreEntry entry = scores.get(rank + 1);
		return entry;
	}

	/**
	 * fuegt einen Eintrag in das Feld ein, Rang bestimmt dabei dessen Position
	 * im Feld
	 * 
	 * @param entry Spielerwertungseintrag
	 */
	public void addEntry(ScoreEntry entry) {
		int rank = entry.getRanking() - 1;
		scores.add(rank, entry);
	}
}
