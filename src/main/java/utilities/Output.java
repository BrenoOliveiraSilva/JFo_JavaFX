package utilities;

import javax.swing.*;

public class Output {
    public static void GENERAL_ERROR_MESSAGE() {
        JOptionPane.showMessageDialog(
                null,
                "Ocorreu um erro!",
                Constant.APP_NAME_BLAZE,
                JOptionPane.ERROR_MESSAGE);
    }
    public static void NEGATIVE_VALUE_ERROR_MESSAGE() {
        JOptionPane.showMessageDialog(
                null,
                "Valor negativo não permitido!",
                Constant.APP_NAME_BLAZE,
                JOptionPane.ERROR_MESSAGE);
    }
    public static void NULL_ERROR_MESSAGE() {
        JOptionPane.showMessageDialog(
                null,
                "Proibido valores nulos!",
                Constant.APP_NAME_BLAZE,
                JOptionPane.ERROR_MESSAGE);
    }
    public static void NULL_VALUE_ERROR_MESSAGE() {
        JOptionPane.showMessageDialog(
                null,
                "Valor nulo não permitido!",
                Constant.APP_NAME_BLAZE,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void NO_PERSON_ERROR_MESSAGE() {
        JOptionPane.showMessageDialog(
                null,
                "Sem pessoas registradas!",
                Constant.APP_NAME_BLAZE,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void INSUFFICIENT_BALANCE() {
        JOptionPane.showMessageDialog(
                null,
                "Saldo insuficiente!",
                Constant.APP_NAME_BLAZE,
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void INSUFFICIENT_TICKETS() {
        JOptionPane.showMessageDialog(
                null,
                "Tickets insuficientes!",
                Constant.APP_NAME_BLAZE,
                JOptionPane.ERROR_MESSAGE
        );
    }
}