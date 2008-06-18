/**
 * 
 */
package com.googlecode.btuswphalma.gameengine;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author sebastian
 *
 */
public class RulesHalmaMoveCheckerTest {
    
    IHalmaMoveChecker moveChecker = new RulesHalmaMoveChecker();

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
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
	
	Assert.assertEquals(true, moveChecker.checkMove(board, move, player)); 
	
	
    }

}
