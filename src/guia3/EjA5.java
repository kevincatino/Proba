package guia3;

import tools.va.util.Set;
import tools.va.ConcreteVA;
import tools.va.vad.FamousVAD;

public class EjA5 {
    public static void main(String[] args) {
        ConcreteVA vad = new FamousVAD(FamousVAD.poisson(10));
        System.out.println(vad.prob(Set.EQ, 5));
    }
}
