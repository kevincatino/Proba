package guia2;

import tools.va.vad.DefinedVAD;
import tools.va.util.ProbCalcInt;

public class EjA9 {
    public static void main(String[] args) {
        ProbCalcInt p  = (n) -> Math.pow(0.8,n-1)*0.2;
        DefinedVAD vad = new DefinedVAD(1,10,p);
        System.out.println(vad);
    }
}
