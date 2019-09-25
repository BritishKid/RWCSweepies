package com.harry.robert.RWCSweepies.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class Match {
    private int matchId;
    private int homeTeam;
    private int awayTeam;
    private String homeTeamName;
    private String awayTeamName;
    private LocalDate date;
    private LocalTime kickOff;
    private String location;
    private String gameType; //todo enum
}
