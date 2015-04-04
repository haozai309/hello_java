package com.pattern.chain;

public class Son extends Handler {

    public Son() {
        super(Handler.SON_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("----- Mother give request to son -----");
        System.out.println(women.getRequest());
        System.out.println("Son's reply is OK.");
    }

}