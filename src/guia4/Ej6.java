package guia4;

import tools.va.vac.DefinedVAC;
import tools.va.util.Set;

public class Ej6 {
    public static void main(String[] args) {
        DefinedVAC vac = new DefinedVAC(100, null, (x)->100/(x*x));
//        System.out.println((vac.F(200)-vac.F(150))/(1-vac.F(150)));
//        FamousVAD vad = new FamousVAD(FamousVAD.binomial(3, vac.F(150)));
//        System.out.println(vad.prob(Select.EQ,2));
        System.out.println(vac.prob(Set.LEQ,150));
        System.out.println(vac.inverseProb(Set.LEQ,0.3));
    }
}
