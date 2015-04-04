package com.pattern.chain;

public class Husband extends Handler {

    public Husband() {
        super(Handler.HUSBAND_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("----- Wife give request to husband -----");
        System.out.println(women.getRequest());
        System.out.println("Husband's reply is OK.");
    }

}
