package com.harry.robert.RWCSweepies.repository;

import com.harry.robert.RWCSweepies.model.Match;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FixturesRepository extends GenericRepository {

    private Statement statement = createConnection();

    public FixturesRepository() throws SQLException {
    }

    public List<Match> getAllFixtures() throws SQLException {
        String sql = "SELECT * FROM fixtures " +
                "INNER JOIN teams ON fixtures.awayTeam=teams.teamId " +
                "INNER JOIN teams ON fixtures.homeTeam=teams.teamId";
        return executeGetFixtureSQL(sql);
    }

    public List<Match> getTeamFixtures(String team) throws SQLException {
        String sql = String.format("SELECT * FROM fixtures " +
                "INNER JOIN teams ON fixtures.awayTeam=teams.teamId " +
                "INNER JOIN teams ON fixtures.homeTeam=teams.teamId " +
                "WHERE homeTeam = '%s' OR awayTeam = '%s'", team, team);
        return executeGetFixtureSQL(sql);
    }

    public List<Match> getTypeFixtures(String type) throws SQLException {
        String sql = String.format("SELECT * FROM fixtures " +
                "INNER JOIN teams ON fixtures.awayTeam=teams.teamId " +
                "INNER JOIN teams ON fixtures.homeTeam=teams.teamId " +
                "WHERE gameType = '%s'", type);
        return executeGetFixtureSQL(sql);
    }

    private List<Match> executeGetFixtureSQL(String sql) throws SQLException {
        statement.getConnection();
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();

        List<Match> fixtures = new ArrayList<>();

        while (resultSet.next()) {
            Match match = new Match();
            match.setMatchId(resultSet.getInt("matchId"));
            match.setHomeTeam(resultSet.getString("homeTeam"));
            match.setAwayTeam(resultSet.getString("awayTeam"));
            match.setDate(resultSet.getDate("date").toLocalDate());
            match.setKickOff(resultSet.getTime("kickOff").toLocalTime());
            match.setLocation(resultSet.getString("location"));
            match.setLocation(resultSet.getString("gameType"));
            fixtures.add(match);
        }

        return fixtures;
    }
}
