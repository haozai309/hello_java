package com.pattern.mediator;

/*
 * Mediator pattern: define an object that encapsulates how a set of objects interact. This pattern
 * promotes loose coupling by keeping objects from referring to each other explicitly, and it lets
 * you vary their interaction independently.
 */
public abstract class AbstractMediator {

    protected Purchase mPurchase;
    protected Sale mSale;
    protected Stock mStock;

    public AbstractMediator() {
        mPurchase = new Purchase(this);
        mSale = new Sale(this);
        mStock = new Stock(this);
    }

    public abstract void execute(String str, Object... objects);
}
