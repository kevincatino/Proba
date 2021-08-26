package guia2;

import tools.ProbCalc;
import tools.VAD;

public class EjA3 {
    public static void main(String[] args) {
        ProbCalc p  = (n)-> {
            return ((6-n)*2.0)/36;
        };
        VAD vad = new VAD(1,6,p);
        System.out.println(vad);
    }
}
