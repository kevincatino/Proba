package guia2;

import tools.*;

public class EjA6 {
    public static void main(String[] args) {
        ProbCalc p = (n) -> {
          return (Mathz.choose(10,n)*Mathz.choose(30,3-n))/(double)Mathz.choose(40,3);
        };
        VADDef vad = new VADDef(0,3,p);
        System.out.println(vad);
        VAD vadd = new VADD(vad, (v)->v*v-2.0);
        System.out.println(vadd);
    }
}
