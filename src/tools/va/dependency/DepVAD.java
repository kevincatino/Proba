package tools.va.dependency;


import org.apache.commons.math3.fraction.Fraction;
import tools.va.util.VAFunctions;
import tools.va.vad.DefinedVAD;
import tools.va.VA;
import tools.va.util.Set;
import tools.va.util.ValuePair;
import tools.va.vad.VAD;


import java.util.Iterator;


public class DepVAD extends DepVA {
    private final VAD vad;
    private final Dependency f;

    public DepVAD(VAD vad, Dependency f) {
        this.vad = vad;
        this.f = f;
    }

    @Override
    public double ev() {
        double value=0;
        Iterator<ValuePair> it = vad.getProbIterator();
        while(it.hasNext()) {
            ValuePair pair = it.next();
            value+=f.apply(pair.value)*pair.prob;
        }
        return value;
    }



    @Override
    public double var() {
        double value=0;
        for(int i=vad.lowerBound() ; i<= vad.upperBound() ; i++) {
            value+=Math.pow(f.apply(vad.rangeValue(i)),2)*vad.prob(Set.EQ,vad.rangeValue(i));
        }
        return value-Math.pow(ev(),2);
    }

    @Override
    public double std() {
        return Math.sqrt(var());
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("E: %.4f (%s)\n", ev(), new Fraction(ev())));
        s.append(String.format("V: %.4f (%s)\n", var(), new Fraction(var())));
        s.append(String.format("DE: %.4f (%s)\n", std(), new Fraction(std())));
        return s.toString();
    }

    @Override
    protected VAFunctions generateVADValues() {
        return new VAFunctions() {
            @Override
            public double probability(double value) {
                double prob=0;
                Iterator<ValuePair> it = vad.getProbIterator();
                while(it.hasNext()) {
                    ValuePair pair = it.next();
                    if (f.apply(pair.value)==value)
                        prob+=pair.prob;
                }
                return prob;
            }

            @Override
            public double cumulativeProbability(double x) {
                double accum=0;
                Iterator<ValuePair> it = vad.getProbIterator();
                while(it.hasNext()) {
                    ValuePair pair = it.next();
                    if (f.apply(pair.value)<x)
                        accum+=pair.prob;
                }
                return accum;
            }
        };
    }

    @Override
    protected double apply(Set s, VAFunctions d, double value) {
        return switch (s) {
            case EQ -> d.probability(value);
            case GEQ -> 1 - d.cumulativeProbability(value - 1);
            case LEQ -> d.cumulativeProbability(value);
            case GRE -> 1 - d.cumulativeProbability(value);
            case LES -> d.cumulativeProbability(value - 1);
        };
    }
}
