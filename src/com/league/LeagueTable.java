package com.league;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LeagueTable {

	private List<LeagueTableEntry> entries;

	/**
	 * Create a league table from the supplied list of match results
	 * 
	 * @param matches
	 */
	public LeagueTable(final List<Match> matches) {
		if (matches != null && !matches.isEmpty()) {
			final Map<String, LeagueTableEntry> map = new HashMap<>();
			try (Stream<Match> stream = matches.stream()) {
				stream.forEach(m -> {
					if (!m.getHomeTeam().equals(m.getAwayTeam())) {
						LeagueTableEntry homeTeam = map.getOrDefault(m.getHomeTeam(),
								new LeagueTableEntry(m.getHomeTeam()));
						LeagueTableEntry awayTeam = map.getOrDefault(m.getAwayTeam(),
								new LeagueTableEntry(m.getAwayTeam()));

						homeTeam.addPlayed();
						awayTeam.addPlayed();

						int diff = Math.abs(m.getHomeScore() - m.getAwayScore());

						homeTeam.addGoalsFor(m.getHomeScore());
						homeTeam.addGoalsAgainst(m.getAwayScore());
						homeTeam.addGoalDifference(diff);

						awayTeam.addGoalsFor(m.getAwayScore());
						awayTeam.addGoalsAgainst(m.getHomeScore());
						awayTeam.addGoalDifference(diff);

						if (m.getHomeScore() > m.getAwayScore()) {
							homeTeam.addWon();
							homeTeam.addPoints(3);
							awayTeam.addLost();
						} else if (m.getHomeScore() < m.getAwayScore()) {
							homeTeam.addLost();
							awayTeam.addWon();
							awayTeam.addPoints(3);
						} else {
							homeTeam.addDrawn();
							homeTeam.addPoints(1);
							awayTeam.addDrawn();
							awayTeam.addPoints(1);
						}

						map.put(homeTeam.getTeamName(), homeTeam);
						map.put(awayTeam.getTeamName(), awayTeam);
					} else {
						LeagueTableEntry team = map.getOrDefault(m.getHomeTeam(),
								new LeagueTableEntry(m.getHomeTeam()));
						team.addPlayed();
						team.addPoints(3);
						map.put(team.getTeamName(), team);
					}
				});
			}
		}

	}

	/**
	 * Get the ordered list of league table entries for this league table. objects are sorted by points, goal
	 * difference, goals for and then team names. The normal rules for scoring points apply.
	 * 
	 * @return
	 */
	public List<LeagueTableEntry> getTableEntries() {
		return entries;
	}

}
