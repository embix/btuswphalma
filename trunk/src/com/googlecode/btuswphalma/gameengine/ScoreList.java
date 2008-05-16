/**
 * ScoreList.java
 * Beinhaltet die Spielerwertungsliste die aus bis zu 6 Eintraegen besteht
 */
package com.googlecode.btuswphalma.gameengine;

import java.util.ArrayList;

public class ScoreList {

	/* erzeugen einer ArrayList */
	private ArrayList<ScoreEntry> scores;

	/* initialisieren der Spielerwertungsliste, dessen Groeﬂe in Abhaengigkeit der Spieleranzahl */
	public ScoreList(int anz) {
		this.scores = new ArrayList<ScoreEntry>(anz);
	}
	
	/* gibt die komplette Spielerwertungsliste aus */
	public ArrayList<ScoreEntry> getScoreList() {
		return this.scores;
	}
	
	/* gibt einen Eintrag aus der Liste aus, anhand des Ranges 
	 * (dieser muss inkrementiert werden)*/
	public ScoreEntry getEntry(int rank) {
		ScoreEntry entry = scores.get(rank + 1);
		return entry;
	}
	
	/* fuegt einen Eintrag in die Liste hinzu, dabei bestimmt der Rang des 
	 * Eintrages die Position des Eintrages in der Liste*/
	public void addEntry(ScoreEntry entry) {
		int rank = entry.getRanking() - 1;
		scores.add(rank, entry);
	}
}
