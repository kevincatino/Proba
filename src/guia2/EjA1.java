package guia2;

import tools.VAD;

public class EjA1 {
    public static void main(String[] args) {
        int[] range = {-10,10};
        double[] prob = {19.0/37,18.0/37};
        VAD vad = new VAD(range, prob);
        System.out.println(vad);
    }
}
