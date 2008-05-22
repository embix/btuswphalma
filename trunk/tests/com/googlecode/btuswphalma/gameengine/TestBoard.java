package com.googlecode.btuswphalma.gameengine;

/**
 * Testklasse fuer Board und BoardPosition
 * @author Christoph
 *
 */
public class TestBoard {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//init des Spielbrettes anhan der Spielerzahl
		int spnum = 4;	
		Board brett = new Board(spnum);
		
		/*Klone-check
		byte[][] klone = brett.getBoardArryClone();*/

		//einzelne Position zu Testzwecken
		byte x = 10;
		byte y = 11;
		BoardPosition pos = new BoardPosition(x, y);
		
		//Positionsstatus pruefen
		boolean iob = HalmaMath.isOnBoard(pos);
		System.out.println(String.valueOf(iob));
		byte state = brett.getPositionState(pos);
		System.out.println(String.valueOf(state));
		
		//init Spielerauffuellung pruefen
		byte z = brett.getPositionState(pos);
		boolean isc = (z == (byte)2);
		System.out.println(String.valueOf(isc));
	}

}
