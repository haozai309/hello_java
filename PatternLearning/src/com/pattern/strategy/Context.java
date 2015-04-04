package com.pattern.strategy;

public class Context {

    private IStrategy mStrategy;

    public Context(IStrategy strategy) {
        mStrategy = strategy;
    }

    public void operate() {
        mStrategy.operate();
    }
}
