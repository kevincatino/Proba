package guia2;

import tools.math.Mathz;
import tools.va.vad.DefinedVAD;
import tools.va.util.ProbCalcInt;


public class Ej12 {
    public static void main(String[] args) {
    ProbCalcInt p = (k)-> Mathz.choose(13,k)*Mathz.choose(39,5-k)/(double)Mathz.choose(52,5);
    DefinedVAD vad = new DefinedVAD(new int[]{1,2,3,4,5}, p);
        System.out.println(vad);
    }
}
