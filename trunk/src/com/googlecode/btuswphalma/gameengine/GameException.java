/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

/**
 * Eine GameException wird geworfen, wenn eine Methode von Game einen Fehler produziert
 * @author sebastian
 *
 */
public class GameException extends Exception {

    /**
     * die serialVersionUID eben
     */
    private static final long serialVersionUID = 8527598533747787018L;

    /**
     * Es der entsprechende Konstruktor von Exception aufgerufen
     */
    public GameException() {
	super();
    }

    /**
     * Es der entsprechende Konstruktor von Exception aufgerufen
     * @param message
     * @param cause
     */
    public GameException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * Es der entsprechende Konstruktor von Exception aufgerufen
     * @param message
     */
    public GameException(String message) {
	super(message);
    }

    /**
     * Es der entsprechende Konstruktor von Exception aufgerufen
     * @param cause
     */
    public GameException(Throwable cause) {
	super(cause);
    }

}
