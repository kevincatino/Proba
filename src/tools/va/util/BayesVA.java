package tools.va.util;

import tools.va.ConcreteVA;
import tools.va.VA;

import java.util.Arrays;

public class BayesVA {
    private final ConcreteVA[] vas;
    private final Double[] prob;

    public BayesVA(ConcreteVA[] vas, Double[] prob) {
        if (vas.length!=prob.length || Arrays.stream(prob).reduce(0.0, Double::sum)!=1)
            throw new RuntimeException("Valores incompatibles");
        this.vas =vas;
        this.prob=prob;
    }

    public double prob(Set s, double value) {
        double acum=0;
        for (int i=0 ; i<prob.length ; i++)
            acum+=prob[i]* vas[i].prob(s,value);
        return acum;
    }

    public double condProb(ConcreteVA va, Set s, double value) {
        for (int i=0 ; i<vas.length ; i++)
            if (vas[i]==va)
                return va.prob(s,value)*prob[i]/prob(s,value);
            return 0.0;
    }
}
