package com.googlecode.btuswphalma.gameengine;

/**
 * Testklasse fuer SimpleHalmaMoveChecker
 * 
 * @author Christoph
 *
 */
public class TestSimpleHalmaMoveChecker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SimpleHalmaMoveChecker hmc = new SimpleHalmaMoveChecker();
		
		/** ein 4-facher Sprung */ 
		HalmaMove sprung = new HalmaMove();
		/** ein einfacher Schub */
		HalmaMove schub = new HalmaMove();
		/** Schub mit falschem Stein */
		HalmaMove schubMF = new HalmaMove();
		/** ein Sprung nach ausserhalb 
		 * faellt raus, weil setMethoden Fehler abfangen*/
		HalmaMove nirvana = new HalmaMove();
		/** nur ein Position im Zug */
		HalmaMove eins = new HalmaMove();
		/** das Spielbrett */
		Board board = new Board(6);
		/** ein Spieler zum testen */
		int player = 1;
		/** x-Koordinate von pos */
		byte x = 0;
		/** y-Koordinate von pos */
		byte y = 0;
		/** eine Boardposition fuer Testzwecke */
		BoardPosition pos;
		
		/** die Testvariable */
		boolean test;
		
		//sprung auffuellen
		for (int i=0; i<=4; i++) {
			x = (byte)(3+i);
			y = (byte)(7-i);
			pos = new BoardPosition(x,y);
			sprung.addBoardPosition(pos);
		}
		//schub auffuellen, nirvana und eins startpos
		x = 3; y = 5;
		pos = new BoardPosition(x,y);
		schub.addBoardPosition(pos);
		nirvana.addBoardPosition(pos);
		eins.addBoardPosition(pos);
		x = 4; y = 6;
		pos = new BoardPosition(x,y);
		schub.addBoardPosition(pos);
		//schubMF auffuellen
		x = 5; y = 9;
		pos = new BoardPosition(x,y);
		schubMF.addBoardPosition(pos);
		x = 5; y = 8;
		pos = new BoardPosition(x,y);
		schubMF.addBoardPosition(pos);
		//nirvana ergaenzen
		x = 3; y = 8;
		pos = new BoardPosition(x,y);
		nirvana.addBoardPosition(pos);
		
		//testen und ausgabe
		test = hmc.checkMove(board, sprung, player);
		System.out.print("Sprung, ok: ");
		System.out.println(test);
		test = hmc.checkMove(board, schub, player);
		System.out.print("Schub, ok: ");
		System.out.println(test);
		test = hmc.checkMove(board, schubMF, player);
		System.out.print("Schub mit falschem Stein: ");
		System.out.println(test);
		test = hmc.checkMove(board, nirvana, player);
		System.out.print("Sprung ins Nirvana: ");
		System.out.println(test);
		test = hmc.checkMove(board, eins, player);
		System.out.print("nur eine Position: ");
		System.out.println(test);
		x = 5; y = 5;
		pos = new BoardPosition(x,y);
		y = 2;
		BoardPosition spos = new BoardPosition(x,y);
		board.exchangePositionState(pos, spos);
		test = hmc.checkMove(board, sprung, player);
		System.out.print("Sprung mit Fehler: ");
		System.out.println(test);
	}
}
