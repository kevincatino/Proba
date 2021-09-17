package tools.va.vac;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.RombergIntegrator;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import tools.va.ConcreteVA;
import tools.va.util.Set;
import tools.va.util.VAFunctions;

import java.util.function.Function;

public abstract class VAC extends ConcreteVA {
    protected final static int MAX_EVAL=Integer.MAX_VALUE;
    protected final static double INFINITE=Double.MAX_VALUE;
    protected final static double INFINITE2=100000.0;
    protected final static double LAMBDA=0.000001;

    protected double apply(Set s, VAFunctions d, double value) {
        return switch (s) {
            case EQ -> d.probability(value);
            case GEQ, GRE -> 1 - d.cumulativeProbability(value);
            case LEQ, LES -> d.cumulativeProbability(value);
        };


    }
    protected double integrate(UnivariateFunction f, double lower, double higher) {
        if (lower < -INFINITE2 && higher > INFINITE2)
            return new RombergIntegrator().integrate(MAX_EVAL, f, lower+LAMBDA, higher-LAMBDA);
        try {
            return new RombergIntegrator().integrate(MAX_EVAL, f, lower+LAMBDA, higher-LAMBDA);
        } catch (TooManyEvaluationsException e) {
            if (lower < -INFINITE)
                lower = -INFINITE2;
            else
                higher = INFINITE2;
            return new RombergIntegrator().integrate(MAX_EVAL, f, lower+LAMBDA, higher-LAMBDA);
        }
    }










}
