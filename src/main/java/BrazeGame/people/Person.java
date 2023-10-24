package BrazeGame.people;

import utilities.Constant;

import javax.swing.*;

public class Person {
    private static int peopleCounter = 0;
    private final int ID;
    private String name;
    private Card card;

    public Person(String name, Card card) {
        this.name = name;
        this.card = card;

        peopleCounter++;
        this.ID = peopleCounter;
    }

    public Card getCard() {
        return card;
    }

    public int getPersonID() {
        return ID;
    }

    public void personInformations() {
        String message = "Nome - " + getName() +
                "\nNúmero do cartão - " + card.getID() +
                "\nSaldo - " + card.getCardBalance() +
                "\nTickets - " + card.getTicketBalance();
        JOptionPane.showMessageDialog(
                null,
                message,
                Constant.APP_NAME_BLAZE,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public String getName() {
        return name;
    }
}