package com.harry.robert.RWCSweepies.service;

import com.harry.robert.RWCSweepies.model.Result;
import com.harry.robert.RWCSweepies.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultsService {

    private final ResultRepository resultRepository;

    public List<Result> getResults() throws SQLException {
        return resultRepository.getResults();
    }

    public List<Result> getTypeResults(String type) throws SQLException {
        return resultRepository.getTypeResults(type);
    }

    public List<Result> getTeamResults(String team) throws SQLException {
        return resultRepository.getTeamResults(team);
    }

    public void updateResults(Result result) throws SQLException {
        resultRepository.updateResults(result);
    }
}
