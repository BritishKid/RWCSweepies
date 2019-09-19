package com.harry.robert.RWCSweepies.service;

import com.harry.robert.RWCSweepies.controller.ResultsService;
import com.harry.robert.RWCSweepies.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResultsController {

    private final ResultsService resultsService;

    @RequestMapping(path = "/results")
    public List<Result> getResults() throws SQLException {
        return resultsService.getResults();
    }

}
