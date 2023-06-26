package section_6;

public class LoopShape {
    static void createRectangle(int width, int height){
        //Draw a Rectangle
        if (width < 1 || height < 1) {
            System.out.println("Dimensões inválidas");
            return;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == 3 || j == 0 || j == 4) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    static void createTriangle(int leg){
        //Draw an Isosceles Right Triangle
        if (leg <  1) {
            System.out.println("Dimensões inválidas");
            return;
        }

        for (int i = 0; i < leg; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == 0 || i == 4 || j == 0 || i == j) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
