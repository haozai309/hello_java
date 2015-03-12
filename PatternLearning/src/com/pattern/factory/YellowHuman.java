package com.pattern.factory;

public class YellowHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("Yellow human is yellow.");
    }

    @Override
    public void talk() {
        System.out.println("Yellow human talk.");
    }

}
