package com.pattern.factory;

public class WhiteHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("White human is white.");
    }

    @Override
    public void talk() {
        System.out.println("White human talk.");
    }

}

