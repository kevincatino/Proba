package tools;

import org.apache.commons.math3.distribution.*;

public interface VADTemp {
    static AbstractIntegerDistribution binomial(int n, double p){
        return new BinomialDistribution(n,p);
    }
    static AbstractIntegerDistribution possion(double p){
        return new PoissonDistribution(p);
    }
    static AbstractIntegerDistribution hipergeo(int N, int m, int n){
        return new HypergeometricDistribution(N,m,n);
    }
    static AbstractIntegerDistribution geo(double p){
        return new GeometricDistribution(p);
    }
    static AbstractIntegerDistribution geoII(double p){
        return new GeometricDistributionII(p);
    }

}
