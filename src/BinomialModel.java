import java.math.BigDecimal;
import java.util.ArrayList;

class BinomialModel {

public static double successChance;
public static int trials;
private static ArrayList<Double> probabilities;

/* Right now theres an issue with having this set too high, in that it expands the textArea past the window bounds. If you can fix that, make an input to set this (jcombobox) */
private final static int PRECISION = 10;

private static BigDecimal probability(int n, int s) {
    return nPk(n, s).multiply(BigDecimal.valueOf(successChance).pow(s)).multiply(BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(successChance)).pow(n - s)).setScale(PRECISION, 6);
}

private static BigDecimal nPk(int n, int k){
    if(n == k || k == 0){
        return BigDecimal.ONE;
    }
    if(k == 1 || k == n - 1){
        return BigDecimal.valueOf(n);
    }
    return factorial(n).divide(factorial(k).multiply(factorial(n-k)), PRECISION, 6);
}

private static BigDecimal factorial(int n){
    return n <= 1 ? BigDecimal.ONE : factorial(n - 1).multiply(BigDecimal.valueOf(n));
}

public static String getStandardDeviation() {
    double out = Math.pow(trials * successChance * (1 - successChance), .5);
    return fmt(out);
}

private static String fmt(double out) {
    return out == (long) out ? String.format("%d", (long) out) : String.format("%." + PRECISION + "f", out);
}

public static String getNumberResults() {
    double out = trials * successChance;
    return fmt(out);
}

public static String getProbabilityDistribution() {
    String out = "";
    for (int i = 0; i < probabilities.size(); i++) {
        out += i + ":\t" + fmt(probabilities.get(i)) + "\n";
    }
    return out;
}

public static void genOccurences() {
    probabilities = new ArrayList<Double>();
    for (int i = 0; i <= trials; i++) {
        probabilities.add(probability(trials, i).doubleValue());
    }
}

public static String getX(int selectedIndex, int sNum) {
    Double answer = 0.;
    switch (selectedIndex) {
        case 0: // <=
            for (int i = 0; i <= sNum; i++) {
                answer += probabilities.get(i);
            }
            break;
        case 1: // >=
            for (int i = sNum; i <= trials; i++) {
                answer += probabilities.get(i);
            }
            break;
        case 2: // <
            for (int i = 0; i < sNum; i++) {
                answer += probabilities.get(i);
            }
            break;
        case 3: // >
            for (int i = sNum + 1; i <= trials; i++) {
                answer += probabilities.get(i);
            }
            break;
    }
    return fmt(answer);
}
}

