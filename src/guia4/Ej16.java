package guia4;

import tools.va.vac.FamousVAC;
import tools.va.util.Set;

public class Ej16 {
    public static void main(String[] args) {
        FamousVAC vad = new FamousVAC(FamousVAC.weibull(0.01,2));
        System.out.println(vad.prob(Set.GEQ,20)/vad.prob(Set.GEQ,10));
    }
}
