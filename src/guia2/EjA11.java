package guia2;

import tools.va.vad.DefinedVAD;
import tools.va.util.ProbCalcInt;

public class EjA11 {
    public static void main(String[] args) {
        ProbCalcInt p = (n)-> Math.pow(5.0/6,n-1)*(1/6.0);
        DefinedVAD vad = new DefinedVAD(1,100, p);
        System.out.println(vad);
    }
}
