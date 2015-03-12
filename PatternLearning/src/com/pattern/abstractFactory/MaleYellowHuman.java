package com.pattern.abstractFactory;

public class MaleYellowHuman extends AbstractYellowHuman {

    @Override
    public void getSex() {
        System.out.println("Male Yellow.");
    }
}
