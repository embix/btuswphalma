package com.googlecode.btuswphalma.gameengine;

/**
 * beschreibt eine Position eines Spielsteines auf dem Spielbrett
 * 
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
     * 
     * @param x
     *                byte (x Koordinate)
     * @param y
     *                byte (y Koordinate)
     */
    public BoardPosition(byte x, byte y) {
	this.xPos = x;
	this.yPos = y;
    }

    /**
     * gibt die x Koordinate aus
     * 
     * @return byte
     */
    public byte getXPos() {
	return this.xPos;
    }

    /**
     * gibt die y Koordinate aus
     * 
     * @return byte
     */
    public byte getYPos() {
	return this.yPos;
    }

    /**
     * ersetzt X Koordinate
     * 
     * @param x
     *                byte
     */
    public void setXPos(byte x) {
	this.xPos = x;
    }

    /**
     * ersetzt Y Koordinate
     * 
     * @param y
     *                byte
     */
    public void setYPos(byte y) {
	this.yPos = y;
    }
    
    /**
     * Eine BoardPosition wird als 2-Tupel in der Form [xpos, ypos] ausgegeben
     * @return ein String der die BoardPosition darstellt 
     */
    @Override
    public String toString() {
	return "[" + xPos + ", " + yPos + "]";
    }
}