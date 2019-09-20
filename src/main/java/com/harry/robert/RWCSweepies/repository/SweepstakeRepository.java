package com.harry.robert.RWCSweepies.repository;

import com.harry.robert.RWCSweepies.model.Team;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SweepstakeRepository  extends GenericRepository{

    private Statement statement = createConnection();

    public SweepstakeRepository() throws SQLException {
    }

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
            result.add(resultSet.getString("name"));
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
            team.setName(resultSet.getString("name"));
            team.setUrl(resultSet.getString("url"));
            team.setPool(resultSet.getString("pool"));
            result.add(team);
        }
        return result;
    }
}
