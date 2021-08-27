package tools;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;

public enum Select {
    EQ {
        @Override
        public double apply(AbstractIntegerDistribution d, int value) {
            return d.probability(value);
        }
    },
    GEQ {
        @Override
        public double apply(AbstractIntegerDistribution d, int value) {
            return 1 - d.cumulativeProbability(d.getSupportLowerBound(), value-1);
        }
    },
    LEQ {
        @Override
        public double apply(AbstractIntegerDistribution d, int value) {
            return d.cumulativeProbability(d.getSupportLowerBound(), value-1);
        }
    },
    GR {
        @Override
        public double apply(AbstractIntegerDistribution d, int value) {
            return 1 - d.cumulativeProbability(d.getSupportLowerBound(), value);
        }
    },
    LE {
        @Override
        public double apply(AbstractIntegerDistribution d, int value) {
            return d.cumulativeProbability(d.getSupportLowerBound(), value);
        }
    };
    abstract public double apply(AbstractIntegerDistribution d, int value);
}
