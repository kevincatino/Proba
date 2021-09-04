package guia4;

import tools.va.vac.FamousVAC;
import tools.va.vad.FamousVAD;
import tools.va.util.Set;

public class Ej10 {
    public static void main(String[] args) {
        FamousVAC vac = new FamousVAC(FamousVAC.exponential(0.001));
        System.out.println(vac.inverseProb(Set.GEQ, 0.05));
        FamousVAD vad1 = new FamousVAD(FamousVAD.binomial(5,vac.prob(Set.GEQ,100)));
        System.out.println(vad1.prob(Set.EQ,5));
        FamousVAD vad2 = new FamousVAD(FamousVAD.binomial(5,vac.prob(Set.GEQ,1000)));
        System.out.println(vad2.prob(Set.EQ,5));
        System.out.println(vad2.prob(Set.GEQ, 3));
    }
}
