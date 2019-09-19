package com.harry.robert.RWCSweepies.repository;

import com.harry.robert.RWCSweepies.model.Match;
import com.harry.robert.RWCSweepies.model.Team;
import lombok.RequiredArgsConstructor;
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
        statement.getConnection();

        String sql = "SELECT * FROM fixtures";

        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();
        return filterResultSet(resultSet);
    }

    public List<Match> getTeamFixtures(String team) throws SQLException {
        statement.getConnection();

        String sql = String.format("SELECT * FROM fixtures WHERE homeTeam = '%s' OR awayTeam = '%s'", team, team);

        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();
        return filterResultSet(resultSet);
    }

    private List<Match> filterResultSet(ResultSet resultSet) throws SQLException {
        List<Match> fixtures = new ArrayList<>();

        while (resultSet.next()) {
            Match match = new Match();
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
