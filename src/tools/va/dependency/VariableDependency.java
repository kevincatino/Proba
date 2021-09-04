package tools.va.dependency;

import java.util.function.BiFunction;

public interface VariableDependency {
    static Dependency generate(BiFunction<Double, Double, Double> f, double param) {
        return rangeValue -> f.apply(rangeValue, param);
    }

}
