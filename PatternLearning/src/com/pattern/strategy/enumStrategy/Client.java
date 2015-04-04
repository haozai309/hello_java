package com.pattern.strategy.enumStrategy;

public class Client {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int result = Calculator.ADD.exec(a, b);
        System.out.println("Result is " + result);
    }

}
