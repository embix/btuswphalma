package com.googlecode.btuswphalma.gameengine;

/**
 * Das Board (Spielbrett) enthaelt alle Spielfeldpositionen 
 * und die Positionen aller Spielfeldsteine
 * @author Christoph
 */
public class Board {

	/** 2D Array für das Spielfeld (17 Reihen X 13 Spalten) */
	private byte[][] boardArray;
	
	//private byte bitmask = 0x01; //eventuell um den Status eines Feldpunktes zu bearbeiten
	
	/**
	 * Konstruktor der, in Abhaengigkeit von der Spielerzahl,
	 * das Spielfeld initialisiert 
	 * @param num int (Spieleranzahl)
	 */
	public Board(int num) {
		this.boardArray = new byte[17][13];
		//markUsablePositions();
		//fillWithPlayers(num);
	}

	/**
	 * gibt eine Kopie des aktuellen Spielbrettes aus
	 * @return 	byte[][]  (Spielbrett)
	 */
	public byte[][] getBoardArryClone() {
	    //FIXME hinten an das boardArray gehört ein .clone()
		byte[][] clone = this.boardArray;
		return clone;
	}
	
	/**
	 * gibt den Status einer Spielfeldposition aus
	 * @param pos BoardPosition (byte, byte)
	 * @return byte 
	 */
	public byte getPositionState(BoardPosition pos) {
		byte x = pos.getXPos();
		byte y = pos.getYPos();
		return this.boardArray[x][y];
	}
	
	/**
	 * setz den Wert von Board(spos) nach Board(epos) und loescht Board(spos)
	 * @param spos BoardPosition (byte, byte)
	 * @param epos BoardPosition (byte, byte)
	 */
	public void exchangePositionState(BoardPosition spos, BoardPosition epos) {
		byte xs = spos.getXPos();
		byte ys = spos.getYPos();
		byte xe = epos.getXPos();
		byte ye = epos.getYPos();
		byte toChange = this.boardArray[xs][ys];
		//TODO vom Namen her war eigentlich gedacht, die Zustaende wirklich zu tauschen
		//was auch bei dieser Implementierung einfach geht
		this.boardArray[xe][ye] = toChange;
		this.boardArray[xs][ys] = 0;
	}
}
