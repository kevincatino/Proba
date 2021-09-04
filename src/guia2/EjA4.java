package guia2;

import tools.math.Mathz;
import tools.va.vad.DefinedVAD;
import tools.va.util.ProbCalcInt;

public class EjA4 {
    public static void main(String[] args) {
        double a = (1.0/6)*(Mathz.choose(3,2)/(double)Mathz.choose(10,2));
        double b = (1.0/6)*(Mathz.choose(7,1)*Mathz.choose(3,1)/(double)Mathz.choose(10,2));
        double c = (1.0/6)*(Mathz.choose(7,2)/(double)Mathz.choose(10,2));
        ProbCalcInt p = (n)-> {
            if (n >= 3 && n<=6)
                return a+b+c;
            if (n==1)
                return a;
            if (n==2)
                return a+b;
            if (n==7)
                return b+c;
            return c;
        };
        DefinedVAD vad = new DefinedVAD(1,8,p);
        System.out.println(vad);
    }
}
