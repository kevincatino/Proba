package guia4;

import tools.va.util.Set;
import tools.va.vac.NotableVAC;

public class Ej17 {
    public static void main(String[] args) {
        NotableVAC vac = new NotableVAC(NotableVAC.normal());
        double q1 = vac.quantil(0.25);
        double q3 = vac.quantil(0.75);
        double iqr = q3-q1;
        double left = q1-1.5*iqr, right = q3+1.5*iqr;
        double ans = vac.prob(Set.LEQ,left) + vac.prob(Set.GEQ,right);
        System.out.println(ans);
        System.out.println(2*NotableVAC.normalCumul(-2.7));
    }
}
