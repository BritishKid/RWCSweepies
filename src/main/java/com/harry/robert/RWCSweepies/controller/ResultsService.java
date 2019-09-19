package com.harry.robert.RWCSweepies.controller;

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
}
