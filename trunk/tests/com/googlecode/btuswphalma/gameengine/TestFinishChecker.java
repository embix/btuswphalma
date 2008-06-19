package com.googlecode.btuswphalma.gameengine;

/**
 * Testklasse fuer FinishChecker
 * 
 * @author Christoph
 * 
 */
public class TestFinishChecker {

    /**
     * @param args
     */
    public static void main(String[] args) {

    FinishChecker check = new FinishChecker();
    
	PlayerList players = new PlayerList();

	for (int i = 1; i < 7; i++) {
	    Player play = new Player(i, "name" + i, false, 2 * i);
	    players.addPlayer(play);
	}

	for (int i = 1; i < 6; i++) {
	    Player play = players.getPlayer(i);
	    play.setSpec(true);
	}

	boolean xx = check.checkGameFinished(players);
	System.out.println(String.valueOf(xx));

    int num = 6;
    int pl = 2;
    byte xs = 0; byte ys = 6;
    byte xe = 7; byte ye = 6;
    Board board = new Board(num);
    BoardPosition spos = new BoardPosition(xs, ys);
    BoardPosition epos = new BoardPosition(xe, ye);
    board.exchangePositionState(spos, epos);
    
    boolean cp = check.checkPlayerCompleted(board, pl, num);
    System.out.println(String.valueOf(cp));
    
    /** fuer folgende tests auf public methods abaendern
    int le = check.locateEndHouse(pl, num);
    System.out.println(le);
    
    boolean pf = check.playerIsFinished(board, pl, pl);
    System.out.println(pf);*/
    }
}
