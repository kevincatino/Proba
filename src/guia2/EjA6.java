package guia2;

import tools.math.Mathz;
import tools.va.VA;
import tools.va.dependency.DepVAD;
import tools.va.vad.DefinedVAD;
import tools.va.util.ProbCalcInt;

public class EjA6 {
    public static void main(String[] args) {
        ProbCalcInt p = (n) -> {
          return (Mathz.choose(10,n)*Mathz.choose(30,3-n))/(double)Mathz.choose(40,3);
        };
        DefinedVAD vad = new DefinedVAD(0,3,p);
        System.out.println(vad);
        VA vadd = new DepVAD(vad, (v)->v*v-2.0);
        System.out.println(vadd);
    }
}
