package com.pattern.chain;

public class Husband implements IHandler {

    @Override
    public void handleMessage(IWomen women) {
        System.out.println("Wife's request is " + women.getRequest());
        System.out.println("Husband's replay is OK.");
    }

}
