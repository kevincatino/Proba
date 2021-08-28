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
            return 1 - d.cumulativeProbability(value-1);
        }
    },
    LEQ {
        @Override
        public double apply(AbstractIntegerDistribution d, int value) {
            return d.cumulativeProbability(value);
        }
    },
    GRE {
        @Override
        public double apply(AbstractIntegerDistribution d, int value) {
            return 1 - d.cumulativeProbability(value);
        }
    },
    LES {
        @Override
        public double apply(AbstractIntegerDistribution d, int value) {
            return d.cumulativeProbability(value-1);
        }
    };
    abstract public double apply(AbstractIntegerDistribution d, int value);
}
