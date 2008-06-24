package com.googlecode.btuswphalma.gameengine;

/**
 * hat 2 Check-Methoden: (1) ob der Spieler, nach einem gueltigen Zug, alle
 * seine Spielsteine in das Zielhaus gebracht hat (2) ob das Spiel beendet ist,
 * falls ein Spieler 'gerade' fertig geworden ist
 * 
 * @author Christoph
 * 
 */
public class FinishChecker {

    /** Spielbrett-Kopie */
    private byte[][] board;

    /**
     * leerer Konstruktor
     */
    public FinishChecker() {
    }

    /**
     * prueft, ob der Spieler mit der Nummer 'id' nach seinem gueltigen Zug
     * fertig geworden ist
     * 
     * @param board
     *                Board (aktuelles Spielbrett)
     * @param id
     *                int (Spieler-ID)
     * @param num
     *                int (Spieleranzahl)
     * @return boolean
     */
    public boolean checkPlayerCompleted(Board board, int id, int num) {
	int endHouse = locateEndHouse(id, num);
	this.board = board.getBoardArryClone();
	boolean finished = playerIsFinished(board, id, endHouse);
	return finished;
    }

    /**
     * prueft, ob das Spiel beendet wurde (also nur noch ein Spieler ein
     * Nicht-Beobachter is)
     * 
     * @param players
     *                PlayerList
     * @return boolean
     */
    public boolean checkGameFinished(PlayerList players) {
	boolean finished = false;
	int noP = players.getSize(); // number of Players
	int noS = 0; // number of Spectator
	for (int i = 1; i < noP; i++) {
	    if (players.getPlayer(i).getSpectator()) {
		noS++;
	    }
	}
	if ((noP - noS) <= 1) {
	    finished = true;
	}
	return finished;
    }

    /**
     * gibt Zielhaus des Spielers, in Abhaengigkeit von der Spieleranzahl, aus
     * 
     * @param id
     *                int (Spieler-ID)
     * @param num
     *                int (Spieleranzahl)
     * @return int
     */
    private int locateEndHouse(int id, int num) {
	int endHouse = 1;
	switch (num) {
	case 2:
	    switch (id) {
	    case 1:
		endHouse = 4;
		break;
	    case 2:
		endHouse = 1;
		break;
	    default:
		System.err.println("Falsche Spieler-ID");
		break;
	    }
	    break;
	case 3:
	    switch (id) {
	    case 1:
		endHouse = 4;
		break;
	    case 2:
		endHouse = 6;
		break;
	    case 3:
		endHouse = 2;
		break;
	    default:
		System.err.println("Falsche Spieler-ID!");
		break;
	    }
	    break;
	case 4:
	    switch (id) {
	    case 1:
		endHouse = 5;
		break;
	    case 2:
		endHouse = 6;
		break;
	    case 3:
		endHouse = 2;
		break;
	    case 4:
		endHouse = 3;
		break;
	    default:
		System.err.println("Falsche Spieler-ID!");
		break;
	    }
	    break;
	case 6:
	    endHouse = (id + 3) % num;
	    endHouse = (endHouse==0)?6:endHouse;
	    break;
	default:
	    System.err.println("Falsche Spieleranzahl!");
	    break;
	}
	return endHouse;
    }

    /**
     * prueft zu gegebenen Spieler und Zielhaus das Spielbrett
     * 
     * @param board
     *                Board (Spielbrett)
     * @param id
     *                int (Spielernummer)
     * @param endHouse
     *                int (Zielhaus)
     * @return int
     */
    private boolean playerIsFinished(Board board, int id, int endHouse) {
	boolean finished = true;
	int i = 0;
	int j = 0;
	switch (endHouse) {
	case 1:
	    while (finished && i < 4) {
		while (finished && j <= (i / 2)) {
		    if ((this.board[i][6 + j] != (byte) id)
			    || (this.board[i][6 - j] != (byte) id)) {
			finished = false;
		    }
		    j++;
		}
		i++;
		j=0;
	    }
	    if ((this.board[1][5] != (byte) id)
		    || (this.board[3][4] != (byte) id)) {
		finished = false;
	    }
	    break;
	case 2:
	    while (finished && i < 4) {
		while (finished && j <= (i / 2)) {
		    if ((this.board[7 - i][10 + j] != (byte) id)
			    || (this.board[7 - i][10 - j] != (byte) id)) {
			finished = false;
		    }
		    j++;
		}
		i++;
		j=0;
	    }
	    if ((this.board[6][11] != (byte) id)
		    || (this.board[4][12] != (byte) id)) {
		finished = false;
	    }
	    break;
	case 3:
	    while (finished && i < 4) {
		while (finished && j <= (i / 2)) {
		    if ((this.board[9 + i][10 + j] != (byte) id)
			    || (this.board[9 + i][10 - j] != (byte) id)) {
			finished = false;
		    }
		    j++;
		}
		i++;
		j=0;
	    }
	    if ((this.board[10][11] != (byte) id)
		    || (this.board[12][12] != (byte) id)) {
		finished = false;
	    }
	    break;
	case 4:
	    while (finished && i < 4) {
		while (finished && j <= (i / 2)) {
		    if ((this.board[16 - i][6 + j] != (byte) id)
			    || (this.board[16 - i][6 - j] != (byte) id)) {
			finished = false;
		    }
		    j++;
		}
		i++;
		j=0;
	    }
	    if ((this.board[15][5] != (byte) id)
		    || (this.board[13][4] != (byte) id)) {
		finished = false;
	    }
	    break;
	case 5:
	    while (finished && i < 4) {
		while (finished && j <= (i / 2)) {
		    if ((this.board[9 + i][1 + j] != (byte) id)
			    || (this.board[9 + i][1 - j] != (byte) id)) {
			finished = false;
		    }
		    j++;
		}
		i++;
		j=0;
	    }
	    if ((this.board[10][2] != (byte) id)
		    || (this.board[12][3] != (byte) id)) {
		finished = false;
	    }
	    break;
	case 6:
	    while (finished && i < 4) {
		while (finished && j <= (i / 2)) {
		    if ((this.board[7 - i][1 + j] != (byte) id)
			    || (this.board[7 - i][1 - j] != (byte) id)) {
			finished = false;
		    }
		    j++;
		}
		i++;
		j=0;
	    }
	    if ((this.board[6][2] != (byte) id)
		    || (this.board[4][3] != (byte) id)) {
		finished = false;
	    }
	    break;
	default:
	    System.err.println("falsches ZielHaus");
	    break;
	}
	return finished;
    }
}
