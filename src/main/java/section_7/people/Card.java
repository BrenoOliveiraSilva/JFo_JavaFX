package section_7.people;

import utilities.Input;
import utilities.Output;

public class Card {
    private static int cardCounter = 0;
    private final int ID;
    private int cardBalance;
    private int ticketBalance;

    public Card () {
        cardCounter++;
        this.ID = cardCounter;
    }

    public void deposit() {
        String message = "Valor a ser depositado";
        int depositValue = Input.inputPositiveInteger(message);
        this.cardBalance += (2 * depositValue);
    }
    public void withdraw(int withdraw) {
        this.ticketBalance -= withdraw;
    }
    public boolean pay(int AmountToBePaid) {
        if(AmountToBePaid <= cardBalance) {
            cardBalance -= AmountToBePaid;
            return true;
        } else {
            Output.INSUFFICIENT_BALANCE();
            return false;
        }
    }

    public int getCardBalance() {
        return cardBalance;
    }

    public int getTicketBalance() {
        return ticketBalance;
    }

    public int getID() {
        return ID;
    }

    public void setTicketBalance(int ticketBalance) {
        this.ticketBalance = ticketBalance;
    }

    public void transferBalance(Person personDoingTransfer, Person personReceivingTransfer) {
        String message = "Valor a ser transferido";
        int transferValue = Input.inputPositiveInteger(message);
        if(personDoingTransfer.getCard().cardBalance >= transferValue) {
            personDoingTransfer.getCard().cardBalance -= transferValue;
            personReceivingTransfer.getCard().cardBalance += transferValue;
        } else {
            Output.INSUFFICIENT_BALANCE();
        }
    }

    public void transferTickets(Person personDoingTransfer, Person personReceivingTransfer) {
        String message = "Valor a ser transferido";
        int transferValue = Input.inputPositiveInteger(message);
        if(personDoingTransfer.getCard().ticketBalance >= transferValue) {
            personDoingTransfer.getCard().ticketBalance -= transferValue;
            personReceivingTransfer.getCard().ticketBalance += transferValue;
        } else {
            Output.INSUFFICIENT_BALANCE();
        }
    }
}