package com.pattern.strategy;

public class GiveGreenLight implements IStrategy {

    @Override
    public void operate() {
        System.out.println("Beg aunt Wu to let me go.");
    }

}
