package BrazeSoccer.utilities;

import BrazeSoccer.league.Team;
import utilities.Constant;

public class Match {
    private static int matchCount = 0;
    private final int matchID;
    private final double temperature;
    private final Team awayTeam;
    private final Team homeTeam;

    int awayTeamPoints;
    int homeTeamPoints;

    public Match(double temperature, Team awayTeam, Team homeTeam) {
        matchCount++;
        matchID = matchCount;

        this.temperature = temperature;
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;



        if(temperature >= 30) {
            awayTeamPoints = pointScorer.pointsScored((int) (temperature/5));
            homeTeamPoints = pointScorer.pointsScored((int) (temperature/5));
        } else {
            awayTeamPoints = pointScorer.pointsScored(0);
            homeTeamPoints = pointScorer.pointsScored(0);
        }
        awayTeam.setPointsScored(awayTeamPoints);
        awayTeam.setPointsAllowed(homeTeamPoints);

        homeTeam.setPointsScored(homeTeamPoints);
        homeTeam.setPointsAllowed(awayTeamPoints);

        if (awayTeamPoints > homeTeamPoints) {
            awayTeam.setWins(1);
            homeTeam.setLosses(1);
        } else if (awayTeamPoints < homeTeamPoints) {
            awayTeam.setLosses(1);
            homeTeam.setWins(1);
        } else {
            awayTeam.setTies(1);
            homeTeam.setTies(1);
        }
    }

    public String getMatchInformations() {
        return  "Jogo #" + matchID + " | Temperatura: " + temperature + Constant.APP_TEMPERATURE +
                "\nTime visitante: " + awayTeam.getName() + " | " + awayTeamPoints +
                "\nTime da casa: " + homeTeam.getName() + " | " + homeTeamPoints;
    }

    public double getTemperature() {
        return temperature;
    }
}
