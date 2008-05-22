package com.googlecode.btuswphalma.gameengine;

/**
 * Testklasse fuer ScoreList
 * @author Christoph
 *
 */
public class TestScores {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScoreList scoreliste = new ScoreList();
		String names[] = {"name1", "name2", "name3", "name4", "name5", "name6"};
		
		for(int i=1; i<4; i++) {
			ScoreEntry eintrag = new ScoreEntry(i, names[i-1], 12+i);
			scoreliste.addEntry(eintrag);			
		}
		
		/**
		for(int i=1; i<=scoreliste.getSize(); i++) {
			String x = scoreliste.getEntry(i).getName();
			System.out.println(x);
		}*/
		
		int pos = 3;
		int runde = 20;
		String spname = "ersetzung";
		ScoreEntry ersatz = new ScoreEntry(pos, spname, runde);
		scoreliste.setEntry(ersatz);
		
		
		for(int i=1; i<=scoreliste.getSize(); i++) {
			String x = scoreliste.getEntry(i).getName();
			System.out.println(x);
		}
		
		spname = "anhang";
		ScoreEntry anhang = new ScoreEntry(8, spname, runde);
		scoreliste.setEntry(anhang);
		
		for(int i=1; i<=scoreliste.getSize();  i++) {
			String x = scoreliste.getEntry(i).getName();
			System.out.println(x);
		}
	}

}
