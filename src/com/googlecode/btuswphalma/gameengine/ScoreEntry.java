package com.googlecode.btuswphalma.gameengine;

public class ScoreEntry {
	
	private	int ranking;
	private	String name;
	private	int rounds;

	public ScoreEntry(int rank, String spname, int runden) {
		this.ranking = rank;
		this.name = spname;
		this.rounds = runden;
	}
	
	public int ranking() {
		return this.ranking;
	}
	
	public String name() {
		return this.name;
	}
	
	public int rounds() {
		return this.rounds;
	}
	
	public void setRanking(int rank) {
		this.ranking = rank;
	}

	public void setName(String spname) {
		this.name = spname;
	}
	
	public void setRounds(int runden) {
		this.rounds = runden;
	}
}
