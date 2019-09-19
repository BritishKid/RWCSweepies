create table participants
(
    name varchar(255) not null

);

create table teams
(
    teamId int not null,
    name varchar(255) not null,
    url varchar(255),
    pool varchar(15),
    PRIMARY KEY teamId
);

create table fixtures
(
    matchId int not null,
    homeTeam int,
    awayTeam int,
    date DATE,
    kickoff TIME,
    location varchar(255),
    gameType varchar(255),
    PRIMARY KEY matchId
);

create table results
(
   matchId int not null,
   homeScore int,
   homeTries int,
   homeConversions int,
   homePenalties int,
   awayScore int,
   awayTries int,
   awayConversions int,
   awayPenalties int
);