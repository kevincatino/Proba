package guia2;

import org.apache.commons.math3.fraction.Fraction;
import tools.VAD;

public class EjA1 {
    public static void main(String[] args) {
        int[] range = {-10,10};
        double[] prob = {19.0/37,18.0/37};
        VAD vad = new VAD(range, prob);
        Fraction f = new Fraction(0.5);
        Fraction f2 = f.multiply(2);
        System.out.println(f2);
    }
}
