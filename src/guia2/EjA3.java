package guia2;

import tools.va.vad.DefinedVAD;
import tools.va.util.ProbCalcInt;

public class EjA3 {
    public static void main(String[] args) {
        ProbCalcInt p  = (n)-> {
            if (n==0)
                return 6.0/36;
            return ((6-n)*2.0)/36;
        };
        DefinedVAD vad = new DefinedVAD(0,6,p);
        System.out.println(vad);
    }
}
