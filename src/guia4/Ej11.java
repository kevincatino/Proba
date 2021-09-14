package guia4;

import tools.va.vac.NotableVAC;
import tools.va.util.Set;

public class Ej11 {
    public static void main(String[] args) {
        NotableVAC vacA = new NotableVAC(NotableVAC.exponential(1.0/500));
        NotableVAC vacB = new NotableVAC(NotableVAC.exponential(1.0/800));
        double prob1 = vacA.prob(Set.GRE, 1000);
        double prob2 = vacB.prob(Set.GRE, 1000);
        double systemFailureAfter1000 = prob1*prob2;
        System.out.println(systemFailureAfter1000);

    }
}
