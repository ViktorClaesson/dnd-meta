package app;

import java.util.Arrays;

public class Statistic {
    public final double avg, stdev;

    public Statistic(double[] values) {
        avg = Arrays.stream(values).sum() / values.length;
        stdev = Math.sqrt(Arrays.stream(values).map(v -> Math.pow(v - avg, 2)).sum() / (values.length - 1));
    }

    @Override
    public String toString() {
        return String.format("µ: %6.2f, σ: %6.2f", avg, stdev);
    }
}
