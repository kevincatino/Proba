package guia2;

import tools.math.Mathz;
import tools.va.vad.DefinedVAD;
import tools.va.util.ProbCalcInt;


public class Ej13 {
    private static int TOTAL=50;
    private static int ERRORS=5;
    private static int CHOSEN=10;
    public static void main(String[] args) {
        ProbCalcInt p = (x) -> {
            return (Mathz.choose(TOTAL - ERRORS, CHOSEN - x)* Mathz.choose(ERRORS, x))/(double)Mathz.choose(TOTAL, CHOSEN) ;
        };
        DefinedVAD vad = new DefinedVAD(0,5,p);
        System.out.println(vad);
    }
}
