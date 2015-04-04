package com.pattern.chain;

public class Son implements IHandler {

    @Override
    public void handleMessage(IWomen women) {
        System.out.println("Mother's request is " + women.getRequest());
        System.out.println("Son's replay is OK.");
    }

}
