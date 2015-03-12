package com.patter.template;

public class HummerH1Model extends HummerModel {

    @Override
    public void start() {
        System.out.println("Hummer H1 start");
    }

    @Override
    public void stop() {
        System.out.println("Hummer H1 stop");
    }

    @Override
    public void alarm() {
        System.out.println("Hummer H1 alarm");
    }

    @Override
    public void engineBoom() {
        System.out.println("Hummer H1 engine boom");
    }

}
