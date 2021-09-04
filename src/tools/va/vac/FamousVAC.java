package tools.va.vac;

import org.apache.commons.math3.distribution.*;
import tools.va.util.VAFunctions;

import java.util.function.Function;

public class FamousVAC extends ConcreteVAC {
    private final AbstractRealDistribution d;
    public FamousVAC(AbstractRealDistribution d) {
        this.d=d;
    }
    @Override
    public double ev() {
        return d.getNumericalMean();
    }

    @Override
    public double var() {
        return d.getNumericalVariance();
    }

    @Override
    public double std() {
        return Math.sqrt(var());
    }


    @Override
    protected VAFunctions generateVADValues() {
        return new VAFunctions(){

            @Override
            public double probability(double x) {
                return d.probability((int)x);
            }

            @Override
            public double cumulativeProbability(double x) {
                return d.cumulativeProbability((int)x);
            }

        };
    }


    public static AbstractRealDistribution exponential(double lambda){
        return new ExponentialDistribution(1/lambda);
    }

    public static AbstractRealDistribution uniform(double a, double b){
        return new UniformRealDistribution(a,b);
    }

    public static AbstractRealDistribution weibull(double lambda, double k){
        return new WeibullDistribution(k,1/lambda);
    }

    public double median() {
        return d.inverseCumulativeProbability(0.5);
    }

    @Override
    public String toString() {
        String base = super.toString();
        return base + String.format("Median: %.4f", median());
    }


    @Override
    protected Function<Double, Double> getInverseCumulFunction() {
        return d::inverseCumulativeProbability;
    }
}
