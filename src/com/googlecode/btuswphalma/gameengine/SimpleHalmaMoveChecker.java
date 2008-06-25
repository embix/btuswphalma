package com.googlecode.btuswphalma.gameengine;

/**
 * Der SimpleHalmaMoveChecker ueberprueft die Postitionsfolge eines Halma-Zuges,
 * dabei wird nur geprueft, ob alle genannten Positionen innerhalb des
 * Spielbrettes liegen und frei sind.
 * 
 * @author Christoph
 * 
 */
public class SimpleHalmaMoveChecker {

    /**
     * (non-Javadoc)
     * 
     * @param board
     * @param move
     * @param player
     * @return ist der Zug korrekt
     * 
     * @see com.googlecode.btuswphalma.gameengine.IHalmaMoveChecker#checkMove(com.googlecode.btuswphalma.gameengine.Board,
     *      com.googlecode.btuswphalma.gameengine.HalmaMove, int)
     */
    public boolean checkMove(Board board, HalmaMove move, int player) {
	boolean result = true;
	BoardPosition pos;
	byte state;
	int i = move.getNumberOfPartMoves()+1;
	if (i<=1) { 
		//wenn weniger oder genau eine Position im Move -> Fehler
		result = false;
	}
	while ((i >= 1) && result) {
	    pos = move.getPartPosition(i);
	    state = board.getPositionState(pos);
	    // wenn Position nicht frei oder ausserhalb, also nicht Null
	    // aber ohne Startposition
	    if (state != 0) {
	    	result = false;
	    }
	    i--;
	}
	//prueft Startposition des Zuges, ob gewaehlter Stein dem Spieler gehoert
	if (result) {
		pos = move.getStartPosition();
		state = board.getPositionState(pos);
		if (state != (byte)player) {
			result = false;
		}
	}
	return result;
    }
}
