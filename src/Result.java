public class Result {
    public double ans;
    public String logs;

    public Result(double ans, String logs) {
        this.ans = ans;
        this.logs = logs;
    }
    public Result() {
        this.ans = 0d;
        this.logs = null;
    }

    public double getAns() {
        return ans;
    }
}
