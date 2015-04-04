package com.pattern.chain;

import java.util.ArrayList;
import java.util.Random;

public class Client {

    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<IWomen> arrayList = new ArrayList<IWomen>();
        for (int i = 0; i < 5; ++i) {
            arrayList.add(new Women(rand.nextInt(4), "want to go shopping"));
        }

        IHandler father = new Father();
        IHandler husband = new Husband();
        IHandler son = new Son();
        for (IWomen women : arrayList) {
            switch (women.getType()) {
            case 1:
                System.out.println("----- Father -----");
                father.handleMessage(women);
                break;
            case 2:
                System.out.println("----- Husband -----");
                husband.handleMessage(women);
                break;
            case 3:
                System.out.println("----- Son -----");
                son.handleMessage(women);
                break;
            default:
                System.out.println("----- do nothing. -----");
                break;
            }
        }
    }

}
