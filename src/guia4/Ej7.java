package guia4;

import tools.va.vac.NotableVAC;
import tools.va.util.Set;

public class Ej7 {
    public static void main(String[] args) {
        NotableVAC vac = new NotableVAC(NotableVAC.uniform(0,5));
        System.out.println(vac.prob(Set.LEQ,3));
    }
}
