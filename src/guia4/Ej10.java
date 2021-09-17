package guia4;

import tools.va.vac.NotableVAC;
import tools.va.vad.NotableVAD;
import tools.va.util.Set;

public class Ej10 {
    public static void main(String[] args) {
        NotableVAC vac = NotableVAC.exponential(0.001);
        System.out.println(vac.inverseProb(Set.GEQ, 0.05));
        NotableVAD vad1 = NotableVAD.binomial(5,vac.prob(Set.GEQ,100));
        System.out.println(vad1.prob(Set.EQ,5));
        NotableVAD vad2 = NotableVAD.binomial(5,vac.prob(Set.GEQ,1000));
        System.out.println(vad2.prob(Set.EQ,5));
        System.out.println(vad2.prob(Set.GEQ, 3));
    }
}
