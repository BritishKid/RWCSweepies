package com.harry.robert.RWCSweepies.repository;

import com.harry.robert.RWCSweepies.model.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FixturesRepository extends GenericRepository {

    private Statement statement = createConnection();

    public FixturesRepository() throws SQLException {
    }

    public List<Match> getAllFixtures() throws SQLException {
        String sql = "SELECT * FROM fixtures ";
        return executeGetFixtureSQL(sql);
    }

    public List<Match> getTeamFixtures(int team) throws SQLException {
        String sql = String.format("SELECT * FROM fixtures " +
                "WHERE homeTeam = '%s' OR awayTeam = '%s'", team, team);
        return executeGetFixtureSQL(sql);
    }

    public List<Match> getTypeFixtures(String type) throws SQLException {
        String sql = String.format("SELECT * FROM fixtures " +
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
            match.setHomeTeam(resultSet.getInt("homeTeam"));
            match.setAwayTeam(resultSet.getInt("awayTeam"));
            match.setDate(resultSet.getDate("date").toLocalDate());
            match.setKickOff(resultSet.getTime("kickOff").toLocalTime());
            match.setLocation(resultSet.getString("location"));
            match.setGameType(resultSet.getString("gameType"));
            fixtures.add(match);
        }

        return fixtures;
    }
}
