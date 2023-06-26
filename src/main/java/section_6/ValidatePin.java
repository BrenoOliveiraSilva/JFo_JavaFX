package section_6;

import java.util.Scanner;

public class ValidatePin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int validPin = 999;
        int inputPin = 0;
        while (inputPin != validPin) {
            System.out.println("Insira o PIN");
            inputPin = scanner.nextInt();
        }
        System.out.println("PIN correto inserido, agora você tem acesso à conta");
    }
}
