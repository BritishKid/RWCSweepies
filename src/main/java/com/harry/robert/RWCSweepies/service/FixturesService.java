package com.harry.robert.RWCSweepies.service;

import com.harry.robert.RWCSweepies.model.Match;
import com.harry.robert.RWCSweepies.repository.FixturesRepository;
import com.harry.robert.RWCSweepies.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FixturesService {

    private final FixturesRepository fixturesRepository;
    private final TeamRepository teamRepository;

    public List<Match> getFixtures() throws SQLException {
        List<Match> allFixtures = fixturesRepository.getAllFixtures();

        return addTeamNames(allFixtures);
    }

    public List<Match> getTeamFixtures(String team) throws SQLException {
        Map<String, Integer> nameToIdMap = teamRepository.getNameToIdMap();
        List<Match> teamFixtures = fixturesRepository.getTeamFixtures(nameToIdMap.get(team));
        return addTeamNames(teamFixtures);
    }
    public List<Match> getTypeFixtures(String type) throws SQLException {
        List<Match> typeFixtures = fixturesRepository.getTypeFixtures(type);
        return addTeamNames(typeFixtures);
    }

    private List<Match> addTeamNames(List<Match> allFixtures) throws SQLException {
        Map<Integer, String> teamIdToNameMap = teamRepository.getTeamIdToNameMap();
        for (Match match: allFixtures) {
            match.setAwayTeamName(teamIdToNameMap.get(match.getAwayTeam()));
            match.setHomeTeamName(teamIdToNameMap.get(match.getHomeTeam()));
        }
        return allFixtures;
    }
}
