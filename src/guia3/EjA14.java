package guia3;

import tools.va.util.Set;
import tools.va.vad.NotableVAD;

public class EjA14 {
    public static void main(String[] args) {
    NotableVAD vad = NotableVAD.geoII(0.3);
        System.out.println(vad.prob(Set.EQ, 5));
        System.out.println(vad);
    }
}
