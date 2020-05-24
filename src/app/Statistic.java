package app;

import java.util.Arrays;

public class Statistic {
    public final double avg, stdev;

    private Statistic(double avg, double stdev) {
        this.avg = avg;
        this.stdev = stdev;
    }

    public static Statistic knownMean(double[] values) {
        return calc(values, true);
    }

    public static Statistic unknownMean(double[] values) {
        return calc(values, false);
    }

    private static Statistic calc(double[] values, boolean known) {
        double avg = Arrays.stream(values).sum() / values.length;
        double stdev = Math
                .sqrt(Arrays.stream(values).map(v -> Math.pow(v - avg, 2)).sum() / (values.length - (known ? 0 : 1)));
        return new Statistic(avg, stdev);
    }
}
