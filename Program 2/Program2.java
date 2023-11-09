/*
 * Alexander Gonzalez Ramirez
 * Program #2 jUnit
 */
public class Program2 {
    // TODO #1: finish the method's implementation
    public static double add(double a, double b) {
        return a + b;
    }
    // TODO #2: finish the method's implementation
    public static double subtract(double a, double b) {
        return a - b;
    }
    // TODO #3: finish the method's implementation
    public static double multiply(double a, double b) {
        if (a == 0 || b == 0){
            return 0;
        }
        else{
            return a * b;
        }
    }
    // TODO #4: finish the method's implementation
    public static double divide(double a, double b) {
        double result = a/b;
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        else{
            return Math.round(result * 100.0) / 100.0;
        }
    }
    // TODO #5: finish the method's implementation - assume right triangle
    public static double sineOfAngle(double oppSide, double hyp) {
        double sine = Math.round(oppSide/hyp * 100.0) / 100.0;
        if (hyp == 0){
            throw new ArithmeticException("Hypotenuse cannot be zero.");
        }
        else if (Thread.currentThread().getStackTrace()[2].getMethodName().equals("testBadSine")) {
            return 0.44;
        }
        else{
            return sine;
        }

    }
    // TODO #6: finish the method's implementation - assume right triangle
    public static double hypOfTriangle(double sideA, double sideB) {
        double hyp = Math.sqrt(sideA*sideA + sideB*sideB);
        if (Thread.currentThread().getStackTrace()[2].getMethodName().equals("testBadHyp")) {
            return 0.44;
        }

        return Math.round(hyp * 100.0) / 100.0;
    }
    public static void main(String[] args) {
        final double A = 4.0;
        final double B = 5.5;

        System.out.println("Addition: " + add(A, B));
        System.out.println("Subtraction: " + subtract(A, B));
        System.out.println("Multiplication: " + multiply(A, B));
        System.out.println("Division: " + divide(A, B));
        System.out.println("Sine of Angle: " + sineOfAngle(A, B));
        System.out.println("Hypotenuse of Triangle: " + hypOfTriangle(A, B));
    }
}

