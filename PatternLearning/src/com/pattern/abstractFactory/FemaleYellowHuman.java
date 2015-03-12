package com.pattern.abstractFactory;


public class FemaleYellowHuman extends AbstractYellowHuman {

    @Override
    public void getSex() {
        System.out.println("Female Yellow.");
    }
}
