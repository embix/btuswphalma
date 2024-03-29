package com.googlecode.btuswphalma.gameengine;

import java.io.Serializable;

/**
 * Spielerdaten beinhalten: ID, Spielename, ist Spieler Beobachter, Runde in der
 * der Spieler fertig geworden ist
 * 
 * @author Christoph
 * 
 */
public class Player implements Serializable {

    /**
     * generierte UID
     */
    private static final long serialVersionUID = -6126418797286564770L;

    private int id;

    private String name;

    private boolean spectator;

    private int finishingRound;

    /**
     * Konstruktor mit folgenden Werten:
     * 
     * @param idnr
     *                int (ID des Spielers 1-6)
     * @param pname
     *                String (Spielername)
     * @param spec
     *                boolean (Spieler ist Beobachter)
     * @param round
     *                int (Runde in der der Spieler fertig wurde)
     */
    public Player(int idnr, String pname, boolean spec, int round) {
	if ((idnr <= 0) || (idnr > 6)) {
	    System.err
		    .println("ID des Spieler entspricht nicht 1-6, setze default=6");
	    this.id = 6;
	} else {
	    this.id = idnr;
	}
	this.name = pname;
	this.spectator = spec;
	this.finishingRound = round;
    }

    /**
     * Ausgabe der Spieler-Identitaetsnummer
     * 
     * @return int
     */
    public int getID() {
	return this.id;
    }

    /**
     * Ausgabe des Spielernamens
     * 
     * @return String
     */
    public String getName() {
	return this.name;
    }

    /**
     * Ausgabe ob Spieler Beobachter ist
     * 
     * @return boolean
     */
    public boolean getSpectator() {
	return this.spectator;
    }

    /**
     * Ausgabe der benoetigten Runden des Spielers
     * 
     * @return int
     */
    public int getRounds() {
	return this.finishingRound;
    }

    /**
     * setz den Wert Beobachter (spectator)
     * 
     * @param spec
     *                boolean
     */
    public void setSpec(boolean spec) {
	this.spectator = spec;
    }

    /**
     * setz die Runde, in der der Spieler fertig wurde
     * 
     * @param round
     *                int
     */
    public void setRound(int round) {
	this.finishingRound = round;
    }

    /**
     * ersetzt den Spielernamen
     * 
     * @param pname
     *                String
     */
    public void setName(String pname) {
	this.name = pname;
    }

    /**
     * ersetzt die ID
     * 
     * @param idnr
     *                int
     */
    public void setID(int idnr) {
	if ((idnr < 0) || (idnr > 6)) {
	    System.err.println("ID entspricht nicht 1-6, setze default=6.");
	    this.id = 6;
	} else {
	    this.id = idnr;
	}
    }
}
