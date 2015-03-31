package com.pattern.mediator;

import java.util.Random;

public class Sale {

    public void sellIbmComputer(int number) {
        Stock stock = new Stock();

        Purchase purchase = new Purchase();
        if (stock.getStockNumber() < number) {
            purchase.buyIbmComputer(number);
        }

        System.out.println("sell IBM computer " + number);
        stock.decrease(number);
    }

    public int getSalesStatus() {
        Random random = new Random(System.currentTimeMillis());
        int saleStatus = random.nextInt(100);
        System.out.println("Sale status is " + saleStatus);
        return saleStatus;
    }

    public void offSale() {
        Stock stock = new Stock();
        System.out.println("Off sale to sell computer " + stock.getStockNumber());
    }
}
