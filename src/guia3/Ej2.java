package guia3;

import tools.va.vac.NotableVAC;

public class Ej2 {
    public static void main(String[] args) {
        NotableVAC vac = new NotableVAC(NotableVAC.uniform(0,8));
        System.out.println(vac.probRange(false,2,5,false)); //0.375
        System.out.println(vac.probRange(false, 0,3,false)/vac.probRange(false,0,5,false)); //0.5
        //0.6
    }
}
