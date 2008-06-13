/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

/**
 * Der RulesHalmaMoveChecker ueberprueft, ob ein Zug den Halmaregeln entspricht.
 * Es findet dabei eine vollstaendige Regelpruefung statt.
 * 
 * @author sebastian
 * 
 */
public class RulesHalmaMoveChecker implements IHalmaMoveChecker {

    /**
     * (non-Javadoc)
     * 
     * @see com.googlecode.btuswphalma.gameengine.IHalmaMoveChecker#checkMove(com.googlecode.btuswphalma.gameengine.Board,
     *      com.googlecode.btuswphalma.gameengine.HalmaMove, int)
     */
    public boolean checkMove(Board board, HalmaMove move, int player) {
	boolean result = false;
	int moves = move.getNumberOfPartMoves();

	if (player != board.getPositionState(move.getStartPosition())) {
	    // player versucht einen Spielstein zu bewegen, der nicht ihm gehört
	    result = false;
	    return result;
	}

	result = (moves > 0) && (isPush(board, move) || isJump(board, move));
	return result;
    }

    /**
     * Es wird ueberprueft, ob ein Zug ein Schub ist
     * 
     * @param board
     * @param move
     * @return
     */
    private boolean isPush(Board board, HalmaMove move) {
	boolean result = false;
	int moves = move.getNumberOfPartMoves();
	BoardPosition startPos = move.getStartPosition();
	BoardPosition endPos = move.getEndPosition();

	result = (moves == 1) && (board.getPositionState(startPos) > 0)
		&& (board.getPositionState(endPos) == 0)
		&& HalmaMath.isOnBoard(startPos) // TODO unnoetig
		&& HalmaMath.isOnBoard(endPos) // dito
		&& HalmaMath.isPushDistance(startPos, endPos);
	return result;
    }

    /**
     * Es wird ueberprueft, ob ein Zug ein Sprung ist
     * 
     * @param board
     *                das Spielbrett
     * @param move
     *                der zu pruefende Zug
     * @return ob es ein Schub ist
     */
    private boolean isJump(Board board, HalmaMove move) {
	boolean result = false;
	int moves = move.getNumberOfPartMoves();
	int i = 0;

	// ersten Sprung prüfen
	result = isJumpStart(board, move.getPartPosition(i), move
		.getPartPosition(i + 1));
	while (result && i < moves) {
	    // Man muss das result nicht noch verunden,
	    // weil dies implizit durch das while geschieht
	    result = isJumpContinuation(board, move.getPartPosition(i), move
		    .getPartPosition(i + 1));
	}

	return result;
    }

    /**
     * Es wird geprueft, ob die zwei Positionen der Anfang eines Sprunges sind.
     * 
     * @param board
     * @param startPos
     * @param endPos
     * @return
     */
    private boolean isJumpStart(Board board, BoardPosition startPos,
	    BoardPosition endPos) {
	boolean result = false;
	BoardPosition overleaptPos;
	result = (board.getPositionState(startPos) > 0)
		&& (board.getPositionState(endPos) == 0)
		&& HalmaMath.isOnBoard(startPos) // TODO unnoetig
		&& HalmaMath.isOnBoard(endPos) // dito
		&& HalmaMath.isJumpDistance(startPos, endPos);
	if (result) {
	    overleaptPos = getOverleaptPos(startPos, endPos);
	    result = (board.getPositionState(overleaptPos) > 0);
	}

	return result;
    }

    /**
     * Es wird ueberprueft, ob die zwei Positionen die Fortsetzung eines Zuges
     * sind
     * 
     * @param board
     * @param startPos
     * @param endPos
     * @return
     */
    private boolean isJumpContinuation(Board board, BoardPosition startPos,
	    BoardPosition endPos) {
	boolean result = false;
	BoardPosition overleaptPos;
	result = (board.getPositionState(startPos) == 0)
		&& (board.getPositionState(endPos) == 0)
		&& HalmaMath.isOnBoard(startPos) // TODO unnoetig
		&& HalmaMath.isOnBoard(endPos) // dito
		&& HalmaMath.isJumpDistance(startPos, endPos);
	if (result) {
	    overleaptPos = getOverleaptPos(startPos, endPos);
	    result = (board.getPositionState(overleaptPos) > 0);
	}

	return result;
    }

    /**
     * Es wird die Position zurueckgegeben, die uebersprungen wird, wenn
     * startPos und endPos ein Teilsprung sind
     * 
     * @param startPos
     * @param endPos
     * @return die BoardPosition, die auf das übersprungene Feld zeigt
     */
    private BoardPosition getOverleaptPos(BoardPosition startPos,
	    BoardPosition endPos) {

	// Ich vertraue darauf, nur startPos und endPos zu kriegen, die die
	// richtige Distanz haben
	byte xs = startPos.getXPos();
	byte ys = startPos.getYPos();
	byte xe = endPos.getXPos();
	byte ye = endPos.getYPos();

	byte xol = (byte) ((xs + xe) / 2);
	byte yol = (byte) (((ys + ye) / 2) + ((xs == xe) ? 0 : (xs % 2)));

	return new BoardPosition(xol, yol);
    }
}
