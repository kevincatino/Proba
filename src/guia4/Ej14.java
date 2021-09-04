package guia4;

import tools.va.vac.FamousVAC;
import tools.va.vad.FamousVAD;
import tools.va.util.Set;

public class Ej14 {
    public static void main(String[] args) {
        FamousVAC vac = new FamousVAC(FamousVAC.exponential(1.182*Math.pow(10,-3)));
        FamousVAD vad = new FamousVAD(FamousVAD.binomial(5,vac.prob(Set.GRE,1200)));
        System.out.println(vad.prob(Set.GEQ,1));
        FamousVAD vad2 = new FamousVAD(FamousVAD.binomial(5, vac.prob(Set.GEQ,1500)));
        System.out.println(vad2.prob(Set.EQ,5));
    }
}
