package com.googlecode.btuswphalma.gameengine;

/**
 * Das Board (Spielbrett) enthaelt alle Spielfeldpositionen und die Positionen
 * aller Spielfeldsteine
 * 
 * @author Christoph
 */
public class Board {

    /** 2D Array fuer das Spielfeld (17 Reihen X 13 Spalten) */
    private byte[][] boardArray;

    // private byte bitmask = 0x01; //eventuell um den Status eines Feldpunktes
    // zu bearbeiten

    /**
     * Konstruktor, der, in Abhaengigkeit von der Spielerzahl, das Spielfeld
     * initialisiert
     * 
     * @param num
     *                int (Spieleranzahl)
     */
    public Board(int num) {
	this.boardArray = new byte[17][13];
	markUsablePositions();
	fillWithPlayers(num);
    }

    /**
     * gibt eine Kopie des aktuellen Spielbrettes aus
     * 
     * @return byte[][] (Spielbrett)
     */
    public byte[][] getBoardArryClone() {
	// FIXME hinten an das boardArray gehoert ein .clone()
	byte[][] clone = this.boardArray.clone();
	return clone;
    }

    /**
     * gibt den Status einer Spielfeldposition aus
     * 
     * @param pos
     *                BoardPosition (byte, byte)
     * @return byte
     */
    public byte getPositionState(BoardPosition pos) {
	byte x = pos.getXPos();
	byte y = pos.getYPos();
	return this.boardArray[x][y];
    }

    /**
     * tauscht die Werte von Board(spos) und Board(epos)
     * 
     * @param spos
     *                BoardPosition (byte, byte)
     * @param epos
     *                BoardPosition (byte, byte)
     */
    public void exchangePositionState(BoardPosition spos, BoardPosition epos) {
	byte xs = spos.getXPos();
	byte ys = spos.getYPos();
	byte xe = epos.getXPos();
	byte ye = epos.getYPos();
	byte toChange = this.boardArray[xe][ye];
	// TODO vom Namen her war eigentlich gedacht, die Zustaende wirklich zu
	// tauschen
	// was auch bei dieser Implementierung einfach geht
	this.boardArray[xe][ye] = this.boardArray[xs][ys];
	this.boardArray[xs][ys] = toChange;
    }

    /**
     * Teilt das Spielbrett in benutzbare und unbenutzbare Felder ein
     * (unbenutzbare Felder haben den Wert -1)
     */
    private void markUsablePositions() {
	for (byte i = 0; i < 17; i++) {
	    for (byte j = 0; j < 13; j++) {
		if (HalmaMath.isOnBoard(i, j)) {
		    this.boardArray[i][j] = 0;
		} else {
		    this.boardArray[i][j] = -1;
		}
	    }
	}
    }

    /**
     * fuellt das Spielbrett initial mit Spielsteinen Positionen anhand der
     * Anzahl der Spieler (2,3,4 und 6)
     * 
     * @param num
     *                int (Spieleranzahl)
     */
    private void fillWithPlayers(int num) {
	switch (num) {
	case 2:
	    fillHouse(1, 1);
	    fillHouse(2, 4);
	    break;
	case 3:
	    fillHouse(1, 1);
	    fillHouse(2, 3);
	    fillHouse(3, 5);
	    break;
	case 4:
	    fillHouse(1, 2);
	    fillHouse(2, 3);
	    fillHouse(3, 5);
	    fillHouse(4, 6);
	    break;
	case 6:
	    for (int i = 1; i <= num; i++) {
		fillHouse(i, i);
	    }
	    break;

	}
    }

    /**
     * fuellt ein Haus (eine Ecke des Spielfeldes) mit Spielsteinen des
     * gegebenen Spielers
     * 
     * @param pl
     *                int (Spielernummer)
     * @param h
     *                int (Hausnummer)
     */
    private void fillHouse(int pl, int h) {
	switch (h) {
	case 1:
	    for (int i = 0; i < 5; i++) {
		for (int j = 0; j <= (i / 2); j++) {
		    this.boardArray[i][6 + j] = (byte) pl;
		    this.boardArray[i][6 - j] = (byte) pl;
		}
	    }
	    this.boardArray[1][5] = (byte) pl;
	    this.boardArray[3][4] = (byte) pl;
	    break;
	case 2:
	    for (int i = 0; i < 5; i++) {
		for (int j = 0; j <= (i / 2); j++) {
		    this.boardArray[8 - i][10 + j] = (byte) pl;
		    this.boardArray[8 - i][10 - j] = (byte) pl;
		}
	    }
	    this.boardArray[7][9] = (byte) pl;
	    this.boardArray[5][8] = (byte) pl;
	    break;
	case 3:
	    for (int i = 0; i < 5; i++) {
		for (int j = 0; j <= (i / 2); j++) {
		    this.boardArray[8 + i][10 + j] = (byte) pl;
		    this.boardArray[8 + i][10 - j] = (byte) pl;
		}
	    }
	    this.boardArray[9][9] = (byte) pl;
	    this.boardArray[11][8] = (byte) pl;
	    break;
	case 4:
	    for (int i = 0; i < 5; i++) {
		for (int j = 0; j <= (i / 2); j++) {
		    this.boardArray[8 - i][2 + j] = (byte) pl;
		    this.boardArray[8 - i][2 - j] = (byte) pl;
		}
	    }
	    this.boardArray[7][1] = (byte) pl;
	    this.boardArray[5][0] = (byte) pl;
	    break;
	case 5:
	    for (int i = 0; i < 5; i++) {
		for (int j = 0; j <= (i / 2); j++) {
		    this.boardArray[8 + i][2 + j] = (byte) pl;
		    this.boardArray[8 + i][2 - j] = (byte) pl;
		}
	    }
	    this.boardArray[9][1] = (byte) pl;
	    this.boardArray[11][0] = (byte) pl;
	    break;
	case 6:
	    for (int i = 0; i < 5; i++) {
		for (int j = 0; j <= (i / 2); j++) {
		    this.boardArray[16 - i][6 + j] = (byte) pl;
		    this.boardArray[16 - i][6 - j] = (byte) pl;
		}
	    }
	    this.boardArray[15][5] = (byte) pl;
	    this.boardArray[13][4] = (byte) pl;
	    break;
	}
    }
}
