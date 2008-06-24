/**
 * 
 */
package com.googlecode.btuswphalma.network;

/**
 * Ein Fehler im Netzwerk
 * 
 * @author sebastian
 *
 */
public class NetworkException extends Exception {

    /**
     * generierte UID
     */
    private static final long serialVersionUID = 1702082769536425725L;

    /**
     * 
     */
    public NetworkException() {
	super();
    }

    /**
     * @param message
     * @param cause
     */
    public NetworkException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * @param message
     */
    public NetworkException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public NetworkException(Throwable cause) {
	super(cause);
    }
    

}
