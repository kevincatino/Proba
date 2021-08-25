package tools;

import org.apache.commons.math3.fraction.Fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class VAD {
    private final int[] range;
    private final double[] prob;
    private double ev;
    private double var;
    private double std;
    private String exactFrac="";


    public VAD(int[] range, double[] prob) {
        this.range=range;
        this.prob=prob;
        setVariables();
    }
    public VAD(int begin, int end, ProbCalc p) {
        this(IntStream.rangeClosed(begin,end).toArray(), p);
    }
    public VAD(int begin, int end, double[] prob) {
        this(IntStream.rangeClosed(begin,end).toArray(),prob);
    }
    public VAD(int[] range, ProbCalc p) {
        double[] probs = new double[range.length];
        for (int i=0 ; i<probs.length ; i++) {
            probs[i]=p.prob(range[i]);
        }
        prob=probs;
        this.range=range;
        setVariables();
    }
    private static double calculateEv(int[] range, double[] prob) {
        double value = 0;
        for (int i=0 ; i<range.length ; i++) {
            value+=prob[i]*range[i];
        }
        return value;
    }
    public double calculateVar(int[] range, double[] prob) {
        int[] rangeSq=new int[range.length];
        for (int i=0 ; i<range.length ; i++) {
            rangeSq[i]=range[i]*range[i];
        }
        return calculateEv(rangeSq, prob)-ev*ev;
    }

    private void setVariables() {
        ev = calculateEv(range, prob);
        var = calculateVar(range, prob);
        std = Math.sqrt(var);
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i=0 ; i<prob.length ; i++) {
            s.append(String.format("%s: %.4f\n", range[i], prob[i]));
        }
        s.append("--------------------------\n");
        s.append(String.format("E: %.4f\n", ev));
        s.append(String.format("V: %.4f\n", var));
        s.append(String.format("DE: %.4f\n", std));
        return s.toString();
    }
    public double ev() {
        return ev;
    }
    public double var() {
        return var;
    }
    public double std() {
        return std;
    }
}
