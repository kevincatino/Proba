package tools.va.vad;

import org.apache.commons.math3.fraction.Fraction;
import tools.va.util.ProbCalcDouble;
import tools.va.util.ProbCalcInt;
import tools.va.util.Set;
import tools.va.util.VAFunctions;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

public class DefinedVAD extends ConcreteVAD {
    protected double ev;
    protected double var;
    protected double std;
    private final double[] range;
    private final double[] prob;

    public DefinedVAD(double[] range, double[] prob) {
        this.range=range;
        this.prob=prob;
        setVariables();
    }
    public DefinedVAD(int begin, int end, ProbCalcInt p) {
        this(Arrays.stream(IntStream.rangeClosed(begin,end).toArray()).asDoubleStream().toArray(), (v)->p.prob((int)v));
    }
    public DefinedVAD(int begin, int end, double[] prob) {
        this(Arrays.stream(IntStream.rangeClosed(begin,end).toArray()).asDoubleStream().toArray(),prob);
    }
    public DefinedVAD(double[] range, ProbCalcDouble p) {
        double[] probs = new double[range.length];
        for (int i=0 ; i<probs.length ; i++) {
            probs[i]=p.prob(range[i]);
        }
        prob=probs;
        this.range=range;
        setVariables();
    }
    public DefinedVAD(int[] range, ProbCalcInt p) {
        double[] probs = new double[range.length];
        double[] newRange = new double[range.length];
        for (int i=0 ; i<probs.length ; i++) {
            probs[i]=p.prob(range[i]);
            newRange[i]=range[i];
        }
        prob=probs;
        this.range=newRange;
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
    private static double calculateEv(double[] range, double[] prob) {
        double value = 0;
        for (int i=0 ; i<range.length ; i++) {
            value+=prob[i]*range[i];
        }
        return value;
    }

    private static double calculateVar(double[] range, double[] prob) {
        double[] rangeSq=new double[range.length];
        for (int i=0 ; i<range.length ; i++) {
            rangeSq[i]=range[i]*range[i];
        }
        return calculateEv(rangeSq, prob)-Math.pow(calculateEv(range,prob),2);
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


    public int lowerBound() {
        return 0;
    }


    public int upperBound() {
        return range.length-1;
    }

    @Override
    protected Function<Double, Double> getInverseCumulFunction() {
        return (v)-> {
            double accum=0;
            for (int i=0 ; i<range.length ; i++) {
                accum+=prob(Set.EQ,prob[i]);
                if (accum>=v)
                    return range[i];
            }
            throw new RuntimeException("Ninguno de los valores del rango acumula la probabilidad deseada");
        };
    }

    @Override
    public double rangeValue(int i) {
        if (i<lowerBound() || i>upperBound())
            throw new RuntimeException("Invalid value");
        return range[i];
    }

    @Override
    protected VAFunctions generateVADValues() {
        return new VAFunctions() {
            @Override
            public double probability(double x) {
                for (int i=0 ; i<range.length ; i++) {
                    if (Double.compare(range[i],x)==0)
                        return prob[i];
                }
                throw new RuntimeException("value does not exist in range");
            }

            @Override
            public double cumulativeProbability(double x) {
                double acum=0;
                for (int i=0 ; i<range.length ; i++) {
                    if (range[i]>x)
                        break;
                    acum+=prob[i];
                }
                return acum;
            }
        };
    }
}
