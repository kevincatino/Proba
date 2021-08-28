package tools;

import org.apache.commons.math3.fraction.Fraction;

import java.util.stream.IntStream;

public class VADDef extends VADC{
    private final int[] range;
    private final double[] prob;
    private double ev;
    private double var;
    private double std;



    public VADDef(int[] range, double[] prob) {
        this.range=range;
        this.prob=prob;
        setVariables();
    }
    public VADDef(int begin, int end, ProbCalc p) {
        this(IntStream.rangeClosed(begin,end).toArray(), p);
    }
    public VADDef(int begin, int end, double[] prob) {
        this(IntStream.rangeClosed(begin,end).toArray(),prob);
    }
    public VADDef(int[] range, ProbCalc p) {
        double[] probs = new double[range.length];
        for (int i=0 ; i<probs.length ; i++) {
            probs[i]=p.prob(range[i]);
        }
        prob=probs;
        this.range=range;
        setVariables();
    }


    private void setVariables() {
        ev = calculateEv(range, prob);
        var = calculateVar(range, prob);
        std = Math.sqrt(var);
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        double accum=0;
        for (int i=0 ; i<prob.length ; i++) {
            s.append(String.format("%s: %.4f  (%s)\n", range[i], prob[i], new Fraction(prob[i])));
            accum+=prob[i];
        }
        s.append(String.format("TOTAL = %.4f\n", accum));
        s.append("--------------------------\n");
        s.append(super.toString());
        return s.toString();
    }
    private static double calculateEv(int[] range, double[] prob) {
        double value = 0;
        for (int i=0 ; i<range.length ; i++) {
            value+=prob[i]*range[i];
        }
        return value;
    }

    private static double calculateVar(int[] range, double[] prob) {
        int[] rangeSq=new int[range.length];
        for (int i=0 ; i<range.length ; i++) {
            rangeSq[i]=range[i]*range[i];
        }
        return calculateEv(rangeSq, prob)-Math.pow(calculateEv(range,prob),2);
    }

    private static double calculateSd(int[] range, double[] prob) {
        return Math.sqrt(calculateVar(range, prob));
    }

    public int[] getRange() {
        return range.clone();
    }

    public double[] getProbs() {
        return prob.clone();
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


    @Override
    public double prob(Select s, int value) {
        for (int i=0 ; i<range.length ; i++){
            if (range[i]==value)
                return prob[i];
        }
        throw new IllegalArgumentException();
    }


}
