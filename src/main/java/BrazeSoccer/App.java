package BrazeSoccer;

import BrazeSoccer.league.Team;
import utilities.Constant;
import utilities.Input;
import BrazeSoccer.utilities.Match;
import utilities.Checker;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class App {
    static ArrayList<Match> matchesPlayed;
    static Team[] team =  new Team[4];
    public static void main(String[] args) {
        matchesPlayed = new ArrayList<>();

        createTeams();
        run();
    }
    public static void createTeams() {
        team[0] = new Team("LOUD");
        team[1] = new Team("Cloud9");
        team[2] = new Team("NRG");
        team[3] = new Team("Leviatán");
    }

    public static void run() {
        String message;

        boolean endLeague = false;
        int coldWeek = 0;

        while (!endLeague) {

            message = "Temperatura em Celsius";
            double temperature = Input.inputDouble(message);
            if (Checker.doubleVerify(temperature)) return;

            if (temperature <= 5) {
                message = "Muito frio para jogar";
                JOptionPane.showMessageDialog(
                        null,
                        message,
                        Constant.APP_NAME_BLAZE,
                        JOptionPane.ERROR_MESSAGE
                );

                coldWeek++;
            } else {
                Random random = new Random();
                int[] teamNumbers = {0, 1, 2, 3};

                for (int i = 3; i > 0; i--) {
                    int j = random.nextInt(i + 1);
                    int temp = teamNumbers[i];
                    teamNumbers[i] = teamNumbers[j];
                    teamNumbers[j] = temp;
                }

                Team[] firstMatch = {team[teamNumbers[0]], team[teamNumbers[1]]};
                Team[] secondMatch = {team[teamNumbers[2]], team[teamNumbers[3]]};

                Match matchOne = new Match(temperature, firstMatch[0], firstMatch[1]);
                matchesPlayed.add(matchOne);

                Match matchTwo = new Match(temperature, secondMatch[0], secondMatch[1]);
                matchesPlayed.add(matchTwo);

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(matchOne.getMatchInformations()).append("\n\n").append(matchTwo.getMatchInformations());

                JOptionPane.showMessageDialog(
                        null,
                        stringBuilder,
                        Constant.APP_NAME_BLAZE,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (coldWeek > 0) {
                    coldWeek--;
                }
            }
            if (coldWeek >= 3) {
                message = "Liga finalizada pois o inverno chegou!";
                JOptionPane.showMessageDialog(
                        null,
                        message,
                        Constant.APP_NAME_BLAZE,
                        JOptionPane.PLAIN_MESSAGE
                );
                if (matchesPlayed.size() > 0) {
                    StringBuilder teamsResults = new StringBuilder();
                    StringBuilder matchesResults = new StringBuilder();

                    for (int i = 0; i < 4; i++) {
                        teamsResults.append(team[i].getTeamResults()).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(
                            null,
                            teamsResults,
                            Constant.APP_NAME_BLAZE,
                            JOptionPane.PLAIN_MESSAGE
                    );


                    for (Match match: matchesPlayed) {
                        matchesResults.append(match.getMatchInformations()).append("\n\n");
                    }
                    matchesResults.append("Temperatura mais quente: ").append(getHottestTemperature()).append(Constant.APP_TEMPERATURE).append("\nTemperature média: ").append(getAverageTemperature()).append(Constant.APP_TEMPERATURE);
                    JOptionPane.showMessageDialog(
                            null,
                            matchesResults,
                            Constant.APP_NAME_BLAZE,
                            JOptionPane.PLAIN_MESSAGE
                    );
                }
                endLeague = true;
            }
        }
    }

    public static double getAverageTemperature() {
        double totalTemperature = 0;

        for (Match match: matchesPlayed) {
            totalTemperature += match.getTemperature();
        }

        return totalTemperature / matchesPlayed.size();
    }

    public static double getHottestTemperature() {
        double hottestTemperature = Double.MIN_VALUE;

        for (Match match: matchesPlayed) {
            double temperature = match.getTemperature();
            if (temperature > hottestTemperature) {
                hottestTemperature = temperature;
            }
        }

        return hottestTemperature;
    }
}
