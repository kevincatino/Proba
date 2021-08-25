package guia2;

import tools.ProbCalc;

public class Ej11 {
    public static void main(String[] args) {
        for (int i=63; i<=70 ; i++) {
            System.out.printf("%s: %.3f%n", i, calculateE(i));
        }
    }
    public static double calculateE(int k) {
        int[] qty = {63, 64, 65, 66, 67, 68, 69, 70};
        double[] prob = {0.01, 0.04, 0.06, 0.08, 0.15, 0.28, 0.22, 0.16};
        double e=0;
        for (int i=0 ; i<qty.length ; i++) {
            if (qty[i]<k)
                e+=(qty[i]-0.4*k)*prob[i];
            else
                e+=0.6*k*prob[i];
        }
        return e;
    }
}
