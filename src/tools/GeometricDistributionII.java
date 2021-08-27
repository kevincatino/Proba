package tools;

import org.apache.commons.math3.distribution.GeometricDistribution;
import org.apache.commons.math3.util.FastMath;

public class GeometricDistributionII extends GeometricDistribution {
    public GeometricDistributionII(double p) {
        super(p);
    }

    public double cumulativeProbability(int x) {
        return super.cumulativeProbability(x-1);
    }
}
