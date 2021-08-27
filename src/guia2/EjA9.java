package guia2;

import tools.ProbCalc;
import tools.VADDef;

public class EjA9 {
    public static void main(String[] args) {
        ProbCalc p  = (n) -> {
            return Math.pow(0.8,n-1)*0.2;
        };
        VADDef vad = new VADDef(1,10,p);
        System.out.println(vad);
    }
}
