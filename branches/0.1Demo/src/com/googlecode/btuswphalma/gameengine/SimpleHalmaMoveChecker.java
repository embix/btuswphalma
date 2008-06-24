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
     * @return
     * 
     * @see com.googlecode.btuswphalma.gameengine.IHalmaMoveChecker#checkMove(com.googlecode.btuswphalma.gameengine.Board,
     *      com.googlecode.btuswphalma.gameengine.HalmaMove, int)
     */
    public boolean checkMove(Board board, HalmaMove move, int player) {
	boolean result = true;
	int i = move.getNumberOfPartMoves() + 1;
	while ((i >= 0) && result) {
	    BoardPosition pos = move.getPartPosition(i);
	    byte state = board.getPositionState(pos);
	    // wenn Position nicht frei oder au�erhalb, also nicht Null
	    if (state != 0) {
		result = false;
	    }
	    i--;
	}
	return result;
    }
}
