package com.pattern.adapter;

public class UserInfo implements IUserInfo {

    @Override
    public String getUserName() {
        System.out.println("user name: Chang");
        return null;
    }

    @Override
    public String getHomeAddress() {
        System.out.println("home address: CD");
        return null;
    }

    @Override
    public String getMobileNumber() {
        System.out.println("mobile number: 023");
        return null;
    }

    @Override
    public String getOfficeTelNumber() {
        System.out.println("office telephone number: 722");
        return null;
    }

    @Override
    public String getJobPosition() {
        System.out.println("job position: XXX");
        return null;
    }

    @Override
    public String getHomeTelNumber() {
        System.out.println("home telephone number: 377");
        return null;
    }

}
