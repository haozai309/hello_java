package com.pattern.mediator;

public class Stock {

    private static int COMPUTER_NUMBER = 100;

    public void increase(int number) {
        COMPUTER_NUMBER += number;
        System.out.println("Stock number is " + COMPUTER_NUMBER);
    }

    public void decrease(int number) {
        COMPUTER_NUMBER -= number;
        System.out.println("Stock number is " + COMPUTER_NUMBER);
    }

    public int getStockNumber() {
        return COMPUTER_NUMBER;
    }

    public void clearStock() {
        Purchase purchase = new Purchase();
        Sale sale = new Sale();
        System.out.println("Clear " + COMPUTER_NUMBER);
        sale.offSale();
        purchase.refuseBuyIbm();
    }
}
