package guia2;

import tools.Mathz;
import tools.ProbCalc;
import tools.VADDef;


public class Ej13 {
    private static int TOTAL=50;
    private static int ERRORS=5;
    private static int CHOSEN=10;
    public static void main(String[] args) {
        ProbCalc p = (x) -> {
            return (Mathz.choose(TOTAL - ERRORS, CHOSEN - x)* Mathz.choose(ERRORS, x))/(double)Mathz.choose(TOTAL, CHOSEN) ;
        };
        VADDef vad = new VADDef(0,5,p);
        System.out.println(vad);

    }
}
