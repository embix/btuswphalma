/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

/**
 * @author sebastian
 *
 */
public class GameException extends Exception {

    private static final long serialVersionUID = 8527598533747787018L;

    public GameException() {
	super();
    }

    public GameException(String message, Throwable cause) {
	super(message, cause);
    }

    public GameException(String message) {
	super(message);
    }

    public GameException(Throwable cause) {
	super(cause);
    }

}
