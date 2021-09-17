package tools.va.vac;

import org.apache.commons.math3.distribution.*;
import tools.va.util.Set;
import tools.va.util.VAFunctions;
import tools.va.vad.NotableVAD;

import java.util.function.Function;

public class NotableVAC extends ConcreteVAC{
    private final AbstractRealDistribution d;
    public NotableVAC(AbstractRealDistribution d) {
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
                return d.probability(x);
            }

            @Override
            public double cumulativeProbability(double x) {
                return d.cumulativeProbability(x);
            }

        };
    }


    public static NotableVAC exponential(double lambda){
        return new NotableVAC(new ExponentialDistribution(1/lambda));
    }

    public static NotableVAC uniform(double a, double b){
        return new NotableVAC(new UniformRealDistribution(a,b));
    }

    public static NotableVAC weibull(double lambda, double k){
        return new NotableVAC(new WeibullDistribution(k,1/lambda));
    }

    public static NotableVAC normal(double mu, double sd){
        return new NotableVAC(new NormalDistribution(mu,sd));
    }
    public static NotableVAC normal(){
        return new NotableVAC(new NormalDistribution());
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
    public Function<Double, Double> getInverseCumulFunction() {
        return d::inverseCumulativeProbability;
    }

    @Override
    public double density(double value) {
        return d.density(value);
    }

    @Override
    public double lowRangeValue() {
        return d.getSupportLowerBound();
    }

    @Override
    public double highRangeValue() {
        return d.getSupportUpperBound();
    }

    public static double normalQuantil(double p) {
        return NotableVAC.normal().quantil(p);
    }

    public static double normalCumul(double p) {
        return NotableVAC.normal().prob(Set.LEQ,p);
    }

    public static double Phi(double p) {
        return normalCumul(p);
    }
}
