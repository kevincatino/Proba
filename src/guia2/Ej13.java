package guia2;

import tools.Mathz;


public class Ej13 {
    private static int TOTAL=50;
    private static int ERRORS=5;
    private static int CHOSEN=10;
    public static void main(String[] args) {
        for (int i=0 ; i<=ERRORS ; i++) {
            System.out.println(String.format("%s: %.4f",i, px(i)));
        }
    }
    public static double px (int x) {
        return (Mathz.choose(TOTAL - ERRORS, CHOSEN - x)* Mathz.choose(ERRORS, x))/(double)Mathz.choose(TOTAL, CHOSEN) ;
    }
}
