package guia4;

import tools.va.vac.NotableVAC;
import tools.va.util.Set;

public class Ej2 {
    public static void main(String[] args) {
        NotableVAC vac = new NotableVAC(NotableVAC.uniform(0,8));
        System.out.println(vac.prob(Set.LEQ,5)-vac.prob(Set.LEQ,2));
    }
}
