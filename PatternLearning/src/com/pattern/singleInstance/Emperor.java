package com.pattern.singleInstance;

/*
 * Singleton pattern: Ensure a class has only one instance, and provide a global point of access
 * to it.
 */
public class Emperor {

    private static final Emperor sEmperor = new Emperor();

    private Emperor() {

    }

    public static Emperor getInstance() {
        return sEmperor;
    }

    public void say() {
        System.out.println("This is the unique emperor.");
    }
}
