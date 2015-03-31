package com.pattern.mediator;

public class Client {

    public static void main(String[] args) {
        System.out.println("----- Buy new computers ---");
        Purchase purchase = new Purchase();
        purchase.buyIbmComputer(100);
        System.out.println("--- Sell computers ---");
        Sale sale = new Sale();
        sale.sellIbmComputer(1);
        System.out.println("--- clear stock ---");
        Stock stock = new Stock();
        stock.clearStock();
    }

}
