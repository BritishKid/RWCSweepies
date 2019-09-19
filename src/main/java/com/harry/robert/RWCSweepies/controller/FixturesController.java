package com.harry.robert.RWCSweepies.controller;

import com.harry.robert.RWCSweepies.model.Match;
import com.harry.robert.RWCSweepies.service.FixturesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FixturesController {

    private final FixturesService fixturesService;

    @RequestMapping(path="/fixtures")
    public List<Match> getFixtures() throws SQLException {
        return fixturesService.getFixtures();
    }
}
