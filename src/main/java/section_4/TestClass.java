package section_4;

public class TestClass {
    public static void main(String[] args) {
        ComputeMethods computeMethods = new ComputeMethods();
        //Create an instance of ComputeMethods,
        //invoke the 3 methods and display their results
        System.out.println("Temp in celsius is " + computeMethods.fToC(100.4));
        System.out.println("Hypotenuse is " + computeMethods.hypotenuse(6, 9));
        System.out.println("The sum of the dice values is " + computeMethods.roll());
    }
}
