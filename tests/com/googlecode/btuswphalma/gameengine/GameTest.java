package com.googlecode.btuswphalma.gameengine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Ignore;
import org.junit.Test;

import com.googlecode.btuswphalma.base.MoveMessage;

/**
 * @author sebastian
 * 
 */
public class GameTest {

    /**
     * Fuer die erlaubten Spieleranzahlen wird getestet, ob der Konstruktor
     * Games in den erwarteten Zustaenden erzeugt. Unerlaubte Spielerzahlen
     * muessen separat getestet werden, da sie Exceptions erzeugen.
     * 
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.Game#Game(int)}.
     * 
     * @throws Exception
     */
    @Test
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
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.Game#addPlayer(int, java.lang.String)}.
     */
    @Test
    @Ignore
    public void testAddPlayer() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.Game#checkAndKeepMove(com.googlecode.btuswphalma.gameengine.HalmaMove, int)}.
     */
    @Test
    @Ignore
    public void testCheckAndKeepMove() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.Game#executeMove()}.
     */
    @Test
    @Ignore
    public void testExecuteMove() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.Game#discardMove()}.
     */
    @Test
    @Ignore
    public void testDiscardMove() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.Game#executePlayerChange()}.
     */
    @Test
    @Ignore
    public void testExecutePlayerChange() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.Game#getRound()}.
     */
    @Test
    @Ignore
    public void testGetRound() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.Game#getActivePlayer()}.
     */
    @Test
    @Ignore
    public void testGetActivePlayer() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.Game#getKeptMove()}.
     */
    @Test
    @Ignore
    public void testGetKeptMove() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.Game#getBoard()}.
     */
    @Test
    @Ignore
    public void testGetBoard() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.googlecode.btuswphalma.gameengine.Game#getPlayerList()}.
     */
    @Test
    @Ignore
    public void testGetPlayerList() {
	fail("Not yet implemented");
    }
    
    /**
     * Testet ein 2-Personen-Spiel, das Spieler 2 gewinnt
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSavedGame1() throws Exception {
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tests/com/googlecode/btuswphalma/gameengine/storedMoves/HalmaSpiel2SpielerGruenGewinnt"));
	LinkedList<MoveMessage> moveMsgs = (LinkedList<MoveMessage>)ois.readObject();
	
	Game game = new Game(2);
	assertTrue(game.addPlayer(1, "Winner"));
	assertFalse(game.addPlayer(2, "Looser"));
	MoveMessage msg;
	HalmaMove mov;
	int plyr;
	
	for (int i = 0; i < moveMsgs.size()-1; i++) {
	    msg = moveMsgs.get(i);
	    mov = msg.getMove();
	    plyr = msg.getSource();
	    assertTrue(game.checkAndKeepMove(mov, plyr));
	    assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	    game.executePlayerChange();
	}
	
	msg = moveMsgs.getLast();
	mov = msg.getMove();
	plyr = msg.getSource();
	assertTrue(game.checkAndKeepMove(mov, plyr));
	assertEquals(Game.EXECUTE_MOVE_GAME_FINISHED, game.executeMove());
	    
    }

    /**
     * Ein Spiel wird von Anfang bis zu Ende durchlaufen. Es ist ein Spiel mit
     * zwei Spielern. Spieler 1 gewinnt, Spieler 2 unterstuetzt teilweise
     * Spieler 1. Das Spiel hat keine besonderen Eigenschaften, es wurde auf dem
     * Papier erzeugt.
     * 
     * @throws Exception
     */
    @Test
    public void testdOneCompleteGame() throws Exception {
	Game game = new Game(2);
	assertTrue(game.addPlayer(1, "Winner"));
	assertFalse(game.addPlayer(2, "Looser"));
	int[][] mov = { { 3, 7 }, { 4, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 13, 4 }, { 12, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 4, 7 }, { 5, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 12, 5 }, { 11, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 2, 7 }, { 4, 6 }, { 6, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 14, 7 }, { 12, 6 }, { 10, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 6, 7 }, { 7, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 10, 5 }, { 9, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 0, 6 }, { 2, 7 }, { 4, 6 }, { 6, 7 }, { 8, 6 },
		{ 10, 5 }, { 12, 6 }, { 14, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 14, 5 }, { 12, 6 }, { 10, 5 }, { 8, 6 },
		{ 6, 7 }, { 4, 6 }, { 2, 7 }, { 0, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 2, 5 }, { 4, 6 }, { 6, 7 }, { 8, 6 }, { 10, 5 },
		{ 12, 6 }, { 14, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 15, 5 }, { 13, 4 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 3, 6 }, { 4, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 14, 6 }, { 12, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 1, 5 }, { 3, 6 }, { 5, 7 }, { 5, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 16, 6 }, { 15, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 2, 6 }, { 4, 5 }, { 6, 6 }, { 8, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 15, 6 }, { 14, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 1, 6 }, { 2, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 12, 7 }, { 11, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 2, 6 }, { 4, 5 }, { 6, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 14, 6 }, { 12, 7 }, { 10, 6 }, { 8, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 8, 7 }, { 9, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 15, 5 }, { 14, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 6, 6 }, { 8, 7 }, { 10, 6 }, { 12, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 14, 6 }, { 12, 7 }, { 10, 6 }, { 8, 7 },
		{ 6, 6 }, { 4, 5 }, { 2, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 4, 7 }, { 6, 6 }, { 8, 7 }, { 10, 6 }, { 12, 7 },
		{ 14, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 13, 5 }, { 12, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 3, 4 }, { 4, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 12, 6 }, { 10, 5 }, { 8, 6 }, { 6, 7 }, { 4, 6 },
		{ 2, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 4, 5 }, { 6, 6 }, { 8, 7 }, { 10, 6 }, { 12, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 13, 6 }, { 11, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 3, 5 }, { 4, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 11, 6 }, { 10, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 4, 6 }, { 6, 7 }, { 8, 6 }, { 10, 5 }, { 12, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 13, 7 }, { 11, 6 }, { 9, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 5, 6 }, { 6, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 11, 5 }, { 10, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 6, 6 }, { 8, 7 }, { 10, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 13, 4 }, { 11, 5 }, { 9, 4 }, { 7, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 5, 5 }, { 6, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 10, 5 }, { 8, 6 }, { 6, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 6, 6 }, { 8, 7 }, { 10, 8 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 10, 7 }, { 8, 6 }, { 6, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 7, 6 }, { 8, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 9, 5 }, { 7, 4 }, { 5, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 8, 6 }, { 10, 7 }, { 12, 8 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 8, 5 }, { 6, 6 }, { 4, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 9, 6 }, { 11, 5 }, { 13, 6 }, { 15, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 7, 5 }, { 5, 4 }, { 3, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 14, 5 }, { 16, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 11, 7 }, { 10, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 10, 8 }, { 11, 8 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 10, 7 }, { 8, 8 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 11, 8 }, { 13, 7 }, { 15, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 8, 8 }, { 8, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 10, 6 }, { 11, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 9, 7 }, { 7, 6 }, { 5, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 11, 5 }, { 13, 4 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 8, 7 }, { 7, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 12, 5 }, { 13, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 3, 5 }, { 1, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 12, 6 }, { 14, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	// showBoard(game);
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 7, 6 }, { 7, 5 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 12, 7 }, { 13, 6 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 6, 7 }, { 4, 8 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 2));
	assertEquals(Game.EXECUTE_MOVE_NORMAL, game.executeMove());
	game.executePlayerChange();

	mov = new int[][] { { 12, 8 }, { 13, 7 } };
	assertTrue(game.checkAndKeepMove(createHalmaMove(mov), 1));
	assertEquals(Game.EXECUTE_MOVE_GAME_FINISHED, game.executeMove());
    }

    /**
     * Aus einem zweidimensionalen Array wird ein HalmaMove erzeugt. Die Methode
     * ist optimistisch, Sie geht davon aus, das das Array in der richtigen Form
     * vorliegt. Naemlich nicht null ist, die inneren Arrays mind. die Laenge 2
     * haben und die int in byte gecastet werden koennen
     * 
     * @param moveArray
     * @return der erzeugte HalmaMove
     */
    private HalmaMove createHalmaMove(int[][] moveArray) {
	ArrayList<BoardPosition> posList = new ArrayList<BoardPosition>(
		moveArray.length);
	for (int i = 0; i < moveArray.length; i++) {
	    posList.add(new BoardPosition((byte) moveArray[i][0],
		    (byte) moveArray[i][1]));
	}
	return new HalmaMove(posList);
    }

}
