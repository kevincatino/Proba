package guia2;

import tools.math.Mathz;
import tools.va.vad.DefinedVAD;
import tools.va.util.ProbCalcInt;

public class EjA5 {
    public static void main(String[] args) {
        ProbCalcInt p = (n)-> {
          return (4.0/n)*(Mathz.choose(36,n-1)/(double)Mathz.choose(40,n));
        };
        DefinedVAD vad = new DefinedVAD(1,37,p);
        System.out.println(vad);
    }
}
