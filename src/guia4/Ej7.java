package guia4;

import tools.va.vac.FamousVAC;
import tools.va.util.Set;

public class Ej7 {
    public static void main(String[] args) {
        FamousVAC vac = new FamousVAC(FamousVAC.uniform(0,5));
        System.out.println(vac.prob(Set.LEQ,3));
    }
}
