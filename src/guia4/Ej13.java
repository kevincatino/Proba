package guia4;

import tools.va.vac.NotableVAC;
import tools.va.vad.NotableVAD;
import tools.va.util.Set;

public class Ej13 {
    public static void main(String[] args) {
        NotableVAC vac = NotableVAC.exponential(1.0/400);
        System.out.println(vac.prob(Set.LES,100));
        NotableVAD vad = NotableVAD.binomial(10, vac.prob(Set.LES,100));
        System.out.println(vad.prob(Set.GEQ,1));
    }
}
