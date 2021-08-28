package guia3;

import tools.Select;
import tools.VADF;

public class EjA14 {
    public static void main(String[] args) {
    VADF vad = new VADF(VADF.geoII(0.5));
        System.out.println(vad.prob(Select.LEQ, 4));
        System.out.println(vad);

    }
}
