package tools.va.util;

import org.apache.commons.math3.distribution.GeometricDistribution;


public class GeometricDistributionII extends GeometricDistribution {
    public GeometricDistributionII(double p) {
        super(p);
    }


    public double cumulativeProbability(int x) {
        return super.cumulativeProbability(x-1);
    }
    public double probability(int x) {
        return super.probability(x-1);
    }
    public double getNumericalMean() {
        return super.getNumericalMean()+1;
    }
}
