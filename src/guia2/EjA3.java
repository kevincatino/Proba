package guia2;

import tools.ProbCalc;
import tools.VADDef;

public class EjA3 {
    public static void main(String[] args) {
        ProbCalc p  = (n)-> {
            if (n==0)
                return 6.0/36;
            return ((6-n)*2.0)/36;
        };
        VADDef vad = new VADDef(0,6,p);
        System.out.println(vad);
    }
}
