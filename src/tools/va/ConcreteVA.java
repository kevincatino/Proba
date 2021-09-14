package tools.va;

import tools.va.util.Set;
import tools.va.util.VAFunctions;

import java.util.function.Function;

public abstract class ConcreteVA extends VA {
    protected abstract VAFunctions generateVADValues();

    protected abstract double apply(Set s, VAFunctions d, double value);

    public double prob(Set s, double value) {
        return apply(s, generateVADValues(), value);
    }

    public double F(double value) {
        return prob(Set.LEQ, value);
    }

    public double G(double value) {
        return prob(Set.GEQ,value);
    }

    public double probRange(boolean includeLeft, double left, double right, boolean includeRight) {
        double leftValue = includeLeft ? prob(Set.LEQ, left) : prob(Set.LES,left);
        double rightValue = includeRight ? prob(Set.LES,right) : prob(Set.LEQ,right);
        return rightValue-leftValue;
    }

}
