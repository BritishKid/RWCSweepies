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
        String sql = "SELECT * FROM results INNER JOIN fixtures ON results.matchId=fixtures.matchId";
        return executeGetResultSQL(sql);
    }

    private List<Result> executeGetResultSQL(String sql) throws SQLException {
        statement.getConnection();
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();

        List<Result> results = new ArrayList<>();

        while (resultSet.next()) {
            Result result = new Result();
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
