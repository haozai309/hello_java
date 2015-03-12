package com.pattern.abstractFactory;

public abstract class AbstractYellowHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("Yellow human is yellow.");
    }

    @Override
    public void talk() {
        System.out.println("Yellow human talk.");
    }
}
