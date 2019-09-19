package com.harry.robert.RWCSweepies.repository;

import com.harry.robert.RWCSweepies.model.Result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultRepository extends GenericRepository {

    Statement statement = createConnection();

    public ResultRepository() throws SQLException {
    }

    public List<Result> getResults() throws SQLException {
        String sql = "SELECT * FROM results " +
                "INNER JOIN fixtures ON results.matchId=fixtures.matchId " +
                "INNER JOIN teams ON fixtures.awayTeam=teams.teamId " +
                "INNER JOIN teams ON fixtures.homeTeam=teams.teamId ";
        return executeGetResultSQL(sql);
    }

    public List<Result> getTeamResults(String team) throws SQLException {
        String sql = String.format("SELECT * FROM results " +
                "INNER JOIN fixtures ON results.matchId=fixtures.matchId " +
                "INNER JOIN teams ON fixtures.awayTeam=teams.teamId " +
                "INNER JOIN teams ON fixtures.homeTeam=teams.teamId " +
                "WHERE homeTeam = '%s' OR awayTeam = '%s'", team, team);
        return executeGetResultSQL(sql);
    }

    public List<Result> getTypeResults(String type) throws SQLException {
        String sql = String.format("SELECT * FROM results " +
                "INNER JOIN fixtures ON results.matchId=fixtures.matchId " +
                "INNER JOIN teams ON fixtures.awayTeam=teams.teamId " +
                "INNER JOIN teams ON fixtures.homeTeam=teams.teamId " +
                "WHERE gameType = '%s'", type);
        return executeGetResultSQL(sql);
    }

    public void updateResults(Result result) throws SQLException {
        String sql = String.format("INSERT INTO results" +
                "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)", result.getHomeTeam(), result.getHomeScore(), result.getHomeTries(),
                result.getHomeConversions(), result.getHomePenalties(), result.getAwayTeam(), result.getAwayScore(), result.getAwayTries(),
                result.getAwayConversions(), result.getAwayPenalties());

        statement.getConnection();
        statement.execute(sql);
    }

    private List<Result> executeGetResultSQL(String sql) throws SQLException {
        statement.getConnection();
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();

        List<Result> results = new ArrayList<>();

        while (resultSet.next()) {
            Result result = new Result();
            result.setMatchId(resultSet.getInt("matchId"));
            result.setHomeTeam(resultSet.getString("homeTeam"));
            result.setHomeScore(resultSet.getInt("homeScore"));
            result.setHomeTries(resultSet.getInt("homeTries"));
            result.setHomeConversions(resultSet.getInt("homeConversions"));
            result.setHomePenalties(resultSet.getInt("homePenalties"));
            result.setAwayScore(resultSet.getInt("awayScore"));
            result.setAwayTries(resultSet.getInt("awayTries"));
            result.setAwayConversions(resultSet.getInt("awayConversions"));
            result.setAwayPenalties(resultSet.getInt("awayPenalties"));
            result.setAwayTeam(resultSet.getString("awayTeam"));

            results.add(result);
        }

        return results;
    }
}
