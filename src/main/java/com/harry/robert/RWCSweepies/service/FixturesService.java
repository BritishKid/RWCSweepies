package com.harry.robert.RWCSweepies.service;

import com.harry.robert.RWCSweepies.model.Match;
import com.harry.robert.RWCSweepies.repository.FixturesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FixturesService {

    private final FixturesRepository fixturesRepository;

    public List<Match> getFixtures() throws SQLException {
        return fixturesRepository.getAllFixtures();
    }
}
