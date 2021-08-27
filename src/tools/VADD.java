package tools;


import org.apache.commons.math3.fraction.Fraction;

import java.util.function.Function;

public class VADD extends VAD{
    private VADDef vad;
    private Function<Integer,Double> f;
    public VADD(VADDef vad, Function<Integer,Double> f) {
        this.vad = vad;
        this.f = f;
    }

    @Override
    public double ev() {
        double value=0;
        int[] range = vad.getRange();
        double[] prob = vad.getProbs();
        for(int i=0 ; i<range.length ; i++) {
            value+=f.apply(range[i])*prob[i];
        }
        return value;
    }

    @Override
    public double var() {
        int[] range = vad.getRange();
        double[] prob = vad.getProbs();
        double value=0;
        for(int i=0 ; i<range.length ; i++) {
            value+=Math.pow(f.apply(range[i]),2)*prob[i];
        }
        return value-Math.pow(ev(),2);
    }

    @Override
    public double std() {
        return Math.sqrt(var());
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("E: %.4f (%s)\n", ev(), new Fraction(ev())));
        s.append(String.format("V: %.4f (%s)\n", var(), new Fraction(var())));
        s.append(String.format("DE: %.4f (%s)\n", std(), new Fraction(std())));
        return s.toString();
    }
}
