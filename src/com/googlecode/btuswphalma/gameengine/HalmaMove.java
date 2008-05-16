package com.googlecode.btuswphalma.gameengine;

import java.util.ArrayList;

/**
 * ein Halmazug beinhaltet den Zug eines Spielsteines,
 * d.h. die Startposition, die Endposition und 
 * eventuelle Zwischenpositionen (Sprung-Zug)
 * @author Christoph
 *
 */
public class HalmaMove {

	/** ArrayList(BoardPositions)*/
	private ArrayList<BoardPosition> positions =
		new ArrayList<BoardPosition>();
	
	/**
	 * Konstruktor, beinhaltet folgende
	 * @param pos ArrayList(BoardPosition)
	 */
	public HalmaMove(ArrayList<BoardPosition> pos) {
		if (pos.size() < 2) {
			System.err.println("kein vollstaendiger Zug");
		}
		this.positions = pos;
	}

	/**
	 * gibt die Startposition eines Zuges aus
	 * @return Boardposition (byte, byte)
	 */
	public BoardPosition getStartPosition() {
		return this.positions.get(0);
	}
	
	/**
	 * gibt die Endposition eines Zuges aus
	 * @return Boardposition (byte, byte)
	 */
	public BoardPosition getEndPosition() {
		int lpos = this.positions.size() - 1;
		return this.positions.get(lpos);
	}
	
	/**
	 * gibt die Anzahl der Zwischenpositionen aus
	 * @return int
	 */
	public int getNumberOfPartMoves() {
		int num = positions.size() - 2;
		return num;
	}
	
	/**
	 * gibt eine Spielfeldposition zu einem Laufindex aus
	 * @param pos int (Laufindex im Feld)
	 * @return Boardposition (byte, byte)
	 */
	public BoardPosition getPartPosition(int pos) {
		if ((pos >= this.positions.size()) || (pos < 0)) {
			System.err.println("Position außerhalb des Feldes");
		}
		return this.positions.get(pos);
	}
	
	public void addBoardPosition(int pos) {
		
	}
}
