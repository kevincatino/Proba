package guia2;

import tools.va.vad.DefinedVAD;
import tools.va.dependency.DepVAD;
import tools.va.dependency.VariableDependency;

import java.util.function.BiFunction;

public class Ej11 {
    public static void main(String[] args) {
        DefinedVAD vad = new DefinedVAD(63,70,new double[]{0.01,0.04, 0.06,0.08,0.15, 0.28,0.22,0.16});
        BiFunction<Double,Double,Double> f = (v, k)-> {
            if (v>k)
                return k-k*0.4;
            else
                return v - k*0.4;
        };

        for (int i=63 ; i<=70 ; i++) {
            System.out.println(String.format("%s: %.4f",i, new DepVAD(vad, VariableDependency.generate(f,i)).ev()));
        }
        }

}
