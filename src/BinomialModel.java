import java.math.BigDecimal;
import java.util.ArrayList;

class BinomialModel {

public static double success_probability;
public static int trials;

private static BigDecimal individual_probability(int n, int s) {
    return nPk(n, s).multiply(BigDecimal.valueOf(success_probability).pow(s)).multiply(BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(success_probability)).pow(n - s)).setScale(10, 6);
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
    return BigDecimal.valueOf(Math.pow(trials * success_probability * (1 - success_probability), .5)).setScale(10, 6).toPlainString();
}

public static String getNumberResults() {
    return BigDecimal.valueOf(trials * success_probability).toPlainString();
}

public static String getProbabilityDistribution() {
    String out = "";
    for(int i = 0; i <= trials; i++) {
        out += i + ":\t" + individual_probability(trials, i) + "\n";
    }
    return out;
}

public static String getSNumber(int selectedIndex, int sNum) {
    BigDecimal answer = BigDecimal.ZERO;
    ArrayList<BigDecimal> occurrences = new ArrayList<BigDecimal>();
    for (int i = 0; i <= trials; i++) {
        occurrences.add(individual_probability(trials, i));
    }
    switch (selectedIndex) {
        case 0: // <=
            for (int i = 0; i <= sNum; i++) {
                answer = answer.add(occurrences.get(i));
            }
            break;
        case 1: // >=
            for (int i = sNum; i <= trials; i++) {
                answer = answer.add(occurrences.get(i));
            }
            break;
        case 2: // <
            for (int i = 0; i < sNum; i++) {
                answer = answer.add(occurrences.get(i));
            }
            break;
        case 3: // >
            for (int i = sNum + 1; i <= trials; i++) {
                answer = answer.add(occurrences.get(i));
            }
            break;
    }
    return answer.toPlainString();
}
}

