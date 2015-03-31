package com.pattern.mediator;

public class Purchase {

    public void buyIbmComputer(int number) {
        Stock stock = new Stock();
        Sale sale = new Sale();

        int salesStatus = sale.getSalesStatus();
        if (salesStatus > 80) {
            System.out.println("Buy IBM computer " + number);
            stock.increase(number);
        } else {
            int buyNumber = number / 2;
            System.out.println("Buy IBM computer " + buyNumber);
            stock.increase(buyNumber);
        }
    }

    public void refuseBuyIbm() {
        System.out.println("Refuse to buy any more computers.");
    }
}
