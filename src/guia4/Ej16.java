package guia4;

import tools.va.vac.NotableVAC;
import tools.va.util.Set;

public class Ej16 {
    public static void main(String[] args) {
        NotableVAC vad =NotableVAC.weibull(0.01,2);
        System.out.println(vad.prob(Set.GEQ,20)/vad.prob(Set.GEQ,10));
    }
}
