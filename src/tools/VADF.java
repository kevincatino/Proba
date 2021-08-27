package tools;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;

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
    public double prob(int value, Select s) {
        return s.apply(d, value);
    }
}
