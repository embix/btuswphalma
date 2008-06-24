/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Ein kleiner Test fuer den FinishChecker. Kann noch ausgebaut werden
 * 
 * @author sebastian
 * 
 */
public class FinishCheckerTest {

    /**
     * Es wird auf einen eigentlich schon identifizierten Bug getestet.
     *  
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.FinishChecker#checkPlayerCompleted(com.googlecode.btuswphalma.gameengine.Board, int, int)}.
     */
    @Test
    public void testCheckPlayerCompleted1() {
	FinishChecker fc = new FinishChecker();
	Board brd = new Board(2);

	BoardPosition spos = new BoardPosition((byte) 0, (byte) 6);
	BoardPosition epos = new BoardPosition((byte) 16, (byte) 6);
	brd.exchangePositionState(spos, epos);
	spos = new BoardPosition((byte) 3, (byte) 4);
	epos = new BoardPosition((byte) 15, (byte) 5);
	brd.exchangePositionState(spos, epos);
	spos = new BoardPosition((byte) 3, (byte) 5);
	epos = new BoardPosition((byte) 14, (byte) 5);
	brd.exchangePositionState(spos, epos);
	spos = new BoardPosition((byte) 3, (byte) 6);
	epos = new BoardPosition((byte) 14, (byte) 7);
	brd.exchangePositionState(spos, epos);
	spos = new BoardPosition((byte) 3, (byte) 7);
	epos = new BoardPosition((byte) 13, (byte) 4);
	brd.exchangePositionState(spos, epos);
	System.out.println(brd.toString());
	assertFalse(fc.checkPlayerCompleted(brd, 1, 2));
    }
}
