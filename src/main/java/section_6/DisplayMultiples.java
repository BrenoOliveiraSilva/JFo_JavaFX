package section_6;

import java.util.Scanner;

public class DisplayMultiples {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose a number: ");
        int number = scanner.nextInt();
         for (int i = 1; i <= 12; i++) {
             int result = i * number;
             System.out.println(number + "x" + i + " = " + result);
         }
    }
}
