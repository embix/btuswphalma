package com.googlecode.btuswphalma.gameengine;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import com.googlecode.btuswphalma.gameengine.Game;

/**
 * @author sebastian
 *
 */
public class GameTest {

    /**
     * Test method for {@link com.googlecode.btuswphalma.gameengine.Game#Game(int)}.
     * @throws Exception 
     */
    @Test
    //@Ignore
    public void testGame() throws Exception {
	assertEquals(0, new Game(2).getActivePlayer());
	assertEquals(0, new Game(3).getActivePlayer());
	assertEquals(0, new Game(4).getActivePlayer());
	assertEquals(0, new Game(6).getActivePlayer());
	
	assertEquals(0, new Game(2).getRound());
	assertEquals(0, new Game(3).getRound());
	assertEquals(0, new Game(4).getRound());
	assertEquals(0, new Game(6).getRound());
    }

    /**
     * Test method for {@link com.googlecode.btuswphalma.gameengine.Game#addPlayer(int, java.lang.String)}.
     */
    @Test
    @Ignore
    public void testAddPlayer() {
	fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.googlecode.btuswphalma.gameengine.Game#checkAndKeepMove(com.googlecode.btuswphalma.gameengine.HalmaMove, int)}.
     */
    @Test
    @Ignore
    public void testCheckAndKeepMove() {
	fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.googlecode.btuswphalma.gameengine.Game#executeMove()}.
     */
    @Test
    @Ignore
    public void testExecuteMove() {
	fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.googlecode.btuswphalma.gameengine.Game#discardMove()}.
     */
    @Test
    @Ignore
    public void testDiscardMove() {
	fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.googlecode.btuswphalma.gameengine.Game#executePlayerChange()}.
     */
    @Test
    @Ignore
    public void testExecutePlayerChange() {
	fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.googlecode.btuswphalma.gameengine.Game#getRound()}.
     */
    @Test
    @Ignore
    public void testGetRound() {
	fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.googlecode.btuswphalma.gameengine.Game#getActivePlayer()}.
     */
    @Test
    @Ignore
    public void testGetActivePlayer() {
	fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.googlecode.btuswphalma.gameengine.Game#getKeptMove()}.
     */
    @Test
    @Ignore
    public void testGetKeptMove() {
	fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.googlecode.btuswphalma.gameengine.Game#getBoard()}.
     */
    @Test
    @Ignore
    public void testGetBoard() {
	fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.googlecode.btuswphalma.gameengine.Game#getPlayerList()}.
     */
    @Test
    @Ignore
    public void testGetPlayerList() {
	fail("Not yet implemented");
    }
    
    @Test
    //@Ignore
    public void testOneCompleteGame() throws Exception {
	Game game = new Game(2);
	assertTrue(game.addPlayer(1, "Winner"));
	assertFalse(game.addPlayer(2, "Looser"));
	int[][] mov = {{3,7},{4,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{13,4},{12,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{4,7},{5,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{12,5},{11,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{2,7},{4,6},{6,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{14,7},{12,6},{10,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{6,7},{7,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{10,5},{9,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{0,6},{2,7},{4,6},{6,7},{8,6},{10,5},{12,6},{14,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{14,5},{12,6},{10,5},{8,6},{6,7},{4,6},{2,7},{0,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{2,5},{4,6},{6,7},{8,6},{10,5},{12,6},{14,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{15,5},{13,4}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{3,6},{4,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{14,6},{12,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{1,5},{3,6},{5,7},{5,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{16,6},{15,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{2,6},{4,5},{6,6},{8,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{15,6},{14,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{1,6},{2,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{12,7},{11,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{2,6},{4,5},{6,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{14,6},{12,7},{10,6},{8,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{8,7},{9,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{15,5},{14,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{6,6},{8,7},{10,6},{12,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{14,6},{12,7},{10,6},{8,7},{6,6},{4,5},{2,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{4,7},{6,6},{8,7},{10,6},{12,7},{14,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{13,5},{12,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{3,4},{4,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{12,6},{10,5},{8,6},{6,7},{4,6},{2,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{4,5},{6,6},{8,7},{10,6},{12,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{13,6},{11,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{3,5},{4,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{11,6},{10,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{4,6},{6,7},{8,6},{10,5},{12,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{13,7},{11,6},{9,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{5,6},{6,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{11,5},{10,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{6,6},{8,7},{10,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{13,4},{11,5},{9,4},{7,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{5,5},{6,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{10,5},{8,6},{6,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{6,6},{8,7},{10,8}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{10,7},{8,6},{6,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{7,6},{8,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{9,5},{7,4},{5,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{8,6},{10,7},{12,8}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{8,5},{6,6},{4,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{9,6},{11,5},{13,6},{15,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{7,5},{5,4},{3,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{14,5},{16,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{11,7},{10,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{10,8},{11,8}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{10,7},{8,8}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{11,8},{13,7},{15,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{8,8},{8,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{10,6},{11,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{9,7},{7,6},{5,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{11,5},{13,4}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{8,7},{7,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{12,5},{13,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{3,5},{1,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{12,6},{14,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	//showBoard(game);
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{7,6},{7,5}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{12,7},{13,6}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{6,7},{4,8}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();
	
	mov = new int[][]{{12,8},{13,7}};
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
	assertEquals(Game.EXECUTE_MOVE_GAME_FINISHED, game.executeMove());
	//game.executePlayerChange();
	
	
	
	
	
	
//	mov = new int[][];
//	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),2));
//	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
//	game.executePlayerChange();
//	
//	mov = new int[][];
//	assertTrue(game.checkAndKeepMove(createHalmaMove(mov),1));
//	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
//	game.executePlayerChange();
	
	
	
	
    }
    
    private void showBoard(Game game) {
	byte [][] brd = game.getBoard().getBoardArryClone();
	for(int i = 0; i < brd.length; i++) {
	    for(int j = 0; j < brd[0].length; j++) {
		if (brd[i][j] > -1)
		    System.out.print(brd[i][j]);
		else 
		    System.out.print(' ');
	    }
	    System.out.println();
	}
	
    }

    private HalmaMove createHalmaMove(int[][] moveArray) {
	ArrayList<BoardPosition> posList = new ArrayList<BoardPosition>(moveArray.length);
	for(int i = 0; i < moveArray.length; i++) {
	    posList.add(new BoardPosition((byte) moveArray[i][0], (byte) moveArray[i][1]));
	}
	return new HalmaMove(posList);
    }

}
