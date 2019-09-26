package com.harry.robert.RWCSweepies.repository;

import com.harry.robert.RWCSweepies.model.Team;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TeamRepository extends GenericRepository{

    private Statement statement = createConnection();

    public TeamRepository() throws SQLException {
    }

    //todo speerate participants into own repository
    public void addParticipant(String participant) throws SQLException {
            String sql = String.format("INSERT INTO participant (name) VALUES (%s)", participant);

            statement.execute(sql);
    }

    public List<String> getParticipants() throws SQLException {
        statement = createConnection();

        String sql = "SELECT * FROM participants";

        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();
        List<String> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(resultSet.getString("participantName"));
        }

        return result;
    }

    public List<Team> getTeams() throws SQLException {
        statement = createConnection();

        String sql = "SELECT * FROM teams";

        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();
        List<Team> result = new ArrayList<>();

        while (resultSet.next()) {
            Team team = new Team();
            team.setTeamId(resultSet.getInt("teamId"));
            team.setName(resultSet.getString("teamName"));
            team.setUrl(resultSet.getString("url"));
            team.setPool(resultSet.getString("pool"));
            result.add(team);
        }
        return result;
    }

    public Map<Integer, String> getTeamIdToNameMap() throws SQLException {
        String sql = "SELECT teamId, Name FROM TEAMS ";
        statement.getConnection();
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();

        Map<Integer, String> result = new HashMap<>();
        while (resultSet.next()) {
            result.put(resultSet.getInt("teamId"), resultSet.getString("teamName"));
        }

        return result;
    }

    public Map<String, Integer> getNameToIdMap() throws SQLException {
        String sql = "SELECT teamId, Name FROM TEAMS ";
        statement.getConnection();
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();

        Map<String, Integer> result = new HashMap<>();
        while (resultSet.next()) {
            result.put(resultSet.getString("teamName"), resultSet.getInt("teamId"));
        }

        return result;
    }

    public Map<String, Team> getSweepstake() throws SQLException {
        String sql = "SELECT * FROM SWEEPSTAKE " +
                "INNER JOIN participants ON SWEEPSTAKE.PARTICIPANT = PARTICIPANTS.id " +
                "INNER JOIN teams on sweepstake.team = teams.teamId";

        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();
        Map<String, Team> results = new HashMap<>();

        while (resultSet.next()) {
            Team team = new Team();
            team.setName(resultSet.getString("teamName"));
            team.setUrl(resultSet.getString("url"));
            team.setPool(resultSet.getString("pool"));
            results.put(resultSet.getString("participantName"), team);
        }

        return results;
    }
}
