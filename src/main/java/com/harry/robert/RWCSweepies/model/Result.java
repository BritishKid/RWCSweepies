package com.harry.robert.RWCSweepies.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result {
    private int matchId;
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamId;
    private int awayTeamId;
    private int homeScore;
    private int homeTries;
    private int homePenalties;
    private int homeConversions;
    private int awayScore;
    private int awayTries;
    private int awayConversions;
    private int awayPenalties;
}