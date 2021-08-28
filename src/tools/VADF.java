package tools;

import org.apache.commons.math3.distribution.*;

public class VADF extends VADC{
    private AbstractIntegerDistribution d;
    public VADF(AbstractIntegerDistribution d) {
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
    public double prob(Select s, int value) {
        return s.apply(d, value);
    }

    public static AbstractIntegerDistribution binomial(int n, double p){
        return new BinomialDistribution(n,p);
    }
    public static AbstractIntegerDistribution poisson(double p){
        return new PoissonDistribution(p);
    }
    public static AbstractIntegerDistribution hipergeo(int N, int m, int n){
        return new HypergeometricDistribution(N,m,n);
    }
    public static AbstractIntegerDistribution geo(double p){
        return new GeometricDistribution(p);
    }
    public static AbstractIntegerDistribution geoII(double p){
        return new GeometricDistributionII(p);
    }
}
