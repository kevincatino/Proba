package tools.va.vad;

import tools.va.ConcreteVA;
import tools.va.util.Set;
import tools.va.util.VAFunctions;
import tools.va.util.ValuePair;

import java.util.Iterator;
import java.util.function.Function;

public abstract class VAD extends ConcreteVA {
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

    /***
     *
     * @param proba
     * @return el primer valor del rango para el cual se acumula una probabilidad igual o mayor a proba
     */
    public abstract double inverseCumul(double proba);









}
