package section_7.utilities;

import section_7.people.Person;
import utilities.Constant;

import javax.swing.*;
import java.util.Random;

public class ArcadeGames {
    static String[] awardCategory = {"Ouro", "Prata", "Bronze"};
    public static void guessNumber(Person person) {
        Random random = new Random();

        if(payCostToPlay(person)) {
            int number = random.nextInt(10) + 1;
            int attempts;
            boolean won = false;
            String message = "Bem-vindo ao " + Constant.GUESS_GAME_NAME +
                    "\nTente adivinhar o número entre 1 e 10";

            JOptionPane.showMessageDialog(
                    null,
                    message,
                    Constant.APP_NAME_BLAZE,
                    JOptionPane.PLAIN_MESSAGE
            );

            for (attempts = 1; attempts < 5; attempts++) {
                message = "º Tentativa";
                int guess = Integer.parseInt(JOptionPane.showInputDialog(null,
                        attempts + message,
                        Constant.GUESS_GAME_NAME,
                        JOptionPane.PLAIN_MESSAGE
                ));

                if (guess < number) {
                    message = "Tente um número maior!";
                    JOptionPane.showMessageDialog(
                            null,
                            message,
                            Constant.GUESS_GAME_NAME,
                            JOptionPane.PLAIN_MESSAGE
                    );
                } else if (guess > number) {
                    message = "Tente um número menor!";
                    JOptionPane.showMessageDialog(
                            null,
                            message,
                            Constant.GUESS_GAME_NAME,
                            JOptionPane.PLAIN_MESSAGE
                    );
                } else {
                    won = true;
                    break;
                }
            }

            if (won) {
                message = "Você venceu!";
                JOptionPane.showMessageDialog(
                        null,
                        message,
                        Constant.GUESS_GAME_NAME,
                        JOptionPane.PLAIN_MESSAGE
                );
                if(attempts == 1) {
                    person.getCard().setTicketBalance(generateTickets(awardCategory[0]));
                } else if(attempts <= 3) {
                    person.getCard().setTicketBalance(generateTickets(awardCategory[1]));
                } else {
                    person.getCard().setTicketBalance(generateTickets(awardCategory[2]));
                }
            } else {
                message = "Você perdeu!";
                JOptionPane.showMessageDialog(
                        null,
                        message,
                        Constant.GUESS_GAME_NAME,
                        JOptionPane.PLAIN_MESSAGE
                );
            }
        }
    }

    public static int generateTickets(String awardCategory) {
        Random random = new Random();
        int award;
        if(awardCategory.equals("Ouro")) {
            award = random.nextInt(10) + 10;
        } else if(awardCategory.equals("Prata")) {
            award = random.nextInt(5) + 5;
        } else {
            award = random.nextInt(2) + 2;
        }
        return award;
    }

    public static boolean payCostToPlay(Person person) {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        String message = "Deseja pagar " + Constant.COST_TO_PLAY + " para jogar?";
        int userChoice = JOptionPane.showConfirmDialog(null,
                message,
                Constant.APP_NAME_BLAZE,
                JOptionPane.YES_NO_OPTION
                );
        if(userChoice == JOptionPane.YES_OPTION) {
            return person.getCard().pay(Constant.COST_TO_PLAY);
        }
        return false;
    }
}