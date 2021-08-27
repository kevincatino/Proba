package tools;

import org.apache.commons.math3.fraction.Fraction;

public abstract class VAD {
    abstract double ev();
    abstract double var();
    abstract double std();
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("E: %.4f  (%s)\n", ev(), new Fraction(ev())));
        s.append(String.format("V: %.4f  (%s)\n", var(), new Fraction(var())));
        s.append(String.format("DE: %.4f  (%s)\n", std(), new Fraction(std())));
        return s.toString();
    }


}
