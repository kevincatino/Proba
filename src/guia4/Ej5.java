package guia4;

import tools.va.vac.DefinedVAC;

public class Ej5 {
    public static void main(String[] args) {
        DefinedVAC vac = new DefinedVAC(0,1,(v)->20*Math.pow(v,3)*(1-v));
        System.out.println((vac.F(1/2.0)-vac.F(1/3.0))/(vac.F(2/3.0)-vac.F(1/3.0)));
    }
}
