package guia4;

import tools.va.vac.DefinedVAC;
import tools.va.util.Set;

public class Ej3 {
    public static void main(String[] args) {
        DefinedVAC vac = new DefinedVAC(0,1,(v)->2*(1-v));
        System.out.println(vac.prob(Set.LEQ,0.2929));
        System.out.println(vac.prob(Set.LEQ,vac.inverseProb(Set.LEQ,0.5)));
    }
}
