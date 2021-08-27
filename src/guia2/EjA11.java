package guia2;

import tools.ProbCalc;
import tools.VADDef;

public class EjA11 {
    public static void main(String[] args) {
        ProbCalc p = (n)-> {
            return Math.pow(5.0/6,n-1)*(1/6.0);
        };
        VADDef vad = new VADDef(1,100, p);
        System.out.println(vad);
    }
}
