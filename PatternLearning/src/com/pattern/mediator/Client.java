package com.pattern.mediator;

public class Client {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();

        System.out.println("----- Buy new computers ---");
        Purchase purchase = new Purchase(mediator);
        purchase.buyIbmComputer(100);
        System.out.println("--- Sell computers ---");
        Sale sale = new Sale(mediator);
        sale.sellIbmComputer(1);
        System.out.println("--- clear stock ---");
        Stock stock = new Stock(mediator);
        stock.clearStock();
    }

}
