package guia4;

import tools.va.vac.NotableVAC;
import tools.va.vad.NotableVAD;
import tools.va.util.Set;

public class Ej14 {
    public static void main(String[] args) {
        NotableVAC vac = NotableVAC.exponential(1.182*Math.pow(10,-3));
        NotableVAD vad =NotableVAD.binomial(5,vac.prob(Set.GRE,1200));
        System.out.println(vad.prob(Set.GEQ,1));
        NotableVAD vad2 = NotableVAD.binomial(5, vac.prob(Set.GEQ,1500));
        System.out.println(vad2.prob(Set.EQ,5));
    }
}
