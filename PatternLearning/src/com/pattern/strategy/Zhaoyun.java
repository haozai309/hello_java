package com.pattern.strategy;

public class Zhaoyun {

    public static void main(String[] args) {
        Context context;

        System.out.println("-- 1st one, arrive to East Wu");
        context = new Context(new BackDoor());
        context.operate();

        System.out.println();
        System.out.println("-- 2nd one, Liu forget home");
        context = new Context(new GiveGreenLight());
        context.operate();

        System.out.println();
        System.out.println("-- 3rd one, enemy on the way");
        context = new Context(new BlockEnemy());
        context.operate();
    }

}
