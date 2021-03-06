package com.pattern.singleInstance;

public class Minister {

    public static void main(String[] args) {

        System.out.println("----- See the unique emperor -----");
        for (int day = 0; day < 3; day++) {
            Emperor emperor = Emperor.getInstance();
            emperor.say();
        }

        System.out.println();

        System.out.println("----- See the multiple emperor -----");
        int ministerNumber = 5;
        for (int i = 0; i < ministerNumber; i++) {
            MultiEmperor emperor = MultiEmperor.getinstance();
            System.out.print("Minister " + i + " see emperor.");
            emperor.say();
        }
    }
}
