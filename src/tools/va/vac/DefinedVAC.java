package tools.va.vac;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.RombergIntegrator;
import tools.va.util.Set;
import tools.va.util.VAFunctions;

import java.util.function.Function;

public class DefinedVAC extends ConcreteVAC {
    private Double a,b;
    private UnivariateFunction f;
    private final static double LAMBDA=0.00001;
    private final static int MAX_EVAL=Integer.MAX_VALUE;
    private final static double INFINITE=Double.MAX_VALUE;

    public DefinedVAC(Double a, Double b, UnivariateFunction f) {
        if (a==null)
            this.a=-INFINITE;
        else
            this.a=a;
        if (b==null)
            this.b=INFINITE;
        else
            this.b=b;

        this.f=f;
    }

    public DefinedVAC(Integer a, Integer b, UnivariateFunction f) {
        if (a==null)
            this.a=-INFINITE;
        else
            this.a=(double)a;
        if (b==null)
            this.b=INFINITE;
        else
            this.b=(double)b;

        this.f=f;
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
                return new RombergIntegrator().integrate(MAX_EVAL,f,a,x);
            }
        };
    }

    @Override
    public double ev() {
        return new RombergIntegrator().integrate(MAX_EVAL,(v)->v*f.value(v),a,b);
    }

    @Override
    public double var() {
        return new RombergIntegrator().integrate(MAX_EVAL,(v)->v*v*f.value(v),a,b)-Math.pow(ev(),2);
    }

    @Override
    public double std() {
        return Math.sqrt(var());
    }

    @Override
    protected Function<Double, Double> getInverseCumulFunction() {
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
}
