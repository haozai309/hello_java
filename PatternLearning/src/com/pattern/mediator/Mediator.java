package com.pattern.mediator;

public class Mediator extends AbstractMediator {

    @Override
    public void execute(String str, Object... objects) {
        if (str.equals("purchase.buy")) {
            buyComputer((Integer) objects[0]);
        } else if (str.equals("sale.sell")) {
            sellComputer((Integer) objects[0]);
        } else if (str.equals("sale.offsale")) {
            offSale();
        } else if (str.equals("stock.clear")) {
            clearStock();
        }
    }

    private void buyComputer(int number) {
        int salesStatus = mSale.getSalesStatus();
        if (salesStatus > 80) {
            System.out.println("Buy IBM computer " + number);
            mStock.increase(number);
        } else {
            int buyNumber = number / 2;
            System.out.println("Buy IBM computer " + buyNumber);
            mStock.increase(buyNumber);
        }
    }

    private void sellComputer(int number) {
        if (mStock.getStockNumber() < number) {
            mPurchase.buyIbmComputer(number);
        }
        mStock.decrease(number);
    }

    private void offSale() {
        System.out.println("Off sale to sell computer " + mStock.getStockNumber());
    }

    private void clearStock() {
        mSale.offSale();
        mPurchase.refuseBuyIbm();
    }
}
