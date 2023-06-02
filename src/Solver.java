import java.util.function.Function;

public class Solver {

    public static double start(int num, Function<Double, Double> f, double a, double b, double accuracy, int numRect) {
        double n = 4;
        Result res, prevRes = null;
        while (true) {
            res = callMethod(num, f, a, b, n, numRect);
            if (prevRes != null && Math.abs(res.ans - prevRes.ans) <= accuracy) {
                System.out.println(prevRes.getAns() + "\n" + res.getAns());
                break;
            }
            prevRes = res;
            n *= 2;
            if (n >= Math.pow(2, 1000)) {
                System.out.println("Very many iterations. Break...");
            }
        }
        System.out.println(res.logs);
        return res.ans;
    }

    private static Result callMethod(int num, Function<Double, Double> f, double a, double b, double n, int numRect) {
        return num == 1
                ? simpsonMethod(f, a, b, n)
                : (num == 2
                ? trapezoidalMethod(f, a, b, n)
                : rectangleMethod(f, a, b, n, numRect)
        );
    }

    public static Result simpsonMethod(Function<Double, Double> f, double a, double b, double n) {
        StringBuilder logs = new StringBuilder();
        double odd = 0, even = 0;
        double step = (b - a) / n;
        double x = a + step;
        for (int i = 1; i < n; i++) {
            double y = f.apply(x);
            if (i % 2 == 0) {
                even += y;
            } else {
                odd += y;
            }
            logs.append("X_").append(i).append(" = ").append(x);
            logs.append("\tY_").append(i).append(" = ").append(y).append("\n");
            x += step;
        }
        logs.append("n = ").append((int)n).append("\n");
        return new Result((f.apply(a) + f.apply(b) + 4 * odd + 2 * even) * step / 3, logs.toString());
    }

    public static Result trapezoidalMethod(Function<Double, Double> f, double a, double b, double n) {
        StringBuilder logs = new StringBuilder();

        double step = (b - a) / n;
        double x = a;
        double ans = 0;
        double y = 0;
        for (int i = 0; i < n-1; i++) {
            x += step;
            y = f.apply(x);
            ans += y;
            logs.append("X_").append(i).append(" = ").append(x);
            logs.append("\tY_").append(i).append(" = ").append(y).append("\n");
        }
        ans += (f.apply(a) + f.apply(b)) / 2;
        ans *= step;
        logs.append("n = ").append((int)n).append("\n");
        return new Result(ans, logs.toString());
    }
    public static Result rectangleMethod(Function<Double, Double> f, double a, double b, double n, int method) {
        StringBuilder logs = new StringBuilder();
        double x = a;
        double step = (b - a) / n;
        double ans = 0;
        for (int i = 0; i < n; i++) {
            double y = f.apply(method == 0 ? (x + step) : (method == 1 ? x + (step / 2) : x));
            ans += y;
            logs.append("X_").append(i).append(" = ").append(x);
            logs.append("\tY_").append(i).append(" = ").append(y).append("\n");
            x += step;
        }
        ans *= step;
        logs.append("n = ").append((int)n).append("\n");
        return new Result(ans, logs.toString());
    }

//    public Result getAnswerForBreaks(boolean type) {
//        double prev;
//        Result current = new Result();
//        int k = 0;
//        do {
//            prev = current.getAns();
//            if (type) {
//                current = getAnswer(a, b - (0.1 / Math.pow(10, k++)));
//            }
//            else {
//                current = getAnswer(left + (0.1 / Math.pow(10, k++)), right);
//            }
//
//        } while (current != null && (Math.abs(current.getRes() - prev) >= epsilon
//                && !Double.isNaN(current.getRes()) && k != PRECISION));
//
//        return current != null && Double.isFinite(current.getRes()) ? current : null;
//    }

}