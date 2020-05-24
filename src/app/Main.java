package app;

public class Main {
    public static void main(String[] args) throws Exception {
        Statistic s = new Statistic(new double[] { 1, 2, 3, 4, 5, 6 });
        Systme.out.printf("%4.2f", s.avg, s.stdev);
    }
}