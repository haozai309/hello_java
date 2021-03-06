package com.pattern.singleInstance;

public class Singleton {

    private static final Singleton sSingleton = new Singleton();

    // prevent to generate several objects
    private Singleton() {

    }

    // get the single instance
    private static Singleton getsSingleton() {
        return sSingleton;
    }

    private static void doSomething() {

    }
}
