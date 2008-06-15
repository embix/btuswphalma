/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

/**
 * In dieser Klasse enthaelt Methoden, deren Ergebnisse nicht von einer Belegung
 * des Spielbretts abhaengen, sondern nur von Zahlenverhaeltnissen
 * 
 * @author sebastian
 * 
 */
public class HalmaMath {

    /**
     * Die Distanz zwischen Feldern zwischen denen ein Schub moeglich ist
     */
    public static final int PUSH_DISTANCE = 2;

    /**
     * Es wird getestet, ob ein eine BoardPosition entsprechend unserer
     * Modellierung des Spielbretts auf das Spielbrett zeigt.
     * 
     * @param pos
     * @return ist die BoardPosiotion auf dem Spielfeld
     */
    public static boolean isOnBoard(BoardPosition pos) {
	if(pos == null) {
	    return false;
	}
	return isOnBoard(pos.getXPos(), pos.getYPos());
    }

    /**
     * Es wird getestet, ob die Koordinaten (x,y) auf das Spielbrett zeigen.
     * 
     * @param x
     * @param y
     * @return ist (x,y) auf dem Spielfeld
     */
    public static boolean isOnBoard(byte x, byte y) {
	boolean result = false;
	result = (x <= 12 && Math.abs((2 * y + x % 2) - 12) <= x)
		|| (x >= 4 && Math.abs((2 * y + x % 2) - 12) <= 16 - x);
	return result;
    }

    /**
     * Es wird geprueft, ob die Distanz zischen zwei Positionen einem Schub
     * entsprechen. Nicht geprueft wird, ob die Felder sich im Spielfeld
     * befinden.
     * 
     * @param startPos
     * @param endPos
     * @return ob die Distanz einem Schub entspricht
     */
    public static boolean isPushDistance(BoardPosition startPos,
	    BoardPosition endPos) {
	if(startPos == null || endPos == null) {
	    return false;
	}
	byte xs = startPos.getXPos();
	byte ys = startPos.getYPos();
	byte xe = endPos.getXPos();
	byte ye = endPos.getYPos();
	boolean result;

	// return (Math.abs(xs - xe) + Math.abs(2 * (ys - ye) + (xs % 2 - xe %
	// 2)) == PUSH_DISTANCE);

	result = Math.abs(ys - ye) == 1 && (xs == xe)
		         || Math.abs(2 * (ys - ye + (xs % 2)) - 1) == 1
		            && Math.abs(xs - xe) == 1;

	return result;
    }

    /**
     * Es wird geprueft, ob die Distanz zwischen zwei Positionen einem
     * (Teil-)Sprung ensprechen. Nicht geprueft wird, ob die Felder sich im
     * Spielfeld befinden.
     * 
     * @param startPos
     * @param endPos
     * @return ob die Distanz einem (Teil-)Sprung entspricht
     */
    public static boolean isJumpDistance(BoardPosition startPos,
	    BoardPosition endPos) {
	if(startPos == null || endPos == null) {
	    return false;
	}
	byte xs = startPos.getXPos();
	byte ys = startPos.getYPos();
	byte xe = endPos.getXPos();
	byte ye = endPos.getYPos();

	return (((xs == xe) && (Math.abs(ys - ye) == 2))
	// ein Sprung ohne vertikale Bewegung
	|| ((Math.abs(xs - xe) == 2) && (Math.abs(ys - ye) == 1)));
	// ein Sprung mit vertikaler Bewegung
    }
}
