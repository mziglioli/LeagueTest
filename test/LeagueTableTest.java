
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.league.LeagueTable;
import com.league.LeagueTableEntry;
import com.league.Match;

public class LeagueTableTest {

	private List<LeagueTableEntry> leagueTableEntries;

	@Test
	public void test() {
		List<Match> matches = buildMatches();
		LeagueTable leagueTable = new LeagueTable(matches);

		leagueTableEntries = leagueTable.getTableEntries();

		LeagueTableEntry top = leagueTableEntries.get(0);
		LeagueTableEntry down = leagueTableEntries.get(3);

		assertEquals(4, leagueTableEntries.size());
		assertEquals("Chelsea", top.getTeamName());
		assertEquals("Manchester United", down.getTeamName());

	}

	private List<Match> buildMatches() {
		List<Match> matches = new ArrayList<>();
		Match m = new Match("Chelsea", "Manchester City", 2, 1);
		Match m1 = new Match("Manchester City", "Chelsea", 1, 2);
		matches.add(m);
		matches.add(m1);
		m = new Match("Chelsea", "Manchester United", 2, 1);
		m1 = new Match("Manchester United", "Chelsea", 1, 2);
		matches.add(m);
		matches.add(m1);
		m = new Match("Chelsea", "Liverpol", 0, 0);
		m1 = new Match("Liverpol", "Chelsea", 0, 0);
		matches.add(m);
		matches.add(m1);

		return matches;
	}

}
