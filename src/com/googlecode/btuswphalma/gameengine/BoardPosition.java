package com.googlecode.btuswphalma.gameengine;

import java.io.Serializable;

/**
 * beschreibt eine Position eines Spielsteines auf dem Spielbrett
 * 
 * @author Christoph
 * 
 */
public class BoardPosition implements Serializable {

    /**
     * generierte UID
     */
    private static final long serialVersionUID = -4622196834934154810L;

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
     * 
     * @return ein String der die BoardPosition darstellt
     */
    @Override
    public String toString() {
	return "[" + xPos + ", " + yPos + "]";
    }

    /**
     * Zwei Positionen sind gleich, wenn ihre x- und y-Werte gleich sind
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	//this und obj sind dasselbe Objekt
	if (this == obj) {
	    return true;
	}
	//obj ist nicht null und ine BoardPosition
	if (obj instanceof BoardPosition) {
	    BoardPosition newPos = (BoardPosition) obj;
	    return this.xPos == newPos.getXPos()
		    && this.yPos == newPos.getYPos();
	}
	//sicher ungleich
	return false;
    }
}