package guia3;

import tools.va.vac.FamousVAC;

public class Ej2 {
    public static void main(String[] args) {
        FamousVAC vac = new FamousVAC(FamousVAC.uniform(0,8));
        System.out.println(vac.probRange(false,2,5,false)); //0.375
        System.out.println(vac.probRange(false, 0,3,false)/vac.probRange(false,0,5,false)); //0.5
        //0.6
    }
}
