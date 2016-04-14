package com.league;

public class LeagueTableEntry {

	private String teamName;
	private int played;
	private int won;
	private int drawn;
	private int lost;
	private int goalsFor;
	private int goalsAgainst;
	private int goalDifference;
	private int points;

	public LeagueTableEntry(String teamName, int played, int won, int drawn, int lost, int goalsFor, int goalsAgainst,
			int goalDifference, int points) {
		this.teamName = teamName;
		this.played = played;
		this.won = won;
		this.drawn = drawn;
		this.lost = lost;
		this.goalsFor = goalsFor;
		this.goalsAgainst = goalsAgainst;
		this.goalDifference = goalDifference;
		this.points = points;
	}

	public LeagueTableEntry(String teamName) {
		this.teamName = teamName;
	}

	public void addPlayed() {
		this.played++;
	}

	public void addWon() {
		this.won++;
	}

	public void addDrawn() {
		this.drawn++;
	}

	public void addLost() {
		this.lost++;
	}

	public void addGoalsFor(int value) {
		this.goalsFor += value;
	}

	public void addGoalsAgainst(int value) {
		this.goalsAgainst += value;
	}

	public void addGoalDifference(int value) {
		this.goalDifference += value;
	}

	public void addPoints(int value) {
		this.points += value;
	}

	public String getTeamName() {
		return teamName;
	}

	public int getPlayed() {
		return played;
	}

	public int getWon() {
		return won;
	}

	public int getDrawn() {
		return drawn;
	}

	public int getLost() {
		return lost;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public int getGoalDifference() {
		return goalDifference;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return "LeagueTableEntry [teamName=" + teamName + ", played=" + played + ", won=" + won + ", drawn=" + drawn
				+ ", lost=" + lost + ", goalsFor=" + goalsFor + ", goalsAgainst=" + goalsAgainst + ", goalDifference="
				+ goalDifference + ", points=" + points + "]";
	}

}
