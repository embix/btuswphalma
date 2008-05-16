package com.googlecode.btuswphalma.gameengine;

/**
 * beschreibt eine Position eines Spielsteines auf dem Spielbrett
 * @author Christoph
 *
 */
public class BoardPosition {

	/** x Koordinate */
	private byte xPos;	
	
	/** y Koordinate */
	private byte yPos;
	
	/**
	 * Konstruktor, beinhaltet folgende 
	 * @param x byte (x Koordinate)
	 * @param y byte (y Koordinate)
	 */
	public BoardPosition(byte x, byte y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	/**
	 * gibt die x Koordinate aus
	 * @return byte
	 */
	public byte getXPos() {
		return this.xPos;
	}
	
	/**
	 * gibt die y Koordinate aus
	 * @return byte
	 */
	public byte getYPos() {
		return this.yPos;
	}
}