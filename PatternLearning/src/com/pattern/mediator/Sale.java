package com.pattern.mediator;

import java.util.Random;

public class Sale extends AbstractColleague {

    public Sale(AbstractMediator mediator) {
        super(mediator);
    }

    public void sellIbmComputer(int number) {
        mMediator.execute("sale.sell", number);
        System.out.println("sell IBM computer " + number);
    }

    public int getSalesStatus() {
        Random random = new Random(System.currentTimeMillis());
        int saleStatus = random.nextInt(100);
        System.out.println("Sale status is " + saleStatus);
        return saleStatus;
    }

    public void offSale() {
        mMediator.execute("sale.offsail");
    }
}
