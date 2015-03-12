package com.pattern.factory;

public class BlackHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("Black human is black.");
    }

    @Override
    public void talk() {
        System.out.println("Black human talk.");
    }

}
