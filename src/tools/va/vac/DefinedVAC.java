package tools.va.vac;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.RombergIntegrator;
import tools.va.util.Set;
import tools.va.util.VAFunctions;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.DoubleStream;

public class DefinedVAC extends ConcreteVAC{
    private Double a,b;
    private Double[] intervals;
    private UnivariateFunction[] fs;
    private final static int MAX_EVAL=Integer.MAX_VALUE;
    private final static double INFINITE=Double.MAX_VALUE;

    /***
     *
     * @param a limite izquierdo. Si es null se considera +inf
     * @param b limite derecho. Si es null se considera -inf
     * @param f funcion de densidad de probabilidad
     */
    public DefinedVAC(Double a, Double b, UnivariateFunction f) {
        this(new Double[]{a,b}, new UnivariateFunction[]{f});
    }


    public DefinedVAC(Double[] intervals, UnivariateFunction[] fs) {
        if (intervals.length != fs.length+1)
            throw new RuntimeException("la cantidad de intervalos y funciones no coincide.");

        if (intervals[0]==null)
            intervals[0]=-INFINITE;

        if (intervals[intervals.length-1]==null)
            intervals[intervals.length-1]=INFINITE;
        a = intervals[0];
        b = intervals[intervals.length-1];
        this.intervals=intervals;
        this.fs=fs;
    }

    public DefinedVAC(Integer[] intervals, UnivariateFunction[] fs) {
        this.intervals = new Double[intervals.length];
        for (int i=0 ; i<intervals.length ; i++)
            if (intervals[i]==null)
                this.intervals[i]=null;
            else
                this.intervals[i]= (double) intervals[i];

        if (intervals.length != fs.length+1)
            throw new RuntimeException("la cantidad de intervalos y funciones no coincide.");

        if (intervals[0]==null)
            this.intervals[0]=-INFINITE;

        if (intervals[intervals.length-1]==null)
            this.intervals[intervals.length-1]=INFINITE;
        a = this.intervals[0];
        b = this.intervals[intervals.length-1];
        this.fs=fs;
    }

    /***
     *
     * @param a limite izquierdo. Si es null se considera +inf
     * @param b limite derecho. Si es null se considera -inf
     * @param f funcion de densidad de probabilidad
     */
    public DefinedVAC(Integer a, Integer b, UnivariateFunction f) {
        this(new Integer[]{a,b}, new UnivariateFunction[]{f});
    }

    private double integrate(BiFunction<UnivariateFunction,Double,Double> f, Double[] intervals) {
        double accum=0;
        for (int i=0 ; i<fs.length ; i++) {
            UnivariateFunction func = fs[i];
            accum+=integrate((v)->f.apply(func,v),intervals[i],intervals[i+1]);
        }
        return accum;
    }

    private Double[] getIntervals(double x) {
        int i;
        for (i=0 ; i<intervals.length && intervals[i]<=x ; i++);
        Double[] toReturn = new Double[i+1];
        for (int j=0 ; j<i ; j++)
            toReturn[j]=intervals[j];
        toReturn[i] = x;
        return toReturn;
    }

    @Override
    protected VAFunctions generateVADValues() {
        return new VAFunctions() {
            @Override
            public double probability(double x) {
                return 0;
            }

            @Override
            public double cumulativeProbability(double x) {
                if (x<=a)
                    return 0;
                else if(x>=b)
                    return 1;
                Double[] intervals = getIntervals(x);
                return integrate(UnivariateFunction::value,intervals);
            }
        };
    }

    @Override
    public double ev() {
        return integrate((f,v)->v*f.value(v), intervals);
    }

    @Override
    public double var() {
        return integrate((f,v)->v*v*f.value(v), intervals)-Math.pow(ev(),2);
    }

    @Override
    public double std() {
        return Math.sqrt(var());
    }

    @Override
    public Function<Double, Double> getInverseCumulFunction() {
        return (v)-> {
            double lower=a,higher=b,delta=10000;
            if (a==-INFINITE) {
                lower=0;
                while(prob(Set.LEQ,lower)>v) {
                    lower-=delta;
                }

            }
            if (b==INFINITE) {
                higher=0;
                while(prob(Set.LEQ,higher)<v) {
                    higher+=delta;
                }
            }

            double med = (lower+higher)/2;
            double result;
            do  {
                result = prob(Set.LEQ, med);
                if (result<v)
                    lower = med;
                else if (result>v)
                    higher = med;
                med = (lower+higher)/2;

            } while (Math.abs(v-result)>LAMBDA);
            return med;
        };
    }

    @Override
    public double density(double value) {
            if (value < a)
                return 0;
            else if(value > b)
                return 0;
            for (int i=0 ; i<fs.length ; i++) {
                if (value>=intervals[i])
                    return fs[i].value(value);
            }
            throw new RuntimeException();
    }

    @Override
    public double lowRangeValue() {
        return a;
    }

    @Override
    public double highRangeValue() {
        return b;
    }


}
