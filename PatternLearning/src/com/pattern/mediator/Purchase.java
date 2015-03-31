package com.pattern.mediator;

public class Purchase extends AbstractColleague {

    public Purchase(AbstractMediator mediator) {
        super(mediator);
    }

    public void buyIbmComputer(int number) {
        mMediator.execute("purchase.buy", number);
    }

    public void refuseBuyIbm() {
        System.out.println("Refuse to buy any more computers.");
    }
}
