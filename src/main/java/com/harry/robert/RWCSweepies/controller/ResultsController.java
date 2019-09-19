package com.harry.robert.RWCSweepies.controller;

import com.harry.robert.RWCSweepies.service.ResultsService;
import com.harry.robert.RWCSweepies.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(path = "/results/team={team}")
    public List<Result> getTeamResults(@PathVariable("team") String team) throws SQLException {
        return resultsService.getTeamResults(team);
    }

    @RequestMapping(path = "/results/type={type}")
    public List<Result> getTypeResults(@PathVariable("type") String type) throws SQLException {
        return resultsService.getTypeResults(type);
    }

    @RequestMapping(path = "/result/update")
    public void updateResults(@RequestBody Result result) throws SQLException {
        resultsService.updateResults(result);
    }
}
