import java.text.DecimalFormat;
import java.util.function.Function;

public class Limits {
    private static final int PRECISION = 10000;
    private static final double EPSILON = 0.00001;

    public static Double leftLimit(Function<Double, Double> f, double x) {
        double round = x - EPSILON;
        double eval = 0d;
        double prev;
        int k = 0;

        do {
            prev = eval;
            eval = f.apply(round);
            round = x - (0.1 / Math.pow(10, k++));
        } while (Math.abs(eval - prev) >= EPSILON && !Double.isNaN(eval) && k != PRECISION);

        return Double.isFinite(eval) ? eval : null;
    }

    public static Double rightLimit(Function<Double, Double> f, double x) {
        double round = x + EPSILON;
        double eval = 0d;
        double prev;
        int k = 0;

        do {
            prev = eval;
            eval = f.apply(round);
            round = x + (0.1 / Math.pow(10, k++));
        } while (Math.abs(eval - prev) >= EPSILON && !Double.isNaN(eval) && k != PRECISION);

        return Double.isFinite(eval) ? eval : null;
    }

    public static Double limit(Function<Double, Double> f, double x, String msg) {
        Double left = leftLimit(f, x);
        Double right = rightLimit(f, x);
        if (left == null || right == null) {
            System.out.println(msg);
            return null;
        }
        return Double.isFinite(left) ? left : null;
    }

    public static Double limitOnInterval(Function<Double, Double> f, double left, double right) {
        DecimalFormat df = new DecimalFormat("#.##");
        for (double i = left + 0.01; i < right; i += 0.01) {
            if (limit(f, Double.parseDouble(df.format(i).replace(",", ".")),
                    "Функция терпит разрыв на интервале в точке " + df.format(i)) == null)
                return Double.parseDouble(df.format(i).replace(",", "."));

        }
        return null;
    }
}
