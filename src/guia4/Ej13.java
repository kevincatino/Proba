package guia4;

import tools.va.vac.FamousVAC;
import tools.va.vad.FamousVAD;
import tools.va.util.Set;

public class Ej13 {
    public static void main(String[] args) {
        FamousVAC vac = new FamousVAC(FamousVAC.exponential(1.0/400));
        System.out.println(vac.prob(Set.LES,100));
        FamousVAD vad = new FamousVAD(FamousVAD.binomial(10, vac.prob(Set.LES,100)));
        System.out.println(vad.prob(Set.GEQ,1));
    }
}
