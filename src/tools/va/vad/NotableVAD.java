package tools.va.vad;

import org.apache.commons.math3.distribution.*;
import tools.va.util.GeometricDistributionII;
import tools.va.util.VAFunctions;

public class NotableVAD extends VAD {
    private final AbstractIntegerDistribution d;
    public NotableVAD(AbstractIntegerDistribution d) {
        this.d=d;
    }

    @Override
    public double ev() {
        return d.getNumericalMean();
    }

    @Override
    public double var() {
        return d.getNumericalVariance();
    }

    @Override
    public double std() {
        return Math.sqrt(var());
    }



    public int lowerBound() {
        return d.getSupportLowerBound();
    }


    public int upperBound() {
        return d.getSupportUpperBound();
    }


    public double inverseCumul(double prob) {
        return d.inverseCumulativeProbability(prob);
    }




    public double rangeValue(int i) {
        return i;
    }





    /***
     *
     * @param n cantidad de elementos de la muestra
     * @param p probabilidad de éxito
     * @return instancia de VAD con distr. binomial para el constructor de VADF
     */
    public static NotableVAD binomial(int n, double p){
        return new NotableVAD(new BinomialDistribution(n,p));
    }

    /***
     *
     * @param e valor esperado (lambda)
     * @return instancia de VAD con distr. de Poisson para el constructor de VADF
     */
    public static NotableVAD poisson(double e){
        return new NotableVAD(new PoissonDistribution(e));
    }

    /***
     *
     * @param N cantidad total de elementos
     * @param m elementos que cumplen con la condición
     * @param n cantidad de elementos de la muestra
     * @return instancia de VAD con distr. hipergeométrica para el constructor de VADF
     */
    public static NotableVAD hipergeo(int N, int m, int n){
        return new NotableVAD(new HypergeometricDistribution(N,m,n));
    }

    /***
     *
     * @param p probabilidad de éxito
     * @return instancia de VAD con distr. geométricaI para el constructor de VADF. La variable aleatoria es el número de fallos antes del primer éxito.
     */
    public static NotableVAD geo(double p){
        return new NotableVAD(new GeometricDistribution(p));
    }
    /***
     *
     * @param p probabilidad de éxito
     * @return instancia de VAD con distr. geométricaII para el constructor de VADF. La variable aleatoria es el intento en el cual ocurre el primer éxito.
     */
    public static NotableVAD geoII(double p){
        return new NotableVAD(new GeometricDistributionII(p));
    }

    @Override
    protected VAFunctions generateVADValues() {
        return new VAFunctions(){

            @Override
            public double probability(double x) {
                return d.probability((int)x);
            }

            @Override
            public double cumulativeProbability(double x) {
                return d.cumulativeProbability((int)x);
            }

        };
    }


}
