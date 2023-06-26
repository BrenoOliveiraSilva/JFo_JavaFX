package section_7;

import section_7.people.Card;
import section_7.people.Person;
import section_7.utilities.ArcadeGames;
import utilities.Constant;
import utilities.Input;
import utilities.Checker;
import utilities.Output;

import javax.swing.*;
import java.util.ArrayList;

public class App {
    static ArrayList<Person> registeredPeople;
    public static void main(String[] args) {
        registeredPeople = new ArrayList<>();
        menu();
    }
    public static void menu() {
        Object[] userOption = {"Jogar", "Registrar", "Pesquisar", "Depositar", "Transferir", "Resgatar prêmio", "Sair"};
        String message = "Escolha uma operação";
        int userChoice = -1;
        while (userChoice != 6) {
            userChoice = Input.inputOption(userOption, message);

            switch (userChoice) {
                case 0 -> play();
                case 1 -> register();
                case 2 -> searchPerson();
                case 3 -> deposit();
                case 4 -> transfer();
                case 5 -> receiveAward();
            }
        }
    }

    public static void register() {
        String message = "Nome";
        String name = Input.inputString(message);
        if (Checker.stringVerify(name)) return;

        Card card = new Card();
        Person person = new Person(name, card);
        registeredPeople.add(person);
    }

    public static void play() {
        if(registeredPeople.size() > 0) {
            int personID = getPersonID();
            if (Checker.integerVerify(personID)) return;

            Person person = findPerson(personID);

            if(person != null) {
                Object[] userOption = {"Guess Number", "Sair"};
                String message = "Escolha seu jogo";
                int userChoice = -1;
                while (userChoice != 1) {
                    userChoice = Input.inputOption(userOption, message);

                    switch (userChoice) {
                        case 0 -> ArcadeGames.guessNumber(person);
                    }
                }
            } else {
                Output.GENERAL_ERROR_MESSAGE();
            }
        } else {
            Output.NO_PERSON_ERROR_MESSAGE();
        }
    }

    public static void searchPerson() {
        if(registeredPeople.size() > 0) {
            int personID = getPersonID();
            if (Checker.integerVerify(personID)) return;

            Person person = findPerson(personID);

            if(person != null) {
                    person.personInformations();
            } else {
                Output.GENERAL_ERROR_MESSAGE();
            }
        } else {
            Output.NO_PERSON_ERROR_MESSAGE();
        }
    }

    public static void deposit() {
        if(registeredPeople.size() > 0) {
            int personID = getPersonID();
            if (Checker.integerVerify(personID)) return;

            Person person = findPerson(personID);

            if(person != null) {
                person.getCard().deposit();
            } else {
                Output.GENERAL_ERROR_MESSAGE();
            }
        } else {
            Output.NO_PERSON_ERROR_MESSAGE();
        }
    }

    public static void transfer() {
        if(registeredPeople.size() > 0) {
            int personID;
            String message;

            message = "Informe quem irá transferir";
            JOptionPane.showMessageDialog(
                    null,
                    message,
                    Constant.APP_NAME_BLAZE,
                    JOptionPane.PLAIN_MESSAGE
            );
            personID = getPersonID();
            if (Checker.integerVerify(personID)) return;
            Person personDoingTransfer = findPerson(personID);

            message = "Informe quem irá receber";
            JOptionPane.showMessageDialog(
                    null,
                    message,
                    Constant.APP_NAME_BLAZE,
                    JOptionPane.PLAIN_MESSAGE
            );
            personID = getPersonID();
            if (Checker.integerVerify(personID)) return;
            Person personReceivingTransfer = findPerson(personID);

            if(personDoingTransfer != null && personReceivingTransfer != null) {
                Object[] userOption = {"Saldo", "Tickets", "Sair"};

                int userChoice = -1;

                message = "Escolha o que irá transferir";
                userChoice = Input.inputOption(userOption, message);

                switch (userChoice) {
                    case 0 -> personDoingTransfer.getCard().transferBalance(personDoingTransfer, personReceivingTransfer);
                    case 1 -> personDoingTransfer.getCard().transferTickets(personDoingTransfer, personReceivingTransfer);
                }

            } else {
                Output.GENERAL_ERROR_MESSAGE();
            }
        } else {
            Output.NO_PERSON_ERROR_MESSAGE();
        }
    }

    public static void receiveAward() {
        if(registeredPeople.size() > 0) {
            int personID = getPersonID();
            if (Checker.integerVerify(personID)) return;

            Person person = findPerson(personID);

            if(person!= null) {
                Object[] userOption = {"Fantasia do Barney, o dinossauro - 100 Tickets", "Bola de futebol - 5 Tickets", "Lápis da Blaze - 1 Ticket", "Sair"};
                int userChoice = -1;
                String message = "Escolha seu prêmio";

                userChoice = Input.inputOption(userOption, message);

                if(userChoice == 0) {
                    message = "Parabéns você conseguiu seu prêmio!";
                    if(person.getCard().getTicketBalance() >= 100) {
                        JOptionPane.showMessageDialog(
                                null,
                                message,
                                Constant.APP_NAME_BLAZE,
                                JOptionPane.PLAIN_MESSAGE
                        );
                        person.getCard().withdraw(100);
                    } else {
                        Output.INSUFFICIENT_TICKETS();
                    }
                } else if(userChoice == 1) {
                    message = "Parabéns você conseguiu seu prêmio!";
                    if(person.getCard().getTicketBalance() >= 5) {
                        JOptionPane.showMessageDialog(
                                null,
                                message,
                                Constant.APP_NAME_BLAZE,
                                JOptionPane.PLAIN_MESSAGE
                        );
                        person.getCard().withdraw(5);
                    } else {
                        Output.INSUFFICIENT_TICKETS();
                    }
                } else {
                    message = "Parabéns você conseguiu seu prêmio!";
                    if(person.getCard().getTicketBalance() >= 1) {
                        JOptionPane.showMessageDialog(
                                null,
                                message,
                                Constant.APP_NAME_BLAZE,
                                JOptionPane.PLAIN_MESSAGE
                        );
                        person.getCard().withdraw(1);
                    } else {
                        Output.INSUFFICIENT_TICKETS();
                    }
                }

            } else {
                Output.GENERAL_ERROR_MESSAGE();
            }
        } else {
            Output.NO_PERSON_ERROR_MESSAGE();
        }
    }

    // UTILITIES
    public static Person findPerson(int personID) {
        Person person = null;
        for (Person i: registeredPeople) {
            if (i.getPersonID() == personID) {
                person = i;
            }
        }
        return person;
    }

    public static int getPersonID() {
        String message = "Informe o ID";
        return Input.inputPositiveInteger(message);
    }
}