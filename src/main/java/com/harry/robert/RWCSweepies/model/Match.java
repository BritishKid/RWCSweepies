package com.harry.robert.RWCSweepies.model;

import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
public class Match {
    private int matchId;
    private String homeTeam;
    private String awayTeam;
    private LocalDate date;
    private LocalTime kickOff;
    private String location;
    private String gameType; //todo enum
}
