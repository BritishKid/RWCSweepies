create table participants
(
    id int,
    participantName varchar(255) not null,
    PRIMARY KEY (id)
);

create table teams
(
    teamId int not null  AUTO_INCREMENT,
    teamName varchar(255) not null,
    url varchar(255),
    pool varchar(15),
    PRIMARY KEY (teamId)
);

create table fixtures
(
    matchId int not null  AUTO_INCREMENT,
    homeTeam int,
    awayTeam int,
    date DATE,
    kickoff TIME,
    location varchar(255),
    gameType varchar(255),
    PRIMARY KEY (matchId)
);

create table results
(
   matchId int not null  AUTO_INCREMENT,
   homeTeam int,
   homeScore int,
   homeTries int,
   homeConversions int,
   homePenalties int,
   awayTeam int,
   awayScore int,
   awayTries int,
   awayConversions int,
   awayPenalties int
);

create table sweepstake
(
    participant int,
    team int
)