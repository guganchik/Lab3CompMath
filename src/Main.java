import java.util.Scanner;
import java.util.function.Function;

public class Main {

    private static Function<Double, Double>[] eqs;

    private static String eqsStr = "Equations:\n" +
            "1. f(x) = sin(x)\n" +
            "2. f(x) = x^3 - 3x\n" +
            "3. f(x) = e^(x/2)\n" +
            "4. f(x) = x^2 + 2x + 1\n";

    public static void main(String[] args) {
        initEqs();

        Scanner scanner = new Scanner(System.in);
        double a, b, accuracy;
        int numEq, numMth, numRect = -1;
        System.out.println(eqsStr);
        System.out.println("Choose the equation. Write from 1 to 4");
        numEq = scanner.nextInt();
        System.out.println("Choose method:\n" +
                "1. Simpson\n" +
                "2. Trapezoidal\n" +
                "3. Rectangle");
        numMth = scanner.nextInt();
        if (numMth == 3) {
            System.out.println("Choose method:\n" +
                    "0 -> Right\n" +
                    "1 -> Central\n" +
                    "2 -> Left");
            numRect = scanner.nextInt();
        }
        System.out.println("Enter the interval");
        a = scanner.nextDouble();
        b = scanner.nextDouble();
        System.out.println("Enter the accuracy");
        accuracy = scanner.nextDouble();

        double result = Solver.start(numMth, eqs[numEq - 1], a, b, accuracy, numRect);
        System.out.println("Answer: " + result);
    }

    private static void initEqs() {
        eqs = new Function[4];
        //eqs[0] = x -> Math.pow(Math.sin(x), 3) * Math.pow(Math.cos(Math.pow(x, 2)), 4);
        eqs[0] = x -> Math.sin(x);
        eqs[1] = x -> Math.pow(x, 3) - 3 * x;
        eqs[2] = x -> Math.exp(x /A 2);
        eqs[3] = x -> Math.pow(x, 2) + 2 * x + 1;
    }

}