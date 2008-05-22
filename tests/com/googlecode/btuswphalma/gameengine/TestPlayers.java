package com.googlecode.btuswphalma.gameengine;

/**
 * Testklasse fuer PlayerList und Player
 * @author Christoph
 *
 */
public class TestPlayers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String names[] = {"name1", "name2", "name3", "name4", "name5", "name6"};
		PlayerList players = new PlayerList();
		
		for (int i=1; i<7; i++) {
			Player player = new Player(i, names[i-1], false, 2*i);
			players.addPlayer(player);
		}
		
		for (int i=7; i<=players.getSize(); i++) {
			System.out.println(players.getPlayer(i).getName());
		}
		
		int pos = 3;
		int runde = 20;
		String spname = "ersetzung";
		Player ersatz = new Player(pos, spname, true, runde);
		players.setPlayer(ersatz);
		
		for(int i=7; i<=players.getSize(); i++) {
			System.out.println(players.getPlayer(i).getName());
		}
		
		
		spname = "anhang";
		Player anhang = new Player(8, spname, false, runde);
		players.setPlayer(anhang);
		
		for(int i=1; i<=players.getSize();  i++) {
			Player play = players.getPlayer(i);
			System.out.print(play.getName() + ' ' + play.getID() + ' ');
			System.out.println(play.getRounds() + "  " + String.valueOf(play.getSpectator()));
		}
	}

}
