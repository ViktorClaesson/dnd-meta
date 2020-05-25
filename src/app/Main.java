package app;

import java.util.Random;
import java.util.function.*;
import java.util.stream.IntStream;

public class Main {
    private final static Random r = new Random();

    public static void task1() {
        Function<Integer, Integer> one = AC -> attack(1, AC, () -> rollN(2, 6) + 3);
        Function<Integer, Integer> two = AC -> attack(1, AC + 5, () -> rollN(2, 6) + 13);

        for (int i = 1; i <= 20; i++) {
            Statistic sOne = simulate(10000, i, one);
            Statistic sTwo = simulate(10000, i, two);
            System.out.printf("AC %3d; %s || %s\n", i, sOne, sTwo);
        }
    }

    public static void task2() {
        double[] value0 = new double[13];
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 4; k++) {
                    value0[i + j + k] += 1.0 / 64;
                }
            }
        }

        double[] value1 = new double[13];
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                value1[i + j] += 1.0 / 36;
            }
        }

        double[] value2 = new double[13];
        for (int i = 1; i <= 12; i++) {
            value2[i] += 1.0 / 12;
        }

        double sum0 = 0, sum1 = 0, sum2 = 0;
        for (int i = 1; i < 13; i++) {
            sum0 += i * value0[i];
            sum1 += i * value1[i];
            sum2 += i * value2[i];
            System.out.printf("%3d: %.4f, %.4f, %.4f\n", i, value0[i], value1[i], value2[i]);
        }
        System.out.printf("%3s: %.4f, %.4f, %.4f\n", "avg", sum0, sum1, sum2);
    }

    public static void task3() {
        double[] value0 = new double[13];
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 4; k++) {
                    for (int l = 1; l <= 4; l++) {
                        for (int m = 1; m <= 4; m++) {
                            for (int n = 1; n <= 4; n++) {
                                value0[Math.max(i + j + k, l + m + n)] += 1.0 / 64 / 64;
                            }
                        }
                    }
                }
            }
        }

        double[] value1 = new double[13];
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    for (int l = 1; l <= 6; l++) {
                        value1[Math.max(i + j, k + l)] += 1.0 / 36 / 36;
                    }
                }
            }
        }

        double[] value2 = new double[13];
        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= 12; j++) {
                value2[Math.max(i, j)] += 1.0 / 12 / 12;
            }
        }

        double sum0 = 0, sum1 = 0, sum2 = 0;
        for (int i = 1; i < 13; i++) {
            sum0 += i * value0[i];
            sum1 += i * value1[i];
            sum2 += i * value2[i];
            System.out.printf("%3d: %.4f, %.4f, %.4f\n", i, value0[i], value1[i], value2[i]);
        }
        System.out.printf("%3s: %.4f, %.4f, %.4f\n", "avg", sum0, sum1, sum2);
    }

    public static void main(String[] args) throws Exception {
        task1();
        System.out.println();
        // task2();
        System.out.println();
        // task3();
    }

    public static Statistic simulate(int iterations, int AC, Function<Integer, Integer> attack) {
        double[] result = new double[iterations];
        for (int i = 0; i < iterations; i++) {
            result[i] = attack.apply(AC);
        }
        return new Statistic(result);
    }

    public static int attack(int n, int AC, Supplier<Integer> damage) {
        return IntStream.range(0, n).map(i -> attack(AC, damage)).sum();
    }

    public static int attack(int AC, Supplier<Integer> damage) {
        return hit(AC) ? damage.get() : 0;
    }

    public static boolean hit(int AC) {
        return roll(20) >= AC;
    }

    public static int roll(int sides) {
        return r.nextInt(sides) + 1;
    }

    public static int roll(int sides, BiFunction<Integer, Integer, Integer> picker) {
        return picker.apply(roll(sides), roll(sides));
    }

    public static int rollN(int n, int sides) {
        return IntStream.range(0, n).map(i -> roll(sides)).sum();
    }

    public static int rollN(int n, int sides, BiFunction<Integer, Integer, Integer> picker) {
        return picker.apply(rollN(n, sides), rollN(n, sides));
    }
}