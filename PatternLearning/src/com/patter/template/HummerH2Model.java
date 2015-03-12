package com.patter.template;

public class HummerH2Model extends HummerModel {

    @Override
    public void start() {
        System.out.println("Hummer H2 start");
    }

    @Override
    public void stop() {
        System.out.println("Hummer H2 stop");
    }

    @Override
    public void alarm() {
        System.out.println("Hummer H2 alarm");
    }

    @Override
    public void engineBoom() {
        System.out.println("Hummer H2 engine boom");
    }

}
