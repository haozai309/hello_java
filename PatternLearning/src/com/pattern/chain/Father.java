package com.pattern.chain;

public class Father extends Handler {
    public Father() {
        super(Handler.FATHER_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("----- Daughter give request to father -----");
        System.out.println(women.getRequest());
        System.out.println("Father's reply is OK.");
    }
}