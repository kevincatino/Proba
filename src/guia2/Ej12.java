package guia2;

import tools.Mathz;
import tools.ProbCalc;
import tools.VAD;


public class Ej12 {
    public static void main(String[] args) {
    ProbCalc p = (k)-> {
        return Mathz.choose(13,k)*Mathz.choose(39,5-k)/(double)Mathz.choose(52,5);
    };
    VAD vad = new VAD(0, 5, p);
        System.out.println(vad);
    }
}
