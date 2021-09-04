package tools.math;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class Mathz {
    public static long choose(int n, int m) {
        return CombinatoricsUtils.binomialCoefficient(n, m);
    }
    public static long factorial(int n) {
        return CombinatoricsUtils.factorial(n);
    }
}
