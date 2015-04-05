package com.pattern.adapter;

public class Client {

    public static void main(String[] args) {
        System.out.println("--- user info ---");
        IUserInfo youngGirl = new UserInfo();
        for (int i = 0; i < 6; i++) {
            youngGirl.getMobileNumber();
        }

        System.out.println("--- outer user info ---");
        youngGirl = new OuterUserInfo();
        for (int i = 0; i < 6; i++) {
            System.out.println(youngGirl.getMobileNumber());
        }
    }

}
