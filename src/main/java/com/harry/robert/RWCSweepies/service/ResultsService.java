package com.harry.robert.RWCSweepies.service;

import com.harry.robert.RWCSweepies.model.Match;
import com.harry.robert.RWCSweepies.model.Result;
import com.harry.robert.RWCSweepies.repository.ResultRepository;
import com.harry.robert.RWCSweepies.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResultsService {

    private final ResultRepository resultRepository;
    private final TeamRepository teamRepository;


    public List<Result> getResults() throws SQLException {
        return addTeamNames(resultRepository.getResults());
    }

    public List<Result> getTypeResults(String type) throws SQLException {
        return addTeamNames(resultRepository.getTypeResults(type));
    }

    public List<Result> getTeamResults(String team) throws SQLException {
        return addTeamNames(resultRepository.getTeamResults(team));
    }

    public void updateResults(Result result) throws SQLException {
        resultRepository.updateResults(result);
    }

    private List<Result> addTeamNames(List<Result> allResults) throws SQLException {
        Map<Integer, String> teamIdToNameMap = teamRepository.getTeamIdToNameMap();
        for (Result result: allResults) {
            result.setAwayTeamName(teamIdToNameMap.get(result.getAwayTeamId()));
            result.setHomeTeamName(teamIdToNameMap.get(result.getHomeTeamId()));
        }
        return allResults;
    }
}
