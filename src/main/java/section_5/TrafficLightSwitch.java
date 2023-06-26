package section_5;

import java.util.Scanner;

public class TrafficLightSwitch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a color code");

        int currentColor = scanner.nextInt();

        switch (currentColor) {
            case 1 -> System.out.println("Next Traffic Light is green");
            case 2 -> System.out.println("Next Traffic Light is yellow");
            case 3 -> System.out.println("Next Traffic Light is red");
            default -> System.out.println("Invalid color");
        }
    }
}
