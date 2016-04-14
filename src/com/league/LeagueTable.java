package com.league;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeagueTable {

	private List<LeagueTableEntry> entries;

	Comparator<LeagueTableEntry> comparator = Comparator.comparing(LeagueTableEntry::getPoints).reversed()
			.thenComparing(LeagueTableEntry::getGoalDifference).reversed().thenComparing(LeagueTableEntry::getGoalsFor)
			.reversed().thenComparing(LeagueTableEntry::getTeamName);

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
					LeagueTableEntry homeTeam = map.getOrDefault(m.getHomeTeam(),
							new LeagueTableEntry(m.getHomeTeam()));
					LeagueTableEntry awayTeam = map.getOrDefault(m.getAwayTeam(),
							new LeagueTableEntry(m.getAwayTeam()));

					handleMatch(homeTeam, awayTeam, m);

					map.put(homeTeam.getTeamName(), homeTeam);
					map.put(awayTeam.getTeamName(), awayTeam);
				});
			}
			entries = map.values().stream().sorted(comparator).collect(Collectors.toList());
		}

	}

	private void handleMatch(LeagueTableEntry homeTeam, LeagueTableEntry awayTeam, Match match) {
		homeTeam.addPlayed();
		awayTeam.addPlayed();

		handleGoals(homeTeam, match.getHomeScore(), match.getAwayScore());
		handleGoals(awayTeam, match.getAwayScore(), match.getHomeScore());

		handleResult(homeTeam, awayTeam, match);
	}

	private void handleGoals(LeagueTableEntry team, int goalsFor, int goalsAgainst) {
		team.addGoalsFor(goalsFor);
		team.addGoalsAgainst(goalsAgainst);
		team.addGoalDifference(Math.abs(goalsFor - goalsAgainst));
	}

	private void handleResult(LeagueTableEntry homeTeam, LeagueTableEntry awayTeam, Match match) {
		if (match.getHomeScore() > match.getAwayScore()) {
			handleWon(homeTeam, awayTeam);
		} else if (match.getHomeScore() < match.getAwayScore()) {
			handleWon(awayTeam, homeTeam);
		} else {
			handleDrawn(homeTeam);
			handleDrawn(awayTeam);
		}
	}

	private void handleWon(LeagueTableEntry won, LeagueTableEntry lost) {
		won.addWon();
		won.addPoints(3);
		lost.addLost();
	}

	private void handleDrawn(LeagueTableEntry team) {
		team.addDrawn();
		team.addPoints(1);
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
