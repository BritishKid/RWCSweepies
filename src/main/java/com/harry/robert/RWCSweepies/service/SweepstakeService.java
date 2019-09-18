package com.harry.robert.RWCSweepies.service;


import com.harry.robert.RWCSweepies.exception.NotEnoughPeopleException;
import com.harry.robert.RWCSweepies.model.Team;
import com.harry.robert.RWCSweepies.repository.SweepstakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SweepstakeService {

    private final SweepstakeRepository sweepstakeRepository;

    public void addParticipant(String participant) throws SQLException {
        if (validateParticipants()) sweepstakeRepository.addParticipant(participant);
    }

    public Map<String, Team> generate() throws SQLException, NotEnoughPeopleException {
        List<String> participantList = sweepstakeRepository.getParticipants();

        if (participantList.size() != 20) {
            throw new NotEnoughPeopleException(String.format("Not enough parcipants only have %d of 20", participantList.size()));
        }

        List<Team> teamList = sweepstakeRepository.getTeams();

        Map<String, Team> results = new HashMap<>();

        while (!participantList.isEmpty()) {
            int randomValue1 = (int) (Math.random() * participantList.size());
            int randomValue2 = (int) (Math.random() * teamList.size());

            results.put(participantList.get(randomValue1), teamList.get(randomValue2));

            participantList.remove(randomValue1);
            teamList.remove(randomValue2);
        }

        return results;
    }

    public void addPersistingParticipant(String participant) throws SQLException {
        String persist = String.format("insert into participants values ('%s')", participant);

        Path path = Paths.get("data.sql");

        try {
            Files.write(path, persist.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println(e);
        }

        addParticipant(participant);
    }

    private boolean validateParticipants() throws SQLException {
        return sweepstakeRepository.getParticipants().size() < 20;
    }
}
