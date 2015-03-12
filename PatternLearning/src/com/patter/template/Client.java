package com.patter.template;

public class Client {

    public static void main(String[] args) {
        System.out.println("---- Hummer H1 model ----");
        HummerModel h1 = new HummerH1Model();
        h1.run();

        System.out.println("\n---- Hummer H2 model ----");
        HummerModel h2 = new HummerH2Model();
        h2.run();
    }
}
