package guia4;

import tools.va.vac.DefinedVAC;
import tools.va.vad.NotableVAD;
import tools.va.util.Set;

public class Ej4 {
    public static void main(String[] args) {
        DefinedVAC vac = new DefinedVAC(0,2,(v)->v*0.5);
        double prob = vac.prob(Set.GEQ,1);
        System.out.println(prob);
        NotableVAD vad = new NotableVAD(NotableVAD.binomial(3,0.75));
        System.out.println(vac);
        System.out.println(vad.prob(Set.EQ, 2));
        System.out.println(vac.prob(Set.GEQ,vac.inverseProb(Set.GEQ,0.75)));
    }
}
