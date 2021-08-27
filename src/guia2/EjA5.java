package guia2;

import tools.Mathz;
import tools.ProbCalc;
import tools.VADDef;

public class EjA5 {
    public static void main(String[] args) {
        ProbCalc p = (n)-> {
          return (4.0/n)*(Mathz.choose(36,n-1)/(double)Mathz.choose(40,n));
        };
        VADDef vad = new VADDef(1,37,p);
        System.out.println(vad);
    }
}
