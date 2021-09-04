package tools.va.vac;

import tools.va.ConcreteVA;
import tools.va.util.Set;
import tools.va.util.VAFunctions;

import java.util.function.Function;

public abstract class ConcreteVAC extends ConcreteVA {
    protected double apply(Set s, VAFunctions d, double value) {
        return switch (s) {
            case EQ -> d.probability(value);
            case GEQ, GRE -> 1 - d.cumulativeProbability(value);
            case LEQ, LES -> d.cumulativeProbability(value);
        };
    }


    protected double applyInverse(Set s, Function<Double,Double> inverseCumul, double value) {
        return switch (s) {
            case GEQ, GRE -> inverseCumul.apply(1 - value);
            case LEQ, LES -> inverseCumul.apply(value);
            case EQ -> throw new IllegalArgumentException("No se puede usar EQ en probabilidad inversa");
        };
    }





}
