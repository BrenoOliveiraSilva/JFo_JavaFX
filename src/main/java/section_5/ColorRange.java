package section_5;

import java.util.Scanner;

public class ColorRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a color code");

        double colorCode = scanner.nextDouble();
        String color = null;

        if (colorCode >= 380 && colorCode < 750) {
            if (colorCode < 450) {
                color = "Violet";
            } else if (colorCode < 495) {
                color = "Blue";
            } else if (colorCode < 570) {
                color = "Green";
            } else if (colorCode < 590) {
                color = "Yellow";
            } else if (colorCode < 620) {
                color = "Orange";
            } else {
                color = "Red";
            }

            System.out.println("The color is " + color);
        } else {
            System.out.println("The entered wavelength is not a part of the visible spectrum");
        }
    }
}