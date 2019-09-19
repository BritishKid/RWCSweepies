create table participants
(
    name varchar(255) not null

);

create table teams
(
    name varchar(255) not null,
    url varchar(255),
    pool varchar(15)
);

create table fixtures
(
    homeTeam varchar(255),
    awayTeam varchar(255),
    date DATE,
    kickoff TIME,
    location varchar(255),
    gameType varchar(255)
);