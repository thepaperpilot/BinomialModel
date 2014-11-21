import java.math.BigDecimal;
import java.util.ArrayList;

class BinomialModel {

public static double successChance;
public static int trials;
private static ArrayList<BigDecimal> probabilities;

private static BigDecimal probability(int n, int s) {
    return nPk(n, s).multiply(BigDecimal.valueOf(successChance).pow(s)).multiply(BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(successChance)).pow(n - s)).setScale(10, 6);
}

private static BigDecimal nPk(int n, int k){
    if(n==k||k==0){
        return BigDecimal.ONE;
    }
    if(k==1||k==n-1){
        return BigDecimal.valueOf(n);
    }
    return factorial(n).divide(factorial(k).multiply(factorial(n-k)),10,6);
}

private static BigDecimal factorial(int n){
    BigDecimal factorial = BigDecimal.valueOf(n);
    while(n>2) {
        n--;
        factorial = factorial.multiply(BigDecimal.valueOf(n));
    }
    return factorial;
}

public static String getStandardDeviation() {
    return BigDecimal.valueOf(Math.pow(trials * successChance * (1 - successChance), .5)).setScale(10, 6).toPlainString();
}

public static String getNumberResults() {
    return BigDecimal.valueOf(trials * successChance).toPlainString();
}

public static String getProbabilityDistribution() {
    String out = "";
    for (int i = 0; i < probabilities.size(); i++) {
        out += i + ":\t" + probabilities.get(i) + "\n";
    }
    return out;
}

public static void genOccurences() {
    probabilities = new ArrayList<BigDecimal>();
    for (int i = 0; i <= trials; i++) {
        probabilities.add(probability(trials, i));
    }
}

public static String getX(int selectedIndex, int sNum) {
    BigDecimal answer = BigDecimal.ZERO;
    switch (selectedIndex) {
        case 0: // <=
            for (int i = 0; i <= sNum; i++) {
                answer = answer.add(probabilities.get(i));
            }
            break;
        case 1: // >=
            for (int i = sNum; i <= trials; i++) {
                answer = answer.add(probabilities.get(i));
            }
            break;
        case 2: // <
            for (int i = 0; i < sNum; i++) {
                answer = answer.add(probabilities.get(i));
            }
            break;
        case 3: // >
            for (int i = sNum + 1; i <= trials; i++) {
                answer = answer.add(probabilities.get(i));
            }
            break;
    }
    return answer.toPlainString();
}
}

