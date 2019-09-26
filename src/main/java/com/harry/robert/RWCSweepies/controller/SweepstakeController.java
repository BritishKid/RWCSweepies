package com.harry.robert.RWCSweepies.controller;

import com.harry.robert.RWCSweepies.exception.NotEnoughPeopleException;
import com.harry.robert.RWCSweepies.model.Team;
import com.harry.robert.RWCSweepies.service.SweepstakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SweepstakeController {

    private final SweepstakeService sweepstakeService;

    @RequestMapping(path="/participant={participant}")
    public void addParticipant(@PathVariable("participant") String participant) throws SQLException {
        sweepstakeService.addParticipant(participant);
    }

    @RequestMapping(path="/participant={participant}/persist=true")
    public void addPersistantParticipant(@PathVariable("participant") String participant) throws SQLException {
        sweepstakeService.addPersistingParticipant(participant);
    }

    @RequestMapping(path="/generate")
    public Map<String, Team> generateSweepstake() throws SQLException, NotEnoughPeopleException {
        return sweepstakeService.generate();
    }

    @RequestMapping(path="/sweepstake")
    public Map<String, Team> getSweepstake() throws SQLException {
        return sweepstakeService.getSweepstake();
    }
}
