/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author sebastian
 *
 */
public class RulesHalmaMoveCheckerTest {
    
    /**
     * Der zu testende RulesHalmaMoveChecker
     */
    IHalmaMoveChecker moveChecker = new RulesHalmaMoveChecker();

    /**
     * Es wird ein Zug mit mehrmaligem Vor und zurueckspringen getestet
     * Test method for {@link com.googlecode.btuswphalma.gameengine.RulesHalmaMoveChecker#checkMove(com.googlecode.btuswphalma.gameengine.Board, com.googlecode.btuswphalma.gameengine.HalmaMove, int)}.
     */
    @Test
    public void testCheckMove() {
	// nur ein sehr vorlaeufiger Test
	// mehr Verschieden Ablaufe Testen
	// mit verschiedenen Ausgaengen
	Board board = new Board(2);
	ArrayList<BoardPosition> pos = new ArrayList<BoardPosition>(2);
	HalmaMove move;
	int player = 1;
	
	pos.add(new BoardPosition((byte)2,(byte)6));
	pos.add(new BoardPosition((byte)4,(byte)7));
	pos.add(new BoardPosition((byte)2,(byte)6));
	pos.add(new BoardPosition((byte)4,(byte)7));
	pos.add(new BoardPosition((byte)2,(byte)6));
	pos.add(new BoardPosition((byte)4,(byte)7));
	move = new HalmaMove(pos);
	
	assertEquals(true, moveChecker.checkMove(board, move, player)); 
	
	
    }

}
