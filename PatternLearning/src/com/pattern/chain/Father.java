package com.pattern.chain;

public class Father implements IHandler {

    @Override
    public void handleMessage(IWomen women) {
        System.out.println("Daughter's request is " + women.getRequest());
        System.out.println("Father's replay is OK.");
    }

}
