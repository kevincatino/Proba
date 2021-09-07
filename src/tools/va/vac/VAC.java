package tools.va.vac;

import tools.va.ConcreteVA;
import tools.va.util.Set;
import tools.va.util.VAFunctions;

import java.util.function.Function;

public abstract class VAC extends ConcreteVA {
    protected double apply(Set s, VAFunctions d, double value) {
        return switch (s) {
            case EQ -> d.probability(value);
            case GEQ, GRE -> 1 - d.cumulativeProbability(value);
            case LEQ, LES -> d.cumulativeProbability(value);
        };
    }


    protected Double applyInverse(Set s, Function<Double,Double> inverseCumul, double value) {
        return switch (s) {
            case GEQ, GRE -> inverseCumul.apply(1 - value);
            case LEQ, LES -> inverseCumul.apply(value);
            case EQ -> throw new IllegalArgumentException("No se puede usar EQ en probabilidad inversa");
        };
    }



    public Double inverseProb(Set s, double value) {
        return applyInverse(s, getInverseCumulFunction(), value);
    }

    protected abstract Function<Double,Double> getInverseCumulFunction();

    public double inverseCumul(double proba) {
        return inverseProb(Set.LEQ,proba);
    }

    public double quantil(double proba) {
        return inverseCumul(proba);
    }





}
