package com.googlecode.btuswphalma.gameengine;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Beinhaltet die Spielerwertungsliste, die aus bis zu 6 Eintraegen besteht
 * 
 * @author Christoph
 */
public class ScoreList implements Serializable {

    /**
     * ArrayList(ScoreEntry), initial leer und 0-lang
     */
    private ArrayList<ScoreEntry> scores = new ArrayList<ScoreEntry>();

    /**
     * leerer Konstruktor
     */
    public ScoreList() {
    }

    /**
     * gibt die komplette Spielerwertungsliste aus
     * 
     * @return ArrayList(ScoreEntry)
     */
    public ArrayList<ScoreEntry> getScoreList() {
	return this.scores;
    }

    /**
     * gibt einen Eintrag, anhand der Position im Feld, aus
     * 
     * @param rank
     *                int
     * @return ScoreEntry
     */
    public ScoreEntry getEntry(int rank) {
	if ((rank <= 0) || (rank > scores.size())) {
	    System.err.println("Position ausserhalb der Liste!");
	    return null;
	} else {
	    return scores.get(rank - 1);
	}
    }

    /**
     * ersetzt einen Eintrag im Feld, Rang bestimmt dabei dessen Position
     * 
     * @param entry
     *                ScoreEntry
     */
    public void setEntry(ScoreEntry entry) {
	int pos = entry.getRanking() - 1;
	if (pos < scores.size()) {
	    this.scores.remove(pos);
	    this.scores.add(pos, entry);
	} else {
	    entry.setRanking(scores.size() + 1);
	    this.scores.add(entry);
	}
    }

    /**
     * fuegt einen Eintrag an das Ende der Liste
     * 
     * @param entry
     *                ScoreEntry
     */
    public void addEntry(ScoreEntry entry) {
	this.scores.add(entry);
    }

    /**
     * gibt aktuelle Laenge der Liste aus
     * 
     * @return int
     */
    public int getSize() {
	return (this.scores.size());
    }
}
