package com.googlecode.btuswphalma.gameengine;

/**
 * Testklasse fuer FinishChecker
 * @author Christoph
 *
 */
public class TestFinishChecker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PlayerList players = new PlayerList();
		
		for (int i=1; i<7; i++) {
			Player play = new Player(i, "name" + i, false, 2*i);
			players.addPlayer(play);
		}
		
		for (int i=1; i<6; i++) {
			Player play = players.getPlayer(i);
			play.setSpec(true);
		}

		FinishChecker check = new FinishChecker();
		
		boolean x = check.checkGameFinished(players);
		System.out.println(String.valueOf(x));
	}

}
