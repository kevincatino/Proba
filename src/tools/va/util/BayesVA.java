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
            if (vas[i]==va) {
                return (va.prob(s,value)*prob[i])/prob(s,value);
            }

            return 0.0;
    }

    public double ev() {
        double accum=0;
        for (int i=0 ; i<vas.length ; i++) {
            accum+=prob[i]*vas[i].ev();
        }
        return accum;
    }

    public double var() {
        double accum=0;
        for (int i=0 ; i<vas.length ; i++) {
            double var=vas[i].var();
            accum += (var + Math.pow(vas[i].ev(),2))*prob[i];

        }
        return accum-Math.pow(ev(),2);
    }

    public double std() {
        return Math.sqrt(var());
    }
}
