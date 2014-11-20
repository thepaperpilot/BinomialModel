import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class BinomialModel {

    public static double success_probability;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("What is the probability of success?");
        success_probability = s.nextDouble();
        System.out.println("How many trials are done?");
        int trial_n = s.nextInt();
        ArrayList<BigDecimal> occurrence_probability = new ArrayList<BigDecimal>();
        ArrayList<String> occurrence_n = new ArrayList<String>();
        for (int i = 0; i <= trial_n; i++) {
            occurrence_probability.add(individual_probability(trial_n, i));
            if (i>9){
                occurrence_n.add("     " + i + "     ");
            }else occurrence_n.add("     " + i + "      ");
        }
        BigDecimal expected_number_of_results = BigDecimal.valueOf(trial_n*success_probability);
        BigDecimal standard_deviation = BigDecimal.valueOf(Math.pow(trial_n*success_probability*(1-success_probability),.5)).setScale(10,6);
        while (true) {
            System.out.println("What more information do you need?");
            String info = s.next();
            if(info.equalsIgnoreCase("end")){
                break;
            }
            BigDecimal answer = BigDecimal.ZERO;
            int number = 0;
            if (info.length()<=4) {
                number = Integer.valueOf(Character.toString(info.charAt(info.length()-1)));
            } else if(info.length()==5){
                number = Integer.valueOf(info.substring(info.length()-2,info.length()));
            }
            if (info.contains("standard")|| info.contains("deviation")){
                System.out.println(standard_deviation);
            }else if (info.contains("expected")|| info.contains("number")||info.contains("of")|| info.contains("results")){
                System.out.println(expected_number_of_results);
            }else if (info.contains("probability")|| info.contains("distribution")){
                System.out.println(occurrence_n);
                System.out.println(occurrence_probability);
            }else if (info.contains("s>=")) {
                for (int i = number; i <= trial_n; i++) {
                    answer = answer.add(occurrence_probability.get(i));
                }
                System.out.println(answer);
            } else if (info.contains("s>")) {
                if (info.length()<=3) {
                    number = Integer.valueOf(Character.toString(info.charAt(info.length()-1)));
                } else if(info.length()==4){
                    number = Integer.valueOf(info.substring(info.length()-2,info.length()));
                }
                for (int i = number+1; i <= trial_n; i++) {
                    answer = answer.add(occurrence_probability.get(i));
                }
                System.out.println(answer);
            } else if (info.contains("s=")) {
                if (info.length()<=3) {
                    number = Integer.valueOf(Character.toString(info.charAt(info.length()-1)));
                } else if(info.length()==4){
                    number = Integer.valueOf(info.substring(info.length()-2,info.length()));
                }
                System.out.println(occurrence_probability.get(number));
            }else if (info.contains("s<=")) {
                for (int i = 0; i <= number; i++) {
                    answer = answer.add(occurrence_probability.get(i));
                }
                System.out.println(answer);
            }else if (info.contains("s<")) {
                if (info.length()<=3) {
                    number = Integer.valueOf(Character.toString(info.charAt(info.length()-1)));
                } else if(info.length()==4){
                    number = Integer.valueOf(info.substring(info.length()-2,info.length()));
                }
                for (int i = 0; i < number; i++) {
                    answer = answer.add(occurrence_probability.get(i));
                }
                System.out.println(answer);
            }
        }
    }

    public static BigDecimal individual_probability(int n, int s) {
        return nPk(n, s).multiply(BigDecimal.valueOf(success_probability).pow(s)).multiply(BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(success_probability)).pow(n - s)).setScale(10, 6);
    }

    public static BigDecimal nPk(int n, int k){
        if(n==k||k==0){
            return BigDecimal.ONE;
        }
        if(k==1||k==n-1){
            return BigDecimal.valueOf(n);
        }
        return factorial(n).divide(factorial(k).multiply(factorial(n-k)),10,6);
    }

    public static BigDecimal factorial(int n){
        BigDecimal factorial = BigDecimal.valueOf(n);
        while(n>2) {
            n--;
            factorial = factorial.multiply(BigDecimal.valueOf(n));
        }
        return factorial;
    }
}

