package guia3;

import tools.va.util.Set;
import tools.va.vad.VAD;
import tools.va.vad.NotableVAD;

public class EjA5 {
    public static void main(String[] args) {
        VAD vad = new NotableVAD(NotableVAD.poisson(10));
        System.out.println(vad.prob(Set.EQ, 5));
        System.out.println(vad.prob(Set.LEQ,vad.inverseCumul(0.335)));
    }
}
