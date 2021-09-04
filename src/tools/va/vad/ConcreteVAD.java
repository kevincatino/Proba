package tools.va.vad;

import tools.va.ConcreteVA;
import tools.va.util.Set;
import tools.va.util.VAFunctions;
import tools.va.util.ValuePair;

import java.util.Iterator;
import java.util.function.Function;

public abstract class ConcreteVAD extends ConcreteVA {
    protected abstract double rangeValue(int i);
    public Iterator<ValuePair> getProbIterator() {
        return new Iterator<>(){
            private int cursor=lowerBound();
            @Override
            public boolean hasNext() {
                return cursor<=upperBound();
            }

            @Override
            public ValuePair next() {
                ValuePair toReturn = new ValuePair(rangeValue(cursor), prob(Set.EQ,rangeValue(cursor)));
                cursor++;
                return toReturn;
            }
        };
    }
    protected abstract int lowerBound();
    protected abstract int upperBound();
    protected double apply(Set s, VAFunctions d, double value) {
        return switch (s) {
            case EQ -> d.probability(value);
            case GEQ -> 1 - d.cumulativeProbability(value - 1);
            case LEQ -> d.cumulativeProbability(value);
            case GRE -> 1 - d.cumulativeProbability(value);
            case LES -> d.cumulativeProbability(value - 1);
        };
    }

    protected double applyInverse(Set s, Function<Double,Double> inverseCumul, double value) {
        double toReturn=0;
        switch (s) {
            case GEQ -> toReturn = inverseCumul.apply(1 - value);
            case LEQ -> toReturn = inverseCumul.apply(value); //OK
            case GRE -> toReturn = inverseCumul.apply(1 - value); //OK
            case LES-> {
                double leq = inverseCumul.apply(value);
                while(inverseCumul.apply(value)==leq)
                    value--;
                toReturn = inverseCumul.apply(value);
            }
            case EQ -> throw new IllegalArgumentException("No se puede usar EQ en probabilidad inversa");
        }
        return toReturn;
    }






}
