package app;

public class Main {
    public static void main(String[] args) throws Exception {
        Statistic s = Statistic.knownMean(new double[] { 1, 2, 3, 4, 5, 6 });
        System.out.printf("µ: %4.2f, σ: %4.2f\n", s.avg, s.stdev);
    }
}